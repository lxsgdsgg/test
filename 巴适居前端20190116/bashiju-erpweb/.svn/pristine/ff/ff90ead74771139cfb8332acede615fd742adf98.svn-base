<template>
    <div style="height: 350px">
      <el-form :model="errorForm" ref="errorForm" label-width="100px" class="demo-form">
        <el-form-item label="类名" prop="className">
          <el-input v-model="errorForm.className"></el-input>
        </el-form-item>
        <el-form-item label="方法名" prop="methodName">
          <el-input v-model="errorForm.methodName"></el-input>
        </el-form-item>
        <el-form-item label="错误信息" prop="errorMessage">
          <el-input
            type="textarea"
            :rows="11"
            v-model="errorForm.errorMessage"
            resize="none"
          >
          </el-input>
        </el-form-item>

      </el-form>
    </div>
</template>

<style></style>

<script>

    export default {
      props:{
          data:{
            type:Object
          }
      },
        data() {
            return {
              errorForm:{
                className:'',
                methodName:'',
                errorMessage:''
              }
            }
        },
      methods:{
        setForm:function(data) {
          for(let i in this.errorForm){
            if(data){
              this.errorForm[i] = data[i]
            }
          }
        }
      }
      ,
      mounted(){
          this.setForm(this.data)
      }
    }

</script>
