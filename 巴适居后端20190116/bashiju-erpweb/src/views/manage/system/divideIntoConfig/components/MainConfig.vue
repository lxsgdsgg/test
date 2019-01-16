<template>
  <div>
    <el-form :model="editForm" :rules="rules" ref="editForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="配置名称" prop="configName">
        <el-input v-model="editForm.configName" placeholder="请填写配置名称"></el-input>
      </el-form-item>

      <el-form-item label="业务类型" prop="configType">
        <el-select style="width: 100%" v-model="editForm.configType" placeholder="请选择业务类型">
          <el-option label="买卖成交" value="00"></el-option>
          <el-option label="租赁成交" value="01"></el-option>
          <el-option label="一手房成交" value="02"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="处理类型" prop="dealType">
        <el-select style="width: 100%" v-model="editForm.dealType" placeholder="请选择处理类型">
          <el-option label="住宅" :value="1"></el-option>
          <el-option label="商业" :value="2"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="所在城市" prop="cityId">
        <city-selector
          v-model="editForm.cityId"
          :url="cityUrl"
          :props="cascaderProps"
          :dataProps="cascaderDataProps"
        >
        </city-selector>
      </el-form-item>

      <el-form-item class="margin-b-none">
        <el-button type="primary" @click="handleEditSubmit">确认</el-button>
        <el-button @click="handleEditCancel">取消</el-button>
      </el-form-item>

    </el-form>
  </div>
</template>
<script>
  import CitySelector from '@/components/BaseCascader'
  import {queryCitySelect} from '@/request/manage/common'
  import {  saveOrUpdateDivideintoConfig  } from '@/request/manage/divideinto'
  export default {
    name: "DivideIntoConfig",
    components: {CitySelector },
    // 父组件传过来的数据
    props: {
      currentData: {
        type: Object
      },
      isAdd: {
        type: Boolean
      }
    },
    data() {
      return  {
        cityUrl: 'manage/commonselect/queryCitySelect',
        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        cityOptions: [],
        loadingBtn: false,
        editForm: {
          configId: '',
          configName: '',
          configType: '',
          dealType: '',
          cityId: []
        },
        rules: {
          configName: [
            {required: true, message: '配置名称不可为空', trigger: 'blur'}
          ],
          configType: [
            {required: true, message: '业务类型不可为空', trigger: 'change'}
          ],
          dealType: [
            {required: true, message: '处理类型不可为空', trigger: 'change'}
          ],
          cityId: [
            {required: true, message: '城市不可为空', trigger: 'change'}
          ]
        }
      }
    },
    methods: {
      handleEditSubmit(){
        this.$refs['editForm'].validate((valid) => {
          if (valid) {
            let params = {...this.editForm}
            !this.isAdd ? params.configId = this.currentData.id : params.configId = ''
            params.rgCode = params.cityId
            this.$confirm('确定保存数据?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.loadingBtn = true
              saveOrUpdateDivideintoConfig({jsonData: JSON.stringify(params)}).then(res => {
                if (res.success) {
                  this.$message({
                    type: 'success',
                    message: res.msg
                  })
                  this.loadingBtn = false
                  this.$emit('handleClick', 1)
                } else {
                  this.$message({
                    type: 'warning',
                    message: res.msg
                  })
                  this.loadingBtn = false
                }
              }).catch(() => {
                this.loadingBtn = false
              })
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '取消!'
              })
            })
          }
        })
      },
      handleEditCancel() {
        this.$emit('handleClick', 2)
      },
      // 获取城市信息
      queryCitySelect: function () {
        queryCitySelect(null).then(res=>{
          this.cityOptions = res
        }).catch(err=>{
          console.log(err)
        });
      },
      setForm: function (data) {
        this.resetForm()
        for (let i in this.editForm) {
          if (data[i]) {
            if(i.indexOf('city') != -1){
              this.editForm.cityId[0] = data[i]
            }else{
              this.editForm[i] = data[i]
            }
          }
        }
      },
      resetForm () {
        this.$refs['editForm'] && this.$refs['editForm'].resetFields()
      }
    },
    mounted() {
      this.queryCitySelect()
      this.setForm(this.currentData)
    }
  }
</script>


<style scoped>

</style>
