<template>
  <div  @click="clearMenu">

    <div id="contract" class="contract">
      <div style="text-align: right;">
        <span>合同编号：</span>
        <span>{{codePrefix}}</span>
        <input  class="input">
      </div>
      <div style="text-align: center;font-size: 20px;font-weight: bold;padding-bottom: 20px;">{{contractName}}</div>
      <template v-for="(item,key) in participant">
        <el-row :gutter="20">
          <el-col :span="8" ><div class="leftRetraction"><label>{{item.name}}：</label><input v-model="item.man" class="input"></div></el-col>
          <el-col :span="8" ><div><label>身份证编号：</label><input v-model="item.identity" class="input"></div></el-col>
          <el-col :span="8"><div class="rightRetraction"><label>电话：</label><input v-model="item.phone" class="input"></div></el-col>
        </el-row>
      </template>
      <div class="leftRetraction">
        <label>居间方：</label>
        <label><i class="el-icon-menu" @click="showDialog(1)" style="color: #e0000f;cursor: pointer;"></i>{{companyName}}</label>
        <input  class="input">
        <label>分公司</label>
      </div>
      <div>
        <p style="text-indent:2em ;line-height: 24px;font-size: 16px;"><i class="el-icon-menu" @click="showDialog(2)" style="color: #e0000f;cursor: pointer;"></i>{{declaration}}</p>
      </div>
      <term  ref="termItem" v-for="(item,index) in terms" :term="item" :key="index + '_' + new Date().getTime()" :index="index" :total="terms.length"
            @delTerm="delTerm" @movePost="movePost" @addTerm="addTerm" @clearMenu="clearMenu"></term>
      <div style="padding-top: 15px;">
        <el-row :gutter="20" v-if="initData.templateType==='01'">
          <el-col :span="8" ><div class="leftRetraction"><label>出卖人（签字）：</label><input v-model="partyA" class="input"></div></el-col>
          <el-col :span="8" ><div><label>买受人（签字）：</label><input v-model="partyB" class="input"></div></el-col>
          <el-col :span="8" ><div><label>经办人（签字）：</label><input v-model="partyC" class="input"></div></el-col>
        </el-row>
        <el-row :gutter="20" v-if="initData.templateType==='02'">
          <el-col :span="8" ><div class="leftRetraction"><label>出租人（签字）：</label><input v-model="partyA"  class="input"></div></el-col>
          <el-col :span="8" ><div><label>承租人（签字）：</label><input v-model="partyB" class="input"></div></el-col>
          <el-col :span="8" ><div><label>经办人（签字）：</label><input v-model="partyC"  class="input"></div></el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="10" :offset="14">
            <div class="" style="text-align: right;padding-top: 15px;">
              <el-date-picker v-model="overDate" :clearable="false" type="year"></el-date-picker>
              <span>年</span>
              <el-date-picker v-model="overDate" :clearable="false" type="month" format="MM"></el-date-picker>
              <span>月</span>
              <el-date-picker v-model="overDate" :clearable="false" type="date" format="dd"></el-date-picker>
              <span>日签订</span>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
    <div id="printdiv" style="text-align: center;margin-top: 40px;">
      <el-button type="primary" @click="saveData">保 存</el-button>
      <el-button type="info" @click="blackList">返 回</el-button>
    </div>
    <el-dialog title="编辑" width="600px" :visible.sync="dialogNameVisible">
      <el-form  label-position="right" :model="contentForm"  ref="contentForm" :rules="rules">
        <el-form-item  prop="name">
          <el-input type="textarea" autosize v-model="contentForm.name"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary"  @click="saveTemplate(1)">确 定</el-button>
        <el-button @click="dialogNameVisible = false">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog title="编辑" width="600px" :visible.sync="dialogContentVisible">
      <el-form  label-position="right" :model="contentForm"  ref="contentForm" :rules="rules">
        <el-form-item  prop="content">
          <el-input type="textarea" autosize v-model="contentForm.content"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <div>
          <el-button id="saveBtn" v-hasOnlyBtn="'saveBtn'" type="primary"  @click="saveTemplate(2)">确 定</el-button>
          <el-button @click="dialogContentVisible = false">取 消</el-button>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>

