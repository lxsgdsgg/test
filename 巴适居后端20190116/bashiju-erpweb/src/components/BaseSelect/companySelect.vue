<template>
  <div>
    <el-select :clearable="clearable" :size="size" :filterable="filterable" @change="handleChange" v-model="companyId" placehelder="--请选择--" style="width: 100%;">
      <el-option
        v-for="item in companyOpts"
        :key="item.name"
        :label="item.name"
        :value="item.id"
      >
      </el-option>
    </el-select>
  </div>
</template>

<script>
import {queryCompanySelectData} from '@/request/manage/deptmanage'
export default {
  name: 'companySelect',
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
      companyId: this.value,
      companyOpts: []
    }
  },
  methods: {
    /**
     * 获取公司下拉选择数据
     */
    getCompanyDataSelect: function () {
      let params = {}
      queryCompanySelectData(params).then((res) => {
        this.companyOpts = res
      }).catch(err => {
        console.log(err)
      })
    },
    handleChange (val) {
      this.$emit('change', val)
    }
  },
  mounted () {
    this.getCompanyDataSelect()
  }
}
</script>

<style scoped>
</style>
