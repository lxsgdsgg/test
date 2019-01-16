/*******************************************************************************************************/
/** *************************************** 新增房源 基本信息 mixin***************************************/
/*******************************************************************************************************/

import {getBaseInfoSelectOpts, getHouseLabel, updateHousing,getSomeEnum} from '@/request/customer/customerUsed'
import {getCustomField} from '@/request/app'
import BaseSelect from '@/components/BaseSelect'
import BaseCascader from '@/components/BaseCascader'
import {getCommunitySelectUrl} from '@/request/manage/common'
const relateTypeId = [

  {
    fieldValue: '先生',
    fieldCode: '1'
  },
  {
    fieldValue: '女士',
    fieldCode: '2'
  },
  {
    fieldValue: '公司',
    fieldCode: '3'
  }
]
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

const rentPriceTypeId = [
  {
    fieldValue: '元/月',
    fieldCode: '1'
  },
  {
    fieldValue: '元/季度',
    fieldCode: '2'
  },
  {
    fieldValue: '元/年',
    fieldCode: '3'
  },
  {
    fieldValue: '元/月',
    fieldCode: '4'
  },
  {
    fieldValue: '元/平/月',
    fieldCode: '5'
  }
]

export default {
  props:{
    formInfo:{
      type: Object,
      default () {
        return null
      }
    },
    isAdd: {
      type: Boolean,
      default () {
        return true
      }
    },
    type: {
      type: String,
      default: 'house' // 房源类型
    }
  },
  components: {BaseSelect,BaseCascader},
  data() {
    return {
      getCommunitySelectUrl: getCommunitySelectUrl,
      cascaderDataProps: {id: 'id', parent: 'regionId'}, // 级联数据源配置选项
      cascaderProps: { // 级联下拉组件配置选项
        value: 'id', // 指定选项的值为选项对象的某个属性值
        label: 'name' // 指定选项标签为选项对象的某个属性值
      },
      detailEditForm :{
        id: '',
        demandId: '',
        custName: '',
        relateTypeId: '',
        titles: '', // 房源标题
        labeld: [], // 推荐标签
        isSynchron: false, // 外网同步
        transactionTypeId: '', // 交易类型
        decorationId: '', // 装修
        orientationId: [], //朝向
        matching: '', // 配套
        matchingIds: [], // 配套ID
        actuality: '', // 现状
        actualityId: [], // 现状ID
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
        rentPriceTypeId: '1', // 租价类型
        sellingPrice: '', // 售价
        buildSpace: '', // 建筑面积
        useSpace: '', // 使用面积
        buildDates: '', // 建造年代
        entrustCode: '', // 委托编号
        percentage: '', // 首付
        minRentPrice: '', // 出租低价
        minSellingPrice: '', // 出售低价
        lastTradingTime: '', // 上次交易日期
        houseLabelList: [], // 标签
        verificationCode: '', // 房源核验码
        heatingModeId: '', // 供暖方式

        floorCount: '', // 楼层
        totalLayers: '', // 总层数
        elevatorCount: '', // 电梯数
        householdCount: '', // 同楼层户数

        room: '', // 室
        hall: '', // 厅
        toilet: '', // 卫
        kitchen: '', // 厨
        balcony: '' ,// 阳台
        communicateStageId: '', //沟通阶段
        consumptIdeaId: [], //消费理念
        censusRegisterId: '', //国籍
        nationsId: '',  //名族
        vehicleId: '' ,//交通工具
        houseUsesIds: [],  //房屋用途
        reason: ''
      },
      customForm: {},
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
        sellingPrice: {required: true, message: '该项为必填项', trigger: 'blur'},

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
      selectPropsOther: {
        label: 'fieldValue', // 指定选项的值绑定为下拉框的label属性
        value: 'id' // 指定选项的值绑定为下拉框的Value属性
      },
      selectPropsLabel: {
        label: 'fieldValue', // 指定选项的值绑定为下拉框的label属性
        value: 'fieldValue' // 指定选项的值绑定为下拉框的Value属性
      },
      labels: [],
      customFields: [], // 自定义动态字段
      loadingView: false,
      loadingSubmitBtn: false,
      houseUsesIds: []
    }
  },
  methods: {
    getSomeEnum(){
      getSomeEnum().then(res => {
        console.log(res.data)
        Object.keys(res.data.houseUses).forEach(key => {
            let item = {
              id: key,
              fieldValue: res.data.houseUses[key]  
            }
            this.houseUsesIds.push(item)
        })
      }).catch(() => {
        
      })

    },
    handleSubmit(){
      this.$refs['detailEditForm'].validate(valid=>{

        this.$refs['customForm'].validate(customValid=>{

          if(valid && customValid){
            this.$confirm('确定保存数据吗？',{
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(()=>{

              this.loadingSubmitBtn = true

              if (this.isAdd) {
                // 新增把参数传回给父组件保存
                this.$emit('handleClick', 1, this._formatParams(this.detailEditForm))
                this.loadingSubmitBtn = false

              } else {
                let params = {}
                params.dataJson = JSON.stringify(this._formatParams(this.detailEditForm))
                // 修改
                params.formName = this.type
                updateHousing(params).then(res => {
                  this.loadingSubmitBtn = false
                  this.$message({
                    type: 'success',
                    message: res.msg || '操作成功',
                    showClose: true
                  })
                  this.$emit('handleClick', 1)
                }).catch(() => {
                  this.loadingSubmitBtn = false
                })
              }

            })
          }

        })

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
        this.$emit('handleClick', 2)
      })
    },

    _getOptions () {
      getBaseInfoSelectOpts({param: 'house_customer'}).then(res => {
        this.selectOpts = res.data || []
        console.log(res.data)
        // 前端固定写死的下拉数据
        this.selectOpts.transactionTypeId = transactionTypeId // 交易类型
        this.selectOpts.payTypeId = payTypeId // 付款方式
        this.selectOpts.mortgageId = mortgageId // 抵押信息
        this.selectOpts.isUploadCertificate = isUploadCertificate // 房本备件
        this.selectOpts.heatingModeId = heatingModeId // 供暖方式
        this.selectOpts.tradingRightsId = tradingRightsId // 交易权属
        this.selectOpts.rentPriceTypeId = rentPriceTypeId // 租价类型
        this.selectOpts.relateTypeId = relateTypeId //客户称呼
        //枚举数据
        this.selectOpts.houseUsesIds = this.houseUsesIds //房屋用途
      })
    },

    // 设置表单数据
    _setForm(){

      this._resetForm()

      let data = {...this.formInfo.baseInfo}
      let form = this.detailEditForm
      if (this.isAdd) {
        // 赋值楼层 梯户等信息
        data.floorCount ? form.floorCount = data.floorCount || '' : ''
        data.totalLayers ? form.totalLayers = data.totalLayers || '' : ''
        data.elevatorCount ? form.elevatorCount = data.elevatorCount || '' : ''
        data.householdCount ? form.householdCount = data.householdCount || '' : ''
        return false
      }

      let customInfo = {...this.formInfo.customInfo}
      //let houseLabels = this.formInfo.houseLabel
      let houseLabels = [];
      Object.keys(form).forEach(key => {
        if (data[key] !== undefined && data[key] !== null) {

          // 转成字符 下拉匹配
          if (typeof data[key] === 'number') {
            data[key] = String(data[key])
          }

          if (key === 'isSynchron') {
            data[key] === 0 ? data[key] = false : data[key] = true
          }

          // 匹配推荐标签值
          if (key === 'labeld') {
            data[key] = data[key].split(',')
          }

          // 匹配配套值
          if (key === 'matchingIds') {
            data[key] = data[key].split(',')
          }

          // 匹配现状值
          if (key === 'actualityId') {
            data[key] = data[key].split(',')
          }

          if (data[key]) {
            form[key] = data[key]
          }
        }
      })

      // 匹配自定义表单值
      Object.keys(this.customForm).forEach(key => {
        if (customInfo.customValue && customInfo.customValue[key] !== undefined && customInfo.customValue[key] !== null) {
          this.customForm[key] = customInfo.customValue[key]
        }
      })


      // 匹配标签复选框组
      houseLabels.forEach(item => {
        this.detailEditForm.houseLabelList.push(item['labelName'])
      })
    },

    _resetForm () {
      this.$refs['detailEditForm'] && this.$refs['detailEditForm'].resetFields();
      this.$refs['customForm'] && this.$refs['customForm'].resetFields();
    },

    // 处理参数
    _formatParams (params) {
      let _params = {...params}

      let actualityId = _params.actualityId || []
      let matchingIds = _params.matchingIds || []

      let actuality = []
      let matching = []

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

      _params.labeld = _params.labeld.join() // 推荐标签
      _params.actuality = actuality.join() // 现状name
      _params.actualityId = actualityId.join() // 现状Ids
      _params.matching = matching.join() // 配套name
      _params.matchingIds = matchingIds.join() // 配套Ids
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
      _params.houseLabelList.forEach(item => {
        this.labels.forEach(label => {
          if (item === label.name) {
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
      }

      delete _params.houseLabelList

      _params.lastTradingTime = this.$utils.timestampToTime(_params.lastTradingTime)
      _params.certificateDate = this.$utils.timestampToTime(_params.certificateDate)

      _params.isSynchron ? _params.isSynchron = '1' : _params.isSynchron = '0'

      // 自定义表单字段
      _params.customData = this.customForm

      Object.keys(_params).forEach(key => {
        if (_params[key] === '') {
          delete _params[key]
        }
      })

      return _params
    },

    _getOptName (key, value) {
      let name = ''

      if (value) {
        this.selectOpts[key].forEach(item => {
          if (item.fieldCode === value){
            name = item.fieldValue
          }
        })
      }

      return name
    },
    onChange(val, name, data){
      console.log("1111")
      console.log(data)
    },

    async _fetchData () {
      this.loadingView = true

      // 获取下拉框数据
      await this._getOptions()

      // 获取标签数据
      await getHouseLabel().then(res => {
        this.labels = res
      })

      // 获取自定义动态表单字段
      await getCustomField({formName: this.type}).then(res => {
        let _arr = res.data

        _arr.forEach(item => {

          // 绑定form model
          this.$set(this.customForm, item.field, '')
          // 处理自定义下拉
          if (item.fieldType === '3') {
            let values = []
            item.value.split(',').forEach(val => {
              values.push({
                fieldCode: val,
                fieldValue: val
              })
            })
            item.value = values
          }
        })
        this.customFields = _arr
      })

      this._setForm()

      this.loadingView = false

    }
  },

  mounted(){
    this._fetchData()
    this.getSomeEnum()
  }
}
