<template>
  <section>
    <div class="banner"></div>

    <div class="data-panel">
      <div class="hd">
        <div class="container clearfix">
          <div class="todo-wrap pull-left clearfix">
            <div class="todo-item">
              <icon
                class="icon"
                name="todo1"
                width="30"
                height="30"
                scale="20">
              </icon>
              <span class="text">待处理</span>
              <span class="num">5</span>
            </div>

            <div class="todo-item">
              <icon
                class="icon"
                name="todo2"
                width="30"
                height="30"
                scale="20">
              </icon>
              <span class="text">待跟进</span>
              <span class="num">5</span>
            </div>

            <div class="todo-item">
              <icon
                class="icon"
                name="todo3"
                width="30"
                height="30"
                scale="20">
              </icon>
              <span class="text">新消息</span>
              <span class="num">5</span>
            </div>

            <div class="todo-item">
              <icon
                class="icon"
                name="todo4"
                width="30"
                height="30"
                scale="20">
              </icon>
              <span class="text">待阅</span>
              <span class="num">5</span>
            </div>
          </div>

          <div class="search-wrap pull-right">
            <template>
              <el-radio-group class="radio-group" v-model="radio2">
                <el-radio class="white-color" :label="3">房源</el-radio>
                <el-radio class="white-color" :label="6">求购</el-radio>
                <el-radio class="white-color" :label="9">求租</el-radio>
              </el-radio-group>
            </template>

            <el-form size="small" class="search-form" :inline="true">
              <el-form-item>
                <el-input style="width: 300px" placeholder="请输入关键字，门牌号。业主姓名，业主电话。。。"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary">查询</el-button>

                <el-button type="primary" icon="el-icon-plus">新增房源</el-button>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </div>

      <div class="center container">
        <el-row  class="chart-wrapper" :gutter="20">
          <el-col class="chart-item ranking" :span="9">
            <div class="content">
              <el-tabs v-model="activeTabs1" @tab-click="handleTabClick1">
                <el-tab-pane class="tab-pane" label="本年前十业绩排行" name="year">
                </el-tab-pane>
                <el-tab-pane class="tab-pane" label="本月前十业绩排行" name="month">
                </el-tab-pane>

                <bar-chart :chartData="barChartData"></bar-chart>
              </el-tabs>
            </div>
          </el-col>

          <el-col class="chart-item progress" :span="9">
            <div class="content">
              <div class="title">本月工作完成进度</div>
              <div class="chart-content">
                <pie-chart :chartData="pieChartData"></pie-chart>
              </div>
            </div>
          </el-col>

          <el-col class="chart-item performance" :span="6">
            <div class="content">

              <div class="performance-item">
                <div class="icon-wrap icon-receivable">
                  <icon name="received" width="50" height="50" scale="20"></icon>
                </div>
                <div class="description-wrap">
                  <div class="text">应收业绩</div>
                  <div class="num">0</div>
                </div>
              </div>

              <div class="performance-item">
                <div class="icon-wrap icon-uncollected">
                  <icon name="uncollected" width="50" height="50" scale="20"></icon>
                </div>
                <div class="description-wrap">
                  <div class="text">未收业绩</div>
                  <div class="num">0</div>
                </div>
              </div>

              <div class="performance-item">
                <div class="icon-wrap icon-received">
                  <icon name="receivable" width="50" height="50" scale="20"></icon>
                </div>
                <div class="description-wrap">
                  <div class="text">实收业绩</div>
                  <div class="num">0</div>
                </div>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>

      <div class="bd container">
        <el-row class="news-wrapper" :gutter="20">
          <el-col class="news-item house" :span="12">
            <div class="content">
              <div class="title">最新房源</div>
              <vue-seamless-scroll :data="listData" :class-option="classOption" class="seamless-warp">
                <ul class="list-wrap">
                  <li :key="index" v-for="(item, index) in listData">
                    <div class="pull-left">
                      <template>{{item.text}}</template>
                      <span v-if="item.type === 1" class="status rent">出租</span>
                      <span v-else-if="item.type === 2" class="status sell">出售</span>
                    </div>

                    <div class="pull-right">
                      <span class="span-time">{{item.time}}</span>
                    </div>
                  </li>
                </ul>
              </vue-seamless-scroll>
            </div>
          </el-col>
          <el-col class="news-item company" :span="12">
            <div class="content">
              <el-tabs v-model="activeTabs2" type="card">
                <el-tab-pane class="tab-pane" label="公司公告" name="company1">
                  <ul class="list-wrap">
                    <li>
                      <div class="pull-left">
                        【公司公告】关于国庆节的假期安排说明
                      </div>

                      <div class="pull-right">
                        <span class="span-time">2018-4-12</span>
                      </div>
                    </li>

                    <li>
                      <div class="pull-left">
                        【公司公告】关于国庆节的假期安排说明
                      </div>

                      <div class="pull-right">
                        <span class="span-time">2018-4-12</span>
                      </div>
                    </li>

                    <li>
                      <div class="pull-left">
                        【公司公告】关于国庆节的假期安排说明
                      </div>

                      <div class="pull-right">
                        <span class="span-time">2018-4-12</span>
                      </div>
                    </li>

                    <li>
                      <div class="pull-left">
                        【公司公告】关于国庆节的假期安排说明
                      </div>

                      <div class="pull-right">
                        <span class="span-time">2018-4-12</span>
                      </div>
                    </li>

                    <li>
                      <div class="pull-left">
                        【公司公告】关于国庆节的假期安排说明
                      </div>

                      <div class="pull-right">
                        <span class="span-time">2018-4-12</span>
                      </div>
                    </li>
                  </ul>
                </el-tab-pane>
                <el-tab-pane class="tab-pane" label="公司新闻" name="company2">
                  <ul class="list-wrap">
                    <li>
                      <div class="pull-left">
                        【最新房源】<span class="status rent">出租</span>欣都龙城15楼两室一厅80平米1500/月精装修
                      </div>

                      <div class="pull-right">
                        <span class="span-time">2018-4-12</span>
                      </div>
                    </li>

                    <li>
                      <div class="pull-left">
                        【最新房源】<span class="status sell">出售</span>欣都龙城15楼两室一厅80平米1500/月精装修
                      </div>

                      <div class="pull-right date">
                        <span class="span-time">2018-4-12</span>
                      </div>
                    </li>

                    <li>
                      <div class="pull-left">
                        【最新房源】<span class="status rent">出租</span>欣都龙城15楼两室一厅80平米1500/月精装修
                      </div>

                      <div class="pull-right">
                        <span class="span-time">2018-4-12</span>
                      </div>
                    </li>

                    <li>
                      <div class="pull-left">
                        【最新房源】<span class="status rent">出租</span>欣都龙城15楼两室一厅80平米1500/月精装修
                      </div>

                      <div class="pull-right">
                        <span class="span-time">2018-4-12</span>
                      </div>
                    </li>

                    <li>
                      <div class="pull-left">
                        【最新房源】<span class="status sell">出租</span>欣都龙城15楼两室一厅80平米1500/月精装修
                      </div>

                      <div class="pull-right">
                        <span class="span-time">2018-4-12</span>
                      </div>
                    </li>
                  </ul>
                </el-tab-pane>
                <el-tab-pane class="tab-pane" label="留言提醒" name="company3">
                  <ul class="list-wrap">
                    <li>
                      <div class="pull-left">
                        【最新房源】<span class="status rent">出租</span>欣都龙城15楼两室一厅80平米1500/月精装修
                      </div>

                      <div class="pull-right">
                        <span class="span-time">2018-4-12</span>
                      </div>
                    </li>

                    <li>
                      <div class="pull-left">
                        【最新房源】<span class="status sell">出售</span>欣都龙城15楼两室一厅80平米1500/月精装修
                      </div>

                      <div class="pull-right date">
                        <span class="span-time">2018-4-12</span>
                      </div>
                    </li>

                    <li>
                      <div class="pull-left">
                        【最新房源】<span class="status rent">出租</span>欣都龙城15楼两室一厅80平米1500/月精装修
                      </div>

                      <div class="pull-right">
                        <span class="span-time">2018-4-12</span>
                      </div>
                    </li>

                    <li>
                      <div class="pull-left">
                        【最新房源】<span class="status rent">出租</span>欣都龙城15楼两室一厅80平米1500/月精装修
                      </div>

                      <div class="pull-right">
                        <span class="span-time">2018-4-12</span>
                      </div>
                    </li>

                    <li>
                      <div class="pull-left">
                        【最新房源】<span class="status sell">出租</span>欣都龙城15楼两室一厅80平米1500/月精装修
                      </div>

                      <div class="pull-right">
                        <span class="span-time">2018-4-12</span>
                      </div>
                    </li>
                  </ul>
                </el-tab-pane>
                <el-tab-pane class="tab-pane" label="跟进提醒" name="company4">
                  <ul class="list-wrap">
                    <li>
                      <div class="pull-left">
                        【最新房源】<span class="status rent">出租</span>欣都龙城15楼两室一厅80平米1500/月精装修
                      </div>

                      <div class="pull-right">
                        <span class="span-time">2018-4-12</span>
                      </div>
                    </li>

                    <li>
                      <div class="pull-left">
                        【最新房源】<span class="status sell">出售</span>欣都龙城15楼两室一厅80平米1500/月精装修
                      </div>

                      <div class="pull-right date">
                        <span class="span-time">2018-4-12</span>
                      </div>
                    </li>

                    <li>
                      <div class="pull-left">
                        【最新房源】<span class="status rent">出租</span>欣都龙城15楼两室一厅80平米1500/月精装修
                      </div>

                      <div class="pull-right">
                        <span class="span-time">2018-4-12</span>
                      </div>
                    </li>

                    <li>
                      <div class="pull-left">
                        【最新房源】<span class="status rent">出租</span>欣都龙城15楼两室一厅80平米1500/月精装修
                      </div>

                      <div class="pull-right">
                        <span class="span-time">2018-4-12</span>
                      </div>
                    </li>

                    <li>
                      <div class="pull-left">
                        【最新房源】<span class="status sell">出租</span>欣都龙城15楼两室一厅80平米1500/月精装修
                      </div>

                      <div class="pull-right">
                        <span class="span-time">2018-4-12</span>
                      </div>
                    </li>
                  </ul>
                </el-tab-pane>
                <el-tab-pane class="tab-pane" label="跟进任务" name="company5">
                  <ul class="list-wrap">
                    <li>
                      <div class="pull-left">
                        【最新房源】<span class="status rent">出租</span>欣都龙城15楼两室一厅80平米1500/月精装修
                      </div>

                      <div class="pull-right">
                        <span class="span-time">2018-4-12</span>
                      </div>
                    </li>

                    <li>
                      <div class="pull-left">
                        【最新房源】<span class="status sell">出售</span>欣都龙城15楼两室一厅80平米1500/月精装修
                      </div>

                      <div class="pull-right date">
                        <span class="span-time">2018-4-12</span>
                      </div>
                    </li>

                    <li>
                      <div class="pull-left">
                        【最新房源】<span class="status rent">出租</span>欣都龙城15楼两室一厅80平米1500/月精装修
                      </div>

                      <div class="pull-right">
                        <span class="span-time">2018-4-12</span>
                      </div>
                    </li>

                    <li>
                      <div class="pull-left">
                        【最新房源】<span class="status rent">出租</span>欣都龙城15楼两室一厅80平米1500/月精装修
                      </div>

                      <div class="pull-right">
                        <span class="span-time">2018-4-12</span>
                      </div>
                    </li>

                    <li>
                      <div class="pull-left">
                        【最新房源】<span class="status sell">出租</span>欣都龙城15楼两室一厅80平米1500/月精装修
                      </div>

                      <div class="pull-right">
                        <span class="span-time">2018-4-12</span>
                      </div>
                    </li>
                  </ul>
                </el-tab-pane>
              </el-tabs>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
  </section>
