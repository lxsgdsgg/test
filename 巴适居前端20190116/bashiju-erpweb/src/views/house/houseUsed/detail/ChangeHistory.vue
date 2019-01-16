<!-- 改价历史 -->
<template>
  <div style="min-height: 550px">
    <el-table
      size="small"
      :data="tableData"
      v-loading="loadingView"
    >

      <el-table-column
        prop="updateTime"
        :formatter="_timeFormat"
        label="日期">
      </el-table-column>

      <el-table-column
        prop="rentPrice"
        label="当前租价"
        :formatter="_rentFormat"
      >
      </el-table-column>

      <el-table-column
        prop="rentFloat"
        label="租价浮动金额"
        :formatter="_rentFormat"
      >
      </el-table-column>

      <el-table-column
        prop="sellingPrice"
        label="当前售价"
        :formatter="_sellingFormat"
      >
      </el-table-column>
      <el-table-column
        prop="sellFloat"
        label="售价浮动金额"
        :formatter="_sellingFormat"
      >
      </el-table-column>

      <el-table-column
        prop="operator"
        label="操作人">
      </el-table-column>

      <el-table-column
        prop="deptName"
        label="部门">
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
  import {getPricedetail} from '@/request/house/houseUsed'

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
        getPricedetail(params).then(res => {
          this.tableData = res.data
          this.listQuery.total = res.count
          this.loadingView = false
        }).catch(() => {
          this.loadingView = false
        })
      },

      // 格式化日期
      _timeFormat (row, column, cellValue) {
        return this.$utils.timeFormat(cellValue, '{y}-{m}-{d} {h}:{i}:{s}')
      },

      _sellingFormat (row, column, cellValue) {
        let num = ''
        if (cellValue) {

          num = (cellValue / 1000000).toFixed(2) + '万'

          if (column.property === 'sellFloat' && cellValue > 0) {
            num = '+' + (cellValue / 1000000).toFixed(2) + '万'
          }

        }
        return num
      },

      _rentFormat (row, column, cellValue) {
        let num = ''
        if (cellValue) {

          num = (cellValue / 100).toFixed(2) + '元'

          if (column.property === 'rentFloat' && cellValue > 0) {
            num = '+' + (cellValue / 100).toFixed(2) + '元'
          }

        }
        return num
      }
    },
  }
</script>

<style scoped lang="scss">

</style>
