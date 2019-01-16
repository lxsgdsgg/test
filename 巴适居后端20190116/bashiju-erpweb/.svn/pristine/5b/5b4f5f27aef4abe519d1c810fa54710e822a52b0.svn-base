<template>
  <div class="page-content">
    <el-row :gutter="20">
      <el-col :span="12">
        <div class="grid-content">
          <!--查询表单-->
          <div class="page-content-hd">
            <div class="query-form">
              <el-form size="small" :inline="true" :model="areaForm" ref="form" class="demo-form-inline">
                <el-form-item label="选择城市" prop="codeHid">
                  <city-selector
                    v-model="areaForm.code"
                    :url="cityUrl"
                    :props="cascaderProps"
                    :dataProps="cascaderDataProps"
                  >
                  </city-selector>
                </el-form-item>

                <el-form-item>
                  <el-button type="primary" @click.native.prevent="queryAreaPageList(true)" :loading="queryAreaBtnLoading">查询</el-button>
                  <el-button @click.native.prevent="resetForm('form')">重置</el-button>
                </el-form-item>

              </el-form>
            </div>
          </div>
          <div class="page-content-bd" v-loading="loadingAreaView">
            <el-table :data="tableDataArea" border align="left"
                      style="width: 100%"
                      @row-click="queryRegionPageList">

              <el-table-column
                prop="name"
                align="left"
                label="区划名称">
              </el-table-column>

              <el-table-column
                prop="code"
                align="left"
                label="区划编码">
              </el-table-column>

              <el-table-column
                prop="longitude"
                align="left"
                label="坐标经度">
              </el-table-column>

              <el-table-column
                prop="latitude"
                align="left"
                label="坐标纬度">
              </el-table-column>

              <el-table-column
                align="center"
                label="操作"
                style="width: 200px;"
              >
                <template slot-scope="scope">
                  <el-button @click="handleDetailConfig(scope.row)" type="text" size="small">新增片区</el-button>
                  <el-button @click="handleEdit(scope.row)" type="text" size="small">设置坐标</el-button>
                </template>
              </el-table-column>

            </el-table>
            <!--分页控件-->
            <b-pagination
              :listQuery="listAreaQuery"
              @handleSizeChange="handleAreaSizeChange"
              @handleCurrentChange="handleAreaCurrentChange">
            </b-pagination>
          </div>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="grid-contente">
          <!--查询表单-->
          <div class="page-content-hd">
            <div class="query-form">
              <el-form size="small" :inline="true" :model="regionForm" ref="form" class="demo-form-inline">
                <el-form-item label="片区名称" prop="regionName">
                  <el-input :model="regionForm.regionName" placeholder="片区名称"></el-input>
                </el-form-item>

                <el-form-item>
                  <el-button
                    type="primary"
                    @click.native.prevent="queryRegionPageListByName(false)"
                    :loading="queryRegionBtnLoading">查询</el-button>
                  <el-button @click.native.prevent="regionForm('form')">重置</el-button>
                </el-form-item>

              </el-form>
            </div>
          </div>
          <div class="page-content-bd" v-loading="loadingRegionView">
            <el-table :data="tableRegionData" border align="left" style="width: 100%">

              <el-table-column
                prop="regionName"
                align="left"
                label="片区名称">
              </el-table-column>

              <el-table-column
                prop="logitude"
                align="left"
                label="坐标经度">
              </el-table-column>

              <el-table-column
                prop="latitude"
                align="left"
                label="坐标纬度">
              </el-table-column>

              <el-table-column
                align="center"
                label="操作">
                <template slot-scope="scope">
                  <el-button @click="handleDetailConfig(scope.row)" type="text" size="small">设置坐标</el-button>
                  <el-button @click="handleEdit(scope.row)" type="text" size="small">修改</el-button>
                  <el-button @click="handleDelete(scope.row)" type="text" size="small">删除</el-button>
                </template>
              </el-table-column>

            </el-table>
            <!--分页控件-->
            <b-pagination
              :listQuery="listRegionQuery"
              @handleSizeChange="handleRegionSizeChange"
              @handleCurrentChange="handleRegionCurrentChange">
            </b-pagination>
          </div>
        </div>
      </el-col>
    </el-row>
    <!--编辑数据弹框-->
    <el-dialog
      title="片区编辑"
      :visible.sync="editDialogVisible"
      :close-on-click-modal="false"
      top="30vh"
      width="400px">

      <el-form :model="regionEditForm" :rules="rules" ref="postEditForm" label-width="100px">
        <el-form-item label="片区名称" prop="name" placeholder="请填写片区名称">
          <el-input v-model="regionEditForm.regionName"></el-input>
        </el-form-item>

        <el-form-item class="margin-b-none">
          <el-button type="primary" @click="handleSubmit" :loding="loadingRegionBtn">确认</el-button>
          <el-button @click="handleCancel">取消</el-button>
        </el-form-item>

      </el-form>
    </el-dialog>
  </div>
</template>

