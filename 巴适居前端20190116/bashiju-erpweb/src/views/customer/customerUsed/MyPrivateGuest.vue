<template>
  <div>
    <el-table
      :data="tableData"
      border
      tooltip-effect="light"
      align="center"
      style="width: 100%"
    >

      <el-table-column
        prop="id"
        align="left"
        label="需求编号"
      ></el-table-column>

      <el-table-column
        prop="status"
        align="left"
        label="状态"
      ></el-table-column>

      <el-table-column
        prop="custName"
        align="left"
        label="客户姓名"
      ></el-table-column>

      <el-table-column
        prop="areaNames"
        align="left"
        label="需求区域"
      ></el-table-column>


      <el-table-column
        prop="maxSpace"
        align="left"
        label="需求面积"
      >
        <template slot-scope="scope">
            <label v-if=" scope.row.minSpace === 0   && scope.row.maxSpace">
              {{
              scope.row.maxSpace+ '㎡以下'

              }}
            </label>
            <label v-else-if="scope.row.minSpace &&  scope.row.maxSpace === 0 ">
              {{
              scope.row.minSpace+ '㎡以上'
              }}
            </label>
            <label v-else>
              {{
              scope.row.minSpace + '-' + scope.row.maxSpace+'㎡'
              }}
            </label>
        </template>
      </el-table-column>

      <el-table-column
        prop="minPrice"
        align="left"
        label="需求价格">
        <template slot-scope="scope">
          <div v-if="scope.row.transactionTypeId === 5">
            <label v-if=" scope.row.minPrice === 0   && scope.row.maxPrice">
              {{
              scope.row.maxPrice/100+ '元以下'

              }}
            </label>
            <label v-else-if="scope.row.minPrice &&  scope.row.maxPrice === 0 ">
              {{
              scope.row.minPrice/100+ '元以上'
              }}
            </label>
            <label v-else>
              {{
              scope.row.minPrice/100+'-'+ scope.row.maxPrice/100+'元'
              }}
            </label>
          </div>
          <div v-else-if="scope.row.transactionTypeId === 4">
            <label v-if=" scope.row.minPrice === 0   && scope.row.maxPrice">
              {{
              scope.row.maxPrice/1000000+ '万以下'

              }}
            </label>
            <label v-else-if="scope.row.minPrice &&  scope.row.maxPrice === 0 ">
              {{
              scope.row.minPrice/1000000+ '万以上'
              }}
            </label>
            <label v-else>
              {{
              scope.row.minPrice/1000000+'-'+ scope.row.maxPrice/1000000+'万'
              }}
            </label>
          </div>
        </template>
      </el-table-column>

      <el-table-column
        prop="maintainer"
        align="left"
        label="维护人"
      ></el-table-column>
    </el-table>
    <!--分页控件-->
    <b-pagination
      :listQuery="listQuery"
      @handleSizeChange="handleSizeChange"
      @handleCurrentChange="handleCurrentChange">
    </b-pagination>
    <div class="btn-group">
      <el-button @click="closeDialog">关闭</el-button>
    </div>
  </div>
</template>
<style></style>
<script>
  import PageList from '@/mixins/pageList'
  import {queryMyPrivateGuest} from "@/request/customer/customerUsed";

  export default {
    props: {
      data: { // 父组件传递过来的参数 房屋类型等
        type: Object,
        required: true
      }
    },
      components:{PageList},
      mixins: [PageList],
        data() {

            return {
                form: {},
                tableData:[],
                loadingView:false,
                queryBtnLoading:false,
                listQuery:{
                  limit:10,
                  page:1,
                  total:0
                },
            }
        },
        methods: {
            _loadData(btn){
                this._queryMyPrivateGuest(btn)
            },
          /**
           * 查询我的私客
           * @private
           */
            _queryMyPrivateGuest(btn){
                this.loadingView = true
                this.queryBtnLoading = true
                let params = Object.assign({},this.form,{
                  limit:this.listQuery.limit,
                  page:this.listQuery.page
                })
                params.transactionTypeId = this.data
                queryMyPrivateGuest(params).then(res =>{
                    this.tableData= res.data
                    this.listQuery.total = res.count
                    this.loadingView = false
                    this.queryBtnLoading = false
                }).catch(error =>{
                  this.loadingView = false
                  this.queryBtnLoading = false
                })
            },
            closeDialog(){
               this.$emit('handleClick')
            }
        },
        mounted() {
        }

    }

</script>
