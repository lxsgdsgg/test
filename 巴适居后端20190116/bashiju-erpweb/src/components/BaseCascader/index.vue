<template>
  <el-cascader
    style="width: 100%"
    filterable
    :options="options"
    clearable
    @change="handleChange($event)"
    :change-on-select="changeOnSelect"
    :show-all-levels="showAllLevels"
    :props="props"
    v-model="cascaderVal"
  ></el-cascader>
</template>

<script>
import mixin from './mixin' // 混入对象
const cascaderProps = {
  value: 'id', // 指定选项的值为选项对象的某个属性值
  children: 'children', // 指定选项的子选项为选项对象的某个属性值
  label: 'name' // 指定选项标签为选项对象的某个属性值
}
export default {
  name: 'BaseCascaderDept',
  mixins: [mixin],
  props: {
    url: {
      type: String,
      required: true
    },
    props: {
      type: Object,
      default: cascaderProps
    }
  }
}
</script>

<style scoped lang="scss">
</style>
