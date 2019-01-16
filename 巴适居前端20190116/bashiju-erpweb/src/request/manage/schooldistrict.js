/*******************************************************************************************************
 * **********************************************学区管理请求地址******************************************
 * ****************************************************************************************************/

import {onGet,onPost} from "../main"

// 获取学区数据列表
export const querySchoolDistrict = params=>{
  return onPost('manage/schoolBlockMgr/getSchoolPageObj',params)
}

// 获取小区分页列表
export const getCommunityPageObj = params=>{
  return onPost('manage/schoolBlockMgr/getCommunityPageObj',params)
}

// 根据学校ID获取周边小区信息列表
export const getDataById = params=>{
  return onPost('manage/schoolBlockMgr/getDataById',params)
}

// 保存学校或小区信息
export const saveOrUpdateSchoolInfo = params=>{
  return onPost('manage/schoolBlockMgr/saveOrUpdateSchoolInfo',params)
}

// 删除学校信息
export const delSchoolBlockData = params=>{
  return onPost('manage/schoolBlockMgr/delSchoolBlockData',params)
}

// 读取小区信息（带城市、行政区划和小区数据信息）
export const queryCommunitySelectWithCityRegionData = params=>{
  return onPost('manage/schoolBlockMgr/queryCommunitySelectWithRegion',params)
}
