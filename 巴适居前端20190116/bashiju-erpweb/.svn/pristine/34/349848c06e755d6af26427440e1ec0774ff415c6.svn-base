<template>
  <div class="page-content">
    <!--<ContractContent houseId="60" houseCode="530102000019" :templateType="templateType1" @saveResult="saveResult1"></ContractContent>-->
    <!--<HouseDeal shhId="1" dealType="02"></HouseDeal>-->
   <!-- <ContractPrint code="C-MM-6457886112596758528"></ContractPrint>-->
    <div v-if="isShowTable">
      <div class="page-content-hd">
        <div class="query-form" >
          <el-form size="small" :inline="true"  class="demo-form-inline" >
            <el-form-item>
              <el-button v-hasOnlyBtn="'addBtn'" type="primary" @click="handleAdd">新增</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <div class="page-content-bd" v-loading="loadingView">
        <el-table :data="tableData" border style="width: 100%">
          <el-table-column align="center" prop="templateName" label="模板名称"  >
          </el-table-column>
          <el-table-column align="center" prop="templateType" label="模板类型">
          </el-table-column>
          <el-table-column align="center" prop="remark" label="备注">
          </el-table-column>
          <el-table-column align="center" label="操作">
            <template slot-scope="scope">
              <el-button v-hasMultipleBtn="['editBtn',scope.row]" @click="handleEdit(1,scope.row)" :loading="loadingView" type="text" size="small">编辑模板名称</el-button>
              <el-button v-hasMultipleBtn="['detailedBtn',scope.row]" @click="handleEdit(2,scope.row)" :loading="loadingView" type="text" size="small">详细</el-button>
              <el-button v-hasMultipleBtn="['deleteBtn',scope.row]" @click="handleDelete(scope.row)" type="text" size="small">删除</el-button>
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
    <div >
      <Detailed v-if="hackReset" :initData="initData" :templateId="templateId"  @blackList="blackList" @saveResult="saveResult"></Detailed>
    </div>
    <el-dialog :title="dialogTitle" width="600px" :visible.sync="dialogNameVisible">
      <el-form  label-position="right" label-width="100px" :model="baseInfo" ref="baseInfo" :rules="rules">
        <el-form-item label="模板名称" prop="templateName">
          <el-input v-model="baseInfo.templateName"></el-input>
        </el-form-item>
        <el-form-item label="模板类型" prop="templateType">
          <el-select v-model="baseInfo.templateType" placeholder="请选择" >
            <el-option
              v-for="item in templateType"
              :key="item.value"
              :label="item.name"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="合同编号前缀" prop="codePrefix">
          <el-input type="text" autosize  placeholder="请输入内容"  v-model="baseInfo.codePrefix">
          </el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input type="textarea" autosize  placeholder="请输入内容"  v-model="baseInfo.remark">
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary"  @click="saveTemplate">确 定</el-button>
        <el-button @click="dialogNameVisible = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>

</template>

<script>
  import {
    queryTemplateList,
    queryDetailed,
    addTemplate,
    updateTemplate,
    saveTemplateDetailed,
    deleteTemplate
  } from '@/request/manage/ContractTemplate.js'
  import Detailed from './components/Detailed.vue'
  import {manageAddLog,manageUpdateLog,manageQueryLog,manageDelLog} from '@/request/log/mangePlatformLog'
