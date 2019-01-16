<template>
  <div class="page-content">
    <div class="page-content-hd">
      <el-form :model="queryForm" ref="queryForm" :inline="true" size="small" class="query-form">
        <el-form-item label="角色名称" prop="roleId">
          <base-select style="width: 100%" :url="getRoleSelectUrl" v-model="queryForm.roleId"></base-select>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click.native.prevent="handlerQuery(true)" :loading="loadQueryBtn">查询</el-button>
          <el-button @click="resetForm('queryForm')">清空</el-button>
          <el-button v-hasOnlyBtn="'addBtn'" type="primary" @click.native.prevent="openingCityAdd()">新增</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="page-content-hd">
      <el-table :data="tableData" border align="center" v-loading="loadingView">
        <el-table-column prop="roleName" label="角色名称" align="left" width="240px;"></el-table-column>
        <el-table-column prop="areaName" label="责任城市" align="left"></el-table-column>
        <el-table-column label="操作区域" width="150px">
          <template slot-scope="scope">
            <el-button v-hasMultipleBtn="['editBtn',scope.row]" @click="openingCityModify(scope.row)" size="small" type="text">编辑</el-button>
            <el-button v-hasMultipleBtn="['delBtn',scope.row]" @click="delResponsibilityCity(scope.row)" size="small" type="text">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--分页控件-->
      <b-pagination
        :listQuery="listQuery"
        @handleSizeChange="handleSizeChange"
        @handleCurrentChange="handleCurrentChange">
      </b-pagination>

      <b-dialog
        title="开通城市"
        :show.sync="editDlgVisible"
        :close-on-click-modal="false"
        top="15vh"
        width="400px"
      >
        <el-form :model="editForm" ref="editForm" :rules="rules" label-width="100px">
          <el-form-item label="角色名称" prop="roleId">
            <base-select style="width: 100%" :url="getRoleSelectUrl" v-model="editForm.roleId"></base-select>
          </el-form-item>

          <el-form-item label="开通城市" prop="cityId">
            <base-select
              v-model="editForm.cityId"
              :data="citySelectOpts"
              :props="selectPropsLabel"
              :multiple="'true'"
            >
            </base-select>
            <!--<el-select v-model="editForm.cityId" :multiple="true">-->
              <!--<el-option v-for="item in citySelectOpts"-->
                         <!--:key="item.code" :label="item.name"-->
                         <!--:value="item.code"></el-option>-->
            <!--</el-select>-->
          </el-form-item>

          <el-form-item class="margin-b-none">
            <el-button type="primary" @click="saveResiposibilityCity" :loading="loadingBtn">保存</el-button>
            <el-button @click="handleCancel">取消</el-button>
          </el-form-item>

        </el-form>
      </b-dialog>
    </div>
  </div>
</template>

