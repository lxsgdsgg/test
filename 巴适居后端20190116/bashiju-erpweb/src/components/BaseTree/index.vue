<template>
  <div
    v-loading="loading"
    element-loading-text="拼命加载中"
    element-loading-spinner="el-icon-loading"
  >
    <el-tree
      :data="treeData"
      :props="props"
      node-key="id"
      ref="tree"
      :show-checkbox="showCheckbox"
      :default-checked-keys="defaultCheckedNodes"
      :default-expanded-keys="defaultExpandedNodes"
      :check-strictly="checkStrictly"
      @check="currentNodeCheck"
      :highlight-current="true"
      :expand-on-click-node="false"
      @node-click="currentNodeClick"
      @check-change="checkChange"
    >
              <span class="custom-tree-node" slot-scope="{ node, data}">
                <span class="a-tree-label">{{data.name}}</span>
              </span>
    </el-tree>
  </div>
</template>

<script>
export default {
  name: 'BaseTree',
  props: {
    data: [Object, Array],
    props: [Object],
    showCheckbox: Boolean,
    checkStrictly: {
      // 在显示复选框的情况下，是否严格的遵循父子不互相关联的做法，默认为 false
      type: Boolean,
      default: false
    },
    singleCheck: {
      // 单选
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      loading: true,
      defaultCheckedNodes: [],
      defaultExpandedNodes: []
    }
  },

  methods: {
    currentNodeClick (nodeData, node) {
      this.$emit('nodeClick', nodeData, node)
    },
    currentNodeCheck (currentObj) {
      this.$emit('nodeCheck', currentObj)
    },
    checkChange (currentObj, isCheck) {
      // 单项选择
      if (this.singleCheck) {
        if (isCheck) {
          this.setCheckedNodes([currentObj])
          this.$emit('nodeSingleCheck', currentObj)
        }
      }
    },
    getDataState (state) {
      if (state) { // 数据获取完成
        this.loading = false
      } else {
        this.loading = true
      }
    },
    getCheckedNodes () {
      let nodes = this.$refs.tree.getCheckedNodes()
      return nodes
    },
    convertNodes () {
      let arr1 = []
      let arr2 = []
      this.data.map((item) => {
        item.ischeck && arr1.push(item.id)
        item.spread && arr2.push(item.id)
        if (item.children && item.children.length) {
          item.children.map((child) => {
            child.ischeck && arr1.push(child.id)
            child.spread && arr2.push(child.id)
          })
        }
      })
      this.defaultCheckedNodes = arr1
      this.defaultExpandedNodes = arr2
    },
    setCheckedNodes (nodes) {
      this.$refs.tree.setCheckedNodes(nodes)
    }
  },
  computed: {
    treeData () {
      this.convertNodes()
      return this.data
    }
  }
}
</script>

<style lang="scss" scoped>
  /*.custom-tree-node {*/
    /*flex: 1;*/
    /*display: flex;*/
    /*align-items: flex-start;*/
    /*justify-content: space-between;*/
    /*font-size: 14px;*/
    /*padding-right: 8px;*/
  /*}*/
  /*.a-tree-label {*/
    /*flex: 1;*/
  /*}*/
  /*.edit-form-wrap {
    border-left: 1px dashed $border-color;
  }*/
</style>
