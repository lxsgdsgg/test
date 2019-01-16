<template>
    <div style="width: 800px;" class="houseDeal">
      <el-form :model="dealForm" :rules="rules" ref="dealForm" label-width="100px" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item prop="dealDate" label="成交日期">
              <el-date-picker value-format="yyyy-MM-dd" type="date" format="yyyy-MM-dd" placeholder="选择日期" v-model="dealForm.dealDate" style="width:150px"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="4" >
            <el-checkbox v-model="dealForm.auto" style="padding-top: 5px;">自动分成</el-checkbox>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="traderName" label="成交人">
              <el-input v-model="dealForm.traderName" style="width: 150px;" :disabled="true"></el-input>
              <!--<base-cascader
                :changeOnSelect="false"
                v-model="dealForm.traderId"
                :url="getReferenceUserSelectUrl"
                :props="cascaderProps"
                :dataProps="cascaderDataProps"  style="width:150px">
              </base-cascader>-->
            </el-form-item>
          </el-col>
        </el-row>
        <el-row v-if="contractType=='02'">
          <el-col :span="12">
            <el-form-item prop="beginLeaseTime" label="租期开始">
              <el-date-picker value-format="yyyy-MM-dd" type="date" format="yyyy-MM-dd" placeholder="选择日期" v-model="dealForm.beginLeaseTime" style="width:150px" :disabled="true"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="endLeaseTime" label="租期截止">
              <el-date-picker value-format="yyyy-MM-dd" type="date" format="yyyy-MM-dd" placeholder="选择日期" v-model="dealForm.endLeaseTime" style="width:150px" :disabled="true"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row v-if="contractType=='01'">
          <el-col :span="12">
            <el-form-item label="合同号" prop="agreementId">
              <el-input v-model="dealForm.agreementId" style="width: 150px;" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item prop="price"  label="成交价格">
              <el-input v-model="dealForm.price" style="width: 150px;" :disabled="true">
                <i slot="suffix" >万</i>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-checkbox v-model="dealForm.isLoan" style="padding-top: 5px;">有贷款</el-checkbox>
          </el-col>
        </el-row>
        <el-row v-if="contractType=='02'">
          <el-col :span="12">
            <el-form-item label="合同号" prop="agreementId">
              <el-input v-model="dealForm.agreementId" style="width: 150px;" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="price"  label="租赁价格">
              <el-input v-model="dealForm.price" style="width: 150px;" :disabled="true">
                <i slot="suffix">元/月</i>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item prop="commissionPrice"  label="应收佣金">
              <el-input v-model="dealForm.commissionPrice" style="width: 150px;" :disabled="true">
                <i slot="suffix" >元</i>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="actualCommission" label="实应收佣金">
              <el-input v-model="dealForm.actualCommission" style="width: 150px;" :disabled="true">
                <i slot="suffix" >元</i>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row v-if="dealForm.isCross==1">
          <el-col :span="12">
            <el-form-item prop="houseRate" label="房源方占比">
              <el-input-number v-model="dealForm.houseRate" @change="houseRateChange" :min="0" :max="100" ></el-input-number>
              <span>%</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="custRate"  label="客源方占比">
              <el-input-number v-model="dealForm.custRate" @change="custRateChange" :min="0" :max="100" ></el-input-number>
              <span>%</span>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="产权证号">
              <el-input v-model="dealForm.propCertificate" style="width: 150px;">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item  label="权证人" prop="referenceId">
              <base-cascader
                :changeOnSelect="false"
                v-model="dealForm.referenceId"
                :url="getReferenceUserSelectUrl"
                :props="cascaderProps"
                :dataProps="cascaderDataProps"  style="width:150px" @change="onReferenceChange">
              </base-cascader>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item  label="备注">
              <el-input type="textarea" autosize v-model="dealForm.supplementInfo">
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <hr>
        <el-row>
          <el-col :span="12">
            <el-form-item label="甲方编号" prop="houseId">
              <el-input v-model="dealForm.houseId" style="width: 150px;" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="乙方编号" prop="demandId">
              <el-input v-model="dealForm.demandId" style="width: 150px;" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="甲方电话">
              <el-input v-model="dealForm.phoneA" style="width: 150px;" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="乙方电话">
              <el-input v-model="dealForm.phoneB" style="width: 150px;" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="甲方姓名">
              <el-input v-model="dealForm.nameA" style="width: 150px;" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="乙方姓名">
              <el-input v-model="dealForm.nameB" style="width: 150px;" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="证件号码">
              <el-input v-model="dealForm.cardA" style="width: 150px;" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="证件号码">
              <el-input v-model="dealForm.cardB" style="width: 150px;" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <hr>
      <el-form size="mini" class="costForm">
        <el-row>
          <el-col :span="4"><div class="headerDeal">费用类型</div></el-col>
          <el-col :span="4"><div class="headerDeal">费用项目</div></el-col>
          <el-col :span="3"><div class="headerDeal">交费人</div></el-col>
          <el-col :span="4"><div class="headerDeal">金额</div></el-col>
          <el-col :span="4"><div class="headerDeal">预计交费日期</div></el-col>
          <el-col :span="4"><div class="headerDeal">备注</div></el-col>
        </el-row>
        <el-row v-for="item in costList">
          <el-col :span="4">
            <el-form-item>
              <el-select v-model="item.moneyType" clearable style="width: 130px;" @change="initMoneyProject(item)">
                <el-option label="中介类费用" value="0"></el-option>
                <el-option label="金融类费用" value="1"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item>
              <el-select v-model="item.moneyProjId" clearable style="width: 130px;" @change="selectMoneyProjHandel(item)">
                <el-option
                  v-for="option in item.moneyProjData"
                  :key="option.value"
                  :label="option.label"
                  :value="option.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="3">
            <el-form-item>
              <el-select v-model="item.payerType" clearable style="width: 96px;">
                <el-option label="业主" value="0"></el-option>
                <el-option label="客户" value="1"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item>
              <el-input v-model="item.price" style="width: 130px;">
                <i slot="suffix" >元</i>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item>
              <el-date-picker v-model="item.estimatePayTime"  value-format="yyyy-MM-dd" type="date" format="yyyy-MM-dd" style="width: 130px;"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item>
              <el-input v-model="item.remark" style="width: 130px;"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="1">
            <el-form-item>
            <el-button type="text" @click="clearItem(item)">清除</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-row style="text-align: center;">
        <el-button type="primary"  @click="saveDeal">确 定</el-button>
        <el-button @click="cancelDeal">取 消</el-button>
      </el-row>
    </div>
