<template>
    <div>
      <ul class="menu">
        <li @click="fontStrong">
          <el-tooltip content="加粗" placement="bottom-start">
            <b>B</b>
          </el-tooltip>
        </li>
        <li @click="fontStyle">
          <el-tooltip content="斜体" placement="bottom-start">
            <i>I</i>
          </el-tooltip>
        </li>
        <li @click="fontUnderLine">
          <el-tooltip content="下划线" placement="bottom-start">
            <span style="text-decoration: underline;">U</span>
          </el-tooltip>
        </li>
        <li @click="lineThrough">
          <el-tooltip content="删除" placement="bottom-start">
            <span style="text-decoration: line-through;">ABC</span>
          </el-tooltip>
        </li>
        <li>
          <el-tooltip content="字体颜色" placement="bottom-start">
            <el-color-picker v-model="color5" @change="setFontColor"  show-alpha size="small" :predefine="predefineColors">
            </el-color-picker>
          </el-tooltip>
        </li>
        <li>
          <el-tooltip content="字体" placement="bottom-start">
            <el-select v-model="fontValue" @change="setFontFamily" size="mini" style="width: 150px;">
              <el-option
                v-for="item in fontFamily"
                :key="item"
                :label="item"
                :value="item">
              </el-option>
            </el-select>
          </el-tooltip>
        </li>
        <li>
          <el-tooltip content="字号" placement="bottom-start">
            <el-select v-model="sizeValue" @change="setFontSize" size="mini" style="width: 100px;">
              <el-option
                v-for="item in fontSize"
                :key="item"
                :label="item"
                :value="item">
              </el-option>
            </el-select>
          </el-tooltip>
        </li>
        <li>
          <el-tooltip content="设置背景图片" placement="bottom-start">
            <i class="el-icon-picture" @click="showUploadDialog"></i>
          </el-tooltip>
        </li>
        <li>
          <el-tooltip content="取消背景图片" placement="bottom-start">
            <i class="el-icon-delete" @click="clearBackGround"></i>
          </el-tooltip>
        </li>
      </ul>
      <el-dialog :visible.sync="dialogVisible">
        <el-upload
          :action="url"
          list-type="picture-card"
          :file-list="fileList"
          :on-preview="handlePictureCardPreview"
          :before-remove="handleRemove">
          <i class="el-icon-plus"></i>
        </el-upload>
      </el-dialog>
    </div>
</template>

<script>
  import {
    queryBackground,
    uploadBackgroundPic,
    deleteBackGroundPic
  } from '@/request/manage/HousePrintTemplate'
  import {BASE_URL} from '@/request/main'
export default{
  data () {
    return {
      url:BASE_URL+'house/uploadBackgroundPic',
      fileList: [],
      color5: 'rgba(255, 69, 0, 0.68)',
      predefineColors: [
        '#ff4500',
        '#ff8c00',
        '#ffd700',
        '#90ee90',
        '#00ced1',
        '#1e90ff',
        '#c71585',
        'rgba(255, 69, 0, 0.68)',
        'rgb(255, 120, 0)',
        'hsv(51, 100, 98)',
        'hsva(120, 40, 94, 0.5)',
        'hsl(181, 100%, 37%)',
        'hsla(209, 100%, 56%, 0.73)',
        '#c7158577'
      ],
      fontValue: 'sans-serif',
      fontFamily: ['宋体', '微软雅黑', '楷体', '黑体', '隶书', 'Arial', 'Arial Black', 'Comic Sans MS', 'Impact', 'Times New Roman', 'sans-serif', 'Andale Mono'],
      sizeValue: 16,
      fontSize: [10, 12, 14, 16, 18, 20, 22, 24, 32, 48, 64, 92, 120],
      fontstrong: true,
      fontstyle: true,
      underlint: true,
      linethrough: true,
      dialogVisible: false
    }
  },
  methods: {
    showUploadDialog () {
      this.dialogVisible = true
      this.$nextTick(() => {
        queryBackground({}).then((res) => {
          this.fileList = res.data
        }).catch(err => {
          console.log(err)
        })
      })
    },
    handleRemove(file, fileList) {
      this.$confirm('确定删除该背景图片吗？', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const param = {id: file.name}
        deleteBackGroundPic(param).then(res => {
          if (res.success) {
            for (let i = 0; i< this.fileList.length; i++) {
              if (this.fileList[i].name === file.name) {
                this.fileList.splice(i,1)
                return true
              }
            }
          } else {
            this.$message({
              type: 'warning',
              message: res.msg
            })
          }
        }).catch(() => {})
      }).catch(() => {

      })
      return false
    },
    handlePictureCardPreview(file) {
      this.dialogVisible = false
      this.$emit('setBackGround', file.url)
    },
    fontStrong () {
      this.$emit('fontStrong', this.fontstrong)
      this.fontstrong = !this.fontstrong
    },
    fontStyle () {
      this.$emit('fontStyle', this.fontstyle)
      this.fontstyle = !this.fontstyle
    },
    fontUnderLine () {
      this.$emit('fontUnderLine', this.underlint)
      this.underlint = !this.underlint
    },
    lineThrough () {
      this.$emit('lineThrough', this.linethrough)
      this.linethrough = !this.linethrough
    },
    setFontSize (item) {
      console.log(item)
      this.$emit('fontSize', item)
    },
    setFontFamily (item) {
      this.$emit('setFontFamily', item)
    },
    setFontColor (value) {
      this.$emit('setFontColor', value)
    },
    clearBackGround () {
      this.$emit('clearBackGround')
    }
  },
  props: ['count']
}
</script>

<style lang="scss" scoped>
  .menu{
    margin: 0;
    padding: 0;
    list-style: none;
    border: solid 1px #999999;
    background-color: lightgrey;
    text-align: left;
  }
  .menu li{
    min-width: 20px;
    display: inline-block;
    margin-top: -10px;
    padding-left: 10px;
    padding-bottom: 5px;
    cursor: pointer;
  }
  .el-color-picker{
    top: 10px;
  }
</style>
