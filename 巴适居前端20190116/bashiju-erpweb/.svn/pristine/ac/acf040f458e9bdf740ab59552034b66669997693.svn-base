<template>
  <div id="imageArea">
    <div align="right">
      <el-button type="primary" size="small" :disabled="checked">设置小区封面</el-button>
      <el-button type="primary" size="small" @click="uploadHandler">上传图片</el-button>
      <el-button type="primary" size="small" @click="downLoadHandle" :disabled="checked">下载</el-button>
      <el-button type="primary" size="small" @click="deleteHandle" :disabled="checked">删除</el-button>
    </div>
    <hr>

    <el-col :span="7" v-for="(item, index) in imageList" :key="item.id">
      <el-card inline :body-style="{ padding: '0px' }" >
        <img :src="item.path" class="image">
        <div style="padding: 14px;">
          <span>好吃的汉堡</span>
          <div class="bottom clearfix">
            <time class="time">
              <el-checkbox :checked="checked"></el-checkbox>
              {{item.pictureTypeName}}
            </time>
          </div>
        </div>
      </el-card>
    </el-col>

    <template>
      <el-dialog
        title="上传图片"
        :visible.sync="uploadDlg"
        :close-on-click-modal="false"
        width="50%"
        top="15vh"
        append-to-body
        :modal-append-to-body="false">
        <file-upload v-if="imgReset" :uploadCfg="uploadCfg" @handleClick="cancelClick" :communityData="data"></file-upload>
      </el-dialog>
    </template>
  </div>
</template>

<script>
  import * as CommunityPostURL from '@/request/manage/community'
  import { FILE_UPLOAD_URL } from '@/request/main'
  import FileUpload from './FileUpload'
  let FILE_URL

  export default {
    name: "CommunityImageList",
    components: { FileUpload },
    props:{
      data: {
        type: Object
      }
    },
    data(){
      return {
        imageList: [],
        imageTypeList: [],
        imgReset: false,
        uploadDlg: false,
        checked: false,
        // 图片上传URL
        uploadCfg: {
          // 图片上传后台请求地址
          url: this.getUploadUrl(),
          // 上传方式：true单张上传，否则为多图片上传
          isSimple: false
        },
        uploadUrl: this.getUploadUrl(),
        // 图片地址
        fileUrl: FILE_URL
      }
    },
    methods:{
      getUploadUrl(){
        return FILE_UPLOAD_URL
      },
      // 上传按钮
      uploadHandler(){
        this.imgReset = false
        this.$nextTick(()=>{
          this.imgReset = true
          this.uploadDlg = true
        })
      },
      // 删除小区图片
      deleteHandle(){
        let ids
        CommunityPostURL.deleteCommunityImage({ids: ids}).then(res=>{
          if(res.success){
            this.$message({
              type: 'success',
              message: res.msg
            })
          }else{
            this.$message({
              type: 'error',
              message: res.msg
            })
          }
        }).catch(err=>{
          console.log(err)
        })
      },

      // 设置小区封面
      setCommunityCoverImage(){
        let params
        CommunityPostURL.setCommunityCoverImage().then(res=>{
          if(res.success){
            this.$message({
              type: 'success',
              message: res.msg
            })
          }else{
            this.$message({
              type: 'error',
              message: res.msg
            })
          }
        }).catch(err=>{
          console.log(err)
        })
      },

      // 小区图片下载
      downLoadHandle(){
        let params
        CommunityPostURL.setCommunityCoverImage().then(res=>{
          if(res.success){
            this.$message({
              type: 'success',
              message: res.msg
            })
          }else{
            this.$message({
              type: 'error',
              message: res.msg
            })
          }
        }).catch(err=>{
          console.log(err)
        })
      },
      // 取消上传
      cancelClick(){
        this.uploadDlg = false
      },
      // 上传成功后的回调函数
      uploadSuccess(file){
        let res = file.response
        if(res.code === ERR_OK){
          this.uploadUrl = file.response.data.title
        }else{
          this.$message({
            type: 'error',
            message: '上传出错,errCode:${res.code}'
          })
        }
      },
      // 查询小区图片列表
      queryCommunityImageList(){
        // attachType 代表图片类型：1 图片 2 视频
        CommunityPostURL.queryCommunityImageList({attachType: '1',communityId: this.data.id}).then(res=>{
          if(res.success){
            this.imageList = res.data
            // console.log(this.imageList)
          }else{
            this.$message({
              type: 'error',
              message:res.msg
            })
          }
        }).catch(err=>{
          console.log(err)
          this.$message({
            type: 'error',
            message: err.msg
          })
        })
      },

      // 查询小区图片类型列表
      queryImageType(){
        CommunityPostURL.queryCommunityPicTypes().then(res=>{
          this.imageTypeList = res.data
        }).catch(err=>{
          console.log(err)
        })
      }
    },
    // 生命周期函数
    mounted() {
      if(this.data){
        this.queryImageType()
        this.queryCommunityImageList()
      }
    },
    watch:{
      data:{
        deep: true,
        handler(){
          this.queryImageType()
          this.queryCommunityImageList()
        }
      }
    }
  }
</script>

<style scoped>
  .image {
    width: 25%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .el-col {
    margin : 5px;
    height: 70px;
  }

  .el-checkbox{
    padding-right: 5px;
  }
</style>
