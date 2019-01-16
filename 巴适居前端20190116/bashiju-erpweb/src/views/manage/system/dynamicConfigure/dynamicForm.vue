<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form">
        <el-form size="small" ref="queryForm" :inline="true" :model="queryForm" class="demo-form-inline">
          <el-form-item label="城市" prop="cityCode">
            <base-city-cascader v-model="queryForm.cityCode" @initSelectData="initSelectData"></base-city-cascader>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click.native.prevent="handleQuery" :loading="loadingQueryBtn">查询</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <el-row :gutter="20">
      <el-col :span="12">
        <div class="grid-content">
          <div class="page-content-bd" v-loading="loadingView">
            <el-table
              :data="tableData"
              border
              @row-click="_showValue"
              align="center"
              style="width: 100%"
              height="580"
              highlight-current-row
            >
              <el-table-column
                type="index"
                width="50"
                align="center"
                label="序号"
              >
              </el-table-column>

              <el-table-column
                prop="tableChinaName"
                align="center"
                label="类型">
              </el-table-column>

              <el-table-column
                align="center"
                label="操作"
              >
                <template slot-scope="scope">
                  <el-button  @click="handleAdd(scope.row)" type="text" size="small">新增字段</el-button>
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
          <div class="page-content-bd" v-loading="loadingItemView">
            <el-table
              :data="valueData"
              border
              align="center"
              style="width: 100%"
              height="580"
            >
              <el-table-column
                prop="title"
                align="center"
                label="字段">
              </el-table-column>

              <el-table-column
                prop="fieldRequired"
                align="center"
                label="必填">
              </el-table-column>

              <el-table-column
                prop="writeType"
                align="center"
                label="填写方式">
              </el-table-column>

              <el-table-column
                prop="fieldUnit"
                align="center"
                label="单位">
              </el-table-column>

              <el-table-column
                prop="sort"
                align="center"
                label="顺序">
              </el-table-column>

              <el-table-column
                align="center"
                label="操作"
              >
                <template slot-scope="scope">
                  <el-button v-if="!scope.row.sqlDescription" @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
                  <el-button v-if="!scope.row.sqlDescription" @click="handleDelete(scope.row)" type="text" size="small">删除</el-button>
                </template>
              </el-table-column>

            </el-table>

          </div>



        </div>
      </el-col>
    </el-row>



    <template>
      <b-dialog :show.sync="dialogAddVisible" :title="title" width="400px">
        <el-form v-loading="loadingForm" :model="addForm" status-icon :rules="addFormRules" ref="addForm" label-width="100px">

          <el-form-item label="类型" prop="tableChinaName">
            <el-input v-model="addForm.tableChinaName"></el-input>
          </el-form-item>

          <el-form-item label="字段" prop="title">
            <el-input v-model="addForm.title"></el-input>
          </el-form-item>
          <el-form-item label="必填" prop="fieldRequired">
            <el-select v-model="addForm.fieldRequired" placeholder="请选择" >
              <el-option
                v-for="item in selectRequired"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="选择已有" v-show="isShowHasField" prop="field" >
            <el-select v-model="addForm.field" placeholder="请选择" @change="changeHasField">
              <el-option
                v-for="item in selectField"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="填写方式" prop="fieldType">
            <el-select v-model="addForm.fieldType" placeholder="请选择" @change="changeFieldType" >
              <el-option
                v-for="item in selectFieldType"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="单位" prop="fieldUnit" v-show="isShowFieldUnit">
            <el-input v-model="addForm.fieldUnit"></el-input>
          </el-form-item>
          <el-form-item label="下拉选项" prop="value" v-show="isShowSelectValue">
            <el-input v-model="addForm.value"></el-input>
          </el-form-item>
          <el-form-item label="排序" prop="sort">
            <el-input v-model="addForm.sort"></el-input>
          </el-form-item>
          <el-form-item class="margin-b-none">
            <el-button type="primary" :loading="loadingSubmitBtn" @click="handleSubmit">确认</el-button>
            <el-button @click="dialogAddVisible=false">取消</el-button>
          </el-form-item>

        </el-form>
      </b-dialog>

    </template>

  </div>
