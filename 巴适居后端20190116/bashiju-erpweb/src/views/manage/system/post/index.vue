<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form" ref="query-form">
        <el-form size="small" :inline="true" :model="queryForm" ref="queryForm" class="demo-form-inline">
          <el-form-item label="职位名称" prop="postName">
            <el-input v-model="queryForm.postName"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click.native.prevent="queryPostDataPageList(true)" :loading="queryBtnLoading">查询</el-button>
            <el-button @click="resetForm('queryForm')">重置</el-button>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="handleAdd('postEditForm')">新增</el-button>
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
          label="职位名称">
        </el-table-column>

        <el-table-column
          prop="addTime"
          align="left"
          :formatter="timeFormat"
          label="新增日期">
        </el-table-column>

        <el-table-column
          prop="updateTime"
          align="left"
          :formatter="timeFormat"
          label="更新日期">
        </el-table-column>

        <el-table-column
          prop="permissionArea"
          align="left"
          label="权限域">
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
        >
          <template slot-scope="scope">
            <el-button @click="handleEdit(scope.row,'postEditForm')" type="text" size="small">编辑</el-button>
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

    <!--编辑数据弹框-->
    <el-dialog
      title="职位编辑"
      :visible.sync="editDialogVisible"
      :close-on-click-modal="false"
      top="30vh"
      width="400px">

      <el-form :model="postEditForm" :rules="rules" ref="postEditForm" label-width="100px">
        <el-form-item label="职位名称" prop="name" placeholder="请填写职位名称">
          <el-input v-model="postEditForm.name"></el-input>
        </el-form-item>

        <el-form-item class="margin-b-none">
          <el-button type="primary" @click="handleSubmit" :loding="loadingBtn">确认</el-button>
          <el-button @click="handleCancel">取消</el-button>
        </el-form-item>

      </el-form>
    </el-dialog>
  </div>
</template>

<script>

  import {queryPostDataPageList, saveOrUpdatePosition, deletePositionById} from '@/request/manage/post'

export default {
  name: 'deptType',
  data () {
    return {
      queryForm: {
        postName: ''
      },
      postEditForm: {
        id:'',
        name: ''
      },
      loadingView: false,
      editDialogVisible: false,
      queryBtnLoading: false,
      tableData: [],
      isAdd: false,
      loadingBtn: false,
      listQuery: {page: 1,limit: 10, total: 0},
      rules: {
        name: [
          {required: true, message: '请输入职位名称', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    //页面改变
    handleSizeChange(val) {
      this.listQuery.limit = val
      this.queryPostDataPageList(false)
    },
    //当前页改变
    handleCurrentChange(val) {
      this.listQuery.page = val
      this.queryPostDataPageList(false)
    },

    // 添加操作
    handleAdd (formName) {
      this.isAdd = true
      // this.resetForm(formName)
      this.setForm('',formName)
      this.editDialogVisible = true
    },
    // 编辑职位
    handleEdit (row,formName) {
      this.isAdd = false
      this.editDialogVisible = true
      this.setForm(row,formName)
    },

    //取消操作
    handleCancel () {
      this.editDialogVisible = false
    },

    //确认提交数据
    handleSubmit () {
      this.saveOrUpdatePosition()
    },

    //时间格式化函数
    timeFormat(row, column, cellValue) {
      return this.$utils.timeFormat(cellValue)
    },

    // 修改时设置界面数据
    setForm: function (data,formName) {
      this.resetForm(formName)
      for (let i in this.postEditForm) {
        this.postEditForm[i] = data[i]
      }
    },

    //重置界面数据
    resetForm (formName) {
      if (this.$refs[formName]) {
        this.$refs[formName].resetFields()
      }
    },
    // 查询页面列表数据
    queryPostDataPageList(isLoading){
      this.queryBtnLoading = isLoading
      this.loadingView = true
      let params = Object.assign({}, this.queryForm, {
        page: this.listQuery.page,
        limit: this.listQuery.limit
      })
      queryPostDataPageList(params).then((res) => {
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

    // 保存职位信息
    saveOrUpdatePosition: function(){
      this.$refs['postEditForm'].validate((valid) => {
        if (valid) {
          let params = {...this.postEditForm}
          this.$confirm('确认保存数据吗？是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.loadingBtn = true
            saveOrUpdatePosition({jsonData:JSON.stringify(params)}).then(res => {
              if (res.success) {
                this.$message({
                  type: 'success',
                  message: res.msg,
                })
                this.editDialogVisible = false
                this.loadingBtn = false
                this.queryPostDataPageList(false)
              } else {
                this.$message({
                  type: 'warning',
                  message: res.msg
                })
                this.editDialogVisible = false
                this.loadingBtn = false
              }
            }).catch(() => {
              this.editDialogVisible = false
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
    // 删除职位信息
    handleDelete (row) {
      this.$confirm('确定删除该职位?', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const param = {postId: row.id}
        deletePositionById(param).then(res => {
          if (res.success) {
            this.$message({
              type: 'success',
              message: res.msg
            })
            this.queryPostDataPageList(false)
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
    }
  },
  mounted(){
    this.queryPostDataPageList(false)
    this.resetForm('postEditForm')
  }
}
</script>

<style lang="scss" scoped>
</style>
