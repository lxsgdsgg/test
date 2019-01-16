<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form">
        <el-form size="small" ref="queryForm" :inline="true" :model="queryForm" class="demo-form-inline">
          <el-form-item label="城市" prop="selectCode">
            <open-city-selector
              v-model="queryForm.selectCode"
              :url="cityUrl"
              :props="cascaderProps"
              :dataProps="cascaderDataProps" @change="handleChangeCityQuery">
            </open-city-selector>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click.native.prevent="handleQuery" :loading="loadingQueryBtn">查询</el-button>
            <el-button @click.native.prevent="_resetForm('queryForm')">清空</el-button>
          </el-form-item>

          <el-form-item class="pull-right">
            <el-button type="primary" @click="handleAdd">新增</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <div class="page-content-bd" v-loading="loadingView">
      <el-table
        :data="tableData"
        border
        align="center"
        style="width: 100%"
        tooltip-effect="light"
        size="mini"
      >

        <el-table-column
          show-overflow-tooltip
          prop="provinceName"
          align="center"
          label="省级名称">
        </el-table-column>


        <el-table-column
          show-overflow-tooltip
          prop="cityName"
          align="center"
          label="城市名称">
        </el-table-column>

        <el-table-column
          show-overflow-tooltip
          prop="url"
          align="center"
          label="映射地址">
        </el-table-column>

        <el-table-column
          show-overflow-tooltip
          prop="address"
          align="center"
          label="联系地址">
        </el-table-column>

        <el-table-column
          show-overflow-tooltip
          prop="telPhone"
          align="center"
          label="联系电话">
        </el-table-column>

        <el-table-column
          show-overflow-tooltip
          prop="email"
          align="center"
          label="邮箱">
        </el-table-column>

        <el-table-column
          show-overflow-tooltip
          prop="publicLoanCeiling"
          align="center"
          label="公积金贷款上限（万）">
        </el-table-column>

        <el-table-column
          show-overflow-tooltip
          prop="LoanRatioCeiling"
          align="center"
          label="商业贷款最大比率（%）">
        </el-table-column>

        <el-table-column
          show-overflow-tooltip
          prop="agencyFeeRatio"
          align="center"
          label="中介佣金比率（%）">
        </el-table-column>

        <el-table-column
          show-overflow-tooltip
          prop="businessLoanRatio"
          align="center"
          label="商业基准利率（%）">
        </el-table-column>

        <el-table-column
          show-overflow-tooltip
          prop="publicLoanRatio"
          align="center"
          label="公积金基准利率（%）">
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
          width="220px"
        >
          <template slot-scope="scope">

            <!--<el-button  @click="handleGrant(scope.row)" type="text" size="small">授权导航</el-button>-->
            <el-button  @click="handleUpdate(scope.row)" type="text" size="small">编辑</el-button>
            <el-button  @click="commonSet(scope.row)" type="text" size="small">基础设置</el-button>
            <el-button  @click="handleDel(scope.row)" type="text" size="small">删除</el-button>
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
      <b-dialog :show.sync="dialogAddVisible" title="平台开通城市" width="660px">
        <el-form v-loading="loadingForm" :model="addForm" status-icon :rules="addFormRules" ref="addForm" size="small" label-width="150px">
          <el-row>
            <el-col :span="12">
              <el-form-item label="城市" prop="cityCode">
                <open-city-selector
                  v-model="addForm.cityCode"
                  :url="cityUrl"
                  :props="cascaderProps"
                  :dataProps="cascaderDataProps" @change="handleChangeCity">
                </open-city-selector>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="映射地址" prop="url">
                <el-input v-model="addForm.url"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="联系地址" prop="address">
                <el-input v-model="addForm.address"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="联系电话" prop="telPhone">
                <el-input v-model="addForm.telPhone"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="邮箱" prop="email">
                <el-input v-model="addForm.email"></el-input>
              </el-form-item>
            </el-col>
            <!--<el-col :span="12">-->
              <!--<el-form-item label="公积金贷款上限" prop="publicLoanCeiling">-->
                <!--<el-input v-model="addForm.publicLoanCeiling"></el-input>-->
                <!--<span class="el-input__suffix">-->
                  <!--<span class="el-input__suffix-inner">-->
                    <!--<i class="el-input__icon">万</i>-->
                  <!--</span>-->
                <!--</span>-->
              <!--</el-form-item>-->
            <!--</el-col>-->
          </el-row>
          <!--<el-row>-->
            <!--<el-col :span="12">-->
              <!--<el-form-item label="商业贷款最大比率" prop="LoanRatioCeiling">-->
                <!--<el-input type="number" v-model="addForm.LoanRatioCeiling" :min="0" :max="100" ></el-input>-->
                <!--<span class="el-input__suffix">-->
                  <!--<span class="el-input__suffix-inner">-->
                    <!--<i class="el-input__icon">%</i>-->
                  <!--</span>-->
                <!--</span>-->
              <!--</el-form-item>-->
            <!--</el-col>-->
            <!--<el-col :span="12">-->
               <!--<el-form-item label="中介佣金比率" prop="agencyFeeRatio">-->
                <!--<el-input type="number" v-model="addForm.agencyFeeRatio" :precision="2" :step="0.1" :min="0.0" :max="100"></el-input>-->
                 <!--<span class="el-input__suffix">-->
                  <!--<span class="el-input__suffix-inner">-->
                    <!--<i class="el-input__icon">%</i>-->
                  <!--</span>-->
                <!--</span>-->
              <!--</el-form-item>-->
            <!--</el-col>-->
          <!--</el-row>-->
          <!--<el-row>-->
            <!--<el-col :span="12">-->
              <!--<el-form-item label="商业贷款基准利率" prop="businessLoanRatio">-->
                <!--<el-input type="number" v-model="addForm.businessLoanRatio" :precision="2" :step="0.1" :min="0.0" :max="100" ></el-input>-->
                <!--<span class="el-input__suffix">-->
                  <!--<span class="el-input__suffix-inner">-->
                    <!--<i class="el-input__icon">%</i>-->
                  <!--</span>-->
                <!--</span>-->
              <!--</el-form-item>-->
            <!--</el-col>-->
            <!--<el-col :span="12">-->
              <!--<el-form-item label="公积金贷款基准利率" prop="publicLoanRatio">-->
                <!--<el-input type="number" v-model="addForm.publicLoanRatio" :precision="2" :step="0.1" :min="0.0" :max="100"></el-input>-->
                <!--<span class="el-input__suffix">-->
                  <!--<span class="el-input__suffix-inner">-->
                    <!--<i class="el-input__icon">%</i>-->
                  <!--</span>-->
                <!--</span>-->
              <!--</el-form-item>-->
            <!--</el-col>-->
          <!--</el-row>-->
          <el-form-item class="margin-b-none">
            <el-button type="primary" :loading="loadingSubmitBtn" @click="handleSubmit">确认</el-button>
            <el-button @click="dialogAddVisible=false">取消</el-button>
          </el-form-item>

        </el-form>
      </b-dialog>
      <!--<b-dialog :show.sync="dialogBars" title="授权导航" width="750px" height="600px">-->
        <!--<el-form v-loading="loadingForm"   label-width="100px">-->
          <!--<el-form-item label="授权导航" >-->
            <!--<el-tree-->
              <!--:data="barData"-->
              <!--show-checkbox-->
              <!--node-key="id"-->
              <!--ref="tree"-->
              <!--:default-expanded-keys="keys"-->
              <!--:default-checked-keys="keys"-->
              <!--:props="defaultProps">-->
            <!--</el-tree>-->
          <!--</el-form-item>-->

          <!--<el-form-item class="margin-b-none">-->
            <!--<el-button type="primary" :loading="loadingGrantBtn" @click="grantNav">确认</el-button>-->
          <!--</el-form-item>-->
        <!--</el-form>-->
      <!--</b-dialog>-->
      <el-dialog
        :visible.sync="commonSetDialog"
        title="基础设置"
        width="950px"
        top="5vh"
        :close-on-click-modal="false"
      >
        <open-city-tab-pages v-if="hackReset" :data="currentRowData" @cancelClick="commonSetDialog === false"></open-city-tab-pages>
      </el-dialog>
    </template>

  </div>
