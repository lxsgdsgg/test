<template>
    <div style="height: 400px">
      <el-form :model="form"  ref="form" :rules="rules" size="medium" class="demo-ruleForm">
          <el-form-item label="住宅出售"  prop="residenceSale">
              <el-input type="text" style="width: 150px" v-model="form.residenceSale"><template slot="append">%</template></el-input>&emsp;的成交比例
          </el-form-item>
          <el-form-item label="商业出售"  prop="bussinessSale">
              <el-input type="text" style="width: 150px" v-model="form.bussinessSale"  ><template slot="append">%</template></el-input>&emsp;成交价的比例或者固定额度&nbsp;
              <el-input type="text" style="width: 150px" v-model="form.businessceSaleUnit" ref="businessceSaleUnit"><template slot="append">元</template></el-input>
          </el-form-item>
          <el-form-item label="住宅出租" prop="residenceRent">
              <el-input type="text" style="width: 150px" v-model="form.residenceRent"><template slot="append">%</template></el-input>&emsp;的成交比例
          </el-form-item>
          <el-form-item label="商业出租" prop="bussinessRent">
              <el-input type="text" style="width: 150px" v-model="form.bussinessRent"><template slot="append">%</template></el-input>
              &emsp;成交价的比例或者固定额度&nbsp;
              <el-input type="text" style="width: 150px" v-model="form.bussinessRentUnit" ref="bussinessRentUnit"><template slot="append">元</template></el-input>
          </el-form-item>
          <el-form-item label="一手成交" prop="houseSaleFirst">
              <el-input type="text" style="width: 150px" v-model="form.houseSaleFirst"><template slot="append">%</template></el-input>&emsp;
              成交价的比例或者固定额度&nbsp;
            <el-input type="text" style="width: 150px" v-model="form.houseSaleFirstUnit" ref="houseSaleFirstUnit"><template slot="append">元</template></el-input>
          </el-form-item>
          <el-form-item >
              <div class="btn-group">
                <el-button type="primary" @click="handleSubmit" >确认</el-button>
                <el-button  @click="resetForm" style="margin:0 auto">取消</el-button>
              </div>
          </el-form-item>
      </el-form>
    </div>
</template>
<style></style>
<script>
  import {saveOrEditPfmTypeDetail,getPfmTypeDetail} from "@/request/manage/performance"

    export default {
      name:'commissionStandara',
      // components:{saveOrEditPfmTypeDetail},
        data() {
          //自定义验证功能规则
          var BussAndBusUnit=(rule, value, callback) =>{
            var businessceSaleUnit = this.form.businessceSaleUnit
            if(value!='' && businessceSaleUnit || businessceSaleUnit!= '' &&value){
              callback(new Error('成交比例和固定额度只能填写一个'));
            }else if(!value && !businessceSaleUnit){
                callback(new Error('该项为必填'));
            }else{
              callback()
            }
          };
          var RentAndRentUnit=(rule, value, callback) => {
            var bussinessRentUnit = this.form.bussinessRentUnit
            if (value != '' && bussinessRentUnit || bussinessRentUnit != '' && value) {
              callback(new Error('成交比例和固定额度只能填写一个'));
            } else if (!value && !bussinessRentUnit) {
              callback(new Error('该项为必填'));
            } else {
              callback()
            }
          };
          var FirstAndFirstUnit=(rule, value, callback) => {
            var houseSaleFirstUnit = this.form.houseSaleFirstUnit
            if (value != '' && houseSaleFirstUnit || houseSaleFirstUnit != '' && value) {
              callback(new Error('成交比例和固定额度只能填写一个'));
            } else if (!value && !houseSaleFirstUnit) {
              callback(new Error('该项为必填'));
            } else {
              callback()
            }
          };
              return {
              form:{
                bussinessRentUnit:'',
                residenceSale:'',
                bussinessSale:'',
                businessceSaleUnit:'',
                residenceRent:'',
                bussinessRent:'',
                houseSaleFirst:'',
                houseSaleFirstUnit:''
              },
              formData:[],//后台数据
              rules: {
                residenceSale:[
                  { required: true, message: '该项为必填', trigger: 'change' }
                ],
                bussinessSale:[
                  { required: true, validator:BussAndBusUnit , trigger: 'change' }
                ],
                businessceSaleUnit:[
                  { required: true, message: '该项为必填', trigger: 'change' }
                ],
                residenceRent:[
                  { required: true, message: '该项为必填', trigger: 'change' }
                ],
                bussinessRent:[
                  { required: true,validator:RentAndRentUnit, trigger: 'change' }
                ],
                houseSaleFirst:[
                  { required: true,  validator:FirstAndFirstUnit,  trigger: 'change' }
                ],
                houseSaleFirstUnit:[
                  { required: true,  message: '该项为必填',trigger: 'change' }
                ]
              }
            }
        },
        methods: {
            resetForm(){
             // console.log(this.form['businessceSaleUnit']='')
             //  this.form['businessceSaleUnit']=''
             //  this.form['bussinessRentUnit']=''
             //  this.form['houseSaleFirstUnit']=''
             //  this.$refs['form'].resetFields()
              this.$emit('handleClick', 2)
            },
          handleSubmit(){
            this.$refs['form'].validate((valid) =>{
                if(valid){
                  let params = {...this.form}
                  // params.rgCode = this.cityId
                  saveOrEditPfmTypeDetail({jsonData:JSON.stringify(params)}).then(res =>{
                    if(res.success){
                      this.$message({
                        type:'success',
                        message:res.msg
                      })
                      this.$emit('handleClick',1)
                    }else{
                       this.$message({
                         type:'warning',
                         message:res,msg
                       })
                    }
                  }).catch(error =>{
                    console.log(error)
                  })
                }
              })
          },
          _getPfmTypeDetail(){
              let params =''
            getPfmTypeDetail(params).then(res =>{
                this.formData = res
                this.setForm()
            })
          },
          setForm(){
            for(let i in this.formData){
               this.form[i] = this.formData[i]
            }
          },
        },
        mounted() {
          this._getPfmTypeDetail()
        }

    }

</script>
