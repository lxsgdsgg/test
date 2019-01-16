<template>
  <div>
    <el-select :clearable="clearable" :size="size" :filterable="filterable"  @change="handleChange" v-model="deptTypeId" placehelder="--请选择--" label-width="100px;">
      <el-option
        v-for="item in deptTypeOps"
        :key="item.name"
        :label="item.name"
        :value="item.id"
      >
      </el-option>
    </el-select>
  </div>
</template>

<script>
import {queryDeptType} from '@/request/manage/deptmanage'

export default {
  name: 'deptTypeSelect',
  props: {
    filterable: {
      type: Boolean,
      default: true
    },
    clearable: {
      type: Boolean,
      default: true
    },
    size: {
      type: String
    },
    value: {
      type: [String, Number],
      default: ''
    }
  },
  model: {
    prop: 'value',
    event: 'change'
  },
  data () {
    return {
      deptTypeId: this.value,
      deptTypeOps: []
    }
  },
  methods: {
    // 获取部门类型信息
    getDeptTypeSelect: function () {
      let params = Object.assign({}, null, {})
      queryDeptType(params).then((res) => {
        this.deptTypeOps = res
      }).catch(err => {
        console.log(err)
      })
    },
    handleChange (val) {
      this.$emit('change', val)
    }
  },
  mounted () {
    this.getDeptTypeSelect()
  }
}
</script>

<style scoped>
</style>
