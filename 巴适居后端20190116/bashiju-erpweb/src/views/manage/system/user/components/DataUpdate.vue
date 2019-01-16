<template>
  <div class="wrapper">
    <div class="hd">
      <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox>

      <div style="margin: 15px 0;"></div>

      <el-checkbox-group v-model="checkedItems" @change="handleCheckedCitiesChange">
        <el-checkbox class="checkbox" v-for="item in updateItems" :label="item" :key="item">{{item}}</el-checkbox>
      </el-checkbox-group>

      <div class="btn-group">
        <el-button type="primary" @click="handleSubmit">确认</el-button>
        <el-button @click="handleCancel">取消</el-button>
      </div>
    </div>
    <div class="bd">
      <el-table
        border
        :data="tableData"
        height="300"
        style="width: 100%">
        <el-table-column
          prop="date"
          label="日期"
          width="180">
        </el-table-column>
        <el-table-column
          prop="name"
          label="姓名"
          width="180">
        </el-table-column>
        <el-table-column
          prop="address"
          label="地址">
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: '',
  data () {
    return {
      checkAll: false,
      checkedItems: [],
      updateItems: ['房源录入店', '房源责任店', '客源录入店', '客源责任店', '房源委托店', '房源钥匙店', '客源独家店', '客源图片店', '房源跟进店', '客源跟进店', '客源待看店', '成交店'],
      isIndeterminate: false,
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }]
    }
  },
  methods: {
    handleCheckAllChange (val) {
      this.checkedItems = val ? this.updateItems : []
      this.isIndeterminate = false
    },
    handleCheckedCitiesChange (value) {
      let checkedCount = value.length
      this.checkAll = checkedCount === this.updateItems.length
      console.log(checkedCount)
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.updateItems.length
    },
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
  .checkbox {
    margin-left: 0;
    margin-right: 15px;
    margin-bottom: 20px;
  }

  .btn-group {
    margin-top: 0;
    margin-bottom: 20px;
  }
</style>
