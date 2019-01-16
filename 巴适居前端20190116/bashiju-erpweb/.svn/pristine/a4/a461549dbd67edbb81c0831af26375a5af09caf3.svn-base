<template>
  <div class="page-content">
    <div class="page-content-hd">
      <el-form :model="queryForm" ref="queryForm" :inline="true" size="small">
        <el-form-item label="选择城市" prop="cityCode">
          <city-select v-model="queryForm.cityCode"></city-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click.native.prevent="_loadData(true)" :loading="loadingQueryBtn">查询</el-button>
          <el-button @click="resetForm('queryForm')">清空</el-button>
          <el-button type="primary" @click.native.prevent="editCostType({})">新增</el-button>
        </el-form-item>
      </el-form>

    </div>

    <div class="page-content-hd">
      <el-row :gutter="20">
        <el-col :span="12">
          <el-table border align="center" :data="financeCostTypeData" v-loading="loadingView" @row-click="queryCostItemPageList" size="mini">
            <el-table-column label="费用类型" align="left" prop="name"></el-table-column>
            <el-table-column label="显示顺序" align="left" prop="sortNo"></el-table-column>
            <el-table-column label="所属城市" align="left" prop="cityName"></el-table-column>
            <el-table-column label="操作区域" align="center">
              <template slot-scope="scope">
                <el-button @click="editCostItem('',true)" size="small" type="text">新增费用类型</el-button>
                <el-button @click="editCostType(scope.row,false)" size="small" type="text">编辑</el-button>
                <el-button @click="deleteCostType(scope.row)" size="small" type="text">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <!--分页控件-->
          <b-pagination
            :listQuery="listQuery"
            @handleSizeChange="handleSizeChange"
            @handleCurrentChange="handleCurrentChange">
          </b-pagination>
        </el-col>

        <el-col :span="12">
          <el-table border align="center" :data="financeCostItemData" v-loading="loadingItemView" size="mini">
            <el-table-column label="费用类型" align="left" prop="financeCostTypeName"></el-table-column>
            <el-table-column label="费用项目" align="left" prop="name"></el-table-column>
            <el-table-column label="显示顺序" align="left" prop="sortNo"></el-table-column>
            <el-table-column label="操作区域" align="center">
              <template slot-scope="scope">
                <el-button @click="editCostItem(scope.row,false)" size="small" type="text">编辑</el-button>
                <el-button @click="deleteCostItem(scope.row)" size="small" type="text">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <!--分页控件-->
          <b-pagination
            :listQuery="listCommQuery"
            @handleSizeChange="handleCommSizeChange"
            @handleCurrentChange="handleCommCurrentChange">
          </b-pagination>
        </el-col>
      </el-row>

      <b-dialog
        title="费用类型编辑"
        :show.sync="editCostTypeDlg"
        close-on-click-modal="false"
        top="15vh"
        width="20%">
        <el-form :model="editCostTypeForm" ref="editCostTypeForm" label-width="100px" :rules="rules">
          <el-form-item label="选择城市" prop="cityCode">
            <city-select v-model="editCostTypeForm.cityCode" @change="getSelectName"></city-select>
          </el-form-item>

          <el-form-item label="费用类型" prop="name">
            <el-input v-model="editCostTypeForm.name" type="text"></el-input>
          </el-form-item>

          <el-form-item label="显示顺序" prop="sortNo">
            <el-input v-model="editCostTypeForm.sortNo" :min="0" type="number"></el-input>
          </el-form-item>

          <el-form-item>
            <div class="btn-group">
              <el-button @click="saveCostTypeData" type="primary">保存</el-button>
              <el-button @click="handleCancel" type="primary">取消</el-button>
            </div>
          </el-form-item>

        </el-form>
      </b-dialog>

      <b-dialog
        title="费用项目编辑"
        :show.sync="editCostItemDlg"
        close-on-click-modal="false"
        top="35vh"
        width="20%"
      >
        <el-form :model="editCostItemForm" ref="editCostItemForm" label-width="100px" :rules="itemRules">
          <el-form-item label="费用类型" prop="financeCostTypeName">
            <el-input v-model="editCostItemForm.financeCostTypeName" disabled></el-input>
          </el-form-item>

          <el-form-item label="项目名称" prop="name">
            <el-input v-model="editCostItemForm.name"></el-input>
          </el-form-item>

          <el-form-item label="显示顺序" prop="sortNo">
            <el-input v-model="editCostItemForm.sortNo"></el-input>
          </el-form-item>

          <el-form-item>
            <div class="btn-group">
              <el-button @click="saveCostItemData" type="primary">保存</el-button>
              <el-button @click="handleCancel" type="primary">取消</el-button>
            </div>
          </el-form-item>
        </el-form>
      </b-dialog>
    </div>


  </div>
