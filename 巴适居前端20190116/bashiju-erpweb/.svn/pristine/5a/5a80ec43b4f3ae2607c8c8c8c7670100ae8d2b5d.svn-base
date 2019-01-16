<template>
  <div class="page-content">
    <div class="page-content-hd">
      <el-form :model="queryForm" ref="queryForm" :inline="true" size="small" class="query-form">
        <el-form-item label="中介名称" prop="keyword">
          <el-input v-model="queryForm.keyword" type="text"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click.native.prevent="handlerQuery(true)" :loading="loadQueryBtn">查询</el-button>
          <el-button v-hasOnlyBtn="'blackListAdd'" type="primary" @click.native.prevent="addIntermediaryBlacklist">新增</el-button>
        </el-form-item>
      </el-form>
    </div>

    <div class="page-content-hd">
      <el-table :data="tableData" border align="center" v-loading="loadingView">
        <el-table-column prop="companyName" label="中介名称" align="left" width="300%"></el-table-column>
        <el-table-column prop="legalPhone" label="中介电话" align="left" width="150%"></el-table-column>
        <el-table-column prop="note" label="违规原因" align="left"></el-table-column>
        <el-table-column label="操作" align="left">
          <template slot-scope="scope">
            <el-button v-hasMultipleBtn="['editBtn',scope.row]" @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
            <el-button v-hasMultipleBtn="['delBtn',scope.row]" @click="handleDelete(scope.row)" type="text" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--分页控件-->
      <b-pagination
        :listQuery="listQuery"
        @handleSizeChange="handleSizeChange"
        @handleCurrentChange="handleCurrentChange">
      </b-pagination>
      <b-dialog
        title="黑名单编辑"
        :show.sync="editDlgVisible"
        :close-on-click-modal="false"
        top="15vh"
        width="400px"
      >
        <el-form :model="editForm" ref="editForm" :rules="rules" label-width="100px">

          <el-form-item label="中介名称" prop="companyId">
            <company-select v-model="editForm.companyId" :url="companyUrl" :changeOnSelect="false"
               :props="cascaderProps" :dataProps="cascaderDataProps" @change="jurgeSelect"></company-select>
          </el-form-item>

          <el-form-item label="中介电话" prop="legalPhone">
            <el-input v-model="editForm.legalPhone" type="number"></el-input>
          </el-form-item>

          <el-form-item label="违规次数" prop="foulsNum">
            <el-input v-model="editForm.foulsNum" type="number"></el-input>
          </el-form-item>

          <el-form-item label="备注信息" prop="note">
            <el-input v-model="editForm.note" type="textarea" autosize></el-input>
          </el-form-item>

          <el-form-item class="margin-b-none">
            <el-button type="primary" @click="saveBlackListData" :loading="saveLoadingBtn">保存</el-button>
            <el-button @click="handleCancel">取消</el-button>
          </el-form-item>

        </el-form>
      </b-dialog>
    </div>
  </div>
</template>

