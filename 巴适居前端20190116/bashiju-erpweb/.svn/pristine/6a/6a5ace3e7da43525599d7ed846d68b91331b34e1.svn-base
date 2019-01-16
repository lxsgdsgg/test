<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form">
        <el-form size="mini" :inline="true" :model="queryForm" ref="queryForm" class="demo-form-inline">
          <el-form-item label="选择片区" prop="areaCode">
              <city-selector
                v-model="queryForm.areaCode"
                :url="regionUrl"
                :props="cascaderProps"
                :dataProps="cascaderDataProps"
                :changeOnSelect="false"
                @change="setSearchValue"
              >
              </city-selector>
          </el-form-item>

          <!--<el-form-item label="选择片区" prop="regionId">-->
            <!--<el-select v-model="queryForm.regionId">-->
              <!--<el-option-->
                <!--v-for="item in regionOpt"-->
                <!--:key="item.name"-->
                <!--:label="item.name"-->
                <!--:value="item.id"-->
              <!--&gt;-->
              <!--</el-option>-->
            <!--</el-select>-->
          <!--</el-form-item>-->
          <el-form-item label="小区名称" prop="pinyin">
            <el-input v-model="queryForm.pinyin"></el-input>
          </el-form-item>

          <el-form-item label="热门小区" prop="hot">
            <el-select v-model="queryForm.hot">
              <el-option label="否" :value="0"></el-option>
              <el-option label="是" :value="1"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="是否标注" prop="biaozhu">
            <el-select v-model="queryForm.biaozhu">
              <el-option label="已标注" :value="1"></el-option>
              <el-option label="未标注" :value="2"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="handlerQuery(false)" :loading="loadingQueryBtn">查询</el-button>
            <el-button @click="resetForm('queryForm')">清空</el-button>
          </el-form-item>
          <el-form-item>
            <el-button v-hasOnlyBtn="'addBtn'" type="primary" @click="handleAdd">新增</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>

    <div class="page-content-bd" v-loading="loadingView">
      <el-table
        :data="tableData"
        border
        align="center"
        style="width: 100%"
        size="mini"
      >

        <el-table-column
          prop="name"
          align="left"
          label="小区名称">
          <template slot-scope="scope">
            <a @click="handleEditDetail(scope.row)"
               href="javascript:;" class="community" type="text">
              {{scope.row.name}}
            </a>
          </template>
        </el-table-column>

        <el-table-column
          prop="recordName"
          align="left"
          label="备案名称">
        </el-table-column>

        <el-table-column
          prop="pinyin"
          align="left"
          label="拼音检索">
        </el-table-column>

        <el-table-column
          prop="reginName"
          align="left"
          label="所在片区">
        </el-table-column>

        <el-table-column
          prop="areaName"
          align="left"
          width="120"
          label="行政区划">
        </el-table-column>

        <el-table-column
          prop="salePrice"
          align="left"
          width="120px"
          label="出售均价">
          <template slot-scope="scope">
            {{scope.row.salePrice}}元/m²
          </template>
        </el-table-column>

        <el-table-column
          prop="biaozhu"
          align="left"
          width="80px"
          label="是否标注">
          <template slot-scope="scope">
            {{exchangeBiaozhu(scope.row)}}
          </template>
        </el-table-column>

        <el-table-column
          prop="isHot"
          align="left"
          width="80px"
          label="热门小区">
          <template slot-scope="scope">
            {{exchangeHot(scope.row)}}
          </template>
        </el-table-column>

        <el-table-column
          prop="address"
          align="left"
          width="300px"
          label="小区地址">
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
        >
          <template slot-scope="scope">
            <el-button v-hasOnlyBtn="'editBtn'" @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
            <el-button v-hasOnlyBtn="'delBtn'" @click="handleDelete(scope.row)" type="text" size="small">删除</el-button>
            <!--<el-button @click="handleEditDetail(scope.row)" type="text" size="small">编辑详细</el-button>-->
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
      <el-dialog
        :title="title"
        :visible.sync="editDialogVisible"
        :close-on-click-modal="false"
        width="400px">
        <community-edit v-if="hackReset" @handleClick="handleEditDialog" :data="currentRowData" :isAdd="isAdd"></community-edit>
      </el-dialog>
    </template>

    <template>
      <el-dialog
        title="小区详细"
        :visible.sync="editDetailDlgVisible"
        :close-on-click-modal="false"
        top="5vh"
        width="1200px"
      >
        <detail-component v-if="dlgReset" @close="handleEditDetailDialog" :data="currentRowData"></detail-component>
      </el-dialog>
    </template>

  </div>
</template>

<script>
  import CommunityEdit from './components/CommunityEdit'
  import DetailComponent from './components/DetailComponent'
  import CitySelector from '@/components/BaseCascader'

  import {queryCommunityPageList,
    queryCommunityDetail,
    addCommunity,
    updateCommunity,
    deleteCommunity,
    updateCommunityDetail,
    queryAllRegionList} from '@/request/manage/community'
  import {systemDelLog} from '@/request/log/systemPlatformLog'

