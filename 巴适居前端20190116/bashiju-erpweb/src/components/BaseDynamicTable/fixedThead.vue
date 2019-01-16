<!--
  动态table 自定义列表
  固定表头, 按照表头顺序排序
-->

<template>
  <div class="wrapper">
    <div class="operates clearfix">

      <slot></slot>

      <el-button class="pull-right">默认按钮</el-button>
    </div>
    <el-table :data="tableData" :key="key" border fit highlight-current-row style="width: 100%">
      <el-table-column prop="name" label="fruitName" width="180"/>
      <el-table-column v-for="fruit in formThead" :key="fruit" :label="fruit">
        <template slot-scope="scope">
          {{ scope.row[fruit] }}
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :visible.sync="dialogVisible" title="自定义列表" width="400px">
      <div class="filter-container">
        <el-checkbox-group v-model="checkboxVal">
          <el-checkbox v-for="(opt, index) in formTheadOptions" :key="index" :label="opt.name">{{opt.name}}</el-checkbox>
        </el-checkbox-group>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  const defaultFormThead = ['apple', 'banana']

  export default {
    props: {
      defaultFormThead: { // 默认表头
        type: Array,
        default () {
          return []
        }
      },
      formThead: { // 表头
        type: Array,
        default () {
          return []
        },
        required: true
      },
      tableData: { // table数据
        type: Array,
        default () {
          return []
        },
        required: true
      },
      formTheadOptions: { // 自定义table列表选项
        type: Array,
        default () {
          return []
        },
        required: true
      }
    },
    data () {
      return {
        key: 1, // table key
        checkboxVal: defaultFormThead, // checkboxVal
        // formThead: defaultFormThead // 默认表头 Default header: value
      }
    },
    watch: {
      checkboxVal(valArr) {
        this.formThead = this.formTheadOptions.filter(i => valArr.indexOf(i) >= 0)
        this.key = this.key + 1// 为了保证table 每次都会重渲 In order to ensure the table will be re-rendered each time
      }
    }
  }
</script>

<style scoped lang="scss">

</style>
