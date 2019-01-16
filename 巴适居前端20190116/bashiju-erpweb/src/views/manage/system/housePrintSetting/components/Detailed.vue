<template>
  <div v-loading="loadingView">
    <div class="page-content-hd">
      <div class="query-form" >
        <el-form size="small" :inline="true"  class="demo-form-inline" >
          <el-form-item>
            <el-select v-model="typeId"  placeholder="请选择" @change="typeChange">
              <el-option
                v-for="item in templateType"
                :key="item.typeId"
                :label="item.typeName"
                :value="item.typeId">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-input style="width: 300px;" v-model="name" placeholder="请输入模板名称"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" :loading="loadingView"  @click="saveTemplate">保存</el-button>
            <el-button type="primary"  @click="blackList">取消</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <div class="draggle">
      <div class="left">
        <div class="widget_item" @dragstart="dragstart" draggable="true" tag="customer" data="自定义文本">
          <span class="title">自定义文本</span>
        </div>
        <div class="widget_item" v-if="showHouse" @dragstart="dragstart" draggable="true" tag="text" column="houseId" data="530102000008">
          <span class="title">房源编号</span>
        </div>
        <div class="widget_item" v-if="showHouse" @dragstart="dragstart" draggable="true" tag="text" column="address" data="滇缅大道与昌源中路的黄金交汇处">
          <span class="title">地址</span>
        </div>
        <div class="widget_item" v-if="showHouse" @dragstart="dragstart" draggable="true" tag="text" column="room" data="2室1厅1卫">
          <span class="title">户型</span>
        </div>
        <div class="widget_item" @dragstart="dragstart" draggable="true" tag="text" column="propertyType" data="商品房">
          <span class="title">产别</span>
        </div>
        <div class="widget_item" v-if="showHouse" @dragstart="dragstart" draggable="true" tag="text" column="buildSpace" data="90㎡">
          <span class="title">面积</span>
        </div>
        <div class="widget_item" v-if="showHouse" @dragstart="dragstart" draggable="true" tag="text" column="floorDes" data="中楼层 / 共7层">
          <span class="title">楼层</span>
        </div>
        <div class="widget_item" v-if="showHouse" @dragstart="dragstart" draggable="true" tag="text" column="decoration" data="精装">
          <span class="title">装修</span>
        </div>
        <div class="widget_item" v-if="showHouse" @dragstart="dragstart" draggable="true" tag="text" column="buildDates" data="2010">
          <span class="title">年代</span>
        </div>
        <div class="widget_item" v-if="showHouse" @dragstart="dragstart" draggable="true" tag="text" column="orientation" data="东南">
          <span class="title">朝向</span>
        </div>
        <div class="widget_item" v-if="showHouse" @dragstart="dragstart" draggable="true" tag="text" column="elevatorCount" data="2梯5户">
          <span class="title">梯户</span>
        </div>
        <div class="widget_item" v-if="showHouse" @dragstart="dragstart" draggable="true" tag="text" column="description" data="70年产权，涂料外墙，户型反正，中间小区，环境优雅，客厅朝南，主卧朝南中间楼层，诚心出售欢迎参观标准2室2厅1卫双阳台证上面积72平米，送飘窗全面积，双阳台半*，采光采阳，空气疏通居住适宜 ">
          <span class="title">核心卖点</span>
        </div>
        <div class="widget_item" v-if="showHouse" @dragstart="dragstart" draggable="true" tag="text" column="structure" data="钢混结构">
          <span class="title">结构</span>
        </div>
        <div class="widget_item" v-if="showHouse" @dragstart="dragstart" draggable="true" tag="text" column="sellingPrice" data="73万">
          <span class="title">总价</span>
        </div>
        <div class="widget_item" v-if="showHouse" @dragstart="dragstart" draggable="true" tag="text" column="averagePrice" data="10111元/m² ">
          <span class="title">单价</span>
        </div>
        <div class="widget_item" v-if="showHouse" @dragstart="dragstart" draggable="true" tag="text" column="rentPrice" data="1200">
          <span class="title">租价</span>
        </div>
        <div class="widget_item" v-if="showHouse" @dragstart="dragstart" draggable="true" tag="text" column="rentPriceTypeName" data="元/月">
          <span class="title">租价单位</span>
        </div>
        <div class="widget_item" v-if="showHouse" @dragstart="dragstart" draggable="true" tag="text" column="maintainer" data="张三">
          <span class="title">维护人</span>
        </div>
        <div class="widget_item" v-if="showHouse" @dragstart="dragstart" draggable="true" tag="text" column="maintainerPhone" data="18888888888">
          <span class="title">维护人电话</span>
        </div>
        <div class="widget_item" v-if="showHouse" @dragstart="dragstart" draggable="true" tag="text" column="printer" data="李四">
          <span class="title">打印人</span>
        </div>
        <div class="widget_item" v-if="showHouse" @dragstart="dragstart" draggable="true" tag="text" column="printerPhone" data="15966666666">
          <span class="title">打印人电话</span>
        </div>
        <div class="widget_item" v-if="showHouse" @dragstart="dragstart" draggable="true" tag="pic" column="houseTypePic" data="http://file.bashiju.com/13577000337/pic/2018-09-07/1536311647840.jpg">
          <span class="title">户型图</span>
        </div>
        <div class="widget_item" v-if="showHouse" @dragstart="dragstart" draggable="true" tag="pic" column="indoorPic" data="http://file.bashiju.com/13577000337/pic/2018-09-07/1536311678129.jpg">
          <span class="title">室内图</span>
        </div>
        <div class="widget_item" v-if="showHouse" @dragstart="dragstart" draggable="true" tag="pic" column="coverPic" data="http://file.bashiju.com/13577000337/pic/2018-09-07/1536311684212.jpg">
          <span class="title">封面图</span>
        </div>

        <div class="widget_item" v-if="showMoney" @dragstart="dragstart" draggable="true" tag="text" column="agreementId" data="M-14-0001">
          <span class="title">合同编号</span>
        </div>
        <div class="widget_item" v-if="showMoney" @dragstart="dragstart" draggable="true" tag="text" column="billNo" data="DJ-14-652798">
          <span class="title">票据号</span>
        </div>
        <div class="widget_item" v-if="showMoney" @dragstart="dragstart" draggable="true" tag="text" column="actualPayTime" data="2018-01-20">
          <span class="title">开票日期</span>
        </div>
        <div class="widget_item" v-if="showMoney" @dragstart="dragstart" draggable="true" tag="text" column="custName" data="张三">
          <span class="title">客户姓名</span>
        </div>
        <div class="widget_item" v-if="showMoney" @dragstart="dragstart" draggable="true" tag="text" column="dealType" data="买卖成交">
          <span class="title">业务类型</span>
        </div>
        <div class="widget_item" v-if="showMoney" @dragstart="dragstart" draggable="true" tag="text" column="address" data="呈贡新城七彩云南第一城A栋403">
          <span class="title">房产地址</span>
        </div>
        <div class="widget_item" v-if="showMoney" @dragstart="dragstart" draggable="true" tag="text" column="moneyProjName" data="违约金">
          <span class="title">费用类型</span>
        </div>
        <div class="widget_item" v-if="showMoney" @dragstart="dragstart" draggable="true" tag="text" column="chinesePrice" data="壹百">
          <span class="title">金额(大写)</span>
        </div>
        <div class="widget_item" v-if="showMoney" @dragstart="dragstart" draggable="true" tag="text" column="price" data="100">
          <span class="title">金额(小写)</span>
        </div>
        <div class="widget_item" v-if="showMoney" @dragstart="dragstart" draggable="true" tag="text" column="payerType" data="业主">
          <span class="title">交佣房</span>
        </div>
        <div class="widget_item" v-if="showMoney" @dragstart="dragstart" draggable="true" tag="text" column="sureTime" data="2018-02-20">
          <span class="title">收费时间</span>
        </div>
        <div class="widget_item" v-if="showMoney" @dragstart="dragstart" draggable="true" tag="text" column="settlementTypeName" data="现金">
          <span class="title">结算方式</span>
        </div>

        <div class="widget_item"  @dragstart="dragstart" draggable="true" tag="text" column="remark" data="急切出售">
          <span class="title">备注</span>
        </div>
      </div>
      <div >
        <EditeHead :count="count" @fontStrong="fontStrong" @fontStyle="fontStyle" @fontUnderLine="fontUnderLine"
                   @lineThrough="lineThrough" @fontSize="fontSize" @setFontFamily="setFontFamily"
                   @setBackGround="setBackGround" @clearBackGround="clearBackGround" @setFontColor="setFontColor"></EditeHead>
        <div tabindex="0" :style="{backgroundImage:'url('+background+')',width:contentWidth+'px',height:contentHeight+'px'}" class="right" @drop="drop" @click="moveDel"  @keyup.left.stop.prevent="moveEl(1)" @keyup.right.stop.prevent="moveEl(2)" @keyup.up.stop.prevent="moveEl(3)" @keyup.down.stop.prevent="moveEl(4)">
          <ul class="menu" :style="delCss">
            <li  @click="delItem">删除</li>
            <li  @click="showPicDialog">编辑尺寸</li>
            <li :style="isCustomer" @click="showCustomerDialog">编辑</li>
          </ul>
          <el-dialog title="图片尺寸" width="400px" :visible.sync="dialogFormVisible">
            <el-form :model="form">
              <el-form-item label="宽度" :label-width="formLabelWidth">
                <el-input-number v-model="form.width" :min="0"></el-input-number>
              </el-form-item>
              <el-form-item label="高度" :label-width="formLabelWidth">
                <el-input-number v-model="form.height" :min="0"></el-input-number>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button type="primary" @click="setPicWidth">确 定</el-button>
              <el-button @click="dialogFormVisible = false">取 消</el-button>
            </div>
          </el-dialog>
          <el-dialog title="编辑文本" width="400px" :visible.sync="dialogCustomerVisible">
            <el-form >
              <el-form-item label="文本值" :label-width="formLabelWidth">
                <el-input v-model="textForm" auto-complete="off"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button type="primary"  @click="setCustomerText">确 定</el-button>
              <el-button @click="dialogCustomerVisible = false">取 消</el-button>
            </div>
          </el-dialog>
          <span draggable="true" @contextmenu.stop.prevent="showMen"  @click="selected(item)" @dragstart="dragmove" v-for="(item,index) in spanList" :key="item.itemId" :id="item.itemId" :index="index" :class="item.css" :style="item.style" :tag="item.tag" :column="item.dataColumn">
        {{item.text}}
      </span>
          <img draggable="true" @contextmenu.stop.prevent="showMen"  @click="selected(item)" @dragstart="dragmove" v-for="(item,index) in picList" :key="item.itemId" :index="index" :src="item.url" :class="item.css" :style="item.style" :tag="item.tag" :column="item.dataColumn"/>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
  import {
    queryDetailed,
    addPrintTemplate,
    updatePrintTemplate
  } from '@/request/manage/HousePrintTemplate'
