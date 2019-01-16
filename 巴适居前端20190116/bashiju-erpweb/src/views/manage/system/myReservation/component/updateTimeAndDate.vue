<template>
  <div>
    <el-form :model="form"  label-width="70px" :rules="rules" style="width: 100%"  ref="form" >
      <el-form-item label="日期" prop="reservationDate" size="medium">
        <el-date-picker
          v-model="form.reservationDate"
          type="date"
          format="yyyy-MM-dd"
          value-format="yyyy-MM-dd"
          placeholder="选择日期">
        </el-date-picker>
      </el-form-item>

      <el-form-item label="时间段" prop="reservationTime" size="medium">
        <el-select v-model="form.reservationTime" @change="reservationTimeChange">
          <el-option
            v-for="item in reservationTimeOpt"
            :key="item.id"
            :value="item.id"
            :label="item.fieldValue">
          </el-option>
        </el-select>
      </el-form-item>


      <el-form-item>
        <div class="btn-group">
          <el-button type="primary" :isLoading="loadingBtn" @click="handleSubmit">确认</el-button>
          <el-button  @click="handleCancel">取消</el-button>
        </div>
      </el-form-item>
    </el-form>

  </div>
</template>
<style></style>
<script>
  import {updateReservationsInfo} from "@/request/manage/myReservation";
  import  *  as  RequestLogUrl from '@/request/log/mangePlatformLog'
  export default {
      props:{
        data:Object
      },
        data() {
            return {
              form:{
                reservationDate:'',
                reservationTime:'',
                id:'',
              },
              originalData:[],
              value11:'',
              loadingBtn:false,
              reservationTimeName:'',
              reservationTimeOpt:[
                {id:0,fieldValue:'全天可看'},
                {id:1,fieldValue:'上午(08:00-12:00)'},
                {id:2,fieldValue:'晚上(18:00-21:00)'},
              ],
              rules:{
                reservationDate:[
                  { required: true, message: '该项为必填', trigger: 'change' }
                ],
                reservationTime:[
                  { required: true, message: '该项为必填', trigger: 'change' }
                ]
              }
            }
        },
        methods: {
          handleCancel(){
            this.$emit('handleClick',1)
          },
          setForm(data){
            for(let i in this.form){
              this.form[i] = data[i]
            }
            this.form.reservationDate = this.$utils.timeFormat(data.reservationDate, '{y}-{m}-{d}')
            this.originalData = Object.assign({},this.form)
          },
          timeFormat(row,column,cellValue){
              return this.$utils.timeFormat(cellValue)
          },
          //转换时间格式
          dateFormat(row,column,cellValue){
            // if(row == null)this.form.reservationDate = ''
            let time =  this.$utils.timeFormat(row, '{y}-{m}-{d}')
            this.form.reservationDate = time
          },
          handleSubmit(){
            this.$refs['form'].validate((valid) => {
              if(valid){
                this.$confirm('确定要保存吗？',{
                  confirmBtnText:'确定',
                  cancelBtnText:'取消'
                }).then(() =>{
                  let param = {...this.form}
                  param.status = 1
                  updateReservationsInfo({jsonData:JSON.stringify(param)}).then(res =>{
                    if(res.success){
                      this.$message({type:'success',message:res.msg})
                        let message = {
                          sourceCode:  param.name,//资源编号：客源编号
                          sourceTypeId:14,// 14：我的预约
                          operatTypeId: 2,//操作类型2: 表示修改,
                          labelData: this.$utils.getFormFields(this.$refs['form']),
                          originalData: this.originalData,
                          newData:param
                        }
                        RequestLogUrl.manageUpdateLog({message:JSON.stringify(message)}).then(res=>{
                          console.log(res)
                        }).catch(error =>{
                          console.log(error)
                        })
                      this.$emit('handleClick',1)
                    }else{
                      this.$message({type:'warning',message:res.msg})
                    }
                  }).catch(error =>{
                    console.log(error)
                  })
                })
                this.loadingBtn = true
                this.loadingBtn = false
              }
            })
          },
          reservationTimeChange(val){
                let obj = {};
                obj = this.reservationTimeOpt.find((item)=>{
                  return item.id === val;
                });
           this.reservationTimeName= obj.fieldValue

          }
        },
        filters:{
          reservationDateFilter(value){
            console.log(value)
          }
        },
        mounted() {
          this.setForm(this.data)
          this.dateFormat(this.data.reservationDate)
        }
    }

</script>
