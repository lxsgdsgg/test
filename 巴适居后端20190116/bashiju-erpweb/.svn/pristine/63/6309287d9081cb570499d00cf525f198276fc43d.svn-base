<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form" ref="query-form">
        <el-form size="small" :inline="true" :model="queryForm" class="demo-form-inline" ref="queryForm">
          <el-form-item label="公司名称">
            <!--<base-company-select size="small" v-model="queryForm.companyId"></base-company-select>-->
            <el-select style="width: 100%" v-model="queryForm.companyId">
              <el-option value="">--请选择--</el-option>
              <el-option
                v-for="item in companyOpts"
                :key="item.name"
                :label="item.name"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="部门名称">
            <el-input v-model="queryForm.deptName" placeholder="请输入内容"></el-input>
          </el-form-item>
          <el-form-item label="部门类型">
            <!--<base-dept-type-select size="small"></base-dept-type-select>-->
            <el-select style="width: 100%" v-model="queryForm.deptTypeId">
              <el-option value="">--请选择--</el-option>
              <el-option
                v-for="item in deptTypeOps"
                :key="item.name"
                :label="item.name"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click.native.prevent="queryDeptListData(true)" :loading="queryBtnLoading">查询</el-button>
            <el-button @click.native.prevent="resetQuery">重置</el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="handleAdd">新增</el-button>
          </el-form-item>
        </el-form>
      </div>

    </div>
    <div class="page-content-bd" v-loading="loadingView">
      <el-table :data="tableData" border align="center" style="width: 100%">
        <el-table-column
          prop="id"
          width="100"
          align="center"
          label="ID">
        </el-table-column>

        <el-table-column
          prop="name"
          align="center"
          label="部门名称">
        </el-table-column>

        <el-table-column
          prop="companyName"
          width="400"
          align="center"
          label="公司名称">
        </el-table-column>

        <el-table-column
          prop="deptTypeName"
          width="100"
          align="center"
          label="部门类型">
        </el-table-column>

        <el-table-column
          prop="parentName"
          align="center"
          label="上级名称">
        </el-table-column>

        <el-table-column
          prop="sortNo"
          align="center"
          width="80"
          label="排序码">
        </el-table-column>

        <el-table-column
          prop="areaCode"
          width="80"
          align="center"
          label="区划编码">
        </el-table-column>
        <el-table-column
          prop="operator"
          align="center"
          label="操作人">
        </el-table-column>

        <el-table-column
          prop="addTime"
          align="center"
          :formatter="timeFormat"
          label="添加时间">
        </el-table-column>
        <el-table-column
          prop="updateTime"
          align="center"
          :formatter="timeFormat"
          label="修改时间">
        </el-table-column>

        <el-table-column
          prop="leaderName"
          align="center"
          label="领导人">
        </el-table-column>
        <el-table-column align="center" label="操作">
          <template slot-scope="scope">
            <el-button @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
            <el-button @click="handleDelete(scope.row)" type="text" size="small">删除</el-button>
            <el-button type="text" size="small" @click="handlePermission">跨店权限</el-button>
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

    <template>
      <el-dialog
        title="修改部门"
        :visible.sync="editDeptDialogVisible"
        :close-on-click-modal="false"
        width="400px"
      >
        <dept-edit v-if="hackReset" @handleClick="handleEditDeptDialog" :data="currentRowData" :isAdd="isAdd"></dept-edit>
      </el-dialog>

      <el-dialog
        title="跨部权限设置"
        :visible.sync="permissionDialogVisible"
        width="600px">
        <dept-permission @handleClick="handlePermissionDialog"></dept-permission>
      </el-dialog>
    </template>
  </div>
</template>

