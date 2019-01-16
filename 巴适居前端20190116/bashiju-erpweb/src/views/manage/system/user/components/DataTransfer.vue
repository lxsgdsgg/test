<template>
  <div>
    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        prop="type"
        label="资源类型">
      </el-table-column>
      <el-table-column
        prop="num"
        label="姓名">
      </el-table-column>
      <el-table-column
        label="转维护人">
        <template slot-scope="scope">
          <el-input v-model="scope.row.user"></el-input>
        </template>
      </el-table-column>

      <el-table-column
        label="进淘宝池"
        align="center"
      >
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.data1"
            active-color="#13ce66">
          </el-switch>
        </template>
      </el-table-column>

      <el-table-column
        label="转公盘"
        align="center"
      >
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.data2"
            active-color="#13ce66">
          </el-switch>
        </template>
      </el-table-column>
    </el-table>

    <div class="btn-group">
      <el-button type="primary" @click="handleSubmit">确认</el-button>
      <el-button @click="handleCancel">取消</el-button>
    </div>

  </div>
</template>

<script>
export default {
  name: 'UserDataTransfer',
  data () {
    return {
      tableData: [
        {
          type: '客源求购',
          num: 11,
          data1: false,
          data2: false
        },
        {
          type: '客源求租',
          num: 4,
          data1: false,
          data2: false
        }
      ]
    }
  },
  methods: {
    handleCancel () {
      this.$emit('handleClick', 2)
    },
    handleSubmit () {
      this.$emit('handleClick', 1)
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
