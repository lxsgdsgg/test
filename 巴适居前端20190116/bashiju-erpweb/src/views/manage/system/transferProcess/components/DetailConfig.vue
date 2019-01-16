<template>
  <div class="wrapper" v-loading="loadingView">
    <h1>
      <span>明细名称</span>
      <el-button @click="handleAdd" class="ml10 btn" size="mini" type="primary" plain icon="el-icon-plus">新增</el-button>
    </h1>

    <div class="form-wrapper">
      <el-row v-if="list" class="item" :gutter="20">
        <el-col class="input-item" :span="8" v-for="item in list" :key="item.id">
          <el-input
            size="small"
            v-model="item.detail['detailName']"
            suffix-icon="el-icon-document" clearable>
          </el-input>
        </el-col>
      </el-row>

      <div class="empty" v-else>
        <i class="el-icon-warning mr5"></i>

        暂无数据，请添加
      </div>
    </div>

    <div class="btn-group" v-if="list.length && list.length > 0">
      <el-button type="primary" @click="handleSubmit" :loading="loadingBtn">确认</el-button>
      <el-button @click="$emit('closeDialog')">取消</el-button>
    </div>
  </div>
</template>

<script>
  import * as RequestURL from '@/request/manage/transferProcess' // 请求后端URL路径
  export default {
    name: 'DetailConfig',
    props: {
      params: {
        type: Object,
        required: true
      }
    },
    data () {
      return {
        list: [],
        originalData: {},
        loadingBtn: false,
        loadingView: false
      }
    },
    methods: {
      handleAdd () {
        const index = this.list.length + 1
        this.list.push({
          index: index,
          detail: {
            detailName: ''
          }
        })
      },

      handleSubmit () {
        this.$confirm('确定保存编辑的信息吗?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.loadingBtn = true
          const _params = this._formatParmas(this.list)

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
            Object.keys(this.originalData).forEach(key => {
              originalList.push(this.originalData[key].detailName)
            })
            this.list.forEach(item => {
              newList.push(item.detail.detailName)
            })

            let title = ''
            if (this.params.confType === 1) {
              title = '业主材料明细配置'
            } else {
              title = '客户材料明细配置'
            }

            let message = {
              sourceCode: this.params.name, // 资源标识
              sourceTypeId: this.$DICT_CODE.LOG.SOURCE_TYPE.MANAGE.TRANSFER_PROCESS, // 资源类型
              operatTypeId: this.$DICT_CODE.LOG.OPERATE_TYPE.TRANSFER_PROCESS_CONFIG, // 操作类型
              logContent: `方案：${this.params.name}(${title}) -> 由【${originalList.join()}】设置为【${newList.join()}】` // 日志内容
            }
            this.$updateLog.manage.manageQueryLog({message: JSON.stringify(message)})

          }).catch(() => {
            this.loadingBtn = false
          })
        })

      },

      handleReset () {
        this.$confirm('确定重置当前已填写的表单吗?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.list.forEach(item => {
            item.detail.detailName = ''
          })
        })
      },

      _formatParmas (data) {
        let params = {}
        let map = {}

        data.forEach(item => {
          if (item.detail.detailName !== '') {
            map[item.index] = item.detail.detailName
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

      _loadData () {
        this.loadingView = true
        RequestURL.queryTransferProcSetDataList(this.params).then(res => {
          if (res.data) {
            this.originalData = this.$jsonUtils.deepClone(res.data)
            this.list = this.$utils.jsonToArr(res.data, 'index', 'detail')
          }
          this.loadingView = false
        }).catch(() => {
          this.loadingView = false
        })
      }
    },
    mounted () {
      this._loadData()
    }
  }
</script>

<style scoped lang="scss">

  .wrapper {
    position: relative;
  }

  .form-wrapper {
    padding: 0 10px;
    max-height: 400px;
    min-height: 400px;
    overflow-y: auto;
    margin-top: 10px;
  }

  .btn-group {
    /*position: absolute;*/
    /*left: 38%;*/
    /*bottom: 0;*/
    margin-top: 0;
  }

  h1 {
    padding-bottom: 10px;
    font-weight: 600;
    font-size: 16px;
    text-align: center;

    .btn {
      font-size: 12px;
    }
  }

  .empty {
    margin-top: 20px;
    margin-bottom: 10px;
    font-size: 16px;
    color: #E6A23C;
  }

  .input-item {
    margin-bottom: 15px;
  }
</style>
