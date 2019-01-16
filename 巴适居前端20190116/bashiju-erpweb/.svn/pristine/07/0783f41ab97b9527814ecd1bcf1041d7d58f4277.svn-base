<template>
  <div>
    <el-form :model="queryForm" :inline="true" ref="queryForm" size="small">
      <el-form-item prop="userId">
        <base-cascader v-model="queryForm.userId" :url="getReferenceUserSelectUrl"
            :props="cascaderProps" :dataProps="cascaderDataProps"  style="width: 120px;" placeholder="选择门店"></base-cascader>
      </el-form-item>

      <el-form-item>
        <el-input type="number" :min="0" v-model="queryForm.minBuildSpace" style="width: 120px;" placeholder="输入最小面积"></el-input>-
        <el-input type="number" :min="0" v-model="queryForm.maxBuildSpace" style="width: 120px;" placeholder="输入最大面积"></el-input>
      </el-form-item>

      <el-form-item prop="communityId">
        <community-component style="width: 120px"
          disabled
          v-model="communityId"
          :url="communityUrl"
          :props="cascaderProps"
          :dataProps="cascaderDataProps">
        </community-component>
      </el-form-item>

      <el-form-item prop="building">
        <el-input type="text" v-model="queryForm.building" style="width: 120px;" placeholder="输入座栋"></el-input>
      </el-form-item>

      <el-form-item prop="vagueData">
        <base-vague-autocomplete
          class="w300" style="width: 240px;"
          :dataProps="vagueQueryInfo"
          v-model="queryForm.vagueData" @select="handleSelectInfo"
          placeholder="房源编号,业主姓名,门牌号,业主电话,小区名称,钥匙编号,委托编号,推荐标签,房源备注">
        </base-vague-autocomplete>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="_loadData(true)" :loading="queryBtnLoading">搜索</el-button>
        <el-button type="primary" @click="resetForm">清空</el-button>
      </el-form-item>
    </el-form>

    <el-table :data="tableData" border size="mini"
          align="center" tooltip-effect="light"
          style="width: 100%" v-loading="loadingView">
      <el-table-column prop="houseId" align="left" label="房源编号" show-overflow-tooltip width="120px">
        <template slot-scope="scope">
          <a @click="toDetail(scope.row.id, scope.row.houseUsesId,scope.row.houseId)" href="javascript:;" class="houseId" type="text">{{scope.row.houseId}}</a>
        </template>
      </el-table-column>
      <el-table-column prop="transactionType" align="left" label="类型" show-overflow-tooltip></el-table-column>
      <el-table-column prop="status" align="left" label="状态" show-overflow-tooltip></el-table-column>
      <el-table-column prop="communityName" align="left" label="小区名称" show-overflow-tooltip></el-table-column>
      <el-table-column prop="buildingsName" align="left" label="座栋" show-overflow-tooltip></el-table-column>
      <el-table-column prop="buildingsUnitName" align="left" label="门牌号" show-overflow-tooltip></el-table-column>
      <el-table-column prop="owner" align="left" label="业主姓名" show-overflow-tooltip></el-table-column>
      <el-table-column prop="houseType" align="left" label="房型" show-overflow-tooltip></el-table-column>
      <el-table-column prop="buildSpace" align="left" label="面积" show-overflow-tooltip></el-table-column>
      <el-table-column prop="sellingPrice" align="left" label="售价" show-overflow-tooltip>
        <template slot-scope="scope">
          {{scope.row.sellingPrice}}万
        </template>
      </el-table-column>
      <el-table-column prop="rentPrice" align="left" label="租价" show-overflow-tooltip>
        <template slot-scope="scope">
          {{scope.row.sellingPrice}}元
        </template>
      </el-table-column>
      <el-table-column prop="operator" align="left" label="维护人" show-overflow-tooltip></el-table-column>
      <el-table-column prop="entrustCode" align="left" label="委托编号" show-overflow-tooltip></el-table-column>
      <el-table-column prop="mainterDept" align="left" label="部门名称" show-overflow-tooltip></el-table-column>
    </el-table>
    <!--分页控件-->
    <b-pagination
      :listQuery="listQuery"
      @handleSizeChange="handleSizeChange"
      @handleCurrentChange="handleCurrentChange">
    </b-pagination>
  </div>
