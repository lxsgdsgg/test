<template>
  <div class="page-content">
    <!--<el-dialog :visible.sync="showdialog"  >
    <PrintHouse templateId="19" houseId="49"></PrintHouse>
    </el-dialog>-->
    <!--<ReceiptTemplate code="123456" payMoney="4563" payMan="李白" content="都是公司" payDate="2018-10-12" recipient="大龙" remark="的地方" ></ReceiptTemplate>-->
    <div v-if="isShowTable">
      <div class="page-content-hd">
        <div class="query-form" >
          <el-form size="small" :inline="true"  class="demo-form-inline" >
            <el-form-item>
              <el-button  v-hasOnlyBtn="'addBtn'" type="primary" @click="handleAdd">新增</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <div class="page-content-bd" v-loading="loadingView">
        <el-table :data="tableData" border style="width: 100%">
          <el-table-column align="center" prop="templateName" label="模板名称"  >
          </el-table-column>
          <el-table-column align="center" prop="typeName" label="纸张类型">
          </el-table-column>
          <el-table-column align="center" label="操作">
            <template slot-scope="scope">
              <el-button v-hasMultipleBtn="['editBtn',scope.row]" @click="handleEdit(scope.row)" :loading="loadingView" type="text" size="small">编辑</el-button>
              <el-button v-hasMultipleBtn="['deleteBtn',scope.row]" @click="handleDelete(scope.row)" type="text" size="small">删除</el-button>
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
    <div >
      <Detailed v-if="hackReset" :spanArry="spanArry" :picArry="picArry" :backgroundUrl="backgroundUrl" :templateName="templateName"
               :typeValue="typeValue" :isUseType="isUseType" :templateId="templateId" @blackList="blackList"></Detailed>
    </div>
  </div>

</template>

<script>
  import {
    deletePrintTemplate,
    queryTemplateList,
    queryDetailed
  } from '@/request/manage/HousePrintTemplate'
  import Detailed from './components/Detailed.vue'
  import {manageAddLog,manageUpdateLog,manageQueryLog,manageDelLog} from '@/request/log/mangePlatformLog'
  /*import ReceiptTemplate from  '@/components/ReceiptTemplate'
  import PrintHouse from '@/views/house/houseUsed/detail/PrintHouse.vue'*/
export default {
  name: 'printList',
  data () {
    return {
      showdialog:true,
      templateId: 0,
      loadingView: false,
      isShowTable: true,
      isUseType: false,
      tableData: [],
      listQuery: {
        page: 1,
        limit: 10,
        total: 0
      },
      backgroundUrl: '',
      templateName: '',
      typeValue: 1,
      spanArry: [],
      picArry: [],
      hackReset: false
    }
  },
  components: {
    Detailed
    //,ReceiptTemplate,PrintHouse
  },
  mounted () {
    this.queryListData()
  },
  methods: {
    queryListData: function () {
      this.loadingView = true
      let params = Object.assign({},{
        page: this.listQuery.page,
        limit: this.listQuery.limit
      })
      queryTemplateList(params).then((res) => {
        this.tableData = res.data.data
        this.listQuery.total = res.data.total
        this.loadingView = false
      }).catch(err => {
        console.log(err)
        this.loadingView = false
      })
    },
    // 当前页改变
    handleCurrentChange(val) {
      this.listQuery.page = val
      this.queryListData()
    },
    // 页面改变
    handleSizeChange(val) {
      this.listQuery.limit = val
      this.queryListData(false)
    },
    handleAdd () {
      this.isShowTable = false
      this.hackReset = true
      this.isUseType = false
      this.templateId = 0
      this.templateName = ''
      this.typeValue = 1
      this.backgroundUrl = ''
      this.spanArry = []
      this.picArry = []
    },
    handleEdit (row) {
        console.log(row)
      this.hackReset = false
      this.$nextTick(() => {
        this.loadingView = true

        let param = {'id': row.id}
        queryDetailed(param).then(res => {
          if (res.success) {
            this.hackReset = true
            this.templateId = row.id
            this.isShowTable = false
            this.isUseType = true
            this.backgroundUrl = res.data.backgroundUrl
            this.templateName = res.data.templateName
            this.typeValue = res.data.typeValue
            if (res.data.spanArry.length > 0) {
              for (let i=0;i<res.data.spanArry.length;i++) {
                res.data.spanArry[i].style = this.getStyle(res.data.spanArry[i])
              }
            }
            if (res.data.picArry.length > 0) {
              for (let i=0;i<res.data.picArry.length;i++) {
                res.data.picArry[i].style = this.getStyle(res.data.picArry[i])
              }
            }
            this.spanArry = res.data.spanArry
            this.picArry = res.data.picArry
            this.loadingView = false
          } else {
            this.$message({
              type: 'warning',
              message: res.msg
            })
          }
        }).catch(() => {})
      })

    },
    blackList () {
      this.isShowTable = true
      this.hackReset = false
      this.queryListData()
    },
    handleDelete (row) {
      this.$confirm('确定要删除打印模板：' + row.templateName + '？', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const param = {id: row.id}
        deletePrintTemplate(param).then(res => {
          if (res.success) {
            this.$message({
              type: 'success',
              message: res.msg
            })
            this.queryListData()
            let message = {sourceCode:row.templateName,sourceTypeId:'12',operatTypeId:'3'
              ,logContent: '删除打印模板：'+row.templateName}
            manageDelLog({message: JSON.stringify(message)})
          } else {
            this.$message({
              type: 'warning',
              message: res.msg
            })
          }
        }).catch(() => {})
        this.$message({
          type: 'success',
          message: '删除成功!'
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    getStyle (item) {
      let css = 'left:' + item.x + 'px;top:' + item.y + 'px;'
      if (item.strong) css += 'font-weight:bold;'
      if (item.fontstyle) css += 'font-style:italic;'
      if (item.underline) css += 'text-decoration:underline;'
      if (item.linethrough) css += 'text-decoration: line-through;'
      if (item.color) css += 'color: ' + item.color + ';'
      if (item.fontfamily) css += 'font-family: ' + item.fontfamily + ';'
      if (item.fontsize) css += 'font-size: ' + item.fontsize + 'px;'
      if (item.width) css += 'width: ' + item.width + 'px;'
      if (item.height) css += 'height: ' + item.height + 'px;'
      return css
    }
  }

}
</script>
<style lang="scss" scoped>

</style>
