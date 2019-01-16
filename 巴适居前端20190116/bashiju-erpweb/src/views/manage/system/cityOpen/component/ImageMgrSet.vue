<template>
  <div class="page-content">
    <div>
      <div class="query-form">
        <el-form size="mini" ref="queryForm" :inline="true" :model="queryForm" class="demo-form-inline">
          <!--<el-form-item label="城市" prop="cityCode">-->
            <!--<open-city-selector-->
              <!--v-model="queryForm.cityCode"-->
              <!--:url="cityUrl"-->
              <!--:props="cascaderProps"-->
              <!--:dataProps="cascaderDataProps" @change="handleChangeCity">-->
            <!--</open-city-selector>-->
          <!--</el-form-item>-->
          <el-form-item label="类型" prop="type">
            <el-select v-model="queryForm.type" placeholder="请选择类型">
              <el-option v-for="item in selectType" :label="item.label" :value="item.value" :key="item.value"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click.native.prevent="handleQuery" :loading="loadingQueryBtn">查询</el-button>
            <el-button @click.native.prevent="_resetForm('queryForm')">清空</el-button>
            <el-button type="primary" @click="handleAdd">添加图片</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <div v-loading="loadingView">
      <el-table
        :data="tableData"
        border tooltip-effect="light"
        align="center"
        style="width: 100%"
        size="mini"
      >

        <el-table-column
          prop="typeName"
          align="center"
          show-overflow-tooltip
          label="类型">
        </el-table-column>

        <el-table-column
          prop="mainTitle"
          align="center"
          show-overflow-tooltip
          label="主标题">
        </el-table-column>

        <el-table-column
          prop="smallTitle"
          align="center"
          show-overflow-tooltip
          label="副标题">
        </el-table-column>

        <el-table-column
          prop="code"
          align="center"
          show-overflow-tooltip
          label="图片标识码">
        </el-table-column>

        <el-table-column
          prop="codeDesc"
          align="center"
          show-overflow-tooltip
          label="图片标识描述">
        </el-table-column>

        <el-table-column
          prop="conditionData"
          align="center"
          show-overflow-tooltip
          label="条件">
        </el-table-column>

        <el-table-column
          prop="updateTime"
          align="center"
          show-overflow-tooltip
          :formatter="_timeFormat"
          label="更新时间">
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
        >
          <template slot-scope="scope">
            <el-button   @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
            <el-button   @click="handleDelete(scope.row)" type="text" size="small">删除</el-button>
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
      <el-dialog
        :visible.sync="dialogAddVisible"
        :title="title"
        width="850px"
        append-to-body
        :modal-append-to-body="false"
      >
        <el-form
          v-loading="loadingForm"
          :model="addForm"
          status-icon
          :rules="addFormRules"
          style="height:540px;overflow-y:scroll;"
          ref="addForm"
          label-width="120px"
          size="mini"
        >
          <!--<el-form-item label="城市" prop="cityCode">-->
            <!--<el-select v-model="addForm.cityCode" placeholder="请选择" style="width:100%">-->
              <!--<el-option-->
                <!--v-for="item in selectCity"-->
                <!--:key="item.cityCode"-->
                <!--:label="item.cityName"-->
                <!--:value="item.cityCode">-->
              <!--</el-option>-->
            <!--</el-select>-->
          <!--</el-form-item>-->
          <el-form-item label="类型" prop="type">
            <el-select v-model="addForm.type" :disabled="isType" placeholder="请选择类型" style="width:100%"  @change="changeType">
              <el-option v-for="item in selectType" :label="item.label" :value="item.value" :key="item.value"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="主标题" prop="mainTitle">
            <el-input v-model="addForm.mainTitle"></el-input>
          </el-form-item>
          <el-form-item label="副标题" prop="smallTitle">
            <el-input v-model="addForm.smallTitle"></el-input>
          </el-form-item>

          <el-form-item label="图片标识码" prop="code">
            <el-select v-model="addForm.code" placeholder="请选择标识" :disabled="isCode" style="width:100%">
              <el-option v-for="item in selectCode" :label="item.label" :value="item.value" :key="item.value" ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="图片标识描述" prop="codeDesc">
            <el-input v-model="addForm.codeDesc"></el-input>
          </el-form-item>
          <el-form-item label="条件" prop="conditionData">
            <el-input type="textArea" v-model="addForm.conditionData"></el-input>
          </el-form-item>

          <el-form-item label="图片" prop="imgName">
            <img v-show="showImg" :src="fileUrl" class="" style="width:500px;">
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
          <el-form-item label="" prop="path" v-show="noShow">
            <el-input v-model="addForm.path" ></el-input>
          </el-form-item>

          <el-form-item class="margin-b-none">
            <el-button type="primary" :loading="loadingSubmitBtn" @click="handleSubmit">确认</el-button>
            <el-button @click="_resetForm('addForm')">清空</el-button>
          </el-form-item>

        </el-form>
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
  import * as RequestURL from '@/request/manage/imgManage' // 请求后端URL路径
  import { ARTICLEIMG_UPLOAD_URL } from '@/request/main' // 上传图片
  import OpenCitySelector from '@/components/BaseCascader' // // 城市级联组件
  import {systemAddLog, systemUpdateLog, systemQueryLog,systemDelLog} from '@/request/log/systemPlatformLog'

  export default {
    name: 'ImageMgrSet',
    mixins: [PageList],
    components: {OpenCitySelector},
    props:{
      data:{
        type: Object,
        required: true,
        default(){
          return null
        }
      }
    },
    data () {
      return {
        tableData: [],
        cityUrl: 'manage/commonselect/queryOpenCity',
        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        selectType: [],
        selectCode: [],
        selectCity: [],
        noShow: false,
        imageUrl: '',
        uploadPath: ARTICLEIMG_UPLOAD_URL,
        showImg: false,
        fileList: [],
        articleClassInfo: {},
        cityInfo: [],
        queryForm: {
          cityCode: this.data.cityCode,
          type: ''
        },
        topForm: {
          isOpen: false,
          isSetTop: false
        },
        addForm: {
          cityCode: this.data.cityCode,
          type: '',
          mainTitle: '',
          smallTitle: '',
          code: '',
          codeDesc: '',
          conditionData: '',
          path: '',
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
          // cityCode: [
          //   {required: true, message: '请输入值', trigger: 'blur'}
          // ],
          type: [
            {required: true, message: '请输入值', trigger: 'blur'}
          ],
          mainTitle: [
            {required: true, message: '请输入值', trigger: 'blur'}
          ],
          smallTitle: [
            {required: true, message: '请输入值', trigger: 'blur'}
          ],
          code: [
            {required: true, message: '请输入值', trigger: 'blur'}
          ],
          codeDesc: [
            {required: true, message: '请输入值', trigger: 'blur'}
          ],
        },
        dialogAddVisible: false,
        dialogShowContent: false,
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
        title: '添加图片',
        fileUrl: '',
        isAdd: false,
        existFlag: false,
        isCity: false,
        isType: false,
        isCode: false,
        originalData: []
      }
    },
    methods: {
      //获取类型
      getType(){
        let param = {};
        RequestURL.getTypeEnum(param).then(res => {
          Object.keys(res.data.type).forEach(key => {
            let item = {
              value: key,
              label: res.data.type[key]
            }
            this.selectType.push(item)
          })
        })
      },
      //获取城市
      getOpenCity(){
        let param = {};
        RequestURL.getOpenCity(param).then(res => {
          this.selectCity = res.data;
        })
      },
      // 新增
      handleAdd (row) {
        this._resetForm('addForm')
        this.currentRowData = null // 置空当前操作的行数据
        this.isAdd = true;
        this.dialogAddVisible = true;
        this.currentDynamicConfigureItem=row;
        this.imageUrl = '';
        this.showImg = false;
        this.fileUrl = '';
        this.fileList = [];
        this.editorVal = '';
        delete this.addForm.id
        this.isCity = false
        this.isType = false
        this.isCode = false
      },
      changeType(){
        if(this.isAdd){
          if(this.addForm.type==='0'){
            this.selectCode = [{label:1,value:1},{label:2,value:2},{label:3,value:3}] //首页轮播图3张
          }
          if(this.addForm.type==='1'){
            this.selectCode = [{label:1,value:1},{label:2,value:2},{label:3,value:3},{label:4,value:4},{label:5,value:5}] //二手房主题图5张
          }
          if(this.addForm.type==='2'){
            this.selectCode = [{label:1,value:1},{label:2,value:2},{label:3,value:3}] //租房主题图3张
          }
          if(this.addForm.type==='3'){
            this.selectCode = [{label:1,value:1},{label:2,value:2},{label:3,value:3}] //微店宝图3张
          }
          if(this.addForm.type==='4'){
            this.selectCode = [{label:1,value:1},{label:2,value:2},{label:3,value:3},{label:4,value:4}] //卖房委托主题图
          }
          if(this.addForm.type==='5'){
            this.selectCode = [{label:1,value:1},{label:2,value:2},{label:3,value:3},{label:4,value:4}] //租房委托主题图
          }
          if(this.addForm.type==='6'){
            this.selectCode = [{label:1,value:1},{label:2,value:2}] //关注、微信小程序图
          }
          if(this.addForm.type==='7'){
            this.selectCode = [{label:1,value:1}] //新房推荐背景图
          }
        }
      },
      handleChangeCity(){

      },
      handleShow(row){
        this.dialogShowContent = true;
        this.showContent = row.content;
      },
      // 编辑
      handleEdit (row) {
        this._resetForm('addForm')
        this.currentRowData = row
        this.isAdd = false
        this.isCity = true
        this.isType = true
        this.isCode = true
        this.dialogAddVisible = true
        this.showImg = true;
        this.$nextTick(() => {
          this._setForm()
        })

      },
      openUploadCover() {
        this.dialogUploadCover = true
      },
      handleAvatarSuccess(res, file) {
        this.addForm.path = res.data.filePath;
        this.fileUrl = res.data.filePath;
        this.showImg = true;
        let fileItem = {
          name: res.data.fileName,
          url: res.data.filePath
        }
        this.fileList.push(fileItem);
      },
      beforeAvatarUpload(file) {
      },
      // 提交
      handleSubmit () {
        this.$refs['addForm'].validate((valid) => {
          if (valid) {
            let typeName = this.selectType.find(item => item.value === this.addForm.type).label
            this.addForm.typeName = typeName
            const params = {...this.addForm}

            if (this.isAdd) {
              this.tableData.forEach((item, index) => {
                if (item.cityCode === this.addForm.cityCode && item.type===this.addForm.type && item.code===this.addForm.code) {
                  this.existFlag = true
                  //break;
                }
              })

            }

            this.$confirm('确定保存编辑的信息吗？, 是否继续?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.loadingSubmitBtn = true
              let cfg = {
                headers: {
                  'Content-Type': 'application/json;charset=UTF-8;'
                }
              }
              if(this.existFlag){
                this.$message({
                  type: 'warning',
                  message: "该类图片已经添加过了，可进行修改！"
                })
              }else{
                RequestURL.saveComImg(params,cfg).then(res => {
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
                      let logContent = '新增了图片，类型：'+this.addForm.typeName+";图片标识码："+this.addForm.code
                      let message = {
                        sourceCode: '图片管理',
                        sourceTypeId: 24,
                        operatTypeId: 1,
                        logContent: logContent,
                        path:this.addForm.path
                      }
                      systemAddLog({message: JSON.stringify(message)}).then(res => {
                        console.log(res)
                      })
                    }else{//编辑
                      let message = {
                        sourceCode: '图片管理',
                        sourceTypeId: 24,
                        operatTypeId: 2,
                        labelData: this.$utils.getFormFields(this.$refs['addForm']),
                        originalData: this.originalData,
                        newData: JSON.parse(JSON.stringify(params))
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
              }

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

          RequestURL.delComImg({id: row.id}).then(res => {
            this.$message({
              type: 'success',
              message: res.msg || '删除成功'
            })
            for(let i=0;i<this.tableData.length;i++){
              if(this.tableData[i].id==row.id){
                this.tableData.splice(i,1)
              }
            }
            //写日志
            let logContent = '删除图片，类型：'+row.typeName+";图片标识码："+row.code
            let message = {
              sourceCode: '图片管理',
              sourceTypeId: 24,
              operatTypeId: 3,
              logContent: logContent
            }
            systemDelLog({message: JSON.stringify(message)}).then(res => {
              console.log(res)
            })
          })
        })
      },


      // 加载数据
      _loadData (btnQuery) {
        if (btnQuery) {
          this.loadingQueryBtn = true
        }
        this.loadingView = true
        const params = this._getParams(this.queryForm)
        RequestURL.getComImg(params).then(res => {
          this.tableData = res.data.map(item => {
            return item;
          })
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
          this.fileUrl = this.currentRowData['path']
          this.addForm['id'] = this.currentRowData['id']
        })

        // 移除表单回填时 element ui自动添加的校验效果
        setTimeout(() => {
          this.$refs['addForm'].clearValidate()
        }, 100)
      },


    },
    mounted () {
      this. getType();
      this.getOpenCity();
    }
  }
</script>

<style scoped lang="scss">

</style>
