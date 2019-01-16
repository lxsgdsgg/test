<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form" ref="query-form">
        <el-form size="small" :inline="true" :model="form">

          <el-form-item label="公司名称">
            <el-input v-model="form.name" placeholder="请输入公司名称"></el-input>
          </el-form-item>

          <el-form-item label="法人证件号码">
            <el-input v-model="form.doccode" placeholder="请输入法人证件号码"></el-input>
          </el-form-item>

          <el-form-item label="法代名称">
            <el-input v-model="form.legrepname" placeholder="请输入法代名称"></el-input>
          </el-form-item>

          <el-form-item label="法代手机号">
            <el-input v-model="form.legdocmobile" placeholder="请输入法代手机号"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="handleQuery" :loading="loadingQueryBtn">查询</el-button>
            <el-button>重置</el-button>
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
      >

        <el-table-column
          prop="name"
          align="center"
          label="公司名称">
        </el-table-column>

        <el-table-column
          prop="doccode"
          align="center"
          label="证件号码">
        </el-table-column>

        <el-table-column
          prop="legrepname"
          align="center"
          label="法代名称">
        </el-table-column>

        <el-table-column
          prop="contname"
          align="center"
          label="联系人">
        </el-table-column>

        <el-table-column
          prop="contphone"
          align="center"
          label="联系电话">
        </el-table-column>

        <el-table-column
          prop="status"
          align="center"
          label="状态">

          <template slot-scope="scope">
            <el-tag
              :type="scope.row.status === '01' ? 'success' : 'warning'"
              disable-transitions>{{scope.row.status}}</el-tag>
          </template>

        </el-table-column>

        <el-table-column
          prop="operator"
          align="center"
          label="操作人">
        </el-table-column>

        <el-table-column
          prop="addTime"
          align="center"
          label="新增时间">
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
        >
          <template slot-scope="scope">
            <el-button @click="handleEdit" type="text" size="small">编辑</el-button>
            <el-button @click="handleDelete" type="text" size="small">删除</el-button>
          </template>
        </el-table-column>

      </el-table>
    </div>

    <template>
      <el-dialog
        title="编辑"
        :visible.sync="editDialogVisible"
        width="600px">
        <edit @handleClick="handleEditDialog"></edit>
      </el-dialog>
    </template>

  </div>
</template>

<script>
  import Edit from './components/edit'
  // import {getUserAllDataWithPage} from '@/request/manage/user'

export default {
  name: 'companyManage',
  components: {Edit, DataTransfer},
  data () {
    return {
      form: {
        name: '',
        legdocmobile: '',
        legrepname: '',
        doccode: ''
      },
      tableData: [
        {
          name: '巴适居网络服务有限公司',
          legDoctype: '01',
          doccode: '330106000109206',
          legrepname: '张三',
          contname: '李四',
          contphone: '15587098269',
          status: 1,
          operator: '超级管理员13320',
          addTime: 1530583133000
        }
      ],
      editDialogVisible: false,
      loadingQueryBtn: false,
      loadingView: false,
      listQuery: {
        page: 1,
        limit: 10,
        total: 0
      }
    }
  },
  methods: {
    handleQuery () {
      console.log(this.form.deptName)
    },
    handleAdd () {
      this.editDialogVisible = true
    },
    handleEdit () {
      this.editDialogVisible = true
    },
    handleDelete () {
      this.$confirm('确定删除该数据？, 是否继续?', {
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
    handleEditDialog (action) {
      if (action && action === 1) { // 确定
        this.editDialogVisible = false
      } else if (action && action === 2) { // 取消
        this.editDialogVisible = false
      }
    },
    _queryTableData (btnQuery) {
      if (btnQuery) {
        // this.loadingQueryBtn = true
      }
      // this.loadingView = true

      // let params = Object.assign({}, {
      //   limit: this.listQuery.limit,
      //   page: this.listQuery.page
      // })

      // getUserAllDataWithPage(params).then(res => {
      //   this.tableData = res.data
      //   this.listQuery.total = res.count
      //   this.loadingQueryBtn = false
      //   this.loadingView = false
      // })
    }
  },
  mounted () {
    this._queryTableData(false)
  }
}
</script>

<style lang="scss" scoped>
</style>
