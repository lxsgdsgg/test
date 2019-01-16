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
          prop="isSetTop"
          align="center"
          label="是否置顶">
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
            <el-button v-hasMultipleBtn="['articleExamineShowBtn',scope.row]"  @click="handleShow(scope.row)" type="text" size="small" v-loading="loadingShowBtn">查看</el-button>
            <el-button v-hasMultipleBtn="['articleExamineSureBtn',scope.row]" v-if="scope.row.examineStatus==0" @click="handleSure(scope.row)" type="text" size="small" v-loading="loadingSureBtn">确认</el-button>
            <el-button v-hasMultipleBtn="['articleExamineRejectBtn',scope.row]" v-if="scope.row.examineStatus==0" @click="handleReject(scope.row)" type="text" size="small" v-loading="loadingRejectBtn">驳回</el-button>
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
      <b-dialog :show.sync="dialogShowContent" title="文章内容" width="760px">
        <el-form v-loading="loadingForm"  label-width="100px">
          <!--<base-editor :content="editorVal" ref="editor"></base-editor>-->
          <p class="p-title">封面：</p>
          <img :src="articleImg" style="width:200px;height:200px;" @click="openShowImg(articleImg)">
          <p class="p-title" >摘要：</p>
          <div>{{articleAbstracts}}</div>
          <p class="p-title">内容：</p>
          <div v-html="articleContent">{{articleContent}}</div>
        </el-form>
      </b-dialog>
      <b-dialog :show.sync="dialogReject" title="驳回原因" width="400px" height="400px">
        <el-form v-loading="loadingForm"  label-width="100px">
          <el-form-item label="驳回原因" >
            <el-input v-model="rejectReason" placeholder="请输入名称"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="rejectArticle" :loading="rejectArticleBtn">确定</el-button>
          </el-form-item>
        </el-form>
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

<script>
  import PageList from '@/mixins/pageList' // 列表页面查询 mixin
  import * as RequestURL from '@/request/manage/contentManage' // 请求后端URL路径
  import BaseCityCascader from '@/components/BaseCascader/city' // 城市级联组件
  import BaseEditor from '@/components/BaseEditor'
  import {systemAddLog, systemUpdateLog, systemQueryLog,systemDelLog} from '@/request/log/systemPlatformLog'

  export default {
    name: 'contentexamine',
    mixins: [PageList],
    components: {BaseEditor,BaseCityCascader},
    data () {
      return {
        queryForm: {},
        selectStatus: [],
        dialogShowContent: false,
        dialogVisibleShowImg: false,
        dialogReject: false,
        loadingShowBtn: false,
        loadingSureBtn: false,
        loadingRejectBtn: false,
        rejectArticleBtn: false,
        loadingForm: false,
        loadingItemView: false,
        currentRowData: null, // 当前操作的行数据
        currentDynamicConfigureItem:null,
        editorVal: '',
        rejectReason: '',
        idForReject: '',
        articleContent: '',
        nowQueryForm: {},
        articleAbstracts: '',
        articleImg: '',
        classesName: '',
        articleTitle: '',
        row:''
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
      openShowImg(path){
        this.dialogVisibleShowImg = true;
        this.cPath = path;
      },
      loadExamineStatus(){
        const params = {};
        RequestURL.getEncyclopediasBaseInfo(params).then(res => {
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
      // 确认
      handleSure (row) {
        this.$confirm('真的通过吗？, 是否继续?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.loadingSureBtn = true
          const params = {
            id: row.id
          }
          RequestURL.sureArticle(params).then(res => {
            this.loadingSureBtn = false
            if (res.success) {
              this.$message({
                type: 'success',
                message: res.msg || '操作成功'
              })
              let paramMsg ={
                reSessionId:row.operatorId
                ,reSessionUuid: row.uuid
                ,reSessionName: row.operator,
                msgContent : `审核通过《${row.title}》`
              }
              this._sendSysMsg(paramMsg);
              this._loadData(false);

              //写日志
              let logContent = "类型："+row.typeName +";标题："+row.title+";审核通过"
              let message = {
                  sourceCode: '文章内容审核',
                  sourceTypeId: 27,
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
            this.loadingSureBtn = false
            this.$message({
              type: 'info',
              message: err.msg || '操作失败'
            })
          })
        })
      },
      //查看
      handleShow(row){
        this.dialogShowContent = true;
        //alert(row.content)
        //this.editorVal = row.content;
        this.articleContent = row.content;
        this.articleAbstracts = row.abstracts;
        this.articleImg= row.cover
      },
      // 驳回
      handleReject (row) {
        this.dialogReject = true;
        this.idForReject = row.id;
        this.classesName = row.typeName;
        this.articleTitle = row.title;
        this.row = row
      },
      rejectArticle(){
        this.$confirm('要驳回吗？, 是否继续?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.rejectArticleBtn = true;
          const params = {
            id: this.idForReject,
            reason: this.rejectReason
          }
          RequestURL.rejectArticle(params).then(res => {
            this.rejectArticleBtn = false
            this.dialogReject = false;
            if (res.success) {
              this.$message({
                type: 'success',
                message: res.msg || '操作成功'
              })
              let paramMsg ={
                reSessionId:this.row.operatorId
                ,reSessionUuid: this.row.uuid
                ,reSessionName: this.row.operator,
                msgContent : `审核不通过《${this.rejectReason}》`
              }
              this._sendSysMsg(paramMsg);
              this._loadData(false);
              //写日志
              let logContent = "类型："+this.classesName +";标题："+this.articleTitle+";驳回"
              let message = {
                  sourceCode: '文章内容审核',
                  sourceTypeId: 27,
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
            this.rejectArticleBtn = false
            this.dialogReject = false;
            this.$message({
              type: 'info',
              message: err.msg || '操作失败'
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
        let params = Object.assign({}, this.nowQueryForm, {
          limit: this.listQuery.limit,
          page: this.listQuery.page
        })
        //const params = this._getParams(this.queryForm)
        RequestURL.getExamineList(params).then(res => {
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
                item.isSetTop = '否'
              }else{
                item.isSetTop = '是'
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
      this.loadExamineStatus();

    }
  }
</script>

<style scoped lang="scss">
  .p-title{
    margin-bottom: 16px;
    margin-top: 12px;
  }
</style>
