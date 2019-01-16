<!-- 带看记录 -->
<template>
  <div style="min-height: 550px">
    <el-table
      size="small"
      :data="tableData"
      v-loading="loadingView"
    >

      <el-table-column
        prop="companyName"
        label="公司"
        width="300"
      >
      </el-table-column>

      <el-table-column
        label="房源编号">
         <template slot-scope="scope">
           <a
              @click="toDetail(scope.row.shhId, scope.row.houseUsesId, scope.row.houseId)"
              href="javascript:;" class="houseId"  type="text"
            >
              {{scope.row.houseId}}
            </a>
         </template>
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

    </el-table>

    <b-pagination
      :listQuery="listQuery"
      @handleSizeChange="handleSizeChange"
      @handleCurrentChange="handleCurrentChange">
    </b-pagination>
  </div>
</template>

<script>
  import PageList from '@/mixins/pageList' // 列表页面查询 mixin
  import {getLookHouse} from '@/request/house/houseNew'

  export default {
    mixins: [PageList],

    props: {
      projectId: {
        type: [Number, String]
      },
    },

    data () {
      return {
      }
    },

    methods: {

      _getTableData () {
        this.loadingView = true
        const params = this._getParams({
          newHouseId: this.projectId
        })
        getLookHouse(params).then(res => {
          this.tableData = res.data
          this.listQuery.total = res.count
          this.loadingView = false
        }).catch(() => {
          this.loadingView = false
        })
      },

      toDetail (id, name, code) {
        this.$router.push({ path: '/house/houseUsedDetail/' + id + '/' + code, query: { formName: name}})
        let houseList = []
        let item = {
          id: id
        }
        houseList.push(item)
        window.localStorage.setItem('houseList', JSON.stringify(houseList))
      },

      // 格式化日期
      _timeFormat (row, column, cellValue) {
        return this.$utils.timeFormat(cellValue)
      }
    },
  }
</script>

<style scoped lang="scss">
  .houseId {
    color: #409eff;
    &:hover {
      text-decoration: underline;
    }
  }
</style>
