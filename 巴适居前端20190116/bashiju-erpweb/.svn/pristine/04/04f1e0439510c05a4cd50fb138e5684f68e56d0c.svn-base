<template>
  <div style="height: 500px;overflow: auto">
      <ul>
        <li v-for="floor in houseUnitData.floorTotal">
          <td><label style="display: inline-block;min-width: 30px;">{{floor}}楼</label></td>
          <td v-for="code in houseUnitData.unitHouseCount" style="vertical-align: middle;">
            <el-input :ref="returnInputKey(floor,code)" :key="returnInputKey(floor,code)" style="width: 125px;"
                      :value="returnInputValue(floor,code)" size="small" @change="updateOriData">
              <el-button slot="append" icon="el-icon-edit" title="点击保存"
                         @click="submitHandler(returnInputKey(floor,code))" style="width: 10px;"></el-button>
            </el-input>
          </td>
        </li>
      </ul>
  </div>
</template>

<script>
  import { queryHouseByUnitId, saveHouseSequence } from '@/request/manage/community'

  export default {
    name: "SetHouseNumber",
    props:{
      houseUnitData:{
        type: Object
      }
    },
    data(){
      return {
        houseNoData: '',
        loadingView: false,
        value: ''
      }
    },
    methods:{
      updateOriData(value){
        this.value = value
      },

      // 返回Key
      returnInputKey(floor,code){
        let key = floor.toString() + code.toString() + '_id'
        return this.houseNoData[key]
      },

      // 获取Value
      returnInputValue(floor,code){
        let key = floor.toString() + code.toString()
        let value = this.houseNoData[key]
        return value
      },
      // 查询房号信息
      queryHouseNumber(){
        let params = { buid : this.houseUnitData.id}
        this.loadingView = true
        queryHouseByUnitId(params).then(res=>{
          let tempData = res.data
          let result = {}
          for(let i in tempData){
            let code = tempData[i].code
            let floor = tempData[i].floor

            let key = floor.toString() + code.toString()
            let floorKey = 'floor_' + floor.toString()

            result[key] = tempData[i].houseNumber
            result[key + '_id'] = tempData[i].id
            result[floorKey] =  floor
            result.code = tempData[i].code
          }
          this.houseNoData = result
          this.loadingView = false
        }).catch(err=>{
          console.log(err)
          this.$message({
            type: 'error',
            message: err.msg
          })
          this.loadingView = false
        })
      },

      // 保存更新后的房号信息
      submitHandler(key){
        let params = {id: key,houseNumber: this.value}

        this.$confirm('确定修改此房号?','提示',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(()=>{
          saveHouseSequence({dataJson:JSON.stringify(params)}).then(res=>{
            if(res.success){
              // 刷新页面数据
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
          }).catch(err=>{
            this.$message({
              type: 'error',
              message: err.data.msg
            })
          })
        })
        //   .catch(err=>{
        //   this.$message({
        //     type: 'info',
        //     message: '已取消操作'
        //   })
        // })
      },

      //按升序排列
      up(x,y){
        return x.floor-y.floor
      }
    },
    mounted(){
      this.queryHouseNumber()
    }
  }
</script>

<style scoped>
  .el-input {
    margin-left: 3px;
    padding-right: 3px;
    padding-bottom: 3px;
    width: 130px;
  }
</style>
