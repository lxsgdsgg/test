<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form">
        <el-form size="small" ref="queryForm" :inline="true" :model="queryForm" class="demo-form-inline">

          <el-form-item label="审核状态" prop="examineStatus">
            <el-select v-model="queryForm.examineStatus" placeholder="请选择" >
              <el-option
                v-for="item in selectStatus"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="标题" prop="title">
            <el-input v-model="queryForm.title" placeholder="请输入标题"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click.native.prevent="handleQuery" :loading="loadingQueryBtn">查询</el-button>
            <el-button @click.native.prevent="_resetForm('queryForm')">重置</el-button>
          </el-form-item>
          <el-form-item class="pull-right">
            <el-button  type="primary" @click="handleAdd">添加快讯</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <div class="page-content-bd" v-loading="loadingView">
      <el-table
        :data="tableData"
        border
        align="center"
        style="width: 100%"
        height="580"
      >

        <el-table-column
          prop="title"
          align="center"
          label="标题">
        </el-table-column>

        <el-table-column
          prop="deptName"
          align="center"
          label="发布部门">
        </el-table-column>

        <el-table-column
          prop="operator"
          align="center"
          label="发布人">
        </el-table-column>

        <el-table-column
          prop="examineStatusName"
          align="center"
          label="审核状态">
        </el-table-column>

        <el-table-column
          prop="auditor"
          align="center"
          label="审核人">
        </el-table-column>

        <el-table-column
          prop="rejectReason"
          align="center"
          label="驳回原因">
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
        >
          <template slot-scope="scope">
            <el-button   @click="handleShow(scope.row)" type="text" size="small">查看</el-button>
            <el-button v-if="scope.row.examineStatus==0" @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
            <el-button  @click="handleDelete(scope.row)" type="text" size="small">删除</el-button>
          </template>
        </el-table-column>

      </el-table>

      <b-pagination
        :listQuery="listQuery"
        @handleSizeChange="handleSizeChange"
        @handleCurrentChange="handleCurrentChange">
      </b-pagination>
    </div>



    <template>
      <b-dialog :show.sync="dialogAddVisible" :title="titleText" width="780px">
        <div style="height:500px;overflow-y:scroll;">

          <el-form v-loading="loadingForm" :model="addForm" status-icon :rules="addFormRules" ref="addForm" label-width="100px">

            <el-form-item label="快讯标题" prop="title">
              <el-input v-model="addForm.title"></el-input>
            </el-form-item>
            <el-form-item label="快讯摘要" prop="abstracts">
              <el-input v-model="addForm.abstracts"></el-input>
            </el-form-item>

            <el-form-item label="快讯封面" prop="cover">
              <img v-show="showImg" :src="fileUrl" class="">
              <el-upload
                class="avatar-uploader"
                :action="uploadPath"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload"
                :show-file-list="false">
                <img v-if="imageUrl" :src="imageUrl" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </el-form-item>
            <el-form-item label="" prop="cover" v-show="noShow">
              <el-input v-model="addForm.cover" ></el-input>
            </el-form-item>
            <base-editor :content="editorVal" ref="editor"></base-editor>
            <el-form-item class="margin-b-none">
              <el-button type="primary" :loading="loadingSubmitBtn" @click="handleSubmit">确认</el-button>
              <el-button @click="dialogAddVisible=false">取消</el-button>
            </el-form-item>

          </el-form>
        </div>
      </b-dialog>
      <b-dialog :show.sync="dialogShowContent" title="快讯内容" width="500px">
        <p class="p-title">封面：</p>
        <img :src="newsImg" style="width:200px;height:200px;" @click="openShowImg(newsImg)">
        <p class="p-title">浏览次数：<span>{{browseNum}}</span></p>
        <p class="p-title" >摘要：</p>
        <div>{{articleAbstracts}}</div>
        <p class="p-title">内容：</p>
        <div v-html="showContent">{{showContent}}</div>
      </b-dialog>
       <!-- 预览大图 -->
        <el-dialog
          title="图片"
          :visible.sync="dialogVisibleShowImg"
          :close-on-click-modal="false"
          :close-on-press-escape="false"
          append-to-body
        >
        <img :src="cPath" style="width:800px;">
        </el-dialog>
    </template>

  </div>
</template>
<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 150px;
    height: 150px;
    line-height: 150px;
    text-align: center;
  }
  .avatar {
    width: 150px;
    height: 150px;
    display: block;
  }
