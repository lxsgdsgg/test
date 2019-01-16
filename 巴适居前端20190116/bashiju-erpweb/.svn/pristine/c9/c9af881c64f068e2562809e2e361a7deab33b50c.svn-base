<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form">
        <el-form size="small" ref="queryForm" :inline="true" :model="queryForm" class="demo-form-inline">
          <el-form-item label="城市" prop="cityCode">
            <base-city-cascader v-model="queryForm.cityCode" @initSelectData="initSelectData"></base-city-cascader>
          </el-form-item>
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

          <el-form-item label="类型" prop="typeName">
            <el-input v-model="queryForm.typeName" placeholder="请输入类型"></el-input>
          </el-form-item>
          <el-form-item label="作者" prop="author">
            <el-input v-model="queryForm.author" placeholder="请输入作者"></el-input>
          </el-form-item>
          <el-form-item label="来源" prop="sources">
            <el-input v-model="queryForm.sources" placeholder="请输入来源"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click.native.prevent="handleQuery" :loading="loadingQueryBtn">查询</el-button>
            <el-button @click.native.prevent="_resetForm('queryForm')">重置</el-button>
          </el-form-item>
          <el-form-item class="pull-right">
            <el-button v-hasOnlyBtn="'informationAddBtn'" type="primary" @click="handleAdd">添加文章</el-button>
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
          prop="typeName"
          align="center"
          label="文章分类">
        </el-table-column>

        <el-table-column
          prop="author"
          align="center"
          label="文章作者">
        </el-table-column>

        <el-table-column
          prop="sources"
          align="center"
          label="文章来源">
        </el-table-column>

        <el-table-column
          prop="sourcesLink"
          align="center"
          label="来源链接">
        </el-table-column>

        <el-table-column
          prop="browseCnt"
          align="center"
          label="浏览次数">
        </el-table-column>

        <el-table-column
          prop="likedCnt"
          align="center"
          label="点赞次数">
        </el-table-column>

        <el-table-column
          prop="isOpen"
          align="center"
          label="是否对所有城市开放">
        </el-table-column>

        <el-table-column
          prop="isSetTopText"
          align="center"
          label="是否置顶">
        </el-table-column>

        <el-table-column
          prop="isPublishedText"
          align="center"
          label="是否发布">
        </el-table-column>

        <el-table-column
          prop="labels"
          align="center"
          label="文章标签">
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
          prop="reason"
          align="center"
          label="驳回原因">
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
        >
          <template slot-scope="scope">
            <el-button v-hasMultipleBtn="['informationShowBtn',scope.row]"  @click="handleShow(scope.row)" type="text" size="small">查看</el-button>
            <el-button v-hasMultipleBtn="['informationUpdateBtn',scope.row]" v-if="scope.row.examineStatus==null || scope.row.examineStatus==0"  @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
            <el-button v-hasMultipleBtn="['informationUpdateBtn',scope.row]" v-if="scope.row.isSetTop==0"  @click="handleSetTop1(scope.row)" type="text" size="small">置顶</el-button>
            <el-button v-hasMultipleBtn="['informationUpdateBtn',scope.row]" v-if="scope.row.isSetTop==1"  @click="handleSetTop2(scope.row)" type="text" size="small">取消置顶</el-button>
            <el-button v-hasMultipleBtn="['informationUpdateBtn',scope.row]" v-if="scope.row.isPublished==0"  @click="handleSetPublished1(scope.row)" type="text" size="small">发布</el-button>
            <el-button v-hasMultipleBtn="['informationUpdateBtn',scope.row]" v-if="scope.row.isPublished==1"  @click="handleSetPublished2(scope.row)" type="text" size="small">撤回</el-button>
            <el-button v-hasMultipleBtn="['informationDelBtn',scope.row]"   @click="handleDelete(scope.row)" type="text" size="small">删除</el-button>
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
      <b-dialog :show.sync="dialogAddVisible" :title="titleText" width="750px">
        <div style="height:500px;overflow-y:scroll;">
          <el-form size="small" ref="topForm" :inline="true" :model="topForm" class="demo-form-inline">
            <el-form-item label="" prop="isOpen">
              <el-checkbox v-model="topForm.isOpen">对所有城市开放</el-checkbox>
            </el-form-item>
            <el-form-item label="" prop="isSetTop">
              <el-checkbox v-model="topForm.isSetTop">置顶</el-checkbox>
            </el-form-item>
          </el-form>
          <el-form v-loading="loadingForm" :model="addForm" status-icon :rules="addFormRules" ref="addForm" label-width="100px">
            <el-form-item label="文章标题" prop="title">
              <el-input v-model="addForm.title"></el-input>
            </el-form-item>
            <el-form-item label="文章摘要" prop="abstracts">
              <el-input v-model="addForm.abstracts"></el-input>
            </el-form-item>
            <el-form-item label="所属城市" prop="cityCode">
              <el-select v-model="addForm.cityCode" placeholder="请选择" @change="changeCity" style="width:100%">
                <el-option
                  v-for="item in selectCity"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="文章分类" prop="classesId">
              <el-select v-model="addForm.classesId" placeholder="请选择" style="width:100%" @change="changeType()">
                <el-option
                  v-for="item in selectType"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="文章标签" prop="labels">
              <el-input v-model="addForm.labels" ></el-input>
            </el-form-item>
            <el-form-item label="文章作者" prop="author">
              <el-input v-model="addForm.author" ></el-input>
            </el-form-item>
            <el-form-item label="文章来源" prop="sources">
              <el-input v-model="addForm.sources" ></el-input>
            </el-form-item>
            <el-form-item label="来源链接" prop="sourcesLink">
              <el-input v-model="addForm.sourcesLink" ></el-input>
            </el-form-item>
            <el-form-item label="文章封面" prop="imgName">
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
      <b-dialog :show.sync="dialogShowContent" title="文章内容" width="760px">
        <p class="p-title">封面：</p>
        <img :src="articleImg" style="width:200px;height:200px;" @click="openShowImg(articleImg)">
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
  import * as RequestURL from '@/request/manage/contentManage' // 请求后端URL路径
  import BaseCityCascader from '@/components/BaseCascader/city' // 城市级联组件
  import { ARTICLEIMG_UPLOAD_URL } from '@/request/main' // 上传封面
  import BaseEditor from '@/components/BaseEditor'
  import {systemAddLog, systemUpdateLog, systemQueryLog,systemDelLog} from '@/request/log/systemPlatformLog'

  export default {
    name: 'informationManage',
    mixins: [PageList],
    components: {BaseCityCascader,BaseEditor},
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
          cityCode: '',
          classesId: '',
          labels: '',
          author: '',
          sources: '',
          sourcesLink: '',
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
          cityCode: [
            {required: true, message: '请输入值', trigger: 'blur'}
          ],
          classesId: [
            {required: true, message: '请输入值', trigger: 'blur'}
          ],
        },
        dialogAddVisible: false,
        dialogShowContent: false,
        dialogVisibleShowImg: false,
        loadingSubmitBtn: false,
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
        cPath: '',
        fileUrl: '',
        nowQueryForm: {},
        articleAbstracts: '',
        articleImg: '',
        originalData:[],
        isAdd: true,
        classesName: '',
        titleText:'添加资讯'
      }
    },
    methods: {
      initSelectData(data) {
          console.log('**************',data)
        if (data!=null&&data.length>0){ 
          this.queryForm.cityCode = data[0].code
          this.handleQuery()
        }
      },
      changeType(){
       this.classesName = this.selectType.find(item=>item.value===this.addForm.classesId).label
      },
      openShowImg(path){
        this.dialogVisibleShowImg = true;
        this.cPath = path;
      },
      handleSetTop1(row){
        let param = {
          id: row.id,
          isSetTop: 1,
          type: 'information'
        }
        this.setStatus("确定置顶吗",param,row.typeName,row.title);
      },
      handleSetTop2(row){
        let param = {
          id: row.id,
          isSetTop: 0,
          type: 'information'
        }
        this.setStatus("确定取消置顶吗",param,row.typeName,row.title);
      },

      handleSetPublished1(row){
        let param = {
          id: row.id,
          isPublished: 1,
          type: 'information'
        }
        this.setStatus("确定发布吗",param,row.typeName,row.title);
      },
      handleSetPublished2(row){
        let param = {
          id: row.id,
          isPublished: 0,
          type: 'information'
        }
        this.setStatus("确定撤回吗",param,row.typeName,row.title);
      },

      setStatus(tag,param,typeName,title){
          let params = {jsonData:JSON.stringify(param)}
          this.$confirm(tag, {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              
              RequestURL.saveArticle(params).then(res => {
                if (res.success) {
                  this.$message({
                    type: 'success',
                    message: res.msg || '操作成功'
                  })
                  this._loadData(false);
                  console.log("aaaaaaaaaaaaaaaaa")
                  console.log(param.isSetTop)
                   //写日志
                  let statusText = ''
                  if(param.isSetTop && param.isSetTop===1){
                    statusText = '设置置顶'
                  }
                  if(param.isSetTop && param.isSetTop===0){
                    statusText = '取消置顶'
                  }
                  if(param.isPublished && param.isPublished===1){
                    statusText = '发布资讯'
                  }
                  if(param.isPublished && param.isPublished===0){
                    statusText = '撤回发布'
                  }
                  let logContent = statusText+';分类:'+typeName+"；标题："+title
                  let message = {
                      sourceCode: '房产资讯',
                      sourceTypeId: 25,
                      operatTypeId: 5,
                      logContent: logContent
                    }
                  systemQueryLog({message: JSON.stringify(message)}).then(res => {
                      console.log(res)
                    })
                }else{
                  this.$message({
                    type: 'warning',
                    message: res.msg
                  })
                }
              }).catch((err) => {
                console.log(err);
                
                this.$message({
                  type: 'info',
                  message: err.msg || '操作失败'
                })
              })
            })
      },
      //获取一些基础信息
      getInformationBaseInfo(){
        const params = {};
        RequestURL.getInformationBaseInfo(params).then(res => {
          console.log("aaaaaaaaaaaaa"+res.data);
          this.articleClassInfo = res.data.articleClassInfo;
          this.cityInfo = res.data.cityInfo;
          for(let i=0;i<res.data.cityInfo.length;i++){
            let cityItem = {
              value: res.data.cityInfo[i].cityCode,
              label: res.data.cityInfo[i].cityName
            }
            this.selectCity.push(cityItem);
          }

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
        this.titleText = '添加资讯'
        this.dialogAddVisible = true;
        this.addForm.widgetCode=row.widgetCode;
        this.currentDynamicConfigureItem=row;
        this.imageUrl= '';
        this.fileList = [];
        this.editorVal = '';
        delete this.addForm.id
        this.showImg = false;
      },
      changeCity(){
        this.selectType = [];
        this.addForm.classesId = '';
        const params = {
          cityCode: this.addForm.cityCode
        }
        RequestURL.getInformationClasse(params).then(res => {
          for(let i=0;i<res.data.length;i++){
            let typeItem = {
              value: res.data[i].id,
              label: res.data[i].name
            }
            this.selectType.push(typeItem);
          }
        })
      },
      handleShow(row){
        this.dialogShowContent = true;
        //alert(row.content)
        this.showContent = row.content;
        this.articleAbstracts = row.abstracts;
        this.articleImg= row.cover
      },
      // 编辑
      handleEdit (row) {
        this._resetForm('addForm')
        this.editorVal = ''
        this.currentRowData = row
        this.isAdd = false
        this.titleText = '编辑资讯'
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
            if(this.topForm.isOpen==false){
              this.addForm.isOpen = 0;
            }else{
              this.addForm.isOpen = 1;
            }
            if(this.topForm.isSetTop==false){
              this.addForm.isSetTop = 0;
            }else{
              this.addForm.isSetTop = 1;
            }
            // alert(this.$refs['editor'].getContent());
            this.addForm.content = this.$refs['editor'].getContent();
            this.addForm.type = 'information';
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
                      let logContent = '新增了一篇资讯，分类：'+this.classesName+";标题："+this.addForm.title
                      let message = {
                          sourceCode: '房产资讯',
                          sourceTypeId: 25,
                          operatTypeId: 1,
                          logContent: logContent,
                          path:this.addForm.cover
                        }
                      systemAddLog({message: JSON.stringify(message)}).then(res => {
                          console.log(res)
                        })
                  }else{//编辑
                      let message = {
                      sourceCode: '房产资讯',
                      sourceTypeId: 25,
                      operatTypeId: 2,
                      labelData: this.$utils.getFormFields(this.$refs['addForm']),
                      originalData: this.originalData,
                      newData: JSON.parse(params.jsonData)
                    }

                    systemUpdateLog({message: JSON.stringify(message)}).then(res => {
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

          RequestURL.delArticle({id: row.id,type:'0'}).then(res => {
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
            let logContent = '删除了一篇资讯，分类：'+row.typeName+";标题："+row.title
            let message = {
                sourceCode: '房产资讯',
                sourceTypeId: 25,
                operatTypeId: 3,
                logContent: logContent
              }
            systemDelLog({message: JSON.stringify(message)}).then(res => {
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
        this.nowQueryForm.type = 'information';
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
              if(item.isOpen===0){
                item.isOpen = '否'
              }else{
                item.isOpen = '是'
              }
              if(item.isSetTop===0){
                item.isSetTopText = '否'
              }else{
                item.isSetTopText = '是'
              }
              if(item.isPublished===0){
                item.isPublishedText = '否'
              }else{
                item.isPublishedText = '是'
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
        this.currentRowData.classesId = Number(this.currentRowData.classesId )
        this.$utils.setFormInfo(this.addForm, this.currentRowData, () => {
          if(this.currentRowData.isOpen==0){
            this.topForm.isOpen=false
          }else{
            this.topForm.isOpen=true
          }
          if(this.currentRowData.isSetTop==0){
            this.topForm.isSetTop=false
          }else{
            this.topForm.isSetTop=true
          }
          this.fileUrl = this.currentRowData['cover']
          //alert(this.currentRowData['cover']);
          this.addForm['id'] = this.currentRowData['id']
          this.editorVal = this.currentRowData['content']

        })

        // 移除表单回填时 element ui自动添加的校验效果
        setTimeout(() => {
          this.$refs['addForm'].clearValidate()
        }, 100)
      },


    },
    mounted () {
      this.getInformationBaseInfo();

    }
  }
</script>

<style scoped lang="scss">
  .p-title{
    margin-bottom: 16px;
    margin-top: 12px;
  }
</style>
