<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div>
        <el-form size="small" :inline="true" :model="form" ref="form"  class="demo-form-inline">

          <el-form-item  prop="dealType"  >
            <el-select v-model="form.dealType" style="width: 150px" placeholder="类型">
              <el-option
                v-for="item in dealTypeOpt"
                :label="item.name"
                :value="item.value"
                :key="item.value"
              ></el-option>
            </el-select>
          </el-form-item>

          <el-form-item  prop="time">
            <el-date-picker
              type="daterange"
              v-model="form.time"
              value-format="yyyy-MM-dd HH:mm:ss"
              range-separator="至"
              :default-value="ct_month"
              start-placeholder="确认开始日期"
              end-placeholder="确认结束日期"
              align="right">
            </el-date-picker>
          </el-form-item>


          <el-form-item prop="dividerId">
            <base-cascader placeholder="分成人"   :changeOnSelect="false"  clearable v-model="form.dividerId" :url="getReferenceUserSelectUrl"
                           :props="cascaderProps" :dataProps="cascaderDataProps" style="width: 150px"
                           @change="handleDivider"
            >
            </base-cascader>
          </el-form-item>

          <el-form-item  prop="dealId">
            <el-input v-model.trim="form.dealId" placeholder="成交编号" style="width: 150px"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button  type="primary" @click.native.prevent="_loadData(true)" :loading="queryBtnLoading">查询</el-button>
            <el-button  @click.native.prevent="resetForm">清空</el-button>
          </el-form-item>
          <el-form-item >
            <el-button  v-hasOnlyBtn="'exportAllBtn'" type="primary" @click.native.prevent="_loadData(true)" :loading="queryBtnLoading">全部导出</el-button>
            <el-button v-hasOnlyBtn="'exportBtn'"  @click.native.prevent="_resetForm">列表导出</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <div class="page-content-bd" v-loading="loadingView">
      <el-table
        :data="tableData"
        border
        tooltip-effect="light"
        align="center"
        style="width: 100%"
      >
        <el-table-column
          show-overflow-tooltip
          prop="address"
          align="center"
          label="地址">
        </el-table-column>

        <el-table-column
          prop="dealId"
          align="center"
          label="成交编号">
            <template slot-scope="scope">
              <a @click="toDetail(scope.row)"
                 href="javascript:;" class="dealId"   type="text">
                {{scope.row.dealId}}
              </a>
            </template>
        </el-table-column>

        <el-table-column
          prop="dealTime"
          align="center"
          :formatter="dateFormat"
          label="成交日期">
        </el-table-column>

        <el-table-column
          prop="addTime"
          align="center"
          :formatter="dateFormat"
          label="确认时间">
        </el-table-column>

        <el-table-column
          prop="dividerName"
          align="center"
          label="分成人">
        </el-table-column>

        <el-table-column
          prop="dividerDeptName"
          align="center"
          label="分成人门店">
        </el-table-column>

        <el-table-column
          prop="dealType"
          align="center"
          label="类型">
          <template slot-scope="scope">
            {{scope.row.dealType | dealTypeFilter}}
          </template>
        </el-table-column>

        <el-table-column
          prop="isDivide"
          align="center"
          label="分成状态">
          <template slot-scope="scope">
            {{scope.row.isDivide |isDivideFilter}}
          </template>
        </el-table-column>

        <el-table-column
          prop="dividReason"
          align="center"
          label="分成缘由">
        </el-table-column>

        <el-table-column
          prop="estimateProfit"
          align="center"
          label="合同业绩">
          <template slot-scope="scope">
            {{scope.row.estimateProfit/100}}
          </template>
        </el-table-column>

        <el-table-column
          prop="actualProfit"
          align="center"
          label="实收业绩">
          <template slot-scope="scope">
            {{scope.row.actualProfit/100}}
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


  </div>

