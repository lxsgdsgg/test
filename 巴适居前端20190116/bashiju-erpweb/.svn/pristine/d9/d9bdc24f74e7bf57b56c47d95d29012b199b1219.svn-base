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
          label="房源编号" width=126px>
          <template slot-scope="scope">
           <a
              @click="toHouseDetail(scope.row.shhId, scope.row.houseUsesId, scope.row.houseId)"
              href="javascript:;" class="houseId"  type="text"
            >
              {{scope.row.houseId}}
            </a>
         </template>
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
            {{scope.row.room?scope.row.room:0+'室'+scope.row.hall?scope.row.hall:0+'厅'}}
          </template>

        </el-table-column>

        <el-table-column
          prop="buildSpace"
          label="面积">
        </el-table-column>

        <el-table-column
          prop="sellingPrice"
          label="售价(万元)">
        </el-table-column>

        <el-table-column
          prop="rentPrice"
          label="租价(元)">
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
          label="客源编号" width=126px>
          <template slot-scope="scope">
          <a
            @click="toCustDetail(scope.row.demandId, scope.row.formName, scope.row.transactionTypeId)"
            href="javascript:;" class="houseId"  type="text"
          >
            {{scope.row.demandId}}
          </a>
        </template>
        </el-table-column>

        <el-table-column
          prop="areaNames"
          label="区域">
        </el-table-column>

        <el-table-column
          label="需求面积">

          <template slot-scope="scope">
            {{scope.row.space}}
          </template>

        </el-table-column>

        <el-table-column
          label="需求价格(万元)">

          <template slot-scope="scope">
            {{scope.row.price}}
          </template>

        </el-table-column>

        <el-table-column
          label="房型">

          <template slot-scope="scope">
            {{scope.row.custRoom}}
          </template>

        </el-table-column>

        <el-table-column
          prop="orientation"
          label="朝向">
        </el-table-column>

        <el-table-column
          label="楼层">

          <template slot-scope="scope">
            {{scope.row.custFloorCount}}
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
          label="客源编号" width=126px>
          <template slot-scope="scope">
          <a
            @click="toCustDetail(scope.row.demandId, scope.row.formName, scope.row.transactionTypeId)"
            href="javascript:;" class="houseId"  type="text"
          >
            {{scope.row.demandId}}
          </a>
        </template>
        </el-table-column>

        <el-table-column
          prop="areaNames"
          label="区域">
        </el-table-column>

        <el-table-column
          label="需求面积">

          <template slot-scope="scope">
            {{scope.row.space}}
          </template>

        </el-table-column>

        <el-table-column
          label="需求价格(元)">

          <template slot-scope="scope">
            {{scope.row.price}}
          </template>

        </el-table-column>

        <el-table-column
          label="房型">

          <template slot-scope="scope">
            {{scope.row.custRoom}}
          </template>

        </el-table-column>

        <el-table-column
          prop="orientation"
          label="朝向">
        </el-table-column>

        <el-table-column
          label="楼层">

          <template slot-scope="scope">
            {{scope.row.custFloorCount}}
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
  import {getArchives} from '@/request/customer/customerUsed'

  export default {
    props: {
      demandId: {
        type: [Number, String]
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
        loadingView: false,
        priceUnit: '元'
      }
    },
    methods: {
      toHouseDetail (id, name, code) {
        console.log(id)
        console.log(name)
        console.log(code)
        this.$emit('closeModel')
        this.$router.push({ path: '/house/houseUsedDetail/' + id + '/' + code, query: { formName: name}})
        let houseList = []
        let item = {
          id: id
        }
        houseList.push(item)
        window.localStorage.setItem('houseList', JSON.stringify(houseList))
      },
      toCustDetail (id, name, type) {
        console.log(id)
        this.$emit('closeModel')
        this.$router.push({ path: '/customer/customerDetail/'+id, query: {formName: name,transactionTypeId: type}})
        let customerList = []
        let item = {
          id: id
        }
        customerList.push(item)
        window.localStorage.setItem('customerList', JSON.stringify(customerList))
      },
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
          demandId: this.demandId,
          limit: this[`${_type}ListQuery`].limit,
          page: this[`${_type}ListQuery`].page,
          type: _type
        }
        getArchives(params).then(res => {
          this[`${_type}TableData`] = res.data.map(item=>{
            if(item.minSpace===null || item.minSpace===''){
              item.minSpace =0
            }
            if(item.maxSpace===null || item.maxSpace===''){
              item.maxSpace =0
            }
            if(item.maxSpace ===0){
              item.space = item.minSpace+"平米以上"
            }else{
              item.space = item.minSpace + "-" + item.maxSpace
            }
            if(item.minRoom===null || item.minRoom===''){
              item.minRoom =0
            }
            if(item.maxRoom===null || item.maxRoom===''){
              item.maxRoom =0
            }
            if(item.maxRoom ===0){
              item.custRoom = item.minRoom+"室以上"
            }else{
              item.custRoom = item.minRoom + "-" + item.maxRoom
            }
            
            if(item.minFloorCount===null || item.minFloorCount===''){
              item.minFloorCount =0
            }
            if(item.maxFloorCount===null || item.maxFloorCount===''){
              item.maxFloorCount =0
            }
            if(item.maxFloorCount ===0){
              item.custFloorCount = item.minFloorCount+"楼以上"
            }else{
              item.custFloorCount = item.minFloorCount + "-" + item.maxFloorCount
            }
            if(item.minPrice===null || item.minPrice===''){
              item.minPrice =0
            }
            if(item.maxPrice===null || item.maxPrice===''){
              item.maxPrice =0
            }
            if(item.transactionTypeId===4){
              this.priceUnit = '万元'
              if(item.maxPrice ===0){
                item.price = item.minPrice/1000000+this.priceUnit+"以上"
              }else{
                item.price = item.minPrice/1000000 + "-" + item.maxPrice/1000000
              }
            }else{
              this.priceUnit = '元'
              if(item.maxPrice ===0){
                item.price = item.minPrice/100+this.priceUnit+"以上"
              }else{
                item.price = item.minPrice/100 + "-" + item.maxPrice/100
              }
            }
            
            
            return item
              
          })
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
  .houseId {
    color: #409eff;
  &:hover {
     text-decoration: underline;
   }
  }
</style>