import Term from './Term'
import html2Canvas from 'html2canvas'
//import JsPDF from 'jspdf'
export default{
  data () {
    return {
      partyA:'',
      partyB:'',
      partyC:'',
      overDate:'',
      codePrefix:'C-MM-',
      contentForm:{'content':'','name':''},
      dialogContentVisible:false,
      dialogNameVisible:false,
      contractName: '房屋买卖合同',
      companyName:'云南巴适居房地产经贸有限公司',
      declaration:'根据《中华人民共和国合同法》、《中华人民共和国城市房地产管理法》及其他有关法律、法规之规定，甲、乙双方在平等、自愿、协商一致的基础上，就房屋买卖事宜达成如下协议：',
      rules: {
        content: [
          { required: true, message: '请输入内容', trigger: 'blur' },
          { min: 3, max: 300, message: '长度在 3 到 300 个字符', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入内容', trigger: 'blur' },
          { min: 2, max: 50, message: '长度在 2 到 50 个字符', trigger: 'blur' }
        ]
      },
      participant: [],
      sellMan: [
        {'name':'出卖人','man':'','identity':'','phone':'','type':1},
        {'name':'共有人','man':'','identity':'','phone':'','type':2},
        {'name':'委托代理人','man':'','identity':'','phone':'','type':3},
        {'name':'买受人','man':'','identity':'','phone':'','type':4},
        {'name':'委托代理人','man':'','identity':'','phone':'','type':5}
      ],
      rentMan: [
        {'name':'出租人','man':'','identity':'','phone':'','type':1},
        {'name':'共有人','man':'','identity':'','phone':'','type':2},
        {'name':'委托代理人','man':'','identity':'','phone':'','type':3},
        {'name':'承租人','man':'','identity':'','phone':'','type':4},
        {'name':'委托代理人','man':'','identity':'','phone':'','type':5}
      ],
      terms: [
        {
        number: '第一条',
        title: '房屋情况',
        content: [],
        rows: [
         [
           {'tag': 'label', 'css': 'txt', 'value': '', 'text': '1 付款方式'},
           {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '一次性付款'},
           {'tag': 'checkbox', 'css': '', 'value': '0', 'text': '按揭贷款支付'}
         ],
         [
           {'tag': 'label', 'css': 'txt', 'value': '',  'text': '2 乙方未按本合同规定的付款方式付款，逾期达一个月以上的，即视为乙方不履行本合同，甲方有权解除合同，届时将由乙方承担此次交易中双方的全部交易税费，并向甲方支付购房款10%违约金。'},
         ]
      ]
      },{
       number: '第二条',
       title: '房屋权属',
       content: [{'tag': 'label', 'css': 'txt', 'value': '', 'text': '本合同未尽事宜，由甲、乙双方另行议定，并签订补充协议，补充协议与本合同具同等法律效力。'}],
          rows:[]
      },
      {
        number: '第三条',
          title: '',
        content: [{'tag': 'label', 'css': 'txt', 'value': '',  'text': '其他补充'},
          {'tag': 'inputText', 'css': 'input', 'value': '忙死了奇葩', 'text': '的关于推进','width':400}
        ],
        rows:[]
      }
      ],
      rentTerms: [
      {
        number: '第一条',
        title: '出租物业坐落地点及设施情况',
        content: [],
        rows: [
          [
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '1 甲方将其拥有的位于'},
            {'tag': 'inputText', 'css': 'input', 'value': '昆明', 'text': '','width':40},
            {'tag': 'label', 'css': '', 'value': '0', 'text': '市'},
            {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':50},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '区'},
            {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':50},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '小区'},
            {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':50},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '栋'},
            {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':30},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '单元'},
            {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':40},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '号房屋及昆明市'},
            {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':50},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '区'},
            {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':50},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '小区'},
            {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':50},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '车位(以下简称为“该房屋”)租给乙方使用，用途为'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '居住，'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '办公，'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '其他，'},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '房屋建筑面积为'},
            {'tag': 'area', 'css': 'txt', 'value': '',  'text': '平米'},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '。在签署本合同前甲方已经获得该房屋共有人对出租该房屋的一致同意。'}
          ],
          [
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '2 该房屋现有装修及设施情况见附件。除双方另有约定外，该附件做为甲方按照本合同约定交付乙方使用和乙方在本合同租赁期满交还该房屋的验证依据。'}
          ]
        ]
      },
      {
        number: '第二条',
        title: '租赁期限',
        content: [],
        rows: [
          [
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '1 该房屋租赁期限共'},
            {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':30},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '年，'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '月，'},
            {'tag': 'beginTime', 'css': '', 'value': '',  'text': ''},
            {'tag': 'endTime', 'css': '', 'value': '',  'text': ''}
          ],
          [
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '2 租赁期满，甲方有权收回该房屋，乙方应如期交还。乙方若要求续租，则必须在租赁期满前一个月内通知甲方，经甲方同意后重新订立租赁合同。'}
          ]
        ]
      },
      {
        number: '第三条',
        title: '租金及支付方式',
        content: [],
        rows: [
          [
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '1 签署本合同时支付定金人民币'},
            {'tag': 'money', 'css': 'txt', 'value': '', 'text': '','chineseNumber':' '},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '以甲方收款收据为准，此定金计入首期租金。'}
          ],
          [
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '2 该房屋租金为人民币'},
            {'tag': 'rentPrice', 'css': 'txt', 'value': '', 'text': '','chineseNumber':' ','annually':false,'monthly':false},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '(此价格不含相关税费)，按'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '一年，'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '半年，'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '三个月，'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '其他：'},
            {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':60},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '一付，首期租金总额为人民币'},
            {'tag': 'money', 'css': 'txt', 'value': '', 'text': '','chineseNumber':' '},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '，租金支付时间为'},
            {'tag': 'date', 'css': '', 'value': '',  'text': ''},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '，以甲方收款收据为准。后期租金于上期租金到期前一个月内支付。'}
          ],
          [
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '3 在签订本合同时，乙方须向丙方交纳中介服务费人民币'},
            {'tag': 'commissionB', 'css': 'txt', 'value': '', 'text': '','chineseNumber':' '},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '。'}
          ],
          [
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '4 4.乙方若逾期支付租金，每逾期一天，则乙方需按月租金的0.5％支付滞纳金。拖欠租金超过一个月，甲方有权收回该房屋，乙方须按实际居住天数交纳租金并承担违约责任。'}
          ]
        ]
      },
      {
        number: '第四条',
        title: '关于押金',
        content: [
          {'tag': 'label', 'css': 'txt', 'value': '', 'text': '为了保证乙方在租赁期间合理并善意地使用该房屋及配套设施，乙方应在'},
          {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '签订本合同并于交纳首期租金时，'},
          {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '租赁期限界满'},
          {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':30},
          {'tag': 'label', 'css': 'txt', 'value': '', 'text': '个月前，支付给出租人人民币'},
          {'tag': 'money', 'css': 'txt', 'value': '', 'text': '','chineseNumber':' '},
          {'tag': 'label', 'css': 'txt', 'value': '', 'text': '做为押金。待租赁期限届满，乙方结清应结费用，甲方验房并认可后，乙方将该房屋钥匙交与甲方，同时甲方将此押金全部归还乙方。'}
        ],
        rows: [ ]
      },
      {
        number: '第五条',
        title: '租赁条件',
        content: [],
        rows: [
          [
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '1 甲方应向乙方出示该房屋的所有权证或有权决定该房屋出租的相关证明。'}
          ],
          [
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '2 甲方应保证该房屋的出租不违反国家法律法规的相关规定并保证自己有权决定此租赁事宜；合同签订后双方当事人应按国家规定进行备案。'}
          ],
          [
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '3 乙方不得在该房屋内进行违反法律法规及政府对出租房屋用途有关规定的行为。'}
          ],
          [
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '4 未经甲方书面同意，乙方不得将该房屋部分或全部转租他人。若擅自转租，甲方有权终止合同，由乙方承担对甲方及第三方的违约责任。'}
          ],
          [
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '5 乙方承担租赁期内（'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '电话费，'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '水费，'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '电费，'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '煤气费，'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '物业管理费，'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '治安费，'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '收视费，'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '发票等'},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '）实际使用的费用，若有特殊约定则从其约定。'}
          ],
          [
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '6 因乙方使用不当或不合理使用致使该房屋或其内部设施出现损坏或发生故障，乙方应及时联络进行维修并负担所发生的费用。由于不可抗力及非乙方原因造成的损失由甲方负责承担有关维修的费用，如经维修确系乙方不能正常使用双方协商解决。'}
          ],
          [
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '7 租赁期内乙方需要对该房屋或屋内设施进行装修或改动，须经甲方书面同意并经政府有关部门批准，甲方有权对装修或改动情况进行监督。租赁期满时乙方'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '可以，'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '不得 '},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '移走自行添加的结构性设施，甲方'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '应当，'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '无须 '},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '对以上设施进行补偿。'}
          ]
        ]
      },
      {
        number: '第六条',
        title: '产权变更',
        content: [
          {'tag': 'label', 'css': 'txt', 'value': '', 'text': ' 如甲方依法定程序将该房屋所有权转移给第三方时，应提前书面通知乙方，并保证乙方可以依本合同的规定继续使用该房屋，否则视甲方违约。甲方若出售该房屋，同等条件下乙方拥有优先购买权。'}
        ],
        rows: [ ]
      },
      {
        number: '第七条',
        title: '合同的终止',
        content: [],
        rows: [
          [
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '1 租赁期限届满或经甲、乙双方协商一致本合同终止。'}
          ],
          [
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '2 乙方应在期满当日将房屋钥匙及正常使用状态下的附件中所列物品交给甲方，乙方所遗留物品均视为放弃，甲方有权处置，乙方绝无异议。'}
          ],
          [
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '3 若甲、乙双方中的一方违约．另一方有权终止合同，并向对方提出赔偿要求。'}
          ]
        ]
      },
      {
        number: '第八条',
        title: '违约的处理',
        content: [],
        rows: [
          [
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '1 甲方违约的处理'},
            {'tag': 'label', 'css': 'br', 'value': '',  'text': '换行'},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '1) 甲方未按合同规定的时间将功能完备及附属设施完好的房屋提供乙方使用的，每逾期一天，甲方应按月租金的0.5%向乙方支付违约金，逾期7天仍不履行，乙方有权终止合同。甲方应按上述规定支付违约金，若乙方实际损失超过违约金的，甲方应据实赔偿。'},
            {'tag': 'label', 'css': 'br', 'value': '',  'text': '换行'},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '2) 自合同签订之日起到租赁期届满若非乙方过失甲方擅自解除本合同提前收回该房屋的，甲方应按年度总租金的20％向乙方支付违约金，若违约金不足弥补乙方损失的，甲方应另行赔偿。'}
          ],
          [
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '2 乙方违约的处理'},
            {'tag': 'label', 'css': 'br', 'value': '',  'text': '换行'},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '1) 未经甲方书面同意，乙方擅自将房屋转租、转借，擅自拆改结构或改变用途的；利用该房屋进行违法活动的；拖欠房租壹个月以上的，乙方应向甲方支付相当于年租金20％的违约金，若违约金不足弥补甲方损失的，乙方还应另行据实赔偿，同时甲方有权终止合同。'},
            {'tag': 'label', 'css': 'br', 'value': '',  'text': '换行'},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '2) 租赁期内乙方逾期交纳本合同第五条第五款所规定的应由乙方交纳的费用达壹个月时，甲方有权用押金支付上述费用，乙方承担造成的一切后果。'},
            {'tag': 'label', 'css': 'br', 'value': '',  'text': '换行'},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '3) 租赁期限届满，若乙方未能将设施完好的房屋及时交给甲方，乙方应按原日租金的贰倍按实际天数向甲方支付违约金。'},
            {'tag': 'label', 'css': 'br', 'value': '',  'text': '换行'},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '4) 自合同签订之日起到租赁期届满若乙方中途擅自退租，所预付的租金及押金甲方可不予退还。'}
          ]
        ]
      },
      {
        number: '第九条',
        title: '免责条款',
        content: [
          {'tag': 'label', 'css': 'txt', 'value': '', 'text': ' 房屋及其附属设施由于不可抗力造成的损失，政府政策等原因导致合同不能全面履行的，甲、乙双方互不承担责任。租金按实际使用时间计算，多退少补。'}
        ],
        rows: [ ]
      },
      {
        number: '第十条',
        title: '特别约定',
        content: [
          {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':1000},
          {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':1000},
          {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':1000}
        ],
        rows: []
      },
      {
        number: '第十一条',
        title: '其他',
        content: [ ],
        rows: [
          [{'tag': 'label', 'css': 'txt', 'value': '', 'text': '1 本合同共四页（含附件），一式三份，甲、乙双方各执一份，由云南华安恒大房地产经纪有限公司备案一份。'}],
          [{'tag': 'label', 'css': 'txt', 'value': '', 'text': '2 甲乙双方履行本合同时所发生的争议应协商解决，协商不成的可以向房屋所在地人民法院起诉。'}],
          [{'tag': 'label', 'css': 'txt', 'value': '', 'text': '3 本合同附件是本合同不可分割的一部分，与本合同有同等的法律效力。'}],
          [{'tag': 'label', 'css': 'txt', 'value': '', 'text': '4 云南华安恒大房地产经纪有限公司作为丙方，仅对上述合同条款不违反国家相关法律的强制性规定予以见证，甲乙双方在签定、履行本协议过程中产生的纠纷应由双方自行解决。'}]
        ]
      }
    ],
      sellTerms:[
      {
        number: '第一条',
        title: '房屋基本情况',
        content: [],
        rows: [
          [
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '1 房屋'},
            {'tag': 'label', 'css': 'br', 'value': '',  'text': '换行'},
            {'tag': 'label', 'css': '', 'value': '0', 'text': '坐落： '},
            {'tag': 'inputText', 'css': 'input', 'value': '昆明', 'text': '','width':40},
            {'tag': 'label', 'css': '', 'value': '0', 'text': '市'},
            {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':50},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '区'},
            {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':50},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '小区'},
            {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':50},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '栋'},
            {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':30},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '单元'},
            {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':40},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '号房屋'},
            {'tag': 'label', 'css': '', 'value': '', 'text': '，结构： 建筑面积'},
            {'tag': 'area', 'css': 'txt', 'value': '',  'text': '平米'},
            {'tag': 'label', 'css': '', 'value': '', 'text': ', 套内建筑面积'},
            {'tag': 'area', 'css': 'txt', 'value': '',  'text': '平米'},
            {'tag': 'label', 'css': '', 'value': '', 'text': '，'},
            {'tag': 'propertyRrightNO', 'css': 'input', 'value': '', 'text': '','width':110}
          ],
          [
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '2 车位'},
            {'tag': 'label', 'css': 'br', 'value': '',  'text': '换行'},
            {'tag': 'label', 'css': '', 'value': '0', 'text': '坐落： '},
            {'tag': 'inputText', 'css': 'input', 'value': '昆明', 'text': '','width':40},
            {'tag': 'label', 'css': '', 'value': '0', 'text': '市'},
            {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':50},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '区'},
            {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':50},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '小区'},
            {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':30},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '号车位'},
            {'tag': 'label', 'css': '', 'value': '0', 'text': '，结构： 建筑面积'},
            {'tag': 'area', 'css': 'txt', 'value': '',  'text': '平米'},
            {'tag': 'label', 'css': '', 'value': '0', 'text': ', 套内建筑面积'},
            {'tag': 'area', 'css': 'txt', 'value': '',  'text': '平米'},
            {'tag': 'label', 'css': '', 'value': '', 'text': '，'},
            {'tag': 'propertyRrightNO', 'css': 'input', 'value': '', 'text': '','width':110}
          ]
        ]
      },{
        number: '第二条',
        title: '房屋权属情况',
        content: [],
        rows:[
          [
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '（1）该房屋的抵押情况为：'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '改房屋未设定抵押；'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '改房屋已经设定抵押。'},
            {'tag': 'label', 'css': 'br', 'value': '',  'text': '换行'},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '  该房屋已经设定抵押的，出卖人应于办理手续前办理抵押注销手续。'}
          ],
          [
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '（2）该房屋的租赁情况为：'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '出卖人未将改房屋出租；'},
            {'tag': 'checkbox', 'css': '', 'value': '2',  'text': '出卖人已将改房屋出租，'},
            {'tag': 'checkbox', 'css': '', 'value': '3',  'text': '买受人为该房屋承租人；'},
            {'tag': 'checkbox', 'css': '', 'value': '4',  'text': '承租人已放弃优先购买权。'},
          ]
        ]
      },
      {
        number: '第三条',
        title: '成交价格及付款方式',
        content: [],
        rows:[
          [
            {'tag': 'label', 'css': 'txt', 'value': '',  'text': '（1）出卖人和买受人协商一致，该房屋 '},
            {'tag': 'sellPrice', 'css': '', 'value': '',  'text': '成交总价为人民币','chineseNumber':' '},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '。该成交总价不含税费。'},
          ],
          [
            {'tag': 'label', 'css': 'txt', 'value': '',  'text': '（2）买受人在签订本合同时支付定金人民币 '},
            {'tag': 'money', 'css': 'txt', 'value': '',  'text': '','chineseNumber':' '},
            {'tag': 'label', 'css': 'txt', 'value': '',  'text': '，支付定金的方式为 '},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '直接支付给出卖人，'},
            {'tag': 'checkbox', 'css': '', 'value': '2',  'text': '资金委托，'},
            {'tag': 'label', 'css': 'txt', 'value': '',  'text': '剩余房款人民币 '},
            {'tag': 'money', 'css': 'txt', 'value': '',  'text': '','chineseNumber':' '},
            {'tag': 'label', 'css': 'txt', 'value': '',  'text': '，买受人按下列方式支付： '},
            {'tag': 'label', 'css': 'br', 'value': '',  'text': '换行'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '一次性付款，买受人于办理房屋过户手续当日在取得房屋权属登记部门出具的《不动产登记受理通知书》后，将剩余房款直接支付给出卖人。'},
            {'tag': 'label', 'css': 'br', 'value': '',  'text': '换行'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '一次性付款，买受人于过户前将房价款存入双方共同委托的第三方交易资金监管账户并签订《交易结算资金划转协议》，取得房屋权属' +
            '登记部门出具的《不动产登记受理通知书》后，出卖人持《不动产登记受理通知书》到第三方按照《交易结算资金划转协议》的约定办理资金划转手续。'},
            {'tag': 'label', 'css': 'br', 'value': '',  'text': '换行'},
            {'tag': 'checkbox', 'css': '', 'value': '1', 'text': '买受人于办理房屋过户手续当日在取得房屋权属登记部门出具的《不动产登记受理通知书》后将首付款（包含已支付定金）人民币'},
            {'tag': 'money', 'css': 'txt', 'value': '',  'text': '','chineseNumber':' '},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '直接支付给出卖人，同时出卖人出具首付款收条给买受人。剩余房款买受人须于'},
            {'tag': 'date', 'css': '', 'value': '',  'text': ''},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '之前向银行申请按揭贷款支付，关于贷款的约定如下：'},
            {'tag': 'label', 'css': 'br', 'value': '',  'text': '换行'},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '买受人向'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '银行，'},
            {'tag': 'checkbox', 'css': '', 'value': '2',  'text': '公积金管理中心 申办'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '商业贷款，'},
            {'tag': 'checkbox', 'css': '', 'value': '2',  'text': '公积金贷款，'},
            {'tag': 'checkbox', 'css': '', 'value': '3',  'text': '组合贷款，拟贷款金额为人民币'},
            {'tag': 'money', 'css': 'txt', 'value': '',  'text': '','chineseNumber':' '},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '，具体贷款金额和贷款发放时间以银行或公积金管理中心批准为准，买受人因自身原因未获得银行或公积金中心批准的，双方同意按照下列方式解决：'},
            {'tag': 'label', 'css': 'br', 'value': '',  'text': '换行'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '买受人自行筹齐剩余房价款，以现金形式支付给出卖人；'},
            {'tag': 'checkbox', 'css': '', 'value': '2',  'text': '买受人继续申请其他银行贷款，至贷款批准，其间已发生的及要产生的各项费用由买受人承担；'},
            {'tag': 'checkbox', 'css': '', 'value': '3',  'text': '本合同终止，买受人支付的定金和房价款应如数返还，双方互不承担违约责任，在交易过程中发生的各项费用由买受人承担。'}
          ]
        ]
      },
      {
        number: '第四条',
        title: '税、费相关规定',
        content: [
          {'tag': 'label', 'css': 'txt', 'value': '',  'text': '本合同履行过程中，按照国家相关规定应缴纳的各项税、费由：'},
          {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '买受人承担；'},
          {'tag': 'checkbox', 'css': '', 'value': '2',  'text': '法定的承担主体各自承担。'},
          {'tag': 'label', 'css': 'br', 'value': '',  'text': '换行'},
          {'tag': 'label', 'css': 'txt', 'value': '',  'text': '本合同履行过程中因政策原因须缴纳新的税费的，由政策规定的缴纳方缴纳；政策中未明确缴纳方的，由'},
          {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '出卖人，'},
          {'tag': 'checkbox', 'css': '', 'value': '2',  'text': '买受人 缴纳。'}
        ],
        rows: []
      },
      {
        number: '第五条',
        title: '房屋产权及具体状况的承诺',
        content: [ ],
        rows: [
          [{'tag': 'label', 'css': 'txt', 'value': '',  'text': '（1）出卖人应当保证该房屋没有产权纠纷，因出卖人原因造成该房屋不能办理产权登记或发生债权债务纠纷的，由出卖人承担相应责任。'}],
          [{'tag': 'label', 'css': 'txt', 'value': '',  'text': '（2）出卖人应当保证已如实陈述该房屋权属状况、附属设施设备、装饰装修情况和相关关系，补充协议及物业交割单所列的该房屋附属设施设备及其装饰装修随同该房屋一并转让给买受人，买受人对出卖人出售的该房屋具体状况充分了解，自愿买受该房屋。'}],
          [{'tag': 'label', 'css': 'txt', 'value': '',  'text': '（3）出卖人应当保证自本合同签订之日起至该房屋验收交接完成，对各项房屋附属设施设备及其装饰装修保持良好的状况。'}],
          [
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '（4）在房屋交付日以前发生的'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '物业管理费，'},
            {'tag': 'checkbox', 'css': '', 'value': '2',  'text': '水，'},
            {'tag': 'checkbox', 'css': '', 'value': '3',  'text': '电，'},
            {'tag': 'checkbox', 'css': '', 'value': '4',  'text': '燃气，'},
            {'tag': 'checkbox', 'css': '', 'value': '5',  'text': '有线电视，'},
            {'tag': 'checkbox', 'css': '', 'value': '6',  'text': '电信，'},
            {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':80},
            {'tag': 'label', 'css': 'txt', 'value': '', 'text': '费用由出卖人承担，交付日以后（含当日）发生的费用由买受人承担。'}
          ]
        ]
      },
      {
        number: '第六条',
        title: '权属转移登记',
        content: [ ],
        rows: [
          [
            {'tag': 'label', 'css': 'txt', 'value': '',  'text': '（1）买卖双方同意于'},
            {'tag': 'date', 'css': '', 'value': '',  'text': ''},
            {'tag': 'label', 'css': 'txt', 'value': '',  'text': '前共同向房屋权属登记部门申请办理房屋权属转移登记手续。'}
          ],
          [
            {'tag': 'label', 'css': 'txt', 'value': '',  'text': '（2）房屋有相关户口的出卖人应当在'},
            {'tag': 'date', 'css': '', 'value': '',  'text': ''},
            {'tag': 'label', 'css': 'txt', 'value': '',  'text': '前向房屋所在地的户籍管理机关办理原有户口迁出手续。如因出卖人自身原因未如期将与房屋相关的户口迁出的，按日计算向买受人支付已交付房价款万分之【五】的违约金。'}
          ]
        ]
      },
      {
        number: '第七条',
        title: '房屋的交付',
        content: [ ],
        rows: [
          [
            {'tag': 'label', 'css': 'txt', 'value': '',  'text': '（1）出卖人应当在'},
            {'tag': 'label', 'css': 'txt', 'value': '',  'text': '【房屋过户手续办理完毕'},
            {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':30},
            {'tag': 'label', 'css': 'txt', 'value': '',  'text': '日内】【'},
            {'tag': 'date', 'css': '', 'value': '',  'text': ''},
            {'tag': 'label', 'css': 'txt', 'value': '',  'text': '前】将该房屋交付给买受人、并将该房屋转让情况告知物业管理的相关单位和部门，积极办理房屋维修基金户名变更手续。为督促出卖人如期如约交付该房屋并结清所有费用，在买受人支付首付款或剩余房款时预留交房保证金人民币'},
            {'tag': 'money', 'css': 'txt', 'value': '',  'text': '','chineseNumber':' '},
            {'tag': 'label', 'css': 'txt', 'value': '',  'text': '在房屋交接完成时支付。'}
          ],
          [
            {'tag': 'label', 'css': 'txt', 'value': '',  'text': '（2）该房屋交付时，应当履行下列手续：① 出卖人与买受人共同对该房屋附属设施设备、装饰装修、相关物品清单等具体情况进行验收，记录水、电、气表的读数，并交接该附件中所列物品；② 买卖双方在物业交割单上签字；③ 移交该房屋钥匙、门禁卡。'}
          ]
        ]
      },
      {
        number: '第八条',
        title: '违约责任',
        content: [ ],
        rows: [
          [
            {'tag': 'label', 'css': 'txt', 'value': '',  'text': '（1）逾期交房责任：除不可抗力外，出卖人未按照第七条约定的期限和条件将该房屋交付买受人的，逾期在15日之内，自第七条约定的交付期限届满之次日起至实际交付之日止，出卖人按日计算向买受人支付已交付房价款万分之五的违约金，并于该房屋实际交付之日向买受人支付违约金，合同继续履行；'}
          ],
          [
            {'tag': 'label', 'css': 'txt', 'value': '',  'text': '（2）逾期付款责任：买受人未按照约定的时间付款的，按照下列方式处理：'},
            {'tag': 'label', 'css': 'br', 'value': '',  'text': '换行'},
            {'tag': 'label', 'css': 'txt', 'value': '',  'text': '① 逾期在15日之内，自约定的应付款期限届满之次日起至实际支付应付款之日止，买受人按日计算向出卖人支付逾期应付款万分之五的违约金，并于实际支付应付款之日向出卖人支付违约金，合同继续履行；'},
            {'tag': 'label', 'css': 'br', 'value': '',  'text': '换行'},
            {'tag': 'label', 'css': 'txt', 'value': '',  'text': '② 逾期超过15日后，出卖人有权解除合同。出卖人解除合同的，买受人应当自解除合同通知送达之日起15日内按照房屋总价款的20%向出卖人支付违约金，已经支付的款项出卖人可不退还。'}
          ],
          [
            {'tag': 'label', 'css': 'txt', 'value': '',  'text': '（3）出卖人将该房屋出卖给第三人，导致买受人不能取得房屋权属证书的，买受人有权退房，出卖人应当自退房通知送达之日起15日内退还买受人全部已付款，并按买受人累计已付房价款的120%支付违约金。'}
          ]
        ]
      },
      {
        number: '第九条',
        title: '',
        content: [
          {'tag': 'label', 'css': 'txt', 'value': '',  'text': '本协议签订完成，'},
          {'tag': 'commissionB', 'css': 'txt', 'value': '',  'text': '买受人向居间方支付人民币','chineseNumber':' '},
          {'tag': 'commissionA', 'css': 'txt', 'value': '',  'text': '，出卖人向居间方支付佣金人民币','chineseNumber':' '},
          {'tag': 'label', 'css': 'txt', 'value': '',  'text': '。如因买卖双方的原因最终未能完成房屋产权过户，此佣金无需退还，因居间方原因导致的除外。'}
        ],
        rows: []
      },
      {
        number: '第十条',
        title: '不可抗力',
        content: [ ],
        rows: [
          [
            {'tag': 'label', 'css': 'txt', 'value': '',  'text': '（1）因不可抗力不能按照约定履行合同的，根据不可抗力的影响，部分或全部免除责任，但因不可抗力不能按照约定履行合同的一方当事人应当及时告知另一方当事人，并自不可抗力事件结束之日起30日内向另一方当事人提供证明。'}
          ],
          [
            {'tag': 'label', 'css': 'txt', 'value': '',  'text': '（2）上述房屋风险责任自该房屋'},
            {'tag': 'checkbox', 'css': '', 'value': '1',  'text': '所有权转移，'},
            {'tag': 'checkbox', 'css': '', 'value': '2',  'text': '转移占有'},
            {'tag': 'label', 'css': 'txt', 'value': '',  'text': '之日起转移给买受人。'},
          ]
        ]
      },
      {
        number: '第十一条',
        title: '争议解决方式',
        content: [
          {'tag': 'label', 'css': 'txt', 'value': '',  'text': '本合同项下发生的争议，由双方协商解决；协商不成的，依法向房屋所在地人民法院起诉。'}
        ],
        rows: []
      },
      {
        number: '第十二条',
        title: '',
        content: [
          {'tag': 'label', 'css': 'txt', 'value': '',  'text': '本合同一式三份，经买卖双方签字（盖章）后生效。双方可以根据具体情况对本合同中未约定、约定不明或不适用的内容签订书面补充协议进行变更或补充。对本合同的解除，应当采用书面形式。本合同附件及补充协议与本合同具有同等法律效力。'}
        ],
        rows: []
      },
      {
        number: '第十三条',
        title: '',
        content: [
          {'tag': 'label', 'css': 'txt', 'value': '',  'text': '其他补充：'},
          {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':812},
          {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':1000},
          {'tag': 'inputText', 'css': 'input', 'value': '', 'text': '','width':1000}
        ],
        rows: []
      }
    ]
    }
  },
  components: {
    Term
  },
  props: ['initData','templateId'],
  mounted () {
    if (this.initData&&this.initData.templateType==='01') {
      this.contractName = '房屋买卖合同'
      this.participant = this.sellMan
      this.terms = this.sellTerms
    }
    if (this.initData&&this.initData.templateType==='02') {
      this.contractName = '房屋出租合同'
      this.participant = this.rentMan
      this.terms = this.rentTerms
    }
    if (this.initData&&this.initData.codePrefix) {
      this.codePrefix = this.initData.codePrefix
    }
    if (this.initData&&this.initData.declaration) {
      this.declaration = this.initData.declaration
    }
    if (this.initData&&this.initData.companyName) {
      this.companyName = this.initData.companyName
    }
    if (this.initData&&this.initData.terms.length > 0) {
      this.terms = this.initData.terms
    }
  },
  methods: {
    clearMenu () {
      if (this.$refs.termItem) {
        for (let i = 0; i < this.$refs.termItem.length; i++) {
          this.$refs.termItem[i].clearMenu()
        }
      }
    },
    delTerm (index) {
      this.terms.splice(index,1)
    },
    movePost (param) {
      console.log(param.index)
      let currTerm = this.terms[param.index]
      let toIndex = param.toIndex
      if (param.post === 0) {
        toIndex = toIndex - 1
      } else {
        toIndex = toIndex + 1
      }
      console.log(this.$refs['term' + param.index])

      console.log(toIndex)

      if (toIndex === -1) {
        this.terms.splice(param.index,1)
        this.terms.unshift(currTerm)

      }else if (toIndex === this.terms.length) {
        this.terms.splice(param.index,1)
        this.terms.push(currTerm)
      } else {
        this.terms.splice(param.index,1)
        this.terms.splice(toIndex,0,currTerm)
      }
      console.log(this.terms)
    },
    addTerm (result,index,isAdd) {
      console.log(result)
      if (isAdd > 0) {
        this.terms.splice(index+1,0,result)
      } else {
        this.terms.splice(index,1,result)
      }
    },
    printContent(e){
      window.print();
    },
    downloadIamge(imgsrc, name) {
        //下载图片地址和图片名
      var image = new Image();
      // 解决跨域 Canvas 污染问题
      image.setAttribute("crossOrigin", "anonymous");
      image.src = imgsrc;
      image.onload = function() {
          var canvas = document.createElement("canvas");
          canvas.width = image.width;
          canvas.height = image.height;
          var context = canvas.getContext("2d");
          context.drawImage(image, 0, 0, image.width, image.height);
          var url = canvas.toDataURL("image/png");
          //得到图片的base64编码数据
          var a = document.createElement("a");
          // 生成一个a元素
        var event = new MouseEvent("click");
        // 创建一个单击事件
           a.download = name || "photo";
           // 设置图片名称
        a.href = url;
        // 将生成的URL设置为a.href属性
         a.dispatchEvent(event);
         // 触发a的单击事件
        }


    },
    blackList () {

     this.$emit('blackList')
      //window.print()
    },
    showDialog (type) {
      if (type === 1) {
        this.dialogNameVisible = true
        this.contentForm = {'content': '','name':this.companyName}
        this.$refs['contentForm'].clearValidate()
      }
      if (type === 2) {
        this.contentForm = {'content': this.declaration,'name':''}
        this.dialogContentVisible = true
        this.$refs['contentForm'].clearValidate()
      }
    },
    saveTemplate (type) {
      this.$refs['contentForm'].validate((valid) => {
        if (valid) {
          if (type === 1) {
            this.companyName = this.contentForm.name
            this.dialogNameVisible = false
          }
          if (type === 2) {
            this.declaration = this.contentForm.content
            this.dialogContentVisible = false
          }
        } else {
          return false;
        }
      })
    },
    saveData () {
      let param = {'id':this.templateId,'companyName':this.companyName,'declaration':this.declaration,'termJson':JSON.stringify(this.terms)}
      console.log(param)

      if(this.companyName === ''){
        this.$message({
          type: 'warning',
          message: '公司名称不能为空'
        })
        return
      }
      if(this.terms.length === 0){
        this.$message({
          type: 'warning',
          message: '条款不能为空'
        })
        return
      }
      let sellPriceTag = false
      let rentPriceTag = false
      let commissionATag = false
      let commissionBTag = false
      let propertyRrightNOTag = false
      let beginTimeTag = false
      let endTimeTag = false

      for (let i=0;i<this.terms.length;i++){
        if (this.terms[i].content){
          for (let j=0;j<this.terms[i].content.length;j++){
            if (this.terms[i].content[j].tag==='sellPrice') {
              sellPriceTag = true
            }
            if (this.terms[i].content[j].tag==='rentPrice') {
              rentPriceTag = true
            }
            if (this.terms[i].content[j].tag==='commissionA') {
              commissionATag = true
            }
            if (this.terms[i].content[j].tag==='commissionB') {
              commissionBTag = true
            }
            if (this.terms[i].content[j].tag==='propertyRrightNO') {
              propertyRrightNOTag = true
            }
            if (this.terms[i].content[j].tag==='beginTime') {
              beginTimeTag = true
            }
            if (this.terms[i].content[j].tag==='endTime') {
              endTimeTag = true
            }
          }
        }
        if (this.terms[i].rows){
          for (let m=0;m<this.terms[i].rows.length;m++){
            for (let j=0;j<this.terms[i].rows[m].length;j++){
              if (this.terms[i].rows[m][j].tag==='sellPrice') {
                sellPriceTag = true
              }
              if (this.terms[i].rows[m][j].tag==='rentPrice') {
                rentPriceTag = true
              }
              if (this.terms[i].rows[m][j].tag==='commissionA') {
                commissionATag = true
              }
              if (this.terms[i].rows[m][j].tag==='commissionB') {
                commissionBTag = true
              }
              if (this.terms[i].rows[m][j].tag==='propertyRrightNO') {
                propertyRrightNOTag = true
              }
              if (this.terms[i].rows[m][j].tag==='beginTime') {
                beginTimeTag = true
              }
              if (this.terms[i].rows[m][j].tag==='endTime') {
                endTimeTag = true
              }
            }
          }
        }
      }
      if (this.initData.templateType==='01'){
        if (!sellPriceTag) {
          this.$message({
            type: 'warning',
            message: '必须添加成交总价控件'
          })
          return
        }
        if (!propertyRrightNOTag) {
          this.$message({
            type: 'warning',
            message: '必须添加所有权证控件'
          })
          return
        }
        if (!commissionATag&&!commissionBTag) {
          this.$message({
            type: 'warning',
            message: '必须添加支付佣金控件'
          })
          return
        }
      }
     if (this.initData.templateType==='02'){
        if (!rentPriceTag) {
          this.$message({
            type: 'warning',
            message: '必须添加租金控件'
          })
          return
        }
       if (!beginTimeTag||!endTimeTag) {
         this.$message({
           type: 'warning',
           message: '必须添加租赁期限控件'
         })
         return
       }
       if (!commissionBTag) {
         this.$message({
           type: 'warning',
           message: '必须添加买家支付佣金控件'
         })
         return
       }
     }

      this.$emit('saveResult',param)
     /* html2Canvas(document.querySelector('#contract'), {
        allowTaint: true
      }).then(function (canvas) {
        let image = canvas.toDataURL('image/png').replace('image/png', 'image/octet-stream')
       // window.location.href = image
        let OpenWindow = window.open('')
        OpenWindow.document.write("<TITLE>例子</TITLE>")
        OpenWindow.document.write("<BODY >")
        OpenWindow.document.write("<img src='"+canvas.toDataURL('image/png')+"'>")
        OpenWindow.document.write("</BODY>")
        OpenWindow.document.write("</HTML>")
        OpenWindow.print()
        /!* OpenWindow.close()*!/
        /!*let contentWidth = canvas.width
        let contentHeight = canvas.height
        let pageHeight = contentWidth / 592.28 * 841.89
        let leftHeight = contentHeight
        let position = 0
        let imgWidth = 595.28
        let imgHeight = 592.28 / contentWidth * contentHeight
        let pageData = canvas.toDataURL('image/jpeg', 1.0)
        let PDF = new JsPDF('', 'pt', 'a4')
        if (leftHeight < pageHeight) {
            PDF.addImage(pageData, 'JPEG', 0, 0, imgWidth, imgHeight)
        } else {
            while (leftHeight > 0) {
                PDF.addImage(pageData, 'JPEG', 0, position, imgWidth, imgHeight)
               leftHeight -= pageHeight
              position -= 841.89
              if (leftHeight > 0) {
                    PDF.addPage()
                }
            }
        }
        PDF.save('test.pdf')*!/

      })*/
      }
  }
}
</script>

