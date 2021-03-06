import BaseFileUpload from '@/components/BaseFileUpload'
import {BASE_URL} from '@/request/main'
import {getSelectValue} from '@/request/app'
import * as RequestURL from '@/request/deal/businessTransactionDeal'
import DealBaseWriteLogMixins from '@/views/deal/bussinessDeal/component/DealBaseWriteLogMixins'

export default {
  components: {BaseFileUpload},
  mixins:[DealBaseWriteLogMixins],
  props: {
    defaultParams: {
      type: Object,
      required: true
    },
    dealType: {
      type: String,
      required: true
    }
  },
  data () {
    return {
      dialogVisible: false,
      dialogVisibleUpdateType: false,
      dialogVisibleViewPhoto: false,
      uploadData: Object.assign({}, this.defaultParams, {
        attachType: '',
        pictureName: ''
      }),
      selectOpts: [{key:'00',value:'合同扫描件'},{key:'01',value:'业主材料扫描件'},{key:'02',value:'客户材料扫描件'}],
      uploadUrl: BASE_URL + 'deal/deal/uploadImgs',
      fileList: [],
      checkedAll: true,
      loadingSubmitBtn: false,
    }
  },

  methods: {
    openFile () {
      this.dialogVisible = true
    },

    // 单张下载
    downloadImgItem () {
      if (this.checkedList.length > 1) {
        this.$message({
          type: 'warning',
          message: '不允许多张下载！',
          showClose: true
        })
        return false
      }

      let imgItem = this.checkedList[0]
      let pictureName = ''
      if(imgItem.attachType === '00'){
        pictureName = "合同扫描件"
      }
      else if(imgItem.attachType === '01'){
        pictureName = "业主材料扫描件"
      }
      else if(imgItem.attachType === '02'){
        pictureName = "客户材料扫描件"
      }

      let name = `${this.dealId}_${pictureName}_${this.$utils.timeFormat(imgItem.updateTime)}`

      let image = new Image()
      // 解决跨域 Canvas 污染问题
      image.setAttribute('crossOrigin', 'anonymous')
      image.src = imgItem.path

      image.onload = function () {
        let canvas = document.createElement('canvas')
        canvas.width = image.width
        canvas.height = image.height

        let context = canvas.getContext('2d')
        context.drawImage(image, 0, 0, image.width, image.height)
        let url = canvas.toDataURL("image/png")

        // 生成一个a元素
        let a = document.createElement('a')
        // 创建一个单击事件
        let event = new MouseEvent('click')

        // 将a的download属性设置为想要下载的图片名称
        a.download = name
        // 将生成的URL设置为a.href属性
        a.href = url
        // 触发a的单击事件
        a.dispatchEvent(event)
      }
      if(this.dealType === '00'){
        this.dealQueryLog(this.defaultParams.dealId,this.defaultParams.dealId,'6','422','单张图片下载：' + imgItem.path)
      }else if(this.dealType === '01'){
        this.dealQueryLog(this.defaultParams.dealId,this.defaultParams.dealId,'7','422','单张图片下载：' + imgItem.path)
      }else if(this.dealType === '02'){
        this.dealQueryLog(this.defaultParams.dealId,this.defaultParams.dealId,'8','422','单张图片下载：' + imgItem.path)
      }
    },

    // 批量下载
    downloadImages () {
      let imgIds = []
      let logContent = '批量图片下载：'
      this.checkedList.forEach(item => {
        imgIds.push(item.path)
        if(item.attachType === '00'){
          logContent = logContent + `合同扫描件-${item.path};`
        }else if(item.attachType === '01'){
          logContent = logContent + `业主材料扫描件-${item.path};`
        }else if(item.attachType === '02'){
          logContent = logContent + `客户材料扫描件-${item.path};`
        }
      })
      // 生成一个a元素
      let a = document.createElement('a')
      // 创建一个单击事件
      let event = new MouseEvent('click')

      // 将a的download属性设置为想要下载的图片名称
      a.download = this.dealId
      // 将生成的URL设置为a.href属性
      a.href = `${RequestURL.downloadImagesUrl}?dealId=${this.dealId}&jsonData=${imgIds.join()}`
      // 触发a的单击事件
      a.dispatchEvent(event)
      this.$message({
        type: 'success',
        message: '批量下载成功',
        showClose: true
      })

      if(this.dealType === '00'){
        this.dealQueryLog(this.defaultParams.dealId,this.defaultParams.dealId,'6','423',logContent)
      }else if(this.dealType === '01'){
        this.dealQueryLog(this.defaultParams.dealId,this.defaultParams.dealId,'7','423',logContent)
      }else if(this.dealType === '02'){
        this.dealQueryLog(this.defaultParams.dealId,this.defaultParams.dealId,'8','423',logContent)
      }
    },

    // 删除图片
    delImages () {
      this.$confirm('确定删除选中的图片吗？', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let imgIds = []
        let logContent = '删除图片：'
        this.checkedList.forEach(item => {
          imgIds.push({id:item.id})
          if(item.attachType === '00'){
            logContent = logContent +`合同扫描件-${item.path};`
          }else if(item.attachType === '01'){
            logContent = logContent +`业主材料扫描件-${item.path};`
          }else if(item.attachType === '02'){
            logContent = logContent +`客户材料扫描件-${item.path};`
          }
        })
        RequestURL.deleteAttach({jsonData: JSON.stringify(imgIds)}).then(res => {
          this.$message({
            type: 'success',
            message: res.msg || '操作成功',
            showClose: true
          })
          this._getTableData()

          if(this.dealType === '00'){
            this.dealDelLog(this.defaultParams.dealId,this.defaultParams.dealId,'6','408',logContent)
          }else if(this.dealType === '01'){
            this.dealDelLog(this.defaultParams.dealId,this.defaultParams.dealId,'7','408',logContent)
          }else if(this.dealType === '02'){
            this.dealDelLog(this.defaultParams.dealId,this.defaultParams.dealId,'8','408',logContent)
          }
        })
      })
    },

    // 全选
    choiceAll () {
      this.fileList.forEach(item => {
        item.checked = this.checkedAll
      })
      this.checkedAll = !this.checkedAll
    },

    handleUploadAll (files) {
      let valid = true
      files.forEach(file => {
        if (!file.data.attachType) {
          valid = false
        }
        else {
          this.selectOpts.forEach(item => {
            if (file.data.attachType === item.key) {
              file.data.attachName = item.value
            }
          })
        }
      })

      if (!valid) {
        this.$message({
          type: 'warning',
          message: '图片类型不能为空!',
          showClose: true
        })
        return false
      } else {
        this.$refs['fileUpload'].uploadAll()
      }
    },

    handleUploadItem (file) {
      let valid = true

      if (!file.data.attachType) {
        valid = false
      } else {
        this.selectOpts.forEach(item => {
          if (file.data.attachType === item.key) {
            file.data.attachName = item.value
          }
        })
      }

      if (!valid) {
        this.$message({
          type: 'warning',
          message: '图片类型不能为空!',
          showClose: true
        })
        return false
      } else {
        this.$refs['fileUpload'].uploadItem(file)
      }
    },

    /**
     * 图片上传成功的钩子
     * @param path 图片途径
     * @param uploaded 是否全部上传成功
     */
    fileSuccess (file, uploaded) {
      if (uploaded) {
        this.$refs['fileUpload'].clearFiles()
        this.dialogVisible = false
        this.$message({
          type:'success',
          message:'操作成功'
        })
      }
      this._getTableData()
    },

    // 获取图片列表
    _getTableData () {
      let params = {
        dealId: this.dealId,
        attachType: this.defaultParams.attachType
      }
      RequestURL.getAttachData(params).then(res => {
        this.fileList = res.data.map(item => {
          item.checked = false
          return item
        }) || []
      })
    }
  },

  mounted () {
    this.dealId = this.defaultParams.dealId
    this._getTableData()
  },

  computed: {
    checkedList () {
      this.dealId = this.defaultParams.dealId
      let list = []
      this.fileList.forEach(item => {
        if (item.checked) {
          list.push(item)
        }
      })
      return list
    }
  },
}
