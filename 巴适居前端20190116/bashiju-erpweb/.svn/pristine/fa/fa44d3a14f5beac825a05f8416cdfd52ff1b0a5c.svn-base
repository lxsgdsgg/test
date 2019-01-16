<!-- 新房 -->

<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form">

        <el-form size="mini" :inline="true" :model="queryForm" ref="queryForm">

          <el-form-item>
            <base-cascader
              class="w300"
              :data="areasSelectOpts"
              :props="cascaderProps"
              v-model="showAreas"
              :dataProps="cascaderDataProps"
              placeholder="选择行政区、片区、小区"
              @change="handleAreasChange"
            >
            </base-cascader>

          </el-form-item>

          <el-form-item prop="houseUseTypes">
            <el-select multiple collapse-tags clearable v-model="queryForm.houseUseTypes" placeholder="房屋用途">
              <el-option
                v-for="item in houseTypes"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item prop="sellingPrice">
            <base-section-select v-model="queryForm.sellingPrice" :data="regionValue.sell" placeholder="价格范围" description="元"></base-section-select>
          </el-form-item>

          <el-form-item prop="synchron" class="w150">
            <el-select clearable v-model="queryForm.synchron" placeholder="是否外网同步">
              <el-option
                label="是"
                value="1">
              </el-option>
              <el-option
                label="否"
                value="0">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item prop="vagueData">

            <base-vague-autocomplete
              class="w250"
              :dataProps="vagueQueryInfo"
              v-model="queryForm.vagueData" @select="handleSelectInfo" placeholder="关键字（楼盘名称）">
            </base-vague-autocomplete>

          </el-form-item>

          <el-form-item>
            <el-button :loading="loadingQueryBtn" @click="handleQuery" type="primary">查询</el-button>
            <el-button @click="_resetQueryForm">重置</el-button>
          </el-form-item>

          <el-form-item class="pull-right">
            <el-button v-hasOnlyBtn="PERMISSION_BTN.ADD" @click="handleAdd" type="primary">新增</el-button>
          </el-form-item>


          <!--<el-input v-model="input" placeholder="关键字"></el-input>-->

        </el-form>

      </div>
    </div>

    <div class="page-content-bd" v-loading="loadingView">
      <el-table
        :data="tableData"
        border
        align="center"
        style="width: 100%"
        height="560"
        size="small"
      >

        <el-table-column
          type="index"
          width="50"
          align="center"
          label="序号"
        >
        </el-table-column>

        <el-table-column
          align="center"
          label="名称">
          <template slot-scope="scope">
            <a
              @click="toDetail(scope.row.id, scope.row.name)"
              href="javascript:;" class="houseId" type="text"
            >
              {{scope.row.name}}
            </a>
          </template>
        </el-table-column>

        <el-table-column
          prop="recordName"
          align="center"
          label="备案名称">
        </el-table-column>

        <el-table-column
          prop="pinyin"
          align="center"
          label="拼音检索">
        </el-table-column>

        <el-table-column
          prop="regionName"
          align="center"
          label="所在片区">
        </el-table-column>

        <el-table-column
          prop="areaName"
          align="center"
          label="行政区划">
        </el-table-column>

        <el-table-column
          prop="address"
          align="center"
          label="地址">
        </el-table-column>

        <el-table-column
          align="center"
          label="是否标注">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.latitude && scope.row.longitude" type="success">已标注</el-tag>
            <el-tag v-else type="info">未标注</el-tag>
          </template>
        </el-table-column>

        <el-table-column
          prop="developers"
          align="center"
          label="开发商">
        </el-table-column>

        <el-table-column
          align="center"
          label="出售均价">
          <template slot-scope="scope">
            {{scope.row.salePrice + ' / 平米'}}
          </template>
        </el-table-column>

        <el-table-column
          prop="buildingArea"
          align="center"
          label="面积">
        </el-table-column>

        <el-table-column
          prop="propertyLimit"
          align="center"
          label="产权年限">
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
          width="250"
        >
          <template slot-scope="scope">

            <div v-if="scope.row">

              <template v-if="btnPermission[PERMISSION_BTN.SYNCHRO_BTN]">
                <el-button v-if="scope.row.isSynchron === 0" @click="handleSetSynch(scope.row, 1)" type="text" size="small">设置外网同步</el-button>
                <el-button v-else @click="handleSetSynch(scope.row, 0)" type="text" size="small">取消外网同步</el-button>
              </template>
              <template v-if="btnPermission[PERMISSION_BTN.SET_REC]">
                <el-button v-if="scope.row.isGood === 0" @click="handleCancelRecommend(scope.row, 1)" type="text" size="small">设置推荐</el-button>
                <el-button v-else @click="handleCancelRecommend(scope.row, 0)" type="text" size="small">取消推荐</el-button>
              </template>

              <el-button v-if="btnPermission[PERMISSION_BTN.DELETE]" @click="handleDelete(scope.row)" type="text" size="small">删除</el-button>

            </div>

          </template>
        </el-table-column>

      </el-table>

      <b-pagination
        :listQuery="listQuery"
        @handleSizeChange="handleSizeChange"
        @handleCurrentChange="handleCurrentChange">
      </b-pagination>
    </div>

    <el-dialog
      title="新增楼盘"
      :visible.sync="dialogVisibleAdd"
      width="1000px"
      :close-on-click-modal="false"
      :modal-append-to-body="false"
    >
      <form-comp v-if="hackReset" :isAdd="true" @handleConfirm="handleAddConfirm" @handleCancel="dialogVisibleAdd = false"></form-comp>
    </el-dialog>
  </div>
