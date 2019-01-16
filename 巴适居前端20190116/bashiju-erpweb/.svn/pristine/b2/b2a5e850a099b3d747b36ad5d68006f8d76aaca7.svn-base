<template>
  <el-form v-loading="loadingView" :model="form" :rules="rules" ref="form" label-width="100px">
    <div class="hd">
      <h3>基础描述</h3>
      <el-form-item label="组合名称" prop="name">
        <el-input v-model="form.name" placeholder="数据组合名称" style="width: 60%"></el-input>
        <el-tag class="ml10" size="mini" closable>尽可能不超过4个字符</el-tag>
      </el-form-item>

      <el-form-item label="关联按钮" prop="relationBtns" ref="relationBtns">
        <el-select v-model="form.relationBtns" multiple placeholder="请选择" style="width: 100%">
          <el-option
            v-for="item in options"
            :key="item.id"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="备注">
        <el-input type="textarea" v-model="form.remark"></el-input>
      </el-form-item>
    </div>

    <div class="bd">
      <h3>
        <span>组合条件</span>
        <el-button class="ml10" @click="handleAddTableRow" size="mini" type="primary" plain icon="el-icon-plus">新增</el-button>
      </h3>

      <el-table
        class="table"
        :data="tableData"
        height="207px"
        style="width: 100%"
      >
        <el-table-column
          label="名称"
          align="center"
        >

          <template slot-scope="scope">
            <template v-if="scope.row.edit">
              <el-input class="edit-input" size="mini" v-model="scope.row.fieldName"></el-input>
            </template>

            <span v-else>{{ scope.row.fieldName }}</span>
          </template>

        </el-table-column>

        <el-table-column
          align="center"
          label="值">
          <template slot-scope="scope">
            <template v-if="scope.row.edit">
              <el-input class="edit-input" size="mini" v-model="scope.row.val"></el-input>
            </template>

            <span v-else>{{ scope.row.val }}</span>
          </template>
        </el-table-column>

        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button v-if="scope.row.edit" type="success" @click="handleConfirmEdit(scope.row)" size="mini" icon="el-icon-circle-check-outline">Ok</el-button>
            <el-button v-else type="primary" @click='scope.row.edit=!scope.row.edit' size="mini" icon="el-icon-edit">编辑</el-button>

            <el-button size="mini" plain icon="el-icon-delete" @click="handleDelete(scope.$index, scope.row)"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="btn-group">
      <el-button type="primary" @click="handleSubmit" :loading="loadingBtn">确认</el-button>
      <el-button @click="handleCancel">取消</el-button>
    </div>
  </el-form>
</template>

