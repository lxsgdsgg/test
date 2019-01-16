<template>
  <div>
    <el-select :collapse-tags="collapseTags" :multiple="multiple" :size="size" :clearable="clearable" :filterable="filterable" :disabled="disabled" @change="handleChange" v-model="val" :placeholder="placeholder" style="width: 100%;">
      <el-option
        v-for="(item, index) in options"
        :key="index"
        :label="item[props.label]"
        :value="item[props.value]"
      >
      </el-option>
    </el-select>
  </div>
</template>

<script>
import mixin from './mixin' // 混入对象
export default {
  name: 'BaseSelect',
  mixins: [mixin]
}
</script>

<style scoped>
</style>
