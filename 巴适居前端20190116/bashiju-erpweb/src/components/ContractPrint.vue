<template>
    <div style="background-color: #ffffff;">
      <div id="contractImg">
        <img  :src="url">
      </div>
      <div id="printdiv" v-if="printBtn" style="text-align: center;margin-top: 40px;">
        <el-button type="primary" @click="print">打 印</el-button>
      </div>
    </div>
</template>

<script>
  import {BASE_URL} from '@/request/main.js'
    export default{
        data () {
            return {
                msg: '',
              printBtn: true,
              url:BASE_URL+'deal/showContractPicture?code='
            }
        },
      mounted (){
        if (this.$route.query.code){
          this.url += this.$route.query.code
        }
      },
      methods: {
        print(){
          this.printBtn=false
          this.$nextTick(()=>{
            window.print()
            this.printBtn=true
            window.close()
          })
        }
      }
    }
</script>

<style lang="scss" scoped>

</style>
