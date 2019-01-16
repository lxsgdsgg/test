/**************************系统平台日志********************************/

import {onPost} from "../main";
//系统【添加操作】记录日志
export const systemAddLog = params =>{
  return onPost('sso/main/systemAddLog',params);
}
// 系统【修改操作】记录日志
export const systemUpdateLog = params =>{
  return onPost('sso/main/systemUpdateLog',params);
}
// 系统【查询操作】记录日志
export const systemQueryLog = params =>{
  return onPost('sso/main/systemQueryLog',params);
}
// 系统【删除操作】记录日志
export const systemDelLog = params =>{
  return onPost('sso/main/systemDelLog',params);
}



