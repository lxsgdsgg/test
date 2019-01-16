<!--
  动态table 自定义列表
  固定表头, 按照表头顺序排序
-->

<template>
  <div class="wrapper">
    <div class="operates clearfix">

      <slot></slot>

      <el-button size="mini" class="pull-right" @click="dialogVisible = true">自定义列表</el-button>
    </div>
    <el-table
      @selection-change="handleSelectionChange"
      max-height="700"
      :data="tableData" :key="key" border fit highlight-current-row style="width: 100%"

    >
      <!--size="small"-->
      <el-table-column
        type="selection"
        width="55"
        :fixed="true"
      >
      </el-table-column>

      <template v-for="(fruit, index) in _formThead">
        <el-table-column
          width="90"
          v-if="fruit.prop === 'recordTime' || fruit.prop === 'lastAllShowedTime' || fruit.prop === 'lastBackTime' || fruit.prop === 'mainterFllowTime'"
          :key="index" :label="fruit.label && fruit.label" :align="fruit.align && fruit.align"
          show-overflow-tooltip
        >
          <template slot-scope="scope">
            {{$utils.timeFormat(scope.row[fruit.prop],'{y}-{m}-{d} {h}:{i}:{s}')}}
          </template>
        </el-table-column>


        <el-table-column v-else :key="index" :label="fruit.label && fruit.label" :align="fruit.align && fruit.align" :width="fruit.width && fruit.width" show-overflow-tooltip>
          <template slot-scope="scope">

            <a
              @click="toDetail(scope.row.id, scope.row.formName,scope.row.transactionTypeId)"
              href="javascript:;" class="houseId" v-if="fruit.prop === 'id'" type="text"
            >
              {{scope.row[fruit.prop]}}
            </a>

            <el-tag v-else-if="fruit.prop === 'discStatus'" size="small">{{scope.row[fruit.prop]}}</el-tag>


            <!--拼接数据-->
            <span v-else-if="fruit.prop === 'room'">
              <!--{{ scope.row.maxRoom == 0 ? scope.row.minRoom +'以上' : scope.row.minRoom+'-'+scope.row.maxRoom+'室'  }}-->
                   <label v-if=" scope.row.minRoom === 0   && scope.row.maxRoom">
                  {{
                     scope.row.maxRoom+ '室以下'
                  }}
                </label>
                <label v-else-if="scope.row.minRoom &&  scope.row.maxRoom === 0 ">
                  {{
                     scope.row.minRoom+ '室以上'
                  }}
                </label>
                <label v-else>
                  {{
                      scope.row.minRoom+'-'+scope.row.maxRoom+'室'
                  }}
                </label>

            </span>




            <span v-else-if="fruit.prop === 'price'">
                <div v-if="scope.row.transactionTypeId === 4">
                          <label v-if=" scope.row.minPrice === 0   && scope.row.maxPrice">
                            {{
                               scope.row.maxPrice/1000000+ '万以下'

                            }}
                          </label>
                          <label v-else-if="scope.row.minPrice &&  scope.row.maxPrice === 0 ">
                            {{
                               scope.row.minPrice/1000000+ '万以上'
                            }}
                          </label>
                          <label v-else>
                            {{
                              scope.row.minPrice/1000000+'-'+ scope.row.maxPrice/1000000+'万'
                            }}
                          </label>
                 </div>
                 <div v-else-if="scope.row.transactionTypeId === 5">
                         <label v-if=" scope.row.minPrice === 0   && scope.row.maxPrice">
                            {{
                               scope.row.maxPrice/100+ '元以下'

                            }}
                          </label>
                          <label v-else-if="scope.row.minPrice &&  scope.row.maxPrice === 0 ">
                            {{
                               scope.row.minPrice/100+ '元以上'
                            }}
                          </label>
                          <label v-else>
                            {{
                              scope.row.minPrice/100+'-'+ scope.row.maxPrice/100+'元'
                            }}
                          </label>
                 </div>
            </span>


            <span v-else-if="fruit.prop === 'space'">
              <!--{{ scope.row.minSpace + '-' + scope.row.maxSpace+'㎡' }}-->



                <label v-if=" scope.row.minSpace === 0   && scope.row.maxSpace">
                  {{
                     scope.row.maxSpace+ '㎡以下'

                  }}
                </label>
                <label v-else-if="scope.row.minSpace &&  scope.row.maxSpace === 0 ">
                  {{
                     scope.row.minSpace+ '㎡以上'
                  }}
                </label>
                <label v-else>
                  {{
                    scope.row.minSpace + '-' + scope.row.maxSpace+'㎡'
                  }}
                </label>
            </span>

            <span v-else-if="fruit.prop === 'levelType'">
              {{ scope.row.levelType + '类客源' }}
            </span>

            <span v-else>
              {{ scope.row[fruit.prop] }}
            </span>

          </template>
        </el-table-column>

      </template>

    </el-table>

    <el-dialog :visible.sync="dialogVisible" title="自定义列表" width="600px">
      <div>

        <el-checkbox-group v-model="checkboxVal">
          <el-checkbox style="margin-left: 0; margin-right: 20px; margin-bottom: 20px" v-for="(opt, index) in formTheadOptions" :key="index" :label="opt">{{opt}}</el-checkbox>
        </el-checkbox-group>

        <div class="btn-group" style="margin-top: 10px">
          <el-button type="primary" :loading="loadingSubmitBtn" @click="saveTableColHidden">保存</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </div>

      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {setTableColHidden, getTableColHiddenList} from '@/request/customer/customerUsed'

  /**
   * 自定义表头 属性
   * prop (columns 值)
   * label (表头文本内容)
   */
  const formThead = [
    {
      prop: 'labeld',
      label: '标签',
    },
    {
      prop: 'id',
      label: '需求编号',
      width: 120
    },
    {
      prop: 'discStatus',
      label: '状态'
    },
    {
      prop: 'houseUses',
      label: '需求用途'
    },
    {
      prop: 'custName',
      label: '客户姓名'
    },
    {
      prop: 'sourceType',
      label: '客户来源',
      width: 90
    },
    {
      prop: 'areaNames',
      label: '需求区域'
    },
    {
      prop: 'space',
      label: '需求面积'
    },
    {
      prop: 'price',
      label: '需求价格'
    },
    {
      prop: 'room',
      label: '户型'
    },
    {
      prop: 'communicateStage',
      label: '沟通阶段'
    },
    {
      prop: 'remark',
      label: '备注'
    },
    {
      prop: 'levelType',
      label: '等级'
    },
    {
      prop: 'recordTime',
      label: '录入日期'
    },
    {
      prop: 'lastAllShowedTime',
      label: '全员最后维护'
    },
    {
      prop: 'lastBackTime',
      label: '最后带看时间'
    },
    {
      prop: 'mainterFllowTime',
      label: '维护人最后维护'
    },
    {
        prop: 'maintainer',
      label: '维护人',
    },
    {
      prop: 'mainterDept',
      label: '部门'
    }
  ]
  export default {
    props: {
      // defaultFormThead: { // 默认表头自定义数据
      //   type: Array,
      //   default () {
      //     return []
      //   }
      // },
      //
      // /**
      //  * 自定义表头 属性
      //  * prop (columns 值)
      //  * label (表头文本内容)
      //  */
      // formThead: {
      //   type: Array,
      //   default () {
      //     return [] // { prop: 项  label：表头text align 文本显示方式 width 宽度}
      //   },
      //   required: true
      // },
      tableData: { // table数据
        type: Array,
        default () {
          return []
        },
        required: true
      },
      // formTheadOptions: { // 自定义table 选项集合
      //   type: Array,
      //   default () {
      //     return []
      //   },
      //   required: true
      // }
    },
    data () {
      return {
        formThead: formThead,
        formTheadOptions: formThead.map(item => item.label),
        key: 1, // table key
        checkboxVal: [], // checkboxVal
        dialogVisible: false,
        _formThead: [],
        loadingSubmitBtn: false
      }
    },
    methods: {
      // 保存table自定列表
      saveTableColHidden () {
        this.loadingSubmitBtn = true
        let valArr = this.checkboxVal
        let hiddenCols = []
        this.formThead.forEach(item => {
          if (valArr.indexOf(item.label) === -1) hiddenCols.push(item.prop)
        })

        let params = {
          formName: 'house_customer',
          columns: hiddenCols.join()
        }

        setTableColHidden(params).then(() => {
          this.$message({
            type: 'success',
            message: '操作成功'
          })
          this.loadingSubmitBtn = false
          this.dialogVisible = false
        }).catch(() => {
          this.loadingSubmitBtn = false
        })
      },

      handleSelectionChange (val) {
        this.$emit('selectionChange', val)
      },

      toDetail (id, name, type) {
        this.$router.push({ path: '/customer/customerDetail/' + id,  query: {formName: name, transactionTypeId: type}})
        window.localStorage.setItem('customerList', JSON.stringify(this.tableData))
      },

      // 设置选中
      setCheckboxVal () {
        getTableColHiddenList({formName: 'house_customer'}).then(res => {
          if (res.data.columns && res.data.columns.length) {
            let columns = res.data.columns
            this.formThead.forEach(item => {
              if (columns.indexOf(item.prop) === -1) {
                this.checkboxVal.push(item.label)
              }
            })
            console.log(this.checkboxVal)
          }
        })
      }
    },
    created () {
      this._formThead = this.formThead.concat()
      this.setCheckboxVal()
    },
    watch: {
      checkboxVal(valArr) {
        let cls = []
        this.formTheadOptions.forEach(i => {
          if (valArr.indexOf(i) >= 0) {
            this.formThead.forEach(item => {
              if (item.label === i) cls.push(item)
            })
          }
        })
        this._formThead = cls
        this.key = this.key + 1// 为了保证table 每次都会重渲
      }
    },
    mounted(){
    }
  }
</script>

<style scoped lang="scss">
  .wrapper {
    min-height: 500px;
  }
  .operates {
    padding: 5px;
    border: 1px solid #ebeef5;
    border-bottom: none;
  }

  .houseId {
    color: #409eff;
    &:hover {
      text-decoration: underline;
    }
  }
</style>
