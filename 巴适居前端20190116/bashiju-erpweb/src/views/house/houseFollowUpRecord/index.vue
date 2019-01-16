<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form" ref="query-form">
        <el-form size="small" :inline="true" :model="queryForm" ref="queryForm" class="demo-form-inline">

          <el-form-item prop="transactionType">
            <el-select v-model="queryForm.transactionType" clearable style="width: 120px;" placeholder="交易类型">
              <el-option
                v-for="item in houseTransactionTypeOpt"
                :key="item.value"
                :label="item.name"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>

          <el-form-item prop="status">
            <el-select v-model="queryForm.status" clearable style="width: 120px;" placeholder="房源状态">
              <el-option
                v-for="item in statusOpt"
                :key="item.value"
                :label="item.name"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>

          <el-form-item prop="followType">
            <el-select v-model="queryForm.followType" clearable style="width: 120px;" placeholder="跟进方式" >
               <el-option v-for="item in options" :label="item.fieldValue"  :value="item.id" :key="item.id"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item prop="followerId">
            <base-cascader placeholder="选择跟进人" :changeOnSelect="false" clearable v-model="queryForm.followerId" :url="getReferenceUserSelectUrl"
               :props="cascaderProps" :dataProps="cascaderDataProps"  style="width: 120px;" @change="getMangeName" >
            </base-cascader>
          </el-form-item>

          <el-form-item prop="maintainId">
            <base-cascader :changeOnSelect="false" placeholder="选择维护人" clearable v-model="queryForm.maintainId" :url="getReferenceUserSelectUrl"
                :props="cascaderProps" :dataProps="cascaderDataProps"  style="width: 120px;" @change="getMangeName">
            </base-cascader>
          </el-form-item>

          <el-form-item prop="communityId">
            <community-selector placeholder="选择小区" style="width: 120px"
              v-model="queryForm.communityId"
              :url="communityUrl"
              :props="cascaderProps"
              :dataProps="cascaderDataProps"
              :changeOnSelect="false"
              @change="getSelectData"
            >
            </community-selector>
          </el-form-item>

          <el-form-item prop="houseId">
            <el-input v-model.trim="queryForm.houseId" placeholder="房源编号"></el-input>
          </el-form-item>

          <el-form-item prop="followTime">
            <el-date-picker
              v-model="queryForm.followTime"
              type="daterange"
              :default-value="ct_month"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              value-format="yyyy-MM-dd"
            >
            </el-date-picker>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click.native.prevent="_loadData(true)" :loading="queryBtnLoading" size="small">查询</el-button>
            <el-button @click="_resetForm('queryForm')" size="small">清空</el-button>
          </el-form-item>

        </el-form>
      </div>
    </div>

    <div class="page-content-bd" v-loading="loadingView">
      <el-table
        :data="tableData"
        border
        tooltip-effect="light"
        align="center"
        style="width: 100%"
      >

        <el-table-column
          prop="followTime"
          show-overflow-tooltip
          align="left"
          :formatter="_timeFormat"
          label="跟进时间">
        </el-table-column>

        <el-table-column
          prop="followTypeName"
          show-overflow-tooltip
          align="left"
          label="跟进方式">
        </el-table-column>

        <el-table-column
          prop="transactionType"
          show-overflow-tooltip
          align="left"
          label="跟进类型">
        </el-table-column>

        <el-table-column
          prop="houseId"
          show-overflow-tooltip
          align="left"
          label="房源编号">
          <template slot-scope="scope">
            <a
              @click="toDetail(scope.row.shhId, scope.row.houseUsesId, scope.row.houseId,scope.row)"
              href="javascript:;" class="houseId"  type="text"
            >
              {{scope.row.houseId}}
            </a>
          </template>
        </el-table-column>

        <el-table-column
          prop="communityName"
          show-overflow-tooltip
          align="left"
          label="小区名称">
        </el-table-column>

        <el-table-column
          prop="status"
          show-overflow-tooltip
          align="left"
          label="状态">
        </el-table-column>

        <el-table-column
          prop="follower"
          show-overflow-tooltip
          align="left"
          label="跟进人">
        </el-table-column>

        <el-table-column
          prop="maintainer"
          show-overflow-tooltip
          align="left"
          label="维护人">
        </el-table-column>

        <el-table-column
          prop="deptName"
          show-overflow-tooltip
          align="left"
          label="部门名称">
        </el-table-column>

        <el-table-column
          prop="content"
          show-overflow-tooltip
          align="left"
          label="跟进内容">
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
          width="100px"
        >
          <template slot-scope="scope">
            <el-button @click="handlerClick(scope.row)" type="text" size="small">查看所有跟进</el-button>
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

    <!--编辑数据弹框-->
    <b-dialog
      title="房源跟进记录详细"
      :show.sync="editDialogVisible"
      :close-on-click-modal="false"
      top="30vh"
      width="60%">

      <el-table
        :data="detailData"
        border
        tooltip-effect="light"
        align="center"
        style="width: 100%"
        :v-loading="loadingDetailView"
      >

        <el-table-column
          prop="addTime"
          show-overflow-tooltip
          align="left"
          :formatter="_timeFormat"
          label="跟进时间">
        </el-table-column>

        <el-table-column
          prop="followTypeName"
          show-overflow-tooltip
          align="left"
          label="跟进方式">
        </el-table-column>

        <el-table-column
          prop="transactionType"
          show-overflow-tooltip
          align="left"
          label="跟进类型">
        </el-table-column>

        <el-table-column
          prop="houseId"
          show-overflow-tooltip
          align="left"
          label="房源编号">
        </el-table-column>

        <el-table-column
          prop="communityName"
          show-overflow-tooltip
          align="left"
          label="小区名称">
        </el-table-column>

        <el-table-column
          prop="status"
          show-overflow-tooltip
          align="left"
          label="状态">
        </el-table-column>

        <el-table-column
          prop="operator"
          show-overflow-tooltip
          align="left"
          label="跟进人">
        </el-table-column>

        <el-table-column
          prop="deptName"
          show-overflow-tooltip
          align="left"
          label="部门名称">
        </el-table-column>

        <el-table-column
          prop="content"
          show-overflow-tooltip
          align="left"
          label="跟进内容">
        </el-table-column>
      </el-table>
      <!--分页控件-->
      <b-pagination
        :listQuery="listDetailQuery"
        @handleSizeChange="handleDetailSizeChange"
        @handleCurrentChange="handleDetailCurrentChange">
      </b-pagination>
    </b-dialog>
  </div>
