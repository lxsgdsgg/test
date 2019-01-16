export default {
  props: {
    //20181218
    disabled:{
      type : Boolean,
      default : false
    },
    placeholder: {
      type: String,
      default: '请选择'
    },
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
      type: String
    },
    multiple: {
      type: Boolean,
      default: false
    },
    collapseTags: {
      type: Boolean,
      default: false
    },
    data: {
      type: Array,
      default () {
        return []
      }
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
      if (this.url) {
        this.$get(this.url, {}).then(res => {
          this.options = res
        })
      } else {
        this.data && (this.options = this.data)
      }
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
        let label = ''
        this.options.forEach(item => {
          if (item[this.props.value] === val) {
            label = item[this.props.label]
          }
        })
        return label
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
    },
    data () {
      this._getOptions()
    }
  }
}
