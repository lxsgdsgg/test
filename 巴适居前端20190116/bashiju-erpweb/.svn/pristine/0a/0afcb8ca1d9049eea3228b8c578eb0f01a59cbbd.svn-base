<template>
  <div width="500px;">
    <el-form v-loading="loadingForm" label-width="100px">
      <el-form-item label="房产文章分类" class="grid-content bg-purple tree-wrap">
        <el-tree
          :data="tableData"
          show-checkbox
          node-key="id"
          ref="tree"
          :default-expanded-keys="keys"
          :default-checked-keys="keys"
          :props="defaultProps"
          >
        </el-tree>
      </el-form-item>

      <el-form-item class="margin-b-none">
        <el-button type="primary" @click="saveGrantArticle" :loading="saveDataBtn" size="small">保存</el-button>
        <!--<el-button type="primary" @click="cancelArticleClose" size="small">关闭</el-button>-->
      </el-form-item>
    </el-form>
  </div>
</template>
<style></style>
<script>
  import {queryArticleClassTree,saveCityArticleClasses} from '@/request/manage/HouseArticleClassify'
  import BaseTree from '@/components/BaseTree'


  export default {
    name:'ArticleTypeSet',
    components: {BaseTree},
    props:{
      data:{
        type: Object,
        required: true,
        default(){
          return null
        }
      }
    },
    data() {
      return {
        keys: [],
        tableData:[],
        loadingGrantBtn: false,
        loadingForm: false,
        saveDataBtn: false,
        defaultProps: {
          children: 'children',
          label: 'name'
        }
      }
    },
    methods: {
      cancelArticleClose(){
        this.$emit('cancelClick')
      },
      _loadData(){
        this.loadingForm = true
        queryArticleClassTree({cityCode: this.data.cityCode}).then(res =>{
          res.data.root.forEach(item=>{
            item.label = item.name
          })
          this.tableData = res.data.root
          this.keys = res.data.ids
          this.loadingForm = false
        })
      },

      saveGrantArticle(){
        this.saveDataBtn = true
        saveCityArticleClasses({cityCode: this.data.cityCode,jsonArray: JSON.stringify(this.$refs.tree.getCheckedNodes())}).then(res =>{
          if(res.success){
            this.$message({
              type: 'success',
              message: '保存成功！'
            })
            this._loadData()
          }else{
            this.$message({
              type: 'error',
              message: '保存失败！'
            })
          }
          this.saveDataBtn = false
        }).catch(err=>{
          console.log(err)
          this.saveDataBtn = false
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
