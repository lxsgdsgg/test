/*******************************************************************************************************/
/** *************************************** 后台管理--片区管理 *****************************************/
/*******************************************************************************************************/


import { onPost } from '../main'

// 获取行政区划数据信息
export const queryAreaPageList = params=>{
  return onPost('/manage/region/getAreaList',params)
}

// 获取片区数据信息--带分页
export const queryRegionPageList = params=>{
  return onPost('/manage/region/getRegionList',params)
}

// 根据片区名称获取片区信息
export const getRegionListByName = params=>{
  return onPost('/manage/region/getRegionListByName',params)
}

// 添加片区
export const addRegion = params=>{
  return onPost('/manage/region/addRegion',params)
}

// 修改片区
export const updateRegion = params=>{
  return onPost('/manage/region/updateName',params)
}

// 删除片区
export const deleteRegion = params=>{
  return onPost('manage/region/deleteRegion',params)
}

// 设置片区坐标
export const updateRegionPoint = params=>{
  return onPost('manage/region/updateRegionPoint',params)
}

// 设置行政区坐标
export const updateAreaPoint = params=>{
  return onPost('manage/region/updateAreaPoint',params)
}
