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
            <base-dept-cascader @change="handleChangeDept" v-model="form.deptId"></base-dept-cascader>
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
            <base-city-cascader @change="handleChangeCity" v-model="form.areaCode"></base-city-cascader>
          </el-form-item>

          <el-form-item label="员工编号" prop="employee_num">
            <el-input v-model="form.employee_num" placeholder="请填写员工编号"></el-input>
          </el-form-item>

          <el-form-item label="职务" prop="postId">
            <base-select style="width: 100%" :url="getPositionSelectUrl" v-model="form.postId"></base-select>
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

          <el-form-item label="外网同步" prop="syncFlag" v-show="form.isAgent">
            <el-select style="width: 100%" v-model="form.syncFlag" placeholder="是否外网同步">
              <el-option
                v-for="item in flagOpts"
                :key="item.label"
                :label="item.label"
                :value="item.value"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="主营片区" prop="mainRegionsIds" v-show="form.isAgent">
            <base-select @change="handleChangeRegion" style="width: 100%" :multiple="true" :url="getRegionSelectUrl" v-model="form.mainRegionsIds"></base-select>
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
            <base-cascader @change="handleChangeReference" v-model="form.code" :url="getReferenceUserSelectUrl" :props="cascaderProps" :dataProps="cascaderDataProps"></base-cascader>
          </el-form-item>

          <el-form-item label="排序" placeholder="请填写排序" prop="sortNo">
            <el-input-number style="width: 100%" v-model="form.sortNo" :min="0"></el-input-number>
          </el-form-item>

          <el-form-item label="生日弹出" prop="birthWindowFlag">
            <el-select style="width: 100%" v-model="form.birthWindowFlag" placeholder="请选择员工状态">
              <el-option
                v-for="item in flagOpts"
                :key="item.label"
                :label="item.label"
                :value="item.value"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="热门经纪人" prop="hotAgentFlag" v-show="form.isAgent">
            <el-select style="width: 100%" v-model="form.hotAgentFlag" placeholder="是否热门经纪人">
              <el-option
                v-for="item in flagOpts"
                :key="item.label"
                :label="item.label"
                :value="item.value"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="主营小区" prop="mainCommunityIds" v-show="form.isAgent">
            <base-select @change="handleChangeCommunity" style="width: 100%" :multiple="true" :url="getCommunitySelectUrl" v-model="form.mainCommunityIds"></base-select>
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
import BaseCityCascader from '@/components/BaseCascader/city'
import BaseCascader from '@/components/BaseCascader'
import BaseSelect from '@/components/BaseSelect'
import * as selectUrl from '@/request/manage/common' // 获取下拉框数据 url地址
import {saveOrUpdateUserInfo} from '@/request/manage/user'

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
  components: {BaseDeptCascader, BaseCascader, BaseSelect, BaseCityCascader},
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
        birthWindowFlag: '',
        email: '',
        code: '',
        sortNo: '',
        mainCommunityIds: '',
        mainRegionsIds: '',
        areaCode: '',
        deptName: '',
        areaName: '',
        referenceName: '',
        mainRegions: '',
        mainCommunitys: '',
        agentDetailId: ''
      },
      rules: {
        realName: [
          { required: true, message: '该项为必填', trigger: 'blur' }
        ],
        areaCode: [
          { required: true, message: '该项为必填', trigger: 'change' }
        ],
        mobile: [
          { required: true, message: '该项为必填', trigger: 'blur' }
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
        ]
      },
      getReferenceUserSelectUrl: selectUrl.getReferenceUserSelectUrl, // 介绍人
      getPositionSelectUrl: selectUrl.getPositionSelectUrl, // 职位
      getRoleSelectUrl: selectUrl.getRoleSelectUrl, // 角色
      getCommunitySelectUrl: selectUrl.getCommunitySelectUrl, // 小区
      getRegionSelectUrl: selectUrl.getRegionSelectUrl, // 片区
      cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
      cascaderProps: { // 级联下拉组件配置选项
        value: 'code', // 指定选项的值为选项对象的某个属性值
        children: 'children', // 指定选项的子选项为选项对象的某个属性值
        label: 'name' // 指定选项标签为选项对象的某个属性值
      },
      flagOpts: flagOpts,
      genderOpts: genderOpts,
      loadingBtn: false,
      loadingView: false
    }
  },
  methods: {
    handleCancel () {
      this.$emit('handleClick', 2)
    },
    handleSubmit () {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          let params = {...this.form}
          console.log(params)
          const mainCommunityIds = params.mainCommunityIds
          const mainRegionsIds = params.mainRegionsIds
          if (typeof mainCommunityIds === 'object') {
            params.mainCommunityIds = mainCommunityIds.join()
          }
          if (typeof mainRegionsIds === 'object') {
            params.mainRegionsIds = mainRegionsIds.join()
          }
          // params.areaCode = params.areaCode.length && params.areaCode.join()
          // params.deptId = params.deptId.length && params.deptId.join()
          !this.isAdd ? params.id = this.data.id : params.id = ''
          this.$confirm('确定保存编辑的信息吗?', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.loadingBtn = true

            saveOrUpdateUserInfo({jsonData: JSON.stringify(params)}).then(res => {
              if (res.success) {
                this.loadingBtn = false
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
                this.loadingBtn = false
              }
            }).catch(() => {
              this.loadingBtn = false
            })
          })
        }
      })
    },
    handleChangeDept (val, name) {
      this.form.deptName = name
    },
    handleChangeCity (val, name) {
      this.form.areaName = name
    },
    handleChangeReference (val, name) {
      this.form.referenceName = name
    },
    handleChangeRegion (val, names) {
      this.form.mainRegions = names.join()
    },
    handleChangeCommunity (val, names) {
      this.form.mainCommunitys = names.join()
    },
    _resetForm () {
      this.$refs['form'] && this.$refs['form'].resetFields()
      // for (let i in this.form) {
      //   this.form[i] = ''
      // }
    },
    _setForm (data) {
      this.loadingView = true
      this._resetForm()
      for (let i in this.form) {
        if (data[i] !== 'undefined') {
          this.form[i] = data[i] || ''
          // if (data['deptId']) this.form['deptId'] = data['deptId'].split(',')
          // if (data['areaCode']) this.form['areaCode'] = data['areaCode'].split(',')
          if (data['mainCommunityIds']) this.form['mainCommunityIds'] = data['mainCommunityIds'].split(',').map(Number)
          if (data['mainRegionsIds']) this.form['mainRegionsIds'] = data['mainRegionsIds'].split(',').map(Number)
        }
      }
      this.loadingView = false
    },
    _FormatParams (form) {}
  },
  mounted () {
    this._resetForm()
    if (!this.isAdd) {
      this._setForm(this.data)
    }
  }
}
</script>

<style lang="scss" scoped>
  .wrapper {
    padding-right: 15px;
    height: 500px;
    overflow-y: scroll;
  }
</style>