import EditeHead from './EditeHead'
  import {manageAddLog,manageUpdateLog,manageQueryLog,manageDelLog} from '@/request/log/mangePlatformLog'
export default {
  name: 'draggle',
  props: ['spanArry','picArry','backgroundUrl','templateName','typeValue','isUseType','templateId'],
  data () {
    return {
      showHouse: true,
      showMoney: false,
      tid: 0,
      loadingView: false,
      contentWidth:794,
      contentHeight:1123,
      spanList: [],
      picList: [],
      background: '',
      name: '',
      typeId: 1,
      //templateType: [{'typeId':1,'typeName':'房源打印'},{'typeId':2,'typeName':'佣金打印'}],
      templateType: [{'typeId':1,'typeName':'A4'},{'typeId':2,'typeName':'A3'}],
      delCss: 'display:none;',
      index: 0,
      tag: '',
      isPic: 'display:none;',
      isCustomer: 'display:none;',
      count: 0,
      selectedArry: [],
      dialogFormVisible: false,
      dialogCustomerVisible: false,
      formLabelWidth: '100px',
      form: {
        'width': 300,
        'height': 300
      },
      textForm: '',
      textItem: {},
      picItem: {}
    }
  },
  components: {
    EditeHead
  },
  mounted () {
    console.log(this.templateId)
    console.log(this.spanArry)
    this.spanList = this.spanArry
    this.picList = this.picArry
    this.background = this.backgroundUrl
    this.name = this.templateName
    this.tid = this.templateId
    this.typeId = this.typeValue
    this.typeChange()
   /* if (this.typeId == 1) {
      this.showHouse = true
      this.showMoney = false
    }
    if (this.typeId == 2) {
      this.showHouse = false
      this.showMoney = true
    }*/
    window.addEventListener('dragover', function (e) {
      e.preventDefault()
    })
  },
  methods: {
    typeChange () {
      /*if (this.typeId == 1) {
        this.showHouse = true
        this.showMoney = false
      }
      if (this.typeId == 2) {
        this.showHouse = false
        this.showMoney = true
      }*/
      if (this.typeId == 1) {
        this.contentWidth = 794
        this.contentHeight = 1123
      }
      if (this.typeId == 2) {
        this.contentWidth = 1123
        this.contentHeight = 794*2
      }
    },
    saveTemplate () {
      let templateTypeName = ''
      for (var i = 0;i < this.templateType.length;i++) {
          if (this.templateType[i].typeId === this.typeId) {
            templateTypeName = this.templateType[i].typeName
          }
      }
      let baseInfo = {'templateName': this.name, 'typeId': this.typeId, 'typeName': templateTypeName}
      if (this.tid > 0) {
        baseInfo = {'templateName': this.name,'typeId': this.typeId, 'typeName': templateTypeName, 'id': this.tid}
      }
      if (this.name === '') {
        this.$message({
          type: 'warning',
          message: '请填写模板名称!'
        })
        return
      }

      if (this.spanList.length === 0 && this.picList.length === 0) {
        this.$message({
          type: 'warning',
          message: '请添加模板子项!'
        })
        return
      }
      this.loadingView = true
      let params = {
        'baseInfo': JSON.stringify(baseInfo),
        'spanArry': JSON.stringify(this.spanList),
        'picArry': JSON.stringify(this.picList),
        'backUrl': this.background
      }
      console.log(params)
      if (this.tid > 0) {
        console.log(this.tid)
        console.log(params)
        updatePrintTemplate(params).then((res) => {
          this.loadingView = false
          if (res.success) {
            this.$message({
              type: 'success',
              message: '保存成功!'
            })
            let message = {sourceCode:this.name,sourceTypeId:'12',operatTypeId:'2'
              ,logContent:'修改模板:'+this.name}
            console.log(message)
            manageAddLog({message: JSON.stringify(message)})
          }
        }).catch(err => {
          this.loadingView = false
          this.$message({
            type: 'warning',
            message: err.msg
          })
        })
      } else {
        console.log(0)
        console.log(params)
        addPrintTemplate(params).then((res) => {
          this.loadingView = false
          if (res.success) {
            this.tid = res.data
            this.$message({
              type: 'success',
              message: '保存成功!'
            })
            let message = {sourceCode:this.name,sourceTypeId:'12',operatTypeId:'1'
              ,logContent: '新增打印模板:'+this.name}
            manageAddLog({message: JSON.stringify(message)})
          }
        }).catch(err => {
          this.loadingView = false
          this.$message({
            type: 'warning',
            message: err.msg
          })
        })
      }
    },
    setPicWidth () {
      this.picItem.width = this.form.width
      this.picItem.height = this.form.height
      this.picItem.style = this.getStyle(this.picItem)
      console.log(this.picItem)
      console.log(this.form)
      this.dialogFormVisible = false
      this.delCss = 'display:none;'
    },
    setCustomerText () {
      this.textItem.text = this.textForm
      this.textItem.style = this.getStyle(this.textItem)
      this.dialogCustomerVisible = false
      this.delCss = 'display:none;'
    },
    showMen (e) {
      this.delCss = 'display:block;' + 'left: ' + e.pageX + 'px; top: ' + (e.layerY<120?120:e.layerY) + 'px;'
      this.index = e.target.getAttribute('index')
      this.tag = e.target.getAttribute('tag')
      this.isCustomer = 'display:none;'
      if (this.tag === 'pic') {
        this.picItem = this.picList[this.index]
      } else {
        this.picItem = this.spanList[this.index]
      }
      this.form.width = this.picItem.width
      this.form.height = this.picItem.height
      if (this.tag === 'customer') {
        this.isCustomer = 'display:block;'
        this.textItem = this.spanList[this.index]
        this.textForm = this.textItem.text
      }
    },
    delItem () {
      if (this.tag === 'text' || this.tag === 'customer') {
        this.spanList.splice(this.index, 1)
      }
      if (this.tag === 'pic') {
        this.picList.splice(this.index, 1)
      }
      this.delCss = 'display:none;'
    },
    showPicDialog () {
      this.dialogFormVisible = true
    },
    showCustomerDialog () {
      this.dialogCustomerVisible = true
    },
    moveDel () {
      this.delCss = 'display:none;'
    },
    moveEl (d) {
      if (d === 1) {
        for (let i = 0; i < this.selectedArry.length; i++) {
          console.log(this.selectedArry[i].x)
          this.selectedArry[i].x = this.selectedArry[i].x - 1
          console.log(this.selectedArry[i].x)
          this.selectedArry[i].style = this.getStyle(this.selectedArry[i])
        }
      }
      if (d === 2) {
        for (let i = 0; i < this.selectedArry.length; i++) {
          this.selectedArry[i].x = this.selectedArry[i].x + 1
          this.selectedArry[i].style = this.getStyle(this.selectedArry[i])
        }
      }
      if (d === 3) {
        for (let i = 0; i < this.selectedArry.length; i++) {
          this.selectedArry[i].y = this.selectedArry[i].y - 1
          this.selectedArry[i].style = this.getStyle(this.selectedArry[i])
        }
      }
      if (d === 4) {
        for (let i = 0; i < this.selectedArry.length; i++) {
          this.selectedArry[i].y = this.selectedArry[i].y + 1
          this.selectedArry[i].style = this.getStyle(this.selectedArry[i])
        }
      }
    },
    selected (item) {
      console.log(item)
      this.count = 0
      if (item.tag === 'text' || item.tag === 'customer') {
        if (item.css.indexOf('selected') === -1) {
          item.css = item.css + ' selected'
          this.selectedArry.push(item)
        } else {
          item.css = item.css.replace('selected', '')
          for (let i = 0; i < this.selectedArry.length; i++) {
            if (this.selectedArry[i].itemId === item.itemId) {
              this.selectedArry.splice(i, 1)
              break
            }
          }
        }
      }
    },
    dragstart (e) {
      console.log(e)
      let tag = e.target.getAttribute('tag')
      let text = e.target.getAttribute('data')
      let column = e.target.getAttribute('column')
      console.log(text)
      e.dataTransfer.setData('tag', tag)
      e.dataTransfer.setData('text', text)
      e.dataTransfer.setData('column', column)
    },
    dragmove (e) {
      let index = e.target.getAttribute('index')
      let tag = e.target.getAttribute('tag')
      console.log(e.target.getAttribute('id'))

      e.dataTransfer.setData('tag', tag)
      e.dataTransfer.setData('index', index)
      if (tag === 'text' || tag === 'customer') {
        for (let i = 0; i < this.selectedArry.length; i++) {
          if (this.selectedArry[i].itemId + '' === e.target.getAttribute('id')) {
            this.selectedArry.splice(i, 1)
            return
          }
        }
      }
    },
    drop (e) {
      console.log(e)
      let index = e.dataTransfer.getData('index')
      let tag = e.dataTransfer.getData('tag')
      let text = e.dataTransfer.getData('text')
      let column = e.dataTransfer.getData('column')

      this.tag = tag
      console.log(tag)
      if (index !== '') {
        console.log()
        if (tag === 'text' || tag === 'customer') {
          this.spanList[index].css = 'txt'
          this.spanList[index].x = e.pageX
          this.spanList[index].y = e.layerY<120?120:e.layerY
          this.spanList[index].style = this.getStyle(this.spanList[index])
        }
        if (tag === 'pic') {
          this.picList[index].css = 'pic'
          this.picList[index].x = e.pageX
          this.picList[index].y = e.layerY<120?120:e.layerY
          this.picList[index].style = this.getStyle(this.picList[index])
        }
      } else {
        if (tag === 'text') {
          let textEl = {}
          textEl.text = text
          textEl.itemId = 'txt' + new Date().getTime()
          textEl.tag = tag
          textEl.css = 'txt'
          textEl.dataColumn = column
          textEl.x = e.pageX
          textEl.y = e.layerY<120?120:e.layerY
          textEl.style = 'left: ' + e.pageX + 'px; top: ' + (e.layerY<120?120:e.layerY) + 'px;'
          this.spanList.push(textEl)
        }
        if (tag === 'customer') {
          let textEl = {}
          textEl.text = text
          textEl.itemId = 'txt' + new Date().getTime()
          textEl.tag = tag
          textEl.css = 'txt'
          textEl.x = e.pageX
          textEl.y = e.layerY<120?120:e.layerY
          textEl.style = 'left: ' + e.pageX + 'px; top: ' + (e.layerY<120?120:e.layerY) + 'px;'
          this.spanList.push(textEl)
          this.textItem = textEl
          this.textForm = textEl.text
          this.dialogCustomerVisible = true
        }
        if (tag === 'pic') {
          let picEl = {}
          picEl.url = text
          picEl.itemId = 'pic' + new Date().getTime()
          picEl.tag = tag
          picEl.css = 'pic'
          picEl.dataColumn = column
          picEl.width = 300
          picEl.height = 300
          picEl.x = e.pageX
          picEl.y = e.layerY<120?120:e.layerY
          picEl.style = 'left: ' + e.pageX + 'px; top: ' + (e.layerY<120?120:e.layerY) + 'px;'
          this.picList.push(picEl)
        }
      }

      // e.target.appendChild(newTag)
      // 如果可以实现直接向target添加 checkbox 组件，并且数据也能动态获取，那就最棒了，之所以这样想是因为，既然vue都实现了virtualDom，为什么还要使用创建元素的形式。所以向大家求助，支个招。thks everybody 。
      // 这块希望可以实现：将checkbox组件添加到div.right
    },
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
    fontStrong (result) {
      for (let i = 0; i < this.selectedArry.length; i++) {
        this.selectedArry[i].strong = result
        this.selectedArry[i].style = this.getStyle(this.selectedArry[i])
      }
    },
    fontStyle (result) {
      for (let i = 0; i < this.selectedArry.length; i++) {
        this.selectedArry[i].fontstyle = result
        this.selectedArry[i].style = this.getStyle(this.selectedArry[i])
      }
    },
    fontUnderLine (result) {
      for (let i = 0; i < this.selectedArry.length; i++) {
        this.selectedArry[i].underline = result
        this.selectedArry[i].style = this.getStyle(this.selectedArry[i])
      }
    },
    lineThrough (result) {
      for (let i = 0; i < this.selectedArry.length; i++) {
        this.selectedArry[i].linethrough = result
        this.selectedArry[i].style = this.getStyle(this.selectedArry[i])
      }
    },
    fontSize (result) {
      console.log(result)
      for (let i = 0; i < this.selectedArry.length; i++) {
        this.selectedArry[i].fontsize = result
        this.selectedArry[i].style = this.getStyle(this.selectedArry[i])
      }
    },
    setFontFamily (result) {
      console.log(result)
      for (let i = 0; i < this.selectedArry.length; i++) {
        this.selectedArry[i].fontfamily = result
        this.selectedArry[i].style = this.getStyle(this.selectedArry[i])
      }
    },
    setFontColor (result) {
      console.log(result)
      for (let i = 0; i < this.selectedArry.length; i++) {
        this.selectedArry[i].color = result
        this.selectedArry[i].style = this.getStyle(this.selectedArry[i])
      }
    },
    setBackGround (url) {
      console.log(url)
      this.background = url
    },
    clearBackGround () {
      this.background = ''
    },
    blackList () {
      this.$emit('blackList')
    }
  }

}
</script>
<style lang="scss" scoped>
  .menu{
    position: absolute;
    z-index: 10000;
    list-style: none;
    margin: 0;
    padding-top: 5px;
    background-color: whitesmoke;
  }
  .menu li{
    display: block;
    padding: 10px;
    cursor: pointer;
  }
  .menu li:hover {
    background-color: #999999;
  }
  .selected{
    border: dashed 1px red;
  }
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
    display: flex;
    padding: 10px;
  }
  .left {
    width: 200px;
    margin-right: 20px;
  }
  .right {
    box-shadow: 0 0 6px #f20;
    background-repeat: no-repeat;
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
