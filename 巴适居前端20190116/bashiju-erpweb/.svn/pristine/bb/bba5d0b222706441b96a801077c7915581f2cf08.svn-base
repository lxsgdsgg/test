<template>
  <div class="wrapper" v-loading="loadingView">
    <selection-tree-table
      class="tree-table"
      :data="data"
      :expandAll="true"
      :maxHeight="580"
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
      <el-button type="primary" :loading="loadingBtn" @click="handleSubmit">确认</el-button>
      <el-button @click="handleCancel">取消</el-button>
    </div>

    <el-dialog
      width="600px"
      title="操作权限"
      :visible.sync="innerVisible"
      append-to-body>

      <opt-buttons
        v-if="hackReset"
        :opts="permissionOperates"
        :type="optType"
        @submit="innerHandleSubmit"
        @cancel="innerHandleCancel"
      ></opt-buttons>

    </el-dialog>
  </div>
</template>

<script>
import SelectionTreeTable from '@/components/BaseTreeTable/selection'
import {queryDeptSelect} from '@/request/manage/common'
import {enterAcrossPermissionNew, saveAcrossStorePermission} from '@/request/manage/deptmanage'
import OptButtons from './OptButtons'
const TYPE_MAP = {
  HOUSE: '房源',
  RENT: '求租',
  BUY: '求购'
}

const TYPE_DICTCODES = {
  '房源': 'house',
  '求租': 'rent',
  '求购': 'buy',
}

