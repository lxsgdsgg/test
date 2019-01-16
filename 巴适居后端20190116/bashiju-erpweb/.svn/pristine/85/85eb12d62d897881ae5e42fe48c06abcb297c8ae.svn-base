<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form" ref="query-form">
        <el-form size="small" :inline="true" :model="queryForm" class="demo-form-inline" ref="queryForm">
          <el-form-item label="部门类型名称" prop="deptTypeName">
            <el-input v-model="queryForm.deptTypeName" placeholper="输入部门类型名称"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click.native.prevent="queryDeptTypePageList(true)" :loading="queryBtnLoading">查询</el-button>
            <el-button @click="resetForm('queryForm')">重置</el-button>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="handleAdd" :isAdd="isAdd">新增</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>

    <div class="page-content-bd" v-loading="loadingView">
      <el-table
        :data="tableData"
        border
        align="center"
        style="width: 100%"
      >

        <el-table-column
          prop="name"
          align="left"
          label="部门类型名称">
        </el-table-column>

        <el-table-column
          prop="parentName"
          align="left"
          label="上级名称">
        </el-table-column>

        <el-table-column
          prop="operator"
          align="left"
          label="操作人">
        </el-table-column>

        <el-table-column
          prop="addTime"
          align="left"
          label="添加日期"
          :formatter="timeFormat"
        >
        </el-table-column>

        <el-table-column
          prop="updateTime"
          align="left"
          label="修改日期"
          :formatter="timeFormat"
        >
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
        >
          <template slot-scope="scope">
            <el-button @click="handleEdit(scope.row)" type="text" size="small" :loading="loadingBtn">编辑</el-button>
            <el-button @click="handleDelete(scope.row)" type="text" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--分页控件-->
      <b-pagination
        :listQuery="listQuery"
        @handleSizeChange="handleSizeChange"
        @handleCurrentChange="handleCurrentChange">
      </b-pagination>

    </div>
    <el-dialog
      title="部门类型编辑"
      :visible.sync="editDialogVisible"
      :close-on-click-modal="false"
      width="400px"
      top="25vh"
    >
      <el-form :model="deptTypeEditForm" :rules="rules" ref="deptTypeEditForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="类型名称" prop="name" placeholder="请填写部门类型名称">
          <el-input v-model="deptTypeEditForm.name"></el-input>
        </el-form-item>

        <el-form-item label="上级类型" prop="parentId">
          <el-select style="width: 100%" v-model="deptTypeEditForm.parentId">
            <el-option value="">--请选择--</el-option>
            <el-option
              v-for="item in parentDeptOpts"
              :key="item.name"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item class="margin-b-none">
          <el-button type="primary" @click="handleSubmit">确认</el-button>
          <el-button @click="handleCancel">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  import {queryDeptTypePageList, queryDeptType, saveOrUpdateDeptType, deleteDeptTypeById}
    from '@/request/manage/depttypemanage'

export default {
  name: 'deptType',
  data () {
    return {
      queryForm: {
        deptTypeName: ''
      },
      deptTypeEditForm: {
        id: '',
        name: '',
        parentId: ''
      },
      tableData: [],
      parentDeptOpts: [],
      editDialogVisible: false,
      queryBtnLoading: false,
      loadingView: false,
      isAdd: false,
      loadingBtn: false,
      listQuery: {page: 1,limit: 10, total: 0},
      rules: {
        name: [
          {required: true, message: '请输入部门类型名称', trigger: 'blur'}
        ],
        parentDept: [
          {required: true, message: '请选择上级部门', trigger: 'change'}
        ]
      }
    }
  },

  methods: {
    //页面改变
    handleSizeChange(val) {
      this.listQuery.limit = val
      this.queryDeptTypePageList(false)
    },
    //当前页改变
    handleCurrentChange(val) {
      this.listQuery.page = val
      this.queryDeptTypePageList(false)
    },
    handleAdd () {
      this.setForm('')
      this.queryParentDeptTypeForSelect()
      this.isAdd = true
      this.editDialogVisible = true
    },
    handleEdit (data) {
      this.isAdd = false
      this.editDialogVisible = true
      this.queryParentDeptTypeForSelect()
      this.setForm(data)
    },
    //删除数据
    handleDelete (row) {
      this.$confirm('即将删除该部门类型，是否继续?', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const param = {deptTypeId: row.id}
        deleteDeptTypeById(param).then(res => {
          if (res.success) {
            this.$message({
              type: 'success',
              message: res.msg
            })
            this.queryDeptTypePageList(false)
          } else {
            this.$message({
              type: 'warning',
              message: res.msg
            })
          }
        }).catch(() => {
          this.$message({
            type: 'error',
            message: '删除异常!'
          })
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消操作!'
        })
      })
    },
    handleCancel () {
      this.editDialogVisible = false
    },
    handleSubmit () {
      this.saveDeptData()
    },
    queryDeptTypePageList(isLoading){
      this.queryBtnLoading = isLoading
      this.loadingView = true
      let params = Object.assign({}, this.queryForm, {
        page: this.listQuery.page,
        limit: this.listQuery.limit
      })
      queryDeptTypePageList(params).then((res) => {
        this.tableData = res.data
        this.listQuery.total = res.count
        this.queryBtnLoading = false
        this.loadingView = false
      }).catch(err => {
        console.log(err)
        this.loadingView = false
        this.queryBtnLoading = false
      })
    },
    /**
     * 查询部门信息作为上级部门类型
     */
    queryParentDeptTypeForSelect: function () {
      let params = {}
      queryDeptType(params).then((res)=>{
        this.parentDeptOpts = res
      }).catch(err=>{
        console.log(err)
      })
    },

    /**
     * 时间格式化函数
     * @param row 行dom对象
     * @param column 列对象
     * @param cellValue 单元格值
     * @returns {string}
     */
    timeFormat(row, column, cellValue) {
      return this.$utils.timeFormat(cellValue)
    },
    /**
     * 修改时设置界面数据
     * @param data
     */
    setForm: function (data) {
      this.resetForm('deptTypeEditForm')
      for (let i in this.deptTypeEditForm) {
        this.deptTypeEditForm[i] = data[i]
      }
    },
    /**
     * 重置界面数据
     */
    resetForm (formName) {
      if(this.$refs[formName]){
        this.$refs[formName].resetFields()
      }
    },
    saveDeptData () {
      this.$refs['deptTypeEditForm'].validate((valid) => {
        if (valid) {
          let params = {...this.deptTypeEditForm}
          if(this.isAdd){
            params.id = ''
          }
          this.$confirm('确定保存编辑的信息吗?', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.loadingBtn = true
            saveOrUpdateDeptType({jsonData: JSON.stringify(params)}).then(res => {
              if (res.success) {
                this.$message({
                  type: 'success',
                  message: res.msg
                })
                this.loadingBtn = false
                this.editDialogVisible = false
                this.queryDeptTypePageList(false)
                this.queryParentDeptTypeForSelect()
              } else {
                this.$message({
                  type: 'warning',
                  message: res.msg
                })
                this.loadingBtn = false
                this.editDialogVisible = false
              }
            }).catch(() => {
              this.loadingBtn = false
              this.editDialogVisible = false
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '取消!'
            })
          })
        }
      })
    }
  },
  mounted() {
    this.queryDeptTypePageList(false)
    this.queryParentDeptTypeForSelect()
  }
}
</script>

<style lang="scss" scoped>
</style>
