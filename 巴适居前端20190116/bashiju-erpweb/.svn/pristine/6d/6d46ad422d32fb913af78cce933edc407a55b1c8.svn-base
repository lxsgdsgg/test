<!-- 相关照片 -->

<template>
  <div>
    <div class="action-bar clearfix">
      <div class="pull-left">
        <el-button v-hasOnlyBtn="'uploadBtn'" type="primary" size="mini" @click="openFile">上传图片</el-button>
      </div>
      <div class="pull-right">
        <el-button type="primary" @click="choiceAll" size="mini">全选</el-button>
        <el-button v-hasOnlyBtn="'setCoverBtn'" @click="setCoverPic" :type="checkedList.length ? 'primary' : 'info'" :disabled="!checkedList.length" size="mini">设置封面</el-button>
        <el-button v-hasOnlyBtn="'singleDownload'" @click="downloadImgItem" :type="checkedList.length ? 'primary' : 'info'" :disabled="!checkedList.length" size="mini">单张下载</el-button>
        <el-button v-hasOnlyBtn="'batchDownload'" @click="downloadImages" :type="checkedList.length ? 'primary' : 'info'" :disabled="!checkedList.length" size="mini">批量下载</el-button>
        <el-button v-hasOnlyBtn="'delImage'" @click="delImages" :type="checkedList.length ? 'primary' : 'info'" :disabled="!checkedList.length" size="mini">删除</el-button>
      </div>
    </div>

    <div class="photo-wrap">

      <el-row :gutter="20">
        <el-col v-for="(item, index) in fileList" :key="index" :span="4.8">
          <el-card class="photo-item" :body-style="{ padding: '0px' }">
            <icon v-if="item.isCover" class="cover" name="cover_icon" width="60" hegiht="60" scale="6.5"></icon>
            <div class="img-box">
              <img @click="dialogVisibleViewPhoto=true" class="pos-center" :src="item.path">
            </div>

            <div @click="item.checked = !item.checked" class="bottom">
              <div class="clearfix">
                  <span class="pull-left">{{item.pictureTypeName}}</span>
                <span class="pull-right">{{item.operator}}</span>
              </div>
              <div class="bottom-content clearfix">
                <time class="time">{{$utils.timeFormat(item.updateTime)}}</time>
                <el-checkbox v-model="item.checked" size="mini" class="button"></el-checkbox>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>


    </div>

    <el-dialog
      title="上传成交图片"
      :visible.sync="dialogVisible"
      width="1100px"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      append-to-body
    >
      <base-file-upload @on-success="fileSuccess" :data="uploadData"
            :postAction="uploadUrl" ref="fileUpload"
            @handleUploadAll="handleUploadAll" @handleUploadItem="handleUploadItem">

        <template slot-scope="scope">

          <el-form ref="uploadForm" :inline="true" :model="scope.file.data" size="small">
            <el-form-item
              label="图片类型"
              prop="pictureType"
              :rules="[
                { required: true, message: '请选择图片类型', trigger: 'change' }
              ]"
            >
              <el-select clearable  style="width: 120px" v-model="scope.file.data['pictureType']" placeholder="图片类型">
                <el-option v-for="item in picTypeSele" :label="item.name" :value="item.id" :key="item.id"></el-option>
              </el-select>
            </el-form-item>
          </el-form>

        </template>

      </base-file-upload>
    </el-dialog>

    <el-dialog
      title="小区图片"
      :visible.sync="dialogVisibleViewPhoto"
      width="800px"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      append-to-body
      :modal-append-to-body="false"
    >

      <el-carousel trigger="click" :interval="4000" type="card" height="300px">
        <el-carousel-item v-for="item in fileList" :key="item.id">

          <div class="carousel-item">
            <img class="pos-center" :src="item.path">
            <p class="text">{{item.pictureTypeName}}</p>
          </div>

        </el-carousel-item>
      </el-carousel>

    </el-dialog>

  </div>
</template>

<script>
  import photoMixin from './photo/photoMixin'
  export default {
    mixins: [photoMixin]
  }
</script>

<style scoped lang="scss">
  @import "photo/photoComp";
</style>
