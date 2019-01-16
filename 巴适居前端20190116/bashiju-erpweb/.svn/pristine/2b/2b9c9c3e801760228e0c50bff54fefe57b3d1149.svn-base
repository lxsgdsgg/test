<template>
  <div>
    <el-form v-loading="loadingView" label-width="150px" class="grid-content bg-purple tree-wrap">
      <el-form-item label="城市设置搜索条件">
        <el-tree
          ref="sourceTree"
          :data="treeData"
          show-checkbox
          node-key="id"
          :default-expanded-keys="checkedData"
          :default-checked-keys="checkedData"
          :props="defaultProps">
        </el-tree>
      </el-form-item>

      <el-form-item class="margin-b-none">
        <el-button type="primary" :loading="loadingGrantBtn" @click="saveData" size="small">保存</el-button>
        <!--<el-button type="primary" @click="grantNav" size="small">取消</el-button>-->
      </el-form-item>
    </el-form>
  </div>
</template>


<script>
  import {queryConditionTree, queryCityConditionList, saveCityCondition,queryCityAvgPriceList,
    addCityAvgPrice,updateCityAvgPrice,deleteCityAvgPrice} from '@/request/manage/queryCodition'
  export default{
    name: 'ClientSearchQuerySet',
    props:{
      data:{
        type: Object,
        required: true,
        default(){
          return null
        }
      }
    },
    data () {
      return {
        treeData:[],
        checkedData:[],
        defaultProps: {
          children: 'children',
          label: 'conditionName'
        },
        tableData:[],
        loadingView: false,
        loadingGrantBtn: false
      }
    },
    methods:{
      _loadData(){
        this.loadingView=true
        let param = {cityCode: this.data.cityCode}
        queryConditionTree(param).then((res)=>{
          if(res.success){
            this.treeData = res.data.root
            this.checkedData = res.data.ids
          }
          this.loadingView = false
        })

      },
      saveData(){
        let nodes = this.$refs['sourceTree'].getCheckedNodes(true);
        this.$confirm('确定要保存吗？','提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.loadingGrantBtn = true
          saveCityCondition({cityCode:this.data.cityCode,jsonData:JSON.stringify(nodes)}).then(res => {
            if(res.success){
              this.$message({
                type: 'success',
                message: res.msg || '操作成功'
              })
              this._loadData()
            }else{
              this.$message({
                type: 'success',
                message: res.msg || '操作失败!'
              })
            }
            this.loadingGrantBtn = false
          }).catch(()=>{
            this.loadingGrantBtn = false
          })
        })
      }
    }
  }
</script>

<style lang="scss" scoped>
  .wrapper {
    width: 800px;
  }

  .tree-wrap {
    min-height: calc(100vh - 450px);
    max-height: calc(100vh - 450px);
    overflow-y: auto;
  }
</style>
