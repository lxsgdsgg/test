<template>
  <div>
    <el-form :model="form" :rules="rules" ref="form" label-width="100px" class="demo-form">

      <el-form-item label="角色名称" prop="name">
        <el-input v-model="form.name"></el-input>
      </el-form-item>

      <el-form-item label="角色组" prop="groups">
        <el-select style="width: 100%" v-model="form.groups" placeholder="请选择活动区域">
          <el-option
            v-for="item in roleGroupOpts"
            :key="item.label"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="所属公司" prop="companyId">
        <el-select style="width: 100%" v-model="form.companyId" placeholder="请选择所属公司">
          <el-option
            v-for="item in companyOpts"
            :key="item.label"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="排序码" prop="sortNo">
        <el-input-number style="width: 100%" v-model="form.sortNo" :min="0"></el-input-number>
      </el-form-item>

      <el-form-item class="margin-b-none">
        <el-button type="primary" :loading="loadingBtn" @click="handleSubmit">确认</el-button>
        <el-button @click="handleCancel">取消</el-button>
      </el-form-item>

    </el-form>
  </div>
</template>

<script>
import {saveOrUpdateRole} from '@/request/manage'
export default {
  name: 'RoleEdit',
  props: {
    data: {
      type: Object
    },
    isAdd: {
      type: Boolean
    }
  },
  data () {
    return {
      form: {
        name: '',
        groups: '',
        companyId: '',
        sortNo: ''
      },
      rules: {
        name: [
          {required: true, message: '请输入名称', trigger: 'blur'}
        ],
        sortNo: [
          {required: true, message: '请输入排序码', trigger: 'blur'}
        ],
        groups: [
          {required: true, message: '请选择角色组', trigger: 'change'}
        ],
        companyId: [
          {required: true, message: '请选择所属公司', trigger: 'change'}
        ]
      },
      roleGroupOpts: [
        {
          value: '01',
          label: '超级管理员'
        },
        {
          value: '02',
          label: '城市管理员'
        },
        {
          value: '03',
          label: '普通用户'
        }
      ],
      companyOpts: [
        {
          value: 'BSJ001',
          label: '巴适居网络服务有限公司'
        },
        {
          value: 'BSJ002',
          label: '巴适居网络服务有限公司昆明分公司'
        },
        {
          value: 'BSJ003',
          label: '巴适居网络服务有限公司大理分公司'
        },
        {
          value: 'BSJ004',
          label: '云南巴适居网路服务有限公司曲靖分公司'
        }
      ],
      loadingBtn: false
    }
  },
  methods: {
    handleCancel () {
      this.$emit('handleClick', 2)
    },
    handleSubmit () {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          let params = {...this.form}
          !this.isAdd ? params.id = this.data.id : params.id = ''

          this.$confirm('确定保存编辑的信息吗?', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.loadingBtn = true
            saveOrUpdateRole({jsonData: JSON.stringify(params)}).then(res => {
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

      // this.$emit('handleClick', 1)
    },
    _setForm (data) {
      this._resetForm()
      for (let i in this.form) {
        if (data[i]) {
          this.form[i] = data[i]
        }
      }
    },
    _resetForm () {
      this.$refs['form'] && this.$refs['form'].resetFields()
    }
  },
  mounted () {
    this._resetForm()

    // 不为新增 表单赋值
    if (!this.isAdd) this._setForm(this.data)
  },
  watch: {
    data: {
      deep: true,
      handler () {
        this._setForm(this.data)
      }
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
