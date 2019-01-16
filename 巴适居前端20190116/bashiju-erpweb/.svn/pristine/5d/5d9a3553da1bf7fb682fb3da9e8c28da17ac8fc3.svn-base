<template>
  <div>
    <el-row :gutter="50">
      <el-col :span="12">
        <div class="rowDiv">
          <div class="rowheader">
            <label>控件选项</label>
          </div>
          <ul class="rowUl">
            <li class="moveCss" v-for="(item,index) in tempData" :index="index" @dragstart="dragstart" draggable="true">
              <DataRow :datas="item.data"></DataRow>
            </li>
          </ul>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="rowheader">
          <label style="margin-right: 70px;">数据内容</label>
          <el-tooltip class="item" effect="dark" content="上移" placement="bottom">
            <el-button icon="el-icon-arrow-up" size="mini" type="primary" @click="moveUp"></el-button>
          </el-tooltip>
          <el-tooltip class="item" effect="dark" content="下移" placement="bottom" >
            <el-button icon="el-icon-arrow-down" size="mini" type="primary" @click="moveDown"></el-button>
          </el-tooltip>
          <el-tooltip class="item" effect="dark" content="编辑" placement="bottom" >
            <el-button icon="el-icon-edit-outline" size="mini" type="primary" @click="editData"></el-button>
          </el-tooltip>
          <el-tooltip class="item" effect="dark" content="删除" placement="bottom">
            <el-button icon="el-icon-delete" size="mini" type="primary" @click="delData"></el-button>
          </el-tooltip>
        </div>
        <div class="rowDiv" @drop="drop">
          <ul class="rowUl">
            <li v-for="(item,index) in rowData" :class="item.css"  :index="index" @click="selectItem(item,index)">
              <DataRow  :datas="item.data"></DataRow>
            </li>
          </ul>
        </div>
      </el-col>
    </el-row>
    <el-dialog title="编辑"  :visible.sync="dialogFormVisible" append-to-body>
      <el-form >
        <el-form-item v-if="showLabel">
          <el-input type="textarea" autosize v-model="labelInfo" ></el-input>
        </el-form-item>
        <el-form-item v-if="!showLabel" label="宽度" >
          <el-input-number v-model="textWidth" :min="0"></el-input-number>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="saveData">确 定</el-button>
        <el-button @click="dialogFormVisible = false">取 消</el-button>
      </div>
    </el-dialog>
    <el-row>
      <el-col :span="24">
        <div style="padding-top: 20px;text-align: center;">
          <el-button type="primary" @click="saveRowData">确 定</el-button>
          <el-button @click="cancelSave">取 消</el-button>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import DataRow from './DataRow.vue'
