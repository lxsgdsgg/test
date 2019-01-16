/*******************************************************************************************************/
/** *************************************** 新增房源 基本信息 mixin***************************************/
/*******************************************************************************************************/

import CommunitySelector from '@/components/BaseCascader/index'//小区选着
import {queryCommunitySelectWithRegion,getRegionValue} from '@/request/app'
import {getBaseInfoSelectOpts, getHouseLabel, saveExchangeCustomerInfo,getSomeEnum } from "@/request/customer/customerUsed";
import {getCustomField} from '@/request/app'
import BaseSelect from '@/components/BaseSelect'
import BaseSectionSelect from '@/components/BaseSectionSelect'

// 楼层 区间数据
const floorRegionValue = {
  min: [
    '0',
    '1',
    '2',
    '3',
    '4',
    '5',
    '6',
    '7'
  ],
  max: [
    '3',
    '4',
    '5',
    '6',
    '7',
    '8',
    '9',
    '不限'
  ]
}

// 房龄 区间数据
const buildDateRegionValue = {
  min: [
    '0',
    '2005',
    '2006',
    '2007',
    '2008',
    '2009',
    '2010',
    '2011'
  ],
  max: [
    '2007',
    '2008',
    '2009',
    '2010',
    '2011',
    '2012',
    '2013',
    '不限'
  ]
}

