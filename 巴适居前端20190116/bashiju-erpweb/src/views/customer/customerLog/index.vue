<template>
  <div class="page-content">
    <div class="page-content-hd">
      <el-form :model="queryForm" ref="queryForm" :inline="true" size="small" v-if="operatorTypeObj">
        <el-form-item prop="demandType" label="资源类型">
          <el-select v-model="queryForm.demandType" style="width: 100px">
            <el-option v-for="item in sourceTypeObj" :key="item.value" :label="item.name" :value="item.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="actionType" label="操作类型">
          <el-select v-model="queryForm.actionType"  style="width: 120px" filterable>
            <el-option v-for="item in operatorTypeObj" :key="item.value" :label="item.name" :value="item.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="addTime" label="起止时间">
          <el-date-picker
            type="daterange"
            range-separator="—"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            v-model="queryForm.addTime"
            value-format="yyyy-MM-dd"
            style="width: 220px"
            clearable
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item prop="operatorId" label="操作员工">
          <base-cascader v-model="queryForm.operatorId" v-if="roleGroup==='03'" filterable
                         :url="getReferenceUserSelectUrl" :props="cascaderProps" :changeOnSelect="false"
                         :dataProps="cascaderDataProps" placeholder="选择员工" @change="jurgeUser" style="width: 120px"></base-cascader>

          <base-cascader v-model="queryForm.operatorId" v-else :url="getReferenceUserSelectUrl"
           :props="cascaderProps" filterable :dataProps="cascaderDataProps" placeholder="选择员工"
           @change="jurgeOtherSelect" style="width: 120px"></base-cascader>
        </el-form-item>
        <el-form-item prop="vagueData" label="模糊查询">
          <base-vague-autocomplete
            class="w300"
            :dataProps="vagueQueryInfo"
            v-model.trim="queryForm.vagueData" @select="handleSelectInfo"
            placeholder="资源编号,日志内容"
          >
          </base-vague-autocomplete>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="small" @click="_loadData(true)" :loading="queryBtnLoading">查询</el-button>
          <el-button type="primary" size="small" @click="resetForm">清空</el-button>
        </el-form-item>
      </el-form>
    </div>

    <div class="page-content-hd">
      <el-table :data="tableData" border align="center" tooltip-effect="light" size="small" v-loading="loadingView">
        <el-table-column label="资源类型" prop="sourceTypeName" show-overflow-tooltip width=120px></el-table-column>
        <el-table-column label="所在部门" prop="departmentName" show-overflow-tooltip width=120px></el-table-column>
        <el-table-column label="操作员工" prop="operator" show-overflow-tooltip width=120px></el-table-column>
        <el-table-column label="操作时间" prop="addTime" show-overflow-tooltip width=140px>
          <template slot-scope="scope">
            {{$utils.timeFormat(scope.row.addTime,'{y}-{m}-{d} {h}:{i}:{s}')}}
          </template>
        </el-table-column>
        <el-table-column label="操作类型" prop="operatTypeName" show-overflow-tooltip width=120px></el-table-column>
        <el-table-column label="资源编号" prop="sourceId" show-overflow-tooltip width=140px>
          <template slot-scope="scope">
            <a @click="toDemandDetail(scope.row)"
               href="javascript:;" class="dealId" type="text">
              {{scope.row.sourceId}}
            </a>
          </template>
        </el-table-column>
        <el-table-column label="操作内容" prop="logContent" show-overflow-tooltip></el-table-column>
      </el-table>
      <!--分页控件-->
      <b-pagination
        :listQuery="listQuery"
        @handleSizeChange="handleSizeChange"
        @handleCurrentChange="handleCurrentChange">
      </b-pagination>
    </div>
  </div>
</template>

