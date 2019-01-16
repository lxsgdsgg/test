<template>
    <div id="communityMap" style="height: 500px;"></div>
</template>

<script>
  export default {
    name: "CommunityMap",
    props:{
      data:{
        type: Object
      }
    },
    methods:{
      /**
       * 初始化地图
       */
      initCommunityMap() {
        this.$nextTick(() => {
          // 创建地图实例
          let map = new BMap.Map('communityMap')
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
          console.log(this.data)
          if(this.data.longitude && this.data.latitude){
            // 创建点坐标
            let point = new BMap.Point(this.data.longitude, this.data.latitude)
            // 初始化地图，设置中心点坐标和地图级别
            map.centerAndZoom(point, 18)
            // 设置不可拖拽
            // map.disableDragging()
            // 创建标记点
            let marker = new BMap.Marker(point)
            // 添加点标记层
            map.addOverlay(marker)
            // 跳动的动画
            marker.setAnimation(BMAP_ANIMATION_BOUNCE)
          }else{
            // 初始化地图，设置中心点坐标和地图级别
            if(this.data.regionLongitude && this.data.regionLatitude){
              // 创建点坐标
              let point = new BMap.Point(this.data.regionLongitude, this.data.regionLatitude)
              // 初始化地图，设置中心点坐标和地图级别
              map.centerAndZoom(point, 18)
              // 创建标记点
              let marker = new BMap.Marker(point)
              // 添加点标记层
              map.addOverlay(marker)
              // 跳动的动画
              marker.setAnimation(BMAP_ANIMATION_BOUNCE);
            }else{
              // 创建点坐标
              let point = new BMap.Point(this.data.areaLongitude, this.data.areaLatitude)
              // 初始化地图，设置中心点坐标和地图级别
              map.centerAndZoom(point, 18)
              // 创建标记点
              let marker = new BMap.Marker(point)
              // 添加点标记层
              map.addOverlay(marker)
              // 跳动的动画
              marker.setAnimation(BMAP_ANIMATION_BOUNCE)
            }
          }
        })
      }
    },
    mounted(){
      if(this.data){
        this.initCommunityMap()
      }
    }
  }
</script>

<style scoped>

</style>
