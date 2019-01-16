<template>
    <div style="text-align: center">

      <el-upload
        style="margin: 0 auto"
        v-show="show"
        :action="action"
        list-type="picture-card"
        :multiple="false"
        :on-remove="handleRemove"
        :auto-upload="false"
        :on-preview="handlePictureCardPreview"
        :on-change="onchange"
        :data="{userId: id}"
        ref="upload"
        :on-success="onSuccess"
        :on-error="onError"
      >
        <i class="el-icon-plus"></i>
      </el-upload>

      <div class="btn-group">
        <el-button :loading="loadingSubmitBtn" @click="submitUpload" type="primary">上传<i class="el-icon-upload el-icon--right"></i></el-button>
        <el-button @click="$emit('onClose')">取消</el-button>
      </div>

      <el-dialog top="10vh" :visible.sync="dialogVisible" append-to-body>
        <img width="100%" :src="dialogImageUrl">
      </el-dialog>

    </div>
</template>

<script>
  import {avatarUploadUrl} from '@/request/manage/user'

  export default {
    props: {
      id: {
        type: [Number, String]
      }
    },
    data() {
      return {
        dialogImageUrl: '',
        dialogVisible: false,
        action: avatarUploadUrl,
        show: true,
        loadingSubmitBtn: false
      };
    },
    methods: {
      handleRemove(file, fileList) {
        if (!fileList.length) {
          let el = document.querySelectorAll('.el-upload')[0]
          el.style.display = 'block'
          el.style.margin = '0 auto'
        }
      },
      onchange() {
        let el = document.querySelectorAll('.el-upload')[0]
        el.style.display = 'none'
      },
      onSuccess (response) {
        if (response.success) {
          this.loadingSubmitBtn = false
          this.$message({
            message: '上传成功',
            showClose: true,
            duration: 2000,
            type: 'success'
          })
          this.$emit('onSuccess')
        }
      },
      onError () {
        this.loadingSubmitBtn = false
      },
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url
        this.dialogVisible = true
      },
      submitUpload() {
        this.loadingSubmitBtn = true
        this.$refs.upload.submit()
      }
    },
    watch: {
      id () {
        this.$refs.upload && this.$refs.upload.clearFiles()
      }
    },
  }
</script>

<style scoped lang="scss">

</style>
