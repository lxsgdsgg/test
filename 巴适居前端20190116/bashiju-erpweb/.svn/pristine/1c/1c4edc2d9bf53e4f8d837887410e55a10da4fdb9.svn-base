<template>
  <div class="page-content">
    <div class="page-content-hd">
      <el-collapse style="overflow: unset">
        <el-collapse-item title="点击展开查询区域>>>>">
          <el-form :model="queryForm" ref="queryForm" :inline="true" size="small">
            <el-form-item prop="communityId">
              <community-selector
                v-model="queryForm.communityId"
                :url="communityUrl"
                :props="cascaderProps"
                :dataProps="cascaderDataProps" placeholder="请选择行政区、片区或小区" @change="getSelectData">
              </community-selector>
            </el-form-item>

            <el-form-item prop="price">
              <base-section-select
                v-model="queryForm.price"
                :data="regionValue.rent"
                :placeholder="priceTitle"
                description="元"
                v-if="queryForm.statusId === 2 || queryForm.statusId === 3"
              >
              </base-section-select>
              <base-section-select
                v-model="queryForm.price"
                :data="regionValue.sell"
                :placeholder="priceTitle"
                description="万元"
              >
              </base-section-select>
            </el-form-item>
            <el-form-item prop="buildSpace">
              <base-section-select
                v-model="queryForm.buildSpace"
                :data="regionValue.space"
                placeholder="面积区间"
                description="平米"
              >
              </base-section-select>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="searchHouseData(false)" size="small">搜索</el-button>
              <el-button @click="resetForm" size="small">清空</el-button>
            </el-form-item>

            <div class="status-wrap">
              <el-form-item label="状态" class="grey-color" style="margin-top: -10px;" prop="statusId">
                <el-radio-group v-model="queryForm.statusId" @change="valueChangedListener">
                  <el-radio label="0">不限</el-radio>
                  <el-radio label="3">我租</el-radio>
                  <el-radio label="4">我售</el-radio>
                  <el-radio label="5">失效</el-radio>
                  <el-radio label="1">有效出售</el-radio>
                  <el-radio label="2">有效出租</el-radio>
                </el-radio-group>
              </el-form-item>
            </div>

            <div class="status-wrap">
              <el-form-item label="用途" class="grey-color" style="margin-top: -20px" prop="houseUsesId">
                <el-radio-group v-model="queryForm.houseUsesId" size="small" @change="searchHouseData(false)">
                  <el-radio label="all">不限</el-radio>
                  <el-radio label="house">住宅</el-radio>
                  <el-radio label="villa">别墅</el-radio>
                  <el-radio label="apartment">公寓</el-radio>
                  <el-radio label="shops">商铺</el-radio>
                  <el-radio label="wareHouse">仓库</el-radio>
                  <el-radio label="factory">厂房</el-radio>
                  <el-radio label="parking">车位</el-radio>
                  <el-radio label="land">土地</el-radio>
                  <el-radio label="officeBuiling">写字楼</el-radio>
                </el-radio-group>
              </el-form-item>
            </div>
          </el-form>
        </el-collapse-item>
      </el-collapse>
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
        <show-house-detail v-if="hackClick" :communityId="communityId" :stationId="''" :query="queryForm" @handlerClick="handlerClick"></show-house-detail>
      </el-dialog>
    </template>
  </div>
</template>

