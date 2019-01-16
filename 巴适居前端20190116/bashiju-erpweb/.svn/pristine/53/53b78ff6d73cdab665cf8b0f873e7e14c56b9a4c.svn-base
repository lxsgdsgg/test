<!-- 房源匹配 -->
<template>
  
  <div style="min-height: 550px">
    <div >
      <el-form ref="queryForm" :inline="true" size="small">
        <el-form-item label="" prop="communityId" ref="communityId" >
              <community-selector
                style="width: 150px"
                :changeOnSelect="false"
                v-model="queryForm.communityId"
                :url="areasUrl"
                :props="cascaderProps"
                :dataProps="cascaderDataProps"
                placeholder="选择小区"
                @change="handleCommunityChange"
              >
              </community-selector>
          </el-form-item>

          <el-form-item label="" >
           <base-section-select style="width: 150px" v-model="queryForm.buildSpace" :data="regionValue.space" placeholder="面积区间" description="平米"></base-section-select>
         </el-form-item> 
         <el-form-item label="">
           <base-section-select style="width: 150px" v-model="queryForm.price" :data="regionPrice" placeholder="价格区间" :description="priceUnit"></base-section-select>
         </el-form-item>

         <el-form-item label="">
           <base-section-select style="width: 150px" v-model="queryForm.room" :data="regionValue.roomType" placeholder="户型" description="室"></base-section-select>
         </el-form-item>

         <el-form-item label="">
           <base-section-select style="width: 150px" v-model="queryForm.floorCount" :data="regionValue.roomFloor" placeholder="楼层" description="楼"></base-section-select>
         </el-form-item>
        <el-form-item label="">
           <base-section-select style="width: 150px" v-model="queryForm.buildDates" :data="regionValue.roomBuildDate" placeholder="房屋年代" description="年"></base-section-select>
         </el-form-item>
         <el-form-item>
             <el-button type="primary" @click.native.prevent="_getTableData(true)" :loading="loadingQueryBtn">查询</el-button>
         </el-form-item>
       </el-form>      
    </div>
    <el-table
      :data="tableData"
      v-loading="loadingView"
    >

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
        prop="buildSpace"
        label="面积">
      </el-table-column>

      <el-table-column
        prop="room"
        label="户型">
      </el-table-column>

      <el-table-column
        prop="price"
        label="价格">
      </el-table-column>

      <el-table-column
        prop="orientation"
        label="朝向">
      </el-table-column>

      <el-table-column
        prop="floorCount"
        label="楼层">
      </el-table-column>

      <el-table-column
        prop="maintainer"
        label="维护人">
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
  import CommunitySelector from '@/components/BaseCascader/index'
  import {matchingHousing} from '@/request/customer/customerUsed'
  import {queryCommunitySelectWithRegion} from '@/request/app'
  import BaseSectionSelect from '@/components/BaseSectionSelect'
  export default {
    components:{CommunitySelector,BaseSectionSelect},
    props: {
      transactionTypeId: {
        type: [Number, String],
        required: true
      },
      regionValue: {
        type: Object,
        required: true,
        default () {
          return {}
        }
      }
    },
    data () {
      return {
        
        queryForm:{
         
        },
        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        areasUrl: queryCommunitySelectWithRegion,
        tableData: [],
        listQuery: {
          page: 1,
          limit: 10,
          total: 0
        },
        loadingView: false,
        loadingQueryBtn: false,
        regionPrice: {},
        priceUnit: '万元'
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

      _getTableData (btn) {
        this.loadingView = true
        this.loadingQueryBtn =  btn
        if(this.transactionTypeId==4){
          this.queryForm.sellingPrice = this.queryForm.price  //出售
          delete this.queryForm.price
        }else{
          this.queryForm.rentPrice = this.queryForm.price //出租
          delete this.queryForm.price
        }
        let conditions = {
          transactionTypeId: this.transactionTypeId || '',
            ...this.queryForm
        }

        const params = {
          conditions: JSON.stringify(conditions),
          limit: this.listQuery.limit,
          page: this.listQuery.page
        }

        console.log(params)
        matchingHousing(params).then(res => {
          this.tableData = res.data.map(item => {
                 if(item.transactionTypeId==4){
                    item.price = item.sellingPrice
                 }else{
                   item.price = item.rentPrice
                 }
                 return item
             })
          this.listQuery.total = res.count
          this.loadingView = false
          this.loadingQueryBtn = false
        }).catch(() => {
          this.loadingView = false
          this.loadingQueryBtn = false
        })
      },

      // 格式化日期
      _timeFormat (row, column, cellValue) {
        return this.$utils.timeFormat(cellValue)
      },
      //小区change事件
      handleCommunityChange(){

      }
    },
    mounted () {
      if(this.transactionTypeId==4){
        this.priceUnit = '万元'
        this.regionPrice = this.regionValue.sell
      }else{
         this.priceUnit = '元'
         this.regionPrice = this.regionValue.rent
      }
    }
  }
</script>

<style scoped lang="scss">

</style>
