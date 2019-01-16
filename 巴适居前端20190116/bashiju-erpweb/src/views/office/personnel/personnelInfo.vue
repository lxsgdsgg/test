<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form">
        <el-form size="small" ref="queryForm" :inline="true" :model="queryForm" class="demo-form-inline">
          <el-form-item label="" prop="isPost">
            <el-select v-model="queryForm.isPost" placeholder="是否在职" >
              <el-option
                v-for="item in selectIsPost"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="" prop="deptId">
              <dept-base-cascader
                @change="handleChangeDept"
                v-model="queryForm.deptId"
                :data="deptSelectOpts" :props="selectProps" :dataProps="selectDataProps" placeholder="部门">
              </dept-base-cascader>
          </el-form-item>
          <el-form-item label="" prop="postId">
            <el-select v-model="queryForm.postId" placeholder="职务" >
              <el-option
                v-for="item in selectPost"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="" prop="entryDate">
            <el-date-picker
              v-model="queryForm.startEntryDate"
              type="date"
              placeholder="入职日期-始" value-format="yyyy-MM-dd">
            </el-date-picker>
            <el-date-picker
              v-model="queryForm.endEntryDate"
              type="date"
              placeholder="入职日期-止" value-format="yyyy-MM-dd">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="" prop="birthDate">
            <el-date-picker
              v-model="queryForm.startBirthDate"
              type="date"
              placeholder="出生日期-始" value-format="yyyy-MM-dd">
            </el-date-picker>
            <el-date-picker
              v-model="queryForm.endBirthDate"
              type="date"
              placeholder="出生日期-止" value-format="yyyy-MM-dd">
            </el-date-picker>
          </el-form-item>
           <el-form-item label="" prop="postChangeTypeId">
            <el-select v-model="queryForm.postChangeTypeId" placeholder="变动类型" >
              <el-option
                v-for="item in selectPostChangeType"
                :key="item.id"
                :label="item.fieldValue"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="" prop="vagueData" >
            <base-vague-autocomplete
              class="w300"
              :dataProps="vagueQueryInfo"
              v-model="queryForm.vagueData" @select="handleSelectInfo" placeholder="员工名称、员工编号、电话号码">
            </base-vague-autocomplete>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click.native.prevent="handleQuery" :loading="loadingQueryBtn">查询</el-button>
            <el-button @click.native.prevent="_resetForm('queryForm')">重置</el-button>
          </el-form-item>
          
        </el-form>
      </div>
    </div>
    <div class="page-content-bd" v-loading="loadingView">
      <el-table
        :data="tableData"
        border
        align="center"
        style="width: 100%"
        
      >

        <el-table-column
          prop="isPostText"
          align="center"
          label="是否在职">
        </el-table-column>

        <el-table-column
          prop="userCode"
          align="center"
          label="编号">
        </el-table-column>

        <el-table-column
          prop="realName"
          align="center"
          label="姓名">
        </el-table-column>

        <el-table-column
          prop="deptName"
          align="center"
          label="部门">
        </el-table-column>

        <el-table-column
          prop="sex"
          align="center"
          label="性别">
        </el-table-column>

        <el-table-column
          prop="postName"
          align="center"
          label="职务">
        </el-table-column>

        <el-table-column
          prop="birthDate"
          align="center"
          :formatter="_timeFormat"
          label="出生日期">
        </el-table-column>

         <el-table-column
          prop="entryDate"
          align="center"
          :formatter="_timeFormat"
          label="入职时间">
        </el-table-column>

        <el-table-column
          prop="quitTime"
          align="center"
          :formatter="_timeFormat"
          label="离职时间">
        </el-table-column>

        <el-table-column
          prop="postChangeTypeName"
          align="center"
          label="变动类型">
        </el-table-column>

        <el-table-column
          prop="idCode"
          align="center"
          label="身份证号">
        </el-table-column>

        <el-table-column
          prop="cardNumber"
          align="center"
          label="银行卡号">
        </el-table-column>

         <el-table-column
          prop="mobile"
          align="center"
          label="手机号码">
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
        >
          <template slot-scope="scope">
            <el-button  @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
            <el-button  @click="handleChange(scope.row)" type="text" size="small">变动</el-button>
          </template>
        </el-table-column>

      </el-table>

      <b-pagination
        :listQuery="listQuery"
        @handleSizeChange="handleSizeChange"
        @handleCurrentChange="handleCurrentChange">
      </b-pagination>
    </div>



    <template>
      <b-dialog :show.sync="dialogAddVisible" title="用户详细信息" width="800px">
        <div style="height:500px;overflow-y:scroll;">
          <el-row>
            <el-col :span="19">
                <el-form v-loading="loadingForm" :model="addForm" status-icon :rules="addFormRules" ref="addForm" label-width="70px">
                    <el-row>
                        <el-col :span="8">
                          <el-form-item label="姓名" prop="realName">
                            <el-input v-model="addForm.realName" :readonly="true" class="w120"></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :span="8">
                          <el-form-item label="编号" prop="userCode">
                            <el-input v-model="addForm.userCode" :readonly="true" class="w120"></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :span="8">
                          <el-form-item label="性别" prop="sex">
                            <el-input v-model="addForm.sex" :readonly="true" class="w120"></el-input>
                          </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="8">
                          <el-form-item label="昵称" prop="nickname">
                            <el-input v-model="addForm.nickname"></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :span="8">
                          <el-form-item label="年龄" prop="age">
                            <el-input v-model="addForm.age"></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :span="8">
                          <el-form-item label="民族" prop="nation">
                            <el-select v-model="addForm.nation" placeholder="" >
                              <el-option
                                v-for="item in selectNation"
                                :key="item.id"
                                :label="item.fieldValue"
                                :value="item.id">
                              </el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="8">
                          <el-form-item label="籍贯" prop="nativePlace">
                            <el-input v-model="addForm.nativePlace"></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :span="8">
                          <el-form-item label="学历" prop="education">
                            <el-select v-model="addForm.education" placeholder="" >
                              <el-option
                                v-for="item in selectEducation"
                                :key="item.id"
                                :label="item.fieldValue"
                                :value="item.id">
                              </el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :span="8">
                          <el-form-item label="婚姻" prop="marriage">
                            <el-select v-model="addForm.marriage" placeholder="" >
                              <el-option
                                v-for="item in selectMarriage"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                              </el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="8">
                          <el-form-item label="户口性质" prop="natureHousehold">
                            <el-select v-model="addForm.natureHousehold" placeholder="" >
                              <el-option
                                v-for="item in selectNatureHousehold"
                                :key="item.id"
                                :label="item.fieldValue"
                                :value="item.id">
                              </el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :span="8">
                          <el-form-item label="政治面貌" prop="politicalStatus">
                            <el-select v-model="addForm.politicalStatus" placeholder="" >
                              <el-option
                                v-for="item in selectPoliticalStatus"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                              </el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :span="8">
                          <el-form-item label="邮政编码" prop="postalCode">
                            <el-input v-model="addForm.postalCode"></el-input>
                          </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="8">
                          <el-form-item label="出生日期" prop="birthDate">
                            <el-input v-model="addForm.birthDate" :readonly="true"></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :span="8">
                          <el-form-item label="身份证号" prop="idCode">
                            <el-input v-model="addForm.idCode" :readonly="true"></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :span="8">
                          <el-form-item label="入职时间" prop="entryDate">
                            <el-input v-model="addForm.entryDate" :readonly="true"></el-input>
                          </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="8">
                          <el-form-item label="联系方式" prop="mobile">
                            <el-input v-model="addForm.mobile" :readonly="true"></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :span="8">
                          <el-form-item label="所属部门" prop="deptName">
                            <el-input v-model="addForm.deptName" :readonly="true"></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :span="8">
                          <el-form-item label="职务" prop="postName">
                            <el-input v-model="addForm.postName" :readonly="true"></el-input>
                          </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="8">
                          <el-form-item label="分管内容" prop="duty">
                            <el-input v-model="addForm.duty"></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :span="8">
                          <el-form-item label="紧急联系人" prop="emergencyContacter">
                            <el-input v-model="addForm.emergencyContacter"></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :span="8">
                          <el-form-item label="紧急联系人电话" prop="emergencyContactPhone">
                            <el-input v-model="addForm.emergencyContactPhone"></el-input>
                          </el-form-item>
                        </el-col>
                    </el-row>
                    
                    <el-row>
                        <el-col :span="8">
                          <el-form-item label="招聘渠道" prop="recruitWay">
                              <el-select v-model="addForm.recruitWay" placeholder="请选择" >
                                <el-option
                                  v-for="item in selectRecruitWay"
                                  :key="item.id"
                                  :label="item.fieldValue"
                                  :value="item.id">
                                </el-option>
                              </el-select>
                              
                          </el-form-item>
                        </el-col>
                        <el-col :span="8">
                          <el-form-item label="合同状态" prop="contractState">
                              <el-select v-model="addForm.contractState" placeholder="请选择" >
                                <el-option
                                  v-for="item in selectContractState"
                                  :key="item.value"
                                  :label="item.label"
                                  :value="item.value">
                                </el-option>
                              </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :span="8">
                          <el-form-item label="投保状态" prop="socialSecurity">
                            <el-select v-model="addForm.socialSecurity" placeholder="请选择" >
                                <el-option
                                  v-for="item in selectSocialSecurity"
                                  :key="item.value"
                                  :label="item.label"
                                  :value="item.value">
                                </el-option>
                              </el-select>
                          </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="8">
                          <el-form-item label="转正时间" prop="formalTime">
                            <el-date-picker
                              v-model="addForm.formalTime"
                              type="date"
                              placeholder="请选择" value-format="yyyy-MM-dd" style="width:100px;">
                            </el-date-picker>
                          </el-form-item>
                        </el-col>
                        <el-col :span="16">
                          <el-form-item label="银行卡号" prop="cardNumber">
                            <el-input v-model="addForm.cardNumber"></el-input>
                          </el-form-item>
                        </el-col>
                        
                    </el-row>
                    <el-row>
                        <el-col :span="24">
                          <el-form-item label="家庭住址" prop="houseAddress">
                            <el-input v-model="addForm.houseAddress"></el-input>
                          </el-form-item>
                        </el-col>
                       
                    </el-row>
                     <el-row>
                        <el-col :span="24">
                          <el-form-item label="现住址" prop="currentAddress">
                            <el-input v-model="addForm.currentAddress"></el-input>
                          </el-form-item>
                        </el-col>
                       
                    </el-row>
                     <el-row>
                        <el-col :span="24">
                          <el-form-item label="个性签名" prop="autograph">
                            <el-input v-model="addForm.autograph"></el-input>
                          </el-form-item>
                        </el-col>
                       
                    </el-row>
                </el-form>
            </el-col>
            <el-col :span="5">
                <div style="padding-left:10px;">
                  <p style="padding-left:10px;margin-bottom:10px;">上传头像</p>
                  <img v-show="showImg" :src="fileUrl" class="" style="width:120px;height:120px;margin-bottom:10px;">
                  <el-upload
                    class="avatar-uploader"
                    :action="uploadPath"
                    :data = "potoData"
                    :on-success="handleAvatarSuccess"
                    :before-upload="beforeAvatarUpload"
                    :show-file-list="false">
                    <img v-if="imageUrl" :src="imageUrl" class="avatar">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                  </el-upload>
                </div>
            </el-col>
          </el-row>
          <div style="text-align:center;margin-top:50px;">
            <el-button type="primary" :loading="loadingSubmitBtn" @click="handleSubmit">确认</el-button>
            <el-button @click="dialogAddVisible=false">取消</el-button>
          </div>
        </div>
      </b-dialog>
      <b-dialog :show.sync="dialogChange" title="人员变动" width="600px">
        <el-form v-loading="loadingForm" :model="changeForm" status-icon :rules="changeFormRules" ref="changeForm" label-width="100px">
          <el-row>
              <el-col :span="12">
                <el-form-item label="编号" prop="userCode">
                  <el-input v-model="changeForm.userCode" :readonly="true"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="姓名" prop="realName">
                  <el-input v-model="changeForm.realName" :readonly="true"></el-input>
                </el-form-item>
              </el-col>
              
          </el-row>
          
          <el-row>
              <el-col :span="12">
                <el-form-item label="入职时间" prop="entryDate">
                  <el-input v-model="changeForm.entryDate" :readonly="true"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="变动类型" prop="postChangeTypeId">
                  <el-select v-model="changeForm.postChangeTypeId" placeholder="变动类型" >
                    <el-option
                      v-for="item in selectPostChangeType"
                      :key="item.id"
                      :label="item.fieldValue"
                      :value="item.id">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              
          </el-row>
          <el-row>
             <el-col :span="12">
               <el-form-item label="变动职务" prop="postId">
                <el-select v-model="changeForm.postId" placeholder="选职务" >
                  <el-option
                    v-for="item in selectPost"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id">
                  </el-option>
               </el-select>
              </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="变动部门" prop="deptId">
                  <dept-base-cascader
                    @change="handleChangeDept"
                    v-model="changeForm.deptId"
                    :data="deptSelectOpts" :props="selectProps" :dataProps="selectDataProps" placeholder="选部门">
                  </dept-base-cascader>
               </el-form-item>
              </el-col>
              
          </el-row>
          <el-row>
             <el-col :span="12">
               <el-form-item label="变动时间" prop="changeTime">
                <el-date-picker
                  v-model="changeForm.changeTime"
                  type="date"
                  placeholder="请选择" value-format="yyyy-MM-dd" style="width:174px;">
                </el-date-picker>
              </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="审核人" prop="approver">
                  <dept-base-cascader
                    @change="handleChangeUser"
                    :changeOnSelect="false"
                    v-model="changeForm.approver"
                    :data="userSelectOpts" :props="selectProps" :dataProps="selectDataProps" placeholder="请选择">
                  </dept-base-cascader>
               </el-form-item>
              </el-col>
              
          </el-row>
          <el-row>
             <el-col :span="24">
               <el-form-item label="变动原因" prop="reason">
                  <el-input v-model="changeForm.reason"></el-input>
                </el-form-item>
              </el-col>
          </el-row>
          
        </el-form>
         <div style="text-align:center;margin-top:50px;">
            <el-button type="primary" :loading="loadingSubmitChange" @click="handleChangeSubmit">确认</el-button>
            <el-button @click="dialogChange=false">取消</el-button>
          </div>
      </b-dialog>
       
    </template>

  </div>
