/*******************************************************************************************************/
/** *************************************** 内容管理 ***************************************/
/*******************************************************************************************************/
import { onGet, onPost } from '../main'

//获取公司开通城市信息
export const getCompanyData = params=>{
  return onPost('manage/companyCityOpen/getData', params)
}
// 获取公司选择下拉框数据信息
export const queryCompanySelectData = params => {
  return onPost('manage/company/queryCompanyNameAndId', params)
}
//保存公司开通城市
export const saveOrUpdateCompanyCityOpen = params=>{
  return onPost('manage/companyCityOpen/saveOrUpdateCityOpen', params)
}
//取消公司开通城市
export const cancelCompanyCityOpen = params=>{
  return onPost('manage/companyCityOpen/cancelCityOpen', params)
}
//获取用户角色组
export const getRoleGroup = params=>{
  return onPost('manage/companyCityOpen/getRoleGroup', params)
}

//获取平台开通城市信息
export const getData = params=>{
  return onPost('manage/cityOpen/getData', params)
}
//保存平台开通城市
export const saveCityOpen = params=>{
  return onPost('manage/cityOpen/saveCityOpen', params)
}
//取消平台开通城市
export const cancelCityOpen = params=>{
  return onPost('manage/cityOpen/cancelCityOpen', params)
}
//获取导航
export const getBars = params=>{
  return onPost('manage/cityOpen/getBars', params)
}

//保存导航授权
export const saveNavigationBarPermission = params=>{
  return onPost('manage/cityOpen/saveNavigationBarPermission', params)
}





