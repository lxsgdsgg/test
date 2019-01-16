<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form" ref="query-form">
        <el-form size="small" :inline="true" :model="queryForm" ref="queryForm" class="demo-form-inline">

          <el-form-item label="导航名称" prop="name">
            <el-input v-model="queryForm.name"></el-input>
          </el-form-item>

          <el-form-item label="导航类型" prop="type">
            <el-select v-model="queryForm.type">
              <el-option label="请选择" value=""></el-option>
              <el-option value="0" label="头部导航栏"></el-option>
              <el-option value="1" label="底部导航栏"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="导航等级" prop="level">
            <el-select v-model="queryForm.level">
              <el-option label="请选择" value=""></el-option>
              <el-option value="1" label="一级"></el-option>
              <el-option value="2" label="二级"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click.native.prevent="_loadData(true)" :loading="queryBtnLoading">查询</el-button>
            <el-button @click="resetForm('queryForm')">清空</el-button>
            <el-button v-hasOnlyBtn="'addBtn'" type="primary" @click.native.prevent="handleAdd('editForm')">新增</el-button>
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
          prop="navigateCode"
          show-overflow-tooltip
          align="left"
          width="80px"
          label="导航编号">
        </el-table-column>

        <el-table-column
          prop="type"
          width="240px"
          show-overflow-tooltip
          align="left"
          label="导航类型">
          <template slot-scope="scope">
            {{toTypeStr(scope.row.type)}}
          </template>
        </el-table-column>

        <el-table-column
          prop="level"
          align="left"
          show-overflow-tooltip
          label="导航等级">
          <template slot-scope="scope">
            {{toLevelStr(scope.row.level)}}
          </template>
        </el-table-column>

        <el-table-column
          prop="name"
          width="240px"
          show-overflow-tooltip
          align="left"
          label="导航名称">
        </el-table-column>

        <el-table-column
          prop="url"
          width="240px"
          show-overflow-tooltip
          align="left"
          label="导航地址">
        </el-table-column>

        <el-table-column
          prop="operator"
          width="240px"
          show-overflow-tooltip
          align="left"
          label="创建用户">
        </el-table-column>

        <el-table-column
          prop="addTime"
          width="240px"
          show-overflow-tooltip
          align="left"
          :formatter="_timeFormat"
          label="创建时间">
        </el-table-column>

        <el-table-column
          prop="updateTime"
          width="240px"
          show-overflow-tooltip
          align="left"
          :formatter="_timeFormat"
          label="更新时间">
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
          width="100px"
        >
          <template slot-scope="scope">
            <el-button v-hasMultipleBtn="['table_navigationBarEdit',scope.row]" @click="handleEdit(scope.row,'editForm')" type="text" size="small">编辑</el-button>
            <el-button v-hasMultipleBtn="['table_navigationBarDel',scope.row]" @click="deleteNavigationBarInfo(scope.row)" type="text" size="small">删除</el-button>
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

    <!--编辑数据弹框-->
    <b-dialog
      title="导航栏编辑"
      :show.sync="editDialogVisible"
      :close-on-click-modal="false"
      top="30vh"
      width="400px">

      <el-form :model="editForm" :rules="rules" ref="editForm" size="small" label-width="100px">
        <el-form-item prop="name" label="导航栏名称">
          <el-input v-model="editForm.name" type="text" style="width: 215px;" :disabled="isExist"></el-input>
        </el-form-item>

        <el-form-item prop="navigateCode" label="导航栏编号">
          <el-input v-model="editForm.navigateCode" type="text" style="width: 215px;"></el-input>
        </el-form-item>

        <el-form-item prop="type" label="导航栏类型">
          <el-select v-model="editForm.type" :disabled="isExist">
            <el-option value="0" label="头部导航栏"></el-option>
            <el-option value="1" label="底部导航栏"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item prop="level" label="导航等级">
          <el-select v-model="editForm.level" @change="queryParentLevelByLevel" :disabled="isExist">
            <el-option value="1" label="一级"></el-option>
            <el-option value="2" label="二级"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item prop="parentId" label="上级类别" v-show="isShowParent">
          <el-select v-model="editForm.parentId" :disabled="isExist">
            <el-option
              v-for="item in parentOpts"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item prop="url" label="导航栏地址">
          <el-input v-model="editForm.url" type="url" style="width: 215px;" :disabled="isExist"></el-input>
        </el-form-item>

        <div class="btn-group">
          <el-button type="primary" @click="handleSubmit" :loading="loadingBtn" size="small">保存</el-button>
          <el-button type="primary" @click="handleCancel" size="small">取消</el-button>
        </div>
      </el-form>
    </b-dialog>
  </div>
