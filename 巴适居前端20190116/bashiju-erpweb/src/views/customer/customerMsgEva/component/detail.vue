<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form" ref="query-form">
        <el-form size="small" :inline="true" :model="queryForm" ref="queryForm" class="demo-form-inline" >

          <el-form-item prop="messageTypeId">
            <el-select v-model="queryForm.messageTypeId" clearable style="width: 120px;" placeholder="留言类型" >
              <el-option
                v-for="item in messageTypeOpt"
                :key="item.value"
                :label="item.name"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>

          <el-form-item prop="isRead">
            <el-select v-model="queryForm.isRead" clearable style="width: 120px;" placeholder="是否已读" >
              <el-option
                v-for="item in isReadOpt"
                :key="item.value"
                :label="item.name"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="mobile" label="客户电话">
            <el-input v-model="queryForm.mobile"></el-input>
          </el-form-item>



          <el-form-item>
            <el-button type="success" @click.native.prevent="_loadData(true)" :loading="queryBtnLoading" size="small">查询</el-button>
            <el-button type="info" @click="_resetForm('queryForm')" size="small">清空</el-button>
          </el-form-item>

        </el-form>
      </div>
    </div>
    <el-row>
    <div class="page-content-bd" v-loading="loadingView">
         <el-table
        :data="tableData"
        border
        tooltip-effect="light"
        align="center"
        style="width: 100%"
        size="medium"
      >

        </el-table-column>
        <el-table-column
          prop="isReadName"
          align="left"
          width="80"
          label="是否已读">
        </el-table-column>

        <el-table-column
          prop="userId"
          align="left"
          label="用户编号">
        </el-table-column>

        <el-table-column
          prop="agentId"
          show-overflow-tooltip
          align="left"
          label="详情编号">
        </el-table-column>

        <el-table-column
          prop="messageType"
          align="left"
          label="留言类型">
        </el-table-column>

        <el-table-column
          prop="realName"
          align="left"
          label="真实姓名">
        </el-table-column>

        <el-table-column
          prop="mobile"
          align="left"
          label="电话号码"
          show-overflow-tooltip
        >
        </el-table-column>

        <el-table-column
          prop="content"
          align="left"
          width="180"
          label="留言内容"
          show-overflow-tooltip
        >
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
          width="100px"
          fixed="right"
        >
          <template slot-scope="scope">
            <el-button @click="handleReviewLeavingMsg(scope.row)" type="text" size="small" v-if="scope.row.isRead == 0">标记已读</el-button>
            <el-button @click="handleDeleteLeavingMsg(scope.row)" type="text" size="small" >删除</el-button>
          </template>
        </el-table-column>

      </el-table>
        <!--分页控件-->
        <b-pagination
          :listQuery="listQuery"
          @handleSizeChange="handleSizeChange"
          @handleCurrentChange="handleCurrentChange">
        </b-pagination>
    </el-col>

    </div>
    </el-row>
    <!--<template>-->
      <!--&lt;!&ndash;修改&ndash;&gt;-->
      <!--<el-dialog-->
        <!--title="修改定金"-->
        <!--:visible.sync="updateDlg"-->
        <!--width="33%"-->
        <!--:close-on-click-modal="false"-->
        <!--append-to-body-->
      <!--&gt;-->
        <!--<update-deposit v-if="hackReset" :data="currentRowData"   @handleClick="windowReturn"></update-deposit>-->
      <!--</el-dialog>-->
    <!--</template>-->
  </div>
</template>
<style></style>
<script>
     import PageList from '@/mixins/pageList'
     import {queryAllCustomerLeavingMsg,queryAllUnreviewedLeavingMsg,deleteCustmerLeavingMsg,markReaded} from "@/request/customer/customerMsgEva";
     import  *  as  RequestLogUrl from '@/request/log/mangePlatformLog'
     export default {
       props:{
         data:{
           type:Object
         }
       },
      components:{PageList},
      mixins: [PageList],
        data() {
            return {
                queryForm: {
                  messageTypeId:'',
                  isRead:'',
                  mobile:''
                },
              tableData:[],
              messageTypeOpt:[
                {name:'求租',value:0},
                {name:'求购',value:1},
                {name:'出租',value:2},
                {name:'出售',value:3},
                {name:'其他',value:4},
              ],
              isReadOpt:[
                {name:'是',value:1},
                {name:'否',value:0}
              ],
              listQuery: {
                limit: 10,
                page: 1,
                total: 0
              },
              loadingView:false,
              queryBtnLoading:false
            }
        },
        methods: {
            _loadData(btn) {
               this.queryAllCustomerLeavingMsg(btn)
            },
          //查询留言数据
          queryAllCustomerLeavingMsg(btn){
            if(btn){
              this.queryBtnLoading = btn
              this.listQuery.page = 1
              this.listQuery.currentPage = 1
            }
              this.loadingView =true
              let params = Object.assign({},this.queryForm,{
                page:this.listQuery.page,
                limit:this.listQuery.limit
              })
               params.agentId = this.data.id
            queryAllCustomerLeavingMsg(params).then(res =>{
                this.tableData = res.data
                this.listQuery.total = res.count
                this.loadingView =false
                this.queryBtnLoading = false
              }).catch(error =>{
                  console.log(error)
                  this.loadingView =false
                  this.queryBtnLoading = false
              })
            },
            //标记已读
            handleReviewLeavingMsg(row){
              this.$confirm('确定标记已读？',{
                confirmBtnText:'确定',
                cancelBtnText:'取消',
                type:'warning'
              }).then(() =>{
                  let params = {id:row.id}
                  markReaded(params).then(res =>{
                      if(res.success){
                        this.$message({type:'success',message:res.msg})
                        let message = {
                          sourceCode:  row.realName,//资源编号：客源编号
                          sourceTypeId:16,// 16：经纪人评价
                          operatTypeId: 2,//操作类型2: 表示修改,
                          logContent: '标记:'+row.realName+'的留言已读'//日志内容
                        }
                        RequestLogUrl.manageQueryLog({message:JSON.stringify(message)}).then(res=>{
                          console.log(res)
                        }).catch(error =>{
                          console.log(error)
                        })
                        this._loadData(false)
                      }else{
                        this.$message({type:'error',message:res.msg})
                      }
                  })
              }).catch(() =>{
                this.$message({type:'info',message:'已取消'})
              })
            },
            // 删除留言
            handleDeleteLeavingMsg(row){
              this.$confirm('确定删除留言？',{
                confirmBtnText:'确定',
                cancelBtnText:'取消',
                type:'warning'
              }).then(() =>{
                let params = {id:row.id}
                deleteCustmerLeavingMsg(params).then(res =>{
                    if(res.success){
                      this.$message({type:'success',message:res.msg})
                      let message = {
                        sourceCode:  row.realName,//资源编号：客源编号
                        sourceTypeId:16,// 16：经纪人评价
                        operatTypeId: 3,//操作类型2: 表示删除,
                        logContent: '删除:'+row.realName+'的留言'//日志内容
                      }
                      RequestLogUrl.manageDelLog({message:JSON.stringify(message)}).then(res=>{
                        console.log(res)
                      }).catch(error =>{
                        console.log(error)
                      })
                      this._loadData(false)
                    }else{
                      this.$message({type:'error',message:res.msg})
                    }
                }).catch(error =>{
                  this.$message({type:'error',message:error.msg})
                })
              }).catch(() =>{
                 this.$message({type:'info',message:'已取消'})
              })

            }
          },
        mounted() {

        }

    }

</script>
