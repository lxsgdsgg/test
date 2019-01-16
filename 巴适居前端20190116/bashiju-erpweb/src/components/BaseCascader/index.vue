<template>
  <el-cascader
    v-if="!multiple"
    style="width: 100%"
    filterable
    :options="options"
    clearable
    @change="handleChange($event)"
    :change-on-select="changeOnSelect"
    :show-all-levels="showAllLevels"
    :props="props"
    v-model="cascaderVal"
    :placeholder="placeholder"
    :disabled="disabled"
  ></el-cascader>

  <div v-else class="multiple-cascader">
    <el-cascader
      ref="cascader"
      :props="props"
      :options="options"
      :show-all-levels="false"
      change-on-select
      @change="handleMultipleChange($event)"
      clearable
      filterable
      v-model="cascaderVal"
      class="cascader"
      placeholder=""
    ></el-cascader>

    <div @click="tagsClick" class="tags">
      <el-tag
        class="tag"
        v-for="(tag, index) in tags"
        :key="index"
        size="mini"
        closable
        @close="handleTagClose(tag)"
      >
        {{tag.label}}
      </el-tag>
    </div>

  </div>

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
      type: String
    },
    props: {
      type: Object,
      default () {
        return cascaderProps
      }
    }
  }
}
</script>

<style scoped lang="scss">
  .multiple-cascader {
    position: relative;
    width: 100%;

    .cascader {
      width: 100%;
      height: 100%;
    }

    .tags {
      position: absolute;
      top: 10px;
      left: 5px;
      height: 20px;
      width: 92%;
      overflow-y: auto;
      z-index: 2;
      background: #fff;
      cursor: pointer;


      .tag {
        margin-right: 3px;
      }
    }
  }
</style>
