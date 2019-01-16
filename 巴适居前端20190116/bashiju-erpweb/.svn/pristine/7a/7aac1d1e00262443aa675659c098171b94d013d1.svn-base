<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div>
        <el-form size="small" :inline="true" :model="form" ref="form"  class="demo-form-inline">

          <el-form-item prop="name">
            <el-input v-model.trim="form.name" placeholder="姓名"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click.native.prevent="_loadData(true)" :loading="queryBtnLoading">查询</el-button>
            <el-button @click.native.prevent="resetForm">清空</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <div class="page-content-bd" v-loading="loadingView">
      <el-table
        :data="tableData"
        border
        tooltip-effect="light"
        align="center"
        style="width: 100%"
      >
        <el-table-column
          prop="name"
          align="left"
          label="姓名"
          width="100"
        >
        </el-table-column>

        <el-table-column
          show-overflow-tooltip
          prop="sex"
          align="left"
          label="性别"
          width="80"
          >
          <template slot-scope="scope">
          {{scope.row.sex | sexFilter }}
          </template>
        </el-table-column>

        <el-table-column
          prop="birthdate"
          align="left"
          :formatter="dateFormat"
          width="150"
          label="出生日期">
        </el-table-column>

        <el-table-column
          prop="email"
          align="left"
          label="邮箱">
        </el-table-column>

        <el-table-column
          prop="mobile"
          align="left"
          label="电话">
        </el-table-column>

        <el-table-column
          prop="nationalities"
          align="left"
          label="民族"
          width="100"
          :formatter="nationFormatter"
        >
        </el-table-column>

        <el-table-column
          prop="politicalOrientation"
          align="left"
          label="政治面貌"
          width="100"
          :formatter="politicalFormatter"
        >
        </el-table-column>

        <el-table-column
          show-overflow-tooltip
          prop="householdRegister"
          align="left"
          label="户籍所在地">

        </el-table-column>

        <el-table-column
          prop="workDate"
          align="left"
          :formatter="dateFormat"
          label="参加工作时间">

        </el-table-column>

        <el-table-column
          show-overflow-tooltip
          prop="address"
          align="left"
          label="家庭住址">
        </el-table-column>



        <el-table-column
          prop="remark"
          align="left"
          label="备注">
        </el-table-column>


        <el-table-column
          align="center"
          label="操作"
        >
          <template slot-scope="scope">
            <el-button  v-hasMultipleBtn="['downloadBtn',scope.row]" @click="downloadImgItem(scope.row)" v-if="scope.row.attachment"  size="mini" >下载附件</el-button>
          </template>

        </el-table-column>

      </el-table>
      <!--分页控件-->
      <b-pagination
        :listQuery="listQuery"
        @handleSizeChange="handleSizeChange"
        @handleCurrentChange="handleCurrentChange">
      </b-pagination>
    </div>

  </div>

</template>
<script>
  import {queryCurriculumVitaeData} from "@/request/office/curriculumVitae";
  import  pageList from '@/mixins/pageList'
  import {dropDown} from "@/request/office/curriculumVitae";
  import  downLoadMixin from  './component/downLoadMixin'
  export default {
    components:{pageList},
    mixins: [pageList,downLoadMixin],
    nameL:'curriculumVitae',
    data() {
      return {
        form:{
        },
        nationOpt:[],
        politicalOrientationOpt:[],
        tableData:[],
        dealTypeOpt:[],
        CommissionStatusOpt:[],
        timeTypeOpt:[
          {id:0,fieldValue:'收取时间'},
          {id:1,fieldValue:'计划时间'}
        ],
        statusOpt:[
          {id:0,fieldValue:'未处理'},
          {id:1,fieldValue:'已处理'},
          {id:2,fieldValue:'已取消'}
        ],
        isValidOpt:[
          {id:0,fieldValue:'无效'},
          {id:1,fieldValue:'有效'},
        ],
        getReferenceUserSelectUrl: 'manage/usermanage/queryReferenInfoToCreateTree', // 介绍人
        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        loadingView:false,
        queryBtnLoading:false,
        editorDialog:false,
        cancelDialog:false,
        isAdd:false,
        listQuery:{
          limit:10,
          page:1,
          total:0
        }
      }
    },
    methods: {
      //过滤数据
      nationFormatter (row, column, cellValue) {
        for(let i =0;i<this.nationOpt.length;i++){
            if(this.nationOpt[i].value==cellValue) return  this.nationOpt[i].name
        }
      },
      politicalFormatter(row, column, cellValue){
        for(let i =0;i<this.politicalOrientationOpt.length;i++){
          if(this.politicalOrientationOpt[i].value==cellValue) return  this.politicalOrientationOpt[i].name
        }
      },
      _loadData(queryBtn){
        this._queryCurriculumVitaeData(queryBtn)
      },
      //查询数据
      _queryCurriculumVitaeData(queryBtn){
        if(queryBtn){
          this.queryBtnLoading = queryBtn
          this.listQuery.page = 1
          this.listQuery.currentPage = 1
        }
        let params = Object.assign({},this.form,{
          limit:this.listQuery.limit,
          page:this.listQuery.page
        })
        this.loadingView = true

        queryCurriculumVitaeData(params).then(res =>{
          this.tableData = res.data
          this.listQuery.total = res.count
          this.loadingView = false
          this.queryBtnLoading = false
        }).catch(() =>{
          this.loadingView = false
          this.queryBtnLoading = false
        })
      },
      // 时间格式转化
      dateFormat(row,column,cellValue){
        return this.$utils.timeFormat(cellValue, '{y}-{m}-{d}')
      },
      timeFormat(row,column,cellValue){
        return this.$utils.timeFormat(cellValue)
      },
      resetForm(){
        this.$refs['form'].resetFields()
      },
      dropDown(){
        dropDown().then(res =>{
            this.nationOpt = res.nation
            this.politicalOrientationOpt = res.politicalOrientation
        })
      },
      //編輯
      handleEdit(row){
        this.hackReset = false
        this.$nextTick(() =>{
          this.hackReset = true
          this.editDialogVisible = true
          this.isAdd = false
          this.currentRowData = row
        })
      },
      //下载附录
      handleDownLoad(){

      }
    },
    filters:{
      eductionFilter(value ){
        if(value == 0)return '高中及以上'
        if(value == 1)return '大专及以上'
        if(value == 2)return '本科及以上'
        if(value == 3)return '硕士及以上'
        if(value == 4)return '博士及以上'
      },
      sexFilter(value){
        if(value == 0)return '男'
        if(value == 1)return '女'
      }
    },
    mounted(){
      this.dropDown()
    }
  }
</script>

<style scoped>

</style>
