<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form">

        <el-form size="mini" :inline="true" :model="queryForm" ref="queryForm">

          <div class="hd">
            <el-form-item prop="areas">
              <community-selector
                class="w300"
                v-model="showAreas"
                :url="areasUrl"
                :props="cascaderProps"
                :dataProps="cascaderDataProps"
                placeholder="选择行政区、片区、小区"
                @change="handleAreasChange"
              >
              </community-selector>

            </el-form-item>


            <el-form-item prop="vagueData">
              <base-vague-autocomplete
                class="w300"
                :dataProps="vagueQueryInfo"
                v-model="queryForm.vagueData" @select="handleSelectInfo" placeholder="客户电话,需求编号,客户姓名">
              </base-vague-autocomplete>
            </el-form-item>



            <el-form-item>
              <el-button type="primary" @click="handleQuery(true)">搜索</el-button>
            </el-form-item>
            <el-form-item>
              <el-button @click="_resetQueryForm('queryForm')">重置</el-button>
            </el-form-item>

            <!--<el-form-item class="pull-right">-->
              <!--<el-dropdown @command="handleCommandAddHouse">-->
                <!--<el-button type="primary">-->
                  <!--新增房源 <i class="el-icon-plus"></i>-->
                <!--</el-button>-->
                <!--<el-dropdown-menu slot="dropdown">-->
                  <!--<el-dropdown-item v-for="item in addTypes" :key="item.value" :command="item">{{item.label}}</el-dropdown-item>-->
                <!--</el-dropdown-menu>-->
              <!--</el-dropdown>-->
            <!--</el-form-item>-->
          </div>

          <div class="center">

            <el-form-item class="checkbox-wrap"  label="用途：">
              <el-checkbox-group v-model="queryForm.addTypes">
            <el-checkbox
              @change="handleChangeHouseUseConditions"
              v-for="(item, index) in addTypes" :key="index" :label="item.value"
              style="margin-left: 0; margin-right: 20px; margin-bottom: 5px"
            >{{item.label}}</el-checkbox>
          </el-checkbox-group>
            </el-form-item>


            <el-form-item class="checkbox-wrap" prop="conditions">
              <el-checkbox-group v-model="queryForm.conditions">
                <el-checkbox
                  @change="handleChangeConditions"
                  v-for="(item, index) in conditions" :key="index" :label="item.value"
                  style="margin-left: 0; margin-right: 20px; margin-bottom: 5px"
                >{{item.label}}</el-checkbox>
              </el-checkbox-group>
            </el-form-item>
          </div>
          <div class="bottom">
            <el-form-item class="margin-b-none" prop="sellingPrice">
              <base-section-select v-model="queryForm.sellingPrice"   :data="regionValue.sell"  placeholder="价格区间" description="万元"></base-section-select>
            </el-form-item>

            <el-form-item class="margin-b-none" prop="buildSpace">
              <base-section-select v-model="queryForm.buildSpace" :data="regionValue.space"  placeholder="面积区间" description="平米"></base-section-select>
            </el-form-item>



            <el-form-item class="margin-b-none">
              <more-conditions @handleConfirm="handleMoreConfirm" ref="moreConditions"></more-conditions>
            </el-form-item>

          </div>
        </el-form>

      </div>
    </div>

    <div class="page-content-bd" v-loading="loadingView">

      <house-used-table :tableData="tableData">
        <el-button size="mini">导出当前页</el-button>

        <el-dropdown size="medium">
          <el-button size="mini">
            房源打印<i class="el-icon-arrow-down el-icon--right"></i>
          </el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>租赁</el-dropdown-item>
            <el-dropdown-item>橙色横版15</el-dropdown-item>
            <el-dropdown-item>绿色横版15</el-dropdown-item>
            <el-dropdown-item>绿色横版15</el-dropdown-item>
            <el-dropdown-item>黄色竖版12</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>

        <el-button size="mini">房源对比</el-button>

        <el-button style="margin-left: 0" size="mini">批量信息删除</el-button>

        <el-button style="margin-left: 0" size="mini">房源删除</el-button>

        <el-dropdown size="medium">
          <el-button size="mini">
            排序<i class="el-icon-arrow-down el-icon--right"></i>
          </el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>按带看次数升序</el-dropdown-item>
            <el-dropdown-item>按带看次数降序</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </house-used-table>

      <b-pagination
        :listQuery="listQuery"
        @handleSizeChange="handleSizeChange"
        @handleCurrentChange="handleCurrentChange">
      </b-pagination>

    </div>

    <!-- 房源跟进 -->
    <el-dialog
      title="新增客源"
      :visible.sync="dialogVisibleAddHouse"
      width="500px"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
    >
      <add-house @handleClick="handleAddHouseClick" :param="addHouseParams" ref="addHouse"></add-house>
    </el-dialog>

  </div>
