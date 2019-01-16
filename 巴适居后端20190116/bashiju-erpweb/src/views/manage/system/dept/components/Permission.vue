<template>
  <div>
    <selection-tree-table
      :data="data"
      :expandAll="true"
      :maxHeight="400"
      :columns="columns"
      size="small"
      border
      @handleChange="handleChange"
    >
      <template slot-scope="props">
        <div>
          <el-button style="font-size: 12px"  @click.native="slotButtonClick(props)" type="text">操作权限</el-button>
        </div>
      </template>
    </selection-tree-table>

    <div class="btn-group">
      <el-button type="primary" @click="handleSubmit">确认</el-button>
      <el-button @click="handleCancel">取消</el-button>
    </div>

    <el-dialog
      width="480px"
      title="操作权限"
      :visible.sync="innerVisible"
      append-to-body>
        <section class="operate-wrap">
          <div class="operate" :key="item.id" v-for="item in permissionOperates">
            <el-checkbox size="small" :label="item.name" :checked="item.checked" border></el-checkbox>
          </div>
        </section>
        <div class="btn-group">
          <el-button type="primary" @click="innerHandleSubmit">确认</el-button>
          <el-button @click="innerHandleCancel">取消</el-button>
        </div>
    </el-dialog>
  </div>
</template>

<script>
import SelectionTreeTable from '@/components/BaseTreeTable/selection'

export default {
  name: 'DeptPermission',
  components: {SelectionTreeTable},
  data () {
    return {
      columns: [
        {
          text: '门店名称',
          width: 150,
          value: 'deptName'
        },
        {
          text: '房源',
          value: 'house',
          slot: '<span class="button-text" @click.native="slotHandle">操作权限</span>'
        },
        {
          text: '求购',
          value: 'buy',
          slot: '<span class="button-text">操作权限</span>'
        },
        {
          text: '求租',
          value: 'rent',
          slot: '<span class="button-text">操作权限</span>'
        }
      ],
      data: [
        {
          id: 1,
          deptName: '总部',
          house: false,
          rent: false,
          buy: false,
          children: [
            {
              id: 2,
              deptName: '行政部',
              house: false,
              rent: false,
              buy: false,
              children: [
                {
                  deptName: '行政1部',
                  house: false,
                  rent: false,
                  buy: false
                },
                {
                  deptName: '行政2部',
                  house: false,
                  rent: false,
                  buy: false
                }
              ]
            },
            {
              id: 3,
              deptName: '权证部',
              house: false,
              rent: false,
              buy: false
            },
            {
              id: 4,
              deptName: '人力资源部',
              house: false,
              rent: false,
              buy: false
            },
            {
              id: 5,
              deptName: '经济事业部',
              house: false,
              rent: false,
              buy: false
            }
          ]
        },
        {
          id: 6,
          deptName: '财务部',
          house: false,
          rent: false,
          buy: false
        },
        {
          id: 7,
          deptName: '呈贡区',
          house: false,
          rent: false,
          buy: false,
          children: [
            {
              id: 8,
              deptName: '呈贡一区',
              house: false,
              rent: false,
              buy: false
            },
            {
              id: 9,
              deptName: '呈贡二区',
              house: false,
              rent: false,
              buy: false
            },
            {
              id: 10,
              deptName: '呈贡三区',
              house: false,
              rent: false,
              buy: false
            }
          ]
        }
      ],
      permissionOperates: [
        {
          name: '修改信息',
          id: 1,
          checked: false
        },
        {
          name: '设置私盘',
          id: 2,
          checked: false
        },
        {
          name: '房源开盘',
          id: 3,
          checked: false
        },
        {
          name: '新增',
          id: 4,
          checked: false
        },
        {
          name: '房源置顶',
          id: 5,
          checked: false
        },
        {
          name: '变更状态',
          id: 6,
          checked: false
        },
        {
          name: '设置钥匙',
          id: 7,
          checked: false
        },
        {
          name: '变更维护人',
          id: 8,
          checked: false
        },
        {
          name: '更改用途',
          id: 9,
          checked: false
        },
        {
          name: '修改电话',
          id: 10,
          checked: false
        },
        {
          name: '设置委托',
          id: 11,
          checked: false
        }
      ],
      innerVisible: false
    }
  },
  methods: {
    handleCancel () {
      this.$emit('handleClick', 2)
    },
    handleSubmit () {
      this.$emit('handleClick', 1)
    },
    innerHandleCancel () {
      this.innerVisible = false
    },
    innerHandleSubmit () {
      this.innerVisible = false
    },
    slotButtonClick (props) {
      console.log(props)
      this.innerVisible = true
    },
    handleChange (data) {
      const key = data.column.value
      const val = data.row[key]
      // 若存在子级
      if (data.row.children && data.row.children.length) {
        this._subNodesOp(data.row.children, key, val)
      }

      // 若存在父级
      if (data.row.parent) {
        this._supNodesOp(data.row.parent, key, val)
      }
    },
    /**
     * @param childNodes 子节点
     * @param key 要操作的数据项
     * @param val 选中状态
     */
    _subNodesOp (childNodes, key, val) {
      childNodes.forEach(item => {
        item[key] = val

        if (item.children && item.children.length) {
          this._subNodesOp(item.children, key, val)
        }
      })
    },
    /**
     * @param parent 父节点
     * @param key 要操作的数据项
     * @param val 选中状态
     */
    _supNodesOp (parent, key, val) {
      let flag = true
      parent.children.forEach(item => {
        if (!item[key]) {
          flag = false
        }
      })
      parent[key] = flag
      if (parent.parent) {
        this._supNodesOp(parent.parent, key, val)
      }
    }
  }
}
</script>

<style lang="scss" scoped>
  .btn-group {
    margin-top: 20px;
  }

  .operate-wrap {
    .operate {
      display: inline-block;
      margin-bottom: 10px;
      width: 25%;
    }

  }
</style>
