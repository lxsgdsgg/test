<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form">
        <el-form size="small" ref="form" :inline="true" :model="form" class="demo-form-inline">
          <el-form-item label="角色名称" prop="roleName">
            <el-input v-model="form.roleName" placeholder="请输入角色名称"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click.native.prevent="handleQuery" :loading="loadingQueryBtn">查询</el-button>
            <el-button @click.native.prevent="_resetForm('form')">重置</el-button>
          </el-form-item>

          <el-form-item class="pull-right">
            <el-button type="primary" @click="handleAdd">新增</el-button>
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
        height="580"
      >

        <el-table-column
          type="index"
          width="50"
          align="center"
          label="序号"
        >
        </el-table-column>

        <el-table-column
          prop="name"
          align="center"
          label="角色名称">
        </el-table-column>

        <el-table-column
          prop="operator"
          align="center"
          label="操作人">
        </el-table-column>

        <el-table-column
          prop="addTime"
          align="center"
          :formatter="_timeFormat"
          label="新增时间">
        </el-table-column>

        <el-table-column
          prop="sortNo"
          align="center"
          label="排序码">
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
        >
          <template slot-scope="scope">
            <el-button type="text" size="small" @click="handlePermission">角色授权</el-button>
            <el-button @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
            <el-button @click="handleDelete(scope.row)" type="text" size="small">删除</el-button>
          </template>
        </el-table-column>

      </el-table>

      <b-pagination
        :listQuery="listQuery"
        @handleSizeChange="handleSizeChange"
        @handleCurrentChange="handleCurrentChange">
      </b-pagination>
    </div>

    <template>
      <el-dialog
        title="角色编辑"
        :visible.sync="editRoleDialogVisible"
        width="400px">
        <role-edit v-if="hackReset" @handleClick="handleEditRoleDialog" :data="currentRowData" :isAdd="isAdd"></role-edit>
      </el-dialog>

      <el-dialog
        title="角色权限设置"
        :visible.sync="permissionDialogVisible"
        width="800px">
        <role-permission @handleClick="handlePermissionDialog"></role-permission>
      </el-dialog>
    </template>

  </div>
</template>

<script>
import RoleEdit from './components/RoleEdit'
import RolePermission from '../components/ThePermissionManage'
import {getRoleData, delRole} from '@/request/manage'
export default {
  name: 'role',
  components: {RoleEdit, RolePermission},
  data () {
    return {
      form: {
        roleName: ''
      },
      tableData: [
        {
          name: '业务部',
          operator: '系统管理员',
          addTime: 1530699575000,
          sortNo: 1
        }
      ],
      editRoleDialogVisible: false,
      permissionDialogVisible: false,
      loadingQueryBtn: false,
      loadingView: false,
      listQuery: {
        page: 1,
        limit: 10,
        total: 0
      },
      currentRowData: null,
      hackReset: true,
      isAdd: false
    }
  },
  methods: {
    handleQuery () {
      this._queryTableData(true)
    },
    handleSizeChange (val) {
      this.listQuery.limit = val
      this._queryTableData(false)
    },
    handleCurrentChange (val) {
      this.listQuery.page = val
      this._queryTableData(false)
    },
    // 新增
    handleAdd () {
      this.hackReset = false
      this.$nextTick(() => {
        this.hackReset = true
        // 如果为新增则清空数据传递
        this.isAdd = true
        this.currentRowData = null
        this.editRoleDialogVisible = true
      })
    },
    // 编辑
    handleEdit (row) {
      this.hackReset = false
      this.$nextTick(() => {
        this.hackReset = true
        this.isAdd = false
        this.currentRowData = row
        this.editRoleDialogVisible = true
      })
    },
    // 删除
    handleDelete (row) {
      this.$confirm('确定删除该部门？, 是否继续?', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const param = {roleId: row.id}
        delRole(param).then(res => {
          if (res.success) {
            this.$message({
              type: 'success',
              message: res.msg
            })

            this._queryTableData(false)
          } else {
            this.$message({
              type: 'warning',
              message: res.msg
            })
          }
        }).catch(() => {})
        this.$message({
          type: 'success',
          message: '删除成功!'
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    handlePermission () {
      this.permissionDialogVisible = true
    },
    handleEditRoleDialog (action) {
      if (action && action === 1) {
        this.editRoleDialogVisible = false
        this._queryTableData(false)
      } else if (action && action === 2) {
        this.editRoleDialogVisible = false
      }
    },
    handlePermissionDialog (action) {
      if (action && action === 1) {
        this.permissionDialogVisible = false
      } else if (action && action === 2) {
        this.permissionDialogVisible = false
      }
    },
    _queryTableData (btnQuery) {
      if (btnQuery) {
        this.loadingQueryBtn = true
      }
      this.loadingView = true

      let params = Object.assign({}, this.form, {
        limit: this.listQuery.limit,
        page: this.listQuery.page
      })

      getRoleData(params).then(res => {
        this.tableData = res.data
        this.listQuery.total = res.count
        this.loadingQueryBtn = false
        this.loadingView = false
      })
    },
    _timeFormat (row, column, cellValue) {
      return this.$utils.timeFormat(cellValue)
    },
    _resetForm (formName) {
      this.$refs[formName] && this.$refs[formName].resetFields()
    }
  },
  mounted () {
    this._queryTableData(false)
  }
}
</script>

<style lang="scss" scoped>
</style>
