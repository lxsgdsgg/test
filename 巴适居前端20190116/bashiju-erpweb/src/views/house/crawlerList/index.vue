<template>
  <div class="page-content">
    <div class="page-content-hd">
      <el-form :model="queryForm" ref="queryForm" :inline="true" size="small">
        <el-form-item label="" prop="transactionTypeId">
          <el-select v-model="queryForm.transactionTypeId" @change="valueChange">
            <el-option label="出售" value="1"></el-option>
            <el-option label="出租" value="2"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item prop="communityName">
          <el-input v-model="queryForm.communityName" type="text" placeholder="行政区、小区"></el-input>
        </el-form-item>

        <el-form-item label="价格区间" prop="price">
          <base-section-select v-model="queryForm.sellingPrice" v-if="sale"
                 :data="regionValue.sell" placeholder="出售价格区间" description="万元"></base-section-select>
          <base-section-select v-model="queryForm.sellingPrice" v-else
                               :data="regionValue.rent" placeholder="出租价格区间" description="元"></base-section-select>
        </el-form-item>

        <el-form-item label="面积区间" prop="minSpace">
          <base-section-select v-model="queryForm.buildSpace"
               :data="regionValue.space" placeholder="面积区间" description="m²"></base-section-select>
        </el-form-item>
        <el-form-item>
            <el-button @click="_loadData(true)" type="primary" :loading="queryBtnLoading">查询</el-button>
            <el-button @click="resetForm" type="info">清空</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="page-content-hd">
      <el-table :data="tableData"  border align="center" tooltip-effect="light" v-loading="loadingView" size="mini">
        <el-table-column
          align="left"
          label="来源"
          prop="sourceName"
          show-overflow-tooltip></el-table-column>
        <el-table-column
          align="left"
          label="采集时间"
          :formatter="_timeFormat"
          prop="recordTime"
          show-overflow-tooltip
          width="160px"
        ></el-table-column>
        <el-table-column align="left" label="标题" prop="title" show-overflow-tooltip width="300px"></el-table-column>
        <el-table-column align="left" label="小区信息" prop="communityName" show-overflow-tooltip width="220px"></el-table-column>
        <el-table-column align="left" label="房屋性质" prop="houseType" show-overflow-tooltip></el-table-column>
        <el-table-column align="left" label="价格" prop="price" show-overflow-tooltip>
          <template slot-scope="scope">
            {{scope.row.price / 1000000 }}万
          </template>
        </el-table-column>
        <el-table-column align="left" label="面积" prop="buildSpace" show-overflow-tooltip>
          <template slot-scope="scope">
            {{scope.row.buildSpace}}m²
          </template>
        </el-table-column>
        <el-table-column align="left" label="户型" prop="room" show-overflow-tooltip></el-table-column>
        <el-table-column align="left" label="朝向" prop="orientation" show-overflow-tooltip></el-table-column>
        <el-table-column align="left" label="装修" prop="decoration" show-overflow-tooltip></el-table-column>
        <el-table-column align="left" label="楼层" prop="floor" show-overflow-tooltip></el-table-column>
        <el-table-column align="left" label="操作" width="220px" fixed="right">
          <template slot-scope="scope">
            <!--v-hasMultipleBtn="['detailBtn',scope.row]"-->
            <el-button inputHouseBtn @click="redirectUrl(scope.row)" type="success" size="mini" >详情</el-button>
            <el-dropdown @command="handleCommandAddHouse">
              <el-button type="primary" size="mini">
                录入房源<i class="el-icon-arrow-down el-icon--right"></i>
              </el-button>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item v-for="item in addTypes" :key="item.value" :command="item">{{item.label}}</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
          </template>
        </el-table-column>
      </el-table>
      <!--分页控件-->
      <b-pagination
        :listQuery="listQuery"
        @handleSizeChange="handleSizeChange"
        @handleCurrentChange="handleCurrentChange">
      </b-pagination>
    </div>
    <template>
      <!-- 房源新增 -->
      <el-dialog
        title="新增房源"
        :visible.sync="dialogVisibleAddHouse"
        width="500px"
        :close-on-click-modal="false"
        :close-on-press-escape="false"
        append-to-body
        :modal-append-to-body="false"
      >
        <add-house v-if="hackReset" @handleClick="handleAddHouseClick" :param="addHouseParams" ref="addHouse"></add-house>
      </el-dialog>
    </template>
  </div>
</template>