</template>

<script>
  import DeptComponent from '@/components/BaseCascader/dept'
  import CommunityComponent from '@/components/BaseCascader'
  import BaseVagueAutocomplete from '@/components/BaseVagueAutocomplete'
  import PageList from '@/mixins/pageList'
  import {queryHouseDetailByConditions} from '@/request/house/houseSeekByMapOrMetro'
  import { queryCommunitySelectWithRegion } from '@/request/app'
  import BaseCascader from '@/components/BaseCascader'

  // 模糊查询其他  门牌号 电话 编号..
  const vagueQueryInfo = [
    {label: '房源编号', type: 1},
    {label: '业主姓名', type: 2},
    {label: '门牌号', type: 3},
    {label: '业主电话', type: 4},
    {label: '小区名称', type: 5},
    {label: '钥匙编号', type: 6},
    {label: '委托编号', type: 7},
    {label: '推荐标签', type: 8},
    {label: '房源备注', type: 9}
  ]

  export default {
    name: "ShowHouseDetail",
    mixins: [PageList],
    components: {DeptComponent,CommunityComponent,BaseVagueAutocomplete,BaseCascader},
    props:{
      communityId:{
        type: String,
        required: true,
        default(){
          return ''
        }
      },
      stationId:{
        type: String,
        required: true,
        default(){
          return ''
        }
      },
      query: {
        type: Object
      }
    },
    data(){
      return{
        tableData: [],
        vagueQueryInfo: vagueQueryInfo,
        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        communityUrl: 'manage/commonselect/queryOpenCityCountyRegionCommunity',
        getReferenceUserSelectUrl: 'manage/commonselect/queryOpenCityCompanyDepartUser',
        queryForm:{
          userId: '',
          minBuildSpace: '',
          maxBuildSpace: '',
          building: '',
          vagueData: ''
        },
        vagueData: {},
        loadingView: false,
        queryBtnLoading: false
      }
    },

    methods:{
      toDetail (id, name,code) {
        this.$emit('handlerClick')
        this.$router.push({ path: './houseUsedDetail/' + id + '/' + code, query: { formName: name}})
      },
      // 点击其他信息输入建议项赋值
      handleSelectInfo (item) {
        // 点击坐栋输入建议项赋值
        this.vagueData = {
          type: item.type,
          value: item.value,
        }
      },
      _loadData(btnQuery){
        if(btnQuery){
          this.queryBtnLoading = true
          this.listQuery.page = 1
          this.listQuery.currentPage = 1
        }
        this.loadingView = true
        let param = {...this.queryForm}
        param.communityId = this.communityId
        param.stationId = this.stationId
        param.vagueData = this.vagueData
        let params = Object.assign({},{jsonData: JSON.stringify(param)},{
          page: this.listQuery.page,limit: this.listQuery.limit
        })

        queryHouseDetailByConditions(params).then(res=>{
          this.tableData = res.data
          this.listQuery.total = res.count
          this.loadingView = false
          this.queryBtnLoading = false
        }).catch(err=>{
          console.log(err)
          this.loadingView = false
          this.queryBtnLoading = false
        })
      },

      resetForm(){
        if(this.$refs['queryForm']){
          this.$refs['queryForm'].resetFields()
          this.vagueData = {}
          this.queryForm.minBuildSpace = ''
          this.queryForm.maxBuildSpace = ''
        }
      }
    },

    watch:{
      // 监听查询 如果当前输入的内容和之前绑定的输入建议不一致 则说明没有点击建议项 赋默认值
      vagueData: function (newValue) {
        if (!newValue) return

        let defaultVal = {
          type: vagueQueryInfo[0].type,
          value: newValue,
        }

        if (this.vagueData) {
          if (newValue !== this.vagueData) {
            this.vagueData = defaultVal
          }
        } else {
          // 没有点击过输入建议项
          this.vagueData = defaultVal
        }
      }
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

