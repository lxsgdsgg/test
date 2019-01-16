<template>
  <div class="file-upload">
    <template v-if="!_cfg.isSimple">
      <el-upload
        ref="upload"
        :limit="_cfg.listCfg.limit"
        :headers="_cfg.headers"
        :multiple="_cfg.listCfg.multiple"
        :action="_cfg.url"
        :show-file-list="_cfg.listCfg.showFileList"
        :list-type="_cfg.listCfg.listType"
        :file-list="fileList"
        :on-success="handleSuccess"
        :on-preview="handlePreview"
        :on-exceed="handleExceed"
        :auto-upload="false"
        :on-remove="onRemove"
        :before-upload="beforeAppImgUpload"
        :before-remove="beforeRemove"
        :on-change="changeUpload"
      >
        <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
        <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
      </el-upload>
    </template>
  </div>
</template>
    <!--<el-col :span="7" align="right">-->
      <!--&lt;!&ndash;<el-select title="图片类型"></el-select>&ndash;&gt;-->
    <!--</el-col>-->

<script>
export default {
  name: 'fileUpload',
  props: {
    uploadCfg: Object,
    //小区数据
    communityData: Object
  },
  data () {
    return {
      imgUrl: '',
      dialogFileUpload: false,
      dialogImageUrl: '',
      submitData: '',
      defaultCfg: {
        url: 'https://jsonplaceholder.typicode.com/posts/',
        isSimple: false, // 单张上传  适用于 头像，logo等直接替换
        headers: {}, // 设置上传的请求头
        listCfg: { // 列表形式上传配置
          multiple: true, // 是否支持多选文件
          limit: null, // 最多上传个数
          listType: 'picture', // 文件列表类型
          showFileList: true
        }
      },
      fileList:[]
    }
  },
  methods: {
    submitUpload() {
      console.log(this.fileList)
      // this.$refs.upload.submit();
    },
    // 文件上传之前的钩子，参数为上传的文件
    beforeAppImgUpload (file) {
      console.log(file)
      const isLt2M = file.size / 1024 / 1024 < 10
      if (!isLt2M) {
        this.$message.error('上传App图片大小不能超过10MB!')
      }
      return isLt2M
    },

    // 文件上传成功时的钩子
    handleSuccess (res, file) {
      this.imgUrl = URL.createObjectURL(file.raw)
      this.$emit('uploadSuccess', file)
    },

    // 点击已上传的图片预览时的钩子
    handlePreview (file) {
      this.dialogImageUrl = file.url
      this.dialogFileUpload = true
    },

    // 文件超出个数限制时的钩子
    handleExceed (files, fileList) {
      this.$message({
        message: `最多只能上传${this._cfg.listCfg.limit}张`,
        type: 'warning'
      })
    },

    // 移除之前
    beforeRemove(file, fileList){
      return this.$confirm(`确定移除 ${ file.name }？`)
    },

    // 移除数据
    onRemove(file,fileList){
      this.fileList = fileList
      console.log(this.fileList)
    },

    // 列表改变
    changeUpload: function(file, fileList) {//预览图片
      this.fileList = fileList;
      // let upload_list_li = document.getElementsByClassName('el-upload-list')[0].children;
      // for (let i = 0; i < upload_list_li.length; i++) {
      //   let li_a = upload_list_li[i].children[0];
      //   let imgElement = document.createElement("img");
      //   imgElement.setAttribute('src', fileList[i].url);
      //   imgElement.setAttribute('style', "max-width:50%;padding-left:25%");
      //   if (li_a.lastElementChild.nodeName !== 'IMG') {
      //     li_a.appendChild(imgElement);
      //   }
      // }
    }
  },
  mounted(){
    this.fileList = null
  },

  computed: {
    _cfg () {
      // 合并覆盖默认配置
      let params = Object.assign({}, this.defaultCfg, this.uploadCfg)
      return params
    }
  }
}
</script>
<style lang="scss" scoped>
  .file-upload {
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    display: inline-block;
    &:hover {
      border-color: #409EFF;
    }
    .upload-icon {
      font-size: 28px;
      color: #8c939d;
      width: 150px;
      height: 150px;
      line-height: 150px;
      text-align: center;
    }
    .app-img {
      width: 150px;
      height: 150px;
      display: block;
    }
  }

  .simple-uploader {
    width: 150px;
    height: 150px;
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .simple-uploader:hover {
    border-color: #409EFF;
  }
  .simple-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 150px;
    height: 150px;
    line-height: 150px;
    text-align: center;
  }
  .simple-uploader .simple-img {
    width: 150px;
    height: 150px;
    display: block;
  }
</style>
