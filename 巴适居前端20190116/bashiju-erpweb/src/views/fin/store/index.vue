<template>
  <div class="page-content">
    <div class="page-content-hd">
      <el-form :model="queryForm" ref="queryForm" :inline="true" size="small">
        <el-form-item label="支付日期" prop="payDate">
          <el-date-picker
            v-model="queryForm.payDate"
            value-format="yyyy-MM-dd"
            style="width: 160px"
            type="date"
            cleable
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="选择门店" prop="shareDeptId">
          <base-dept-cascader v-model="queryForm.shareDeptId" style="width: 160px;" @change="jurgeUser" cleable></base-dept-cascader>
        </el-form-item>

        <el-button type="primary" @click="_loadData(true)" :loading="queryLoadingBtn" size="small">查询</el-button>
        <el-button type="primary" @click="handlerSubmit" size="small">新增</el-button>
        <el-button type="success" @click="handlerSubmit" size="small">导出当前页</el-button>
      </el-form>
    </div>
    <div class="page-content-hd">
      <el-table :data="tableData" border align="center" tooltip-effect="light" size="small" :loading="loadingView">
        <el-table-column label="录入时间" prop="addTime" show-overflow-tooltip>
          <template slot-scope="scope">
            {{$utils.timeFormat(scope.row.addTime,'{y}-{m}-{d} {h}:{i}:{s}')}}
          </template>
        </el-table-column>
        <el-table-column label="录入人" prop="operator" show-overflow-tooltip></el-table-column>
        <el-table-column label="支出日期" prop="payDate" show-overflow-tooltip>
          <template slot-scope="scope">
            {{$utils.timeFormat(scope.row.addTime,'{y}-{m}-{d}')}}
          </template>
        </el-table-column>
        <el-table-column label="分摊金额（元）" prop="totalMoney" show-overflow-tooltip></el-table-column>
        <el-table-column label="分摊门店（下属门店）" prop="shareDeptName" show-overflow-tooltip></el-table-column>
        <el-table-column label="用途说明" prop="remark" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button type="primary" @click="handlerSubmitDetatil(scope.row)" size="mini">设置分摊详细</el-button>
            <el-button type="primary" @click="handlerSubmit(scope.row)" size="mini">修改</el-button>
            <el-button type="primary" @click="deleteStoreShare(scope.row)" size="mini">删除</el-button>
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
    <template>
      <b-dialog :show.sync="editDialogView" :title="title" width="450px">
        <el-form label-width="100px" :model="editForm" ref="editForm" :rules="rules" size="small">
          <el-form-item label="支付日期" prop="payDate">
            <el-date-picker
              v-model="editForm.payDate"
              value-format="yyyy-MM-dd"
              style="width: 215px"
              type="date">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="总金额" prop="totalMoney">
            <el-input type="number" v-model="editForm.totalMoney" :min=0 style="width: 215px"></el-input>
          </el-form-item>
          <el-form-item label="分摊门店" prop="shareDeptId">
            <base-dept-cascader v-model="editForm.shareDeptId" style="width: 150px" @change="jurgeUser"></base-dept-cascader>
            <span>[下属门店]</span>
          </el-form-item>
          <el-form-item label="用途说明" prop="remark">
            <el-input v-model="editForm.remark" style="width: 215px"></el-input>
          </el-form-item>

          <el-form-item class="margin-b-none">
            <el-button type="primary" :loading="saveLoadingBtn" @click="saveOrUpdateData">保存</el-button>
            <el-button type="primary" @click="handlerCancel">取消</el-button>
          </el-form-item>

        </el-form>
      </b-dialog>

      <b-dialog :show.sync="eidtDetailDialogView" title="设置分摊详细" width="400px">
        <el-form :model="editDetailForm" ref="editDetailForm" size="small" :inline="true">
          <el-row :gutter="10">
            <el-col :span="12"><span style="margin-left: 20px;font-weight: 700;">门店</span></el-col>
            <el-col :span="12"><span style="margin-left: 20px;font-weight: 700;">分摊金额</span></el-col>
          </el-row>
          <br>
          <div v-for="(item,index) in editDetailForm.shareDetail">
            <el-form-item :prop="'shareDetail.'+index +'.shareDeptId'" :rules="{required: true, message: '门店不能为空', trigger: 'change'}">
              <base-dept-cascader v-model="item.shareDeptId" style="width: 160px;" :disabled="true"></base-dept-cascader>
            </el-form-item>

            <el-form-item :prop="'shareDetail.'+index +'.shareMoney'" :rules="{required: true, message: '分摊金额', trigger: 'blur'}">
              <el-input type="number" :min=0 v-model="item.shareMoney" style="width: 160px"></el-input>
              <span class="el-input__suffix">
                <span class="el-input__suffix-inner">
                  <i class="el-input__icon">元</i>
                </span>
              </span>
            </el-form-item>
          </div>
          <div class="btn-group">
            <el-button type="primary" size="small" @click="saveStoreShareDetailData" :loading="saveDetailLoadingBtn">保存</el-button>
            <el-button type="info" @click="handlerCancel" size="small">取消</el-button>
            <el-button type="primary" size="small" @click="divideShareMoney">均摊</el-button>
          </div>

        </el-form>
      </b-dialog>
    </template>
  </div>
