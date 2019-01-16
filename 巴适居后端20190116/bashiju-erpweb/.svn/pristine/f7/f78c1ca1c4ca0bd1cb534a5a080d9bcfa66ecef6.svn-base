/*******************************************************************************************************/
/** *************************************** 后台管理--分成配置--接口地址集合 *********************************/
/*******************************************************************************************************/

import {onPost} from "../../main";

// 查询分成页面数据信息
export const queryDivideintoPageList = params=>{
  return onPost('manage/divideIntoConfig/queryAllDivideIntoConfigInfo',params)
}

// 新增分成主配置
export const saveOrUpdateDivideintoConfig= params => {
  return onPost('manage/divideIntoConfig/saveOrUpdateDivideInto',params)
}

// 删除分成配置信息
export const deleteDivideintoMainConfig = params => {
  return onPost('manage/divideIntoConfig/deleteDivideIntoConfigInfo',params)
}

// 新增分成详细配置
export const saveOrUpdateDivideintoDetailConfig = params =>{
  return onPost('manage/divideIntoConfig/saveOrUpdateDivConfInfo',params)
}

export const queryDivideDetailConfigObj = params=> {
  return onPost('manage/divideIntoConfig/queryDivideDetailConfigObj',params)
}
