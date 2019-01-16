<!-- 房屋照片 -->

<template>
  <div>
    <div class="action-bar clearfix">
      <div class="pull-left">
        <el-button v-hasMultipleBtn="[PERMISSION_BTN.UPLOAD_IMG, houseInfoPermission]" icon="el-icon-upload" size="mini" @click="openFile">上传图片</el-button>
        <el-button v-hasMultipleBtn="[PERMISSION_BTN.COVER_BTN, houseInfoPermission]" @click="setCover" :type="checkedList.length ? '' : 'info'" :disabled="!checkedList.length" size="mini">设置封面</el-button>
      </div>
      <div class="pull-right">
        <el-button @click="choiceAll" size="mini">全选</el-button>
        <el-button v-hasMultipleBtn="[PERMISSION_BTN.DOWNLOAD_IMG, houseInfoPermission]" @click="downloadImgItem" :type="checkedList.length ? '' : 'info'" :disabled="!checkedList.length" size="mini">单张下载</el-button>
        <el-button v-hasMultipleBtn="[PERMISSION_BTN.DOWNLOAD_IMG, houseInfoPermission]" @click="downloadImages" :type="checkedList.length ? '' : 'info'" :disabled="!checkedList.length" size="mini">批量下载</el-button>
        <el-button v-hasMultipleBtn="[PERMISSION_BTN.DEL_IMG, houseInfoPermission]" @click="delImages" :type="checkedList.length ? '' : 'info'" :disabled="!checkedList.length" size="mini">删除</el-button>
        <el-button v-hasMultipleBtn="[PERMISSION_BTN.UPDATE_PIC_TYPE, houseInfoPermission]" @click="updateType" :type="checkedList.length ? '' : 'info'" :disabled="!checkedList.length" size="mini">图片类型</el-button>
      </div>
    </div>

    <div class="photo-wrap">

      <el-row :gutter="20">
        <el-col v-for="(item, index) in fileList" :key="index" :span="4.8">
          <el-card class="photo-item" :body-style="{ padding: '0px' }">
            <icon v-if="item.isCover" class="cover" name="cover_icon" width="60" hegiht="60" scale="6.5"></icon>

            <div class="img-box">
              <img @click="dialogVisibleViewPhoto = true" class="pos-center" :src="item.path">
            </div>

            <div @click="item.checked = !item.checked" class="bottom">
              <div class="clearfix">
                <span class="pull-left">{{item.pictureName}}</span>
                <span class="pull-right">{{item.applyer}}</span>
              </div>
              <div class="bottom-content clearfix">
                <time class="time">{{$utils.timeFormat(item.applyTime, '{y}-{m}-{d} {h}:{i}:{s}')}}</time>
                <el-checkbox v-model="item.checked" size="mini" class="button"></el-checkbox>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>


    </div>

    <el-dialog
      title="上传房源图片"
      :visible.sync="dialogVisible"
      width="1100px"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      append-to-body
    >
      <base-file-upload @on-success="fileSuccess" :data="uploadData" :postAction="uploadUrl" ref="fileUpload" @handleUploadAll="handleUploadAll" @handleUploadItem="handleUploadItem">

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
                <el-option v-for="item in selectOpts" :label="item.fieldValue" :value="item.fieldCode" :key="item.id"></el-option>
              </el-select>
            </el-form-item>
          </el-form>

        </template>

      </base-file-upload>
    </el-dialog>

    <el-dialog
      title="修改相关图片类型"
      :visible.sync="dialogVisibleUpdateType"
      width="400px"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
    >
      <el-form size="small" :model="updateTypeForm" ref="updateTypeForm" label-width="100px">

        <el-form-item
          label="原图片类型"
        >
          <el-input readonly :value="updateTypeForm.orgPictureName"></el-input>
        </el-form-item>

        <el-form-item
          label="现图片类型"
          prop="pictureType"
          :rules="[
            { required: true, message: '请选择图片类型', trigger: 'change' }
          ]"
        >
          <el-select style="width: 100%;" clearable v-model="updateTypeForm.pictureType" placeholder="图片类型">
            <el-option v-for="item in selectOpts" :label="item.fieldValue" :value="item.fieldCode" :key="item.id"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item class="margin-b-none">
          <el-button type="primary" :loading="loadingSubmitBtn" @click="submitUpdateTypeForm">确定</el-button>
          <el-button @click="dialogVisibleUpdateType = false">取消</el-button>
        </el-form-item>

      </el-form>
    </el-dialog>

    <el-dialog
      title="房源图片"
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
  import photoMixin from './photoMixin'
  import {updateAttachment} from '@/request/house/houseUsed'
  import {getSelectValue} from '@/request/app'

  export default {
    mixins: [photoMixin],
    methods: {
      // 设置封面
      setCover () {
        if (this.checkedList.length > 1) {
          this.$message({
            type: 'warning',
            message: '请选择单张设置封面',
            showClose: true
          })
          return false
        }

        let checkedFile = this.checkedList[0]

        if (checkedFile.isCover === 1) {
          this.$message({
            type: 'info',
            message: '该图片已经是封面了',
            showClose: true
          })
          return false
        }

        this.$confirm('确定设置该张图片为封面吗？', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let params = {
            shhId: this.defaultParams.shhId,
            imgInfos: {
              id: checkedFile.id,
              isCover: 1
            }
          }

          let cfg = {
            headers: {
              'Content-Type': 'application/json;charset=UTF-8;'
            }
          }

          updateAttachment(params, cfg).then(res => {
            this.$message({
              type: 'success',
              message: res.msg || '操作成功',
              showClose: true
            })
            this._getFileList()

            // 向后台传递日志数据
            let message = Object.assign({}, this.logMessage, {
              operatTypeId: this.$DICT_CODE.LOG.BUSINESS_OPERATE_TYPE.HOUSE_SET_COVER, // 操作类型
              logContent: `设置房屋照片封面`, // 日志内容
              path: checkedFile.path
            })
            this.$updateLog.house.houseQueryLog({message: JSON.stringify(message)})

          }).catch(() => {
            this.loadingSubmitBtn = false
          })
        })
      },

      _getSelectOpts () {
        getSelectValue({param: 'housePictureType'}).then(res => {
          this.selectOpts = res.data
        })
      }
    }
  }
</script>

<style scoped lang="scss">
  @import "photoComp";
</style>
