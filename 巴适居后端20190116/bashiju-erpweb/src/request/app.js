// 公共接口集合
import {onGet} from './main'

// 获取菜单数据
export const getMenuData = params => {
  return onGet('sso/main/menuData', params)
}

// 获取用户信息
export const getUserInfo = params => {
  return onGet('sso/queryUserInfo', params)
}

// 查询所有城市信息
export const queryCitySelect = params => {
  return onGet('manage/commonselect/queryCitySelect', params)
}

// 查询所有城市信息
export const getCitySelectUrl = 'manage/commonselect/queryCitySelect'
