<!-- 开盘日期 -->

<template>
  <div style="min-height: 550px">
    <el-button v-if="btnPermission[PERMISSION_BTN.ADD_OPEN_TIME]" @click="handleAdd" type="primary" plain icon="el-icon-circle-plus" size="small">新增</el-button>

    <el-table
      size="small"
      :data="tableData"
      v-loading="loadingView"
    >
      <el-table-column
        prop="openDate"
        :formatter="_timeFormat"
        label="开盘日期">
      </el-table-column>
      <el-table-column
        prop="addTime"
        :formatter="_timeFormat"
        label="新增日期">
      </el-table-column>
      <el-table-column
        prop="buildings"
        label="开盘楼栋">
      </el-table-column>
      <el-table-column
        prop="remark"
        label="备注信息"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column
        prop="operator"
        label="操作人">
      </el-table-column>
      <el-table-column
        label="操作"
        width="200"
      >
        <template slot-scope="scope">
          <el-button v-if="btnPermission[PERMISSION_BTN.UPDATE_OPEN_TIME]" @click="handleEdit(scope.row)" icon="el-icon-edit" size="mini">编辑</el-button>
          <el-button v-if="btnPermission[PERMISSION_BTN.DEL_OPEN_TIME]" @click="handleDelete(scope.row)" icon="el-icon-delete" size="mini">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <b-pagination
      :listQuery="listQuery"
      @handleSizeChange="handleSizeChange"
      @handleCurrentChange="handleCurrentChange">
    </b-pagination>

    <el-dialog
      title="新增/编辑"
      :visible.sync="dialogVisible"
      width="400px"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
    >

      <el-form ref="form" :model="form" :rules="rules" size="medium" label-width="100px">
        <el-form-item label="开盘时间" prop="openDate">

          <el-date-picker
            style="width: 100%"
            v-model="form.openDate"
            type="date"
            placeholder="选择日期"
            value-format="yyyy-MM-dd"
          >
          </el-date-picker>

        </el-form-item>

        <el-form-item label="开盘楼栋" prop="buildingsIds">

          <el-select
            style="width: 100%"
            v-model="form.buildingsIds"
            multiple
            filterable
            collapse-tags
            placeholder="请选择">
            <el-option
              v-for="item in buildingsData"
              :key="item.id"
              :label="item.name + item.buildUnitAlias"
              :value="item.id">
            </el-option>
          </el-select>

        </el-form-item>

        <el-form-item label="备注" prop="remark">
          <el-input style="width: 100%" type="textarea" v-model="form.remark"></el-input>
        </el-form-item>

        <el-form-item class="margin-b-none">
          <el-button @click="handleSubmit" type="primary">保存</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>

    </el-dialog>
  </div>
</template>

