<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form">
        <el-form size="small" :inline="true" :model="form" ref="form">

          <el-form-item label="部门" prop="deptId">
            <base-dept-cascader v-model="form.deptId"></base-dept-cascader>
          </el-form-item>

          <el-form-item label="员工名称" prop="userName">
            <el-input v-model="form.userName" placeholder="请输入角色名称"></el-input>
          </el-form-item>

          <el-form-item label="员工编号" prop="employeeNum">
            <el-input v-model="form.employeeNum" placeholder="请输入角色名称"></el-input>
          </el-form-item>

          <el-form-item label="员工电话" prop="mobile">
            <el-input v-model="form.mobile" placeholder="请输入角色名称"></el-input>
          </el-form-item>

          <el-form-item label="状态" prop="isLocked">
            <el-select clearable style="width: 100%" v-model="form.isLocked" placeholder="请选择员工状态">
              <el-option label="正常" value="01"></el-option>
              <el-option label="锁定" value="0"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="handleQuery" :loading="loadingQueryBtn">查询</el-button>
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
        height="580"
        style="width: 100%"
      >
        <el-table-column
          type="index"
          width="50"
          align="center"
        >
        </el-table-column>

        <el-table-column
          prop="realName"
          align="center"
          label="员工名称">
        </el-table-column>

        <el-table-column
          prop="deptName"
          align="center"
          label="所在部门">
        </el-table-column>

        <el-table-column
          prop="sexName"
          align="center"
          label="性别"
          width="80"
        >
        </el-table-column>

        <el-table-column
          prop="birthDate"
          align="center"
          label="出生日期"
          :formatter="_timeFormat"
        >
        </el-table-column>

        <el-table-column
          prop="roleName"
          align="center"
          label="员工角色">
        </el-table-column>

        <el-table-column
          prop="mobile"
          align="center"
          label="手机号码">
        </el-table-column>

        <el-table-column
          prop="employee_num"
          align="center"
          label="员工编号">
        </el-table-column>

        <el-table-column
          prop="postName"
          align="center"
          label="职位">
        </el-table-column>

        <el-table-column
          prop="statusName"
          align="center"
          label="状态">

          <template slot-scope="scope">
            <el-tag
              :type="scope.row.statusName === '正常' ? 'success' : 'warning'"
              disable-transitions>{{scope.row.statusName}}</el-tag>
          </template>

        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
        >

          <template slot-scope="scope">
            <el-dropdown trigger="click" @command="handleCommand" style="cursor: pointer;color: #409eff;">
                  <span class="el-dropdown-link">
                    选择操作<i class="el-icon-arrow-down el-icon--right"></i>
                  </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item
                  v-for="item in operations"
                  :key="item.value"
                  :command="{val: item.value, row: scope.row}"
                >
                  {{item.label}}
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
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
        title="用户编辑"
        :visible.sync="editRoleDialogVisible"
        width="650px">
        <user-edit v-if="hackReset" :data="currentRowData" :isAdd="isAdd" @handleClick="handleEditRoleDialog"></user-edit>
      </el-dialog>

      <el-dialog
        title="用户权限设置"
        :visible.sync="permissionDialogVisible"
        width="800px">
        <user-permission @handleClick="handlePermissionDialog"></user-permission>
      </el-dialog>

      <el-dialog
        title="用户信息转移"
        :visible.sync="DataTransferDialogVisible"
        width="600px">
        <data-transfer @handleClick="handleDataTransferDialog"></data-transfer>
      </el-dialog>

      <el-dialog
        title="数据更新"
        :visible.sync="DataUpdateDialogVisible"
        width="600px">
        <data-update @handleClick="handleDataUpdateDialog"></data-update>
      </el-dialog>

      <el-dialog
        title="重置密码"
        :visible.sync="resetPwdDialogVisible"
        width="400px">

        <el-form :model="resetPwdForm" :rules="resetPwdRules" ref="resetPwdForm" label-width="100px" class="demo-form">
          <el-form-item label="员工姓名" prop="name">
            <el-input disabled v-model="form.name"></el-input>
          </el-form-item>

          <el-form-item label="新密码" prop="password">
            <el-input type="password" v-model="form.password"></el-input>
          </el-form-item>

          <el-form-item label="确认密码" prop="confirmPassword">
            <el-input type="password" v-model="form.confirmPassword"></el-input>
          </el-form-item>

          <div class="btn-group">
            <el-button type="primary" @click="handleResetPwdSubmit">确认</el-button>
            <el-button @click="handleResetPwdCancel">取消</el-button>
          </div>

        </el-form>

      </el-dialog>
    </template>

  </div>
</template>

<script>
import UserEdit from './components/UserEdit'
import UserPermission from '../components/ThePermissionManage'
import DataTransfer from './components/DataTransfer'
import DataUpdate from './components/DataUpdate'
import BaseDeptCascader from '@/components/BaseCascader/dept'
import {getUserAllDataWithPage} from '@/request/manage/user'