</template>
<style>
  .avatar-uploader{
    padding-left: 50px;
  }
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 150px;
    height: 150px;
    line-height: 150px;
    text-align: center;
  }
  .avatar {
    width: 150px;
    height: 150px;
    display: block;
  }
</style>
<script>
  import PageList from '@/mixins/pageList' // 列表页面查询 mixin
  import * as RequestURL from '@/request/office/personnel' // 请求后端URL路径
  import PostBaseCascader from '@/components/BaseCascader' // 职务级联组件
  import DeptBaseCascader from '@/components/BaseCascader' // 部门级联组件
  import {queryReferenceUserSelect,queryPositionSelect,queryOpenCityCompanyDepart} from '@/request/manage/common'
  import { USERPOTO_UPLOAD_URL } from '@/request/main' // 上传封面
  import BaseEditor from '@/components/BaseEditor'
  import BaseVagueAutocomplete from '@/components/BaseVagueAutocomplete'
  import {getSelectValue} from '@/request/app'
  import {oaAddLog, oaUpdateLog, oaQueryLog,oaDelLog} from '@/request/log/oaLog'

  export default {
    name: 'personnelInfo',
    mixins: [PageList],
    components: {PostBaseCascader,DeptBaseCascader,BaseEditor,BaseVagueAutocomplete},
    data () {
      return {
        selectIsPost:[{value:1,label:'在职'},{value:0,label:'离职'}],
        selectContractState: [{value:1,label:'已签'},{value:0,label:'未签'}],
        selectSocialSecurity: [{value:1,label:'已投'},{value:0,label:'未投'}],
        selectMarriage: [{value:1,label:'已婚'},{value:0,label:'未婚'},{value:2,label:'丧偶'}],
        selectPoliticalStatus: [{value:0,label:'群众'},{value:1,label:'团员'},{value:2,label:'党员'}],
        selectPost: [],
        deptSelectOpts: [],
        selectPostChangeType: [],
        selectRecruitWay: [],
        selectNatureHousehold: [],
        selectEducation: [],
        selectNation: [],
        userSelectOpts: [],
        selectDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        selectProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          children: 'children', // 指定选项的子选项为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        noShow: false,
        imageUrl: '',
        uploadPath: USERPOTO_UPLOAD_URL,
        showImg: false,
        fileList: [],
        articleClassInfo: {},
        cityInfo: [],
        queryForm: {},
        vagueData: '', // 
        // 员工名称，员工编号,电话号码
        vagueQueryInfo: [
          {label: '员工名称', type: 1},
          {label: '员工编号', type: 2},
          {label: '电话号码', type: 3},

        ],
        topForm: {
          isOpen: false,
          isSetTop: false
        },
        addForm: {
          realName: '',
          userCode: '',
          sex: '',
          nickname: '',
          age: '',
          nation: '',
          nativePlace: '',
          education: '',
          marriage: '',
          natureHousehold: '',
          politicalStatus: '',
          postalCode: '',
          birthDate: '',
          idCode: '',
          entryDate: '',
          mobile: '',
          deptName: '',
          postName: '',
          duty: '',
          emergencyContacter: '',
          emergencyContactPhone: '',
          recruitWay: 0,
          contractState: '',
          socialSecurity: '',
          formalTime: '',
          cardNumber: '',
          houseAddress: '',
          currentAddress: '',
          autograph: ''
        },
        changeForm: {
          realName: '',
          userCode: '',
          entryDate: '',
        },

        selectStatus: [],
        selectCity: [],
        selectType: [],
        options: [],
        nextChildArr: [],
        lastChildArr: [],
        editSqlForm: {
          sqlDescription: ''
        },
        // 表单校验配置
        addFormRules: {
          
        },
        changeFormRules: {
          postChangeTypeId: [
            {required: true, message: '请选择', trigger: 'blur'}
          ],
          postId: [
            {required: true, message: '请选择', trigger: 'blur'}
          ],
          deptId: [
            {required: true, message: '请选择', trigger: 'blur'}
          ],
          changeTime: [
            {required: true, message: '请选择', trigger: 'blur'}
          ],
          approver: [
            {required: true, message: '请选择', trigger: 'blur'}
          ],
          reason: [
            {required: true, message: '请填写', trigger: 'blur'}
          ],
          
        },
        dialogAddVisible: false,
        dialogShowContent: false,
        loadingSubmitBtn: false,
        dialogVisibleShowImg: false,
        dialogChange: false,
        loadingForm: false,
        loadingItemView: false,
        loadingSubmitChange: false,
        currentRowData: null, // 当前操作的行数据
        currentDynamicConfigureItem:null,
        valueData: [],
        levelInfo1: [],
        levelInfo2: [],
        levelInfo3: [],
        editorVal: '',
        showContent: '',
        fileUrl: '',
        nowQueryForm: {},
        browseNum: 0,
        newsImg: '',
        articleAbstracts: '',
        titleText: '添加快讯',
        cPath: '',
        potoData: {},
        originalData: []
      }
    },
    methods: {
      // 获取部门
      handleChangeDept (val, name, data) {
        this.changeForm.deptName = name
      },
      // 获取职务
      handleChangePost (val, name, data) {
      },

      //获取人员
      handleChangeUser(val, name, data) {
        
      },
      openShowImg(path){
        this.dialogVisibleShowImg = true;
        this.cPath = path;
      },
      

      // 新增
      handleAdd (row) {
        this._resetForm('addForm')
        this.currentRowData = null // 置空当前操作的行数据
        this.isAdd = true;
        this.titleText = '添加快讯'
        this.imageUrl= '';
        this.fileList = [];
        this.editorVal = '';
        this.dialogAddVisible = true;
        this.addForm.widgetCode=row.widgetCode;
        this.currentDynamicConfigureItem=row;
        delete this.addForm.id
        this.showImg = false;
      },
      changeCity(){
        
      },
       handleChange(row){
        this._resetForm('changeForm')
        this.currentRowData = row
        this.isAdd = false
        this.dialogChange = true
        this.showImg = true;
        this.$nextTick(() => {
          this._setChangeForm()
        })
      },
      handleShow(row){
        this.dialogShowContent = true;
        //alert(row.content)
        this.showContent = row.content;
        this.browseNum = row.browseCnt,
        this.newsImg =  row.cover
        this.articleAbstracts = row.abstracts
      },
      // 编辑
      handleEdit (row) {
        this._resetForm('addForm')
        this.currentRowData = row
        this.potoData = {"id":row.id}
        this.isAdd = false
        this.dialogAddVisible = true
        this.showImg = true;
        this.$nextTick(() => {
          this._setForm()
        })

      },
      openUploadCover() {

        this.dialogUploadCover = true
        // this.uploadPath = RequestUploadURL;
      },
      handleAvatarSuccess(res, file) {
        console.log(res);
        console.log(file)
        //this.imageUrl = URL.createObjectURL(file.raw);
        this.fileUrl = res.data.filePath;
        let fileItem = {
          name: res.data.fileName,
          url: res.data.filePath
        }
        this.fileList.push(fileItem);
      },
      beforeAvatarUpload(file) {
        
        
        // debugger
        // const isJPG = file.type === 'image/jpeg';
        // const isLt2M = file.size / 1024 / 1024 < 2;
        //
        // if (!isJPG) {
        //   this.$message.error('上传头像图片只能是 JPG 格式!');
        // }
        // if (!isLt2M) {
        //   this.$message.error('上传头像图片大小不能超过 2MB!');
        // }
        // return isJPG && isLt2M;
      },
      // 提交
      handleSubmit () {

        this.$refs['addForm'].validate((valid) => {
          if (valid) {
            
            // alert(this.$refs['editor'].getContent());
            
            let saveForm = {
              id: this.addForm.id,
              nickname: this.addForm.nickname,
              age: this.addForm.age,
              nation: this.addForm.nation,
              nativePlace: this.addForm.nativePlace,
              education: this.addForm.education,
              marriage: this.addForm.marriage,
              natureHousehold: this.addForm.natureHousehold,
              politicalStatus: this.addForm.politicalStatus,
              postalCode: this.addForm.postalCode,
              duty: this.addForm.duty,
              emergencyContacter: this.addForm.emergencyContacter,
              emergencyContactPhone: this.addForm.emergencyContactPhone,
              recruitWay: this.addForm.recruitWay,
              contractState: this.addForm.contractState,
              socialSecurity: this.addForm.socialSecurity,
              formalTime: this.addForm.formalTime,
              cardNumber: this.addForm.cardNumber,
              houseAddress: this.addForm.houseAddress,
              currentAddress: this.addForm.currentAddress,
              autograph: this.addForm.autograph

            }
            const params = {
              jsonData: JSON.stringify(saveForm)
            }

            

            this.$confirm('确定保存编辑的信息吗？, 是否继续?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.loadingSubmitBtn = true
              RequestURL.savePersonnel(params).then(res => {
                this.loadingSubmitBtn = false
                this.dialogAddVisible = false
                if (res.success) {
                  this.$message({
                    type: 'success',
                    message: res.msg || '操作成功'
                  })
                  this._loadData(false);
                  
                  let message = {
                      sourceId: this.addForm.id,
                      sourceCode: '人事编辑',
                      businessTypeId: 6,//业务类型，
                      sourceTypeId: 15,
                      operatTypeId: 610,
                      labelData: this.$utils.getFormFields(this.$refs['addForm']),
                      originalData: this.originalData,
                      newData: JSON.parse(JSON.stringify({...this.addForm}))
                    }

                    oaUpdateLog({message: JSON.stringify(message)}).then(res => {
                      console.log(res)
                    })
                    
                }else{
                  this.loadingSubmitBtn = false
                  this.$message({
                    type: 'warning',
                    message: res.msg
                  })
                }
              }).catch((err) => {
                console.log(err);
                this.loadingSubmitBtn = false
                this.$message({
                  type: 'info',
                  message: err.msg || '保存失败'
                })
              })
            })

          }
        })
      },

      handleChangeSubmit(){
          this.$refs['changeForm'].validate((valid) => {
          if (valid) {
            
            // alert(this.$refs['editor'].getContent());
            console.log(this.selectPost)
            console.log(this.selectPostChangeType)
            let postName = this.selectPost.find(item=>item.id===this.changeForm.postId).name
            let postChangeTypeName = this.selectPostChangeType.find(item=>item.id===this.changeForm.postChangeTypeId).fieldValue
            let saveForm = {
              userId: this.changeForm.id,
              oldPostId: this.currentRowData.postId,
              oldPpostName: this.currentRowData.postName,
              oldDeptId: this.currentRowData.deptId,
              oldDeptName: this.currentRowData.deptName,
              postId: this.changeForm.postId,
              postName: postName,
              deptId: this.changeForm.deptId,
              deptName: this.changeForm.deptName,
              postChangeTypeId: this.changeForm.postChangeTypeId,
              postChangeTypeName: postChangeTypeName,
              approver: this.changeForm.approver,
              changeTime: this.changeForm.changeTime,
              reason: this.changeForm.reason

            }
            const params = {
              jsonData: JSON.stringify(saveForm)
            }

            

            this.$confirm('确定保存编辑的信息吗？, 是否继续?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.loadingSubmitChange = true
              RequestURL.savePersonnelChange(params).then(res => {
                this.loadingSubmitChange = false
                this.dialogChange = false
                if (res.success) {
                  this.$message({
                    type: 'success',
                    message: res.msg || '操作成功'
                  })
                  this._loadData(false);
                  //写日志
                let logContent = '变动申请；'+'原部门：'+this.currentRowData.deptName+';变动部门：'+this.changeForm.deptName+';原职务：'+this.currentRowData.postName+'；变动职务：'+postName+'；变动类型：'+postChangeTypeName
                let message = {
                     sourceId: this.changeForm.id,
                     sourceCode: '人事变动',
                     businessTypeId: 6,//业务类型，
                     sourceTypeId: 15,
                     operatTypeId: 611,
                     logContent: logContent
                  }
                oaQueryLog({message: JSON.stringify(message)}).then(res => {
                    console.log(res)
                  })
                }else{
                  this.loadingSubmitChange = false
                  this.$message({
                    type: 'warning',
                    message: res.msg
                  })
                }
              }).catch((err) => {
                console.log(err);
                this.loadingSubmitChange = false
                this.$message({
                  type: 'info',
                  message: err.msg || '保存失败'
                })
              })
            })

          }
        })
      },

      // 删除
      handleDelete (row) {
        this.$confirm('确定删除该条数据？, 是否继续?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          RequestURL.delArticle({id: row.id}).then(res => {
            this.$message({
              type: 'success',
              message: res.msg || '删除成功'
            })
            //this._loadData(false);
            for(let i=0;i<this.tableData.length;i++){
              if(this.tableData[i].id==row.id){
                this.tableData.splice(i,1)
              }
            }
          })
        })
      },

      handleSelectInfo(item){
        // 点击坐栋输入建议项赋值
        console.log(item)
        // this.queryForm.vagueData = {
        //   type: item.type,
        //   value: item.value,
        // }
        this.queryForm.vagueType = item.type;
      },

      handleQuery () {
        this.nowQueryForm = Object.assign({}, this.queryForm)
        this.listQuery.page = 1
        this.listQuery.currentPage = 1
        this._loadData(true)
      },
      // 加载数据
      _loadData (btnQuery) {
        if (btnQuery) {
          this.loadingQueryBtn = true
        }

        this.loadingView = true
        
         let params = Object.assign({}, this.nowQueryForm, {
          limit: this.listQuery.limit,
          page: this.listQuery.page
        })
        //const params = this._getParams(this.queryForm)
        RequestURL.getPersonnelList(params).then(res => {
          this.tableData = res.data.map(item => {
             if(item.isPost===1){
               item.isPostText = '是'
             }else{
               item.isPostText = '否'
             }
             if(item.sex==='1'){
               item.sex = '女'
             }else{
               item.sex = '男'
             }
             return item
          })
          console.log(this.tableData);
          this.listQuery.total = res.count
          this.loadingQueryBtn = false
          this.loadingView = false

        }).catch(() => {
          this.loadingQueryBtn = false
          this.loadingView = false
        })
      },
      
      // 表单回填
      _setForm () {
        // 拷贝为修改过的原始表单数据
        this.originalData = Object.assign({}, this.addForm)

        if(this.currentRowData.entryDate){
          this.currentRowData.entryDate = this.$utils.timestampToTime(this.currentRowData.entryDate)
        }
        if(this.currentRowData.birthDate){
          this.currentRowData.birthDate = this.$utils.timestampToTime(this.currentRowData.birthDate)
        }
        if(this.currentRowData.formalTime){
          this.currentRowData.formalTime = this.$utils.timestampToTime(this.currentRowData.formalTime)
        }
        
        if(this.currentRowData.nation){
          this.currentRowData.nation = Number(this.currentRowData.nation)
        }
        if(this.currentRowData.education){
          this.currentRowData.education = Number(this.currentRowData.education)
        }
        if(this.currentRowData.marriage){
          this.currentRowData.marriage = Number(this.currentRowData.marriage)
        }
        if(this.currentRowData.natureHousehold){
          this.currentRowData.natureHousehold = Number(this.currentRowData.natureHousehold)
        }
        if(this.currentRowData.politicalStatus){
          this.currentRowData.politicalStatus = Number(this.currentRowData.politicalStatus)
        }
        if(this.currentRowData.recruitWay){
          this.currentRowData.recruitWay = Number(this.currentRowData.recruitWay)
        }
        if(this.currentRowData.contractState){
          this.currentRowData.contractState = Number(this.currentRowData.contractState)
        }
        if(this.currentRowData.socialSecurity){
          this.currentRowData.socialSecurity = Number(this.currentRowData.socialSecurity)
        }
        

        this.$utils.setFormInfo(this.addForm, this.currentRowData, () => {
          
         
          if(this.currentRowData.sex==='0'){
            this.currentRowData.sex = '男'
          }
          if(this.currentRowData.sex==='1'){
            this.currentRowData.sex = '女'
          }
          //alert(this.currentRowData['cover']);
          this.addForm['id'] = this.currentRowData['id']
          
          this.imageUrl = ""
          this.fileUrl = this.currentRowData.profilePhoto
        })

        // 移除表单回填时 element ui自动添加的校验效果
        setTimeout(() => {
          this.$refs['addForm'].clearValidate()
        }, 100)
      },
      _setChangeForm(){
        if(this.currentRowData.entryDate){
          this.currentRowData.entryDate = this.$utils.timestampToTime(this.currentRowData.entryDate)
        }
         
        this.$utils.setFormInfo(this.changeForm, this.currentRowData, () => {
          //alert(this.currentRowData['cover']);
          this.changeForm['id'] = this.currentRowData['id']
          
          this.imageUrl = ""
        })

        // 移除表单回填时 element ui自动添加的校验效果
        setTimeout(() => {
          this.$refs['changeForm'].clearValidate()
        }, 100)
      },

    },
    mounted () {
      
       //职务选择
      queryPositionSelect({}).then(res => {
        this.selectPost = res
      }),
      //部门选择
      queryOpenCityCompanyDepart({}).then(res => {
        this.deptSelectOpts = res
      }),
      //人员选择
      queryReferenceUserSelect({}).then(res => {
        this.userSelectOpts = res
      }),
      //变动类型
      getSelectValue({param: 'postChangeType'}).then(res => {
         // console.log(res.data)
          this.selectPostChangeType = res.data
        })
        //招聘渠道
      getSelectValue({param: 'recruitWay'}).then(res => {
         // console.log(res.data)
          this.selectRecruitWay = res.data
        })
      //户口性质
      getSelectValue({param: 'natureHousehold'}).then(res => {
         // console.log(res.data)
          this.selectNatureHousehold = res.data
        })
        //学历
      getSelectValue({param: 'education'}).then(res => {
         // console.log(res.data)
          this.selectEducation = res.data
        })
          //民族
      getSelectValue({param: 'nations'}).then(res => {
         // console.log(res.data)
          this.selectNation = res.data
        })
      
    }
  }
</script>

<style scoped lang="scss">
  .p-title{
    margin-bottom: 16px;
    margin-top: 12px;
  }
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 40px;
    height: 40px;
    line-height: 40px;
    text-align: center;
  }
  .avatar {
    width: 40px;
    height: 40px;
    display: block;
  }
  .w120{
    width:120px;
  }
</style>
