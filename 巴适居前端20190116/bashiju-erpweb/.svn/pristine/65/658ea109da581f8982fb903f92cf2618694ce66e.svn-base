/*******************************************************************************************************/
/** *************************************** 公共函数 mixin***************************************/
/*******************************************************************************************************/
import {sendMsgToReceiver} from '@/request/manage/common'

export default {
  methods: {
    /**
     * 图片审核完后给图片上传人发送消息
     * @param reSessionId
     * @param msgContent
     */
    sendMsgToReceiver(reSessionId,msgContent) {
      let params = {reSessionId: reSessionId,msgContent:msgContent}
      sendMsgToReceiver(params).then(res=>{
        if(res.success){
          this.$message({
            type: 'success',
            message: res.msg
          })
        }else{
          this.$message({
            type: 'error',
            message: '消息发送失败'
          })
        }
      }).catch(err=>{
        console.log(err)
      })
    }
  }
}
