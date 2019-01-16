<template>
  <div>
    <el-select :multiple="multiple" :size="size" :clearable="clearable" :filterable="filterable" @change="handleChange" v-model="val" placehelder="--请选择--" style="width: 100%;">
      <el-option
        v-for="item in options"
        :key="item[props.value]"
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
