<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form">
        <el-form size="small" ref="queryForm" :inline="true" :model="queryForm" class="demo-form-inline">
         <el-form-item label="" prop="selectId">
            <base-cascader
                @change="handleChangeRemind"
                v-model="queryForm.selectId"
                :data="peopleSelectOpts" :props="selectProps" :dataProps="selectDataProps" placeholder="部门、员工">
            </base-cascader>
          </el-form-item>
          <el-form-item label="" prop="type">
            <el-select v-model="queryForm.type" placeholder="类型" >
              <el-option
                v-for="item in selectType"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="" prop="classesId">
            <el-select v-model="queryForm.classesId" placeholder="类目" >
              <el-option
                v-for="item in selectClasses"
                :key="item.value"
                :label="item.label"
                :value="item.value" >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="" prop="months">
            <el-date-picker
              v-model="queryForm.months"
              type="month"
              placeholder="选择月份" value-format="yyyy-MM">
            </el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click.native.prevent="handleQuery" :loading="loadingQueryBtn">查询</el-button>
            <el-button @click.native.prevent="_resetQueryForm">重置</el-button>
          </el-form-item>

          <el-form-item class="pull-right">
            <el-button v-hasOnlyBtn="'desertsAddBtn'" type="primary" @click="handleAdd">新增</el-button>
            <a style="margin-left:20px;color:blue;cursor: pointer;">导出</a>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <div class="page-content-bd" v-loading="loadingView">
      <el-table
        :data="tableData"
        border
        :summary-method="getSummaries"
        show-summary
        highlight-current-row
        align="center"
        style="width: 100%"
        
      >

        <el-table-column
          prop="companyName"
          align="center"
          label="公司">
        </el-table-column>

        <el-table-column
          prop="userName"
          align="center"
          label="员工">
        </el-table-column>

        <el-table-column
          prop="typeName"
          align="center"
          label="类型">
        </el-table-column>

        <el-table-column
          prop="classesName"
          align="center"
          label="类目">
        </el-table-column>

        <el-table-column
          prop="reason"
          align="center"
          label="原因">
        </el-table-column>

        <el-table-column
          prop="amount"
          align="center"
          label="金额(元)">
        </el-table-column>

        <el-table-column
          prop="desertsDate"
          align="center"
          :formatter="_timeFormat"
          label="日期">
        </el-table-column>

        <el-table-column
          prop="examineStatusName"
          align="center"
          label="审核状态">
        </el-table-column>

        <el-table-column
          prop="rejectReason"
          align="center"
          label="驳回原因">
        </el-table-column>

        <el-table-column
          prop="auditor"
          align="center"
          label="审核人">
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
        >
          <template slot-scope="scope">
            <el-button v-hasMultipleBtn="['desertsUpdateBtn',scope.row]" v-if="scope.row.examineStatus==0" @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
            <el-button v-hasMultipleBtn="['desertsDelBtn',scope.row]"  v-if="scope.row.examineStatus==0" @click="handleDel(scope.row)" type="text" size="small">删除</el-button>
            <el-button v-hasMultipleBtn="['desertsSureBtn',scope.row]"  v-if="scope.row.examineStatus==0" @click="handleSure(scope.row)" type="text" size="small">通过</el-button>
            <el-button v-hasMultipleBtn="['desertsRejectBtn',scope.row]"  v-if="scope.row.examineStatus==0" @click="handleReject(scope.row)" type="text" size="small">驳回</el-button>
            <!-- <el-button  @click="handleReject(scope.row)" type="text" size="small">驳回</el-button> -->
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
      <b-dialog :show.sync="dialogAddVisible" :title="title" width="550px" height="500px">
        <el-form v-loading="loadingForm" :model="addForm" status-icon :rules="addFormRules" ref="addForm" label-width="100px">
          <el-row :gutter="20">
            <el-col :span="12">
               <el-form-item label="类型" prop="type">
                <el-radio v-model="addForm.type"  label="1">奖</el-radio>
                <el-radio v-model="addForm.type"  label="-1">罚</el-radio>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="类目" prop="classesId">
                <el-select v-model="addForm.classesId" placeholder="请选择" style="width:150px">
                  <el-option
                    v-for="item in selectClasses"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="员工" prop="userId" >
                <base-cascader
                  v-model="addForm.userId"
                  :changeOnSelect="false"
                  :data="peopleSelectOpts"
                  :props="selectProps"
                  :dataProps="selectDataProps" @change="onChange" style="width:150px">
                </base-cascader>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="金额（元）" prop="amount">
                <el-input v-model="addForm.amount" style="width:150px"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="奖罚时间" prop="desertsDate">
                <el-date-picker
                  v-model="addForm.desertsDate"
                  type="date"
                  placeholder="选择日期" value-format="yyyy-MM-dd" style="width:150px">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="原因" prop="reason">
                <el-input v-model="addForm.reason" style="width:150px"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          
          <el-form-item class="margin-b-none">
            <el-button type="primary" :loading="loadingSubmitBtn" @click="handleSubmit">确认</el-button>
            <el-button @click="dialogAddVisible=false">取消</el-button>
          </el-form-item>

        </el-form>
      </b-dialog>
      <b-dialog :show.sync="dialogRejectReason" title="驳回原因" width="400px">
        <el-form v-loading="loadingForm" label-width="100px" :model="rejectForm" ref="rejectForm" :rules="rejectRules">

          <el-form-item label="驳回原因" prop="rejectReason">
            <el-input v-model="rejectForm.rejectReason"></el-input>
          </el-form-item>

          <el-form-item class="margin-b-none">
            <el-button type="primary" :loading="loadingRejectBtn" @click="rejectSave">确认</el-button>
            <el-button  @click="dialogRejectReason=false">取消</el-button>
          </el-form-item>

        </el-form>
      </b-dialog>

    </template>

  </div>
