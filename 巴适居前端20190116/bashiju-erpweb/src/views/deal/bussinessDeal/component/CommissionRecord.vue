<template>
  <div>
    <div>
      <el-button v-hasMultipleBtn="['updateCommission',dealInfo]"
                 type="primary" size="small" @click="handlerAdjustPlan"
                 class="pull-right" v-if="dealInfo.isBreach !== 1">调整计划</el-button>
      <br><br>
      <el-table :data="tableData" border align="center" style="margin-top: 10px;" tooltip-effect="light" size="small" v-if="dealId">
        <el-table-column label="收取状态" prop="status" show-overflow-tooltip>
          <template slot-scope="scope">
            {{formatStatus(scope.row.status)}}
          </template>
        </el-table-column>
        <el-table-column label="票据编号" prop="billNo" show-overflow-tooltip></el-table-column>
        <el-table-column label="费用类型" prop="moneyType" show-overflow-tooltip>
          <template slot-scope="scope">
            {{scope.row.moneyType === '0' ?'中介类费用':'金融类费用'}}
          </template>
        </el-table-column>
        <el-table-column label="费用项目" prop="moneyProjName" show-overflow-tooltip></el-table-column>
        <el-table-column label="费用金额" prop="price" show-overflow-tooltip>
          <template slot-scope="scope">
            ￥{{scope.row.price}}
          </template>
        </el-table-column>
        <el-table-column label="交费人" prop="payerType" show-overflow-tooltip>
          <template slot-scope="scope">
            {{scope.row.payerType === '0' ?'业主':'客户'}}
          </template>
        </el-table-column>
        <el-table-column label="计划日期" prop="estimatePayTime" :formatter="_timeFormat" show-overflow-tooltip></el-table-column>
        <el-table-column label="收取时间" prop="actualPayTime" show-overflow-tooltip></el-table-column>
        <el-table-column label="收取人" prop="operator" show-overflow-tooltip></el-table-column>
        <el-table-column label="结算方式" prop="settlementTypeName" show-overflow-tooltip></el-table-column>
        <!--<el-table-column label="银行卡号" prop="bankCardNo" show-overflow-tooltip></el-table-column>-->
        <el-table-column label="备注" prop="remark" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作" width="300px;">
          <template slot-scope="scope">
            <el-button size="mini" type="button" v-if="scope.row.status==='00' && dealInfo.isBreach !== 1"
               v-hasMultipleBtn="['reciveCommission',scope.row]" style="color: #409eff" @click="handlerRecieve(scope.row)">收取佣金</el-button>
            <el-button size="mini" type="button" v-if="(scope.row.status==='01' || scope.row.status==='03') && dealInfo.isBreach !== 1"
               v-hasMultipleBtn="['updateCommission',scope.row]" @click="handlerEdit(scope.row)" style="color: #409eff">修改</el-button>
            <el-button size="mini" type="primary" v-if="(scope.row.status==='00' || scope.row.status==='01' || scope.row.status==='03') && dealInfo.isBreach !== 1"
                       v-hasMultipleBtn="['delCommission',scope.row]" @click="delCommissions(scope.row)">删除</el-button>
            <el-button size="mini" type="button" v-if="scope.row.status !== '00' && dealInfo.isBreach !== 1"
                       v-hasMultipleBtn="['printCommision',scope.row]" @click="handlerPrint(scope.row)" style="color: #409eff">打印</el-button>
            <el-button size="mini" type="primary" v-if="(scope.row.status==='01' || scope.row.status==='03') && dealInfo.isBreach !== 1"
                       v-hasMultipleBtn="['cancelCommission',scope.row]" @click="cancelCommission(scope.row)">取消</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin-left: 30px;width: 600px;">
        <table class="el-table" line-height="20px" style="margin-top: 20px;width: 600px;">
          <col width="100%;">
          <thead>
            <th style="width: 50%;background-color: #d3dce6;text-align: center" border="1">佣金合计</th>
          </thead>
          <tbody>
          <tr>
            <td align="center"><span>业主:应收({{(dealInfo.ownerPrice+dealInfo.financeOwnerPrice)/100}})-已收({{(dealInfo.ownerActualPrice+dealInfo.financeOwnerActualPrice)/100}})
                      =余额:{{(dealInfo.ownerPrice+dealInfo.financeOwnerPrice-dealInfo.ownerActualPrice-dealInfo.financeOwnerActualPrice)/100}}</span></td>
          </tr>
          <tr>
            <td align="center" border="1"><span>客户:应收({{(dealInfo.custPrice+dealInfo.financeCustPrice)/100}})-已收({{(dealInfo.custActualPrice+dealInfo.financeCustActualPrice)/100}})
                  =余额:{{(dealInfo.custPrice+dealInfo.financeCustPrice-dealInfo.custActualPrice-dealInfo.financeCustActualPrice)/100}}</span></td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
    <template>
      <el-dialog
        :title="title"
        :visible.sync="dialogVisibleEdit"
        width="500px"
        :close-on-click-modal="false"
      >
        <el-form :model="editForm" ref="editForm" label-width="120px" size="small" :rules="rules">

          <el-form-item label="票据编号" prop="billNo">
            <el-input v-model="editForm.billNo" style="width: 215px" disabled></el-input>
          </el-form-item>

          <el-form-item label="费用类型" prop="moneyType">
            <el-select v-model="editForm.moneyType" clearable @change="changeProject">
              <el-option label="中介类费用" value="0"></el-option>
              <el-option label="金融类费用" value="1"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="费用项目" prop="moneyProjId">
            <base-select :data="projectSelData" v-model="editForm.moneyProjId" style="width: 215px" @change="moneyProjIdName"></base-select>
          </el-form-item>

          <el-form-item label="收取时间" prop="actualPayTime">
            <el-date-picker
              v-model="editForm.actualPayTime"
              value-format="yyyy-MM-dd"
              style="width: 215px"
              type="date">
            </el-date-picker>
          </el-form-item>

          <el-form-item label="结算方式" prop="settlementTypeId">
            <base-select :data="settlementTypeArr" v-model="editForm.settlementTypeId"
               style="width: 215px" @change="settlementTypeName"></base-select>
          </el-form-item>


          <el-form-item label="交佣方" prop="payerType">
            <el-select v-model="editForm.payerType" clearable style="width: 215px">
              <el-option label="业主" value="0"></el-option>
              <el-option label="客户" value="1"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="付款人名称" prop="payerName">
            <el-input v-model="editForm.payerName" style="width: 215px">
            </el-input>
          </el-form-item>

          <el-form-item label="费用金额" prop="price">
            <el-input v-model="editForm.price" style="width: 215px" readonly><i slot="suffix">元</i>
            </el-input>
          </el-form-item>

          <el-form-item label="实际收取金额" prop="actualPrice">
            <el-input v-model="editForm.actualPrice" style="width: 215px">
              <i slot="suffix" >元</i>
            </el-input>
          </el-form-item>

          <el-form-item label="备注" prop="remark">
            <el-input type="textarea"
                      autosize :autosize="{ minRows: 2, maxRows: 4}"
                      maxlength="100" v-model="editForm.remark" style="width: 215px"></el-input>
          </el-form-item>

          <div class="btn-group">
            <el-button type="primary" @click="handlerSubmitEdit" size="small" :loading="saveBtn">保存</el-button>
            <el-button type="primary" @click="handlerCancel('0')" size="small">取消</el-button>
          </div>
        </el-form>
      </el-dialog>

      <!--调整计划-->
      <el-dialog
         title="调整计划"
         :visible.sync="dialogVisiblePlan"
         width="860px"
         style="height: 600px"
         :close-on-click-modal="false">
          <el-form :model="planForm" ref="planForm" :inline="true" size="small">
            <el-row :gutter="10">
              <el-col :span="4"><span style="margin-left: 20px;font-weight: 700;">费用类型</span></el-col>
              <el-col :span="4"><span style="margin-left: 20px;font-weight: 700;">费用项目</span></el-col>
              <el-col :span="3"><span style="margin-left: 20px;font-weight: 700;">缴费人员</span></el-col>
              <el-col :span="3"><span style="margin-left: 20px;font-weight: 700;">缴费金额</span></el-col>
              <el-col :span="4"><span style="margin-left: 20px;font-weight: 700;">预计缴费日期</span></el-col>
              <el-col :span="6"><span style="margin-left: 20px;font-weight: 700;">备注</span></el-col>
            </el-row>
            <br>
            <div v-for="(item,index) in planForm.planDataAdjust">
              <el-form-item :prop="'planDataAdjust.'+index + '.moneyType'"
                            :rules="{required: true, message: '费用类型不能为空', trigger: 'change'}">
                <el-select v-model="item.moneyType" clearable @change="changeProject" style="width: 120px;">
                  <el-option label="中介类费用" value="0"></el-option>
                  <el-option label="金融类费用" value="1"></el-option>
                </el-select>
              </el-form-item>

              <el-form-item :prop="'planDataAdjust.'+index + '.moneyProjId'"
                            :rules="{required: true, message: '费用项目不能为空', trigger: 'change'}">
                <base-select
                  :data="item.moneyType === '0'?agencyFee:financeFee"
                  v-model="item.moneyProjId"
                  style="width: 120px"
                  @change="getMoneyName"
                ></base-select>
              </el-form-item>

              <el-form-item :prop="'planDataAdjust.'+index + '.payerType'"
                            :rules="{required: true, message: '缴费人不能为空', trigger: 'change'}">
                <el-select v-model="item.payerType" clearable style="width: 100px">
                  <el-option label="业主" value="0"></el-option>
                  <el-option label="客户" value="1"></el-option>
                </el-select>
              </el-form-item>

              <el-form-item :prop="'planDataAdjust.'+index + '.price'"
                            :rules="{required: true, message: '金额不能为空', trigger: 'blur'}">
                <el-input v-model="item.price" type="number" :min=0 style="width: 100px"><i slot="suffix">元</i></el-input>
              </el-form-item>

              <el-form-item :prop="'planDataAdjust.'+index + '.estimatePayTime'"
                            :rules="{required: true, message: '预计缴费日期不能为空', trigger: 'change'}">
                <el-date-picker
                  v-model="item.estimatePayTime"
                  value-format="yyyy-MM-dd"
                  style="width: 130px"
                  type="date">
                </el-date-picker>
              </el-form-item>

              <el-form-item>
                <el-input type="text"
                          maxlength="100" v-model="item.remark" style="width: 100px"></el-input>
              </el-form-item>

              <el-form-item>
                <el-button type="primary" @click="removeData(index)" size="small">移除</el-button>
              </el-form-item>
            </div>
            <div class="btn-group">
              <el-button type="primary" @click="handleAdd" size="small">新增</el-button>
              <el-button type="info" @click="handlerCancel('0')" size="small">取消</el-button>
              <el-button type="primary" size="small" @click="saveCommissionnfos" :loading="adjustPlanBtn">保存</el-button>
            </div>
          </el-form>
      </el-dialog>

      <el-dialog
        title="票据打印"
        :visible.sync="dialogVisiblePrint"
        width="800px"
        :close-on-click-modal="false"
      >
        <receipt-template @printOver='printEndHandler' :code="printData.code" :payMoney="printData.payMoney" :payMan="printData.payMan"
          :content="printData.content" :payDate="printData.actualPayTime" :recipient="printData.recipient" v-if="hackReset"
          :remark="printData.remark" ></receipt-template>
      </el-dialog>

    </template>
  </div>