export const custCallType = [
  {
    fieldCode: '1',
    fieldValue: '先生'
  },
  {
    fieldCode: '2',
    fieldValue: '女士'
  },
  {
    fieldCode: '3',
    fieldValue: '公司'
  }
]
//标签
export const demandLabel = [
  {
    id: 'one',
    name: '一手'
  },
  {
    id: 'two',
    name: '二手'
  },
]
export const statusLabel = [
  {
    id: 'isUrgent',
    name: '急切'
  },
  {
    id: 'isFullAmount',
    name: '全款'
  },
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
      default: 'house_customer' // 房源类型
    }
  },
  components: {BaseSelect,CommunitySelector,BaseSectionSelect},
  data() {
    return {
      detailEditForm :{
        custName:'',//客户姓名
        labeld: [],//标签
        relateTypeId:'',//客户称呼类型
        areaCodes:'',//行政区划
        areaNames:'',
        houseUsesIds:[],//房屋用途
        houseUses:[],//房屋用途
        minRoom:'',//需求户型
        maxRoom:'',//需求户型
        minPrice:'',//需求价格
        maxPrice:'',//需求价格
        minSpace:'',//需求面积
        maxSpace:'',//需求面积
        orientationId:[],//朝向Id
        orientation:[],//朝向
        minFloorCount:'',//需求楼层
        maxFloorCount:'',//需求楼层
        minBuildDates:'',//需求房龄
        maxBuildDates:'',//需求房龄
        decorationIds:[],//装修Id
        decoration:[],//装修
        matchingIds:[],//配套Id
        matching: [],//配套
        reason:'',//需求原因
        houseTypeIds:[],//房屋类型
        houseType:[],//房屋类型
        housePropType:[],//房源属性：一手，二手
        communicateStageId:'',//沟通阶段Id
        communicateStage:'',//沟通阶段
        sourceTypeId:'',//客户来源Id
        sourceType:'',//客户来源
        consumptIdeaId:[],//消费理念Id
        consumptIdeaName:[],//消费理念,
        censusRegisterId:'',//国籍
        nationsId:'',//民族
        IDCard:'',//证件号
        email:'',//邮箱
        qqNum:'',//qq
        wxNum:'',//微信
        vehicleId:'',//交通工具
        contactAddress:'',//联系地址
        levelType:'',//客源等级
        remark:'',//备注
        isFullAmount:'',//是否全款
        isUrgent:'',//是否急切
        room:'',//室
        space:'',//面积
        price:'',//价格
        floorCount:'',//楼层
        buildDates:'',//房龄

      },
      room:'',//室
      space:'',//面积
      price:'',//价格
      floorCount:'',//楼层
      buildDates:'',//房龄
      houseUsesIds:[],
      customForm: {},
      rules:{
        custName: {required: true, message: '该项为必填项', trigger: 'blur'},
        custCallType: {required: true, message: '该项为必填项', trigger: 'blur'},
        areaCodes: {required: true, message: '该项为必填项', trigger: 'blur'},
        houseUsesIds: {required: true, message: '该项为必填项', trigger: 'change'},
        room: {required: true, message: '该项为必填项', trigger: 'change'},
        price: {required: true, message: '该项为必填项', trigger: 'change'},
        space: {required: true, message: '该项为必填项', trigger: 'change'},
        orientationId: {required: true, message: '该项为必填项', trigger: 'change'},
        floorCount: {required: true, message: '该项为必填项', trigger: 'change'},
        buildDates: {required: true, message: '该项为必填项', trigger: 'change'},
        decorationIds: {required: true, message: '该项为必填项', trigger: 'blur'},
        sourceTypeId: {required: true, message: '该项为必填项', trigger: 'change'},
        communicateStageId: {required: true, message: '该项为必填项', trigger: 'change'},
        remark: {required: true, message: '该项为必填项', trigger: 'change'},
      },
      selectOpts: null,
      selectProps: {
        label: 'fieldValue', // 指定选项的值绑定为下拉框的label属性
        value: 'fieldCode' // 指定选项的值绑定为下拉框的Value属性
      },
      //指定属性字段
      selectPropsOther:{
        label: 'fieldValue', // 指定选项的值绑定为下拉框的label属性
        value: 'id' // 指定选项的值绑定为下拉框的Value属性
      },
      selectPropsLabel: {
        label: 'fieldValue', // 指定选项的值绑定为下拉框的label属性
        value: 'fieldValue' // 指定选项的值绑定为下拉框的Value属性
      },
      areasUrl: queryCommunitySelectWithRegion,
      cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
      cascaderProps: { // 级联下拉组件配置选项
        value: 'code', // 指定选项的值为选项对象的某个属性值
        label: 'name' // 指定选项标签为选项对象的某个属性值
      },
      regionValue: {
        room:{},
        price:{},
        space: {},
        buildDates:{},
        floorCount:{},
        buildingYears:{}
      },
      labels: [],
      labelsStatus:[],
      customFields: [], // 自定义动态字段
      loadingView: false,
      loadingSubmitBtn: false
    }
  },
  methods: {
    handleSubmit(){

      this.$refs['detailEditForm'].validate(valid=>{

        if(valid){
          this.$confirm('确定保存数据吗？',{
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(()=>{
            //获取区间下拉的范围值mix-min
            this.loadingSubmitBtn = true
            this.detailEditForm.minRoom = this.detailEditForm.room.min
            this.detailEditForm.maxRoom = this.detailEditForm.room.max
            this.detailEditForm.minPrice = this.detailEditForm.price.min
            this.detailEditForm.maxPrice = this.detailEditForm.price.max
            this.detailEditForm.minSpace = this.detailEditForm.space.min
            this.detailEditForm.maxSpace = this.detailEditForm.space.max
            this.detailEditForm.minFloorCount = this.detailEditForm.floorCount.min
            this.detailEditForm.maxFloorCount = this.detailEditForm.floorCount.max
            this.detailEditForm.minBuildDates = this.detailEditForm.buildDates.min
            this.detailEditForm.maxBuildDates = this.detailEditForm.buildDates.max

            if (this.isAdd) {
              // 新增把参数传回给父组件保存
              this.$emit('handleClick', 1, this._formatParams(this.detailEditForm))
              this.loadingSubmitBtn = false

            } else { // 修改
              let params = {}
              this.detailEditForm.id = this.detailEditForm.demandId
              delete this.detailEditForm.demandId
              params.dataJson = JSON.stringify(this._formatParams(this.detailEditForm))
              
              //租售转换
              if(this.formInfo.firstInfo){
                params.discStatusId = this.formInfo.firstInfo.discStatusId
                params.discStatus = this.formInfo.firstInfo.discStatus
                params.isSendBindCode = this.formInfo.firstInfo.isSendBindCode
                params.phone = this.formInfo.firstInfo.phone
                params.relateType = this.formInfo.firstInfo.relateType
                params.relateName = this.formInfo.firstInfo.relateName //电话类型
                params.relateTypeName = this.formInfo.firstInfo.relateTypeName //用户称呼
                if(this.formInfo.firstInfo.transactionTypeId==4){
                  params.transactionTypeId=5
                  params.transactionType = '求租'
                }else if(this.formInfo.firstInfo.transactionTypeId==5){
                  params.transactionTypeId=4
                  params.transactionType = '求购'
                }
              }
              
              params.formName = this.type
              saveExchangeCustomerInfo(params).then(res => {
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
          }).catch(error =>{
            this.$message({
              type: 'success',
              message: '已取消',
              showClose: true
            })
             this.loadingSubmitBtn = false
          })
        }else{
          this.loadingSubmitBtn = false
        }
      })
    },
    //获取区间数据
    _getRegionValue(){
      const priceParams = {
        widgetCode: 'custSellPriceRange',
        tag: 2
      }
      getRegionValue(priceParams).then(res => {
        this.regionValue.price = res.data || []
      })
      const spaceParams = {
        widgetCode: 'CustSpaceRange',
        tag: 2
      }
      getRegionValue(spaceParams).then(res => {
        this.regionValue.space = res.data || []
      })
      const buildingYearsParams = {
        widgetCode: 'buildingYearsRange',
        tag: 2
      }
      getRegionValue(buildingYearsParams).then(res => {
        this.regionValue.buildingYears = res.data || []
      })
      const roomParams = {
        widgetCode: 'roomRange',
        tag: 2
      }
      getRegionValue(roomParams).then(res => {
        this.regionValue.room = res.data || []
      })
      const floorParams = {
        widgetCode: 'floorRange',
        tag: 2
      }
      getRegionValue(floorParams).then(res => {
        this.regionValue.floor = res.data || []
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
    //获取行政区划的名称
    handleChange(value,name){
      this.detailEditForm.areaNames = name
    },
    //获取房屋用途枚举
    getSomeEnum(){
      getSomeEnum().then(res =>{
        console.log(res)
        Object.keys(res.data.houseUses).forEach(key =>{
          let item ={
            id:key,
            fieldValue:res.data.houseUses[key]
          }
          this.houseUsesIds.push(item)
        })
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
        this._resetForm()
      })
    },
    //上一步
    handleLastStep(){
        this.$emit('handleClick', 3)
    },
    _getOptions () {
      getBaseInfoSelectOpts({param: 'house_customer'}).then(res => {
        this.selectOpts = res.data || []
        this.selectOpts.custCallType = custCallType //称呼类型
        this.selectOpts.houseUsesIds = this.houseUsesIds//房屋用途
      })
    },

    // 设置表单数据
    _setForm(){
      this.labels = demandLabel
      this.labelsStatus = statusLabel
      this._resetForm()

      let data = {...this.formInfo.baseInfo}
      let form = this.detailEditForm

      if (this.isAdd) {


        //客户名称、电话、等信息
        form.custName = data.custName
        form.relateTypeId = String(data.relateTypeId)
        return false
      }

      let customInfo = {...this.formInfo.customInfo}
      //let houseLabels = this.formInfo.houseLabel

      Object.keys(form).forEach(key => {
        if (data[key] !== undefined && data[key] !== null) {

          // 转成字符 下拉匹配
          if (typeof key === 'number') {
            data[key] = String(data[key])
          }

          // if (key === 'isSynchron') {
          //   data[key] === 0 ? data[key] = false : data[key] = true
          // }

          // 匹配推荐标签值
          if (key === 'labeld') {
            data[key] = data[key].split(',')
          }
          // 匹配房屋用途
          if (key === 'houseUsesIds') {
            data[key] = data[key].split(',')
          }

          // 匹配配套值
          if (key === 'matchingIds') {
            data[key] = data[key].split(',')
          }
          // 匹配装修
          if (key === 'decorationIds') {
            data[key] = data[key].split(',').map(item => Number(item))
          }

          //匹配朝向
          if (key === 'orientationId') {
            data[key] = data[key].split(',').map(item => Number(item))
          }

          //匹配房屋类型
          if (key === 'consumptIdeaId') {
            data[key] = data[key].split(',').map(item => Number(item))
          }

          //匹配消防理念
          if (key === 'houseTypeIds') {
            data[key] = data[key].split(',').map(item => Number(item))
          }

          if (data[key]) {
            form[key] = data[key]
          }


        }
      })
      this.detailEditForm.room = {
        min: data.minRoom,
        max: data.maxRoom
      }
      this.detailEditForm.price = {
        min: data.minPrice,
        max: data.maxPrice
      }
      this.detailEditForm.space = {
        min: data.minSpace,
        max: data.maxSpace
      }
      this.detailEditForm.floorCount = {
        min: data.minFloorCount,
        max: data.maxFloorCount
      }
      this.detailEditForm.buildDates = {
        min: data.minBuildDates,
        max: data.maxBuildDates
      }

      // this.detailEditForm.price.min = data.minPrice
      // this.detailEditForm.price.max = data.maxPrice
      // this.detailEditForm.space.min = data.minSpace
      // this.detailEditForm.space.max  = data.maxSpace
      // this.detailEditForm.floorCount.min = data.minFloorCount
      // this.detailEditForm.floorCount.max = data.maxFloorCount
      // this.detailEditForm.buildDates.min = data.minBuildDates
      // this.detailEditForm.buildDates.max = data.maxBuildDates

      // 匹配自定义表单值
       Object.keys(this.customForm).forEach(key => {
         if (customInfo.customValue && customInfo.customValue[key] !== undefined && customInfo.customValue[key] !== null) {
          this.customForm[key] = customInfo.customValue[key]
        }
      })


      // 匹配标签复选框组
      // houseLabels.forEach(item => {
      //   this.detailEditForm.houseLabelList.push(item['labelName'])
      // })
    },

    _resetForm () {
      this.$refs['detailEditForm'] && this.$refs['detailEditForm'].resetFields();
      this.$refs['customForm'] && this.$refs['customForm'].resetFields();
    },
    // 处理参数
    _formatParams (params) {
      console.log(params)
      let _params = {...params}
      let actualityId = _params.actualityId || []
      let matchingIds = _params.matchingIds || []
      let houseUsesIds = _params.houseUsesIds || []
      let houseTypeIds = _params.houseTypeIds || []
      let decorationIds = _params.decorationIds || []
      let consumptIdeaId = _params.consumptIdeaId || []
      let orientationId = _params.orientationId || []

      let actuality = []
      let matching = []
      let houseUses =[]
      let houseType =[]
      let decoration =[]
      let consumptIdeaName =[]
      let orientation =[]
      actualityId.forEach(item => {
        this.selectOpts.actualityId.find(labelItem => {
          if (labelItem.fieldCode === item) {
            actuality.push(labelItem.fieldValue)
          }
        })
      })
      //房屋配套
      matchingIds.forEach(item => {
        this.selectOpts.matchingIds.find(labelItem => {
          if (labelItem.id === item) {
            matching.push(labelItem.fieldValue)
          }
        })
      })
      //房屋用途
      houseUsesIds.forEach(item => {
        this.selectOpts.houseUsesIds.find(labelItem => {
          if (labelItem.id === item) {
            houseUses.push(labelItem.fieldValue)
          }
        })
      })
      //房屋类型
      houseTypeIds.forEach(item => {
        this.selectOpts.houseTypeIds.find(labelItem => {
          if (labelItem.id === item) {
            houseType.push(labelItem.fieldValue)
          }
        })
      })
      // houseTypeIds.forEach(item => {
      //   this.selectOpts.houseTypeId.find(labelItem => {
      //     if (labelItem.fieldCode === item) {
      //       houseType.push(labelItem.fieldValue)
      //     }
      //   })
      // })
      //装修
      decorationIds.forEach(item => {
        console.log(this.selectOpts)

        this.selectOpts.decorationIds.find(labelItem => {
          if (labelItem.id === item) {
            decoration.push(labelItem.fieldValue)
          }
        })
      })
      //消费理念
      consumptIdeaId.forEach(item => {
        this.selectOpts.consumptIdeaId.find(labelItem => {
          if (labelItem.id === item) {
            consumptIdeaName.push(labelItem.fieldValue)
          }
        })
      })
      //朝向
      orientationId.forEach(item => {
        this.selectOpts.orientationId.find(labelItem => {
          if (labelItem.id === item) {
            orientation.push(labelItem.fieldValue)
          }
        })
      })
      _params.labeld = _params.labeld.join() // 推荐标签
      _params.actualityId = actualityId.join() // 现状Ids
      _params.matching = matching.join() // 配套name
      _params.matchingIds = matchingIds.join() // 配套Ids
      _params.houseUsesIds = houseUsesIds.join() // 房屋用途Id
      _params.houseUses = houseUses.join() // 房屋用途
      _params.houseTypeIds = houseTypeIds.join() // 房屋用途
      _params.houseType = houseType.join() // 房屋用途
      _params.decoration = decoration.join() // 装修
      _params.decorationIds = decorationIds.join() // 装修ID
      _params.consumptIdeaName = consumptIdeaName.join() // 消费理念
      _params.consumptIdeaId = consumptIdeaId.join() // 消费理念
      _params.orientation = orientation.join() // 朝向
      _params.orientationId = orientationId.join() // 朝向Id

      _params.housePropType = _params.housePropType.join()
      //获取中文?
      _params.sourceType = this._getOptName('resourceTypeId', _params.sourceTypeId) //来源
      _params.communicateStage = this._getOptName('communicateStageId', _params.communicateStageId) //沟通阶段
      // _params.decoration = this._getOptName('decorationId', _params.decorationIds)
      _params.vehicle = this._getOptName('vehicleId', _params.vehicleId) //交通工具
      // _params.consumptIdeaName = this._getOptName('consumptIdeaId', _params.consumptIdeaId)
      _params.censusRegister = this._getOptName('censusRegisterId', _params.censusRegisterId) //国籍
      _params.nations = this._getOptName('nationsId', _params.nationsId) //名族
      // let labelList =[]
      // _params.houseLabelList.forEach(item => {
      //   this.labels.forEach(label => {
      //     if (item === label.name) {
      //       labelList.push({
      //         code: label.code,
      //         name: label.name,
      //         color: label.color,
      //       })
      //     }
      //   })
      // })

      // if (labelList.length) {
      //   _params.houselabelList = labelList
      // }

      delete _params.houseLabelList

      // 自定义表单字段
      _params.customData = this.customForm

      Object.keys(_params).forEach(key => {
        if (_params[key] === '') {
          delete _params[key]
        }
      })

      return _params
    },

    _getOptName (selectKey, value) {
      let name = ''

      if (value) {
        this.selectOpts[selectKey].forEach(item => {
          if (item.id === value){
            name = item.fieldValue
          }
        })
      }
      console.log(name)
      return name
    },

    async _fetchData () {
      this.loadingView = true

      // 获取下拉框数据
      await this._getOptions()

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
    this._getRegionValue()
    this._fetchData()
    this.getSomeEnum()
  }
}
