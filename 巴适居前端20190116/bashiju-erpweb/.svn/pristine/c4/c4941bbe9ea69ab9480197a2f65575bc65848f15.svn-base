<template>
  <div>
    <el-button icon="el-icon-upload" size="mini" @click="openFile">上传视频</el-button>

    <div class="video-wrap">
      <video
        v-if="Object.keys(video).length"
        :src="video.path"
        controls="controls" width="800"></video
      >
    </div>

    <el-dialog
      title="上传房源视频"
      :visible.sync="dialogVisible"
      width="1100px"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      append-to-body
    >
      <base-file-upload
        @on-success="fileSuccess"
        :data="uploadData" :postAction="uploadUrl"
        ref="fileUpload"
        :simple="true" :multiple="false"
        :extensions="'mp4'"
        :accept="'video/mp4'"
        :size="1024 * 1024 * 500"
        @handleUploadAll="handleUploadAll" @handleUploadItem="handleUploadItem"
      >
      </base-file-upload>
    </el-dialog>
  </div>
</template>

<script>
  import {BASE_URL} from '@/request/main'
  import BaseFileUpload from '@/components/BaseFileUpload'
  import {getFile} from '@/request/house/houseUsed'

  export default {
    props: {
      video: {
        type: Object,
        required: true
      },

      defaultParams: {
        type: Object,
        required: true
      }
    },

    components: {BaseFileUpload},

    data () {
      return {
        dialogVisible: false,
        uploadData: Object.assign({}, this.defaultParams),
        uploadUrl: BASE_URL + 'house/uploadFile',
        hackReset: true
      }
    },

    methods: {
      openFile () {
        this.dialogVisible = true
      },

      handleUploadAll () {
        this.$refs['fileUpload'].uploadAll()
      },

      handleUploadItem (file) {
        this.$refs['fileUpload'].uploadItem(file)
      },

      /**
       * 上传成功的钩子
       * @param path 图片途径
       * @param uploaded 是否全部上传成功
       */
      fileSuccess (file, uploaded) {
        if (uploaded) {

          this.$message({
            type: 'success',
            message: '上传成功！待审核！',
            showClose: true
          })

          this.$refs['fileUpload'].clearFiles()

          this.dialogVisible = false
        }
      }
    },

    mounted () {
      console.log(this.video)
    }
  }
</script>

<style scoped lang="scss">
  .video-wrap {
    min-height: 500px;
    text-align: center;

    video {
      margin: 20px auto;
    }
  }
</style>
