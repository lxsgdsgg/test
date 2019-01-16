<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form" ref="query-form">
        <el-form size="small" :inline="true" :model="queryForm" ref="queryForm">
          <el-form-item label="业绩类型名称" prop="pfmTypeName">
            <el-input v-model="queryForm.pfmTypeName" placeholder="业绩类型名称"></el-input>
          </el-form-item>

          <el-form-item label="所属城市" prop="areaCode">
            <base-city-cascader @click="handleChangeCity" v-model="queryForm.areaCode"></base-city-cascader>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click.native.prevent="queryPerformance(true)" :loading="queryBtnLoading">查询</el-button>

            <el-button @click.native.prevent="resetQueryForm">清空</el-button>
          </el-form-item>

          <el-form-item>
            <el-button v-hasOnlyBtn="'addBtn'"  @click="handleAdd">新增</el-button>
            <!--<el-button v-hasOnlyBtn="'commissionStandardSetBtn'" @click="handleCommissionStandardAdd">佣金标准设置</el-button>-->
          </el-form-item>
        </el-form>
      </div>
    </div>
    <div class="page-content-bd" v-loading="loadingView">
      <el-table
        :data="tableData"
        border
        align="center"
        style="width: 100%">


        <el-table-column
          prop="pfmTypeName"
          align="center"
          label="方案名称"
        >
        </el-table-column>

        <el-table-column
          prop="caculateMethodName"
          align="center"
          label="计算方式"
        >
        </el-table-column>

        <el-table-column
          prop="cityName"
          align="center"
          label="所属城市"
        >
        </el-table-column>

        <el-table-column
          prop="usePfmTypeName"
          align="center"
          label="适用类型名称"
        >
        </el-table-column>

        <el-table-column
          prop="operator"
          align="center"
          label="创建用户"
        >
        </el-table-column>

        <el-table-column
          prop="addTime"
          align="center"
          :formatter="_timeFormat"
          label="新增时间"
        >
        </el-table-column>



        <el-table-column
          prop="updateTime"
          align="center"
          :formatter="_timeFormat"
          label="更新时间"
        >
        </el-table-column>

        <el-table-column
          prop="companyName"
          align="center"
          label="操作">
          <template slot-scope="scope">
              <el-button v-hasMultipleBtn="['editBtn',scope.row]" @click="handleEditor(scope.row)" type="text" size="small" >编辑</el-button>
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

    <!--编辑窗体模板-->
      <template>
          <el-dialog
            title="编辑业绩类型"
            :visible.sync="editorPerformanceDialogVisible"
            :close-on-click-modal="false"
            top="30vh"
            width="600px">
            <performance-editor v-if="hackReset" @handleClick="handleEditorPerformanceDialog" :data="currentRowData"
            :isAdd="isAdd"></performance-editor>
          </el-dialog>

        <el-dialog
          title="选择城市"
          :visible.sync="selectCityDialogVisible"
          :close-on-click-modal="false"
          :before-close="handleClose"
          top="30vh"
          width="400px">
          <el-form :model="commissionForm" :rules="rules" ref="commissionForm" label-width="100px">
            <el-form-item label="城市" placeholder="请选择城市" prop="cityId">
              <base-city-cascader v-model="commissionForm.cityId"></base-city-cascader>
            </el-form-item>

            <el-form-item class="margin-b-none">
              <el-button type="primary" @click="handleSelectCitySubmit" :loding="loadingBtn">确认</el-button>
              <el-button @click="handleCancel">取消</el-button>
            </el-form-item>
          </el-form>
        </el-dialog>


        <el-dialog
          title="新增佣金标准"
          :visible.sync="commissionStandaraAddDialogVisible"
          :close-on-click-modal="false"
          top="30vh"
          width="700px">
          <commission-standard :cityId="commissionForm.cityId"  @handleClick="handleCommissionStandaraAddDialog"
                              :idAdd="isAdd"></commission-standard>
        </el-dialog>

        <!--:before-close="handleCloseCommissionStand"-->
      </template>
  </div>
</template>



