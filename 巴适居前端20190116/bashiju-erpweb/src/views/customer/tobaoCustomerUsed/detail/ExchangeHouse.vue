<template>
  <div>

    <el-form size="small" v-loading="loadingView" :model="form" :rules="rules" ref="form" label-width="130px">

      <el-form-item label="公私客" required class="margin-b-none">

        <el-col :span="11">
          <el-form-item prop="discStatusId">
            <el-select clearable v-model="form.discStatusId" placeholder="盘状态">
              <el-option label="公客" value="2"></el-option>
              <el-option label="私客" value="1"></el-option>
            </el-select>
          </el-form-item>
        </el-col>


          <el-col :span="11">
            <el-form-item prop="discStatusId">
              <a href="wwww.baidu.com" >我的私客</a>
            </el-form-item>
          </el-col>
        </el-form-item>




      <el-form-item label="是否发送绑定码" prop="isSendBindCode">

        <el-switch
          v-model="form.isSendBindCode">
        </el-switch>

      </el-form-item>

      <el-form-item label="姓名" required class="margin-b-none">
        <el-col :span="11">
          <el-form-item prop="custName">
            <el-input v-model="form.custName"></el-input>
          </el-form-item>
        </el-col>
        <el-col style="text-align: center" :span="2">-</el-col>
        <el-col :span="11">
          <el-form-item prop="relateTypeId">
            <el-select clearable v-model="form.relateTypeId" >
              <el-option v-for="item in genderOpts" :label="item.label" :value="item.value" :key="item.value"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-form-item>

      <el-form-item label="电话" required class="margin-b-none">
        <el-col :span="11">
          <el-form-item prop="phone">
            <el-input v-model="form.phone"></el-input>
          </el-form-item>
        </el-col>
        <el-col style="text-align: center" :span="2">-</el-col>
        <el-col :span="11">
          <el-form-item prop="relateType">
            <el-select clearable v-model="form.relateType" placeholder="电话">
              <el-option v-for="item in relateTypeOpts" :label="item.label" :value="item.value" :key="item.value"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-form-item>

      <p class="msg">* 固话与区号间请用"-"分隔，例如:022-8888888</p>

      <el-form-item class="margin-b-none">
        <el-button type="primary" :loading="loadingSubmitBtn" @click="handleNext">下一步</el-button>
        <el-button @click="handleCancel">取消</el-button>
      </el-form-item>

    </el-form>


    <!-- 房源基本信息编辑 -->

    <!-- 住宅 -->
    <el-dialog
      title="新增房源"
      :visible.sync="dialogVisibleHouse"
      width="1100px"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      append-to-body
    >
      <add-house-form @handleClick="handleAddHouseClick" :formInfo="formInfo" :isAdd="false" :type="type"></add-house-form>
    </el-dialog>



    <el-dialog
      title="新增商铺"
      :visible.sync="shopsDialogVisible"
      width="1100px"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      append-to-body
    >
      <add-shops-form @handleClick="handleAddHouseClick" :formInfo="formInfo" :isAdd="false" :type="type"></add-shops-form>
    </el-dialog>

    <el-dialog
      title="新增公寓"
      :visible.sync="apartmentDialogVisible"
      width="1100px"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      append-to-body
    >
      <add-apartmentForm @handleClick="handleAddHouseClick" :formInfo="formInfo" :isAdd="false" :type="type"></add-apartmentForm>
    </el-dialog>

    <el-dialog
      title="新增仓库"
      :visible.sync="wareHouseDialogVisible"
      width="1100px"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      append-to-body
    >
      <add-ware-HouseForm @handleClick="handleAddHouseClick" :formInfo="formInfo" :isAdd="false" :type="type"></add-ware-HouseForm>
    </el-dialog>

    <el-dialog
      title="新增厂房"
      :visible.sync="factoryDialogVisible"
      width="1100px"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      append-to-body
    >
      <add-factoryFrom @handleClick="handleAddHouseClick" :formInfo="formInfo" :isAdd="false" :type="type"></add-factoryFrom>
    </el-dialog>


    <el-dialog
      title="新增车位"
      :visible.sync="parkingDialogVisible"
      width="1100px"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      append-to-body
    >
      <add-parking @handleClick="handleAddHouseClick" :formInfo="formInfo" :isAdd="false" :type="type"></add-parking>
    </el-dialog>

    <el-dialog
      title="新增土地"
      :visible.sync="landDialogVisible"
      width="1100px"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      append-to-body
    >
      <add-land @handleClick="handleAddHouseClick" :formInfo="formInfo" :isAdd="false" :type="type"></add-land>
    </el-dialog>

  </div>
