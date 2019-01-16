<template>
  <div class="dataRow">
    <template v-for="item in datas">
      <template v-if="item.tag=='label'&&item.css!='br'" >{{item.text}}</template>
      <input v-if="item.tag=='inputText'" type="text" :class="item.css" v-model="item.value" :style="{width:item.width+'px'}">
      <YearMonthDate v-if="item.tag=='date'" :date="item"></YearMonthDate>
      <YearMonthDate v-if="item.tag=='beginTime'" :date="item"></YearMonthDate>
      <YearMonthDate v-if="item.tag=='endTime'" :date="item"></YearMonthDate>
      <RentMoney v-if="item.tag=='rentPrice'"></RentMoney>
      <Money v-if="item.tag=='money'" :describe="item.text"></Money>
      <Money v-if="item.tag=='sellPrice'" :describe="item.text"></Money>
      <Money v-if="item.tag=='commissionA'" :describe="item.text"></Money>
      <Money v-if="item.tag=='commissionB'" :describe="item.text"></Money>
      <Area v-if="item.tag=='area'"></Area>
      <Checkbox v-if="item.tag=='checkbox'" :describe="item.text"></Checkbox>
      <br v-if="item.css=='br'">
      <template v-if="item.tag=='propertyRrightNO'">
        <span>所有权证编号：</span>
        <input type="text" :class="item.css" v-model="item.value" :style="{width:item.width+'px'}">
      </template>
    </template>
  </div>
</template>

<script>
import YearMonthDate from './YearMonthDate.vue'
import RentMoney from './RentMoney.vue'
import Money from './Money.vue'
import Area from './Area.vue'
import Checkbox from './Checkbox.vue'
export default{
  data () {
    return {

    }
  },
  components: {
    YearMonthDate,RentMoney,Money,Area,Checkbox
  },
  computed: {
    getCurrDate () {
      let date = new Date();
      let seperator1 = "-";
      let year = date.getFullYear();
      let month = date.getMonth() + 1;
      let strDate = date.getDate();
      return year + seperator1 + month + seperator1 + strDate
    }
  },
  mounted () {
    console.log(this.datas)
  },
  props: ['datas']
}
</script>

<style lang="scss" scoped>

</style>
