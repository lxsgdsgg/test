<template>
  <div style="width: 800px;">
    <el-tabs type="border-card">
      <el-tab-pane label="首付预算">
        <el-row>
          <el-col :span="15">
            <el-form label-position="right" label-width="100px" :model="taxationForm">
              <el-form-item label="房屋总价">
                <el-input v-model="taxationForm.price" style="width: 200px;" @change="taxationPriceChange"><i slot="suffix" >万元</i></el-input>
              </el-form-item>
              <el-form-item label="房屋面积">
                <el-input v-model="taxationForm.area" style="width: 200px;">
                  <i slot="suffix" >平米</i>
                </el-input>
              </el-form-item>
              <el-form-item label="房屋类型">
                <el-select v-model="taxationForm.houseType" placeholder="请选择" @change="houseTypeChange">
                  <el-option v-for="item in houseType" :key="item.id" :label="item.name" :value="item.id"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="房贷类型">
                <el-radio-group v-model="taxationForm.loanType" >
                  <el-radio :label="1">商业贷款</el-radio>
                  <el-radio :label="2">公积金贷款</el-radio>
                  <el-radio :label="3">组合贷款</el-radio>
                  <el-radio :label="4">全款</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="卖房家庭唯一">
                <el-radio-group v-model="taxationForm.only">
                  <el-radio :label="1">唯一</el-radio>
                  <el-radio :label="0">不唯一</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="距上次交易">
                <el-radio-group v-model="taxationForm.fiveYear">
                  <el-radio :label="5">满五年</el-radio>
                  <el-radio :label="2">满两年</el-radio>
                  <el-radio :label="0">不满两年</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="买方家庭首套">
                <el-radio-group v-model="taxationForm.first">
                  <el-radio :label="1">首套</el-radio>
                  <el-radio :label="2">二套</el-radio>
                  <el-radio :label="3">二套以上</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item  label="首付其他">
                <el-row style="width: 300px;">
                  <el-col :span="8">
                    <el-input v-model="taxationForm.otherBiLi" @change="otherChange"><i slot="suffix" >%</i></el-input>
                  </el-col>
                  <el-col :span="12">
                    <el-input v-model="taxationForm.other"><i slot="suffix" >万</i></el-input>
                  </el-col>
                </el-row>
              </el-form-item>
              <el-form-item >
                <el-button type="primary"  @click="calculationTaxation">开始计算</el-button>
              </el-form-item>
            </el-form>
          </el-col>
          <el-col :span="9">
            <el-card class="box-card" style="width: 300px;">
              <div slot="header" class="clearfix">
                <span>计算结果</span>
              </div>
              <div>
                <el-row>
                  <el-col :span="16"><span class="oneLevel">净首付</span></el-col>
                  <el-col :span="8"><span class="oneLevel">{{taxationValue.cleanPay}}万</span></el-col>
                </el-row>
                <el-row>
                  <el-col :span="16"><span class="oneLevel">税费合计</span></el-col>
                  <el-col :span="8"><span class="oneLevel">{{taxationValue.taxTotal}}万</span></el-col>
                </el-row>
                <el-row>
                  <el-col :span="16"><span class="twoLevel">增值税及附加</span></el-col>
                  <el-col :span="8"><span class="twoLevel">{{taxationValue.addedValueTax}}</span></el-col>
                </el-row>
                <el-row>
                  <el-col :span="16"><span class="twoLevel">契税</span></el-col>
                  <el-col :span="8"><span class="twoLevel">{{taxationValue.deedTaxs}}</span></el-col>
                </el-row>
                <el-row>
                  <el-col :span="16"><span class="twoLevel">个人所得税</span></el-col>
                  <el-col :span="8"><span class="twoLevel">{{taxationValue.incomeTax}}</span></el-col>
                </el-row>
                <el-row>
                  <el-col :span="16"><span class="twoLevel">土地出让金</span></el-col>
                  <el-col :span="8"><span class="twoLevel">{{taxationValue.landTtransferTax}}</span></el-col>
                </el-row>
                <el-row>
                  <el-col :span="16"><span class="oneLevel">其他</span></el-col>
                  <el-col :span="8"><span class="oneLevel">{{taxationValue.agencyFeeRatio}}万</span></el-col>
                </el-row>
                <hr>
                <el-row>
                  <el-col :span="16"><span class="threeLevel">首付总计</span></el-col>
                  <el-col :span="8"><span class="threeLevel">{{taxationValue.total}}万</span></el-col>
                </el-row>
                <div >
                  <TaxationPieChart v-if="initChart" :chartData="taxationData"></TaxationPieChart>
                </div>

              </div>
            </el-card>
          </el-col>
        </el-row>
      </el-tab-pane>
      <el-tab-pane label="房贷计算器">
        <el-row>
          <el-col :span="13">
            <el-form label-position="right" label-width="100px" :model="loanForm">
              <el-form-item label="房屋总价">
                <el-input v-model="loanForm.totalPrice" style="width: 260px;" @change="priceChange"><i slot="suffix" >万元</i></el-input>
              </el-form-item>
              <el-form-item label="房贷类型">
                <el-radio-group v-model="loanForm.loanType" @change="loanTypeChange">
                  <el-radio :label="1">商业贷款</el-radio>
                  <el-radio :label="2">公积金贷款</el-radio>
                  <el-radio :label="3">组合贷款</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item v-if="isBusiness" label="商业贷款">
                <el-row style="width: 260px;">
                  <el-col :span="13">
                    <el-select v-model="loanForm.businessBiLi" placeholder="请选择" @change="businessLoanBiLiChange">
                    <el-option v-for="item in businessBiLi" :key="item.value" :label="item.name" :value="item.value"></el-option>
                  </el-select>
                  </el-col>
                  <el-col :span="11">
                    <el-input v-model="loanForm.businessLoan"><i slot="suffix" >万元</i></el-input>
                  </el-col>
                </el-row>
              </el-form-item>

              <el-form-item v-if="isBusiness" label="商业年利率">
                <el-row style="width: 260px;">
                  <el-col :span="13">
                    <el-select v-model="loanForm.businessRateValue" placeholder="请选择" @change="businessRateValueChange">
                      <el-option v-for="item in loanRate.businessRates" :key="item.value" :label="item.name" :value="1"></el-option>
                      <el-option v-for="item in loanRate.rebates" :key="item.value" :label="item.name" :value="item.value"></el-option>
                    </el-select>
                  </el-col>
                  <el-col :span="11">
                    <el-input v-model="loanForm.businessRate"><i slot="suffix" >%</i></el-input>
                  </el-col>
                </el-row>
              </el-form-item>
              <el-form-item v-if="isPublic" label="公积金贷款">
                <el-input v-model="loanForm.publicLoan" style="width: 260px;" @change="publicLoanChange"><i slot="suffix" >万元</i></el-input>
              </el-form-item>
              <el-form-item v-if="isPublic" label="公积金年利率">
                <el-row style="width: 260px;">
                  <el-col :span="13">
                    <el-select v-model="loanForm.publicRateValue" placeholder="请选择" @change="publicRateValueChange">
                      <el-option v-for="item in loanRate.publicFundRates" :key="item.value" :label="item.name" :value="1"></el-option>
                      <el-option v-for="item in loanRate.rebates" :key="item.value" :label="item.name" :value="item.value"></el-option>
                    </el-select>
                  </el-col>
                  <el-col :span="11">
                    <el-input v-model="loanForm.publicRate"><i slot="suffix" >%</i></el-input>
                  </el-col>
                </el-row>
              </el-form-item>
              <el-form-item label="贷款年限">
                <el-input-number v-model="loanForm.years"  :min="1" :max="30" :step="1"></el-input-number>
              </el-form-item>
              <el-form-item >
                <el-button type="primary"  @click="calculationLoan">开始计算</el-button>
              </el-form-item>
            </el-form>
          </el-col>
          <el-col :span="11">
            <el-card class="box-card" style="width: 350px;">
              <div slot="header" class="clearfix">
                <el-row>
                  <el-col :span="6" ><span class="panelHeader">类型</span></el-col>
                  <el-col :span="9" style="text-align: center;"><span class="panelHeader">等额本息还款</span></el-col>
                  <el-col :span="9" style="text-align: center;"><span class="panelHeader">等额本金还款</span></el-col>
                </el-row>
              </div>
              <div >
                <el-row>
                  <el-col :span="6"><label class="cardHeader">月供</label></el-col>
                  <el-col :span="9" style="text-align: right;"><span class="cardHeader">{{repayment.interest.monthPay}}</span>元</el-col>
                  <el-col :span="9" style="text-align: right;"><span class="cardHeader">{{repayment.principal.monthPay}}</span>元</el-col>
                </el-row>
                <el-row>
                  <el-col :span="24" style="color: #8c939d;text-align: right;">每月递减<span class="cardHeader">{{repayment.principal.degression}}</span>元</el-col>
                </el-row>
                <el-row>
                  <el-col :span="6"><label class="cardHeader">还款月数</label></el-col>
                  <el-col :span="9" style="text-align: right;"><span class="cardHeader">{{repayment.interest.monthcount}}</span>月</el-col>
                  <el-col :span="9" style="text-align: right;"><span class="cardHeader">{{repayment.principal.monthcount}}</span>月</el-col>
                </el-row>
                <el-row>
                  <el-col :span="6"><label class="cardHeader">总利息</label></el-col>
                  <el-col :span="9" style="text-align: right;"><span class="cardHeader">{{repayment.interest.totalInterest}}</span>元</el-col>
                  <el-col :span="9" style="text-align: right;"><span class="cardHeader">{{repayment.principal.totalInterest}}</span>元</el-col>
                </el-row>
                <el-row>
                  <el-col :span="6"><label class="cardHeader">本息合计</label></el-col>
                  <el-col :span="9" style="text-align: right;"><span class="cardHeader">{{repayment.interest.totalPrice}}</span>元</el-col>
                  <el-col :span="9" style="text-align: right;"><span class="cardHeader">{{repayment.principal.totalPrice}}</span>元</el-col>
                </el-row>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import {
    queryHousePropertyList,
    queryLoanRate
  } from '@/request/house/calculator.js'
  import TaxationPieChart from '@/components/TaxationPieChart.vue'
  export default{
    data () {
      return {
        initChart:false,
        currType:[],
        houseType:[],
        businessBiLi:[],
        loanRate:{},
        isPublic:false,
        isBusiness:true,
        publicBaseRate:0,
        businessBaseRade:0,
        otherBiLi:'',
        taxationForm: {'price':'','area':'','houseType':'','only':1,'fiveYear':5,'first':1,'loanType':1,'otherBiLi':'','other':''},
        taxationData: [
          {value: 320,name: '增值税及附加'},
          {value: 4500,name: '契税'},
          {value: 32000,name: '个人所得税'},
          {value: 4500,name: '土地出让金'}
        ],
        taxationValue:{cleanPay:'',taxTotal:'',addedValueTax:'元',deedTaxs:'元',incomeTax:'元',landTtransferTax:'元',agencyFeeRatio:'',total:''},
        loanForm:{totalPrice:'',loanType:1,businessBiLi:'',businessLoan:'',publicLoan:'80',businessRateValue:'',businessRate:'',publicRateValue:'',publicRate:'',years:'30'},
        repayment:{
          interest:{monthPay:'',monthcount:'',totalInterest:'',totalPrice:''},
          principal:{monthPay:'',monthcount:'',totalInterest:'',totalPrice:'',degression:''}
        }
      }
    },
    props:['price','area'],
    components: {
      TaxationPieChart
    },
    mounted () {
      if (this.price!=null) {
        this.taxationForm.price = this.price
        this.loanForm.totalPrice = this.price
      }
      if (this.area!=null) {
        this.taxationForm.area = this.area
      }
      queryHousePropertyList(null).then((res)=>{
        if (res.success){
          this.houseType = res.data
          console.log(res.data)
          this.taxationForm.houseType = res.data[0].id
          this.houseTypeChange(res.data[0].id)
        }
      })
      queryLoanRate(null).then((res)=>{
        if (res.success){
          this.loanRate = res.data
          console.log('贷款利率',res.data)
          console.log('商业贷款最大比率',this.loanRate.loanRatioCeiling)
          for (let i=this.loanRate.loanRatioCeiling;i>0;i--){
            let item = {value:i,name:i+'%'}
            this.businessBiLi.push(item)
          }
          this.loanForm.publicLoan = this.loanRate.publicLoanCeiling
          this.loanForm.businessBiLi = this.loanRate.loanRatioCeiling
          this.businessLoanBiLiChange(this.loanRate.loanRatioCeiling)
          this.businessBaseRade = this.loanRate.businessRates[0].value
          this.publicBaseRade = this.loanRate.publicFundRates[0].value
          this.$nextTick(()=>{
            this.loanForm.businessRate = this.businessBaseRade
            this.loanForm.publicRate = this.publicBaseRade
            this.loanForm.businessRateValue = 1
            this.loanForm.publicRateValue = 1
          })
        }
      })

    },
    methods:{
      calculationTaxation(){
        if (this.taxationForm.price==='') {
          this.$message({
            type: 'warning',
            message: '请输入总价'
          })
          return
        }else if (isNaN(this.taxationForm.price)){
          this.$message({
            type: 'warning',
            message: '总价输入错误'
          })
          return
        }
        if (this.taxationForm.area==='') {
          this.$message({
            type: 'warning',
            message: '请输入面积'
          })
          return
        }else if (isNaN(this.taxationForm.area)){
          this.$message({
            type: 'warning',
            message: '面积输入错误'
          })
          return
        }
        let typeItem={}
        if (this.taxationForm.houseType){
          for (let  i=0;i<this.houseType.length;i++){
            if (this.houseType[i].id===this.taxationForm.houseType){
                typeItem = this.houseType[i]
              break
            }
          }
          console.log(typeItem)
        }else {
          this.$message({
            type: 'warning',
            message: '请选择房屋类型'
          })
          return
        }
        if (this.taxationForm.other===''||isNaN(this.taxationForm.other)){
          this.$message({
            type: 'warning',
            message: '首付其他数据错误'
          })
          return
        }
        this.initChart=false
        this.$nextTick(()=>{
          let isFiveYear = 0 //满五年
          let isTwoYear = 0 //满两年
          let  only = this.taxationForm.only //唯一
          let isFirst = 0 //首套
          let isTwo = 0 //二套
          let isThree = 0 //三套
          let isLimitAreaAbove = 0 //90平米以上
          if (this.taxationForm.fiveYear===5){
            isTwoYear = 1
            isFiveYear = 1
          }
          if (this.taxationForm.fiveYear===2){
            isTwoYear = 1
          }
          if (this.taxationForm.first===1){
            isFirst = 1
          }
          if (this.taxationForm.first===2){
            isTwo = 1
          }
          if (this.taxationForm.first===3){
            isThree = 1
          }
          let area = parseFloat(this.taxationForm.area)
          if (area>90){
            isLimitAreaAbove=1
          }
          let price = parseInt(this.taxationForm.price)
          let cleanPay = 0

          if (this.taxationForm.loanType===1){
            cleanPay = price * 0.3
            if (String(cleanPay).indexOf(".")>-1){
              this.taxationValue.cleanPay = cleanPay.toFixed(2)
            }else {
              this.taxationValue.cleanPay = cleanPay
            }
          }else if(this.taxationForm.loanType===2){
            this.taxationValue.cleanPay=price-this.loanRate.publicLoanCeiling
          }else if(this.taxationForm.loanType===3){
            cleanPay=(price-this.loanRate.publicLoanCeiling)*0.3
            if (String(cleanPay).indexOf(".")>-1){
              this.taxationValue.cleanPay = cleanPay.toFixed(2)
            }else {
              this.taxationValue.cleanPay = cleanPay
            }
          }else {
            this.taxationValue.cleanPay=price
          }
          /*let agency = price*typeItem.agencyFeeRatio/100
          if (String(agency).indexOf(".")>-1){
            this.taxationValue.agencyFeeRatio = agency.toFixed(2)
          }else {
            this.taxationValue.agencyFeeRatio = agency
          }*/
          this.taxationValue.agencyFeeRatio = this.taxationForm.other
          let added = price*typeItem.addedValueTax/100*10000
          let addedUnit = '元'
          if (added>=10000){
            addedUnit = '万'
            added = added/10000
          }
          if (String(added).indexOf(".")>-1){
            this.taxationValue.addedValueTax = added.toFixed(2) + addedUnit
          }else {
            this.taxationValue.addedValueTax = added + addedUnit
          }
          this.taxationData[0].value = price*typeItem.addedValueTax/100*10000
          let income = price*typeItem.incomeTax/100*10000
          let incomeUnit = '元'
          if (income>=10000){
            incomeUnit = '万'
            income = income/10000
          }
          if (income==0) incomeUnit = ''
          if (isFiveYear===1&&only===1){
            this.taxationData[2].value = 0
            this.taxationValue.incomeTax = 0
            income = 0
          }else {
            if (String(income).indexOf(".")>-1){
              this.taxationValue.incomeTax = income.toFixed(2) + incomeUnit
            }else {
              this.taxationValue.incomeTax = income + incomeUnit
            }
            this.taxationData[2].value = price*typeItem.incomeTax/100*10000
          }
          let land=price*typeItem.landTtransferTax/100*10000
          let landUnit = '元'
          if (land>=10000){
            landUnit = '万'
            land = land/10000
          }
          if (land==0) landUnit = ''
          if (String(land).indexOf(".")>-1){
            this.taxationValue.landTtransferTax = land.toFixed(2) + landUnit
          }else {
            this.taxationValue.landTtransferTax = land + landUnit
          }
          this.taxationData[3].value = price*typeItem.landTtransferTax/100*10000
          let deedTax = 0
          let taxRate = 0
          if (typeItem.type==0){
            taxRate = typeItem.businessDeedTax
            deedTax = price*typeItem.businessDeedTax/100*10000
            let deedTaxUnit = '元'
            if (deedTax>=10000){
              deedTaxUnit = '万'
              deedTax=deedTax/10000
            }
            if (String(deedTax).indexOf(".")>-1){
              this.taxationValue.deedTaxs = deedTax.toFixed(2) + deedTaxUnit
            }else {
              this.taxationValue.deedTaxs = deedTax + deedTaxUnit
            }
            this.taxationData[1].value = price*typeItem.businessDeedTax/100*10000
          }else {

            for (let i=0; i< typeItem.deedTaxs.length;i++){
              if (isThree===1&&typeItem.deedTaxs[i].isThree===isThree){
                taxRate = typeItem.deedTaxs[i].taxRate
                console.log('三套',taxRate)
                break
              }
              if (isTwo===1&&typeItem.deedTaxs[i].isTwoYear===isTwoYear&&typeItem.deedTaxs[i].isLimitAreaAbove===isLimitAreaAbove
                &&typeItem.deedTaxs[i].isTwo===isTwo){
                taxRate = typeItem.deedTaxs[i].taxRate
                console.log('二套',taxRate)
                break
              }
              if (isFirst===1&&typeItem.deedTaxs[i].isTwoYear===isTwoYear&&typeItem.deedTaxs[i].isLimitAreaAbove===isLimitAreaAbove
                &&typeItem.deedTaxs[i].isFirst===isFirst){
                taxRate = typeItem.deedTaxs[i].taxRate
                console.log('首套',taxRate)
                break
              }

            }
            console.log('契税费率')
            console.log(taxRate)
            console.log(this.taxationForm)
            console.log(isFirst,isTwo,isThree)
            deedTax= price*taxRate*100
            let deedTaxUnit = '元'
            if (deedTax>=10000){
              deedTaxUnit = '万'
              deedTax=deedTax/10000
            }
            if (String(deedTax).indexOf(".")>-1){
              this.taxationValue.deedTaxs = deedTax.toFixed(2) + deedTaxUnit
            }else {
              this.taxationValue.deedTaxs = deedTax + deedTaxUnit
            }

            this.taxationData[1].value = price*taxRate*100
          }
          let taxTotal = (this.taxationData[0].value+this.taxationData[1].value+this.taxationData[2].value+this.taxationData[3].value)/10000
          console.log(taxTotal)
          if (String(taxTotal).indexOf(".")>-1){
            this.taxationValue.taxTotal = taxTotal.toFixed(2)
          }else {
            this.taxationValue.taxTotal = taxTotal
          }
          let total  = parseFloat(this.taxationValue.cleanPay )+ taxTotal + parseFloat(this.taxationValue.agencyFeeRatio)
          if (String(total).indexOf(".")>-1){
            this.taxationValue.total = total.toFixed(2)
          }else {
            this.taxationValue.total = total
          }
          console.log(this.taxationValue)
          this.initChart=true
        })
      },
      selectedType(value,item){
         console.log(item)
      },
      taxationPriceChange(){
        this.otherChange()
      },
      priceChange(){
        if (!isNaN(this.loanForm.totalPrice)) {
          let price = parseInt(this.loanForm.totalPrice)
          this.loanForm.businessLoan = price * this.loanForm.businessBiLi/100
        }
      },
      houseTypeChange(value){
        for (let i=0;i<this.houseType.length;i++){
          if (this.houseType[i].id===value){
            this.taxationForm.otherBiLi = this.houseType[i].agencyFeeRatio
            this.otherBiLi = this.houseType[i].agencyFeeRatio
            this.otherChange()
            return
          }
        }
      },
      otherChange(){
        if (this.taxationForm.otherBiLi==='' || isNaN(this.taxationForm.otherBiLi)) {
          this.taxationForm.otherBiLi = this.otherBiLi
        }
        if (this.taxationForm.price!=''&&!isNaN(this.taxationForm.price)) {
          this.taxationForm.other = this.taxationForm.price * this.taxationForm.otherBiLi / 100
          if (String(this.taxationForm.other).indexOf('.')>-1){
            this.taxationForm.other = this.taxationForm.other.toFixed(2)
          }
        }
      },
      loanTypeChange(value){
         if (value===1){
           this.isBusiness = true
           this.isPublic = false
         }
        if (value===2){
          this.isBusiness = false
          this.isPublic = true
        }
        if (value===3){
          this.isBusiness = true
          this.isPublic = true
        }
      },
      businessLoanBiLiChange(item){
          console.log(this.loanForm.totalPrice)
        if (this.loanForm.totalPrice!=''&&!isNaN(this.loanForm.totalPrice)){
          this.loanForm.businessLoan = parseInt(this.loanForm.totalPrice) * item/100
        }
      },
      businessRateValueChange(value){
        this.loanForm.businessRate = (this.businessBaseRade*value).toFixed(2)
      },
      publicRateValueChange(value){
        this.loanForm.publicRate = (this.publicBaseRade*value).toFixed(2)
      },
      publicLoanChange(){
        if (isNaN(this.loanForm.publicLoan)) {
          this.loanForm.publicLoan = this.loanRate.publicLoanCeiling
        }else {
          let total = parseInt(this.loanForm.publicLoan)
          if (total>this.loanRate.publicLoanCeiling){
            this.loanForm.publicLoan = this.loanRate.publicLoanCeiling
          }
        }
      },
      calculationLoan(){
          console.log(this.loanForm)
       if (this.loanForm.loanType === 1) {
         if(this.loanForm.totalPrice===''||isNaN(this.loanForm.totalPrice)) {
           this.$message({
             type: 'warning',
             message: '请输入总价'
           })
           return
         }else {
           if (this.loanForm.businessLoan!=''&&this.loanForm.businessRate!=''&&this.loanForm.years!=''
           &&!isNaN(this.loanForm.businessLoan)&&!isNaN(this.loanForm.businessRate)&&!isNaN(this.loanForm.years)){
             //商业本金贷款
             let busPrincipal = this.getPrincipal(this.loanForm.businessLoan,this.loanForm.businessRate,this.loanForm.years)
             //商业本息
             let busInterest = this.getInterest(this.loanForm.businessLoan,this.loanForm.businessRate,this.loanForm.years)
             this.repayment.interest = busInterest
             this.repayment.principal = busPrincipal
           }else {
             this.$message({
               type: 'warning',
               message: '贷款、利率、年限不能为空'
             })
             return
           }
         }
       }
        if (this.loanForm.loanType === 2) {
          if(this.loanForm.publicLoan===''||isNaN(this.loanForm.publicLoan)) {
            this.$message({
              type: 'warning',
              message: '请输入公积金贷款金额'
            })
            return
          } else {
            if (this.loanForm.publicRate!=''&&!isNaN(this.loanForm.publicRate)&&!isNaN(this.loanForm.years)&&this.loanForm.years!=''){
              //本金贷款
              let principal = this.getPrincipal(this.loanForm.publicLoan,this.loanForm.publicRate,this.loanForm.years)
              //本息
              let interest = this.getInterest(this.loanForm.publicLoan,this.loanForm.publicRate,this.loanForm.years)
              this.repayment.interest = interest
              this.repayment.principal = principal
            }else {
              this.$message({
                type: 'warning',
                message: '利率、年限不能为空'
              })
              return
            }
          }
        }
        if (this.loanForm.loanType === 3) {
          if (this.loanForm.businessLoan!=''&&this.loanForm.businessRate!=''&&this.loanForm.years!=''&&this.loanForm.publicLoan!=''&&!isNaN(this.loanForm.publicLoan)
            &&this.loanForm.publicRate!=''&&!isNaN(this.loanForm.publicRate)&&!isNaN(this.loanForm.businessLoan)&&!isNaN(this.loanForm.businessRate)&&!isNaN(this.loanForm.years)){
            //商业本金贷款
            let busPrincipal = this.getPrincipal(this.loanForm.businessLoan,this.loanForm.businessRate,this.loanForm.years)
            //商业本息
            let busInterest = this.getInterest(this.loanForm.businessLoan,this.loanForm.businessRate,this.loanForm.years)
            //公积金本金贷款
            let pubPrincipal = this.getPrincipal(this.loanForm.publicLoan,this.loanForm.publicRate,this.loanForm.years)
            //公积金本息
            let pubInterest = this.getInterest(this.loanForm.publicLoan,this.loanForm.publicRate,this.loanForm.years)

            let interest = {monthPay:'',monthcount:'',totalInterest:'',totalPrice:''}
            let principal = {monthPay:'',monthcount:'',totalInterest:'',totalPrice:'',degression:''}
            interest.monthPay = parseFloat(busInterest.monthPay) + parseFloat(pubInterest.monthPay)
            if (String(interest.monthPay).indexOf(".")>-1){
              interest.monthPay = interest.monthPay.toFixed(2)
            }
            interest.totalInterest = parseFloat(busInterest.totalInterest) + parseFloat(pubInterest.totalInterest)
            if (String(interest.totalInterest).indexOf(".")>-1){
              interest.totalInterest = interest.totalInterest.toFixed(2)
            }
            interest.totalPrice = parseFloat(busInterest.totalPrice) + parseFloat(pubInterest.totalPrice)
            if (String(interest.totalPrice).indexOf(".")>-1){
              interest.totalPrice = interest.totalPrice.toFixed(2)
            }
            interest.monthcount = busInterest.monthcount

            principal.monthPay = parseFloat(busPrincipal.monthPay) + parseFloat(pubPrincipal.monthPay)
            if (String(principal.monthPay).indexOf(".")>-1){
              principal.monthPay = principal.monthPay.toFixed(2)
            }
            principal.totalInterest = parseFloat(busPrincipal.totalInterest) + parseFloat(pubPrincipal.totalInterest)
            if (String(principal.totalInterest).indexOf(".")>-1){
              principal.totalInterest = principal.totalInterest.toFixed(2)
            }
            principal.totalPrice = parseFloat(busPrincipal.totalPrice) + parseFloat(pubPrincipal.totalPrice)
            if (String(principal.totalPrice).indexOf(".")>-1){
              principal.totalPrice = principal.totalPrice.toFixed(2)
            }
            principal.degression = parseFloat(busPrincipal.degression) + parseFloat(pubPrincipal.degression)
            if (String(principal.degression).indexOf(".")>-1){
              principal.degression = principal.degression.toFixed(2)
            }
            principal.monthcount = busInterest.monthcount

            this.repayment.interest = interest
            this.repayment.principal = principal
          }else {
            this.$message({
              type: 'warning',
              message: '贷款、利率、年限不能为空'
            })
            return
          }
        }
      },
      getInterest(total,rate,years){
        let busTotal = total*10000 //商业贷款金额
        let count = years*12 //还款月数
        let busRateMonth = rate/12/100 //商业月利率
        let temp = Math.pow(1+busRateMonth,count)
        let busFixedMonth = busTotal*busRateMonth*temp/(temp-1) //每月固定还款
        let interestAll2 = busFixedMonth * count - busTotal //本息总利息
        let busAll2 = busTotal + interestAll2 //本息合计
        if (String(busFixedMonth).indexOf(".")>-1){
          busFixedMonth = busFixedMonth.toFixed(2)
        }
        if (String(interestAll2).indexOf(".")>-1){
          interestAll2 = interestAll2.toFixed(2)
        }
        if (String(busAll2).indexOf(".")>-1){
          busAll2 = busAll2.toFixed(2)
        }
        return {monthPay:busFixedMonth,monthcount:count,totalInterest:interestAll2,totalPrice:busAll2}
      },
      getPrincipal(total,rate,years){
        let busTotal = total*10000 //商业贷款金额
        let count = years*12 //还款月数
        let busRateMonth = rate/12/100 //商业月利率
        let  principalMonth = busTotal/count //商业月本金
        let interestAll1=0;//本金总利息
        for (let i=0;i<count;i++){
          interestAll1 += (busTotal-(i*principalMonth))* busRateMonth
        }
        let interestMonth1 = busTotal*busRateMonth //第一个月利息
        let interestMonth2 = (busTotal-principalMonth)*busRateMonth //第二个月利息
        let busDegression = interestMonth1 - interestMonth2
        let busAll1 = busTotal + interestAll1 //本息合计
        let monthPay = principalMonth+interestMonth1
        if (String(monthPay).indexOf(".")>-1){
          monthPay = monthPay.toFixed(2)
        }
        if (String(interestAll1).indexOf(".")>-1){
          interestAll1 = interestAll1.toFixed(2)
        }
        if (String(busAll1).indexOf(".")>-1){
          busAll1 = busAll1.toFixed(2)
        }
        if (String(busDegression).indexOf(".")>-1){
          busDegression = busDegression.toFixed(2)
        }
        return {monthPay:monthPay,monthcount:count,totalInterest:interestAll1,totalPrice:busAll1,degression:busDegression}
      }
    }
  }
</script>

<style lang="scss" scoped>
.oneLevel{
  font-size: 14px;
  color: #000000;
  font-weight: 600;
  line-height: 24px;
}
.twoLevel{
  font-size: 14px;
  color: #666;
  font-weight: 500;
  line-height: 20px;
}
.threeLevel{
  font-size: 14px;
  color: #bd4147;
  font-weight: 600;
  line-height: 24px;
}
.panelHeader{
  font-size: 16px;
  font-weight: 700;
}
label.cardHeader{
  font-size: 14px;
  font-weight: 700;
}
span.cardHeader{
  font-size: 14px;
  color: #e0000f;
}
.cardHeader{
  line-height: 24px;
}
.el-radio + .el-radio {
  margin-left: 16px;
}
</style>
