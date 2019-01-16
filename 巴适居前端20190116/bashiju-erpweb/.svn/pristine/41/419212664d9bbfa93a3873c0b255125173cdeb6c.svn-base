<!--
  动态table 自定义列表
  固定表头, 按照表头顺序排序
-->

<template>
  <div class="wrapper">
    <div class="operates clearfix">

      <slot></slot>

      <el-button size="mini" class="pull-right" @click="dialogVisible = true">自定义列表</el-button>
    </div>
    <el-table
      @selection-change="handleSelectionChange"
      :height="height"
      :data="tableData" :key="key" border fit highlight-current-row style="width: 100%"
      :size="size"
    >

      <el-table-column
        v-if="showCheckBox"
        type="selection"
        width="55"
        :fixed="fixed"
      >
      </el-table-column>

      <el-table-column
        v-if="showIndex "
        :fixed="fixed"
        type="index"
        width="55">
      </el-table-column>

      <el-table-column v-for="(fruit, index) in _formThead" :key="index" :label="fruit.label && fruit.label" :align="fruit.align && fruit.align" :width="fruit.width && fruit.width">
        <template slot-scope="scope">
          {{ scope.row[fruit.prop] || scope.row[fruit.prop] }}
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :visible.sync="dialogVisible" title="自定义列表" width="600px">
      <el-checkbox-group v-model="checkboxVal">
        <el-checkbox style="margin-left: 0; margin-right: 20px; margin-bottom: 20px" v-for="(opt, index) in formTheadOptions" :key="index" :label="opt">{{opt}}</el-checkbox>
      </el-checkbox-group>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    props: {
      defaultFormThead: { // 默认表头自定义数据
        type: Array,
        default () {
          return []
        }
      },

      /**
       * 自定义表头 属性
       * prop (columns 值)
       * label (表头文本内容)
       */
      formThead: {
        type: Array,
        default () {
          return [] // { prop: 项  label：表头text align 文本显示方式 width 宽度}
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
      formTheadOptions: { // 自定义table 选项集合
        type: Array,
        default () {
          return []
        },
        required: true
      },
      showCheckBox: { // 是否有多选框
        type: Boolean,
        default: false
      },
      showIndex: { // 是否显示序号
        type: Boolean,
        default: false
      },
      fixed: { // 固定列 固定第一项
        type: Boolean,
        default: false
      },
      height: {
        type: [Number, String],
        default: 580
      },
      size: {
        type: String,
        default: 'medium'
      }
    },
    data () {
      return {
        key: 1, // table key
        checkboxVal: this.defaultFormThead, // checkboxVal
        dialogVisible: false,
        _formThead: []
      }
    },
    methods: {
      handleSelectionChange (val) {
        this.$emit('selectionChange', val)
      }
    },
    created () {
      this._formThead = this.formThead.concat()
    },
    watch: {
      checkboxVal(valArr) {
        let cls = []
        this.formTheadOptions.forEach(i => {
          if (valArr.indexOf(i) >= 0) {
            this.formThead.forEach(item => {
              if (item.label === i) cls.push(item)
            })
          }
        })
        this._formThead = cls
        this.key = this.key + 1// 为了保证table 每次都会重渲
      }
    }
  }
</script>

<style scoped lang="scss">
  .operates {
    padding: 5px;
    border: 1px solid #ebeef5;
    border-bottom: none;
  }
</style>
