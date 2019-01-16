<!-- 房源动态 -->
<template>
  <div style="min-height: 550px">
    <el-button v-if="btnPermission[PERMISSION_BTN.ADD_DYNAMIC]" @click="handleAdd" type="primary" plain icon="el-icon-circle-plus" size="small">新增</el-button>

    <el-table
      size="small"
      :data="tableData"
      v-loading="loadingView"
    >
      <el-table-column
        prop="title"
        label="标题">
      </el-table-column>
      <el-table-column
        prop="operator"
        label="操作人">
      </el-table-column>
      <el-table-column
        prop="content"
        label="资讯内容"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column
        prop="addTime"
        :formatter="_timeFormat"
        label="新增时间">
      </el-table-column>
      <el-table-column
        label="操作"
        width="200"
      >
        <template slot-scope="scope">
          <el-button v-if="btnPermission[PERMISSION_BTN.UPDATE_DYNAMIC]" @click="handleEdit(scope.row)" icon="el-icon-edit" size="mini">编辑</el-button>
          <el-button v-if="btnPermission[PERMISSION_BTN.DEL_DYNAMIC]" @click="handleDelete(scope.row)" icon="el-icon-delete" size="mini">删除</el-button>
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

        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title"></el-input>
        </el-form-item>

        <el-form-item label="资讯内容" prop="content">
          <el-input style="width: 100%" type="textarea" v-model="form.content"></el-input>
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
  import {queryPropertyDynamicByProjectId, saveOrUpdatePropertyDynamic, delPropertyDynamic} from '@/request/house/houseNew'

  export default {
    mixins: [PageList, constMixin],

    props: {
      projectId: {
        type: [Number, String]
      },
    },

    data () {
      return {
        dialogVisible: false,
        currentRowData: null,
        isAdd: true,
        form: {
          title: '',
          content: ''
        },
        rules: {
          title: [
            { required: true, message: '必填项', trigger: 'blur' }
          ],
          content: [
            { required: true, message: '必填项', trigger: 'blur' }
          ]
        }
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
              params.projectId = this.projectId

              if (this.currentRowData) {
                params.id = this.currentRowData.id
              }

              saveOrUpdatePropertyDynamic({jsonData: JSON.stringify(params)}).then(() => {
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
                  message = Object.assign({}, {
                    newData: params,
                    originalData: this.currentRowData,
                    labelData: this.$utils.getFormFields(this.$refs['form']),
                    sourceTypeId:this.$DICT_CODE.LOG.BUSINESS_SOURCE_TYPE.NEW_DEAL, // 资源类型
                    operatTypeId: 330
                  }, message)
                  request = this.$updateLog.newHouse.houseUpdateLog
                } else {
                  message = Object.assign({}, {
                    logContent: `标题：${this.form.title}, 资讯内容：${this.form.content}`,
                    sourceTypeId: this.$DICT_CODE.LOG.BUSINESS_SOURCE_TYPE.NEW_DEAL, // 资源类型
                    operatTypeId: 304
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

        Object.keys(form).forEach(key => {
          if (data[key] !== 'undefined' && data[key] !== null) {

            form[key] = data[key]

          }
        })
      },

      handleDelete (row) {
        this.$confirm('确定删除该条数据吗?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          delPropertyDynamic({id: row.id}).then(res => {
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
              operatTypeId: 331, // 操作类型
              logContent: `删除项目动态 => ${row.title}`
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
        queryPropertyDynamicByProjectId(params).then(res => {
          this.tableData = res.data
          this.listQuery.total = res.count
          this.loadingView = false
        }).catch(() => {
          this.loadingView = false
        })
      }
    },
  }
</script>

<style scoped lang="scss">

</style>