</template>

<script type="text/ecmascript-6">
import BarChart from './components/BarChart'
import PieChart from './components/PieChart'
import VueSeamlessScroll from 'vue-seamless-scroll'

export default {
  name: 'dashboard',
  components: {BarChart, PieChart, VueSeamlessScroll},
  data () {
    return {
      radio2: 3,
      activeTabs1: 'year',
      activeTabs2: 'company1',
      barChartData: null,
      pieChartData: null,
      listData: [
        {
          text: '【最新房源】欣都龙城15楼两室一厅80平米1500/月精装修',
          time: '2017-12-16',
          type: 1
        },
        {
          text: '【最新房源】欣都龙城15楼两室一厅80平米1500/月精装修',
          time: '2017-12-16',
          type: 1
        },
        {
          text: '【最新房源】欣都龙城15楼两室一厅80平米1500/月精装修',
          time: '2017-12-16',
          type: 2
        },
        {
          text: '【最新房源】欣都龙城15楼两室一厅80平米1500/月精装修',
          time: '2017-12-16',
          type: 1
        },
        {
          text: '【最新房源】欣都龙城15楼两室一厅80平米1500/月精装修',
          time: '2017-12-16',
          type: 2
        }
      ]
    }
  },
  methods: {
    handleTabClick1 (tab) {
      if (tab.name === 'year') {
        this.barChartData = {
          categories: ['小李', '小陈', '小孙', '老王', '老杨', '张三', '李四', '王五', '赵六', '小明'],
          data: [20, 12, 14, 54, 33, 5, 20, 36, 10, 10]
        }
      } else {
        this.barChartData = {
          categories: ['张三', '李四', '王五', '赵六', '小明', '小李', '小陈', '小孙', '老王', '老杨'],
          data: [5, 20, 36, 10, 10, 20, 12, 34, 54, 33]
        }
      }
    }
  },
  mounted () {
    this.barChartData = {
      categories: ['张三', '李四', '王五', '赵六', '小明', '小李', '小陈', '小孙', '老王', '老杨'],
      data: [5, 20, 36, 10, 10, 20, 12, 46, 54, 33]
    }

    // this.pieChartData = {
    //   categories: ['Industries', 'Technology', 'Forex', 'Gold', 'Forecasts'],
    //   data: [
    //     { value: 320, name: 'Industries' },
    //     { value: 240, name: 'Technology' },
    //     { value: 149, name: 'Forex' },
    //     { value: 100, name: 'Gold' },
    //     { value: 59, name: 'Forecasts' }
    //   ]
    // }

    this.pieChartData = {
      categories: ['新房源录入量', '客户接待量', '客户带看量', '成交量'],
      data: [
        { value: 240, name: 'Technology' },
        { value: 149, name: 'Forex' },
        { value: 100, name: 'Gold' },
        { value: 59, name: 'Forecasts' }
      ]
    }
  },
  computed: {
    classOption: function () {
      return {
        singleHeight: 60,
        waitTime: 2500
      }
    }
  }
}
</script>