<script>
  import CommunitySelector from '@/components/BaseCascader/'
  import {queryCommunitySelectWithRegion} from '@/request/app'
  import * as RequestURL from '@/request/house/houseSeekByMapOrMetro'
  import circle from './circle.png'
  import ShowHouseDetail from './components/ShowHouseDetail'
  import BaseSectionSelect from '@/components/BaseSectionSelect'
  import {getRegionValue} from '@/request/app'

  let map
  export default {
    name: "houseSeekByMap",
    components: { CommunitySelector,ShowHouseDetail ,circle,BaseSectionSelect},
    data() {
      return {
        communityUrl: queryCommunitySelectWithRegion,
        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        queryForm: {
          price: '',
          buildSpace: '',
          statusId: '',
          houseUsesId: ''
        },
        showDetailDlg: false,
        hackClick: true,
        communityId: '',
        areaArr: [],
        regionArr: [],
        communityArr: [],
        oneData: '',
        regionValue: {
          sell: {},
          rent: {},
          space: {}
        },
        priceTitle: '出售价格区间'
      }
    },

    methods:{
      valueChangedListener(val){
        if(val === '2' || val === '3'){
          this.priceTitle = '租赁价格区间'
        }else{
          this.priceTitle = '出售价格区间'
        }
        this.searchHouseData(false)
      },

      // 获取区间下拉数据
      _getRegionValue () {
        // 获取售价区间
        const sellParams = {
          widgetCode: 'sellPriceRange',
          tag: 2
        }
        getRegionValue(sellParams).then(res => {
          this.regionValue.sell = res.data || []
        })

        // 获取租价区间
        const rentParams = {
          widgetCode: 'rentPriceRange',
          tag: 2
        }
        getRegionValue(rentParams).then(res => {
          this.regionValue.rent = res.data || []
        })

        // 获取租价区间
        const spaceParams = {
          widgetCode: 'spaceRange',
          tag: 2
        }
        getRegionValue(spaceParams).then(res => {
          this.regionValue.space = res.data || []
        })
      },

      // 得到选择的节点数据
      getSelectData(value,name,data){
        this.areaArr = []
        this.communityArr = []
        this.regionArr = []
        if(data.dataType === 'area'){
          // map.setZoom(12)
          this.areaArr[0] = value
        }else if(data.dataType === 'region'){
          // map.setZoom(13)
          this.regionArr[0] = value.replace('r-','')
        }else if(data.dataType === 'community'){
          // map.setZoom(16)
          this.communityArr[0] = value
        }
      },

      resetForm(){
        this.regionArr = []
        this.areaArr = []
        this.communityArr = []
        if(this.$refs['queryForm']){
          this.$refs['queryForm'].resetFields()
        }
      },
      // 点击连接隐藏弹框
      handlerClick(){
        this.showDetailDlg = false
      },

      initMap(){
        this.$nextTick(()=>{
          // 创建地图实例
          map = new BMap.Map('mapArea',{minZoom:10,maxZoom:20})
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
          map.centerAndZoom("昆明", 12)
          //添加地图缩放事件监听
          map.addEventListener('zoomend',()=>{
            this.searchHouseData(false)
          })
        })
      },

      //显示圆形覆盖物
      showCircleOverlay(houseInfo){
        let curZoom = map.getZoom()
        map.clearOverlays();
        for(let i in houseInfo){
          let temp = houseInfo[i]
          let longitude = temp.longitude
          let latitude = temp.latitude
          let point
          if(longitude && latitude && longitude != 'undefined' && latitude != 'undefined'){
            point = new BMap.Point(longitude,latitude) //根据经纬度创建点
          }
          let title = '<span style="color: white;">' + temp.areaName
            + "</span><br><span style=\"color: white\">" + temp.count + "套</span>"
          let myIcon = new BMap.Icon(circle , new BMap.Size(180, 160))
          let marker = new BMap.Marker(point,{icon:myIcon})  // 创建标注

          let opts = {
            position : point// 指定文本标注所在的地理位置
            // offset   : new BMap.Size(5, 0)//设置文本偏移量
          }
          let label = new BMap.Label(title, opts)  // 创建文本标注对象
          label.setStyle({
            color : "#23262e;",				//字体为黑色
            backgroundColor:'transparent',	//文本背景色
            borderColor:'transparent',		//文本框边框色
            fontSize : "12px",
            height : "20px",
            padding: "0px;",
            lineHeight : "20px",
            fontFamily:"微软雅黑",
            border: false
          })

          if(curZoom < 13){
            label.setTitle(temp.areaCode)
          }else{
            label.setTitle(temp.regionId)
          }
          marker.addEventListener('click',()=>{
            let curZoom = map.getZoom()
            let pro = label.getTitle()
            if(curZoom < 16 ){
              this.queryOneDataByPro(pro,curZoom)
            } else {
              this.queryOneDataByPro(pro,curZoom)
            }
          });
          map.addOverlay(marker)
          map.addOverlay(label)
        }
      },

      //显示正方形的覆盖物
      showSquareOverlay(houseInfo){
        map.clearOverlays()
        let longitudeOne
        let latitudeOne
        for(let i in houseInfo){
          let temp = houseInfo[i]
          if(i == 0){
            longitudeOne = temp.longitude
            latitudeOne =temp.latitude
          }
          let longitude = temp.longitude
          let latitude = temp.latitude
          let point
          if(longitude && latitude && longitude != 'undefined' && latitude != 'undefined'){
            point = new BMap.Point(longitude,latitude) //根据经纬度创建点
          }
          let content = temp.communityName + "&nbsp;&nbsp;&nbsp;&nbsp;" + temp.count + "套"
          let opts = {
            position : point,// 指定文本标注所在的地理位置
            offset   : new BMap.Size(-30, -10)//设置文本偏移量
          }
          let label = new BMap.Label(content, opts)  // 创建文本标注对象
          label.setTitle(temp.communityId);
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
              this.communityId = label.getTitle()
              this.showDetailDlg = true
            })
          })
          // let pointOne = new BMap.Point(longitudeOne,latitudeOne)
          // map.panTo(pointOne)
          map.addOverlay(label)
        }
      },

      // 查询单条数据
      queryOneDataByPro(pro,curZoom){
        let params = {}
        params.id = pro
        params.curZoom = curZoom
        RequestURL.queryOneObjectForLocation(params).then(res=>{
          this.oneData = ''
          this.oneData = res
          this.searchHouseData(true)
        }).catch(err=>{
          console.log(err)
        })
      },
      /**
       * 搜索数据
       */
      searchHouseData(isClick) {
        //点击进行搜索
        let curZoom = map.getZoom()
        let params = {...this.queryForm}

        if(params.price){
          params.priceRangeLower = params.price.min
          params.priceRangeUpper = params.price.max
        }
        if(params.buildSpace){
          params.areaRangeLower = params.buildSpace.min
          params.areaRangeUpper = params.buildSpace.max
        }
        delete params.price
        delete params.buildSpace

        params.areaCode = this.areaArr
        params.regionId = this.regionArr
        params.communityId = this.communityArr

        RequestURL.queryHouseInfoByMap({curZoom: curZoom,jsonData: JSON.stringify(params)}).then(res=>{
          if(res.success){
            let resultData = res.data;
            if(resultData.length == 0){
              map.clearOverlays()
              this.$message({
                type: 'info',
                message: '根据条件未搜索到房源！'
              })
            }else {
              if (isClick) {
                if (curZoom <= 12) {
                  let newPoint = new BMap.Point(this.oneData.longitude, this.oneData.latitude)
                  this.showCircleOverlay(resultData)
                  map.setZoom(15)
                  setTimeout(() => {}, 10)
                  map.panTo(newPoint)
                }
                if(curZoom > 12 && curZoom < 16){
                  let newPoint = new BMap.Point(this.oneData.longitude, this.oneData.latitude)
                  this.showSquareOverlay(resultData)
                  setTimeout(() => {}, 10)
                  map.setZoom(16)
                  map.panTo(newPoint)
                }
              } else {
                if (curZoom < 16) {
                  this.showCircleOverlay(resultData)
                } else {
                  this.showSquareOverlay(resultData)
                }
              }
            }
          }else{
            this.$message({
              type: 'error',
              message: res.msg
            })
          }
        }).catch(err=>{
          console.log(err)
        })
      }
    },

    mounted(){
      this.initMap()
      this._getRegionValue()
    }
  }
</script>

<style land="scss">
  .grey-color {
    color: #909399;
  }

  .map-district {
    width: 90px;
    padding: 25px 0;
    height: 40px;
    background-color: rgba(64,147,240,.8);
    color: #fff;
    border-radius: 45px;
    box-shadow: 0 2px 4px 0 rgba(0,0,0,.24);
  }

  .page-content-hd .el-collapse-item__wrap {
    overflow: unset !important;
  }
</style>
