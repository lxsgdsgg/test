<template>
  <div class="wrapper">
    <div ref="editor" class="editor" :style="{minHeight: height}"></div>
    </div>
</template>

<script>
  import E from 'wangeditor'
  import { EDITORIMG_UPLOAD_URL } from '@/request/main' // 上传图片
  export default {
    name: 'editor',
    props: {
      height: {
        type: String,
        default: '320px'
      },
      content: {}
    },
    data () {
      return {
        editorContent: '',
        editor: null,
        imgPath: EDITORIMG_UPLOAD_URL
      }
    },
    methods: {
      getContent () {
        return this.editorContent
      },

      setContent () {
        this.editor.txt.html(this.content)
      },

      clear () {
        this.editor.txt.clear()
      }

    },
    mounted() {
      this.editor = new E(this.$refs.editor)
      this.editor.customConfig.onchange = (html) => {
        this.editorContent = html
      }

      // 下面两个配置，使用其中一个即可显示“上传图片”的tab。但是两者不要同时使用！！！
      //this.editor.customConfig.uploadImgShowBase64 = true   // 使用 base64 保存图片
       this.editor.customConfig.uploadImgServer = this.imgPath  // 上传图片到服务器

      this.editor.create()
      this.editor.customConfig.uploadImgHooks = {

        success: function (xhr, editor, result) {
          console.log(result)
        // 图片上传并返回结果，图片插入成功之后触发
        // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果

        },
        fail: function (xhr, editor, result) {
            // 图片上传并返回结果，但图片插入错误时触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
        },

      }

    },
    watch: {
      content(val) {
        this.setContent()
        if (!val) {
          this.editor.txt.clear()
        }
      }
    },
  }
</script>

<style scoped lang="scss">
  .wrapper {
    overflow-x: auto;
    margin-bottom: 15px;
  }
  .editor {
    text-align: left;
    width: 100%;
    min-width: 700px;
  }
</style>
