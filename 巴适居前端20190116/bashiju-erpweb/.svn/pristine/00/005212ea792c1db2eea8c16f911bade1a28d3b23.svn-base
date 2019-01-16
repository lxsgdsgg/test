<!-- 看房轨迹-->
<template>
  <div style="min-height: 550px">
  <div>
    <el-form>
        <el-form size="small" ref="queryForm" :inline="true" :model="queryForm" class="demo-form-inline">
          <el-form-item label="" prop="dataId" >
            <base-cascader
              v-model="queryForm.dataId"
              :url="getReferenceUserSelectUrl"
              :props="cascaderProps"
              :dataProps="cascaderDataProps" @change="onChange" style="width:150px">
            </base-cascader>
          </el-form-item>
          <el-form-item label="" prop="startTime">
            <el-date-picker
              v-model="queryForm.startTime"
              type="date"
              placeholder="选择日期" value-format="yyyy-MM-dd" style="width:150px">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="" prop="endTime">
            <el-date-picker
              v-model="queryForm.endTime"
              type="date"
              placeholder="选择日期" value-format="yyyy-MM-dd" style="width:150px">
            </el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click.native.prevent="_getTableData (true)" :loading="loadingQueryBtn">查询</el-button>
            <el-button @click.native.prevent="_resetForm('queryForm')">重置</el-button>
          </el-form-item>
          
        </el-form>
    </el-form>
  </div>
  <div class="page-content-hd" id="mapArea" style="height: 690px;margin-top: 0px;"></div>
    
  </div>
</template>

<script>
  import {getLookHousePath} from '@/request/customer/customerUsed'
  import BaseCascader from '@/components/BaseCascader'
   import circle from './circle.png'
  let map
  export default {
    components: {BaseCascader},
    data () {
      return {
        queryForm: {
          startTime: '',
          endTime: ''
        },
        getReferenceUserSelectUrl: 'manage/usermanage/queryReferenInfoToCreateTree', // 选员工
        cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        cascaderProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        tableData: [],
        listQuery: {
          page: 1,
          limit: 10,
          total: 0
        },
        loadingView: false,
        loadingQueryBtn: false
      }
    },

    methods: {
      handleDelete (row) {
        this.$confirm('确定删除该条数据吗?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          delFollow({id: row.id}).then(res => {
            this.$message({
              type: 'success',
              message: res.msg
            })
            this._getTableData()
          })
        })
      },

      /**
       * 分页 pageSize 改变时会触发
       */
      handleSizeChange (val) {
        this.listQuery.limit = val
        this._getTableData()
      },

      /**
       * 分页 currentPage  改变时会触发
       */
      handleCurrentChange (val) {
        this.listQuery.page = val
        this._getTableData()
      },

      _getTableData (btn) {
        let curZoom = map.getZoom()
        this.loadingView = true
        this.loadingQueryBtn = btn
        this.queryForm.demandId = this.$route.query.demandId;
        let params = {...this.queryForm}
        let cfg = {
              headers: {
                'Content-Type': 'application/json;charset=UTF-8;'
              }
            }
        getLookHousePath(params,cfg).then(res => {
           console.log(res.data)
           let resultData = res.data;
            if(resultData.length == 0){
              this.$message({
                type: 'error',
                message: '根据条件未搜索到房源！'
              })
              this.showSquareOverlay(null)
            }else {
               this.showSquareOverlay(resultData)
            }
            //console.log(res.data)
           // console.log(resultData)
               
          this.loadingView = false
          this.loadingQueryBtn = false
        }).catch(() => {
          this.loadingView = false
          this.loadingQueryBtn = false
        })
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
            this._getTableData(false)
          })
        })

      },
      
      //显示正方形的覆盖物
      showSquareOverlay(houseInfo){
        
        map.clearOverlays()
        for(let i in houseInfo){
          let label
          let temp = houseInfo[i]
          let longitude = temp.longitude
          let latitude = temp.latitude
          let point
          if(longitude && latitude && longitude != 'undefined' && latitude != 'undefined'){
             point = new BMap.Point(longitude,latitude) //根据经纬度创建点
          }
        
          let myIcon = new BMap.Icon(circle , new BMap.Size(180, 160))
          let marker = new BMap.Marker(point,{icon:myIcon})  // 创建标注
          marker.disableMassClear();
          marker.addEventListener('mouseover',()=>{
          let content = temp.leader + "<br/>" + temp.communityName + "<br/>" + this.$utils.timeFormat(temp.addTime)
          console.log(content)
          let opts = {
            position : point,// 指定文本标注所在的地理位置
            offset   : new BMap.Size(-30, -10)//设置文本偏移量
          }
          label = new BMap.Label(content, opts)  // 创建文本标注对象
          label.setTitle(temp.communityId);
          label.setStyle({
              color : "#FCF9F2",//字体颜色为白色--白色
              backgroundColor:'#39AC6A',	//文本背景色--淡绿色
              borderColor:'transparent',		//文本框边框色
              height : "85px",
              lineHeight : "20px",
              fontFamily:"微软雅黑",
              border: false
          })
          map.addOverlay(label) 

          label.addEventListener('click',()=>{
            //回到详细页页签
          })
          label.addEventListener('mouseout',(event)=>{
            console.log(event.target)
            //label.hide()
            map.clearOverlays()
          })
            
            
          })
         

          map.addOverlay(marker)
          //map.addOverlay(label) 
          marker.addEventListener('mouseout',(event)=>{
                console.log(event.target)
                //label.hide()
                map.clearOverlays()
              })
        }
      },
       onChange (val, label, data) {
        // alert(111);
        console.log(val, label, data)
       // alert(data.parent.name)
       if(data.dataType=="dept"){
         this.queryForm.deptId = this.queryForm.dataId;
       } else if(data.dataType=="user"){
         this.queryForm.peopleId = this.queryForm.dataId;
       }

      },

      // 格式化日期
      _timeFormat (row, column, cellValue) {
        return this.$utils.timeFormat(cellValue)
      }
    },
     mounted () {
       let date = new Date().getTime();

       let nowDate = this.$utils.timestampToTime(date);
       this.queryForm.endTime = nowDate;

       let startDate = date - (7 * 24 * 60 * 60 * 1000)
       
       let startTime = this.$utils.timestampToTime(startDate);
       console.log(startTime)
       this.queryForm.startTime = startTime;
       this.initMap()
       
    }
  }
</script>

<style scoped lang="scss">

</style>