<script>
  //分页控件
  import PageList from '@/mixins/pageList'
  import BaseCityCascader from '@/components/BaseCascader/city'
  import {queryPerformance,delPerformance,saveOrEditPfmTypeDetail} from "@/request/manage/performance"
  import performanceEditor from './component/performanceEditor'
  import  commissionStandard from './component/commissionStandard'

  export default {
        name:'performance',
        mixins:[PageList],
        components:{BaseCityCascader,performanceEditor,commissionStandard},
        data() {
            return {
              queryForm:{
                pfmTypeName:'',
                areaCode:''
              },
              commissionForm:{
                cityId:'',
              },
              tableData:[],
              loadingView:false,
              queryBtnLoading:false,
              loadingBtn:false,
              hackReset:true,
              editorPerformanceDialogVisible:false,
              selectCityDialogVisible:false,
              commissionStandaraAddDialogVisible:false,
              isAdd:false,
              currentRowData:'',
              listQuery:{
                limit:10,
                page:1,
                total:0
              },
              rules:{
                cityId:[
                    { required: true, message: '该项为必填', trigger: 'change' }
                  ]
              }
            }
        },
        methods:{
            //分页重写的方法
            _loadData(btnQuery){
                this.queryPerformance(btnQuery)
            },
            //时间格式转化
            _timeFormat(row,culmn,cellValue){
                return this.$utils.timeFormat(cellValue)
            },
          //城市搜索框赋值
            handleChangeCity(val,name){
              this.queryForm.areaCode = val
            },
          handleAddCommissionChangeCity(val,name){
            this.commissionForm.cityId = val
          },
          //新增佣金标准前事件（选择城市）
          handleSelectCitySubmit(){
            this.$refs['commissionForm'].validate((valid) =>{
              if(valid){
                //关闭当前窗体，把选择的城市，传到新增佣金标准的窗体中去
                this.selectCityDialogVisible = false
                //清空城市
                this.commissionStandaraAddDialogVisible = true
              }
            })
          },
          //取消新增佣金标准时城市选择
          handleCancel(){
            this.resetCommissionForm()
            this.selectCityDialogVisible = false
          }
          ,
          //清空搜索框值
          resetQueryForm:function(){
             this.$refs['queryForm'].resetFields()
          },
          //编辑业绩类型
          handleEditor(row){
            //row：得到当前行的数据
            this.hackReset = false
            this.$nextTick(() =>{
              this.hackReset =true
              this.isAdd = false
              //向子组件传值
              this.currentRowData = row
              //加载窗体
              this.editorPerformanceDialogVisible = true
            })
          },
          //新增业绩类型
          handleAdd(){
            this.hackReset = false
            this.$nextTick(() =>{
              this.hackReset = true
              this.isAdd = true
              //新增窗体的数据
              this.currentRowData =null
              this.editorPerformanceDialogVisible = true
            })
          },
          //新增佣金标准
          handleCommissionStandardAdd(){
              //打开新增佣金窗体
              this.selectCityDialogVisible = true
          },
          //删除业绩类型
          handleDelete(row){
            this.$confirm('确定删除业绩类型?',{
              confirmButtonText:'确定',
              cancelButtonText:'取消',
              type:'warning'
            }).then(() =>{
              const  params = {pfmTypeId:row.id}
              delPerformance(params).then(res =>{
                  if(res.success){
                    this.$message({
                      type:'success',
                      message:res.msg
                    })
                    this.queryPerformance(true)
                  }else{
                      this.$message({
                        type:'warning',
                        message:'res.msg'
                      })
                  }
              }).catch(error =>{
                    this.$message({
                      type:'success',
                      message:'删除成功'
                    })
              })
            }).catch(() =>{
              this.$message({
                type:'info',
                message:'已取消删除'
              })
            })
          },
          //窗口开启和关闭的回调
          handleEditorPerformanceDialog(val){
              if(val && val === 1 ){
                this.editorPerformanceDialogVisible = false;
                this.queryPerformance(false)
              }else if(val && val === 2){
                this.editorPerformanceDialogVisible = false;
              }
          },
          //新增佣金标准窗口回调事件
          handleCommissionStandaraAddDialog(value){
            if(value && value === 1 ){
              this.commissionStandaraAddDialogVisible = false;
              this.queryPerformance(false)
              this.$refs['commissionForm'] && this.$refs['commissionForm'].resetFields()
            }else if(value && value === 2){
              this.commissionStandaraAddDialogVisible = false;
            }
          },
          //查询数据
          queryPerformance(isLoading){
            if(isLoading){
              this.queryBtnLoading = isLoading
              this.listQuery.page = 1
              this.listQuery.currentPage = 1
            }
            this.loadingView = true;
            //设置参数
            let params = Object.assign({},this.queryForm,{
                  page:this.listQuery.page,
                  limit:this.listQuery.limit
            })
            queryPerformance(params).then(res =>{
              this.loadingView = false
              this.queryBtnLoading = false
              this.tableData = res.data
              this.listQuery.total = res.count
            }).catch(error =>{
              this.loadingView = false
              this.queryBtnLoading = false
              console.log(error)
            })
          },
           //清空选择的城市
           resetCommissionForm(){
            this.$refs['commissionForm'] && this.$refs['commissionForm'].resetFields()
          },
          handleClose(){
            this.$refs['commissionForm'] && this.$refs['commissionForm'].resetFields()
            this.selectCityDialogVisible = false
          },
          handleCloseCommissionStand(){
            this.$refs['commissionForm'] && this.$refs['commissionForm'].resetFields()
            this.commissionStandaraAddDialogVisible = false
          }
        }
        ,
        mounted(){
          this.queryPerformance(false)
        }
    }

</script>


<style></style>