</template>

<script>

  //沟通阶段
  const communicateStage = [
    {
      label: '非共有',
      value: '0'
    },
    {
      label: '共有',
      value: '1'
    }
  ]

  //客户来源
  const sourceType = [
    {
      label: '非共有',
      value: '0'
    },
    {
      label: '共有',
      value: '1'
    }
  ]
  //装修
  const decoration = [
    {
      label: '非共有',
      value: '0'
    },
    {
      label: '共有',
      value: '1'
    }
  ]



  import {saveHouseFollowAndRemind, queryCountHousing, queryBuildingInfo, addHouse} from '@/request/house/houseUsed'
  
  import {queryCommunitySelectWithRegion} from '@/request/app'
  import CommunitySelector from '@/components/BaseCascader/index'
  import {addCustomer} from "@/request/customer/customerUsed";
  import BuildingUnitComponent from '@/views/manage/system/community/components/BuildingUnitComponent' // 坐栋
  import {GENDER_OPTS,RELATE_TYPE_OPTS} from '../consts'
  import addHouseForm from '../formComp/house' // 房源form组件
  import addShopsForm from '../formComp/shops' //商铺form组件
  import addApartmentForm from '../formComp/apartment' //公寓form组件
  import addWareHouseForm from '../formComp/wareHouse'//仓库form组件
  import addFactoryFrom from '../formComp/factory' //工厂form组件
  import addParking from '../formComp/parking'  //车位form组件
  import addLand from '../formComp/land' //土地form组件
  export default {
    components: {CommunitySelector, addHouseForm, BuildingUnitComponent,addShopsForm,addApartmentForm,addWareHouseForm,addFactoryFrom,addParking,addLand},
    props: {
      param: { // 父组件传递过来的参数 房屋类型等
        type: Object,
        required: true
      },
      formInfo:{
        type: Object,
        default () {
          return null
        }
      },
      
      type: {
        type: String,
        default: 'house_customer' // 房源类型
      }
    },
    data () {
      return {
        genderOpts: GENDER_OPTS,
        relateTypeOpts: RELATE_TYPE_OPTS,
        loadingView: false,
        loadingSubmitBtn: false,
        selectDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        selectProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          children: 'children', // 指定选项的子选项为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        form: {
         
        },
        // 表单校验配置
        rules: {
          transactionTypeId: [
            {required: true, message: '该项为必填项', trigger: 'change'}
          ],
          custName: [
            {required: true, message: '该项为必填项', trigger: 'blur'},
          ],
          custCallType: [
            {required: true, message: '该项为必填项', trigger: 'blur'},
          ],
          phone: [
            {required: true, message: '该项为必填项', trigger: 'blur'},
          ],
        },

        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        areasUrl: queryCommunitySelectWithRegion,
        addHouseFormInfo: null,
        dialogVisibleHouse: false,
        dialogVisibleBuildingUnit: false,
        shopsDialogVisible:false,
        apartmentDialogVisible:false,
        wareHouseDialogVisible:false,
        factoryDialogVisible:false,
        parkingDialogVisible:false,
        landDialogVisible:false,
        currentBuildingData: null,
        hackResetBuilding: true
      }
    },
    methods: {

      handleNext () {
        this.$refs['form'].validate((valid) => {
          if (this.form.discStatusId === '2') {
            this.form.discStatus = '公客'
          } else if (this.form.discStatusId === '1') {
            this.form.discStatus = '私客'
          }
          //是否发送绑定码
          this.form.isSendBindCode ? this.form.isSendBindCode = '1' : this.form.isSendBindCode = '0'
          this.form.relateName = this._getOptName('relateType', this.relateTypeOpts) //电话类型
          this.form.relateTypeName = this._getOptName('relateTypeId', this.genderOpts) //用户称呼
          this.formInfo.firstInfo = this.form 
          if (valid) {
            
                // 跟进房源用途 弹出对应表单
                switch (this.type) {
                  case 'house_customer': // 住宅
                    this.dialogVisibleHouse = true
                    break
                  case 'shops_customer'://商铺
                      this.shopsDialogVisible = true
                      break
                  case 'apartment_customer'://公寓
                        this.apartmentDialogVisible = true
                        break
                  case 'wareHouse_customer'://仓库
                        this.wareHouseDialogVisible = true
                        break
                  case 'factory_customer'://工厂
                        this.factoryDialogVisible = true
                        break
                  case 'parking_customer'://车位
                        this.parkingDialogVisible = true
                        break
                  case 'land_customer'://土地
                        this.landDialogVisible = true
                        break
                  default:
                    this.dialogVisibleHouse = true
                }


          }
        })
      },


      _resetForm () {
        this.$refs['form'] && this.$refs['form'].resetFields()
      },

      handleCancel () {
        this.$emit('handleClick', 2)
      },

      handleAddHouseClick (action, _params) {
        if (action === 1) {
          // 合并参数
          debugger;
          let params = {..._params, ...this.form, ...this.param}


          if (params.discStatusId === '2') {
            params.discStatus = '公客'
          } else if (params.discStatusId === '1') {
            params.discStatus = '私客'
          }
          params.transactionTypeId = 4      //交易类型Id
          params.transactionType = '求购' //交易类型
          params.formName = params.houseUsesId+'_customer' //表单名称
          // params.houseUsesIds = this.param.houseUsesId  //房屋用途
          delete params.houseUsesId
          //是否发送绑定码
          params.isSendBindCode ? params.isSendBindCode = '1' : params.isSendBindCode = '0'
          delete params.room
          delete params.space
          delete params.price
          delete params.floorCount
          delete params.buildDates
          console.log(params)
          addCustomer({dataJson: JSON.stringify(params)}).then(res => {
            this.$message({
              type: 'success',
              message: res.msg || '操作成功'
            })
            this.dialogVisibleHouse = false
            this.shopsDialogVisible = false
            this.apartmentDialogVisible = false
            this.wareHouseDialogVisible = false
            this.factoryDialogVisible = false
            this.parkingDialogVisible = false
            this.landDialogVisible = false
            this.dialogVisibleHouse = false
            this.$emit('handleClick', 1)
          })
        } else {

          this.dialogVisibleHouse = false
          this.shopsDialogVisible = false
          this.apartmentDialogVisible = false
          this.wareHouseDialogVisible = false
          this.factoryDialogVisible = false
          this.parkingDialogVisible = false
          this.landDialogVisible = false
          this.dialogVisibleHouse = false

        }
      },

      _getOptName (value, opts) {

        console.log(value, opts)
        let name = ''
        opts.forEach(item => {
          if (item.value === value){
            name = item.label
          }
        })
        return name
      }
    },
    mounted(){
    this.form = this.formInfo.firstInfo
    this.form.discStatusId = String(this.form.discStatusId)
    this.form.relateTypeId = String(this.form.relateTypeId)
    this.form.relateType = String(this.form.relateType)
  }

  }
</script>

<style scoped lang="scss">
  .msg {
    margin-bottom: 15px;
    color: red;
    font-size: 12px;
    text-align: center;
  }
</style>
