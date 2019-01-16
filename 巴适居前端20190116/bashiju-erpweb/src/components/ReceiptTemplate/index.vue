<template>
  <div  style="width: 660px;">
    <div id="subOutputRank" style="width: 660px;" v-if="!isPrint">
      <div style="text-align: center;position: relative;height: 30px;width: 660px;">
        <div style="border-bottom: double 3px #000000;width:200px;padding-left:50px;margin:auto;letter-spacing: 50px;text-align: center;font-size: 18px;">收据</div>
        <div style="position: absolute;top: 5px;right: 5px;font-size: 16px;">
          <span>NO.</span>
          <span>{{receiptCode}}</span>
        </div>
      </div>
      <div style="text-align: center;padding-left: 100px;width: 660px;">
        <el-date-picker v-model="currentDate" :clearable="false" type="year" class="receiptDate" @change="updateDate"></el-date-picker>
        <span>年</span>
        <el-date-picker v-model="currentDate" :clearable="false" type="month" class="receiptDate" format="M"  @change="updateDate"></el-date-picker>
        <span>月</span>
        <el-date-picker v-model="currentDate" :clearable="false" type="date" class="receiptDate" format="d"  @change="updateDate"></el-date-picker>
        <span>日</span>
        <div style="float: right;margin-right: 10px;">
          <span>第</span>
          <input  v-model="dijihao" style="width: 60px;">
          <span>号</span>
        </div>
      </div>
      <div style="width: 660px;">
        <table class="receiptTable" style="width: 660px;border: solid 2px #333;">
          <tr>
            <td colspan="3" style="border-bottom:solid 2px #333;">
              <span>兹收到</span>
              <input  v-model="payer" style="width: 300px;">
              <span>交来下列款项此据</span>
            </td>
          </tr>
          <tr>
            <td style="width:300px;height: 42px;">
              <div style="text-align: center;letter-spacing: 50px;">摘要</div>
            </td>
            <td rowspan="4" style="padding: 0;width: 190px;vertical-align: top;">
              <table class="moneyTable">
                <tr class="header">
                  <td colspan="9" style="text-align: justify;text-align-last: justify;padding: 0 60px;border-top: 0;border-left: 0;">金额</td>
                </tr>
                <tr class="header">
                  <td class="rightBorder leftZero">佰</td>
                  <td>拾</td>
                  <td>万</td>
                  <td class="rightBorder">仟</td>
                  <td>佰</td>
                  <td>拾</td>
                  <td class="rightBorder">元</td>
                  <td>角</td>
                  <td>分</td>
                </tr>
                <tr>
                  <td  class="rightBorder leftZero"><input v-model="baiWan1" class="money"></td>
                  <td><input v-model="shiWan1" class="money"></td>
                  <td><input v-model="wan1" class="money"></td>
                  <td class="rightBorder"><input v-model="qian1" class="money"></td>
                  <td><input class="money" v-model="bai1"></td>
                  <td><input class="money" v-model="shi1"></td>
                  <td class="rightBorder"><input v-model="yuan1" class="money"></td>
                  <td><input class="money" v-model="jiao1"></td>
                  <td><input class="money" v-model="fen1"></td>
                </tr>
                <tr>
                  <td  class="rightBorder leftZero"><input v-model="baiWan2" class="money"></td>
                  <td><input v-model="shiWan2" class="money"></td>
                  <td><input v-model="wan2" class="money"></td>
                  <td class="rightBorder"><input v-model="qian2" class="money"></td>
                  <td><input class="money" v-model="bai2"></td>
                  <td><input class="money" v-model="shi2"></td>
                  <td class="rightBorder"><input v-model="yuan2" class="money"></td>
                  <td><input class="money" v-model="jiao2"></td>
                  <td><input class="money" v-model="fen2"></td>
                </tr>
                <tr>
                  <td  class="rightBorder leftZero"><input v-model="baiWan3" class="money"></td>
                  <td><input v-model="shiWan3" class="money"></td>
                  <td><input v-model="wan3" class="money"></td>
                  <td class="rightBorder"><input v-model="qian3" class="money"></td>
                  <td><input class="money" v-model="bai3"></td>
                  <td><input class="money" v-model="shi3"></td>
                  <td class="rightBorder"><input v-model="yuan3" class="money"></td>
                  <td><input class="money" v-model="jiao3"></td>
                  <td><input class="money" v-model="fen3"></td>
                </tr>
              </table>
            </td>
            <td style="width: 160px;">
              <div style="text-align: center;letter-spacing: 40px;">备注</div>
            </td>
          </tr>
          <tr>
            <td><input v-model="abstract1" style="width: 100%;"></td>
            <td><input v-model="remark1" style="width: 100%;"></td>
          </tr>
          <tr>
            <td><input v-model="abstract2" style="width: 100%;"></td>
            <td><input v-model="remark2" style="width: 100%;"></td>
          </tr>
          <tr>
            <td><input v-model="abstract3" style="width: 100%;"></td>
            <td><input v-model="remark3" style="width: 100%;"></td>
          </tr>
          <tr>
            <td colspan="3" style="border-top:solid 2px #333;">
              <div style="width: 50px;height:30px;display: inline-block;position: relative;">
                <span style="width:50px;position: absolute;top: -4px;left: 0;letter-spacing: 10px;">合计</span>
                <span style="position: absolute;top: 12px;left: 0;">人民币</span>
              </div>
              （小写）￥
              <input  type="number" class="input" style="width: 100px;border-bottom: 1px solid #000;" @keyup="keyupEvent" v-model="money" min="1" max="9999999">
              （大写）<span style="letter-spacing:2px;border-bottom: 1px solid #000;display: inline-block;min-width: 300px;line-height: 20px;">&nbsp;{{chineseNumber}}</span>
              <!--<span>(大写)</span>
              <input v-model="baiWan" style="text-align: center;">佰
              <input v-model="shiWan" style="text-align: center;">拾
              <input v-model="wan" style="text-align: center;">万
              <input v-model="qian" style="text-align: center;">仟
              <input v-model="bai" style="text-align: center;">佰
              <input v-model="shi" style="text-align: center;">拾
              <input v-model="yuan" style="text-align: center;">元
              <input v-model="jiao" style="text-align: center;">角
              <input v-model="fen" style="text-align: center;">分
              ￥<input v-model="money"  style="width: 90px;border-bottom: solid 1px #000;">-->
            </td>
          </tr>
        </table>
      </div>
      <div style="width: 660px;padding-top: 5px;">
        <div style="float: left;width: 350px;"><span>主管：</span><input v-model="director" style="width: 300px;"></div>
        <div style="float: left;width: 300px;"><span>经办人：</span><input v-model="operator" style="width: 240px;"></div>
      </div>
    </div>
    <div style="clear: both;"></div>
    <div id="subOutputRank-print" style="width: 660px;" v-if="isPrint">
      <div style="text-align: center;position: relative;height: 30px;width: 660px;">
        <div style="border-bottom: double 3px #000000;width:200px;padding-left:50px;margin:auto;letter-spacing: 50px;text-align: center;font-size: 18px;">收据</div>
        <div style="position: absolute;top: 5px;right: 5px;font-size: 16px;">
          <span>NO.</span>
          <span>{{receiptCode}}</span>
        </div>
      </div>
      <div style="text-align: center;width: 660px;padding-bottom: 4px;">
        <span style="width: 40px;margin-left: 140px;display: inline-block;">{{year}}</span>
        <span>年</span>
        <span style="width: 40px;display: inline-block;">{{month}}</span>
        <span>月</span>
        <span style="width: 40px;display: inline-block;">{{date}}</span>
        <span >日</span>
        <div style="float: right;margin-right: 10px;">
          <span>第</span>
          <span  style="width: 60px;display: inline-block;">{{dijihao}}</span>
          <span>号</span>
        </div>
      </div>
      <div style="width: 660px;">
        <table class="receiptTable" style="width: 660px;border: solid 2px #000;" cellspacing="0" cellpadding="0">
          <tr>
            <td colspan="3" style="border-bottom:solid 2px #333;">
              <span>兹收到</span>
              <span  style="width: 300px;display: inline-block;">{{payer}}</span>
              <span>交来下列款项此据</span>
            </td>
          </tr>
          <tr>
            <td style="width:300px;" class="zaiyao">
              <div style="text-align: center;letter-spacing: 50px;">摘要</div>
            </td>
            <td rowspan="4" style="padding: 0;width: 190px;vertical-align: top;">
              <table class="moneyTable" cellspacing="0" cellpadding="0">
                <tr class="header">
                  <td colspan="9" style="text-align: justify;text-align-last: justify;padding: 0 60px;border-top: 0;border-left: 0;">金额</td>
                </tr>
                <tr class="header">
                  <td class="rightBorder leftZero">佰</td>
                  <td>拾</td>
                  <td>万</td>
                  <td class="rightBorder">仟</td>
                  <td>佰</td>
                  <td>拾</td>
                  <td class="rightBorder">元</td>
                  <td>角</td>
                  <td>分</td>
                </tr>
                <tr>
                  <td  class="rightBorder leftZero"><span style="display: inline-block;width: 20px;">{{baiWan1}}</span></td>
                  <td><span   style="display: inline-block;width: 20px;">{{shiWan1}}</span></td>
                  <td><span  style="display: inline-block;width: 20px;">{{wan1}}</span></td>
                  <td class="rightBorder"><span  style="display: inline-block;width: 20px;">{{qian1}}</span></td>
                  <td><span style="display: inline-block;width: 20px;" >{{bai1}}</span></td>
                  <td><span style="display: inline-block;width: 20px;" >{{shi1}}</span></td>
                  <td class="rightBorder"><span style="display: inline-block;width: 20px;">{{yuan1}}</span></td>
                  <td><span style="display: inline-block;width: 20px;" >{{jiao1}}</span></td>
                  <td><span style="display: inline-block;width: 20px;" >{{fen1}}</span></td>
                </tr>
                <tr>
                  <td  class="rightBorder leftZero"><span style="display: inline-block;width: 20px;">{{baiWan2}}</span></td>
                  <td><span  style="display: inline-block;width: 20px;">{{shiWan2}}</span></td>
                  <td><span  style="display: inline-block;width: 20px;">{{wan2}}</span></td>
                  <td class="rightBorder"><span  style="display: inline-block;width: 20px;">{{qian2}}</span></td>
                  <td><span style="display: inline-block;width: 20px;" >{{bai2}}</span></td>
                  <td><span style="display: inline-block;width: 20px;" >{{shi2}}</span></td>
                  <td class="rightBorder"><span style="display: inline-block;width: 20px;">{{yuan2}}</span></td>
                  <td><span style="display: inline-block;width: 20px;" >{{jiao2}}</span></td>
                  <td><span style="display: inline-block;width: 20px;" >{{fen2}}</span></td>
                </tr>
                <tr>
                  <td  class="rightBorder leftZero"><span style="display: inline-block;width: 20px;">{{baiWan3}}</span></td>
                  <td><span  style="display: inline-block;width: 20px;">{{shiWan3}}</span></td>
                  <td><span  style="display: inline-block;width: 20px;">{{wan3}}</span></td>
                  <td class="rightBorder"><span  style="display: inline-block;width: 20px;">{{qian3}}</span></td>
                  <td><span style="display: inline-block;width: 20px;" >{{bai3}}</span></td>
                  <td><span style="display: inline-block;width: 20px;" >{{shi3}}</span></td>
                  <td class="rightBorder"><span style="display: inline-block;width: 20px;">{{yuan3}}</span></td>
                  <td><span style="display: inline-block;width: 20px;" >{{jiao3}}</span></td>
                  <td><span style="display: inline-block;width: 20px;" >{{fen3}}</span></td>
                </tr>
              </table>
            </td>
            <td style="width: 160px;">
              <div style="text-align: center;letter-spacing: 40px;">备注</div>
            </td>
          </tr>
          <tr>
            <td><span  style="width: 100%;display: inline-block;">&nbsp;{{abstract1}}</span></td>
            <td><span  style="width: 100%;display: inline-block;">&nbsp;{{remark1}}</span></td>
          </tr>
          <tr>
            <td><span  style="width: 100%;display: inline-block;">&nbsp;{{abstract2}}</span></td>
            <td><span  style="width: 100%;display: inline-block;">&nbsp;{{remark2}}</span></td>
          </tr>
          <tr>
            <td><span  style="width: 100%;display: inline-block;">&nbsp;{{abstract3}}</span></td>
            <td><span  style="width: 100%;display: inline-block;">&nbsp;{{remark3}}</span></td>
          </tr>
          <tr>
            <td colspan="3" style="border-top:solid 2px #333;">
              <div style="width: 50px;height:30px;display: inline-block;position: relative;">
                <span style="display: inline-block;width:80px;position: absolute;top: 0px;left: 0;letter-spacing: 16px;">合计</span>
                <span style="display: inline-block;position: absolute;top: 16px;left: 0;">人民币</span>
              </div>
              （小写）￥
              <span    style="width: 100px;border-bottom: 1px solid #000;display: inline-block;line-height: 20px;" >{{money}} </span>元
              （大写）<span style="letter-spacing:2px;border-bottom: 1px solid #000;display: inline-block;min-width: 300px;line-height: 20px;">&nbsp;{{chineseNumber}}</span>
              <!--<span>(大写)</span>
              <span style="text-align: center;width: 28px;display: inline-block;">{{baiWan}}</span>佰
              <span  style="text-align: center;width: 28px;display: inline-block;">{{shiWan}}</span>拾
              <span  style="text-align: center;width: 28px;display: inline-block;">{{wan}}</span>万
              <span  style="text-align: center;width: 28px;display: inline-block;">{{qian}}</span>仟
              <span style="text-align: center;width: 28px;display: inline-block;">{{bai}}</span>佰
              <span  style="text-align: center;width: 28px;display: inline-block;">{{shi}}</span>拾
              <span  style="text-align: center;width: 28px;display: inline-block;">{{yuan}}</span>元
              <span  style="text-align: center;width: 28px;display: inline-block;">{{jiao}}</span>角
              <span  style="text-align: center;width: 28px;display: inline-block;">{{fen}}</span>分
              ￥<span   style="display: inline-block;width: 90px;height:30px;border-bottom: solid 1px #000;">{{money}}</span>-->
            </td>
          </tr>
        </table>
      </div>
      <div style="width: 660px;padding-top: 5px;">
        <span>主管：</span><span style="width: 300px;display: inline-block;">{{director}}</span>
        <span>经办人：</span><span style="width: 200px;display: inline-block;">{{operator}}</span>
      </div>
    </div>

    <div id="printdiv" style="text-align: center;margin-top: 40px;">
      <el-button type="primary" @click="printContent">打 印</el-button>
    </div>
  </div>
