
import * as RequestLogURL from '@/request/log/deallog'

export default {

  methods: {
    /**
     * 成交模块【新增】写日志
     * @param sourceId 资源Id---成交编号
     * @param sourceCode 资源编号---成交编号
     * @param sourceTypeId 资源类型--6：买卖 7：租赁 8：一手
     * @param operatTypeId 操作类型--参照LogHouseOperateTypeEnum枚举类
     * @param logContent 日志内容
     */
    dealAddLog(sourceId, sourceCode, sourceTypeId, operatTypeId, logContent) {
      if (sourceId && sourceCode && sourceTypeId && operatTypeId && logContent) {
        let message = {
          sourceId: sourceId,
          sourceCode: sourceCode,
          businessTypeId: 4,//业务类型，4：代表成交
          sourceTypeId: sourceTypeId,
          operatTypeId: operatTypeId,
          logContent: logContent
        }
        RequestLogURL.dealAddLog({message: JSON.stringify(message)}).then(res => {
          console.log(res)
        })
      } else {
        console.log('传入参数错误')
      }
    },

    /**
     * 成交模块【修改】写日志
     * @param sourceId 资源Id---成交编号
     * @param sourceCode 资源编号---成交编号
     * @param sourceTypeId 资源类型--6：买卖 7：租赁 8：一手
     * @param operatTypeId 操作类型--参照LogHouseOperateTypeEnum枚举类
     * @param labelData 表单属性名称
     * @param originalData 原始数据
     * @param newData 新数据
     */
    dealUpdateLog(sourceId, sourceCode, sourceTypeId, operatTypeId, labelData, originalData, newData) {
      if (sourceId && sourceCode && sourceTypeId && operatTypeId
         && labelData && originalData && newData) {
        console.log("---------------------------")
        let message = {
          sourceId: sourceId,
          sourceCode: sourceCode,
          businessTypeId: 4,//业务类型，4：代表成交
          sourceTypeId: sourceTypeId,
          operatTypeId: operatTypeId,
          labelData: labelData,
          originalData: originalData,
          newData: newData,
        }
        RequestLogURL.dealUpdateLog({message: JSON.stringify(message)}).then(res => {
          console.log(res)
        })
      } else {
        console.log('传入参数错误')
      }
    },

    /**
     * 成交模块【删除】写日志
     * @param sourceId 资源Id---成交编号
     * @param sourceCode 资源编号---成交编号
     * @param sourceTypeId 资源类型--6：买卖 7：租赁 8：一手
     * @param operatTypeId 操作类型--参照LogHouseOperateTypeEnum枚举类
     * @param logContent 日志内容
     */
    dealDelLog(sourceId, sourceCode, sourceTypeId, operatTypeId, logContent) {
      if (sourceId && sourceCode && sourceTypeId && operatTypeId && logContent) {
        let message = {
          sourceId: sourceId,
          sourceCode: sourceCode,
          businessTypeId: 4,//业务类型，4：代表成交
          sourceTypeId: sourceTypeId,
          operatTypeId: operatTypeId,
          logContent: logContent
        }
        RequestLogURL.dealDelLog({message: JSON.stringify(message)}).then(res => {
          console.log(res)
        })
      } else {
        console.log('传入参数错误')
      }
    },
    /**
     * 查询写日志公共方法
     * @param sourceId 资源Id---成交编号
     * @param sourceCode 资源编号---成交编号
     * @param sourceTypeId 资源类型--6：买卖 7：租赁 8：一手
     * @param operatTypeId 操作类型--参照LogHouseOperateTypeEnum枚举类
     * @param logContent 日志内容
     */
    dealQueryLog(sourceId, sourceCode, sourceTypeId, operatTypeId, logContent) {
      if (sourceId && sourceCode && sourceTypeId && operatTypeId && logContent) {
        let message = {
          sourceId: sourceId,
          sourceCode: sourceCode,
          businessTypeId: 4,//业务类型，4：代表成交
          sourceTypeId: sourceTypeId,
          operatTypeId: operatTypeId,
          logContent: logContent
        }
        RequestLogURL.dealQueryLog({message: JSON.stringify(message)}).then(res => {
          console.log(res)
        })
      } else {
        console.log('传入参数错误')
      }
    },

    /**
     * 成交修改分成写日志公用方法
     * @param dealId 成交编号
     * @param dealType 成交类型
     * @param saveData 保存新数据
     * @param originalData 原始数据
     * @param title 标题
     */
    writeBaseActionLog(dealId,dealType,saveData,originalData,title){
      let logContent = title
      for(let i in saveData){
        let newTemp = saveData[i]
        if(newTemp.id && newTemp.isValid === 1){ //正常修改
          originalData.forEach(item=>{
            if(newTemp.id === item.id){
              Object.keys(newTemp).forEach(function(key,i,v){
                if (newTemp[key] !== item[key] && key ==='dividerName') {
                  logContent = logContent + `修改经纪人[${item[key]}]修改为[${newTemp[key]}];`
                }

                if (newTemp[key] !== item[key] && key ==='dividerDeptName') {
                  logContent = logContent + `修改门店[${item[key]}]-->[${newTemp[key]}];`
                }

                if (newTemp[key] !== item[key] && key ==='dividReason') {
                  logContent = logContent + `修改分成缘由[${item[key]}]-->[${newTemp[key]}];`
                }

                if (newTemp[key] !== item[key] && key ==='dividRate') {
                  logContent = logContent + `修改[${newTemp.dividerName}]的分成比例从[${item[key]}]%-->[${newTemp[key]}]%;`
                }
              })
            }
          })
        }
        if(newTemp.id && newTemp.isValid === 0){//删除分成
          logContent = logContent + `删除分成[${newTemp.dividerName}]的比例：${newTemp.dividRate}%;`
        }

        if(!newTemp.id && !newTemp.isValid){//新增分成
          logContent = logContent + `新增分成：经纪人=${newTemp.dividerName},门店=${newTemp.dividerDeptName},分成缘由=${newTemp.dividReason},分成比例=${newTemp.dividRate}%;`
        }
      }
      if(dealType === '00'){
        this.dealQueryLog(dealId,dealId,'6','406',logContent)
      }else if(dealType === '01'){
        this.dealQueryLog(dealId,dealId,'7','406',logContent)
      }else if(dealType === '02') {
        this.dealQueryLog(dealId,dealId,'8','406',logContent)
      }
    }
  }
}
