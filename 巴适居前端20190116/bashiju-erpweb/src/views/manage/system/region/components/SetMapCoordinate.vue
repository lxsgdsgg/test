<template>
  <div>
    <div id="region" class="page-content" style="height: 650px;"></div>
    <div align="center">
      <el-button type="primary" @click="handlerSubmit" size="small">保存</el-button>
      <el-button @click="handleCancel" size="small">取消</el-button>
    </div>
  </div>
</template>

<script>
    import {
      updateRegionPoint,
      updateAreaPoint
    } from '@/request/manage/region'
    import {systemQueryLog} from '@/request/log/systemPlatformLog'

    let longitude
    let latitude
    export default {
      name: "setMapCoordinate",
      props:{
        data:{
          type: Object
        },
        coordinateType:{
          type : String
        }
      },
      methods:{
        // 初始化地图===jd:经度 wd：纬度 name：名称
        initMap(){
          let marker
          // 创建地图实例
          let map = new BMap.Map('region')
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
          //开启鼠标滚轮缩放
          map.enableScrollWheelZoom(true)

          if(this.data.longitude && this.data.latitude){
            // 创建点坐标
            let point = new BMap.Point(this.data.longitude, this.data.latitude)
            if(this.coordinateType == "1"){
              // 初始化地图，设置中心点坐标和地图级别
              map.centerAndZoom(point, 12)
            }else if(this.coordinateType == "2"){
              // 初始化地图，设置中心点坐标和地图级别
              map.centerAndZoom(point, 14)
            }else{
              console.log('暂不支持')
            }
            // 创建标记点
            marker = new BMap.Marker(point)
            // 添加点标记层
            map.addOverlay(marker)
            // 跳动的动画
            marker.setAnimation(BMAP_ANIMATION_BOUNCE);
            longitude = this.data.longitude
            latitude = this.data.latitude
          }else{
            if(this.coordinateType == "1"){
              // 初始化地图，设置中心点坐标和地图级别
              map.centerAndZoom(this.data.name, 12)
            }else if(this.coordinateType == "2"){
              // 初始化地图，设置中心点坐标和地图级别
              map.centerAndZoom(this.data.name, 14)
            }else{
              console.log('暂不支持')
            }
          }

          //单击获取点击的经纬度
          map.addEventListener("click",function(e){
            if(marker){
              map.removeOverlay(marker)
            }
            // 地图标记
            marker = new BMap.Marker(e.point)
            // 添加点覆盖物
            map.addOverlay(marker);
            longitude = e.point.lng
            latitude = e.point.lat
          })
        },

        // 提交坐标设置信息
        handlerSubmit(){
          if(this.coordinateType && this.coordinateType == "1"){
            this.updateAreaPoint()
          }else if(this.coordinateType && this.coordinateType == "2"){
            this.updateRegionPoint()
          }else {
            this.$message({
              type: 'error',
              message: '错误操作'
            })
          }
        },

        // 更新行政区坐标信息
        updateAreaPoint() {
          let params = {code: this.data.code,longitude: longitude, latitude: latitude}
          this.$confirm('确定保存编辑的信息吗?', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            updateAreaPoint(params).then(res => {
              if (res.success) {
                this.$message({
                  type: 'success',
                  message: res.msg
                })
                this.$emit('handleClick', 1)
              } else {
                this.$message({
                  type: 'warning',
                  message: res.msg
                })
              }
              console.log(this.data)
              let message = {sourceCode:this.data.name,sourceTypeId:'3',operatTypeId:'5'
                ,logContent: `设置行政区坐标：经度=${longitude },纬度=${latitude}`}
              systemQueryLog({message: JSON.stringify(message)})
            }).catch((err) => {
              console.log(err)
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '取消!'
            })
          })
        },

        // 更新片区坐标信息
        updateRegionPoint() {
          const params = {id: this.data.id,longitude: longitude, latitude: latitude}
          this.$confirm('确定保存编辑的信息吗?', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            updateRegionPoint(params).then(res => {
              if (res.success) {
                this.$message({
                  type: 'success',
                  message: res.msg
                })
                this.$emit('handleClick', 2)
              } else {
                this.$message({
                  type: 'warning',
                  message: res.msg
                })
              }
              let message = {sourceCode:this.data.name,sourceTypeId:'3',operatTypeId:'5'
                ,logContent: `设置片区坐标：经度=${longitude },纬度=${latitude}`}
              systemQueryLog({message: JSON.stringify(message)})
            }).catch((err) => {
              console.log(err)
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '取消!'
            })
          })
        },
        // 取消设置坐标信息
        handleCancel() {
          this.$emit('handleClick', 3)
        }
      },
      mounted () {
        this.initMap()
      }
    }
</script>

<style scoped>
</style>
