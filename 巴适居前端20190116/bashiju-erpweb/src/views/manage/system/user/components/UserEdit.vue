<template>
  <div class="wrapper" v-loading="loadingView">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px" class="demo-form">

      <el-row>
        <el-col :span="12">
          <el-form-item label="员工姓名" prop="realName">
            <el-input v-model="form.realName" placeholder="请输入员工姓名"></el-input>
          </el-form-item>

          <el-form-item label="手机号码" prop="mobile">
            <el-input v-model="form.mobile"  placeholder="请输入手机号码"></el-input>
          </el-form-item>

          <el-form-item label="所属部门" prop="deptId">
            <base-dept-cascader  @change="handleChangeDept" v-model="form.deptId"></base-dept-cascader>
          </el-form-item>

          <el-form-item label="出生日期" prop="birthDate">
            <el-date-picker
              style="width: 100%"
              v-model="form.birthDate"
              type="date"
              value-format="timestamp"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>

          <el-form-item label="行政区划" prop="areaCode">
            <base-cascader @change="handleChangeCity" :changeOnSelect="false" v-model="form.areaCode" :url="queryOpenCityUrl" :props="cascaderProps" :dataProps="cascaderDataProps"></base-cascader>
          </el-form-item>

          <el-form-item label="员工编号" prop="employee_num">
            <el-input v-model="form.employee_num" placeholder="请填写员工编号"></el-input>
          </el-form-item>

          <el-form-item label="职务" prop="postId">
            <base-select style="width: 100%" :url="getPositionSelectUrl" v-model="form.postId"></base-select>
          </el-form-item>

          <el-form-item label="生日弹出" prop="birthWindowFlag">
            <el-select style="width: 100%" v-model="form.birthWindowFlag" placeholder="请选择生日弹出">
              <el-option
                v-for="item in flagOpts"
                :key="item.label"
                :label="item.label"
                :value="item.value"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="是否经纪人" prop="isAgent">
            <el-select style="width: 100%" v-model="form.isAgent" placeholder="请选择员是否经纪人">
              <el-option
                v-for="item in flagOpts"
                :key="item.label"
                :label="item.label"
                :value="item.value"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="外网同步" prop="syncFlag" v-if="form.isAgent">
            <el-select style="width: 100%" v-model="form.syncFlag" placeholder="是否外网同步">
              <el-option
                v-for="item in flagOpts"
                :key="item.label"
                :label="item.label"
                :value="item.value"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="主营小区" prop="mainCommunityIds" v-if="form.isAgent">
            <base-select
              v-model="form.mainCommunityIds"
              :data="communitySelectOpts"
              :props="selectPropsLabel"
              :multiple="true"
            >
            </base-select>
          </el-form-item>

        </el-col>

        <el-col :span="12">
          <el-form-item label="性别" prop="sex">
            <el-select style="width: 100%" v-model="form.sex" placeholder="请选择性别">
              <el-option
                v-for="item in genderOpts"
                :key="item.label"
                :label="item.label"
                :value="item.value"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="身份证号" prop="idCode">
            <el-input v-model="form.idCode" placeholder="请填写身份证号"></el-input>
          </el-form-item>

          <el-form-item label="电话号码" prop="telPhone">
            <el-input v-model="form.telPhone" placeholder="请填写电话号码"></el-input>
          </el-form-item>

          <el-form-item label="用户角色" prop="roleId">
            <base-select style="width: 100%" :url="getRoleSelectUrl" v-model="form.roleId"></base-select>
          </el-form-item>

          <el-form-item label="入职时间" prop="entryDate">
            <el-date-picker
              style="width: 100%"
              v-model="form.entryDate"
              type="date"
              value-format="timestamp"
              placeholder="选择入职时间">
            </el-date-picker>
          </el-form-item>

          <el-form-item label="电子邮箱" prop="email">
            <el-input v-model="form.email" placeholder="请填写电子邮箱"></el-input>
          </el-form-item>

          <el-form-item label="介绍人" prop="code">
            <base-cascader :changeOnSelect="false" @change="handleChangeReference" v-model="form.code" :url="getReferenceUserSelectUrl" :props="cascaderProps" :dataProps="cascaderDataProps"></base-cascader>
          </el-form-item>

          <el-form-item label="排序" placeholder="请填写排序" prop="sortNo">
            <el-input-number style="width: 100%" v-model="form.sortNo" :min="0"></el-input-number>
          </el-form-item>

          <el-form-item label="热门经纪人" prop="hotAgentFlag" v-if="form.isAgent">
            <el-select style="width: 100%" v-model="form.hotAgentFlag" placeholder="是否热门经纪人">
              <el-option
                v-for="item in flagOpts"
                :key="item.label"
                :label="item.label"
                :value="item.value"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="主营片区" prop="mainRegionsIds" v-if="form.isAgent">
            <base-cascader @change="handleChangeRegion" :changeOnSelect="false" v-model="form.mainRegionsIds" :data="regionSelectOpts" :props="cascaderProps" :dataProps="cascaderDataProps"></base-cascader>
          </el-form-item>

        </el-col>
      </el-row>

      <div class="btn-group">
        <el-button type="primary" :loading="loadingBtn" @click="handleSubmit">确认</el-button>
        <el-button @click="handleCancel">取消</el-button>
      </div>

    </el-form>
  </div>
