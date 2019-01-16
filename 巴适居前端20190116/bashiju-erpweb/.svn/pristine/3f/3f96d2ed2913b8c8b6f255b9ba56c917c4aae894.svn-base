<template>
     
  <div>
    <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      <el-form-item label="选择城市" prop="regionId">
        <!--<base-cascader-->
        <!--:changeOnSelect="false"-->
        <!--v-model="temp"-->
        <!--:data="tempData"-->
        <!--:dataProps="cascaderDataProps"-->
        <!--:props="cascaderProps"-->
        <!--@change="queryAllRegionListByAreaCode"-->
        <!--style="width: 216px"-->
        <!--changeOnSelect-->
        <!--&gt;</base-cascader>-->
        <base-cascader
          @change="queryAllRegionListByAreaCode"
          :changeOnSelect="false"
          v-model="temp"
          :data="regionSelectOpts"
          :props="cascaderProps"
          :dataProps="cascaderDataProps"
          style="width: 216px"
        ></base-cascader>

      </el-form-item>


      <el-form-item label="选模板" prop="templateId">
        <el-select v-model="form.templateId" @change="getTemplateName">
          <el-option
            v-for="item in templetOpt"
            :key="item.id"
            :label="item.templateName"
            :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="交易类型" prop="transactionTypeId">
        <el-select v-model="form.transactionTypeId" @change="getTransactionTypeName">
          <el-option
            v-for="item in transactionTypeOpt"
            :key="item.id"
            :label="item.fieldValue"
            :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="采集地址" prop="houseListUrl">
        <el-input v-model="form.houseListUrl" placeholder="业绩类型名称" style="width: 216px"></el-input>
      </el-form-item>

      <div class="btn-group">
        <el-button type="primary" @click="handleSubmit">确定</el-button>
        <el-button @click="handleCancle">取消</el-button>
      </div>
    </el-form>
  </div>
