export default {
  props: {
    multiple: {
      type: Boolean,
      default () {
        return false
      }
    },

    data: {
      type: Array,
      default () {
        return []
      }
    },
    disabled: {
      type: Boolean,
      default () {
        return false
      }
    },
    changeOnSelect: {
      type: Boolean,
      default: true
    },
    showAllLevels: {
      type: Boolean,
      default: false
    },
    expandTrigger: {
      type: String,
      default: 'click'
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
    },
    placeholder: {
      type: String,
      default: '请选择'
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
      originalData: [],
      tags: []
    }

  },
  methods: {
    handleChange (val) {
      let data = null // 选中项数据
      let value = '' // 选中项value
      let label = '' // 选中项label
      if (val.length > 0) {
        data = this._getCascaderSel(val[val.length - 1]) // 选中项数据
        value = val[val.length - 1] // 选中项value
        label = data[this.props.label] // 选中项label
      }

      this.$emit('change', value, label, data)
    },
    _getOptions () {
      let options = []
      if (this.url) {
        this.$get(this.url, {}).then(res => {
          this.originalData = res
          options = this.$utils.toTreeData(res, this.dataProps)
          if (typeof this.value === 'object') {
            this._getValues(res, this.value[this.value.length - 1])
          } else {
            this._getValues(res, this.value)
          }
          this.options = options

          setTimeout(() => {
            this.$emit('initSelectData', options)
          }, 50)
        })


      } else {
        this.originalData = this.data
        if (typeof this.value === 'object') {
          this._getValues(this.data, this.value[this.value.length - 1])
        } else {
          this._getValues(this.data, this.value)
        }
        this.data && (options = this.$utils.toTreeData(this.data, this.dataProps))
        this.options = options
      }
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
    _getCascaderSel (val) {
      const data = this.originalData
      for (let item of data) {
        if (item[this.props.value] === val) {
          return item
        }
      }
    },

    handleMultipleChange (val) {
      // this.selOpts = []
      let data = null // 选中项数据
      let value = '' // 选中项value
      let label = '' // 选中项label

      let emitVals = []
      let emitDatas = []

      if (val.length > 0) {
        this._pushTags(val[val.length - 1])
        this.cascaderVal = [val[val.length - 1]]
      } else {
        this.tags = []
      }


      this.tags.forEach(tag => {
        emitVals.push(tag.value)
        emitDatas.push(tag.data)
      })
      this.$emit('change', emitVals, emitDatas)
    },

    handleTagClose (tag) {
      this.tags.splice(this.tags.indexOf(tag), 1)
    },

    tagsClick () {
      let el = this.$refs['cascader']
      if (el) {
        el.menuVisible = true
      }
    },

    _setTags (values) {
      values.forEach(val => {
        this._pushTags(val)
      })
    },

    _pushTags (val) {
      let data = this._getCascaderSel(val)
      let label = data[this.props.label]
      let value = data[this.props.value]

      let tag = {label: label, value: value, data: data}

      if (this.tags.length) {
        let added = true
        this.tags.forEach(item => {
          if (item.value === tag.value) {
            added = false
          }
        })

        if (added) {
          this.tags.push(tag)
        }

      } else {
        this.tags.push(tag)
      }


    }
  },
  mounted () {
    this._getOptions()

    if (this.multiple) {
      this._setTags(this.value)
    }
  },
  watch: {
    value (newValue) {
      if (!this.multiple) {
        this.cascaderVal = []
        if (newValue !== '') {
          this._getValues(this.originalData, newValue)
        }
      } else {
        this.cascaderVal = [newValue[newValue.length - 1]]
        console.log(this.cascaderVal)
      }

    },

    url () {
      this._getOptions()
    },

    data () {
      this._getOptions()
    }
  }
}
