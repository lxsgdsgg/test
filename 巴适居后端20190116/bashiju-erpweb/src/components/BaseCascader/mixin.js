export default {
  props: {
    changeOnSelect: {
      type: Boolean,
      default: true
    },
    showAllLevels: {
      type: Boolean,
      default: false
    },
    value: {
      default () {
        return []
      }
    },
    dataProps: {
      type: Object,
      /**
       *  源数据配置选项 用于将具有父子关系的原始数据格式化成树形结构数据
       *  id 指定选项的值 为源数据的id
       *  parent 指定选项的值 为源数据的parentKey
       */
      default () {
        return {id: 'id', parent: 'parentId'}
      }
    }
  },
  model: {
    prop: 'value',
    event: 'change'
  },
  data () {
    return {
      options: [],
      cascaderVal: [],
      selectVal: '',
      originalData: []
    }
  },
  methods: {
    handleChange (val) {
      const changeLabel = this._getCascaderLabel(val[val.length - 1])
      let value = val[val.length - 1]
      this.$emit('change', value, changeLabel)
    },
    _getOptions () {
      this.$get(this.url, {}).then(res => {
        this.originalData = res
        const options = this.$utils.toTreeData(res, this.dataProps)
        if (typeof this.value === 'object') {
          this._getValues(res, this.value[this.value.length - 1])
        } else {
          this._getValues(res, this.value)
        }
        this.options = options
      })
    },
    _getValues (data, val) {
      const dataProps = this.dataProps
      for (let i = 0; i < data.length; i++) {
        if (data[i][dataProps.id] === val) {
          this.cascaderVal.unshift(data[i][dataProps.id])
          let parentId = data[i][dataProps.parent]
          if (parentId) {
            this._getValues(data, parentId)
          } else {
            // noinspection JSAnnotator
            break
          }
        }
      }
    },
    _getCascaderLabel (val) {
      const data = this.originalData
      for (let item of data) {
        if (item[this.props.value] === val) {
          return item[this.props.label]
        }
      }
    }
  },
  mounted () {
    this._getOptions()
  },
  watch: {
    value (newValue) {
      if (newValue === '') this.cascaderVal = []
    }
  }
}
