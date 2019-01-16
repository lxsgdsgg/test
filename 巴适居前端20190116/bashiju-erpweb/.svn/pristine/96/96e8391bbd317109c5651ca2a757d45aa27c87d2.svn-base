<template>
  <div class="page-content">
    <div class="page-content-hd">
      <el-form :model="queryForm" ref="queryForm" :inline="true" size="small">
        <el-form-item label="模板名称" prop="templateName">
          <el-input v-model="queryForm.templateName" type="text"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click.native.prevent="handlerQuery(true)" :loading="loadingQueryBtn">查询</el-button>
          <el-button @click="resetForm('queryForm')">清空</el-button>
          <el-button v-hasOnlyBtn="'addBtn'" type="primary" @click.native.prevent="submitHandler('')">新增</el-button>
        </el-form-item>
      </el-form>

    </div>

    <div class="page-content-hd">
      <el-table border align="center" :data="tableData" v-loading="loadingView" tooltip-effect="light">
        <el-table-column label="模板名称" align="left" prop="templateName" width="200px" show-overflow-tooltip></el-table-column>
        <el-table-column label="下一页选择器" align="left" prop="nextSelector" width="200px" show-overflow-tooltip></el-table-column>
        <el-table-column label="标题选择器" align="left" prop="titleSelector" show-overflow-tooltip></el-table-column>
        <el-table-column label="小区选择器" align="left" prop="communitySelector" show-overflow-tooltip></el-table-column>
        <el-table-column label="楼层选择器" align="left" prop="floorSelector" show-overflow-tooltip></el-table-column>
        <el-table-column label="建筑面积" align="left" prop="buildSpaceSelector" show-overflow-tooltip></el-table-column>
        <el-table-column label="朝向选择器" align="left" prop="orientationSelector" show-overflow-tooltip></el-table-column>
        <el-table-column label="装修选择器" align="left" prop="decorationSelector" show-overflow-tooltip></el-table-column>
        <el-table-column label="价格选择器" align="left" prop="priceSelector" show-overflow-tooltip></el-table-column>
        <el-table-column label="户型选择器" align="left" prop="roomSelector" show-overflow-tooltip></el-table-column>
        <el-table-column label="房屋性质选择器" align="left" prop="houseTypeSelector" show-overflow-tooltip></el-table-column>
        <el-table-column label="电话选择器" align="left" prop="phoneSelector" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作区域" align="center" width="100px">
          <template slot-scope="scope">
            <el-button v-hasMultipleBtn="['editBtn',scope.row]" @click="submitHandler(scope.row)" size="small" type="text">编辑</el-button>
            <el-button v-hasMultipleBtn="['delBtn',scope.row]" @click="deleteTemplate(scope.row)" size="small" type="text">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--分页控件-->
      <b-pagination
        :listQuery="listQuery"
        @handleSizeChange="handleSizeChange"
        @handleCurrentChange="handleCurrentChange">
      </b-pagination>

      <el-dialog
        title="采集模板编辑页面"
        :visible.sync="editDlgVisible"
        :close-on-click-modal="false"
        top="2vh"
        width="40%">
        <el-form :model="editForm" ref="editForm" label-width="150px" :rules="rules" size="small">
          <el-form-item label="模板名称" prop="templateName">
            <el-input v-model="editForm.templateName" type="text"></el-input>
          </el-form-item>

          <el-form-item label="A标签选择器" prop="aSelector">
            <el-input v-model="editForm.aSelector" type="text"></el-input>
          </el-form-item>

          <el-form-item label="下一页标签选择器" prop="nextSelector">
            <el-input v-model="editForm.nextSelector" type="text"></el-input>
          </el-form-item>

          <el-form-item label="标题选择器" prop="titleSelector">
            <el-input v-model="editForm.titleSelector" type="text"></el-input>
          </el-form-item>

          <el-form-item label="小区名称选择器" prop="communitySelector">
            <el-input v-model="editForm.communitySelector" type="text"></el-input>
          </el-form-item>

          <el-form-item label="楼层选择器" prop="floorSelector">
            <el-input v-model="editForm.floorSelector" type="text"></el-input>
          </el-form-item>

          <el-form-item label="建筑面积选择器" prop="buildSpaceSelector">
            <el-input v-model="editForm.buildSpaceSelector" type="text"></el-input>
          </el-form-item>

          <el-form-item label="朝向选择器" prop="orientationSelector">
            <el-input v-model="editForm.orientationSelector" type="text"></el-input>
          </el-form-item>

          <el-form-item label="装修选择器" prop="decorationSelector">
            <el-input v-model="editForm.decorationSelector" type="text"></el-input>
          </el-form-item>

          <el-form-item label="价格选择器" prop="priceSelector">
            <el-input v-model="editForm.priceSelector" type="text"></el-input>
          </el-form-item>

          <el-form-item label="户型选择器" prop="roomSelector">
            <el-input v-model="editForm.roomSelector" type="text"></el-input>
          </el-form-item>

          <el-form-item label="房屋性质选择器" prop="houseTypeSelector">
            <el-input v-model="editForm.houseTypeSelector" type="text"></el-input>
          </el-form-item>

          <el-form-item label="联系电话选择器" prop="phoneSelector">
            <el-input v-model="editForm.phoneSelector" type="text"></el-input>
          </el-form-item>

          <div class="btn-group">
            <el-button @click="saveTemplateListData" type="primary" size="small" :loading="saveLoadingBtn">保存</el-button>
            <el-button @click="handleCancel" type="primary" size="small">取消</el-button>
          </div>

        </el-form>
      </el-dialog>

    </div>
  </div>
