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
