<!-- 经纪人反馈 -->
<template>
  <div>
    <div>
      <p>带看房源<span style="color:red;margin-left:20px;margin-right:10px;" @click="openDialogVisibleSelectHouse">选择二手房源</span><span style="color:red;">选择一手房源</span></p>
      <div style="margin-top:10px;">
       <div style="display:inline-block;margin-right:20px;">
          <el-upload
            class="avatar-uploader"
            :action="uploadPath"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
            :file-list="fileList">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon">上传</i>
          </el-upload>
       </div>
       <div style="display:inline-block">
         <el-switch
          v-model="repeatLook"
          active-color="#13ce66"
          inactive-color="#eee" @change="switchChange">
        </el-switch>复看
       </div>
        
      </div>
    </div>
    <div style="">
      <el-table
        :data="tableData"
        v-loading="loadingView"
        :height="200"
      >

        <el-table-column
          prop="houseId"
          label="房源编号"
          width="120">
        </el-table-column>

        <el-table-column
          prop="communityName"
          label="小区">
        </el-table-column>

        <el-table-column
          prop="buildingsName"
          label="坐栋">
        </el-table-column>

        <el-table-column
          prop="buildingHouseName"
          label="门牌号">
        </el-table-column>

        <el-table-column
          prop="buildSpace"
          label="面积">
        </el-table-column>

         <el-table-column
          prop="price"
          :label="priceTitle">
        </el-table-column>

      </el-table>

    </div>
    <div style="margin-top:20px;">
      <el-input type="textarea" v-model="content" placeholder="客户评价"></el-input>
    </div>
    <div style="margin-top:20px;text-align:center;">
      <el-button type="success" @click="saveLookHouse" :loading="loadingSubmitBtn">确定</el-button>
      <el-button @click="handleCancel">取消</el-button>
    </div>
     <template>
        <!-- 选择房源 -->
        <el-dialog
          title="选择二手房源"
          :visible.sync="dialogVisibleSelectHouse"
          width="800px"
          :close-on-click-modal="false"
          :close-on-press-escape="false"
          append-to-body
        >
        <select-er-house @handleClickRow="handleErClickRow" :transactionTypeId="topParams.transactionTypeId" :regionValue="regionValue" :deptSelectOpts="deptSelectOpts" ref="selectErHouseDialog"> </select-er-house>
        </el-dialog>
    </template>

  </div>

</template>

<script>
  import {customerFindHouseList,saveLookhouse} from '@/request/customer/customerUsed'
  import {LOOKHOUSEIMG_UPLOAD_URL} from '@/request/main' // 上传带看图片
  import SelectErHouse from './SelectErHouse' // 选择房源
  export default {
    components: {SelectErHouse},
     props: {
      
      deptSelectOpts: {
        type: Array,
        default () {
          return []
        }
      },
       regionValue: {
        type: Object,
        required: true,
        default () {
          return {}
        }
      },
      topParams: {
        type: Object,
        required: true,
        default () {
          return {}
        }
      }
    },
    data () {
      return {
       
        tableData: [],
        tableHouseData: [],
        queryForm: {},
        listQuery: {
          page: 1,
          limit: 10,
          total: 0
        },
        dialogVisibleSelectHouse: false,
        loadingView: false,
        loadingHouseView: false,
        loadingQueryBtn: false,
        loadingSubmitBtn: false,
        repeatLook: false,
        priceTitle: '售价',
        content: '',
        imageUrl: '',
        uploadPath: LOOKHOUSEIMG_UPLOAD_URL,
        showImg: false,
        fileList: [],
        filePath: '',
      }
    },

    methods: {

      handleErClickRow (row) {
        console.log(row)
        this.tableData=[]
        this.tableData.push(row)
        this.dialogVisibleSelectHouse = false
      },
      
      openDialogVisibleSelectHouse(){
        this.dialogVisibleSelectHouse = true;
        this.$nextTick(() => {
          this.$refs['selectErHouseDialog'] && this.$refs['selectErHouseDialog']._getTableData()
        })
      },
      

      // 格式化日期
      _timeFormat (row, column, cellValue) {
        return this.$utils.timeFormat(cellValue)
      },
      handleQuery(){

      },
      _resetForm(){

      },
      selectHouse(row, event, column){
        this.tableData = []
        this.tableData.push(row)
        this.dialogVisibleSelectHouse = false
      },
      handleChangeRegion(){

      },
      handleChangeDept(){

      },
      switchChange(){
        //this.$alert(this.repeatLook)
      },
      handleAvatarSuccess(res, file) {
        console.log(res);
        console.log(file)
        this.imageUrl = URL.createObjectURL(file.raw);
        this.filePath = res.data.filePath;
        let fileItem = {
          name: res.data.fileName,
          url: res.data.filePath
        }
        this.fileList.push(fileItem);
      },
      beforeAvatarUpload(file) {
        
      },
      saveLookHouse(){
        this.$confirm('确定保存编辑的信息吗?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
               this.loadingSubmitBtn = true
               let params = {}
               let demandId = this.$route.query.demandId
               let sourceId = this.tableData[0].id
               params.houseType = 0
               params.sourceId = sourceId
               params.demandId = demandId
               params.content = this.content
               this.filePath = "111111"
               if(this.filePath!=""){
                 params.filePath = this.filePath
                 params.attachType = '01'
               }
               
               params.transactionTypeId=this.topParams.transactionTypeId
               params.transactionType=this.topParams.transactionType
               params.custName = this.topParams.custName
               params.custCallType = this.topParams.custCallType
               if(this.repeatLook==true){
                 params.isRepeat = 1
               }
               let cfg = {
                headers: {
                  'Content-Type': 'application/json;charset=UTF-8;'
                }
              }
              saveLookhouse(params, cfg).then(res => {
                this.loadingSubmitBtn = false
                this.$message({
                  type: 'success',
                  message: res.msg
                })
                this.$emit('submit', null)
              }).catch(() => {
                this.loadingSubmitBtn = false
              })
            })
      },
      handleCancel(){
        this.$emit('submit', null)
      }
    },
     mounted () {
       console.log(this.topParams)
     }
  }
</script>

<style scoped lang="scss">
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
    font-size: 14px;
    color: #8c939d;
    width: 100px;
    height: 100px;
    line-height: 100px;
    text-align: center;
    border:1px solid #eee;
  }
  .avatar {
    width: 100px;
    height: 100px;
    display: block;
  }
</style>
