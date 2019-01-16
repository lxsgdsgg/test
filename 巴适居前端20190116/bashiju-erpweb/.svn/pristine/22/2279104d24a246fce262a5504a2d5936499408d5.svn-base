<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form">
        <el-form size="small" ref="queryForm" :inline="true" :model="queryForm" class="demo-form-inline">
          <el-form-item label="名称" prop="name">
            <el-input v-model="queryForm.name" placeholder="请输入名称"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click.native.prevent="handleQuery" :loading="loadingQueryBtn">查询</el-button>
            <el-button @click.native.prevent="_resetForm('queryForm')">重置</el-button>
            <el-button  @click="handleCommissionStandardAdd">佣金标准设置</el-button>
            <!--v-hasOnlyBtn="'commissionStandardSetBtn'"-->
          </el-form-item>

        </el-form>
      </div>
    </div>
    <div class="page-content-bd" v-loading="loadingView">
      <el-table
        :data="tableData"
        border
        highlight-current-row
        align="center"
        style="width: 100%"
        height="580"
      >

        <el-table-column
          prop="companyName"
          align="center"
          label="公司">
        </el-table-column>


        <el-table-column
          prop="name"
          align="center"
          label="名称">
        </el-table-column>

        <el-table-column
          prop="calculateFormulaName"
          align="center"
          label="提成计算方式">
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
        >
          <template slot-scope="scope">
            <el-button v-hasMultipleBtn="['businessTypeUpdateBtn',scope.row]" @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
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
      <b-dialog :show.sync="dialogAddVisible" title="编辑业务类型" width="450px" height="600px">
        <el-form v-loading="loadingForm" :model="addForm" status-icon :rules="addFormRules" ref="addForm" label-width="100px">
          <el-form-item label="类型" prop="calculateFormula">
            <el-select v-model="addForm.calculateFormula" placeholder="请选择" >
              <el-option
                v-for="item in selectType"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item class="margin-b-none">
            <el-button type="primary" :loading="loadingSubmitBtn" @click="handleSubmit">确认</el-button>
            <el-button @click="dialogAddVisible=false">取消</el-button>
          </el-form-item>

        </el-form>
      </b-dialog>

      <!--佣金标准窗体2018-11-15-->
      <el-dialog
        title="新增佣金标准"
        :visible.sync="commissionStandaraAddDialogVisible"
        :close-on-click-modal="false"
        top="30vh"
        width="700px">
        <commission-standard   @handleClick="closeDialog" v-if="hackReset"></commission-standard>
      </el-dialog>


    </template>

  </div>
</template>

<script>
  import PageList from '@/mixins/pageList' // 列表页面查询 mixin
  import * as RequestURL from '@/request/fin/emolument' // 请求后端URL路径
  import OpenCitySelector from '@/components/BaseCascader' // // 城市级联组件
  import BarSelector from '@/components/BaseCascader' // 导航级联组件
  import CommissionStandard  from './commissionStandard' //佣金标准设置
  import {dealAddLog, dealUpdateLog, dealDelLog,dealQueryLog} from '@/request/log/finLog'

  export default {
    name: 'businessType',
    mixins: [PageList],
    components: {OpenCitySelector,BarSelector,CommissionStandard},
    data () {
      return {
        tableData: [],
        queryForm: {},
        addForm: {
          calculateFormula: '',

        },
        // 表单校验配置
        addFormRules: {
          handleResult: [
            {required: true, message: '请输入结果', trigger: 'blur'}
          ]
        },
        dialogAddVisible: false,
        dialogBars: false,
        loadingSubmitBtn: false,
        loadingGrantBtn: false,
        loadingForm: false,
        currentRowData: null, // 当前操作的行数据
        isAdd: false,
        selectType: [],
        nowQueryForm: {},
        hackReset:false,
        commissionStandaraAddDialogVisible:false,//佣金标准
        currBusinessType: '',
        yMethod: '',
      }
    },
    methods: {

      //编辑
      handleEdit(row){
        this._resetForm('addForm')
        this.currentRowData = row ;//
        this.currBusinessType = row.name
        this.yMethod = row.calculateFormulaName
        this.isAdd = false;
        this.dialogAddVisible = true;
        this.$nextTick(() => {
          this._setForm()
        })
      },

      // 提交
      handleSubmit () {

        this.$refs['addForm'].validate((valid) => {
          if (valid) {

            const params = {
              jsonData: JSON.stringify({...this.addForm})
            }
            let currCalculateFormula = this.selectType.find(item=>item.value===String(this.addForm.calculateFormula)).label
            this.$confirm('确定保存编辑的信息吗？, 是否继续?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.loadingSubmitBtn = true
              RequestURL.updateBusinessType(params).then(res => {
                this.loadingSubmitBtn = false
                this.dialogAddVisible = false
                if (res.success) {
                  this.$message({
                    type: 'success',
                    message: res.msg || '操作成功'
                  })
                  this._loadData(false);

                  //写日志
                  let logContent = this.currBusinessType+"的提成计算方式由"+this.yMethod+"变成"+currCalculateFormula
                  let message = {
                    sourceId: params.id,
                    sourceCode: "业务类型："+this.currBusinessType,
                    businessTypeId: 5,//业务类型，
                    sourceTypeId: 11,
                    operatTypeId: 533,
                    logContent: logContent
                  }
                  dealQueryLog({message: JSON.stringify(message)}).then(res => {
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
                this.loadingSubmitBtn = false
                this.$message({
                  type: 'info',
                  message: err.msg || '保存失败'
                })
              })
            })

          }
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
        RequestURL.getBusinessType(params).then(res => {
          this.selectType = []
          for(let key in res.commissionCalculateFormulate){
            let o = {
              value: key,
              label: res.commissionCalculateFormulate[key]
            }
            this.selectType.push(o);
          }
          if(res.companyCommissionCalculateFormulate){
            this.tableData = res.companyCommissionCalculateFormulate.data.map(item => {
            for(let key in res.commissionCalculateFormulate){
              if(item.calculateFormula==key){
                // item.calculateFormula = res.commissionCalculateFormulate[key]
                item.calculateFormulaName = res.commissionCalculateFormulate[key]
              }
            }
            return item;
          })
          this.listQuery.total = res.companyCommissionCalculateFormulate.count
          }else{
            for(let key in res.businessType){
              let item = {
                companyName:'',
                code: key,
                name: res.businessType[key],
                calculateFormula: 0,
                calculateFormulaName: res.commissionCalculateFormulate["0"]
              }
            }
            this.listQuery.total = res.businessType.length
          }

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
          this.addForm['id'] = this.currentRowData['id']
          this.addForm['calculateFormula'] = String(this.currentRowData['calculateFormula'])
        })

        // 移除表单回填时 element ui自动添加的校验效果
        setTimeout(() => {
          this.$refs['addForm'].clearValidate()
        }, 100)
      },
      //打开佣金标准窗体
      handleCommissionStandardAdd(){
        this.hackReset = false
        this.$nextTick(()=>{
          this.hackReset = true
          this.commissionStandaraAddDialogVisible = true;
        })
      },
      //佣金标准窗体关闭回调事件
      closeDialog(value){
        if(value == 1) {
          this.commissionStandaraAddDialogVisible = false
        }else if(value == 2){
          this.commissionStandaraAddDialogVisible = false
          this._loadData(false);
        }
      }
    },
    mounted () {

    }

  }
</script>

<style scoped lang="scss">

</style>
