import BaseFileUpload from '@/components/BaseFileUpload'
import {BASE_URL} from '@/request/main'
import * as RequestURL from '@/request/manage/community'
import { querySelectItems } from '@/request/deal/houseDeal'
import {systemQueryLog,systemDelLog} from '@/request/log/systemPlatformLog'

export default {
  components: {BaseFileUpload},
  props: {
    defaultParams: {
      type: Object,
      required: true
    }
  },
  data () {
    return {
      dialogVisible: false,
      dialogVisibleUpdateType: false,
      dialogVisibleViewPhoto: false,
      uploadData: Object.assign({}, this.defaultParams, {
        pictureType: '',
        pictureTypeName: ''
      }),
      uploadUrl: BASE_URL + 'manage/community/uploadImage',
      fileList: [],
      checkedAll: true,
      loadingSubmitBtn: false,
      communityId: '',
      picTypeSele: [] // 图片类型下拉值
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
      let pictureName = imgItem.pictureTypeName
      let name = `${this.communityId}_${pictureName}_${this.$utils.timeFormat(imgItem.updateTime)}`

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
      let message = {sourceCode:imgItem.pictureTypeName,sourceTypeId:'4',operatTypeId:'1'
        ,logContent: `单张图片下载:${imgItem.path}`}
      systemQueryLog({message: JSON.stringify(message)})
    },

    // 批量下载
    downloadImages () {
      let imgIds = []
      let path = ''
      imgIds.push({dealId:this.communityId})//这里为了使用成交的批量下载，参数名称暂定dealId
      this.checkedList.forEach(item => {
        imgIds.push({id:item.id,path: item.path})
        path = path + item.path + ';'
      })

      // 生成一个a元素
      let a = document.createElement('a')
      // 创建一个单击事件
      let event = new MouseEvent('click')

      // 将a的download属性设置为想要下载的图片名称
      a.download = this.communityId
      // 将生成的URL设置为a.href属性
      a.href = `${RequestURL.downloadImagesUrl}?jsonData=${JSON.stringify(imgIds)}`
      // 触发a的单击事件
      a.dispatchEvent(event)
      this._getTableData()
      let message = {sourceCode:this.defaultParams.communityName,sourceTypeId:'4',operatTypeId:'1'
        ,logContent: `批量图片下载:${path}`}
      systemQueryLog({message: JSON.stringify(message)})
    },

    // 删除图片
    delImages () {
      this.$confirm('确定删除选中的图片吗？', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let imgIds = ''
        this.checkedList.forEach(item => {
          if(imgIds.length > 0){
            imgIds  = imgIds + ','
          }
          imgIds = imgIds + (item.id)
        })
        RequestURL.deleteCommunityImage({ids: imgIds}).then(res => {
          this.$message({
            type: 'success',
            message: res.msg || '操作成功',
            showClose: true
          })
          this._getTableData()
          let message = {sourceCode:this.defaultParams.communityName,sourceTypeId:'4',operatTypeId:'3'
            ,logContent: '删除图片，图片ID：'+ imgIds}
          systemDelLog({message: JSON.stringify(message)})
        })
      })
    },

    // 设置封面
    setCoverPic(){
      if(this.checkedList.length > 1){
        this.$message({
          type: 'warning',
          message: '只能设置一个封面'
        })
        return
      }
      this.$confirm('确定提交数据吗？', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let imgId = this.checkedList[0].id
        let imgInfos = []
        imgInfos.unshift({id: imgId,isCover: 1})
        let params = {communityId: this.communityId, imgInfos: imgInfos }
        let cfg = {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8;'
          }
        }
        RequestURL.setCommunityCoverImage(params,cfg).then(res => {
          if(res.success){
            this.$message({
              type: 'success',
              message: res.msg || '操作成功',
              showClose: true
            })
            this._getTableData()
          }else{
            this.$message({
              type: 'error',
              message: res.msg
            })
          }
          let message = {sourceCode:this.communityId,sourceTypeId:'4',operatTypeId:'5'
            ,logContent: '设置小区封面，小区ID：'+ imgId}
          systemDelLog({message: JSON.stringify(message)})
        }).catch(err=>{
          this.$message({
            type: 'error',
            message: err.data.msg || '操作失败',
            showClose: true
          })
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
        }else{
          this.picTypeSele.forEach(item => {
            if (file.data.pictureType === item.id) {
              file.data.pictureTypeName = item.name
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
        this.picTypeSele.forEach(item => {
          if (file.data.pictureType === item.id) {
            file.data.pictureTypeName = item.name
            file.data.pictureType = item.id
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
        communityId: this.communityId,
        attachType: '1'// 1: 图片 2 视频
      }

      RequestURL.queryCommunityImageList(params).then(res => {
        this.fileList = res.data.map(item => {
          item.checked = false
          return item
        }) || []
      })
    },

    // 获取图片类型下拉值
    queryCommunityPicTypeItems(){
      querySelectItems({'param':'commPicType'}).then((res)=>{
        if (res.success){
          for(let i=0;i<res.data.length;i++){
            let data = {'name':res.data[i].fieldValue,'id': String(res.data[i].id)}
            this.picTypeSele.push(data)
          }
        }
      })
    }
  },

  mounted () {
    this.communityId = this.defaultParams.communityId
    this.queryCommunityPicTypeItems()
    this._getTableData()
  },

  watch: {
    '$route.query.communityId'(){
      if (this.$route.query.communityId) {
        this.communityId = this.$route.query.communityId
        this._getTableData()
      }
    }
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
