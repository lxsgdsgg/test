<template>
  <div class="dataRow">
    <span v-if="date.tag=='beginTime'">自</span>
    <span v-if="date.tag=='endTime'">至</span>
    <el-date-picker v-model="dateForm" :clearable="false" type="year"></el-date-picker>
    <span>年</span>
    <el-date-picker v-model="dateForm" :clearable="false" type="month" format="MM"></el-date-picker>
    <span>月</span>
    <el-date-picker v-model="dateForm" :clearable="false" type="date" format="dd"></el-date-picker>
    <span>日</span>
    <span v-if="date.tag=='endTime'">止</span>
  </div>
</template>

<script>
export default{
  data () {
    return {
      dateForm: ''
    }
  },
  mounted () {
    //this.dateForm = this.date
  },
  props: ['date']
 }
</script>

<style lang="scss" scoped>

</style>