</template>

<script>
  import PageList from '@/mixins/pageList' // 列表页面查询 mixin
  import * as RequestURL from '@/request/manage/cityOpen' // 请求后端URL路径
  import OpenCitySelector from '@/components/BaseCascader' // // 城市级联组件
  import BarSelector from '@/components/BaseCascader' // 导航级联组件
  import OpenCityTabPages from './component/OpenCityTabPages' // 导航级联组件
  import {systemAddLog, systemUpdateLog, systemQueryLog,systemDelLog} from '@/request/log/systemPlatformLog'
  export default {
    name: 'platformOpenCity',
    mixins: [PageList],
    components: {OpenCitySelector,BarSelector,OpenCityTabPages},
    data () {
      return {
        tableData: [],
        cityUrl: 'manage/commonselect/queryAllCity',
        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        queryForm: {selectCode: ''},
        companyOpts: [],
        addForm: {
          cityCode: '',
          url: '',
          address: '',
          telPhone: '',
          email: '',
          publicLoanCeiling: 0,
          LoanRatioCeiling: 0,
          agencyFeeRatio: '',
          businessLoanRatio:0,
          publicLoanRatio:0
        },
        // 表单校验配置
        addFormRules: {
          cityCode: [
            {required: true, message: '请选择城市', trigger: 'change'}
          ],
          address: [
            {required: true, message: '请输入联系地址', trigger: 'blur'}
          ],
          telPhone: [
            {required: true, message: '请输入联系电话', trigger: 'blur'}
          ],
          email: [
            {required: true, message: '请输入联系邮箱', trigger: 'blur'}
          ]
          // ,
          // publicLoanCeiling: [
          //   {required: true, message: '请输入公积金贷款上限', trigger: 'blur'}
          // ],
          // LoanRatioCeiling: [
          //   {required: true, message: '请输入商业贷款最大比率', trigger: 'blur'}
          // ],
          // agencyFeeRatio: [
          //   {required: true, message: '请输入佣金比率', trigger: 'blur'}
          // ],
          // businessLoanRatio: [
          //   {required: true, message: '请输入商业贷款基准利率', trigger: 'blur'}
          // ],
          // publicLoanRatio: [
          //   {required: true, message: '请输入公积金贷款基准利率', trigger: 'blur'}
          // ]
        },
        dialogAddVisible: false,
        dialogBars: false,
        loadingSubmitBtn: false,
        loadingGrantBtn: false,
        loadingForm: false,
        currentRowData: null, // 当前操作的行数据
        currentDynamicConfigureItem:null,
        bars: [],
        idForBar: '',
        barData: [],
        levelInfo1: [],
        levelInfo2: [],
        defaultProps: {
          children: 'children',
          label: 'label'
        },
        keys: [],
        isAdd: false,
        title: '开通',
        nowQueryForm: {},
        originalData: [],
        cityForBar: '',
        commonSetDialog: false,
        hackReset: true,
        loadingQueryBtn: false
      }
    },
    methods: {
      //新增
      handleAdd(){
        this._resetForm('addForm')
        this.currentRowData = null ;// 置空当前操作的行数据
        this.isAdd = true;
        this.title = '开通';
        this.dialogAddVisible = true;
        delete this.addForm.id
      },
      //编辑
      handleUpdate(row){
        this._resetForm('addForm')
        this.currentRowData = row
        this.isAdd = false
        this.title = '编辑'
        this.dialogAddVisible = true
        this.$nextTick(() => {
          this._setForm()
        })
      },
      /**
       * 基础设置
       * @param row
       */
      commonSet(row){
        this.hackReset = false
        this.$nextTick(()=>{
          this.hackReset = true
          this.currentRowData = row
          this.commonSetDialog = true
        })
      },
      // 删除
      handleDel (row) {
        this.$confirm('确定删除该条数据？, 是否继续?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          RequestURL.cancelCityOpen({id: row.id}).then(res => {
            this.$message({
              type: 'success',
              message: res.msg || '删除成功'
            })
            this._loadData(false);
            //写日志
            let logContent = '删除城市：'+row.cityName+'的开通'
            let message = {
                sourceCode: '平台开通城市',
                sourceTypeId: 19,
                operatTypeId: 3,
                logContent: logContent
              }
            systemDelLog({message: JSON.stringify(message)}).then(res => {
                console.log(res)
              })
          })
        })
      },
      handleGrant(row){
        this.dialogBars = true;
        this.idForBar = row.id;
        this.cityForBar = row.cityName
        this.getBars(row.id);
      },
      // 提交
      handleSubmit () {

        this.$refs['addForm'].validate((valid) => {
          if (valid) {
            let saveForm = Object.assign({}, this.addForm)
            saveForm.agencyFeeRatio = this.addForm.agencyFeeRatio * 100
            saveForm.businessLoanRatio = this.addForm.businessLoanRatio * 100
            saveForm.publicLoanRatio = this.addForm.publicLoanRatio * 100
            const params = {
              jsonData: JSON.stringify({...saveForm})
            }
            const paramsLog = {
              jsonData: JSON.stringify({...this.addForm})
            }
            this.$confirm('确定保存编辑的信息吗？, 是否继续?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.loadingSubmitBtn = true
              RequestURL.saveCityOpen(params).then(res => {
                this.loadingSubmitBtn = false
                this.dialogAddVisible = false
                if (res.success) {
                  this.$message({
                    type: 'success',
                    message: res.msg || '操作成功'
                  })
                  this._loadData(false);
                //记日志
                if(this.isAdd){//新增
                    let logContent = '开通城市：'+this.addForm.cityName+";联系地址："+this.addForm.address+";联系电话："+this.addForm.telPhone+";邮箱："+this.addForm.email+";公积金贷款上限:"+this.addForm.publicLoanCeiling+";商业贷款最大比："+this.addForm.LoanRatioCeiling+";中介佣金比率："+this.addForm.agencyFeeRatio
                    let message = {
                        sourceCode: '平台开通城市',
                        sourceTypeId: 19,
                        operatTypeId: 1,
                        logContent: logContent
                      }
                    systemAddLog({message: JSON.stringify(message)}).then(res => {
                        console.log(res)
                      })
                }else{//编辑
                    let message = {
                    sourceCode: '平台开通城市',
                    sourceTypeId: 19,
                    operatTypeId: 2,
                    labelData: this.$utils.getFormFields(this.$refs['addForm']),
                    originalData: this.originalData,
                    newData: JSON.parse(paramsLog.jsonData)
                  }

                  systemUpdateLog({message: JSON.stringify(message)}).then(res => {
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

      handleChangeCity (val, label, data) {
        this.addForm.cityName = data.name;
        this.addForm.provinceCode = data.parentCode;
        this.addForm.provinceName = data.parent.name;
      },

      handleChangeCityQuery(val, label, data){
        this.queryForm.provinceCode = ''
        this.queryForm.cityCode = ''
        if(data.parent==null){
            this.queryForm.provinceCode = this.queryForm.selectCode
            delete this.queryForm.selectCode
        }else{
            this.queryForm.cityCode = this.queryForm.selectCode
            delete this.queryForm.selectCode
        }
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
        RequestURL.getData(params).then(res => {
          this.tableData = res.data.map(item => {
            if(!item.agencyFeeRatio){
              item.agencyFeeRatio = 0
            }
            item.agencyFeeRatio = item.agencyFeeRatio/100
            item.businessLoanRatio=item.businessLoanRatio/100
            item.publicLoanRatio=item.publicLoanRatio/100
            return item
          })
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
        this.$utils.setFormInfo(this.addForm, this.currentRowData, () => {
          this.addForm['id'] = this.currentRowData['id']
        })

        // 拷贝为修改过的原始表单数据
        this.originalData = Object.assign({}, this.addForm)

        // 移除表单回填时 element ui自动添加的校验效果
        setTimeout(() => {
          this.$refs['addForm'].clearValidate()
        }, 100)
      },

    },
    mounted () {

    }

  }
</script>

<style scoped lang="scss">

</style>
