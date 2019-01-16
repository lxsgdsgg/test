<template>
  <div>
    <el-form :model="editUnitForm" ref="editUnitForm" label-width="100px">
      <el-row :gutter="0">
        <el-col :span="8">
          <el-form-item prop="name" label="单元名称">
            <el-input v-model="editUnitForm.name" placeholder="单元名称" style="width: 140px;"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="4">
          <el-form-item prop="unitAlias" style="width:200px;margin-left: -100px;">
            <el-select v-model="editUnitForm.unitAlias">
              <el-option label="单元" value="单元"></el-option>
              <el-option label="号楼" value="号楼"></el-option>
              <el-option label="号" value="号"></el-option>
              <el-option label="门" value="门"></el-option>
              <el-option label="座" value="座"></el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="7">
          <el-form-item label="单元信息" prop="unitElevatorCount" style="margin-left: -40px">
            <el-input-number v-model="editUnitForm.unitElevatorCount" :min=0 style="width: 120px;"></el-input-number>
            <span class="ml10">梯</span>
          </el-form-item>
        </el-col>

        <el-col :span="5">
          <el-form-item prop="unitHouseCount">
            <el-input-number v-model="editUnitForm.unitHouseCount" :min=0 style="width:120px;margin-left:-100px;" :disabled="isUpdate"></el-input-number>
            <span class="ml10">户</span>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <el-form-item label="总楼层" prop="floorTotal">
            <el-input-number v-model="editUnitForm.floorTotal" :min=0 style="width: 83%;" :disabled="isUpdate"></el-input-number>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="房号起始值" prop="houseStartNumber" style="margin-left:-38px;">
            <el-input-number type="number" :min=0
                             v-model="editUnitForm.houseStartNumber"
                             style="width: 90%;" :disabled="isUpdate"></el-input-number>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="起始楼层" prop="floorStartNumber">
        <el-input-number v-model="editUnitForm.floorStartNumber" :min=0 style="width: 220px;" :disabled="isUpdate"></el-input-number>
      </el-form-item>

      <el-form-item label="跳过楼层" prop="skipFloor">
        <el-input v-model="editUnitForm.skipFloor" style="width: 35%" :disabled="isUpdate"></el-input>
        <span style="color:red;font-weight: 900;font-size: smaller">多个楼层时请用用英文逗号隔开</span>
      </el-form-item>

      <div class="btn-group">
        <el-button type="primary" @click="handlerSubmit" size="small" :loading="saveLoadingBtn">保存</el-button>
        <el-button @click="handleCancel" size="small">取消</el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
  let unitDetail = []
  import {addBuildingUnit, updateBuildingUnit, queryBuildingUnitsById } from '@/request/manage/community'
  import {systemAddLog,systemUpdateLog,systemQueryLog,systemDelLog} from '@/request/log/systemPlatformLog'


  export default {
    name: "EditBuildingUnitComponent",
    props:{
      data:{
        type: Object
      },
      isUpdate:{
        type: Boolean
      }
    },
    data(){
      return {
        saveLoadingBtn: false,
        editUnitForm: {
          buildingId: '',
          name: '',
          unitAlias: '',
          unitElevatorCount: '',
          unitHouseCount: '',
          floorTotal: '',
          houseStartNumber: '',
          floorStartNumber: '',
          skipFloor: ''
        },
        originalData: {}
      }
    },
    methods:{
      handlerSubmit(){
        if(this.isUpdate){
          this.updateHouseUnitInfo()
        }else{
          this.saveHouseUnitInfo()
        }
      },

      // 取消操作
      handleCancel(){
        this.$emit('handleClick','2')
      },
      // 重置表单
      resetForm(){
        if(this.$refs['editUnitForm']){
          this.$refs['editUnitForm'].resetFields()
        }
      },

      // 保存单元信息
      saveHouseUnitInfo(){
        this.$refs['editUnitForm'].validate(valid=>{
          if(valid){
            this.$confirm('确认保存数据？','提示',{
              type: 'waring',
              confirmButtonText: '确定',
              cancelButtonText: '取消'
            }).then(()=>{
              this.saveLoadingBtn = true
              let params = Object.assign({},this.editUnitForm,{})
              params.bid = this.data.id
              params.buildingId = this.data.id
              addBuildingUnit({dataJson: JSON.stringify(params)}).then(res=>{
                if(res.success){
                  this.$message({
                    type: 'success',
                    message: res.msg
                  })
                }else{
                  this.$message({
                    type: 'error',
                    message: res.msg
                  })
                }
                this.saveLoadingBtn = false
                this.$emit('handleClick','3')

                let message = {sourceCode:params.name,sourceTypeId:'4',operatTypeId:'1'
                  ,logContent: `新增单元:${params.name}${params.unitAlias}`}
                systemAddLog({message: JSON.stringify(message)})
              }).catch(err=>{
                this.saveLoadingBtn = false
                console.log(err)
                if(err.data.msg){
                  this.$message({
                    type: 'error',
                    message: err.data.msg
                  })
                }
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

      // 更新单元信息
      updateHouseUnitInfo(){
        this.$refs['editUnitForm'].validate(valid=>{
          if(valid){
            this.$confirm('确认保存数据？','提示',{
              type: 'waring',
              confirmButtonText: '确定',
              cancelButtonText: '取消'
            }).then(()=>{
              this.saveLoadingBtn = true
              let params = Object.assign({},this.editUnitForm,{})
              params.id = this.data.id
              updateBuildingUnit({dataJson: JSON.stringify(params)}).then(res=>{
                if(res.success){
                  this.$message({
                    type: 'success',
                    message: res.msg
                  })
                  this.$emit('handleClick','3')
                }else{
                  this.$message({
                    type: 'error',
                    message: res.msg
                  })
                }
                this.saveLoadingBtn = false

                let message = {sourceCode:params.name+params.unitAlias,sourceTypeId:'4',operatTypeId:'2'
                  ,labelData:this.$utils.getFormFields(this.$refs['editUnitForm'])
                  ,originalData:this.originalData,newData: params}
                systemUpdateLog({message: JSON.stringify(message)})
              }).catch(err=>{
                this.saveLoadingBtn = false
                console.log(err)
                if(err.data.msg){
                  this.$message({
                    type: 'error',
                    message: err.data.msg
                  })
                }
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

      // 根据单元ID查询单元详细
      queryBuildingUnitsById(){
        let params = {id: this.data.id}
        queryBuildingUnitsById(params).then(res=>{
          if(res.success){
            unitDetail = res.data
            this.setForm()
          }
        }).catch(err=>{
          console.log(err)
        })
      },

      // 设置表单数据
      setForm(){
        this.resetForm()
        for(let i in this.editUnitForm){
          if(unitDetail[i]){
            this.editUnitForm[i] = unitDetail[i]
          }
        }
        this.originalData = Object.assign({},{...this.editUnitForm})
      }
    },
    // 设置表单数据
    mounted(){
      this.resetForm()
      if(this.isUpdate){
        this.queryBuildingUnitsById()
      }
    }

  }
</script>

<style scoped>

</style>
