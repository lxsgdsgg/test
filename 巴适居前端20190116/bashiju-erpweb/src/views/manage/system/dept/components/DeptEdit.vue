<template>
  <div>
    <el-form :model="deptEditForm" :rules="rules" ref="deptEditForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="部门名称" prop="name">
        <el-input type="text" v-model="deptEditForm.name"></el-input>
      </el-form-item>
      <el-form-item label="选择公司" prop="companyId" v-if="roleGroup !== '03'">
        <company-select v-model="deptEditForm.companyId" :url="companyUrl" :changeOnSelect="false"
          @change="jurgeSelect" :props="cascaderProps" :dataProps="cascaderDataProps"></company-select>
      </el-form-item>
      <el-form-item label="部门类型" prop="deptTypeId">
        <el-select style="width: 100%" v-model="deptEditForm.deptTypeId" placehelder="--请选择--" :disabled="!isAdd" @change="isShowParentWithCity">
          <el-option
            v-for="item in deptTypeOps"
            :key="item.name"
            :label="item.name"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="上级部门" v-if="showParent" prop="parentId">
        <base-dept-cascader v-model="deptEditForm.parentId" :disabled="!isAdd"></base-dept-cascader>
      </el-form-item>

      <el-form-item label="领导人">
        <refer-user v-model="deptEditForm.leader" :url="referUserUrl" :changeOnSelect="false"
                    :props="cascaderProps" :dataProps="cascaderDataProps" @change="jurgeSelectUser"></refer-user>
      </el-form-item>

      <el-form-item label="所属城市" prop="areaCode" v-if="deptEditForm.deptTypeId === '1'">
        <city-selector  v-model="deptEditForm.areaCode" :url="cityUrl"></city-selector>
      </el-form-item>
      <el-form-item label="所属城市" prop="areaCode" v-else-if="deptEditForm.deptTypeId === '6' && deptEditForm.parentId === '' ">
        <city-selector  v-model="deptEditForm.areaCode" :url="cityUrl" @change="isShowParent"></city-selector>
      </el-form-item>

      <el-form-item label="排序" prop="sortNo">
        <el-input-number style="width: 100%" v-model="deptEditForm.sortNo" :min="1"></el-input-number>
      </el-form-item>

      <el-form-item class="margin-b-none">
        <el-button type="primary" @click="handleSubmit" :loading="confirmBtn">确认</el-button>
        <el-button @click="handleCancel">取消</el-button>
      </el-form-item>

    </el-form>
  </div>
