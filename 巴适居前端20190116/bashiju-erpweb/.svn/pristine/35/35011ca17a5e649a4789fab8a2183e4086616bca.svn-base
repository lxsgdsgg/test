<template>
  <div v-loading="loadingView">
    <h1>
      <span>过户流程</span>

      <el-dropdown class="ml10" @command="handleCommand" trigger="click">
        <el-button size="mini" type="primary">
          新增流程<i class="el-icon-arrow-down el-icon--right"></i>
        </el-button>
        <el-dropdown-menu size="mini" slot="dropdown">
          <template v-for="item in options">

            <el-dropdown-item :disabled="item.disabled" :command="{index: item.index, name: item.name}">{{item.name}}</el-dropdown-item>

          </template>
        </el-dropdown-menu>
      </el-dropdown>

    </h1>

    <div class="steps-wrapper">
      <div class="steps" id="TransProcCfgSteps">
          <el-steps direction="vertical">
            <el-step class="step" @click.native="handleClickStep(item, index)" v-for="(item, index) in steps" :key="item.detail.id" :title="item.detail.detailName"></el-step>
          </el-steps>
      </div>
    </div>

    <div class="btn-group" v-if="steps.length && steps.length > 0">
      <el-button type="primary" @click="handleSubmit" :loading="loadingBtn">确认</el-button>
      <el-button @click="$emit('closeDialog')">取消</el-button>
    </div>

    <template>
      <el-dialog title="流程节点设置" width="350px" :visible.sync="dialogStepEditVisible" :modal-append-to-body="false" append-to-body>
        <div class="step-edit">

          <h3><el-tag type="info">{{currentStepData && currentStepData.index + ' . ' + currentStepData.detail.detailName}}</el-tag></h3>

          <el-dropdown class="mr15" @command="handleEditStep" trigger="click">
            <el-button size="mini" type="primary">
              修改流程<i class="el-icon-arrow-down el-icon--right"></i>
            </el-button>
            <el-dropdown-menu size="mini" slot="dropdown">
              <template v-for="item in options">

                <el-dropdown-item :disabled="item.disabled" :command="{index: item.index, name: item.name}">{{item.name}}</el-dropdown-item>

              </template>
            </el-dropdown-menu>
          </el-dropdown>

          <el-button @click="handleDeleteStep" size="mini" type="primary" plain icon="el-icon-delete">删除</el-button>
        </div>

      </el-dialog>
    </template>

  </div>
</template>