<script>
  import PageList from '@/mixins/pageList' // 列表页面查询 mixin
  import constMixin from './constMixin'
  import {queryPropertyOpenDateByProjectId, saveOrUpdatePropertyOpenDate, delPropertyOpenDateById} from '@/request/house/houseNew'

  export default {
    mixins: [PageList, constMixin],

    props: {
      projectId: {
        type: [Number, String]
      },

      buildingsData: {
        type: Array
      }
    },

    data () {
      return {
        dialogVisible: false,
        currentRowData: null,
        isAdd: true,
        form: {
          openDate: '',
          remark: '',
          buildingsIds: [],
        },
        rules: {
          buildingsIds: [
            { required: true, message: '必填项', trigger: 'change' }
          ],

          openDate: [
            { required: true, message: '请选择日期', trigger: 'change' }
          ],

          remark: [
            { required: true, message: '必填项', trigger: 'blur' }
          ]
        },
        originalData: {}
      }
    },

    methods: {
      handleAdd () {
        this.currentRowData = null
        this._resetForm('form')
        this.dialogVisible = true
      },

      handleSubmit () {
        this.$refs['form'].validate(valid=> {
          if (valid) {
            this.$confirm('确定保存编辑的信息?',
              {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }
            ).then(() => {
              let params = {...this.form}
              let buildings = []

              params.buildingsIds.forEach(item => {
                this.buildingsData.forEach(_item => {
                  if (item === _item.id) {
                    buildings.push(_item.name)
                  }
                })
              })

              params.buildingsIds = params.buildingsIds.join()
              params.buildings = buildings.join()

              params.projectId = this.projectId

              if (this.currentRowData) {
                params.id = this.currentRowData.id
              }

              saveOrUpdatePropertyOpenDate({jsonData: JSON.stringify(params)}).then(res => {
                this.$message({
                  type: 'success',
                  message: '操作成功',
                  showClose: true,
                  duration: 2000
                })
                this.dialogVisible = false
                this._getTableData()

                // 向后台传递日志数据
                let message = {
                  sourceId: this.projectId, // 资源ID
                  sourceCode: this.sourceCode, // 资源编号
                  businessTypeId: this.$DICT_CODE.LOG.BUSINESS_TYPE.NEW_HOUSE, // 业务类型
                }
                let request = null

                if (this.currentRowData) {
                  params.id = this.currentRowData.id
                  message = Object.assign({}, {
                    newData: params,
                    originalData: this.originalData,
                    labelData: this.$utils.getFormFields(this.$refs['form']),
                    sourceTypeId:this.$DICT_CODE.LOG.BUSINESS_SOURCE_TYPE.NEW_DEAL,//资源类型
                    operatTypeId: 325
                  }, message)
                  request = this.$updateLog.newHouse.houseUpdateLog
                } else {
                  message = Object.assign({}, {
                    logContent: `开盘时间：${this.form.openDate}, 开盘楼栋：${params.buildings}, 备注：${this.form.remark}`,
                    sourceTypeId:this.$DICT_CODE.LOG.BUSINESS_SOURCE_TYPE.NEW_DEAL,//资源类型
                    operatTypeId: 324
                  }, message)
                  request = this.$updateLog.newHouse.houseQueryLog
                }
                request({message: JSON.stringify(message)})
              })
            })
          }
        })
      },

      handleEdit (row) {
        this._resetForm('form')

        this.currentRowData = Object.assign({}, row)
        this.dialogVisible = true

        this.$nextTick(() => {
          this.setFormInfo()
        })
      },

      setFormInfo () {
        let data = this.currentRowData
        let form = this.form
        let dateStr = ''

        Object.keys(form).forEach(key => {
          if (data[key] !== 'undefined' && data[key] !== null) {

            form[key] = data[key]

            if (key === 'buildingsIds') {
              form[key] = data[key].split(',').map(item => Number(item))
            }

            if (key === 'openDate') {
              dateStr = this.$utils.timestampToTime(data[key])
            }

          }
        })
        form.openDate = dateStr

        this.originalData = Object.assign({}, form)
        this.originalData.buildingsIds = this.originalData.buildingsIds.join(',')
      },

      handleDelete (row) {
        this.$confirm('确定删除该条数据吗?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          delPropertyOpenDateById({id: row.id}).then(res => {
            this.$message({
              type: 'success',
              message: res.msg
            })
            this._getTableData()

            // 向后台传递日志数据
            let message = {
              sourceId: this.projectId, // 资源ID
              sourceCode: this.sourceCode, // 资源编号
              businessTypeId: this.$DICT_CODE.LOG.BUSINESS_TYPE.NEW_HOUSE, // 业务类型
              sourceTypeId:this.$DICT_CODE.LOG.BUSINESS_SOURCE_TYPE.NEW_DEAL,//资源类型
              operatTypeId: 326, // 操作类型
              logContent: `删除开盘时间 => ：${row.remark}`
            }
            this.$updateLog.newHouse.houseQueryLog({message: JSON.stringify(message)})
          })
        })
      },

      _getTableData () {
        this.loadingView = true
        const params = this._getParams({
          projectId: this.projectId,
        })
        queryPropertyOpenDateByProjectId(params).then(res => {
          this.tableData = res.data
          this.listQuery.total = res.count
          this.loadingView = false
        }).catch(() => {
          this.loadingView = false
        })
      }
    }
  }
</script>

<style scoped lang="scss">

</style>
