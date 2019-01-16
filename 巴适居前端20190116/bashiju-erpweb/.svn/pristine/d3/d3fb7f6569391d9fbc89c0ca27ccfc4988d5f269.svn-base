<template>
    <div>
        <el-table
          :data="tableData"
          border
          tooltip-effect="light"
          align="center"
          style="width: 100%"
        >

          <el-table-column
            prop="houseId"
            align="left"
            label="房源编号"
          ></el-table-column>

          <el-table-column
            prop="transactionType"
            align="left"
            label="交易类型"
          ></el-table-column>

          <el-table-column
            prop="sellingPrice"
            align="left"
            label="售价(万元)"
          ></el-table-column>

          <el-table-column
            prop="rentPrice"
            align="left"
            label="租价(元)"
          ></el-table-column>


          <el-table-column
            prop="status"
            align="left"
            label="状态"
          ></el-table-column>

          <el-table-column
            prop="maintainer"
            align="left"
            label="维护人"
          ></el-table-column>

          <el-table-column
            prop="recordTime"
            align="left"
            :formatter="timeFormat"
            label="录入时间"
          ></el-table-column>
        </el-table>
        <div class="btn-group">
          <el-button @click="closeDialog">关闭</el-button>
        </div>
    </div>
</template>
<style></style>
<script>
  import PageList from '@/mixins/pageList'
    export default {
      props:{
          data:{
            type:Array
          }
      },
        data() {
            return {
                form: {},
                tableData:[],
            }
        },
        methods: {
          //时间格式化
          timeFormat(row,column,cellValue){
            return this.$utils.timeFormat(cellValue)
          },
          //回调关闭当前窗体
          closeDialog(){
            this.$emit('handleClick')
          }
        },
        mounted() {
          this.tableData =this.data
        }

    }

</script>
