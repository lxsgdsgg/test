<template>
  <div class="page-content">
    <div class="page-content-hd">
      <div class="query-form">
        <el-form size="small" ref="form" :inline="true" :model="form">
          <el-form-item label="方案名称" prop="transferProcName">
            <el-input v-model="form.transferProcName" placeholder="请输入方案名称"></el-input>
          </el-form-item>

          <el-form-item label="所属城市" prop="areaCode">
            <base-city-cascader v-model="form.areaCode"></base-city-cascader>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click.native.prevent="handleQuery" :loading="loadingQueryBtn">查询</el-button>
            <el-button @click.native.prevent="_resetForm('form')">重置</el-button>
          </el-form-item>

          <el-form-item class="pull-right">
            <el-button v-hasOnlyBtn="PERMISSION_BTN.ADD" type="primary" @click="handleAdd">新增</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>

    <div class="page-content-bd" v-loading="loadingView">
      <el-table
        size="small"
        :data="tableData"
        border
        align="center"
        style="width: 100%"
        height="580"
      >

        <el-table-column
          type="index"
          width="50"
          align="center"
          label="序号"
        >
        </el-table-column>

        <el-table-column
          prop="programmeName"
          align="center"
          label="方案名称">
        </el-table-column>

        <el-table-column
          prop="operator"
          align="center"
          label="创建用户">
        </el-table-column>

        <el-table-column
          prop="addTime"
          align="center"
          :formatter="_timeFormat"
          label="新增时间">
        </el-table-column>

        <el-table-column
          prop="updateOperator"
          align="center"
          label="更新用户">
        </el-table-column>

        <el-table-column
          prop="areaName"
          align="center"
          label="所属城市">
        </el-table-column>

        <el-table-column
          align="center"
          label="操作"
          width="250"
        >
          <template slot-scope="scope">
            <el-button v-hasMultipleBtn="[PERMISSION_BTN.EDIT, scope.row]" @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
            <el-button v-hasMultipleBtn="[PERMISSION_BTN.OWNER_DETAIL, scope.row]" @click="handleDetail(scope.row, 1)" type="text" size="small">业主材料明细</el-button>
            <el-button v-hasMultipleBtn="[PERMISSION_BTN.CUS_DETAIL, scope.row]" @click="handleDetail(scope.row, 2)" type="text" size="small">客户材料明细</el-button>
            <el-button v-hasMultipleBtn="[PERMISSION_BTN.CONFIG, scope.row]" @click="handleConfig(scope.row)" type="text" size="small">过户流程设置</el-button>
            <el-button v-hasMultipleBtn="[PERMISSION_BTN.DELETE, scope.row]" @click="handleDelete(scope.row)" type="text" size="small">删除</el-button>
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
      <b-dialog :show.sync="dialogEditVisible" title="配置编辑" width="400px">
        <el-form v-loading="loadingForm" :model="editForm" status-icon :rules="editFormRules" ref="editForm" label-width="100px">

          <el-form-item label="选择城市" prop="areaCode">
            <base-city-cascader @change="handleChangeCity" v-model="editForm.areaCode"></base-city-cascader>
          </el-form-item>

          <el-form-item label="方案名称" prop="procName">
            <el-input v-model="editForm.procName" placeholder="请填写方案名称"></el-input>
          </el-form-item>

          <el-form-item class="margin-b-none">
            <el-button type="primary" :loading="loadingSubmitBtn" @click="handleSubmit">确认</el-button>
            <el-button @click="dialogEditVisible = false">取消</el-button>
          </el-form-item>

        </el-form>
      </b-dialog>

      <b-dialog :show.sync="dialogDetailVisible" :title="dialigDetailTitle" width="650px">
        <detail-config v-if="hackReset" @closeDialog="closeDialogDetail" :params="detailParams"></detail-config>
      </b-dialog>

      <b-dialog :show.sync="dialogConfigVisible" title="过户流程设置" width="400px">
        <trans-proc-config v-if="hackReset" @closeDialog="closeDialogConfig" :params="detailParams"></trans-proc-config>
      </b-dialog>
    </template>

  </div>
</template>