<script>

  import {
    queryAreaPageList,
    queryRegionPageList,
    getRegionListByName,
    addRegion,
    updateRegion,
    deleteRegion,
    updateRegionPoint,
    updateAreaPoint
  } from '@/request/manage/region'
  import CitySelector from '@/components/BaseCascader'

  export default {
    name: "regionManage",
    components: {CitySelector},
    data(){
      return {
        cityUrl: 'manage/commonselect/queryCitySelect',
        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        editDialogVisible: false,
        loadingAreaView: false,
        loadingRegionView: false,
        queryAreaBtnLoading: false,
        queryRegionBtnLoading: false,
        loadingRegionBtn: false,
        tableDataArea: [],
        tableRegionData: [],
        areaForm:{
          code: ''
        },
        regionForm: {
          regionName: ''
        },
        regionEditForm: {
          regionName: ''
        },
        listAreaQuery:{page:0,limit: 10,total: 0},
        listRegionQuery:{page:0,limit: 10,total: 0},
        // 数据校验
        rules : {
          regionName:[
            {required : true ,message: '片区名称不可为空',trigger: blur}
          ]
        }
      }
    },
    methods: {
      //页面改变
      handleAreaSizeChange(val) {
        this.listAreaQuery.limit = val
        this.queryAreaPageList(false)
      },
      //当前页改变
      handleAreaCurrentChange(val) {
        this.listQuery.page = val
        this.queryAreaPageList(false)
      },
      //页面改变
      handleRegionSizeChange(val) {
        this.listQuery.limit = val
        this.queryRegionPageList(false,'')
      },
      //当前页改变
      handleRegionCurrentChange(val) {
        this.listQuery.page = val
        this.queryRegionPageList(false,'')
      },
      //取消操作
      handleCancel () {
        this.editDialogVisible = false
      },
      //确认提交数据
      handleSubmit () {
        this.addRegion()
      },
      //时间格式化函数
      timeFormat(row, column, cellValue) {
        return this.$utils.timeFormat(cellValue)
      },

      // 修改时设置界面数据
      setForm: function (data,formName) {
        this.resetForm(formName)
        for (let i in this.regionEditForm) {
          this.postEditForm[i] = data[i]
        }
      },

      //重置界面数据
      resetForm (formName) {
        if (this.$refs[formName]) {
          this.$refs[formName].resetFields()
        }
      },
      // 查询页面列表数据
      queryAreaPageList(isLoading){
        this.queryAreaBtnLoading = isLoading
        this.loadingView = true
        let params = Object.assign({}, this.areaForm, {
          page: this.listAreaQuery.page,
          limit: this.listAreaQuery.limit
        })
        console.log(params)
        queryAreaPageList(params).then((res) => {
          this.tableDataArea = res.data
          this.listAreaQuery.total = res.count
          this.queryAreaBtnLoading = false
          this.loadingAreaView = false
          this.queryRegionPageListForFirst(this.tableDataArea[0].code)
        }).catch(err => {
          console.log(err)
          this.loadingAreaView = false
          this.queryAreaBtnLoading = false
        })
      },

      // 初始化页面时，查询第一个行政区划下属的片区信息
      queryRegionPageListForFirst(code){
        this.queryRegionBtnLoading = false
        this.loadingRegionView = true
        let params = {code: code,page : this.listRegionQuery.page,limit: this.listRegionQuery.limit};
        console.log(params)
        queryRegionPageList(params).then((res) => {
          this.tableRegionData = res.data
          this.listRegionQuery.total = res.count
          this.queryRegionBtnLoading = false
          this.loadingRegionView = false
        }).catch(err => {
          console.log(err)
          this.loadingRegionView = false
          this.queryRegionBtnLoading = false
        })
      },

      // 当点击列表时，查询片区页面列表数据
      queryRegionPageList(row){
        this.queryRegionBtnLoading = false
        this.loadingRegionView = true
        let params = {code: row.code,page : this.listRegionQuery.page,limit: this.listRegionQuery.limit};
        console.log(params)
        queryRegionPageList(params).then((res) => {
          this.tableRegionData = res.data
          this.listRegionQuery.total = res.count
          this.queryRegionBtnLoading = false
          this.loadingRegionView = false
        }).catch(err => {
          console.log(err)
          this.loadingRegionView = false
          this.queryRegionBtnLoading = false
        })
      },

      // 输入搜索条件时，查询片区页面列表数据
      queryRegionPageListByName(isLoading){
        this.queryRegionBtnLoading = isLoading
        this.loadingRegionView = true
        let params = Object.assign({}, this.regionForm, {
          page: this.listRegionQuery.page,
          limit: this.listRegionQuery.limit
        })
        getRegionListByName(params).then((res) => {
          this.tableRegionData = res.data
          this.listRegionQuery.total = res.count
          this.queryRegionBtnLoading = false
          this.loadingRegionView = false
        }).catch(err => {
          console.log(err)
          this.loadingRegionView = false
          this.queryRegionBtnLoading = false
        })
      },

      // 保存职位信息
      addRegion: function(){
        this.$refs['regionEditForm'].validate((valid) => {
          if (valid) {
            let params = {...this.regionEditForm}
            this.$confirm('确认保存数据吗？是否继续?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.loadingRegionBtn = true
              addRegion({jsonData:JSON.stringify(params)}).then(res => {
                if (res.success) {
                  this.$message({
                    type: 'success',
                    message: res.msg,
                  })
                  this.editDialogVisible = false
                  this.loadingRegionBtn = false
                  this.queryPostDataPageList(false)
                } else {
                  this.$message({
                    type: 'warning',
                    message: res.msg
                  })
                  this.editDialogVisible = false
                  this.loadingRegionBtn = false
                }
              }).catch(() => {
                this.editDialogVisible = false
                this.loadingRegionBtn = false
              })
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '取消!'
              })
            })
          }
        })
      },
      // 删除职位信息
      handleDelete (row) {
        this.$confirm('确定删除该职位?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          const param = {postId: row.id}
          deleteRegion(param).then(res => {
            if (res.success) {
              this.$message({
                type: 'success',
                message: res.msg
              })
              this.queryPostDataPageList(false)
            } else {
              this.$message({
                type: 'warning',
                message: res.msg
              })
            }
          }).catch(() => {
            this.$message({
              type: 'error',
              message: '删除异常!'
            })
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消操作!'
          })
        })
      }
    },
    mounted() {
      this.queryAreaPageList(false)
    }
  }
</script>
<style scoped>

</style>
