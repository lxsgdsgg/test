<template>
  <div class="page-content">
    <el-row :gutter="20">
      <el-col :span="12">
        <div class="grid-content">
          <div class="page-content-hd">
            <div class="query-form">
              <el-form size="small" ref="queryForm" :inline="true" :model="queryForm" class="demo-form-inline">

                <el-form-item label="名称" prop="name">
                  <el-input v-model="queryForm.name" placeholder="请输入名称"></el-input>
                </el-form-item>

                <el-form-item>
                  <el-button type="primary" @click.native.prevent="handleQuery" :loading="loadingQueryBtn">查询</el-button>
                  <el-button @click.native.prevent="_resetForm('queryForm')">重置</el-button>
                </el-form-item>
                <el-form-item class="pull-right">
                  <el-button v-hasOnlyBtn="'ssfAddBtn'" type="primary" @click="handleAddConfig">新增社保方案</el-button>
                </el-form-item>
              </el-form>
            </div>
          </div>
          <div class="page-content-bd" v-loading="loadingView">
            <el-table
              :data="tableData"
              border
              ref="multipleTable"
              @row-click="_showValue"
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
                align="center"
                label="操作"
              >
                <template slot-scope="scope">
                  <el-button v-hasMultipleBtn="['ssfUpdateBtn',scope.row]" @click="handleEditConfig(scope.row)" type="text" size="small">编辑</el-button>
                  <el-button v-hasMultipleBtn="['ssfDelBtn',scope.row]" @click="delConfig(scope.row)" type="text" size="small">删除</el-button>
                </template>
              </el-table-column>

            </el-table>

            <b-pagination
              :listQuery="listQuery"
              @handleSizeChange="handleSizeChange"
              @handleCurrentChange="handleCurrentChange">
            </b-pagination>
          </div>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="grid-content">
          <div class="page-content-hd">
            <div class="query-form">
              <el-form size="small" :inline="true" class="demo-form-inline">

                <el-form-item label="社保明细项" prop="name">

                </el-form-item>

                <el-form-item class="pull-right">
                  <el-button v-hasOnlyBtn="'ssfAddBtn'" type="primary" @click="handleAddDetail">新增社保明细</el-button>
                </el-form-item>
              </el-form>
            </div>
          </div>
          <div class="page-content-bd" v-loading="loadingItemView">
            <el-table
              :data="valueData"
              border
              highlight-current-row
              align="center"
              style="width: 100%"
              height="580"
            >

              <el-table-column
                prop="name"
                align="center"
                label="名称">
              </el-table-column>

              <el-table-column
                prop="ssfCardinality"
                align="center"
                label="缴费基数(元/月)">
              </el-table-column>

              <el-table-column
                prop="companyRate"
                align="center"
                label="公司缴费比例(%)">
              </el-table-column>

              <el-table-column
                prop="companyAmount"
                align="center"
                label="公司缴费金额(元/月)">
              </el-table-column>

              <el-table-column
                prop="privateRate"
                align="center"
                label="个人缴费比例(%)">
              </el-table-column>

              <el-table-column
                prop="privateAmount"
                align="center"
                label="个人缴费金额(元/月)">
              </el-table-column>

              <el-table-column
                align="center"
                label="操作"
              >
                <template slot-scope="scope">
                  <el-button v-hasMultipleBtn="['ssfUpdateBtn',scope.row]"  @click="handleEditDetail(scope.row)" type="text" size="small">编辑</el-button>
                  <el-button v-hasMultipleBtn="['ssfDelBtn',scope.row]"  @click="delDetail(scope.row)" type="text" size="small">删除</el-button>
                </template>
              </el-table-column>

            </el-table>

          </div>



        </div>
      </el-col>
    </el-row>



    <template>
      <b-dialog :show.sync="dialogAddConfig" :title="configTitle" width="400px">
        <el-form v-loading="loadingForm" :model="addConfigForm" status-icon :rules="addConfigFormRules" ref="addConfigForm" label-width="100px">

          <el-form-item label="名称" prop="name">
            <el-input v-model="addConfigForm.name"></el-input>
          </el-form-item>

          <el-form-item class="margin-b-none">
            <el-button type="primary" :loading="loadingSaveConfigBtn" @click="saveConfig">确认</el-button>
            <el-button @click="dialogAddConfig=false">取消</el-button>
          </el-form-item>

        </el-form>
      </b-dialog>
      <b-dialog :show.sync="dialogAddDetail" :title="detailTitle" width="600px">
        <el-form v-loading="loadingForm" :model="addDetailForm" status-icon size="mini"
                 :rules="addDetailFormRules" ref="addDetailForm" label-width="150px" style="padding-right:30px">
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="名称" prop="name">
                <el-input v-model="addDetailForm.name" style="width:120px"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="缴费基数(元/月)" prop="ssfCardinality">
                <el-input v-model="addDetailForm.ssfCardinality" @blur="myBlur1" style="width:120px"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          
          <el-row :gutter="20">
            <el-col :span="12">
                <el-form-item label="公司缴费比例(%)" prop="companyRate">
                  <el-input type="number" v-model="addDetailForm.companyRate"  :max="100" :step='1' @blur="myBlur2" @change="myBlur2" style="width:120px"></el-input>
                </el-form-item>
            </el-col> 
            <el-col :span="12">
              <el-form-item label="公司缴费金额(元/月)" prop="companyAmount">
                <el-input v-model="addDetailForm.companyAmount" :readonly="true" style="width:120px"></el-input>
              </el-form-item>
            </el-col> 
          </el-row> 
         <el-row :gutter="20">
            <el-col :span="12">
               <el-form-item label="个人缴费比例(%)" prop="privateRate">
                <el-input type="number" v-model="addDetailForm.privateRate"  :max="100"  :step='1' @blur="myBlur3" @change="myBlur3" style="width:120px"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="个人缴费金额(元/月)" prop="privateAmount">
                <el-input v-model="addDetailForm.privateAmount" :readonly="true" style="width:120px"></el-input>
              </el-form-item>
            </el-col>
         </el-row>
          
          <el-form-item class="margin-b-none">
            <el-button type="primary" :loading="loadingSaveDetailBtn" @click="saveDetail">确认</el-button>
            <el-button @click="dialogAddDetail=false">取消</el-button>
          </el-form-item>

        </el-form>
      </b-dialog>
    </template>

  </div>
