<template>
  <div :style="{height:height,width:width}"></div>
</template>

<script>
import echarts from 'echarts'

require('echarts/theme/macarons') // echarts theme
export default {
  name: 'TaxationPieChart',
  data () {
    return {
      width: '100%',
      height: '150px',
      pieData:[]
    }
  },
  props: ['chartData'],
  methods: {
    initChart () {
      this.chart = echarts.init(this.$el, 'macarons')
      this.setOptions()
    },
    setOptions () {
      this.chart.setOption({
        tooltip: {
          show: true, // 防止鼠标移到不需要的数据上弹出label
          trigger: 'item',
          formatter: "{b}: {c} ({d}%)"
        },
        legend: {
          orient: 'vertical',
          x: 'left',
          itemHeight: 10, // 图例的高度
          itemGap: 2, // 图例之间的间距
          //bottom: 'bottom',
          data: ['增值税及附加','契税','个人所得税','土地出让金'] // 图例的名字需要和饼图的name一致，才会显示图例
        },
        series: [
          {
            name: '',
            type: 'pie',
            //radius: ['35%', '30%'], // 环的位置
            //radius : '55%',
            center: ['70%', '60%'],
            label: {
              normal: {
                show: false,
                position: 'center'
              }
            },
            labelLine: {
              normal: {
                show: false
              }
            },
            data: this.pieData
          }
        ]
      })
    }
  },
  mounted () {console.log(this.chartData)
    console.log('饼图数据',this.chartData)
    if (this.chartData) {
      this.pieData = this.chartData
      let isRun=false
      for (let i=0;i<this.chartData.length;i++) {
        if (this.chartData[i].value>10000) {
          isRun=true
          break
        }
      }
      if (isRun){
        for (let i=0;i<this.pieData.length;i++) {
          this.pieData[i].value = parseFloat((this.pieData[i].value/10000).toFixed(2))
        }
      }
      console.log(isRun,this.pieData)
    }
    setTimeout(() => {
      this.initChart()
    }, 300)
  }
}
</script>

<style scoped>

</style>
