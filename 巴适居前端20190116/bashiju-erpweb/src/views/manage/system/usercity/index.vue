<template>
  <div class="page-content">
    <div class="page-content-hd" v-loading="loadingView">
      <div class="query-form">
        <el-form size="small" :inline="true"  ref="form">

          <el-form-item label="部门(员工)" prop="queryKey" style="margin-left: 100px;">
            <refer-user :url="referUserUrl" :changeOnSelect="true" :props="cascaderProps" :dataProps="cascaderDataProps"
                        @change="handleChangeDept" v-model="queryKey"></refer-user>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="handleQuery" >查询</el-button>
            <el-button @click.native.prevent="_resetForm">清空</el-button>
          </el-form-item>

          <el-form-item class="pull-right">
            <el-button  v-hasOnlyBtn="'addBtn'" type="primary" @click="handleAdd">新增</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div class="page-content-bd">
        <el-table
          :data="tableData"
          border
          align="center"
          height="580"
          style="width: 100%"
        >
          <el-table-column
            prop="name"
            align="center"
            label="员工名称">
          </el-table-column>

          <el-table-column
            prop="deptName"
            align="center"
            label="所在部门">
          </el-table-column>
          <el-table-column
            prop="cityNames"
            align="center"
            label="城市">
          </el-table-column>

          <el-table-column
            align="center"
            label="操作"
          >
            <template slot-scope="scope">
              <el-button v-hasMultipleBtn="['editBtn', scope.row]"  @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
              <el-button v-hasMultipleBtn="['delBtn', scope.row]"  @click="handleDelete(scope.row)" type="text" size="small">删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <b-pagination
          :listQuery="listQuery"
          @handleSizeChange="handleSizeChange"
          @handleCurrentChange="handleCurrentChange">
        </b-pagination>
      </div>
    </div>
    <el-dialog title="用户分配城市" width="600px" :visible.sync="dialogVisible">
      <el-form  label-position="right" label-width="100px" :model="userForm" ref="userForm" :rules="rules">
        <el-form-item label="用户" prop="userId">
          <refer-user :url="referUserUrl" :changeOnSelect="false" :props="cascaderProps" :dataProps="cascaderDataProps"
                      @change="handleChangeUser" v-model="userForm.userId"></refer-user>
        </el-form-item>
        <el-form-item label="城市" prop="cityCodes">
          <el-select v-model="userForm.cityCodes" multiple placeholder="请选择" @change="handleChangeCity" style="width: 100%;">
            <el-option
              v-for="item in cityList"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary"  @click="saveUserCity">确 定</el-button>
        <el-button @click="dialogVisible = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>


