<template>
  <div class="wrapper">

    <h1 v-if="action === 'role'">角色名称：<span class="red-color">{{params && params.name}}</span></h1>
    <h1 v-else>用户名称：<span class="red-color">{{params && params.name}}</span></h1>

    <!-- 城市管理员 城市列表 -->
    <div v-if="cityList.length" class="city-radio-group">
      <span>授权城市</span>
      <el-radio-group v-model="cityRadio" @change="handleCityRadioChange">
        <el-radio v-for="item in cityList" :label="item.cityCode">{{item.cityName}}</el-radio>
      </el-radio-group>
    </div>

    <el-tabs v-loading="loadingView" class="tab-content" v-model="tabActive">
      <!-- 一级菜单 -->
      <el-tab-pane v-for="(menu1, menu1Index) in menuData" :key="menu1.id" :label="menu1.name" :name="String(menu1.id)">

        <el-row>
          <!-- 左侧菜单导航 -->
          <el-col :span="5" class="menu-content">
            <el-menu
              default-active="0-0"
            >

              <template v-if="menu1.datas && menu1.datas.length" v-for="(menu2, menu2Index) in menu1.datas">
                <!-- 二级菜单 -->
                <el-submenu :key="menu2.id" v-if="menu2.datas && menu2.datas.length" :index="String(menu2Index)">
                  <template slot="title">{{menu2.name}}</template>

                  <!-- 三级菜单 -->
                  <el-menu-item v-for="(menu3, menu3Index) in menu2.datas"
                                v-if="menu3.level && menu3.level === 3" :key="menu3.id"
                                :index="menu2Index + '-' + menu3Index">
                    <a class="anchor" href="javascript:void(0)" @click="goAnchor('anchor' + menu3.id, menu1Index)">{{menu3.name}}</a>
                  </el-menu-item>

                </el-submenu>

                <!-- 二级菜单 -->
                <el-menu-item :key="menu2.id" v-else :index="String(menu2Index)">
                  <a class="anchor" href="javascript:void(0)"
                     @click="goAnchor('anchor' + menu2.id)">{{menu2.name}}</a>
                </el-menu-item>
              </template>

            </el-menu>
          </el-col>

          <!-- 右侧权限信息 -->
          <el-col :span="19">
            <ul :id="'perEdit' + menu1Index" class="permission-edit" v-if="menu1.datas && menu1.datas.length">
              <!-- 二级菜单 -->
              <template v-for="menu2 in menu1.datas">

                <!-- 三级菜单 -->
                <template v-if="menu2.datas && menu2.datas.length" v-for="menu3 in menu2.datas">

                  <li class="clearfix" :key="menu3.id" v-if="menu3.level && menu3.level === 3">
                    <span class="label">
                      <el-tag :id="'anchor' + menu3.id" :type="('anchor' + menu3.id) === currentAnchor ? '' : 'info'">{{menu3.name}}</el-tag>
                    </span>

                    <!-- 菜单按钮 -->
                    <div class="content">
                      <!-- 普通用户 -->
                      <template v-if="roleGroup === '03'">

                        <template v-if="menu3.datas && menu3.datas.length" v-for="subItemBtn in menu3.datas">

                          <template v-if="subItemBtn.functionType === 'detailButton'">

                            <!-- 查看  判断三级菜单数据权限标识  -->
                            <!--
                              v-model="form[item.id + '_' +data.id + '_' + subItem.id]"
                              根据 菜单父子ID拼接绑定表单值 例：1_4_9
                              如果是查看项 取一级 + 二级 + 三级 其他项取一级 + 二级 + 三级 + 四级
                            -->
                            <label
                              v-if="!menu3.dataPerFlag"
                              class="checkbox item"
                            >
                              <input data-type="checkbox" :ref="menu1.id + '_' +menu2.id + '_' + menu3.id" v-model="form[menu1.id + '_' +menu2.id + '_' + menu3.id]" type="checkbox" />
                              <span>{{subItemBtn.name}}</span>
                            </label>

                            <div v-else class="select-box item">

                              <select
                                v-model="form[menu1.id + '_' +menu2.id + '_' + menu3.id]"
                                :ref="menu1.id + '_' +menu2.id + '_' + menu3.id"
                                class="select"
                                data-type="select"
                              >
                                <option
                                  label="禁止"
                                  :value="undefined"
                                >
                                </option>
                                <option
                                  label="所有"
                                  :value="0"
                                >
                                </option>
                                <option
                                  label="本市"
                                  :value="1"
                                >
                                </option>
                                <option
                                  label="本大区"
                                  :value="2"
                                >
                                </option>
                                <option
                                  label="本区"
                                  :value="3"
                                >
                                </option>
                                <option
                                  label="本店"
                                  :value="4"
                                >
                                </option>
                                <option
                                  label="本组"
                                  :value="5"
                                >
                                </option>
                                <option
                                  label="本人"
                                  :value="7"
                                >
                                </option>
                              </select>

                              <span class="label">{{subItemBtn.name}}</span>
                            </div>

                          </template>

                          <!-- 唯一按钮 -->
                          <label
                            v-if="subItemBtn.functionType === 'onlyButton'"
                            class="item checkbox"
                          >
                            <input data-type="checkbox" :ref="menu1.id + '_' +menu2.id + '_' + menu3.id + '_' + subItemBtn.id" v-model="form[menu1.id + '_' +menu2.id + '_' + menu3.id + '_' + subItemBtn.id]" type="checkbox" />
                            <span>{{subItemBtn.name}}</span>
                          </label>

                          <!-- 重复按钮 -->
                          <div v-else-if="subItemBtn.functionType === 'multipleButton'" class="select-box item">

                            <select
                              v-model="form[menu1.id + '_' +menu2.id + '_' + menu3.id + '_' + subItemBtn.id]"
                              :value="-1"
                              :ref="menu1.id + '_' +menu2.id + '_' + menu3.id + '_' + subItemBtn.id"
                              class="select"
                              data-type="select"
                            >
                              <option
                                label="禁止"
                                :value="undefined"
                              >
                              </option>
                              <option
                                label="所有"
                                :value="0"
                              >
                              </option>
                              <option
                                label="本市"
                                :value="1"
                              >
                              </option>
                              <option
                                label="本大区"
                                :value="2"
                              >
                              </option>
                              <option
                                label="本区"
                                :value="3"
                              >
                              </option>
                              <option
                                label="本店"
                                :value="4"
                              >
                              </option>
                              <option
                                label="本组"
                                :value="5"
                              >
                              </option>
                              <option
                                label="本人"
                                :value="7"
                              >
                              </option>
                            </select>

                            <span class="label">{{subItemBtn.name}}</span>
                          </div>

                        </template>

                        <!-- 条件按钮 -->
                        <template v-if="combinationBtns[menu3.id] && Object.keys(combinations).length">
                          <table cellspacing="0" cellpadding="0" border="0" class="combinations-table">
                            <colgroup>
                              <col width="80">
                            </colgroup>

                            <thead>
                            <tr>
                              <th>事项</th>
                              <th v-for="combination in combinations[menu3.id]" :key="combination.id">
                                <div><label>{{combination.name}}</label></div>

                                <el-dropdown size="medium" trigger="click" @command="handleCommand" style="cursor: pointer;color: #409eff;">
                                  <span class="el-dropdown-link">
                                    批量操作<i class="el-icon-arrow-down el-icon--right"></i>
                                  </span>

                                  <el-dropdown-menu slot="dropdown">
                                    <el-dropdown-item
                                      v-for="item in selectOpts"
                                      :command="{combination: combination, menuIds: [menu1.id, menu2.id, menu3.id], val: item.value}"
                                      :key="item.value"
                                    >
                                      {{item.label}}
                                    </el-dropdown-item>
                                  </el-dropdown-menu>
                                </el-dropdown>

                              </th>
                            </tr>
                            </thead>

                            <tbody>
                            <tr v-for="combinationBtn in combinationBtns[menu3.id]" :key="combinationBtn.id">

                              <td>{{combinationBtn.name}}</td>

                              <td v-for="combination in combinations[menu3.id]" :key="combination.id">
                                <div v-if="combination.relationBtn.indexOf(combinationBtn.id) === -1">--</div>

                                <div v-else>
                                  <select
                                    v-model="form[menu1.id + '_' +menu2.id + '_' + menu3.id + '_' + combinationBtn.id + '@@' + combination.id]"
                                    :ref="menu1.id + '_' +menu2.id + '_' + menu3.id + '_' + combinationBtn.id + '@@' + combination.id"
                                    class="select"
                                    data-type="select"
                                  >
                                    <option
                                      label="禁止"
                                      :value="undefined"
                                    >
                                    </option>
                                    <option
                                      label="所有"
                                      :value="0"
                                    >
                                    </option>
                                    <option
                                      label="本市"
                                      :value="1"
                                    >
                                    </option>
                                    <option
                                      label="本大区"
                                      :value="2"
                                    >
                                    </option>
                                    <option
                                      label="本区"
                                      :value="3"
                                    >
                                    </option>
                                    <option
                                      label="本店"
                                      :value="4"
                                    >
                                    </option>
                                    <option
                                      label="本组"
                                      :value="5"
                                    >
                                    </option>
                                    <option
                                      label="本人"
                                      :value="7"
                                    >
                                    </option>
                                  </select>

                                </div>

                              </td>

                            </tr>
                            </tbody>

                          </table>
                        </template>
                      </template>

                      <!-- 超级管理员 城市管理员 -->
                      <template v-else>
                        <template v-if="menu3.datas && menu3.datas.length" v-for="subItemBtn in menu3.datas">
                          <template v-if="subItemBtn.functionType === 'detailButton'">

                            <label
                              class="checkbox item"
                            >
                              <input data-type="checkbox" :ref="menu1.id + '_' +menu2.id + '_' + menu3.id" v-model="form[menu1.id + '_' +menu2.id + '_' + menu3.id]" type="checkbox" />
                              <span>{{subItemBtn.name}}</span>
                            </label>

                          </template>

                          <template v-else>

                            <label
                              class="checkbox item"
                            >
                              <input
                                data-type="checkbox"
                                :ref="menu1.id + '_' +menu2.id + '_' + menu3.id + '_' + subItemBtn.id"
                                v-model="form[menu1.id + '_' +menu2.id + '_' + menu3.id + '_' + subItemBtn.id]" type="checkbox" />
                              <span>{{subItemBtn.name}}</span>
                            </label>

                          </template>
                        </template>
                      </template>

                    </div>
                  </li>

                </template>

              </template>
            </ul>
          </el-col>
        </el-row>

      </el-tab-pane>
    </el-tabs>

    <div class="btn-group">
      <el-button :loading="loadingBtn" @click="handleSubmit" type="primary">确定</el-button>
      <el-button @click="handleCancel">取消</el-button>
    </div>
  </div>
