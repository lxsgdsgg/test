<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form" ref="query-form" >
        <el-form size="small" :inline="true" :model="form" ref="form">

          <el-form-item prop="name">
            <el-input v-model="form.name" placeholder="公司名称"></el-input>
          </el-form-item>

          <el-form-item  prop="doccode">
            <el-input v-model="form.doccode" placeholder="法人证件号码"></el-input>
          </el-form-item>

          <el-form-item  prop="legrepname">
            <el-input v-model="form.legrepname" placeholder="法代名称"></el-input>
          </el-form-item>

          <el-form-item prop="legdocmobile">
            <el-input v-model="form.legdocmobile" placeholder="法代手机号"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click.native.prevent="queryCompanyList(true)" :loading="loadingQueryBtn">查询</el-button>
            <el-button @click.native.prevent="resetForm" >清空</el-button>
          </el-form-item>

          <el-form-item class="pull-right">
            <el-button  v-hasOnlyBtn="'addBtn'" type="primary" @click="handleAdd">新增</el-button>
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
        size="small"
      >

        <el-table-column
          prop="id"
          align="center"
          label="公司编号"
        >
        </el-table-column>

        <el-table-column
          prop="name"
          align="center"
          label="公司名称"
          show-overflow-tooltip
        >
        </el-table-column>

          <el-table
          prop="Doctype"
          align="center"
          label="证件类型"
          ></el-table>

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
          prop="operator"
          align="center"
          label="操作人">
        </el-table-column>

        <el-table-column
          prop="addTime"
          align="center"
          label="新增时间"
          :formatter="timeFormat"
        >
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
        >
          <template slot-scope="scope">
            <el-button  v-hasMultipleBtn="['editBtn',scope.row]" @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
            <el-button  v-hasMultipleBtn="['delBtn',scope.row]" @click="handleDelete(scope.row)" type="text" size="small">删除</el-button>
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
        title="编辑"
        :visible.sync="editDialogVisible"
        width="650px">
        <edit @handleClick="handleEditDialog" v-if="hackReset" :data="currentRowData" :isAdd="isAdd"></edit>
      </el-dialog>
    </template>

  </div>
</template>

<script>
  import Edit from './components/edit'
  import {queryCompanyData,delCompany,updateCompany} from '@/request/manage/company'
  import * as RequeryLogUrl from '@/request/log/systemPlatformLog'//客源日志统一接口

  export default {
  name: 'company',
    components: {Edit, DataTransfer},
    data () {
    return {
      form: {
        name: '',
        legdocmobile: '',
        legrepname: '',
        doccode: ''
      },
      tableData: [],
      editDialogVisible: false,
      loadingQueryBtn: false,
      loadingView: false,
      currentRowData:'',
      hackReset: true,
      isAdd:false,
      listQuery: {
        page: 1,
        limit: 10,
        total: 0
      },
      rules:[

      ]
    }
  },
  methods: {
    handleSizeChange(val){
      this.listQuery.limit = val
      this.queryCompanyList(true);
    }
    ,
    handleQuery () {
      console.log(this.form.deptName)
    },
    handleAdd () {
      this.hackReset = false
      this.$nextTick(() =>{
        this.hackReset = true
        //新增时清空数据传递
        this.isAdd = true
        this.editDialogVisible = true
        this.currentRowData = null
        this.id =''
      })

    },
    handleEdit (row) {
     this.hackReset=false
     this.$nextTick(() =>{
       this.hackReset= true
       this.isAdd = false
       this.id = row.id
       //得到当前行的值
       this.currentRowData = row
       this.editDialogVisible = true
     })

    },
    handleDelete (row) {
        this.$confirm('确定删除该公司？',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(()=>{
            const params = {companyId: row.id}
            delCompany(params).then(res=>{
               if(res.success){
                 this.$message({type: 'success', message: res.msg})
                  let message = {
                    sourceCode:  row.name,//资源编号：客源编号
                    sourceTypeId:20,//类型：公司管理
                    operatTypeId: 3,//操作类型 删除,
                    logContent: '删除公司'+row.name//日志内容
                  }
                  RequeryLogUrl.systemDelLog({message:JSON.stringify(message)}).then(res=>{
                     console.log(res)
                  }).catch(error =>{
                       console.log(error)
                  })
                  this.queryCompanyList(true)
              }else{
                   this.$message({type: 'warning', message: res.msg})
                  }
               }).catch((error)=> {
                   console.log(error)
               })
        }).catch(()=>{
          this.$message({type: 'info', message: '已取消'})
        })

    },
    handleCurrentChange(val){
      this.listQuery.page = val
      this.queryCompanyList(true)
    },
    //编辑页面弹出
    handleEditDialog (action) {
      if (action && action === 1) { // 确定
        this.editDialogVisible = false
        this.queryCompanyList()
      } else if (action && action === 2) { // 取消
        this.editDialogVisible = false
      }
    },
    queryCompanyList (loading) {
      if(loading){
        this.listQuery.page = 1
        this.listQuery.currentPage = 1
      }
        this.loadingQueryBtn = loading
        this.loadingView = true
        let params = Object.assign({},this.form,{
          limit:this.listQuery.limit,
          page:this.listQuery.page,
        })
        queryCompanyData(params).then(res => {
           this.tableData = res.data
           this.listQuery.total = res.count
           this.loadingView = false
          this.loadingQueryBtn = false
      }).catch(() =>{
          this.loadingQueryBtn = false
          this.loadingView = false
      })
    },
    resetForm(){
      this.$refs['form'].resetFields()
    },
    _queryTableData (btnQuery) {

    },
    handleChangeCity (val, name) {
      this.form.regaddrcity = name
    },
    /***
     * 时间格式化
     * @param row 行dom对象
     * @param column 列dom对象
     * @param cellValue 单元格值
     */
    timeFormat(row,column,cellValue){
      return this.$utils.timeFormat(cellValue)
    },
    checkCompanyIdIsExist(){
    },
  },
  mounted () {
    this.queryCompanyList();
  }
}
</script>

<style lang="scss" scoped>
</style>