<script>
  import DeptEdit from './components/DeptEdit'
  import DeptPermission from './components/Permission'
  import BaseDeptCascader from '@/components/BaseCascader/dept'
  import BaseCompanySelect from '@/components/BaseSelect/companySelect'
  import BaseDeptTypeSelect from '@/components/BaseSelect/DeptTypeSelect'
  import {
    queryCompanySelectData,
    queryDeptList,
    saveOrUpdateDept,
    delDepartment,
    queryDeptType
  } from '@/request/manage/deptmanage'

  export default {
    name: 'dept',
    components: {DeptEdit, DeptPermission, BaseDeptCascader, BaseCompanySelect,BaseDeptTypeSelect},
    data() {
      return {
        loadingView: false,
        companyId: '',
        deptTypeId: '',
        tableData: [],
        companyOpts: [],
        deptTypeOps: [],
        currentRowData: '',
        hackReset: true,
        editDeptDialogVisible: false,
        permissionDialogVisible: false,
        queryBtnLoading: false,
        isAdd: false,
        treeData: [],
        treeProps: {
          children: 'children',
          label: 'label'
        },
        queryForm: {
          deptName: '',
          companyId: '',
          deptTypeId: ''
        },
        listQuery: {
          page: 1,
          limit: 10,
          total: 0
        }
      }
    },
    methods: {
      //页面改变
      handleSizeChange(val) {
        this.listQuery.limit = val
        this.queryDeptListData(false)
      },
      /**
       * 获取部门列表数据
       */
      queryDeptListData: function (isloding) {
        this.queryBtnLoading = isloding
        this.loadingView = true
        let params = Object.assign({}, this.queryForm, {
          page: this.listQuery.page,
          limit: this.listQuery.limit
        })
        queryDeptList(params).then((res) => {
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
      //当前页改变
      handleCurrentChange(val) {
        this.listQuery.page = val
        this.queryDeptListData(false)
      },
      /**
       * 新增部门
       */
      handleAdd() {
        this.hackReset = false
        this.letTick(() => {
          this.hackReset = true
          // 如果为新增则清空数据传递
          this.isAdd = true
          this.deptTypeId = ''
          this.companyId = ''
          this.currentRowData = null
          this.editDeptDialogVisible = true
        })
      },
      /**
       * 修改部门
       */
      handleEdit(row) {
        this.hackReset = false
        this.$letTick(() => {
          this.hackReset = true
          this.isAdd = false
          this.companyId = row.companyId
          this.deptTypeId = row.deptTypeId
          this.currentRowData = row
          this.editDeptDialogVisible = true
        })
      },
      /**
       * 删除部门
       */
      handleDelete(row) {
        this.$confirm('确定删除该部门？', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          const param = {deptId: row.id}
          delDepartment(param).then(res => {
            if (res.success) {
              this.$message({
                type: 'success',
                message: res.msg
              })

              this.queryDeptListData(false)
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
      handlePermission() {
        this.permissionDialogVisible = true
      },
      /**
       * 编辑部门弹出窗口
       * @param action
       */
      handleEditDeptDialog(actionType) {
        if (actionType && actionType === 1) {
          this.editDeptDialogVisible = false
          this.queryDeptListData(true)
        } else if (actionType && actionType === 2) {
          this.editDeptDialogVisible = false
        }else{
          this.$message({
            type: 'info',
            message: '暂不支持此操作'
          })
        }
      },
      /**
       * 跨店权限弹窗
       * @param action
       */
      handlePermissionDialog(action) {
        if (action && action === 1) {
          this.permissionDialogVisible = false
        } else if (action && action === 2) {
          this.permissionDialogVisible = false
        }
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
       * 查询条件重置
       */
      resetQuery: function () {
        this.$refs['queryForm'].resetFields()
      },
      getCompanyDataSelect: function () {
        let params = Object.assign({},{}, {})
        queryCompanySelectData(params).then((res) => {
          this.companyOpts = res
        }).catch(err => {
          console.log(err)
        })
      },
      getDeptTypeSelect: function () {
        let params = {}
        queryDeptType(params).then((res) => {
          this.deptTypeOps = res
        }).catch(err => {
          console.log(err)
        })
      }
    },
    mounted() {
      //挂在页面时调用列表数据读取
      this.queryDeptListData(false)
      this.getDeptTypeSelect()
      this.getCompanyDataSelect()
    }
  }
</script>

<style scoped lang="scss">

</style>
