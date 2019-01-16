<!-- 带看记录 -->
<template>
  <div style="min-height: 550px">
    <el-table
      :data="tableData"
      v-loading="loadingView"
    >

      <el-table-column
        prop="companyName"
        label="公司">
      </el-table-column>

      <el-table-column
        prop="sourceId"
        label="房源编号">
      </el-table-column>

      <el-table-column
        prop="operator"
        label="带看人">
      </el-table-column>

      <el-table-column
        prop="deptname"
        label="所在部门">
      </el-table-column>

      <el-table-column
        prop="content"
        label="带看内容">
      </el-table-column>

      <el-table-column
        prop="addTime"
        :formatter="_timeFormat"
        label="带看时间">
      </el-table-column>

      <el-table-column
        prop="visterEvaluate"
        label="带看评价">
      </el-table-column>
      <el-table-column
        align="center"
        label="操作"
      >
        <template slot-scope="scope">
          <el-button  @click="delLookhouse(scope.row)" type="text" icon="el-icon-delete" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <b-pagination
      :listQuery="listQuery"
      @handleSizeChange="handleSizeChange"
      @handleCurrentChange="handleCurrentChange">
    </b-pagination>
  </div>
</template>

<script>
  import {getLookHouse,delLookhouse} from '@/request/customer/customerUsed'

  export default {
    data () {
      return {
        tableData: [],
        listQuery: {
          page: 1,
          limit: 10,
          total: 0
        },
        loadingView: false
      }
    },

    methods: {
      /**
       * 分页 pageSize 改变时会触发
       */
      handleSizeChange (val) {
        this.listQuery.limit = val
        this._getTableData()
      },

      /**
       * 分页 currentPage  改变时会触发
       */
      handleCurrentChange (val) {
        this.listQuery.page = val
        this._getTableData()
      },

      _getTableData () {
        this.loadingView = true
        const params = {
          //shhId: this.$route.query.houseId,
          demandId: this.$route.query.demandId,
          limit: this.listQuery.limit,
          page: this.listQuery.page
        }
        getLookHouse(params).then(res => {
          this.tableData = res.data
          this.listQuery.total = res.count
          this.loadingView = false
        }).catch(() => {
          this.loadingView = false
        })
      },
      delLookhouse(row){
        this.$confirm('确定删除该条数据？, 是否继续?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          delLookhouse({id: row.id,demandId: this.$route.query.demandId}).then(res => {
            this.$message({
              type: 'success',
              message: res.msg || '删除成功'
            })
            //this._loadValueData(this.currentDynamicConfigureItem.widgetCode,this.currentDynamicConfigureItem.type);
            for(let i=0;i<this.tableData.length;i++){
              if(this.tableData[i].id==row.id){
                this.tableData.splice(i,1);
              }
            }
          })
        })
      },

      // 格式化日期
      _timeFormat (row, column, cellValue) {
        return this.$utils.timeFormat(cellValue)
      }
    },
  }
</script>

<style scoped lang="scss">

</style>
