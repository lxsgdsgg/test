<!-- 经纪人反馈 -->
<template>
  <div style="height: 510px;overflow: auto">
    <div>
      <p>带看房源
        <el-button type="primary" v-show="lookTwo" @click="openDialogVisibleSelectHouse" size="mini">选择二手房源</el-button>
        <el-button type="primary" style="margin-left:-1px;" v-show="lookOne" @click="openDialogVisibleSelectNewHouse" size="mini">选择一手楼盘</el-button>
        <el-switch
          v-model="isErLookBtn"
          active-text="二看"
          style="margin-left:20px;"
          @change="handelErLook"
         >
        </el-switch>
        <el-switch
          v-model="isRepeatBtn"
          active-text="复看"
          @change="handelRepeat"
          >
        </el-switch>
      </p>
      <hr>
      <div style="margin-top:10px;">
       <div style="display:inline-block;margin-right:20px;">
          <el-upload
            ref="upload"
            class="avatar-uploader"
            list-type="picture-card"
            :action="uploadPath"
            :on-remove="handleRemove"
            :on-preview="handlePictureCardPreview"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <i class="el-icon-plus"></i>
          </el-upload>
       </div>
       <!--<div style="display:inline-block;margin-right:20px;">-->
          <!--<el-upload-->
            <!--class="avatar-uploader"-->
            <!--:action="uploadPath"-->
            <!--:on-success="handleAvatarSuccess"-->
            <!--:before-upload="beforeAvatarUpload"-->
            <!--:show-file-list="false">-->
            <!--<img v-if="imageUrl" :src="imageUrl" class="avatar">-->
            <!--<i v-else class="el-icon-plus avatar-uploader-icon">上传</i>-->
          <!--</el-upload>-->
       <!--</div>-->
       <!--<div style="display:inline-block" v-for="(item, index) in imgPaths" v-show="showImg">-->
         <!--<img :src="item" style="margin-left:10px;width:100px;height:80px;" @click="openShowImg(item)">-->
       <!--</div>-->

      </div>
    </div>
    <hr>
    <div style="">
      <el-table
        :data="tableData" border size="mini"
        v-loading="loadingView"
        v-show="erTable"
      >

        <el-table-column
          prop="transactionType"
          label="类型"
          width="120px">
        </el-table-column>

        <el-table-column
          prop="houseId"
          label="房源编号"
          width="120">
        </el-table-column>

        <el-table-column
          prop="communityName"
          label="小区"
          width="120"
        >
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

        <el-table-column
          align="center"
          label="操作"
        >
          <template slot-scope="scope">
            <el-button @click="handleDelete(scope.row)" type="text" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-table
        :data="tableData" border size="mini"
        v-loading="loadingView"
        v-show="newTable"
      >
        
        <el-table-column
          prop="name"
          label="标题"
          width="180px">
        </el-table-column>

        <el-table-column
          prop="areaAndRegion"
          label="区域">
        </el-table-column>

        <el-table-column
          prop="salePrice"
          label="均价(元/平米)">
        </el-table-column>

        <el-table-column
          prop="custServicer"
          label="维护人">
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
        >
          <template slot-scope="scope">
            <el-button @click="handleDelete(scope.row)" type="text" size="small">删除</el-button>
          </template>
        </el-table-column>

      </el-table>

    </div>
    <div style="margin-top:20px;">
      <el-form  :model="lookForm" status-icon :rules="lookFormRules" ref="lookForm">
        <el-form-item label="带看内容" prop="content">
             <el-input type="textarea" v-model="lookForm.content" placeholder=""></el-input>
        </el-form-item>
     
      </el-form>
    </div>
    <div style="margin-top:20px;text-align:center;">
      <el-button type="primary" @click="saveLookHouse" :loading="loadingSubmitBtn">确定</el-button>
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
        <select-er-house @handleClickRow="handleErClickRow" @cancelSelect="cancelSelect" :transactionTypeId="topParams.transactionTypeId" :regionValue="regionValue" :deptSelectOpts="deptSelectOpts" ref="selectErHouseDialog"> </select-er-house>
        </el-dialog>
        <el-dialog
          title="选择一手楼盘"
          :visible.sync="dialogVisibleSelectNewHouse"
          width="800px"
          :close-on-click-modal="false"
          :close-on-press-escape="false"
          append-to-body
        >
        <select-new-house @handleClickRow="handleNewClickRow" @cancelSelect="cancelNewSelect" :transactionTypeId="topParams.transactionTypeId" :regionValue="regionValue" :deptSelectOpts="deptSelectOpts" ref="selectNewHouseDialog"> </select-new-house>
        </el-dialog>
         <!-- 预览大图 -->
        <el-dialog
          title="图片"
          :visible.sync="dialogVisibleShowImg"
          width="700px"
          :close-on-click-modal="false"
          :close-on-press-escape="false"
          append-to-body
        >
          <img :src="cPath">
        </el-dialog>

       <el-dialog :visible.sync="dialogVisible"
          :close-on-click-modal="false"
          :close-on-press-escape="false" append-to-body>
         <img width="100%" :src="dialogImageUrl">
       </el-dialog>
    </template>

  </div>