</template>

<script>
  import Snowflake from '@/common/js/snowFlake.js'
  import html2Canvas from 'html2canvas'
  export default{
    data () {
      return {
        year:'',
        month:'',
        date:'',
        payer: '',
        receiptCode: '',
        dijihao: '',
        abstract1:'',
        abstract2:'',
        abstract3:'',
        remark1: '',
        remark2: '',
        remark3: '',
        baiWan: '',
        shiWan: '',
        wan: '',
        qian: '',
        bai: '',
        shi: '',
        yuan: '',
        jiao: '',
        fen:'',
        baiWan2: ' ',
        shiWan2: ' ',
        wan2: ' ',
        qian2: ' ',
        bai2: ' ',
        shi2: ' ',
        yuan2: ' ',
        jiao2: ' ',
        fen2:' ',
        baiWan3: ' ',
        shiWan3: ' ',
        wan3: ' ',
        qian3: ' ',
        bai3: ' ',
        shi3: ' ',
        yuan3: ' ',
        jiao3: ' ',
        fen3:'',
        baiWan1: ' ',
        shiWan1: ' ',
        wan1: ' ',
        qian1: ' ',
        bai1: ' ',
        shi1: ' ',
        yuan1: ' ',
        jiao1: ' ',
        fen1:' ',
        money:'',
        director:'',
        operator: '',
        isPrint: true,
        currentDate: '',
        chineseNumber:''
      }
    },
    props:['code','payMoney','payMan','content','payDate','recipient','remark'],
    mounted () {
      this.currentDate = this.getCurrDate ()
      console.log(Snowflake)
      let tempSnowflake = new Snowflake(1, 1, 0)
      this.receiptCode = tempSnowflake.nextId()
      if (this.payMoney){
         let  money = this.payMoney +''
         if (money.indexOf('.')===-1){
           this.money = money + '.00'
         }else {
           this.money = money
         }
        this.keyupEvent()
      }
      if (this.payMan)this.payer = this.payMan
      if (this.content) this.abstract1 = this.content
      if (this.payDate) this.currentDate = this.payDate
      if (this.recipient) this.operator = this.recipient
      if (this.remark) this.remark1 = this.remark
      if (this.code) this.receiptCode = this.code
    },
    methods: {
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
        this.baiWan1 = ''
        this.shiWan1= ''
          this.wan1= ''
          this.qian1= ''
          this.bai1= ''
          this.shi1= ''
          this.yuan1= ''
          this.jiao1= ''
          this.fen1= ''
        this.chineseNumber=''
        let moneyArry = ['baiWan','shiWan','wan','qian','bai','shi','yuan','jiao','fen']
        if (/^([1-9]\d*)(\.\d+)?$/.test(this.money)){
          let money = this.money+'00'
          var indexpoint = money.indexOf('.');
          if (indexpoint >= 0){
            money = money.substring(0, indexpoint) + money.substr(indexpoint+1, 2);   // 若为小数，截取需要使用的unit单位
          }
          let index = moneyArry.length-money.length
          if (index>=0){
            this.chineseNumber = this.toChineseNumber(this.money)
            for (let i=0;i<money.length;i++){
              switch (moneyArry[i+index]){
                case 'baiWan':
                  this.baiWan1=money.charAt(i)
                  break
                case 'shiWan':
                  this.shiWan1=money.charAt(i)
                  break
                case 'wan':
                  this.wan1=money.charAt(i)
                  break
                case 'qian':
                  this.qian1=money.charAt(i)
                  break
                case 'bai':
                  this.bai1=money.charAt(i)
                  break
                case 'shi':
                  this.shi1=money.charAt(i)
                  break
                case 'yuan':
                  this.yuan1=money.charAt(i)
                  break
                case 'jiao':
                  this.jiao1=money.charAt(i)
                  break
                case 'fen':
                  this.fen1=money.charAt(i)
                  break
              }
            }
          }else {
            this.money = ''
          }

        }else {
          this.money = ''
        }
      },
      getCurrDate () {
        let date = new Date();
        let seperator1 = "-";
        let year = date.getFullYear();
        let month = date.getMonth() + 1;
        let strDate = date.getDate();
        this.year = year
        this.month = month
        this.date = strDate
        return year + seperator1 + month + seperator1 + strDate
      },
      updateDate (value) {
        let date = new Date(value);
        let seperator1 = "-";
        let year = date.getFullYear();
        let month = date.getMonth() + 1;
        let strDate = date.getDate();
        this.year = year
        this.month = month
        this.date = strDate
      },
      printContent(e){
        //this.isPrint=true
        /*this.$nextTick((res)=>{
          html2Canvas(document.querySelector('#subOutputRank-print'), {
            allowTaint: true
          }).then((canvas)=> {
            let image = canvas.toDataURL('image/png')

            //this.isPrint=false
          })
        })*/

        let openWindow = window.open('')
        openWindow.document.write("<HTML>")
        openWindow.document.write("<head>")
        openWindow.document.write("<style>")
        openWindow.document.write(".receiptTable{border-right:1px solid #333;border-bottom:1px solid #333;}")
        openWindow.document.write(".receiptTable td{border-left:1px solid #333;border-top:1px solid #333;height: 40px;padding: 0 10px;}")
        openWindow.document.write(".moneyTable td{padding: 0 1px;text-align: center;height:40px;}")
        openWindow.document.write(".moneyTable input{width: 20px;text-align: center;}")
        openWindow.document.write(".zaiyao{height: 44px !important;}")
        openWindow.document.write(".header td{height: 21px !important;}")
        openWindow.document.write("td.rightBorder{border-right: 2px solid #333;}")
        openWindow.document.write("td.leftZero{border-left: 0;}")
        openWindow.document.write(".receiptDate{width: 40px !important;}")
        openWindow.document.write("#subOutputRank-print span{display: inline-block;}")
        openWindow.document.write(" span.money{width: 20px;}")
        openWindow.document.write("</style>")
        openWindow.document.write("</head>")
        openWindow.document.write("<BODY >")
        openWindow.document.write(document.getElementById("subOutputRank-print").innerHTML)
        //openWindow.document.write('<img src="'+image+'">')
        openWindow.document.write("</BODY>")
        openWindow.document.write("</HTML>")
        openWindow.print()
        openWindow.close()
        this.$emit('printOver')
      }
    }
  }
