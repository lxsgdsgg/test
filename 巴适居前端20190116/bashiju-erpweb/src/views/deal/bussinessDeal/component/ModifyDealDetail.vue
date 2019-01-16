<template>
  <div style="width: 680px;" class="houseDeal">
    <el-form :model="dealForm" :rules="rules" ref="dealForm" label-width="100px" size="mini" :inline="true">
      <el-form-item prop="dealTime" label="成交日期">
        <el-date-picker
          value-format="yyyy-MM-dd"
          type="date"
          placeholder="选择日期"
          v-model="dealForm.dealTime"
          style="width:200px">
        </el-date-picker>
      </el-form-item>
      <el-form-item prop="traderId" label="成交人">
        <base-cascader
          :changeOnSelect="false"
          v-model="dealForm.traderId"
          :url="getReferenceUserSelectUrl"
          :props="cascaderProps"
          :dataProps="cascaderDataProps"  style="width:200px" disabled>
        </base-cascader>
      </el-form-item>

      <el-form-item prop="beginLeaseTime" label="租期开始" v-if="dealType==='01'">
        <el-date-picker value-format="yyyy-MM-dd" type="date" format="yyyy-MM-dd" placeholder="选择日期" v-model="dealForm.beginLeaseTime" style="width:200px" disabled></el-date-picker>
      </el-form-item>
      <el-form-item prop="endLeaseTime" label="租期截止" v-if="dealType==='01'">
        <el-date-picker disabled value-format="yyyy-MM-dd" type="date" format="yyyy-MM-dd" placeholder="选择日期" v-model="dealForm.endLeaseTime" style="width:200px"></el-date-picker>
      </el-form-item>

      <el-form-item label="合同号">
        <el-input v-model="dealForm.agreementId" style="width: 200px;" readonly></el-input>
      </el-form-item>
      <el-form-item prop="price"  label="成交价格"  v-if="dealType != '01'">
        <el-input v-model="dealForm.price" style="width: 120px;" readonly>
          <i slot="suffix" >万</i>
        </el-input>
      </el-form-item>
      <el-checkbox v-model="dealForm.isLoan" style="padding-top: 5px;" v-if="dealType != '01'" disabled>有贷款</el-checkbox>

      <el-form-item prop="price"  label="租赁价格" v-if="dealType==='01'">
        <el-input v-model="dealForm.price" style="width: 200px;" readonly>
          <i slot="suffix">元/月</i>
        </el-input>
      </el-form-item>

      <el-form-item prop="commissionPrice"  label="应收佣金">
        <el-input v-model="dealForm.commissionPrice" style="width: 200px;" readonly>
          <i slot="suffix" >元</i>
        </el-input>
      </el-form-item>
      <el-form-item prop="landCertifcate" label="土地证号" v-if="dealType != '02'">
        <el-input v-model="dealForm.landCertifcate" style="width: 200px;" readonly>
        </el-input>
      </el-form-item>

      <el-form-item prop="propCertificate" label="产权证号" v-if="dealType != '02'">
        <el-input v-model="dealForm.propCertificate" style="width: 200px;" readonly>
        </el-input>
      </el-form-item>

      <el-form-item  label="权证人" v-if="dealType === '00'" prop="referenceId">
        <base-cascader
          :changeOnSelect="false"
          v-model="dealForm.referenceId"
          :url="getReferenceUserSelectUrl"
          :props="cascaderProps"
          :dataProps="cascaderDataProps"  style="width:200px" @change="onReferenceChange" disabled>
        </base-cascader>
      </el-form-item>

      <el-form-item prop="supplementInfo" label="备注">
        <el-input type="textarea" autosize :autosize="{ minRows: 2, maxRows: 4}" v-model="dealForm.supplementInfo" style="width: 515px">
        </el-input>
      </el-form-item>

      <hr style="width: 680px" v-if="dealType === '02'">
      <hr style="width: 600px" v-else>

      <el-form-item label="乙方编号" v-if="dealType==='02'" prop="demandId">
        <el-input v-model="dealForm.demandId" style="width: 200px;" readonly></el-input>
      </el-form-item>


      <el-form-item label="甲方姓名" v-if="dealType !='02'" prop="partyA">
        <el-input v-model="dealForm.partyA" style="width: 200px;" readonly></el-input>
      </el-form-item>

      <el-form-item label="乙方姓名" prop="partyB" v-if="dealType !== '02'">
        <el-input v-model="dealForm.partyB" style="width: 200px;" readonly></el-input>
      </el-form-item>
      <el-form-item label="乙方姓名" prop="custName" v-else>
        <el-input v-model="dealForm.custName" style="width: 200px;" readonly></el-input>
      </el-form-item>

      <el-form-item label="证件号码" v-if="dealType !='02'" prop="cardNoA">
        <el-input v-model="dealForm.cardNoA" style="width: 200px;" readonly></el-input>
      </el-form-item>

      <el-form-item label="证件号码" prop="IDCard" v-else>
        <el-input v-model="dealForm.IDCard" style="width: 200px;" readonly></el-input>
      </el-form-item>

      <el-form-item label="联系地址" v-if="dealType !='02'" prop="addressA">
        <el-input v-model="dealForm.addressA" style="width: 200px;" readonly></el-input>
      </el-form-item>

      <el-form-item label="联系地址" prop="contactAddress" v-else>
        <el-input v-model="dealForm.contactAddress" style="width: 200px;" readonly></el-input>
      </el-form-item>
    </el-form>

    <hr style="width: 680px" v-if="dealType === '02'">
    <hr style="width: 600px" v-else>

    <!--成交类型为一手成交时显示===老板说了暂时先屏蔽掉-->
    <el-form size="mini" class="costForm" v-if="dealType === '002'" :inline="true">
      <el-row>
        <el-col :span="4"><span style="margin-left: 20px;font-weight: 700;">费用类型</span></el-col>
        <el-col :span="4"><span style="margin-left: -10px;font-weight: 700;">费用项目</span></el-col>
        <el-col :span="3"><span style="margin-left: -20px;font-weight: 700;">交费人</span></el-col>
        <el-col :span="4"><span style="margin-left: -20px;font-weight: 700;">金额</span></el-col>
        <el-col :span="4"><span style="margin-left: -40px;font-weight: 700;">预计交费日期</span></el-col>
        <el-col :span="4"><span style="margin-left: -20px;font-weight: 700;">备注</span></el-col>
      </el-row>

      <br>
      <div v-for="item in costList">
          <el-form-item prop="moneyType">
            <el-select v-model="item.moneyType" clearable style="width: 100px;" @change="initMoneyProject(item)" disabled>
              <el-option label="中介类费用" value="0"></el-option>
              <el-option label="金融类费用" value="1"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item prop="moneyProjId">
            <el-select v-model="item.moneyProjId" clearable style="width: 100px;" @change="selectMoneyProjHandel(item)" disabled>
              <el-option
                v-for="option in item.moneyProjData"
                :key="option.value"
                :label="option.label"
                :value="option.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item prop="payerType">
            <el-select v-model="item.payerType" clearable style="width: 96px;" disabled>
              <el-option label="业主" value="0"></el-option>
              <el-option label="客户" value="1"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item prop="price">
            <el-input v-model="item.price" style="width: 100px;" disabled>
              <i slot="suffix" >元</i>
            </el-input>
          </el-form-item>

          <el-form-item prop="estimatePayTime">
            <el-date-picker v-model="item.estimatePayTime"  value-format="yyyy-MM-dd" type="date" format="yyyy-MM-dd" style="width: 100px;" disabled></el-date-picker>
          </el-form-item>

          <el-form-item prop="remark">
            <el-input v-model="item.remark" style="width: 100px;" disabled></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="text" @click="clearItem(item)">清除</el-button>
          </el-form-item>
      </div>
    </el-form>

    <div class="btn-group">
      <el-button type="primary" @click="saveDeal" size="small" :loading="saveBtn">确定</el-button>
      <el-button @click="cancelDeal" size="small">取消</el-button>
    </div>
  </div>
