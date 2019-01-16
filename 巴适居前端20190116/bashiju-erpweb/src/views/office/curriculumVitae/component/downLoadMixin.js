
import  *  as  RequestLogUrl from '@/request/log/oaLog'

export default {

  data () {
    return {

    }
  },

  methods: {

    // 附件下载
    downloadImgItem (row) {
      let name = row.name
      // 生成一个a元素
      let a = document.createElement('a')
      // 创建一个单击事件
      let event = new MouseEvent('click')
      // 将a的download属性设置为想要下载的图片名称

      a.download = name
      // 将生成的URL设置为a.href属性
      a.href = row.attachment
      // 触发a的单击事件
      a.dispatchEvent(event)

      let message = {
        sourceCode:  row.name,//资源编号：客源编号
        sourceTypeId:23,// 23：简历管理
        operatTypeId: 614,//操作类型7: 简历下载,
        logContent:+row.name+'下载附件：'//日志内容
      }
      RequestLogUrl.oaQueryLog({message:JSON.stringify(message)}).then(res=>{
         console.log(res)
      }).catch(error =>{
        console.log(error)
      })
    },

  },

  mounted () {
  },

  watch: {
  },

  computed: {
  },
}