</script>

<style lang="scss" >
.receiptTable{
  border-right:1px solid #333;
  border-bottom:1px solid #333;
}
 .receiptTable td{
   border-left:1px solid #333;
   border-top:1px solid #333;
   line-height: 40px;
   padding: 0 10px;
 }
 .zaiyao{
   height: 42px;
 }
 #subOutputRank input{
    border: 0;
    outline: none;
    width: 30px;
  }
.moneyTable td{
  padding: 0 1px;
  text-align: center;
}
.moneyTable input{
  width: 20px;
  text-align: center;
}
.header td{
  line-height: 20px !important;
}
td.rightBorder{
  border-right: 2px solid #333;
}
td.leftZero{
  border-left: 0;
}
.receiptDate{
  width: 40px !important;
}
.receiptDate .el-input__inner{
  -web-kit-appearance:none !important;
  -moz-appearance: none!important;
  outline:0!important;
  border-left-style: none!important;
  border-top-style: none!important;
  border-right-style: none!important;
  border-bottom: 0!important;
  width: 40px!important;
  height: 16px!important;
  border-radius: 0!important;
  padding: 0!important;
  text-align: center!important;
}
.receiptDate .el-input__prefix{
  display: none;
}
#subOutputRank-print span{
  display: inline-block;
}
span.money{
  width: 20px;
}
</style>