</template>

<script>
  import PageList from '@/mixins/pageList'
  import * as RequestURL from '@/request/manage/navigationBar'
  import {systemAddLog,systemUpdateLog,systemQueryLog,systemDelLog} from '@/request/log/systemPlatformLog'

  export default {
    name: 'navigationBar',
    mixins: [PageList],
    data () {
      return {
        currentRowData: '',
        parentOpts: [],
        queryForm: {
          name: '',
          type: '',
          level: ''
        },
        editForm: {
          id: '',
          name: '',
          navigateCode: '',
          type: '',
          level: '',
          parentId: '',
          url: ''
        },
        loadingView: false,
        editDialogVisible: false,
        queryBtnLoading: false,
        tableData: [],
        loadingBtn: false,
        isExist: false,
        isAdd: true,
        isShowParent: false,
        rules: {
          name: [
            {required: true, message: '该项为必填', trigger: 'blur'}
          ],
          navigateCode: [
            {required: true, message: '该项为必填', trigger: 'blur'}
            // ,{ validator: this.checkIsExistNavigationCode, trigger: 'blur'}
          ],
          type: [
            {required: true, message: '该项为必填', trigger: 'change'}
          ],
          level: [
            {required: true, message: '该项为必填', trigger: 'change'}
          ],
          url: [
            {required: true, message: '该项为必填', trigger: 'blur'}
          ]
        },
        originalData: {}
      }
    },
    methods: {
      //转换类别描述
      toTypeStr : function(type){
        if(type==0)
          return '头部导航栏';
        else
          return '底部导航栏';
      },
      toLevelStr : function(level){
        if(level==1)
          return '一级'
        else
          return '二级';
      },

      // 新增操作
      handleAdd (formName) {
        this.resetForm(formName)
        this.isAdd = true
        this.$nextTick(() => {
          this.currentRowData = ''
          this.setForm(this.editForm,'')
          this.editDialogVisible = true
        })
      },
      // 编辑操作
      handleEdit (row,formName) {
        this.resetForm(formName)
        this.isAdd = false
        this.$nextTick(() => {
          this.currentRowData = row
          this.currentRowData.type = String(this.currentRowData.type)
          this.currentRowData.level = String(this.currentRowData.level)
          // this.currentRowData.parentId = String(this.currentRowData.parentId)
          if(this.currentRowData.parentId == '0'){
            this.currentRowData.parentId = ''
          }
          this.queryParentLevelByLevel(this.currentRowData.level)
          this.setForm(this.editForm,this.currentRowData)
          this.editDialogVisible = true
        })
      },

      //取消操作
      handleCancel () {
        this.editDialogVisible = false
        this.isExist = false
      },

      //确认提交数据
      handleSubmit () {
        this.saveOrUpdatenavigationBar()
      },

      // 修改时设置界面数据
      setForm: function (formName,data) {
        this.resetForm(formName)
        for (let i in this.editForm) {
          this.editForm[i] = data[i]
        }
        this.originalData = Object.assign({},{...formName})
      },

      //重置界面数据
      resetForm (formName) {
        this.$refs[formName] && this.$refs[formName].resetFields()
      },

      // 查询页面列表数据
      _loadData(btnQuery){
        if(btnQuery){
          this.listQuery.page = 1
          this.listQuery.currentPage = 1
          this.queryBtnLoading = btnQuery
        }
        this.loadingView = true
        let params = Object.assign({}, this.queryForm, {
          page: this.listQuery.page,
          limit: this.listQuery.limit
        })
        RequestURL.queryNavigationBarData(params).then((res) => {
          this.tableData = res.data
          this.listQuery.total = res.count
          this.queryBtnLoading = false
          this.loadingView = false
        }).catch(err => {
          console.log(err)
          this.loadingView = false
          this.queryBtnLoading = false
        })
      },

      // 保存或更新定时任务信息
      saveOrUpdatenavigationBar: function(){
        this.$refs['editForm'].validate((valid) => {
          if (valid) {
            let params = {...this.editForm}
            this.$confirm('确认保存数据吗？', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.loadingBtn = true
              if(params.parentId == ''){
                params.parentId = '0'
              }
              RequestURL.saveOrUpdataNavigationBar({jsonData: JSON.stringify(params)}).then(res => {
                if (res.success) {
                  this.$message({
                    type: 'success',
                    message: res.msg,
                  })
                  this.editDialogVisible = false
                  this.loadingBtn = false
                  this._loadData(false)
                } else {
                  this.$message({
                    type: 'warning',
                    message: res.msg
                  })
                  this.editDialogVisible = false
                  this.loadingBtn = false
                }

                if(this.editForm.id){
                  let message = {sourceCode:params.name,sourceTypeId:'23',operatTypeId:'2'
                    ,labelData:this.$utils.getFormFields(this.$refs['editForm'])
                    ,originalData:this.originalData,newData: {...this.editForm}}
                  systemUpdateLog({message: JSON.stringify(message)})
                }else{
                  let message = {sourceCode:params.name,sourceTypeId:'23',operatTypeId:'1'
                    ,logContent: `新增导航:${params.name}`}
                  systemAddLog({message: JSON.stringify(message)})
                }

              }).catch(() => {
                this.editDialogVisible = false
                this.loadingBtn = false
              })
            })
            //   .catch(() => {
            //   this.$message({
            //     type: 'info',
            //     message: '取消!'
            //   })
            // })
          }
        })
      },
      // 删除导航栏信息
      deleteNavigationBarInfo(row){
        let params = {navigationBarId: row.id}
        this.$confirm('确定删除数据吗','提示',{
          confirmButtonText: '',
          cancelButtonText: '',
          type: 'warning'
        }).then(()=>{
          RequestURL.delNavigationBarById(params).then(res=>{
            if(res.success){
              this.$message({
                type: 'success',
                message: res.msg
              })
              this._loadData(false)

              let message = {sourceCode:row.name,sourceTypeId:'23',operatTypeId:'3'
                ,logContent: `删除导航： ${row.name}`}
              systemDelLog({message: JSON.stringify(message)})
            }else{
              this.$message({
                type: 'error',
                message: res.msg
              })
            }
          })
        })
        //   .catch(()=>{
        //   this.$message({
        //     type: 'info',
        //     message: '已取消'
        //   })
        // })
      },

      // 根据导航等级查询上级类别
      queryParentLevelByLevel(value){
        let params = {level: value - 1}
        this.editForm.parentId = ''
        this.isShowParent = true
        if (value > 1){
          this.isShowParent = true
        }else{
          this.isShowParent = false
        }
        RequestURL.queryAllNavigationBarNameByLevel(params).then((res) => {
          this.parentOpts = res
        }).catch(err => {
          console.log(err)
        })
      },

      // 根据导航等级查询上级类别
      checkIsExistNavigationCode(rule, value, callback){
        if(!this.isAdd){
          callback()
        }else{
          if (value) {
            let params = {navigateCode: value}
            RequestURL.queryIsExistNavigationCode(params).then((res) => {
              if(res.success){
                this.isExist = false
              }else{
                this.isExist = true
              }
            }).catch(err => {
              callback(new Error('编号已存在!'))
              console.log(err)
              this.isExist = true
            })
            if (this.isExist) {
              callback(new Error('编号已存在!'))
            } else {
              callback()
            }
          }
        }
      }
    }
  }
</script>

<style lang="scss" scoped>
</style>