</template>

<script>
  import PageList from '@/mixins/pageList' // 列表页面查询 mixin
  import * as RequestURL from '@/request/customer/customerUsed' // 请求后端URL路径
  import HouseUsedTable from './Table'
  import AddHouse from './AddHouse'
  import BaseVagueAutocomplete from '@/components/BaseVagueAutocomplete'
  import CommunitySelector from '@/components/BaseCascader/index'
  import {queryCommunitySelectWithRegion,getRegionValue} from '@/request/app'
  import BaseSectionSelect from '@/components/BaseSectionSelect'
  import MoreConditions from './MoreConditions'
  const transactionTypes = [
    {
      label: '全部',
      value: 4,
    },
    {
      label: '出售',
      value: 1,
    },
    {
      label: '出租',
      value: 2,
    },
    {
      label: '租售',
      value: 3,
    }
  ]

  const conditions = [
      {
        label: '急切',
        value: 'isUrgent'
      },
      {
        label: '封盘',
        value: 'closeDiscPhone'
      },
      {
        label: '有带看',
        value: 'viewing'
      },
      {
        label: '下定',
        value: 'isBond'
      },
      {
        label: '我的客源',
        value: 'mycustomer'
      },
      {
        label: '我的私客',
        value: 'myPrivate'
      },
      {
        label: '7天未回访',
        value: 'unBack'
      },
      {
        label: '30天未带看',
        value: 'unShowed'
      },
      {
        label: '我的收藏',
        value: 'myCollection'
      },
      {
        label: '我的学区',
        value: 'isSchoolRoom'
      },
      {
        label: '淘宝池',
        value: 'hunting'
      },
      {
        label: '非淘宝池',
        value: 'unHunting'
      },
      {
        label: '通知撤单',
        value: 'isCancelNotice'
      },
      {
        label: '30天租赁到期',
        value: 'contractEnd'
      },
      {
        label: '一手',
        value: 'one'
      },
      {
        label: '二手',
        value: 'two'
      },
  ]

  const addTypes = [
    {
      label: '住宅',
      value: 'house'
    },
    {
      label: '别墅',
      value: 'villa'
    },
    {
      label: '商铺',
      value: 'shops'
    },
    {
      label: '公寓',
      value: 'apartment'
    },
    {
      label: '写字楼',
      value: 'officeBuiling'
    },
    {
      label: '仓库',
      value: 'wareHouse'
    },
    {
      label: '厂房',
      value: 'factory'
    },
    {
      label: '车位',
      value: 'parking'
    },
    {
      label: '土地',
      value: 'land'
    },
  ]

  const invalids = [
    {
      label: '他租',
      value: 4
    },
    {
      label: '他售',
      value: 5
    },
    {
      label: '未知',
      value: 6
    },
    {
      label: '自住',
      value: 7
    },
    {
      label: '出租中',
      value: 8
    },
    {
      label: '空置',
      value: 9
    },
    {
      label: '已驳回',
      value: 11
    }
  ]

  // 模糊查询坐栋数据
  const vagueQueryBuilding = [
    {label: '精确查找', type: 1},
    {label: '模糊查找', type: 2}
  ]

  // 模糊查询其他  门牌号 电话 编号..
  const vagueQueryInfo = [
    {label: '房源编号', type: 1},
    {label: '业主姓名', type: 2},
    {label: '门牌号', type: 3},
    {label: '业主电话', type: 4},
    {label: '小区名称', type: 5},
    {label: '钥匙编号', type: 6},
    {label: '委托编号', type: 7},
    {label: '推荐标签', type: 8},
    {label: '房源备注', type: 9},
  ]


  export default {
    name: 'customerListPage',
    mixins: [PageList],
    components: {HouseUsedTable, BaseVagueAutocomplete, CommunitySelector, AddHouse,BaseSectionSelect,MoreConditions},
    data () {
      return {
        queryForm: {
          transactionTypeId: '',
          conditions: [],
          invalids: [],
          vagueData: '', // 门牌，电话 编号 业主... 模糊查询
          building: '', // 座栋
          buildingUnit: '',
          buildingHouse: '',
          areas: [],
          addTypes:[]
        },
        showAreas:'',
        statusItemActive: '全部',
        statusItemChildActive: '',
        tableData: [],
        conditions: conditions,
        transactionTypes: transactionTypes,
        addTypes: addTypes,
        invalids: invalids,
        loadingQueryBtn: false,
        loadingView: false,
        queryParams: {



          areas:[],
          status: {},
          building: {},
          vagueData: {},
          transactionTypeId:{},
          hunting:{},
          sellingPrice:{},
          buildSpace:{},
          houseUsesIds:{},
        },
        restaurants: [],
        state2: '',
        vagueQueryBuilding: vagueQueryBuilding,
        vagueQueryInfo: vagueQueryInfo,
        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        areasUrl: queryCommunitySelectWithRegion,
        dialogVisibleAddHouse: false,
        addHouseParams: {
          houseUsesId: '',
          houseUses: '',
          transactionTypeId:''
        },
        regionValue: {
          sell: {},
          rent: {},
          space: {}
        }, // 区间下拉数据
      }
    },
    methods: {
      handleChangeHouseUseConditions(){
        this._loadData(false)
      },


      // 设置导航标签标题
      setTagsViewTitle() {
        let tempRoute = Object.assign({}, this.$route)
        let type = tempRoute.params && Number(tempRoute.params.type)
        let title = ''

        switch (type) {
          case 4:
            title = '买卖淘宝池'
            break
          case 5:
            title = '租赁淘宝池'
            break
          default:
            title = '淘宝池'
            break
        }

        const route = Object.assign({}, tempRoute, { title: title })
        console.log(title)
        this.$store.dispatch('updateVisitedView', route)
      },

      handleSelectionChange () {},

      // 点击搜索区域状态项
      setQueryStatus (status) {
        if (status.invalid) { // 点击失效
          this.queryForm.invalids = this.invalids.map(item => item.value)
          this.queryParams.status = {invalid: this.queryForm.invalids}
        } else
          this.queryParams.status = status
      },

      // 更多查询条件弹框 点击确认后
      handleMoreConfirm (formInfo) {
        this.moreFormParams = formInfo || {}
      },

      querySearch (queryString, cb) {
        console.log(queryString)

        let results = vagueQueryBuilding.map(item => {
          return {
            label: item.label,
            value: queryString,
            type: item.type,
          }
        })

        console.log(results)

        // let results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants
        // 调用 callback 返回建议列表的数据
        cb(results)
      },

      // 点击坐栋输入建议项赋值
      handleSelectBuilding (item) {
        this.queryParams.building = {
          type: item.type,
          value: item.value,
        }
      },
      _getRegionValue(){
        // 获取售价区间
        const sellPriceRange = {
          widgetCode: 'sellPriceRange',
          tag: 2
        }
        getRegionValue(sellPriceRange).then(res => {
          console.log(res.data)
          this.regionValue.sell = res.data || []
        })
        // 获取面积区间
        const spaceParams = {
          widgetCode: 'spaceRange',
          tag: 2
        }
        getRegionValue(spaceParams).then(res => {
          this.regionValue.space = res.data || []
        })


      },

      // 点击其他信息输入建议项赋值
      handleSelectInfo (item) {
        // 点击坐栋输入建议项赋值
        this.queryParams.vagueData = {
          type: item.type,
          value: item.value,
        }
      },

      handleAreasChange (val, label, data) {
        let regionCode = ''
        if (data) {
          if(data.level == 2){
            regionCode = data.code.split('region')[1]
            this.queryForm.areas = [{
              type: data.level,
              value: regionCode
            }]
          }else{
            // Todo 级联选择暂时不支持多选 后面加入
            this.queryForm.areas = [{
              type: data.level,
              value: data.code
            }]
          }
        }
      },

      // 新增房源弹框
      handleCommandAddHouse (command) {
        this.addHouseParams = {
          houseUsesId: command.value,
          houseUses: command.label,
          transactionTypeId:this.$route.params.type
        }
        this.$refs['addHouse'] && this.$refs['addHouse']._resetForm()
        this.dialogVisibleAddHouse = true
      },

      // 监听房源新增
      handleAddHouseClick (value) {
        //取消还是上一步
        if(value == 1){
          //取消
          this._loadData(false)
          this.dialogVisibleAddHouse = false
        }else if(value == 2){
          this.dialogVisibleAddHouse = false
        }
      },

      // 选择条件复选框
      handleChangeConditions () {
        this._loadData(false)
      },


      _loadData (btnQuery) {
        if (btnQuery) {
          this.loadingQueryBtn = true
        }

        this.loadingView = true
        const params = Object.assign({}, this._formatQueryParams(this.queryForm), {
          start: this.listQuery.limit * (this.listQuery.page - 1),
          length: this.listQuery.limit,
          draw: 1,
        })
        RequestURL.searchCustomerList(params).then(res => {
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

          // 重置绑定的状态项
          this.queryParams.status = {}
          this.queryForm.addTypes = []
          this.showAreas = '',
          // 重置状态
          this.statusItemActive = '全部'
          this.statusItemChildActive = ''
        })
      },

      _formatQueryParams (form) {
        let params = {}
        let temp = {}


        // 复选框组
        this.conditions.forEach(item => {

          form.conditions.forEach(fc => {
            temp[fc] = 1
          })

        })


        this.queryParams.status && (temp.status = this.queryParams.status)
        this.queryParams.building && (temp.building = this.queryParams.building)
        this.queryParams.vagueData && (temp.vagueData = this.queryParams.vagueData)
        this.queryParams.transactionTypeId && (temp.transactionTypeId = this.$route.params.type)
        this.queryParams.hunting && (temp.hunting = 1)
        this.queryParams.sellingPrice && (temp.sellingPrice = form.sellingPrice)
        this.queryParams.buildSpace && (temp.buildSpace = form.buildSpace)
        this.queryParams.houseUsesIds && (temp.houseUsesIds = form.addTypes.join(','))//数组拆分
        this.queryParams.areas && (temp.areas = form.areas)
        // 与更多添加弹出框参数对象合并
        temp = {...temp, ...this.moreFormParams}

        // 排序
        if (this.tableSort) temp.sort = this.tableSort

        // 移除多余的参数
        // if (!temp.areas.length) delete temp.areas
        delete temp.conditions
        delete temp.invalids
        delete temp.addTypes
        params.condition = JSON.stringify(temp)
        return params
      }

    },
    mounted(){
      this.setTagsViewTitle()
      this._getRegionValue()
    },
    watch: {
      // 监听坐栋查询 如果当前输入的内容和之前绑定的输入建议不一致 则说明没有点击建议项 赋默认值
      'queryForm.building': function (newValue) {
        if (!newValue) return
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
        if (!newValue) return

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
      }
    }
  }
</script>

<style scoped lang="scss">
  $grey-color: #909399;
  .status-wrap {
    min-height: 50px;
    .item {
      display: inline-block;
      margin-right: 10px;
      &>a {
        color: $grey-color;
      }

      .children {
        display: inline-block;
        position: relative;

        .arrow {
          position: absolute;
          top: 50%;
          left: 0;
          width: 0;
          height: 0;
          border-color: transparent;
          border-style: solid;
          border-width: 5px 5px 5px 0;
          border-right-color: $grey-color;
          margin-top: -5px;
        }

        .content {
          padding: 0 5px;
          margin-left: 5px;
          /*max-width: 500px;*/
          padding: 0 8px;
          text-align: center;
          background-color: #fff;
          border-radius: 4px;
          border: 1px solid $grey-color;
          font-size: 12px;
        }
      }

      .child {
        display: inline-block;
        color: $grey-color;
      }
    }

    a:hover {
      text-decoration: underline;
    }
  }

  .red-color {
    color: #e41e2b;
  }
  .grey-color {
    color: #909399;
  }

  .active {
    color: #e41e2b !important;
  }
</style>