export default{
  data () {
    return {
      showLabel: true,
      dialogFormVisible: false,
      rowData:[],
      tempData: [
       {
         'type': 'label',
         'data': {'tag': 'label', 'css': 'txt', 'value': '', 'text': '自定义文本'}
       },
       {
         'type': 'checkbox',
         'data': {'tag': 'checkbox', 'css': '', 'value': '', 'text': '选择项'}
       },
        {
          'type': 'notedit',
          'data': {'tag': 'propertyRrightNO', 'css': 'input', 'width': 120,'value': '',  'text': ''}
        },
       {
         'type': 'notedit',
         'data': {'tag': 'money', 'css': 'txt', 'value': '',  'text': '','chineseNumber':' '}
       },
       {
         'type': 'notedit',
         'data': {'tag': 'sellPrice', 'css': 'txt', 'value': '', 'text': '成交总价为人民币','chineseNumber':' '}
       },
        {
          'type': 'notedit',
          'data': {'tag': 'rentPrice', 'css': 'txt', 'value': '', 'text': '','chineseNumber':' '}
        },
       {
         'type': 'notedit',
         'data': {'tag': 'commissionA', 'css': 'txt', 'value': '',  'text': '出卖人向居间方支付佣金人民币'}
       },
        {
          'type': 'notedit',
          'data': {'tag': 'commissionB', 'css': 'txt', 'value': '',  'text': '买受人向居间方支付佣金人民币'}
        },
       {
         'type': 'notedit',
         'data': {'tag': 'date', 'css': '', 'value': '',  'text': ''}
       },
        {
          'type': 'notedit',
          'data': {'tag': 'beginTime', 'css': '', 'value': '',  'text': ''}
        },
        {
          'type': 'notedit',
          'data': {'tag': 'endTime', 'css': '', 'value': '',  'text': ''}
        },
       {
         'type': 'notedit',
         'data': {'tag': 'area', 'css': 'txt', 'value': '',  'text': '平米'}
       },
       {
         'type': 'text',
         'data': {'tag': 'inputText', 'css': 'input', 'width': 100, 'value': '', 'text': '输入框'}
       },
       {
         'type': 'notedit',
         'data': {'tag': 'label', 'css': 'br', 'value': '', 'text': '换行'}
       }
      ],
      selectedData: [],
      labelInfo: '',
      textWidth: ''
    }
  },
  props: ['initData'],
  components: {
    DataRow
  },
  mounted () {
    console.log('initData:')
    console.log(this.initData)
    if (this.initData.length>0) {
      for (let i=0;i<this.initData.length;i++) {
        let item = {}
        if (this.initData[i].tag === 'label' && this.initData[i].css === 'txt') {
          item.type = 'label'
        }
        if (this.initData[i].tag === 'label' && this.initData[i].css === 'br') {
          item.type = 'notedit'
        }
        if (this.initData[i].tag === 'checkbox') {
          item.type = 'checkbox'
        }
        if (this.initData[i].tag === 'inputText') {
          item.type = 'text'
        }
        if (this.initData[i].tag === 'xiaoxie') {
          item.type = 'notedit'
        }
        if (this.initData[i].tag === 'baiwan') {
          item.type = 'notedit'
        }
        if (this.initData[i].tag === 'wan') {
          item.type = 'notedit'
        }
        if (this.initData[i].tag === 'area') {
          item.type = 'notedit'
        }
        if (this.initData[i].tag === 'date') {
          item.type = 'notedit'
        }
        item.data = JSON.parse(JSON.stringify(this.initData[i])) //克隆数据
        item.css = 'arrow'
        this.rowData.push(item)
      }
    }

    window.addEventListener('dragover', function (e) {
      e.preventDefault()
    })
  },
  methods:{
    dragstart (e) {
      let index = e.target.getAttribute('index')
      e.dataTransfer.setData('index', index)
    },
    drop (e) {
      let index = e.dataTransfer.getData('index')
      console.log('tempData')
      console.log(this.tempData[index])
      let item = {}
      item.type = this.tempData[index].type
      item.data = JSON.parse(JSON.stringify(this.tempData[index].data)) //克隆数据
      item.css = 'arrow'
      this.rowData.push(item)
    },
    selectItem (item,index) {

      let css = item.css
      console.log(css)

      if (this.selectedData.length>0) {
        this.rowData[this.selectedData[0]].css = 'arrow'
      }
      if (css.indexOf('selected') === -1) {
        item.css = 'arrow selected'
        this.selectedData[0] = index
      } else {
        this.rowData[index].css = 'arrow'
        this.selectedData = []
      }
      console.log(this.rowData)
    },
    moveUp () {
      for (let i = 0; i < this.selectedData.length; i++) {
        for (let j = 0; j < this.rowData.length; j++) {
          if (j > 0 && this.selectedData[i] === j) {
            let data = this.rowData[j]
            this.rowData.splice(j,1)
            this.rowData.splice(j-1, 0, data)
            this.selectedData[i] = j-1
          }
        }
      }
    },
    moveDown () {
      console.log(this.selectedData)
      for (let i = 0; i < this.selectedData.length; i++) {
        let index = this.selectedData[i]
        for (let j = 0; j < this.rowData.length; j++) {
          if (j < this.rowData.length-1 && index === j) {
            let data = this.rowData[j]
            this.rowData.splice(j,1)
            this.rowData.splice(j+1, 0, data)
            this.selectedData[i] = j+1
          }
        }
      }
    },
    editData () {
      console.log(this.selectedData)
      if (this.selectedData.length>0) {
        let type = this.rowData[this.selectedData[0]].type
        console.log(type)
        if (type === 'notedit') {
          return
        }
        if (type === 'label') {
          this.showLabel = true
          this.labelInfo = this.rowData[this.selectedData[0]].data.text
        }
        if (type === 'checkbox') {
          this.showLabel = true
          this.labelInfo = this.rowData[this.selectedData[0]].data.text
        }
        if (type === 'text') {
          this.showLabel = false
          this.textWidth = this.rowData[this.selectedData[0]].data.width
        }
        this.dialogFormVisible = true
      }

    },
    delData () {

     if (this.selectedData.length>0) {
       this.rowData.splice(this.selectedData[0],1)
       this.selectedData = []
       /*  this.$confirm('确定要删除该项吗？', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.rowData.splice(this.selectedData[0],1)
          this.selectedData.splice(0,1)
          /!* const param = {id: row.id}
           deletePrintTemplate(param).then(res => {
           if (res.success) {
           this.$message({
           type: 'success',
           message: res.msg
           })
           } else {
           this.$message({
           type: 'warning',
           message: res.msg
           })
           }
           }).catch(() => {})*!/
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })*/
      }
    },
    saveData () {
      this.dialogFormVisible = false
      let type = this.rowData[this.selectedData[0]].type
      if (type === 'label') {
        this.rowData[this.selectedData[0]].data.text = this.labelInfo
      }
      if (type === 'checkbox') {
        this.rowData[this.selectedData[0]].data.text = this.labelInfo
      }
      if (type === 'text') {
        this.rowData[this.selectedData[0]].data.width = this.textWidth
      }
    },
    saveRowData() {
      let result = []
      for (let i=0; i<this.rowData.length;i++) {
        result.push(this.rowData[i].data)
      }
      console.log(result)
      this.$emit('saveRowData',result)
    },
    cancelSave () {
      this.$emit('cancelSave')
    }
  }
}
</script>

<style lang="scss" scoped>
.rowUl{
  text-align: center;
  border: 1px solid #ebeef5;
  height: 300px;
  overflow-y: scroll;
}
.rowUl li{
  margin: 5px 0;
}
.moveCss{
  cursor: move;
}
.arrow{
  cursor: default;
}

.rowheader{
  height: 40px;
  line-height: 40px;
  background: #f5f7fa;
  margin: 0;
  padding-left: 15px;
  border: 1px solid #ebeef5;
  border-bottom: 0;
  box-sizing: border-box;
  color: #000;
}
.selected{
  background-color: #d9d9d9;
}

</style>
