

import {onPost,onGet} from "../main";

// 财务模块【添加】操作日志
export const dealAddLog = (params)=>{
  return onPost('sso/main/finAddLog',params)
}

// 财务模块【修改】操作日志
export const dealUpdateLog = (params)=>{
  return onPost('sso/main/finUpdateLog',params)
}

// 财务模块【删除】操作日志
export const dealDelLog = (params)=>{
  return onPost('sso/main/finDelLog',params)
}

// 财务模块【查询】操作日志
export const dealQueryLog = (params)=>{
  return onPost('sso/main/finQueryLog',params)
}
//取操作类型
export const returnOperatorTypeObj = params=>{
  return onPost('customer/customerLogQuery/returnOperatorTypeObj',params)
}