</template>

<script>
import BaseDeptCascader from '@/components/BaseCascader/dept'
import BaseCascader from '@/components/BaseCascader'
import BaseSelect from '@/components/BaseSelect'
import * as selectUrl from '@/request/manage/common' // 获取下拉框数据 url地址
import {saveOrUpdateUserInfo, queryAreaAndRegion, queryCommunityByRegionId} from '@/request/manage/user'
import {checkMobile, checkCardNo} from '@/common/js/validator'

const flagOpts = [
  {
    label: '是',
    value: 1
  },
  {
    label: '否',
    value: 0
  }
]

const genderOpts = [
  {
    label: '女',
    value: '1'
  },
  {
    label: '男',
    value: '2'
  }
]

export default {
  name: 'UserEdit',
  components: {BaseDeptCascader, BaseCascader, BaseSelect},
  props: {
    data: {
      type: Object
    },
    isAdd: {
      type: Boolean
    }
  },
  data () {
    return {
      form: {
        realName: '',
        mobile: '',
        deptId: '',
        birthDate: '',
        postId: '',
        syncFlag: '',
        isAgent: '',
        hotAgentFlag: '',
        employee_num: '',
        sex: '',
        telPhone: '',
        roleId: '',
        entryDate: '',
        birthWindowFlag: 1,
        email: '',
        code: '',
        sortNo: '',
        mainCommunityIds: '',
        mainRegionsIds: '',
        areaCode: '',
        deptName: '',
        areaName: '',
        refereeName: '',
        mainRegions: '',
        mainCommunity: '',
        agentDetailId: '',
        mainAreaCodes: '',
        mainArea: '',
        idCode: ''
      },
      rules: {
        mainRegionsIds: [
          { required: true, message: '该项为必填', trigger: 'change' }
        ],
        employee_num: [
          { required: true, message: '该项为必填', trigger: 'blur' }
        ],
        mainCommunityIds: [
          { required: true, message: '该项为必填', trigger: 'change' }
        ],
        mainAreaCodes: [
          { required: true, message: '该项为必填', trigger: 'change' }
        ],
        syncFlag: [
          { required: true, message: '该项为必填', trigger: 'change' }
        ],
        hotAgentFlag: [
          { required: true, message: '该项为必填', trigger: 'change' }
        ],
        sortNo: [
          { required: true, message: '该项为必填', trigger: 'blur' }
        ],
        roleId: [
          { required: true, message: '该项为必填', trigger: 'change' }
        ],
        realName: [
          { required: true, message: '该项为必填', trigger: 'blur' }
        ],
        areaCode: [
          { required: true, message: '该项为必填', trigger: 'change' }
        ],
        deptId: [
          { required: true, message: '该项为必填', trigger: 'change' }
        ],
        sex: [
          { required: true, message: '该项为必填', trigger: 'change' }
        ],
        postId: [
          { required: true, message: '该项为必填', trigger: 'change' }
        ],
        isAgent: [
          { required: true, message: '该项为必填', trigger: 'change' }
        ],
        entryDate: [
          { required: true, message: '该项为必填', trigger: 'change' }
        ],
        idCode: [
          { validator: checkCardNo, trigger: 'blur' }
        ],
        mobile: [
          { required: true, message: '该项为必填', trigger: 'blur' },
          { validator: checkMobile, trigger: 'blur' }
        ],
        telPhone: [
          { validator: checkMobile, trigger: 'blur' }
        ],
      },
      getReferenceUserSelectUrl: selectUrl.getReferenceUserSelectUrl, // 介绍人
      getPositionSelectUrl: selectUrl.getPositionSelectUrl, // 职位
      getRoleSelectUrl: selectUrl.getRoleSelectUrl, // 角色
      queryOpenCityUrl: selectUrl.queryOpenCityUrl, // 城市
      regionSelectOpts: [], // 片区级联选择数据
      communitySelectOpts: [], // 小区级联选择数据
      cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
      cascaderProps: { // 级联下拉组件配置选项
        value: 'code', // 指定选项的值为选项对象的某个属性值
        children: 'children', // 指定选项的子选项为选项对象的某个属性值
        label: 'name' // 指定选项标签为选项对象的某个属性值
      },
      selectPropsLabel: {
        label: 'name', // 指定选项的值绑定为下拉框的label属性
        value: 'id' // 指定选项的值绑定为下拉框的Value属性
      },
      flagOpts: flagOpts,
      genderOpts: genderOpts,
      loadingBtn: false,
      loadingView: false,
      originalFormData: {}
    }
  },
  methods: {
    getName(value,name){
    },
    handleCancel () {
      this.$emit('handleClick', 2)
    },
    handleSubmit () {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          let params = {...this.form}
          let mainCommunityIds = params.mainCommunityIds || []
          let mainCommunity = []

          mainCommunityIds.forEach(item => {
            this.communitySelectOpts.find(labelItem => {
              if (labelItem.id === item) {
                mainCommunity.push(labelItem.name)
              }
            })
          })

          params.mainCommunityIds = mainCommunityIds.join()
          params.mainCommunity = mainCommunity.join()

          !this.isAdd ? params.id = this.data.id : params.id = ''

          this.$confirm('确定保存编辑的信息吗?', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.loadingBtn = true

            saveOrUpdateUserInfo({jsonData: JSON.stringify(params)}).then(res => {
              this.loadingBtn = false
              this.$message({
                type: 'success',
                message: res.msg
              })
              this.$emit('handleClick', 1)

              // 向后台传递日志数据
              if (this.isAdd) {
                let message = {
                  sourceCode: params.realName, // 资源标识
                  sourceTypeId: this.$DICT_CODE.LOG.SOURCE_TYPE.MANAGE.USER, // 资源类型
                  operatTypeId: this.$DICT_CODE.LOG.OPERATE_TYPE.ADD, // 操作类型
                  logContent: `新增用户：${params.realName}` // 日志内容
                }
                this.$updateLog.manage.manageAddLog({message: JSON.stringify(message)})
              } else {

                let message = {
                  sourceCode: params.realName, // 资源标识
                  sourceTypeId: this.$DICT_CODE.LOG.SOURCE_TYPE.MANAGE.USER, // 资源类型
                  operatTypeId: this.$DICT_CODE.LOG.OPERATE_TYPE.UPDATE, // 操作类型
                  labelData: this.$utils.getFormFields(this.$refs['form']), // 修改字段对应的label
                  originalData: this.originalFormData, // 原始表单对象
                  newData: params // 修改后的表单对象
                }
                this.$updateLog.manage.manageUpdateLog({message: JSON.stringify(message)})

              }

            }).catch(() => {
              this.loadingBtn = false
            })
          })
        }
      })
    },
    handleChangeDept (val, name, data) {

      if (data.dataType !== 'dept') {
        // this.$message({
        //   showClose: true,
        //   type: 'warning',
        //   message: '只能选择部门!'
        // })

        setTimeout(() => {
          this.form.deptId = ''
          this.form.deptName = ''
        }, 50)


      } else {
        this.form.deptName = name
      }

    },

    handleChangeReference (val, name, data) {
      this.form.refereeName = name

      if (data.dataType !== 'user') {
        this.$message({
          showClose: true,
          type: 'warning',
          message: '只能选择用户!'
        })

        setTimeout(() => {
          this.form.refereeName = ''
          this.form.code = ''
        }, 50)

      }
    },

    handleChangeCity (val, name) {
      if (this.deptId === '') {
        this.$message({
          showClose: true,
          type: 'warning',
          message: '部门不能为空!'
        })

        this.form.mainRegionsIds = ''
        this.regionSelectOpts = []
        return false
      }

      this.form.mainRegionsIds = ''
      this.regionSelectOpts = []

      this.form.areaName = name
    },

    handleChangeRegion (val, name, data) {

      this.form.mainCommunityIds = []
      this.communitySelectOpts = []

      if (data.dataType === 'region') {
        this.form.mainRegionsIds = val
        this.form.mainRegions = name
        this.form.mainAreaCodes = data.parent.code
        this.form.mainArea  = data.parent.name
      } else {
        this.$message({
          showClose: true,
          type: 'warning',
          message: '只能选择片区!'
        })

        setTimeout(() => {
          this.form.mainRegionsIds = ''
          this.form.mainRegions = ''
          this.form.mainAreaCodes = ''
          this.form.mainArea  = ''
        }, 50)

      }

    },
    _resetForm () {
      this.$refs['form'] && this.$refs['form'].resetFields()
    },
    _setForm (data) {
      this.loadingView = true
      this._resetForm()

      Object.keys(this.form).forEach(key => {
        if (data[key] !== undefined) {

          this.form[key] = data[key]

          this.form['mainRegionsIds'] = data['mainRegionsIds'] || ''

          let mainCommunityIds = data['mainCommunityIds'] || ''
          this.form['mainCommunityIds'] = mainCommunityIds.split(',').map(Number)
        }
      })

      this.loadingView = false
    },
    _FormatParams (form) {}
  },
  mounted () {
    this._resetForm()
    if (!this.isAdd) {
      this.originalFormData = Object.assign({}, this.data)
      this._setForm(this.data)
    }
  },

  watch: {
    'form.areaCode' (newValue) {
      if (!newValue) {
        this.form.mainRegionsIds = ''
        this.regionSelectOpts = []
        return
      }

      queryAreaAndRegion({cityCode: newValue || '', deptId: this.form.deptId}).then(res => {
        this.regionSelectOpts = res || []
      })
    },

    'form.mainRegionsIds' (newValue) {
      if (!newValue) {
        this.form.mainCommunityIds = []
        this.communitySelectOpts = []
        return
      }

      queryCommunityByRegionId({regionId: newValue || ''}).then(res => {
        this.communitySelectOpts = res || []
      })
    }
  },
}
</script>

<style lang="scss" scoped>
  .wrapper {
    padding-right: 15px;
    height: 500px;
    overflow-y: scroll;
  }
</style>
