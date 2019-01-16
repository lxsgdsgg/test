<!-- 相关照片 -->

<template>
  <div>
    <div class="action-bar clearfix">
      <div class="pull-left">
        <el-button v-hasMultipleBtn="['uploadImg',dealInfo]" type="primary" size="mini" @click="openFile">上传图片</el-button>
      </div>
      <div class="pull-right">
        <el-button type="primary" @click="choiceAll" size="mini">全选</el-button>
        <el-button v-hasMultipleBtn="['downloadAttach',dealInfo]" @click="downloadImgItem" :type="checkedList.length ? 'primary' : 'info'" :disabled="!checkedList.length" size="mini">单张下载</el-button>
        <el-button v-hasMultipleBtn="['delPaymentBtn',dealInfo]" @click="downloadImages" :type="checkedList.length ? 'primary' : 'info'" :disabled="!checkedList.length" size="mini">批量下载</el-button>
        <el-button v-hasMultipleBtn="['batchUploadAttach',dealInfo]" @click="delImages" :type="checkedList.length ? 'primary' : 'info'" :disabled="!checkedList.length" size="mini">删除</el-button>
      </div>
    </div>

    <div class="photo-wrap">

      <el-row :gutter="20">
        <el-col v-for="(item, index) in fileList" :key="index" :span="4.8">
          <el-card class="photo-item" :body-style="{ padding: '0px' }">
            <div class="img-box">
              <img @click="dialogVisibleViewPhoto = true" class="pos-center" :src="item.path">
            </div>

            <div @click="item.checked = !item.checked" class="bottom">
              <div class="clearfix">
                <span class="pull-left" v-if="item.attachType === '00'">合同扫描件</span>
                <span class="pull-left" v-if="item.attachType === '01'">业主材料扫描件</span>
                <span class="pull-left" v-if="item.attachType === '02'">客户材料扫描件</span>
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
              prop="attachType"
              :rules="[
                { required: true, message: '请选择图片类型', trigger: 'change' }
              ]"
            >
              <el-select clearable  style="width: 120px" v-model="scope.file.data['attachType']" placeholder="图片类型">
                <el-option v-for="item in selectOpts" :label="item.value" :value="item.key" :key="item.key"></el-option>
              </el-select>
            </el-form-item>
          </el-form>

        </template>

      </base-file-upload>
    </el-dialog>

    <el-dialog
      title="成交详细图片"
      :visible.sync="dialogVisibleViewPhoto"
      width="800px"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
    >

      <el-carousel trigger="click" :interval="4000" type="card" height="300px">
        <el-carousel-item v-for="item in fileList" :key="item.id">

          <div class="carousel-item">
            <img class="pos-center" :src="item.path">
            <p class="text">{{item.pictureName}}</p>
          </div>

        </el-carousel-item>
      </el-carousel>

    </el-dialog>

  </div>
</template>

<script>
  import photoMixin from './photo/photoMixin'
  export default {
    mixins: [photoMixin],
    props:{
      dealInfo: {
        type: Object,
        required: true,
        default(){
          return null
        }
      }
    }
  }
</script>

<style scoped lang="scss">
  @import "photo/photoComp";
</style>