/*import ContractContent from '@/components/ContractContent.vue'
  import HouseDeal from '@/components/HouseDeal.vue'
  import ContractPrint from '@/components/ContractPrint.vue'*/
  export default {
    name: 'contractList',
    data () {
      return {
        templateType1:'01',
        dialogTitle:'新增合同模板',
        templateId: 0,
        loadingView: false,
        isShowTable: true,
        dialogNameVisible: false,
        tableData: [],
        listQuery: {
          page: 1,
          limit: 10,
          total: 0
        },
        templateType: [{'value':'01','name':'买卖合同'},{'value':'02','name':'出租合同'}],
        baseInfo: {'id':0,'templateName':'','templateType':'01','remark':'','codePrefix':'C-MM-'},
        currRow:{},
        hackReset: false,
        rules: {
          templateName: [
            { required: true, message: '请输入合同模板名称', trigger: 'blur' },
            { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
          ]
        },
        initData: {}
      }
    },
    components: {
      Detailed
      //ContractContent,HouseDeal,ContractPrint
    },
    mounted () {
      this.queryListData()
      //this.$router.push({path:'/printContract',query:{code:'C-MM-6457886112596758528'}})
    },
    methods: {
      queryListData: function () {
        this.loadingView = true
        let params = Object.assign({},{
          page: this.listQuery.page,
          limit: this.listQuery.limit
        })
        queryTemplateList(params).then((res) => {
          this.tableData = res.data
          this.listQuery.total = res.count
          this.loadingView = false
        }).catch(err => {
          console.log(err)
          this.loadingView = false
        })
      },
      // 当前页改变
      handleCurrentChange(val) {
        this.listQuery.page = val
        this.queryListData()
      },
      // 页面改变
      handleSizeChange(val) {
        this.listQuery.limit = val
        this.queryListData(false)
      },
      handleAdd () {
        this.dialogNameVisible = true
        this.$nextTick(()=>{
          this.dialogTitle = '新增合同模板'
          this.baseInfo = {'id':0,'templateName':'','templateType':'01','remark':'','codePrefix':'C-MM-'}
          this.$refs['baseInfo'].clearValidate()
        })


      },
      handleEdit (type,row) {
        if (type === 1){
          this.dialogNameVisible = true
        } else {
          this.hackReset = false
          this.loadingView = true
        }
        this.currRow = row
        this.$nextTick(() => {
          if (type === 1) {
            this.dialogTitle = '编辑合同模板'
            this.baseInfo = {'id':row.id,'templateName': row.templateName, 'templateType':row.templateTypeId,'codePrefix':row.codePrefix,'remark':row.remark}
            this.$refs['baseInfo'].clearValidate()
          } else {

            let param = {'templateId': row.id}
            queryDetailed(param).then(res => {
              if (res.success) {
                this.hackReset = true
                this.templateId = row.id
                this.isShowTable = false
                this.initData = res.data
              } else {
                this.$message({
                  type: 'warning',
                  message: res.msg
                })
              }
              this.loadingView = false
            }).catch(() => {
            })
          }
        })



      },
      blackList () {
        this.isShowTable = true
        this.hackReset = false
        this.queryListData()
      },
      handleDelete (row) {
        this.$confirm('确定要删除合同模板：' + row.templateName + '？', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          const param = {templateId: row.id}
          deleteTemplate(param).then(res => {
            if (res.success) {
              this.$message({
                type: 'success',
                message: res.msg
              })
              this.queryListData()
              let message = {sourceCode:row.templateName,sourceTypeId:'13',operatTypeId:'3'
                ,logContent: '删除合同模板：'+row.templateName}
              manageDelLog({message: JSON.stringify(message)})
            } else {
              this.$message({
                type: 'warning',
                message: res.msg
              })
            }
          }).catch(() => {})
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      },
      saveTemplate () {
          console.log(this.baseInfo)
        this.$refs['baseInfo'].validate((valid) => {
          if (valid) {
            if (this.baseInfo.id === 0) {
              addTemplate(this.baseInfo).then((res) => {
                if (res.success) {
                  this.$message({
                    type: 'success',
                    message: res.msg
                  })
                  this.queryListData()
                let message = {sourceCode:this.baseInfo.templateName,sourceTypeId:'13',operatTypeId:'1'
                  ,logContent:'添加合同模板:'+this.baseInfo.templateName}
                console.log(message)
                manageAddLog({message: JSON.stringify(message)})
                } else {
                  this.$message({
                    type: 'warning',
                    message: res.msg
                  })
                }
              }).catch(err => {
                console.log(err)
                this.loadingView = false
              })
            }else {
              updateTemplate(this.baseInfo).then((res) => {
                if (res.success) {
                  this.$message({
                    type: 'success',
                    message: res.msg
                  })
                  this.queryListData()
                  if (this.baseInfo.templateType=='01')this.baseInfo.templateType='买卖合同'
                  if (this.baseInfo.templateType=='02')this.baseInfo.templateType='租赁合同'
                let message = {sourceCode:this.currRow.templateName,sourceTypeId:'13',operatTypeId:'2'
                  ,labelData:this.$utils.getFormFields(this.$refs['baseInfo'])
                  ,originalData:this.currRow,newData: this.baseInfo}
                console.log(message)
                manageUpdateLog({message: JSON.stringify(message)})
                } else {
                  this.$message({
                    type: 'warning',
                    message: res.msg
                  })
                }
              }).catch(err => {
                console.log(err)
                this.loadingView = false
              })
            }
            this.dialogNameVisible = false
          } else {
            return false;
          }
        })


      },
      saveResult (param) {
        this.loadingView = true
         saveTemplateDetailed(param).then(res=>{
         if (res.success) {
         this.$message({
         type: 'success',
         message: res.msg
         })
         this.isShowTable = true
         this.hackReset = false
         this.queryListData()
           let message = {sourceCode:this.currRow.templateName,sourceTypeId:'13',operatTypeId:'2'
             ,logContent:'修改了合同模板:'+this.currRow.templateName+"的详细内容"}
           console.log(message)
           manageAddLog({message: JSON.stringify(message)})
         }else {
         this.$message({
         type: 'warning',
         message: res.msg
         })
         }
         this.loadingView = false
         }).catch(() => {
         })
      },
      saveResult1(param){
        console.log(3)
          console.log(param)
      }
    }

  }
</script>
<style lang="scss" scoped>

</style>
