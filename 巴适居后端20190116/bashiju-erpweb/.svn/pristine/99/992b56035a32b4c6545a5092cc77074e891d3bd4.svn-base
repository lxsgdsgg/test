export default {
  props: {
    filterable: {
      type: Boolean,
      default: true
    },
    clearable: {
      type: Boolean,
      default: true
    },
    size: {
      type: String
    },
    value: {
      type: [String, Number, Array],
      default: ''
    },
    url: {
      type: String,
      required: true
    },
    multiple: {
      type: Boolean,
      default: false
    },
    props: {
      type: Object,
      default () {
        return {
          label: 'name', // 指定选项的值绑定为下拉框的label属性
          value: 'id' // 指定选项的值绑定为下拉框的Value属性
        }
      }
    }
  },
  model: {
    prop: 'value',
    event: 'change'
  },
  data () {
    return {
      val: this.value,
      options: []
    }
  },
  methods: {
    /**
     * 获取下拉选择数据
     */
    _getOptions () {
      this.$get(this.url, {}).then(res => {
        this.options = res
      })
    },
    handleChange (val) {
      const changeLabel = this._getSelectLabel(val)
      this.$emit('change', val, changeLabel)
    },
    _getSelectLabel (val) {
      if (this.multiple) {
        let labels = []

        this.options.forEach(opt => {
          val.forEach(item => {
            if (opt[this.props.value] === item) {
              labels.push(opt[this.props.label])
            }
          })
        })
        return labels
      } else {
        this.options.forEach(item => {
          if (item[this.props.value] === val) {
            return item[this.props.label]
          }
        })
      }
      return null
    }
  },
  mounted () {
    this._getOptions()
  },
  watch: {
    value (newValue) {
      this.val = newValue
    }
  }
}