<style scoped lang="scss">
  @import "../../common/sass/variable";
  .banner {
    height: 580px;
    background: url("../../common/images/banner.jpg") center top no-repeat;
  }

  .data-panel {
    margin-top: -120px;
    .hd {
      padding: 30px 0;
      height: 120px;
      background-color: rgba(255, 255, 255, 0.2);

      .todo-wrap {
        margin-top: 10px;
        .todo-item {
          float: left;
          margin-left: 30px;
          min-width: 120px;
          height: 45px;
          padding: 10px;
          border-radius: 5px;
          background-color: $red;
          color: #fff;
          transition: all 0.38s ease-out;
          cursor: pointer;

          &:hover {
            background-color: #fff;
            color: $red;
          }

          span {
            display: inline-block;
          }

          .text {
            margin: 0 5px;
            font-size: 12px;
          }

          .num {
            font-size: 16px;
          }

          .icon {
            vertical-align: middle;
          }
        }
      }

      .search-wrap {
        padding: 0 10px;
        display: table;
        margin: 0 auto;

        .search-form {
          margin-top: 10px;
        }
      }
    }

    .center {
      padding: 20px 0;
      .chart-wrapper {

        .chart-item {
          border-radius: 5px;
          .content {
            padding: 15px 15px 0;
            height: 420px;
            border-radius: 5px;
            background: #fff;
          }
        }

        .ranking {
          .tab-pane {
            width: 100%;
          }
        }

        .progress {
          .title {
            font-size: 16px;
            padding: 15px;
            text-align: center;
          }
        }

        .performance {
          .content {
            padding-right: 20px !important;
            padding-left: 20px !important;
          }

          .performance-item {
            margin-bottom: 30px;
            height: 108px;
            cursor: pointer;
            font-size: 12px;
            position: relative;
            overflow: hidden;
            color: #666;
            background: #fff;
            -webkit-box-shadow: 4px 4px 40px rgba(0, 0, 0, 0.05);
            box-shadow: 4px 4px 40px rgba(0, 0, 0, 0.05);
            border-color: rgba(0, 0, 0, 0.05);

            &:hover {
              .icon-wrap {
                color: #fff;
              }

              .icon-received {
                background-color: #34bfa3;
              }

              .icon-uncollected {
                background-color: #f4516c;
              }

              .icon-receivable {
                background-color: #40c9c6;
              }
            }

            .icon-wrap {
              float: left;
              margin: 14px 0 0 14px;
              padding: 16px;
              -webkit-transition: all 0.38s ease-out;
              transition: all 0.38s ease-out;
              border-radius: 6px;
            }

            .icon-received {
              color: #34bfa3
            }

            .icon-uncollected {
              color: #f4516c;
            }

            .icon-receivable {
              color: #40c9c6;
            }

            .description-wrap {
              float: right;
              font-weight: bold;
              margin: 26px;
              margin-left: 0px;

              .text {
                line-height: 18px;
                color: rgba(0, 0, 0, 0.45);
                font-size: 16px;
                margin-bottom: 12px;
              }

              .num {
                font-size: 20px;
              }
            }
          }
        }
      }
    }

    .bd {
      .news-wrapper {
        padding-bottom: 20px;

        .news-item {
          .content {
            padding: 15px 15px 0;
            height: 400px;
            border-radius: 5px;
            background: #fff;
          }
        }

        .list-wrap {
          li {
            height: 60px;
            line-height: 60px;
            background: #fff;
            border-bottom: 1px dashed #e6e6e6;

            .status {
              margin: 0 10px;
              display: inline-block;

              &.rent {
                color: $color-danger;
              }

              &.sell {
                color: $color-warning;
              }
            }

            .date {
              color: $color-info;
            }
          }
        }

        .title {
          font-size: 16px;
          padding: 20px;
          text-align: center;
        }
      }
    }
  }

  .seamless-warp {
    overflow: hidden;
    height: 300px;
  }
</style>
