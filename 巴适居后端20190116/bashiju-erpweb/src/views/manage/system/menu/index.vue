<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form">
        <el-form size="small" :inline="true" :model="queryForm" ref="queryForm" class="demo-form-inline">
          <el-form-item label="菜单名称" prop="menuName">
            <el-input v-model="queryForm.menuName" placeholder="请输入角色名称"></el-input>
          </el-form-item>

          <el-form-item label="菜单类型" prop="functionType">
            <el-select style="width: 100%" v-model="queryForm.functionType" clearable placeholder="选择菜单类型">
              <el-option
                v-for="item in functionTypeOpts"
                :key="item.label"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="菜单等级" prop="level">
            <el-select clearable style="width: 100%" v-model="queryForm.level" placeholder="请选择菜单等级">
              <el-option
                v-for="item in menuLevelOpts"
                :key="item.label"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click.native.prevent="handleQuery" :loading="queryBtnLoading">查询</el-button>
            <el-button @click.native.prevent="handleReset">重置</el-button>
          </el-form-item>

          <el-form-item class="pull-right">
            <el-button type="primary" @click="handleAdd(3)">新增</el-button>
          </el-form-item>

        </el-form>
      </div>
    </div>

    <div class="page-content-bd">
      <div class="wrapper">
        <el-row :gutter="20">
          <el-col :span="10">
            <div class="grid-content bg-purple tree-wrap">
              <base-tree
                :data="treeData"
                ref="baseTree"
                @nodeClick="nodeClick"
              >
              </base-tree>
            </div>
          </el-col>

          <el-col :span="14" v-show="currentNodeData">
            <div class="grid-content bg-purple">
              <div class="edit-form-wrap">
                <el-form :model="form" :rules="formRules" ref="form" label-width="100px">
                  <el-form-item label="名称" prop="name">
                    <el-input v-model="form.name" placeholder="请输入名称"></el-input>
                  </el-form-item>

                  <el-form-item label="菜单等级" prop="level">
                    <el-select clearable style="width: 100%" v-model="form.level" placeholder="请选择菜单等级">
                      <el-option
                        v-for="item in menuLevelOpts"
                        :key="item.label"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </el-form-item>

                  <el-form-item label="菜单类型" prop="functionType">

                    <el-select clearable style="width: 100%" v-model="form.functionType" placeholder="选择菜单类型">
                      <el-option
                        v-for="item in functionTypeOpts"
                        :key="item.label"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>

                  </el-form-item>

                  <el-form-item v-if="form.functionType === 'menu' || form.level !== 4" label="菜单路径" prop="url">
                    <el-input v-model="form.url" placeholder="请输入菜单路径"></el-input>
                  </el-form-item>

                  <el-form-item v-if="form.functionType !== 'menu' || form.level === 4" label="按钮名称" prop="btnName">
                    <el-input v-model="form.btnName" placeholder="请输入按钮名称"></el-input>
                  </el-form-item>

                  <el-form-item label="可视角色组" prop="roleGroup">
                    <el-select style="width: 100%" v-model="form.roleGroup" multiple placeholder="所有">
                      <el-option
                        v-for="item in roleGroupOpts"
                        :key="item.label"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>

                  </el-form-item>

                  <el-form-item label="顺序" prop="sortNo">
                    <el-input-number :min="0" size="medium" v-model="form.sortNo"></el-input-number>
                  </el-form-item>

                  <el-form-item v-if="form.level === 3" label="数据权限标识" prop="dataPerFlag">
                    <el-switch v-model="form.dataPerFlag"></el-switch>
                  </el-form-item>
                  <el-form-item size="small">
                    <el-button type="primary" :loading="submitLoading" @click.native.prevent="submitForm">保存</el-button>
                    <el-button @click.native.prevent="handleAdd(1)">添加同级</el-button>
                    <el-button @click.native.prevent="handleAdd(2)">添加子级</el-button>
                    <el-button type="primary" plain @click.native.prevent="handleDelete">删除</el-button>
                    <el-button v-if="form.level === 3" type="primary" plain @click="handleCombination">数据组合</el-button>
                  </el-form-item>
                </el-form>
              </div>
            </div>
          </el-col>

        </el-row>
      </div>
    </div>

    <template>
      <b-dialog title="新增菜单" width="500px" :show.sync="addMenuDialogVisible">
        <div class="edit-form-wrap">
          <el-form :model="form" :rules="formRules" ref="form" label-width="100px">
            <el-form-item label="名称" prop="name">
              <el-input v-model="form.name" placeholder="请输入名称"></el-input>
            </el-form-item>

            <el-form-item label="菜单等级" prop="level">
              <el-select clearable style="width: 100%" v-model="form.level" placeholder="请选择菜单等级">
                <el-option
                  v-for="item in menuLevelOpts"
                  :key="item.label"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="菜单类型" prop="functionType">

              <el-select clearable style="width: 100%" v-model="form.functionType" placeholder="选择菜单类型">
                <el-option
                  v-for="item in functionTypeOpts"
                  :key="item.label"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>

            </el-form-item>

            <el-form-item v-if="form.functionType === 'menu' || form.level !== 4" label="菜单路径" prop="url">
              <el-input v-model="form.url" placeholder="请输入菜单路径"></el-input>
            </el-form-item>

            <el-form-item v-if="form.functionType !== 'menu' || form.level == 4" label="按钮名称" prop="btnName">
              <el-input v-model="form.btnName" placeholder="请输入按钮名称"></el-input>
            </el-form-item>

            <el-form-item label="可视角色组" prop="roleGroup">
              <el-select style="width: 100%" v-model="form.roleGroup" multiple placeholder="所有">
                <el-option
                  v-for="item in roleGroupOpts"
                  :key="item.label"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>

            </el-form-item>

            <el-form-item label="顺序" prop="sortNo">
              <el-input-number :min="0" size="medium" v-model="form.sortNo"></el-input-number>
            </el-form-item>

            <el-form-item v-if="form.level === 3" label="数据权限标识" prop="dataPerFlag">
              <el-switch v-model="form.dataPerFlag"></el-switch>
            </el-form-item>

            <el-form-item size="small">
              <el-button type="primary" :loading="submitLoading" @click.native.prevent="submitForm">保存</el-button>
              <el-button @click.native.prevent="handleAdd(1)">添加同级</el-button>
              <el-button @click.native.prevent="handleAdd(2)">添加子级</el-button>
            </el-form-item>
          </el-form>
        </div>
      </b-dialog>
      <data-combination v-if="combinationDialogVisible" :menuId="currentNodeData && currentNodeData.id" :show.sync="combinationDialogVisible"></data-combination>
    </template>
  </div>
