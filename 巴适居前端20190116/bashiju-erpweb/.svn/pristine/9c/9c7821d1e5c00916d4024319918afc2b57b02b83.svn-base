<template>
  <div :style="{height:height,width:width}"></div>
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
export default {
  name: 'LineChart',
  props: {
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '350px'
    },
    chartData: {
      type: Object
    }
  },
  data () {
    return {
      chart: null
    }
  },
  methods: {
    initChart () {
      this.chart = echarts.init(this.$el, 'macarons')
      setTimeout(() => {
        this.setOptions()
      }, 100)
    },
    setOptions () {
      this.chart.setOption({
        tooltip: {},
        xAxis: {
          data: this.chartData.categories
        },
        yAxis: {
        },
        series: [{
          name: '业绩',
          type: 'bar',
          data: this.chartData.data
        }]
      })
    }
  },
  mounted () {
    setTimeout(() => {
      this.initChart()
    }, 300)
  },
  watch: {
    chartData: {
      deep: true,
      handler () {
        this.initChart()
      }
    }
  }
}
</script>

<style scoped>

</style>
