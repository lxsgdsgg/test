<!-- 楼盘 新增编辑 -->

<template>
  <el-form :model="editForm" ref="editForm" size="small" label-width="100px" class="wrapper">

    <!-- 小区 -->
    <el-row>
      <el-col :span="6">
        <el-form-item label="小区名称" prop="communityData.name" cusProp="name" :rules="[
            {required: true, message: '该项为必填项', trigger: 'blur'},
          ]">
          <el-input v-model="editForm.communityData.name"></el-input>
        </el-form-item>
      </el-col>

      <el-col :span="6">
        <el-form-item label="备案名称" prop="communityData.recordName" cusProp="recordName" :rules="[
            {required: true, message: '该项为必填项', trigger: 'blur'},
          ]">
          <el-input v-model="editForm.communityData.recordName"></el-input>
        </el-form-item>
      </el-col>

      <el-col :span="6">
        <el-form-item label="拼音检索" prop="communityData.pinyin" cusProp="pinyin"
          :rules="{required: true, message: '该项为必填项', trigger: 'blur'}"
        >
          <el-input v-model="editForm.communityData.pinyin"></el-input>
        </el-form-item>
      </el-col>

      <el-col :span="6">
        <el-form-item label="热门小区" prop="communityData.isHot" cusProp="isHot">
          <el-switch v-model="editForm.communityData.isHot" active-color="#13ce66"></el-switch>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>

      <el-col :span="6">

        <el-form-item
          label="占地面积"
          prop="communityData.floorArea"
          cusProp="floorArea"
          :rules="[
            { required: true, message: '不能为空'},
            { type: 'number', message: '必须为数字值'}
          ]"
          >
          <el-input v-model.number="editForm.communityData.floorArea" autocomplete="off">
            <i slot="suffix" style="font-size: 12px;margin-right: 5px">平米</i>
          </el-input>
        </el-form-item>

      </el-col>

      <el-col :span="6">
        <el-form-item label="建筑面积" prop="communityData.buildingArea" cusProp="buildingArea"
          :rules="[
            { required: true, message: '不能为空'},
            { type: 'number', message: '必须为数字值'}
          ]"
        >
          <el-input v-model.number="editForm.communityData.buildingArea">
            <i slot="suffix" style="font-size: 12px;margin-right: 5px">平米</i>
          </el-input>
        </el-form-item>
      </el-col>

      <el-col :span="6">
        <el-form-item label="容积率" prop="communityData.capacityRatio" cusProp="capacityRatio"
          :rules="[
            { required: true, message: '不能为空'},
            { type: 'number', message: '必须为数字值'}
          ]"
        >
          <el-input v-model.number="editForm.communityData.capacityRatio">
          </el-input>
        </el-form-item>
      </el-col>

      <el-col :span="6">
        <el-form-item label="绿化率" prop="communityData.greenRate" cusProp="greenRate"
          :rules="[
            { required: true, message: '不能为空'},
            { type: 'number', message: '必须为数字值'}
          ]"
        >
          <el-input v-model.number="editForm.communityData.greenRate">
            <i slot="suffix" style="font-size: 12px;margin-right: 5px">%</i>
          </el-input>
        </el-form-item>
      </el-col>

      <el-col :span="12">
        <el-form-item label="所属片区" required class="margin-b-none">

          <el-form-item style="display: inline-block; width: 48%; margin-right: 2%" prop="communityData.areaCode" cusProp="areaCode" :rules="[
              {required: true, message: '该项为必填项', trigger: 'change'},
            ]">
            <city-selector
              @change="handleChangeArea"
              v-model="editForm.communityData.areaCode" :url="cityUrl" :props="cascaderProps"
              :dataProps="cascaderDataProps"
            ></city-selector>
          </el-form-item>

          <el-form-item style="display: inline-block; width: 48%;" prop="communityData.regionId" cusProp="regionId"
                        :rules="[
            {required: true, message: '该项为必填项', trigger: 'change'},
          ]"
          >
            <el-select clearable v-model="editForm.communityData.regionId">
              <el-option
                v-for="item in regionOpt"
                :key="item.name"
                :label="item.name"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>

        </el-form-item>

      </el-col>

      <el-col :span="12">
        <el-form-item label="开发商" prop="developers" cusProp="developers">
          <el-input v-model="editForm.communityData.developers"></el-input>
        </el-form-item>
      </el-col>

      <el-col :span="8">
        <el-form-item label="出售均价" prop="communityData.salePrice" cusProp="salePrice"
          :rules="[
            {required: true, message: '该项为必填项', trigger: 'blur'},
            {type: 'number', message: '必须为数字值'}
          ]"
        >
          <el-input v-model.number="editForm.communityData.salePrice"></el-input>
          <span class="el-input__suffix">
          <span class="el-input__suffix-inner">
            <i class="el-input__icon">元/㎡</i>
          </span>
        </span>
        </el-form-item>
      </el-col>

      <el-col :span ="8">
        <el-form-item label="产权年限：" prop="communityData.propertyLimit" cusProp="propertyLimit"
          :rules="[
            {required: true, message: '该项为必填项', trigger: 'change'},
          ]"
        >
          <el-select clearable  v-model="editForm.communityData.propertyLimit">
            <el-option label="40年" :value="40"></el-option>
            <el-option label="50年" :value="50"></el-option>
            <el-option label="70年" :value="70"></el-option>
            <el-option label="其他" :value="0"></el-option>
          </el-select>
        </el-form-item>
      </el-col>

      <el-col :span="8">
        <el-form-item label="物业用途" prop="communityData.houseUseId" cusProp="houseUseId"
          :rules="[
            { required: true, message: '该项为必填项', trigger: 'change'}
          ]"
        >

          <base-select
            :multiple="true"
            :collapseTags="true"
            v-model="editForm.communityData.houseUseId"
            :data="useTypeOpts"
            :props="selectProps"
          >
          </base-select>

        </el-form-item>
      </el-col>

      <el-col :span="16">
        <el-form-item label="小区地址" prop="communityData.address" cusProp="address" :rules="[
            {required: true, message: '该项为必填项', trigger: 'blur'},
          ]">
          <el-input type="textarea" v-model="editForm.communityData.address"></el-input>
        </el-form-item>
      </el-col>

    </el-row>

    <hr style="margin-bottom: 30px">

    <!-- 楼盘 -->
    <el-row>

      <el-col :span="6">
        <el-form-item label="规划车位数" prop="newHouseData.parkCnt" cusProp="parkCnt"
          :rules="[
            {required: true, message: '该项为必填项', trigger: 'blur'},
            {type: 'number', message: '必须为数字值'}
          ]"
        >
          <el-input v-model.number="editForm.newHouseData.parkCnt">
            <i slot="suffix" style="font-size: 12px;margin-right: 5px">个</i>
          </el-input>
        </el-form-item>
      </el-col>

      <el-col :span="6">
        <el-form-item label="物业费" prop="newHouseData.propertyFees" cusProp="propertyFees"
          :rules="[
            {required: true, message: '该项为必填项', trigger: 'blur'},
            {type: 'number', message: '必须为数字值'}
          ]"
        >
          <el-input v-model.number="editForm.newHouseData.propertyFees">
            <i slot="suffix" style="font-size: 12px;margin-right: 5px">元/平米/月</i>
          </el-input>
        </el-form-item>
      </el-col>

      <el-col :span="6">
        <el-form-item label="出售状态" prop="newHouseData.saleType" cusProp="saleType"
          :rules="[
            {required: true, message: '该项为必填项', trigger: 'change'},
          ]"
        >
          <base-select
            v-model="editForm.newHouseData.saleType"
            :data="saleType"
            :props="selectProps"
          >
          </base-select>
        </el-form-item>
      </el-col>

      <el-col :span="6">
        <el-form-item label="外网同步" prop="isSynchron" cusProp="isSynchron">
          <el-switch v-model="editForm.newHouseData.isSynchron" active-color="#13ce66"></el-switch>
        </el-form-item>
      </el-col>

    </el-row>

    <el-row>

      <el-col :span="6">
        <el-form-item label="物业公司" prop="propertyName" cusProp="propertyName">
          <el-input v-model="editForm.newHouseData.propertyName"></el-input>
        </el-form-item>
      </el-col>

      <template v-if="isAdd">
        <el-col :span="6">
          <el-form-item label="渠道佣金" prop="newHouseData.channelCommissions" cusProp="channelCommissions"
            :rules="[
              {required: true, message: '该项为必填项', trigger: 'blur'},
              {type: 'number', message: '必须为数字值'}
            ]"
          >
            <el-input v-model.number="editForm.newHouseData.channelCommissions">
              <i slot="suffix" style="font-size: 12px;margin-right: 5px">元</i>
            </el-input>
          </el-form-item>
        </el-col>

        <el-col :span="6">
          <el-form-item label="结算佣金" prop="newHouseData.settlementCommissions" cusProp="settlementCommissions"
            :rules="[
              {required: true, message: '该项为必填项', trigger: 'blur'},
              {type: 'number', message: '必须为数字值'}
            ]"
          >
            <el-input v-model.number="editForm.newHouseData.settlementCommissions">
              <i slot="suffix" style="font-size: 12px;margin-right: 5px">元</i>
            </el-input>
          </el-form-item>
        </el-col>
      </template>

      <el-col :span="6">
        <el-form-item label="合作协议" prop="isAgreement" cusProp="isAgreement">
          <el-switch v-model="editForm.newHouseData.isAgreement" active-color="#13ce66"></el-switch>
        </el-form-item>
      </el-col>

      <el-col :span="10">
        <el-form-item label="装修情况" prop="decorationIds" cusProp="decorationIds">

          <base-select
            v-model="editForm.newHouseData.decorationIds"
            :data="decorationOpts"
            :props="selectProps"
            :multiple="true"
            :collapseTags="true"
          >
          </base-select>

        </el-form-item>
      </el-col>

      <el-col :span="24">

        <el-form-item class="margin-b-none" label="售楼部地址" prop="salesDeptAdd" cusProp="salesDeptAdd">
          <el-input type="textarea" v-model="editForm.newHouseData.salesDeptAdd"></el-input>
        </el-form-item>

      </el-col>
    </el-row>

    <div class="btn-group">
      <el-button type="primary" :loading="loadingSubmitBtn" @click="handleSubmit">确认</el-button>
      <el-button @click="handleCancel">取消</el-button>
    </div>
  </el-form>

