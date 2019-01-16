<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="16">
        <el-row :gutter="10">
          <el-col :span="12">
            <span class="labelRow">所在城市：{{data.areaName}}</span>
          </el-col>
          <el-col :span="12">
            <span class="labelRow">小区名称：{{data.communityName}}</span>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <span class="labelRow">所属片区：{{data.regionName}}</span>
          </el-col>
          <el-col :span="12">
            <span class="labelRow">小区地址：{{data.address}}</span>
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="8">
        <el-dropdown @command="handleCommandAddHouse">
          <el-button type="primary" size="small">
            新增房源 <i class="el-icon-plus"></i>
          </el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item v-for="item in addTypes" :key="item.value" :command="item">{{item.label}}</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <!--<el-button @click="" type="primary" size="small">新增房源</el-button>-->
      </el-col>
    </el-row>
    <hr>
    <el-row :gutter="20">
      <el-row :gutter="20">
        <el-col :span="8"><span class="labelRow">交易类型：{{data.transactionTypeName}}</span></el-col>
        <el-col :span="8"><span class="labelRow">房屋编号：{{data.buildingHouseName}}</span></el-col>
        <el-col :span="8"><span class="labelRow">出租价格：{{data.rentPriceName}}</span></el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="8"><span class="labelRow">出租类型：{{data.leaseTypeName}}</span></el-col>
        <el-col :span="8"><span class="labelRow">房屋户型：{{data.houseType}}</span></el-col>
        <el-col :span="8"><span class="labelRow">支付方式：{{data.payType}}</span></el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="8"><span class="labelRow">出租方式：{{data.joinRentStyleName}}</span></el-col>
        <el-col :span="8"><span class="labelRow">建筑面积：{{data.buildSpace}}</span></el-col>
        <el-col :span="8"><span class="labelRow">客户电话：{{data.mobile}}</span></el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="8"><span class="labelRow">座栋名称：{{data.buildingsName}}</span></el-col>
        <el-col :span="8"><span class="labelRow">楼层描述：{{data.floorDesc}}</span></el-col>
        <el-col :span="8"><span class="labelRow">客户称呼：{{data.clienteleName}}</span></el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="8"><span class="labelRow">所在单元：{{data.buildingsUnitName}}</span></el-col>
        <el-col :span="8"><span class="labelRow">出售价格：{{data.sellingPriceName}}</span></el-col>
        <el-col :span="8"><span class="labelRow">房屋描述：{{data.descs}}</span></el-col>
      </el-row>
    </el-row>

    <template>
      <!-- 房源新增 -->
      <el-dialog
        title="新增房源"
        :visible.sync="dialogVisibleAddHouse"
        width="500px"
        :close-on-click-modal="false"
        :close-on-press-escape="false"
        append-to-body
        :modal-append-to-body="false"
      >
        <add-house v-if="hackReset" @handleClick="handleAddHouseClick" :param="addHouseParams" ref="addHouse"></add-house>
      </el-dialog>
    </template>
  </div>
</template>

<script>
  const addTypes = [
    {label: '住宅', value: 'house'},
    {label: '别墅', value: 'villa'},
    {label: '商铺', value: 'shops'},
    {label: '公寓', value: 'apartment'},
    {label: '写字楼', value: 'officeBuiling'},
    {label: '仓库', value: 'wareHouse'},
    {label: '厂房', value: 'factory'},
    {label: '车位', value: 'parking'},
    {label: '土地', value: 'land'}
  ]

  import AddHouse from '@/views/house/houseUsed/AddHouse'

  export default {
    name: "EntrustDetailComponent",
    components: {AddHouse},
    props:{
      data:{
        type: Object,
        required: true
      }
    },
    data(){
      return {
        addHouseParams: {
          houseUsesId: '',
          houseUses: ''
        },
        dialogVisibleAddHouse: false,
        addTypes: addTypes,
        hackReset: true
      }
    },
    methods:{
      // 新增房源弹框
      handleCommandAddHouse (command) {
        this.hackReset = false
        this.$nextTick(()=>{
          this.hackReset = true
          this.addHouseParams = {
            houseUsesId: command.value,
            houseUses: command.label,
            transactionTypeId: this.data.transactionType // 当前选中的交易类型
          }
          this.$refs['addHouse'] && this.$refs['addHouse']._resetForm()
          this.dialogVisibleAddHouse = true
        })
      },
      // 监听房源新增
      handleAddHouseClick (action) {
        if(action === 1){
          this.dialogVisibleAddHouse = false
          this.$emit('handlerClick','1')
        }
        this.dialogVisibleAddHouse = false
      }
    }
  }
</script>

<style scoped>
  .labelRow{
    color: #666;
    text-align: left;
    float: left;
    display: block;
    padding: 9px 15px;
    font-weight: 500;
    line-height: 20px;
  }
</style>