<script>
  import PageList from '@/mixins/pageList'
  import * as RequestURL from '@/request/house/crawler'
  import AddHouse from '@/views/house/houseUsed/AddHouse'
  import BaseSectionSelect from '@/components/BaseSectionSelect'
  import {getRegionValue} from '@/request/app'
  import  *  as  RequestLogUrl from '@/request/log/housePlatformLog'


  const addTypes = [
    {label: '住宅', value: 'house'},
    {label: '别墅', value: 'villa'},
    {label: '商铺', value: 'shops'},
    {label: '公寓', value: 'apartment'},
    {label: '写字楼', value: 'officeBuiling'},
    {label: '仓库', value: 'wareHouse'},
    {label: '厂房', value: 'factory'},
    {label: '车位', value: 'parking'},
    {label: '土地', value: 'land'}
  ]

  export default {
    name: 'crawlerList',
    mixins: [PageList],
    components: {AddHouse,BaseSectionSelect},
    data () {
      return {
        queryForm: {
          minSpace:null,
          minPrice: null,
          maxPrice: null,
          maxSpace: null,
          sellingPrice: '',
          buildSpace: '',
          communityName: null,
          transactionTypeId: null
        },
        loadingView: false,
        queryBtnLoading: false,
        tableData: [],

        addHouseParams: {
          houseUsesId: '',
          houseUses: ''
        },
        dialogVisibleAddHouse: false,
        addTypes: addTypes,
        hackReset: true,
        regionValue: {
          sell: {},
          rent: {},
          space: {}
        }, // 区间下拉数据
        sale: true
      }
    },
    methods: {
      valueChange(value){
        if(value === '1'){
          this.sale = true
        }else{
          this.sale = false
        }
      },
      // 获取区间下拉数据
      _getRegionValue () {
        // 获取售价区间
        const sellParams = {
          widgetCode: 'sellPriceRange',
          tag: 2
        }
        getRegionValue(sellParams).then(res => {
          this.regionValue.sell = res.data || []
        })

        // 获取租价区间
        const rentParams = {
          widgetCode: 'rentPriceRange',
          tag: 2
        }
        getRegionValue(rentParams).then(res => {
          this.regionValue.rent = res.data || []
        })

        // 获取租价区间
        const spaceParams = {
          widgetCode: 'spaceRange',
          tag: 2
        }
        getRegionValue(spaceParams).then(res => {
          this.regionValue.space = res.data || []
        })
      },

      getMangeName(value,name){
        this.queryForm.follower = name
      },

      // 查询跟进详细
      handlerClick(row){
        this.currentRowData = ''
        this.editDialogVisible = false
        this.$nextTick(()=>{
          this.currentRowData = row
          this.queryHouseFollowRecordDetail()
          this.editDialogVisible = true
        })
      },

      // 查询页面列表数据
      _loadData(btnQuery) {
        if (btnQuery) {
          this.queryBtnLoading = btnQuery
          this.listQuery.page = 1
          this.listQuery.currentPage = 1
        }
        this.loadingView = true
        let query = {...this.queryForm}
        if(query.sellingPrice){
          if(query.transactionTypeId && query.transactionTypeId === 2){
            query.minPrice = query.sellingPrice.min * 100
            query.maxPrice = query.sellingPrice.max * 100
          }else{
            query.minPrice = query.sellingPrice.min * 1000000
            query.maxPrice = query.sellingPrice.max * 1000000
          }
        }
        if(query.buildSpace){
          query.minSpace = query.buildSpace.min
          query.maxSpace = query.buildSpace.max
        }
        delete query.sellingPrice
        delete query.buildSpace
        let params = Object.assign({}, {condition: JSON.stringify(query)}, {
          page: this.listQuery.page,
          limit: this.listQuery.limit
        })
        RequestURL.queryCrawlerPageList(params).then((res) => {
          this.tableData = res.data
          this.listQuery.total = res.count
          this.queryBtnLoading = false
          this.loadingView = false
        }).catch(err => {
          console.log(err)
          this.loadingView = false
          this.queryBtnLoading = false
        })
      },

      // 重置表单数据
      resetForm(){
        if(this.$refs['queryForm']){
          this.$refs['queryForm'].resetFields()
          this.queryForm.sellingPrice = null
          this.queryForm.buildSpace= null
        }
      },
      // 打开详细
      redirectUrl(row){
        window.open(row.houseUrl)

        let message = {
          sourceId:  row.sourceId,//资编号：房源编号
          sourceCode:  row.sourceName,//资源编号：房源编号
          businessTypeId: 1,//业务类型，1：房源
          sourceTypeId: row.transactionTypeId,//类型：求购
          operatTypeId: 42,//操作类型 ：钥匙退还,
          logContent: '查看房源详细 :'+row.communityName//日志内容
        }

        RequestLogUrl.houseQueryLog({message:JSON.stringify(message)}).then(res =>{
          console.log(res)
        }).catch(error =>{
          console.log(error)
        })
      },

      // 新增房源弹框
      handleCommandAddHouse (command) {
        this.hackReset = false
        this.$nextTick(()=>{
          this.hackReset = true
          this.addHouseParams = {
            houseUsesId: command.value,
            houseUses: command.label,
            // transactionTypeId: '0' // 当前选中的交易类型
          }
          this.$refs['addHouse'] && this.$refs['addHouse']._resetForm()
          this.dialogVisibleAddHouse = true
        })
      },
      // 监听房源新增
      handleAddHouseClick (action) {
        if (action === 1) {
          this._loadData(false)
        }
        this.dialogVisibleAddHouse = false
      }
    },
    mounted(){
      this._getRegionValue()
    }
  }
</script>

<style scoped type="scss">
  .el-dropdown {
    vertical-align: top;
  }
  .el-dropdown + .el-dropdown {
    margin-left: 15px;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
</style>
