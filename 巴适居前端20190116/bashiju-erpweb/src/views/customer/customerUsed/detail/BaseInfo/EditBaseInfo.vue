<template>
  <div v-loading="loadingView" style="height: 575px">
    <el-form v-if="selectOpts" :model="detailEditForm" ref="detailEditForm" :rules="rules" label-width="110px" size="small">

      <div class="form-wrapper">
        <el-row>
          <el-col :span="8">
            <el-form-item label="客户姓名">
              <el-form-item style=""><el-input style="" v-model="detailEditForm.custName"></el-input></el-form-item>
              <el-form-item style=""><el-input style="" v-model="detailEditForm.relateTypeId"></el-input></el-form-item>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="需求区域" prop="labeld">
              <base-select
                v-model="detailEditForm.labeld"
                :data="selectOpts.labeld"
                :props="selectProps"
                :multiple="true"
              >
              </base-select>
            </el-form-item>
          </el-col>

          <el-col :span ="6">
            <el-form-item label="用途" prop="transactionTypeId">

              <base-select
                v-model="detailEditForm.transactionTypeId"
                :data="selectOpts.transactionTypeId"
                :props="selectProps"
              >
              </base-select>

            </el-form-item>
          </el-col>


        </el-row>

        <el-row>
          <el-col :span ="6">
            <el-form-item label="需求户型" required>
              <el-input></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item label="需求价格" label-width="80px">
              <el-form-item style="display: inline-block;"><el-input :value="houseInfo.totalLayers"></el-input></el-form-item>
              <el-form-item style="display: inline-block;"><el-input :value="houseInfo.floorCount"></el-input></el-form-item>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="需求面积" label-width="80px">
              <el-form-item style="display: inline-block;"><el-input :value="houseInfo.totalLayers"></el-input></el-form-item>
              <el-form-item style="display: inline-block;"><el-input :value="houseInfo.floorCount"></el-input></el-form-item>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            急切
          </el-col>
        </el-row>


        <el-row>

          <el-col :span="8">
            <el-form-item label="需求楼层" style="">
              <el-form-item style="display: inline-block;"><el-input :value="houseInfo.elevatorCount"></el-input></el-form-item>
              <el-form-item style="display: inline-block;"><el-input :value="houseInfo.householdCount"></el-input></el-form-item>
            </el-form-item>
          </el-col>

          <el-col :span ="5">
            <el-form-item label="朝向" prop="buildSpace">
              <el-input v-model="detailEditForm.buildSpace"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span ="5">
            <el-form-item label="需求房龄" prop="useSpace">
              <el-input v-model="detailEditForm.useSpace"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="装修:" prop="decorationId">

              <base-select
                v-model="detailEditForm.decorationId"
                :data="selectOpts.decorationId"
                :props="selectProps"
              >
              </base-select>

            </el-form-item>
          </el-col>

        </el-row>

        <el-row>

          <el-col :span="10" class="select-con">
            <el-form-item label="配套:" prop="matching">

              <base-select
                v-model="detailEditForm.matching"
                :data="selectOpts.matchingIds"
                :props="selectProps"
                :multiple="true">
              </base-select>

            </el-form-item>
          </el-col>

          <el-col :span="14" class="select-con">
            <el-form-item label="需求原因" prop="actuality">

             <el-input></el-input>

            </el-form-item>
          </el-col>

        </el-row>

        <el-row>

          <el-col :span="8" class="select-con">
            <el-form-item label="推荐标签" prop="structureId">

              <base-select
                v-model="detailEditForm.structureId"
                :data="selectOpts.structureId"
                :props="selectProps"
              >
              </base-select>

            </el-form-item>
          </el-col>

          <el-col :span="8" class="select-con">
            <el-form-item label="房屋类型" prop="buildDates">
              <el-date-picker
                v-model="detailEditForm.buildDates"
                type="year"
                editable
                value-format="yyyy"
                placeholder="选择建造年代"
                style="width: 100%"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>

          <el-col :span="8" class="select-con">
            <el-form-item label="产权性质" prop="propertyTypeId">

             一手、二手

            </el-form-item>
          </el-col>


        </el-row>
        <el-row>***8*88****88*8*</el-row>
        <el-row>

          <el-col :span="6" class="select-con">
            <el-form-item label="沟通阶段" prop="propRightsLenId">

              <base-select
                v-model="detailEditForm.propRightsLenId"
                :data="selectOpts.propRightsLenId"
                :props="selectProps"
              >
              </base-select>

            </el-form-item>
          </el-col>

          <el-col :span="6" class="select-con">
            <el-form-item label="客户来源" prop="taxpayerId">

              <base-select
                v-model="detailEditForm.taxpayerId"
                :data="selectOpts.taxpayerId"
                :props="selectProps"
              >
              </base-select>

            </el-form-item>
          </el-col>

          <el-col :span="6" class="select-con">
            <el-form-item label=" 消费理念" prop="housingYearsId">

              <base-select
                v-model="detailEditForm.housingYearsId"
                :data="selectOpts.taxpayerId"
                :props="selectProps"
              >
              </base-select>

            </el-form-item>
          </el-col>

          <el-col :span="6" class="select-con">
            <el-form-item label="国籍" prop="resourceTypeId">

              <base-select
                v-model="detailEditForm.resourceTypeId"
                :data="selectOpts.resourceTypeId"
                :props="selectProps"
              >
              </base-select>

            </el-form-item>
          </el-col>

        </el-row>

        <el-row>
          <el-col :span="6" class="select-con">
            <el-form-item label="民族" prop="entrustCode">

              <el-input v-model="detailEditForm.entrustCode" style="width: 100%"></el-input>

            </el-form-item>
          </el-col>

          <el-col :span="6" class="select-con">
            <el-form-item label="证件号码" prop="payTypeId">

              <base-select
                v-model="detailEditForm.payTypeId"
                :data="selectOpts.payTypeId"
                :props="selectProps"
              >
              </base-select>

            </el-form-item>
          </el-col>

          <el-col :span="6" class="select-con">
            <el-form-item label="邮箱" prop="percentage">

              <el-input v-model="detailEditForm.percentage" style="width: 100%"></el-input>

            </el-form-item>
          </el-col>

          <el-col :span="6" class="select-con">
            <el-form-item label="QQ" prop="minSellingPrice">

              <el-input v-model="detailEditForm.minSellingPrice" style="width: 100%"></el-input>

            </el-form-item>
          </el-col>


        </el-row>

        <el-row>

          <el-col :span="6" class="select-con">
            <el-form-item label="微信" prop="minRentPrice">

              <el-input v-model="detailEditForm.minRentPrice" style="width: 100%"></el-input>

            </el-form-item>
          </el-col>


          <el-col :span="6" class="select-con">
            <el-form-item label="交通工具" prop="viewingTypeId">

              <base-select
                v-model="detailEditForm.viewingTypeId"
                :data="selectOpts.viewingTypeId"
                :props="selectProps"
              >
              </base-select>

            </el-form-item>
          </el-col>

          <!--<el-col :span="6">-->
          <!--<el-form-item label="钥匙店" prop="developers">-->

          <!--<el-select v-model="detailEditForm.propertyFees" style="width: 100%">-->
          <!--<el-option label="40年" value="40"></el-option>-->
          <!--<el-option label="50年" value="50"></el-option>-->
          <!--<el-option label="70年" value="70"></el-option>-->
          <!--<el-option label="其他" value="0"></el-option>-->
          <!--</el-select>-->

          <!--</el-form-item>-->
          <!--</el-col>-->

          <el-col :span="6" class="select-con">
            <el-form-item label="联系地址" prop="lastTradingTime">

              <el-date-picker
                v-model="detailEditForm.lastTradingTime"
                type="date"
                editable
                value-format="timestamp"
                placeholder="选择建筑年代"
                style="width: 100%"
              >
              </el-date-picker>

            </el-form-item>
          </el-col>

          <el-col :span="6" class="select-con">
            <el-form-item label="客源等级" prop="mortgageId">

              <base-select
                v-model="detailEditForm.mortgageId"
                :data="selectOpts.mortgageId"
                :props="selectProps"
              >
              </base-select>

            </el-form-item>
          </el-col>

        </el-row>

       
        <el-form-item label="备注：" prop="remark">
          <el-input type="textarea" v-model="detailEditForm.remark" style="width: 100%"></el-input>
        </el-form-item>
      </div>

      <div class="btn-group">
        <el-button type="primary" @click="handleSubmit" :loading="loadingSubmitBtn" size="medium">保存</el-button>
        <el-button @click="handleCancel" size="medium">取消</el-button>
        <el-button type="primary" @click="handleReset" size="medium">重置</el-button>
      </div>
    </el-form>
  </div>