</template>

<script>
  import PageList from '@/mixins/pageList'
  import * as RequestURL from '@/request/manage/storeExpenditureAllocation'
  import CitySelect from '@/components/BaseCascader/city'

  export default {
    name: "storeExpenditureAllocation",
    mixins: [PageList],
    components: { CitySelect },
    data() {
      return {
        editCostTypeDlg: false,
        editCostItemDlg: false,
        financeCostTypeData: [],
        financeCostItemData:[],
        loadingView: false,
        loadingItemView: false,
        loadingQueryBtn: false,
        currCostTypeData: '',
        isAdd: false,
        listCommQuery: {
          page: 1,
          limit: 10,
          total: 0
        },
        queryForm: {
          cityCode: ''
        },
        editCostTypeForm:{
          id: '',
          cityCode: '',
          cityName: '',
          name: '',
          sortNo: ''
        },
        editCostItemForm: {
          id: '',
          financeCostTypeId: '',
          financeCostTypeName: '',
          name: '',
          sortNo: ''
        },
        rules:{
          cityCode:{required: true,message: '该项为必填项',trigger: onchange},
          name:{required: true,message: '该项为必填项',trigger: blur},
          sortNo:{required: true,message: '该项为必填项',trigger: blur}
        },
        itemRules: {
          financeCostTypeName:{required: true,message: '该项为必填项',trigger: blur},
          name:{required: true,message: '该项为必填项',trigger: blur},
          sortNo:{required: true,message: '该项为必填项',trigger: blur}
        }
      }
    },
    methods:{
      getSelectName(value,name){
        this.editCostTypeForm.cityName = name
      },
      /**
       * 分页 pageSize 改变时会触发
       */
      handleCommSizeChange (val) {
        this.listCommunityQuery.limit = val
        this.queryCostItemPageList(this.currCostTypeData)
      },

      /**
       * 分页 currentPage  改变时会触发
       */
      handleCommCurrentChange (val) {
        this.listCommunityQuery.page = val
        this.queryCostItemPageList(this.currCostTypeData)
      },

      handleCancel() {
        this.editCostTypeDlg = false
        this.editCostItemDlg = false
      },

      // 编辑费用项目
      editCostItem(row,isAdd){
        this.isAdd = isAdd
        this.$nextTick(()=>{
          let temp = {}
          if(!this.isAdd){
            temp = row
          }
          temp.financeCostTypeId = this.currCostTypeData.id
          temp.financeCostTypeName = this.currCostTypeData.name
          this.resetForm('editCostItemForm')
          this.setForm(this.editCostItemForm,temp)
          this.editCostItemDlg = true
        })
      },

      // 添加费用类型
      editCostType(row,isAdd){
        this.isAdd = isAdd
        this.$nextTick(()=>{
          this.resetForm('editCostTypeForm')
          this.setForm(this.editCostTypeForm,row)
          this.editCostTypeDlg = true
        })
      },

      /**
       * 查询费用类型数据信息
       * @param btnQuery
       * @private
       */
      _loadData(btnQuery){
        if(btnQuery){
          this.loadingQueryBtn = true
          this.listQuery.page = 1
          this.listQuery.currentPage = 1
        }
        this.loadingView = true

        let params = Object.assign({},this.queryForm,{
          page: this.listQuery.page,
          limit: this.listQuery.limit
        })
        RequestURL.queryCostTypePageList(params).then(res=>{
          this.financeCostTypeData = res.data
          this.listQuery.total = res.count
          if(res.data.count > 0){
            this.currCostTypeData = res.data[0]
            this.queryCostItemPageList(res.data[0])
          }
          this.loadingView = false
          this.loadingQueryBtn = false
        }).catch(err=>{
          this.$message({
            type: 'error',
            message: err.data.msg
          })
          this.loadingView = false
          this.loadingQueryBtn = false
        })
      },

      // 查询费用项目数据信息
      queryCostItemPageList(row){
        this.currCostTypeData = row
        this.loadingItemView = true
        let params = Object.assign({},{financeCostTypeId: this.currCostTypeData.id},{
          page: this.listQuery.page,
          limit: this.listQuery.limit
        })
        RequestURL.queryCostItemPageList(params).then(res=>{
          this.financeCostItemData = res.data
          this.listCommQuery.total = res.count
          this.loadingItemView = false
        }).catch(err=>{
          this.$message({
            type: 'error',
            message: err.data.msg
          })
          this.loadingItemView = false
        })
      },

      // 保存费用类型数据
      saveCostTypeData(){
        this.$refs['editCostTypeForm'].validate((valid)=>{
          if(valid){
            this.$confirm('确定要保存吗？','提示',{
              confirmButtonText: '',
              cancelButtonText: '',
              type: 'warning'
            }).then(()=>{
              let params = {...this.editCostTypeForm}
              RequestURL.saveFinanceCostType({jsonData: JSON.stringify(params)}).then(res=>{
                if(res.success){
                  this.$message({
                    type: 'success',
                    message: res.msg
                  })
                  this.editCostTypeDlg = false
                  this._loadData(false)
                }else{
                  this.$message({
                    type: 'error',
                    message: res.msg
                  })
                }
                this.editCostTypeDlg = false
              }).catch(err=>{
                console.log(err)
                this.$message({
                  type: 'error',
                  message: err.data.msg
                })
              })
            }).catch(()=>{
              this.$message({
                type: 'info',
                message: '用户取消操作'
              })
            })
          }
        })
      },

      // 保存费用项目
      saveCostItemData(){
        this.$refs['editCostItemForm'].validate((valid)=>{
          if(valid){
            this.$confirm('确定要保存吗？','提示',{
              confirmButtonText: '',
              cancelButtonText: '',
              type: 'warning'
            }).then(()=>{
              let params = {...this.editCostItemForm}
              delete params.financeCostTypeName
              RequestURL.saveFinanceCostProj({jsonData: JSON.stringify(params)}).then(res=>{
                if(res.success){
                  this.$message({
                    type: 'success',
                    message: res.msg
                  })
                  this.editCostItemDlg = false
                  this.queryCostItemPageList(this.currCostTypeData)
                }else{
                  this.$message({
                    type: 'error',
                    message: res.msg
                  })
                }
                this.editCostItemDlg = false
              }).catch(err=>{
                console.log(err)
                this.$message({
                  type: 'error',
                  message: err.data.msg
                })
              })

            }).catch(()=>{
              this.$message({
                type: 'info',
                message: '用户取消操作'
              })
            })
          }
        })
      },

      // 删除费用项目
      deleteCostItem(row){
        this.$confirm('确定要删除此项目吗？','提示',{
          confirmButtonText: '',
          cancelButtonText: '',
          type: 'warning'
        }).then(()=>{
          RequestURL.delFinanceCostProj({financeCostProjId: row.id}).then(res=>{
            if(res.success){
              this.$message({
                type: 'success',
                message: res.msg
              })
              this.queryCostItemPageList(this.currCostTypeData)
            }else{
              this.$message({
                type: 'error',
                message: res.msg
              })
            }
          }).catch(err=>{
            console.log(err)
            this.$message({
              type: 'error',
              message: err.data.msg
            })
          })
        }).catch(()=>{
          this.$message({
            type: 'info',
            message: '用户取消操作'
          })
        })
      },

      // 删除费用类型
      deleteCostType(row){
        this.$confirm('确定要删除此费用类型吗？','提示',{
          confirmButtonText: '',
          cancelButtonText: '',
          type: 'warning'
        }).then(()=>{
          RequestURL.deleteFinanceCostType({id: row.id}).then(res=>{
            if(res.success){
              this.$message({
                type: 'success',
                message: res.msg
              })
              this._loadData(false)
            }else{
              this.$message({
                type: 'error',
                message: res.msg
              })
            }
          }).catch(err=>{
            console.log(err)
            this.$message({
              type: 'error',
              message: err.data.msg
            })
          })
        }).catch(()=>{
          this.$message({
            type: 'info',
            message: '用户取消操作'
          })
        })
      },

      // 重置表单
      resetForm(formName){
        if(this.$refs[formName]){
          this.$refs[formName].resetFields()
        }
      },

      // 设置表单数据
      setForm(formName,data){
        for(let i in formName){
          formName[i] = data[i]
        }
      }
    }
  }
</script>

<style scoped>

</style>
