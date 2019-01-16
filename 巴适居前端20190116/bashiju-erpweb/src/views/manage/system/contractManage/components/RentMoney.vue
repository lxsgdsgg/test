<template>
  <div class="dataRow">
    租金为人民币（小写）￥：
    <input  type="number" class="input" style="width: 80px;" @keyup="keyupEvent" v-model="inputValue" min="0" max="99999999">
    元，（大写）：<span style="letter-spacing:2px;border-bottom: 1px solid #000;display: inline-block;min-width: 150px;">{{chineseNumber}}</span>
    每<el-checkbox></el-checkbox>
    <span>年</span>
    <el-checkbox></el-checkbox>
    <span>月</span>
  </div>
</template>

<script>

  export default{
    data () {
      return {
        chineseNumber:'',
        inputValue:''
      }
    },
    props:['describe'],
    components: {

    },
    methods:{
      toChineseNumber (n) {

        if (!/^([1-9]\d*)(\.\d+)?$/.test(n)){
          return "";  //判断数据是否大于0
        }

        var unit = "千百拾亿千百拾万千百拾元角分", str = "";
        n += "00";

        var indexpoint = n.indexOf('.');  // 如果是小数，截取小数点前面的位数

        if (indexpoint >= 0){

          n = n.substring(0, indexpoint) + n.substr(indexpoint+1, 2);   // 若为小数，截取需要使用的unit单位
        }

        unit = unit.substr(unit.length - n.length);  // 若为整数，截取需要使用的unit单位
        for (var i=0; i < n.length; i++){
          str += "零壹贰叁肆伍陆柒捌玖".charAt(n.charAt(i)) + unit.charAt(i);  //遍历转化为大写的数字
        }

        return str.replace(/零(千|百|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g, "$1").replace(/(亿)万|壹(拾)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g, "元整"); // 替换掉数字里面的零字符，得到结果

      },
      keyupEvent () {
        this.chineseNumber = this.toChineseNumber(this.inputValue)
      }
    }
  }
</script>

<style lang="scss" scoped>
  .input{
    -web-kit-appearance:none;
    -moz-appearance: none;
    outline:0;
    border-left-style: none;
    border-top-style: none;
    border-right-style: none;
    border-bottom: solid 1px #000000;
    width: auto;
  }
</style>