</template>
<script>
  import BaseDeptCascader from '@/components/BaseCascader/dept'
  import ReferUser from '@/components/BaseCascader'
  import {queryReferenceUserSelect} from '@/request/manage/common'
  import CitySelector from '@/components/BaseCascader/city'
  import CompanySelect from '@/components/BaseCascader'
  import {manageAddLog,manageUpdateLog,manageQueryLog,manageDelLog} from '@/request/log/mangePlatformLog'
  import { saveOrUpdateDept,queryDeptTypeFromEnum, queryDeptType, queryCompanySelectData, queryDeptIdAndDeptName } from '@/request/manage/deptmanage'


  export default {
    name: 'DeptEdit',
    components: { BaseDeptCascader,ReferUser, CitySelector ,CompanySelect},
    props: {
      data: {
        type: Object,
        default(){
          return null
        }
      },
      isAdd: {
        type: Boolean
      }
    },
    data () {
      return {
        showParent: true,
        referUserUrl: 'manage/commonselect/queryOpenCityCompanyDepartUser',
        companyUrl:'manage/commonselect/queryOpenCityCompany',
        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        cityUrl: 'manage/commonselect/queryCitySelect',
        deptOps: [],
        deptTypeOps: [],
        companyOpts: [],
        leader: [],
        confirmBtn: false,
        // parentId: [],
        deptEditForm: {
          id: '',
          name: '',
          areaCode: '',
          companyId: '',
          deptTypeId: '',
          parentId: '',
          leader: '',
          sortNo: ''
        },
        rules: {
          name: [
            {required: true, message: '部门不可为空', trigger: 'blur'}
          ],
          companyId: [
            {required: true, message: '公司不可为空', trigger: 'change'}
          ],
          deptTypeId: [
            { required: true, message: '部门类型不可为空', trigger: 'change'}
          ],
          leader: [
            { required: true, message: '领导名称不可为空', trigger: 'change'}
          ],
          sortNo: [
            {required: true, message: '排序编号不可为空', trigger: 'blur'}
          ],
          parentId: [
            {required: true, message: '上级部门不可为空', trigger: 'blur'}
          ],
          areaCode: [
            {required: true, message: '所属城市不可为空', trigger: 'blur'}
          ]
        },
        originalData: {}
      }
    },
    methods: {
      jurgeSelect(value,name,data){
        if (data.dataType !== 'company') {
          this.$message({
            showClose: true,
            type: 'warning',
            message: '只能选择公司!'
          })
          setTimeout(() => {
            this.deptEditForm.companyId = ''
          }, 50)
        }
      },
      jurgeSelectUser(value,name,data){
        if (data.dataType !== 'user') {
          this.$message({
            showClose: true,
            type: 'warning',
            message: '只能选择用户!'
          })
          setTimeout(() => {
            this.deptEditForm.leader = ''
          }, 50)
        }
      },
      // 是否显示上级部门控件
      isShowParent(value,name){
        if(value || this.deptEditForm.deptTypeId === '1'){
          this.showParent = false
        }else{
          this.showParent = true
        }
      },
      // 是否显示上级部门控件
      isShowParentWithCity(value,name){
        this.deptEditForm.areaCode = ''
        this.deptEditForm.parentId = ''
        if(value === '1') {
          this.showParent = false
        }else{
          this.showParent = true
        }
      },

      handleCancel () {
        this.$emit('handleClick', 2)
      },
      handleSubmit () {
        this.$refs['deptEditForm'].validate((valid) => {
          if (valid) {
            let params = {...this.deptEditForm}
            if(!this.isAdd){
              params.id = this.data.id
            }else{
              params.id = ''
            }
            if(params.parentId.length == 0){
              params.parentId = ''
            }
            this.$confirm('确定保存编辑的信息吗?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.confirmBtn = true
              saveOrUpdateDept({jsonData: JSON.stringify(params)}).then(res => {
                if (res.success) {
                  this.$message({
                    type: 'success',
                    message: res.msg
                  })
                  this.$emit('handleClick', 1)
                } else {
                  this.$message({
                    type: 'warning',
                    message: res.msg
                  })
                }
                this.confirmBtn = false

                if(params.id){
                  let message = {sourceCode:params.name,sourceTypeId:'2',operatTypeId:'2'
                    ,labelData:this.$utils.getFormFields(this.$refs['deptEditForm'])
                    ,originalData:this.originalData,newData: params}

                  console.log(message)
                  manageUpdateLog({message: JSON.stringify(message)})
                }else{
                  let message = {sourceCode:params.name,sourceTypeId:'2',operatTypeId:'1'
                    ,logContent: `新增部门:${params.name}`}
                  manageAddLog({message: JSON.stringify(message)})
                }

              }).catch((err) => {
                console.log(err)
                this.confirmBtn = false
              })
            })
            //   .catch(() => {
            //   this.$message({
            //     type: 'info',
            //     message: '取消!'
            //   })
            // })
          }
        })
      },
      setForm: function () {
        this.resetForm()
        this.data.deptTypeId = String(this.data.deptTypeId)
        if(this.data.parentId){
          this.showParent = true
        }else{
          this.showParent = false
        }
        for (let i in this.deptEditForm) {
          if (this.data && this.data[i]) {
            this.deptEditForm[i] = this.data[i]
          }
        }
        this.originalData = Object.assign({},{...this.deptEditForm})
      },
      resetForm () {
        this.$refs['deptEditForm'] && this.$refs['deptEditForm'].resetFields()
      },

      //获取部门类型信息
      getDeptTypeSelect: function () {
        queryDeptTypeFromEnum().then((res) => {
          if(res){
            this.deptTypeOps = this.$jsonUtils.jsonToArr(res,'id','name')
          }
        }).catch(err => {
          console.log(err)
        })
      },
      getCompanyDataSelect: function () {
        let params = Object.assign({},null, {})
        queryCompanySelectData(params).then((res) => {
          this.companyOpts = res
        }).catch(err => {
          console.log(err)
        })
      },
      //获取部门类型信息
      queryDeptIdAndDeptName: function () {
        let params = Object.assign({}, null, {})
        queryDeptIdAndDeptName(params).then((res) => {
          this.deptOps = res
        }).catch(err => {
          console.log(err)
        })
      },
      queryReferenceUserSelect(){
        let params = Object.assign({},null, {})
        queryReferenceUserSelect(params).then((res) => {
          this.leader = res
        }).catch(err => {
          console.log(err)
        })
      }
    },
    mounted () {
      this.getCompanyDataSelect()
      this.getDeptTypeSelect()
      this.queryDeptIdAndDeptName()
      this.resetForm()
      // 不为新增 表单赋值
      if (!this.isAdd){
        this.setForm()
      }
    },
    computed: {
      roleGroup () {
        return this.$store.getters.userInfo.roleGroup
      }
    }
  }
</script>
<style lang="scss" scoped>
</style>