</template>

<script>
  import {getBaseInfoSelectOpts, getHouseLabel, updateHousing} from '@/request/customer/customerUsed'
  import BaseSelect from '@/components/BaseSelect'

  const transactionTypeId = [
    {
      fieldValue: '出售',
      fieldCode: '1'
    },
    {
      fieldValue: '出租',
      fieldCode: '2'
    },
    {
      fieldValue: '租售',
      fieldCode: '3'
    }
  ]

  const payTypeId = [
    {
      fieldValue: '押一年付',
      fieldCode: '1'
    },
    {
      fieldValue: '押一半年付',
      fieldCode: '2'
    }
  ]

  const mortgageId = [
    {
      fieldValue: '无抵押',
      fieldCode: '0'
    },
    {
      fieldValue: '有抵押',
      fieldCode: '1'
    }
  ]

  const isUploadCertificate = [
    {
      fieldValue: '未上传',
      fieldCode: '0'
    },
    {
      fieldValue: '已上传',
      fieldCode: '1'
    }
  ]

  const heatingModeId = [
    {
      fieldValue: '集中供暖',
      fieldCode: '1'
    },
    {
      fieldValue: '无供暖',
      fieldCode: '2'
    }
  ]

  const tradingRightsId = [
    {
      fieldValue: '非共有',
      fieldCode: '0'
    },
    {
      fieldValue: '共有',
      fieldCode: '1'
    }
  ]

  export default {
    props:{
      customerInfo:{
        type: Object,
        required: true
      }
    },
    components: {BaseSelect},
    data() {
      return {
        detailEditForm :{
          id: '',
          titles: '', // 房源标题
          labeld: [], // 推荐标签
          isSynchron: false, // 外网同步
          transactionTypeId: '', // 交易类型
          decorationId: '', // 装修
          matching: [], // 配套
          actuality: [], // 现状
          houseTypeId: '', // 房屋类型
          structureId: '', // 建筑结构
          propertyTypeId: '', // 产权性质
          propRightsLenId: '', // 产权年限
          certificateDate: '', // 产证日期
          taxpayerId: '', // 房源税费
          housingYearsId: '', // 交易年限
          viewingTypeId: '', // 看房方式
          resourceTypeId: '', // 来源
          payTypeId: '', // 付款方式
          tradingRightsId: '', // 交易权属
          isUploadCertificate: '', // 房本备件
          mortgageId: '', // 抵押信息
          remark: '', // 备注
          rentPrice: '', // 租价
          rentPriceTypeId: '', // 租价
          sellingPrice: '', // 售价
          buildSpace: '', // 建筑面积
          useSpace: '', // 使用面积
          buildDates: '', // 建造年代
          entrustCode: '', // 委托编号
          percentage: '', // 首付
          minRentPrice: '', // 出租低价
          minSellingPrice: '', // 出售低价
          lastTradingTime: '', // 上次交易日期
          houselabelList: [], // 标签
          verificationCode: '', // 房源核验码
          // floorCount: '', // 楼层
          // totalLayers: '', // 总层数
          // elevatorCount: '', // 电梯数
          // householdCount: '', // 同楼层户数
          heatingModeId: '', // 供暖方式

          room: '', // 室
          hall: '', // 厅
          toilet: '', // 卫
          kitchen: '', // 厨
          balcony: '', // 阳台
        },
        rules:{
          titles: {required: true, message: '该项为必填项', trigger: 'blur'},
          remark: {required: true, message: '该项为必填项', trigger: 'blur'},
          room: {required: true, message: '该项为必填项', trigger: 'blur'},
          hall: {required: true, message: '该项为必填项', trigger: 'blur'},
          toilet: {required: true, message: '该项为必填项', trigger: 'blur'},
          kitchen: {required: true, message: '该项为必填项', trigger: 'blur'},
          balcony: {required: true, message: '该项为必填项', trigger: 'blur'},
          householdCount: {required: true, message: '该项为必填项', trigger: 'blur'},
          elevatorCount: {required: true, message: '该项为必填项', trigger: 'blur'},
          floorCount: {required: true, message: '该项为必填项', trigger: 'blur'},
          totalLayers: {required: true, message: '该项为必填项', trigger: 'blur'},

          labeld: {required: true, message: '该项为必填项', trigger: 'change'},
          transactionTypeId: {required: true, message: '该项为必填项', trigger: 'change'},
          decorationId: {required: true, message: '该项为必填项', trigger: 'change'},
          actuality: {required: true, message: '该项为必填项', trigger: 'change'},
          houseTypeId: {required: true, message: '该项为必填项', trigger: 'change'},
          structureId: {required: true, message: '该项为必填项', trigger: 'change'},
          propertyTypeId: {required: true, message: '该项为必填项', trigger: 'change'},
          propRightsLenId: {required: true, message: '该项为必填项', trigger: 'change'},
          certificateDate: {required: true, message: '该项为必填项', trigger: 'change'},
          viewingTypeId: {required: true, message: '该项为必填项', trigger: 'change'},
          resourceTypeId: {required: true, message: '该项为必填项', trigger: 'change'},
          rentPrice: {required: true, message: '该项为必填项', trigger: 'blur'},
          rentPriceTypeId: {required: true, message: '该项为必填项', trigger: 'change'},
          buildSpace: {required: true, message: '该项为必填项', trigger: 'change'},
          useSpace: {required: true, message: '该项为必填项', trigger: 'change'},
          buildDates: {required: true, message: '该项为必填项', trigger: 'change'},
          lastTradingTime: {required: true, message: '该项为必填项', trigger: 'change'},
        },
        selectOpts: null,
        selectProps: {
          label: 'fieldValue', // 指定选项的值绑定为下拉框的label属性
          value: 'fieldCode' // 指定选项的值绑定为下拉框的Value属性
        },
        labels: [],
        loadingView: false,
        loadingSubmitBtn: false
      }
    },
    methods: {
      handleSubmit(){
        this.$refs['detailEditForm'].validate(valid=>{

          if(valid){
            this.$confirm('确定保存数据吗？','提示',{
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(()=>{
              this.loadingSubmitBtn = true
              let params = {}
              params.dataJson = JSON.stringify(this._formatParams(this.detailEditForm))
              params.formName = 'villa'
              updateHousing(params).then(res => {
                this.loadingSubmitBtn = false
                this.$message({
                  type: 'success',
                  message: res.msg || '操作成功',
                  showClose: true
                })
                this.$emit('hancleClick', 1)
              })
            })
          }
        })
      },

      handleReset () {
        this.$confirm('确定重置已经填写的数据吗？','提示',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(()=>{
          this._resetForm()
        })
      },

      // 取消操作
      handleCancel(){
        this.$confirm('确定关闭正在编辑的窗口？','提示',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(()=>{
          this.$emit('handleClick',2)
        })
      },

      _getOptions () {
        this.loadingView = true

        return new Promise(resolve => {
          getBaseInfoSelectOpts({param: 'house'}).then(res => {
            this.selectOpts = res.data || []

            this.selectOpts.transactionTypeId = transactionTypeId // 交易类型
            this.selectOpts.payTypeId = payTypeId // 付款方式
            this.selectOpts.mortgageId = mortgageId // 抵押信息
            this.selectOpts.isUploadCertificate = isUploadCertificate // 房本备件
            this.selectOpts.heatingModeId = heatingModeId // 供暖方式
            this.selectOpts.tradingRightsId = tradingRightsId // 交易权属

            resolve()
          })
        })
      },

      // 设置表单数据
      _setForm(){
        this._resetForm()

        let data = {...this.customerInfo}
        let form = this.detailEditForm

        Object.keys(form).forEach(key => {
          if (data[key] !== 'undefined' && data[key] !== null) {

            // 转成字符 下拉匹配
            if (typeof data[key] === 'number') {
              data[key] = String(data[key])
            }

            if (key === 'isSynchron') {
              data[key] === 0 ? data[key] = false : data[key] = true
            }

            // if (key === 'certificateDate') {
            //   data[key] = this.$utils.timeFormat(data[key], '{y}-{m}-{d}')
            // }
            //
            // if (key === 'lastTradingTime') {
            //   data[key] = this.$utils.timeFormat(data[key], '{y}-{m}-{d}')
            // }

            // 获取标签值
            if (key === 'labeld') {
              let labels = []
              if (data[key] !== ('' || undefined)) {
                labels = data[key].split()
              } else {
                labels = []
              }
              let labelIds = []
              labels.forEach((label) => {
                this.selectOpts.labeld.find(labelItem => {
                  if (label.indexOf(labelItem.fieldValue) !== -1) {
                    labelIds.push(labelItem.fieldCode)
                  }
                })
              })
              data[key] = labelIds
            }

            // 获取配套
            if (key === 'matching') {
              let labels = []
              if (data[key] !== ('' || undefined)) {
                labels = data[key].split()
              } else {
                labels = []
              }

              let matching = []
              labels.forEach((label) => {
                this.selectOpts.matchingIds.find(labelItem => {
                  if (label.indexOf(labelItem.fieldValue) !== -1) {
                    matching.push(labelItem.fieldCode)
                  }
                })
              })
              data[key] = matching
            }

            // 获取现状
            if (key === 'actuality') {
              let labels = []
              if (data['actuality'] !== ('' || undefined)) {
                labels = data['actuality'].split()
              } else {
                labels = []
              }
              let actuality = []
              labels.forEach((label) => {
                this.selectOpts.actualityId.find(labelItem => {
                  if (label.indexOf(labelItem.fieldValue) !== -1) {
                    actuality.push(labelItem.fieldCode)
                  }
                })
              })
              data[key] = actuality
            }

            form[key] = data[key] || ''
            form.houselabelList = data['houselabelList'] || []
          }
        })
      },

      _resetForm () {
        this.$refs['detailEditForm'] && this.$refs['detailEditForm'].resetFields();
      },

      // 处理参数
      _formatParams (params) {
        let _params = {...params}

        let labeld = _params.labeld || []
        let actualityId = _params.actuality || []
        let matchingIds = _params.matching || []

        let labels = []
        let actuality = []
        let matching = []

        labeld.forEach(item => {
          this.selectOpts.labeld.find(labelItem => {
            if (labelItem.fieldCode === item) {
              labels.push(labelItem.fieldValue)
            }
          })
        })

        actualityId.forEach(item => {
          this.selectOpts.actualityId.find(labelItem => {
            if (labelItem.fieldCode === item) {
              actuality.push(labelItem.fieldValue)
            }
          })
        })

        matchingIds.forEach(item => {
          this.selectOpts.matchingIds.find(labelItem => {
            if (labelItem.fieldCode === item) {
              matching.push(labelItem.fieldValue)
            }
          })
        })

        _params.labeld = labels.join()
        _params.actuality = actuality.join()
        _params.matching = matching.join()
        _params.transactionType = this._getOptName('transactionTypeId', _params.transactionTypeId)
        _params.rentPriceTypeName = this._getOptName('transactionTypeId', _params.transactionTypeId)
        _params.orientation = this._getOptName('orientationId', _params.orientationId)
        _params.decoration = this._getOptName('decorationId', _params.decorationId)
        _params.houseType = this._getOptName('houseTypeId', _params.houseTypeId)
        _params.structure = this._getOptName('structureId', _params.structureId)
        _params.propertyType = this._getOptName('propertyTypeId', _params.propertyTypeId)
        _params.housingYears = this._getOptName('housingYearsId', _params.housingYearsId)
        _params.viewingType = this._getOptName('viewingTypeId', _params.viewingTypeId)
        _params.resourceType = this._getOptName('resourceTypeId', _params.resourceTypeId)
        _params.payType = this._getOptName('payTypeId', _params.payTypeId)
        _params.mortgageName = this._getOptName('mortgageId', _params.mortgageId)
        _params.heatingModeName = this._getOptName('heatingModeId', _params.heatingModeId)
        _params.tradingRightsName = this._getOptName('tradingRightsId', _params.tradingRightsId)

        let labelList =[]
        _params.houselabelList.forEach(item => {
          this.labels.forEach(label => {
            if (item === label.code) {
              labelList.push({
                code: label.code,
                name: label.name,
                color: label.color,
              })
            }
          })
        })

        if (labelList.length) {
          _params.houselabelList = labelList
        } else {
          delete _params.houselabelList
        }

        _params.lastTradingTime = this.$utils.timestampToTime(_params.lastTradingTime)
        _params.certificateDate = this.$utils.timestampToTime(_params.certificateDate)

        _params.isSynchron ? _params.isSynchron = 1 : _params.isSynchron = 0

        Object.keys(_params).forEach(key => {
          if (_params[key] === '') {
            delete _params[key]
          }
        })
        return _params
      },

      _getOptName (key, value) {
        let name = ''
        this.selectOpts[key].forEach(item => {
          if (item.fieldCode === value){
            name = item.fieldValue
          }
        })
        return name
      }
    },

    mounted(){
      this._getOptions().then(() => {

        getHouseLabel().then(res => {
          this.labels = res

          this._setForm()
          this.loadingView = false

        })
      })

    }
  }
</script>

<style scoped>
  .form-wrapper {
    max-height: 520px;
    height: 520px;
    overflow-y: auto;
    overflow-x: hidden;
  }

  .select-con {
    /*height: 52px;*/
  }
  .el-input {
    width:110px;
  }
</style>