</template>

<script>
  import {customerFindHouseList,saveLookhouse,deleteImg} from '@/request/customer/customerUsed'
  import {LOOKHOUSEIMG_UPLOAD_URL} from '@/request/main' // 上传带看图片
  import SelectErHouse from './WriteLookSelectErHouse' // 选择二手房源
  import SelectNewHouse from './WriteLookSelectNewHouse' // 选择一手楼盘
  import {customerAddLog, customerUpdateLog, customerQueryLog,customerDelLog} from '@/request/log/customerLog'
  export default {
    components: {SelectErHouse,SelectNewHouse},
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
        lookForm: {
          content: ''
        },
        tableData: [],
        tableHouseData: [],
        queryForm: {},
        listQuery: {
          page: 1,
          limit: 10,
          total: 0
        },
        dialogVisibleSelectHouse: false,
        dialogVisibleSelectNewHouse: false,
        dialogVisibleShowImg: false,
        loadingView: false,
        loadingHouseView: false,
        loadingQueryBtn: false,
        loadingSubmitBtn: false,
        repeatLook: false,
        priceTitle: '售价(万元)',
        content: '',
        imageUrl: '',
        uploadPath: LOOKHOUSEIMG_UPLOAD_URL+'?demandId='+this.topParams.demandId,
        showImg: true,
        fileList: [],
        filePath: '',
        radio: '',
        imgPaths: [],
        cPath: '',
        isErLookBtn: false,
        isRepeatBtn: false,
        houseType: 0,
        erTable: false,
        newTable: false,
        // 表单校验配置
        lookFormRules: {
          content: [
            {required: true, message: '必须要填写', trigger: 'blur'}
          ],
        },
        lookTwo: true,
        lookOne: true,
        dialogImageUrl: '',
        dialogVisible: false
      }
    },

    methods: {
      handleErClickRow (houseData) {
        //this.tableData=[]
        //this.tableData.push(row)
        this.erTable = true
        this.newTable = false
        if(houseData instanceof Object && !houseData.length){
            this.tableData=[]
            this.tableData.push(houseData)
        }else if(houseData instanceof Array && houseData.length){
            this.tableData = houseData
        }

        this.lookOne = false
        this.lookTwo = true
        this.dialogVisibleSelectHouse = false

      },

      openDialogVisibleSelectHouse(){
        this.houseType = 0
        this.dialogVisibleSelectHouse = true;
        this.$nextTick(() => {
          this.$refs['selectErHouseDialog'] && this.$refs['selectErHouseDialog']._getTableData()
        })
      },

      handleNewClickRow (houseData) {
        this.erTable = false
        this.newTable = true
        if(houseData instanceof Object && !houseData.length){
            this.tableData = []
            this.tableData.push(houseData)
        }else if(houseData instanceof Array && houseData.length){
            this.tableData = houseData
        }

        this.lookTwo = false
        this.lookOne = true
        this.dialogVisibleSelectNewHouse = false
      },

      openDialogVisibleSelectNewHouse(){
        this.houseType=1
        this.dialogVisibleSelectNewHouse = true;
        this.$nextTick(() => {
          this.$refs['selectNewHouseDialog'] && this.$refs['selectNewHouseDialog']._getTableData()
        })
      },

      openShowImg(path){
        this.dialogVisibleShowImg = true;
        this.cPath = path;
      },

      // 格式化日期
      _timeFormat (row, column, cellValue) {
        return this.$utils.timeFormat(cellValue)
      },

      handleQuery(){

      },

      _resetForm(){
        this.tableData = []
        this.imgPaths = []
        this.fileList = []
        this.isErLookBtn = false
        this.isRepeatBtn = false
        this.lookForm.content = ''
        this.lookTwo = true
        if(this.topParams.transactionTypeId===4){
          this.priceTitle = '售价(万元)'
          this.lookOne = true
        }else{
          this.priceTitle = '租价(元)'
          this.lookOne = false
        }
      },

      selectHouse(row, event, column){
        this.tableData = []
        this.tableData.push(row)
        this.dialogVisibleSelectHouse = false
      },
      cancelSelect(){
        this.dialogVisibleSelectHouse = false
      },
      cancelNewSelect(){
        this.dialogVisibleSelectNewHouse = false
      },
      handleChangeRegion(){

      },
      handleChangeDept(){

      },
      handelRepeat(){
        if(this.isErLookBtn){
          this.isRepeatBtn = false
        }
      },
      handelErLook(){
        if(this.isRepeatBtn){
          this.isErLookBtn = false
        }
      },
      handleAvatarSuccess(res, file) {
        this.imgPaths.push(res.data.filePath)
        let fileItem = {
          name: res.data.fileName,
          url: res.data.filePath,
          fileName: file.name
        }
        this.fileList.push(fileItem)
      },

      beforeAvatarUpload(file) {

      },

      // 删除图片
      handleRemove(file, fileList){
        let path = this.fileList.find(item=>file.name === item.fileName).url
        let param = {demandId: this.topParams.demandId,path: path}
        deleteImg(param).then(res=>{
          console.log(res)
        })
      },

      // 预览图片
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      },

      saveLookHouse(){
        this.$refs['lookForm'].validate((valid) => {
          if (valid) {
            this.submitBusinessData()
          }
        })
      },

      submitBusinessData(){
        if(this.tableData.length<=0){
          this.$message({
            type: 'warning',
            message: '请选择房源'
          })
        }else{
          let params = {}
          let demandId = this.topParams.demandId
          if(this.isErLookBtn===true){
            params.isErLook = 1
          }else{
            params.isErLook = 0
          }
          if(this.isRepeatBtn===true){
            params.isRepeat = 1
          }else{
            params.isRepeat = 0
          }

          this.$confirm('确定保存编辑的信息吗?', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {

            this.loadingSubmitBtn = true

            let sourceIdArr = [];
            this.tableData.forEach((item,index) => {
              let house = {
                id: item.id,
                houseId: item.houseId
              }
              sourceIdArr.push(house)

            })
            params.houseType = this.houseType
            params.sourceId = sourceIdArr
            params.demandId = demandId
            params.content = this.content
            if(this.imgPaths.length>0){
              params.filePath = this.imgPaths
              params.attachType = '01'
            }

            params.transactionTypeId=this.topParams.transactionTypeId
            params.transactionType=this.topParams.transactionType
            params.custName = this.topParams.custName
            params.custCallType = this.topParams.custCallType

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
              //记日志
              let logContent = ''
              let operatTypeId = ''
              if(this.houseType===0){
                logContent = '二手房带看，带看内容：'+this.content
                operatTypeId = 219
              }else if(this.houseType===1){
                logContent = '一手楼盘带看，带看内容：'+this.content
                operatTypeId = 220
              }
              let newHouseId = ''
              let newHouseCode = ''
              sourceIdArr.forEach(item=>{
                if(this.houseType===0){
                  newHouseId = item.id
                  newHouseCode = item.houseId
                }else if(this.houseType===1){
                  newHouseId = item.id
                  newHouseCode = item.id
                }
                let message = {
                  sourceId: this.topParams.demandId,
                  sourceCode: this.topParams.demandId,
                  businessTypeId: 2,//业务类型，2：代表客源
                  sourceTypeId: this.topParams.transactionTypeId,
                  newHouseId: newHouseId,
                  newHouseCode: newHouseCode,
                  operatTypeId: operatTypeId,
                  logContent: logContent,
                  path:JSON.stringify(this.imgPaths),
                  remark: this.$route.query.formName
                }
                customerAddLog({message: JSON.stringify(message)}).then(res => {
                  console.log(res)
                })
              })

              this.loadingSubmitBtn = false
            })
          })
        }
      },

      handleCancel(){
        this.$emit('submit', null)
      },
      handleDelete(row){
        for(let i=0;i<this.tableData.length;i++){
          if(this.tableData[i].id==row.id){
            this.tableData.splice(i,1)
          }
        }
      }
    },
    mounted() {
      if (this.topParams.transactionTypeId === 4) {
        this.priceTitle = '售价(万元)'
        this.lookOne = true
      } else {
        this.priceTitle = '租价(元)'
        this.lookOne = false
      }
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
    width: 80px;
    height: 80px;
    line-height: 80px;
    text-align: center;
    border:1px solid #eee;
  }
  .avatar {
    width: 80px;
    height: 80px;
    display: block;
  }
</style>
