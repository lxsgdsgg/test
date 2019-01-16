<!-- 简易房源列表 -->
<template>
  <div>
    <div class="page-content-hd">
      <div class="query-form">

        <el-form size="mini" :inline="true" :model="queryForm" ref="queryForm">
          <el-form-item prop="departmentOrUser">
            <base-cascader
              style="width: 200px;"
              :changeOnSelect="true"
              v-model="queryForm.departmentOrUser"
              :data="selectData.peopleSelectOpts"
              :props="cascaderProps"
              :dataProps="cascaderDataProps"
              placeholder="选择部门或用户"
            >
            </base-cascader>
          </el-form-item>

          <el-form-item prop="buildSpace">
            <base-section-select style="width: 200px;" v-model="queryForm.buildSpace" :data="selectData.regionValue.space" placeholder="面积区间" description="平米"></base-section-select>
          </el-form-item>

          <el-form-item prop="areas">
            <base-cascader
              style="width: 200px;"
              v-model="showAreas"
              :data="selectData.areasSelectOpts"
              :props="cascaderProps"
              :dataProps="cascaderDataProps"
              placeholder="选择区域"
              @change="handleAreasChange"
            >
            </base-cascader>

          </el-form-item>

          <el-form-item prop="building">

            <base-vague-autocomplete
              style="width: 200px;"
              :dataProps="selectData.vagueQueryBuilding"
              v-model="queryForm.building" @select="handleSelectBuilding" placeholder="坐栋">
            </base-vague-autocomplete>

          </el-form-item>

          <el-form-item prop="vagueData">

            <base-vague-autocomplete
              style="width: 300px;"
              :dataProps="selectData.vagueQueryInfo"
              v-model="queryForm.vagueData" @select="handleSelectInfo" placeholder="门牌号,电话,编号,业主,小区,钥匙编号,委托编号">
            </base-vague-autocomplete>

          </el-form-item>

          <el-form-item>
            <el-button :loading="loadingQueryBtn" type="primary" @click="handleQuery">搜索</el-button>
          </el-form-item>

          <el-form-item>
            <el-button @click="_resetQueryForm('queryForm')">重置</el-button>
          </el-form-item>

        </el-form>

      </div>
    </div>

    <div class="page-content-bd" v-loading="loadingView">

      <el-table
        height="500"
        :data="tableData" border fit :highlight-current-row="Selectable" style="width: 100%"
        size="small"
        @row-dblclick="selectionHouse"
      >

        <template v-for="(fruit, index) in houseFormThead">

          <el-table-column :key="index" :label="fruit.label && fruit.label" :width="fruit.width && fruit.width">
            <template slot-scope="scope">

              <template v-if="fruit.prop === 'houseId'">
                <a
                  v-if="viewDetail"
                  @click="toDetail(scope.row)"
                  href="javascript:;" class="houseId" type="text"
                >
                  {{scope.row[fruit.prop]}}
                </a>
                <span v-else>
                  {{scope.row[fruit.prop]}}
                </span>
              </template>

              <el-tag v-else-if="fruit.prop === 'discStatus'" size="small">{{scope.row[fruit.prop]}}</el-tag>

              <span v-else>
                {{ scope.row[fruit.prop] }}
            </span>

            </template>
          </el-table-column>

        </template>

      </el-table>

      <b-pagination
        :listQuery="listQuery"
        @handleSizeChange="handleSizeChange"
        @handleCurrentChange="handleCurrentChange">
      </b-pagination>

    </div>
  </div>
</template>