</template>
<style></style>
<script>
  import {queryAllRegionList} from '@/request/manage/community'
  import CitySelector from '@/components/BaseCascader'
  import {
    queryCollectionAddressConfigData,
    deleteCollectionAddressConfigData,
    saveCollectionAddressConfigData,
    getTempletNames
  } from '@/request/manage/collectionAddressConfig'
  import BaseCascader from '@/components/BaseCascader'//区县控件
  import * as selectUrl from '@/request/manage/common' // 获取下拉框数据 url地址
  import *  as  RequestUrl from '@/request/log/systemPlatformLog'
  import {queryAreaAndRegion} from '@/request/manage/user'

  export default {
    components: {CitySelector, BaseCascader},
    props: {
      data: {
        type: Object,
      },
      isAdd: {
        type: Boolean
      },
      cityCode:{
        type: String,
        required: true,
        default(){
          return ''
        }
      }
    },
    data() {
      return {
        form: {
          id: '',
          cityCode: '',
          areaCode: '',
          areaName: '',
          templateId: '',
          templateName: '',
          transactionTypeId: '',
          transactionType: '',
          houseListUrl: '',
          regionId: '',
          regionName: ''
        },
        temp: '',
        tempData: [],
        originalData: {},
        templetOpt: [],
        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        queryOpenCityCountyUrl: selectUrl.queryOpenCityCountyRegion, // 区县
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        transactionTypeOpt: [
          {id: 2, fieldValue: '出租'},
          {id: 1, fieldValue: '出售'}
        ],
        getUrl: 'manage/commonselect/queryThirdLevelCitySelect',
        rules: {
          areaCode: [
            {required: true, message: '必填项', trigger: 'change'}
          ],
          templateId: [
            {required: true, message: '必填项', trigger: 'change'}
          ],
          transactionType: [
            {required: true, message: '必填项', trigger: 'change'}
          ],
          houseListUrl: [
            {required: true, message: '必填项', trigger: 'change'}
          ]
        },
        regionSelectOpts: []
      }
    },
    methods: {
      setForm(data) {
        for (let i in this.form) {
          this.form[i] = data[i]
        }
        if (data.regionId !== '') {
          this.form.regionId = String(data.regionId);
          this.temp = this.form.regionId
        }else if (data.areaCode !== '') {
          this.form.areaCode = data.areaCode;
          this.temp = this.form.areaCode
        }else {
          this.form.cityCode = data.cityCode;
          this.temp = this.form.cityCode
        }
        this.originalData = Object.assign({}, {...this.form});
      },
      handleSubmit() {
        this.$refs['form'].validate(res => {
          if (res) {
            let params = {...this.form}
            let cfg = {
              headers: {
                'Content-Type': 'application/json;charset=UTF-8'
              }
            }
            //如果为空清空传递
            if (!this.form.regionId) {
              delete this.form.regionName
              delete this.form.regionId
            }
            if (!this.form.areaCode) {
              delete this.form.areaCode
              delete this.form.areaName
            }
            saveCollectionAddressConfigData(params, cfg).then(res => {
              if (res.success) {
                this.$message({type: 'success', message: res.msg})
                if (params.id != '') {
                  this.originalData.transactionTypeId = 1 ? this.originalData.transactionType = '出售' : '出租'
                  let message = {
                    sourceCode: params.templateName,//资源编号：客源编号
                    sourceTypeId: 13,// 13：采集地址配置
                    operatTypeId: 2,//操作类型2: 表示修改,
                    labelData: this.$utils.getFormFields(this.$refs['form']),
                    originalData: this.originalData,
                    newData: params,
                  }
                  RequestUrl.systemUpdateLog({message: JSON.stringify(message)}).then(res => {
                    console.log(res)
                  }).catch(error => {
                    console.log(error)
                  })
                } else {
                  let message = {
                    sourceCode: params.templateName,//资源编号：客源编号
                    sourceTypeId: 13,// 13：采集地址配置
                    operatTypeId: 1,//操作类型1: 表示新增,
                    logContent: '新增采集地址:' + params.templateName + ':' + params.houseListUrl//日志内容
                  }
                  RequestUrl.systemAddLog({message: JSON.stringify(message)}).then(res => {
                    console.log(res)
                  }).catch(error => {
                    console.log(error)
                  })
                }
                this.$emit('handleClick', 1)
              } else {
                this.$message({type: 'warning', message: res.msg})
              }
            }).catch(error => {
              console.log(error)
              this.$message({type: 'warning', message: res.msg})
            })
          }
        })

      },
      queryAllRegionListByAreaCode(value, name, data) {
        this.form.cityCode = null;
        this.form.areaCode = null;
        this.form.areaName = null;
        this.form.regionName = null;
        this.form.regionId = null;
        if (data.dataType === 'city') {
          this.form.cityCode = data.code
        } else if (data.dataType === 'area') {
          this.form.cityCode = this.cityCode
          this.form.areaCode = data.code
          this.form.areaName = data.name
        } else if (data.dataType === 'region') {
          this.form.regionName = data.name
          this.form.regionId = data.code
          this.form.areaCode = data.parent.code
          this.form.areaName = data.parent.name
          this.form.cityCode = this.cityCode
        }
      },
      handleCancle() {
        this.$emit('handleClick', 2)
      },
      getRegionName(value, name) {
        this.form.regionName = name;
      },
      //获取模板名称
      getTempletNames() {
        let params = {}
        getTempletNames(params).then(res => {
          this.templetOpt = res.data
        })
      },
      //获取交易类型名称
      getTransactionTypeName(val) {
        let obj = {};
        obj = this.transactionTypeOpt.find((item) => {
          return item.id === val;
        });
        this.form.transactionType = obj.fieldValue
      },
      //获取模板名称
      getTemplateName(val) {
        let obj = {};
        obj = this.templetOpt.find((item) => {
          return item.id === val;
        });
        this.form.templateName = obj.templateName
      },
      _queryOpenCityCountyRegion() {
        selectUrl.queryOpenCityCountyRegion({}).then(res => {
          this.tempData = res
        })
      },
      queryAreaAndRegion(){
        queryAreaAndRegion({cityCode: this.cityCode, deptId: ''}).then(res => {
          this.regionSelectOpts = res || []
        })
      }
    },
    mounted() {
      if (!this.isAdd) {
        this.setForm(this.data)
      }
      this._queryOpenCityCountyRegion();
      this.getTempletNames()
      this.queryAreaAndRegion()
    }
  }

</script>
