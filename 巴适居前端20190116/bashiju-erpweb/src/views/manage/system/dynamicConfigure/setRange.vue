<template>
  <div class="page-content">
    <el-row :gutter="20">
      <el-col :span="10">
        <div class="grid-content">
          <div class="page-content-hd">
            <div class="query-form">
              <el-form size="small" ref="queryForm" :inline="true" :model="queryForm" class="demo-form-inline">
                <el-form-item label="城市" prop="cityCode">
                  <base-city-cascader v-model="queryForm.cityCode" style="width:100px;" @initSelectData="initSelectData"></base-city-cascader>
                </el-form-item>
                <el-form-item label="编号" prop="widgetCode">
                  <el-input v-model="queryForm.widgetCode" placeholder="请输入编号" style="width:100px;"></el-input>
                </el-form-item>

                <el-form-item label="名称" prop="name">
                  <el-input v-model="queryForm.name" placeholder="请输入名称" style="width:100px;"></el-input>
                </el-form-item>

                <el-form-item>
                  <el-button type="primary" @click.native.prevent="handleQuery" :loading="loadingQueryBtn">查询</el-button>
                  <el-button @click.native.prevent="_resetForm('queryForm')">重置</el-button>
                  <el-button type="primary" @click="handleAddType" :loading="loadingQueryBtn">新增区间</el-button>
                </el-form-item>
              </el-form>
            </div>
          </div>
          <div class="page-content-bd" v-loading="loadingView">
            <el-table
              :data="tableData"
              border
              highlight-current-row
              @row-click="_showValue"
              align="center"
              style="width: 100%"
              height="580"
            >
              <el-table-column
                prop="cityName"
                align="center"
                label="城市">
              </el-table-column>

              <el-table-column
                prop="widgetCode"
                align="center"
                label="编号">
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
                  <el-button  @click="handleAdd(scope.row)" type="text" size="small">设置区间</el-button>
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
      <el-col :span="14">
        <div class="grid-content">
          <div class="page-content-hd">
            <div class="value-title">
              配置项明细
            </div>
          </div>
          <div class="page-content-bd" v-loading="loadingItemView">
            <el-table
              :data="valueData"
              border
              align="center"
              style="width: 100%"
              height="580"
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

            </el-table>

          </div>



        </div>
      </el-col>
    </el-row>



    <template>
      <b-dialog :show.sync="dialogAddVisible" :title="dialogTitle" width="600px">
        <el-form v-loading="loadingEditView" :model="addForm" status-icon :rules="addFormRules" ref="addForm" label-width="100px">
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
          <span v-for="(item,index) in editData">
            <el-row>
              <el-col :span="12">
                <el-input v-model="item.fieldValue" placeholder="" :readonly="true"></el-input>
              </el-col>
              <el-col :span="12">
                <el-input v-model="item.maxRegionValue" @blur="changeMaxValue(index)" placeholder=""></el-input>
              </el-col>
            </el-row>
          </span>
          <el-row>
            <el-col :span="24">
              <div class="save-btn">
                <el-button type="primary" @click="saveRange" :loading="loadingSaveBtn">确定</el-button>
                <el-button  @click="dialogAddVisible = false" >取消</el-button>
              </div>
            </el-col>
          </el-row>
        </el-form>
      </b-dialog>
      <b-dialog :show.sync="dialogAddRangeType" title="新增区间" width="400px">
         <el-form size="small" ref="form" :inline="true" :model="form" :rules="formRules" class="demo-form-inline">
            <el-form-item  label="城市" prop="cityCode">
              <base-city-cascader v-model="form.cityCode" @change="handleChangeCity"></base-city-cascader>
            </el-form-item>

            <el-form-item label="控件名称" prop="name">
              <el-input v-model="form.name" placeholder="请输入"></el-input>
            </el-form-item>
           
            <el-form-item label="控件编号" prop="widgetCode">
              <el-input v-model="form.widgetCode" @blur="validateWidgetCode" placeholder="请输入"></el-input>
            </el-form-item>
            
            <el-form-item>
              <el-button type="primary"  @click="saveRangeType" :loading="loadingSaveTypeBtn">确定</el-button>
              <el-button   @click="dialogAddRangeType=false">取消</el-button>
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
    name: 'setRange',
    mixins: [PageList],
    components: {BaseCityCascader},
    data () {
      return {
        queryForm: {
          cityCode: '',
          widgetCode: '',
          name: ''
        },
        addForm: {
          widgetCode: '',
          fieldValue:'',
          maxRegionValue: '',
        },
        form:{},
        // 表单校验配置
        addFormRules: {
          fieldValue: [
            {required: true, message: '请输入值', trigger: 'blur'}
          ],
          maxRegionValue: [
            {required: true, message: '请输入值', trigger: 'blur'}
          ]
        },
        formRules:{
          cityCode: [
            {required: true, message: '请选择城市', trigger: 'blur'}
          ],
          name: [
            {required: true, message: '请输入名称', trigger: 'blur'}
          ],
          widgetCode: [
            {required: true, message: '请输入编号', trigger: 'blur'}
          ]
        },
        dialogAddVisible: false,
        dialogAddRangeType: false,
        loadingSubmitBtn: false,
        loadingSaveBtn: false,
        loadingForm: false,
        loadingItemView: false,
        loadingSaveTypeBtn: false,
        currentRowData: null, // 当前操作的行数据
        currentDynamicConfigureItem:null,
        valueData: [],
        minTitle:'下限（>）',
        maxTitle:'上限（<=）',
        loadingEditView: false,
        editData: [],
        maxregion: [],
        dialogTitle: '',
        hasWidget: false,
        nowQueryForm: {},
        cityName : ''
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
       handleChangeCity (val, label, data) {
        // alert(111);
        console.log(val, label, data)
       // alert(data.parent.name)
        this.cityName = data.name;
      },
      // 新增
      handleAdd (row) {
        this._resetForm('addForm')
        this.currentRowData = null // 置空当前操作的行数据
        this.isAdd = true;
        this._loadEditData(row.widgetCode,row.type);
        this.dialogAddVisible = true;
        this.addForm.widgetCode=row.widgetCode;
        this.currentDynamicConfigureItem=row;
        this.dialogTitle = "对"+row.name+"进行设置";
      },
      handleAddType (row) {
        this._resetForm('form')
        this.dialogAddRangeType = true
      },
      //验证编号是否被用了
      validateWidgetCode(){
          const params = {
            widgetCode: this.form.widgetCode,
            widgetType: '3',//区间
          }
          this.loadingSaveTypeBtn = true
          RequestURL.validateWidgetCode(params).then(res => {
             this.loadingSaveTypeBtn = false
            if (res.success) {
                this.hasWidget = false;
              }else{
                this.hasWidget = true;
                this.$message({
                  type: 'warning',
                  message: res.data.msg
                })
            }

        }).catch(() => {
          this.hasWidget = true;
          this.loadingSaveTypeBtn = false
        })

      },
      saveRangeType(){
        this.$refs['form'].validate((valid) => {
          if (valid) {
            
            if(this.hasWidget){
              
              this.$message({
                    type: 'warning',
                    message: '编号已经被使用了'
                  })
            }else{
              this.form.type = '3'
            const params = {
              formData: JSON.stringify({...this.form})
            }
            this.$confirm('确定保存编辑的信息吗？, 是否继续?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.loadingSaveTypeBtn = true
              RequestURL.setWidget(params).then(res => {
                this.loadingSaveTypeBtn = false
                this.dialogAddRangeType = false
                if (res.success) {
                  
                  this.$message({
                    type: 'success',
                    message: res.msg || '操作成功'
                  })
                  this._loadData(false)
                  //写日志
                  let logContent = "新增一个区间，城市："+this.cityName+";区间名称："+this.form.name
                  let message = {
                      sourceCode: '区间配置',
                      sourceTypeId: 6,
                      operatTypeId: 1,
                      logContent: logContent
                    }
                  systemAddLog({message: JSON.stringify(message)}).then(res => {
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
                this.loadingSaveTypeBtn = false
                this.dialogAddRangeType = false
                this.$message({
                  type: 'info',
                  message: err.msg || '保存失败'
                })
              })
            })
            }
            
          }
        })
      },
      saveRange(){
        for(let i=0;i<this.editData.length;i++){
          if(this.editData[i].maxRegionValue != ''){
            /*const item = {
              name: 'maxRegionValue',
              value: this.editData[i].maxRegionValue
            }*/
            this.maxregion.push(this.editData[i].maxRegionValue);
          }
        }
        const jsonData = {
          firstmin: this.editData[0].fieldValue,
          widgetCode: this.currentDynamicConfigureItem.widgetCode,
          maxregion: JSON.stringify(this.maxregion)
        }
        RequestURL.saveOrUpdateRangeItem(jsonData).then(res => {
          this.loadingSaveBtn = false
          this.dialogAddVisible = false
          console.log(res);
          if (res.success) {
            this.$message({
              type: 'success',
              message: res.msg || '操作成功'
            })
            this._loadValueData(this.currentDynamicConfigureItem.widgetCode,this.currentDynamicConfigureItem.type);
          }else{
            this.$message({
              type: 'warning',
              message: res.msg
            })
          }
        }).catch((err) => {
          console.log(err);
          this.loadingSaveBtn = false
          this.dialogAddVisible = false
          this.$message({
            type: 'info',
            message: err.msg || '保存失败'
          })
        })
      },

      // 编辑
      handleEdit (row) {
        this._resetForm('addForm')
        this.currentRowData = row
        this.isAdd = false
        this.dialogAddVisible = true
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

            if (this.isAdd) {
              params.id = ''
            }

            this.$confirm('确定保存编辑的信息吗？, 是否继续?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.loadingSubmitBtn = true
              RequestURL.saveOrUpdateSelectItem(params).then(res => {
                this.loadingSubmitBtn = false
                this.dialogAddVisible = false
                if (res.success) {
                  this.$message({
                    type: 'success',
                    message: res.data.msg || '操作成功'
                  })
                  this._loadValueData(this.currentDynamicConfigureItem.widgetCode,this.currentDynamicConfigureItem.type);
                }else{
                  this.$message({
                    type: 'warning',
                    message: res.data.msg
                  })
                }
              }).catch((err) => {
                console.log(err);
                this.loadingSubmitBtn = false
                this.$message({
                  type: 'info',
                  message: err.data.msg || '保存失败'
                })
              })
            })

          }
        })
      },
      changeMaxValue(index){
        var val = parseInt(this.editData[index].maxRegionValue);
        //layer.msg(val)
        console.log("上限："+val);

        var preval = parseInt(this.editData[index].fieldValue);
        console.log("下限："+preval);
        //layer.msg(preval)
        var nextval = parseInt(this.editData[index+1].maxRegionValue);
        //layer.msg(nextval)
        if(isNaN(val) || val<preval){
          //layer.msg(11)
          this.editData[index+1].fieldValue = '';
          this.editData[index].maxRegionValue = '';
          if(val<preval){
            this.$message({
              type: 'warning',
              message: "数值不能小于"+ preval
            })
          }

        }

        if(!isNaN(nextval) && val>nextval){
          this.$message({
            type: 'warning',
            message: "数值不能大于"+nextval
          })
        }

        this.editData[index+1].fieldValue = this.editData[index].maxRegionValue;


        /*if(!$(obj).attr("id") && !isNaN(val) ){
          var previnptv = $(obj).parent().parent().prev().children().eq(1).find("input").val();
          if(previnptv==""){
            $(".newmax").eq(0).val(val);
            $(".newmax").eq(0).parent().parent().next().children().eq(0).find("input").val(val);
            $(obj).val("");
            var prevvalue = parseInt($(".newmax").eq(0).parent().parent().prev().children().eq(1).find("input").val());
            if(val<prevvalue){
              $(".newmax").eq(0).val("");
              $(".newmax").eq(0).parent().parent().next().children().eq(0).find("input").val("");
              layer.alert("数值不能小于"+prevvalue);
            }
          }

        }*/

      },
      // 删除
      handleDelete (row) {
        this.$confirm('确定删除该条数据？, 是否继续?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          RequestURL.delDropdownValue({id: row.id}).then(res => {
            this.$message({
              type: 'success',
              message: res.msg || '删除成功'
            })
            this._loadValueData(this.currentDynamicConfigureItem.widgetCode,this.currentDynamicConfigureItem.type);
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

       // const params = this._getParams(this.queryForm)
       let params = Object.assign({}, this.nowQueryForm, {
          limit: this.listQuery.limit,
          page: this.listQuery.page
        })
        RequestURL.getRangeType(params).then(res => {
          this.tableData = res.data
          this.listQuery.total = res.count
          this.loadingQueryBtn = false
          this.loadingView = false
          this._loadValueData(this.tableData[0].widgetCode,this.tableData[0].type);
        }).catch(() => {
          this.loadingQueryBtn = false
          this.loadingView = false
        })
      },
      _loadValueData(widgetCode,type){
        this.loadingItemView = true;
        const params = {
          widgetCode:widgetCode,
          type:type
        };
        RequestURL.getRange(params).then(res => {
          console.log(res.data);
          this.valueData = res.data
          this.loadingItemView = false;
        }).catch(() => {
          this.loadingItemView = false;
        })
      },
      _loadEditData(widgetCode,type){
        this.loadingEditView = true;
        const params = {
          widgetCode:widgetCode,
          type:type
        };
        RequestURL.getRange(params).then(res => {
          console.log(res.data);
          this.editData = res.data
          this.loadingEditView = false;
          for(let i = this.editData.length;i<10;i++){
            const valueItem = {
              fieldValue:'',
              maxRegionValue:''
            }
            this.editData.push(valueItem);
          }
        }).catch(() => {
          this.loadingEditView = false;
        })
      },
      // 表单回填
      _setForm () {
        this.$utils.setFormInfo(this.addForm, this.currentRowData, () => {
          this.addForm['widgetCode'] = this.currentRowData['widgetCode']
          this.addForm['fieldCode'] = this.currentRowData['fieldCode']
          this.addForm['fieldValue'] = this.currentRowData['fieldValue']
          this.addForm['sorting'] = this.currentRowData['sorting']
          this.addForm['id'] = this.currentRowData['id']
        })

        // 移除表单回填时 element ui自动添加的校验效果
        setTimeout(() => {
          this.$refs['addForm'].clearValidate()
        }, 100)
      },
      _showValue(row, event, column){
        //console.log(row.widgetCode)
        this.currentDynamicConfigureItem = row;
        this._loadValueData(row.widgetCode,row.type);

      }
    },

  }
</script>

<style scoped lang="scss">
  .value-title{
    height: 34px;
    line-height: 34px;
  }
  .save-btn{
    text-align: center;
    padding-top:30px;
    padding-bottom: 10px;
  }
</style>