<style >
  .contract{
    width: 1000px;
    margin: auto;
    padding-top: 20px;
    font-size: 16px;
  }
/*  .contract input.input{
    font-size: 12px;
    line-height: 20px;
    color: #444;
  }*/
  .contract div>span{
    font-size: 16px;
  }
  .contract div>label{
    font-size: 16px;
  }
  .contract p{
    font-size: 16px;
  }
  .contract .term{
    font-size: 16px;
    line-height: 24px;
  }
  .contract .number{
    font-size: 16px;
    width: 80px !important;
  }

  .area{
    width: 50px !important;
  }
  .input{
    -web-kit-appearance:none;
    -moz-appearance: none;
    outline:0;
    border-left-style: none;
    border-top-style: none;
    border-right-style: none;
    border-bottom: solid 1px #000000;
    width: 130px;
  }
  .contract .el-date-editor{
    width: auto !important;
  }
  .contract .el-date-editor .el-input__inner{
    -web-kit-appearance:none !important;
    -moz-appearance: none!important;
    outline:0!important;
    border-left-style: none!important;
    border-top-style: none!important;
    border-right-style: none!important;
    border-bottom: solid 1px #000000!important;
    width: 40px!important;
    height: 16px!important;
    border-radius: 0!important;
    padding: 0!important;
    text-align: center!important;
  }
  .dataRow .el-form-item__content{
    line-height: 40px;
  }
 /* .el-form-item{
    margin: 0 !important;
  }*/
  .el-input__prefix{
    display: none;
  }
  .money{
    width: 30px;
  }
/*  .content{
    background-color: #ffffff !important;
  }*/
  .leftRetraction{
    padding-left: 50px;
    line-height: 24px;
  }
  .rightRetraction{
    padding-right: 50px;
    line-height: 24px;
  }
  .dataRow{
    display: inline-block;
    line-height: 24px;
  }
  .contractCheckbox{
    display: inline-block;
    margin-left: 5px;
  }
</style>
