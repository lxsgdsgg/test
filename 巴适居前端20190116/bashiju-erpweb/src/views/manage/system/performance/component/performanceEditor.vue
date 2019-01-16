<template>
    <div>
       <el-form :model="performanceForm"  label-width="180px"   :rules="rules" ref="performanceForm" class="demo-ruleForm" size="medium">
          <el-form-item label="业绩名称" prop="pfmTypeName" style="width: 70%">
              <el-input type="text" v-model="performanceForm.pfmTypeName"></el-input>
          </el-form-item>

         <el-form-item label="计算方式" prop="caculateMethod">
           <el-select v-model="performanceForm.caculateMethod">
             <el-option
               v-for="item in options"
               :key="item.id"
               :value="item.id"
               :label="item.fieldValue">
             </el-option>
           </el-select>
         </el-form-item>

         <el-form-item label="适用业务类型" prop="usePfmTypeValue" ref="usePfmTypeValue" style="width: 70%">
               <base-select @change="getLabel" style="width: 100%" :multiple="true" :props="usePfmTypeProp" :url="getSelectUrl" v-model="performanceForm.usePfmTypeValue"></base-select>
         </el-form-item>

         <el-form-item label="选择城市" prop="cityId" style="width: 70%">
              <base-city-cascader v-model="performanceForm.cityId"  @change="handleChangeCity"></base-city-cascader>
         </el-form-item>

          <el-form-item>
               <el-button type="primary" :isLoading="loadingBtn" @click="handleSubmit">确认</el-button>
               <el-button  @click="handleCancel">取消</el-button>
          </el-form-item>
       </el-form>

    </div>
</template>
<style></style>
<script>
  import BaseCityCascader from '@/components/BaseCascader/city'
  import {saveOrModifyPfmTypeData,queryCaculateMethodData,queryPerfermanceTypeData} from "@/request/manage/performance"
  import BaseSelect from '@/components/BaseSelect'
  import * as selectUrl from '@/request/manage/performance' // 获取下拉框数据 url地址
  export default {
      name:'performanceEditor',
      components:{BaseCityCascader,BaseSelect},
      //接收父组件传值
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
              performanceForm:{
                pfmTypeName:'',
                cityName:'',
                cityId:'',
                caculateMethod: '',
                usePfmTypeValue:'',
                usePfmTypeName:''
              },
              options:[],
              perfermanceTypeOption:[],
              loadingBtn:false,
              getSelectUrl:selectUrl.queryPerfermanceTypeSelectData,
              usePfmTypeProp: {
                label: 'fieldValue',
                value: 'id'
              },
              rgCode1Prop:{
                  label:'cityName',
                  id:'code'
             },
              rules:{
                pfmTypeName:[
                  { required: true, message: '该项为必填', trigger: 'change' }
                ],
                caculateMethod:[
                  { required: true, message: '该项为必填', trigger: 'change' }
                ],
                usePfmTypeValue:[
                  { required: true, message: '该项为必填', trigger: 'blur' }
                ],
                cityId:[
                  { required: true, message: '该项为必填', trigger: 'change' }
                ]
              },
            }
        },
        methods:{
        //设置值
            setForm(data){
              for(let i in this.performanceForm){
                  if(data[i]){
                    this.performanceForm[i] = data[i]
                  }
              }
              //把caculateMethod转成number类型
              this.performanceForm['caculateMethod'] = Number(this.performanceForm['caculateMethod'])
              if(data['usePfmTypeValue'])this.performanceForm['usePfmTypeValue'] =  this.performanceForm['usePfmTypeValue'].split(',').map(Number)
              if(data['usePfmTypeName'])this.performanceForm['usePfmTypeName'] =  this.performanceForm['usePfmTypeName']
            },
            //传递chane事件给子组件，子组件传递数据回到父组件
            getLabel(value,names){
              this.performanceForm.usePfmTypeName = names.join()
            }
              ,
          //获取计算方式的值
          queryCaculateMethodData(){
              queryCaculateMethodData().then(res =>{
                  this.options = res
              }).catch(error =>{
                console.log(error)
              })
          },
          //获取计算
          queryPerfermanceTypeData(){
            queryPerfermanceTypeData().then(res =>{
              this.perfermanceTypeOption = res
            }).catch(error =>{
              console.log(error)
            })
          },
          handleSubmit(){
            this.$refs['performanceForm'].validate((valid) =>{
              if(valid){
                let params = {...this.performanceForm}
                //判断是新增还是修改
               !this.isAdd ? params.id = this.data.id : params.id = ''
                const usePfmTypeValue = params.usePfmTypeValue
                if (typeof usePfmTypeValue === 'object') {
                  params.usePfmTypeValue = usePfmTypeValue.join()
                }
                this.$confirm('确定要保存吗？',{
                  confirmButtonText:'确定',
                  cancelButtonText:'取消',
                }).then(() =>{
                  this.loadingBtn = true
                  console.log(this.performanceForm)
                  saveOrModifyPfmTypeData({jsonData:JSON.stringify(params)}).then(res =>{
                    if(res.success){
                      this.loadingBtn = false
                      this.$message({type:'success',message:res.msg})
                      this.$emit('handleClick',1)
                    }else{
                      this.$message({type:'warning',message:res.msg})
                    }
                  }).catch(error =>{
                    console.log(error)
                    this.loadingBtn = false
                  })
                })
              }
            })
          },
          //  取消编辑
          handleCancel(){
            this.$emit('handleClick',2)
              // this.$confirm('确定要退出编辑吗?',{
              //   confirmButtonText:'确定',
              //   cancelButtonText:'取消',
              //   type:'warning'
              // }).then(()=>{
              //   //回调
              //   //handleClick：事件，2
              //
              // })
          },
          //城市选择改变事件
          handleChangeCity(val,name){
              this.performanceForm.rgName1 = name
              this.performanceForm.rgCode1 = val
          }
        },
        mounted() {
          this.queryCaculateMethodData()
          if(!this.isAdd){
            this.setForm(this.data)
          }

        }

    }

</script>
