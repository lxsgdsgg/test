<template>
  <div>
    <el-form :model="form"  label-width="85px"  style="width: 100%"  ref="form"  :rules="rule" :inline="true">
          <el-form-item label="任务名称" prop="name">
            <el-input v-model.trim="form.name" style="width: 220px"></el-input>
          </el-form-item>

        <el-form-item prop="addTime" label="起止时间">
          <el-date-picker
            type="daterange"
            range-separator="—"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            v-model="time"
            value-format="yyyy-MM-dd"
            style="width: 220px"
            clearable
            :disabled="!this.isAdd"
          >
          </el-date-picker>
        </el-form-item>


      <div class="btn-group">
          <el-form-item>
                <el-button type="primary"  @click.native.prevent="handleSubmit">确认</el-button>
                <el-button  @click="handleCancel">取消</el-button>
          </el-form-item>
      </div>

    </el-form>
  </div>
</template>
<style></style>
<script>
  import {addOrUpdTask} from "@/request/office/taskManage";
  import  *  as  RequestLogUrl from '@/request/log/oaLog'
  export default {
      props:{
        data:{
          type:Object
        },
        isAdd:{
          type:Boolean
        }
      },
        data() {
            return {
                form: {
                   id:'',
                   name:'',//任务名称
                   beginTime:'',//开始时间
                   endTime:'',//结束时间
                },
              originalData:'',
              time:[],

              rule:{
                name: [
                  { required: true, message: '任务不能为空', trigger: 'change' }
                ],
                beginTime: [
                  { required: true, message: '开始时间不能为空', trigger: 'change' }
                ],
                endTime: [
                  { required: true, message: '结束时间不能为空', trigger: 'change' }
                ],
              }
            }
        },
        methods: {
          handleCancel(){
            this.$emit('handleClick',1)
          },
          _setForm(data){
              for(let i in this.form){
                 this.form[i] = data[i]
              }
              //时间戳转化为正常格式
              this.form.beginTime = this.$utils.timeFormat(data.beginTime, '{y}-{m}-{d}')
              this.form.endTime = this.$utils.timeFormat(data.endTime, '{y}-{m}-{d}')
              //回显
              let temp = this.form.beginTime+','+this.form.endTime
              this.time =  temp.split(',')
              this.originalData = Object.assign({},this.form)
          },
          /**
           * 提交
           */
          handleSubmit(){



            this.$refs['form'].validate((valid) =>{
                if(valid){
                    let params = {...this.form}
                    let temp = this.time
                    if(temp.length){
                      params.beginTime = temp[0]
                      params.endTime =  temp[1]
                    }
                    addOrUpdTask({jsonData:JSON.stringify(params)}).then(res =>{
                      if(res.success){
                        this.$message({type:'success',message:res.msg})
                        if(this.isAdd){
                          let message = {
                            sourceCode:  params.name,
                            sourceTypeId:17,// 17：任务
                            operatTypeId: 618,//操作类型: 新增任务,
                            businessTypeId: 6,//业务类型 办公，
                            logContent:'新增任务:'+ JSON.stringify(params)
                          }
                          RequestLogUrl.oaAddLog({message:JSON.stringify(message)}).then(res=>{
                            console.log(res)
                          }).catch(error =>{
                            console.log(error)
                          })
                        }else{
                            let message = {
                              sourceCode:  params.name,
                              sourceTypeId:17,// 17：任务
                              operatTypeId: 620,//操作类型: 修改任务,
                              businessTypeId: 6,//业务类型 办公，
                              labelData: this.$utils.getFormFields(this.$refs['form']),
                              originalData: this.originalData,
                              newData: params,
                            }
                            RequestLogUrl.oaUpdateLog({message:JSON.stringify(message)}).then(res=>{
                              console.log(res)
                            }).catch(error =>{
                              console.log(error)
                            })
                        }
                        this.$emit('handleClick',2)
                      }else{
                        this.$message({type:'warning',message:res.msg})
                      }
                    }).catch(error =>{
                      console.log(error)
                    })
                }
            })

          }
        },
        mounted() {
            if(!this.isAdd){
              this._setForm(this.data)
            }
            console.log(this.data)
        }

    }

</script>
