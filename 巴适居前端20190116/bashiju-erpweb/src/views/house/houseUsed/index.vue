<template>
  <div class="page-content">

    <div class="page-content-hd">
      <div class="query-form">

        <el-form size="mini" :inline="true" :model="queryForm" ref="queryForm">

          <div class="hd">
            <el-form-item prop="transactionTypeId">
              <el-select clearable class="w150" v-model="queryForm.transactionTypeId">
                <el-option v-for="(item, index) in transactionTypes" :label="item.label" :key="index" :value="item.value"></el-option>
              </el-select>
            </el-form-item>

            <el-form-item prop="areas">
              <base-cascader
                class="w300"
                :data="areasSelectOpts"
                :props="cascaderProps"
                v-model="showAreas"
                :dataProps="cascaderDataProps"
                placeholder="选择行政区、片区、小区"
                @change="handleAreasChange"
              >
              </base-cascader>

            </el-form-item>

            <el-form-item prop="building">

              <base-vague-autocomplete
                :dataProps="vagueQueryBuilding"
                v-model="queryForm.building" @select="handleSelectBuilding" placeholder="坐栋">
              </base-vague-autocomplete>

            </el-form-item>

            <el-form-item prop="buildingUnit">
              <el-input class="w100" v-model="queryForm.buildingUnit" placeholder="单元"></el-input>
            </el-form-item>

            <el-form-item prop="buildingHouse">
              <el-input class="w100" v-model="queryForm.buildingHouse" placeholder="房号"></el-input>
            </el-form-item>

            <el-form-item prop="vagueData">

              <base-vague-autocomplete
                class="w300"
                :dataProps="vagueQueryInfo"
                v-model="queryForm.vagueData" @select="handleSelectInfo" placeholder="门牌号,电话,编号,业主,小区,钥匙编号,委托编号">
              </base-vague-autocomplete>

            </el-form-item>

            <el-form-item>
              <el-button type="primary" :loading="loadingQueryBtn" @click="handleQuery">查询</el-button>`
            </el-form-item>
            <el-form-item>
              <el-button @click="_resetQueryForm('queryForm')">清空</el-button>
            </el-form-item>

            <el-form-item class="pull-right">
              <el-dropdown v-hasOnlyBtn="PERMISSION_BTN.ADD_HOUSE" @command="handleCommandAddHouse">
                <el-button v-hasOnlyBtn="'addHouse'" type="primary">
                  新增房源 <i class="el-icon-plus"></i>
                </el-button>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item v-for="item in addTypes" :key="item.value" :command="item">{{item.label}}</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </el-form-item>
          </div>

          <div class="center">
            <div class="status-wrap">
              <el-form-item label="状态">
                <div class="item">
                  <a
                    @click="setQueryStatus({'isAll': 1}), statusItemActive = '全部', statusItemChildActive = ''"
                    :class="{active: statusItemActive === '全部'}"
                    href="javascript:;"
                  >全部</a>

                </div>
                <div class="item">
                  <a
                    @click="setQueryStatus({'effective': 3}),statusItemActive = '有效', statusItemChildActive = ''"
                    :class="{active: statusItemActive === '有效'}"
                    class="grey-color" href="javascript:;">有效</a>
                  <transition name="el-zoom-in-center">
                    <div class="children" v-show="statusItemActive === '有效'">
                      <div class="arrow"></div>
                      <div class="content">
                        <a
                          @click="setQueryStatus({'effective': 1}), statusItemChildActive = '公盘'"
                          :class="{active: statusItemChildActive === '公盘'}"
                          class="child" href="javascript:;">公盘</a>
                        <a
                          @click="setQueryStatus({'effective': 2}), statusItemChildActive = '私盘'"
                          :class="{active: statusItemChildActive === '私盘'}"
                          class="child" href="javascript:;">私盘</a>
                      </div>
                    </div>
                  </transition>
                </div>
                <div class="item">
                  <a
                    @click="setQueryStatus({'deal': 3}), statusItemActive = '成交', statusItemChildActive = ''"
                    :class="{active: statusItemActive === '成交'}"
                    class="grey-color" href="javascript:;">成交</a>
                  <transition name="el-zoom-in-center">
                    <div class="children" v-show="statusItemActive === '成交'">
                      <div class="arrow"></div>
                      <div class="content">
                        <a
                          @click="setQueryStatus({'deal': 1}), statusItemChildActive = '我售'"
                          :class="{active: statusItemChildActive === '我售'}"
                          class="child" href="javascript:;">我售</a>
                        <a
                          @click="setQueryStatus({'deal': 2}), statusItemChildActive = '我租'"
                          :class="{active: statusItemChildActive === '我租'}"
                          class="child"href="javascript:;">我租</a>
                      </div>
                    </div>
                  </transition>
                </div>
                <div class="item">
                  <a
                    @click="setQueryStatus({'invalid': queryForm.invalids}), statusItemActive = '失效', statusItemChildActive = ''"
                    :class="{active: statusItemActive === '失效'}"
                    class="grey-color" href="javascript:;">失效</a>
                  <transition name="el-zoom-in-center">
                    <div class="children" v-show="statusItemActive === '失效'">
                      <div class="arrow"></div>
                      <div class="content">
                        <el-checkbox-group style="font-size: 12px" v-model="queryForm.invalids">
                          <el-checkbox @change="handleChangeInvalids" v-for="(item, index) in invalids" :key="index" :label="item.value">{{item.label}}</el-checkbox>
                        </el-checkbox-group>
                      </div>
                    </div>
                  </transition>

                </div>
              </el-form-item>
            </div>

            <!-- 条件复选框组 -->
            <el-form-item class="checkbox-wrap" prop="conditions">
              <el-checkbox-group v-model="queryForm.conditions">
                <el-checkbox
                  @change="handleChangeConditions"
                  v-for="(item, index) in conditions" :key="index" :label="item.value"
                  style="margin-left: 0; margin-right: 20px; margin-bottom: 5px"
                >{{item.label}}</el-checkbox>
              </el-checkbox-group>
            </el-form-item>
          </div>

          <div class="bottom">
            <el-form-item class="margin-b-none" prop="sellingPrice">
              <base-section-select v-model="queryForm.sellingPrice" :data="regionValue.sell" placeholder="价格区间" description="万元"></base-section-select>
            </el-form-item>

            <el-form-item class="margin-b-none" prop="buildSpace">
              <base-section-select v-model="queryForm.buildSpace" :data="regionValue.space" placeholder="面积区间" description="平米"></base-section-select>
            </el-form-item>

            <el-form-item class="margin-b-none" prop="agentType">
              <el-select clearable class="w150" v-model="queryForm.agentType">
                <el-option v-for="(value, key) in deptUserType" :label="value" :key="key" :value="key"></el-option>
              </el-select>
            </el-form-item>

            <el-form-item class="margin-b-none" prop="departmentOrUser">
              <base-cascader
                :changeOnSelect="false"
                v-model="showDepartmentOrUser"
                :data="peopleSelectOpts"
                :props="cascaderProps"
                :dataProps="cascaderDataProps"
                placeholder="选择用户"
                @change="handleDepartmentOrUser"
              >
              </base-cascader>
            </el-form-item>

            <el-form-item class="margin-b-none">
              <more-conditions @handleFormChange="handleMoreFormChange" @handleConfirmOrReset="listenMoreFormParams" ref="moreConditions"></more-conditions>
            </el-form-item>

          </div>

        </el-form>

      </div>
    </div>

    <div class="page-content-bd" v-loading="loadingView">

      <house-used-table @selectionChange="handleSelectionChange" ref="table" :tableData="tableData">
        <el-button v-hasOnlyBtn="PERMISSION_BTN.EXPORT_BTN" size="mini">导出当前页</el-button>

        <el-dropdown v-hasOnlyBtn="PERMISSION_BTN.PRINT_HOUSE_BTN" size="medium">
          <el-button :disabled="!selectionRows.length" size="mini">
            房源打印<i class="el-icon-arrow-down el-icon--right"></i>
          </el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>租赁</el-dropdown-item>
            <el-dropdown-item>橙色横版15</el-dropdown-item>
            <el-dropdown-item>绿色横版15</el-dropdown-item>
            <el-dropdown-item>绿色横版15</el-dropdown-item>
            <el-dropdown-item>黄色竖版12</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>

        <el-button :disabled="!selectionRows.length" @click="houseContrast" size="mini">房源对比</el-button>

        <el-button :disabled="!selectionRows.length" @click="handleHouseTransfer" style="margin-left: 0" size="mini">批量信息转移</el-button>

        <el-button :disabled="!selectionRows.length" @click="deleteHouse" style="margin-left: 0" size="mini">房源删除</el-button>

        <el-dropdown v-hasOnlyBtn="PERMISSION_BTN.SYNCHRO_BTN" @command="handleCommandSynch" size="medium">
          <el-button :disabled="!selectionRows.length" size="mini">
            外网同步<i class="el-icon-arrow-down el-icon--right"></i>
          </el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="1">与外网同步</el-dropdown-item>
            <el-dropdown-item command="0">取消与外网同步</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>

        <el-dropdown @command="handleCommandSort" size="medium">
          <el-button size="mini">
            排序<i class="el-icon-arrow-down el-icon--right"></i>
          </el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="1">按带看次数升序</el-dropdown-item>
            <el-dropdown-item command="2">按带看次数降序</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </house-used-table>

      <b-pagination
        :listQuery="listQuery"
        @handleSizeChange="handleSizeChange"
        @handleCurrentChange="handleCurrentChange">
      </b-pagination>

    </div>

    <template>

      <!-- 房源新增 -->
      <el-dialog
        :title="dynamicTitle"
        :visible.sync="dialogVisibleAddHouse"
        width="500px"
        :close-on-click-modal="false"
        :close-on-press-escape="false"
      >
        <add-house @handleClick="handleAddHouseClick" :param="addHouseParams" ref="addHouse"></add-house>
      </el-dialog>

      <!-- 批量信息转移 -->
      <el-dialog
        :title="dialogHouseTransferTitle"
        :visible.sync="dialogVisibleHouseTransfer"
        width="400px"
        :close-on-click-modal="false"
        :close-on-press-escape="false"
      >
        <el-form :model="houseTransferForm" ref="houseTransferForm" label-width="80px">

          <el-form-item
            prop="userId"
            label="转移给"
            :rules="[
              { required: true, message: '该项不能为空！', trigger: 'change' }
            ]"
          >

            <!-- Todo 房源信息转移用户信息 -->
            <base-cascader
              :changeOnSelect="false"
              v-model="houseTransferForm.userId"
              :data="peopleSelectOpts"
              :props="cascaderProps"
              :dataProps="cascaderDataProps"
              placeholder="选择部门或用户"
              @change="handleChangeCascaderTransfer"
            >
            </base-cascader>

          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="submitHouseTransferForm">确定</el-button>
            <el-button @click="dialogVisibleHouseTransfer = false">取消</el-button>
          </el-form-item>

        </el-form>

      </el-dialog>

      <!-- 房源对比 -->
      <el-dialog
        title="房源对比"
        :visible.sync="dialogVisibleHouseContrast"
        width="950px"
        :close-on-click-modal="false"
        :close-on-press-escape="false"
      >
        <house-contrast
          ref="houseContrast"
          :data="selectionRows"
        >
        </house-contrast>
        <div style="text-align: center">
          <el-button size="small" @click="dialogVisibleHouseContrast = false">关闭</el-button>
        </div>
      </el-dialog>

    </template>

  </div>
</template>

<script>
  import PageList from '@/mixins/pageList' // 列表页面查询 mixin
  import {searchHouseList, deleteHousing, houseSynchron, houseTransfer} from '@/request/house/houseUsed' // 请求后端URL路径
  import HouseUsedTable from './Table'
  import AddHouse from './AddHouse'
  import BaseVagueAutocomplete from '@/components/BaseVagueAutocomplete'
  import BaseCascader from '@/components/BaseCascader/index'
  import BaseSectionSelect from '@/components/BaseSectionSelect'
  import MoreConditions from './MoreConditions'
  import HouseContrast from './HouseContrast'
  import {queryCommunitySelectWithRegion, getRegionValue} from '@/request/app'
  import {queryReferenceUserSelect} from '@/request/manage/common'
  import * as consts from './consts'

  const transactionTypes = [
    {
      label: '全部',
      value: 4,
    },
    {
      label: '出售',
      value: 1,
    },
    {
      label: '出租',
      value: 2,
    },
    {
      label: '租售',
      value: 3,
    }
  ]

  const conditions = [
    {
      label: '我的私盘',
      value: 'myPrivate'
    },
    {
      label: '我的房源',
      value: 'myHousing'
    },
    {
      label: '我的收藏',
      value: 'myCollection'
    },
    {
      label: '今日浏览',
      value: 'todaySee'
    },
    {
      label: '责任盘',
      value: 'managePlate'
    },
    {
      label: '待确认',
      value: 'unConfirm'
    },
    {
      label: '淘宝池',
      value: 'hunting'
    },
    {
      label: '公盘非淘宝池',
      value: 'unHunting'
    },
    {
      label: '图片',
      value: 'isPicture'
    },
    {
      label: '视频 ',
      value: 'isVideo'
    },
    {
      label: '钥匙',
      value: 'isKey'
    },
    {
      label: '无钥匙',
      value: 'unKey'
    },
    {
      label: '独家',
      value: 'isUnique'
    },
    {
      label: '委托',
      value: 'isIntrust'
    },
    {
      label: '封盘路径',
      value: 'closeDiscWay'
    },
    {
      label: '封盘电话',
      value: 'closeDiscPhone'
    },
    {
      label: '通知撤单',
      value: 'isCancelNotice'
    },
    {
      label: '新上',
      value: 'newHousing'
    },
    {
      label: '急切',
      value: 'isUrgent'
    },
    {
      label: '学区房',
      value: 'isSchoolRoom'
    },
    {
      label: '靓房',
      value: 'isGood'
    },
    {
      label: '外网同步',
      value: 'isSynchron'
    },
    {
      label: '30天租赁到期',
      value: 'contractEnd'
    },
    {
      label: '降价',
      value: 'reducePriceFlg'
    },
    {
      label: '带看',
      value: 'viewing'
    },

  ]

  const addTypes = [
    {
      label: '住宅',
      value: 'house'
    },
    {
      label: '别墅',
      value: 'villa'
    },
    {
      label: '商铺',
      value: 'shops'
    },
    {
      label: '公寓',
      value: 'apartment'
    },
    {
      label: '写字楼',
      value: 'officeBuiling'
    },
    {
      label: '仓库',
      value: 'wareHouse'
    },
    {
      label: '厂房',
      value: 'factory'
    },
    {
      label: '车位',
      value: 'parking'
    },
    {
      label: '土地',
      value: 'land'
    },
  ]

  const invalids = [
    {
      label: '他租',
      value: 4
    },
    {
      label: '他售',
      value: 5
    },
    {
      label: '未知',
      value: 6
    },
    {
      label: '自住',
      value: 7
    },
    {
      label: '出租中',
      value: 8
    },
    {
      label: '空置',
      value: 9
    },
    {
      label: '已驳回',
      value: 11
    }
  ]

  const deptUserType = {
    '1': '录入人',
    '2': '委托人',
    '3': '开盘人',
    '8': '维护人',
    '10': '独家人',
    '9': '图片人',
    '7': '拿钥匙人',
  }


  export default {
    name: 'houseUsed',

    mixins: [PageList],

    components: {BaseSectionSelect, HouseUsedTable, BaseVagueAutocomplete, BaseCascader, AddHouse, MoreConditions, HouseContrast},

    data () {
      return {
        PERMISSION_BTN: consts.PERMISSION_BTN,
        queryForm: {
          transactionTypeId: 4,
          conditions: [],
          invalids: [],
          vagueData: '', // 门牌，电话 编号 业主... 模糊查询
          building: '', // 座栋
          buildingUnit: '',
          buildingHouse: '',
          areas: [],
          agentType: '',
          sellingPrice: '',
          buildSpace: '',
          departmentOrUser: ''
        },
        dynamicTitle:null,//动态标题
        showAreas: '',
        showDepartmentOrUser: '',
        statusItemActive: '全部',
        statusItemChildActive: '',
        tableData: [],
        conditions: conditions,
        transactionTypes: transactionTypes,
        addTypes: addTypes,
        invalids: invalids,
        deptUserType: deptUserType,
        queryParams: {
          status: null,
          building: null,
          vagueData: null
        },
        restaurants: [],
        peopleSelectOpts: [], // 相关用户员工级联选择数据
        areasSelectOpts: [], // 小区级联
        state2: '',
        vagueQueryBuilding: consts.vagueQueryBuilding,
        vagueQueryInfo: consts.vagueQueryInfo,
        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        dialogVisibleAddHouse: false,
        dialogVisibleHouseTransfer: false,
        dialogVisibleHouseContrast: false,
        addHouseParams: {
          houseUsesId: '',
          houseUses: ''
        },
        moreFormParams: {}, // 更多条件组件 form参数
        selectionRows: [], // 选中的table列
        tableSort: '', // 排序参数
        dialogHouseTransferTitle: '', // 批量信息转移弹框
        houseTransferForm: {
          userId: ''
        },
        regionValue: {
          sell: {},
          rent: {},
          space: {}
        } // 区间下拉数据
      }
    },

    methods: {
      handleDepartmentOrUser (val, name, data) {
        if (data.dataType !== 'user') {
          setTimeout(() => {
            this.queryForm.departmentOrUser = ''
            this.showDepartmentOrUser = ''
          }, 50)
        } else {
          this.queryForm.departmentOrUser = {
            value: val,
            type: 2
          }
        }
      },

      handleQuery () {
        this.listQueryParams = Object.assign({}, this.queryForm)

        this.queryParams.building && (this.listQueryParams.building = this.queryParams.building)
        this.queryParams.vagueData && (this.listQueryParams.vagueData = this.queryParams.vagueData)

        this.listQuery.page = 1
        this.listQuery.currentPage = 1
        this._loadData(true)
      },

      // 点击坐栋输入建议项赋值
      handleSelectBuilding (item) {
        this.queryParams.building = {
          type: item.type,
          value: item.value,
        }
      },

      // 点击其他信息输入建议项赋值
      handleSelectInfo (item) {
        // 点击坐栋输入建议项赋值
        this.queryParams.vagueData = {
          type: item.type,
          value: item.value,
        }
      },

      // 区域级联选择
      handleAreasChange (val, label, data) {
        if (data) {
          // Todo 级联选择暂时不支持多选 后面加入
          if(data.dataType === 'city'){
            //城市
            this.queryForm.areas = [{
              type: 0,
              value: data.code
            }]
          }else if(data.dataType === 'area'){
              //行政区域
              this.queryForm.areas = [{
                type: 1,
                value: data.code
              }]
          }else if(data.dataType === 'region'){
                //片区
                //拆分
               let region = data.code.split('r-');
               if(region){//不为空的情况下
                 this.queryForm.areas = [{
                   type: 2,
                   value: region[1]
                 }]
               }
          }else{
            //小区
            this.queryForm.areas = [{
              type: 3,
              value: data.code
            }]
          }
        }

      },

      // 更多查询条件弹框 点击确认后
      listenMoreFormParams (formInfo) {
        this.moreFormParams = formInfo || {}
        if (formInfo) {
          this.listQuery.page = 1
          this.listQuery.currentPage = 1
          this._loadData(false)
        }
      },

      // 监听更多查询条件form事件
      handleMoreFormChange (formInfo) {
        this.moreFormParams = formInfo || {}

        if (Object.keys(formInfo).length) {
          this.listQuery.page = 1
          this.listQuery.currentPage = 1
          this._loadData(false)
        }

      },

      // 点击搜索区域状态项
      setQueryStatus (status) {
        if (status.invalid) { // 点击失效
          // 默认全选
          this.queryForm.invalids = this.invalids.map(item => item.value)

          this.queryParams.status = {invalid: this.queryForm.invalids}
        } else {
          this.queryParams.status = status
        }
        this.listQuery.page = 1
        this.listQuery.currentPage = 1
        this._loadData(false)
      },

      // 选择搜索区域状态项 失效复选框
      handleChangeInvalids () {
        this.queryParams.status = {invalid: this.queryForm.invalids}
        this.listQuery.page = 1
        this.listQuery.currentPage = 1
        this._loadData(false)
      },

      // 选择条件复选框
      handleChangeConditions () {
        this.listQuery.page = 1
        this.listQuery.currentPage = 1
        this._loadData(false)
      },

      // 新增房源弹框
      handleCommandAddHouse (command) {
        this.dynamicTitle = '新增'+command.label//设置动态新增标题
        this.addHouseParams = {
          houseUsesId: command.value,
          houseUses: command.label,
          // transactionTypeId: this.queryForm.transactionTypeId // 当前选中的交易类型
        }
        this.$refs['addHouse'] && this.$refs['addHouse']._resetForm()
        this.dialogVisibleAddHouse = true
      },

      // 监听房源新增
      handleAddHouseClick (action) {
        if (action === 1) {
          this._loadData(false)
        }
        this.dialogVisibleAddHouse = false
      },

      // 监听table选中事件
      handleSelectionChange (rows) {
        this.selectionRows = rows
      },

      // 房源对比
      houseContrast () {
        if (this.selectionRows.length > 5) {
          this.$alert('最多只能比较5条房源', {
            confirmButtonText: '确定',
            type: 'warning'
          })

          return false
        }
        this.$refs['houseContrast'] && this.$refs['houseContrast']._initialization()
        this.dialogVisibleHouseContrast = true
      },

      // 批量信息转移 houseIds 房源id userId 转移给的用户id
      handleHouseTransfer () {
        this._resetForm('houseTransferForm')

        let length = this.selectionRows.length
        this.dialogHouseTransferTitle = `批量信息转移[你选择了${length}条数据]`
        this.dialogVisibleHouseTransfer = true
      },


      handleChangeCascaderTransfer (val, name, data) {
        if (data.dataType !== 'user') {
          this.$message({
            showClose: true,
            type: 'warning',
            message: '只能选择用户!',
            duration: 1500
          })

          setTimeout(() => {
            this.houseTransferForm.userId = ''
          }, 50)

        }
      },

      // 提交房源信息转移
      submitHouseTransferForm () {

        this.$refs['houseTransferForm'].validate((valid) => {

            if (valid) {
              this.$confirm('确定转移选中的房源给该用户吗？', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                let selectionRows = this.selectionRows
                let houseIds = selectionRows.map(item => {
                  return item.id
                })
                let params = {
                  houseIds: houseIds.join(),
                  userId: this.houseTransferForm.userId
                }
                houseTransfer(params).then(() => {
                  this.$message({
                    type: 'success',
                    message: '操作成功',
                    duration: 1500
                  })
                  this._loadData(false)
                  this.dialogVisibleHouseTransfer = false

                  let houseCodes = []
                  selectionRows.forEach(item => {
                    houseCodes.push(item.houseId)
                  })
                  // 向后台传递日志数据
                  let message = {
                    businessTypeId: this.$DICT_CODE.LOG.BUSINESS_TYPE.HOUSE, // 业务类型
                    operatTypeId: this.$DICT_CODE.LOG.BUSINESS_OPERATE_TYPE.MAINER_TRANSFER, // 操作类型
                    logContent: `【${houseCodes.join()}】`,
                  }
                  this.$updateLog.house.houseQueryLog({message: JSON.stringify(message)})
                })
              })
            }

        })
      },

      // 删除房源
      deleteHouse () {
        this.$confirm('确定删除选中的房源吗？', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let selectionRows = this.selectionRows

          let houseIds = selectionRows.map(item => {
            return item.id
          })

          deleteHousing({houseIds: houseIds.join()}).then(() => {
            this.$message({
              type: 'success',
              message: '操作成功',
              duration: 1500
            })
            this._loadData(false)

            let houseCodes = []
            selectionRows.forEach(item => {
              houseCodes.push(item.houseId)
            })

            // 向后台传递日志数据
            let message = {
              businessTypeId: this.$DICT_CODE.LOG.BUSINESS_TYPE.HOUSE, // 业务类型
              operatTypeId: this.$DICT_CODE.LOG.BUSINESS_OPERATE_TYPE.DEL_HOUSE, // 操作类型
              logContent: `删除房源【${houseCodes.join()}】`,
            }
            this.$updateLog.house.houseDelLog({message: JSON.stringify(message)})
          })
        })
      },

      // 排序 1: 升序 2：降序
      handleCommandSort (command) {
        this.tableSort = command
        this._loadData(false)
      },

      // 与外网同步 1: 同步，0：取消同步
      handleCommandSynch (command) {
        this.$confirm('确定操作选中的房源与外网同步吗？', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let selectionRows = this.selectionRows

          let houseIds = selectionRows.map(item => {
            return item.id
          })

          let params = {
            houseIds: houseIds.join(),
            state: command
          }

          houseSynchron(params).then(() => {
            this.$message({
              type: 'success',
              message: '操作成功',
              duration: 1500
            })
            this._loadData(false)

            // modify by zuoyuntao 20181212 领导说这里的批量操作进行循环记日志
            let operatorInfo = ''
            let operate = null
            if (command === '1') {
              operate = this.$DICT_CODE.LOG.BUSINESS_OPERATE_TYPE.HOUSE_SET_SYN
              operatorInfo = '二手房源外网同步'
            } else {
              operate = this.$DICT_CODE.LOG.BUSINESS_OPERATE_TYPE.HOUSE_CANCEL_SYN
              operatorInfo = '二手房源取消外网同步'
            }

            selectionRows.forEach(item => {
              // 向后台传递日志数据
              let message = {
                sourceId: item.id,
                sourceCode: item.houseId,
                sourceTypeId: item.transactionTypeId,
                businessTypeId: this.$DICT_CODE.LOG.BUSINESS_TYPE.HOUSE, // 业务类型
                operatTypeId: operate, // 操作类型
                logContent: operatorInfo
                // logContent: `【${houseCodes.join()}】`
              }
              console.log(message)
              this.$updateLog.house.houseQueryLog({message: JSON.stringify(message)})
            })
          })
        })
      },

      _loadData (btnQuery) {
        if (btnQuery) {
          this.loadingQueryBtn = true
        }

        this.loadingView = true

        const params = Object.assign({}, this._formatQueryParams(this.listQueryParams), {
          start: this.listQuery.limit * (this.listQuery.page - 1),
          length: this.listQuery.limit,
          draw: 1
        })

        searchHouseList(params).then(res => {
          this.tableData = res.data
          this.listQuery.total = res.recordsTotal
          this.loadingQueryBtn = false
          this.loadingView = false
        }).catch(() => {
          this.loadingQueryBtn = false
          this.loadingView = false
        })
      },

      // 获取区间下拉数据
      _getRegionValue () {
        // 获取售价区间
        const sellParams = {
          widgetCode: 'sellPriceRange',
          tag: 2
        }
        getRegionValue(sellParams).then(res => {
          this.regionValue.sell = res.data || []
        })

        // 获取租价区间
        const rentParams = {
          widgetCode: 'rentPriceRange',
          tag: 2
        }
        getRegionValue(rentParams).then(res => {
          this.regionValue.rent = res.data || []
        })

        // 获取租价区间
        const spaceParams = {
          widgetCode: 'spaceRange',
          tag: 2
        }
        getRegionValue(spaceParams).then(res => {
          this.regionValue.space = res.data || []
        })
      },

      _resetQueryForm (form) {
        this._resetForm(form, () => {
          // 重置绑定的坐栋和详细信息模糊查询
          this.queryParams.building = {}
          this.queryParams.vagueData = {}

          // 重置绑定的状态项
          this.queryParams.status = {}

          this.queryForm.conditions = []

          // 重置状态
          this.statusItemActive = '全部'
          this.statusItemChildActive = ''

          // 重置排序
          this.tableSort = ''

          // 重置更多条件
          this.$refs['moreConditions'].handleReset()

          // 重置区域小区表单显示
          this.showAreas = ''

          // 重置用户检索表单显示
          this.showDepartmentOrUser = ''
        })
      },

      _formatQueryParams (_params) {
        let params = {}
        let temp = {}
        let form = this.queryForm

        Object.keys(_params).forEach(key => {
          if (_params[key]) {
            temp[key] = _params[key]
          }
        })

        // 复选框组
        form.conditions.forEach(fc => {
          temp[fc] = 1
        })

        this.queryParams.status && (temp.status = this.queryParams.status)

        // 与更多添加弹出框参数对象合并
        temp = {...temp, ...this.moreFormParams}

        // 排序
        if (this.tableSort) temp.sort = this.tableSort

        // 移除多余的参数
        if (temp.areas && !temp.areas.length) delete temp.areas
        temp.conditions && delete temp.conditions
        temp.invalids && delete temp.invalids

        params.condition = JSON.stringify(temp)
        return params
      }
    },

    mounted () {

      this._getRegionValue()

      // 获取相关用户员工级联数据
      queryReferenceUserSelect({}).then(res => {
        this.peopleSelectOpts = res
      })

      // 获取行政区 小区等级联数据
      this.$get(queryCommunitySelectWithRegion, {}).then(res => {
        this.areasSelectOpts = res
      })

    },

    created () {
      if (this.$route.query.viewMyResource) {
        this.queryForm.conditions.push('myHousing')
      }
    },

    watch: {
      // 监听坐栋查询 如果当前输入的内容和之前绑定的输入建议不一致 则说明没有点击建议项 赋默认值
      'queryForm.building': function (newValue) {

        // 如果为空重置模糊查询对象
        if (!newValue) {
          this.queryParams.building = {}
          return
        }

        let defaultVal = {
          type: this.vagueQueryBuilding[0].type,
          value: newValue,
        }

        if (this.queryParams.building) {
          if (newValue !== this.queryParams.building) {
            this.queryParams.building = defaultVal
          }
        } else {
          // 没有点击过输入建议项
          this.queryParams.building = defaultVal
        }
      },

      // 监听查询 如果当前输入的内容和之前绑定的输入建议不一致 则说明没有点击建议项 赋默认值
      'queryForm.vagueData': function (newValue) {
        // 如果为空重置模糊查询对象
        if (!newValue) {
          this.queryParams.vagueData = {}
          return
        }

        let defaultVal = {
          type: this.vagueQueryInfo[0].type,
          value: newValue,
        }

        if (this.queryParams.vagueData) {
          if (newValue !== this.queryParams.vagueData) {
            this.queryParams.vagueData = defaultVal
          }
        } else {
          // 没有点击过输入建议项
          this.queryParams.vagueData = defaultVal
        }
      },

      showDepartmentOrUser (newVal) {
        if (!newVal) {
          this.queryForm.departmentOrUser = ''
        }
      },

      '$route.query.viewMyResource': function (val) {
        if (val) {
          this._resetQueryForm('queryForm')

          this.queryForm.conditions.push('myHousing')

          this._loadData(false)
        }
      }
    }
  }
</script>

<style scoped lang="scss">
  $grey-color: #909399;
  .status-wrap {
    min-height: 50px;
    .item {
      display: inline-block;
      margin-right: 10px;
      &>a {
        color: $grey-color;
      }

      .children {
        display: inline-block;
        position: relative;

        .arrow {
          position: absolute;
          top: 50%;
          left: 0;
          width: 0;
          height: 0;
          border-color: transparent;
          border-style: solid;
          border-width: 5px 5px 5px 0;
          border-right-color: $grey-color;
          margin-top: -5px;
        }

        .content {
          padding: 0 5px;
          margin-left: 5px;
          /*max-width: 500px;*/
          padding: 0 8px;
          text-align: center;
          background-color: #fff;
          border-radius: 4px;
          border: 1px solid $grey-color;
          font-size: 12px;
        }
      }

      .child {
        display: inline-block;
        color: $grey-color;
      }
    }

    a:hover {
      text-decoration: underline;
    }
  }

  .red-color {
    color: #e41e2b;
  }
  .grey-color {
    color: #909399;
  }

  .active {
    color: #e41e2b !important;
  }
</style>
