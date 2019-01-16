<!-- 选择房源 -->
<template>
  <div>
      <div>
          <el-form :model="queryForm"  ref="queryForm" :inline="true"  label-width="100px" class="demo-form-inline">
            <el-form-item label="" prop="selectId">
              <dept-base-cascader
                @change="handleChangeDept"
                v-model="queryForm.selectId"
                :data="deptSelectOpts" :props="selectProps" :dataProps="selectDataProps" placeholder="门店">
              </dept-base-cascader>
            </el-form-item>
            <el-form-item label="" prop="buildSpace">
              <base-section-select style="width: 150px" v-model="queryForm.buildSpace" :data="regionValue.space" placeholder="面积区间" description="平米"></base-section-select>
            </el-form-item>
            <el-form-item label="" prop="communityId"  >
              <community-selector
                style="width: 150px"
                v-model="queryForm.communityId"
                :url="areasUrl"
                :props="cascaderProps"
                :dataProps="cascaderDataProps"
                placeholder="选择区域"
                @change="handleCommunityChange"
              >
              </community-selector>
            </el-form-item>
            <el-form-item label="" prop="buildingsName">
              <el-input v-model="queryForm.buildingsName" placeholder="坐栋" ></el-input>
            </el-form-item>
             <el-form-item label="" prop="vagueData">
              <base-vague-autocomplete
                class="w300"
                :dataProps="vagueQueryInfo"
                v-model="queryForm.vagueData" @select="handleSelectInfo" placeholder="房源编号,电话,门牌号,委托编号">
              </base-vague-autocomplete>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click.native.prevent="_getTableData(true)" :loading="loadingQueryBtn">查询</el-button>
              <el-button @click.native.prevent="_resetQueryForm('queryForm')">重置</el-button>
            </el-form-item>
          </el-form>
        </div>
        
        <el-table
        :data="tableHouseData"
        v-loading="loadingHouseView"
        highlight-current-row
        ref="erHouseTable"
        @row-dblclick="dbclickSelectHouse"
        @selection-change="handleSelectionChange"
      >
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>

        <el-table-column
          prop="transactionType"
          label="类型"
          width="120">
        </el-table-column>

        <el-table-column
          prop="houseId"
          label="房源编号"
          width="120">
        </el-table-column>

        <el-table-column
          prop="communityName"
          label="小区">
        </el-table-column>

        <el-table-column
          prop="buildingsName"
          label="坐栋">
        </el-table-column>

        <el-table-column
          prop="buildingHouseName"
          label="门牌号">
        </el-table-column>

        <el-table-column
          prop="buildSpace"
          label="面积">
        </el-table-column>

        <el-table-column
          prop="price"
          label="价格">
        </el-table-column>

        <el-table-column
          prop="maintainer"
          label="维护人">
        </el-table-column>

      </el-table>

      <b-pagination
        :listQuery="listQuery"
        @handleSizeChange="handleSizeChange"
        @handleCurrentChange="handleCurrentChange">
      </b-pagination>
      <div style="text-align:center;margin-top:50px;">
        <el-button type="primary"  @click="selectHouse">确认</el-button>
        <el-button @click="cancelSelect">取消</el-button>
      </div>
  </div>

</template>