<script>
  import {queryRoleSelect,queryOpenCity} from '@/request/manage/common'
  import * as selectUrl from '@/request/manage/common' // 获取下拉框数据 url地址
  import * as CityOpenURL from '@/request/manage/distributionManage'
  import PageList from '@/mixins/pageList'
  import BaseSelect from '@/components/BaseSelect'
  import {systemAddLog,systemUpdateLog,systemQueryLog,systemDelLog} from '@/request/log/systemPlatformLog'

  export default {
    name: "openingcity",
    mixins: [PageList],
    components:{BaseSelect},
    data() {
      return {
        roleOpts: [],
        editRoleOpts: [],
        tableData: [],
        isAdd: false,
        hackReset: true,
        loadQueryBtn: false,
        loadingView: false,
        editDlgVisible: false,
        currentRowData: '',
        queryForm:{
          roleId: ''
        },
        editForm:{
          roleId: '',
          cityId: []
        },
        rules:{
          roleId:{required: true,message: '角色不可为空',trigger: onchange},
          cityId:{required: true,message: '开通城市不可为空',trigger: onchange}
        },
        getRoleSelectUrl: selectUrl.getRoleSelectUrl, // 角色
        selectPropsLabel: {
          label: 'name', // 指定选项的值绑定为下拉框的label属性
          value: 'code' // 指定选项的值绑定为下拉框的Value属性
        },
        loadingBtn: false,
        citySelectOpts: [], // 小区级联选择数据
        originalData: {}
      }
    },
    methods:{
      handleCancel() {
        this.editDlgVisible = false
      },
      /**
       * 查询角色下拉
       */
      getRoleDataSelect: function () {
        let params = Object.assign({},{}, {})
        queryRoleSelect(params).then((res) => {
          this.roleOpts = res
          this.editRoleOpts = res
        }).catch(err => {
          console.log(err)
        })
      },

      handlerQuery(btnQuery){
        this.listQuery.page = 1
        this.listQuery.currentPage = 1
        this._loadData(btnQuery)
      },

      /**
       * 查询页面数据
       * @param btnQuery
       * @private
       */
      _loadData(btnQuery){
        let param = {...this.queryForm}
        let params = {roleId:param.roleId,
          page: this.listQuery.page,
          limit: this.listQuery.limit
        }
        if(btnQuery){
          this.loadQueryBtn = true
        }
        this.loadingView = true
        CityOpenURL.getResponsibilityData(params).then(res=>{
          this.tableData = res.data
          this.listQuery.total = res.count
          this.loadQueryBtn = false
          this.loadingView = false
        }).catch(err=>{
          console.log(err)
          this.$message({
            type: 'error',
            message: err.data.msg
          })
          this.loadQueryBtn = false
          this.loadingView = false
        })
      },

      // 添加城市管理分配
      openingCityAdd(){
        this.$nextTick(()=>{
          this.isAdd = true
          this.currentRowData = ''
          this.resetForm('editForm')
          this.setForm(this.editForm)
          this.editDlgVisible = true
        })
      },

      // 编辑城市管理分配
      openingCityModify(row){
        this.resetForm('editForm')
        this.$nextTick(()=>{
          this.isAdd = false
          this.currentRowData = row
          this.setForm(this.editForm)
          this.editDlgVisible = true
        })
      },
      /**
       * 保存 新增或修改的城市管理分配信息
       */
      saveResiposibilityCity(){
        this.$refs['editForm'].validate((valid)=>{
          if(valid){
            this.$confirm('保存后该角色的信息将发生变化，确定保存数据吗?','提示',{
              confirmButtonText: '',
              cancelButtonText: '',
              type: 'warning'
            }).then(()=>{
              let params = {...this.editForm}
              !this.isAdd?params.id=this.currentRowData.id:params.id = ''
              let roleId = params.roleId
              let dataStr = ''
              params.cityId.forEach(item=>{
                if(dataStr.length > 0){
                  dataStr = dataStr + ','
                }
                dataStr = dataStr + item
              })
              this.loadingBtn = true
              CityOpenURL.saveResiposibilityCity({dataStr: dataStr,roleId: roleId}).then(res=>{
                if(res.success){
                  this.$message({
                    type: 'success',
                    message: res.msg
                  })
                  this.editDlgVisible = false
                  this._loadData(false)
                }else{
                  this.$message({
                    type: 'error',
                    message: res.msg
                  })
                }
                this.loadingBtn = false

                if(this.isAdd){
                  let message = {sourceCode:params.cityId,sourceTypeId:'10',operatTypeId:'1'
                    ,logContent: `新增城市分配:${params.cityId}`}
                  systemAddLog({message: JSON.stringify(message)})
                }else{
                  let message = {sourceCode:this.currentRowData.areaName,sourceTypeId:'10',operatTypeId:'2'
                    ,labelData:this.$utils.getFormFields(this.$refs['editForm'])
                    ,originalData:this.originalData,newData: params}
                  systemUpdateLog({message: JSON.stringify(message)})
                }
              }).catch(err=>{
                console.log(err)
                this.loadingBtn = false
              })
            })
            //   .catch(err=>{
            //   this.$message({
            //     type: 'infor',
            //     message: '已取消'
            //   })
            // })
          }
        })
      },

      // 取消城市管理分配
      delResponsibilityCity(row){
        let params = {roleId: row.roleId}
        this.$confirm('确定删除城市管理分配吗','提示',{
          confirmButtonText: '',
          cancelButtonText: '',
          type: 'warning'
        }).then(()=>{
          CityOpenURL.delResponsibilityCity(params).then(res=>{
            if(res.success){
              this.$message({
                type: 'success',
                message: res.msg
              })
              this._loadData(false)

              let message = {sourceCode:row.areaName,sourceTypeId:'10',operatTypeId:'3'
                ,logContent: `删除城市管理分配 ${row.roleName }->${ row.areaName}`}
              systemDelLog({message: JSON.stringify(message)})
            }else{
              this.$message({
                type: 'error',
                message: res.msg
              })
            }
          })
        })
        //   .catch(()=>{
        //   this.$message({
        //     type: 'info',
        //     message: '已取消'
        //   })
        // })
      },

      // 重置表单数据
      resetForm(formName){
        this.$refs[formName] && this.$refs[formName].resetFields()
      },

      // 设置表单数据
      setForm(formName){
        if(formName){
          if(this.currentRowData){
            this.currentRowData.cityId = this.currentRowData.areaCode.split(",")
          }
          for(let i in formName){
            formName[i] = this.currentRowData[i]
          }
          this.originalData = Object.assign({},{...formName})
        }
      },

      queryOpenCity(){
        queryOpenCity().then(res=>{
          this.citySelectOpts = res
        })
      }
    },

    /**
     * 生命周期函数
     */
    mounted(){
      this.getRoleDataSelect()
      this.queryOpenCity()
    }
  }
</script>

<style scoped>

</style>