</template>

<script>
  import PageList from '@/mixins/pageList'
  import * as RequestURL from '@/request/manage/analysizTemplate'
  import {systemAddLog,systemUpdateLog,systemQueryLog,systemDelLog} from '@/request/log/systemPlatformLog'

  export default {
    name: "analysisTemplet",
    mixins: [PageList],
    data() {
      return {
        saveLoadingBtn: false,
        editDlgVisible: false,
        tableData: [],
        loadingView: false,
        isAdd: true,
        queryForm: {
          templateName: ''
        },
        editForm:{
          id: '',
          templateName: '',
          aSelector: '',
          nextSelector: '',
          titleSelector: '',
          communitySelector: '',
          floorSelector: '',
          buildSpaceSelector: '',
          orientationSelector: '',
          decorationSelector: '',
          priceSelector: '',
          roomSelector: '',
          houseTypeSelector: '',
          phoneSelector: ''
        },
        rules:{
          templateName:[{required: true,message: '该项为必填项',trigger: blur}],
          aSelector:[{required: true,message: '该项为必填项',trigger: blur},{ validator: this.checkData, trigger: 'blur'}],
          nextSelector:[{required: true,message: '该项为必填项',trigger: blur},{ validator: this.checkData, trigger: 'blur'}],
          titleSelector:[{required: true,message: '该项为必填项',trigger: blur},{ validator: this.checkData, trigger: 'blur'}],
          communitySelector:[{required: true,message: '该项为必填项',trigger: blur},{ validator: this.checkData, trigger: 'blur'}],
          floorSelector:[{required: true,message: '该项为必填项',trigger: blur},{ validator: this.checkData, trigger: 'blur'}],
          buildSpaceSelector:[{required: true,message: '该项为必填项',trigger: blur},{ validator: this.checkData, trigger: 'blur'}],
          orientationSelector:[{required: true,message: '该项为必填项',trigger: blur},{ validator: this.checkData, trigger: 'blur'}],
          decorationSelector:[{required: true,message: '该项为必填项',trigger: blur},{ validator: this.checkData, trigger: 'blur'}],
          priceSelector:[{required: true,message: '该项为必填项',trigger: blur},{ validator: this.checkData, trigger: 'blur'}],
          roomSelector:[{required: true,message: '该项为必填项',trigger: blur},{ validator: this.checkData, trigger: 'blur'}],
          houseTypeSelector:[{required: true,message: '该项为必填项',trigger: blur},{ validator: this.checkData, trigger: 'blur'}],
          phoneSelector:[{required: true,message: '该项为必填项',trigger: blur},{ validator: this.checkData, trigger: 'blur'}]
        },
        originalData: {}
      }
    },
    methods:{
      // validator: checkData,
      checkData (rule, value, callback) {
        if (value) {
          if (/[\u4E00-\u9FA5]/g.test(value)) {
            callback(new Error('编码不能输入汉字!'));
          } else {
            callback();
          }
        }
        callback();
      },
      // 取消操作
      handleCancel() {
        this.editDlgVisible = false
      },

      // 添加电话黑名单
      submitHandler(row){
        this.$nextTick(()=>{
          this.resetForm('editForm')
          this.setForm(row)
          this.editDlgVisible = true
        })
      },

      handlerQuery(btnQuery){
        this.listQuery.page = 1
        this.listQuery.currentPage = 1
        this._loadData(btnQuery)
      },

      /**
       * 查询电话黑名单数据信息
       * @param btnQuery
       * @private
       */
      _loadData(btnQuery){
        if(btnQuery){
          this.loadingQueryBtn = true
        }
        this.loadingView = true
        let params = Object.assign({},this.queryForm,{
          page: this.listQuery.page,
          limit: this.listQuery.limit
        })
        RequestURL.queryTemplatePageList(params).then(res=>{
          this.tableData = res.data
          this.listQuery.total = res.count
          this.loadingView = false
          this.loadingQueryBtn = false
        }).catch(err=>{
          this.$message({
            type: 'error',
            message: err.data.msg
          })
          this.loadingView = false
          this.loadingQueryBtn = false
        })
      },

      // 保存采集模板数据
      saveTemplateListData(){
        this.$refs['editForm'].validate((valid)=>{
          if(valid){
            this.$confirm('确定要保存吗？','提示',{
              confirmButtonText: '',
              cancelButtonText: '',
              type: 'warning'
            }).then(()=>{
              let params = JSON.stringify({...this.editForm})
              let cfg = {
                headers: {
                  'Content-Type': 'application/json;charset=UTF-8;'
                }
              }
              this.saveLoadingBtn = true
              RequestURL.saveAnalysisTemplet({...this.editForm},cfg).then(res=>{
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
                this.saveLoadingBtn =false
                this.editDlgVisible = false

                if(this.editForm.id){
                  let message = {sourceCode:this.editForm.templateName,sourceTypeId:'14',operatTypeId:'2'
                    ,labelData:this.$utils.getFormFields(this.$refs['editForm'])
                    ,originalData:this.originalData,newData: {...this.editForm}}
                  systemUpdateLog({message: JSON.stringify(message)})
                }else{
                  let message = {sourceCode:this.editForm.templateName,sourceTypeId:'14',operatTypeId:'1'
                    ,logContent: `新增采集模板:${this.editForm.templateName}`}
                  systemAddLog({message: JSON.stringify(message)})
                }
              }).catch(err=>{
                console.log(err)
                this.saveLoadingBtn = false
                this.$message({
                  type: 'error',
                  message: err.data.msg
                })
              })
            })
            //   .catch(()=>{
            //   this.$message({
            //     type: 'info',
            //     message: '用户取消操作'
            //   })
            // })
          }
        })
      },

      // 重置表单
      resetForm(formName){
        if(this.$refs[formName]){
          this.$refs[formName].resetFields()
        }
      },

      // 设置表单数据
      setForm(row){
        for(let i in this.editForm){
          this.editForm[i] = row[i]
        }
        this.originalData = Object.assign({},{...this.editForm})
      },

      // 删除采集模板
      deleteTemplate(row){
        this.$confirm('确定要删除此数据吗？','提示',{
          confirmButtonText: '',
          cancelButtonText: '',
          type: 'warning'
        }).then(()=>{
          RequestURL.delAnalysisTemplet({id: row.id},'').then(res=>{
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
            let message = {sourceCode:row.templateName,sourceTypeId:'14',operatTypeId:'3'
              ,logContent: `删除采集模板： ${row.templateName}`}
            systemDelLog({message: JSON.stringify(message)})
          }).catch(err=>{
            console.log(err)
            this.$message({
              type: 'error',
              message: err.data.msg
            })
          })
        })
        //   .catch(()=>{
        //   this.$message({
        //     type: 'info',
        //     message: '用户取消操作'
        //   })
        // })
      },
    }
  }
</script>

<style scoped>

</style>
