<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form" ref="query-form">
        <el-form size="small" :inline="true" :model="queryForm" ref="queryForm" class="demo-form-inline">

          <el-form-item label="考核项目名" prop="name">
            <el-input v-model="queryForm.name"></el-input>
          </el-form-item>

          <el-form-item label="经纪人名称" prop="userName">
            <el-input v-model="queryForm.userName"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click.native.prevent="_loadData(true)" :loading="queryBtnLoading">查询</el-button>
            <el-button @click="resetForm('queryForm')">清空</el-button>
            <el-button type="primary" @click.native.prevent="handleAdd(true)">新增</el-button>
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
          show-overflow-tooltip
          align="left"
          label="考核项目名称">
        </el-table-column>

        <el-table-column
          prop="userName"
          show-overflow-tooltip
          align="left"
          label="经纪人名称">
        </el-table-column>

        <el-table-column
          prop="achievement"
          show-overflow-tooltip
          align="left"
          label="考核成绩">
        </el-table-column>

        <el-table-column
          prop="introduction"
          show-overflow-tooltip
          align="left"
          label="考核项目简介">
        </el-table-column>

        <el-table-column
          prop="operator"
          show-overflow-tooltip
          align="left"
          label="操作人">
        </el-table-column>

        <el-table-column
          prop="addTime"
          show-overflow-tooltip
          align="left"
          :formatter="_timeFormat"
          label="创建时间">
        </el-table-column>

        <el-table-column
          prop="updateTime"
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
            <el-button @click="handleEdit(scope.row,'editForm')" type="text" size="small">编辑</el-button>
            <el-button @click="delAgentAbilityidentify(scope.row)" type="text" size="small">删除</el-button>
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
      title="经纪人能力认定编辑"
      :show.sync="editDialogVisible"
      :close-on-click-modal="false"
      top="30vh"
      width="400px">

      <el-form :model="editForm" :rules="rules" ref="editForm" size="small" label-width="120px">
        <el-form-item prop="name" label="考核项目名称">
          <el-input v-model="editForm.name" type="text" :disabled="isExist"></el-input>
        </el-form-item>

        <el-form-item prop="agentId" label="经纪人姓名">
          <base-cascader v-model="editForm.agentId" :url="getReferenceUserSelectUrl"
               :props="cascaderProps" :dataProps="cascaderDataProps" @change="getAgentName"></base-cascader>
        </el-form-item>

        <el-form-item prop="achievement" label="考核成绩">
          <el-input v-model="editForm.achievement" type="text"></el-input>
        </el-form-item>

        <el-form-item prop="introduction" label="考核项目简介">
          <el-input v-model="editForm.introduction" type="text"></el-input>
        </el-form-item>

        <div class="btn-group">
          <el-button type="primary" @click="handleSubmit" :loding="loadingBtn" size="small">保存</el-button>
          <el-button type="primary" @click="handleCancel" size="small">取消</el-button>
        </div>
      </el-form>
    </b-dialog>
  </div>
</template>

<script>
  import PageList from '@/mixins/pageList'
  import * as RequestURL from '@/request/manage/agentAbilityidentify'
  import BaseCascader from '@/components/BaseCascader'

  export default {
    name: 'index',
    mixins: [PageList],
    components: {BaseCascader},
    data () {
      return {
        currentRowData: '',
        queryForm: {
          name: '',
          userName: ''
        },
        editForm: {
          id: '',
          name: '',
          userName: '',
          userId: '',
          agentId: '',
          achievement: '',
          introduction: ''
        },
        loadingView: false,
        editDialogVisible: false,
        queryBtnLoading: false,
        tableData: [],
        loadingBtn: false,
        isExist: false,
        isAdd: true,
        getReferenceUserSelectUrl: 'manage/usermanage/queryAgentInfoToCreateTree', // 介绍人
        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        rules: {
          name: [
            {required: true, message: '该项为必填', trigger: 'blur'}
          ],
          agentId: [
            {required: true, message: '该项为必填', trigger: 'change'}
          ],
          achievement: [
            {required: true, message: '该项为必填', trigger: 'blur'}
          ],
          introduction: [
            {required: true, message: '该项为必填', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {

      getAgentName(value,name){
        this.editForm.userName = name
        this.editForm.agentId = value
      },

      // 新增操作
      handleAdd(formName) {
        this.resetForm(formName)
        this.isAdd = true
        this.$nextTick(() => {
          this.currentRowData = ''
          this.setForm(this.editForm, '')
          this.editDialogVisible = true
        })
      },
      // 编辑操作
      handleEdit(row, formName) {
        this.resetForm(formName)
        this.isAdd = false
        this.currentRowData = ''
        this.$nextTick(() => {
          this.currentRowData = row
          let agentId = row.userId + '-' + row.agentId
          this.setForm(this.editForm, this.currentRowData)
          this.editForm.agentId = agentId
          this.editDialogVisible = true
        })
      },

      //取消操作
      handleCancel() {
        this.editDialogVisible = false
        this.isExist = false
      },

      //确认提交数据
      handleSubmit() {
        this.saveOrUpdateAgentAbilityidentify()
      },

      // 修改时设置界面数据
      setForm: function (formName, data) {
        this.resetForm(formName)
        for (let i in this.editForm) {
          this.editForm[i] = data[i]
        }
      },

      //重置界面数据
      resetForm(formName) {
        this.$refs[formName] && this.$refs[formName].resetFields()
      },

      // 查询页面列表数据
      _loadData(btnQuery) {
        if (btnQuery) {
          this.queryBtnLoading = btnQuery
        }
        this.loadingView = true
        let params = Object.assign({}, this.queryForm, {
          page: this.listQuery.page,
          limit: this.listQuery.limit
        })
        RequestURL.queryAgentAbilityidentifyDate(params).then((res) => {
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
      saveOrUpdateAgentAbilityidentify: function () {
        this.$refs['editForm'].validate((valid) => {
          if (valid) {
            let params = {...this.editForm}
            let agentId = params.agentId
            let idArr = agentId.split('-')
            if(idArr.length != 2){
              this.$message({
                type: 'warning',
                message: '经纪人姓名信息错误'
              })
              return
            }
            params.userId = idArr[0]
            params.agentId = idArr[1]
            this.$confirm('确认保存数据吗？', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.loadingBtn = true
              RequestURL.saveOrUpdateAgentAbilityidentify({jsonData: JSON.stringify(params)}).then(res => {
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
              }).catch(() => {
                this.editDialogVisible = false
                this.loadingBtn = false
              })
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '取消!'
              })
            })
          }
        })
      },
      // 删除导航栏信息
      delAgentAbilityidentify(row) {
        let params = {id: row.id}
        this.$confirm('确定删除数据吗', '提示', {
          confirmButtonText: '',
          cancelButtonText: '',
          type: 'warning'
        }).then(() => {
          RequestURL.delAgentAbilityidentify(params).then(res => {
            if (res.success) {
              this.$message({
                type: 'success',
                message: res.msg
              })
              this._loadData(false)
            } else {
              this.$message({
                type: 'error',
                message: res.msg
              })
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          })
        })
      }
    }
  }
</script>

<style lang="scss" scoped>
</style>
