<template>
  <div>
    <el-form :model="editForm" :rules="rules" ref="editForm" label-width="100px" class="demo-form">
      <el-form-item label="小区名称" prop="name">
        <el-input v-model="editForm.name"></el-input>
      </el-form-item>

      <el-form-item label="备案名称" prop="recordName">
        <el-input v-model="editForm.recordName"></el-input>
      </el-form-item>

      <el-form-item label="拼音检索" prop="pinyin">
        <el-input v-model="editForm.pinyin"></el-input>
      </el-form-item>

      <el-form-item label="所属片区" prop="regionId">
        <city-selector v-model="editForm.regionId" :url="regionUrl" :props="cascaderProps" :changeOnSelect="false"
                       :dataProps="cascaderDataProps" @change="setSearchValue"></city-selector>
        <!--<el-select v-model="editForm.regionId" style="width: 45%">-->
          <!--<el-option-->
            <!--v-for="item in regionOpt"-->
            <!--:key="item.name"-->
            <!--:label="item.name"-->
            <!--:value="item.id"-->
          <!--&gt;-->
          <!--</el-option>-->
        <!--</el-select>-->
      </el-form-item>

      <el-form-item label="小区地址" prop="address">
        <el-input v-model="editForm.address"></el-input>
      </el-form-item>

      <el-form-item label="出售均价" prop="salePrice">
        <el-input v-model="editForm.salePrice"></el-input>
        <span class="el-input__suffix">
          <span class="el-input__suffix-inner">
            <i class="el-input__icon">元/㎡</i>
          </span>
        </span>
      </el-form-item>

      <el-form-item label="热门小区" prop="isHot">
        <el-switch v-model="editForm.isHot" active-color="#13ce66"></el-switch>
      </el-form-item>

    <div class="btn-group">
      <el-button type="primary" @click="addCommunityData" :loading="savegBtn">确认</el-button>
      <el-button @click="handleCancel">取消</el-button>
    </div>
    </el-form>
  </div>
</template>

<script>
import CitySelector from '@/components/BaseCascader'
import {queryAllRegionList,addCommunity, updateCommunityDetail} from '@/request/manage/community'
import {systemAddLog,systemUpdateLog,systemQueryLog,systemDelLog} from '@/request/log/systemPlatformLog'

export default {
  name: 'communityEdit',
  components: {CitySelector},
  props:{
    data:{
      type: Object
    },
    isAdd:{
      type: Boolean
    }
  },
  data () {
    return {
      savegBtn:false,
      editForm: {
        name: '',
        recordName: '',
        pinyin: '',
        areaCode: '',
        regionId: '',
        address: '',
        salePrice: '',
        isHot: false
      },
      regionUrl: 'manage/commonselect/queryOpenCityCountyRegion',//城市、区域、片区
      cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
      cascaderProps: { // 级联下拉组件配置选项
        value: 'code', // 指定选项的值为选项对象的某个属性值
        label: 'name' // 指定选项标签为选项对象的某个属性值
      },
      regionOpt: [],
      rules: {
        name: [
          {required: true, message: '请输入小区名称', trigger: 'blur'},
        ],
        recordName: [
          {required: true, message: '请备案名称', trigger: 'blur'}
        ],
        pinyin: [
          {required: true, message: '请输入英文拼音', trigger: 'blur'},
          { pattern: /[a-zA-Z]/, message: '请输入拼音' }
        ],
        areaCode: [
          {required: true, message: '请选择行政区', trigger: 'change'}
        ],
        regionId: [
          {required: true, message: '请选择片区', trigger: 'change'}
        ],
        address: [
          {required: true, message: '该项为必填项', trigger: 'change'}
        ],
        salePrice: [
          {required: true, message: '该项为必填项', trigger: 'change'}
        ]
      },
      originalData: {}
    }
  },
  methods: {
    // 设置查询条件值
    setSearchValue(val,name,data){
      this.editForm.areaCode = data.parent.code
      this.editForm.regionId = val
    },

    handleCancel () {
      this.$emit('handleClick', 2)
    },
    // 获取片区数据
    queryAllRegionListByAreaCode(value,name,data){
      if (data.dataType && data.dataType !== 'area') {
        this.$message({
          showClose: true,
          type: 'warning',
          message: '只能选择大区!'
        })
        setTimeout(() => {
        }, 50)
        return false
      }
      this.editForm.regionId = ''
      queryAllRegionList({code:value}).then(res=>{
        this.regionOpt = res.data
      }).catch(err=>{
        console.log(err)
      })
    },
    // 重置表单
    resetForm(formName){
      this.$refs[formName] && this.$refs[formName].resetFields()
    },
    // 设置表单数据
    setForm(data){
      for(let i in this.editForm){
        if(this.editForm[i] === 'regionId'){
          this.data[i] = String(this.data[i])
        }
        this.editForm[i] = this.data[i]
      }
      this.originalData = Object.assign({},{...this.editForm})
    },
    // 保存数据
    addCommunityData: function () {
      this.$refs['editForm'].validate(valid=>{
        if(valid){
          let params = Object.assign({},this.editForm,{})
          !this.isAdd ? params.id = this.data.id:params.id = ''
          if(params.isHot){
            params.isHot = 1
          }else{
            params.isHot = 0
          }
          this.$confirm('确定保存编辑的信息吗?', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.savegBtn = true
            if(!this.isAdd){///修改小区
              updateCommunityDetail({dataJson: JSON.stringify(params)}).then(res => {
                if (res.success) {
                  this.$message({
                    type: 'success',
                    message: res.msg
                  })
                  this.$emit('handleClick', 1)
                } else {
                  this.$message({
                    type: 'error',
                    message: res.msg
                  })
                }
                this.savegBtn = false
                // 昆明市五华区虹山片区浅唱低吟小区
              }).catch(err => {
                console.log(err)
              })
              let message = {sourceCode:params.name,sourceTypeId:'4',operatTypeId:'2'
                ,labelData:this.$utils.getFormFields(this.$refs['editForm'])
                ,originalData:this.originalData,newData: params}
              systemUpdateLog({message: JSON.stringify(message)})
            }else{ /// 新增小区
              addCommunity(params).then(res => {
                if (res.success) {
                  this.$message({
                    type: 'success',
                    message: res.msg
                  })
                  this.$emit('handleClick', 1)
                } else {
                  this.$message({
                    type: 'error',
                    message: res.msg
                  })
                }
                this.savegBtn = false
                let message = {sourceCode:params.name,sourceTypeId:'4',operatTypeId:'1'
                  ,logContent: '新增小区：'+ params.name}
                systemAddLog({message: JSON.stringify(message)})
              }).catch(err => {
                console.log(err)
                this.savegBtn = false
              })
            }
          })
        }
      })
    }
  },
  mounted(){
    this.resetForm('editForm')
    // this.queryAllRegionListByAreaCode(this.data.areaCode,'',this.data)
    // 不为新增 表单赋值
    if (!this.isAdd){
      this.data.regionId = String(this.data.regionId)
      this.setForm(this.data)
      if(this.data.isHot === 1){
        this.editForm.isHot = true
      }else{
        this.editForm.isHot = false
      }
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
