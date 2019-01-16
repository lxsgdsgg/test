<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form">
        <el-form size="small" ref="queryForm" :inline="true" :model="queryForm" class="demo-form-inline">
          <el-form-item label="" prop="status">
            <el-select v-model="queryForm.status" placeholder="审批状态" >
              <el-option
                v-for="item in selectStatus"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="" prop="selectId">
              <dept-base-cascader
                @change="handleChangeDept"
                v-model="queryForm.selectId"
                :data="userSelectOpts" :props="selectProps" :dataProps="selectDataProps" placeholder="部门或员工">
              </dept-base-cascader>
          </el-form-item>
          <el-form-item label="" prop="postChangeTypeId">
            <el-select v-model="queryForm.postChangeTypeId" placeholder="变动类型" >
              <el-option
                v-for="item in selectPostChangeType"
                :key="item.id"
                :label="item.fieldValue"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="" prop="changeTime">
            <el-date-picker
              v-model="queryForm.startChangeTime"
              type="date"
              placeholder="变动日期-始" value-format="yyyy-MM-dd">
            </el-date-picker>
            <el-date-picker
              v-model="queryForm.endChangeTime"
              type="date"
              placeholder="变动日期-止" value-format="yyyy-MM-dd">
            </el-date-picker>
          </el-form-item>


          <el-form-item>
            <el-button type="primary" @click.native.prevent="handleQuery" :loading="loadingQueryBtn">查询</el-button>
            <el-button @click.native.prevent="_resetForm('queryForm')">重置</el-button>
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

      >

        <el-table-column
          prop="statusText"
          align="center"
          label="审批状态">
        </el-table-column>

        <el-table-column
          prop="userCode"
          align="center"
          label="用户编号">
        </el-table-column>

        <el-table-column
          prop="realName"
          align="center"
          label="用户姓名">
        </el-table-column>

        <el-table-column
          prop="approveror"
          align="center"
          label="审核人">
        </el-table-column>

        <el-table-column
          prop="postChangeTypeName"
          align="center"
          label="变动类型">
        </el-table-column>

        <el-table-column
          prop="reason"
          align="center"
          label="变动原因">
        </el-table-column>

        <el-table-column
          prop="changeTime"
          align="center"
          :formatter="_timeFormat"
          label="变动时间">
        </el-table-column>

        <el-table-column
          prop="oldDeptName"
          align="center"
          label="原部门">
        </el-table-column>

        <el-table-column
          prop="oldPpostName"
          align="center"
          label="原职务">
        </el-table-column>

        <el-table-column
          prop="deptName"
          align="center"
          label="调动部门">
        </el-table-column>

        <el-table-column
          prop="postName"
          align="center"
          label="调动职务">
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
        >
          <template slot-scope="scope">
            <el-button v-if="scope.row.status===0" @click="handleSure(scope.row)" type="text" size="small">同意</el-button>
            <el-button v-if="scope.row.status===0" @click="handleReject(scope.row)" type="text" size="small">不同意</el-button>
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
      <b-dialog :show.sync="dialogAddVisible" title="填写原因" width="280px">
        <el-form v-loading="loadingForm" :model="sureForm" status-icon :rules="sureFormRules" ref="sureForm" label-width="100px">
          <el-form-item label="原因" prop="reason">
            <el-input v-model="sureForm.reason"></el-input>
          </el-form-item>
        </el-form>

        <div style="text-align:center;margin-top:50px;">
          <el-button type="primary" :loading="loadingSubmitBtn" @click="handleSubmit">确认</el-button>
          <el-button @click="dialogAddVisible=false">取消</el-button>
        </div>

      </b-dialog>


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
  import * as RequestURL from '@/request/office/personnel' // 请求后端URL路径
  import PostBaseCascader from '@/components/BaseCascader' // 职务级联组件
  import DeptBaseCascader from '@/components/BaseCascader' // 部门级联组件
  import {queryReferenceUserSelect,queryPositionSelect,queryOpenCityCompanyDepart} from '@/request/manage/common'
  import { ARTICLEIMG_UPLOAD_URL } from '@/request/main' // 上传封面
  import BaseEditor from '@/components/BaseEditor'
  import BaseVagueAutocomplete from '@/components/BaseVagueAutocomplete'
  import {getSelectValue} from '@/request/app'
  import {oaAddLog, oaUpdateLog, oaQueryLog,oaDelLog} from '@/request/log/oaLog'

  export default {
    name: 'personnelExamine',
    mixins: [PageList],
    components: {PostBaseCascader,DeptBaseCascader,BaseEditor,BaseVagueAutocomplete},
    data () {
      return {
        selectStatus:[{value:0,label:'待审核'},{value:1,label:'同意'},{value:2,label:'不同意'}],
        selectContractState: [{value:1,label:'已签'},{value:0,label:'未签'}],
        selectSocialSecurity: [{value:1,label:'已投'},{value:0,label:'未投'}],
        selectPost: [],
        deptSelectOpts: [],
        selectPostChangeType: [],
        selectRecruitWay: [],
        userSelectOpts: [],
        selectDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        selectProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          children: 'children', // 指定选项的子选项为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        noShow: false,
        imageUrl: '',
        uploadPath: ARTICLEIMG_UPLOAD_URL,
        showImg: false,
        fileList: [],
        articleClassInfo: {},
        cityInfo: [],
        queryForm: {
          selectId: '',
          userId: '',
          deptId: ''
        },
        vagueData: '', //
        // 员工名称，员工编号,电话号码
        vagueQueryInfo: [
          {label: '员工名称', type: 1},
          {label: '员工编号', type: 2},
          {label: '电话号码', type: 3},

        ],
        topForm: {
          isOpen: false,
          isSetTop: false
        },
        addForm: {
          realName: '',
          userCode: '',
          sex: '',
          nickname: '',
          age: '',
          nation: '',
          nativePlace: '',
          education: '',
          marriage: '',
          natureHousehold: '',
          politicalStatus: '',
          postalCode: '',
          birthDate: '',
          idCode: '',
          entryDate: '',
          mobile: '',
          deptName: '',
          postName: '',
          duty: '',
          emergencyContacter: '',
          emergencyContactPhone: '',
          recruitWay: 0,
          contractState: '',
          socialSecurity: '',
          formalTime: '',
          cardNumber: '',
          houseAddress: '',
          currentAddress: '',
          autograph: ''
        },
        sureForm: {

        },


        selectCity: [],
        selectType: [],
        options: [],
        nextChildArr: [],
        lastChildArr: [],
        editSqlForm: {
          sqlDescription: ''
        },
        // 表单校验配置
        sureFormRules: {
          'reason':[
              { required: true, message: '请填写原因', trigger: 'blur' }
            ],
        },
        changeFormRules: {

        },
        dialogAddVisible: false,
        dialogShowContent: false,
        loadingSubmitBtn: false,
        dialogVisibleShowImg: false,
        dialogChange: false,
        loadingForm: false,
        loadingItemView: false,
        loadingSubmitChange: false,
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
        cPath: '',
        sure: true,
        sureId: '',
        row:''
      }
    },
    methods: {
      // 获取部门
      handleChangeDept (val, name, data) {
          if (data.dataType == 'dept') {
            this.queryForm.deptId = data.code
            delete this.queryForm.userId
            delete this.queryForm.selectId

          }else if(data.dataType == 'user'){
            this.queryForm.userId = data.code
            delete this.queryForm.deptId
            delete this.queryForm.selectId
          }else{
            setTimeout(() => {
              this.queryForm.deptId = ''
              this.queryForm.userId = ''
              this.queryForm.selectId = ''
            }, 50)
          }

      },
      // 获取职务
      handleChangePost (val, name, data) {
      },

      //获取人员
      handleChangeUser(val, name, data) {

      },
      openShowImg(path){
        this.dialogVisibleShowImg = true;
        this.cPath = path;
      },

      handleSure(row){
        this.sureId = row.id
        this.sure = true
        this.dialogAddVisible = true;
        this.row = row
      },

      handleReject(row){
        this.sureId = row.id
        this.sure = false
        this.dialogAddVisible = true

      },

      // 新增
      handleAdd (row) {
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
       handleChange(row){
        this._resetForm('changeForm')
        this.currentRowData = row
        this.isAdd = false
        this.dialogChange = true
        this.showImg = true;
        this.$nextTick(() => {
          this._setChangeForm()
        })
      },
      handleShow(row){
        this.dialogShowContent = true;
        //alert(row.content)
        this.showContent = row.content;
        this.browseNum = row.browseCnt,
        this.newsImg =  row.cover
        this.articleAbstracts = row.abstracts
      },
      // 编辑
      handleEdit (row) {
        this._resetForm('addForm')
        this.currentRowData = row
        this.isAdd = false
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

        this.$refs['sureForm'].validate((valid) => {
          if (valid) {

            // alert(this.$refs['editor'].getContent());

            const params = {
              id: this.sureId,
              reason: this.sureForm.reason
            }

            this.$confirm('确定保存编辑的信息吗？, 是否继续?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.loadingSubmitBtn = true
              if(this.sure){//同意
                  RequestURL.surePersonnel(params).then(res => {
                    this.loadingSubmitBtn = false
                    this.dialogAddVisible = false
                    if (res.success) {
                      this.$message({
                        type: 'success',
                        message: res.msg || '操作成功'
                      })
                      let paramMsg ={
                        reSessionId: this.row.userId
                        ,reSessionUuid: this.row.userUUID
                        ,reSessionName: this.row.realName,
                        msgContent : `变动审核通过`
                      }
                      this._sendSysMsg(paramMsg);
                      let paramMsg1 ={
                        reSessionId: this.row.operatorId
                        ,reSessionUuid: this.row.operatorUUID
                        ,reSessionName: this.row.operator,
                        msgContent : `变动审核通过`
                      }
                      this._sendSysMsg(paramMsg1);
                      this._loadData(false);
                       //写日志
                    let logContent = '同意变动'
                    let message = {
                        sourceId: this.sureId,
                        sourceCode: '变动审批',
                        businessTypeId: 6,//业务类型，
                        sourceTypeId: 15,
                        operatTypeId: 612,
                        logContent: logContent,
                      }
                    oaQueryLog({message: JSON.stringify(message)}).then(res => {
                        console.log(res)
                      })

                    }else{
                      this.loadingSubmitBtn = false
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
              }else{//不同意
                  RequestURL.rejectPersonnel(params).then(res => {
                    this.loadingSubmitBtn = false
                    this.dialogAddVisible = false
                    if (res.success) {
                      this.$message({
                        type: 'success',
                        message: res.msg || '操作成功'
                      })
                      this._loadData(false);
                        //写日志
                    let logContent = '不同意变动'
                    let message = {
                        sourceId: this.sureId,
                        sourceCode: '变动审批',
                        businessTypeId: 6,//业务类型，
                        sourceTypeId: 15,
                        operatTypeId: 613,
                        logContent: logContent,
                      }
                    oaQueryLog({message: JSON.stringify(message)}).then(res => {
                        console.log(res)
                      })
                    }else{
                      this.loadingSubmitBtn = false
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

      handleChangeSubmit(){
          this.$refs['changeForm'].validate((valid) => {
          if (valid) {

            // alert(this.$refs['editor'].getContent());
            let postName = this.selectPost.find(item=>item.value===this.changeForm.postId).label
            let postChangeTypeName = this.selectPostChangeType.find(item=>item.value===this.changeForm.postChangeTypeId).label
            let saveForm = {
              userId: this.changeForm.id,
              oldPostId: this.currentRowData.postId,
              oldPpostName: this.currentRowData.postName,
              oldDeptId: this.currentRowData.deptId,
              oldDeptName: this.currentRowData.deptName,
              postId: this.changeForm.postId,
              postName: postName,
              deptId: this.changeForm.deptId,
              deptName: this.changeForm.deptName,
              postChangeTypeId: this.changeForm.postChangeTypeId,
              postChangeTypeName: postChangeTypeName,
              approver: this.changeForm.approver,
              changeTime: this.changeForm.changeTime,
              reason: this.changeForm.reason

            }
            const params = {
              jsonData: JSON.stringify(saveForm)
            }



            this.$confirm('确定保存编辑的信息吗？, 是否继续?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.loadingSubmitChange = true
              RequestURL.savePersonnelChange(params).then(res => {
                this.loadingSubmitChange = false
                this.dialogChange = false
                if (res.success) {
                  this.$message({
                    type: 'success',
                    message: res.msg || '操作成功'
                  })
                  this._loadData(false);
                }else{
                  this.loadingSubmitChange = false
                  this.$message({
                    type: 'warning',
                    message: res.msg
                  })
                }
              }).catch((err) => {
                console.log(err);
                this.loadingSubmitChange = false
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
          })
        })
      },

      handleSelectInfo(item){
        // 点击坐栋输入建议项赋值
        console.log(item)
        // this.queryForm.vagueData = {
        //   type: item.type,
        //   value: item.value,
        // }
        this.queryForm.vagueType = item.type;
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

         let params = Object.assign({}, this.nowQueryForm, {
          limit: this.listQuery.limit,
          page: this.listQuery.page
        })
        //const params = this._getParams(this.queryForm)
        RequestURL.getPersonnelExamine(params).then(res => {
          this.tableData = res.data.map(item => {
             if(item.status===0){
               item.statusText = '待审核'
             }else if(item.status===1){
               item.statusText = '同意'
             }else{
                item.statusText = '不同意'
             }
             if(item.sex==='1'){
               item.sex = '女'
             }else{
               item.sex = '男'
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
        this.$utils.setFormInfo(this.addForm, this.currentRowData, () => {


          if(this.currentRowData.sex==='0'){
            this.currentRowData.sex = '男'
          }
          if(this.currentRowData.sex==='1'){
            this.currentRowData.sex = '女'
          }
          //alert(this.currentRowData['cover']);
          this.addForm['id'] = this.currentRowData['id']

          this.imageUrl = ""
        })

        // 移除表单回填时 element ui自动添加的校验效果
        setTimeout(() => {
          this.$refs['addForm'].clearValidate()
        }, 100)
      },
      _setChangeForm(){

        this.$utils.setFormInfo(this.changeForm, this.currentRowData, () => {
          //alert(this.currentRowData['cover']);
          this.changeForm['id'] = this.currentRowData['id']

          this.imageUrl = ""
        })

        // 移除表单回填时 element ui自动添加的校验效果
        setTimeout(() => {
          this.$refs['changeForm'].clearValidate()
        }, 100)
      },
      /**
       * 发送消息统一接口
       * @private
       * @param params
       */
      _sendSysMsg(params){
        RequestURL.sendSysMsg({message:JSON.stringify(params)}).then(res =>{
          console.log(res);
        })
      }
    },
    mounted () {

       //职务选择
      queryPositionSelect({}).then(res => {
        this.selectPost = res
      }),
      //部门选择
      queryOpenCityCompanyDepart({}).then(res => {
        this.deptSelectOpts = res
      }),
      //人员选择
      queryReferenceUserSelect({}).then(res => {
        this.userSelectOpts = res
      }),
      //变动类型
      getSelectValue({param: 'postChangeType'}).then(res => {
         // console.log(res.data)
          this.selectPostChangeType = res.data
        })
        //变动类型
      getSelectValue({param: 'recruitWay'}).then(res => {
         // console.log(res.data)
          this.selectRecruitWay = res.data
        })

    }
  }
</script>

<style scoped lang="scss">
  .p-title{
    margin-bottom: 16px;
    margin-top: 12px;
  }
</style>