</template>

<script>
  import {
    querySelectItems,
    addHouseDeal
  }  from '@/request/deal/houseDeal'

  import BaseCascader from '@/components/BaseCascader'
  import * as RequestURL from '@/request/deal/businessTransactionDeal'
  import DealBaseWriteLogMixins from './DealBaseWriteLogMixins'

  export default{
    mixins: [DealBaseWriteLogMixins],
    data () {
      return {
        saveBtn: false,
        dealForm:{
          dealTime:'',
          traderId:'',
          agreementId:'',
          price:'',
          isLoan: '',
          commissionPrice:'',
          landCertifcate: '',
          propCertificate: '',
          actualCommission:'',
          referenceId:'',
          reference:'',
          supplementInfo:'',
          houseId:'',
          demandId:'',
          addressA:'',
          addressB:'',
          partyA:'',
          partyB:'',
          cardNoA:'',
          cardNoB:'',
          beginLeaseTime:'',
          endLeaseTime:'',
          custName: '',
          contactAddress: '',
          IDCard: ''
        },
        rules:{
          dealTime:[
            { required: true, message: '请选择成交日期', trigger: 'blur' }
          ],
          traderId:[
            { required: true, message: '请选择成交人', trigger: 'blur' }
          ],
          price:[
            { required: true, message: '成交价格不能为空', trigger: 'blur' }
          ],
          commissionPrice:[
            { required: true, message: '应收佣金不能为空', trigger: 'blur' }
          ],
          actualCommission:[
            { required: true, message: '实应收佣金不能为空', trigger: 'blur' }
          ],
          beginLeaseTime:[
            { required: true, message: '租期开始日期不能为空', trigger: 'blur' }
          ],
          endLeaseTime:[
            { required: true, message: '租期截止日期不能为空', trigger: 'blur' }
          ],
          partyA:[
            { required: true, message: '此项为必填项', trigger: 'blur' }
          ],
          partyB:[
            { required: true, message: '此项为必填项', trigger: 'blur' }
          ]
        },
        getReferenceUserSelectUrl: 'manage/usermanage/queryReferenInfoToCreateTree', // 选员工
        cascaderDataProps: {id: 'code', parent: 'parentCode'},
        cascaderProps: {  value: 'code', label: 'name'},
        agencyData:[{'label':'中介费','value':'1'},{'label':'违约金','value':'2'}],
        financeData:[{'label':'过户服务费','value':'1'},{'label':'房屋评估费','value':'2'},{'label':'贷款服务费','value':'3'},{'label':'代办过户费','value':'4'}],
        costList:[
          {'moneyType':'','moneyProjId':'','moneyProjName':'','moneyProjData':[],'payerType':'','price':'','estimatePayTime':'','remark':''},
          {'moneyType':'','moneyProjId':'','moneyProjName':'','moneyProjData':[],'payerType':'','price':'','estimatePayTime':'','remark':''},
          {'moneyType':'','moneyProjId':'','moneyProjName':'','moneyProjData':[],'payerType':'','price':'','estimatePayTime':'','remark':''},
          {'moneyType':'','moneyProjId':'','moneyProjName':'','moneyProjData':[],'payerType':'','price':'','estimatePayTime':'','remark':''},
          {'moneyType':'','moneyProjId':'','moneyProjName':'','moneyProjData':[],'payerType':'','price':'','estimatePayTime':'','remark':''},
          {'moneyType':'','moneyProjId':'','moneyProjName':'','moneyProjData':[],'payerType':'','price':'','estimatePayTime':'','remark':''},
          {'moneyType':'','moneyProjId':'','moneyProjName':'','moneyProjData':[],'payerType':'','price':'','estimatePayTime':'','remark':''},
          {'moneyType':'','moneyProjId':'','moneyProjName':'','moneyProjData':[],'payerType':'','price':'','estimatePayTime':'','remark':''}
        ],
        contractType : '01', //01：买卖 02:租赁 一手的未知
        originalData: {},
        dealInfo: null
      }
    },

    props:{
      dealType:{
        type: String,
        required: true,
        default(){
          return '00'
        }
      },
      dealId: {
        type: String,
        required: true
      },
      agreementId: {
        type: String,
        required: true
      }
    },
    components: {
      BaseCascader
    },

    // 钩子函数
    mounted () {

      this._getTableData()
      //中介费下拉初始化
      querySelectItems({'param':'agencyFee'}).then((res)=>{
        if (res.success){
          this.agencyData = []
          for(let i=0;i<res.data.length;i++){
            let data = {'label':res.data[i].fieldValue,'value':res.data[i].fieldCode}
            this.agencyData.push(data)
          }
        }
      })
      //金融类费用初始化
      querySelectItems({'param':'financeFee'}).then((res)=>{
        if (res.success){
          this.financeData = []
          for(let i=0;i<res.data.length;i++){
            let data = {'label':res.data[i].fieldValue,'value':res.data[i].fieldCode}
            this.financeData.push(data)
          }
        }
      })

      if (this.dealType === '01'){
        this.contractType = '02'
      }


    },

    methods:{
      /**
       * 根据房源id和合同类型查询合同信息
       */
      queryContractInfo(){
        RequestURL.queryContractInfoByAgreenmentId({code:this.agreementId}).then((res)=>{
          if (res.success && res.data){
            this.dealForm.agreementId = res.data.code
            if (this.contractType === '01'){
              this.dealForm.price = res.data.price/10000
              // this.dealForm.commissionPrice = res.data.price * 0.02
            }else {
              this.dealForm.price = res.data.price
              this.dealForm.commissionPrice = res.data.price
            }
            this.dealForm.actualCommission = res.data.commissionA + res.data.commissionB
            this.dealForm.demandId = res.data.custId
            this.dealForm.partyA = res.data.partyA
            this.dealForm.partyB = res.data.partyB
            this.dealForm.cardNoA = res.data.cardNoA
            this.dealForm.cardNoB = res.data.cardNoB

            if (res.data.beginTime){
              this.dealForm.beginLeaseTime = this.$utils.timeFormat(res.data.beginTime)
            }
            if (res.data.endTime){
              this.dealForm.endLeaseTime = this.$utils.timeFormat(res.data.endTime)
            }
            this.dealForm.propCertificate = res.data.propertyRrightNO
          }
        })
      },

      _getTableData(){
        if(this.dealId === undefined){
          this.dealId = ''
        }else{
          this.dealInfo = null
          let params = {id: this.dealId,dealType: this.dealType}
          RequestURL.queryDealInfoByIdAndType(params).then(res=>{
            this.dealInfo = res
            this.setForm()
            this.queryContractInfo()
          })
        }
      },

      // 表单赋值
      setForm(){
        this.dealInfo.dealTime = this.$utils.timeFormat(this.dealInfo.dealTime,'{y}-{m}-{d}')
        this.dealInfo.beginLeaseTime = this.$utils.timeFormat(this.dealInfo.beginLeaseTime,'{y}-{m}-{d}')
        this.dealInfo.endLeaseTime = this.$utils.timeFormat(this.dealInfo.endLeaseTime,'{y}-{m}-{d}')

        if(this.dealType ==='02'){
          this.dealInfo.price = this.dealInfo.price / 1000000
        }else{
          this.dealInfo.price = this.dealInfo.price / 100
        }
        this.dealInfo.commissionPrice = this.dealInfo.commissionPrice / 100
        this.dealInfo.referenceId = String(this.dealInfo.referenceId)
        this.dealInfo.traderId = String(this.dealInfo.traderId)
        for(let i in this.dealForm){
          this.dealForm[i] = this.dealInfo[i]
        }
        this.dealForm.isLoan = this.dealInfo.isLoan === 1
        this.originalData = Object.assign({},{
          dealTime: this.dealInfo.dealTime,supplementInfo: this.dealInfo.supplementInfo
        })
      },

      initMoneyProject(item){
        if (item.moneyType === '0') {
          item.moneyProjData = this.agencyData
        }
        if (item.moneyType === '1') {
          item.moneyProjData = this.financeData
        }
      },

      clearItem(item){
        item.moneyType=''
        item.moneyProjId=''
        item.moneyProjName=''
        item.moneyProjData=[]
        item.payerType=''
        item.price=''
        item.estimatePayTime=''
        item.remark=''
      },

      // 用户点击取消
      cancelDeal(){
        this.$emit('cancelDeal')
      },

      // 费用类型选择事件监听
      selectMoneyProjHandel(item){
        for (let  i =0 ; i<item.moneyProjData.length;i++){
          if (item.moneyProjId===item.moneyProjData[i].value){
            item.moneyProjName = item.moneyProjData[i].label
            return
          }
        }
      },

      //
      onReferenceChange(val, label, data){
        this.dealForm.reference = label
      },

      // 保存成交详细
      saveDeal(){
        this.$refs['dealForm'].validate((valid) => {
          if (valid) {
            this.$confirm('确定提交数据吗？','提示',{
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(()=>{
              let params = {}
              params.id = this.dealInfo.id
              params.dealTime = this.dealForm.dealTime
              params.supplementInfo = this.dealForm.supplementInfo

              this.saveBtn = true
              RequestURL.updateDealTransaction({jsonData: JSON.stringify(params)}).then((res)=>{
                if (res.success){
                  this.$message({
                    type: 'success',
                    message: res.msg
                  })
                  this.$emit('saveDeal',true)
                }else{
                  this.$message({
                    type: 'success',
                    message: res.msg
                  })
                }
                this.saveBtn = false
                this.dealUpdateLog(this.dealInfo.id,this.dealInfo.id,'6','402',this.$utils.getFormFields(this.$refs['dealForm'])
                  ,this.originalData
                  ,{dealTime:params.dealTime
                  ,supplementInfo:params.supplementInfo})
              }).catch(()=>{
                this.saveBtn = false
                this.$emit('saveDeal',false)
              })
            })
          } else {
            this.saveBtn = false
            console.log('error submit!!');
            return false;
          }
        })
      }
    }
  }
</script>

<style lang="scss" scoped>
  .headerDeal{
    text-align: center;
    font-weight: 500;
  }
  .costForm .el-form-item--mini.el-form-item {
    margin-bottom: 4px;
  }
</style>