</template>

<script>
  import PageList from '@/mixins/pageList' // 列表页面查询 mixin
  import FormComp from './formComp'
  import BaseVagueAutocomplete from '@/components/BaseVagueAutocomplete'
  import BaseCascader from '@/components/BaseCascader/index'
  import BaseSectionSelect from '@/components/BaseSectionSelect'
  import {queryCommunitySelectWithRegion, getRegionValue} from '@/request/app'
  import * as RequestURL from '@/request/house/houseNew' // 请求后端URL路径
  import * as consts from './consts'

  const houseTypes = [
    {
      label: '住宅',
      value: 'house'
    },
    {
      label: '别墅',
      value: 'villa'
    },
    {
      label: '公寓',
      value: 'apartment'
    },
    {
      label: '商铺',
      value: 'shops'
    },
    {
      label: '写字楼',
      value: 'officeBuiling'
    }
  ]

  // 模糊查询其他  门牌号 电话 编号..
  export const vagueQueryInfo = [
    {label: '楼盘名称', type: 5}
  ]

  export default {
    name: 'houseNew',

    mixins: [PageList],

    components: {BaseVagueAutocomplete, BaseCascader, BaseSectionSelect, FormComp},

    data () {
      return {
        PERMISSION_BTN: consts.PERMISSION_BTN,
        dialogVisibleAdd: false,
        queryForm: {
          vagueData: '', //  模糊查询
          synchron: '',
          areaCodes: [], // 区域ids
          regionIds: [], // 片区ids
          communityIds: [], // 小区ids
          cityCodes: [], // 城市ids
          houseUseTypes: [],
          sellingPrice: ''
        },
        showAreas: '',
        houseTypes: houseTypes,
        vagueQueryInfo: vagueQueryInfo,
        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        areasSelectOpts: [], // 小区级联
        vagueDataParam: {}, // 模糊查询
        regionValue: {
          sell: {}
        },
        hackReset: true
      }
    },

    methods: {
      handleQuery () {
        this.listQueryParams = Object.assign({}, this.queryForm)
        this.listQuery.page = 1
        this.listQuery.currentPage = 1
        this._loadData(true)
      },

      // 新增
      handleAdd () {
        this.hackReset = false
        this.$nextTick(() => {
          this.hackReset = true
          this.dialogVisibleAdd = true
        })
      },

      // 监听新增
      handleAddConfirm () {
        this._loadData(false)
        this.dialogVisibleAdd = false
      },

      // 设置外网状态
      handleSetSynch (row, action) {
        let text = ''
        let result = ''
        let operate = ''
        if (action === 1) {
          text = '设置外网同步'
          result = 1
          operate = this.$DICT_CODE.LOG.BUSINESS_OPERATE_TYPE.NEW_HOUSE_SYN
        } else {
          text = '取消外网同步'
          result = 0
          operate = this.$DICT_CODE.LOG.BUSINESS_OPERATE_TYPE.NEW_HOUSE_CANCEL_SYN
        }

        this.$confirm(`确定${text}要吗？`, {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let params = {
            result: result,
            id: row.id
          }
          RequestURL['setSynchron'](params).then(() => {
            this.$message({
              type: 'success',
              message: '操作成功',
              duration: 1500
            })
            this._loadData(false)

            // 向后台传递日志数据
            let message = {
              sourceId: row.id, // 资源ID
              sourceCode: row.name, // 资源标识
              operatTypeId: operate, // 操作类型
              sourceTypeId:this.$DICT_CODE.LOG.BUSINESS_SOURCE_TYPE.NEW_DEAL,//资源类型
              businessTypeId: this.$DICT_CODE.LOG.BUSINESS_TYPE.NEW_HOUSE,
              logContent: text // 日志内容
            }
            this.$updateLog.newHouse.houseQueryLog({message: JSON.stringify(message)})

          })
        })
      },

      // 删除楼盘
      handleDelete (row) {
        this.$confirm('确定删除该楼盘么？', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          RequestURL['deleteNewHouse']({id: row.id}).then(() => {
            this.$message({
              type: 'success',
              message: '操作成功',
              duration: 1500
            })
            this._loadData(false)

            // 向后台传递日志数据
            let message = {
              sourceId: row.id, // 资源ID
              sourceCode: row.name, // 资源标识
              operatTypeId: this.$DICT_CODE.LOG.BUSINESS_OPERATE_TYPE.DELETE_NEW_HOUSE, // 操作类型
              sourceTypeId:this.$DICT_CODE.LOG.BUSINESS_SOURCE_TYPE.NEW_DEAL,//资源类型
              businessTypeId: this.$DICT_CODE.LOG.BUSINESS_TYPE.NEW_HOUSE,
              logContent: '删除一手房源'
            }
            this.$updateLog.newHouse.houseDelLog({message: JSON.stringify(message)})
          })
        })
      },

      // 设置新房楼盘推荐状态
      handleCancelRecommend (row, action) {
        let text = ''
        let result = ''
        let operate = ''
        if (action === 1) {
          text = '设置推荐'
          result = 1
          operate = this.$DICT_CODE.LOG.BUSINESS_OPERATE_TYPE.NEW_HOUSE_SET_GOOD
        } else {
          text = '取消推荐'
          result = 0
          operate = this.$DICT_CODE.LOG.BUSINESS_OPERATE_TYPE.NEW_HOUSE_CANCEL_GOOD
        }

        this.$confirm(`确定要${text}吗？`, {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let params = {
            result: result,
            id: row.id
          }
          RequestURL['setIsGood'](params).then(() => {
            this.$message({
              type: 'success',
              message: '操作成功',
              duration: 1500
            })
            this._loadData(false)

            // 向后台传递日志数据
            let message = {
              sourceId: row.id, // 资源ID
              sourceCode: row.name, // 资源标识
              operatTypeId: operate, // 操作类型
              businessTypeId: this.$DICT_CODE.LOG.BUSINESS_TYPE.NEW_HOUSE,
              sourceTypeId:this.$DICT_CODE.LOG.BUSINESS_SOURCE_TYPE.NEW_DEAL,//资源类型
              logContent: text // 日志内容
            }
            this.$updateLog.newHouse.houseQueryLog({message: JSON.stringify(message)})
          })
        })
      },

      // 跳转详情
      toDetail (id, name) {
        let menuId = this.$route.query.menu_id
        this.$router.push({ path: './houseNewDetail/' + id, query: {name: name, menu_id: menuId}})
        window.localStorage.setItem('newHouseList', JSON.stringify(this.tableData))
      },

      // 区域级联选择
      handleAreasChange (val, label, data) {
        console.log(data)
        // Todo 区域可多选  暂时写死成单选
        if (data) {

          if (data.dataType === 'city') {
            this.queryForm.cityCodes = [val]
            this.queryForm.areaCodes = []
            this.queryForm.regionIds = []
            this.queryForm.communityIds = []
          }

          if (data.dataType === 'area') {
            this.queryForm.cityCodes = []
            this.queryForm.areaCodes = [val]
            this.queryForm.regionIds = []
            this.queryForm.communityIds = []
          }

          if (data.dataType === 'region') {
            this.queryForm.cityCodes = []
            this.queryForm.areaCodes = []
            this.queryForm.regionIds = [val]
            this.queryForm.communityIds = []
          }

          if (data.dataType === 'community') {
            this.queryForm.cityCodes = []
            this.queryForm.areaCodes = []
            this.queryForm.regionIds = []
            this.queryForm.communityIds = [val]
          }
        }

      },

      // 点击其他信息输入建议项赋值
      handleSelectInfo (item) {
        // 点击坐栋输入建议项赋值
        this.vagueData = {
          type: item.type,
          value: item.value,
        }
      },

      // 清空查询表单
      _resetQueryForm () {
        this._resetForm('queryForm', () => {
          // 重置模糊查询
          this.vagueDataParam = {}

          // 重置区域小区表单显示
          this.showAreas = ''
          this.queryForm.areaCodes = []
          this.queryForm.regionIds = []
          this.queryForm.communityIds = []
          this.queryForm.cityCodes = []
        })
      },

      _loadData (btnQuery) {
        if (btnQuery) {
          this.loadingQueryBtn = true
        }

        this.loadingView = true

        let params = this._getParams()
        let temp = {...this.listQueryParams}
        let form = this.queryForm

        temp.minPrice = form.sellingPrice.min || ''
        temp.maxPrice = form.sellingPrice.max || ''
        temp.name = form.vagueData || ''
        delete temp.vagueData
        delete temp.sellingPrice

        params.param = JSON.stringify(temp)

        RequestURL['queryNewHouseList'](params).then(res => {
          this.tableData = res.data
          this.listQuery.total = res.count
          this.loadingQueryBtn = false
          this.loadingView = false
        }).catch(() => {
          this.loadingQueryBtn = false
          this.loadingView = false
        })
      },

      _formatQueryParams (form) {
        let temp = {}

        Object.keys(form).forEach(key => {
          if (form[key]) {
            temp[key] = form[key]
          }
        })
      }

    },

    computed: {
      // 按钮权限
      btnPermission () {
        return this.$store.getters.permissions
      }
    },

    mounted () {
      // 获取行政区 小区等级联数据
      this.$get(queryCommunitySelectWithRegion, {}).then(res => {
        this.areasSelectOpts = res
      })

      // 获取单价区间
      const sellParams = {
        widgetCode: 'averagePriceRange',
        tag: 2
      }
      getRegionValue(sellParams).then(res => {
        this.regionValue.sell = res.data || []
      })
    },

    watch: {

      // 监听查询 如果当前输入的内容和之前绑定的输入建议不一致 则说明没有点击建议项 赋默认值
      'queryForm.vagueData': function (newValue) {
        // 如果为空重置模糊查询对象
        if (!newValue) {
          this.vagueDataParam = {}
          return
        }

        let defaultVal = {
          type: vagueQueryInfo[0].type,
          value: newValue,
        }

        if (this.vagueDataParam) {
          if (newValue !== this.vagueDataParam) {
            this.vagueDataParam = defaultVal
          }
        } else {
          // 没有点击过输入建议项
          this.vagueDataParam = defaultVal
        }
      },

      showAreas (newVal) {
        if (!newVal) {
          this.queryForm.areaCodes = []
          this.queryForm.regionIds = []
          this.queryForm.communityIds = []
          this.queryForm.cityCodes = []
        }
      }
    }

  }
</script>

<style scoped lang="scss">
  .houseId {
    color: #409eff;
    &:hover {
      text-decoration: underline;
    }
  }
</style>