</template>

<script>
import {saveAdminPermissionInfo, saveCityPermissionInfo, saveOrdinaryPermissionInfo, getOrdinaryPermission, getAdminPermission, cityPermission, getCityPermission} from '@/request/manage'

const selectOpts = [
  {
    label: '禁止',
    value: undefined // 设置该项是为了设置下拉框默认值为禁用
  },
  {
    label: '所有',
    value: '0'
  },
  {
    label: '本市',
    value: '1'
  },
  {
    label: '本大区',
    value: '2'
  },
  {
    label: '本区',
    value: '3'
  },
  {
    label: '本店',
    value: '4'
  },
  {
    label: '本组',
    value: '5'
  },
  {
    label: '本人',
    value: '7'
  }
]

const ROLE_GROUPS = {
  ordinary: '03',
  admin: '01',
  city: '02',
}

export default {
  name: 'ThePermissionManage',
  props: {
    params: {
      type: Object,
      required: true
    },
    action: {
      type: String,
      default: 'role'
    }
  },
  data () {
    return {
      tabActive: '1',
      checkList: [],
      menuData: [],
      selectOpts: selectOpts,
      form: {},
      loadingView: false,
      loadingBtn: false,
      roleGroup: this.params.roleGroup,
      combinations: {},
      combinationBtns: {},
      emptyForm: {},
      cityRadio: '',
      cityList: [], // 城市管理员 授权城市列表
      currentCityMenus: [],
      currentAnchor: '',
      originalFormData: {}
    }
  },
  methods: {
    handleSubmit () {
      let data = {}
      Object.keys(this.form).forEach(item => {
        if (this.form[item] === true) {
          data[item] = 'on'
        } else if (this.form[item] === 0) {
          data[item] = ''
        } else if (this.form[item] === false) {

        } else {
          data[item] = this.form[item]
        }
      })
      let params = {}
      params.data = JSON.stringify(data)
      params.roleId = this.params.roleId
      params.userId = this.params.userId
      this.$confirm('确定保存该操作？, 是否继续?', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.loadingBtn = true

        let roleGroup = this.params.roleGroup

        // let message = {
        //   sourceCode: this.params.name, // 资源标识
        //   operatTypeId: this.$DICT_CODE.LOG.OPERATE_TYPE.AUTHORIZATION, // 操作类型
        //   labelData: this.$utils.getFormFields(this.$refs['form']), // 修改字段对应的label
        //   originalData: this.originalFormData, // 原始表单对象
        //   newData: params // 修改后的表单对象
        // }

        // if (this.action === 'user') {
        //   message.sourceTypeId = this.$DICT_CODE.LOG.SOURCE_TYPE.MANAGE.USER
        // } else {
        //   message.sourceTypeId = this.$DICT_CODE.LOG.SOURCE_TYPE.MANAGE.ROLE
        // }

        if (roleGroup === ROLE_GROUPS.admin) {

          saveAdminPermissionInfo(params).then(res => {
            this.$message({
              type: 'success',
              message: res.msg || '操作成功'
            })
            this.loadingBtn = false
            this.$emit('handleClick', 1)

            // this.$updateLog.manage.manageUpdateLog({message: JSON.stringify(message)})
          }).catch(() => {
            this.loadingBtn = false
          })

        } else if (roleGroup === ROLE_GROUPS.city) {
          let _params = Object.assign({}, params, {
            responsibilityCity: this.cityRadio
          })
          saveCityPermissionInfo(_params).then(res => {
            this.$message({
              type: 'success',
              message: res.msg || '操作成功'
            })
            this.loadingBtn = false
            this.$emit('handleClick', 1)

            // this.$updateLog.manage.manageUpdateLog({message: JSON.stringify(message)})
          }).catch(() => {
            this.loadingBtn = false
          })

        } else if (roleGroup === ROLE_GROUPS.ordinary) {

          saveOrdinaryPermissionInfo(params).then(res => {
            this.$message({
              type: 'success',
              message: res.msg || '操作成功'
            })
            this.loadingBtn = false
            this.$emit('handleClick', 1)

            // this.$updateLog.manage.manageUpdateLog({message: JSON.stringify(message)})
          }).catch(() => {
            this.loadingBtn = false
          })

        }

      })
    },

    handleCancel () {
      this.$emit('handleClick', 2)
    },

    handleCommand (data) {
      let {combination, menuIds, val} = data
      combination.relationBtn.split(',').forEach(combinationBtnId => {
        this.form[menuIds[0] + '_' + menuIds[1] + '_' + menuIds[2] + '_' + combinationBtnId + '@@' + combination.id] = val
        this.$refs[menuIds[0] + '_' + menuIds[1] + '_' + menuIds[2] + '_' + combinationBtnId + '@@' + combination.id][0].value = val

        if (val === undefined) {
          this.$refs[menuIds[0] + '_' + menuIds[1] + '_' + menuIds[2] + '_' + combinationBtnId + '@@' + combination.id][0].selectedIndex = 0
        }

      })

    },

    // 切换城市
    handleCityRadioChange () {
      this.loadingView = true

      let params = {
        permission: this.cityRadio,
        roleId: this.params.roleId,
        userId: this.params.userId
      }

      getCityPermission(params).then(res => {
        let temp = {
          per: res || {},
          menus: this.currentCityMenus
        }
        this._setData(temp, true)
        this.loadingView = false
      })

    },

    _loadData () {
      this.loadingView = true
      let {roleId, userId, roleGroup} = this.params
      let params = {}
      params.roleId = roleId
      params.userId = userId

      if (roleGroup === ROLE_GROUPS.ordinary) {

        getOrdinaryPermission(params).then(res => {
          this._setData(res)
          this.combinationBtns = res.combinationBtns
          this.combinations = res.combinations
          this.loadingView = false
        })

      } else if (roleGroup === ROLE_GROUPS.city) {

        cityPermission(params).then(res => {
          this.currentCityMenus = res['menus'] || []

          // 获取授权城市列表
          this.cityList = res['responsibility']

          // 默认选中第一项
          if (this.cityList.length) {
            this.cityRadio = this.cityList[0].cityCode
          }

          let _params = Object.assign({}, params, {
            permission: this.cityRadio
          })

          getCityPermission(_params).then(res => {
            let temp = {
              per: res || {},
              menus: this.currentCityMenus
            }
            this._setData(temp)
            this.loadingView = false
          })
        })

      } else if (roleGroup === ROLE_GROUPS.admin) {

        getAdminPermission(params).then(res => {
          this._setData(res)
          this.loadingView = false
        })

      }
    },

    _setData (data, noFormat) {
      let {menus, per} = data
      this.tabActive = String(menus[0].id)
      this.menuData = menus

      if (!noFormat) {
        this._menuDataFormat(this.menuData)
      }

      this._setForm(per)
    },

    _menuDataFormat (data) {
      for (let i = 0; i < data.length; i++) {
        if (data[i].level && data[i].level === 3) {
          data[i].datas.unshift({
            name: '查看',
            level: 4,
            functionType: 'detailButton'
          })
        }

        if (data[i].datas && data[i].datas.length) {
          this._menuDataFormat(data[i].datas)
        }
      }
    },

    _setForm (data) {
      let _data = Object.assign({}, data)
      this.originalFormData = Object.assign({}, _data)

      // 城市管理员
      if (this.params.roleGroup === ROLE_GROUPS.city) {

        this.$nextTick(() => {
          Object.keys(_data).forEach(item => {

            if (_data[item]) {

              if (this.$refs[item][0].getAttribute('data-type') === 'checkbox') {
                _data[item] = true
              } else if (this.$refs[item][0].getAttribute('data-type') === 'select') {
                _data[item] = 0
              }

            }

          })
        })

      } else {

        this.$nextTick(() => {
          Object.keys(_data).forEach(item => {
            if (_data[item] === 'on') {
              if (this.$refs[item][0].getAttribute('data-type') === 'checkbox') {
                _data[item] = true
              } else if (this.$refs[item][0].getAttribute('data-type') === 'select') {
                _data[item] = 0
              }
            }
          })
        })


      }

      this.form = _data
    },

    // 锚点定位
    goAnchor (selector, menu1Index) {
      this.currentAnchor = selector
      const anchor = this.$el.querySelector('#' + selector)
      const scrollW = document.querySelectorAll('.permission-edit')[menu1Index]
      // this.$utils.scroll(scrollW, anchor.offsetTop)
      scrollW.scrollTop = anchor.offsetTop
    }
  },
  mounted () {
    this._loadData()
  }
}
</script>