export default {
  name: 'DeptPermission',
  components: {SelectionTreeTable, OptButtons},
  props: {
    deptId: {
      type: [Number, String],
      required: true
    },
  },
  data () {
    return {
      columns: [
        {
          text: '门店名称',
          width: 200,
          value: 'name'
        },
        {
          text: TYPE_MAP.HOUSE,
          value: null // 通过后台获取
        },
        {
          text: TYPE_MAP.BUY,
          value: null
        },
        {
          text: TYPE_MAP.RENT,
          value: null
        }
      ],
      data: [],
      permissionOperates: [],
      optType: '',
      innerVisible: false,
      loadingView: false,
      buyOpts: [],
      rentOpts: [],
      houseOpts: [],
      currentRowData: null,
      currentColumn: null,
      hackReset: true,
      loadingBtn: false,
      menuMap: {} // 菜单type id 对象
    }
  },
  methods: {
    handleCancel () {
      this.$emit('handleClick', 2)
    },

    handleSubmit () {
      console.log(this.$utils.treeToArray(this.data))
      this.$confirm('确定保存设置的信息吗?', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        this.loadingBtn = true

        let dataArray = this.$utils.treeToArray(this.data)
        let params = {}
        let jsonData = []
        // 生成权限信息
        dataArray.forEach((item, index) => {
          let menuIds = []

          this.columns.forEach(column => {
            // checkBox绑定的 源数据属性判断是否选中
            if (column.value !== 'name' && item[column.value]) {
              menuIds.push(column.value)

              // 添加对应操作按钮id集合
              let optChecksKey = TYPE_DICTCODES[column.text] + 'OptChecks'
              if (item[optChecksKey]) {
                menuIds = menuIds.concat(item[optChecksKey])
              }

              jsonData[index] = {
                deptId: this.deptId,
                overDeptId: item.id,
                menuId: menuIds.join() // 添加选中的权限操作按钮
              }
            }

          })
        })
        params.deptId = this.deptId
        let _jsonData = []
        jsonData.forEach(item => {
          if (item !== ('' && 'undefined')) {
            _jsonData.push(item)
          }
        })
        params.jsonData = JSON.stringify(_jsonData)
        saveAcrossStorePermission(params).then(res => {
          this.loadingBtn = false

          this.$message({
            type: 'success',
            message: res.msg || '操作成功'
          })
          this.$emit('handleClick', 1)
        }).catch(() => {
          this.loadingBtn = false
        })
      })


    },

    // 弹出设置对应操作权限按钮
    slotButtonClick (props) {
      this.permissionOperates = []
      let {row, column} = props
      // 当前操作的源列表 行数据
      this.currentRowData = row
      this.currentColumn = column

      // if (!this.currentRowData[column.value]) {
      //   this.$message({
      //     type: 'warning',
      //     message: '请先勾选对应权限',
      //     showClose: true
      //   })
      //   return
      // }

      this.hackReset = false
      this.$nextTick(() => {

        this.hackReset = true

        // 房源
        if (column.text === TYPE_MAP.HOUSE) {
          this.permissionOperates = this.$jsonUtils.copyArr(this.houseOpts)
          this.optType = TYPE_MAP.HOUSE
        }

        // 求购
        if (column.text === TYPE_MAP.BUY) {
          this.permissionOperates = this.$jsonUtils.copyArr(this.buyOpts)
          this.optType = TYPE_MAP.BUY
        }

        // 求租
        if (column.text === TYPE_MAP.RENT) {
          this.permissionOperates = this.$jsonUtils.copyArr(this.rentOpts)
          this.optType = TYPE_MAP.RENT
        }

        // 选中
        let checkeds = this.currentRowData[TYPE_DICTCODES[this.optType] + 'OptChecks']
        if (checkeds && checkeds.length) {
          checkeds.forEach(item => {
            this.permissionOperates.forEach(opt => {
              if (opt.id === item) {
                opt.checked = true
              }
            })
          })
        }
        this.innerVisible = true

      })
    },

    innerHandleSubmit (checkeds, type) {
      this.currentRowData[TYPE_DICTCODES[type] + 'OptChecks'] = checkeds
      // 如果对应权限操作按钮集合有选中的 勾选外层
      this.columns.forEach(column => {
        if (column.text === type) {
          if (checkeds.length > 0) {
            this.currentRowData[column.value] = true
          }
        }
      })

      this.innerVisible = false
    },

    innerHandleCancel () {
      this.innerVisible = false
    },

    handleChange (data) {
      const key = data.column.value
      const val = data.row[key]
      // 若存在子级
      if (data.row.children && data.row.children.length) {
        this._subNodesOp(data.row.children, key, val)
      }
      //
      // // 若存在父级
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
    },

    _getData () {
      this.loadingView = true
      enterAcrossPermissionNew({deptId: this.deptId}).then(res => {
        let {depts, buyMenuId, houseMenuId, rentMenuId, houseBtn, rentBtn, buyBtn, acrossStorePermission} = res

        // 给每条数据绑定model表单属性 方便操作
        depts.forEach(dept => {
          dept[houseMenuId] = false
          dept[rentMenuId] = false
          dept[buyMenuId] = false
        })

        // 生成树结构
        this.data = this.$utils.toTreeData(depts, {id: 'id', parent: 'parentId'})

        // 列属性
        this.columns.forEach(item => {
          if (item.text === TYPE_MAP.HOUSE) {
            item.value = houseMenuId
          } else if (item.text === TYPE_MAP.RENT) {
            item.value = rentMenuId
          } else if (item.text === TYPE_MAP.BUY) {
            item.value = buyMenuId
          }
        })

        // 赋值对应权限操作按钮 type集合
        this.menuMap[houseMenuId] = 'house'
        this.menuMap[buyMenuId] = 'buy'
        this.menuMap[rentMenuId] = 'rent'

        this.houseOpts = houseBtn
        this.buyOpts = buyBtn
        this.rentOpts = rentBtn

        // 权限信息
        this.acrossStorePermission = acrossStorePermission
        // 设置权限信息页面显示
        this._setFormInfo(res)

        this.loadingView = false
      })
    },

    _setFormInfo (data) {
      let Per = data.acrossStorePermission || []
      let deptData = data.depts || []

      // 所有权限按钮集合
      let buttons = {
        house: data.houseBtn || [],
        buy: data.buyBtn || [],
        rent: data.rentBtn || [],
      }

      Per.forEach(item => {
        let overDeptId = item.overDeptId
        let menuIds = item.menuId.split(',')

        deptData.forEach(dept => {
          if (dept.id === overDeptId) {

            menuIds.forEach(id => {

              if (dept[id] === false) {
                dept[id] = true

                // 给该对象设置一个空数组来保存权限按钮集合
                dept[this.menuMap[id] + 'OptChecks'] = []

                // 该类型对应按钮集合
                let opts = buttons[this.menuMap[id]]

                opts.forEach(opt => {
                  // 选中对应按钮
                  if ( menuIds.includes( String(opt.id) ) ) {
                    dept[this.menuMap[id] + 'OptChecks'].push(opt.id)
                  }
                })
              }

            })

          }
        })

      })
    }
  },
  mounted () {
    this._getData()
  },

  watch: {
    deptId () {
      this._getData()
    }
  },
}
</script>

<style lang="scss" scoped>
  .wrapper {
    .tree-table {
      min-height: 600px;
    }
  }
</style>
