<template>
  <div class="page-content" style="height: 500px">
    <div class="page-content-bd" v-loading="loadingView">
      <el-table
        :data="tableData"
        border
        tooltip-effect="light"
        align="center"
        style="width: 100%"
      >
        <el-table-column
          prop="followTime"
          align="left"
          :formatter="_timeFormat"
          label="跟进时间">
        </el-table-column>

        <el-table-column
          prop="followTypeName"
          show-overflow-tooltip
          align="left"
          label="跟进方式">
        </el-table-column>


        <el-table-column
          prop="follower"
          align="left"
          label="跟进人">
        </el-table-column>

        <el-table-column
          prop="deptName"
          align="left"
          label="部门">
        </el-table-column>


        <el-table-column
          prop="content"
          align="left"
          label="跟进内容">
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
<style></style>
<script>
  import PageList from '@/mixins/pageList'
  import {queryFollowUpRecordByDemandId} from "@/request/customer/followUpRecord";

  export default {
      components:{PageList},
      mixins: [PageList],
      props:{
          data:{
            type:String
          }
      },
        data() {
            return {
              form:{
                demandId:''
              },
              loadingView:false,
              tableData:[],
              listQuery:{
                limit:10,
                page:1,
                total:0
              }
            }
        },
        methods: {
          _loadData(btn){
            this.queryFollowUpRecordByDemandId  (btn)
          },
          queryFollowUpRecordByDemandId(){
              let params = Object.assign({},this.form,{
                  limit:this.listQuery.limit,
                   page:this.listQuery.page
              })
              params.demandId = this.data
              queryFollowUpRecordByDemandId(params).then(res=>{
                 this.tableData = res.data
                this.listQuery.total = res.count
              })
          }
        }

    }

</script>