<script>
  import * as RequestURL from '@/request/manage/transferProcess' // 请求后端URL路径
  export default {
    name: 'TransProcConfig',
    props: {
      params: {
        type: Object,
        required: true
      }
    },
    data () {
      return {
        options: [],
        loadingBtn: false,
        loadingView: false,
        dialogStepEditVisible: false,
        steps: [],
        originalSteps: [],
        step: null,
        currentStepData: null
      }
    },
    methods: {

      handleSubmit () {
        this.$confirm('确定保存编辑的信息吗?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.loadingBtn = true
          const _params = this._formatParmas(this.steps)

          if (!_params) {
            this.loadingBtn = false
            this.$message({
              type: 'warning',
              message: '没有要保存的数据'
            })
            return
          }

          const params = {
            jsonData: JSON.stringify(_params)
          }

          RequestURL.saveOrUpdateTransferProcDetail(params).then(res => {
            this.loadingBtn = false

            this.$message({
              type: 'success',
              message: res.msg || '操作成功'
            })

            this.$emit('closeDialog', 1)

            // 向后台传递日志
            let originalList =[]
            let newList = []
            this.originalSteps.forEach(item => {
              originalList.push(item.detail.detailName)
            })
            this.steps.forEach(item => {
              newList.push(item.detail.detailName)
            })

            let message = {
              sourceCode: this.params.name, // 资源标识
              sourceTypeId: this.$DICT_CODE.LOG.SOURCE_TYPE.MANAGE.TRANSFER_PROCESS, // 资源类型
              operatTypeId: this.$DICT_CODE.LOG.OPERATE_TYPE.TRANSFER_PROCESS_CONFIG, // 操作类型
              logContent: `方案：${this.params.name}(过户流程) -> 由【${originalList.join()}】设置为【${newList.join()}】` // 日志内容
            }
            this.$updateLog.manage.manageQueryLog({message: JSON.stringify(message)})

          }).catch(() => {
            this.loadingBtn = false
          })
        })

      },

      handleReset () {
        this.$confirm('确定重置当前表单吗?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.steps = this.originalSteps.concat()
        })
      },

      // 触发流程下拉框  新增
      handleCommand (command) {
        const index = command.index
        this.steps.push({
          index: index,
          detail: {
            detailName: command.name
          }
        })
        this._setStepsElHeight()
      },

      // 点击流程节点
      handleClickStep (stepData, index) {
        this.currentStepData = {...stepData}
        this.currentStepData.$index = index
        this.dialogStepEditVisible = true
      },

      // 删除流程节点
      handleDeleteStep () {
        this.$confirm('删除该行数据将在保存时生效?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          const index = this.currentStepData.$index
          console.log(index)
          this.steps.splice(index, 1)
          this.dialogStepEditVisible = false
          this._setStepsElHeight()
        })
      },

      // 删除流程节点 修改流程节点
      handleEditStep (command) {
        const index = this.currentStepData.$index

        // 修改后的数据
        const editItem = {
          index: command.index,
          detail: {
            detailName: command.name
          }
        }

        this.$set(this.steps, index, editItem)
        this.dialogStepEditVisible = false
      },


      _loadData () {
        this.loadingView = true
        RequestURL.queryTransferProcSetDataList(this.params).then(res => {
          if (res.data) {
            this.originalData = this.$jsonUtils.deepClone(res.data)
            this.originalSteps = this.$utils.jsonToArr(this.originalData, 'index', 'detail')
            this.steps = this.$utils.jsonToArr(res.data, 'index', 'detail')
          }

          this._setStepsElHeight()

          // 设置下拉框禁用
          this._setOptionsDisabled()
          this.loadingView = false
        }).catch(() => {
          this.loadingView = false
        })
      },


      _loadOptions () {
        return new Promise(resolve => {
          RequestURL.queryTransferProcName().then(res => {
            this.options = this.$utils.jsonToArr(res, 'index', 'name')
            resolve()
          })
        })
      },

      // 设置流程组件容器高度和滚动
      _setStepsElHeight () {
        const stepH = 50 // 单个步骤高度
        const stepsH = stepH * this.steps.length
        const stepsEl = document.querySelectorAll('#TransProcCfgSteps')[0]
        const stepsElWrap = document.querySelectorAll('.steps-wrapper')[0]

        stepsEl.style.height = stepsH + 'px'

        // 滚动到最后一条数据
        this.$nextTick(() => {
          stepsElWrap.scrollTop = stepsH
        })

      },

      // 设置下拉框节点是否可选 Disabled属性
      _setOptionsDisabled () {
        this.options.forEach(item => item.disabled = false)

        this.steps.forEach(item => {
          this.options.forEach(opt => {
            if (opt.name === item.detail.detailName) {
              opt.disabled = true
            }
          })
        })
      },

      _formatParmas (data) {
        let params = {}
        let map = {}

        data.forEach((item, index) => {
          if (item.detail.detailName !== '') {
            map[index + 1] = String(item.index)
          }
        })

        if (!map) {
          return null
        }

        map.detail = this.originalData || ''
        params = Object.assign({}, map, {
          procId: this.params.procId,
          confType: this.params.confType
        })
        return params
      },
    },
    mounted () {
      this._loadOptions().then(() => {
        this._loadData()
      })
    },

    watch: {
      steps () {
        this._setOptionsDisabled()
      }
    },
  }
</script>

<style scoped lang="scss">
  h1 {
    padding-bottom: 10px;
    font-weight: 600;
    font-size: 16px;
    text-align: center;

    .btn {
      font-size: 12px;
    }
  }

  .steps-wrapper {
    max-height: 400px;
    height: 320px;
    overflow-y: auto;

  }

  .steps {
    padding: 0 10px;
    margin-top: 10px;

    .step {
      cursor: pointer;

    }
  }

  .step-edit {
    text-align: center;

    h3 {
      padding-bottom: 20px;
      font-weight: 600;
      font-size: 16px;
      text-align: center;
    }
  }
</style>
