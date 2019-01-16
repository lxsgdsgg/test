<template>
  <div :style="{height:height,width:width}"></div>
</template>

<script>
import echarts from 'echarts'

require('echarts/theme/macarons') // echarts theme
export default {
  name: 'PieChart',
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
      this.setOptions()
    },
    setOptions () {
      this.chart.setOption({
        tooltip: {
          show: false, // 防止鼠标移到不需要的数据上弹出label
          trigger: 'item',
          formatter: '{a} : {c} ({d}%)'
        },
        animation: false,
        color: ['#34bfa3', '#e6b600', '#40c9c6', '#f4516c'],
        legend: {
          orient: 'vertical',
          x: 'left',
          itemHeight: 10, // 图例的高度
          itemGap: 8, // 图例之间的间距
          data: this.chartData.categories // 图例的名字需要和饼图的name一致，才会显示图例
        },
        series: [
          {
            name: '新房源录入量',
            type: 'pie',
            radius: ['35%', '30%'], // 环的位置

            label: {
              normal: {
                position: 'inner'
              }
            },
            labelLine: {
              normal: {
                show: false
              }
            },
            data: [
              {
                value: 32, // 需要显示的数据
                itemStyle: {
                  normal: {
                    color: '#34bfa3'
                  }
                }
              },
              {
                value: 45, // 不需要显示的数据，颜色设置成和背景一样
                itemStyle: {
                  normal: {
                    color: 'transparent'
                  }
                }
              }
            ]
          },
          {
            name: '客户接待量',
            type: 'pie',
            radius: ['40%', '45%'],

            label: {
              normal: {
                position: 'inner'
              }
            },
            labelLine: {
              normal: {
                show: false
              }
            },
            data: [
              {
                value: 44,
                itemStyle: {
                  normal: {
                    color: '#e6b600'
                  }
                }
              },
              {
                value: 66,
                itemStyle: {
                  normal: {
                    color: 'transparent'
                  }
                }
              }
            ]
          },
          {
            name: '客户带看量',
            type: 'pie',
            radius: ['55%', '50%'],

            label: {
              normal: {
                position: 'inner'
              }
            },
            labelLine: {
              normal: {
                show: false
              }
            },
            data: [
              {
                value: 11,
                itemStyle: {
                  normal: {
                    color: '#40c9c6'
                  }
                }
              },
              {
                value: 99,
                itemStyle: {
                  normal: {
                    color: 'transparent'
                  }
                }
              }
            ]
          },
          {
            name: '成交量',
            type: 'pie',
            radius: ['65%', '60%'],

            label: {
              normal: {
                position: 'inner'
              }
            },
            labelLine: {
              normal: {
                show: false
              }
            },
            data: [
              {
                value: 98,
                itemStyle: {
                  normal: {
                    color: '#f4516c'
                  }
                }
              },
              {
                value: 34,
                itemStyle: {
                  normal: {
                    color: 'transparent'
                  }
                }
              }
            ]
          }
        ]
      })
    }
  },
  mounted () {
    setTimeout(() => {
      this.initChart()
    }, 300)
  }
}
</script>

<style scoped>

</style>
