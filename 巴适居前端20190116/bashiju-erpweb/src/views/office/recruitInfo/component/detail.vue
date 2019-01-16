<template>
    <div style="height: 500px">
            <el-form>
                <el-form-item>
                      <label style="font-size: 25px">{{this.data.name}}</label>
                </el-form-item>
              <el-form-item>
                  <label>{{  this.$utils.timeFormat(this.data.publishTime)+'发布' }}</label>
              </el-form-item>
              <hr>
              <br><br><br>
              <el-row>
                  <el-col :span="6">
                       <div>
                           招聘人数&nbsp;&nbsp;&nbsp;&nbsp;<label>{{this.data.count+'人'}}</label>
                       </div>
                      <br>
                      <div>
                        工作年限 &nbsp;&nbsp;&nbsp;&nbsp;<label>{{this.data.minWorking+'-'+this.data.maxWorking}}</label>
                      </div>
                      <br>
                     <div>
                      开始时间&nbsp;&nbsp;&nbsp;&nbsp;<label>{{this.$utils.timeFormat(this.data.publishTime)}}</label>
                    </div>
                  </el-col>

                <el-col :span="6">
                  <div>
                    学历要求&nbsp;&nbsp;&nbsp;&nbsp;<label>{{this.data.education |eductionFilter}}</label>
                  </div>
                  <br>
                  <div>
                    工作城市 &nbsp;&nbsp;&nbsp;&nbsp;<label>{{this.data.workCityName}}</label>
                  </div>
                  <br>
                  <div>
                    结束时间&nbsp;&nbsp;&nbsp;&nbsp;<label>{{this.$utils.timeFormat(this.data.endTime)}}</label>
                  </div>
                </el-col>

                <el-col :span="6">
                  <div>
                    所需专业&nbsp;&nbsp;&nbsp;&nbsp;<label>{{this.data.majorSubject}}</label>
                  </div>
                  <br>
                  <div>
                    薪资（元） &nbsp;&nbsp;&nbsp;&nbsp;<label>{{this.data.minWages/100+'-'+this.data.maxWages/100}}</label>
                  </div>
                  <br>
                  <div>
                    招聘专线&nbsp;&nbsp;&nbsp;&nbsp;<label>{{this.data.mobile}}</label>
                  </div>
                </el-col>
              </el-row>
              <br><br><br>
              <el-row>
                  <div>
                    <label style="font-size: 25px">任务要求</label>
                  </div>
                  <hr>
                  <div>
                    <label>{{this.data.requirement}}</label>
                  </div>
              </el-row>
              <br><br><br>
              <el-row>
                <div>
                  <label style="font-size: 18px">主要职责</label>
                </div>
                <hr>
                <div>
                  <label>{{this.data.duty}}</label>
                </div>
              </el-row>
            </el-form>
    </div>
</template>
<script>
     export  default {
        name:'detail',
        props:{
            data:{
              type:Object
            }
        },
        data(){
          return {
              form:{
                  name:'',//岗位名称
                  publishTime:'',//发布时间
                  count:'',//招聘人数
                  minWorking:'', //工作年限
                  maxWorking:'', //工作年限
                  education:'', //学历要求
                  workCityName:'',//工作地点
                  endTime:'', //截止时间
                  majorSubject:'',//所需专业
                  minWages:'',//薪资（元）
                  maxWages:'',//薪资（元）
                  mobile:'', //招聘专线
                  requirement:'', //任职要求
                  duty:'', //主要职责
              }
          }
        },
       methods:{
          _setForm(data){
              for(let i in this.form){
                 this.form[i] = data[i]
              }
          }
       },
       filters:{
         eductionFilter(value ){
           if(value == 0)return '高中及以上'
           if(value == 1)return '大专及以上'
           if(value == 2)return '本科及以上'
           if(value == 3)return '硕士及以上'
           if(value == 4)return '博士及以上'
         },
       },
       mounted(){
          this._setForm(this.data)
         this.$utils.timeFormat(this.data.publishTime)
         this.$utils.timeFormat(this.data.publishTime)
         this.$utils.timeFormat(this.data.publishTime)
        }
     }
</script>
