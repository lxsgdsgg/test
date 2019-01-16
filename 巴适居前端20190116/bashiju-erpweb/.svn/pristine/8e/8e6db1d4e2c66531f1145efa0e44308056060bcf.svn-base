<!-- 日志 -->
<template>
  <div style="min-height: 550px">
    <div style="margin-left:85%;"> <a style="color:#6CDCFA;" href="javascript:;" @click="openPhoneLog">查看电话日志</a></div>
    <el-table
      :data="tableData"
      v-loading="loadingView"
    >

      <el-table-column
        prop="addTime"
        label="时间" align="center" width=160px>
        <template slot-scope="scope">
          {{$utils.timeFormat(scope.row.addTime,'{y}-{m}-{d} {h}:{i}:{s}')}}
        </template>
      </el-table-column>

      <el-table-column
        prop="operatTypeName"
        label="类型" align="center" width=120px>
      </el-table-column>
      
      <el-table-column
        prop="operator"
        label="操作人" align="center" width=120px>
      </el-table-column>

      <el-table-column
        prop="departmentName"
        label="所在部门" align="center" width=120px>
      </el-table-column>

      <el-table-column
        prop="logContent"
        align="center"
        label="日志内容">
      </el-table-column>

    </el-table>

    <b-pagination
      :listQuery="listQuery"
      @handleSizeChange="handleSizeChange"
      @handleCurrentChange="handleCurrentChange">
    </b-pagination>
    <el-dialog
      title="电话日志"
      :visible.sync="dialogVisiblePhoneLog"
      width="600px"
      append-to-body
    >
    <phone-log  ref="phoneLogDialog" :demandId="demandId"> </phone-log>
  </el-dialog>
  </div>
</template>

<script>
  import {queryLog} from '@/request/customer/customerUsed'
  import PhoneLog from './phoneLog'
  export default {
     props: {
      demandId: {
        type: [Number, String]
      }
    },
    components: {PhoneLog},
    data () {
      return {
        tableData: [],
        listQuery: {
          page: 1,
          limit: 10,
          total: 0
        },
        loadingView: false,
        dialogVisiblePhoneLog: false
      }
    },

    methods: {
      /**
       * 分页 pageSize 改变时会触发
       */
      handleSizeChange (val) {
        this.listQuery.limit = val
        this._getTableData()
      },

      /**
       * 分页 currentPage  改变时会触发
       */
      handleCurrentChange (val) {
        this.listQuery.page = val
        this._getTableData()
      },

      _getTableData () {
        this.loadingView = true
        const params = {
          //shhId: this.$route.query.houseId,
          demandId: this.demandId,
          limit: this.listQuery.limit,
          page: this.listQuery.page
        }
        queryLog(params).then(res => {
          this.tableData = res.data
          this.listQuery.total = res.count
          this.loadingView = false
        }).catch(() => {
          this.loadingView = false
        })
      },
      openPhoneLog(){
          this.dialogVisiblePhoneLog=true   
          this.$nextTick(()=>{
              this.$refs['phoneLogDialog'] && this.$refs['phoneLogDialog']._getTableData()
          })       
          
         
      },
      delLookhouse(row){
        this.$confirm('确定删除该条数据？, 是否继续?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          delLookhouse({id: row.id,demandId: this.$route.query.demandId}).then(res => {
            this.$message({
              type: 'success',
              message: res.msg || '删除成功'
            })
            //this._loadValueData(this.currentDynamicConfigureItem.widgetCode,this.currentDynamicConfigureItem.type);
            for(let i=0;i<this.tableData.length;i++){
              if(this.tableData[i].id==row.id){
                this.tableData.splice(i,1);
              }
            }
          })
        })
      },
       toDetail (id, name, code) {
        console.log(id)
        console.log(name)
        console.log(code)
        this.$router.push({ path: '/house/houseUsedDetail/' + id + '/' + code, query: { formName: name}})
        let houseList = []
        let item = {
          id: id
        }
        houseList.push(item)
        window.localStorage.setItem('houseList', JSON.stringify(houseList))
      },
      // 格式化日期
      _timeFormat (row, column, cellValue) {
        return this.$utils.timeFormat(cellValue)
      }
    },
  }
</script>

<style scoped lang="scss">
  .houseId {
    color: #409eff;
    &:hover {
      text-decoration: underline;
    }
  }
</style>
