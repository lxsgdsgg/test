<template>
  <div :style="{height:height,width:width}"></div>
</template>

<script>
import echarts from 'echarts'

//标示文字
const labelTop = {
  normal: {
    label: {
      show: false,
      position: 'center',
      formatter: '{b}',
      textStyle: {
        baseline: 'bottom',  //垂直对齐方式
        fontSize: 12,
        color: '#666',
        align: 'center'  //水平对齐方式
      }
    },
    color: '#de4751',
    labelLine: {  //标示线
      show: false
    }
  }
}
//百分比设置
const labelFromatter = {
  normal: {
    label: {
      formatter: function (params) {
        return 100 - params.value + '/' + '158'
      },
      textStyle: {
        baseline: 'middle',  //垂直对齐方式
        fontSize: 27,
        color: '#666',
        align: 'center'   //水平对齐方式
      }
    }
  }
}
//数据位置
const labelBottom = {
  normal: {
    color: '#ccc',
    label: {
      show: true,
      position: 'center'
    },
    labelLine: {
      show: false
    }
  },
  emphasis: {
    color: 'rgba(0,0,0,0)'
  }
}

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
      default: '260px'
    },
    cfg: {
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
      if (!Object.keys(this.cfg).length) return
      this.chart = echarts.init(this.$el, 'macarons')
      this.setOptions()
    },
    setOptions () {
      this.chart.setOption({
        // title: {
        //   text: '带看',
        //   bottom: 30,
        //   left: '40%'
        // },
        graphic:[{
          type:'text',
          left:'center',
          top:'40%',
          style:{
            text: this.cfg.value,
            fill:'#ff0000',
            width:30,
            height:30,
            fontSize:16,
          }
        },{
          type:'text',
          left:'center',
          top:'48%',
          style:{
            text: this.cfg.subValue,
            fill:'#000',
            width:30,
            height:30,
            fontSize: 14,
          }
        },{
          type:'text',
          left:'center',
          top:'55%',
          style:{
            text: `( ${this.cfg.unit} )`,
            fill:'#000',
            width:30,
            height:30,
            fontSize: 14,
          }
        }],
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
          show: false
        },
        series: [
          {
            name: this.cfg.name,
            type:'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
              normal: {
                show: false,
                position: 'center'
              },
              emphasis: {
                show: false,
                textStyle: {
                  fontSize: '30',
                  fontWeight: 'bold'
                }
              }
            },
            labelLine: {
              normal: {
                show: false
              }
            },
            data:[
              {value: this.cfg.data1, name:'已完成', itemStyle: { color: this.cfg.color}},
              {value: this.cfg.data2, name:'未完成', itemStyle: { color: '#e1e1e1'}}
            ]
          }
        ]
      })
    }
  },
  mounted () {
    this.initChart()
  },
  watch: {
    cfg: {
      deep: true,
      handler () {
        this.initChart()
      }
    }
  },
}
</script>

<style scoped>

</style>