</style>
<script>
  import PageList from '@/mixins/pageList' // 列表页面查询 mixin
  import * as RequestURL from '@/request/office/article' // 请求后端URL路径
  import BaseCityCascader from '@/components/BaseCascader/city' // 城市级联组件
  import BaseDeptCascader from '@/components/BaseCascader/dept' // 部门级联组件
  import { ARTICLEIMG_UPLOAD_URL } from '@/request/main' // 上传封面
  import BaseEditor from '@/components/BaseEditor'
  import {oaAddLog, oaUpdateLog, oaQueryLog,oaDelLog} from '@/request/log/oaLog'

  export default {
    name: 'informationManage',
    mixins: [PageList],
    components: {BaseCityCascader,BaseDeptCascader,BaseEditor},
    data () {
      return {
        noShow: false,
        imageUrl: '',
        uploadPath: ARTICLEIMG_UPLOAD_URL,
        showImg: false,
        fileList: [],
        articleClassInfo: {},
        cityInfo: [],
        queryForm: {},
        topForm: {
          isOpen: false,
          isSetTop: false
        },
        addForm: {
          title: '',
          abstracts: '',
          cover: '',
        },
        selectStatus: [],
        selectCity: [],
        selectType: [],
        options: [],
        nextChildArr: [],
        lastChildArr: [],
        editSqlForm: {
          sqlDescription: ''
        },
        // 表单校验配置
        addFormRules: {
          title: [
            {required: true, message: '请输入值', trigger: 'blur'}
          ],
          abstracts: [
            {required: true, message: '请输入值', trigger: 'blur'}
          ],

          cover: [
            {required: true, message: '请上传', trigger: 'blur'}
          ],
        },
        dialogAddVisible: false,
        dialogShowContent: false,
        loadingSubmitBtn: false,
        dialogVisibleShowImg: false,
        loadingForm: false,
        loadingItemView: false,
        currentRowData: null, // 当前操作的行数据
        currentDynamicConfigureItem:null,
        valueData: [],
        levelInfo1: [],
        levelInfo2: [],
        levelInfo3: [],
        editorVal: '',
        showContent: '',
        fileUrl: '',
        nowQueryForm: {},
        browseNum: 0,
        newsImg: '',
        articleAbstracts: '',
        titleText: '添加快讯',
        isAdd: true,
        originalData: []
      }
    },
    methods: {
      openShowImg(path){
        this.dialogVisibleShowImg = true;
        this.cPath = path;
      },
      //获取一些基础信息
      getArticleType(){
        const params = {};
        RequestURL.getArticleType(params).then(res => {
          this.selectCity = res.data.cityInfo;
          for(let key in res.data.examineStatus ){
            let item = {
              value: key,
              label: res.data.examineStatus[key]
            }
            this.selectStatus.push(item);
          }
        }).catch((err) => {
          console.log(err);
          this.$message({
            type: 'info',
            message: err.msg
          })
        })
      },

      // 新增
      handleAdd (row) {
        this.$refs['editor'] && this.$refs['editor'].clear()
        this._resetForm('addForm')
        this.currentRowData = null // 置空当前操作的行数据
        this.isAdd = true;
        this.titleText = '添加快讯'
        this.imageUrl= '';
        this.fileList = [];
        this.editorVal = '';
        this.dialogAddVisible = true;
        this.addForm.widgetCode=row.widgetCode;
        this.currentDynamicConfigureItem=row;
        delete this.addForm.id
        this.showImg = false;
      },
      changeCity(){

      },
       handleChange(val, label, data){
        console.log(data)
        if(data.dataType=="dept"){
          this.addForm.deptName=data.name
        }else{
          setTimeout(() => {
            this.addForm.deptId=""
          this.addForm.deptName=""
          }, 50)
        }
      },
      handleShow(row){
        // this.dialogShowContent = true;
        // //alert(row.content)
        // this.showContent = row.content;
        // this.browseNum = row.browseCnt,
        // this.newsImg =  row.cover
        // this.articleAbstracts = row.abstracts
        this.$router.push('./newsDetail/' + row.id)
      },
      // 编辑
      handleEdit (row) {
        this._resetForm('addForm')
        this.currentRowData = row
        this.isAdd = false
        this.titleText = '编辑快讯'
        this.dialogAddVisible = true
        this.showImg = true;
        this.$nextTick(() => {
          this._setForm()
        })

      },
      openUploadCover() {

        this.dialogUploadCover = true
        // this.uploadPath = RequestUploadURL;
      },
      handleAvatarSuccess(res, file) {
        console.log(res);
        console.log(file)
        this.imageUrl = URL.createObjectURL(file.raw);
        this.addForm.cover = res.data.filePath;
        let fileItem = {
          name: res.data.fileName,
          url: res.data.filePath
        }
        this.fileList.push(fileItem);
      },
      beforeAvatarUpload(file) {
        // debugger
        // const isJPG = file.type === 'image/jpeg';
        // const isLt2M = file.size / 1024 / 1024 < 2;
        //
        // if (!isJPG) {
        //   this.$message.error('上传头像图片只能是 JPG 格式!');
        // }
        // if (!isLt2M) {
        //   this.$message.error('上传头像图片大小不能超过 2MB!');
        // }
        // return isJPG && isLt2M;
      },
      // 提交
      handleSubmit () {

        this.$refs['addForm'].validate((valid) => {
          if (valid) {

            // alert(this.$refs['editor'].getContent());
            this.addForm.content = this.$refs['editor'].getContent();
            this.addForm.type = '0';
            const params = {
              jsonData: JSON.stringify({...this.addForm})
            }

            if (this.isAdd) {
              params.id = ''
            }

            this.$confirm('确定保存编辑的信息吗？, 是否继续?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.loadingSubmitBtn = true
              RequestURL.saveArticle(params).then(res => {
                this.loadingSubmitBtn = false
                this.dialogAddVisible = false
                if (res.success) {
                  this.$message({
                    type: 'success',
                    message: res.msg || '操作成功'
                  })
                  this._loadData(false);
                  //记日志
                  if(this.isAdd){//新增
                      let logContent = '新增快讯；'+"标题："+this.addForm.title
                      let message = {
                        sourceId: '',
                        sourceCode: this.addForm.title,
                        businessTypeId: 6,//业务类型，
                        sourceTypeId: 14,
                        operatTypeId: 600,
                        logContent: logContent,
                        path:this.addForm.cover
                      }
                      oaAddLog({message: JSON.stringify(message)}).then(res => {
                        console.log(res)
                      })
                  }else{//编辑
                      let message = {
                      sourceId: this.addForm.id,
                      sourceCode: '快讯',
                      businessTypeId: 6,//业务类型，
                      sourceTypeId: 14,
                      operatTypeId: 601,
                      labelData: this.$utils.getFormFields(this.$refs['addForm']),
                      originalData: this.originalData,
                      newData: JSON.parse(params.jsonData)
                    }

                    oaUpdateLog({message: JSON.stringify(message)}).then(res => {
                      console.log(res)
                    })
                }
                }else{
                  this.$message({
                    type: 'warning',
                    message: res.msg
                  })
                }
              }).catch((err) => {
                console.log(err);
                this.loadingSubmitBtn = false
                this.$message({
                  type: 'info',
                  message: err.msg || '保存失败'
                })
              })
            })

          }
        })
      },

      // 删除
      handleDelete (row) {
        this.$confirm('确定删除该条数据？, 是否继续?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          RequestURL.delArticle({id: row.id}).then(res => {
            this.$message({
              type: 'success',
              message: res.msg || '删除成功'
            })
            //this._loadData(false);
            for(let i=0;i<this.tableData.length;i++){
              if(this.tableData[i].id==row.id){
                this.tableData.splice(i,1)
              }
            }
            //写日志
            let logContent = '删除快讯；'+";标题："+row.title
            let message = {
              sourceId: row.id,
              sourceCode: row.title,
              businessTypeId: 6,//业务类型，
              sourceTypeId: 14,
              operatTypeId: 602,
              logContent: logContent
            }
            oaDelLog({message: JSON.stringify(message)}).then(res => {
              console.log(res)
            })
          })
        })
      },

      handleQuery () {
        this.nowQueryForm = Object.assign({}, this.queryForm)
        this.listQuery.page = 1
        this.listQuery.currentPage = 1
        this._loadData(true)
      },
      // 加载数据
      _loadData (btnQuery) {
        if (btnQuery) {
          this.loadingQueryBtn = true
        }

        this.loadingView = true
        this.nowQueryForm.type = '0';
         let params = Object.assign({}, this.nowQueryForm, {
          limit: this.listQuery.limit,
          page: this.listQuery.page
        })
        //const params = this._getParams(this.queryForm)
        RequestURL.getArticleList(params).then(res => {
          this.tableData = res.data.map(item => {
             if(item.examineStatus===0){
               item.examineStatusName = '未审核'
             }else if(item.examineStatus===1){
               item.examineStatusName = '已审核'
             }else{
               item.examineStatusName = '已驳回'
             }
             return item
          })
          console.log(this.tableData);
          this.listQuery.total = res.count
          this.loadingQueryBtn = false
          this.loadingView = false

        }).catch(() => {
          this.loadingQueryBtn = false
          this.loadingView = false
        })
      },

      // 表单回填
      _setForm () {
        // 拷贝为修改过的原始表单数据
        this.originalData = Object.assign({}, this.addForm)
        this.$utils.setFormInfo(this.addForm, this.currentRowData, () => {


          this.fileUrl = this.currentRowData['cover']
          //alert(this.currentRowData['cover']);
          this.addForm['id'] = this.currentRowData['id']
          this.editorVal = this.currentRowData['content']
          this.imageUrl = ""
        })

        // 移除表单回填时 element ui自动添加的校验效果
        setTimeout(() => {
          this.$refs['addForm'].clearValidate()
        }, 100)
      },


    },
    mounted () {
      this.getArticleType();

    }
  }
</script>

<style scoped lang="scss">
  .p-title{
    margin-bottom: 16px;
    margin-top: 12px;
  }
</style>
