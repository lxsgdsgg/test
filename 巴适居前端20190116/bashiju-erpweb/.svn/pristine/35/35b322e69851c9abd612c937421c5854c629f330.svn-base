<!-- 获取客户档案 -->
<template>
  <el-tabs v-model="activeName" @tab-click="handleTabClick" type="card">
    <el-tab-pane label="房源" name="house">

      <el-table
        :data="houseTableData"
        style="width: 100%"
        height="300"
        size="mini"
        v-loading="loadingView"
      >

        <el-table-column
          prop="transactionType"
          label="类型">
        </el-table-column>

        <el-table-column
          prop="status"
          label="状态">
        </el-table-column>

        <el-table-column
          prop="houseId"
          label="房源编号">
        </el-table-column>

        <el-table-column
          prop="communityName"
          label="小区">
        </el-table-column>
        <el-table-column
          prop="buildingsName"
          label="坐栋">
        </el-table-column>

        <el-table-column
          prop="buildingHouseName"
          label="房号">
        </el-table-column>

        <el-table-column
          prop="floorCount"
          label="楼层">
        </el-table-column>


        <el-table-column
          label="房型">

          <template slot-scope="scope">
            {{scope.row.room && scope.row.hall ? scope.row.room + scope.row.hall + '室' : ''}}
          </template>

        </el-table-column>

        <el-table-column
          prop="buildSpace"
          label="面积">
        </el-table-column>

        <el-table-column
          prop="sellingPrice"
          label="售价">
          <template slot-scope="scope">
            {{scope.row.sellingPrice ? scope.row.sellingPrice / 100 / 10000 + '万' : ''}}
          </template>
        </el-table-column>

        <el-table-column
          prop="rentPrice"
          label="租价">
          <template slot-scope="scope">
            {{scope.row.rentPrice ? scope.row.rentPrice / 100 + '元' : ''}}
          </template>
        </el-table-column>

        <el-table-column
          prop="maintainer"
          label="维护人">
        </el-table-column>

      </el-table>

      <b-pagination
        :listQuery="houseListQuery"
        @handleSizeChange="handleSizeChange('house')"
        @handleCurrentChange="handleCurrentChange('house')">
      </b-pagination>

    </el-tab-pane>

    <el-tab-pane label="求购" name="buy">
      <el-table
        :data="buyTableData"
        style="width: 100%"
        height="300"
        size="mini"
        v-loading="loadingView"
      >

        <el-table-column
          prop="discStatus"
          label="公/私">
        </el-table-column>

        <el-table-column
          prop="status"
          label="状态">
        </el-table-column>

        <el-table-column
          prop="demandId"
          label="客源编号">
        </el-table-column>

        <el-table-column
          prop="areaNames"
          label="区域">
        </el-table-column>

        <el-table-column
          label="需求面积">

          <template slot-scope="scope">
            {{scope.row.minSpace + scope.row.maxSpace + '平米'}}
          </template>

        </el-table-column>

        <el-table-column
          label="需求价格">

          <template slot-scope="scope">
            {{scope.row.minPrice/ 100 / 10000  + scope.row.maxPrice / 100 / 10000 }}万
          </template>

        </el-table-column>

        <el-table-column
          label="房型">

          <template slot-scope="scope">
            {{scope.row.minRoom + scope.row.maxRoom + '室'}}
          </template>

        </el-table-column>

        <el-table-column
          prop="orientation"
          label="朝向">
        </el-table-column>

        <el-table-column
          label="楼层">

          <template slot-scope="scope">
            {{scope.row.minFloorCount + scope.row.maxFloorCount + '室'}}
          </template>

        </el-table-column>


        <el-table-column
          prop="maintainer"
          label="维护人">
        </el-table-column>

      </el-table>

      <b-pagination
        :listQuery="buyListQuery"
        @handleSizeChange="handleSizeChange('buy')"
        @handleCurrentChange="handleCurrentChange('buy')">
      </b-pagination>
    </el-tab-pane>

    <el-tab-pane label="求租" name="rent">

      <el-table
        :data="rentTableData"
        style="width: 100%"
        height="300"
        size="mini"
        v-loading="loadingView"
      >

        <el-table-column
          prop="discStatus"
          label="公/私">
        </el-table-column>

        <el-table-column
          prop="status"
          label="状态">
        </el-table-column>

        <el-table-column
          prop="demandId"
          label="客源编号">
        </el-table-column>

        <el-table-column
          prop="areaNames"
          label="区域">
        </el-table-column>

        <el-table-column
          label="需求面积">

          <template slot-scope="scope">
            {{scope.row.minSpace + scope.row.maxSpace + '平米'}}
          </template>

        </el-table-column>

        <el-table-column
          label="需求价格">

          <template slot-scope="scope">
            {{scope.row.minPrice + scope.row.maxPrice}}
          </template>

        </el-table-column>

        <el-table-column
          label="房型">

          <template slot-scope="scope">
            {{scope.row.minRoom + scope.row.maxRoom + '室'}}
          </template>

        </el-table-column>

        <el-table-column
          prop="orientation"
          label="朝向">
        </el-table-column>

        <el-table-column
          label="楼层">

          <template slot-scope="scope">
            {{scope.row.minFloorCount + scope.row.maxFloorCount + '室'}}
          </template>

        </el-table-column>


        <el-table-column
          prop="maintainer"
          label="维护人">
        </el-table-column>

      </el-table>

      <b-pagination
        :listQuery="rentListQuery"
        @handleSizeChange="handleSizeChange('rent')"
        @handleCurrentChange="handleCurrentChange('rent')">
      </b-pagination>

    </el-tab-pane>
  </el-tabs>
</template>

<script>
  import {getArchives} from '@/request/house/houseUsed'

  export default {
    props: {
      shhId: {
        type: [String, Number]
      }
    },
    data () {
      return {
        activeName: 'house',
        houseTableData: [],
        buyTableData: [],
        rentTableData: [],
        houseTableDataSize: 0,
        buyTableDataSize: 0,
        rentTableDataSize: 0,
        houseListQuery: {
          page: 1,
          limit: 10,
          total: 0
        },
        buyListQuery: {
          page: 1,
          limit: 10,
          total: 0
        },
        rentListQuery: {
          page: 1,
          limit: 10,
          total: 0
        },
        loadingView: false
      }
    },
    methods: {
      handleTabClick () {

      },

      /**
       * 分页 pageSize 改变时会触发
       */
      handleSizeChange (val, type) {
        this[`${type}ListQuery`].limit = val
        this._getData(type)
      },

      /**
       * 分页 currentPage  改变时会触发
       */
      handleCurrentChange (val, type) {
        this[`${type}ListQuery`].page = val
        this._getData(type)
      },

      _getTableData () {
        this._getData('house')
        this._getData('buy')
        this._getData('rent')
      },

      _getData (_type) {
        this.loadingView = true
        const params = {
          shhId: this.shhId,
          limit: this[`${_type}ListQuery`].limit,
          page: this[`${_type}ListQuery`].page,
          type: _type
        }
        getArchives(params).then(res => {
          this[`${_type}TableData`] = res.data
          this[`${_type}TableDataSize`] = res.count
          this[`${_type}ListQuery`].total = res.count
          this.loadingView = false
        }).catch(() => {
          this.loadingView = false
        })
      }
    },

    mounted () {
      this._getTableData()
    }
  }
</script>

<style scoped lang="scss">

</style>