</template>

<script>
  import PageList from '@/mixins/pageList' // 列表页面查询 mixin
  import * as RequestURL from '@/request/fin/emolument' // 请求后端URL路径
  import BaseCityCascader from '@/components/BaseCascader/city' // 城市级联组件
  import {dealAddLog, dealUpdateLog, dealDelLog,dealQueryLog} from '@/request/log/finLog'

  export default {
    name: 'ssfConfigurate',
    mixins: [PageList],
    components: {BaseCityCascader},
    data () {
      // 自定义金额验证
      var money = (rule, value, callback) => {
        let reg = /^[0-9]+/
        if(!value){
          return callback(new Error('金额不能为空'))
        }
        if(value<=0){
           return callback(new Error('金额需大于0'))
        }
        if (reg.test(value)) {
          callback();
        }
        return callback(new Error('只能输入数字或小数'));
      };
      var intNumber = (rule, value, callback) => {
        let reg = /^-?[0-9]\d*$/
        
        if(value<=0){
           return callback(new Error('比例需大于0'))
        }
        if (reg.test(value)) {
          callback();
        }
        return callback(new Error('只能输入整数'));
      };
      
      return {
        queryForm: {
        },
        addConfigForm: {
          name: '',
        },
        addDetailForm: {
          name: '',
          ssfCardinality: '',
          companyRate: '',
          companyAmount: '',
          privateRate: '',
          privateAmount: ''
        },
        // 表单校验配置
        addConfigFormRules: {
          name: [
            {required: true, message: '请输入名称', trigger: 'blur'}
          ],
        },
        // 表单校验配置
        addDetailFormRules: {
          name: [
            {required: true, message: '请输入名称', trigger: 'blur'}
          ],
          ssfCardinality: [
            {required: true, message: '请输入基数', trigger: 'blur'},
            {validator: money, trigger: 'blur' }
          ],
          companyRate: [
            {required: true, message: '请输入公司缴费比例', trigger: 'blur'},
            {validator: intNumber, trigger: 'blur' }
          ],
          privateRate: [
            {required: true, message: '请输入个人比例', trigger: 'blur'},
            {validator: intNumber, trigger: 'blur' }
          ],

        },
        companyOpts: [],
        dialogAddConfig: false,
        dialogAddDetail: false,
        loadingSaveConfigBtn: false,
        loadingSaveDetailBtn: false,
        loadingForm: false,
        loadingItemView: false,
        currentRowData: null, // 当前操作的行数据
        currentConfigItem:null,
        valueData: [],
        isAddConfig: false,
        isAddDetail: false,
        configTitle: '新增社保方案',
        detailTitle: '新增社保明细',
        nowQueryForm: {},
        originalConfigData: [],
        originalDetailData: []
      }
    },
    methods: {
      // 新增方案
      handleAddConfig (row) {
        this._resetForm('addConfigForm')
        this.currentConfigItem = null // 置空当前操作的行数据
        this.isAddConfig = true;
        this.configTitle = '新增社保方案'
        this.dialogAddConfig = true;
        delete this.addConfigForm.id
      },
      // 新增明细
      handleAddDetail (row) {
        this._resetForm('addDetailForm')
        this.currentRowData = null // 置空当前操作的行数据
        this.isAddDetail = true;
        this.detailTitle = '新增社保明细'
        this.dialogAddDetail = true;
        delete this.addDetailForm.id
      },
      // 编辑方案
      handleEditConfig (row) {
        this._resetForm('addConfigForm')
        this.currentConfigItem = row
        this.isAddConfig = false
        this.configTitle = '编辑社保方案'
        this.dialogAddConfig = true
        this.$nextTick(() => {
          this._setConfigForm()
        })
      },
      // 编辑明细
      handleEditDetail (row) {
        this._resetForm('addDetailForm')
        this.currentRowData = row
        this.isAddDetail = false
        this.detailTitle = '编辑社保明细'
        this.dialogAddDetail = true
        this.$nextTick(() => {
          this._setDetailForm()
        })
      },
      myBlur1(){
        if(this.addDetailForm.ssfCardinality!=""){
          this.addDetailForm.companyAmount = (this.addDetailForm.ssfCardinality * this.addDetailForm.companyRate/100).toFixed(2)
          this.addDetailForm.privateAmount = (this.addDetailForm.ssfCardinality * this.addDetailForm.privateRate/100).toFixed(2)
          
        }
      },
      myBlur2(){
        if(this.addDetailForm.ssfCardinality!="" && this.addDetailForm.companyRate!=""){
          this.addDetailForm.companyAmount = (this.addDetailForm.ssfCardinality * this.addDetailForm.companyRate/100).toFixed(2)
        }
      },
      myBlur3(){
        if(this.addDetailForm.ssfCardinality!="" && this.addDetailForm.privateRate!=""){
          this.addDetailForm.privateAmount = (this.addDetailForm.ssfCardinality * this.addDetailForm.privateRate/100).toFixed(2)
        }
      },
      // 提交方案
      saveConfig () {

        this.$refs['addConfigForm'].validate((valid) => {
          if (valid) {
            const params = {
              name: this.addConfigForm.name
            }
            if(!this.isAddConfig){
              params.id = this.addConfigForm.id
            }
            this.$confirm('确定保存编辑的信息吗？, 是否继续?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.loadingSaveConfigBtn = true
              RequestURL.saveOrUpdateSsfConfigurate(params).then(res => {
                console.log(res)
                this.loadingSaveConfigBtn = false
                this.dialogAddConfig = false
                if (res.success) {
                  this.$message({
                    type: 'success',
                    message: res.msg || '操作成功'
                  })
                  this._loadData(false);
                  //记日志
                  if(this.isAddConfig){//新增
                      let logContent = "新增社保配置"
                      let message = {
                      sourceId: '',
                      sourceCode: this.addConfigForm.name,
                      businessTypeId: 5,//业务类型，
                      sourceTypeId: 11,
                      operatTypeId: 516,
                      logContent: logContent
                    }
                    dealAddLog({message: JSON.stringify(message)}).then(res => {
                      console.log(res)
                    })
                  }else{//编辑
                      let message = {
                      sourceId: this.addConfigForm.id,
                      sourceCode: this.addConfigForm.name,
                      businessTypeId: 5,//业务类型，
                      sourceTypeId: 11,
                      operatTypeId: 517,
                      labelData: this.$utils.getFormFields(this.$refs['addConfigForm']),
                      originalData: this.originalConfigData,
                      newData: JSON.parse(JSON.stringify(params))
                    }

                    dealUpdateLog({message: JSON.stringify(message)}).then(res => {
                      console.log(res)
                    })
                }
                }else{
                  this.$message({
                    type: 'warning',
                    message: res.msg
                  })
                }
              }).catch((err) => {
                console.log(err);
                this.loadingSaveConfigBtn = false
                this.dialogAddConfig = false
                this.$message({
                  type: 'info',
                  message: err.msg || '保存失败'
                })
              })
            })

          }
        })
      },
      // 提交明细
      saveDetail () {

        this.$refs['addDetailForm'].validate((valid) => {
          if (valid) {
            const params = {
              name: this.addDetailForm.name,
              ssfCardinality: this.addDetailForm.ssfCardinality,
              companyRate:this.addDetailForm.companyRate,
              privateRate: this.addDetailForm.privateRate,
              ssfId: this.currentConfigItem.id
            }
            if(!this.isAddDetail){
              params.id = this.addDetailForm.id
            }
            this.$confirm('确定保存编辑的信息吗？, 是否继续?','提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.loadingSaveDetailBtn = true
              RequestURL.saveOrupdateSsfConfigurateDetail(params).then(res => {
                this.loadingSaveDetailBtn = false
                this.dialogAddDetail = false
                if (res.success) {
                  this.$message({
                    type: 'success',
                    message: res.msg || '操作成功'
                  })
                  //this._loadData(false);
                  if(this.isAddDetail){
                    let item = {
                      id: res.data,
                      name: this.addDetailForm.name,
                      ssfCardinality: this.addDetailForm.ssfCardinality,
                      companyRate: this.addDetailForm.companyRate,
                      companyAmount: this.addDetailForm.companyAmount,
                      privateRate: this.addDetailForm.privateRate,
                      privateAmount: this.addDetailForm.privateAmount,
                      ssfUpdateBtn: this.currentConfigItem.ssfUpdateBtn,
                      ssfDelBtn:  this.currentConfigItem.ssfDelBtn
                    }
                    this.valueData.push(item)
                  }else{
                    this.valueData.map(item=>{
                      if(item.id == this.currentRowData.id){
                        item.name = this.addDetailForm.name
                        item.ssfCardinality = this.addDetailForm.ssfCardinality
                        item.companyRate =this.addDetailForm.companyRate
                        item.companyAmount = this.addDetailForm.companyAmount
                        item.privateRate = this.addDetailForm.privateRate
                        item.privateAmount = this.addDetailForm.privateAmount
                      }
                      return item
                    })
                  }  
                  this.$refs.multipleTable.setCurrentRow(this.currentConfigItem);
                   //记日志
                  if(this.isAddDetail){//新增
                      let logContent = "为社保方案："+this.currentConfigItem.name+"新增明细项"
                      let message = {
                      sourceId: '',
                      sourceCode: this.addDetailForm.name,
                      businessTypeId: 5,//业务类型，
                      sourceTypeId: 11,
                      operatTypeId: 519,
                      logContent: logContent
                    }
                    dealAddLog({message: JSON.stringify(message)}).then(res => {
                      console.log(res)
                    })
                  }else{//编辑
                      let message = {
                      sourceId: this.addDetailForm.id,
                      sourceCode: this.addDetailForm.name,
                      businessTypeId: 5,//业务类型，
                      sourceTypeId: 11,
                      operatTypeId: 520,
                      labelData: this.$utils.getFormFields(this.$refs['addDetailForm']),
                      originalData: this.originalDetailData,
                      newData: JSON.parse(JSON.stringify(params))
                    }

                    dealUpdateLog({message: JSON.stringify(message)}).then(res => {
                      console.log(res)
                    })
                  }
                }else{
                  this.$message({
                    type: 'warning',
                    message: res.msg
                  })
                }
                this.dialogAddDetail = false
              }).catch((err) => {
                console.log(err);
                this.loadingSaveDetailBtn = false
                this.$message({
                  type: 'info',
                  message: err.msg || '保存失败'
                })
              })
            })

          }
        })
      },
      // 删除
      delConfig (row) {
        this.$confirm('确定删除该条数据？, 是否继续?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          RequestURL.delSsfConfigurate({id: row.id}).then(res => {
            this.$message({
              type: 'success',
              message: res.msg || '删除成功'
            })
            //this._loadValueData(this.currentDynamicConfigureItem.widgetCode,this.currentDynamicConfigureItem.type);
            for(let i=0;i<this.tableData.length;i++){
              if(this.tableData[i].id==row.id){
                this.tableData.splice(i,1);
              }
            }
            //记日志
            let logContent = "删除社保配置"
            let message = {
            sourceId: row.id,
            sourceCode: row.name,
            businessTypeId: 5,//业务类型，
            sourceTypeId: 11,
            operatTypeId: 518,
            logContent: logContent
          }
          dealDelLog({message: JSON.stringify(message)}).then(res => {
            console.log(res)
          })

          })
        })
      },
      // 删除
      delDetail (row) {
        this.$confirm('确定删除该条数据？, 是否继续?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          RequestURL.delSsfConfigurateDetail({id: row.id}).then(res => {
            this.$message({
              type: 'success',
              message: res.msg || '删除成功'
            })
            //this._loadValueData(this.currentDynamicConfigureItem.widgetCode,this.currentDynamicConfigureItem.type);
            for(let i=0;i<this.valueData.length;i++){
              if(this.valueData[i].id==row.id){
                this.valueData.splice(i,1);
              }
            }
            //记日志
            let logContent = "删除社保明细"
            let message = {
            sourceId: row.id,
            sourceCode: row.name,
            businessTypeId: 5,//业务类型，
            sourceTypeId: 11,
            operatTypeId: 521,
            logContent: logContent
          }
          dealDelLog({message: JSON.stringify(message)}).then(res => {
            console.log(res)
          })
          })
        })
      },
      handleChangeCity (val, name) {
        this.editForm['areaName'] = name
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
        RequestURL.ssfConfigurateAll(params).then(res => {
          console.log(res);
          this.tableData = res.data
          console.log(this.tableData);
          this.listQuery.total = res.count
          this.loadingQueryBtn = false
          this.loadingView = false
          this.currentConfigItem = res.data[0];
          this.$refs.multipleTable.setCurrentRow(this.tableData[0]);
          this._loadValueData(res.data[0].datas,res.data[0].ssfUpdateBtn,res.data[0].ssfDelBtn);

        }).catch(() => {
          this.loadingQueryBtn = false
          this.loadingView = false
        })
      },
      _loadValueData(data,ssfUpdateBtn,ssfDelBtn){
        this.valueData = [];
        if(data){
            this.valueData = JSON.parse(data).map(item => {
              if(ssfUpdateBtn){
                item.ssfUpdateBtn = ssfUpdateBtn
              }
              if(ssfDelBtn){
                item.ssfDelBtn = ssfDelBtn
              }
              return item
            });
        }
        
      },
      // 表单回填
      _setConfigForm () {
        // 拷贝为修改过的原始表单数据
        this.originalConfigData = Object.assign({}, this.addConfigForm)
        this.$utils.setFormInfo(this.addConfigForm, this.currentConfigItem, () => {
          this.addConfigForm['id'] = this.currentConfigItem['id']
        })

        // 移除表单回填时 element ui自动添加的校验效果
        setTimeout(() => {
          this.$refs['addForm'].clearValidate()
        }, 100)
      },
      _setDetailForm(){
        // 拷贝为修改过的原始表单数据
        this.originalDetailData = Object.assign({}, this.addDetailForm)
        this.$utils.setFormInfo(this.addDetailForm, this.currentRowData, () => {
          this.addDetailForm['id'] = this.currentRowData['id']
        })

        // 移除表单回填时 element ui自动添加的校验效果
        setTimeout(() => {
          this.$refs['addDetailForm'].clearValidate()
        }, 100)
      },
      _showValue(row, event, column){
        //console.log(row.widgetCode)
        this.currentConfigItem = row;
        this._loadValueData(row.datas,row.ssfUpdateBtn,row.ssfDelBtn);

      }
    },
    mounted () {

    }
  }
</script>

<style scoped lang="scss">
  .value-title{
    height: 34px;
    line-height: 34px;
  }
</style>
