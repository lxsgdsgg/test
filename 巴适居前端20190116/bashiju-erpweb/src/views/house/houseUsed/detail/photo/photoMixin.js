import BaseFileUpload from '@/components/BaseFileUpload'
import {BASE_URL} from '@/request/main'
import {getFile, downloadImages, delFile, updateAttachment, downloadImagesUrl} from '@/request/house/houseUsed'
import * as consts from '../../consts'

export default {
  components: {BaseFileUpload},
  props: {
    defaultParams: {
      type: Object,
      required: true
    },
    houseInfoPermission: {
      type: Object,
      required: true
    },
    logMessage: {
      type: Object
    }
  },
  data () {
    return {
      PERMISSION_BTN: consts.PERMISSION_BTN,
      dialogVisible: false,
      dialogVisibleUpdateType: false,
      dialogVisibleViewPhoto: false,
      uploadData: Object.assign({}, this.defaultParams, {
        pictureType: '',
        pictureName: ''
      }),
      selectOpts: [],
      uploadUrl: BASE_URL + 'house/uploadFile',
      fileList: [],
      checkedAll: true,
      updateTypeForm: {
        pictureType: '',
        pictureName: '',
        id: '',
        orgPictureName: '',
        orgPictureType: '',
      },
      loadingSubmitBtn: false
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
      let name = `${this.defaultParams.houseId}_${imgItem.pictureName}_${this.$utils.timeFormat(imgItem.applyTime)}`

      let image = new Image()
      // 解决跨域 Canvas 污染问题
      image.setAttribute('crossOrigin', 'anonymous')
      image.src = imgItem.path

      let _self = this
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
        // // 向后台传递日志数据
        // let message = Object.assign({}, _self.logMessage, {
        //   operatTypeId: _self.$DICT_CODE.LOG.BUSINESS_OPERATE_TYPE.DEAL_DOWNLOAD_SINGLE_PICTURE, // 操作类型
        //   logContent: `下载图片`, // 日志内容
        //   path: imgItem.path
        // })
        // _self.$updateLog.house.houseQueryLog({message: JSON.stringify(message)})
      }

    },

    // 批量下载
    downloadImages () {
      let imgIds = []
      this.checkedList.forEach(item => {
        imgIds.push(item.id)
      })

      // 生成一个a元素
      let a = document.createElement('a')
      // 创建一个单击事件
      let event = new MouseEvent('click')

      // 将a的download属性设置为想要下载的图片名称
      a.download = this.defaultParams.houseId
      // 将生成的URL设置为a.href属性
      a.href = `${downloadImagesUrl}?houseId=${this.defaultParams.shhId}&imageIds=${imgIds.join()}`
      // 触发a的单击事件
      a.dispatchEvent(event)
    },

    // 删除图片
    delImages () {
      this.$confirm('确定删除选中的图片吗？', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let imgIds = []
        let imgNames = []
        this.checkedList.forEach(item => {
          imgIds.push(item.id)
          imgNames.push(item.pictureName)
        })

        let params = {
          imgIds: imgIds.join(),
          shhId: this.defaultParams.shhId
        }
        delFile(params).then(res => {
          this.$message({
            type: 'success',
            message: res.msg || '操作成功',
            showClose: true
          })
          this._getFileList()

          // 向后台传递日志数据
          let message = Object.assign({}, this.logMessage, {
            operatTypeId: this.$DICT_CODE.LOG.BUSINESS_OPERATE_TYPE.DEL_PICTURE, // 操作类型
            logContent: `删除图片->${imgNames.join()}` // 日志内容
          })
          this.$updateLog.house.houseQueryLog({message: JSON.stringify(message)})

        })
      })
    },

    // 修改图片类型
    updateType () {
      if (this.checkedList.length > 1) {
        this.$message({
          type: 'warning',
          message: '请选择单张修改',
          showClose: true
        })
        return false
      }
      this.updateTypeForm.orgPictureName = this.checkedList[0].pictureName
      this.updateTypeForm.orgPictureType = this.checkedList[0].pictureType
      this.updateTypeForm.id = this.checkedList[0].id
      this.dialogVisibleUpdateType = true
    },

    // 修改图片提交
    submitUpdateTypeForm () {
      let form = this.updateTypeForm
      if (form.pictureType === String(form.orgPictureType)) {
        this.$message({
          type: 'warning',
          message: '你没有做任何修改！',
          showClose: true
        })
        return false
      }

      this.$confirm('确定保存修改的信息？', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        this.loadingSubmitBtn = true
        let pictureName = ''

        this.selectOpts.forEach(item => {
          if (form.pictureType === item.fieldCode) {
            pictureName = item.fieldValue
          }
        })

        let params = {
          shhId: this.defaultParams.shhId,
          imgInfos: {
            id: form.id,
            pictureType: form.pictureType,
            pictureName: pictureName
          }
        }

        let cfg = {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8;'
          }
        }

        updateAttachment(params, cfg).then(res => {
          this.loadingSubmitBtn = false
          this.$message({
            type: 'success',
            message: res.msg || '操作成功',
            showClose: true
          })
          this._getFileList()
          this.dialogVisibleUpdateType = false

          // 向后台传递日志数据
          let message = Object.assign({}, this.logMessage, {
            operatTypeId: this.$DICT_CODE.LOG.BUSINESS_OPERATE_TYPE.HOUSE_UPDATE_PIC_TYPE, // 操作类型
            logContent: `由${this.updateTypeForm.orgPictureName}修改为${pictureName}` // 日志内容
          })
          this.$updateLog.house.houseQueryLog({message: JSON.stringify(message)})

        }).catch(() => {
          this.loadingSubmitBtn = false
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
        if (!file.data.pictureType) {
          valid = false
        } else {
          this.selectOpts.forEach(item => {
            if (file.data.pictureType === item.fieldCode) {
              file.data.pictureName = item.fieldValue
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

      if (!file.data.pictureType) {
        valid = false
      } else {
        this.selectOpts.forEach(item => {
          if (file.data.pictureType === item.fieldCode) {
            file.data.pictureName = item.fieldValue
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

        this.$message({
          type: 'success',
          message: '上传成功，待审核！',
          showClose: true
        })

        this.$refs['fileUpload'].clearFiles()

        this.dialogVisible = false
      }
    },

    // 获取图片列表
    _getFileList () {
      let params = {
        shhId: this.defaultParams.shhId,
        attachType: this.defaultParams.attachType
      }
      getFile(params).then(res => {
        this.fileList = res.data.map(item => {
          item.checked = false
          return item
        }) || []
      })
    }
  },

  mounted () {
    this._getSelectOpts()
    this._getFileList()
  },

  computed: {
    checkedList () {
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