<script>
  import CommunitySelector from '@/components/BaseCascader/index'
  import {customerFindHouseList} from '@/request/customer/customerUsed'
  import deptBaseCascader from '@/components/BaseCascader'
  import {queryCommunitySelectWithRegion} from '@/request/app'
  import BaseSectionSelect from '@/components/BaseSectionSelect'
   import BaseVagueAutocomplete from '@/components/BaseVagueAutocomplete'
  export default {
    components: {deptBaseCascader,CommunitySelector,BaseSectionSelect,BaseVagueAutocomplete},
     props: {
       regionValue: {
        type: Object,
        required: true,
        default () {
          return {}
        }
      },
      deptSelectOpts: {
        type: Array,
        default () {
          return []
        }
      },
       transactionTypeId: {
        type: [Number, String],
        required: true
      },
    },
    data () {
      return {
        areasUrl: queryCommunitySelectWithRegion,
        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          children: 'children', // 指定选项的子选项为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        selectDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        selectProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          children: 'children', // 指定选项的子选项为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        
        tableData: [],
        tableHouseData: [],
        queryForm: {
          selectId: '',
          userId: '',
          deptId: '',
          selectAreaId: '',
          areaCode: '',
          regionId: '',
          communityId: ''
        },
        listQuery: {
          page: 1,
          limit: 10,
          total: 0
        },
        vagueData: '', //房源编号,电话,门牌号,委托编号
        // 房源编号,电话,门牌号,委托编号
        vagueQueryInfo: [
          {label: '房源编号', type: 1},
          {label: '电话', type: 2},
          {label: '门牌号', type: 3},
          {label: '委托编号', type: 4},

        ],
        loadingView: false,
        loadingHouseView: false,
        loadingQueryBtn: false,
        repeatLook: false,
        priceTitle: '售价',
        visterEvaluate: '',
        multipleSelection: []
      }
    },

    methods: {
      /**
       * 分页 pageSize 改变时会触发
       */
      handleSizeChange (val) {
        this.listQuery.limit = val
        this._getTableData()
      },

      /**
       * 分页 currentPage  改变时会触发
       */
      handleCurrentChange (val) {
        this.listQuery.page = val
        this._getTableData()
      },
      handleSelectInfo(item){
        
        console.log(item)
        this.queryForm.vagueType = item.type;
      },
      _getTableData (btn) {
        this.loadingHouseView = true
        this.loadingQueryBtn = btn
        let areas = []
        let area = {}
        if(this.queryForm.areaCode && this.queryForm.areaCode!=''){
            area.type = 1
            area.value = this.queryForm.areaCode
        }
        if(this.queryForm.regionId && this.queryForm.regionId!=''){
            let a = this.queryForm.regionId.substr(2)
            area.type = 2
            area.value = this.queryForm.regionId.substr(2)
        }
        if(this.queryForm.communityId && this.queryForm.communityId!=''){
            area.type = 3
            area.value = this.queryForm.communityId
        }
        console.log(area)
        if(JSON.stringify(area)!='{}'){
          areas.push(area)
        }
        let departmentOrUser = {}
        if(this.queryForm.deptId && this.queryForm.deptId!=''){
            departmentOrUser.type = 1
            departmentOrUser.value = this.queryForm.deptId
        }
        if(this.queryForm.userId && this.queryForm.userId!=''){
            departmentOrUser.type = 2
            departmentOrUser.value = this.queryForm.userId
        }
        let vagueData = {
          type: this.queryForm.vagueType,
          value: this.queryForm.vagueData
        }
        
        let buildSpace = {
            min: this.queryForm.buildSpace.min,
            max: this.queryForm.buildSpace.max,
        }
        let conditions = {
          transactionTypeId: this.transactionTypeId || '',
          vagueData: vagueData,
          building: this.queryForm.buildingsName,
          buildSpace: buildSpace
        }
        if(areas.length>0){
          conditions.areas = areas
        }
        if(JSON.stringify(departmentOrUser)!='{}'){
           conditions.departmentOrUser = departmentOrUser
        }
        
        const params = {
          conditions: JSON.stringify(conditions),
          limit: this.listQuery.limit,
          page: this.listQuery.page
        }
        customerFindHouseList(params).then(res => {
          console.log(res.data)
          this.tableHouseData = res.data.map(item => {
                 if(item.transactionTypeId!=2){
                    item.price = item.sellingPrice
                 }else{
                   item.price = item.rentPrice
                 }
                 return item
            })
          this.listQuery.total = res.count
          this.loadingHouseView = false
          this.loadingQueryBtn = false
        }).catch(() => {
          this.loadingHouseView = false
          this.loadingQueryBtn = false
        })
      },

       _resetQueryForm (form) {
        this._resetForm(form, () => {
          delete this.queryForm.vagueType
          delete this.queryForm.dataType
        })
      },

      // 格式化日期
      _timeFormat (row, column, cellValue) {
        return this.$utils.timeFormat(cellValue)
      },
      handleQuery(){

      },
      _resetForm (formName, callback) {
        this.$refs[formName] && this.$refs[formName].resetFields()
        if (callback && typeof callback === 'function') {
          callback()
        }
      },
      dbclickSelectHouse(row){
        this.$emit('handleClickRow', row)
      },
      selectHouse(row, event, column){
        this.$emit('handleClickRow', this.multipleSelection)
      },
      cancelSelect(){
         this.$emit('cancelSelect', null)
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;

      },
      clickRow(row){
        this.$refs.erHouseTable.toggleRowSelection(row)
      },
      handleChangeRegion(){

      },
      
      // 获取部门或人
      handleChangeDept (val, name, data) {
        if (data.dataType == 'dept') {
          this.queryForm.deptId = data.code
          delete this.queryForm.userId
          delete this.queryForm.selectId

        }else if(data.dataType == 'user'){
          this.queryForm.userId = data.code
           delete this.queryForm.deptId
          delete this.queryForm.selectId
        }else{
          setTimeout(() => {
            this.queryForm.deptId = ''
            this.queryForm.userId = ''
            this.queryForm.selectId = ''
          }, 50)
        }
      },
      handleCommunityChange(val, name, data){
        console.log(data)
         if (data.dataType == 'area') {
          this.queryForm.areaCode = data.code
          delete this.queryForm.regionId
          delete this.queryForm.communityId
          delete this.queryForm.selectAreaId

        }else if(data.dataType == 'region'){
          this.queryForm.regionId = data.code
           delete this.queryForm.areaCode
           delete this.queryForm.communityId
          delete this.queryForm.selectAreaId
        }else if(data.dataType == 'community'){
          this.queryForm.communityId = data.code
           delete this.queryForm.areaCode
           delete this.queryForm.regionId
          delete this.queryForm.selectAreaId
        }else{
          setTimeout(() => {
            this.queryForm.areaCode = ''
            this.queryForm.regionId = ''
            this.queryForm.communityId = ''
            this.queryForm.selectAreaId = ''
          }, 50)
        }
      },
      
    },
     mounted () {
       //console.log(this.regionSelectOpts)
     }
  }
</script>

<style scoped lang="scss">
 
</style>
