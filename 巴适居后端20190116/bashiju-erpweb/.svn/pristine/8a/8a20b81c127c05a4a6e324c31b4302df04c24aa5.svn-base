<template>
  <div>
    <el-form :model="deptEditForm" :rules="rules" ref="deptEditForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="部门名称" prop="name">
        <el-input type="text" v-model="deptEditForm.name"></el-input>
      </el-form-item>
      <el-form-item label="选择公司" prop="companyId">
        <!--<base-company-select v-model="deptEditForm.companyId"></base-company-select>-->
        <el-select style="width: 100%" v-model="deptEditForm.companyId" placehelder="--请选择--">
          <el-option
            v-for="item in companyOpts"
            :key="item.name"
            :label="item.name"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="部门类型" prop="deptTypeId">
        <el-select style="width: 100%" v-model="deptEditForm.deptTypeId" placehelder="--请选择--">
          <el-option
            v-for="item in deptTypeOps"
            :key="item.name"
            :label="item.name"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="上级名称">
        <base-dept-cascader v-model="parentId"></base-dept-cascader>
      </el-form-item>

      <el-form-item label="领导人">
        <refer-user v-model="deptEditForm.leader" :url="referUserUrl" :props="cascaderProps" :dataProps="cascaderDataProps"></refer-user>
      </el-form-item>

      <el-form-item label="排序" prop="sortNo">
        <el-input-number style="width: 100%" v-model="deptEditForm.sortNo" :min="1"></el-input-number>
      </el-form-item>

      <el-form-item class="margin-b-none">
        <el-button type="primary" @click="handleSubmit">确认</el-button>
        <el-button @click="handleCancel">取消</el-button>
      </el-form-item>

    </el-form>
  </div>
</template>
<script>
  import BaseDeptCascader from '@/components/BaseCascader/dept'
  import ReferUser from '@/components/BaseCascader'
  import {queryReferenceUserSelect} from '@/request/manage/common'

  import { saveOrUpdateDept, queryDeptType, queryCompanySelectData, queryDeptIdAndDeptName } from '@/request/manage/deptmanage'
  export default {
    name: 'DeptEdit',
    components: { BaseDeptCascader,ReferUser },
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
        referUserUrl: 'manage/commonselect/queryReferenceUserSelect',
        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        deptOps: [],
        deptTypeOps: [],
        companyOpts: [],
        loadingBtn: false,
        parentId: [],
        leader: [],
        deptEditForm: {
          id: '',
          name: '',
          companyId: '',
          deptTypeId: '',
          parentId: [],
          leader: '',
          sortNo: ''
        },
        rules: {
          name: [
            {required: true, message: '部门不可为空', trigger: 'blur'}
          ],
          companyId: [
            {required: true, message: '公司不可为空', trigger: 'change'}
          ],
          deptTypeId: [
            { required: true, message: '部门类型不可为空', trigger: 'change'}
          ],
          leader: [
            { required: true, message: '领导名称不可为空', trigger: 'change'}
          ],
          sortNo: [
            {required: true, message: '排序编号不可为空', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      handleCancel () {
        this.$emit('handleClick', 1)
      },
      handleSubmit () {
        this.$refs['deptEditForm'].validate((valid) => {
          if (valid) {
            let params = {...this.deptEditForm}
            !this.isAdd ? params.id = this.data.id : params.id = ''
            this.$confirm('确定保存编辑的信息吗?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.loadingBtn = true
              console.log(params)
              saveOrUpdateDept({jsonData: JSON.stringify(params)}).then(res => {
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
      setForm: function (data) {
        this.resetForm()
        for (let i in this.deptEditForm) {
          if (data[i]) {
            this.deptEditForm[i] = data[i]
          }
        }

        if(data){
          this.parentId[0] = data.parentId
        }
      },
      resetForm () {
        this.$refs['deptEditForm'] && this.$refs['deptEditForm'].resetFields()
      },
      //获取部门类型信息
      getDeptTypeSelect: function () {
        let params = Object.assign({}, null, {})
        queryDeptType(params).then((res) => {
          this.deptTypeOps = res
        }).catch(err => {
          console.log(err)
        })
      },
      getCompanyDataSelect: function () {
        let params = Object.assign({},null, {})
        queryCompanySelectData(params).then((res) => {
          this.companyOpts = res
        }).catch(err => {
          console.log(err)
        })
      },
      //获取部门类型信息
      queryDeptIdAndDeptName: function () {
        let params = Object.assign({}, null, {})
        queryDeptIdAndDeptName(params).then((res) => {
          this.deptOps = res
        }).catch(err => {
          console.log(err)
        })
      },
      queryReferenceUserSelect(){
        let params = Object.assign({},null, {})
        queryReferenceUserSelect(params).then((res) => {
          this.leader = res
        }).catch(err => {
          console.log(err)
        })
      }
    },
    mounted () {
      this.getCompanyDataSelect()
      this.getDeptTypeSelect()
      this.queryDeptIdAndDeptName()
      this.resetForm()
      // 不为新增 表单赋值
      if (!this.isAdd){
        this.setForm(this.data)
      }
    },
    watch: {
      data: {
        deep: true,
        handler () {
          this.setForm(this.data)
        }
      }
    }
  }
</script>
<style lang="scss" scoped>
</style>
