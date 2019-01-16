<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form" ref="query-form">
        <el-form size="small" :inline="true" :model="queryForm" ref="queryForm" class="demo-form-inline">

          <el-form-item prop="syncFlag">
            <el-select v-model="queryForm.syncFlag" clearable style="width: 120px;" placeholder="外网同步" >
              <el-option
                v-for="item in syncFlagOpt"
                :key="item.value"
                :label="item.name"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>

          <el-form-item prop="hotAgentFlag">
            <el-select v-model="queryForm.hotAgentFlag" clearable style="width: 120px;" placeholder="热门经纪人" >
              <el-option
                v-for="item in hotAgentFlagOpt"
                :key="item.value"
                :label="item.name"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="showedRdCnt">
              <el-input v-model="queryForm.showedRdCnt" placeholder="近30天带看记录"></el-input>
          </el-form-item>



          <el-form-item>
            <el-button type="success" @click.native.prevent="_loadData(true)" :loading="queryBtnLoading" size="small">查询</el-button>
            <el-button type="info" @click="_resetForm('queryForm')" size="small">清空</el-button>
          </el-form-item>

        </el-form>
      </div>
    </div>

    <div class="page-content-bd" v-loading="loadingView">
      <el-table
        :data="tableData"
        border
        tooltip-effect="light"
        align="center"
        style="width: 100%"
      >


        <!--<el-table-column-->
          <!--prop="userId"-->
          <!--align="left"-->
          <!--label="用户编号">-->
        <!--</el-table-column>-->

        <el-table-column
          prop="userName"
          align="left"
          label="用户名称"
        >
        </el-table-column>


        <el-table-column
          prop="deptName"
          align="left"
          label="部门名称"
        >
        </el-table-column>



        <el-table-column
          prop="showedRdCnt"
          align="center"
          label="近30天带看记录"
        >
        </el-table-column>

        <el-table-column
          prop="histShowedRdCnt"
          align="center"
          label="历史带看记录"
        >
        </el-table-column>

        <el-table-column
          prop="dealCnt"
          align="center"
          label="近30天成交记录"
        >
        </el-table-column>

        <el-table-column
          prop="histDealCnt"
          align="center"
          label="历史成交记录"
        >
        </el-table-column>


        <el-table-column
          prop="StarRating"
          align="center"
          label="星级评分"
        >
        </el-table-column>


        <el-table-column
          prop="commentCnt"
          align="center"
          label="客户历史评价记录"
        >
        </el-table-column>


        <el-table-column
          prop="seniority"
          align="center"
          label="工龄(年)"
        >
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
          fixed="right"
        >
          <template slot-scope="scope">
              <el-button @click="handleWatchMsg(scope.row)" type="text" size="small">留言评价</el-button>
              <el-button @click="handleWatchStar(scope.row)" type="text" size="small">星级评价</el-button>
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
      <!--查看查看留言窗体-->
      <el-dialog
        title="查看留言"
        :visible.sync="msgDlg"
        width="50%"
        :close-on-click-modal="false"
      >
        <detail v-if="hackReset" :data="currentRowData" @handleClick="closeWindows"></detail>
      </el-dialog>
    </template>


    <template>
      <!--查看星级评价窗体-->
      <el-dialog
        title="查看星级评价"
        :visible.sync="starDlg"
        width="55%"
        :close-on-click-modal="false"
      >
        <star-evaluation :data="currentRowStarData" @handleClick="closeStarWindows" v-if="hackReset"></star-evaluation>
      </el-dialog>
    </template>
  </div>
</template>
<style></style>
<script>
  import {queryAllAgentInfoPages} from "@/request/customer/customerMsgEva";
  import PageList from '@/mixins/pageList'
  import Detail from './component/detail'
  import StarEvaluation from './component/starEvaluation'

    export default {
      components:{PageList,Detail,StarEvaluation},
      mixins: [PageList],
      name:'customerMsgEva',
        data() {
            return {
              queryForm: {
                syncFlag:'',
                hotAgentFlag:'',
                showedRdCnt:'',

              },
              tableData:[],
              syncFlagOpt:[
                {name:'是',value:1},
                {name:'否',value:0}
              ],
              hotAgentFlagOpt:[
                {name:'是',value:1},
                {name:'否',value:0}
              ],
              listQuery: {
                limit: 10,
                page: 1,
                total: 0
              },
              queryBtnLoading:false,
              loadingView:false,
              currentRowData:null,
              currentRowStarData:null,
              msgDlg:false,
              starDlg:false,
              hackReset:false
            }
        },
        methods: {
            _loadData(btn){
              this.queryCustomerMsgEva(btn)
            },
            //查询数据
            queryCustomerMsgEva(btn){
              if(btn){
                this.queryBtnLoading = btn
                this.listQuery.page = 1
                this.listQuery.currentPage = 1
              }
              this.loadingView = true
              let params = Object.assign({},this.queryForm,{
                page:this.listQuery.page,
                limit:this.listQuery.limit
              })
              queryAllAgentInfoPages(params).then(res =>{
                  this.tableData = res.data
                  this.listQuery.total = res.count
                  this.loadingView = false
                  this.queryBtnLoading = false
              }).catch(error =>{
                  this.loadingView = false
                  this.queryBtnLoading = false
                  console.log(error)
              })
            },
            //时间戳格式化
            _timeFormat(row,column,cellValue){
              return this.$utils.timeFormat(cellValue)
            },
            //查看留言信息事件
            handleWatchMsg(row){
                this.hackReset = false
                this.$nextTick(() =>{
                  this.hackReset = true
                   this.msgDlg = true
                   this.currentRowData = row
                })
            },
            //查看星级评级
            handleWatchStar(row){
              this.hackReset = false
                this.$nextTick(() =>{
                  this.hackReset = true
                    this.starDlg = true
                    this.currentRowStarData = row
                })
            },
            //窗体回调事件
            closeWindows(){

            },
            closeStarWindows(){

            }
        },
        mounted() {


        }

    }

</script>
