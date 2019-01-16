/*******************************************************************************************************/
/** *************************************** 后台管理接口地址集合 ***************************************/
/*******************************************************************************************************/

import {onPost, onGet} from '../main'

/**
 * 菜单管理
 */

// 获取菜单数据
export const getMenuManageListData = params => {
  return onPost('manage/menu/getMemuData', params)
}

// 新增修改菜单
export const saveOrUpdateMenu = params => {
  return onPost('manage/menu/saveOrUpdateMenu', params)
}

// 删除菜单
export const delMenu = params => {
  return onPost('manage/menu/delMenu', params)
}

// 菜单管理 下拉选择项
export const menuPageBaseInfo = params => {
  return onGet('manage/menu/menuPageBaseInfo', params)
}

// 获取菜单数据组合列表数据
export const getCombinationPageBaseInfo = params => {
  return onGet('manage/combination/combinationPageBaseInfo', params)
}

// 获取菜单数据组合 单个条件明细
export const getCombinationDetail = params => {
  return onGet('manage/combination/combinationDetailData', params)
}

// 新增修改 保存菜单数据组合 单个条件明细
export const saveOrUpdateCombination = params => {
  return onPost('manage/combination/saveOrUpdateCombination', params)
}

// 删除菜单 单条数据组合
export const delCombination = params => {
  return onPost('manage/combination/delCombination', params)
}

/**
 * 角色管理
 */

// 获取角色管理列表数据
export const getRoleData = params => {
  return onPost('manage/role/getRoleData', params)
}

// 新增修改角色
export const saveOrUpdateRole = params => {
  return onPost('manage/role/saveOrUpdateRole', params)
}

// 删除角色
export const delRole = params => {
  return onPost('manage/role/delRole', params)
}

/**
 * 角色管理 用户管理  权限信息
 */

// 获取普通用户权限信息
export const getOrdinaryPermission = params => {
  return onGet('manage/permission/ordinaryPermission', params)
}

// 获取超级管理员权限信息
export const getAdminPermission = params => {
  return onGet('manage/adminPermission/adminPermission', params)
}

// 获取城市管理员权限相关信息
export const cityPermission = params => {
  return onGet('manage/cityPermission/cityPermission', params)
}

// 获取城市管理员权限信息
export const getCityPermission = params => {
  return onGet('manage/cityPermission/getPermissions', params)
}

// 保存超级管理员权限设置
export const saveAdminPermissionInfo = params => {
  return onPost('manage/adminPermission/savePermissionInfo', params)
}

// 保存城市管理员权限设置
export const saveCityPermissionInfo = params => {
  return onPost('manage/cityPermission/savePermissionInfo', params)
}

// 保存普通用户权限设置
export const saveOrdinaryPermissionInfo = params => {
  return onPost('manage/permission/savePermissionInfo', params)
}

// 获取城市权限信息
// export const cityPermission = params => {
//   return onGet('manage/cityPermission/cityPermission', params)
// }

// 获取权限菜单信息
// export const getPermissionMenuData = params => {
//   return onGet('getPermissionMenuData', params)
// }
//
// // 获取角色权限设置详细
// export const getPermissionDetail = params => {
//   return onGet('getPermissionDetail', params)
// }
//

//
// // 获取组合条件信息
// export const getPermissionCombinations = params => {
//   return onGet('getPermissionCombinations', params)
// }
//
// // 获取组合条件按钮集合
// export const getPermissionCombinationBnts = params => {
//   return onGet('getPermissionCombinationBnts', params)
// }

// 查询中介黑名单数据信息
export const queryBlackListData = params=>{
  return onPost('manage/BlackList/queryBlackListData',params)
}
// 保存中介黑名单
export const saveBlackListData = params=>{
  return onPost('manage/BlackList/saveOrUpdateBlackList',params)
}
// 删除中介黑名单
export const delBlackList = params=>{
  return onPost('manage/BlackList/delBlackList',params)
}

// 获取部门管理中公司选择下拉框数据信息
export const queryCompanySelectData = params => {
  return onPost('manage/company/queryCompanyNameAndId', params)
}

// 获取电话黑名单列表数据
export const queryPhoneBlacklistData = params => {
  return onPost('manage/phoneBlacklist/queryPhoneBlacklistData', params)
}

// 获取电话黑名单数据信息
export const savePhoneBlackList = params => {
  return onPost('manage/phoneBlacklist/savePhoneBlackList', params)
}

// 删除电话黑名单数据信息
export const deletePhoneBlackList = params => {
  return onPost('manage/phoneBlacklist/deletePhoneBlackList', params)
}
