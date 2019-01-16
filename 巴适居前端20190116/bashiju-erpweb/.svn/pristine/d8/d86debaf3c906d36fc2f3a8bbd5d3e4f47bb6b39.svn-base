<template>
  <div class="page-content" >
    <div class="page-content-bd">
      <el-table
        :data="tableData"
        border
        tooltip-effect="light"
        align="center"
        style="width: 100%"
        @row-click="rowClick"
      >

        <el-table-column
          prop="communityName"
          align="left"
          label="小区名称">
        </el-table-column>

        <el-table-column
          prop="buildingsName"
          align="left"
          label="座栋">
        </el-table-column>


        <el-table-column
          prop="buildingHouseName"
          align="left"
          label="门牌号">
        </el-table-column>

        <el-table-column
          prop="owner"
          align="left"
          label="业主姓名">
        </el-table-column>


        <el-table-column
          prop="houseType"
          align="left"
          label="房型">
        </el-table-column>

        <el-table-column
          prop="buildSpace"
          align="left"
          label="面积">
        </el-table-column>


        <el-table-column
          prop="sellingPrice"
          align="left"
          label="售价">
        </el-table-column>

        <el-table-column
          prop="rentPrice"
          align="left"
          label="租价">
        </el-table-column>

        <el-table-column
          prop="maintainer"
          align="left"
          label="维护人">
        </el-table-column>

        <el-table-column
          prop="entrustCode"
          align="left"
          label="委托人">
        </el-table-column>

        <el-table-column
          prop="mainterDept"
          align="left"
          label="部门">
        </el-table-column>


        <el-table-column
          align="center"
          label="操作"
          width="100px"
        >
          <template slot-scope="scope">
            <el-button @click="handleDelLookRecord(scope.row)" type="text" size="small" >删除</el-button>
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
<style></style>
<script>
  import {customerFindHouseList} from "@/request/customer/depositManage";
  import PageList from '@/mixins/pageList'
  export default {
    components:{PageList},
    mixins: [PageList],
    data() {
      return {
        form: {

        },
        tableData:[],
        listQuery:{
          limit:10,
          page:1,
          total:0
        },
      }
    },
    methods: {
      //查询数据
      customerFindHouseList(btn){
        let params = Object.assign({},this.form,{
          limit:this.listQuery.limit,
          page:this.listQuery.page
        })
          let conditons = {}
          params.conditons = conditons
        customerFindHouseList(params).then(res =>{
          this.tableData = res.data
          this.listQuery.total = res.count
        })
      },
      _loadData(btn){
        this.customerFindHouseList(btn)
      },
      //行点击事件
      rowClick(row, event, column){
            this.$emit('handleClick',row)
      }
    },
    mounted() {

    }

  }

</script>
