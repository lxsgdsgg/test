<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="6">
        <el-form :model="queryForm" ref="queryForm" :inline="true" label-width="70px">
          <el-form-item prop="lineName" label="输入线路" size="small">
            <base-select style="width: 100px;" :url="getMetroUrl" v-model="metroId" @change="getMetroName"></base-select>
          </el-form-item>

          <el-form-item size="small">
            <el-button type="primary" @click.native.prevent="searchStaionInfo">查询</el-button>
          </el-form-item>

          <el-form-item prop="station">
            <div id="stationInfo"></div>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="18">
        <div id="metro" style="height: 500px;"></div>
      </el-col>
    </el-row>

    <el-button type="primary" :loading="loadingBtn" @click="saveStationInfoDataList" size="small">保存</el-button>
    <el-button @click="handlerCancel" size="small">取消</el-button>
  </div>
</template>

<script>
  let busline
  let referResult

  import * as RequestURL from '@/request/manage/stationManage'
  import BaseSelect from '@/components/BaseSelect'
  import {systemAddLog,systemUpdateLog,systemQueryLog,systemDelLog} from '@/request/log/systemPlatformLog'

  export default {
    name: "AddMetroStation",
    components: { BaseSelect },
    props: {
      cityCode: {
        type: String,
        required: true
      }
    },
    data() {
      return {
        loadingBtn: false,
        metroName: '',
        metroId: '',
        queryForm: {
          lineName: ''
        },
        getMetroUrl: 'manage/metromanage/queryMetroLineInfoForSelect?cityCode='+ this.cityCode
      }
    },
    methods:{
      getMetroName(value,name){
        this.metroName = name
        this.metroId = value
      },
      /*******
       * 展示地图区域  并定位到当前城市
       */
      showMap(){
        // 创建地图实例
        let map = new BMap.Map("metro")
        map.centerAndZoom('昆明',14)
        let geolocation = new BMap.Geolocation()
        geolocation.getCurrentPosition(function(r){
          if(this.getStatus() == BMAP_STATUS_SUCCESS){
            map.panTo(r.point)
          }
        },{ enableHighAccuracy: true})

        //初始化地铁线路对象
        busline = new BMap.BusLineSearch(map,{
          renderOptions:{map:map,panel:"stationInfo"},
          onGetBusListComplete: function(result){
            if(result) {
              var fstLine = result.getBusListItem(0)//获取第一个公交列表显示到map上
              busline.getBusLine(fstLine)
              referResult = result
            }
          }
        })
      },

      /**
       * 注册搜索事件
       */
      searchStaionInfo(){
        if(this.metroName){
          busline.getBusList(this.metroName);
        }else{
          this.$message({
            type: 'waring',
            message: '请先选择线路'
          })
        }
      },

      // 取消操作
      handlerCancel(){
        this.$emit('cancelClickHandler','2')
      },

      /**
       * 保存地铁站点信息
       */
      saveStationInfoDataList(){
        this.$confirm('确定保存此数据吗?','提示',{
          confirmButtonText: '',
          cancelButtonText: '',
          type: 'warning'
        }).then(()=>{
          this.loadingBtn = true
          let params = {jsonData: JSON.stringify(referResult)}
          params.metroName = this.metroName
          params.metroId = this.metroId
          params.areaCode = this.cityCode
          RequestURL.saveStationDataList(params).then(res=>{
            if(res.success){
              this.$message({
                type: 'success',
                message: res.msg
              })
            }else{
              this.$message({
                type: 'error',
                message: res.msg
              })
            }
            this.loadingBtn = false
            this.$emit('cancelClickHandler','1')

            let message = {sourceCode:this.metroName,sourceTypeId:'12',operatTypeId:'1'
              ,logContent: `新增地铁线路:${this.metroName}的所有站点`}
            systemAddLog({message: JSON.stringify(message)})
          })
        }).catch(err=>{
          this.loadingBtn = false
          this.$message({
            type: 'infor',
            message: '已取消'
          })
        })
      }
    },
    mounted() {
      this.showMap()
    }
  }
</script>

<style scoped>

</style>