</template>

<script>
  import PageList from '@/mixins/pageList' // 列表页面查询 mixin
  import * as RequestURL from '@/request/fin/emolument' // 请求后端URL路径
  import OpenCitySelector from '@/components/BaseCascader' // // 城市级联组件
  import BarSelector from '@/components/BaseCascader' // 导航级联组件
  import BaseCascader from '@/components/BaseCascader'
  import {queryReferenceUserSelect} from '@/request/manage/common'
  import {dealAddLog, dealUpdateLog, dealDelLog,dealQueryLog} from '@/request/log/finLog'

  export default {
    name: 'desertsInfo',
    mixins: [PageList],
    components: {OpenCitySelector,BarSelector,BaseCascader},
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
      return {
        peopleSelectOpts: [], // 相关用户员工级联选择数据
        selectDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        selectProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          children: 'children', // 指定选项的子选项为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        selectType: [
          {
            value:'1',
            label: '奖'
          },
          {
            value:'-1',
            label: '罚'
          }
        ],
        selectClasses: [],
        queryForm: {months:''},
        getReferenceUserSelectUrl: '/manage/usermanage/queryOpenCityCompanyDepartUser', // 选员工
        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        addForm: {
          type: '',
          classesId: '',
          classesName: '',
          userId: '',
          userName: '',
          reason: '',
          desertsDate: '',
          amount: ''
        },
        // 表单校验配置
        addFormRules: {
          type: [
            {required: true, message: '请选择', trigger: 'blur'}
          ],
          classesId: [
            {required: true, message: '请选择', trigger: 'blur'}
          ],
          userId: [
            {required: true, message: '请选择', trigger: 'blur'}
          ],
          amount: [
            {required: true, message: '请填值', trigger: 'blur'},
            { validator: money, trigger: 'blur' }
          ],
          desertsDate: [
            {required: true, message: '请选择', trigger: 'blur'}
          ]
          
        },
        rejectRules:{
            'rejectReason':[
              { required: true, message: '请填写原因', trigger: 'blur' }
            ],
           
            },
        dialogAddVisible: false,
        dialogBars: false,
        dialogRejectReason:false,
        loadingSubmitBtn: false,
        loadingGrantBtn: false,
        loadingForm: false,
        loadingRejectBtn: false,
        currentRowData: null, // 当前操作的行数据
        isAdd: false,
        idForBar: '',
        barData: [],
        levelInfo1: [],
        levelInfo2: [],
        defaultProps: {
          children: 'children',
          label: 'label'
        },
        keys: [],
        rejectReason: '',
        title: '新增奖罚信息',
        nowQueryForm: {},
        rejectForm: {},
        idForReject: '',
        userId: '',
        originalData:[]
      }
    },
    methods: {
      _resetQueryForm(){
        this._resetForm('queryForm')
        this.queryForm.deptId = ''
        this.queryForm.userId = ''
        this.queryForm.selectId = ''
      },
      
      // 获取部门或人
      handleChangeRemind (val, name, data) {
        if (data.dataType == 'dept') {
          this.queryForm.deptId = data.code
          delete this.queryForm.userId
          delete this.queryForm.selectId

        }else if(data.dataType == 'user'){
          this.queryForm.userId = data.code
           delete this.queryForm.deptId
          delete this.queryForm.selectId
        }else{
          setTimeout(() => {
            this.queryForm.deptId = ''
            this.queryForm.userId = ''
            this.queryForm.selectId = ''
          }, 50)
        }
      },
      //获取奖罚类目下拉项
      querySelectValue(){
        const  params = {type: 'one',param: 'deserts'}
        RequestURL.querySelectValue(params).then(res => {
          for(let i=0;i<res.data.length;i++){
            let item = {
              value: res.data[i].id,
              label: res.data[i].fieldValue
            }
            this.selectClasses.push(item);
          }

        })
      },

      //新增
      handleAdd(){
        this._resetForm('addForm')
        this.currentRowData = null ;// 置空当前操作的行数据
        this.isAdd = true;
        this.title = '新增奖罚信息';
        this.dialogAddVisible = true;
      },
      //编辑
      handleEdit(row){
        this._resetForm('addForm')
        this.currentRowData = row ;// 置空当前操作的行数据
        this.isAdd = false;
        this.title = '编辑奖罚信息';
        this.dialogAddVisible = true;
        this.$nextTick(() => {
          this._setForm()
        })
      },
      // 删除
      handleDel (row) {
        this.$confirm('确定删除该条数据？, 是否继续?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          RequestURL.delUserDesertsInfo({id: row.id}).then(res => {
            this.$message({
              type: 'success',
              message: res.msg || '删除成功'
            })
            //this._loadData(false);
            for(let i=0;i<this.tableData.length;i++){
              if(this.tableData[i].id==row.id){
                this.tableData.splice(i,1)
              }
            }
            //记日志
            let logContent = "删除"+row.typeName+":"+row.classesName+",金额："+row.amount+"元"
            let message = {
            sourceId: row.id,
            sourceCode: "用户id："+row.userId,
            businessTypeId: 5,//业务类型，
            sourceTypeId: 10,
            operatTypeId: 539,
            logContent: logContent
          }
          dealDelLog({message: JSON.stringify(message)}).then(res => {
            console.log(res)
          })
          })
        })
      },
      //审核通过
      handleSure(row){
        let params = {
          id: row.id,
          examineStatus: '1'
        }
        RequestURL.examineUserDesertsInfo(params).then(res => {
          console.log(res)
           if (res.success) {
                this.$message({
                  type: 'success',
                  message: res.msg || '操作成功'
                })
                
                this.tableData.find(item => item.id === row.id).examineStatusName = "已审核"
                this.tableData.find(item => item.id === row.id).auditor = res.data
                this.tableData.find(item => item.id === row.id).examineStatus = '1'
              }
              //记日志
              let logContent = "奖罚确认"
              let message = {
              sourceId: row.id,
              sourceCode: "用户id："+row.userId,
              businessTypeId: 5,//业务类型，
              sourceTypeId: 10,
              operatTypeId: 546,
              logContent: logContent
            }
            dealQueryLog({message: JSON.stringify(message)}).then(res => {
              console.log(res)
            })
        }).catch((err) => {
            console.log(err);
            this.$message({
              type: 'info',
              message: err.msg || '操作失败'
            })
          })
      },
      //驳回弹框
      handleReject(row){
        this._resetForm('rejectForm')
        this.idForReject = row.id;
        this.userId = row.userId
        this.dialogRejectReason = true;
      },

      // 驳回确认
      rejectSave () {
        this.$refs['rejectForm'].validate((valid) => {
            if (valid) {
                let params = {
                id: this.idForReject,
                examineStatus: '2',
                rejectReason: this.rejectForm.rejectReason
              }
              this.$confirm('确定保存编辑的信息吗？, 是否继续?', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                this.loadingRejectBtn = true;
                RequestURL.examineUserDesertsInfo(params).then(res => {
                  this.loadingRejectBtn = false;
                  this.dialogRejectReason = false
                  if (res.success) {
                    this.$message({
                      type: 'success',
                      message: res.msg || '操作成功'
                    })
                    this._loadData(false);
                    //记日志
                    let logContent = "奖罚驳回"
                    let message = {
                    sourceId: this.idForReject,
                    sourceCode: "用户id："+this.userId,
                    businessTypeId: 5,//业务类型，
                    sourceTypeId: 10,
                    operatTypeId: 547,
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
                  this.loadingRejectBtn = false
                  this.dialogRejectReason = false
                  this.$message({
                    type: 'info',
                    message: err.msg || '保存失败'
                  })
                })
              })
            }
        })
        

      },

      // 提交
      handleSubmit () {

        this.$refs['addForm'].validate((valid) => {
          if (valid) {
            for(let i=0;i<this.selectClasses.length;i++){
              if(this.addForm.classesId == this.selectClasses[i].value){
                this.addForm.classesName = this.selectClasses[i].label;
              }
            }
            
            if(this.isAdd){
              delete  this.addForm.id
            }
            const params = {
              jsonData: JSON.stringify({...this.addForm})
            }
            this.$confirm('确定保存编辑的信息吗？, 是否继续?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.loadingSubmitBtn = true
              RequestURL.saveOrUpdateUserDesertsInfo(params).then(res => {
                this.loadingSubmitBtn = false
                this.dialogAddVisible = false
                if (res.success) {
                  this.$message({
                    type: 'success',
                    message: res.msg || '操作成功'
                  })
                  this._loadData(false);
                  //记日志
                  if(this.isAddDetail){//新增
                      //记日志
                      let logContent = "新增奖罚"
                      let message = {
                      sourceId: '',
                      sourceCode: "用户id："+this.addForm.userId,
                      businessTypeId: 5,//业务类型，
                      sourceTypeId: 10,
                      operatTypeId: 537,
                      logContent: logContent
                    }
                    dealAddLog({message: JSON.stringify(message)}).then(res => {
                      console.log(res)
                    })
                  }else{//编辑
                      let message = {
                      sourceId: this.addForm.id,
                      sourceCode: "用户id："+this.addForm.userId,
                      businessTypeId: 5,//业务类型，
                      sourceTypeId: 10,
                      operatTypeId: 538,
                      labelData: this.$utils.getFormFields(this.$refs['addForm']),
                      originalData: this.originalData,
                      newData: JSON.parse(params.jsonData)
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

      onChange (val, label, data) {
        // alert(111);
        console.log(data)
       // alert(data.parent.name)
        this.addForm.userName = data.name;
        this.addForm.companyId = data.companyId
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
        if(!btnQuery){
            let date = new Date();
            let year = date.getFullYear();
            let month = date.getMonth();
            if(month<10){
              month = "0"+ month
            }
            let months = year+"-"+month
            this.nowQueryForm.months = months
        }
        let params = Object.assign({}, this.nowQueryForm, {
          limit: this.listQuery.limit,
          page: this.listQuery.page
        })
        //const params = this._getParams(this.queryForm)
        RequestURL.userDesertsInfoDatas(params).then(res => {
          //console.log(11111)
          //console.log(res);
          this.tableData = res.data.map(item => {
            item.amount = item.amount / 100
            if(item.type==1){
                item.typeName = '奖'
            }else{
                item.typeName = '罚'
            }
            if(item.examineStatus==0){
                item.examineStatusName = '未审核'
            }else if(item.examineStatus==1){
                item.examineStatusName = '已审核'
            }else if(item.examineStatus==2){
                item.examineStatusName = '已驳回'
            }
            return item;
          })
          //console.log(this.tableData);
          this.listQuery.total = res.count
          this.loadingQueryBtn = false
          this.loadingView = false

        }).catch(() => {
          this.loadingQueryBtn = false
          this.loadingView = false
        })
      },
      // 表单回填
      _setForm () {
        console.log(this.currentRowData)
        // 拷贝为修改过的原始表单数据
        this.originalData = Object.assign({}, this.addForm)
        this.currentRowData.desertsDate = this.$utils.timestampToTime(this.currentRowData.desertsDate)
        this.currentRowData.type = String(this.currentRowData.type)
        this.currentRowData.userId = String(this.currentRowData.userId)

        this.$utils.setFormInfo(this.addForm, this.currentRowData, () => {
          this.addForm['id'] = this.currentRowData['id']
        })

        // 移除表单回填时 element ui自动添加的校验效果
        setTimeout(() => {
          this.$refs['addForm'].clearValidate()
        }, 100)
      },
      
      getSummaries(param) {
        const { columns, data } = param;
        const sums = [];
        columns.forEach((column, index) => {
          if (index === 0) {
            sums[index] = '合计';
            return;
          }
          const values = data.map(item => Number(item[column.property]));
          if (column.property=='amount') {
            sums[index] = values.reduce((prev, curr) => {
              const value = Number(curr);
              if (!isNaN(value)) {
                return prev + curr;
              } else {
                return prev;
              }
            }, 0);
            sums[index] += ' 元';
          } else {
            sums[index] = '';
          }
        });

        return sums;
      }
    
    },
    mounted () {
       //人员选择
      queryReferenceUserSelect({}).then(res => {
        
        this.peopleSelectOpts = res
      })
      this.querySelectValue();
      let date = new Date();
      let year = date.getFullYear();
      let month = date.getMonth();
      if(month<10){
        month = "0"+ month
      }
      let months = year+"-"+month
      this.queryForm.months = months;
    }

  }
</script>

<style scoped lang="scss">

</style>
