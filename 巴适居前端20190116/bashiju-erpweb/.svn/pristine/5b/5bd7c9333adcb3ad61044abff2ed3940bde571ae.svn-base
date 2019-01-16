<!-- 修改客户信息 -->
<template>
  <div>
    <el-form v-if="type === 1" v-loading="loadingView" :model="formBase" :rules="rulesBase" ref="formBase" label-width="50px">
      <el-form-item label="业主" prop="owner">
        <el-input type="text" v-model="formBase.owner" placeholder="业主姓名"></el-input>
      </el-form-item>

      <el-form-item label="性别" prop="nameType">
        <el-select style="width: 100%" class="mr15" clearable v-model="formBase.nameType" placeholder="业主性别">
          <el-option label="先生" :value="1"></el-option>
          <el-option label="女士" :value="2"></el-option>
          <el-option label="公司" :value="3"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item class="margin-b-none">
        <el-button type="primary" :loading="loadingSubmitBtn" @click="handleBaseSubmit">确认</el-button>
        <el-button @click="handleCancel">取消</el-button>
      </el-form-item>

    </el-form>

    <el-form v-else v-loading="loadingView" :model="formPhone" :rules="rulesPhone" ref="formPhone" label-width="100px">
      <el-form-item label="电话" prop="phone">
        <el-input type="text" v-model="formPhone.phone" placeholder="客户电话"></el-input>
      </el-form-item>

      <el-form-item label="备注" prop="remark">
        <el-input type="textarea" v-model="formPhone.remark" placeholder="电话备注"></el-input>
      </el-form-item>

      <p style="color: red; font-size: 12px; text-align: center; margin-bottom: 10px">*固话与区号间请用"-"分隔，例如:022-8888888</p>

      <el-form-item class="margin-b-none">
        <el-button type="primary" :loading="loadingSubmitBtn" @click="handlePhoneSubmit">确认</el-button>
        <el-button @click="handleCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import {updateCustomerPhone, saveCustomerPhone} from '@/request/customer/customerUsed'

  export default {
    name: 'EditOwnerInfo',

    props: {
      type: {
        type: Number,
        required: true
      },
      data: {
        type: Object,
        required: true
      }
    },

    data () {
      return {
        loadingSubmitBtn: false,
        loadingView: false,
        formBase: {
          owner: '',
          nameType: '',
          nameTitle: ''
        },
        rulesBase: {
          owner: [
            {required: true, message: '该项为必填项', trigger: 'blur'}
          ],
          nameType: [
            {required: true, message: '该项为必填项', trigger: 'change'},
          ]
        },
        formPhone: {
          phone: '',
          remark: '',
        },
        rulesPhone: {
          phone: [
            {required: true, message: '该项为必填项', trigger: 'blur'}
          ],
          remark: [
            {required: true, message: '该项为必填项', trigger: 'blur'}
          ]
        }
      }
    },

    methods: {
      handleBaseSubmit () {
        this.loadingSubmitBtn = true
        let cfg = {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8;'
          }
        }
        let nameType = this.formBase.nameType

        if (nameType === 1) {
          this.formBase.nameTitle = '先生'
        } else if (nameType === 2) {
          this.formBase.nameTitle = '女士'
        } else {
          this.formBase.nameTitle = '公司'
        }

        this.$confirm('确定保存编辑的信息吗?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          updateHouseOwner({...this.formBase}, cfg).then(res => {
            this.$message({
              type: 'success',
              message: res.msg
            })
            this.loadingSubmitBtn = false

            this.$emit('handleBtnClick', 1, 1, {...this.formBase})

          }).catch(() => {
            this.loadingSubmitBtn = false
          })
        })

      },

      handlePhoneSubmit () {
        this.loadingSubmitBtn = true
        let cfg = {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8;'
          }
        }

        // 业主电话编辑
        if (this.type === 2) {
          this.$confirm('确定保存编辑的信息吗?', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            updateCustomerPhone({...this.formPhone}, cfg).then(res => {
              this.$message({
                type: 'success',
                message: res.msg
              })
              this.loadingSubmitBtn = false

              this.$emit('handleBtnClick', 1, 2,  {...this.formPhone})

            }).catch(() => {
              this.loadingSubmitBtn = false
            })
          })

          // 业主电话新增
        } else if (this.type === 3) {
          this.$confirm('确定保存编辑的信息吗?', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            saveCustomerPhone({...this.formPhone}, cfg).then(res => {
              this.$message({
                type: 'success',
                message: res.msg
              })
              this.loadingSubmitBtn = false

              this.$emit('handleBtnClick', 1, 2,  {...this.formPhone})

            }).catch(() => {
              this.loadingSubmitBtn = false
            })
          })
        }

      },

      handleCancel () {
        this.$emit('handleBtnClick', 2)
      },

      _resetForm () {
        this.$refs['formBase'] && this.$refs['formBase'].resetFields()
        this.$refs['formPhone'] && this.$refs['formPhone'].resetFields()
      },

      _setForm () {
        if (this.type === 1) {
          this.formBase = this.data
        } else if (this.type === 2) {
          this.formPhone = this.data
        } else if (this.type === 3) {
          this.formPhone = this.data
        }
      }
    },

    mounted () {
      this._setForm()
    },

    watch: {
      data: {
        deep: true,
        handler () {
          this._setForm()
        }
      }
    },
  }
</script>

<style scoped lang="scss">

</style>
