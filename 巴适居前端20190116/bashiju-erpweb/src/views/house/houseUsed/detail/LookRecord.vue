<!-- 带看记录 -->
<template>
  <div style="min-height: 550px">
    <el-table
      :data="tableData"
      v-loading="loadingView"
      size="small"
    >

      <el-table-column
        prop="companyName"
        label="公司">
      </el-table-column>

      <el-table-column
        prop="operator"
        label="带看人">
      </el-table-column>

      <el-table-column
        prop="deptName"
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

    </el-table>

    <b-pagination
      :listQuery="listQuery"
      @handleSizeChange="handleSizeChange"
      @handleCurrentChange="handleCurrentChange">
    </b-pagination>
  </div>
</template>

<script>
  import {getLookHouse} from '@/request/house/houseUsed'

  export default {
    props: {
      houseId: {
        required: true
      },
    },

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
          shhId: this.houseId,
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

      // 格式化日期
      _timeFormat (row, column, cellValue) {
        return this.$utils.timeFormat(cellValue)
      }
    },
  }
</script>

<style scoped lang="scss">

</style>