</template>

<script>
  import {
    queryContractInfo,
    querySelectItems,
    addHouseDeal,
    queryCommissionPrice
  }  from '@/request/deal/houseDeal.js'
  import BaseCascader from '@/components/BaseCascader'
    export default{
        data () {
            return {
              contractInfo:{},
              dealForm:{
                'dealDate':'',
                'traderId':'',
                'traderName':'',
                'agreementId':'',
                'price':'',
                'auto':true,
                'isLoan':false,
                'commissionPrice':'',
                'propCertificate':'',
                'actualCommission':'',
                'referenceId':'',
                'reference':'',
                'supplementInfo':'',
                'houseId':'',
                'demandId':'',
                'phoneA':'',
                'phoneB':'',
                'nameA':'',
                'nameB':'',
                'cardA':'',
                'cardB':'',
                'beginLeaseTime':'',
                'endLeaseTime':'',
                'houseRate':'50',
                'custRate':'50',
                'isCross':0
              },
              rules:{
                'houseRate':[
                  { required: true, message: '请输入房源公司佣金占比', trigger: 'blur' }
                ],
                'custRate':[
                  { required: true, message: '请输入客源公司佣金占比', trigger: 'blur' }
                ],
              'dealDate':[
                { required: true, message: '请选择成交日期', trigger: 'blur' }
              ],
                'traderName':[
                  { required: true, message: '请选择成交人', trigger: 'blur' }
                  ],
                'agreementId':[
                  { required: true, message: '合同号不能为空', trigger: 'blur' }
                ],
                'price':[
                  { required: true, message: '成交价格不能为空', trigger: 'blur' }
                ],
                'commissionPrice':[
                  { required: true, message: '应收佣金不能为空', trigger: 'blur' }
                ],
                'actualCommission':[
                  { required: true, message: '实应收佣金不能为空', trigger: 'blur' }
                ],
                'beginLeaseTime':[
                  { required: true, message: '租期开始日期不能为空', trigger: 'blur' }
                ],
                'endLeaseTime':[
                  { required: true, message: '租期截止日期不能为空', trigger: 'blur' }
                ],
                'houseId':[
                  { required: true, message: '房源编号不能为空', trigger: 'blur' }
                ],
                'demandId':[
                  { required: true, message: '客户编号不能为空', trigger: 'blur' }
                ]
               },
              getReferenceUserSelectUrl: 'manage/commonselect/queryOpenCityCompanyDepartUser', // 选员工
              cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
              cascaderProps: { // 级联下拉组件配置选项
                value: 'code', // 指定选项的值为选项对象的某个属性值
                label: 'name' // 指定选项标签为选项对象的某个属性值
              },
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
              contractType : '01'
            }
        },
      props:['dealType','shhId'],
      components: {
        BaseCascader
      },
      mounted () {
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
        queryContractInfo({'shhId':this.shhId,'agreementType':this.contractType}).then((res)=>{
          if (res.success){
            this.contractInfo = res.data
            this.dealForm.agreementId = res.data.code
            if (this.contractType=='01'){
              this.dealForm.price = res.data.price/10000
              /*this.dealForm.commissionPrice = res.data.price*0.02
              if (String(this.dealForm.commissionPrice).indexOf(".")>-1){
                this.dealForm.commissionPrice = this.dealForm.commissionPrice.toFixed(2)
              }*/
            }else {
              this.dealForm.price = res.data.price
              //this.dealForm.commissionPrice = res.data.price
            }

            this.dealForm.actualCommission = res.data.commissionA+res.data.commissionB
            this.dealForm.houseId = res.data.houseId
            this.dealForm.demandId = res.data.custId
            this.dealForm.phoneA = res.data.mobileA
            this.dealForm.phoneB = res.data.mobileB
            this.dealForm.nameA = res.data.partyA
            this.dealForm.nameB = res.data.partyB
            this.dealForm.cardA = res.data.cardNoA
            this.dealForm.cardB = res.data.cardNoB
            this.dealForm.traderId = res.data.signPerson
            this.dealForm.traderName = res.data.signPersonName
            this.dealForm.isCross=res.data.isCross
            if (res.data.beginTime){
              this.dealForm.beginLeaseTime = this.$utils.timeFormat(res.data.beginTime)
            }
            if (res.data.endTime){
              this.dealForm.endLeaseTime = this.$utils.timeFormat(res.data.endTime)
            }
            this.dealForm.propCertificate = res.data.propertyRrightNO
            queryCommissionPrice({'houseId':this.shhId,'contractType':this.contractType,'dealPrice': res.data.price}).then((result)=>{
              if (result.success){
                this.dealForm.commissionPrice = result.data
              }
            })
          }
        })
      },
      methods:{
        initMoneyProject(item){
          if (item.moneyType === '0') {
              item.moneyProjData = this.agencyData
          }
          if (item.moneyType === '1') {
            item.moneyProjData = this.financeData
          }
        },
        clearItem(item){
          console.log(item)
          item.moneyType=''
          item.moneyProjId=''
          item.moneyProjName=''
          item.moneyProjData=[]
          item.payerType=''
          item.price=''
          item.estimatePayTime=''
          item.remark=''
        },
        cancelDeal(){
          this.$emit('cancelDeal')
        },
        selectMoneyProjHandel(item){
          for (let  i =0 ; i<item.moneyProjData.length;i++){
            if (item.moneyProjId===item.moneyProjData[i].value){
                item.moneyProjName = item.moneyProjData[i].label
                return
            }
          }
        },
        houseRateChange(val){
           this.dealForm.custRate = 100 - val
        },
        custRateChange(val){
          this.dealForm.houseRate = 100 - val
        },
        onReferenceChange(val, label, data){
          this.dealForm.reference = label
          if(data.dataType!='user'){
            //this.dealForm.referenceId = ''
            this.$message({
              type: 'warning',
              message: '只能选择人员！'
            })
            setTimeout(() => {
              this.dealForm.referenceId = ''
              this.dealForm.reference = ''
            }, 50)
          }


        },
        saveDeal(){
          this.$refs['dealForm'].validate((valid) => {
            if (valid) {
              let param = {}
              param.dealType = this.dealType
              if (this.contractType=='02'){
                param.beginLeaseTime = this.dealForm.beginLeaseTime
                param.endLeaseTime = this.dealForm.endLeaseTime
              }else {
                  if (this.dealForm.referenceId){
                    param.referenceId = this.dealForm.referenceId
                  }
                if (this.dealForm.reference){
                  param.reference = this.dealForm.reference
                }
              }
              param.agreementId = this.dealForm.agreementId
              param.demandId = this.dealForm.demandId
              param.shhid = this.shhId
              param.owner = this.dealForm.nameA
              param.custName = this.dealForm.nameB
              param.traderId = this.dealForm.traderId
              param.price = this.dealForm.price
              param.dealTime = this.dealForm.dealDate
              param.isautoDivide = this.dealForm.auto?1:0
              param.commissionPrice = this.dealForm.commissionPrice
              param.supplementInfo = this.dealForm.supplementInfo
              param.isLoan = this.dealForm.isLoan?1:0
              param.propCertificate = this.dealForm.propCertificate
              param.actualCommission = this.dealForm.actualCommission
              param.permissionArea = this.contractInfo.signDeptId
              param.operatorId = this.contractInfo.signPerson
              param.companyId = this.contractInfo.signCompanyId
              param.companyName = this.contractInfo.signCompanyName
              param.operator=this.contractInfo.signPersonName
              param.deptName = this.contractInfo.signDeptName

              let commissionsJson = []
              for (let i=0;i<this.costList.length;i++){
                if (this.costList[i].moneyType!=''&&this.costList[i].moneyProjId!=''
                  &&this.costList[i].payerType!=''&&this.costList[i].price!=''&&this.costList[i].estimatePayTime!=''){
                  let item = {}
                  item.moneyType=this.costList[i].moneyType
                  item.moneyProjId=this.costList[i].moneyProjId
                  item.moneyProjName=this.costList[i].moneyProjName
                  item.payerType=this.costList[i].payerType
                  item.price=this.costList[i].price
                  item.estimatePayTime=this.costList[i].estimatePayTime
                  item.remark=this.costList[i].remark
                  commissionsJson.push(item)
                }
              }


              let dealParam = {'dataJson':JSON.stringify(param),'commissionsJson':JSON.stringify(commissionsJson)}
              if (this.dealForm.isCross===1){
                let crossJson = {}
                crossJson.houseCompanyId = this.contractInfo.houseCompanyId
                crossJson.houseCompanyName = this.contractInfo.houseCompanyName
                crossJson.custCompanyId = this.contractInfo.custCompanyId
                crossJson.custCompanyName = this.contractInfo.custCompanyName
                crossJson.houseMaintainDeptId = this.contractInfo.houseMaintainDeptId
                crossJson.houseMaintainDeptName = this.contractInfo.houseMaintainDeptId
                crossJson.custMaintainDeptId = this.contractInfo.custMaintainDeptId
                crossJson.custMaintainDeptName = this.contractInfo.custMaintainDeptName
                crossJson.houseMaintainId = this.contractInfo.houseMaintainId
                crossJson.houseMaintainName = this.contractInfo.houseMaintainName
                crossJson.custMaintainId = this.contractInfo.custMaintainId
                crossJson.custMaintainName = this.contractInfo.custMaintainName
                crossJson.houseRate = this.dealForm.houseRate
                crossJson.custRate = this.dealForm.custRate
                dealParam.crossJson = JSON.stringify(crossJson)
              }
              addHouseDeal(dealParam).then((res)=>{
                if (res.success){
                  this.$message({
                    type: 'success',
                    message: '保存成功！'
                  })
                  this.$emit('saveDeal',true)
                }else {
                  this.$emit('saveDeal',false)
                }
              }).catch(()=>{
                this.$emit('saveDeal',false)
              })

            } else {
              console.log('error submit!!');
              return false;
            }
          });
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
