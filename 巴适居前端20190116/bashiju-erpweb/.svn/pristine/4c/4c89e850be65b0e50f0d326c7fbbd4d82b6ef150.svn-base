<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form">
        <el-form size="small" ref="form" :inline="true" :model="form">
          <el-form-item label="角色名称" prop="roleName">
            <el-input v-model="form.roleName" placeholder="请输入角色名称"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click.native.prevent="handleQuery" :loading="loadingQueryBtn">查询</el-button>
            <el-button @click.native.prevent="_resetForm('form')">重置</el-button>
          </el-form-item>

          <el-form-item class="pull-right">
            <el-button v-hasOnlyBtn="PERMISSION_BTN.ADD" type="primary" @click="handleAdd">新增</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <div class="page-content-bd" v-loading="loadingView">
      <el-table
        size="small"
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
            <el-button v-hasMultipleBtn="[PERMISSION_BTN.AUTHORIZATION, scope.row]" type="text" size="small" @click="handlePermission(scope.row)">角色授权</el-button>
            <el-button v-hasMultipleBtn="[PERMISSION_BTN.EDIT, scope.row]" @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
            <el-button v-hasMultipleBtn="[PERMISSION_BTN.DELETE, scope.row]" @click="handleDelete(scope.row)" type="text" size="small">删除</el-button>
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
        :close-on-click-modal="false"
        :modal-append-to-body="false"
        :visible.sync="editRoleDialogVisible"
        width="400px">
        <role-edit v-if="hackReset" @handleClick="handleEditRoleDialog" :data="currentRowData" :roleGroup="roleGroup" :isAdd="isAdd"></role-edit>
      </el-dialog>

      <el-dialog
        title="角色权限设置"
        :visible.sync="permissionDialogVisible"
        width="1150px"
        top="8vh"
        :close-on-click-modal="false"
      >
        <role-permission v-if="hackPerReset" action="role" :params="permissionParams" @handleClick="handlePermissionDialog"></role-permission>
      </el-dialog>
    </template>

  </div>
</template>

<script>
  import PageList from '@/mixins/pageList' // 列表页面查询 mixin
  import RoleEdit from './components/RoleEdit'
import RolePermission from '../components/ThePermissionManage'
import {getRoleData, delRole} from '@/request/manage'

// 权限按钮
const PERMISSION_BTN = {
  ADD: 'roleAdd',
  EDIT: 'roleEdit',
  AUTHORIZATION: 'authorization',
  DELETE: 'roleDel'
}

export default {
  name: 'roleManage',
  mixins: [PageList],
  components: {RoleEdit, RolePermission},
  data () {
    return {
      PERMISSION_BTN: PERMISSION_BTN,
      form: {
        roleName: ''
      },
      tableData: [],
      editRoleDialogVisible: false,
      permissionDialogVisible: false,
      loadingQueryBtn: false,
      currentRowData: null,
      hackReset: true,
      hackPerReset: true,
      isAdd: false,
      permissionParams: null
    }
  },
  methods: {
    handleQuery () {
      this.listQueryParams = Object.assign({}, this.form)
      this.listQuery.page = 1
      this.listQuery.currentPage = 1
      this._loadData(true)
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
          this.$message({
            type: 'success',
            message: res.msg
          })

          this._loadData(false)

          let message = {
            sourceCode: row.name, // 资源标识
            sourceTypeId: this.$DICT_CODE.LOG.SOURCE_TYPE.MANAGE.ROLE, // 资源类型
            operatTypeId: this.$DICT_CODE.LOG.OPERATE_TYPE.DELETE, // 操作类型
            logContent: `删除角色：${row.name}` // 日志内容
          }
          this.$updateLog.manage.manageDelLog({message: JSON.stringify(message)})
        })

      })
    },

    // 权限
    handlePermission (data) {
      this.hackPerReset = false
      this.$nextTick(() => {
        this.hackPerReset = true
        this.currentRowData = data
        this.permissionParams = {
          roleId: data.id,
          roleGroup: data.groups,
          userId: null,
          name: data.name
        }
        this.permissionDialogVisible = true
      })
    },
    handleEditRoleDialog (action) {
      if (action && action === 1) {
        this.editRoleDialogVisible = false
        this._loadData(false)
      } else if (action && action === 2) {
        this.editRoleDialogVisible = false
      }
    },
    handlePermissionDialog (action) {
      if (action && action === 1) {
        this._loadData(false)
        this.permissionDialogVisible = false
      } else if (action && action === 2) {
        this.permissionDialogVisible = false
      }
    },
    _loadData (btnQuery) {
      if (btnQuery) {
        this.loadingQueryBtn = true
      }
      this.loadingView = true

      let params = Object.assign({}, this.listQueryParams, {
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
  computed: {
    roleGroup () {
      return this.$store.getters['userInfo'].roleGroup
    }
  },
}
</script>

<style lang="scss" scoped>
</style>