</template>

<script>
  import PageList from '@/mixins/pageList'
  import * as RequestURL from '@/request/house/houseFollowRecord'
  import { queryCommunitySelectWithRegion } from '@/request/app'
  import CommunitySelector from '@/components/BaseCascader/'
  import BaseCascader from '@/components/BaseCascader'
  import {getSelectValue} from '@/request/app'
  import  *  as  RequestLogUrl from '@/request/log/housePlatformLog'

  export default {
    name: 'houseFollowUpRecord',
    mixins: [PageList],
    components: {CommunitySelector, BaseCascader },
    data () {
      return {
        queryForm: {
          transactionType: '',//交易类型
          followTypeName: '',
          status: '',//状态
          followType: '',//跟进类型
          followerId: '',//跟进人
          maintainId: '',//维护人
          followTime: '',
          communityId: '',//公司编号
          houseId: '',//房源编号
          follower: ''//跟进人名称
        },
        options: [],//
        ct_month:null,
        listDetailQuery:{
          page: 1,limit: 10,total: 0
        },
        loadingView: false,
        loadingDetailView: false,
        queryBtnLoading: false,
        loadingBtn: false,
        editDialogVisible: false,
        getReferenceUserSelectUrl: 'manage/commonselect/queryOpenCityCompanyDepartUser', // 介绍人
        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        communityUrl: queryCommunitySelectWithRegion,
        currentRowData: '',
        houseTransactionTypeOpt: [],
        statusOpt: [],
        tableData: [],
        detailData: []
      }
    },
    methods: {
      // 得到选择的节点数据
      getSelectData(value,name,data){
        if (data.dataType !== 'community') {
          this.$message({
            showClose: true,
            type: 'warning',
            message: '只能选择小区!'
          })
        }
        setTimeout(() => {
        }, 50)
      },

      /**
       * 分页 pageSize 改变时会触发
       */
      handleDetailSizeChange (val) {
        this.listDetailQuery.limit = val
        this.queryHouseFollowRecordDetail()
      },

      toDetail (id, name, code) {
        this.$router.push({ path: './houseUsedDetail/' + id + '/' + code, query: { formName: name}})
        window.localStorage.setItem('houseList', JSON.stringify(this.tableData))
      },

      /**
       * 分页 currentPage  改变时会触发
       */
      handleDetailCurrentChange (val) {
        this.listDetailQuery.page = val
        this.queryHouseFollowRecordDetail()
      },
      timeDefaultShow(){//默认显示上个月
        this.ct_month= new Date();
        this.ct_month.setTime(this.ct_month.getTime() - 3600 * 1000 * 24 * 30);
      },
      getMangeName(value,name,data){
        this.queryForm.follower = name
        if (data.dataType !== 'user') {
          this.$message({
            showClose: true,
            type: 'warning',
            message: '只能选择用户!'
          })
        }
        setTimeout(() => {
        }, 50)
      },

      // 查询跟进详细
      handlerClick(row){
        this.currentRowData = ''
        this.editDialogVisible = false
        this.$nextTick(()=>{
          this.currentRowData = row
          this.queryHouseFollowRecordDetail()
          this.editDialogVisible = true

          let message = {
            sourceId:  row.shhId,//资编号：房源编号
            sourceCode:  row.houseId,//资源编号：房源编号
            businessTypeId: 1,//业务类型，1：房源
            sourceTypeId: row.transactionTypeId,//类型：求购
            operatTypeId: 37,//操作类型 ：跟进记录,
            logContent: '查询所有跟进列表 :'+row.houseId//日志内容
          }

          RequestLogUrl.houseQueryLog({message:JSON.stringify(message)}).then(res =>{
            console.log(res)
          }).catch(error =>{
            console.log(error)
          })
        })
      },

      // 查询页面列表数据
      //TODO:分页查询未解决
      _loadData(btn) {
        if(btn){
          this.listQuery.page = 1
          this.listQuery.currentPage = 1
        }
        this.loadingView = true
        let query = {...this.queryForm}
        let params = Object.assign({}, query, {
          page: this.listQuery.page,
          limit: this.listQuery.limit
        })
        if(params.followTime.length > 0){
          params.beginTime = params.followTime[0]
          params.endTime = params.followTime[1]
        }
        delete params.followTime;
        RequestURL.queryHouseFollowRecordPageList(params).then((res) => {
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

      /**
       * 查询跟进明细
       * @param row
       */
      queryHouseFollowRecordDetail(){
        this.loadingDetailView = true
        let params = Object.assign({},{houseId:this.currentRowData.houseId},{
          page: this.listDetailQuery.page,
          limit: this.listDetailQuery.limit
        })
        RequestURL.queryHouseFollowRecordDetailPageList(params).then(res=>{
          this.detailData = res.data
          console.table( res.data)
          this.listDetailQuery.total = res.count
          this.loadingDetailView = false
        }).catch(err=>{
          console.log(err)
          this.$message({
            type: 'error',
            message: err.data.msg
          })
          this.loadingDetailView = false
        })
      },

      /**
       * 查询跟进明细
       * @param row
       */
      houseTransactionType(){
        RequestURL.houseTransactionType().then(res=>{
          this.houseTransactionTypeOpt = res
        }).catch(err=>{
          this.$message({
            type: 'error',
            message: err.data.msg
          })
        })
      },

      /**
       * 查询房源跟进状态
       * @param row
       */
      queryHouseStatus(){
        RequestURL.queryHouseStatus().then(res=>{
          this.statusOpt = res
        }).catch(err=>{
          console.log(err)
          this.$message({
            type: 'error',
            message: err.data.msg
          })
        })
      },
      //获取跟进方式数据
      _getOptions () {
        getSelectValue( {param: 'followMethod'}).then(res => {
          this.options = res.data
          let temp = {}
          temp.fieldValue = '隐号通话'
          temp.id = '90000000'
          this.options.unshift(temp)
        })
      }
    },
    mounted(){
      this.houseTransactionType()
      this.queryHouseStatus()
      this._getOptions()
      this.timeDefaultShow()
    }
  }
</script>
<style lang="scss" scoped>
  .houseId {
    color: #409eff;
  &:hover {
     text-decoration: underline;
   }
  }
</style>