</template>

<script>
  import CitySelector from '@/components/BaseCascader'
  import BaseSelect from '@/components/BaseSelect'
  import {queryAllRegionList} from '@/request/manage/community'
  import {queryOpenCityCountyUrl} from '@/request/manage/common'
  import {addNewHouse, updateHousing} from '@/request/house/houseNew'
  import {getSelectValue} from '@/request/app'
  import {saleType, houseUseTypes} from './consts'

  export default {
    name: 'formComp',

    components: {CitySelector, BaseSelect},

    props: {
      formInfo: {
        type: Object,
        default () {
          return {}
        }
      },

      isAdd: {
        type: Boolean
      }
    },

    data () {
      return {
        loadingSubmitBtn: false,
        editForm: {
          communityData: {
            name: '',
            propertyLimit: '',
            recordName: '',
            pinyin: '',
            areaCode: '',
            regionId: '',
            address: '',
            salePrice: '',
            developers: '', // 开发商
            floorArea: '', // 占地面积
            buildingArea: '', // 建筑面积
            capacityRatio: '', // 容积率
            greenRate: '', // 绿化率
            isHot: false,
            houseUseId: '', // 物业用途
          },

          newHouseData: {
            // 楼盘信息
            parkCnt: '', // 规划车位数
            salesDeptAdd: '', // 售楼部地址
            propertyFees: '', // 物业费
            saleType: '', // 出售状态
            isSynchron: false, // 外网同步
            propertyName: '', // 物业公司
            decorationIds: [], // 装修情况
            isAgreement: false, // 已签合作协议
            salesDeptAdd: '', // 售楼部地址
            // houseTypes: '', // 房屋类型
            channelCommissions: '', // 渠道佣金
            settlementCommissions: '', // 结算佣金
          }
        },
        cityUrl: queryOpenCityCountyUrl,
        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        selectProps: {
          label: 'fieldValue', // 指定选项的值绑定为下拉框的label属性
          value: 'fieldCode' // 指定选项的值绑定为下拉框的Value属性
        },
        regionOpt: [], // 片区下拉数据
        decorationOpts: [], // 装修情况下拉数据
        useTypeOpts: houseUseTypes, // 物业用途下拉数据
        saleType: saleType,
        originalData: {}
      }
    },

    methods: {

      handleSubmit () {
        this.$refs['editForm'].validate(valid=>{
          if(valid){
            this.$confirm('确定保存编辑的信息?',
              {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }
            ).then(() => {
              this.loadingSubmitBtn = true
              let requestFun = null

              let temp = this._formatParams(this.editForm)

              if (!this.isAdd) {
                temp.communityData.id = this.formInfo.communityId
                temp.newHouseData.id = this.formInfo.houseId
                requestFun = updateHousing
              } else {
                // temp.communityData.id = ''
                // temp.newHouseData.id = ''
                requestFun = addNewHouse
              }

              let params = JSON.stringify({communityData: temp.communityData, newHouseData: temp.newHouseData})

              requestFun({data: params}).then(() => {
                this.$message({
                  type: 'success',
                  message: '操作成功',
                  showClose: true,
                  duration: 2000
                })
                this.$emit('handleConfirm')
                this.loadingSubmitBtn = false

                if (!this.isAdd) {
                  // 向后台传递日志数据
                  let message = {
                    sourceId: this.formInfo.houseId, // 资源ID
                    sourceCode: this.editForm.communityData.name, // 资源编号
                    sourceTypeId:this.$DICT_CODE.LOG.BUSINESS_SOURCE_TYPE.NEW_DEAL,//资源类型
                    businessTypeId: this.$DICT_CODE.LOG.BUSINESS_TYPE.NEW_HOUSE, // 业务类型
                    operatTypeId: this.$DICT_CODE.LOG.BUSINESS_OPERATE_TYPE.UPDATE_NEW_HOUSE, // 操作类型
                    labelData: this.$utils.getFormFields(this.$refs['editForm'], 'cusProp'),
                    originalData: this.originalData,
                    newData: {...temp.communityData, ...temp.newHouseData}
                  }
                  this.$updateLog.newHouse.houseUpdateLog({message: JSON.stringify(message)})
                }

              }).catch(() => {
                this.loadingSubmitBtn = false
              })

            })
          }
        })
      },

      handleCancel () {
        this.$emit('handleCancel')
      },

      handleChangeArea () {
        this.editForm.communityData.regionId = ''
        queryAllRegionList({code: this.editForm.communityData.areaCode}).then(res=>{
          this.regionOpt = res.data
        })
      },

      _setFormInfo () {
        let data = {...this.formInfo} || {}
        let houseForm = {...this.editForm.newHouseData}
        let communityForm = {...this.editForm.communityData}

        this.originalData = Object.assign({}, data)

        Object.keys(houseForm).forEach(key => {
          if (data[key] !== undefined && data[key] !== null) {
            if (key === 'isAgreement') {
              data[key] === 1 ? data[key] = true : data[key] = false
            }

            if (key === 'isSynchron') {
              data[key] === 1 ? data[key] = true : data[key] = false
            }

            houseForm[key] = data[key]

            // 装修
            let ids = []
            data['decorations'].forEach(item => {
              ids.push(String(item.code))
            })
            houseForm['decorationIds'] = ids

          }
        })

        Object.keys(communityForm).forEach(key => {
          if (data[key] !== undefined && data[key] !== null) {

            if (key === 'isHot') {
              data[key] === 1 ? data[key] = true : data[key] = false
            }

            if (key === 'salePrice') {
              data[key] = data[key] / 100
            }

            if (key === 'areaCode') {
              queryAllRegionList({code: this.editForm.communityData.areaCode}).then(res=>{
                this.regionOpt = res.data

                communityForm['regionId'] = data['regionId']

              })

            }

            if (data[key]) {
              communityForm[key] = data[key]
            }

            let houseUses = []
            data['houseUses'].forEach(item => {
              houseUses.push(String(item.code))
            })
            communityForm['houseUseId'] = houseUses
          }
        })

        this.editForm.newHouseData = houseForm
        this.editForm.communityData = communityForm
      },

      // 获取动态下拉数据
      _getSelectOpts () {
        // 获取装修情况下拉
        getSelectValue({param: 'decoration'}).then(res => {
          this.decorationOpts = res.data
        })

      },

      _formatParams (params) {
        let communityData = {...params.communityData}
        let newHouseData = { ...params.newHouseData}

        communityData.isHot ? communityData.isHot = 1 : communityData.isHot = 0

        newHouseData.isAgreement ? newHouseData.isAgreement = 1 : newHouseData.isAgreement = 0
        newHouseData.isSynchron ? newHouseData.isSynchron = 1 : newHouseData.isSynchron = 0

        newHouseData.isSynchron ? newHouseData.isSynchron = 1 : newHouseData.isSynchron = 0

        // 获取装修name集合
        let decNames = []
        newHouseData.decorationIds.forEach(item => {
          this.decorationOpts.forEach(_item => {
            if (item === _item.fieldCode) {
              decNames.push(_item.fieldValue)
            }
          })
        })

        // 获取物业用途name集合
        let houseUseNames = []
        communityData.houseUseId.forEach(item => {
          this.useTypeOpts.forEach(_item => {
            if (item === _item.fieldCode) {
              houseUseNames.push(_item.fieldValue)
            }
          })
        })

        communityData.houseUseId = communityData.houseUseId.join()
        communityData.houseUseName = houseUseNames.join()

        newHouseData.decorationNames = decNames.join()
        newHouseData.decorationIds = newHouseData.decorationIds.join()

        return {
          communityData: communityData,
          newHouseData: newHouseData
        }
      }
    },

    mounted () {
      this._getSelectOpts()

      this.$refs['editForm'].resetFields()

      if (!this.isAdd) {
        this._setFormInfo()
      }
    },

    watch: {
      'editForm.communityData.areaCode' (newVal) {
        if (newVal) {
          this.editForm.communityData.regionId = ''
          queryAllRegionList({code: newVal}).then(res=>{
            this.regionOpt = res.data
          }).catch(err=>{
            console.log(err)
          })
        }
      },

      formInfo: {
        deep: true,
        handler () {
          this.$refs['editForm'].resetFields()
          this._setFormInfo()
        }
      }
    },
  }
</script>

<style scoped lang="scss">
  .wrapper {
    height: 500px;
    overflow-y: auto;
  }
</style>
