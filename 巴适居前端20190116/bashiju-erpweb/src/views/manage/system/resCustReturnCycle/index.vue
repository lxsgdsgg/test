<template>
  <div class="page-content">
    <div class="page-content-hd">
        <el-form size="small" :inline="true" :model="form" ref="form">
          <el-form-item>
            <el-button v-hasOnlyBtn="'setBtn'" type="primary" @click="seting">设置</el-button>
          </el-form-item>
        </el-form>
    </div>
      <div class="page-content-bd" v-loading="loadingView">
        <el-table :data="tableData" border align="center" style="width: 100%">

          <el-table-column
            prop="levelType"
            align="center"
            width="350"
            label="客源等级">
            <template slot-scope="scope">
              {{scope.row.levelType | validateLevelType}}
            </template>
          </el-table-column>

          <el-table-column
            prop="transactionType"
            align="center"
            label="类型">
            <template slot-scope="scope">
              {{scope.row.transactionType | validateTransactionType}}
            </template>
          </el-table-column>

          <el-table-column
            prop="privateAllBack"
            align="center"
            label="私盘回访周期">
            <template slot-scope="scope">
              {{'全员:'+scope.row.privateAllBack +'天;'+'维护人:'+scope.row.privateAdminBack+'天' }}
            </template>
          </el-table-column>

          <el-table-column
            prop="cityId"
            align="center"
            label="公盘回访周期">
            <template slot-scope="scope">
                {{'全员:'+scope.row.pubicAllBack+'天;'+'维护人:'+scope.row.publicAdminBack+'天'}}
            </template>
          </el-table-column>

          <el-table-column
            prop="cityName"
            align="center"
            label="私盘带看周期">
            <template slot-scope="scope">
              {{'全员:'+scope.row.privateAllLook+'天;'+'维护人:'+scope.row.privateAdminLook+'天'}}
            </template>
          </el-table-column>

          <el-table-column
            prop="operator"
            align="center"
            label="公盘带看周期">
            <template slot-scope="scope">
              {{'全员:'+scope.row.publicAllLook+'天;'+'维护人:'+scope.row.publicAdminLook+'天'}}
            </template>
          </el-table-column>
        </el-table>


    <div style="margin-top: 50px">
        <label style="color: gray;">提示:</label>
        <div class="bor">
          <br>
          <div style="margin-left: 25px;color: gray">
            <label> 私盘: 超过回访期 跳公盘</label>
            <br>
            <br>
            <label> 公盘: 超过回访期 进淘宝池</label>
          </div>
        </div>
      </div>
    </div>
    <template>
        <el-dialog
          title="设置客源回访周期"
          :visible.sync="setCustReturnCycleDialog"
          :close-on-click-modal="false"
          width="1000px"
        >
          <set-cust-return-cycle @handleClick="optionOrClose" v-if="hackReset" :isAdd="isAdd"></set-cust-return-cycle>
        </el-dialog>
    </template>
  </div>
</template>
<style></style>
<script>
  import {queryResCustReturnCycleData} from "@/request/manage/custReturnCycle";
  import SetCustReturnCycle from './component/setCustReturnCycle'
  export default {
        name:'resCustReturnCycle',
        components:{SetCustReturnCycle},
        data() {
            return {
              form:{

              },
              isAdd:false,
              tableData:[],
              loadingView:false,
              setCustReturnCycleDialog:false,
              hackReset:false
            }
        },
        methods: {
          //查询数据

          queryResCustReturnCycleData(){
            this.loadingView =true
            let params ={}
            queryResCustReturnCycleData(params).then(res =>{
              this.loadingView =false
              this.tableData = res
            }).catch(error =>{
              this.loadingView =false
              console.log(error)
            })
          },
          seting(){
            this.hackReset = false
              this.$nextTick(()=>{
              this.hackReset = true
                if(!this.tableData.length){
                    this.isAdd = true
                }else{
                  this.isAdd = false
                }
              this.setCustReturnCycleDialog = true
            })
          },
          //窗体回调
          optionOrClose(value){
            if(value && value == 1) {
              this.setCustReturnCycleDialog = false
                this.queryResCustReturnCycleData()
              }else if(value && value == 2) {
                  this.setCustReturnCycleDialog = false
               }
            }

        },
        mounted() {
            this.queryResCustReturnCycleData()
        },
        //过滤器
        filters:{
          validateLevelType:function(value){
            if(value && value == 'A') return 'A类客源'
            if(value && value == 'B') return 'B类客源'
            if(value && value == 'C') return 'C类客源'
            if(value && value == 'D') return 'D类客源'
          },
          validateTransactionType:function (value) {
              if(value && value =='4')return '求购'
              if(value && value =='5')return '求租'
          }
        }

    }

</script>
<style>
</style>
