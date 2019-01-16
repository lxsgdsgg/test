<template>
  <div class="page-content">
    <div class="page-content-hd">
      <el-form :model="queryForm" ref="queryForm" :inline="true" size="small">

        <el-form-item prop="metroName" label="线路名称">
          <base-select style="width: 100%" :url="getMetroLineUrl" v-model="queryForm.metroName" @change="getLineName" filterable></base-select>
        </el-form-item>

        <el-form-item prop="ukId" label="选择站点">
          <el-select v-model="queryForm.ukId" filterable clearable >
            <el-option
              v-for="item in stationOpts"
              :key="item.ukId"
              :label="item.stationName"
              :value="item.ukId">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="searchHouseData" size="small">搜索</el-button>
          <el-button @click="resetForm" size="small">清空</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="page-content-hd" id="mapArea" style="height: 690px;margin-top: -30px;"></div>
    <template>
      <el-dialog title="房源明细查看"
                 :visible.sync="showDetailDlg"
                 :close-on-click-modal="false"
                 top="10vh"
                 width="60%"
                 append-to-body
                 :modal-append-to-body="false" @close="handlerClick">
        <show-house-detail v-if="hackClick" :communityId="communityId" :stationId="stationId" :query="queryForm" @handlerClick="handlerClick"></show-house-detail>
      </el-dialog>
    </template>
  </div>
</template>

<script>
  import CommunitySelector from '@/components/BaseCascader/'
  import * as RequestURL from '@/request/house/houseSeekByMapOrMetro'
  import BaseSelect from '@/components/BaseSelect'
  import ShowHouseDetail from '@/views/house/houseSeekByMap/components/ShowHouseDetail'

  let map
  export default {
    name: "houseSeekByMetro",
    components: { CommunitySelector,BaseSelect,ShowHouseDetail },
    data() {
      return {
        getMetroLineUrl: 'manage/metro/queryMetroByCityCode',
        lineOpts: [],
        stationOpts: [],
        queryForm: {
          metroName: '',
          ukId: ''
        },
        showDetailDlg: false,
        hackClick: true,
        communityId: '',
        stationId: ''
      }
    },

    methods:{
      // 点击连接隐藏弹框
      handlerClick(){
        this.showDetailDlg = false
      },
      // 查询地铁站点信息
      queryStationOpts(){
        RequestURL.queryStationOpts().then(res=>{
          this.stationOpts = res
        }).catch(err=>{
          console.log(err)
          this.$message({
            type: 'error',
            message: err.data.msg
          })
        })
      },

      getLineName(value,name){
        this.queryForm.metroName = name
      },

      // 得到选择的节点数据
      getSelectData(value,name,data){
        this.areas = []
        let temp = {}
        temp.value = value
        temp.type = data.level
        this.areas[0] = temp
      },

      resetForm(){
        if(this.$refs['queryForm']){
          this.$refs['queryForm'].resetFields()
        }
      },

      initMap(){
        this.$nextTick(()=>{
          // 创建地图实例
          map = new BMap.Map('mapArea')
          // 左上角，添加比例尺
          let top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT})
          //左上角，添加默认缩放平移控件
          let top_left_navigation = new BMap.NavigationControl()
          //右上角，仅包含平移和缩放按钮
          let top_right_navigation = new BMap.NavigationControl({
            anchor: BMAP_ANCHOR_TOP_RIGHT,
            type: BMAP_NAVIGATION_CONTROL_SMALL
          })
          // 将地图工具放到地图的左上角、右上角
          map.addControl(top_left_control)
          map.addControl(top_left_navigation)
          map.addControl(top_right_navigation)
          // 设置可缩放
          map.enableScrollWheelZoom()
          map.centerAndZoom("昆明", 16)
          //添加地图缩放事件监听
          map.addEventListener('zoomend',()=>{
            this.searchHouseData()
          })
        })
      },

      //显示正方形的覆盖物
      showSquareOverlay(houseInfo){
        map.clearOverlays()
        for(let i in houseInfo){
          let temp = houseInfo[i]
          let longitude = temp.longitude
          let latitude = temp.latitude
          let point
          if(longitude && latitude && longitude != 'undefined' && latitude != 'undefined'){
            point = new BMap.Point(longitude,latitude) //根据经纬度创建点
          }
          let content = temp.stationName + "&nbsp;&nbsp;&nbsp;&nbsp;" + temp.count + "套"
          let opts = {
            position : point,// 指定文本标注所在的地理位置
            offset   : new BMap.Size(-30, -10)//设置文本偏移量
          }
          let label = new BMap.Label(content, opts)  // 创建文本标注对象
          label.setTitle(`${temp.ukId}`);
          console.log(temp.ukId)
          label.setStyle({
            color : "#FCF9F2",//字体颜色为白色--白色
            backgroundColor:'#39AC6A',	//文本背景色--淡绿色
            borderColor:'transparent',		//文本框边框色
            height : "25px",
            lineHeight : "20px",
            fontFamily:"微软雅黑",
            border: false
          })
          label.addEventListener('click',()=>{
            this.hackClick = false
            this.$nextTick(()=>{
              this.hackClick = true
              this.stationId = label.getTitle()
              this.showDetailDlg = true
            })
          })
          map.addOverlay(label)
        }
      },

      /**
       * 搜索数据
       */
      searchHouseData() {
        //点击进行搜索
        let curZoom = map.getZoom()
        let params = {...this.queryForm}

        params.areas = this.areas
        RequestURL.queryHouseInfoByMetro({curZoom: curZoom,jsonData: JSON.stringify(params)}).then(res=>{
          if(res.success){
            let resultData = res.data;
            if(resultData.length == 0){
              this.$message({
                type: 'info',
                message: '根据条件未搜索到房源！'
              })
            }
            this.showSquareOverlay(resultData)
            if(resultData.length > 0){
              let newPoint = new BMap.Point(resultData[0].longitude, resultData[0].latitude)
              map.panTo(newPoint)
            }
          }else{
            this.$message({
              type: 'error',
              message: res.msg
            })
          }
        }).catch(err=>{
          console.log(err)
          this.$message({
            type: 'error',
            message: err.data.msg
          })
        })
      }
    },

    mounted(){
      this.initMap()
      this.queryStationOpts()
    }
  }
</script>

<style scoped land="scss">
  .grey-color {
    color: #909399;
  }
</style>
