<!-- 下定金 -->
<template>
  <div>
    <div>
      <el-row>
        <el-col :span="10">
          <div>客源编号：<span>{{topParams.demandId}}</span></div>
          <div style="margin-top:10px;margin-bottom:10px;">客户姓名：<span>{{topParams.custName}}</span></div>
          <div style="margin-top:10px;margin-bottom:10px;"><span style="color:red;" @click="openDialogVisibleSelectHouse">选择房源</span></div>
          <div>房源编号：<span>{{houseId}}</span></div>
        </el-col>
        <el-col :span="14">
          <el-form ref="formDeposit" :model="formDeposit" :rules="rulesFormDeposit" size="medium" label-width="120px">
            <el-form-item label="下定金额(元)" prop="price">
              <el-input v-model="formDeposit.price"></el-input>
            </el-form-item>
            <el-form-item label="下定日期" prop="entrustTime">
              <el-date-picker
                v-model="formDeposit.entrustTime"
                type="date"
                placeholder="选择日期"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="经办人" prop="trusteesId">
              <base-cascader
                :changeOnSelect="false"
                @change="handleChangePeople"
                v-model="formDeposit.trusteesId"
                :data="peopleSelectOpts" :props="selectProps" :dataProps="selectDataProps">
              </base-cascader>
            </el-form-item>
            <el-form-item label="票据号" prop="billNo">
              <el-input v-model="formDeposit.billNo"></el-input>
            </el-form-item>
            <el-form-item label="预计成交金额" prop="maybeDealPrice">
              <el-input v-model="formDeposit.maybeDealPrice"></el-input>
            </el-form-item>
            <el-form-item label="预计成交日期" prop="maybeDealDate">
              <el-date-picker
                v-model="formDeposit.maybeDealDate"
                type="date"
                placeholder="选择日期"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd">
              </el-date-picker>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
    </div>
    <div>
      <el-row>
        <el-col :span="2"> 备注：</el-col> <el-col :span="22"><el-input type="textarea" v-model="remark"></el-input></el-col>
      </el-row>
    </div>
    <div style="margin-top:20px;text-align:center;">
      <el-button type="success" @click="saveDeposit" :loading="loadingSubmitBtn">确定</el-button>
      <el-button @click="handleCancel">取消</el-button>
    </div>
    <template>
        <!-- 选择房源 -->
        <el-dialog
          title="选择二手房源"
          :visible.sync="dialogVisibleSelectHouse"
          width="800px"
          :close-on-click-modal="false"
          :close-on-press-escape="false"
          append-to-body
        >
        <select-er-house @handleClickRow="handleErClickRow" :transactionTypeId="topParams.transactionTypeId" :regionValue="regionValue" :deptSelectOpts="deptSelectOpts" ref="selectErHouseDialog"> </select-er-house>
        </el-dialog>

        <el-dialog
        title="票据打印"
        :visible.sync="dialogVisiblePrint"
        width="800px"
        :close-on-click-modal="false"
        append-to-body
      >
          <receipt-template @printOver='printEndHandler' :code="printData.code" :payMoney="printData.payMoney" :payMan="printData.payMan"
          :content="printData.content" :payDate="printData.actualPayTime" :recipient="printData.recipient" v-if="hackReset"
          :remark="printData.remark" >
          </receipt-template>
      </el-dialog>
    </template>
  </div>

</template>

