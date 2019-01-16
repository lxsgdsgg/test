// 公共接口集合
import {onGet, onPost} from './main'

// 获取菜单数据
export const getMenuData = params => {
  return onGet('sso/main/menuData', params)
}

// 获取用户信息
export const getUserInfo = params => {
  return onGet('sso/queryUserInfo', params)
}

// 注销
export const logout = params => {
  return onPost('', params)
}

// 解锁
export const unlock = params => {
  return onPost('sso/unlock', params)
}

// 获取页面按钮权限信息
export const getBtnPermission = params => {
  return onGet('manage/adminPermission/getBtnPermission', params)
}

// 查询所有城市信息
export const queryCitySelect = params => {
  return onGet('manage/commonselect/queryCitySelect', params)
}

/**
 * 获取自定义动态下拉选项
 * @param menuName 菜单
 * @param params 参数
 */
export const getSelectValue = (params) => {
  return onGet(`manage/dynamicConfigure/getSelectValue/one`, params)
}

/**
 * 获取区间值
 * @param params 参数
 */
export const getRegionValue = params => {
  return onGet('manage/dynamicConfigure/getRegionValue/one', params)
}

/**
 * 获取自定义动态表单字段
 * @param params 参数
 */
export const getCustomField = (params) => {
  return onGet('manage/dynamicConfigure/getCustomField', params)
}

// 查询所有城市信息
export const getCitySelectUrl = 'manage/commonselect/queryOpenCity'

// 查询行政区、片区、小区 级联数据
export const queryCommunitySelectWithRegion = 'manage/commonselect/queryOpenCityCountyRegionCommunity'