</template>

<script>
  import * as RequestURL from '@/request/deal/businessTransactionDeal'
  import {queryContractInfo, querySelectItems, addHouseDeal } from '@/request/deal/houseDeal'

  import PageList from '@/mixins/pageList'
  import BaseSelect from '@/components/BaseSelect'
  import ReceiptTemplate from  '@/components/ReceiptTemplate'
  import DealBaseWriteLogMixins from '@/views/deal/bussinessDeal/component/DealBaseWriteLogMixins'

  export default {
    name: "CommissionRecord",
    mixins: [PageList,DealBaseWriteLogMixins],
    components: {BaseSelect,ReceiptTemplate},
    props:{
      dealId:{
        type: String,
        required: true,
        default() {
          return ''
        }
      },
      dealInfo:{
        type: Object,
        required: true,
        default() {
          return ''
        }
      },
      dealType: {
        type: String,
        required: true
      }
    },
    data(){
      return {
        title: '',
        saveBtn: false,
        adjustPlanBtn: false,
        tableData: [],
        dialogVisibleEdit: false,
        hackReset: true,
        projectSelData: [],
        agencyFee: [],
        financeFee: [],
        editForm:{
          id: '',
          billNo: '',
          moneyType: '',
          moneyProjId: '',
          moneyProjName: '',
          price: '',
          actualPrice: '',
          payerType: '',
          payerName: '',
          actualPayTime: '',
          settlementTypeId: '',
          settlementTypeName: '',
          remark: ''
        },
        planForm:{
          planDataAdjust:[{
            moneyType: '',
            moneyProjId: '',
            payerType: '',
            price: '',
            estimatePayTime: '',
            remark: ''
          }]
        },
        rules: {
          billNo: [{required: true, message: '此项必填项', trigger: 'blur'}],
          moneyType: [{required: true, message: '此项必填项', trigger: 'change'}],
          moneyProjId: [{required: true, message: '此项必填项', trigger: 'change'}],
          price: [{required: true, message: '此项必填项', trigger: 'blur'}],
          actualPrice: [{required: true, message: '此项必填项', trigger: 'blur'}],
          payerType: [{required: true, message: '此项必填项', trigger: 'change'}],
          payerName: [{required: true, message: '此项必填项', trigger: 'blur'}],
          actualPayTime: [{required: true, message: '此项必填项', trigger: 'change'}],
          settlementTypeId: [{required: true, message: '此项必填项', trigger: 'change'}]
        },
        planRules: {
          moneyType: [{required: true, message: '此项必填项', trigger: 'change'}],
          moneyProjId: [{required: true, message: '此项必填项', trigger: 'change'}],
          payerType: [{required: true, message: '此项必填项', trigger: 'change'}],
          price: [{required: true, message: '此项必填项', trigger: 'blur'}],
          estimatePayTime: [{required: true, message: '此项必填项', trigger: 'change'}]
        },
        settlementTypeArr: [],
        removeDatas: [],
        dialogVisiblePlan: false,
        editType: '',
        dialogVisiblePrint:false,
        printData: {},
        originalData: {},
        originalPlanData: []
      }
    },
    methods:{

      getMoneyName(val,name,data){

      },

      // 删除佣金记录
      delCommissions(row){
        this.$confirm('删除后无法恢复，确认要删除吗？','提示',{
          confirmButtonText: '',
          cancelButtonText: '',
          type: 'warning'
        }).then(()=>{
          RequestURL.delCommissions({dealId: this.dealId,commissionId: row.id,commissionType: row.moneyType}).then(res=>{
            if(res.success){
              this.$message({
                type: 'success',
                message: res.msg
              })
            }else{
              this.$message({
                type: 'error',
                message: res.msg
              })
            }
            this.getTableData()

            let operatorTypeId = ''
            let title = ''
            if(row.moneyType === '0'){
              operatorTypeId = '419'
              title = '删除中介类佣金记录'
            }else{
              title = '删除金融类佣金记录'
              operatorTypeId = '436'
            }

            if(this.dealType === '00'){
              this.dealDelLog(this.dealId,this.dealId,'6',operatorTypeId,title)
            }else if(this.dealType === '01'){
              this.dealDelLog(this.dealId,this.dealId,'7',operatorTypeId,title)
            }else if(this.dealType === '02') {
              this.dealDelLog(this.dealId,this.dealId,'8',operatorTypeId,title)
            }
          }).catch(err=>{
            console.log(err)
          })
        })
      },

      handleAdd () {
        const index = this.planForm.planDataAdjust.length + 1
        this.planForm.planDataAdjust.push({
          index: index,
          detail: {
            companyId: this.dealInfo.companyId,
            companyName: this.dealInfo.companyName,
            moneyType: '',
            moneyProjId: '',
            payerType: '',
            price: '',
            estimatePayTime: '',
            remark: '',
            isValid: 1
          }
        })
      },

      /**
       * 打印收据
       * @param row
       */
      handlerPrint(row){
        this.$nextTick(()=>{
          this.printData = {}
          this.printData.moneyType = row.moneyType
          this.printData.code = row.billNo
          this.printData.payMoney = row.price
          this.printData.content = row.moneyProjName
          this.printData.payMan = row.payerName
          this.printData.payDate = row.actualPayTime
          this.printData.remark = row.remark
          this.printData.recipient = row.operator
          this.dialogVisiblePrint = true
        })
      },

      // 打印完成
      printEndHandler(){
        this.dialogVisiblePrint = false
        let operatorTypeId = ''
        let title = ''
        if(this.printData.moneyType === '0'){
          operatorTypeId = '418'
          title = '打印中介类收据'
        }else{
          title = '打印金融类收据'
          operatorTypeId = '435'
        }
        if(this.dealType === '00'){
          this.dealAddLog(this.dealId,this.dealId,'6',operatorTypeId,title)
        }else if(this.dealType === '01'){
          this.dealAddLog(this.dealId,this.dealId,'7',operatorTypeId,title)
        }else if(this.dealType === '02') {
          this.dealAddLog(this.dealId,this.dealId,'8',operatorTypeId,title)
        }
      },

      // 移除数据
      removeData(index){
        let temp = this.planForm.planDataAdjust[index]
        if(temp.id){
          temp.isValid = 0
          this.removeDatas.unshift(temp)
        }
        this.planForm.planDataAdjust.splice(index,1)
      },

      // 重置数据
      resetData(index){
        this.planForm.planDataAdjust[index].moneyType = ''
        this.planForm.planDataAdjust[index].moneyProjId = ''
        this.planForm.planDataAdjust[index].payerType = ''
        this.planForm.planDataAdjust[index].price = ''
        this.planForm.planDataAdjust[index].estimatePayTime = ''
        this.planForm.planDataAdjust[index].remark = ''
        this.planForm.planDataAdjust[index].isValid = ''
      },

      formatStatus(value){
        if (value === '00') {
          return '计划'
        } else if (value === '01') {
          return '待确认'
        } else if (value === '02') {
          return '已确认'
        }else if (value === '03') {
          return '已驳回'
        }
      },

      // 取到费用类型的名称
      moneyProjIdName(val,name){
        this.editForm.moneyProjName = name
      },

      // 获取结算方式名称
      settlementTypeName(val,name){
        this.editForm.settlementTypeName = name
      },

      // 调整计划
      handlerAdjustPlan(){
        this.queryCommissionInfos()
        this.$nextTick(()=>{
          this._resetForm('planForm')
          this.hackReset = true
          this.dialogVisiblePlan = true
        })
      },

      // 编辑佣金信息
      handlerEdit(row){
        this.title = '修改佣金'
        this.editType = '0'
        this.hackReset = false
        this.$nextTick(()=>{
          this._resetForm('editForm')
          this.projectSelData = []
          if(row.moneyType === '0'){
            this.projectSelData = this.agencyFee
          }else{
            this.projectSelData = this.financeFee
          }
          row.moneyProjId = String(row.moneyProjId)
          row.moneyType = String(row.moneyType)
          row.payerType = String(row.payerType)
          if(row.settlementTypeId) {
            row.settlementTypeId = String(row.settlementTypeId)
          }
          this.setForm(row)
          this.originalData = Object.assign({},{...this.editForm})
          this.hackReset = true
          this.dialogVisibleEdit = true
        })
      },

      // 收取佣金信息
      handlerRecieve(row){
        this.title = '收取佣金'
        this.editType = '1'
        this.hackReset = false
        this.$nextTick(()=>{
          this._resetForm('editForm')
          this.projectSelData = []
          if(row.moneyType === '0'){
            this.projectSelData = this.agencyFee
          }else{
            this.projectSelData = this.financeFee
          }
          row.moneyProjId = String(row.moneyProjId)
          row.moneyType = String(row.moneyType)
          row.payerType = String(row.payerType)
          if(row.settlementTypeId) {
            row.settlementTypeId = String(row.settlementTypeId)
          }
          this.getBillNoForCommission()
          this.setForm(row)
          this.hackReset = true
          this.dialogVisibleEdit = true
        })
      },

      // 取消操作
      handlerCancel(action){
        if(action === '1'){
          this.dialogVisibleEdit = false
          this.dialogVisiblePlan = false
          this.getTableData()
        }else{
          this.dialogVisibleEdit = false
          this.dialogVisiblePlan = false
        }
      },

      setForm(data){
        if(data){
          for(let i in this.editForm){
            this.editForm[i] = data[i]
          }
        }
      },

      // 调整计划时查询数据
      queryCommissionInfos(){
        RequestURL.queryCommissionInfos({dealId: this.dealId}).then((res)=>{
          this.planForm.planDataAdjust = res
          for(let i in res){
            this.planForm.planDataAdjust[i].moneyProjId = String(this.planForm.planDataAdjust[i].moneyProjId)
            this.planForm.planDataAdjust[i].moneyType = String(this.planForm.planDataAdjust[i].moneyType)
            this.planForm.planDataAdjust[i].payerType = String(this.planForm.planDataAdjust[i].payerType)
            this.planForm.planDataAdjust[i].price = this.planForm.planDataAdjust[i].price / 100
            this.planForm.planDataAdjust[i].actualPrice = this.planForm.planDataAdjust[i].payerType / 100
            this.planForm.planDataAdjust[i].estimatePayTime
              = this.$utils.timeFormat(this.planForm.planDataAdjust[i].estimatePayTime, '{y}-{m}-{d}')
            this.planForm.planDataAdjust[i].companyId = this.dealInfo.companyId
            this.planForm.planDataAdjust[i].companyName = this.dealInfo.companyName
          }
          console.log(`res=${res}`)
          this.originalPlanData = this.$jsonUtils.copyArr(this.planForm.planDataAdjust)
        })
      },

      // 查询佣金数据信息
      getTableData(){
        RequestURL.queryCommissionInfosByDealId({dealId: this.dealId}).then(res=>{
          this.tableData = res
          for(let i in this.tableData){
            this.tableData[i].price = this.tableData[i].price / 100
            this.tableData[i].actualPrice = this.tableData[i].actualPrice / 100
            this.tableData[i].moneyType = String(this.tableData[i].moneyType)
            this.tableData[i].payerType = String(this.tableData[i].payerType)
            if(this.tableData[i].actualPayTime){
              this.tableData[i].actualPayTime
                = this.$utils.timeFormat(this.tableData[i].actualPayTime, '{y}-{m}-{d}')
            }
          }
        }).catch(err=>{
          console.log(err)
        })
      },

      // 提交数据
      handlerSubmitEdit(){
        let checkData = {...this.editForm}
        checkData.actualPrice = String(checkData.actualPrice)
        if(checkData.actualPrice === '0'){
          this.$message({
            type: 'warning',
            message: '实际收取金额必须大于0'
          })
          return false
        }
        if(this.editType === '0'){//修改
          this.modifyCommission()
        }else if(this.editType  === '1'){//收取佣金
          this.reciveCommission()
        }
      },

      // 收取佣金
      reciveCommission(){
        this.$refs['editForm'].validate((valid)=>{
          if(valid){
            this.$confirm('确定收取佣金吗?','提示',{
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(()=>{
              let params = {...this.editForm}
              RequestURL.reciverDealCommission({dealId: this.dealId,jsonData: JSON.stringify(params)}).then(res=>{
                this.saveBtn = true
                if(res.success){
                  this.$message({
                    type: 'success',
                    message: res.msg
                  })
                }else{
                  this.$message({
                    type: 'error',
                    message: res.msg
                  })
                }
                this.saveBtn = false
                this.handlerCancel('1')

                let title = ''
                let operatorTypeId = ''
                if(params.moneyType === '0'){
                  operatorTypeId = '416'
                  title = '收取中介类佣金'
                }else{
                  title = '收取金融类佣金'
                  operatorTypeId = '433'
                }

                if(this.dealType === '00'){
                  this.dealAddLog(this.dealId,this.dealId,'6',operatorTypeId,title)
                }else if(this.dealType === '01'){
                  this.dealAddLog(this.dealId,this.dealId,'7',operatorTypeId,title)
                }else if(this.dealType === '02') {
                  this.dealAddLog(this.dealId,this.dealId,'8',operatorTypeId,title)
                }

              }).catch(err=>{
                this.$message({
                  type: 'error',
                  message: err.data.msg
                })
                this.saveBtn = false
              })
            })
          }
        })
      },

      // 调整计划
      saveCommissionnfos(){
        if(!this.planForm.planDataAdjust){
          this.$message({
            type: 'warning',
            message: '保存数据为空'
          })
          return false
        }

        let newPlanAdjustData = []
        if(this.removeDatas.length > 0){
          newPlanAdjustData = this.$jsonUtils.mergeArray(this.planForm.planDataAdjust,this.removeDatas)
        }else{
          newPlanAdjustData = this.planForm.planDataAdjust
        }
        this.$refs['planForm'].validate((valid)=>{
          if(valid){
            this.$confirm('确定提交数据吗?','提示',{
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(()=>{
              let saveData = []
              for(let index in newPlanAdjustData){
                let temp = newPlanAdjustData[index]
                let tempObj = {}
                tempObj.moneyType = temp.moneyType
                tempObj.moneyProjId = temp.moneyProjId
                tempObj.payerType = temp.payerType
                tempObj.price = temp.price
                tempObj.estimatePayTime = temp.estimatePayTime
                tempObj.remark = temp.remark
                tempObj.id = temp.id
                tempObj.companyName = temp.companyName
                tempObj.companyId = temp.companyId
                tempObj.isValid = temp.isValid

                let moneyProjId = temp.moneyProjId
                if(temp.moneyType === '0'){
                  for(let i in this.agencyFee){
                    if(this.agencyFee[i].id === moneyProjId){
                      tempObj.moneyProjName = this.agencyFee[i].name
                      break
                    }
                  }
                }else{
                  for(let i in this.financeFee){
                    if(this.financeFee[i].id === moneyProjId){
                      tempObj.moneyProjName = this.financeFee[i].name
                      break
                    }
                  }
                }
                saveData.unshift(tempObj)
              }
              this.adjustPlanBtn = true
              RequestURL.saveCommissionnfos({dealId: this.dealId,jsonData: JSON.stringify(saveData)}).then(res=>{
                if(res.success){
                  this.$message({
                    type: 'success',
                    message: res.msg
                  })
                }else{
                  this.$message({
                    type: 'error',
                    message: res.msg
                  })
                }
                this.adjustPlanBtn = false
                this.handlerCancel('1')
                this.writeActionLog(saveData)
              }).catch(err=>{
                this.adjustPlanBtn = false
                this.$message({
                  type: 'error',
                  message: err.data.msg
                })
              })
            })
          }
        })
      },

      /**
       * 调整计划写日志
       */
      writeActionLog(saveData){
        let logContent = ''
        for(let i in saveData){
          let newTemp = saveData[i]
          if(newTemp.id && newTemp.isValid === 1){ //正常修改
            this.originalPlanData.forEach(item=>{
              if(newTemp.id === item.id){
                Object.keys(newTemp).forEach(function(key,i,v){
                  if(key === 'payerType' && newTemp[key] !== item[key]) {
                    if(newTemp[key] === '0' && item[key] === '1'){
                      logContent = logContent + `缴费人员：[客户]修改为[业主];`
                    }else{
                      logContent = logContent + `缴费人员：[业主]修改为[客户];`
                    }
                  }
                  if (key === 'moneyType' && newTemp[key] !== item[key]) {
                    if(newTemp[key] === '0' && item[key] === '1'){
                      logContent = logContent + `缴费人员：[金融类费用]修改为[中介类费用];`
                    }else{
                      logContent = logContent + `缴费人员：[中介类费用]修改为[金融类费用];`
                    }
                  }

                  if (newTemp[key] !== item[key] && key ==='price') {
                    logContent = logContent + `缴费金额：${item[key]}修改为${newTemp[key]};`
                  }

                  if (newTemp[key] !== item[key] && key ==='estimatePayTime') {
                    logContent = logContent + `预计缴费日期：${item[key]}修改为${newTemp[key]};`
                  }

                  if (newTemp[key] !== item[key] && key ==='remark') {
                    logContent = logContent + `摘要：${item[key]}修改为${newTemp[key]};`
                  }
                })
              }
            })
          }
          if(newTemp.id && newTemp.isValid === 0){//删除计划
            logContent = logContent + `删除计划，ID为：${newTemp.id};`
          }

          if(!newTemp.id && !newTemp.isValid){//新增计划
            let moneyTypeName = ''
            if(newTemp.moneyType === '0'){
              moneyTypeName = '中介类费用'
            }else{
              moneyTypeName = '金融类费用'
            }
            logContent = logContent + `新增计划：费用类型=${moneyTypeName},费用项目=${newTemp.moneyProjName},缴费金额=${newTemp.price},预计缴费时间=${newTemp.estimatePayTime};`
          }
        }

        if(this.dealType === '00'){
          this.dealQueryLog(this.dealId,this.dealId,'6','420',logContent)
        }else if(this.dealType === '01'){
          this.dealQueryLog(this.dealId,this.dealId,'7','420',logContent)
        }else if(this.dealType === '02') {
          this.dealQueryLog(this.dealId,this.dealId,'8','420',logContent)
        }
      },

      // 修改佣金
      modifyCommission() {
        this.$refs['editForm'].validate((valid)=>{
          if(valid){
            this.$confirm('确定提交数据吗?','提示',{
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(()=>{
              let params = {...this.editForm}
              RequestURL.updateDealCommission({jsonData: JSON.stringify(params)}).then(res=>{
                this.saveBtn = true
                if(res.success){
                  this.$message({
                    type: 'success',
                    message: res.msg
                  })
                }else{
                  this.$message({
                    type: 'error',
                    message: res.msg
                  })
                }
                this.saveBtn = false
                this.handlerCancel('1')

                let operatorTypeId = ''
                if(this.params.moneyType === '0'){
                  operatorTypeId = '417'
                }else{
                  operatorTypeId = '434'
                }
                if(this.dealType === '00'){
                  this.dealUpdateLog(this.dealId,this.dealId,'6',operatorTypeId
                    ,this.$utils.getFormFields(this.$refs['editForm']),this.originalData,{...this.editForm})
                }else if(this.dealType === '01'){
                  this.dealUpdateLog(this.dealId,this.dealId,'7',operatorTypeId
                    ,this.$utils.getFormFields(this.$refs['editForm']),this.originalData,{...this.editForm})
                }else if(this.dealType === '02') {
                  this.dealUpdateLog(this.dealId,this.dealId,'8',operatorTypeId
                    ,this.$utils.getFormFields(this.$refs['editForm']),this.originalData,{...this.editForm})
                }
              }).catch(err=>{
                this.saveBtn = false
                this.$message({
                  type: 'error',
                  message: err.data.msg
                })
              })
            })
          }
        })
      },

      // 取消收取
      cancelCommission(row) {
        this.$confirm('确定取消佣金吗?','提示',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(()=>{
          RequestURL.cancelCommission({dealId: this.dealId,id: row.id}).then(res=>{
            if(res.success){
              this.$message({
                type: 'success',
                message: res.msg
              })
            }else{
              this.$message({
                type: 'error',
                message: res.msg
              })
            }
            this.handlerCancel('1')

            let operatorTypeId = ''
            let title = ''
            if(row.moneyType === '0'){
              operatorTypeId = '421'
              title = '取消收取中介类佣金'
            }else{
              title = '取消收取中介类佣金'
              operatorTypeId = '438'
            }

            if(this.dealType === '00'){
              this.dealQueryLog(this.dealId,this.dealId,'6',operatorTypeId,title)
            }else if(this.dealType === '01'){
              this.dealQueryLog(this.dealId,this.dealId,'7',operatorTypeId,title)
            }else if(this.dealType === '02') {
              this.dealQueryLog(this.dealId,this.dealId,'8',operatorTypeId,title)
            }
          }).catch(err=>{
            this.$message({
              type: 'error',
              message: err.data.msg
            })
          })
        })
      },

      // 费用项目切换
      changeProject(moneyType){
        this.projectSelData = []
        this.editForm.moneyProjId = ''
        if (moneyType === '0') {
          this.projectSelData = this.agencyFee
        }
        if (moneyType === '1') {
          this.projectSelData = this.financeFee
        }
      },

      //费用项目下拉初始化
      querySelectItems(){
        querySelectItems({'param':'agencyFee'}).then((res)=>{
          if (res.success){
            for(let i=0;i<res.data.length;i++){
              let data = {'name':res.data[i].fieldValue,'id': String(res.data[i].fieldCode)}
              this.agencyFee.push(data)
            }
          }
        })
      },

      //费用初始化
      queryFinSelectItems(){
        querySelectItems({'param':'financeFee'}).then((res)=>{
          if (res.success){
            for(let i=0;i<res.data.length;i++){
              let data = {'name':res.data[i].fieldValue,'id': String(res.data[i].fieldCode)}
              this.financeFee.push(data)
            }
          }
        })
      },

      // 查询【结算方式】下拉数据
      querySettlementType(){
        querySelectItems({'param':'settlementType'}).then((res)=>{
          if (res.success){
            for(let i=0;i < res.data.length;i++){
              let data = {'name':res.data[i].fieldValue,'id': String(res.data[i].id)}
              this.settlementTypeArr.push(data)
            }
          }
        })
      },

      // 获取票据编号
      getBillNoForCommission(){
        RequestURL.getBillNoForCommission().then(res=>{
          this.editForm.billNo = res
        }).catch(err=>{
          this.$message({
            type: 'error',
            message: '获取票据编号失败!'
          })
        })
      }
    },
    mounted(){
      this.querySelectItems()
      this.queryFinSelectItems()
      this.querySettlementType()
    }
  }
</script>

<style scoped>

</style>
