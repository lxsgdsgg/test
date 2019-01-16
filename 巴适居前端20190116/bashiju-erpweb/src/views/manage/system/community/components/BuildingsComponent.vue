<!--小区座栋管理-->
<template>
  <div>
    <el-form :model="buildingsEdit" ref="buildingsEdit" :inline="true" :rules="rules" label-width="100px">

      <el-form-item label="座栋名称" prop="prefix" v-if="isBatch">
        <el-input v-model="buildingsEdit.prefix" style="width: 100px;"></el-input>
        <span class="el-input__suffix">
          <span class="el-input__suffix-inner">
            <i class="el-input__icon">前缀</i>
          </span>
        </span>
      </el-form-item>

      <el-form-item label="座栋名称" prop="name" v-else="isBatch">
        <el-input v-model="buildingsEdit.name" style="width: 180px;"></el-input>
      </el-form-item>

      <el-form-item v-if="isBatch" style="margin-left: -10px;">
        <span class="ml0">加</span>
        <el-select v-model="buildingsEdit.nameType" style="width: 80px;" @change="setValue">
          <el-option label="数字" value="1"></el-option>
          <el-option label="字母" value="2"></el-option>
        </el-select>
        <span>从</span>
        <el-form-item prop="begin">
          <el-input type="number"
              v-if="buildingsEdit.nameType === '1'"
              min=0 v-model="buildingsEdit.begin"
              style="width: 60px;"></el-input>
          <el-input type="text" v-else v-model="buildingsEdit.begin" style="width: 60px;"></el-input>
          <span>到</span>
        </el-form-item>

        <el-form-item style="margin-left: -10px;" prop="end">
          <el-input type="number" v-if="buildingsEdit.nameType === '1'" min=0 v-model="buildingsEdit.end" style="width: 60px;"></el-input>
          <el-input type="text" v-else v-model="buildingsEdit.end" style="width: 60px;"></el-input>
        </el-form-item>
      </el-form-item>

      <el-form-item prop="buildUnitAlias" v-if="isBatch" style="margin-left: -20px;">
        <el-select v-model="buildingsEdit.buildUnitAlias" style="width: 90px;">
          <el-option label="号楼" value="号楼"></el-option>
          <el-option label="栋" value="栋"></el-option>
          <el-option label="幢" value="幢"></el-option>
          <el-option label="号" value="号"></el-option>
          <el-option label="弄" value="弄"></el-option>
          <el-option label="座" value="座"></el-option>
          <el-option label="阁" value="阁"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item prop="buildUnitAlias" v-else="isBatch">
        <el-select v-model="buildingsEdit.buildUnitAlias" style="width: 180px;">
          <el-option label="号楼" value="号楼"></el-option>
          <el-option label="栋" value="栋"></el-option>
          <el-option label="幢" value="幢"></el-option>
          <el-option label="号" value="号"></el-option>
          <el-option label="弄" value="弄"></el-option>
          <el-option label="座" value="座"></el-option>
          <el-option label="阁" value="阁"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="房号起始值" prop="houseStartNumber">
        <el-input-number type="number" :min=0 v-model="buildingsEdit.houseStartNumber" style="width: 150px;" :disabled="isUpdate"></el-input-number>
      </el-form-item>

      <el-row>
        <el-col :span="20">
          <el-form-item label="建造年代" prop="buildYear" class="block">
              <el-date-picker
                v-model="buildingsEdit.buildYear"
                type="year"
                editable
                value-format="yyyy"
                placeholder="选择建筑年代">
              </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="单元数量" prop="buildingUnitCount">
        <el-input-number v-model="buildingsEdit.buildingUnitCount" :min=0 style="width: 220px;" :disabled="isUpdate"></el-input-number>
      </el-form-item>

      <el-form-item prop="unitAlias">
        <el-select v-model="buildingsEdit.unitAlias" style="width: 160px;">
          <el-option label="单元" value="单元"></el-option>
          <el-option label="号楼" value="号楼"></el-option>
          <el-option label="号" value="号"></el-option>
          <el-option label="门" value="门"></el-option>
          <el-option label="座" value="座"></el-option>
        </el-select>
        <span style="color:red;font-weight: 900;font-size: smaller">无单元时写0</span>
      </el-form-item>

      <el-form-item label="起始楼层" prop="floorStartNumber">
        <el-input-number v-model="buildingsEdit.floorStartNumber" :min=0 style="width: 220px;" :disabled="isUpdate"></el-input-number>
      </el-form-item>
      <el-form-item label="总楼层" prop="floorTotal">
        <el-input-number v-model="buildingsEdit.floorTotal" :min=0 style="width: 240px;" :disabled="isUpdate"></el-input-number>
      </el-form-item>

      <el-row :gutter="0">
        <el-form-item label="单元信息" prop="unitElevatorCount">
          <el-input-number v-model="buildingsEdit.unitElevatorCount" :min=0 style="width: 120px;"></el-input-number><span class="ml10">梯</span>
        </el-form-item>

        <el-form-item prop="unitHouseCount">
          <el-input-number v-model="buildingsEdit.unitHouseCount" :min=0 style="width:120px;" :disabled="isUpdate"></el-input-number><span class="ml10">户</span>
        </el-form-item>

        <el-form-item label="单元起始值" prop="unitStartNumber">
          <el-input-number v-model="buildingsEdit.unitStartNumber" :min=0 style="width: 160px;" :disabled="isUpdate"></el-input-number>
        </el-form-item>


        <el-form-item prop="unitAlias">
          <el-select v-model="buildingsEdit.unitAlias" style="width: 90px;">
            <el-option label="单元" value="单元"></el-option>
            <el-option label="号楼" value="号楼"></el-option>
            <el-option label="号" value="号"></el-option>
            <el-option label="门" value="门"></el-option>
            <el-option label="座" value="座"></el-option>
          </el-select>
        </el-form-item>
      </el-row>

      <el-form-item label="跳过楼层" prop="skipFloor">
        <el-input v-model="buildingsEdit.skipFloor" style="width: 45%" :disabled="isUpdate"></el-input>
        <span style="color:red;font-weight: 900;font-size: smaller">多个楼层时请用用英文逗号隔开</span>
      </el-form-item>

      <div class="btn-group">
        <el-button type="primary" @click="handlerSubmit" :loading="saveLoadingBtn">保存</el-button>
        <el-button @click="handleCancel">取消</el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
  import * as BuildingsAction from '@/request/manage/community'
  import {systemAddLog,systemUpdateLog,systemQueryLog,systemDelLog} from '@/request/log/systemPlatformLog'

  let detailData = []
  export default {
    name: "BuildingsComponent",
    props: {
      buildingData: {
        type: Object
      },
      isUpdate: {
        type: Boolean
      },
      communityData:{
        type: Object
      },
      isBatch:{
        type: Boolean
      }
    },
    data() {
      var checknum = (rule, value, callback)=>{
        if(!this.buildingsEdit.nameType){
          this.buildingsEdit.begin = ''
          this.buildingsEdit.end = ''
          callback(new Error('请先选择类型'));
        }
        let str= /^[A-Z]$/
        if (!str.test(value) && this.buildingsEdit.nameType === '2'){
          callback(new Error('请输入大写字母'));
        } else {
          callback();
        }
      };
      return {
        saveLoadingBtn: false,
        buildingsEdit: {
          id: '',
          name: '',
          prefix: '',
          buildUnitAlias: '',
          houseStartNumber: '',
          buildYear: '',
          buildingUnitCount: '',
          floorStartNumber: '',
          floorTotal: '',
          unitElevatorCount: '',
          unitHouseCount: '',
          unitStartNumber: '',
          unitAlias: '',
          skipFloor: '',
          nameType: '',
          begin: '',
          end: ''
        },
        rules: {
          name: [
            {required: true, message: '座栋名称不可为空', trigger: 'blur'}
          ],
          // prefix: [
          //   {required: true, message: '前缀名称不可为空', trigger: 'blur'}
          // ],
          buildUnitAlias: [
            {required: true, message: '座栋名称单位不可为空', trigger: 'change'}
          ],
          houseStartNumber: [
            {required: true, message: '房号起始值不可为空', trigger: 'blur'}
          ],
          buildingUnitCount: [{
            required: true, message: '单元数量不可为空', trigger: 'blur'
          }],
          unitAlias: [{
            required: true, message: '单元单位不可为空', trigger: 'change'
          }],
          floorStartNumber: [
            {required: true, message: '楼层起始值不可为空', trigger: 'blur'}
          ],
          floorTotal: [
            {required: true, message: '总楼层不可为空', trigger: 'blur'}
          ],
          unitStartNumber: [
            {required: true, message: '单元起始值不可为空', trigger: 'blur'}
          ],
          buildYear: [
            {required: true, message: '建造年代不可为空', trigger: 'blur'}
          ]
          ,
          begin: [
            {validator: checknum,trigger: 'blur'}
          ],
          end: [
            {validator: checknum,trigger: 'blur'}
          ]
        },
        originalData: {}
      }
    },
    methods:{
      setValue(){
        this.buildingsEdit.begin = ''
        this.buildingsEdit.end = ''
        this.buildingsEdit.buildUnitAlias = ''
      },

      StrToGMT(time){
        let GMT = new Date(time)
        return GMT
      },
      // 点击取消操作
      handleCancel(){
        this.$emit('handleClick','2')
      },

      handlerSubmit(){
        if(this.isBatch){
          this.batchSaveBuildingsData()
        }else{
          if(this.isUpdate){
            this.updateBuildingsData()
          }else{
            console.log(1)
            this.saveBuildingsData()
          }
        }
      },
      // 保存座栋信息
      saveBuildingsData() {
        console.log(2)
        this.$refs['buildingsEdit'].validate(valid => {
          console.log(valid)
          if (valid) {
            this.$confirm('确定保存编辑的信息吗?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.saveLoadingBtn = true
              let params = Object.assign({}, this.buildingsEdit, {})
              BuildingsAction.addBuilding({
                dataJson: JSON.stringify(params),
                areaCode: this.communityData.areaCode,
                areaName: this.communityData.areaName,
                communityId: this.communityData.id
              }).then(res => {
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
                this.saveLoadingBtn = false

                let message = {sourceCode:params.name+params.buildUnitAlias,sourceTypeId:'4',operatTypeId:'1'
                  ,logContent: `小区名称：${this.communityData.name}->新增座栋->${params.name}${params.buildUnitAlias}`}
                systemAddLog({message: JSON.stringify(message)})

              }).catch(err => {
                this.saveLoadingBtn = false
                this.$message({
                  type: 'error',
                  message: err.data.msg
                })
              })
            }).catch(err => {
              console.log(err)
            })
          }
        })
      },
      // 批量添加座栋
      batchSaveBuildingsData(){
        debugger
        this.$refs['buildingsEdit'].validate(valid => {
          debugger
          if (valid) {
            this.$confirm('确定保存编辑的信息吗?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.saveLoadingBtn = true
              let params = Object.assign({},this.buildingsEdit,{})
              BuildingsAction.batchAddBuilding({
                dataJson: JSON.stringify(params),
                areaCode: this.communityData.areaCode,
                areaName: this.communityData.areaName,
                communityId: this.communityData.id
              }).then(res=>{
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
                this.saveLoadingBtn = false
                let message = {sourceCode:params.name+params.buildUnitAlias,sourceTypeId:'4',operatTypeId:'1'
                  ,logContent: `小区名称：${this.communityData.name}->批量新增座栋->从${params.begin}${params.buildUnitAlias}到${params.end}${params.buildUnitAlias}`}
                systemAddLog({message: JSON.stringify(message)})
              }).catch(err=>{
                this.saveLoadingBtn = false
                this.$message({
                  type: 'error',
                  message: err.data.msg
                })
              })
            }).catch(err=>{
              console.log(err)
            })
          }
        })
      },
      // 更新座栋
      updateBuildingsData(){
        this.$refs['buildingsEdit'].validate(valid=>{
          if(valid){
            this.$confirm('确定保存编辑的信息吗?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(()=>{
              this.saveLoadingBtn = true
              let params = Object.assign({},this.buildingsEdit,{})
              params.buildYear = new Date(params.buildYear).getFullYear()
              BuildingsAction.updateBuilding({dataJson:JSON.stringify(params)}).then(res=>{
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
                this.saveLoadingBtn = false


                let message = {sourceCode:params.name,sourceTypeId:'4',operatTypeId:'1'
                  ,labelData:this.$utils.getFormFields(this.$refs['buildingsEdit'])
                  ,originalData:this.originalData,newData: params}
                systemUpdateLog({message: JSON.stringify(message)})
              }).catch(err=>{
                this.saveLoadingBtn = false
                this.$message({
                  type: 'error',
                  message: err.data.msg
                })
              })
            }).catch(err=>{
              console.log(err)
            })
          }
        })
      },

      // 添加座栋单元
      addBuildingsUnitData(){
        this.$refs['buildingsEdit'].validate(valid=>{
          if(valid){
            this.$confirm('确定保存编辑的信息吗?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(()=>{
              this.saveLoadingBtn = true
              let params = Object.assign({},this.buildingsEdit,{})
              BuildingsAction.addBuildingUnit(params).then(res=>{
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
                this.saveLoadingBtn = false
              }).catch(err=>{
                this.saveLoadingBtn = false
                this.$message({
                  type: 'error',
                  message: err.data.msg
                })
              })
            }).catch(err=>{
              console.log(err)
            })
          }
        })
      },
      // 更新座栋单元
      updateBuildingsUnitData(){
        this.$refs['buildingsEdit'].validate(valid=>{
          if(valid){
            this.$confirm('确定保存编辑的信息吗?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(()=>{
              this.saveLoadingBtn = true
              let params = Object.assign({},this.buildingsEdit,{})
              BuildingsAction.updateBuildingUnit(params).then(res=>{
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
                this.saveLoadingBtn = false

                let message = {sourceCode:params.name,sourceTypeId:'4',operatTypeId:'1'
                  ,labelData:this.$utils.getFormFields(this.$refs['buildingsEdit'])
                  ,originalData:this.originalData,newData: params}
                systemUpdateLog({message: JSON.stringify(message)})
              }).catch(err=>{
                this.saveLoadingBtn = false
                this.$message({
                  type: 'error',
                  message: err.data.msg
                })
              })
            })
            //   .catch(()=>{
            //   this.$message({
            //     type: 'info',
            //     message: '用户取消操作'
            //   })
            // })
          }
        })
      },

      // 重置表单数据
      resetForm(){
        this.$refs['buildingsEdit'] && this.$refs['buildingsEdit'].resetFields()
      },
      // 设置表单数据
      setForm() {
        let buildYear = detailData.buildYear
        detailData.buildYear = this.StrToGMT("'" + detailData.buildYear + "'")
        for (let i in this.buildingsEdit) {
          if(detailData[i]){
            this.buildingsEdit[i] = detailData[i]
          }
        }
        this.originalData = Object.assign({},{...this.buildingsEdit})
        this.originalData.buildYear = buildYear
      },

      // 根据座栋ID查询座栋详细信息（修改座栋时调用）
      queryBuildingsById(id){
        let params = { id: id}
        BuildingsAction.queryBuildingsById(params).then(res=>{
          detailData = res.data
          if(detailData){
            this.setForm()
          }
        }).catch(err=>{
          console.log(err)
        })
      },
    },
    mounted(){
      // 设置表单数据
      if(this.isUpdate){
        this.queryBuildingsById(this.buildingData.id)
      }
    }
  }
</script>

<style scoped>
</style>
