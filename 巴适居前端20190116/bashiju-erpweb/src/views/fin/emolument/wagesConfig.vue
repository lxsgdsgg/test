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
          <el-form-item label="" prop="welfareId">
            <el-select v-model="queryForm.welfareId" placeholder="福利方案" >
              <el-option
                v-for="item in selectWelfare"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="" prop="ssfConfigId">
            <el-select v-model="queryForm.ssfConfigId" placeholder="社保方案" >
              <el-option
                v-for="item in selectSsfConfig"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="" prop="agentComConfigId">
            <el-select v-model="queryForm.agentComConfigId" placeholder="中介类提成方案" >
              <el-option
                v-for="item in selectAgentComConfig"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="" prop="financeComConfigId">
            <el-select v-model="queryForm.financeComConfigId" placeholder="金融类提成方案" >
              <el-option
                v-for="item in selectFinanceComConfig"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click.native.prevent="handleQuery" :loading="loadingQueryBtn">查询</el-button>
            <el-button @click.native.prevent="_resetQueryForm">重置</el-button>
          </el-form-item>

          <!-- <el-form-item class="pull-right">
            <el-button type="primary" @click="handleAdd">新增</el-button>
          </el-form-item> -->
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
          prop="deptName"
          align="center"
          label="部门">
        </el-table-column>

        <el-table-column
          prop="userName"
          align="center"
          label="员工">
        </el-table-column>

        <el-table-column
          prop="basePay"
          align="center"
          label="基本工资">
        </el-table-column>

        <el-table-column
          prop="welfareName"
          align="center"
          label="福利方案">
        </el-table-column>

        <el-table-column
          prop="ssfConfigName"
          align="center"
          label="社保方案">
        </el-table-column>

        <el-table-column
          prop="agentComConfigName"
          align="center"
          label="中介类提成方案">
        </el-table-column>

        <el-table-column
          prop="financeComConfigName"
          align="center"
          label="金融类提成方案">
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
        >
          <template slot-scope="scope">
            <el-button v-hasMultipleBtn="['wagesUpdateBtn',scope.row]" @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
            <!-- <el-button v-hasMultipleBtn="['wagesDelBtn',scope.row]" @click="handleDel(scope.row)" type="text" size="small">删除</el-button> -->
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
      <b-dialog :show.sync="dialogAddVisible" :title="title" width="750px" height="600px">
        <el-form v-loading="loadingForm" :model="addForm" status-icon :rules="addFormRules" ref="addForm" label-width="120px">
           <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="员工" prop="userId" >
                <base-cascader
                  v-model="addForm.userId"
                  :changeOnSelect="false"
                  :data="peopleSelectOpts"
                  :props="selectProps"
                  :dataProps="selectDataProps" @change="onChange">
                </base-cascader>
              </el-form-item>
            </el-col>
            <el-col :span="12">
               <el-form-item label="基本工资" prop="basePay">
                <el-input v-model="addForm.basePay"></el-input>
              </el-form-item>
            </el-col>
           </el-row>
           <el-row :gutter="20">
            <el-col :span="12">
               <el-form-item label="福利方案" prop="welfareId">
                <el-select v-model="addForm.welfareId" placeholder="请选择" >
                  <el-option
                    v-for="item in selectWelfare"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="社保方案" prop="ssfConfigId">
                <el-select v-model="addForm.ssfConfigId" placeholder="请选择" >
                  <el-option
                    v-for="item in selectSsfConfig"
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
               <el-form-item label="中介类提成方案" prop="agentComConfigId">
                <el-select v-model="addForm.agentComConfigId" placeholder="请选择" >
                  <el-option
                    v-for="item in selectAgentComConfig"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="金融类提成方案" prop="financeComConfigId">
                  <el-select v-model="addForm.financeComConfigId" placeholder="请选择" >
                    <el-option
                      v-for="item in selectFinanceComConfig"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>
                </el-form-item>
            </el-col>
           </el-row>
          
          
          <el-form-item class="margin-b-none" style="text-align:center;">
            <el-button type="primary" :loading="loadingSubmitBtn" @click="handleSubmit">确认</el-button>
            <el-button @click="dialogAddVisible = false">取消</el-button>
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
  import BaseCascader from '@/components/BaseCascader'
  import {queryReferenceUserSelect} from '@/request/manage/common'
  import {dealAddLog, dealUpdateLog, dealDelLog,dealQueryLog} from '@/request/log/finLog'

  export default {
    name: 'wagesConfig',
    mixins: [PageList],
    components: {OpenCitySelector,BaseCascader},
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
        selectWelfare: [],
        selectSsfConfig: [],
        selectAgentComConfig: [],
        selectFinanceComConfig: [],
        queryForm: {
          selectId: '',
          deptId: '',
          userId: ''
        },
        getReferenceUserSelectUrl: 'manage/usermanage/queryReferenInfoToCreateTree', // 选员工
        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        addForm: {
          userId: '',
          basePay: '',
          welfareId: '',
          ssfConfigId: '',
          agentComConfigId: '',
          financeComConfigId: '',
        },
        // 表单校验配置
        addFormRules: {
          userId: [
            {required: true, message: '请选择员工', trigger: 'blur'}
          ],
          basePay: [
            {required: true, message: '请填写工资', trigger: 'blur'},
            { validator: money, trigger: 'blur' }
          ],
          welfareId: [
            {required: true, message: '请选择福利', trigger: 'blur'}
          ],
          ssfConfigId: [
            {required: true, message: '请选择社保', trigger: 'blur'}
          ],
          agentComConfigId: [
            {required: true, message: '请选择中介类提成', trigger: 'blur'}
          ],
          financeComConfigId: [
            {required: true, message: '请选择金融类提成', trigger: 'blur'}
          ]
        },
        dialogAddVisible: false,
        dialogBars: false,
        loadingSubmitBtn: false,
        loadingGrantBtn: false,
        loadingForm: false,
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
        title: '新增员工薪资配置',
        nowQueryForm: {},
        originalData: []
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
      //获取各方案
      getSomeConfig(){
        const  params = {}
        this.selectWelfare = [],
        this.selectSsfConfig = [],
        this.selectAgentComConfig = [],
        this.selectFinanceComConfig = [],
        RequestURL.getSomeConfig(params).then(res => {
          for(let i=0;i<res.welfareConfig.length;i++){
            let item = {
              value: res.welfareConfig[i].id,
              label: res.welfareConfig[i].name
            }
            this.selectWelfare.push(item);
          }
          for(let i=0;i<res.ssfConfig.length;i++){
            let item = {
              value: res.ssfConfig[i].id,
              label: res.ssfConfig[i].name
            }
            this.selectSsfConfig.push(item);
          }
          
          for(let i=0;i<res.commissionsConfig.length;i++){
            let item = {
              value: res.commissionsConfig[i].id,
              label: res.commissionsConfig[i].name
            }
            this.selectAgentComConfig.push(item);
          }
          for(let i=0;i<res.commissionsConfig.length;i++){
            let item = {
              value: res.commissionsConfig[i].id,
              label: res.commissionsConfig[i].name
            }
            this.selectFinanceComConfig.push(item);
          }

        })
      },

      //新增
      handleAdd(){
        this._resetForm('addForm')
        this.currentRowData = null ;// 置空当前操作的行数据
        this.isAdd = true;
        this.title = '新增员工薪资配置';
        this.dialogAddVisible = true;
      },
      //编辑
      handleEdit(row){
        this._resetForm('addForm')
        this.currentRowData = row ;// 置空当前操作的行数据
        this.isAdd = false;
        this.title = '编辑员工薪资配置';
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

          RequestURL.delEmployeeWagesConfigurate({id: row.id}).then(res => {
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
          })
        })
      },

      // 提交
      handleSubmit () {

        this.$refs['addForm'].validate((valid) => {
          if (valid) {
            if(this.selectWelfare.length>0){
              for(let i=0;i<this.selectWelfare.length;i++){
                if(this.addForm.welfareId == this.selectWelfare[i].value){
                  this.addForm.welfareName = this.selectWelfare[i].label;
                }
              }
            }else{
              this.addForm.welfareId = 0;
              this.addForm.welfareName = '';
            }
            if(this.selectSsfConfig.length>0){
              for(let i=0;i<this.selectSsfConfig.length;i++){
                if(this.addForm.ssfConfigId == this.selectSsfConfig[i].value){
                  this.addForm.ssfConfigName = this.selectSsfConfig[i].label;
                }
              }
            }else{
              this.addForm.ssfConfigId = 0;
              this.addForm.ssfConfigName = '';
            }

            if(this.selectAgentComConfig.length>0){
              for(let i=0;i<this.selectAgentComConfig.length;i++){
                if(this.addForm.agentComConfigId == this.selectAgentComConfig[i].value){
                  this.addForm.agentComConfigName = this.selectAgentComConfig[i].label;
                }
              }
            }else{
              this.addForm.agentComConfigId = 0;
              this.addForm.agentComConfigName = '';
            }
            if(this.selectFinanceComConfig.length>0){
              for(let i=0;i<this.selectFinanceComConfig.length;i++){
                if(this.addForm.financeComConfigId == this.selectFinanceComConfig[i].value){
                  this.addForm.financeComConfigName = this.selectFinanceComConfig[i].label;
                }
              }
            }else{
              this.addForm.financeComConfigId = 0;
              this.addForm.financeComConfigName = '';
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
              RequestURL.saveOrUpdateEmployeeWagesConfigurate(params).then(res => {
                this.loadingSubmitBtn = false
                this.dialogAddVisible = false
                if (res.success) {
                  this.$message({
                    type: 'success',
                    message: res.msg || '操作成功'
                  })
                  this._loadData(false);
                  //记日志
                  let message = {
                      sourceId: this.addForm.id,
                      sourceCode: this.addForm.userId,
                      businessTypeId: 5,//业务类型，
                      sourceTypeId: 11,
                      operatTypeId: 522,
                      labelData: this.$utils.getFormFields(this.$refs['addForm']),
                      originalData: this.originalData,
                      newData: JSON.parse(params.jsonData)
                    }

                    dealUpdateLog({message: JSON.stringify(message)}).then(res => {
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
                this.dialogAddVisible = false
                this.$message({
                  type: 'info',
                  message: err.msg || '保存失败'
                })
              })
            })

          }
        })
      },

      onChange(val, label, data){
        console.log(data);
        this.addForm.userName = data.name;
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
        RequestURL.employeeWagesConfigurate(params).then(res => {
          //console.log(11111)
          //console.log(res);
          this.tableData = res.data.map(item => {
             item.basePay = item.basePay/100
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
        // 拷贝为修改过的原始表单数据
        this.originalData = Object.assign({}, this.addForm)
        this.currentRowData.userId = String(this.currentRowData.userId)
        if(this.currentRowData.basePay==0){
          this.currentRowData.basePay = ''
        }
        if(this.currentRowData.welfareId==0){
          this.currentRowData.welfareId = ''
        }
        if(this.currentRowData.ssfConfigId==0){
          this.currentRowData.ssfConfigId = ''
        }
        if(this.currentRowData.agentComConfigId==0){
          this.currentRowData.agentComConfigId = ''
        }
        if(this.currentRowData.financeComConfigId==0){
          this.currentRowData.financeComConfigId = ''
        }

        this.$utils.setFormInfo(this.addForm, this.currentRowData, () => {
          this.addForm['id'] = this.currentRowData['id']
        })

        // 移除表单回填时 element ui自动添加的校验效果
        setTimeout(() => {
          this.$refs['addForm'].clearValidate()
        }, 100)
      },

    },
    mounted () {
       //人员选择
      queryReferenceUserSelect({}).then(res => {
        this.peopleSelectOpts = res
      })
      this.getSomeConfig();
    }

  }
</script>

<style scoped lang="scss">

</style>
