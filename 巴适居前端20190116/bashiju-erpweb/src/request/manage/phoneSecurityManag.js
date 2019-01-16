/***************************************后台接口***********************************************/
import {onPost,onGet} from "../main";

export const queryPhoneSecurityData = params =>{
  return onPost('manage/phoneSecurityManag/queryPhoneSecurityData',params)
}

//绑定虚拟电话号码
export const bindViertualPhone = params =>{
  return onPost('manage/phoneSecurityManag/bindViertualPhone',params)
}

//解绑虚拟电话号码
export const unBindViertualPhone = params =>{
  return onPost('manage/phoneSecurityManag/unBindViertualPhone',params)
}