<script>
import {getCombinationDetail, saveOrUpdateCombination} from '@/request/manage'
export default {
  props: {
    isAdd: {
      type: Boolean
    },
    params: {
      type: Object
    }
  },
  data () {
    return {
      form: {
        name: '',
        relationBtns: '',
        remark: ''
      },
      rules: {
        name: [
          {required: true, message: '名称不能为空', trigger: 'blur'}
        ]
      },
      options: [],
      tableData: [],
      loadingBtn: false,
      loadingView: false,
      originalFormData: {}
    }
  },
  methods: {
    handleSubmit () {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          this.$confirm('确定保存当前编辑的窗口吗?', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            if (!this.form.relationBtns.length) {
              this.$message({
                type: 'warning',
                showClose: true,
                message: '关联按钮不能为空!'
              })
              return
            }

            if (!this.tableData.length) {
              this.$message({
                type: 'warning',
                showClose: true,
                message: '组合条件不能为空!'
              })
              return
            }

            let params = this._FormatParams(this.form, this.tableData)

            this.loadingBtn = true
            saveOrUpdateCombination(params).then(res => {

              this.loadingBtn = false
              this.$message({
                type: 'success',
                message: res.msg || '操作成功'
              })
              this.$emit('handleClick', 1)

              // 向后台传递日志数据
              if (this.isAdd) {

                let message = {
                  sourceCode: this.params.menuName, // 资源标识
                  sourceTypeId: this.$DICT_CODE.LOG.SOURCE_TYPE.SYSTEM.MENU, // 资源类型
                  operatTypeId: this.$DICT_CODE.LOG.OPERATE_TYPE.ADD, // 操作类型
                  logContent: `${this.params.menuName}下新增数据权限条件组合：${params.name}` // 日志内容
                }
                this.$updateLog.system.systemAddLog({message: JSON.stringify(message)})

              } else {

                let message = {
                  sourceCode: this.params.menuName, // 资源标识
                  sourceTypeId: this.$DICT_CODE.LOG.SOURCE_TYPE.SYSTEM.MENU, // 资源类型
                  operatTypeId: this.$DICT_CODE.LOG.OPERATE_TYPE.UPDATE, // 操作类型
                  labelData: this.$utils.getFormFields(this.$refs['form']), // 修改字段对应的label
                  originalData: this.originalFormData, // 原始表单对象
                  newData: params // 修改后的表单对象
                }
                this.$updateLog.system.systemUpdateLog({message: JSON.stringify(message)})

              }


            }).catch(() => {
              this.loadingBtn = false
            })
          })
        }
      })
    },
    handleCancel () {
      this.$confirm('确定退出当前编辑的窗口吗?', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$emit('handleClick', 2)
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消!'
        })
      })
    },
    handleAddTableRow () {
      this.tableData.push({
        fieldName: '',
        val: '',
        edit: true
      })
    },
    handleConfirmEdit (row) {
      row.edit = false

      this.$message({
        message: '编辑成功!',
        type: 'success',
        showClose: true,
        duration: 2000
      })
    },
    handleDelete (index, row) {
      if (row.id || row.fieldName || row.val) {
        this.$confirm('删除该行后将在保存后生效!', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.tableData.splice(index, 1)
        })
      } else {
        this.tableData.splice(index, 1)
      }
    },
    _reset () {
      this.$refs['form'] && this.$refs['form'].resetFields()
      this.tableData = []
    },
    _getData () {
      this.loadingView = true
      let params = {}

      if (!this.isAdd) {
        params = {
          menuId: this.params.menuId,
          combinationId: this.params.combinationId
        }
      } else {
        params = {
          menuId: this.params.menuId
        }
      }

      getCombinationDetail(params).then(res => {
        this._setData(res)
        this.loadingView = false
      }).catch(() => {
        this.loadingView = false
      })
    },
    _setData (data) {
      let btns = data['btns'] || []
      let details = data['details'] || []
      // 组合条件table
      this.tableData = details.map(item => {
        item.edit = false
        return item
      }) || []

      // 关联按钮下拉框
      this.options = btns || []

      const combinationData = data['combination'] || {}

      if (combinationData) {
        // 基础描述表单
        for (let i in this.form) {
          if (combinationData[i]) {
            this.form[i] = combinationData[i]
            if (combinationData['relationBtn']) {
              this.form['relationBtns'] = combinationData['relationBtn'].split(',').map(item => Number(item))
            }
          }
        }
      }

      this.originalFormData = this._FormatParams(this.form, this.tableData)
    },
    _FormatParams (form, tableData) {
      let params = Object.assign({}, form)
      params.relationBtns = params.relationBtns.join()
      let groupInfo = tableData.map(item => {
        return {
          fieldName: item.fieldName,
          val: item.val
        }
      })

      params.groupInfo = JSON.stringify(groupInfo)

      params.id = this.params.combinationId || ''
      params.menuId = this.params.menuId
      return params
    }
  },
  mounted () {
    if (!this.isAdd && this.params) {
      this._reset()
      this._getData()
    } else {
      this._reset()
      this._getData()
    }
  }
}
</script>

<style scoped lang="scss">
  .bd {
    .inline {
      display: inline-block;
    }
  }

  h3 {
    padding-bottom: 20px;
    font-size: 16px;
    text-align: center;
  }
</style>