export default {
  name: 'community',
  components: {CommunityEdit,CitySelector,DetailComponent},
  data () {
    return {
      title: '',
      queryForm: {
        areaCode: '',
        regionId: '',
        pinyin: '',
        hot: '',
        biaozhu: ''
      },
      regionUrl: 'manage/commonselect/queryOpenCityCountyRegion',//城市、区域、片区
      cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
      cascaderProps: { // 级联下拉组件配置选项
        value: 'code', // 指定选项的值为选项对象的某个属性值
        label: 'name' // 指定选项标签为选项对象的某个属性值
      },
      isAdd: false,
      hackReset: true,
      dlgReset: true,
      tableData: [],
      currentRowData: null,
      editDialogVisible: false,
      editDetailDlgVisible: false,
      loadingQueryBtn: false,
      loadingView: false,
      listQuery: { page: 1, limit: 10, total: 0},
      regionOpt: []
    }
  },
  methods: {
    // 设置查询条件值
    setSearchValue(val,name,data){
      this.queryForm.areaCode = data.parent.code
      this.queryForm.regionId = val
    },

    // 获取片区数据
    queryAllRegionListByAreaCode(val,name,data){
      if (data.dataType !== 'area') {
        this.$message({
          showClose: true,
          type: 'warning',
          message: '只能选择大区!'
        })
        setTimeout(() => {
        }, 50)
      }else{
        if(!this.queryForm.areaCode){
          this.$message({
            type: 'warning',
            message: '请先选择行政区'
          })
          return false
        }
        queryAllRegionList({code:this.queryForm.areaCode}).then(res=>{
          this.regionOpt = res.data
        }).catch(err=>{
          console.log(err)
        })
      }
    },

    //页面改变
    handleSizeChange(val) {
      this.listQuery.limit = val
      this.queryCommunityPageList(false)
    },
    //当前页改变
    handleCurrentChange(val) {
      this.listQuery.page = val
      this.queryCommunityPageList(false)
    },

    // 添加小区
    handleAdd () {
      this.hackReset = false
      this.title = '新增小区'
      this.$nextTick(() => {
        this.hackReset = true
        this.isAdd = true
        this.currentRowData = null
        this.editDialogVisible = true
      })
    },

    // 修改小区
    handleEdit (row) {
      this.title = '修改小区'
      this.hackReset = false
      this.$nextTick(() => {
        this.hackReset = true
        this.isAdd = false
        this.currentRowData = row
        this.editDialogVisible = true
      })
    },

    // 修改小区详细
    handleEditDetail (row) {
      this.dlgReset = false
      this.$nextTick(() => {
        this.dlgReset = true
        this.currentRowData = row
        this.editDetailDlgVisible = true
      })
    },

    // 删除小区
    handleDelete (row) {
      this.$confirm('确定删除该数据？, 是否继续?', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteCommunity({id:row.id}).then(res=>{
          if(res.success){
            this.$message({
              type: 'success',
              message: res.msg
            })
            this.queryCommunityPageList()
          }else{
            this.$message({
              type: 'error',
              message: res.msg
            })
          }

          let message = {sourceCode:row.name,sourceTypeId:'4',operatTypeId:'3'
            ,logContent: '删除小区：'+ row.name}
          systemDelLog({message: JSON.stringify(message)})
        }).catch((err) => {
          this.$message({
            type: 'error',
            message: err.data.msg
          })
        })
      })
    },

    // 提交数据后的回调函数
    handleEditDialog (action) {
      if (action && action === 1) { // 确定
        this.editDialogVisible = false
        this.queryCommunityPageList(false)
      } else if (action && action === 2) { // 取消
        this.editDialogVisible = false
      }
      // this.currentRowData = null
    },

    // 提交小区详细数据后的回调函数
    handleEditDetailDialog () {
      this.editDetailDlgVisible = false
      this.queryCommunityPageList(false)
      this.currentRowData = null
    },

    /*点击查询按钮的查询*/
    handlerQuery(isLoading){
      this.listQuery.page = 1
      this.listQuery.currentPage = 1
      this.queryCommunityPageList(isLoading)
    },

    // 查询小区数据信息
    queryCommunityPageList (isLoading) {
      let params = Object.assign({},this.queryForm, {
        page: this.listQuery.page,
        limit: this.listQuery.limit
      })
      this.loadingQueryBtn = isLoading
      this.loadingView = true
      queryCommunityPageList(params).then(res=>{
        this.tableData = res.data
        this.listQuery.total = res.count
        this.loadingQueryBtn = false
        this.loadingView = false
      }).catch(err=>{
        console.log(err)
        this.loadingQueryBtn = false
        this.loadingView = false
      })
    },

    // 格式化日期
    timeFormat(row, column, cellValue) {
      return this.$utils.timeFormat(cellValue)
    },

    // 重置查询表单
    resetForm(formName) {
      this.queryForm.areaCode = ''
      this.queryForm.regionId = ''
      this.$refs[formName] && this.$refs[formName].resetFields()
    },
    // 设置表单数据
    setForm(data) {
      for(let i in this.queryForm){
        this.queryForm[i] = data[i]
      }
    },
    // 通过地图坐标转为是否已标注
    exchangeBiaozhu(row){
      if(row.longitude && row.latitude){
        return '已标注'
      }else{
        return '未标注'
      }
    },
    // 是否热门小区展示
    exchangeHot(row){
      if(row){
        if(row.isHot === 1){
          return '是'
        }else{
          return '否'
        }
      }
    }
  },
  mounted () {
    this.queryCommunityPageList(false)
  }
}
</script>

<style lang="scss" scoped>
  .community {
    color: #409eff;
    &:hover {
      text-decoration: underline;
    }
  }
</style>
