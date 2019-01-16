/*******************************************************************************************************/
/** *************************************** 列表页面查询 mixin***************************************/
/*******************************************************************************************************/

export default {
  data () {
    return {
      tableData: [],
      listQuery: {
        page: 1,
        limit: 10,
        total: 0,
        currentPage: 1
      },
      form: {},
      loadingQueryBtn: false,
      loadingView: false,
      listQueryParams: {}
    }
  },
  methods: {
    /**
     * 点击查询
     */
    handleQuery () {
      this.listQuery.page = 1
      this.listQuery.currentPage = 1
      this._loadData(true)
    },

    /**
     * 分页 pageSize 改变时会触发
     */
    handleSizeChange (val) {
      this.listQuery.limit = val
      this._loadData(false)
    },

    /**
     * 分页 currentPage  改变时会触发
     */
    handleCurrentChange (val) {
      this.listQuery.page = val
      this._loadData(false)
    },

    /**
     * 获取请求参数 默认只传递 page(页码) limit(每页条数) 可以由调用方法传递指定对象合并(或者覆盖)原参数
     * @param _params
     * @returns {*}
     */
    _getParams (_params) {
      let params = _params || {}
      return Object.assign({
        limit: this.listQuery.limit,
        page: this.listQuery.page
      }, params)
    },

    /**
     * @overwrite
     * 加载数据方法 用到该mixin的都应该重写该方法 否则无法实现加载数据
     */
    _loadData (btnQuery) {},

    /**
     *
     * @param formName 表单名称 model绑定的表单对象 和 element ref绑定的对象 名称应该保持一致
     * @param callback
     * @private
     */
    _resetForm (formName, callback) {
      this.$refs[formName] && this.$refs[formName].resetFields()
      if (callback && typeof callback === 'function') {
        callback()
      }
      // this.$refs[formName].resetFields()
      console.log(this[formName])
    },

    // 格式化日期
    _timeFormat (row, column, cellValue) {
      return this.$utils.timeFormat(cellValue)
    },
  },
  mounted () {
    this._loadData(false)
  }
}
