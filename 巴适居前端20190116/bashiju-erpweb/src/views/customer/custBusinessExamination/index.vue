<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div>
        <el-form size="small" :inline="true" :model="form" ref="form"  class="demo-form-inline">

          <el-form-item  prop="transactionTypeId">
            <el-select v-model="form.transactionTypeId" placeholder="业务类型" style="width: 130px;">
              <el-option
                v-for="item in transactionTypeOpt"
                :label="item.name"
                :value="item.value"
                :key="item.value"
              ></el-option>
            </el-select>
          </el-form-item>

            <el-form-item prop="examineTypeId" >
            <el-select v-model="form.examineTypeId" placeholder="审核类型"  style="width: 130px;">
              <el-option
                v-for="item in examineTypeOpt "
                :label="item.name"
                :value="item.value"
                :key="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="status" >
            <el-select v-model="form.status" placeholder="审核状态" style="width: 130px;">
              <el-option
                v-for="item in examineStatusOpt"
                :label="item.name"
                :value="item.value"
                :key="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="applyerId">
            <base-cascader
              placeholder="申请人"
              clearable
              v-model="form.applyerId"
              :url="getReferenceUserSelectUrl"
              :props="cascaderProps"
              :dataProps="cascaderDataProps"
              :changeOnSelect="false"
              @change="handleApplyer"
               style="width: 130px;"
            >
            </base-cascader>
          </el-form-item>

          <el-form-item prop="auditor">
            <base-cascader
              placeholder="审核人"
              clearable
              v-model="form.auditor"
              :url="getReferenceUserSelectUrl"
              :props="cascaderProps"
              :dataProps="cascaderDataProps"
              :changeOnSelect="false"
              @change="handleAuditor"
              style="width: 130px;"
            >
            </base-cascader>
          </el-form-item>


          <el-form-item  prop="time">
            <el-date-picker
              v-model="form.time"
              type="daterange"
              :default-value="ct_month"
              value-format="yyyy-MM-dd HH:mm:ss"
              range-separator="至"
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
          prop="demandId"
          align="left"
          label="需区编号"
          show-overflow-tooltip
        >
          <template slot-scope="scope">
            <a
              @click="toDetail(scope.row.demandId, scope.row.formName, scope.row.transactionTypeId)"
              href="javascript:;" class="houseId"  type="text"
            >
              {{scope.row.demandId}}
            </a>
          </template>
        </el-table-column>

        <el-table-column
          prop="transactionTypeId"
          align="left"
          label="业务类型"
          width="114"
        >
          <template slot-scope="scope">
            {{scope.row.transactionTypeId | transactionTypeIdFilter }}
          </template>
        </el-table-column>

        <el-table-column
          prop="examineTypeId"
          align="left"
          label="审核类型">
          <template slot-scope="scope">
            {{scope.row.examineTypeId | examineTypeFilter }}
          </template>
        </el-table-column>

        <el-table-column
          prop="status"
          align="left"
          label="审核状态"
          width="100"
        >
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
          prop="applyer"
          align="left"
          label="申请人">
        </el-table-column>

        <el-table-column
          prop="deptName"
          align="left"
          label="申请部门">
        </el-table-column>

        <el-table-column
          prop="applyTime"
          align="left"
          :formatter="timeFormat"
          label="申请时间"
        >
        </el-table-column>

        <el-table-column
          prop="content"
          align="left"
          label="申请内容">
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
          label="审核时间">
        </el-table-column>

        <el-table-column
          align="left"
          label="操作">
          <template slot-scope="scope">
            <el-button v-hasMultipleBtn="['confirmBtn',scope.row]"  size="small" type="text" @click.native.prevent="handleConfirm(scope.row)"  v-show="scope.row.status ==  '0' ">确认</el-button>
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
import {queryCustBusinessExamineData,custBusinessExamin,getCustBusinessExamineDropdown,sendSysMsg} from "@/request/customer/custBusinessExamination";
import PageList from '@/mixins/pageList'
import BaseCascader from '@/components/BaseCascader'
import * as RequeryLogURL from '@/request/log/customerLog'//客源日志统一接口
export default {
    components:{PageList,BaseCascader},
    mixins: [PageList],
    name:'custBusinessExamination',
        data() {
            return {
              form:{
                transactionTypeId:'',
                examineTypeId:'',
                status:'',
                operatorId:'',
                time:'',
                applyerId:'',
                auditor:''
              },
              rejectForm:{
                demandId:'',
                transactionTypeId:'',
                rejectReason:'',
                id:''
              },
              queryParams:{
                transactionTypeId:'',
                examineTypeId:'',
                status:'',
                operatorId:'',
                beginApplyTime:'',
                endApplyTime:'',
                applyerId:''
              },
              statusId:'',
              loadingView:false,
              tableData:[],
              transactionTypeOpt:[],
              transactionTypeJson:[],
              operatorIdOpt:[],
              examineStatusOpt:[],
              examineTypeOpt:[],
              statusOpt:[],
              operatorOpt:[],
              queryBtnLoading:false,
              editorDialog:false,
              rejectDialog:false,
              hackReset:false,
              getReferenceUserSelectUrl: 'manage/commonselect/queryOpenCityCompanyDepartUser', // 介绍人
              cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
              cascaderProps: { // 级联下拉组件配置选项
                value: 'code', // 指定选项的值为选项对象的某个属性值
                label: 'name' // 指定选项标签为选项对象的某个属性值
              },
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
          queryCustBusinessExamineData(btn){
                if(btn){
                  this.listQuery.page = 1
                  this.listQuery.currentPage = 1
                }
                this.loadingView =  true
                const params = Object.assign({}, this._formatQueryParams(this.form), {
                     limit:this.listQuery.limit,
                      page:this.listQuery.page,
                })
                queryCustBusinessExamineData(params).then(res =>{
                    this.tableData = res.data
                    this.listQuery.total = res.count
                    this.loadingView =  false
                })
          },
          toDetail (id, name, type) {
            this.$router.push({ path: '/customer/customerDetail/'+id, query: {formName: name,transactionTypeId: type}})
            window.localStorage.setItem('customerList', JSON.stringify(this.tableData))
          },
          timeDefaultShow(){//默认显示上个月
            this.ct_month= new Date();
            this.ct_month.setTime(this.ct_month.getTime() - 3600 * 1000 * 24 * 30);
          },
          //搜索框数据样源
          getCustBusinessExamineDropdown(){
            getCustBusinessExamineDropdown().then(res =>{
                  this.transactionTypeOpt = res.transactionType
                  this.examineStatusOpt = res.examineStatus
                  this.examineTypeOpt = res.examineType
                  this.operatorOpt = res.operatorInfo
            })
          },
          //处理参数
          _formatQueryParams(form){
            console.log(form)
            let params = {}
            let temp = {}
            this.queryParams.transactionTypeId = temp.transactionTypeId = form.transactionTypeId
            this.queryParams.examineTypeId =temp.examineTypeId = form.examineTypeId
            this.queryParams.transactionTypeId =temp.transactionTypeId = form.transactionTypeId
            this.queryParams.status =temp.status = form.status
            this.queryParams.operatorId =temp.operatorId = form.operatorId
            this.queryParams.applyerId = temp.applyerId = form.applyerId
            this.queryParams.auditor = temp.auditor = form.auditor
            if(form.time.length>0){
              this.queryParams.beginApplyTime =temp.beginApplyTime = form.time[0]
              this.queryParams.endApplyTime =temp.endApplyTime = form.time[1]
            }
            // 与更多添加弹出框参数对象合并
            params.conditions = JSON.stringify(temp)
            return params
          },
          //审核
          handleConfirm(row) {
            this.$confirm('确定要申请吗？', {
                confirmBtnText: '确定',
                cancelBtnText: '取消'
            }).then(() => {
                    let toObject =  JSON.parse(row.remark)
                    console.log(toObject)
                    debugger;
                    let params = {id: row.id, status: 1}
                    custBusinessExamin(params).then(res => {
                      if (res.success) {
                        this.$message({type: 'success', message: res.msg})
                        let operatTypeId = ''
                        switch (row.examineTypeId) {
                          case  '01' :
                            operatTypeId = 233//修改状态审核
                            break;
                          case  '02' :
                            operatTypeId = 235//淘宝审核
                            break;
                          case  '03' :
                            operatTypeId = 234//封盘审核
                            break;
                          case  '04' :
                            operatTypeId = 236//收为私客
                            break;
                        }
                        if(operatTypeId === 234){
                            // 封盘审核
                            this.statusId =  toObject.closeCustomerTypeId
                        }else if(operatTypeId === 233){
                            //修改状态审核
                            this.statusId = toObject.statusId
                        }
                        let message = {
                          sourceId:  row.demandId,//资源编号：客源编号
                          sourceCode:  row.demandId,//资源编号：客源编号
                          businessTypeId: 2,//业务类型，2：代表客源
                          sourceTypeId: row.transactionTypeId,//类型：求购
                          operatTypeId: operatTypeId,//操作类型（参考操作类型枚举）
                          logContent: row.applyer+'申请：'+row.content+'；审核结果:通过',//日志内容
                          cityCode : row.cityCode,
                          companyId : row.companyId,
                          companyName : row.companyName,
                          departmentId : row.deptId,
                          departmentName : row.deptName,
                          operator:row.applyer,
                          operatorId:row.applyerId,
                          newStatus:this.statusId,//修改后的状态
                        }
                        let operator2 = ''
                        switch (operatTypeId) {
                          case  233 :
                            operator2 = 237//修改状态
                            break;
                          case  235 :
                            operator2 = 209//淘宝
                            break;
                          case  234 :
                            operator2 = 206//封盘
                            break;
                          case  '04' :
                            operator2 = 236//收为私客
                            break;
                        }
                        RequeryLogURL.customerAddLog({message:JSON.stringify(message)}).then(res =>{
                            console.log(res)
                        }).catch(error =>{
                             console.log(error)
                        })
                        //根据申请类型选择日志内容
                        let  logContent = '';
                        if(operator2 === 237){ // 修改状态
                           logContent = '修改状态:'+'把'+row.custStatus+'改为'+toObject.statusName//日志内容
                        }else if(operator2 === 206){ //封盘
                           logContent = `封盘类型:${toObject.closeCustomerType},封盘到期时间：${toObject.closeCustomerEndTime}`
                        }else if(operator2 === 209){//淘宝
                           logContent = '申请淘宝'
                        }else{//收为私客
                           logContent = '收为私客'
                        }
                        let message2 ={
                          sourceId:  row.demandId,//资编号：房源编号
                          sourceCode:  row.demandId,//资源编号：房源编号
                          businessTypeId: 2,//业务类型，2：客源
                          sourceTypeId: row.transactionTypeId,//类型：求购
                          operatTypeId: operator2,//操作类型 ：
                          logContent: logContent
                        }
                        console.log(toObject)
                        debugger;
                        RequeryLogURL.customerQueryLog({message:JSON.stringify(message2)}).then(res =>{
                          console.log(res)
                        }).catch(error =>{
                          console.log(error)
                        })
                        let paramMsg = {
                          reSessionId: row.applyerId
                          ,reSessionUuid: row.applyerUUID
                          ,reSessionName: row.rejectForm.applyer,
                          msgContent : `申请通过:${row.content}`
                        }
                        this._sendSysMsg(paramMsg);
                        this._loadData(false)
                      } else {
                        this.$message({type: 'warning', message: res.msg})
                      }
                    }).catch(error => {
                      console.log(error)
                    })
            })
          } ,
          //打开驳回窗体
          handleReject(row){
            this.rejectDialog = true
            this.rejectForm.id = row.id
            this.rejectForm.status = '2'
            this.rejectForm.transactionTypeId = row.transactionTypeId
            this.rejectForm.demandId = row.demandId
            this.rejectForm.applyerUUID = row.applyerUUID
            this.rejectForm.applyer = row.applyer
            this.rejectForm.applyerId = row.applyerId
            console.log(this.rejectForm)
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
                        custBusinessExamin(params).then(res=>{
                              if(res.success){
                                this.$message({type: 'success', message: res.msg})
                                let message = {
                                  sourceId:  params.demandId,//资源编号：客源编号
                                  sourceCode:  params.demandId,//资源编号：客源编号
                                  businessTypeId: 2,//业务类型，2：代表客源
                                  sourceTypeId: params.transactionTypeId ,//类型：求购
                                  operatTypeId: 219,//操作类型,
                                  logContent: '审核不通过,原因:'+params.rejectReason//日志内容
                                }
                                RequeryLogURL.customerQueryLog({message:JSON.stringify(message)}).then(res =>{
                                  console.log(res)
                                }).catch(error =>{
                                  console.log(error)
                                })
                                let paramMsg = {
                                  reSessionId: this.rejectForm.applyerId
                                  ,reSessionUuid: this.rejectForm.applyerUUID
                                  ,reSessionName: this.rejectForm.applyer,
                                  msgContent : `申请被驳回：${this.rejectForm.rejectReason}`
                                }
                                this._sendSysMsg(paramMsg);
                                this._loadData(false)
                                this.handleCancel()
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
               this.queryCustBusinessExamineData(btnQuery)
          },
          //时间格式化
          timeFormat(row,column,cellValue){
               return this.$utils.timeFormat(cellValue)
          },
          //申请人
          handleApplyer(value,name,data){
            if(data.dataType !== 'user'){
              setTimeout(() => {
                this.form.applyerId = ''
              }, 50)
            }
          },
          //审核人
          handleAuditor(value,name,data){
            if(data.dataType !== 'user'){
              setTimeout(() => {
                this.form.auditor = ''
              }, 50)
            }
          },
          /**
           * 发送消息统一接口
           * @private
           */
          _sendSysMsg(param){
               sendSysMsg({message:JSON.stringify(param)}).then(res =>{

               })
            }
        },
        //过滤器
        filters:{
          transactionTypeIdFilter(value){
              if(value == 4)return '求购'
              if(value == 5)return '求租'
          },
          examineTypeFilter(value){
              if(value == '01')return '修改状态'
              if(value == '02')return '淘宝'
              if(value == '03')return '封盘'
              if(value == '04')return '收为私客'

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
          }
        },
        mounted() {
              this.getCustBusinessExamineDropdown()
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
