<template>
  <div class="term" @click="clearMenu">
    <template >
      <span v-if="bindData.number"   class="number " style="margin-right: 10px;">
        <i class="el-icon-menu" @click.stop="showTermMenu" style="color: #e0000f;cursor: pointer;"></i>
        {{SectionToChinese}}
      </span>
      <span v-if="bindData.title" class="number" >{{bindData.title}}</span>
      <br v-if="bindData.title">

      <template v-if="bindData.content" v-for="item in bindData.content">
        <template v-if="item.tag=='label'&&item.css!='br'" >{{item.text}}</template>
        <input v-if="item.tag=='inputText'" type="text" :class="item.css" v-model="item.value" :style="{width:item.width+'px'}">
        <template v-if="item.tag=='date'" >
          <el-date-picker v-model="dateForm" :clearable="false" type="year"></el-date-picker>
          <span>年</span>
          <el-date-picker v-model="dateForm" :clearable="false" type="month" format="MM"></el-date-picker>
          <span>月</span>
          <el-date-picker v-model="dateForm" :clearable="false" type="date" format="dd"></el-date-picker>
          <span>日</span>
        </template>
        <template v-if="item.tag=='beginTime'" >
          <span>自</span>
          <el-date-picker v-model="item.value" :clearable="false" type="year"></el-date-picker>
          <span>年</span>
          <el-date-picker v-model="item.value" :clearable="false" type="month" format="MM"></el-date-picker>
          <span>月</span>
          <el-date-picker v-model="item.value" :clearable="false" type="date" format="dd"></el-date-picker>
          <span>日</span>
        </template>
        <template v-if="item.tag=='endTime'" >
          <span>至</span>
          <el-date-picker v-model="item.value" :clearable="false" type="year"></el-date-picker>
          <span>年</span>
          <el-date-picker v-model="item.value" :clearable="false" type="month" format="MM"></el-date-picker>
          <span>月</span>
          <el-date-picker v-model="item.value" :clearable="false" type="date" format="dd"></el-date-picker>
          <span>日止</span>
        </template>
        <template v-if="item.tag=='sellPrice'" >
          {{item.text}}（小写）￥：
          <input  type="number" class="input" style="width: 80px;" @keyup="keyupEvent(item)" v-model="item.value" min="1" max="99999999">
          元，（大写）：<span style="letter-spacing:2px;border-bottom: 1px solid #000;display: inline-block;min-width: 150px;">{{item.chineseNumber}}</span>
        </template>
        <template v-if="item.tag=='money'" >
          （小写）￥：
          <input  type="number" class="input" style="width: 80px;" @keyup="keyupEvent(item)" v-model="item.value" min="1" max="99999999">
          元，（大写）：<span style="letter-spacing:2px;border-bottom: 1px solid #000;display: inline-block;min-width: 150px;">{{item.chineseNumber}}</span>
        </template>
        <template v-if="item.tag=='commissionA'" >
          {{item.text}}（小写）￥：
          <input  type="number" class="input" style="width: 80px;" @keyup="keyupEvent(item)" v-model="item.value" min="1" max="99999999">
          元，（大写）：<span style="letter-spacing:2px;border-bottom: 1px solid #000;display: inline-block;min-width: 150px;">{{item.chineseNumber}}</span>
        </template>
        <template v-if="item.tag=='commissionB'" >
          {{item.text}}（小写）￥：
          <input  type="number" class="input" style="width: 80px;" @keyup="keyupEvent(item)" v-model="item.value" min="1" max="99999999">
          元，（大写）：<span style="letter-spacing:2px;border-bottom: 1px solid #000;display: inline-block;min-width: 150px;">{{item.chineseNumber}}</span>
        </template>
        <template v-if="item.tag=='rentPrice'" >
          {{item.text}}（小写）￥：
          <input  type="number" class="input" style="width: 80px;" @keyup="keyupEvent(item)" v-model="item.value" min="1" max="99999999">
          元，（大写）：<span style="letter-spacing:2px;border-bottom: 1px solid #000;display: inline-block;min-width: 150px;">{{item.chineseNumber}}</span>
          每<el-checkbox v-model="item.annually"></el-checkbox>
          <span>年，</span>
          <el-checkbox v-model="item.monthly"></el-checkbox>
          <span>月</span>
        </template>
        <template v-if="item.tag=='propertyRrightNO'">
          <span>所有权证编号：</span>
          <input type="text" :class="item.css" v-model="item.value" :style="{width:item.width+'px'}">
        </template>
        <template v-if="item.tag=='area'">
          <input v-model="item.value" type="number" class="input area"  >
          <span  :class="item.css">{{item.text}}</span>
        </template>
        <template v-if="item.tag=='checkbox'">
          <el-checkbox></el-checkbox>
          <span>{{item.text}}</span>
        </template>
        <br v-if="item.css=='br'">
      </template>
      <br v-if="bindData.content&&bindData.content.length>0&&bindData.rows">
      <template v-if="bindData.rows" >
        <div class="dataRow" v-for="row in bindData.rows">
          <!--<ReadDataRow   :datas="row"></ReadDataRow>-->
          <template  v-for="item in row">
            <template v-if="item.tag=='label'&&item.css!='br'" >{{item.text}}</template>
            <input v-if="item.tag=='inputText'" type="text" :class="item.css" v-model="item.value" :style="{width:item.width+'px'}">
            <template v-if="item.tag=='date'" >
              <el-date-picker v-model="item.value" :clearable="false" type="year"></el-date-picker>
              <span>年</span>
              <el-date-picker v-model="item.value" :clearable="false" type="month" format="MM"></el-date-picker>
              <span>月</span>
              <el-date-picker v-model="item.value" :clearable="false" type="date" format="dd"></el-date-picker>
              <span>日</span>
            </template>
            <template v-if="item.tag=='beginTime'" >
              <span>自</span>
              <el-date-picker v-model="item.value" :clearable="false" type="year"></el-date-picker>
              <span>年</span>
              <el-date-picker v-model="item.value" :clearable="false" type="month" format="MM"></el-date-picker>
              <span>月</span>
              <el-date-picker v-model="item.value" :clearable="false" type="date" format="dd"></el-date-picker>
              <span>日</span>
            </template>
            <template v-if="item.tag=='endTime'" >
              <span>至</span>
              <el-date-picker v-model="item.value" :clearable="false" type="year"></el-date-picker>
              <span>年</span>
              <el-date-picker v-model="item.value" :clearable="false" type="month" format="MM"></el-date-picker>
              <span>月</span>
              <el-date-picker v-model="item.value" :clearable="false" type="date" format="dd"></el-date-picker>
              <span>日止</span>
            </template>
            <template v-if="item.tag=='sellPrice'" >
              {{item.text}}（小写）￥：
              <input  type="number" class="input" style="width: 80px;" @keyup="keyupEvent(item)" v-model="item.value" min="1" max="99999999">
              元，（大写）：<span style="letter-spacing:2px;border-bottom: 1px solid #000;display: inline-block;min-width: 150px;">{{item.chineseNumber}}</span>
            </template>
            <template v-if="item.tag=='money'" >
              （小写）￥：
              <input  type="number" class="input" style="width: 80px;" @keyup="keyupEvent(item)" v-model="item.value" min="1" max="99999999">
              元，（大写）：<span style="letter-spacing:2px;border-bottom: 1px solid #000;display: inline-block;min-width: 150px;">{{item.chineseNumber}}</span>
            </template>
            <template v-if="item.tag=='commissionA'" >
              {{item.text}}（小写）￥：
              <input  type="number" class="input" style="width: 80px;" @keyup="keyupEvent(item)" v-model="item.value" min="1" max="99999999">
              元，（大写）：<span style="letter-spacing:2px;border-bottom: 1px solid #000;display: inline-block;min-width: 150px;">{{item.chineseNumber}}</span>
            </template>
            <template v-if="item.tag=='commissionB'" >
              {{item.text}}（小写）￥：
              <input  type="number" class="input" style="width: 80px;" @keyup="keyupEvent(item)" v-model="item.value" min="1" max="99999999">
              元，（大写）：<span style="letter-spacing:2px;border-bottom: 1px solid #000;display: inline-block;min-width: 150px;">{{item.chineseNumber}}</span>
            </template>
            <template v-if="item.tag=='rentPrice'" >
              {{item.text}}（小写）￥：
              <input  type="number" class="input" style="width: 80px;" @keyup="keyupEvent(item)" v-model="item.value" min="1" max="99999999">
              元，（大写）：<span style="letter-spacing:2px;border-bottom: 1px solid #000;display: inline-block;min-width: 150px;">{{item.chineseNumber}}</span>
              每<el-checkbox v-model="item.annually"></el-checkbox>
              <span>年，</span>
              <el-checkbox v-model="item.monthly"></el-checkbox>
              <span>月</span>
            </template>
            <template v-if="item.tag=='propertyRrightNO'">
              <span>所有权证编号：</span>
              <input type="text" :class="item.css" v-model="item.value" :style="{width:item.width+'px'}">
            </template>
            <template v-if="item.tag=='area'">
              <input v-model="item.value" type="number" class="input area"  >
              <span  :class="item.css">{{item.text}}</span>
            </template>
            <template v-if="item.tag=='checkbox'">
              <el-checkbox></el-checkbox>
              <span>{{item.text}}</span>
            </template>
            <br v-if="item.css=='br'">
          </template>
        </div>
      </template>
    </template>
    <ul class="menu" :style="menuCss">
      <li  @click="delItem">删除条款</li>
      <li  @click="showMoveDialog">移动条款位置</li>
      <li  @click="editItem">编辑条款</li>
      <li  @click="addItem">新增条款</li>
    </ul>
    <el-dialog title="移动条款位置" width="400px" :visible.sync="dialogMoveVisible">
      <el-form  label-position="right" label-width="80px" v-model="moveParam">
        <el-form-item label="当前条款" >
          <label>{{SectionToChinese}}</label>
        </el-form-item>
        <el-form-item label="移动至" >
          <el-select v-model="moveParam.toIndex" placeholder="请选择" @change="selectToIndex">
            <el-option
              v-for="item in termData"
              :key="item.value"
              :label="item.name"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="位置">
          <el-radio-group v-model="moveParam.post">
            <el-radio :label="0" :disabled="frontDisabled" border>前面</el-radio>
            <el-radio :label="1" :disabled="behindDisabled" border>后面</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary"  @click="movePost">确 定</el-button>
        <el-button @click="dialogMoveVisible = false">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog title="编辑条款"  :visible.sync="dialogEditVisible">
      <div style="overflow: auto;">
        <el-form ref="form" :model="editTermData" label-width="80px">
          <el-form-item label="标题">
            <input v-model="editTermData.title" style="width: 80%;height: 30px;"></input>
          </el-form-item>
          <el-form-item label="描述">
            <el-row>
              <el-col :span="20">
                <div>
                  <ReadDataRow  :datas="editTermData.content"></ReadDataRow>
                </div>
              </el-col>
              <el-col :span="4">
                <div >
                  <el-tooltip class="item" effect="dark" content="编辑" placement="bottom" >
                    <el-button icon="el-icon-edit-outline" size="mini" type="primary" @click="editData(-1)"></el-button>
                  </el-tooltip>
                </div>
              </el-col>
            </el-row>

          </el-form-item>
          <el-form-item label="子条款">
            <template v-for="(row,index) in editTermData.rows">
              <el-row>
                <el-col :span="20">
                  <ReadDataRow   :datas="row"></ReadDataRow>
                </el-col>
                <el-col :span="4">
                  <div >
                    <el-tooltip class="item" effect="dark" content="编辑" placement="bottom" >
                      <el-button icon="el-icon-edit-outline" size="mini" type="primary" @click="editData(index)"></el-button>
                    </el-tooltip>
                    <el-tooltip class="item" effect="dark" content="删除" placement="bottom" >
                      <el-button icon="el-icon-delete" size="mini" type="primary" @click="delRowData(index)"></el-button>
                    </el-tooltip>
                  </div>
                </el-col>
              </el-row>
            </template>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary"  @click="saveTerm">确 定</el-button>
          <el-button type="primary"  @click="addRow">增加子条款</el-button>
          <el-button @click="dialogEditVisible = false">取 消</el-button>
        </div>
      </div>
    </el-dialog>
    <el-dialog title="编辑条款项"  :visible.sync="dialogEditContentVisible">
      <div>
        <EditDataRow v-if="dialogEditContentVisible" :initData="currentData" @cancelSave="cancelSave" @saveRowData="saveRowData"></EditDataRow>
      </div>
    </el-dialog>

    <el-dialog title="新增条款"  :visible.sync="dialogAddVisible">
      <div>
        <el-form ref="form" :model="newTerm" label-width="80px">
          <el-form-item label="标题">
            <input v-model="newTerm.title" style="width: 80%;height: 30px;"></input>
          </el-form-item>
          <el-form-item label="描述">
            <el-row>
              <el-col :span="20">
                <div>
                  <ReadDataRow :datas="newTerm.content" ></ReadDataRow>
                </div>
              </el-col>
              <el-col :span="4">
                <div >
                  <el-tooltip class="item" effect="dark" content="编辑" placement="bottom" >
                    <el-button icon="el-icon-edit-outline" size="mini" type="primary" @click="editData(-1)"></el-button>
                  </el-tooltip>
                </div>
              </el-col>
            </el-row>

          </el-form-item>
          <el-form-item label="子条款">
            <template v-for="(row,index) in newTerm.rows">
              <el-row>
                <el-col :span="20">
                  <ReadDataRow   :datas="row"></ReadDataRow>
                </el-col>
                <el-col :span="4">
                  <div >
                    <el-tooltip class="item" effect="dark" content="编辑" placement="bottom" >
                      <el-button icon="el-icon-edit-outline" size="mini" type="primary" @click="editData(index)"></el-button>
                    </el-tooltip>
                    <el-tooltip class="item" effect="dark" content="删除" placement="bottom" >
                      <el-button icon="el-icon-delete" size="mini" type="primary" @click="delRowData(index)"></el-button>
                    </el-tooltip>
                  </div>
                </el-col>
              </el-row>
            </template>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary"  @click="saveTerm">确 定</el-button>
          <el-button type="primary"  @click="addRow">增加子条款</el-button>
          <el-button @click="dialogAddVisible = false">取 消</el-button>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import EditDataRow from './EditDataRow'
