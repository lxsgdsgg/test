<template>
  <div class="page-content">
    <div>
      <el-form :model="queryForm" ref="queryForm" :inline="true" size="mini">
        <el-form-item label="类型" prop="type">
          <el-select v-model="queryForm.type" clearable placeholder="请选择">
            <el-option label="二手房" value="0">二手房</el-option>
            <el-option label="出租房" value="1">出租房</el-option>
            <el-option label="新房" value="2">新房</el-option>
            <el-option label="小区" value="3">小区</el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="_loadData(true)" :loading="loadingQueryBtn">搜索</el-button>
          <el-button type="primary" @click="handlerEdit">新增</el-button>
        </el-form-item>
      </el-form>
    </div>

    <div>
      <el-table :data="tableData" border align="left" v-loading="loadDataView" size="mini">
        <el-table-column
          align="center"
          label="类型">
          <template slot-scope="scope">
            <span v-if="scope.row.type===0">二手房</span>
            <span v-if="scope.row.type===1">出租房</span>
            <span v-if="scope.row.type===2">新房</span>
            <span v-if="scope.row.type===3">小区</span>
          </template>
        </el-table-column>

        <el-table-column
          prop="name"
          align="center"
          label="名称">
        </el-table-column>

        <el-table-column
          prop="minPrice"
          align="center"
          label="最低价格">
          <template slot-scope="scope">{{scope.row.minPrice}}</template>
        </el-table-column>

        <el-table-column
          prop="maxPrice"
          align="center"
          label="最高价格">
          <template slot-scope="scope">{{scope.row.maxPrice}}</template>
        </el-table-column>

        <el-table-column
          align="center"
          label="操作">
          <template slot-scope="scope">
            <el-button type="primary" @click="handlerEdit(scope.row)" size="mini">编辑</el-button>
            <el-button type="primary" @click="handleDel(scope.row)" size="mini">删除</el-button>
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
      <el-dialog :title="title" :visible.sync="showDialogView"
                 width="600px" append-to-body
                 :modal-append-to-body="false">
        <el-form :model="editForm" ref="editForm" label-width="100px" size="small" :rules="rules">
          <el-form-item label="类型" prop="type">
            <el-select v-model="editForm.type" placeholder="请选择类型" clearable style="width: 330px;">
              <el-option label="二手房" value="0"></el-option>
              <el-option label="出租房" value="1"></el-option>
              <el-option label="新房" value="2"></el-option>
              <el-option label="小区" value="3"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="名称" prop="name">
            <el-input v-model="editForm.name" placeholder="名称" style="width: 330px;"></el-input>
          </el-form-item>

          <el-form-item label="最低价格" prop="minPrice">
            <el-input type="number" v-model="editForm.minPrice" placeholder="最低价格" style="width: 330px;"></el-input>
          </el-form-item>

          <el-form-item label="最高价格" prop="maxPrice">
            <el-input type="number" autosize v-model="editForm.maxPrice" placeholder="最高价格" style="width: 330px;"></el-input>
          </el-form-item>

          <div class="btn-group">
            <el-button type="primary" @click="saveCondition" size="small">保存</el-button>
            <el-button @click="showDialogView = false" size="small">取消</el-button>
          </div>
        </el-form>
      </el-dialog>
    </template>
  </div>
</template>

<script>
  import PageList from '@/mixins/pageList'
import {queryCityAvgPriceList, addCityAvgPrice,updateCityAvgPrice,deleteCityAvgPrice}  from '@/request/manage/queryCodition'


  export default {
    name: "ClientPriceSearchQuerySet",
    mixins: [PageList],
    props:{
      data:{
        type: Object,
        required: true,
        default(){
          return null
        }
      }
    },
    data(){
      return{
        title: '新增价格搜索条件配置',
        tableData: [],
        isAdd: true,
        showDialogView: false,
        loadDataView: false,
        loadingQueryBtn: false,
        queryForm:{cityCode: this.data.cityCode, type: ''},
        editForm:{
          type: '',
          name: '',
          minPrice: 0,
          maxPrice: 0
        },
        currentRowData: '',
        rules: {
          type:[{required: true,message: '必填项',trigger: 'change'}],
          name:[{required: true,message: '必填项',trigger: 'blur'}],
          minPrice:[{required: true,message: '必填项',trigger: 'blur'}],
          maxPrice:[{required: true,message: '必填项',trigger: 'blur'}]
        }
      }
    },
    methods:{
      handlerEdit(row){
        if (this.$refs['editForm']){
          this.$refs['editForm'].resetFields();
        }
        this.currentRowData = null
        if(row && row.id){
          this.currentRowData = row
          this.isAdd = false
          this.title = '编辑价格搜索条件配置'
          this.setForm()
        }else{
          this.isAdd = true
          this.title = "新增价格搜索条件配置"
        }
        this.showDialogView = true
      },

      setForm(){
        for(let i in this.editForm){
          this.editForm[i] = this.currentRowData[i]
        }
        if(!this.isAdd){//回显异常，特殊处理
          this.editForm.type = this.currentRowData?String(this.currentRowData.type):''
        }
      },
      _loadData(btnQuery){
        if(btnQuery){
          this.loadingQueryBtn = true
          this.listQuery.currentPage = 1
          this.listQuery.page = 1
        }
        let param = Object.assign({},{...this.queryForm},{
          limit: this.listQuery.limit,
          page: this.listQuery.page
        })
        this.loadDataView = true
        queryCityAvgPriceList(param).then((res)=>{
          if (res.data){
            this.tableData = res.data
            this.listQuery.total = res.count
          }else {
            this.tableData = []
            this.listQuery.total = 0
          }
          this.loadingQueryBtn = false
          this.loadDataView = false
        }).catch(() => {
          this.loadingQueryBtn = false
          this.loadDataView = false
        })
      },

      /**
       * 保存配置信息
       */
      saveCondition(){
        this.$refs['editForm'].validate((valid) => {
          if (valid) {
            if(!this.isAdd){
              this.editForm.id = this.currentRowData.id
            }
            this.editForm.cityCode = this.data.cityCode
            let param = {jsonData:JSON.stringify(this.editForm)}
            if (this.isAdd){
              addCityAvgPrice(param).then((res)=>{
                if (res.success){
                  this.$message({
                    type: 'success',
                    message: res.msg || '操作成功'
                  })
                  this.showDialogView = false
                  this._loadData(false)
                }else {
                  this.$message({
                    type: 'warning',
                    message: res.msg || '操作失败'
                  })
                }
              })
            }else {
              updateCityAvgPrice(param).then((res)=>{
                if (res.success){
                  this.$message({
                    type: 'success',
                    message: res.msg || '操作成功'
                  })
                  this.showDialogView = false
                  this._loadData(false)
                }else {
                  this.$message({
                    type: 'warning',
                    message: res.msg || '操作失败'
                  })
                }
              })
            }
          }
        })
      },

      // 删除价格配置
      handleDel(row){
        this.$confirm('确定要删除吗？', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteCityAvgPrice({id: row.id}).then(res => {
            this._loadData(false)
            this.$message({
              type: 'success',
              message: res.msg || '操作成功'
            })
          }).catch((err)=>{
            console.log(err)
          })
        })
      }
    }
  }
</script>

<style scoped>

</style>
