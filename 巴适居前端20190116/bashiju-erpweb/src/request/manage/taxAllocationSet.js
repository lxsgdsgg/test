/*******************************************************************************************************/
/** *************************************** 税费配置管理 ***************************************/
/*******************************************************************************************************/
import { onGet, onPost } from '../main'

// 根据城市查询首付房屋类型列表
export const queryHousePropertyListByCity = params=>{
  return onPost('manage/queryHousePropertyListByCity', params)
}

// 根据访问类型id查询首付契税列表
export const queryDeedTaxs = params=>{
  return onPost('manage/queryDeedTaxs', params)
}

// 新增首付房屋类型
export const addHouseProperty = params=>{
  return onPost('manage/addHouseProperty', params)
}

// 修改首付房屋类型
export const updateHouseProperty = params=>{
  return onPost('manage/updateHouseProperty', params)
}

// 删除首付房屋类型
export const deleteHouseProperty = params=>{
  return onPost('manage/deleteHouseProperty', params)
}
