<!-- 房源跟进记录 -->
<template>
  <div style="min-height: 550px">
    <el-table
      size="small"
      :data="tableData"
      v-loading="loadingView"
    >
      <el-table-column
        prop="followTime"
        :formatter="_timeFormat"
        label="跟进时间">
      </el-table-column>
      <el-table-column
        prop="followTypeName"
        label="跟进方式">
      </el-table-column>
      <el-table-column
        prop="follower"
        label="跟进人">
      </el-table-column>
      <el-table-column
        prop="deptname"
        label="所在部门">
      </el-table-column>
      <el-table-column
        prop="content"
        label="跟进内容"
        show-overflow-tooltip
      >
        <template slot-scope="scope">
          <template v-if="scope.row.followType !== 90000000">
            {{scope.row.content}}
          </template>

          <template v-else>
            {{scope.row.content.split(':')[0]}}
            &nbsp;
            <icon
              v-if="playShow(scope.row.content)"
              @click.native="playSound(scope.row.content)"
              class="play-icon" name="play" width="20" height="20" scale="20">
            </icon>
          </template>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        width="100"
      >
        <template slot-scope="scope">
          <el-button @click="handleDelete(scope.row)" icon="el-icon-delete" class="mr10" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <b-pagination
      :listQuery="listQuery"
      @handleSizeChange="handleSizeChange"
      @handleCurrentChange="handleCurrentChange">
    </b-pagination>

    <audio ref="soundEl" style="display:none; height: 0" preload="auto"></audio>

  </div>
</template>

<script>
  import {getFollowRecord, delHouseFollow} from '@/request/house/houseUsed'

  export default {
    props: {
      houseId: {
        required: true
      },

      logMessage: {
        type: Object,
        required: true,
        default () {
          return {}
        }
      }
    },

    data () {
      return {
        tableData: [],
        listQuery: {
          page: 1,
          limit: 10,
          total: 0
        },
        loadingView: false
      }
    },

    methods: {
      handleDelete (row) {
        this.$confirm('确定删除该条数据吗?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          delHouseFollow({id: row.id}).then(res => {
            this.$message({
              type: 'success',
              message: res.msg
            })
            this._getTableData()

            // 向后台传递日志数据
            let message = Object.assign({}, this.logMessage, {
              operatTypeId: this.$DICT_CODE.LOG.BUSINESS_OPERATE_TYPE.DEL_FOLLOW, // 操作类型
              logContent: `删除了${row.follower}写的跟进` // 日志内容
            })
            this.$updateLog.house.houseQueryLog({message: JSON.stringify(message)})
          })
        })
      },

      /**
       * 分页 pageSize 改变时会触发
       */
      handleSizeChange (val) {
        this.listQuery.limit = val
        this._getTableData()
      },

      /**
       * 分页 currentPage  改变时会触发
       */
      handleCurrentChange (val) {
        this.listQuery.page = val
        this._getTableData()
      },

      playShow (data) {
        let index = data.indexOf(':')
        let url

        if (index !== -1) url = data.substring(index + 1)

        return !!url
      },

      playSound (data) {
        let index = data.indexOf(':')
        let url

        if (index !== -1) url = data.substring(index + 1)

        if (url) {
          let el = this.$refs['soundEl']

          if (el.paused) {
            el.src = url
            el.play()
          } else {
            el.pause()
          }

        }

      },

      _getTableData () {
        this.loadingView = true
        const params = {
          shhId: this.houseId,
          limit: this.listQuery.limit,
          page: this.listQuery.page
        }
        getFollowRecord(params).then(res => {
          this.tableData = res.data
          this.listQuery.total = res.count
          this.loadingView = false
        }).catch(() => {
          this.loadingView = false
        })
      },

      // 格式化日期
      _timeFormat (row, column, cellValue) {
        return this.$utils.timeFormat(cellValue, '{y}-{m}-{d} {h}:{i}:{s}')
      }
    },
  }
</script>

<style scoped lang="scss">
  .play-icon {
    vertical-align: middle; cursor: pointer;transition: all 0.3s;

    &:hover {
      color: #e41e2b;
    }
  }
</style>