</template>

<script>
  import PageList from '@/mixins/pageList'
  import * as RequestURL from '@/request/fin/store'
  import BaseDeptCascader from '@/components/BaseCascader/dept'

  export default {
    name: "storeShare",
    mixins: [PageList],
    components: {BaseDeptCascader},
    data(){
      return {
        title: '新增门店分摊',
        hackReset: true,
        loadingView: false,
        saveLoadingBtn: false,
        saveDetailLoadingBtn: false,
        queryLoadingBtn: false,
        editDialogView: false,
        eidtDetailDialogView: false,
        tableData: [],
        queryForm: {
          payDate: '',
          shareDeptId: ''
        },
        editForm:{
          id: '',
          shareDeptId: '',
          shareDeptName: '',
          totalMoney: 0,
          payDate: '',
          remark: ''
        },
        editDetailForm:{
          shareDetail: {
            shareId: '',
            shareDeptId: '',
            shareDeptName: '',
            payDate: '',
            shareMoney: ''
          }
        },
        rules: {
          payDate: [{required: true, message: '此项必填项', trigger: 'change'}],
          totalMoney: [{required: true, message: '此项必填项', trigger: 'blur'}],
          shareDeptId: [{required: true, message: '此项必填项', trigger: 'change'}]
        },
        storeDeptList: [],
        storeDetailList: [],
        currentRowData: null
      }
    },
    methods:{
      jurgeUser(value,name,data){
        this.editForm.shareDeptName = name
        // 普通用户
        if (data.dataType === 'city') {
          setTimeout(() => {
            this.editForm.shareDeptId = ''
            this.queryForm.shareDeptId = ''
          }, 50)
        }else{
          this.querySubordinateStoreDataByDeptId(this.editForm.shareDeptId)
        }
      },
      handlerSubmit(row){
        this.hackReset = false
        this.setForm('')
        if(row){
          this.title = '修改门店分摊'
          this.isAdd = false
          row.payDate = this.$utils.timeFormat(row.payDate,'{y}-{m}-{d}')
          this.setForm(row)
        }
        this.$nextTick(()=>{
          this.hackReset = true
          this.editDialogView = true
        })
      },
      handlerCancel(){
        this.editDialogView = false
        this.eidtDetailDialogView = false
      },
      handlerSubmitDetatil(row){
        this.currentRowData = null
        this.currentRowData = row
        this.queryStoreShareDetailByShareId(row.id)
        this.setForm('')
        this.$nextTick(()=>{
          this.eidtDetailDialogView = true
        })
      },
      deleteStoreShare(row){
        this.$confirm('确认要删除此配置吗','提示',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(()=>{
          RequestURL.deleteStoreShareDataById({id: row.id}).then(res=>{
            this._showMessage(res.success,res.msg)
            this._loadData(false)
          })
        })
      },

      _showMessage(success,msg){
        if(success){
          this.$message({
            type:'success',
            message: msg
          })
        }else{
          this.$message({
            type:'error',
            message: msg
          })
        }
      },

      divideShareMoney(){
        const size = this.storeDetailList.length
        let totalMoney = this.currentRowData.totalMoney
        let shareMoney = totalMoney/size
        for(let i in this.editDetailForm.shareDetail){
          this.$set(this.editDetailForm.shareDetail[i],'shareMoney',shareMoney)
        }
      },

      // 查询数据
      _loadData(btnQuery){
        if(btnQuery){
          this.listQuery.page = 1
          this.listQuery.currentPage = 1
          this.loadingQueryBtn = true
        }
        this.loadingView = true
        let param = {...this.queryForm}
        let params = Object.assign({},{jsonData: JSON.stringify(param)},{page: this.listQuery.page,limit: this.listQuery.limit})
        RequestURL.queryStoreSharePageList(params).then(res=>{
          this.tableData = res.data
          this.listQuery.total = res.total
          this.loadingView = false
          this.loadingQueryBtn = false
        }).catch(err=>{
          console.log(err)
          this.loadingView = false
          this.loadingQueryBtn = false
        })
      },
      saveOrUpdateData(){
        this.$refs['editForm'].validate(valid=>{
          if(valid){
            if(this.storeDeptList && this.storeDeptList.length === 0){
              this._showMessage(false,'不存在下属门店')
              return false
            }else{
              this.$confirm('确认要提交数据吗','提示',{
                confirmButtonText: '',
                cancelButtonText: '',
                type: 'warning'
              }).then(()=>{
                this.saveLoadingBtn = true
                let params = Object.assign({},this.editForm,{})
                // 金额元转换为分
                params.totalMoney = params.totalMoney * 100
                RequestURL.saveOrUpdateStoreShareData({jsonData: JSON.stringify(params)}).then(res=>{
                  this._showMessage(res.success,res.msg)
                  this.saveLoadingBtn = false
                  this.handlerCancel()
                  this._loadData(false)
                }).catch(err=>{
                  console.log(err)
                  this.saveLoadingBtn = false
                })
              })
            }
          }
        })
      },

      // 保存设置分摊详细
      saveStoreShareDetailData(){
        this.$refs['editDetailForm'].validate(valid=>{
          if(valid){
            this.$confirm('确认要提交数据吗','提示',{
              confirmButtonText: '',
              cancelButtonText: '',
              type: 'warning'
            }).then(()=>{
              let totalMoney = this.currentRowData.totalMoney
              let totalMoneyNew = 0
              for(let i in this.editDetailForm.shareDetail){
                let temp = this.editDetailForm.shareDetail[i]
                temp.payDate = this.$utils.timeFormat(this.currentRowData.payDate,'{y}-{m}-{d}')
                totalMoneyNew = totalMoneyNew + Number(temp.shareMoney)
                delete temp.isValid
                delete temp.operator
                delete temp.id
                delete temp.addTime
                delete temp.updateTime
              }
              if(totalMoney !== totalMoneyNew){
                this.$message({
                  type: 'warning',
                  message: '分摊总金额和配置总金额不相等，请检查数据'
                })
                return false
              }
              this.saveDetailLoadingBtn = true
              RequestURL.saveStoreShareDetailData({shareId: this.currentRowData.id
                    ,jsonData: JSON.stringify(this.editDetailForm.shareDetail)}).then(res=>{
                this._showMessage(res.success,res.msg)
                this.saveDetailLoadingBtn = false
                this.handlerCancel()
                this._loadData(false)
              }).catch(err=>{
                console.log(err)
                this.saveDetailLoadingBtn = false
              })
            })
          }
        })
      },

      // 根据部门ID查询部门下属门店
      querySubordinateStoreDataByDeptId(deptId){
        RequestURL.querySubordinateStoreDataByDeptId({deptId: deptId}).then(res=>{
          this.storeDeptList = res
        })
      },

      // 查询分摊详细信息
      queryStoreShareDetailByShareId(shareId){
        RequestURL.queryStoreShareDetailByShareId({shareId: shareId}).then(res=>{
          this.storeDetailList = res
          this.editDetailForm.shareDetail = res
        })
      },
      resetForm(){
        if(this.$refs['editForm']){
          this.$refs['editForm'].resetFields()
        }
      },
      setForm(data){
        this.resetForm()
        for(let i in this.editForm){
          this.editForm[i] = data[i]
        }
      }
    }
  }
</script>

<style scoped>

</style>