// 操作下拉列表
const OPERATIONS = {
  EDIT: 'EDIT',
  PERMISSION: 'PERMISSION',
  DATA_TRANSFER: 'DATA_TRANSFER',
  DATA_UPDATE: 'DATA_UPDATE',
  RESET_PASSWORD: 'RESET_PASSWORD',
  LOCKING: 'LOCKING'
}

export default {
  name: 'userManage',
  components: {UserEdit, UserPermission, DataTransfer, DataUpdate, BaseDeptCascader},
  data () {
    return {
      form: {
        userName: '',
        employeeNum: '',
        mobile: '',
        isLocked: '',
        deptId: ''
      },
      tableData: [
        {
          realName: '张三',
          createDate: '2018-07-21',
          address: 1,
          deptName: '业务部',
          statusName: '正常',
          gender: 1,
          birthDate: '1991-05-24',
          mobile: 15587098269,
          roleName: '经纪人',
          employee_num: '134534'
        }
      ],
      resetPwdForm: {
        name: '',
        password: '',
        confirmPassword: ''
      },
      resetPwdRules: {
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        confirmPassword: [
          { required: true, message: '请输入确认密码', trigger: 'blur' }
        ]
      },
      editRoleDialogVisible: false,
      permissionDialogVisible: false,
      resetPwdDialogVisible: false,
      DataTransferDialogVisible: false,
      DataUpdateDialogVisible: false,
      loadingQueryBtn: false,
      loadingView: false,
      listQuery: {
        page: 1,
        limit: 10,
        total: 0
      },
      // 操作下拉列表
      operations: [
        {
          value: 'EDIT',
          label: '编辑'
        },
        {
          value: 'PERMISSION',
          label: '权限设置'
        },
        {
          value: 'DATA_TRANSFER',
          label: '信息转移'
        },
        {
          value: 'DATA_UPDATE',
          label: '数据更新'
        },
        {
          value: 'RESET_PASSWORD',
          label: '重置密码'
        },
        {
          value: 'LOCKING',
          label: '锁定'
        }
      ],
      currentRowData: null, // 当前操作的行数据,
      hackReset: true,
      isAdd: true
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
    handleAdd () {
      this.hackReset = false
      this.$nextTick(() => {
        this.hackReset = true
        this.currentRowData = null
        this.isAdd = true
        this.editRoleDialogVisible = true
      })
    },
    handleEdit (row) {
      this.hackReset = false
      this.$nextTick(() => {
        this.hackReset = true
        this.currentRowData = row
        this.isAdd = false
        this.editRoleDialogVisible = true
      })
    },
    handleDelete () {
      this.$confirm('确定删除该部门？, 是否继续?', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
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
        this._queryTableData(false)
        this.editRoleDialogVisible = false
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
    handleResetPassword () {
      this.resetPwdDialogVisible = true
    },
    handleResetPwdSubmit () {
      this.resetPwdDialogVisible = false
    },
    handleResetPwdCancel () {
      this.resetPwdDialogVisible = false
    },
    handleDataTransfer () {
      this.DataTransferDialogVisible = true
    },
    handleDataTransferDialog (action) {
      if (action && action === 1) {
        this.DataTransferDialogVisible = false
      } else if (action && action === 2) {
        this.DataTransferDialogVisible = false
      }
    },
    handleDataUpdate () {
      this.DataUpdateDialogVisible = true
    },
    handleDataUpdateDialog (action) {
      if (action && action === 1) {
        this.DataUpdateDialogVisible = false
      } else if (action && action === 2) {
        this.DataUpdateDialogVisible = false
      }
    },
    /**
     * 操作下拉选择事件
     * @param command 选中项指令
     */
    handleCommand (command) {
      const val = command.val
      const row = command.row
      switch (val) {
        case OPERATIONS.EDIT: // 编辑
          this.handleEdit(row)
          break
        case OPERATIONS.LOCKING: // 锁定
          this.handleDelete(row.id)
          break
        case OPERATIONS.PERMISSION: // 权限
          this.handlePermission(row.id)
          break
        case OPERATIONS.DATA_TRANSFER: // 数据转移
          this.handleDataTransfer(row.id)
          break
        case OPERATIONS.DATA_UPDATE: // 数据更新
          this.handleDataUpdate(row.id)
          break
        case OPERATIONS.RESET_PASSWORD: // 重置密码
          this.handleResetPassword(row.id)
          break
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

      getUserAllDataWithPage(params).then(res => {
        this.tableData = res.data
        this.listQuery.total = res.count
        this.loadingQueryBtn = false
        this.loadingView = false
      })
    },
    _timeFormat (row, column, cellValue) {
      return this.$utils.timeFormat(cellValue, '{y}-{m}-{d}')
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
