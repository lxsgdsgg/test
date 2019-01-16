<template>
  <el-cascader
    style="width: 100%"
    :expand-trigger="expandTrigger"
    filterable
    :options="options"
    clearable
    @change="handleChange"
    :change-on-select="changeOnSelect"
    :show-all-levels="showAllLevels"
    :props="props"
    :disabled="disabled"
    v-model="cascaderVal"
    placeholder="选择部门"
  ></el-cascader>
</template>

<script>
import mixin from './mixin' // 混入对象
export default {
  name: 'BaseCascaderDept',
  mixins: [mixin],
  props: {
    dataProps: {
      type: Object,
      /**
       *  源数据配置选项 用于将具有父子关系的原始数据格式化成树形结构数据
       *  id 指定选项的值 为源数据的id
       *  parent 指定选项的值 为源数据的parentKey
       */
      default () {
        return {id: 'code', parent: 'parentCode'}
      }
    }
  },
  data () {
    return {
      url: 'manage/commonselect/queryOpenCityCompanyDepart',
      props: {
        value: 'code', // 指定选项的值为选项对象的某个属性值
        children: 'children', // 指定选项的子选项为选项对象的某个属性值
        label: 'name' // 指定选项标签为选项对象的某个属性值
      }
    }
  }
}
</script>

<style scoped lang="scss">
</style>