</template>

<script>
  import PageList from '@/mixins/pageList' // 列表页面查询 mixin
  import * as RequestURL from '@/request/manage/dynamicConfigure' // 请求后端URL路径
  import BaseCityCascader from '@/components/BaseCascader/city' // 城市级联组件
  import {systemAddLog, systemUpdateLog, systemQueryLog,systemDelLog} from '@/request/log/systemPlatformLog'

  export default {
    name: 'setDynamicForm',
    mixins: [PageList],
    components: {BaseCityCascader},
    data () {
      return {
        noShowData: true,
        queryForm: {
          cityCode: '',
        },
        queryField: {},
        addForm: {
          tableChinaName: '',
          title: '',
          fieldRequired: '',
          fieldType: '',
          sort: '',
          field: ''
        },
        selectRequired: [{
          value: '',
          label: '非必填'
        }, {
          value: 'required',
          label: '必填'
        }],
        selectField: [],
        hasFieldInfo: [],
        selectFieldType: [{
          value: '1',
          label: '文本'
        }, {
          value: '2',
          label: '数字'
        },{
          value: '3',
          label: '自定义下拉'
        }],
        editSqlForm: {
          sqlDescription: ''
        },
        // 表单校验配置
        addFormRules: {
          title: [
            {required: true, message: '请输入字段名称', trigger: 'blur'}
          ],
          fieldType: [
            {required: true, message: '请输入填写方式', trigger: 'blur'}
          ],
          sort: [
            {required: true, message: '请输入顺序', trigger: 'blur'}
          ]
        },
        dialogAddVisible: false,
        dialogEditSql: false,
        loadingSubmitBtn: false,
        loadingForm: false,
        loadingItemView: false,
        currentRowData: null, // 当前操作的行数据
        currentDynamicConfigureItem:null,
        valueData: [],
        isShowHasField: false,
        isShowFieldUnit: false,
        isShowSelectValue: false,
        title: '新增配置明细',
        nowQueryForm: {},
        originalData: [],
        isAdd: true
      }
    },
    methods: {
      initSelectData(data) {
          console.log('**************',data)
        if (data!=null&&data.length>0){ 
          this.queryForm.cityCode = data[0].code
          this.handleQuery()
        }
      },
      /**
       * 重写点击查询
       */
      handleQuery () {
        this.noShowData = false
        this.nowQueryForm = Object.assign({}, this.queryForm)
        this.listQuery.page = 1
        this.listQuery.currentPage = 1
        this._loadData(true)
      },
      // 新增
      handleAdd (row) {
        this._resetForm('addForm')
        this.addForm.tableChinaName = row.tableChinaName;
        this.currentRowData = null ;// 置空当前操作的行数据
        this.isAdd = true;
        this.isShowHasField = true;
        this.isShowFieldUnit = false;
        this.isShowSelectValue = false;
        this.title = '新增配置明细'
        this.dialogAddVisible = true;
        this.currentDynamicConfigureItem=row;
        this.selectHasField();
      },
      handleSave(){

      },
      // 编辑
      handleEdit (row) {
        this._resetForm('addForm')
        this.currentRowData = row
        this.isAdd = false
        this.isShowHasField = false;
        this.title = '编辑配置明细'
        this.dialogAddVisible = true
        this.$nextTick(() => {
          this._setForm()
        })
      },
      selectHasField(){
        RequestURL.queryCityDynamicFormFieldSelect({formId:this.currentDynamicConfigureItem.id}).then(res => {
          console.log(res);
          if (res.success) {
            this.selectField = [];
            for(let i=0;i<res.data.length;i++){
              let item = {
                value: res.data[i].field,
                label: res.data[i].title
              }
              this.selectField.push(item);
              let info = {
                field: res.data[i].field,
                fieldType: res.data[i].fieldType,
                fieldUnit: res.data[i].fieldUnit,
                value: res.data[i].value
              }
              this.hasFieldInfo.push(info);
            }

          }else{
            this.$message({
              type: 'warning',
              message: res.msg
            })
          }
        })
      },
      changeHasField(){
        for(let i=0;i<this.hasFieldInfo.length;i++){
          //文本
          if(this.addForm.field==this.hasFieldInfo[i].field && this.hasFieldInfo[i].fieldType==1){
            this.isShowFieldUnit = false;
            this.isShowSelectValue = false;
            this.addForm.fieldType = '1';
          }
          //数字
          if(this.addForm.field==this.hasFieldInfo[i].field && this.hasFieldInfo[i].fieldType==2){
            this.isShowFieldUnit = true;
            this.isShowSelectValue = false;
            this.addForm.fieldUnit = this.hasFieldInfo[i].fieldUnit;
            this.addForm.fieldType = '2';
          }
          //自定义下拉
          if(this.addForm.field==this.hasFieldInfo[i].field && this.hasFieldInfo[i].fieldType==3){
            this.isShowFieldUnit = false;
            this.isShowSelectValue = true;
            this.addForm.value = this.hasFieldInfo[i].value;
            this.addForm.fieldType = '3';
          }
        }
      },
      changeFieldType(){
        if(this.addForm.fieldType==1){
          this.isShowFieldUnit = false;
          this.isShowSelectValue = false;
        }
        if(this.addForm.fieldType==2){
          this.isShowFieldUnit = true;
          this.isShowSelectValue = false;
        }
        if(this.addForm.fieldType==3){
          this.isShowFieldUnit = false;
          this.isShowSelectValue = true;
        }
      },
      // 提交
      handleSubmit () {

        this.$refs['addForm'].validate((valid) => {
          if (valid) {
            delete this.addForm.tableChinaName;
            this.addForm.formId = this.currentDynamicConfigureItem.id;
            if(this.isAdd){
              this.addForm.tableName = this.currentDynamicConfigureItem.tableDbName;
              this.addForm.customTableName = this.currentDynamicConfigureItem.customTableName;
              this.addForm.tableType = this.currentDynamicConfigureItem.tableType;
            }
            this.addForm.cityCode = this.queryForm.cityCode;
            const params = {
              dataJson: JSON.stringify(this.addForm)
            }

            this.$confirm('确定保存编辑的信息吗？, 是否继续?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.loadingSubmitBtn = true
              RequestURL.saveFormField(params).then(res => {
                this.loadingSubmitBtn = false
                this.dialogAddVisible = false
                if (res.success) {
                  this.$message({
                    type: 'success',
                    message: res.msg || '操作成功'
                  })
                  this._loadValueData(this.queryForm.cityCode,this.currentDynamicConfigureItem.id);
                  //记日志
                  if(this.isAdd){//新增
                      let logContent = this.currentDynamicConfigureItem.tableChinaName+';新增了一个字段：'+this.addForm.title
                      let message = {
                          sourceCode: '动态表单配置',
                          sourceTypeId: 9,
                          operatTypeId: 1,
                          logContent: logContent,
                          
                        }
                      systemAddLog({message: JSON.stringify(message)}).then(res => {
                          console.log(res)
                        })
                  }else{//编辑
                      let message = {
                      sourceCode: '动态表单配置',
                      sourceTypeId: 9,
                      operatTypeId: 2,
                      labelData: this.$utils.getFormFields(this.$refs['addForm']),
                      originalData: this.originalData,
                      newData: JSON.parse(params.dataJson)
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

      // 删除
      handleDelete (row) {
        this.$confirm('确定删除该条数据？, 是否继续?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          RequestURL.delFormField({id: row.id}).then(res => {
            this.$message({
              type: 'success',
              message: res.msg || '删除成功'
            })
            this._loadValueData(this.queryForm.cityCode,this.currentDynamicConfigureItem.id);
            //写日志
            let logContent = this.currentDynamicConfigureItem.tableChinaName+';删除了一个字段：'+row.title
            let message = {
                sourceCode: '动态表单配置',
                sourceTypeId: 9,
                operatTypeId: 3,
                logContent: logContent
              }
            systemDelLog({message: JSON.stringify(message)}).then(res => {
                console.log(res)
              })
          })
        })
      },

      handleChangeCity (val, name) {
        this.editForm['areaName'] = name
      },

      // 加载数据
      _loadData (btnQuery) {
        if(!this.noShowData){
          if (btnQuery) {
            this.loadingQueryBtn = true
          }

          this.loadingView = true
          this.nowQueryForm.formType = 1;
          let params = Object.assign({}, this.nowQueryForm, {
          limit: this.listQuery.limit,
          page: this.listQuery.page
        })
         // const params = this._getParams(this.queryForm)
          RequestURL.getDynamicFormList(params).then(res => {
            this.tableData = res.data
            //console.log(this.tableData);
            this.listQuery.total = res.count
            this.loadingQueryBtn = false
            this.loadingView = false
            this.currentDynamicConfigureItem = this.tableData[0];
            this._loadValueData(this.queryForm.cityCode,this.tableData[0].id);
          }).catch(() => {
            this.loadingQueryBtn = false
            this.loadingView = false
          })
        }
      },
      _loadValueData(cityCode,formId){
        this.loadingItemView = true;
        this.queryField = {
          cityCode: cityCode,
          formId: formId
        };
        const params = this._getParams(this.queryField)
        RequestURL.getDynamicFormFieldList(params).then(res => {
         // console.log(res.data);
          this.valueData = res.data
          for(let i=0;i<this.valueData.length;i++){
            if(this.valueData[i].fieldType==1){
              this.valueData[i].writeType="文本"
            }
            if(this.valueData[i].fieldType==2){
              this.valueData[i].writeType="数字"
            }
            if(this.valueData[i].fieldType==3){
              this.valueData[i].writeType="自定义下拉"
            }
          }
          this.loadingItemView = false;
        }).catch(() => {
          this.loadingItemView = false;
        })
      },
      // 表单回填
      _setForm () {
        // 拷贝为修改过的原始表单数据
        this.originalData = Object.assign({}, this.addForm)

        this.$utils.setFormInfo(this.addForm, this.currentRowData, () => {
          /*this.addForm['widgetCode'] = this.currentRowData['widgetCode']
          this.addForm['fieldCode'] = this.currentRowData['fieldCode']
          this.addForm['fieldValue'] = this.currentRowData['fieldValue']
          this.addForm['sorting'] = this.currentRowData['sorting']*/
          if(this.currentRowData.fieldType==1){
            this.isShowFieldUnit = false;
            this.isShowSelectValue = false;
          }
          if(this.currentRowData.fieldType==2){
            this.isShowFieldUnit = true;
            this.isShowSelectValue = false;
            this.addForm['fieldUnit'] = this.currentRowData['fieldUnit'];
          }
          if(this.currentRowData.fieldType==3){
            this.isShowFieldUnit = false;
            this.isShowSelectValue = true;
            this.addForm['value'] = this.currentRowData['value'];
          }
          this.addForm['id'] = this.currentRowData['id'];
          this.addForm.tableChinaName = this.currentDynamicConfigureItem.tableChinaName;
        })

        // 拷贝为修改过的原始表单数据
        this.originalData = Object.assign({}, this.addForm)

        // 移除表单回填时 element ui自动添加的校验效果
        setTimeout(() => {
          this.$refs['addForm'].clearValidate()
        }, 100)
      },

      _showValue(row, event, column){
        //console.log(row.widgetCode)
        this.currentDynamicConfigureItem = row;
        this._loadValueData(this.queryForm.cityCode,row.id);
      }
    },

  }
</script>

<style scoped lang="scss">

</style>
