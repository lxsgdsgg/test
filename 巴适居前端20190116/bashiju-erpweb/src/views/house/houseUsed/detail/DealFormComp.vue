<template>
  <div>
    <el-form class="wrapper" :model="form" ref="form" :rules="rules" label-width="100px" size="small">

      <div class="content">
        <el-row>

          <el-col :span="10">
            <el-form-item label="成交日期" prop="form1">

              <el-date-picker
                style="width: 100%;"
                v-model="form.form1"
                type="date"
                placeholder="选择日期"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd">
              </el-date-picker>

            </el-form-item>
          </el-col>

          <el-col :span="5">
            <el-form-item label="自动分成" prop="form2" label-width="80px">

              <el-switch
                v-model="form.form2">
              </el-switch>

            </el-form-item>
          </el-col>

          <el-col :span="9">
            <el-form-item label="成交人" prop="form3" label-width="60px">

              <base-cascader
                :changeOnSelect="false"
                @change="handleChange"
                v-model="form.form3"
                :data="peopleSelectOpts" :props="selectProps" :dataProps="selectDataProps">
              </base-cascader>

            </el-form-item>
          </el-col>

        </el-row>

        <el-row>

          <el-col :span="12">
            <el-form-item label="租期开始" prop="form4">

              <el-date-picker
                style="width: 100%;"
                v-model="form.form4"
                type="date"
                placeholder="选择日期"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd">
              </el-date-picker>

            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="租期截止" prop="form5">

              <el-date-picker
                style="width: 100%;"
                v-model="form.form5"
                type="date"
                placeholder="选择日期"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd">
              </el-date-picker>

            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="合同号" prop="form6">

              <el-input v-model="form.form6"></el-input>

            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="租赁价格" prop="form7">

              <el-input v-model="form.form7"></el-input>

            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="应收佣金" prop="form8">

              <el-input v-model="form.form8"></el-input>

            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="土地证号" prop="form9">

              <el-input v-model="form.form9"></el-input>

            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="产权证号" prop="form10">

              <el-input v-model="form.form10"></el-input>

            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="补充条款" prop="form11">

              <el-input type="textarea" v-model="form.form11"></el-input>

            </el-form-item>
          </el-col>

        </el-row>

        <hr>

        <el-row>

          <el-col :span="12">
            <el-form-item label="甲方编号" prop="form12">

              <el-input v-model="form.form12"></el-input>

            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="乙方编号" prop="form13">

              <el-input v-model="form.form13"></el-input>

            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="业主电话" prop="form14">

              <el-input v-model="form.form14"></el-input>

            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="客户电话" prop="form15">

              <el-input v-model="form.form15"></el-input>

            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="甲方姓名" prop="form16">

              <el-input v-model="form.form16"></el-input>

            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="乙方姓名" prop="form17">

              <el-input v-model="form.form17"></el-input>

            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="甲方证件号码" prop="form18">

              <el-input v-model="form.form18"></el-input>

            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="乙方证件号码" prop="form19">

              <el-input v-model="form.form19"></el-input>

            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="甲方联系地址" prop="form20">

              <el-input v-model="form.form20"></el-input>

            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="乙方联系地址" prop="form21">

              <el-input v-model="form.form21"></el-input>

            </el-form-item>
          </el-col>

        </el-row>

        <hr>

        <div class="money-table">

          <h2 class="text-center">
            费用项设置
            <el-button @click="handleAddTableRow" class="ml10" type="primary" plain size="mini" icon="el-icon-plus">新增项</el-button>
          </h2>
          <el-table
            size="mini"
            :data="moneyTableData"
            style="width: 100%">

            <el-table-column
              width="130"
              label="费用类型">

              <template slot-scope="scope">
                <el-select v-model="form.form23" size="mini">
                  <el-option label="中介类费用" value="shanghai"></el-option>
                  <el-option label="金融类费用" value="beijing"></el-option>
                </el-select>
              </template>

            </el-table-column>

            <el-table-column
              width="130"
              label="费用项目">

              <template slot-scope="scope">
                <el-select v-model="form.form23" size="mini">
                  <el-option label="区域一" value="shanghai"></el-option>
                  <el-option label="区域二" value="beijing"></el-option>
                </el-select>
              </template>

            </el-table-column>

            <el-table-column
              width="120"
              label="交费人">

              <template slot-scope="scope">
                <el-select v-model="form.form23" size="mini">
                  <el-option label="业主" value="shanghai"></el-option>
                  <el-option label="客户" value="beijing"></el-option>
                </el-select>
              </template>

            </el-table-column>

            <el-table-column
              width="120"
              label="金额">

              <template slot-scope="scope">
                <el-input
                  size="mini"
                  v-model="form.form22">
                  <i slot="suffix" class="fa fa-rmb mr5" style="vertical-align: sub"></i>
                </el-input>
              </template>

            </el-table-column>

            <el-table-column
              width="150"
              label="预计交费日期">
              <template slot-scope="scope">
                <el-date-picker
                  size="mini"
                  style="width: 100%;"
                  v-model="form.form5"
                  type="date"
                  placeholder="选择日期"
                  format="yyyy 年 MM 月 dd 日"
                  value-format="yyyy-MM-dd">
                </el-date-picker>
              </template>

            </el-table-column>

            <el-table-column
              width="150"
              label="备注">

              <template slot-scope="scope">
                <el-input
                  size="mini"
                  v-model="form.form22">
                </el-input>
              </template>

            </el-table-column>

            <el-table-column
              width="80"
              label="操作">
              <template slot-scope="scope">
                <el-button type="text">清除</el-button>
              </template>
            </el-table-column>

          </el-table>
        </div>

      </div>

      <el-form-item class="btn-groups" label-width="0">
        <el-button type="primary" :loading="loadingSubmitBtn" @click="handleSubmit">确认</el-button>
        <el-button>取消</el-button>
        <el-button type="primary" plain>重置</el-button>
      </el-form-item>

    </el-form>
  </div>
</template>

<script>
  import BaseCascader from '@/components/BaseCascader'

  export default {
    components: {BaseCascader},
    props: {
      peopleSelectOpts: {
        type: Array,
        default () {
          return []
        }
      }
    },
    data () {
      return {
        form: {
          form1: '',
          form2: '',
          form3: '',
          form4: '',
          form5: '',
          form6: '',
          form7: '',
          form8: '',
          form9: '',
          form10: '',
          form11: '',
          form12: '',
          form13: '',
          form14: '',
          form15: '',
          form16: '',
          form17: '',
          form18: '',
          form19: '',
          form20: '',
          form21: '',
          form22: '',
          form23: '',
        },
        rules: {},
        selectDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        selectProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          children: 'children', // 指定选项的子选项为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        loadingSubmitBtn: false,
        moneyTableData: [{}]
      }
    },
    methods: {
      handleChange () {},
      handleSubmit() {},
      handleAddTableRow () {
        this.moneyTableData.push({})
      }
    },
  }
</script>

<style scoped lang="scss">
  .wrapper {
    height: 585px;
  }
  .content {
    height: 550px;
    max-height: 550px;
    overflow-y: auto;
  }

  .money-table {
    h2 {
      margin-top: 15px;
      margin-bottom: 10px;
    }
  }

  .btn-groups {
    margin-bottom: 0; text-align: center; padding-top: 15px; border-top: 1px dashed #ddd;
  }
</style>