<script>
  import PageList from '@/mixins/pageList'
  import * as RequestURL from '@/request/manage'
  import CompanySelect from '@/components/BaseCascader'
  import {systemAddLog,systemUpdateLog,systemQueryLog,systemDelLog} from '@/request/log/systemPlatformLog'

  export default {
    name: "IntermediaryBlacklist",
    mixins:[PageList],
    components: {CompanySelect},
    data(){
      // 自定义电话验证
      let legalPhone = (rule, value, callback) => {
        let reg = /^1[3|4|5|7|8][0-9]\d{8}$/
        if(!value){
          return callback(new Error('电话号码不能为空'))
        }
        if (reg.test(value)) {
          callback();
        }
        return callback(new Error('请输入正确的电话号码'));
      }

      return{
        saveLoadingBtn: false,
        companyUrl:'manage/commonselect/queryOpenCityCompany',
        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        tableData: [],
        editDlgVisible: false,
        currentRowData: '',
        loadQueryBtn: false,
        loadingView: false,
        queryForm:{
          keyword: ''
        },
        editForm:{
          id: '',
          companyId: '',
          legalPhone: '',
          foulsNum: '',
          note: ''
        },
        companyOpts: [],
        rules:{
          companyId:{required: true,message: '该项为必填' ,trigger: onchange},
          legalPhone:
            // {required: true,message: '该项为必填' ,trigger: blur},
          { validator: legalPhone, trigger: 'blur' },
          foulsNum:{required: true,message: '该项为必填' ,trigger: blur},
          note:{required: true,message: '该项为必填' ,trigger: blur}
        },
        originalData: {},
        companyName: ''
      }
    },
    methods: {
      jurgeSelect(value,name,data){
        if (data.dataType !== 'company') {
          this.$message({
            showClose: true,
            type: 'warning',
            message: '只能选择公司!'
          })
          setTimeout(() => {
            this.editForm.companyId = ''
          }, 50)
        }else{
          this.editForm.companyId = value
          this.companyName = name
        }
      },

      handleCancel() {
        this.editDlgVisible = false
      },

      getMangeName(value,name){
        this.editForm.manageName = name
      },

      handlerQuery(btnQuery){
        this.listQuery.page = 1
        this.listQuery.currentPage = 1
        this._loadData(btnQuery)
      },

      /**
       * 查询页面数据
       * @param btnQuery
       * @private
       */
      _loadData(btnQuery){
        let param = {...this.queryForm}
        let params = Object.assign({},param,{
          page: this.listQuery.page,
          limit: this.listQuery.limit
        })
        if(btnQuery){
          this.loadQueryBtn = true
        }
        this.loadingView = true
        RequestURL.queryBlackListData(params).then(res=>{
          this.tableData = res.data
          this.listQuery.total = res.count
          this.loadQueryBtn = false
          this.loadingView = false
        }).catch(err=>{
          console.log(err)
          this.$message({
            type: 'error',
            message: err.data.msg
          })
          this.loadQueryBtn = false
          this.loadingView = false
        })
      },
      // 修改中介黑名单
      handleEdit(row){
        this.resetForm('editForm')
        this.$nextTick(()=>{
          this.currentRowData = row
          this.setForm(this.editForm)
          this.editDlgVisible = true
        })
      },

      // 删除中介黑名单
      handleDelete(row){
        this.$confirm('确定要删除数据吗？','提示',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type:'warning'
        }).then(()=>{
          RequestURL.delBlackList({id:row.id}).then(res=>{
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

            let message = {sourceCode:row.companyName,sourceTypeId:'21',operatTypeId:'3'
              ,logContent: `删除中介黑名单： ${row.companyName}`}
            systemDelLog({message: JSON.stringify(message)})
          }).catch(err=>{
            console.log(err)
          })
        })
      },

      // 添加中介黑名单
      addIntermediaryBlacklist(){
        this.resetForm('editForm')
        this.$nextTick(()=>{
          this.currentRowData = ''
          this.setForm(this.editForm)
          this.editDlgVisible = true
        })
      },
      /**
       * 保存中介黑名单信息
       */
      saveBlackListData(){
        this.$refs['editForm'].validate((valid)=>{
          if(valid){
            this.$confirm('确定保存此数据吗?','提示',{
              confirmButtonText: '',
              cancelButtonText: '',
              type: 'warning'
            }).then(()=>{
              let params = {...this.editForm}
              params.userTypeId = params.userType
              this.saveLoadingBtn = true
              RequestURL.saveBlackListData({jsonData: JSON.stringify(params)}).then(res=>{
                if(res.success){
                  this.$message({
                    type: 'success',
                    message: res.msg
                  })
                  this.editDlgVisible = false
                  this._loadData(false)
                }else{
                  this.$message({
                    type: 'error',
                    message: res.msg
                  })
                }
                this.saveLoadingBtn = false

                if(this.editForm.id){
                  let message = {sourceCode:this.companyName,sourceTypeId:'21',operatTypeId:'2'
                    ,labelData:this.$utils.getFormFields(this.$refs['editForm'])
                    ,originalData:this.originalData,newData: {...this.editForm}}
                  systemUpdateLog({message: JSON.stringify(message)})
                }else{
                  let message = {sourceCode:this.companyName,sourceTypeId:'21',operatTypeId:'1'
                    ,logContent: `新增黑名单:${this.companyName}`}
                  systemAddLog({message: JSON.stringify(message)})
                }

              })
            })
            //   .catch(err=>{
            //   this.$message({
            //     type: 'infor',
            //     message: '已取消'
            //   })
            // })
          }
        })
      },

      // 重置表单数据
      resetForm(formName){
        this.$refs[formName] && this.$refs[formName].resetFields()
      },

      // 设置表单数据
      setForm(formName){
        if(formName){
          for(let i in formName){
            formName[i] = this.currentRowData[i]
          }
        }
        this.originalData = Object.assign({},{...formName})
      }
    },
    mounted() {
      // this.getCompanyDataSelect()
    }
  }
</script>

<style scoped>

</style>