</template>
<script>
  import {queryDealFinanceDividenInfoData,dropDown} from "../../../request/deal/financeDivideinto";
  import  pageList from '@/mixins/pageList'
  import BaseCascader from '@/components/BaseCascader'
  export default {
    name: 'financeDivideinto',
    components:{pageList,BaseCascader},
    mixins: [pageList],
    data() {
      return {
        form:{
          isValid:'',
          dividerId:'',
          dealId:'',
          time:''
        },
        tableData:[],
        dealTypeOpt:[],
        statusOpt:[
          {id:0,fieldValue:'未处理'},
          {id:1,fieldValue:'已处理'},
          {id:2,fieldValue:'已取消'}
        ],
        getReferenceUserSelectUrl: 'manage/commonselect/queryOpenCityCompanyDepartUser', // 介绍人
        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        loadingView:false,
        queryBtnLoading:false,
        editorDialog:false,
        cancelDialog:false,
        hackReset:false,
        cancelData:null,
        ct_month:null,
        listQuery:{
          limit:10,
          page:1,
          total:0
        },
        rules:{
          remark:[
            { required: true, message: '该项为必填', trigger: 'change' }
          ]
        }
      }
    },
    methods: {
      //查询数据
      _loadData(queryBtn){
        if(queryBtn){
          this.listQuery.page = 1
          this.listQuery.currentPage = 1
        }
        let params = Object.assign({},this.form,{
          limit:this.listQuery.limit,
          page:this.listQuery.page
        })
        if(params.time.length>0){
          params.beginTime = params.time[0]
          params.endTime = params.time[1]
        }
        this.loadingView = true
        this.queryBtnLoading = queryBtn
        queryDealFinanceDividenInfoData(params).then(res =>{
          this.tableData = res.data
          this.listQuery.total = res.count
          this.loadingView = false
          this.queryBtnLoading = false
        }).catch(error =>{
          console.log(error)
          this.loadingView = false
          this.queryBtnLoading = false
        })
      },
      toDetail (row) {
        if(row.dealType =='00'){
          //买卖成交
          this.$router.push({ path: './dealDetail/' + row.dealId})
        }else if(row.dealType =='01'){
          //租赁成交
          this.$router.push({ path: './rentDealDetial/' + row.dealId})
        }else if(row.dealType =='02'){
          //一手成交
          this.$router.push({ path: './oneHandDealDetail/' + row.dealId})
        }
        // this.queryDetailAllPage(row)
      },
      // 时间格式转化
      dateFormat(row,column,cellValue){
        return this.$utils.timeFormat(cellValue, '{y}-{m}-{d}')
      },
      timeDefaultShow(){//默认显示上个月
        this.ct_month= new Date();
        this.ct_month.setTime(this.ct_month.getTime() - 3600 * 1000 * 24 * 30);
      },
      time1(row,column,cellValue){
        let time =  this.$utils.timeFormat(row, '{y}-{m}-{d}')
        this.form.reservationDate = time
      },
      timeFormat(row,column,cellValue){
        return this.$utils.timeFormat(cellValue)
      },
      // _loadData(queryBtn){
      //   this.queryDealFinanceDividenInfoData(queryBtn)
      // },
      resetForm(){
        this.$refs['form'].resetFields()
        this.time == ''
      },
      //下拉数据源
      dropDown(){
        dropDown().then(res =>{
          this.dealTypeOpt = res.dealType
          console.log(this.dealTypeOpt)
        })
      },
      handleDivider(value,name,data){
        if (data.dataType !== 'user') {
          setTimeout(() => {
            this.form.dividerId = ''
          }, 50)
        }
      }
    },
    filters:{
      dealTypeFilter(value){
        if(value == '00') return '买卖成交'
        if(value == '01') return '租赁成交'
        if(value == '02') return '一手房成交'
      },
      isDivideFilter(value){
        if(value === 0)return "未分成";
        if(value === 1)return "已分成"
      }
    },
    mounted(){
      this.dropDown()
      this.timeDefaultShow(0)
    }
  }
</script>

<style scoped>
  .dealId{
    color: #409eff;
  &:hover {
     text-decoration: underline;
   }
  }
</style>
