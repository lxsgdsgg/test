<template>
  <div class="page-content-hd">
    <el-form :model="editForm" ref="editForm" label-width="60%" size="small" width="600px" :rules="rules">
      <el-form-item label="公积金贷款上限" prop="publicLoanCeiling" style="width: 70%;">
        <el-input v-model="editForm.publicLoanCeiling"></el-input>
        <span class="el-input__suffix">
          <span class="el-input__suffix-inner">
            <i class="el-input__icon">万</i>
          </span>
        </span>
      </el-form-item>
      <el-form-item label="商业贷款最大比例" prop="LoanRatioCeiling" style="width: 70%;">
        <el-input type="number" v-model="editForm.LoanRatioCeiling" :min="0" :max="100"></el-input>
        <span class="el-input__suffix">
          <span class="el-input__suffix-inner">
            <i class="el-input__icon">%</i>
          </span>
        </span>
      </el-form-item>
      <el-form-item label="中介佣金比例" prop="agencyFeeRatio" style="width: 70%;">
        <el-input type="number" v-model="editForm.agencyFeeRatio" :precision="2" :step="0.1" :min="0.0"
                  :max="100"></el-input>
        <span class="el-input__suffix">
          <span class="el-input__suffix-inner">
            <i class="el-input__icon">%</i>
          </span>
        </span>
      </el-form-item>
      <el-form-item label="商业贷款基准利率" prop="businessLoanRatio" style="width: 70%;">
        <el-input type="number" v-model="editForm.businessLoanRatio" :precision="2" :step="0.1" :min="0.0"
                  :max="100"></el-input>
        <span class="el-input__suffix">
          <span class="el-input__suffix-inner">
            <i class="el-input__icon">%</i>
          </span>
        </span>
      </el-form-item>
      <el-form-item label="公积金贷款基准利率" prop="publicLoanRatio" style="width: 70%;">
        <el-input type="number" v-model="editForm.publicLoanRatio" :precision="2" :step="0.1" :min="0.0"
                  :max="100"></el-input>
        <span class="el-input__suffix">
          <span class="el-input__suffix-inner">
            <i class="el-input__icon">%</i>
          </span>
        </span>
      </el-form-item>
      <div class="btn-group">
        <el-button type="primary" @click="handleSubmit" :loading="saveDataBtn" size="small">保存</el-button>
      </div>

    </el-form>
  </div>
</template>

<script>
  import {systemAddLog, systemUpdateLog, systemQueryLog, systemDelLog} from '@/request/log/systemPlatformLog'
  import * as RequestURL from '@/request/manage/cityOpen' // 请求后端URL路径

  export default {
    name: "LoanRatioSet",
    props: {
      data: {
        type: Object,
        required: true,
        default() {
          return null
        }
      }
    },
    data() {
      return {
        isAdd: false,
        saveDataBtn: false,
        originalData: null,
        editForm: {
          publicLoanCeiling: this.data.publicLoanCeiling,
          LoanRatioCeiling: this.data.LoanRatioCeiling,
          agencyFeeRatio: this.data.agencyFeeRatio,
          businessLoanRatio: this.data.businessLoanRatio,
          publicLoanRatio: this.data.publicLoanRatio
        },
        rules: {
          publicLoanCeiling: [{required: true, message: '请输入公积金贷款上限', trigger: 'blur'}],
          LoanRatioCeiling: [{required: true, message: '请输入商业贷款最大比率', trigger: 'blur'}],
          agencyFeeRatio: [{required: true, message: '请输入佣金比率', trigger: 'blur'}],
          businessLoanRatio: [{required: true, message: '请输入商业贷款基准利率', trigger: 'blur'}],
          publicLoanRatio: [{required: true, message: '请输入公积金贷款基准利率', trigger: 'blur'}]
        }
      }
    },
    methods: {
      // 提交
      handleSubmit() {
        this.$refs['editForm'].validate((valid) => {
          if (valid) {
            this.editForm.id = this.data.id
            this.editForm.cityCode = this.data.cityCode
            let saveForm = Object.assign({}, {...this.editForm})
            saveForm.agencyFeeRatio = saveForm.agencyFeeRatio * 100
            saveForm.businessLoanRatio = saveForm.businessLoanRatio * 100
            saveForm.publicLoanRatio = saveForm.publicLoanRatio * 100
            const params = {
              jsonData: JSON.stringify(saveForm)
            }
            // const paramsLog = {
            //   jsonData: JSON.stringify({...this.editForm})
            // }
            this.$confirm('确定保存编辑的信息吗？, 是否继续?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.saveDataBtn = true
              RequestURL.saveCityOpen(params).then(res => {
                if (res.success) {
                  this.$message({
                    type: 'success',
                    message: res.msg || '操作成功'
                  })

                  this.data.publicLoanCeiling = saveForm.publicLoanCeiling
                  this.data.LoanRatioCeiling = saveForm.LoanRatioCeiling
                  this.data.agencyFeeRatio = saveForm.agencyFeeRatio / 100
                  this.data.businessLoanRatio = saveForm.businessLoanRatio / 100
                  this.data.publicLoanRatio = saveForm.publicLoanRatio / 100
                  //记日志
                  // if(this.isAdd){//新增
                  let logContent = '贷款比例配置：'
                    + ";公积金贷款上限:" + this.editForm.publicLoanCeiling
                    + ";商业贷款最大比：" + this.editForm.LoanRatioCeiling
                    + ";中介佣金比率：" + this.editForm.agencyFeeRatio
                    + ";商业贷款基准利率：" + this.editForm.businessLoanRatio
                    + ";公积金贷款基准利率：" + this.editForm.publicLoanRatio
                  let message = {
                    sourceCode: '平台开通城市',
                    sourceTypeId: 19,
                    operatTypeId: 2,
                    logContent: logContent
                  }
                  systemQueryLog({message: JSON.stringify(message)}).then(res => {
                    console.log(res)
                  })
                  // }else{//编辑
                  //   let message = {
                  //     sourceCode: '贷款比例配置',
                  //     sourceTypeId: 19,
                  //     operatTypeId: 2,
                  //     labelData: this.$utils.getFormFields(this.$refs['editForm']),
                  //     originalData: this.originalData,
                  //     newData: JSON.parse(paramsLog.jsonData)
                  //   }

                  // systemUpdateLog({message: JSON.stringify(message)}).then(res => {
                  //   console.log(res)
                  // })
                  // }
                } else {
                  this.$message({
                    type: 'warning',
                    message: res.msg
                  })
                }
                this.saveDataBtn = false
              }).catch((err) => {
                console.log(err)
                this.saveDataBtn = false
                this.$message({
                  type: 'info',
                  message: err.msg || '保存失败'
                })
              })
            })

          }
        })
      }
    },
    mounted() {
      this.originalData = Object.assign({}, this.data, {})
    }
  }
</script>

<style scoped>

</style>
