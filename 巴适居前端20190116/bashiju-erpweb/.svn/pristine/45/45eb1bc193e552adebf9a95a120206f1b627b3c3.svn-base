/*******************************************************************************************************/
/** *************************************** 后台管理--地铁线路管理 *****************************************/
/*******************************************************************************************************/

import { onGet, onPost} from "../../main";

// 查询站点信息
export const queryStationDataList = params=>{
  return onPost('manage/metromanage/queryAllMetroStationInfo',params)
}

// 查询周边小区信息
export const queryPeripheryCommunityDataList = params=>{
  return onPost('manage/metromanage/getCommunityPageObj',params)
}

// 查询地铁线路下拉选项信息
export const queryMetroLineInfoForSelect = params=>{
  return onPost('manage/metromanage/queryMetroLineInfoForSelect',params)
}

// 保存地铁站点信息
export const saveStationDataList = params=>{
  return onPost('manage/metromanage/saveMetroStationInfo',params)
}

// 保存周边小区信息
export const savePeripheryCommunityDataList = params=>{
  return onPost('manage/metromanage/saveStationCommunityInfo',params)
}

// 删除周边小区信息
export const deletePeripheryCommunityDataList = params=>{
  return onPost('manage/metromanage/deleteStationCommunityData',params)
}
