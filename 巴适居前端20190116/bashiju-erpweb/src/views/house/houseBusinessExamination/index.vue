<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div>
        <el-form size="small" :inline="true" :model="form" ref="form"  class="demo-form-inline">

          <el-form-item prop="transactionTypeId">
            <el-select v-model="form.transactionTypeId" placeholder="业务类型"  style="width: 130px;">
              <el-option
                v-for="item in transactionTypeOpt"
                :label="item.name"
                :value="item.value"
                :key="item.value"
              ></el-option>
            </el-select>
          </el-form-item>

            <el-form-item  prop="examineTypeId" >
            <el-select v-model="form.examineTypeId" placeholder="审核类型"  style="width: 130px;">
              <el-option
                v-for="item in examineTypeOpt "
                :label="item.name"
                :value="item.value"
                :key="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item  prop="status">
            <el-select v-model="form.status" placeholder="审核状态"  style="width: 130px;">
              <el-option
                v-for="item in examineStatusOpt"
                :label="item.name"
                :value="item.value"
                :key="item.value"
              ></el-option>
            </el-select>
          </el-form-item>

          <el-form-item  prop="applyerId">
            <base-cascader placeholder="申请人"
                 clearable
                 v-model="form.applyerId"
                 :url="getReferenceUserSelectUrl"
                 :props="cascaderProps"
                 :dataProps="cascaderDataProps"
                 style="width: 130px;"
                 :changeOnSelect="false"
                 @change="handleApplyerId"
            >
            </base-cascader>
          </el-form-item>

          <el-form-item  prop="auditor">
            <base-cascader placeholder="审核人"
                   clearable
                   v-model="form.auditor"
                   :url="getReferenceUserSelectUrl"
                   :props="cascaderProps"
                   :dataProps="cascaderDataProps"
                   style="width: 130px;"
                   :changeOnSelect="false"
                   @change="handleAuditor"
            >
            </base-cascader>
          </el-form-item>


          <el-form-item  prop="time">
            <el-date-picker
              v-model="form.time"
              type="daterange"
              value-format="yyyy-MM-dd HH:mm:ss"
              range-separator="至"
              :default-value="ct_month"
              start-placeholder="申请开始日期"
              end-placeholder="申请结束日期"
              :default-time="['00:00:00', '23:59:59']"
              align="right">
            </el-date-picker>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click.native.prevent="_loadData(true)" :loading="queryBtnLoading">查询</el-button>
            <el-button @click.native.prevent="resetForm">清空</el-button>
          </el-form-item>

        </el-form>
      </div>
    </div>
    <div class="page-content-bd" v-loading="loadingView">
      <el-table
        :data="tableData"
        border
        tooltip-effect="light"
        align="center"
        style="width: 100%"
      >

        <el-table-column
          prop="transactionTypeId"
          align="left"
          label="业务类型">
          <template slot-scope="scope">
            {{scope.row.transactionTypeId | transactionTypeIdFilter }}
          </template>
        </el-table-column>

        <el-table-column
          prop="examineTypeId"
          align="left"
          label="审核类型"
          :formatter="examineTypeFormatter"
        >
        </el-table-column>

        <el-table-column
          prop="status"
          align="left"
          label="审核状态">
          <template slot-scope="scope">
            {{scope.row.status |statusFilter }}
          </template>
        </el-table-column>

        <el-table-column
          prop="rejectReason"
          align="left"
          label="驳回原因">
        </el-table-column>

        <el-table-column
          prop="handleType"
          align="left"
          label="处理方式"
        :formatter="handleTypeFormatter"
        >
        </el-table-column>

        <el-table-column
          prop="operator"
          align="left"
          label="申请人">
        </el-table-column>

        <el-table-column
          prop="deptName"
          align="left"
          label="申请部门">
        </el-table-column>

        <el-table-column
          prop="addTime"
          align="left"
          :formatter="timeFormat"
          label="申请时间"
          show-overflow-tooltip
        >
        </el-table-column>

        <el-table-column
          prop="content"
          align="left"
          label="申请内容"
          show-overflow-tooltip
        >
        </el-table-column>

        <el-table-column
          prop="houseId"
          align="left"
          label="资源编号">
          <template slot-scope="scope">
            <a
              @click="toDetail(scope.row.shhId, scope.row.houseUsesId, scope.row.houseId)"
              href="javascript:;" class="houseId"  type="text"
            >
              {{scope.row.houseId}}
            </a>
          </template>
        </el-table-column>

        <el-table-column
          prop="auditorName"
          align="left"
          label="审核人">
        </el-table-column>

        <el-table-column
          prop="auditTime"
          align="left"
          :formatter="timeFormat"
          label="审核时间"
          show-overflow-tooltip
        >
        </el-table-column>

        <el-table-column
          align="left"
          label="操作">
          <template slot-scope="scope">
            <el-button v-hasMultipleBtn="['confirmBtn',scope.row]"   size="small" type="text" @click.native.prevent="handleConfirm(scope.row)"  v-show="scope.row.status ==  '0' ">确认</el-button>
            <el-button v-hasMultipleBtn="['rejectBtn',scope.row]"  size="small" type="text" @click.native.prevent="handleReject(scope.row)" v-show="scope.row.status ==  '0' ">驳回</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--分页控件-->
      <b-pagination
        :listQuery="listQuery"
        @handleSizeChange="handleSizeChange"
        @handleCurrentChange="handleCurrentChange">
      </b-pagination>
    </div>
    <!--编辑窗体-->
    <template>
      <el-dialog
        title="修改预约时间"
        :visible.sync="editorDialog"
        :close-on-click-modal="false"
        top="30vh"
        width="400px">
        <update-time-and-date @handleClick="openOrCloseWindow" :data="currentRowData"   v-if="hackReset"></update-time-and-date>
      </el-dialog>
    </template>

    <!--驳回窗体-->
    <template>
      <el-dialog
        title="驳回"
        :visible.sync=" rejectDialog"
        :close-on-click-modal="false"
        top="30vh"
        width="400px">

        <el-form :model="rejectForm"  label-width="85px"   :rules="rules" ref="rejectForm" class="demo-ruleForm" size="medium">
          <el-form-item label="驳回理由" prop="rejectReason" size="medium">
            <el-input v-model="rejectForm.rejectReason"></el-input>
          </el-form-item>

          <el-form-item>
            <div class="btn-group">
              <el-button type="primary"  @click="handleSubmit">确认</el-button>
              <el-button  @click="handleCancel">取消</el-button>
            </div>
          </el-form-item>
        </el-form>
      </el-dialog>
    </template>
  </div>
