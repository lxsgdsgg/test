/****************************************客源平台日志****************/



import {onPost,onGet} from "../main";
//客源【添加操作】记录日志
export const customerAddLog = params =>{
  return onPost('sso/main/customerAddLog',params);
}
// 客源【修改操作】记录日志
export const customerUpdateLog = params =>{
  return onPost('sso/main/customerUpdateLog',params);
}
// 客源【查询操作】记录日志
export const customerQueryLog = params =>{
  return onPost('sso/main/customerQueryLog',params);
}
// 客源【删除操作】记录日志
export const customerDelLog = params =>{
  return onPost('sso/main/customerDelLog',params);
}

// 客源日志-----查询客源日志
export const queryCustomerActionLogPageList = params=>{
  return onPost('customer/customerLogQuery/queryCustomerActionLogPageList',params)
}
// 客源日志-----根据客源编号查询客源日志
export const auxCustomerActionLogByDemandId = params=>{
  return onPost('customer/customerLogQuery/auxCustomerActionLogByDemandId',params)
}
// 客源日志-----根据客源类型查询客源日志
export const queryCustomerActionLogByCustomerType = params=>{
  return onPost('customer/customerLogQuery/queryCustomerActionLogByCustomerType',params)
}
// 客源日志-----根据客源类型查询客源操作枚举对象
export const returnOperatorTypeObj = params=>{
  return onPost('customer/customerLogQuery/returnOperatorTypeObj',params)
}
// 客源日志-----根据客源类型查询客源资源类型枚举对象
export const returnSourceTypeObj = params=>{
  return onPost('customer/customerLogQuery/returnSourceTypeObj',params)
}