<script>
  import {queryReferenceUserSelect} from '@/request/manage/common'
  import PageList from '@/mixins/pageList' // 列表页面查询 mixin
  import BaseVagueAutocomplete from '@/components/BaseVagueAutocomplete'
  import BaseCascader from '@/components/BaseCascader/index'
  import BaseSectionSelect from '@/components/BaseSectionSelect'
  import {searchHouseList} from '@/request/house/houseUsed' // 请求后端URL路径
  import {queryCommunitySelectWithRegion, getRegionValue} from '@/request/app'
  import {vagueQueryBuilding, vagueQueryInfo} from './consts'

  /**
   * 房源表头 属性
   * prop (columns 值)
   * label (表头文本内容)
   */
  const houseFormThead = [
    {
      prop: 'houseId',
      label: '房源编号',
      width: 120
    },
    {
      prop: 'transactionType',
      label: '类型'
    },
    {
      prop: 'discStatus',
      label: '状态'
    },
    {
      prop: 'communityName',
      label: '小区'
    },
    {
      prop: 'buildingsName',
      label: '座栋'
    },
    {
      prop: 'buildingHouseName',
      label: '房号'
    },
    {
      prop: 'floorCount',
      label: '楼层'
    },
    {
      prop: 'room',
      label: '房型'
    },
    {
      prop: 'buildSpace',
      label: '面积'
    },
    {
      prop: 'sellingPrice',
      label: '售价'
    },
    {
      prop: 'rentPrice',
      label: '租价'
    },
    {
      prop: 'entrustCode',
      label: '委托编号'
    },
    {
      prop: 'operator',
      label: '维护人'
    },
    {
      prop: 'mainterDept',
      label: '部门'
    }
  ]

  export default {
    name: 'HouseListComp',

    mixins: [PageList],

    components: {BaseVagueAutocomplete, BaseCascader, BaseSectionSelect},

    props: {
      // 是否可选
      Selectable: {
        type: Boolean,
        default: false
      },

      // 默认查询该小区下房源
      communityId: {
        type: [Number, String]
      },

      viewDetail: {
        type: Boolean,
        default: true
      }
    },

    data () {
      return {
        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        tableData: [],
        houseFormThead: houseFormThead,
        showAreas: '',
        queryParams: {
          building: null,
          vagueData: null
        },
        queryForm: {
          departmentOrUser: '',
          buildSpace: '',
          building: '',
          vagueData: '',
          areas: []
        },
        selectData: {
          vagueQueryBuilding: vagueQueryBuilding,
          vagueQueryInfo: vagueQueryInfo,
          areasSelectOpts: [],
          peopleSelectOpts: [],
          regionValue: {
            sell: {},
            rent: {},
            space: {}
          } // 区间下拉数据
        }
      }
    },

    methods: {
      handleQuery () {
        this.listQueryParams = Object.assign({}, this.queryForm)
        this.listQuery.page = 1
        this.listQuery.currentPage = 1
        this._loadData(true)
      },

      // 区域级联选择
      handleAreasChange (val, label, data) {
        // Todo 级联选择暂时不支持多选 后面加入
        this.queryForm.areas = [{
          type: data.level,
          value: data.code
        }]
      },

      // 点击坐栋输入建议项赋值
      handleSelectBuilding (item) {
        this.queryParams.building = {
          type: item.type,
          value: item.value,
        }
      },

      // 点击其他信息输入建议项赋值
      handleSelectInfo (item) {
        // 点击坐栋输入建议项赋值
        this.queryParams.vagueData = {
          type: item.type,
          value: item.value,
        }
      },

      // 双击选择房源
      selectionHouse (row) {

        if (this.Selectable){
          this.$emit('handleSelection', row)
        }

      },

      toDetail (row) {
        this.$router.push({ path: '/house/houseUsedDetail/' + row.id + '/' + row.houseId, query: { formName: row.houseUsesId}})
        window.localStorage.setItem('houseList', JSON.stringify(this.tableData))
        this.$emit('close')
      },

      _getSelectData () {
        // 获取相关用户员工级联数据
        queryReferenceUserSelect({}).then(res => {
          this.selectData.peopleSelectOpts = res
        })

        // 获取行政区 小区等级联数据
        this.$get(queryCommunitySelectWithRegion, {}).then(res => {
          this.selectData.areasSelectOpts = res
        })

        this._getRegionValue()

      },

      // 获取区间下拉数据
      _getRegionValue () {
        // 获取售价区间
        const sellParams = {
          widgetCode: 'sellPriceRange',
          tag: 2
        }
        getRegionValue(sellParams).then(res => {
          this.selectData.regionValue.sell = res.data || []
        })

        // 获取租价区间
        const rentParams = {
          widgetCode: 'rentPriceRange',
          tag: 2
        }
        getRegionValue(rentParams).then(res => {
          this.selectData.regionValue.rent = res.data || []
        })

        // 获取租价区间
        const spaceParams = {
          widgetCode: 'spaceRange',
          tag: 2
        }
        getRegionValue(spaceParams).then(res => {
          this.selectData.regionValue.space = res.data || []
        })
      },

      _loadData (btnQuery) {
        if (btnQuery) {
          this.loadingQueryBtn = true
        }

        this.loadingView = true

        // 查同小区房源
        if (this.communityId) {
          this.showAreas = String(this.communityId)
          this.queryForm.areas = [
            {
              type: 3,
              value: this.communityId
            }
          ]
          // 初次弹框时要带查询参数  modify by zuoyuntao 20181210
          this.listQueryParams = Object.assign({}, this.queryForm)
        }
        const params = Object.assign({}, this._formatQueryParams(this.listQueryParams), {
          start: this.listQuery.limit * (this.listQuery.page - 1),
          length: this.listQuery.limit,
          draw: 1
        })
        searchHouseList(params).then(res => {
          this.tableData = res.data
          this.listQuery.total = res.recordsTotal
          this.loadingQueryBtn = false
          this.loadingView = false
        }).catch(() => {
          this.loadingQueryBtn = false
          this.loadingView = false
        })
      },

      _resetQueryForm (form) {
        this._resetForm(form, () => {
          // 重置绑定的坐栋和详细信息模糊查询
          this.queryParams.building = {}
          this.queryParams.vagueData = {}

          // 重置区域小区表单显示
          this.showAreas = ''
        })
      },

      _formatQueryParams (form) {
        let params = {}
        let temp = {}

        Object.keys(form).forEach(key => {
          if (form[key]) {
            temp[key] = form[key]
          }
        })

        this.queryParams.building && (temp.building = this.queryParams.building)
        this.queryParams.vagueData && (temp.vagueData = this.queryParams.vagueData)

        temp.transactionTypeId = 4

        // 移除多余的参数
        if (temp.areas && !temp.areas.length) delete temp.areas

        params.condition = JSON.stringify(temp)
        return params
      }
    },

    mounted () {
      this._getSelectData()
    },

    watch: {
      // 监听坐栋查询 如果当前输入的内容和之前绑定的输入建议不一致 则说明没有点击建议项 赋默认值
      'queryForm.building': function (newValue) {

        // 如果为空重置模糊查询对象
        if (!newValue) {
          this.queryParams.building = {}
          return
        }

        let defaultVal = {
          type: vagueQueryBuilding[0].type,
          value: newValue,
        }

        if (this.queryParams.building) {
          if (newValue !== this.queryParams.building) {
            this.queryParams.building = defaultVal
          }
        } else {
          // 没有点击过输入建议项
          this.queryParams.building = defaultVal
        }
      },

      // 监听查询 如果当前输入的内容和之前绑定的输入建议不一致 则说明没有点击建议项 赋默认值
      'queryForm.vagueData': function (newValue) {
        // 如果为空重置模糊查询对象
        if (!newValue) {
          this.queryParams.vagueData = {}
          return
        }

        let defaultVal = {
          type: vagueQueryInfo[0].type,
          value: newValue,
        }

        if (this.queryParams.vagueData) {
          if (newValue !== this.queryParams.vagueData) {
            this.queryParams.vagueData = defaultVal
          }
        } else {
          // 没有点击过输入建议项
          this.queryParams.vagueData = defaultVal
        }
      },

      communityId () {
        this._loadData(false)
      }
    }
  }
</script>

<style scoped lang="scss">

  .page-content-hd {
    margin: 0;
  }

  .houseId {
    color: #409eff;
    &:hover {
      text-decoration: underline;
    }
  }

</style>
