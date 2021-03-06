/*********************管理平台****/
import {onPost,onGet} from "../main";
//管理平台【添加操作】记录日志
export const manageAddLog = params =>{
  return onPost('sso/main/manageAddLog',params);
}
// 管理平台【修改操作】记录日志
export const manageUpdateLog = params =>{
  return onPost('sso/main/manageUpdateLog',params);
}
// 管理平台【查询操作】记录日志
export const manageQueryLog = params =>{
  return onPost('sso/main/manageQueryLog',params);
}
// 管理平台【删除操作】记录日志
export const manageDelLog = params =>{
  return onPost('sso/main/manageDelLog',params);
}


// 后台日志-----查询管理后台日志
export const queryManageActionLogPageList = params=>{
  return onPost('manage/manageLogQuery/queryManageActionLogPageList',params)
}
// 后台日志-----查询系统平台日志
export const auxSystemActionLogPageList = params=>{
  return onPost('manage/manageLogQuery/auxSystemActionLogPageList',params)
}
// 后台日志-----根据后台编号查询后台日志
export const queryManageActionLogByDealId = params=>{
  return onPost('manage/manageLogQuery/queryManageActionLogByManageId',params)
}
// 后台日志-----根据后台类型查询后台日志
export const queryManageActionLogByDealType = params=>{
  return onPost('manage/manageLogQuery/queryManageActionLogByManageType',params)
}
// 后台日志-----根据后台类型查询后台操作枚举对象
export const returnOperatorTypeObj = params=>{
  return onPost('manage/manageLogQuery/returnOperatorTypeObj',params)
}
// 获取资源类型枚举对象
export const returnSourceTypeObj = params=>{
  return onPost('manage/manageLogQuery/returnSourceTypeObj',params)
}
// 获取资源类型枚举对象
export const returnSystemTypeObj = params=>{
  return onPost('manage/manageLogQuery/returnSystemTypeObj',params)
}

// 办公平台日志
export const queryOaActionLogPageList = params=>{
  return onPost('oa/oaLogQuery/queryOaActionLogPageList',params)
}
// 办公平台 获取资源类型枚举对象
export const returnOaOperatorTypeObj = params=>{
  return onPost('oa/oaLogQuery/returnOaOperatorTypeObj',params)
}