<script>
  import PageList from '@/mixins/pageList'
  import ReferUser from '@/components/BaseCascader'
  import {queryUserOpenCityList, addUserOpenCity, updateUserOpenCity,deleteUserOpenCity,queryCompanyOpenCity} from '@/request/manage/usercity'
  export default{
    mixins: [PageList],
    components: {ReferUser},
    data () {
      return {
        referUserUrl: 'manage/commonselect/queryOpenCityCompanyDepartUser',
        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        queryKey: '',
        deptId:'',
        userId:'',
        tableData:[],
        loadingView: false,
        dialogVisible:false,
        cityList:[],
        recordId:0,
        userForm:{userId:0,name:'',companyId:'',deptId:'',cityCodes:[],cityNames:''},
        rules:{
          userId: [
            { required: true, message: '请选择用户', trigger: 'change' }
          ],
          cityCodes: [
            { required: true, message: '请选择城市', trigger: 'change' }
          ]
        }
      }
    },
    methods:{
      handleQuery () {
        this.listQueryParams = {deptId:this.deptId,userId:this.userId}
        this.listQuery.page = 1
        this.listQuery.currentPage = 1
        this._loadData()
      },

      _loadData () {
        this.loadingView = true
        let params = Object.assign({}, this.listQueryParams, {
          limit: this.listQuery.limit,
          page: this.listQuery.page
        })
        queryUserOpenCityList(params).then(res => {
          this.tableData = res.data
          this.listQuery.total = res.count
          this.loadingView = false
        }).catch(()=>{
          this.loadingView = false
        })
      },
      _resetForm(){
        this.queryKey = ''
        this.deptId=''
        this.userId=''
      },
      handleChangeDept (val, name, data) {
        if (data.dataType === 'dept') {
          this.deptId=val
          this.userId=''
        }else if (data.dataType === 'user'){
          this.deptId=''
          this.userId=val
        }else {
          setTimeout(() => {
            this.queryKey = ''
          }, 50)
        }
      },
      handleAdd(){
        this.recordId=0
        this.dialogVisible = true
        this.cityList=[]
        this.userForm = {userId:0,name:'',companyId:'',deptId:'',cityCodes:[],cityNames:''}
        if (this.$refs['userForm']){
          this.$refs['userForm'].resetFields();
        }

      },
      handleChangeUser(val, name, data){
          console.log(data)
        this.cityList=[]
        this.userForm.cityCodes=[]
        if (data.dataType === 'user'){
          this.userForm.name=data.name
          this.userForm.companyId=data.companyId
          this.userForm.deptId=data.parentCode
          this.userForm.userId=val
          queryCompanyOpenCity({companyId:data.companyId}).then((res)=>{
             if (res.success){
               this.cityList=res.data
             }
          })
        }else {
          setTimeout(() => {
            this.userForm.userId = 0
          }, 50)
        }
      },
      handleChangeCity(value){
          console.log(value)
        if (this.cityList.length>0&&value){
          let cityNames = []
          for (let i=0;i<this.cityList.length;i++){
              let index=value.indexOf(this.cityList[i].value)
             if (index>-1){
               cityNames.push(this.cityList[i].label)
             }
          }
          this.userForm.cityNames=cityNames.join(',')
          console.log(this.userForm.cityNames)
        }
      },
      saveUserCity(){
        this.$refs['userForm'].validate((valid) => {
          if (valid) {
            this.loadingView = true
            if (this.recordId==0){
               let paramData= Object.assign({},this.userForm)
               paramData.cityCodes = paramData.cityCodes.join(',')
               addUserOpenCity({jsonData:JSON.stringify(paramData)}).then((res)=>{
                 this.loadingView = false
                 if (res.success){
                   this.$message({
                     type: 'success',
                     message: res.msg || '操作成功'
                   })
                   this.dialogVisible = false
                   this._loadData ()
                   // 向后台传递日志数据
                   let message = {
                     sourceCode: res.data, // 资源标识
                     sourceTypeId: 26, // 资源类型
                     operatTypeId: 1, // 操作类型
                     logContent: `用户：${this.userForm.name} -> 分配城市：${this.userForm.cityNames}` // 日志内容
                   }
                   this.$updateLog.manage.manageQueryLog({message: JSON.stringify(message)})
                 }else {
                   this.$message({
                     type: 'warning',
                     message: res.msg || '操作失败'
                   })
                 }
               })
             }else {
                let param={id:this.recordId,cityCodes:this.userForm.cityCodes.join(','),cityNames:this.userForm.cityNames}
               updateUserOpenCity(param).then((res)=>{
                 this.loadingView = false
                 if (res.success){
                 this.$message({
                   type: 'success',
                   message: res.msg || '操作成功'
                 })
                 this.dialogVisible = false
                 this._loadData ()
                   let message = {
                     sourceCode: this.recordId, // 资源标识
                     sourceTypeId: 26, // 资源类型
                     operatTypeId: 2, // 操作类型
                     logContent: `用户：${this.userForm.name} -> 分配城市：${this.userForm.cityNames}` // 日志内容
                   }
                   this.$updateLog.manage.manageQueryLog({message: JSON.stringify(message)})
               }else {
                 this.$message({
                   type: 'warning',
                   message: res.msg || '操作失败'
                 })
               }
               })
             }
          } else {
            return false;
          }
        });
      },
      handleDelete(row){
        this.$confirm('确定要删除吗？', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
           this.loadingView = true
          deleteUserOpenCity({id: row.id}).then(res => {
            this.loadingView = false
            this._loadData()
            this.$message({
              type: 'success',
              message: res.msg || '操作成功'
            })
            let message = {
              sourceCode: this.recordId, // 资源标识
              sourceTypeId: 26, // 资源类型
              operatTypeId: 3, // 操作类型
              logContent: `删除数据->用户：${row.name} ; 分配城市：${row.cityNames}` // 日志内容
            }
            this.$updateLog.manage.manageDelLog({message: JSON.stringify(message)})
          }).catch(()=>{
            this.loadingView = false
          })
        })
      },
      handleEdit(row){
        this.recordId=row.id
        this.dialogVisible = true
        if (this.$refs['userForm']){
          this.$refs['userForm'].resetFields();
        }
        this.cityList=[]
        this.userForm = {userId:row.userId+'',name:row.name,companyId:row.companyId,deptId:row.deptId,cityCodes:[],cityNames:row.cityNames}
        queryCompanyOpenCity({companyId:row.companyId}).then((res)=>{
          if (res.success){
            this.cityList=res.data
            this.userForm.cityCodes=row.cityCodes.split(',')
          }
        })
      }
    }
  }
</script>

<style lang="scss" scoped>

</style>
