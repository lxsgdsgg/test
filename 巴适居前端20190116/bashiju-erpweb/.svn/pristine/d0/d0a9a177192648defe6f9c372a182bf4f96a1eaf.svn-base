<!-- 房源跟进记录 -->
<template>
  <div style="min-height: 550px">
    <el-table
      :data="tableData"
      v-loading="loadingView"
    >
      <el-table-column
        prop="followTime"
        label="跟进时间">
        <template slot-scope="scope">
          {{$utils.timeFormat(scope.row.followTime,'{y}-{m}-{d} {h}:{i}:{s}')}}
        </template>
      </el-table-column>
      <el-table-column
        prop="followTypeName"
        label="跟进方式">
      </el-table-column>
      <el-table-column
        prop="follower"
        label="跟进人">
      </el-table-column>
      <el-table-column
        prop="deptname"
        label="所在部门">
      </el-table-column>
      <el-table-column
        prop="content"
        label="跟进内容"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column
        label="操作"
        width="100"
      >
        <template slot-scope="scope">
          <el-button  v-hasMultipleBtn="['delBtn',scope.row]" @click="handleDelete(scope.row)" icon="el-icon-delete" class="mr10" size="small">删除</el-button>
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
  import {getFollowRecord, delFollow} from '@/request/customer/customerUsed'

  export default {
    props: {
      demandId: {
        type: [Number, String]
      }
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
      handleDelete (row) {
        this.$confirm('确定删除该条数据吗?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          delFollow({id: row.id}).then(res => {
            this.$message({
              type: 'success',
              message: res.msg
            })
            this._getTableData()
          })
        })
      },

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
          demandId: this.demandId,
          limit: this.listQuery.limit,
          page: this.listQuery.page
        }
        getFollowRecord(params).then(res => {
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
