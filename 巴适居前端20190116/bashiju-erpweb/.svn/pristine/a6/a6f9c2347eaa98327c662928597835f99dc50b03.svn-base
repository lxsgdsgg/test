<template>
  <el-form :model="resetPwdForm" :rules="resetPwdRules" status-icon ref="resetPwdForm" label-width="100px">
    <el-form-item label="用户名称" prop="userName">
      <el-input disabled v-model="resetPwdForm.userName"></el-input>
    </el-form-item>

    <el-form-item label="新密码" prop="newPassword">
      <el-input type="password" v-model="resetPwdForm.newPassword" auto-complete="off"></el-input>
    </el-form-item>

    <el-form-item label="确认密码" prop="confirmPassword">
      <el-input type="password" v-model="resetPwdForm.confirmPassword" auto-complete="off"></el-input>
    </el-form-item>

    <div class="btn-group">
      <el-button type="primary" :loading="loadingResetPwdBtn" @click="handleResetPwdSubmit">确认</el-button>
      <el-button @click="handleResetPwdCancel">取消</el-button>
    </div>

  </el-form>
</template>

<script>
  import md5 from 'md5'
  import {resetUserPassword} from '@/request/manage/user'

  export default {
    props: {
      userInfo: {
        type: Object,
        default () {
          return {}
        }
      },
    },

    data () {
      const validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'))
        } else {
          if (this.resetPwdForm['confirmPassword'] !== '') {
            this.$refs.resetPwdForm.validateField('confirmPassword')
          }
          callback()
        }
      }
      const validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'))
        } else if (value !== this.resetPwdForm['newPassword']) {
          callback(new Error('两次输入密码不一致!'))
        } else {
          callback()
        }
      }

      return {
        loadingResetPwdBtn: false,
        resetPwdForm: {
          userName: '',
          newPassword: '',
          confirmPassword: '',
          userId: ''
        },
        resetPwdRules: {
          newPassword: [
            { required: true, validator: validatePass, trigger: 'blur'  }
          ],
          confirmPassword: [
            { required: true, validator: validatePass2, trigger: 'blur' }
          ]
        },
      }
    },

    methods: {
      // 重置密码提交
      handleResetPwdSubmit () {
        this.$refs['resetPwdForm'].validate((valid) => {
          if (valid) {

            const params = {...this.resetPwdForm}

            params.newPassword = md5("B5K5$RM<d5+A~X|x" + params.newPassword+ "]f1v|l^7sm{Ll1Jt")
            params.confirmPassword = md5("B5K5$RM<d5+A~X|x" + params.confirmPassword+ "]f1v|l^7sm{Ll1Jt")

            this.$confirm('确定保存编辑的信息吗?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.loadingResetPwdBtn = true
              resetUserPassword({jsonData: JSON.stringify(params)}).then(res => {
                this.loadingResetPwdBtn = false
                this.$message({
                  type: 'success',
                  message: res.msg || '操作成功'
                })
                this.$emit('handleClick', 1)

                // 向后台传递日志数据
                let message = {
                  sourceCode: params.userName, // 资源标识
                  sourceTypeId: this.$DICT_CODE.LOG.SOURCE_TYPE.MANAGE.USER, // 资源类型
                  operatTypeId: this.$DICT_CODE.LOG.OPERATE_TYPE.RESET_PASSWORD, // 操作类型
                  logContent: `用户：${params.userName} -> 被重置密码` // 日志内容
                }
                this.$updateLog.manage.manageQueryLog({message: JSON.stringify(message)})

              }).catch(() => {
                this.loadingResetPwdBtn = false
              })
            })

          }
        })
      },

      handleResetPwdCancel () {
        this.$emit('handleClick', 2)
      }
    },

    mounted () {
      this.resetPwdForm.userName = this.userInfo.name
      this.resetPwdForm.userId = this.userInfo.id
    }
  }
</script>

<style scoped lang="scss">

</style>
