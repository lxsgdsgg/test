/**************************房源业务审批后台接口**********************/

import {onPost,onGet} from "../main";


export const queryHouseBusinessExaminationData = params =>{
  return onPost('house/businessExamine/getBusinessExamineData',params)
}


export const getBusinessExamineDropdown = params =>{
  return onPost('house/businessExamine/businessExamineDropdown')
}

export const examineApplication = params =>{
  return onPost('house/businessExamine/examineApplication',params)
}
//发送消息的接口
export const sendSysMsg = params => {
  return onPost('sso/sendSysMsg', params)
}