</template>

<script>
import BaseTree from '@/components/BaseTree'
import DataCombination from './DataCombination'
import {getMenuManageListData, saveOrUpdateMenu, delMenu, menuPageBaseInfo} from '@/request/manage'
export default {
  name: 'menuManage',
  components: {BaseTree, DataCombination},
  data () {
    return {
      submitLoading: false,
      queryForm: {
        menuName: '',
        level: '',
        functionType: ''
      },
      form: {
        name: '',
        url: '',
        sortNo: '',
        roleGroup: [],
        dataPerFlag: '',
        functionType: '',
        level: '',
        btnName: ''
      },
      formRules: {
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' }
        ],
        url: [
          { required: true, message: '请url地址', trigger: 'blur' }
        ],
        sortNo: [
          { required: true, message: '请输入顺序', trigger: 'change' }
        ],
        level: [
          { required: true, message: '请选择菜单等级', trigger: 'change' }
        ],
        btnName: [
          { required: true, message: '请选择按钮名称', trigger: 'blur' }
        ]
      },
      treeData: [],
      listQuery: {
        page: 0,
        limit: 10,
        total: 0
      },
      queryBtnLoading: false,
      roleGroupOpts: [],
      functionTypeOpts: [],
      menuLevelOpts: [],
      currentNodeData: null,
      menuAddAction: null,
      addMenuDialogVisible: false,
      combinationDialogVisible: false
    }
  },
  methods: {
    handleQuery () {
      this._getMenuData(true)
    },
    handleReset () {
      this.$refs['queryForm'].resetFields()
    },
    nodeClick (node) {
      this.currentNodeData = node
      this._setForm(node)
    },
    handleAdd (action) {
      this._resetForm()
      this.menuAddAction = action
      if (action === 3) {
        this.currentNodeData = null
        this.addMenuDialogVisible = true
      }
    },
    handleDelete () {
      this.$confirm('确定删除该条数据吗?', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const param = {
          menuId: this.currentNodeData.id
        }
        delMenu(param).then(res => {
          if (res.success) {
            this.$message({
              type: 'success',
              message: res.msg || '操作成功'
            })
            this._getMenuData(false)
            this.currentNodeData = null
          } else {
            this.$message({
              type: 'warning',
              message: res.msg
            })
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消!'
        })
      })
    },
    handleCombination () {
      this.combinationDialogVisible = true
    },
    submitForm () {
      this.$refs['form'].validate((valid) => {
        let currentNodeData = this.currentNodeData
        if (valid) {
          let params = {...this.form}

          params.dataPerFlag ? params.dataPerFlag = 1 : params.dataPerFlag = 0

          params.roleGroup = params.roleGroup.join()
          // 添加同级
          if (this.menuAddAction === 1) {
            params.parentId = currentNodeData.parentId
            params.id = ''
            // 添加子级
          } else if (this.menuAddAction === 2) {
            params.parentId = currentNodeData.id
            params.id = ''
            // 新增
          } else if (this.menuAddAction === 3) {
            if (params.level === 1) {
              params.id = ''
              params.parentId = ''
            }
            // 修改
          } else {
            params.parentId = currentNodeData.parentId
            params.id = currentNodeData.id
          }

          if (params.level > 1 && this.menuAddAction === 3) {
            this.$message({
              type: 'warning',
              message: '暂时只能添加一级菜单，其他菜单暂无上级数据'
            })
            return
          }

          this.$confirm('确定保存编辑的信息吗?', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.submitLoading = true

            saveOrUpdateMenu({jsonData: JSON.stringify(params)}).then(res => {
              if (res.success) {
                this.$message({
                  type: 'success',
                  message: res.msg
                })
                this.submitLoading = false
                this._getMenuData(false)
                this.currentNodeData = null
                this.addMenuDialogVisible = false
              } else {
                this.$message({
                  type: 'warning',
                  message: res.msg
                })
                this.submitLoading = false
              }
            }).catch(() => {
              this.submitLoading = false
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '取消!'
            })
          })
        }
      })
    },
    _getMenuData (btnQuery) {
      if (btnQuery) {
        this.queryBtnLoading = true
      }

      // 重新加载菜单数据是清空隐藏 编辑表单页面
      this.currentNodeData = null

      let params = Object.assign({}, this.queryForm, {
        page: 1,
        limit: 9999
      })
      getMenuManageListData(params).then((res) => {
        this.treeData = this.$utils.toTreeData(res.data, {id: 'id', parent: 'parentId'})
        console.log(this.treeData)
        this.$refs['baseTree'].getDataState(true)
        this.queryBtnLoading = false
      }).catch(err => {
        console.log(err)
      })
    },
    _setForm (data) {
      this._resetForm()
      for (let i in this.form) {
        if (data[i]) {
          this.form[i] = data[i]
          data['dataPerFlag'] === 1 ? this.form['dataPerFlag'] = true : this.form['dataPerFlag'] = false
          if (data['roleGroup']) {
            this.form['roleGroup'] = data['roleGroup'].split(',')
          }
        } else {
          this.form[i] = ''
          if (!this.form['roleGroup']) this.form['roleGroup'] = []
        }
      }
    },
    _resetForm () {
      this.$refs['form'].resetFields()
      // 重置绑定的表单数据
      if (this['form']) {
        for (let i in this['form']) {
          this['form']['roleGroup'] = []
          this['form'][i] = ''
        }
      }
    },
    _getBaseInfo () {
      menuPageBaseInfo({}).then(res => {
        this.roleGroupOpts = this._jsonToArr(res['enumMap'])
        this.functionTypeOpts = this._jsonToArr(res['menuFunctionTypeEnum'])
        this.menuLevelOpts = this._jsonToArr(res['menuLevelEnum'])
      })
    },
    _jsonToArr (json) {
      return this.$utils.jsonToArr(json, 'value', 'label')
    }
  },
  mounted () {
    this._getBaseInfo()
    this._getMenuData(false)
  }
}
</script>

<style lang="scss" scoped>
  .wrapper {
    width: 900px;
  }

  .tree-wrap {
    min-height: calc(100vh - 310px);
    max-height: calc(100vh - 310px);
    overflow-y: auto;
  }
</style>