import ReadDataRow from './ReadDataRow.vue'
export default{
  data () {
    return {
      dialogAddVisible: false,
      dialogEditContentVisible: false,
      dialogEditVisible: false,
      dialogMoveVisible: false,
      isNewAdd: false,
      menuCss: 'display:none;',
      moveParam: {
        'index': this.index,
        'toIndex': 0,
        'post': 0
      },
      tempTitle: '',
      termData: [],
      currentData: [],
      bindData: {},
      newTerm: {'number':'','title':'','content':[],'rows':[]},
      editTermData: {},
      editDataIndex: -1,
      frontDisabled: false,
      behindDisabled: false,
      dateForm: '',
      baiWanItem: [
        {'tag': 'text', 'css': 'txt', 'value': '', 'formType': '', 'text': '（大写）'},
        {'tag': 'form', 'css': 'input money', 'value': '', 'formType': 'text', 'text': ''},
        {'tag': 'text', 'css': 'txt', 'value': '', 'formType': '', 'text': '佰'},
        {'tag': 'form', 'css': 'input money', 'value': '', 'formType': 'text', 'text': ''},
        {'tag': 'text', 'css': 'txt', 'value': '', 'formType': '', 'text': '拾'},
        {'tag': 'form', 'css': 'input money', 'value': '', 'formType': 'text', 'text': ''},
        {'tag': 'text', 'css': 'txt', 'value': '', 'formType': '', 'text': '万'},
        {'tag': 'form', 'css': 'input money', 'value': '', 'formType': 'text', 'text': ''},
        {'tag': 'text', 'css': 'txt', 'value': '', 'formType': '', 'text': '千'},
        {'tag': 'form', 'css': 'input money', 'value': '', 'formType': 'text', 'text': ''},
        {'tag': 'text', 'css': 'txt', 'value': '', 'formType': '', 'text': '佰'},
        {'tag': 'form', 'css': 'input money', 'value': '', 'formType': 'text', 'text': ''},
        {'tag': 'text', 'css': 'txt', 'value': '', 'formType': '', 'text': '拾'},
        {'tag': 'form', 'css': 'input money', 'value': '', 'formType': 'text', 'text': ''},
        {'tag': 'text', 'css': 'txt', 'value': '', 'formType': '', 'text': '元整'}
      ],
      wanYuanItem: [
        {'tag': 'text', 'css': 'txt', 'value': '', 'formType': '', 'text': '（大写）'},
        {'tag': 'form', 'css': 'input money', 'value': '', 'formType': 'text', 'text': ''},
        {'tag': 'text', 'css': 'txt', 'value': '', 'formType': '', 'text': '万'},
        {'tag': 'form', 'css': 'input money', 'value': '', 'formType': 'text', 'text': ''},
        {'tag': 'text', 'css': 'txt', 'value': '', 'formType': '', 'text': '千'},
        {'tag': 'form', 'css': 'input money', 'value': '', 'formType': 'text', 'text': ''},
        {'tag': 'text', 'css': 'txt', 'value': '', 'formType': '', 'text': '佰'},
        {'tag': 'form', 'css': 'input money', 'value': '', 'formType': 'text', 'text': ''},
        {'tag': 'text', 'css': 'txt', 'value': '', 'formType': '', 'text': '拾'},
        {'tag': 'form', 'css': 'input money', 'value': '', 'formType': 'text', 'text': ''},
        {'tag': 'text', 'css': 'txt', 'value': '', 'formType': '', 'text': '元整'}
      ],
      xiaoXieItem: [
        {'tag': 'text', 'css': 'txt', 'value': '', 'formType': '', 'text': '（小写）￥'},
        {'tag': 'form', 'css': 'input number', 'value': '', 'formType': 'number', 'text': ''},
        {'tag': 'text', 'css': 'txt', 'value': '', 'formType': '', 'text': '元'}
      ],
      areaItem: [
        {'tag': 'form', 'css': 'input area', 'value': '', 'formType': 'number', 'text': ''},
        {'tag': 'text', 'css': 'txt', 'value': '', 'formType': '', 'text': '平米'}
      ]
    }
  },
  components: {
    EditDataRow,ReadDataRow
  },
  props: ['term','index','total'],
  mounted () {
     this.init()
  },
  computed: {
    SectionToChinese () {
      let section = this.index + 1
      return this.numberUpper(section)
    }
  },
  methods: {
    deepClone(obj) {
      var o = obj instanceof Array ? [] : {};
      for(var k in obj)
        o[k] = typeof obj[k] === Object ? this.deepClone(obj[k]) : obj[k];
      return o;
    },
      init () {
        /*this.bindData.number = ''
        if (this.term.title) {
          this.bindData.title = this.term.title
        } else {
          this.bindData.title =''
        }
        if (this.term.content) {
          this.bindData.content = JSON.parse(JSON.stringify(this.term.content))
        } else {
          this.bindData.content = []
        }
        if (this.term.rows) {
          this.bindData.rows = JSON.parse(JSON.stringify(this.term.rows))
        } else {
          this.bindData.rows = []
        }*/
        this.bindData = this.deepClone(this.term)
        console.log(this.total)
        for (let i = 0; i < this.total; i++){
          if(i != this.index) {
            let item = {}
            item.value = i
            item.name = this.numberUpper(i+1)
            this.termData.push(item)
          }
        }
        this.moveParam.toIndex = this.termData[0].value
        this.selectToIndex(this.moveParam.toIndex)
        console.log(this.bindData)
      },
    numberUpper (section) {
      let chnNumChar = ["零","一","二","三","四","五","六","七","八","九"]
      let chnUnitChar = ["","十","百","千","万"]
      let strIns = ''
      let chnStr = ''
      let unitPos = 0
      let zero = true
      while(section > 0 && unitPos < 6){
        let v = section % 10
        if(v === 0){
          if(!zero){
            zero = true
            chnStr = chnNumChar[v] + chnStr
          }
        }else{
          zero = false
          strIns = chnNumChar[v]
          strIns += chnUnitChar[unitPos]
          chnStr = strIns + chnStr
        }
        unitPos++
        section = Math.floor(section / 10)
      }
      if (chnStr.indexOf('一十') === 0) {
        chnStr = chnStr.substring(1)
      }
      return '第' + chnStr + '条'
    },
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
    keyupEvent (item) {
      item.chineseNumber = this.toChineseNumber(item.value)
    },
    showTermMenu (e) {
        console.log(e)
      this.$emit('clearMenu')
      this.menuCss = 'display:block;left:' + e.layerX + 'px;top:' + (e.layerY) + 'px;'
    },
    clearMenu () {
      this.menuCss = 'display:none;'
    },
    delItem () {
      this.$confirm('确定要删除【' + this.SectionToChinese + '】吗？', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$emit('delTerm',this.index)
      }).catch(() => {

      })
      this.menuCss = 'display:none;'
    },
    showMoveDialog () {
      this.dialogMoveVisible = true
      this.menuCss = 'display:none;'
    },
    selectToIndex (toIndex) {
      this.moveParam.post = 0
      this.frontDisabled = false
      this.behindDisabled = false
      if (toIndex === (this.index + 1)) {
        this.frontDisabled = true
        this.behindDisabled = false
        this.moveParam.post = 1
      }
      if (toIndex === (this.index - 1)) {
        this.frontDisabled = false
        this.behindDisabled = true
      }
    },
    movePost () {
      console.log(this.moveParam)
      this.$emit('movePost',this.moveParam)
      this.dialogMoveVisible = false
    },
    editItem (data) {
       this.editTermData = JSON.parse(JSON.stringify(this.bindData))
        console.log(this.editTermData)
      this.tempTitle = this.editTermData.title
      this.dialogEditVisible = true
      this.isNewAdd = false
      this.menuCss = 'display:none;'
    },
    addItem () {
      this.isNewAdd = true
      this.newTerm = {'number':'','title':'','content':[],'rows':[]}
      this.dialogAddVisible = true
      this.menuCss = 'display:none;'
    },
    editData (data) {
      if (this.isNewAdd) {
        if (data >=0 ) {
          this.currentData = this.newTerm.rows[data]
        } else {
          this.currentData =  this.newTerm.content
        }
      } else {
         console.log(this.editTermData)
        if (data >=0 ) {
          this.currentData = this.editTermData.rows[data]
        } else {
          this.currentData =  this.editTermData.content
        }
      }

      this.dialogEditContentVisible = true
      this.editDataIndex = data
    },
    cancelSave () {
      this.dialogEditContentVisible = false
    },
    saveRowData (result) {
      if (this.isNewAdd) {
        if (this.editDataIndex < 0) {
          this.newTerm.content =  result
        } else if (this.editDataIndex === this.newTerm.rows.length) {
          this.newTerm.rows.push(result)
        } else {
          this.newTerm.rows.splice(this.editDataIndex,1,result)
        }
      } else {
        if (this.editDataIndex < 0) {
          this.editTermData.content =  result
        } else if (this.editDataIndex === this.editTermData.rows.length) {
          this.editTermData.rows.push(result)
        } else {
          this.editTermData.rows.splice(this.editDataIndex,1,result)
        }
      }

      this.dialogEditContentVisible = false
    },
    delRowData (index) {
      console.log(index)
      console.log(this.editTermData)
      if (this.isNewAdd) {
        this.newTerm.rows.splice(index,1)
      } else {
        this.editTermData.rows.splice(index,1)
      }
    },
    addRow () {
      this.currentData=[]
      if (this.isNewAdd) {
        this.editDataIndex = this.newTerm.rows.length
      } else {
        this.editDataIndex = this.editTermData.rows.length
      }

      this.dialogEditContentVisible = true
    },
    saveTerm () {
      this.dialogAddVisible = false
      if (this.isNewAdd) {
        this.newTerm.number  = this.index+1
        this.$emit('addTerm',this.newTerm,this.index,1)
      } else {
        this.$emit('addTerm',this.editTermData,this.index,0)
      }

    }
  },
  watch: {
    term: {
      handler () {
          this.init()
      },
      deep: true
    },
  }
}
</script>

<style lang="scss" scoped>
  .number{
    font-weight:bold;
    font-size: 16px;
  }
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
</style>