<script>
  import {customerFindHouseList,saveDeposit} from '@/request/customer/customerUsed'
  import SelectErHouse from './SelectErHouse' // 选择房源
  import BaseCascader from '@/components/BaseCascader'
  import ReceiptTemplate from  '@/components/ReceiptTemplate'
  export default {
    components: {SelectErHouse,BaseCascader,ReceiptTemplate},
     props: {

      regionValue: {
      type: Object,
      required: true,
        default () {
          return {}
        }
      },
      topParams: {
        type: Object,
        required: true,
        default () {
          return {}
        }
      },
      peopleSelectOpts: {
        type: Array,
        default () {
          return []
        }
      },
      
      deptSelectOpts: {
        type: Array,
        default () {
          return []
        }
      },
     
    },
    data () {
      return {
        selectDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        selectProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          children: 'children', // 指定选项的子选项为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        tableData: [],
        tableHouseData: [],
        queryForm: {},
        formDeposit: {},
        rulesFormDeposit: {
          price: [
            {required: true, message: '请输入值', trigger: 'blur'}
          ],
          trusteesId: [
            {required: true, message: '请输入值', trigger: 'blur'}
          ],
          billNo: [
            {required: true, message: '请输入值', trigger: 'blur'}
          ],
          maybeDealPrice: [
            {required: true, message: '请输入值', trigger: 'blur'}
          ],
        },
        listQuery: {
          page: 1,
          limit: 10,
          total: 0
        },
        dialogVisibleSelectHouse: false,
        dialogVisiblePrint: false,
        loadingView: false,
        loadingHouseView: false,
        loadingQueryBtn: false,
        loadingSubmitBtn: false,
        repeatLook: false,
        priceTitle: '售价',
        visterEvaluate: '',
        imageUrl: '',
        showImg: false,
        fileList: [],
        filePath: '',
        houseId: '',
        shhId: 0,
        remark: '',
        printData: {},
        hackReset: true,
      }
    },

    methods: {

      handleErClickRow (row) {
        console.log(row)
        this.houseId = row.houseId
        this.shhId = row.id
        this.dialogVisibleSelectHouse = false
      },

      openDialogVisibleSelectHouse(){
        this.dialogVisibleSelectHouse = true;
        this.$nextTick(() => {
          this.$refs['selectErHouseDialog'] && this.$refs['selectErHouseDialog']._getTableData()
        })
        
      },

      // 打印完成
      printEndHandler(){
        this.dialogVisiblePrint = false
      },
      // 格式化日期
      _timeFormat (row, column, cellValue) {
        return this.$utils.timeFormat(cellValue)
      },
      handleQuery(){

      },
      _resetForm(){

      },
      selectHouse(row, event, column){
        this.tableData = []
        this.tableData.push(row)
        this.dialogVisibleSelectHouse = false
      },
      handleChangePeople(val, name, data){
        this.formDeposit.trustees = name
        if (data.dataType !== 'user') {
          this.$message({
            showClose: true,
            type: 'warning',
            message: '只能选择用户!'
          })

          setTimeout(() => {
            this.formDeposit.trustees = ''
            this.formDeposit.trusteesId = ''
          }, 50)

        }
      },
      handleChangeDept(){

      },
      saveDeposit(){
         this.$refs['formDeposit'].validate((valid) => {
          if (valid) {
              this.$confirm('确定保存编辑的信息吗?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
               this.loadingSubmitBtn = true

               this.formDeposit.demandId = this.topParams.demandId
               this.formDeposit.houseId = this.houseId
               this.formDeposit.shhId = this.shhId
               this.formDeposit.remark = this.remark
               this.formDeposit.price = this.formDeposit.price * 100
               let params = {...this.formDeposit}
               console.log(params);

               let cfg = {
                headers: {
                  'Content-Type': 'application/json;charset=UTF-8;'
                }
              }
              saveDeposit(params, cfg).then(res => {
                this.loadingSubmitBtn = false

                this.$message({
                  type: 'success',
                  message: res.msg
                })
                this.$emit('submit', null)
                this.$confirm('是否打印收据?', {
                  confirmButtonText: '是',
                  cancelButtonText: '否',
                  type: 'warning'
                }).then(() => {
                  this.dialogVisiblePrint = true;
                  this.printData.code = this.formDeposit.billNo
                  this.printData.payMoney = this.formDeposit.price/100
                  this.printData.recipient = this.formDeposit.trusteesId
                  this.printData.payMan = this.topParams.custName
                  this.printData.content = '下定金'
                  this.printData.actualPayTime = this.formDeposit.entrustTime
                  this.printData.remark = this.remark
                })
              }).catch(() => {
                this.loadingSubmitBtn = false
              })
            })
          }
         })
        
      },
      handleCancel(){
        this.$emit('submit', null)
      }
    },
     mounted () {
       console.log(this.regionSelectOpts)
     }
  }
</script>

<style scoped lang="scss">
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 14px;
    color: #8c939d;
    width: 100px;
    height: 100px;
    line-height: 100px;
    text-align: center;
    border:1px solid #eee;
  }
  .avatar {
    width: 100px;
    height: 100px;
    display: block;
  }
</style>
