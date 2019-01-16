<template>
  <div class="page-content">
    <div class="page-content-hd">
      <el-form :model="queryForm" ref="queryForm" :inline="true" size="small">
        <el-form-item label="所属城市" prop="rgCode">
          <city-selector v-model="queryForm.rgCode" @initSelectData="initSelectData"></city-selector>
        </el-form-item>

        <el-form-item label="学校名称" prop="schoolName">
          <el-input v-model="queryForm.schoolName"></el-input>
        </el-form-item>

        <el-form-item label="学校地址" prop="schoolAddress">
          <el-input v-model="queryForm.schoolAddress"></el-input>
        </el-form-item>

        <el-form-item>
          <div>
            <el-button type="primary" @click="handlerQuery(true)" :loading="queryLoadingBtn">查询</el-button>
            <el-button @click="resetForm('queryForm')">清空条件</el-button>
            <el-button v-hasOnlyBtn="'schoolAdd'" type="primary" @click="addSchoolHandler('1')" :loading="saveLoadingBtn">新增学校</el-button>
          </div>
        </el-form-item>

      </el-form>
    </div>

    <el-row :gutter="20">
      <el-col :span="14">
        <div class="page-content-hd" v-loading="loadingView">
          <el-table :data="schoolData" @row-click="queryCommunityData" tooltip-effect="light"
              border align="center" style="width: 100%" size="small" stripe highlight-current-row>
            <el-table-column prop="schoolName" label="学校名称" align="left" width="180px" show-overflow-tooltip></el-table-column>

            <el-table-column prop="schoolAddress" label="学校地址" width="230px" align="left" show-overflow-tooltip></el-table-column>

            <el-table-column prop="cityName" label="所属城市" align="left" show-overflow-tooltip></el-table-column>

            <el-table-column prop="middleSchoolName" label="对口学校" align="left" width="120px" show-overflow-tooltip></el-table-column>

            <el-table-column align="center" label="操作" width="180px">
              <template slot-scope="scope">
                <el-button v-hasOnlyBtn="'communityAdd'" @click="addHandler('2',scope.row)" type="text" size="small">新增周边小区</el-button>
                <el-button v-hasOnlyBtn="'schoolEdit'" @click="editSchoolHandler(scope.row,'1')" type="text" size="small">编辑</el-button>
                <el-button v-hasOnlyBtn="'schoolDel'" type="text" size="small" @click="deletSchoolData(scope.row)">删除</el-button>
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
      </el-col>

      <el-col :span="10">
        <div class="page-content-hd" v-loading="loadingCommView">
          <el-table :data="communityData" border align="center" style="width: 100%" size="small">
            <el-table-column prop="communityName" label="小区名称" align="left"></el-table-column>

            <el-table-column prop="communityRemark" label="备注说明" align="left"></el-table-column>

            <el-table-column align="center" label="操作">
              <template slot-scope="scope">
                <el-button v-hasOnlyBtn="'communityEdit'" @click="editHandler(scope.row,'2')" type="text" size="small">编辑</el-button>
                <el-button v-hasOnlyBtn="'communityDel'" @click="deletCommunityData(scope.row)" type="text" size="small">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <b-pagination
            :listQuery="communityListQuery"
            @handleSizeChange="handleSizeChange"
            @handleCurrentChange="handleCurrentChange">
          </b-pagination>
        </div>
      </el-col>
    </el-row>
    <!--编辑数据弹框-->
    <el-dialog
      title="周边小区编辑"
      :visible.sync="editDlgVisible"
      :close-on-click-modal="false"
      top="30vh"
      width="400px"
    >

      <el-form :model="editForm" ref="editForm" label-width="100px" size="small" :rules="ruleCommunity">
        <el-form-item label="选择小区" prop="communityId">
          <community-selector
            v-model="editForm.communityId"
            :url="communityUrl"
            :props="cascaderProps"
            :dataProps="cascaderDataProps" :changeOnSelect="false" @change="getName">
          </community-selector>
        </el-form-item>
        <el-form-item label="小区备注" prop="communityRemark">
          <el-input v-model="editForm.communityRemark"></el-input>
        </el-form-item>

        <div class="btn-group">
          <el-button type="primary" @click="saveOrUpdateCommunityData" size="small" :loading="saveLodingBtn">保存</el-button>
          <el-button type="primary" @click="handlerCancel" size="small">取消</el-button>
        </div>
      </el-form>
    </el-dialog>

    <!--编辑数据弹框-->
    <el-dialog
      title="编辑学校"
      :visible.sync="editSchoolDlgVisible"
      :close-on-click-modal="false"
      top="30vh"
      width="400px">

      <el-form :model="editSchoolForm" ref="editSchoolForm" :rules="ruleSchool" label-width="100px">
        <el-form-item label="所属城市" prop="rgCode">
          <city-selector v-model="editSchoolForm.rgCode"></city-selector>
        </el-form-item>
        <el-form-item label="学校名称" prop="schoolName">
          <el-input v-model="editSchoolForm.schoolName"></el-input>
        </el-form-item>
        <el-form-item label="学校地址" prop="schoolAddress">
          <el-input v-model="editSchoolForm.schoolAddress"></el-input>
        </el-form-item>
        <el-form-item label="对口中学" prop="middleSchoolName">
          <el-input v-model="editSchoolForm.middleSchoolName"></el-input>
        </el-form-item>
        <el-form-item label="学校网址" prop="schoolWebsite">
          <el-input type="email" v-model="editSchoolForm.schoolWebsite"></el-input>
        </el-form-item>

        <div class="btn-group">
          <el-button type="primary" @click="saveOrUpdateSchoolData" :loading="saveLoadingBtn">保存</el-button>
          <el-button type="primary" @click="handlerCancel">取消</el-button>
        </div>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  import PageList from '@/mixins/pageList'
  import CitySelector from '@/components/BaseCascader/city'
  import CommunitySelector from '@/components/BaseCascader/'
  import * as SchoolDistrictRequestURL from '@/request/manage/schooldistrict'
  import {systemAddLog,systemUpdateLog,systemQueryLog,systemDelLog} from '@/request/log/systemPlatformLog'

  export default {
    name: "schoolDistrict",
    mixins: [PageList],
    components: { CitySelector, CommunitySelector },
    data() {
      return {
        communityUrl: 'manage/commonselect/queryOpenCityCountyRegionAllCommunity',
        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        queryForm: {
          rgCode: '',
          schoolName: '',
          schoolAddress: ''
        },
        editForm: {
          comId: '',
          communityId: '',
          communityRemark: ''
        },
        editSchoolForm:{
          id:'',
          rgCode: '',
          schoolName: '',
          schoolAddress: '',
          middleSchoolName: '',
          schoolWebsite: ''
        },
        schoolData: [],
        communityData: [],
        communityListQuery:{
          page: 1,limit: 10,total: 0
        },
        queryLoadingBtn: false,
        saveLoadingBtn: false,
        loadingView: false,
        loadingCommView: false,
        editDlgVisible: false,
        editSchoolDlgVisible: false,
        hackReset: false,
        type: '',
        currentRowData: '',
        schoolId: '',
        isAdd: false,
        communityName: '',
        ruleCommunity: {
          communityId:{required: true,message: '小区不可为空', trigger: 'change'}
        },
        ruleSchool:{
          rgCode:{required: true,message: '所属城市不可为空', trigger: 'change'},
          schoolName:{required: true,message: '学校名称不可为空', trigger: 'blur'},
          schoolAddress:{required: true,message: '学校地址不可为空', trigger: 'blur'}
        },
        saveLodingBtn: false,
        originalData: {}
      }
    },
    methods:{
      initSelectData(data) {
        if (data !== null && data.length > 0){
          this.listQuery.page = 1
          this.listQuery.currentPage = 1
          this.queryForm.rgCode = data[0].code
          this._loadData(false)
        }
      },
      getName(value,name,data){
        this.communityName = name
        if (data.dataType !== 'community') {
          this.$message({
            showClose: true,
            type: 'warning',
            message: '只能选择小区!'
          })
          setTimeout(() => {
            this.communityName = ''
            this.editForm.communityId = ''
          }, 50)
        }
      },
      // 取消
      handlerCancel(){
        this.editDlgVisible = false
        this.editSchoolDlgVisible = false
      },
      // 新增周边小区
      addHandler(type,row){
        this.hackReset = false
        this.$nextTick(()=>{
          this.type = type
          this.hackReset = true
          this.schoolId = row.id
          this.isAdd = true
          if(this.currentRowData){
            this.currentRowData = ''
          }
          this.resetForm('editForm')
          this.setForm(this.editForm)
          this.editDlgVisible = true
        })
      },
      // 编辑周边小区
      editHandler(row,type){
        this.hackReset = false
        this.$nextTick(()=>{
          this.type = type
          this.hackReset = true
          this.currentRowData = row
          this.resetForm('editForm')
          this.setForm(this.editForm)
          this.editForm.comId = row.id
          this.communityName = row.communityName
          this.isAdd = false
          this.editDlgVisible = true
        })
      },
      // 新增学校
      addSchoolHandler(type){
        this.type = type
        this.isAdd = true
        this.currentRowData = ''
        this.resetForm('editSchoolForm')
        this.editSchoolDlgVisible = true
        this.$nextTick(()=>{
          this.setForm(this.editSchoolForm)
        })
      },
      // 编辑学校
      editSchoolHandler(row,type){
        this.hackReset = false
        this.resetForm('editSchoolForm')
        this.$nextTick(()=>{
          this.hackReset = true
          this.type = type
          this.isAdd = false
          this.currentRowData = row
          this.setForm(this.editSchoolForm)
          this.editSchoolDlgVisible = true
        })
      },

      handlerQuery(btnQuery){
        this.listQuery.page = 1
        this.listQuery.currentPage = 1
        this._loadData(btnQuery)
      },

      // 查询列表数据
      _loadData(btnQuery){
        this.loadingView = true
        if(btnQuery){
          this.queryLoadingBtn = true
        }
        let params = Object.assign({},this.queryForm,{
          page: this.listQuery.page,
          limit: this.listQuery.limit
        })
        SchoolDistrictRequestURL.querySchoolDistrict(params).then(res=>{
          this.schoolData = res.data
          this.listQuery.total = res.count
          // 取到学校的第一条数据查询小区信息
          this.queryCommunityData(res.data[0],null)
          this.schoolId = res.data[0].id
          this.loadingView = false
          this.queryLoadingBtn = false
        }).catch(err=>{
          this.loadingView = false
          this.queryLoadingBtn = false
          this.$message({
            type: 'error',
            message: err.data.msg
          })
        })
      },

      // 根据学校ID查询周边小区
      queryCommunityData(row,event){
        if(event){
          event.stopPropagation()
          event.preventDefault()
        }
        this.loadingCommView = true
        if(row && row != 'undefined'){
          this.schoolId = row.id
        }
        let params = {schoolId: this.schoolId,page: this.communityListQuery.page,limit: this.communityListQuery.limit}
        SchoolDistrictRequestURL.getCommunityPageObj(params).then(res=>{
          this.communityData = res.data
          this.communityListQuery.total = res.count
          this.loadingCommView = false
        }).catch(err=>{
          this.$message({
            type: 'error',
            message: err.data.msg
          })
          this.loadingCommView = false
        })
      },

      // 保存学校信息
      saveOrUpdateSchoolData(){
        this.$refs['editSchoolForm'].validate((valid)=>{
          if(valid){
            this.$confirm('确定要保存数据吗','提示',{
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(()=>{
              this.saveLoadingBtn = true
              let params = Object.assign({},this.editSchoolForm,{})
              SchoolDistrictRequestURL.saveOrUpdateSchoolInfo({jsonData: JSON.stringify(params),type: this.type}).then(res=>{
                if(res.success){
                  this.$message({
                    type: 'success',
                    message: res.msg
                  })
                  this._loadData(false)
                }else{
                  this.$message({
                    type: 'error',
                    message: res.msg
                  })
                }
                this.saveLoadingBtn = false
                this.handlerCancel()

                if(this.isAdd){
                  let message = {sourceCode:params.schoolName,sourceTypeId:'5',operatTypeId:'1'
                    ,logContent: `新增学校:${params.schoolName}`}
                  systemAddLog({message: JSON.stringify(message)})
                }else{
                  let message = {sourceCode:params.schoolName,sourceTypeId:'5',operatTypeId:'2'
                    ,labelData:this.$utils.getFormFields(this.$refs['editSchoolForm'])
                    ,originalData:this.originalData,newData: params}
                  systemUpdateLog({message: JSON.stringify(message)})
                }
              }).catch(err=>{
                this.saveLoadingBtn = false
                this.$message({
                  type: 'success',
                  message: res.msg
                })
              })
            })
            //   .catch(()=>{
            //   this.$message({
            //     type: 'info',
            //     message: '用户取消操作'
            //   })
            // })
          }
        })
      },

      // 保存学区信息
      saveOrUpdateCommunityData(){
        this.$refs['editForm'].validate((valid)=>{
          if(valid){
            this.$confirm('确定要保存数据吗','提示',{
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(()=>{
              this.saveLodingBtn = true
              let params = Object.assign({},this.editForm,{})
              params.schoolId = this.schoolId
              params.communityName = this.communityName
              SchoolDistrictRequestURL.saveOrUpdateSchoolInfo({
                  jsonData: JSON.stringify(params),type:this.type}).then(res=>{
                if(res.success){
                  this.$message({
                    type: 'success',
                    message: res.msg
                  })
                  this.queryCommunityData('undefined')
                }else{
                  this.$message({
                    type: 'error',
                    message: res.msg
                  })
                }
                this.saveLodingBtn = false
                this.handlerCancel()


                if(this.isAdd){
                  let message = {sourceCode:this.communityName,sourceTypeId:'5',operatTypeId:'1'
                    ,logContent: `新增学区:${this.communityName}`}
                  systemAddLog({message: JSON.stringify(message)})
                }else{
                  let message = {sourceCode:params.schoolName,sourceTypeId:'5',operatTypeId:'2'
                    ,labelData:this.$utils.getFormFields(this.$refs['editForm'])
                    ,originalData:this.originalData,newData: params}
                  systemUpdateLog({message: JSON.stringify(message)})
                }
              }).catch(err=>{
                this.saveLodingBtn = false
                this.$message({
                  type: 'success',
                  message: err.data.msg
                })
              })
            })
            //   .catch(()=>{
            //   this.$message({
            //     type: 'info',
            //     message: '用户取消操作'
            //   })
            // })
          }
        })
      },

      // 删除小区信息
      deletCommunityData(row){
        this.$confirm('确定删除此数据？','提示',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type:'warning'
        }).then(()=>{
          let params = { communityId: row.id}
          SchoolDistrictRequestURL.delSchoolBlockData(params).then(res=>{
            if(res.success){
              this.$message({
                type: 'success',
                message: res.msg
              })
              this.queryCommunityData()

              let message = {sourceCode:row.communityName,sourceTypeId:'5',operatTypeId:'3'
                ,logContent: '删除学区：'+ row.communityName}
              systemDelLog({message: JSON.stringify(message)})

            }else{
              this.$message({
                type: 'error',
                message: res.msg
              })
            }
          })
        })
        //   .catch(err=>{
        //   this.$message({
        //     type: 'info',
        //     message: '用户取消操作'
        //   })
        // })
      },

      // 删除学校信息
      deletSchoolData(row){
        this.$confirm('确定删除此数据？','提示',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type:'warning'
        }).then(()=>{
          let params = { schoolId: row.id}
          SchoolDistrictRequestURL.delSchoolBlockData(params).then(res=>{
            if(res.success){
              this.$message({
                type: 'success',
                message: res.msg
              })
              this._loadData(false)

              let message = {sourceCode:row.schoolName,sourceTypeId:'5',operatTypeId:'3'
                ,logContent: '删除学校：'+ row.schoolName}
              systemDelLog({message: JSON.stringify(message)})
            }else{
              this.$message({
                type: 'error',
                message: res.msg
              })
            }
          })
        })
        //   .catch(err=>{
        //   this.$message({
        //     type: 'info',
        //     message: '用户取消操作'
        //   })
        // })
      },

      // 重置表单数据
      resetForm(formName){
        this.$refs[formName] && this.$refs[formName].resetFields()
      },

      setForm(form){
        for(let i in form){
          form[i] = this.currentRowData[i]
        }
        this.originalData = Object.assign({},{...form})
      }
    }
  }
</script>

<style scoped>

</style>
