<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form" ref="query-form">
        <el-form size="small" :inline="true" :model="queryForm" ref="queryForm" class="demo-form-inline">

          <el-form-item prop="houseStatus">
            <el-select v-model="queryForm.houseStatus" clearable style="width: 120px;" placeholder="房源状态" >
              <el-option
                v-for="item in houseStatusOpt"
                :key="item.value"
                :label="item.name"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>

          <el-form-item prop="keyStatus">
              <el-select v-model="queryForm.keyStatus" clearable style="width: 120px;" placeholder="钥匙状态" >
                <el-option
                  v-for="item in keyStatusOpt"
                  :key="item.value"
                  :label="item.name"
                  :value="item.value"
                ></el-option>
              </el-select>
          </el-form-item>

          <el-form-item prop="reciverId">
            <base-cascader
                placeholder="得钥匙人"
                clearable
                v-model="queryForm.reciverId"
                :url="getReferenceUserSelectUrl"
                :props="cascaderProps"
                :dataProps="cascaderDataProps"
                :changeOnSelect="false"
                style="width: 130px"
                @change="handleReciver"
            >
            </base-cascader>
          </el-form-item>

          <el-form-item prop="reciveTime">
            <el-date-picker
              v-model="queryForm.reciveTime"
              type="daterange"
              range-separator="至"
              :default-value="ct_month"
              start-placeholder="收匙开始日期"
              end-placeholder="收匙结束日期"
              value-format="yyyy-MM-dd"
            >
            </el-date-picker>
          </el-form-item>


          <el-form-item>
            <el-button type="success" @click.native.prevent="_loadData(true)" :loading="queryBtnLoading" size="small">查询</el-button>
            <el-button type="info" @click="_resetForm('queryForm')" size="small">清空</el-button>
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
        size="small"
      >
        <el-table-column
          prop="keyStatus"
          align="center"
          width="100px;"
          label="钥匙状态">
          <template slot-scope="scope">
          {{scope.row.keyStatus | keyStatusFilter}}
          </template>
        </el-table-column>

        <el-table-column
          prop="houseStatus"
          show-overflow-tooltip
          align="center"
          width="100px;"
          label="房源状态">
        </el-table-column>

        <el-table-column
          prop="areaName"
          align="center"
          width="100px;"
          label="所在区域">
        </el-table-column>

        <el-table-column
          prop="regionName"
          align="center"
          label="片区">
        </el-table-column>

        <el-table-column
          prop="communityName"
          align="center"
          label="小区">
        </el-table-column>

        <el-table-column
          prop="buildingsName"
          align="center"
          width="100px;"
          label="座栋名称">
        </el-table-column>

        <el-table-column
          prop="buildingsUnitName"
          align="center"
          label="单元名称">
        </el-table-column>


        <el-table-column
          prop="buildingHouseName"
          align="center"
          label="房号名称">
        </el-table-column>


        <el-table-column
          prop="houseId"
          align="center"
          label="房源编号"
          show-overflow-tooltip
        >
          <template slot-scope="scope">
            <a
              @click="toDetail(scope.row.shhId, scope.row.houseUsesId, scope.row.houseId,scope.row)"
              href="javascript:;" class="houseId"  type="text"
            >
              {{scope.row.houseId}}
            </a>
          </template>
        </el-table-column>

        <el-table-column
          prop="reciveTime"
          align="center"
          :formatter="_timeFormat"
          label="收匙时间">
        </el-table-column>

        <el-table-column
          prop="deptName"
          align="center"
          label="钥匙所在店">
        </el-table-column>

        <el-table-column
          prop="reciver"
          align="center"
          label="得钥匙人">
        </el-table-column>

        <el-table-column
          prop="borrower"
          align="center"
          label="借出钥匙人">
        </el-table-column>

        <el-table-column
          prop="keyCode"
          align="center"
          label="钥匙编号"
          show-overflow-tooltip
        >
        </el-table-column>

        <el-table-column
          prop="receipt"
          align="center"
          label="收据">
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
          width="180px"
        >
          <template slot-scope="scope">
            <div v-if="scope.row.keyStatus === '01'">
              <el-button v-hasMultipleBtn="['hsKeyLog',scope.row]"  @click="handleLog(scope.row)" type="text" size="small" >日志</el-button>
              <el-button v-hasMultipleBtn="['hsKeyBorrow',scope.row]"  @click="handleBorrow(scope.row)" type="text" size="small">借出</el-button>
              <el-button v-hasMultipleBtn="['hsKeyBack',scope.row]" @click="handleReturn(scope.row)" type="text" size="small" >退还</el-button>
              <el-button v-hasMultipleBtn="['hsKeySeal',scope.row]" @click="handleSeal(scope.row)" type="text" size="small">封存</el-button>
            </div>
            <div v-else-if="scope.row.keyStatus === '02'">
              <el-button v-hasMultipleBtn="['hsKeyLog',scope.row]"  @click="handleLog(scope.row)" type="text" size="small" >日志</el-button>
              <el-button   @click="handleBack(scope.row)" type="text" size="small" >归还</el-button>
            </div>
            <div v-else-if="scope.row.keyStatus === '03'">
              <el-button v-hasMultipleBtn="['hsKeyLog',scope.row]"  @click="handleLog(scope.row)" type="text" size="small" >日志</el-button>
              <el-button v-hasMultipleBtn="['hsKeyBack',scope.row]" @click="handleReturn(scope.row)" type="text" size="small" >退还</el-button>
            </div>

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
    <template>
      <!--钥匙日志窗体-->
      <el-dialog
        title="查看房源钥匙日志"
        :visible.sync="logDlg"
        width="50%"
        :close-on-click-modal="false"
      >
        <key-log :data="currentRowData" v-if="hackReset"></key-log>
      </el-dialog>
    </template>

    <template>
      <!--借出钥匙窗体-->
      <el-dialog
        title="借出钥匙"
        :visible.sync="borrowKeyDlg"
        width="400px"
        :close-on-click-modal="false"
      >
        <el-form :model="borrowForm"  label-width="100px"   :rules="rules" ref="borrowForm" class="demo-ruleForm" size="medium">
          <el-form-item label="钥匙借出给" prop="borrowerId" v-if="!isInput">
              <base-cascader
                placeholder="钥匙借出给"
                clearable
                :changeOnSelect="false"
                v-model="borrowForm.borrowerId"
               :url="getReferenceUserSelectUrl"
               :props="cascaderProps"
                :dataProps="cascaderDataProps"
                @change="borrowerName">
              </base-cascader>
          </el-form-item>
          <el-form-item label="钥匙借出给" prop="borrower" v-else-if="isInput">
              <el-input v-model="borrowForm.borrower"></el-input>
          </el-form-item>
          <el-form-item label="是否可输入">
              <el-switch v-model="isInput"></el-switch>
          </el-form-item>

          <el-form-item>
              <span style="color:red">*借给其他中介可直接录入公司名称</span>
          </el-form-item>

          <el-form-item>
            <div class="btn-group">
              <el-button type="primary"  @click="handleSubmit"  >确认</el-button>
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
  import {queryKeyManagementData,keySeal,keyBorrowed,keyRecived,keyManagementDropdown,keyBack} from "@/request/house/keyManagement";
  import KeyLog  from './component/keyLog'
  import BaseCascader from '@/components/BaseCascader'
  import  *  as  RequestLogUrl from '@/request/log/housePlatformLog'

  export default {
    name:'keyManagement',
    components:{PageList,KeyLog,BaseCascader},
    mixins: [PageList],
    data() {
        return {
          queryForm:{
            houseStatus:'',
            reciveTime:'',
            reciverId:'',
            buildingsName:'',
            keyStatus:''
          },
          borrowForm:{
              id:'',
              borrower:'',
              borrowerId:'',
              status:'',
              houseId:'',
              transactionTypeId:''
          },
          isInput:false,
          houseStatusOpt:[],
          keyStatusOpt:[],
          time:'',
          currentRowData:'',
          hackReset:'',
          loadingView:false,
          queryBtnLoading:false,
          logDlg:false,
          borrowKeyDlg:false,
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
          },
          tableData:[],
          rules:{
            borrowerId: [
              {required: true, message: '该项为必填项', trigger: 'change'}
            ],
            borrower: [
              {required: true, message: '该项为必填项', trigger: 'change'}
            ],
          }
        }
    },
    methods: {
      _loadData(btn){
          this.queryKeyManagementData(btn)
      },
      toDetail (id, name, code) {
        this.$router.push({ path: './houseUsedDetail/' + id + '/' + code, query: { formName: name}})
        window.localStorage.setItem('houseList', JSON.stringify(this.tableData))
      },
      //借钥匙人
        borrowerName(value,name,data){
          if(data.dataType !== 'user'){
              setTimeout(() => {
                this.$refs['borrowForm'].resetFields()
              }, 50)
          }
          this.borrowForm.borrower = name
      },
      //得钥匙人
      handleReciver(value,name,data){
          if(data.dataType !== 'user'){
              setTimeout(() => {
                this.queryForm.reciverId = ''
              }, 50)
          }
      },
      //数据查询
      queryKeyManagementData(btn){
        if(btn){
          this.listQuery.page = 1
          this.listQuery.currentPage = 1
        }
        this.loadingView = true
        this.queryBtnLoading= btn
        let params = Object.assign({},this.queryForm,{
          limit:this.listQuery.limit,
          page:this.listQuery.page
        })
        if(params.reciveTime.length>0){
           params.beginTime = params.reciveTime[0]
           params.endTime = params.reciveTime[1]
        }
        queryKeyManagementData(params).then(res =>{
          this.tableData = res.data
          this.listQuery.total = res.count
          this.loadingView = false
          this.queryBtnLoading= false
        }).catch(error =>{
          console.log(error)
        })
      },
      //时间戳转换为日期
      _timeFormat(row,column,cellValue){
        return this.$utils.timeFormat(cellValue)
      },
      //日志
      handleLog(row){
        this.hackReset = false
         this.$nextTick(()=>{
           this.hackReset = true
           this.currentRowData = row.id
           this.logDlg = true
         })
      },
      //退还
      handleReturn(row){
          this.$confirm('确定要退还钥匙？',{
            confirmBtnText:'确定',
            cancelBtnText:'取消',
            type:'warning'
          }).then(() =>{
            let params = {id:row.id}
            keyRecived(params).then(res =>{
                if(res.success){
                  this.$message({type:'success', message:res.msg})
                  let message = {
                    sourceId:  row.houseId,//资编号：房源编号
                    sourceCode:  row.houseId,//资源编号：房源编号
                    businessTypeId: 1,//业务类型，2：房源
                    sourceTypeId: row.transactionTypeId,//类型：求购
                    operatTypeId: 22,//操作类型 ：钥匙退还,
                    logContent: '钥匙退还 :'+row.houseId//日志内容
                  }
                  RequestLogUrl.houseQueryLog({message:JSON.stringify(message)}).then(res =>{
                    console.log(res)
                  }).catch(error =>{
                    console.log(error)
                  })
                  this._loadData(false)
                }else{
                  this.$message({
                    type:'error',
                    message:res.msg
                  })
                }
            })
          })
      },
      //归还
      handleBack(row){
        this.$confirm('确定要归还钥匙？',{
          confirmBtnText:'确定',
          cancelBtnText:'取消',
          type:'warning'
        }).then(() =>{
          let params = {id:row.id}
          keyBack(params).then(res =>{
            if(res.success){
              this.$message({type:'success', message:res.msg})
              let message = {
                sourceId:  row.shhId,//资编号：房源编号
                sourceCode: row.houseId,//资源编号：房源编号
                businessTypeId: 1,//业务类型，2：房源
                sourceTypeId: row.transactionTypeId,//类型：求购
                operatTypeId: 40,//操作类型 ：钥匙借出,
                logContent: '归还钥匙 :'+row.deptName//日志内容
              }
              RequestLogUrl.houseQueryLog({message:JSON.stringify(message)}).then(res =>{
                console.log(res)
              }).catch(error =>{
                console.log(error)
              })
              this._loadData(false)
            }
          })
        })
      },
      // 借出
      handleBorrow(row){
        console.log(row)
        this.$nextTick(() =>{
          this.borrowKeyDlg = true
          this.borrowForm.id = row.id
          this.borrowForm.status = row.keyStatus
          this.borrowForm.borrowerId = row.borrowerId
          this.borrowForm.borrower = row.borrower
          this.borrowForm.houseId = row.houseId
          this.borrowForm.transactionTypeId = row.transactionTypeId
        })

      },
      handleCancel(){
          this.borrowKeyDlg = false
      },
      timeDefaultShow(){//默认显示上个月
        this.ct_month= new Date();
        this.ct_month.setTime(this.ct_month.getTime() - 3600 * 1000 * 24 * 30);
      },
      handleSubmit(){

        this.$refs['borrowForm'].validate((valid) =>{
          if(valid){
            this.$confirm('确定要借出钥匙？',{
              confirmBtnText:'确定',
              cancelBtnText:'取消',
              type:'warning'
            }).then(()=>{
              let params = {...this.borrowForm}
              delete params.houseId
              delete params.transactionTypeId
              keyBorrowed({jsonData:JSON.stringify(params)}).then(res =>{
                if(res.success){
                  this.$message({type:'success', message:res.msg})
                  let message = {
                    sourceId:  this.borrowForm.houseId,//资编号：房源编号
                    sourceCode:  this.borrowForm.houseId,//资源编号：房源编号
                    businessTypeId: 1,//业务类型，2：房源
                    sourceTypeId: this.borrowForm.transactionTypeId,//类型：求购
                    operatTypeId: 40,//操作类型 ：钥匙借出,
                    logContent: '钥匙借出给 :'+params.borrower//日志内容
                  }
                  RequestLogUrl.houseQueryLog({message:JSON.stringify(message)}).then(res =>{
                    console.log(res)
                  }).catch(error =>{
                    console.log(error)
                  })
                  this._loadData(false)
                  this.borrowKeyDlg = false
                }else{
                  this.$message({
                    type:'error',
                    message:res.msg
                  })
                }
              })
            })
          }
        })
      },
      //封存
      handleSeal(row){
        this.$confirm('确定要封存钥匙？',{
          confirmBtnText:'确定',
          cancelBtnText:'取消',
          type:'warning'
        }).then(() =>{
          let params = {id:row.id,keyStatus:row.keyStatus}
          keySeal(params).then(res =>{
            if(res.success){
              this.$message({type:'success', message:res.msg
              })
              let message = {
                sourceId:  row.houseId,//资编号：房源编号
                sourceCode:  row.houseId,//资源编号：房源编号
                businessTypeId: 1,//业务类型，2：房源
                sourceTypeId: row.transactionTypeId,//类型：求购
                operatTypeId: 25,//操作类型 ：钥匙封存,
                logContent: '封存钥匙 :'+row.houseId//日志内容
              }
              RequestLogUrl.houseQueryLog({message:JSON.stringify(message)}).then(res =>{
                console.log(res)
              }).catch(error =>{
                console.log(error)
              })
              this._loadData(false)
            }else{
              this.$message({
                type:'error',
                message:res.msg
              })
            }
          })
        })
      },
      //钥匙管理下拉数据源
      keyManagementDropdown(){
          keyManagementDropdown().then(res =>{
              this.houseStatusOpt = res.houseStatus
              this.keyStatusOpt = res.keyStatus
          })
      }
    },
    filters:{
        keyStatusFilter(value){
          if(value == '01') return '已收'
          if(value == '02') return '借出'
          if(value == '03') return '封存'
        }
    },
    mounted() {
      this.queryKeyManagementData(false)
      this.keyManagementDropdown()
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
