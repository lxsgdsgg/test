<template>
  <div class="pagination-container">
    <template v-if="!listQuery.simple">
      <el-pagination
        background
        layout="total, sizes, prev, pager, next, jumper"
        :page-sizes="listQuery.pageSizes || [10,20,30, 50]"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="listQuery.currentPage"
        :page-size="listQuery.pageSize"
        :total="listQuery.total"
      >
      </el-pagination>
    </template>
    <template v-else>
      <el-pagination
        layout="total, prev, pager, next"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="listQuery.currentPage"
        :page-size="listQuery.pageSize"
        :total="listQuery.total"
      >
      </el-pagination>
    </template>
  </div>
</template>
<script>
export default {
  props: {
    listQuery: Object
  },
  name: 'BasePagination',
  data () {
    return {}
  },
  methods: {
    handleSizeChange (val) {
      this.listQuery.limit = val
      this.$emit('handleSizeChange', val)
    },
    handleCurrentChange (val) {
      this.listQuery.page = val
      this.$emit('handleCurrentChange', val)
    }
  }
}
</script>
<style lang="scss" scoped>
  .pagination-container {
    margin-top: 20px;
    text-align: center;
  }
</style>
