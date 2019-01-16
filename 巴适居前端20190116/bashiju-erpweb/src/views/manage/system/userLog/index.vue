<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form" ref="query-userLog">
        <el-form size="small" :inline="true" :model="queryForm" ref="queryForm">
          <el-form-item label="操作类型" prop="operationType">
            <el-input v-model.trim="queryForm.operationType" placeholder="操作类型"></el-input>
          </el-form-item>


          <el-form-item label="资源类型" prop="sourceType">
            <el-input v-model.trim="queryForm.sourceType" placeholder="资源类型"></el-input>
          </el-form-item>
          <el-form-item label="添加时间">

            <el-date-picker
              v-model="time"
              type="daterange"
              value-format="yyyy-MM-dd HH:mm:ss"
              range-separator="至"
              :default-value="ct_month"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              align="right"
              show-overflow-tooltip
              :default-time="['00:00:00', '23:59:59']"
            >
            </el-date-picker>

          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click.native.prevent="queryUserLogData(true)" :loading="queryBtnLoading">查询</el-button>
            <el-button @click="resetForm">清空</el-button>
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
          prop="loginUser"
          align="center"
          label="登录人"
        >
        </el-table-column>


        <el-table-column
          prop="deptId"
          align="center"
          label="部门编号"
        >
        </el-table-column>


        <el-table-column
          prop="deptName"
          align="center"
          label="部门名称"
        >
        </el-table-column>

        <el-table-column
          prop="companyId"
          align="center"
          label="公司编号"
        >
        </el-table-column>

        <el-table-column
          prop="companyName"
          align="center"
          label="公司名称"
          show-overflow-tooltip
        >
        </el-table-column>


        <el-table-column
          prop="loginIp"
          align="center"
          label="登陆ip"
        >
        </el-table-column>

        <el-table-column
          prop="sourceType"
          align="center"
          label="资源类型"
        >
        </el-table-column>

        <el-table-column
          prop="sourceId"
          align="center"
          label="资源编号"
        >
        </el-table-column>



        <el-table-column
          prop="operationType"
          align="center"
          label="操作类型"
        >
        </el-table-column>
        <el-table-column
          prop="actionTime"
          align="center"
          show-overflow-tooltip
          label="执行时间"
        >
        </el-table-column>
        <el-table-column
          prop="excuteTime"
          align="center"
          :formatter="_timeFormat"
          show-overflow-tooltip
          label="执行日期"
        >
        </el-table-column>

        <el-table-column
          prop="excuteContext"
          align="center"
          label="执行内容"
        >
        </el-table-column>

        <el-table-column
          prop="addTime"
          align="center"
          :formatter="_timeFormat"
          label="添加时间"
          show-overflow-tooltip
        >
        </el-table-column>

        <el-table-column
          prop="updateTime"
          align="center"
          :formatter="_timeFormat"
          label="修改时间"
          show-overflow-tooltip
        >
        </el-table-column>

        <el-table-column
          prop="operator"
          align="center"
          label="操作人"
        >
        </el-table-column>

      </el-table>
      <!--分页控件-->
      <b-pagination
        :listQuery="listQuery"
        @handleSizeChange="handleSizeChange"
        @handleCurrentChange="handleCurrentChange">
      </b-pagination>
    </div>
  </div>
</template>

<script>
  import pageList from '@/mixins/pageList'
  import {queryUserLogData} from '@/request/manage/userLog'


  export default {
      name:'userLog',
      mixins:[pageList],
        data(){
          return {
            queryForm:{
              sourceType:'',
              operationType:''
            },
            pickerOptions2: {
              shortcuts: [{
                text: '最近一周',
                onClick(picker) {
                  const end = new Date();
                  const start = new Date();
                  start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                  picker.$emit('pick', [start, end]);
                }
              }, {
                text: '最近一个月',
                onClick(picker) {
                  const end = new Date();
                  const start = new Date();
                  start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                  picker.$emit('pick', [start, end]);
                }
              }, {
                text: '最近三个月',
                onClick(picker) {
                  const end = new Date();
                  const start = new Date();
                  start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                  picker.$emit('pick', [start, end]);
                }
              }]
            },
            tableData:[],
            time:'',
            loadingView:false,
            queryBtnLoading:false,
            ct_month:null,
            listQuery:{
              limit:10,
              page:1,
              count:0
            }
          }
        },methods:{
        _loadData(btnQuery){
          this.queryUserLogData(btnQuery)
        },
        //时间格式化转换
        _timeFormat(row, column, cellValue){
          return this.$utils.timeFormat(cellValue,'{y}-{m}-{d} {h}:{i}:{s}')
        }
        ,
      timeDefaultShow(){//默认显示上个月
        this.ct_month= new Date();
        this.ct_month.setTime(this.ct_month.getTime() - 3600 * 1000 * 24 * 30);
      },
          queryUserLogData(isloading){
            if(isloading){
              this.queryBtnLoading = isloading
              this.listQuery.page = 1
              this.listQuery.currentPage = 1
            }

              this.loadingView = isloading

              //设置参数询参数
            //拆分时间并绑定包queryForm
        /*      this.queryForm.addTime = this.time*/
            //拆分时间并绑定包queryForm

              let params = Object.assign({},this.queryForm,{
                page:this.listQuery.page,
                limit:this.listQuery.limit
              })
            //把数组用逗号分割为字符窜
            if(this.time!='' && this.time!=null){
              params.addTime =  this.time.join(',')
            }
            queryUserLogData(params).then(res =>{
              //渲染数据
                this.tableData = res.data
                 this.listQuery.total = res.count
                this.loadingView = false
              this.queryBtnLoading = false
            }).catch(error =>{
              console.log(error)
              this.loadingView = false
              this.queryBtnLoading = false
            })
          },
        resetForm(){
          this.$refs['queryForm'].resetFields()
          this.time = ''
        }
      },
      mounted(){
          this.queryUserLogData(false)
          this.timeDefaultShow();
      }
    }
</script>

<style scoped>

</style>