<script>
  import PageList from '@/mixins/pageList' // 列表页面查询 mixin
  import * as RequestURL from '@/request/manage/transferProcess' // 请求后端URL路径
  import BaseCityCascader from '@/components/BaseCascader/city' // 城市级联组件
  import DetailConfig from './components/DetailConfig'
  import TransProcConfig from './components/TransProcConfig'

  // 权限按钮
  const PERMISSION_BTN = {
    ADD: 'transferProcAdd',
    EDIT: 'procModify',
    OWNER_DETAIL: 'hsOwnerDetail',
    CUS_DETAIL: 'cusDetail',
    CONFIG: 'procConf',
    DELETE: 'procDel'
  }

  export default {
    name: 'transferProcess',
    mixins: [PageList],
    components: {BaseCityCascader, DetailConfig, TransProcConfig},
    data () {
      return {
        PERMISSION_BTN: PERMISSION_BTN,
        form: {
          transferProcName: '',
          areaCode: ''
        },
        editForm: {
          procName: '',
          areaName: '',
          areaCode: '',
          procId: ''
        },
        // 表单校验配置
        editFormRules: {
          procName: [
            {required: true, message: '请输入名称', trigger: 'blur'}
          ],
          areaCode: [
            {required: true, message: '请选择城市', trigger: 'change'}
          ]
        },
        dialogEditVisible: false,
        dialogConfigVisible: false,
        dialogDetailVisible: false,
        loadingSubmitBtn: false,
        loadingQueryBtn: false,
        loadingForm: false,
        currentRowData: null, // 当前操作的行数据
        detailParams: null,
        hackReset: true,
        dialigDetailTitle: '',
        originalFormData: {}
      }
    },
    methods: {
      handleQuery () {
        this.listQueryParams = Object.assign({}, this.form)
        this.listQuery.page = 1
        this.listQuery.currentPage = 1
        this._loadData(true)
      },

      // 新增
      handleAdd () {
        this._resetForm('editForm')
        this.currentRowData = null // 置空当前操作的行数据
        this.isAdd = true
        this.dialogEditVisible = true
      },
      // 编辑
      handleEdit (row) {
        this._resetForm('editForm')
        this.currentRowData = row
        this.isAdd = false
        this.dialogEditVisible = true
        this.$nextTick(() => {
          this.originalFormData = Object.assign({}, this.currentRowData)
          this._setForm()
        })
      },

      // 提交
      handleSubmit () {

        this.$refs['editForm'].validate((valid) => {
          if (valid) {

            const params = {
              jsonData: JSON.stringify({...this.editForm})
            }

            if (this.isAdd) {
              params.procId = ''
            }

            this.$confirm('确定保存编辑的信息吗？, 是否继续?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.loadingSubmitBtn = true
              RequestURL.saveOrUpdateTranserProc(params).then(res => {
                this.loadingSubmitBtn = false
                this.$message({
                  type: 'success',
                  message: res.msg || '操作成功'
                })
                this.dialogEditVisible = false
                this._loadData(false)

                // 向后台传递日志数据
                if (this.isAdd) {
                  let message = {
                    sourceCode: this.currentRowData['programmeName'], // 资源标识
                    sourceTypeId: this.$DICT_CODE.LOG.SOURCE_TYPE.MANAGE.TRANSFER_PROCESS, // 资源类型
                    operatTypeId: this.$DICT_CODE.LOG.OPERATE_TYPE.TRANSFER_PROCESS_ADD, // 操作类型
                    logContent: `新增过户流程方案：${params.procName}` // 日志内容
                  }
                  console.log(message)
                  this.$updateLog.manage.manageAddLog({message: JSON.stringify(message)})

                } else {

                  let message = {
                    sourceCode: this.currentRowData['programmeName'], // 资源标识
                    sourceTypeId: this.$DICT_CODE.LOG.SOURCE_TYPE.MANAGE.TRANSFER_PROCESS, // 资源类型
                    operatTypeId: this.$DICT_CODE.LOG.OPERATE_TYPE.TRANSFER_PROCESS_EDIT, // 操作类型
                    labelData: this.$utils.getFormFields(this.$refs['editForm']), // 修改字段对应的label
                    originalData: this.originalFormData, // 原始表单对象
                    newData: {...this.editForm} // 修改后的表单对象
                  }
                  this.$updateLog.manage.manageUpdateLog({message: JSON.stringify(message)})

                }

              }).catch(() => {
                this.loadingSubmitBtn = false
              })
            })

          }
        })
      },

      // 删除
      handleDelete (row) {
        this.currentRowData = row
        this.$confirm('确定删除该条数据？, 是否继续?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          RequestURL.delTransferProc({procId: row.id}).then(res => {
            this.$message({
              type: 'success',
              message: res.msg || '删除成功'
            })
            this._loadData(false)

            let name = row['programmeName']
            // 向后台传递日志数据
            let message = {
              sourceCode: name, // 资源标识
              sourceTypeId: this.$DICT_CODE.LOG.SOURCE_TYPE.MANAGE.TRANSFER_PROCESS, // 资源类型
              operatTypeId: this.$DICT_CODE.LOG.OPERATE_TYPE.TRANSFER_PROCESS_DELETE, // 操作类型 删除
              logContent: `删除过户流程方案：${name}` // 日志内容
            }
            this.$updateLog.manage.manageDelLog({message: JSON.stringify(message)})

          })
        })
      },

      // 材料明细 contentType 类型 1:业主 2:客户
      handleDetail (row, confType) {
        if (confType === 1) {
          this.dialigDetailTitle = '业主材料明细配置'
        } else {
          this.dialigDetailTitle = '客户材料明细配置'
        }
        this.hackReset = false
        this.$nextTick(() => {
          this.hackReset = true
          this.dialogDetailVisible = true

          this.detailParams = {
            confType: confType,
            procId: row.id,
            name: row['programmeName']
          }
        })
      },

      // 过户流程设置
      handleConfig (row) {
        this.hackReset = false
        this.$nextTick(() => {
          this.hackReset = true

          this.dialogConfigVisible = true
          this.detailParams = {
            confType: 3,
            procId: row.id,
            name: row['programmeName']
          }

        })
      },

      handleChangeCity (val, name) {
        this.editForm['areaName'] = name
      },

      closeDialogDetail (action) {
        this.dialogDetailVisible = false
        if (action) this._loadData(false)
      },

      closeDialogConfig (action) {
        this.dialogConfigVisible = false
        if (action) this._loadData(false)
      },

      // 加载数据
      _loadData (btnQuery) {
        if (btnQuery) {
          this.loadingQueryBtn = true
        }

        this.loadingView = true

        const params = this._getParams(this.listQueryParams)

        RequestURL.queryTransferProcessList(params).then(res => {
          this.tableData = res.data
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
        this.$utils.setFormInfo(this.editForm, this.currentRowData, () => {
          this.editForm['procName'] = this.currentRowData['programmeName']
          this.editForm['procId'] = this.currentRowData['id']
        })

        // 移除表单回填时 element ui自动添加的校验效果
        setTimeout(() => {
          this.$refs['editForm'].clearValidate()
        }, 100)
      }
    },
  }
</script>

<style scoped lang="scss">

</style>