</template>
<style></style>
<script>
  import PageList from '@/mixins/pageList'
  import {queryHouseBusinessExaminationData,getBusinessExamineDropdown,examineApplication,sendSysMsg} from "@/request/house/houseBusinessExamination";
  import  *  as  RequestLogUrl from '@/request/log/housePlatformLog'
  import BaseCascader from '@/components/BaseCascader'
  export default {
    components:{PageList,BaseCascader},
    mixins: [PageList],
    name: 'houseBusinessExamination',
    data() {
        return {
          form:{
            transactionTypeId:'',
            examineTypeId:'',
            status:'',
            auditor:'',
            applyerId:'',
            time:''
          },
          rejectForm:{
            houseId:'',
            transactionTypeId:'',
            rejectReason:'',
            id:'',
            examineTypeId:''
          },
          getReferenceUserSelectUrl: 'manage/commonselect/queryOpenCityCompanyDepartUser', // 介绍人
          cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
          cascaderProps: { // 级联下拉组件配置选项
            value: 'code', // 指定选项的值为选项对象的某个属性值
            label: 'name' // 指定选项标签为选项对象的某个属性值
          },
          statusId:'',
          loadingView:false,
          tableData:[],
          transactionTypeOpt:[],
          transactionTypeJson:[],
          handleTypeOpt:[],
          operatorIdOpt:[],
          examineStatusOpt:[],
          examineTypeOpt:[],
          statusOpt:[],
          operatorOpt:[],
          queryBtnLoading:false,
          editorDialog:false,
          rejectDialog:false,
          hackReset:false,
          ct_month:null,
          listQuery:{
            limit:10,
            page:1,
            total:0
          },rules:{
            rejectReason:[
              {required:true,message:'必填项',trigger:'change'}
            ]
          }
        }
    },
    methods: {
      //查询数据
      queryHouseBusinessExaminationData(btn){
         if(btn){
          this.listQuery.page = 1
          this.listQuery.currentPage = 1
        }
            this.loadingView =  true
            let params = Object.assign({},this.form,{
                limit:this.listQuery.limit,
                page:this.listQuery.page
            })
        if(params.time.length >0){
              params.beginAddTime = params.time[0]
              params.endAddTime = params.time[1]
        }

        delete params.time
        console.log(params)
        queryHouseBusinessExaminationData(params).then(res =>{
            this.tableData = res.data
            this.listQuery.total = res.count
            this.loadingView =  false
        })
      },
      toDetail (id, name, code) {
        this.$router.push({ path: './houseUsedDetail/' + id + '/' + code, query: { formName: name}})
        window.localStorage.setItem('houseList', JSON.stringify(this.tableData))
      },
      examineTypeFormatter(row, column, cellValue){
        for(let i =0;i<this.examineTypeOpt.length;i++){
          if(this.examineTypeOpt[i].value==cellValue) return  this.examineTypeOpt[i].name
        }
      },
      handleTypeFormatter(row, column, cellValue){
        console.log(cellValue)
        for(let i =0;i<this.handleTypeOpt.length;i++){
          if(this.handleTypeOpt[i].value==cellValue) return  this.handleTypeOpt[i].name
        }
      },

      //搜索框数据样源
      getBusinessExamineDropdown(){
        getBusinessExamineDropdown().then(res =>{
              this.transactionTypeOpt = res.transactionType
              this.examineStatusOpt = res.examineStatus
              this.examineTypeOpt = res.examineType
              // this.operatorOpt = res.operatorInfo
              this.handleTypeOpt = res.handleType
        })
      },
      //审核
      handleConfirm(row) {
        this.$confirm('确定要申请吗？', {
            confirmBtnText: '确定',
            cancelBtnText: '取消'
        }).then(() => {
          if(row.examineTypeId != '02'){
            let toObject =  JSON.parse(row.remark)
              this.statusId = toObject.statusId
          }
          if (row.examineTypeId == '03') {//房源举报
                let handleType = '02'  //放入公盘
                let params = {id: row.id, status: "1", handleType: '02'}
                examineApplication(params).then(res => {
                  if (res.success) {
                      this.$message({type: 'success', message: res.msg})
                    let operatTypeId = ''
                    switch (row.examineTypeId) {
                      case  '01' :
                        operatTypeId = 46//房源修改状态审核
                        break;
                      case  '02' :
                        operatTypeId = 45//新增
                        break;
                      case  '03' :
                        operatTypeId = 47//房源举报
                        break;
                      case  '04' :
                        operatTypeId = 48//淘宝
                        break;
                      case  '05' :
                        operatTypeId = 49//开盘
                        break;
                      case  '06' :
                        operatTypeId = 50//封盘
                        break;
                      case  '07' :
                        operatTypeId = 51//驳回房源修改
                        break;
                      case  '08' :
                        operatTypeId = 52 //经纪人反馈
                        break;
                      case  '09' :
                        operatTypeId = 53 //公盘转私盘
                        break;

                    }

                      let message = {
                        sourceId:  row.shhId,//资编号：房源编号
                        sourceCode:  row.houseId,//资源编号：房源编号
                        businessTypeId: 1,//业务类型，1：房源
                        sourceTypeId: row.transactionTypeId,//类型：求购
                        operatTypeId: operatTypeId,//操作类型
                        logContent: row.applyer +':申请:'+row.houseId+row.content+'=>审核结果:审核通过',//日志内容
                        cityCode : row.cityCode,
                        companyId : row.companyId,
                        companyName : row.companyName,
                        departmentId : row.deptId,
                        departmentName : row.deptName,
                        operator:row.applyer,
                        operatorId:row.applyerId,
                        newStatus:this.statusId,//修改后的状态
                      }
                      RequestLogUrl.houseQueryLog({message:JSON.stringify(message)}).then(res =>{
                        console.log(res)

                      }).catch(error =>{
                        console.log(error)
                      })
                    let operatTypeId1 = ''
                    switch (row.examineTypeId) {
                      case  '01' :
                        operatTypeId1 = 4//房源修改状态审核
                        break;
                      case  '02' :
                        operatTypeId1 = 3//新增
                        break;
                      case  '03' :
                        operatTypeId1 = 13//房源举报
                        break;
                      case  '04' :
                        operatTypeId1 = 24//淘宝
                        break;
                      case  '05' :
                        operatTypeId1 = 10//开盘
                        break;
                      case  '06' :
                        operatTypeId1 = 54//封盘
                        break;
                    }
                    let message2 ={
                      sourceId:  row.shhId,//资编号：房源编号
                      sourceCode:  row.houseId,//资源编号：房源编号
                      businessTypeId: 1,//业务类型，1：房源
                      sourceTypeId: row.transactionTypeId,//类型：求购
                      operatTypeId: operatTypeId1,//操作类型 ：
                      logContent: '新增'+row.houseId,//日志内容
                      addTime:this.$utils.timeFormat(row.addTime),
                      updateTime:this.$utils.timeFormat(row.addTime)
                    }
                    RequestLogUrl.houseAddLog({message:JSON.stringify(message2)}).then(res =>{
                      console.log(res)
                    }).catch(error =>{
                      console.log(error)
                    })
                      let paramMsg ={
                        reSessionId: row.applyerId
                        ,reSessionUuid: row.applyerUUID
                        ,reSessionName: row.applyer,
                         msgContent : `审核通过,${row.content}`
                      }
                      this._sendSysMsg(paramMsg);
                      this._loadData(false)
                 }
              })
          }else {//审核通过
                  let params = {id: row.id, status: 1}
                  examineApplication(params).then(res => {
                  if (res.success) {
                    this.$message({type: 'success', message: res.msg})
                    let operatTypeId = ''
                    switch (row.examineTypeId) {
                      case  '01' :
                        operatTypeId = 46//房源修改状态审核
                        break;
                      case  '02' :
                        operatTypeId = 45//新增
                        break;
                      case  '03' :
                        operatTypeId = 47//房源举报
                        break;
                      case  '04' :
                        operatTypeId = 48//淘宝
                        break;
                      case  '05' :
                        operatTypeId = 49//开盘
                        break;
                      case  '06' :
                        operatTypeId = 50//封盘
                        break;
                      case  '07' :
                        operatTypeId = 51//驳回房源修改
                        break;
                      case  '08' :
                        operatTypeId = 52 //经纪人反馈
                        break;
                      case  '09' :
                        operatTypeId = 53 //公盘转私盘
                        break;

                    }
                    let message = {
                      sourceId:  row.shhId,//资编号：房源编号
                      sourceCode:  row.houseId,//资源编号：房源编号
                      businessTypeId: 1,//业务类型，1：房源
                      sourceTypeId: row.transactionTypeId,//类型：求购
                      operatTypeId: operatTypeId,//操作类型 ：
                      logContent: '审核通过 :'+row.houseId,//日志内容
                      cityCode : row.cityCode,
                      companyId : row.companyId,
                      companyName : row.companyName,
                      departmentId : row.deptId,
                      departmentName : row.deptName,
                      operator:row.applyer,
                      operatorId:row.applyerId,
                      newStatus:this.statusId,//修改后的状态
                    }
                    RequestLogUrl.houseQueryLog({message:JSON.stringify(message)}).then(res =>{
                        if(res.success){

                        }
                    }).catch(error =>{
                      console.log(error)
                    })
                    let operatTypeId1 = ''
                    switch (row.examineTypeId) {
                      case  '01' :
                        operatTypeId1 = 4//房源修改状态审核
                        break;
                      case  '02' :
                        operatTypeId1 = 3//新增
                        break;
                      case  '03' :
                        operatTypeId1 = 13//房源举报
                        break;
                      case  '04' :
                        operatTypeId1 = 24//淘宝
                        break;
                      case  '05' :
                        operatTypeId1 = 10//开盘
                        break;
                      case  '06' :
                        operatTypeId1 = 54//封盘
                        break;
                      // case  '07' :
                      //   operatTypeId1 = 51//驳回房源修改
                      //   break;
                      // case  '08' :
                      //   operatTypeId1 = 52 //经纪人反馈
                      //   break;
                      // case  '09' :
                      //   operatTypeId1 = 53 //公盘转私盘
                      //   break;

                    }
                    let message2 ={
                      sourceId:  row.shhId,//资编号：房源编号
                      sourceCode:  row.houseId,//资源编号：房源编号
                      businessTypeId: 1,//业务类型，1：房源
                      sourceTypeId: row.transactionTypeId,//类型：求购
                      operatTypeId: operatTypeId1,//操作类型 ：
                      logContent: '新增'+row.houseId,//日志内容
                      addTime:this.$utils.timeFormat(row.addTime),
                      updateTime:this.$utils.timeFormat(row.addTime)
                    }
                    RequestLogUrl.houseAddLog({message:JSON.stringify(message2)}).then(res =>{
                      console.log(res)
                    }).catch(error =>{
                      console.log(error)
                    })
                    let paramMsg ={
                      reSessionId: row.applyerId
                      ,reSessionUuid: row.applyerUUID
                      ,reSessionName: row.applyer,
                      msgContent : `审核通过,${row.content}`
                    }
                    this._sendSysMsg(paramMsg);
                    this._loadData(false)
                  } else {
                    this.$message({type: 'warning', message: res.msg})
                  }
                }).catch(error => {
                  console.log(error)
                })
            }
        })
      } ,
      //打开驳回窗体
      handleReject(row){
        this.rejectDialog = true
        this.rejectForm.id = row.id
        this.rejectForm.status = '2'
        this.rejectForm.transactionTypeId = row.transactionTypeId
        this.rejectForm.houseId = row.houseId
        this.rejectForm.examineTypeId = row.examineTypeId
        this.rejectForm.applyer = row.applyer
        this.rejectForm.applyerId = row.applyerId
        this.rejectForm.applyerUUID = row.applyerUUID
        this.rejectForm.content = row.content

      },
      handleCancel(){
        this.rejectDialog = false
        this.$refs['rejectForm'].resetFields()
      },
      // 提交驳回信息
      handleSubmit(){
          this.$refs['rejectForm'].validate((valide)=>{
              if(valide){
                  this.$confirm('确定要驳回吗？', {
                    confirmBtnText: '确定',
                    cancelBtnText: '取消'
                  }).then(()=>{
                     let params = {...this.rejectForm}
                     if(params.examineTypeId == '03'){
                       params.handleType ='02'
                     }
                     examineApplication(params).then(res=>{
                          if(res.success){
                            this.$message({type: 'success', message: res.msg})
                            let operatTypeId = ''
                            switch (params.examineTypeId) {
                              case  '01' :
                                operatTypeId = 46//房源修改状态审核
                                break;
                              case  '02' :
                                operatTypeId = 45//新增
                                break;
                              case  '03' :
                                operatTypeId = 47//房源举报
                                break;
                              case  '04' :
                                operatTypeId = 48//淘宝
                                break;
                              case  '05' :
                                operatTypeId = 49//开盘
                                break;
                              case  '06' :
                                operatTypeId = 50//封盘
                                break;
                              case  '07' :
                                operatTypeId = 51//驳回房源修改
                                break;
                              case  '08' :
                                operatTypeId = 52 //经纪人反馈
                                break;
                              case  '09' :
                                operatTypeId = 53 //公盘转私盘
                                break;
                            }
                            let message = {
                              sourceId:  params.id,//资编号：房源编号
                              sourceCode:  params.houseId,//资源编号：房源编号
                              businessTypeId: 1,//业务类型，1：房源
                              sourceTypeId: params.transactionTypeId,//类型：求购
                              operatTypeId: operatTypeId,//操作类型 ：
                              logContent: params.applyer+':申请'+params.content +'审核结果=>驳回,驳回原因'+params.rejectReason//日志内容
                            }
                            RequestLogUrl.houseQueryLog({message:JSON.stringify(message)}).then(res =>{
                                console.log(res)
                            }).catch(error =>{
                              console.log(error)
                            })
                            let paramMsg ={
                              reSessionId: this.rejectForm.applyerId
                              ,reSessionUuid: this.rejectForm.applyerUUID
                              ,reSessionName: this.rejectForm.applyer,
                              msgContent : `${this.rejectForm.content}驳回`
                            }
                            this._sendSysMsg(paramMsg);
                            this._loadData(false)
                            this.handleCancel()
                          }else{
                            this.$message({type: 'warning', message: res.msg})
                          }
                     }).catch(error =>{
                       console.log(error)
                     })
                  }).catch(()=>{
                    this.$message({type: 'info', message: '已取消'})
                  })
              }
          })
      },
      //清空搜索框
      resetForm(){
           this.$refs['form'].resetFields()
      },
      _loadData(btnQuery){
           this.queryHouseBusinessExaminationData(btnQuery)
      },
      //时间格式化
      timeFormat(row,column,cellValue){
           return this.$utils.timeFormat(cellValue)
      },
      timeDefaultShow(){//默认显示上个月
        this.ct_month= new Date();
        this.ct_month.setTime(this.ct_month.getTime() - 3600 * 1000 * 24 * 30);
      },
      //审核人
      handleAuditor(value,name,data){
        if(data.dataType !== 'user'){
          setTimeout(() => {
            this.form.auditor = ''
          }, 50)
        }
      },
      //申请人
      handleApplyerId(value,name,data){
        if(data.dataType !== 'user'){
          setTimeout(() => {
            this.form.applyerId = ''
          }, 50)
        }
      },
      /**
       * 发送消息统一接口
       * @private
       * @param params
       */
      _sendSysMsg(params){
         sendSysMsg({message:JSON.stringify(params)}).then(res =>{
            console.log(res);
         })
      }
    },
    //过滤器
    filters:{
      transactionTypeIdFilter(value){
          if(value == 1)return '出售'
          if(value == 2)return '出租'
          if(value == 3)return '租售'
      },
      statusFilter(value){
          if(value == 0)return '未审核'
          if(value == 1)return '已审核'
          if(value == 2)return '已驳回'
      },
      handleTypeFilter(value){
          if(value == '01' ) return '房源失效(撤单)'
          if(value == '02' ) return '放入公盘'
          if(value == '03' ) return '放入淘宝'
          if(value == '04' ) return '转移给其他人'
      },
    },
    mounted() {
          this.getBusinessExamineDropdown()
          this.timeDefaultShow()
    }

    }

</script>
<style>
  .houseId {
    color: #409eff;
  &:hover {
     text-decoration: underline;
   }
  }
</style>
