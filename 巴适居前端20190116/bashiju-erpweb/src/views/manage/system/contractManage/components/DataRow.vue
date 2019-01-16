<template>
  <div class="dataRow">

      <span v-if="datas.tag=='label'" :class="datas.css">{{datas.text}}</span>
<!--      <input v-if="item.formType=='checkbox'" :type="item.formType" :class="item.css" :value="item.value" >
      <input v-if="item.formType=='number'" :type="item.formType" :class="item.css" :value="item.value" >-->
      <input v-if="datas.tag=='inputText'" type="text" :class="datas.css" v-model="datas.value" :style="{width:datas.width+'px'}">
      <YearMonthDate v-if="datas.tag=='date'" :date="datas"></YearMonthDate>
      <YearMonthDate v-if="datas.tag=='beginTime'" :date="datas"></YearMonthDate>
      <YearMonthDate v-if="datas.tag=='endTime'" :date="datas"></YearMonthDate>
      <RentMoney v-if="datas.tag=='rentPrice'"></RentMoney>
      <WanYuan v-if="datas.tag=='wan'"></WanYuan>
      <XiaoxieMoney v-if="datas.tag=='xiaoxie'"></XiaoxieMoney>
      <Money v-if="datas.tag=='money'" :describe="datas.text"></Money>
      <Money v-if="datas.tag=='sellPrice'" :describe="datas.text"></Money>
      <Money v-if="datas.tag=='commissionA'" :describe="datas.text"></Money>
      <Money v-if="datas.tag=='commissionB'" :describe="datas.text"></Money>
      <Area v-if="datas.tag=='area'"></Area>
      <Checkbox v-if="datas.tag=='checkbox'" :describe="datas.text"></Checkbox>
      <br v-if="datas.tag=='br'">
      <template v-if="datas.tag=='propertyRrightNO'">
        <span>所有权证编号：</span>
        <input type="text" :class="datas.css" v-model="datas.value" :style="{width:datas.width+'px'}">
      </template>
  </div>
</template>

<script>
import YearMonthDate from './YearMonthDate.vue'
import RentMoney from './RentMoney.vue'
import Area from './Area.vue'
import Checkbox from './Checkbox.vue'
import Money from './Money.vue'
export default{
  data () {
    return {

    }
  },
  components: {
    YearMonthDate,RentMoney,Area,Checkbox,Money
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
