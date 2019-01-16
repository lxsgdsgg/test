<template>
  <div class="page-content">
    <div class="page-content-hd">
    <el-row >
      <el-button type="success" @click="addDropdown">下拉</el-button>
      <el-button type="success" @click="addRange">区间</el-button>
    </el-row>
  </div>
    <div class="page-content-hd">
      <div class="query-form">
        <el-form size="small" ref="form" :inline="true" :model="form" :rules="formRules" class="demo-form-inline">
          <el-form-item v-show="isShow" label="城市" prop="cityCode">
            <base-city-cascader v-model="form.cityCode"></base-city-cascader>
          </el-form-item>

          <el-form-item label="控件名称" prop="name">
            <el-input v-model="form.name" placeholder="请输入"></el-input>
          </el-form-item>
          <el-form-item label="控件类型" prop="type">
            <el-select v-model="form.type" placeholder="请选择" @change="changeType">
              <el-option
                v-for="item in widgetTypes"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="控件编号" prop="widgetCode">
            <el-input v-model="form.widgetCode" @blur="validateWidgetCode" placeholder="请输入"></el-input>
          </el-form-item>
          <el-form-item label="sql语句" v-show="isShowSql" prop="sql">
            <el-input v-model="form.sql" @blur="showSql" placeholder="请输入"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button @click.native.prevent="_resetForm('form')">重置</el-button>
          </el-form-item>

          <el-form-item class="pull-right">
            <el-button type="primary" @click="handleAdd">新增明细项</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>

    <div class="page-content-bd"  v-show="isShowDropdown" v-loading="">
      <el-table
        :data="tableData"
        border
        align="center"
        style="width: 100%"
        height="480"
      >

        <el-table-column
          prop="fieldCode"
          align="center"
          label="编号">
        </el-table-column>

        <el-table-column
          prop="fieldValue"
          align="center"
          label="值">
        </el-table-column>

        <el-table-column
          prop="sorting"
          align="center"
          label="排序">
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
        >
          <!--<template slot-scope="scope">
            <el-button  @click="handleDelete(scope.row)" type="text" size="small">删除</el-button>
          </template>-->
        </el-table-column>

      </el-table>
    </div>
    <div class="page-content-bd"  v-show="isShowSqlDropdown" v-loading="">
      <el-table
        :data="tableData"
        border
        align="center"
        style="width: 100%"
        height="480"
      >

        <el-table-column
          prop="sql"
          align="center"
          label="sql语句">
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
        >

        </el-table-column>

      </el-table>
    </div>
    <div class="page-content-bd"  v-show="isShowRange" v-loading="">
      <el-table
        :data="tableData"
        border
        align="center"
        style="width: 100%"
        height="480"
      >

        <el-table-column
          prop="fieldValue"
          align="center"
          label="最小值">
        </el-table-column>

        <el-table-column
          prop="maxRegionValue"
          align="center"
          label="最大值">
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
        >
          <!--<template slot-scope="scope">
            <el-button  @click="handleDelete(scope.row)" type="text" size="small">删除</el-button>
          </template>-->
        </el-table-column>

      </el-table>

    </div>

    <div class="page-content-hd">
      <el-row >
        <div class="save-btn">
          <el-button  type="success" @click="handleSubmit">保存</el-button>
        </div>
      </el-row>
    </div>

    <template>
      <b-dialog :show.sync="dialogDropdownVisible" title="新增下拉明细项" width="400px">
        <el-form v-loading="loadingForm" :model="dropdownForm" status-icon :rules="editFormRules" ref="dropdownForm" label-width="100px">

          <el-form-item label="编号" prop="fieldCode">
            <el-input v-model="dropdownForm.fieldCode"></el-input>
          </el-form-item>

          <el-form-item label="值" prop="fieldValue">
            <el-input v-model="dropdownForm.fieldValue"></el-input>
          </el-form-item>

          <el-form-item label="排序" prop="sorting">
            <el-input v-model="dropdownForm.sorting"></el-input>
          </el-form-item>

          <el-form-item class="margin-b-none">
            <el-button type="primary"  @click="addDropdownItem">确认</el-button>
            <el-button @click="_resetForm('editForm')">重置</el-button>
          </el-form-item>

        </el-form>
      </b-dialog>
      <b-dialog :show.sync="dialogRangeVisible" title="新增区间明细项" width="400px">
        <el-form v-loading="loadingForm" :model="rangeForm" status-icon :rules="editFormRules" ref="rangeForm" label-width="100px">
          <el-row>
            <el-col :span="24">
              <div>由低到高的顺序填写，后一项不填写默认为以上</div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-input v-model="minTitle" class="border-none" placeholder="请输入编号"></el-input>
            </el-col>
            <el-col :span="12">
              <el-input v-model="maxTitle" class="border-none" placeholder="请输入编号"></el-input>
            </el-col>
          </el-row>
          <span v-for="(item,index) in editData" v-loading="loadingEditView">
            <el-row>
              <el-col :span="12">
                <el-input v-model="item.fieldValue" placeholder="" :readonly="true"></el-input>
              </el-col>
              <el-col :span="12">
                <el-input v-model="item.maxRegionValue" @blur="changeMaxValue(index)" placeholder=""></el-input>
              </el-col>
            </el-row>
          </span>
          <el-form-item class="margin-b-none">
            <el-button type="primary" :loading="loadingSubmitBtn" @click="addRangeItem">确认</el-button>
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

  export default {
    name: 'addWidget',
    components: {BaseCityCascader},
    data () {
      return {
        widgetTypes: [{
          value: '1',
          label: '一般下拉'
        }, {
          value: '2',
          label: 'sql下拉'
        },{
          value: '3',
          label: '区间'
        }],
        form: {
          cityCode: '',
          name: '',
          widgetCode: '',
          type: '1',
        },
        dropdownForm: {
          fieldCode: '',
          fieldValue: '',
          sorting: '',
        },
        rangeForm: {
          fieldValue: '',
          maxRegionValue: '',
        },
        // 表单校验配置
        editFormRules: {
          fieldValue: [
            {required: true, message: '请输入名称', trigger: 'blur'}
          ],
          sorting: [
            {required: true, message: '请输入排序码', trigger: 'blur'}
          ],
          maxRegionValue: [
            {required: true, message: '请输入名称', trigger: 'blur'}
          ],
        },
        formRules: {
          cityCode: [
            {required: true, message: '请输入城市', trigger: 'blur'}
          ],
          name: [
            {required: true, message: '请输入名称', trigger: 'blur'}
          ],
          widgetCode: [
            {required: true, message: '请输入编号', trigger: 'blur'}
          ],
          typeName: [
            {required: true, message: '请输入类型', trigger: 'blur'}
          ],
          sql: [
            {required: true, message: '请输入sql语句', trigger: 'blur'}
          ]
        },
        dialogDropdownVisible: false,
        dialogRangeVisible: false,
        loadingSubmitBtn: false,
        loadingForm: false,
        currentRowData: null, // 当前操作的行数据
        loadingView: false,
        isShow:false,
        tableData: [],
        isShowDropdown: true,
        isShowSqlDropdown: false,
        isShowRange: false,
        isShowSql: false,
        valueData: [],
        minTitle: '下限',
        maxTitle: '上限',
        editData: []
      }
    },
    methods: {
      //选择新增下拉
      addDropdown(){
        this.isShow = false;
        this.isShowDropdown = true;
        this.isShowSqlDropdown = false;
        this.isShowRange = false;
        this.form.type = '1';
      },
      //选择新增区间
      addRange(){
        this.isShow = true;
        this.isShowDropdown = false;
        this.isShowSqlDropdown = false;
        this.isShowRange = true;
        this.form.type = "3";
      },
      //验证编号是否被用了
      validateWidgetCode(){
          const params = {
            widgetCode: this.form.widgetCode,
            widgetType: this.form.type,
          }
          if(this.isShow){
            params.cityCode = this.form.cityCode;
          }
          RequestURL.validateWidgetCode(params).then(res => {
          this.$message({
            type: 'success',
            message: res.data.msg
          })

        }).catch(() => {
          this.loadingSubmitBtn = false
        })

      },
      changeType(){
        if(this.form.type==2){
          this.isShowSql = true;
          this.isShowDropdown = false;
          this.isShowSqlDropdown = true;
          this.isShowRange = false;
          this.isShow = false;
        }else if(this.form.type==1){
          this.isShowSql = false;
          this.isShowDropdown = true;
          this.isShowSqlDropdown = false;
          this.isShowRange = false;
          this.isShow = false;
        }else{
          this.isShowSql = false;
          this.isShowDropdown = false;
          this.isShowSqlDropdown = false;
          this.isShowRange = true;
          this.isShow = true;
        }
      },
      // 新增
      handleAdd () {
        if(this.isShowDropdown==true){
          this._resetForm('dropdownForm')
          this.currentRowData = null // 置空当前操作的行数据
          this.dialogDropdownVisible = true
        }
        if(this.isShowRange==true){
          this._resetForm('rangeForm')
          this.currentRowData = null // 置空当前操作的行数据
          this.editData = [];
          for(let i=0;i<10;i++){
            if(i==0){
              let firstValue = {
                fieldValue: '0',
                maxRegionValue: ''
              }
              this.editData.push(firstValue);
            }else{
              let value = {
                fieldValue: '',
                maxRegionValue: ''
              }
              this.editData.push(value);
            }


          }

          this.dialogRangeVisible = true
        }
      },
      // 编辑
      handleEdit (row) {
        this._resetForm('editForm')
        this.currentRowData = row
        this.isAdd = false
        this.dialogEditVisible = true
        this.$nextTick(() => {
          this._setForm()
        })
      },
      //新增下拉明细项
      addDropdownItem(){
        //this.tableData = [];
        var value = {
          widgetCode: this.form.widgetCode,
          fieldCode: this.dropdownForm.fieldCode,
          fieldValue: this.dropdownForm.fieldValue,
          sorting: this.dropdownForm.sorting
        }
        this.tableData.push(value);
        this.dialogDropdownVisible = false;
      },
      //新增区间明细项
      addRangeItem(){
        for(let i=0;i<this.editData.length;i++){
          this.editData[i].widgetCode = this.form.widgetCode;
          if(this.editData[i].fieldValue==''){
            this.editData.splice(i);
          }
        }
        this.tableData = this.editData;
        /*var value = {
          widgetCode: this.form.widgetCode,
          fieldValue: this.rangeForm.fieldValue,
          maxRegionValue: this.rangeForm.maxRegionValue
        }
        this.tableData.push(value);*/
        this.dialogRangeVisible = false;
      },
      showSql(){
        this.tableData = [];
        var value = {
          sql: this.form.sql,
        }
        this.tableData.push(value);

      },
      changeMaxValue(index){
        this.editData[index+1].fieldValue = this.editData[index].maxRegionValue;
      },
      // 提交
      handleSubmit () {

        this.$refs['form'].validate((valid) => {
          /*if (valid) {*/
          var formData= JSON.stringify(this.form);
          const params = {
           formData: formData
          }
         var valueData = [];
          for(var i=0;i<this.tableData.length;i++){
            if(this.isShowDropdown == true && this.isShowSql==false){
              var value = {
                widgetCode:this.tableData[i].widgetCode,
                fieldValue:this.tableData[i].fieldValue,
                sorting:this.tableData[i].sorting,
                fieldCode:this.tableData[i].fieldCode,
              }
              valueData.push(value);
              params.valueData = JSON.stringify(valueData);
            }
            if(this.isShowRange == true){
              var value = {
                widgetCode:this.tableData[i].widgetCode,
                fieldValue:this.tableData[i].fieldValue,
                maxRegionValue:this.tableData[i].maxRegionValue,
              }
              valueData.push(value);
              params.valueData = JSON.stringify(valueData);
            }
          }
          console.log(valueData);

         // let temp = Object.assign({},this.form,{})

          // console.log(params)


            this.$confirm('确定保存编辑的信息吗？, 是否继续?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.loadingSubmitBtn = true
              RequestURL.setWidget(params).then(res => {
                this.loadingSubmitBtn = false
                this.$message({
                  type: 'success',
                  message: res.msg || '操作成功'
                })
                this.dialogEditVisible = false

              }).catch(() => {
                this.loadingSubmitBtn = false
              })
            })

         /* }*/
        })
      },

      // 删除
      handleDelete (row) {

      },

      handleChangeCity (val, name) {
        this.editForm['areaName'] = name
      },

      // 加载数据
      _loadData (btnQuery) {
        if (btnQuery) {
          this.loadingQueryBtn = true
        }

        this.loadingView = true

        /*const params = this._getParams(this.form)
        RequestURL.queryTransferProcessList(params).then(res => {
          this.tableData = res.data
          this.listQuery.total = res.count
          this.loadingQueryBtn = false
          this.loadingView = false
        }).catch(() => {
          this.loadingQueryBtn = false
          this.loadingView = false
        })*/
      },

      // 表单回填
      _setForm () {
        this.$utils.setFormInfo(this.editForm, this.currentRowData, () => {
          this.editForm['procName'] = this.currentRowData['programmeName']
          this.editForm['procId'] = this.currentRowData['id']
        })

        // 移除表单回填时 element ui自动添加的校验效果
        setTimeout(() => {
          this.$refs['editForm'].clearValidate()
        }, 100)
      },
      _resetForm (formName, callback) {
        this.$refs[formName] && this.$refs[formName].resetFields()
        if (callback && typeof callback === 'function') {
          callback()
        }
      },

    },

  }
</script>

<style scoped lang="scss">
  .save-btn{
    text-align: center;
  }
</style>
