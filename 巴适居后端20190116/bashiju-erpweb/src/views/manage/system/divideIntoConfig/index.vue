<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form">
        <el-form size="small" :inline="true" :model="form" ref="form" class="demo-form-inline">
          <el-form-item label="配置名称" prop="configName">
            <el-input v-model="form.configName" placeholder="配置名称"></el-input>
          </el-form-item>
          <el-form-item label="所在城市" prop="codeHid">
            <city-selector
              v-model="form.codeHid"
              :url="cityUrl"
              :props="cascaderProps"
              :dataProps="cascaderDataProps"
            >
            </city-selector>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click.native.prevent="queryDivideintoPageList(true)" :loading="queryBtnLoading">查询</el-button>
            <el-button @click.native.prevent="resetForm('form')">重置</el-button>
            <el-button type="primary" @click="handleAdd">新增</el-button>
          </el-form-item>

        </el-form>
      </div>
    </div>

    <div class="page-content-bd" v-loading="loadingView">
      <el-table :data="tableData" border align="left" style="width: 100%">

        <el-table-column
          prop="configName"
          align="left"
          width="350"
          label="名称">
        </el-table-column>

        <el-table-column
          prop="status"
          align="left"
          label="状态">
        </el-table-column>

        <el-table-column
          prop="companyId"
          align="left"
          label="公司名称">
        </el-table-column>

        <el-table-column
          prop="cityId"
          align="left"
          label="城市编码">
        </el-table-column>

        <el-table-column
          prop="cityName"
          align="left"
          label="所在城市">
        </el-table-column>

        <el-table-column
          prop="operator"
          align="left"
          label="创建用户">
        </el-table-column>

        <el-table-column
          prop="addTime"
          align="left"
          :formatter="timeFormat"
          label="创建日期">
        </el-table-column>

        <el-table-column
          prop="dealTypeName"
          align="left"
          label="处理类型">
        </el-table-column>

        <el-table-column
          prop="configTypeName"
          align="left"
          label="配置类型">
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
        >
        <template slot-scope="scope">
          <el-button @click="handleDetailConfig(scope.row)" type="text" size="small">分成配置</el-button>
          <el-button @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
          <el-button @click="handleDelete(scope.row)" type="text" size="small">删除</el-button>
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
      <el-dialog
        title="配置编辑"
        :visible.sync="editDialogVisible"
        :close-on-click-modal="false"
        width="400px"
        top="25vh"
      >
        <main-config v-if="hackReset" @handleClick="handleEditDialog" :currentData="currentRowData" :isAdd="isAdd"></main-config>
      </el-dialog>

      <el-dialog
        title="分成配置"
        :visible.sync="configDialogVisible"
        :close-on-click-modal="false"
        style="padding-top: 5px;"
        width="600px">
        <divideinto-detail-config @handleClick="handleEditDialog" :detailData="detailData" :configId="configId"></divideinto-detail-config>
      </el-dialog>
    </template>
  </div>
</template>
  <script>
    import {
      queryDivideintoPageList,
      saveOrUpdateDivideintoConfig,
      saveOrUpdateDivideintoDetailConfig,
      deleteDivideintoMainConfig,
      queryDivideDetailConfigObj
    } from '@/request/manage/divideinto'

    import {queryCitySelect, queryDivideintoRole} from '@/request/manage/common'
    import MainConfig from './components/MainConfig'
    import DivideintoDetailConfig from './components/DivideintoDetailConfig'
    import CitySelector from '@/components/BaseCascader'

    export default {
      name: 'divideIntoConfig',
      components: {MainConfig ,DivideintoDetailConfig, CitySelector},
      data () {
        return {
          cityUrl: 'manage/commonselect/queryCitySelect',
          cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
          cascaderProps: { // 级联下拉组件配置选项
            value: 'code', // 指定选项的值为选项对象的某个属性值
            label: 'name' // 指定选项标签为选项对象的某个属性值
          },
          cityCode: '',
          isAdd: false,
          hackReset: true,
          loadingView: false,
          queryBtnLoading: false,
          editDialogVisible: false,
          configDialogVisible: false,
          configId: '',
          detailData: null,
          currentRowData: '',
          cityOptions: [],
          tableData: [],
          form: {
            codeHid: '',
            configName: ''
          },
          listQuery: {page: 1, limit: 10,total: 0}
        }
      },
      methods: {
        //页面改变
        handleSizeChange(val) {
          this.listQuery.limit = val
          this.queryDivideintoPageList(false)
        },
        //当前页改变
        handleCurrentChange(val) {
          this.listQuery.page = val
          this.queryDivideintoPageList(false)
        },
        handleEditDialog(actionType) {
          if (actionType && actionType === 1) {
            this.editDialogVisible = false
            this.queryDivideintoPageList(true)
          } else if (actionType && actionType === 2) {
            this.editDialogVisible = false
          } else if(actionType && actionType === 3){
            this.configDialogVisible = false
          }else if(actionType && actionType === 4){
            this.configDialogVisible = false
            this.queryDivideintoPageList(false)
          }else{
            this.$message({
              type: 'info',
              message: '暂不支持此操作'
            })
          }
        },
        // 新增分成配置
        handleAdd() {
          this.hackReset = false
          this.$letTick(() => {
            this.hackReset = true
            // 如果为新增则清空数据传递
            this.isAdd = true
            this.currentRowData = null
            this.editDialogVisible = true
          })
        },
        // 修改分成配置
        handleEdit(row) {
          this.hackReset = false
          this.$letTick(() => {
            this.hackReset = true
            this.isAdd = false
            this.configId = row.id
            this.currentRowData = row
            this.editDialogVisible = true
          })
        },
        handleEditCancel () {
          this.editDialogVisible = false
        },
        // 分成详细配置
        handleDetailConfig (row) {
          this.configId = row.id
          this.queryDivideDetailConfigObj(row.id)
          this.configDialogVisible = true
        },
        // 查询分成配置明细数据
        queryDivideDetailConfigObj(configId) {
          const params = {configId: configId}
          queryDivideDetailConfigObj(params).then(res=>{
            this.detailData = res
          }).catch(err=>{
            console.log(err)
          })
        },
        // 查询页面数据
        queryDivideintoPageList: function(isLoading){
          this.queryBtnLoading = isLoading
          this.loadingView = true
          let params = Object.assign({},this.form, {
            page: this.listQuery.page,
            limit: this.listQuery.limit
          })
          console.log(params)
          queryDivideintoPageList(params).then(res=>{
            this.tableData = res.data
            this.listQuery.total = res.count
            this.queryBtnLoading = false
            this.loadingView = false
          }).catch(err=>{
            console.log(err)
            this.loadingView = false
            this.queryBtnLoading = false
          })
        },
        handleDelete (row) {
          const params = {configId: row.id}
          this.$confirm('确定删除此分成配置?', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            deleteDivideintoMainConfig(params).then(res=>{
              if (res.success) {
                this.$message({
                  type: 'success',
                  message: res.msg
                })
                this.queryDivideintoPageList(false)
              } else {
                this.$message({
                  type: 'warning',
                  message: res.msg
                })
              }
            })
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
        timeFormat(row, column, cellValue) {
          return this.$utils.timeFormat(cellValue)
        },
        resetForm (formName) {
          this.$refs[formName] && this.$refs[formName].resetFields()
        }
      },
      mounted() {
        this.queryDivideintoPageList(false)
      }
    }
  </script>
<style lang="scss" scoped>
  .ratio {
    display: flex;
    align-items: center;

    span {
      margin-left: 10px;
    }
  }
</style>
