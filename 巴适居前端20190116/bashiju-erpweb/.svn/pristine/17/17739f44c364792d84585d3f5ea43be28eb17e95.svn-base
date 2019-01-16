<template>
  <div class="page-content">
    <div class="page-content-hd" v-loading="loadingView">
      <div class="query-form">
        <el-form size="small" :inline="true"  ref="form">

          <el-form-item label="城市" prop="cityCode" style="margin-left: 100px;">
            <base-city-cascader v-model="queryForm.cityCode" @initSelectData="initSelectData"></base-city-cascader>
          </el-form-item>

          <el-form-item label="类型" prop="type">
            <el-select v-model="queryForm.type" clearable placeholder="请选择">
              <el-option
                v-for="item in queryOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="handleQuery" >查询</el-button>
          </el-form-item>

          <el-form-item class="pull-right">
            <el-button   type="primary" @click="handleSet">设置</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div class="page-content-bd">
        <el-table
          :data="tableData"
          border
          align="center"
          style="width: 100%"

        >
        <el-table-column
          align="center"
          label="类型">
          <template slot-scope="scope">
            <span v-if="scope.row.type===0">二手房</span>
            <span v-if="scope.row.type===1">出租房</span>
            <span v-if="scope.row.type===2">新房</span>
            <span v-if="scope.row.type===3">小区</span>
          </template>
        </el-table-column>

        <el-table-column
          prop="conditionCode"
          align="center"
          label="编号">
        </el-table-column>


        <el-table-column
          prop="conditionName"
          align="center"
          label="名称">
        </el-table-column>

        <el-table-column
          prop="valueCodes"
          align="center"
          label="值">
        </el-table-column>

        <el-table-column
          prop="valueNames"
          align="center"
          label="值描述">
        </el-table-column>

        </el-table>

        <b-pagination
          :listQuery="listQuery"
          @handleSizeChange="handleSizeChange"
          @handleCurrentChange="handleCurrentChange">
        </b-pagination>
      </div>
    </div>
    <el-dialog title="城市设置搜索条件" width="600px" :visible.sync="dialogVisible">
      <el-form >
        <el-form-item  >
          <el-tree
            ref="sourceTree"
            :data="treeData"
            show-checkbox
            node-key="id"
            :default-checked-keys="checkedData"
            :props="defaultProps">
          </el-tree>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary"  @click="saveData">确 定</el-button>
        <el-button @click="dialogVisible = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>


<script>
  import PageList from '@/mixins/pageList'
  import BaseCityCascader from '@/components/BaseCascader/city' // 城市级联组件
  import {queryConditionTree, queryCityConditionList, saveCityCondition,queryCityAvgPriceList,
    addCityAvgPrice,updateCityAvgPrice,deleteCityAvgPrice} from '@/request/manage/queryCodition'
  export default{
    mixins: [PageList],
    components: {BaseCityCascader},
    data () {
      return {
        treeData:[],
        checkedData:[],
        defaultProps: {
          children: 'children',
          label: 'conditionName'
        },
        queryForm: {cityCode:'',type:''},
        queryOptions: [{label:'二手房',value:0},{label:'出租房',value:1},{label:'新房',value:2},{label:'小区',value:3}],
        tableData:[],
        loadingView: false,
        dialogVisible:false
      }
    },
    methods:{
      initSelectData(data) {
        console.log('**************',data)
        if (data!=null&&data.length>0){
          this.queryForm.cityCode = data[0].code
          this.handleQuery()
        }
      },
      handleQuery () {
        this.listQuery.page = 1
        this.listQuery.currentPage = 1
        this._loadData()
      },
      _loadData () {
        if (this.queryForm.cityCode) {
          this.loadingView = true
          let params = Object.assign({}, this.queryForm, {
            limit: this.listQuery.limit,
            page: this.listQuery.page
          })
          queryCityConditionList(params).then(res => {
            this.tableData = res.data
            this.listQuery.total = res.count
            this.loadingView = false
          }).catch(()=>{
            this.loadingView = false
          })
        }

      },
      handleSet(){
        this.dialogVisible=true
        let param = {cityCode:this.queryForm.cityCode}
        queryConditionTree(param).then((res)=>{
          if(res.success){
            this.treeData=res.data.root
            this.checkedData=res.data.ids
          }
        })

      },
      saveData(){
       let nodes = this.$refs['sourceTree'].getCheckedNodes(true);
        this.$confirm('确定要保存吗？', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.loadingView = true
          saveCityCondition({cityCode:this.queryForm.cityCode,jsonData:JSON.stringify(nodes)}).then(res => {
            this.loadingView = false
            this.handleQuery()
            this.$message({
              type: 'success',
              message: res.msg || '操作成功'
            })
            this.dialogVisible=false
          }).catch(()=>{
            this.loadingView = false
          })
        })
      }
    }
  }
</script>

<style lang="scss" scoped>

</style>
