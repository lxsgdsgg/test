/************************客源业务审批后台接口*************************/
import {onPost,onGet} from "../main";


// 查询经纪人受理列表数据
export const queryCustBusinessExamineData = params=>{
  return onPost('customer/businessExamine/getBusinessExamineData',params)
}

//审核
export const custBusinessExamin = params=>{
  return onPost('customer/businessExamine/examineApplication',params)
}

//下拉数据源
export const getCustBusinessExamineDropdown = params=>{
  return onPost('customer/businessExamine/getCustBusinessExamineDropdown',params)
}
//发送消息的接口
export const sendSysMsg = params => {
  return onPost('sso/sendSysMsg', params)
}