<script>
  // 模糊查询其他  门牌号 电话 编号..
  const vagueQueryInfo = [
    {label: '资源编号', type: 1},
    {label: '日志内容', type: 2}
  ]

  import * as RequestURL from '@/request/log/customerLog'
  import PageList from '@/mixins/pageList'
  import BaseVagueAutocomplete from '@/components/BaseVagueAutocomplete'
  import BaseCascader from '@/components/BaseCascader'
  import BaseSelect from '@/components/BaseCascader'
  import * as selectUrl from '@/request/manage/common' // 获取下拉框数据 url地址

  export default {
    name: "customerLog",
    mixins: [PageList],
    components: {BaseVagueAutocomplete,BaseSelect,BaseCascader},
    data() {
      return {
        tableData: [],
        vagueQueryInfo: vagueQueryInfo,
        queryForm: {
          demandType: '',
          actionType: '',
          addTime: '',
          operatorId: '',
          departmentId: '',
          companyId: '',
          vagueData: ''
        },
        vagueData: {},
        queryBtnLoading: false,
        loadingView: false,
        getReferenceUserSelectUrl: selectUrl.getReferenceUserSelectUrl,
        cascaderDataProps: {id: 'code', parent: 'parentCode'},
        cascaderProps: {
          value: 'code',
          label: 'name'
        },
        operatorTypeObj: null,
        sourceTypeObj: null,
        userInfo: null,
        roleGroup: ''
      }
    },
    methods: {
      jurgeUser(value,name,data){
        // 普通用户
        if (data.dataType !== 'user') {
          this.$message({
            showClose: true,
            type: 'warning',
            message: '只能选择用户!'
          })
          setTimeout(() => {
            this.queryForm.operatorId = ''
          }, 50)
        }
      },
      // 超级管理员组,城市管理员组
      jurgeOtherSelect(value,name,data){
        if (data.dataType === 'city') {
          setTimeout(() => {
            this.queryForm.operatorId = ''
          }, 50)
        }else{
          this.setSelectValue(data,value)
        }
      },
      // 设置用户选择的下拉值
      setSelectValue(data,value){
        if(data.dataType === 'company'){
          this.queryForm.departmentId = ''
          this.queryForm.companyId = value
        }else if(data.dataType === 'dept'){
          this.queryForm.companyId = ''
          this.queryForm.departmentId = value
        }else if(data.dataType === 'user'){
          this.queryForm.departmentId = ''
          this.queryForm.operatorId = value
        }
      },
      // 点击其他信息输入建议项赋值
      handleSelectInfo(item) {
        // 点击坐栋输入建议项赋值
        this.vagueData = {
          type: item.type,
          value: item.value,
        }
      },
      // 进入客源详细
      toDemandDetail(row){
        this.$router.push({ path: '/customer/customerDetail/' + row.sourceCode
          ,query: { demandId: row.sourceCode, formName: row.remark
            ,transactionTypeId: row.sourceTypeId}})
      },
      _loadData(btnQuery) {
        if (btnQuery) {
          this.listQuery.page = 1
          this.listQuery.currentPage = 1
          this.queryBtnLoading = true
        }
        this.loadingView = true
        this._getTableData()
      },

      _getTableData() {
        let param = {...this.queryForm}
        let params = Object.assign({},{} , {
          page: this.listQuery.page,
          limit: this.listQuery.limit
        })
        if (this.vagueData) {
          switch (this.vagueData.type) {
            case 1:
              param.sourceId= this.vagueData.value.trim()
              break
            case 2:
              param.logContent = this.vagueData.value.trim()
              break
            default:
              break
          }
        }
        if(param.addTime && param.addTime.length > 0){
          param.minTime = param.addTime[0]
          param.maxTime = param.addTime[1]
        }else{
          param.minTime = ''
          param.maxTime = ''
        }
        if(this.roleGroup !== '03'){
          if(this.queryForm.departmentId || this.queryForm.companyId){
            param.operatorId = ''
          }
        }
        delete param.addTime
        delete param.vagueData
        params.jsonData = JSON.stringify(param)
        RequestURL.queryCustomerActionLogPageList(params).then(res => {
          this.tableData = res.data
          this.listQuery.total = res.count
          this.queryBtnLoading = false
          this.loadingView = false
        }).catch(err=>{
          this.queryBtnLoading = false
          this.loadingView = false
        })
      },
      _timeFormat() {
        return this.$utils.timeFormat()
      },
      resetForm(){
        if(this.$refs['queryForm']){
          this.$refs['queryForm'].resetFields()
          this.vagueData = {}
          this.queryForm.addTime = ''
          this.queryForm.departmentId = ''
          this.queryForm.companyId = this.userInfo.companyId
        }
      },
      returnOperatorTypeObj(){
        RequestURL.returnOperatorTypeObj({type: '2'}).then(res=>{
          this.operatorTypeObj = res
        }).catch(err=> {
          console.log(err)
        })
      },
      returnSourceTypeObj(){
        RequestURL.returnSourceTypeObj({type: '2'}).then(res=>{
          this.sourceTypeObj = res
        }).catch(err=> {
          console.log(err)
        })
      },
      returnUserInfoUserThread(){
        selectUrl.returnUserInfoUserThread().then(res=>{
          this.roleGroup = res.roleGroup
          this.userInfo = res
        }).catch(err=> {
          console.log(err)
        })
      }
    },
    watch: {
      // 监听查询 如果当前输入的内容和之前绑定的输入建议不一致 则说明没有点击建议项 赋默认值
      'queryForm.vagueData': function (newValue) {

        if (!newValue) {
          this.vagueData = {}
          return
        }
        let defaultVal = {
          type: vagueQueryInfo[0].type,
          value: newValue,
        }

        if (this.vagueData) {
          if (newValue !== this.vagueData) {
            this.vagueData = defaultVal
          }
        } else {
          // 没有点击过输入建议项
          this.vagueData = defaultVal
        }
      },
      'queryForm.operatorId': function(newValue){
        if (!newValue) {
          this.queryForm.companyId = this.userInfo.companyId
          this.queryForm.departmentId = ''
          return
        }
      }
    },
    mounted(){
      this.returnOperatorTypeObj()
      this.returnSourceTypeObj()
      this.returnUserInfoUserThread()
    }
  }
</script>

<style scoped lang="scss">
  .dealId {
    color: #409eff;
  &:hover {
     text-decoration: underline;
   }
  }
</style>
