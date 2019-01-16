<template>
    <div v-loading="loadingView" class="draggle">
      <div style="padding-bottom: 4px;text-align: right;">
        <el-button type="primary"  @click="printHouse">打 印</el-button>
        <el-button type="primary"  @click="printCancel">取 消</el-button>
      </div>
      <div id="printHouseInfo">
        <div  tabindex="0" :style="{backgroundImage:'url('+background+')',width:contentWidth+'px',height:contentHeight+'px'}" class="right" >
        <span  v-for="(item,index) in spanList" :key="item.itemId" :id="item.itemId" :index="index" :class="item.css" :style="item.style" :tag="item.tag" :column="item.dataColumn">
        {{item.text}}
      </span>
          <img  v-for="(item,index) in picList" :key="item.itemId" :index="index" :src="item.url" :class="item.css" :style="item.style" :tag="item.tag" :column="item.dataColumn"/>
        </div>
      </div>
    </div>
</template>

<script>
  import {onPost} from "@/request/main";
  export default{
    data () {
      return {
        loadingView: false,
        contentWidth:794,
        contentHeight:1123,
        spanList: [],
        picList: [],
        background: ''
      }
    },
    props:['templateId','houseId'],
    mounted (){
      this.loadingView = false
      onPost('house/queryHousePrintDetailed',{'houseId':this.houseId,'templateId':this.templateId}).then((res)=>{
        if (res.success) {
          this.background = res.data.backgroundUrl
          if (res.data.typeValue == 1) {
            this.contentWidth = 794
            this.contentHeight = 1123
          }
          if (res.data.typeValue == 2) {
            this.contentWidth = 1123
            this.contentHeight = 794*2
          }
          if (res.data.spanArry.length > 0) {
            for (let i=0;i<res.data.spanArry.length;i++) {
              res.data.spanArry[i].style = this.getStyle(res.data.spanArry[i])
            }
          }
          if (res.data.picArry.length > 0) {
            for (let i=0;i<res.data.picArry.length;i++) {
              res.data.picArry[i].style = this.getStyle(res.data.picArry[i])
            }
          }
          this.spanList = res.data.spanArry
          this.picList = res.data.picArry

        } else {
          this.$message({
            type: 'warning',
            message: res.msg
          })
        }
        this.loadingView = false
      })
    },
    methods:{
      getStyle (item) {
        let css = 'left:' + item.x + 'px;top:' + item.y + 'px;'
        if (item.strong) css += 'font-weight:bold;'
        if (item.fontstyle) css += 'font-style:italic;'
        if (item.underline) css += 'text-decoration:underline;'
        if (item.linethrough) css += 'text-decoration: line-through;'
        if (item.color) css += 'color: ' + item.color + ';'
        if (item.fontfamily) css += 'font-family: ' + item.fontfamily + ';'
        if (item.fontsize) css += 'font-size: ' + item.fontsize + 'px;'
        if (item.width) css += 'width: ' + item.width + 'px;'
        if (item.height) css += 'height: ' + item.height + 'px;'
        return css
      },
      printHouse(){
        let OpenWindow = window.open('')
        OpenWindow.document.write("<HTML>")
        OpenWindow.document.write("<head>")
        OpenWindow.document.write("<TITLE>例子</TITLE>")
        OpenWindow.document.write("<style>")
        OpenWindow.document.write(".txt{position: absolute;z-index: 999;}")
        OpenWindow.document.write(".pic{position: absolute;z-index: 100;opacity:0.5;width: 300px;height: 300px;}")
        //OpenWindow.document.write(".draggle {display: flex;padding: 10px;}")
        OpenWindow.document.write(".right {background-repeat: no-repeat;position: relative;}")
        OpenWindow.document.write(".right span {line-height: 22px;}")
        OpenWindow.document.write("</style>")
        OpenWindow.document.write("</head>")
        OpenWindow.document.write("<BODY >")
        OpenWindow.document.write(document.getElementById("printHouseInfo").innerHTML)
        OpenWindow.document.write("</BODY>")
        OpenWindow.document.write("</HTML>")
        OpenWindow.print()
        OpenWindow.close()
        this.$emit('printHouse')
      },
      printCancel(){
        this.$emit('printCancel')
      }
    }
  }
</script>

<style  lang="scss" scoped>
  .txt{
    position: absolute;
    z-index: 999;
  }
  .pic{
    position: absolute;
    z-index: 100;
    opacity:0.5;
    width: 300px;
    height: 300px;
  }
  .draggle {
    /*display: flex;*/
    padding: 10px;
  }
  .right {
    box-shadow: 0 0 6px #f20;
    background-repeat: no-repeat;
    position: relative;
    /*background-position: center;
    background-size: 100%;*/
  }
  .right span {
    line-height: 22px;
  }
  .widget_item {
    float: left;
    width: 92px;
    text-align: center;
    margin: 6px 4px;
    border: 1px dashed #999;
    padding: 5px 0;
    cursor: move;
    border-radius: 2px;
    -webkit-transition: background ease-in-out 0.15s;
    -moz-transition: background ease-in-out 0.15s;
    transition: background ease-in-out 0.15s;
    background-color: #f5f5f5;
    position: relative;
    z-index: 100;
  }
</style>
