<template>
  <div>
    <el-form v-loading="loadingForm" label-width="150px" class="grid-content bg-purple tree-wrap">
      <el-form-item label="授权导航">
        <el-tree
          :data="barData"
          show-checkbox
          node-key="id"
          ref="tree"
          :default-expanded-keys="keys"
          :default-checked-keys="keys"
          :props="defaultProps">
        </el-tree>
      </el-form-item>

      <el-form-item class="margin-b-none">
        <el-button type="primary" :loading="loadingGrantBtn" @click="grantNav" size="small">确认</el-button>
        <!--<el-button type="primary" @click="grantNav" size="small">取消</el-button>-->
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import * as RequestURL from '@/request/manage/cityOpen' // 请求后端URL路径
  import {systemAddLog, systemUpdateLog, systemQueryLog,systemDelLog} from '@/request/log/systemPlatformLog'

  export default {
    name: "AuthorNavigation",
    props:{
      data:{
        type: Object,
        required: true,
        default(){
          return null
        }
      }
    },
    data(){
      return {
        loadingGrantBtn: false,
        keys: [],
        defaultProps: {
          children: 'children',
          label: 'label'
        },
        barData: [],
        loadingForm: false
      }
    },
    methods:{
      cancelAuthorClose(){
        this.$emit('cancelClick')
      },
      //导航数据
      getBars(){
        let params = {
          cityOpenInfoId: this.data.id
        }
        RequestURL.getBars(params).then(res => {
          this.barData = [];
          this.levelInfo1 = [];
          this.levelInfo2 = [];
          this.keys = [];
          for(let i=0;i<res.data.length;i++){
            if(res.data[0].hasGrantId ){
              this.keys = res.data[0].hasGrantId.split(",")
            }
            if(res.data[i].parentId==0){
              this.levelInfo1.push(res.data[i]);
            }else{
              this.levelInfo2.push(res.data[i]);
            }
          }
          if(this.levelInfo1.length>0) {
            for (let a = 0; a < this.levelInfo1.length; a++) {
              let item = {
                id: this.levelInfo1[a].id,
                label: this.levelInfo1[a].name
              }
              if(this.levelInfo2.length>0) {
                let nextChildArr = [];
                for (let b = 0; b < this.levelInfo2.length; b++) {
                  if(this.levelInfo1[a].id == this.levelInfo2[b].parentId){
                    let nextItem = {
                      id: this.levelInfo2[b].id,
                      label: this.levelInfo2[b].name
                    }
                    nextChildArr.push(nextItem);
                  }
                }
                item.children = nextChildArr;
              }
              this.barData.push(item);
            }
          }
        })
      },
      //授权导航
      grantNav(){
        let data = [];
        for(let i = 0;i<this.$refs.tree.getCheckedKeys().length;i++){
          let item = {
            navigationBarId: this.$refs.tree.getCheckedKeys()[i]
          }
          data.push(item);
        }
        const params = {
          jsonData: JSON.stringify(data),
          cityOpenInfoId:  this.data.id
        }
        this.$confirm('确定保存编辑的信息吗？, 是否继续?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.loadingGrantBtn = true
          RequestURL.saveNavigationBarPermission(params).then(res => {
            this.loadingGrantBtn = false
            if (res.success) {
              this.$message({
                type: 'success',
                message: res.msg || '操作成功'
              })
              this.getBars(this.data.id);
              //写日志
              let logContent = '给城市：'+this.cityForBar+"授权导航"
              let message = {
                sourceCode: '平台开通城市',
                sourceTypeId: 19,
                operatTypeId: 4,
                logContent: logContent
              }
              systemAddLog({message: JSON.stringify(message)}).then(res => {
                console.log(res)
              })
            }else{
              this.$message({
                type: 'warning',
                message: res.msg
              })
            }
          }).catch((err) => {
            console.log(err);
            this.loadingGrantBtn = false
            this.$message({
              type: 'info',
              message: err.msg || '保存失败'
            })
          })
        })
      }
    },

    mounted(){
      this.getBars()
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