<style lang="scss" scoped>

  .wrapper {
    /*min-height: 700px;*/
    h1 {
      margin-bottom: 20px;
      font-size: 16px;
      text-align: center;
    }
  }

  .city-radio-group {
    margin-bottom: 15px;

    span {
      margin-right: 15px;
    }
  }

  .tab-content {
    min-height: 600px;
  }

  .menu-content {
    max-height: 545px;
    min-height: 545px;
    overflow-y: auto;
  }

  .permission-edit {
    max-height: 545px;
    min-height: 545px;
    overflow-y: auto;
    li {
      padding: 15px 10px 0 10px;
      line-height: 20px;
      border-bottom: 1px dashed #ccc;
      display: flex;
      .label {
        margin-right: 15px;
      }

      .content {

        .item {
          margin-bottom: 15px;
        }

        .select-box {
          display: inline-block;
          font-size: 12px;
          vertical-align: top;

          .select {
            width: 75px;
            margin-right: 5px;
          }
        }

        .checkbox {
          margin-left: 0;
          margin-right: 15px;
        }
      }
    }
  }

  .btn-group {
    margin-top: 20px;
  }

  .anchor {
    display: block;
  }

  .combinations-table {
    text-align: center;
    margin-bottom: 15px;
    border: 1px solid #ebeef5;

    th {
      border-bottom: 1px solid #ebeef5;
      white-space: nowrap;
      overflow: hidden;
      user-select: none;
      text-align: left;
      background-color: #fff;
      padding: 12px 10px;
      min-width: 0;
      box-sizing: border-box;
      text-overflow: ellipsis;
      vertical-align: middle;
      position: relative;
      font-size: 12px;

      div {
        font-size: 12px;
        margin-bottom: 8px;
      }
    }

    td {
      padding: 12px 5px;
      transition: background-color .25s ease;
      border-bottom: 1px solid #ebeef5;
      font-size: 12px;
    }

    tr:hover {
      background: #f2ffec;
    }
  }

  .select {
    appearance: none;
    -moz-appearance: none;
    -webkit-appearance: none;
    padding: 0 20px 0 10px;
    background: url('./arrow.png') no-repeat scroll 55px center transparent;
    height: 28px;
    line-height: 28px;
    border: 1px solid #b3b3b3;
    border-radius: 5px;
    -moz-border-radius: 5px;
    -webkit-border-radius: 5px;
    width: 75px;
    font-size: 12px;
  }

  .checkbox {
    display: inline-block;

    input[type="checkbox"] {
      appearance: none;
      -webkit-appearance: none;
      outline: none;
      display: none;

      &:checked+span {
        background: url(./checked.png) no-repeat left center;
      }
    }

    span {
      color: #666666;
      font-size: 12px;
      float: left;
      display: inline-block;
      padding: 4px 0 4px 20px;
      height: 25px;
      line-height: 20px;
      background: url('./check.png') no-repeat left;
      cursor: pointer;
      margin-left: 10px;
    }

  }

</style>
