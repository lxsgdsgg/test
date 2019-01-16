/*********************房源日志***************************/

import {onPost,onGet} from "../main";
//房源平台【添加操作】记录日志
export const houseAddLog = params =>{
  return onPost('sso/main/houseAddLog',params);
}
// 房源平台修改操作】记录日志
export const houseUpdateLog = params =>{
  return onPost('sso/main/houseUpdateLog',params);
}
// 房源平台【查询操作】记录日志
export const houseQueryLog = params =>{
  return onPost('sso/main/houseQueryLog',params);
}
// 房源平台【删除操作】记录日志
export const houseDelLog = params =>{
  return onPost('sso/main/houseDelLog',params);
}


// 房源日志-----查询房源日志
export const queryHouseActionLogPageList = params=>{
  return onPost('house/houseLogQuery/queryHouseActionLogPageList',params)
}
// 房源日志-----根据房源编号查询房源日志
export const queryHouseActionLogByHouseId = params=>{
  return onPost('house/houseLogQuery/queryHouseActionLogByHouseId',params)
}
// 房源日志-----根据房源类型查询房源日志
export const queryHouseActionLogByHouseType = params=>{
  return onPost('house/houseLogQuery/queryHouseActionLogByHouseType',params)
}
// 房源日志-----根据房源类型查询一手房源日志
export const auxOneHandHouseActionLogPageList = params=>{
  return onPost('house/houseLogQuery/auxOneHandHouseActionLogPageList',params)
}
// 查看电话日志
export const auxTelephoneViewLogPageList = params=>{
  return onPost('house/houseLogQuery/auxTelephoneViewLogPageList',params)
}
// 房源日志-----根据房源类型查询房源操作枚举对象
export const returnOperatorTypeObj = params=>{
  return onPost('house/houseLogQuery/returnOperatorTypeObj',params)
}
// 获取资源类型枚举对象
export const returnSourceTypeObj = params=>{
  return onPost('house/houseLogQuery/returnSourceTypeObj',params)
}

