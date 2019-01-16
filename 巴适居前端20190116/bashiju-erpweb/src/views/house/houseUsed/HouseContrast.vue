<!-- 房源对比 -->

<template>
  <div class="wrapper">

    <table cellspacing="0" cellpadding="0" border="0" class="table">
      <colgroup>
        <col width="80">
      </colgroup>

      <tbody>
        <tr v-for="item in formThead">
          <th>{{item}}</th>

          <template v-for="(house, index) in houseList">
            <td @mouseenter.prevent="tdEnter(index)" v-if="item === '房源编号'" class="td-code">
              <span>{{house.houseId}}</span>

              <span v-if="index === currentIndex">
                <el-tooltip class="item" effect="dark" content="移除" placement="right">
                  <i @click="handleDelete(index)" class="el-icon-circle-close close"></i>
                </el-tooltip>
              </span>

            </td>
            <td @mouseenter.prevent="tdEnter(index)" v-if="item === '区域'">{{`${house.areaName}-${house.regionName}-${house.communityName}`}}</td>
            <td @mouseenter.prevent="tdEnter(index)" v-if="item === '面积'">{{house | spaceFilter}}</td>
            <td @mouseenter.prevent="tdEnter(index)" v-if="item === '售价'">{{house.sellingPrice && `${house.sellingPrice}万`}}</td>
            <td @mouseenter.prevent="tdEnter(index)" v-if="item === '租价'">{{house.rentPrice && `${house.rentPrice}元`}}</td>
            <td @mouseenter.prevent="tdEnter(index)" v-if="item === '单价'">{{house.unitPrice && `${house.unitPrice}元`}}</td>
            <td @mouseenter.prevent="tdEnter(index)" v-if="item === '户型'">{{house | unitFilter}}</td>
            <td @mouseenter.prevent="tdEnter(index)" v-if="item === '楼层'">{{house | floorFilter}}</td>
            <td @mouseenter.prevent="tdEnter(index)" v-if="item === '朝向'">{{house.orientation}}</td>
          </template>

        </tr>
      </tbody>
    </table>

    <div @click="handleAddHouse" class="icon">
      <el-tooltip class="item" effect="dark" content="新增房源" placement="top-start">
        <icon name="houseContrastAdd" width="40" height="40"></icon>
      </el-tooltip>
    </div>

    <p class="tip-text">*最多只能选择5套房源进行比较</p>

    <el-dialog
      width="900px"
      title="选择房源"
      :visible.sync="innerVisible"
      append-to-body
      :close-on-click-modal="false"
      :close-on-press-escape="false"
    >

      <house-list-comp :viewDetail="false" @handleSelection="selectionHouse" :Selectable="true"></house-list-comp>

    </el-dialog>

  </div>
</template>

<script>
  import HouseListComp from './HouseListComp' // 房源列表

  const formThead = [
    '房源编号',
    '区域',
    '面积',
    '售价',
    '租价',
    '单价',
    '户型',
    '楼层',
    '朝向'
  ]

  export default {
    name: 'houseContrast',

    components: {HouseListComp},

    props: {
      data: {
        type: Array,
        default () {
          return []
        }
      }
    },

    data () {
      return {
        houseList: this.data.concat(),
        innerVisible: false,
        currentIndex: -1,
        maxSize: 5,
        formThead: formThead
      }
    },

    methods: {
      tdEnter (tIndex) {
        this.currentIndex = tIndex
      },

      handleAddHouse () {
        this.innerVisible = true
      },

      // 选择房源
      selectionHouse (row) {

        if (this.houseList.length >= this.maxSize) {
          this.$message('最多只能比较5条房源', {
            message: '确定',
            type: 'warning',
            duration: 2000,
            showClose: true
          })
          this.innerVisible = false
          return false
        }

        let exist = false
        this.houseList.forEach(item => {
          if (item.id === row.id){
            exist = true
            this.$message('房源信息重复', {
              message: '确定',
              type: 'warning',
              duration: 2000,
              showClose: true
            })
          }
        })

        if (exist) return false

        this.houseList.push(row)

        this.innerVisible = false

      },

      // 移除房源
      handleDelete (index) {
        this.houseList.splice(index, 1)
      },

      // 重新初始化对比房源列表
      _initialization () {
        this.houseList = this.data.concat()
      }
    },

    filters: {
      spaceFilter (val) {
        let temp = ''

        if (val.buildSpace) {
          temp = val.buildSpace + '平米'
        }

        return temp || ''
      },

      unitFilter (val) {
        let temp = ''
        if (val.room) {
          temp += `${val.room}室`
        }
        if (val.room) {
          temp += `${val.hall}厅`
        }
        if (val.toilet) {
          temp += `${val.toilet}卫`
        }
        if (val.balcony) {
          temp += `${val.balcony}阳台`
        }
        return temp
      },

      floorFilter (val) {
        let temp = ''
        if (val.floorCount) {
          temp += `${val.floorCount}`
        }
        if (val.totalLayers) {
          temp += `/${val.totalLayers}`
        }
        return temp
      }
    }
  }
</script>

<style scoped lang="scss">
  .wrapper {
    position: relative;
    padding-right: 80px;
    overflow: hidden;

    .icon {
      cursor: pointer;
      position: absolute;
      top: 50%;
      margin-top: -20px;
      right: 10px;
      transition: all .3s;
      color: #6c82a3;

      &:hover {
        color: #324157;
      }
    }

    .tip-text {
      margin: 20px 0 10px 0;
      text-align: right;
      font-size: 12px;
      color: red;
    }
  }
  .table {
    text-align: center;
    margin-bottom: 15px;
    /*border: 1px solid #ebeef5;*/

    tr {
      border-bottom: 1px solid #ebeef5;

      td {
        padding: 12px 10px;
        min-width: 150px;
        transition: background-color .25s ease;
        /*border-bottom: 1px solid #ebeef5;*/
      }

      .td-code {
        padding-right: 20px;
        position: relative;

        .close {
          position: absolute;
          right: 15px;
          top: 11px;
          cursor: pointer;
          font-size: 16px;
          color: #6c82a3;
          vertical-align: baseline;

          &:hover {
            color: #324157;
          }
        }
      }
    }

    th {
      /*border-bottom: 1px solid #ebeef5;*/
      white-space: nowrap;
      overflow: hidden;
      user-select: none;
      text-align: left;
      padding: 12px 10px;
      min-width: 0;
      box-sizing: border-box;
      text-overflow: ellipsis;
      vertical-align: middle;
      position: relative;

      div {
        font-size: 12px;
        margin-bottom: 8px;
      }
    }



    tr:hover {
      background: #f2ffec;
    }
  }
</style>
