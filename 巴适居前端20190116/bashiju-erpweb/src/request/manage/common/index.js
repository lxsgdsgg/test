/*******************************************************************************************************/
/** *************************************** 后台管理--公共配置--接口地址集合 *********************************/
/*******************************************************************************************************/

import {onGet, onPost} from '../../main'

// 查询所有职位信息
export const queryPositionSelect = params => {
  return onPost('manage/commonselect/queryPositionSelect', params)
}

// 查询所有角色信息
export const queryRoleSelect = params => {
  return onPost('manage/commonselect/queryRoleSelect', params)
}

// 查询所有城市信息（显示两级行政区）
export const queryCitySelect = params => {
  return onPost('manage/commonselect/queryCitySelect', params)
}

// 查询所有城市信息（显示三级行政区）
export const queryThirdLevelCitySelect = params => {
  return onPost('manage/commonselect/queryThirdLevelCitySelect', params)
}

// 查询所有片区信息
export const queryRegionSelect = params => {
  return onPost('manage/commonselect/queryRegionSelect', params)
}

// 查询所有小区信息
export const queryCommunitySelect = params => {
  return onPost('manage/commonselect/queryCommunitySelect', params)
}

// 查询所有部门信息
export const queryDeptSelect = params => {
  return onPost('manage/commonselect/queryDeptSelect', params)
}

// 查询所有用户信息
export const queryUserSelect = params => {
  return onPost('manage/commonselect/queryUserSelect', params)
}

// 查询介绍人信息
export const queryReferenceUserSelect = params => {
  // return onPost('manage/commonselect/queryReferenceUserSelect', params)
  // return onPost('manage/usermanage/queryReferenInfoToCreateTree', params)
  return onPost('manage/commonselect/queryOpenCityCompanyDepartUser', params)
}

// 查询所有分成角色信息
export const queryDivideintoRole = params => {
  return onPost('manage/commonselect/queryDivideintoRole', params)
}

// 查询业绩类型配置：1 业务类型 2 计算方式
export const queryCaculateMethod = params => {
  return onPost('manage/commonselect/queryCaculateMethod', params)
}

// 查询介绍人数据
export const getReferenceUserSelectUrl = 'manage/commonselect/queryOpenCityCompanyDepartUser'
// 查询所有职位数据
export const getPositionSelectUrl = 'manage/commonselect/queryPositionSelect'
// 查询角色数据
export const getRoleSelectUrl = 'manage/commonselect/queryRoleSelect'
// 查询小区数据
export const getCommunitySelectUrl = 'manage/commonselect/queryCommunitySelect'
// 查询片区数据
export const getRegionSelectUrl = 'manage/commonselect/queryRegionSelect'

// 查询片城市、区域、区列表
export const queryRegionList = params=>{
  return onGet('manage/community/queryRegionList')
}

// 给图片上传人发送消息
export const sendMsgToReceiver = params=>{
  return onPost('im/sysMessage/sysMsg',params)
}



//added 20181101

// 查询省、市所有数据
export const queryAllCity = params => {
  return onPost('manage/commonselect/queryAllCity', params)
}

// 查询开通城市
export const queryOpenCity = params => {
  return onPost('manage/commonselect/queryOpenCity', params)
}

// 查询开通市、县开通数据
export const queryOpenCityCounty = params => {
  return onPost('manage/commonselect/queryOpenCityCounty', params)
}

// 查询开通省、市
export const queryOpenProvinceCity = params=>{
  return onPost('manage/commonselect/queryOpenProvinceCity',params)
}

// 查询开通城市、区县、片区
export const queryOpenCityCountyRegion = params=>{
  return onPost('manage/commonselect/queryOpenCityCountyRegion',params)
}

// 查询开通城市、区县、片区、小区
export const queryOpenCityCountyRegionCommunity = params=>{
  return onPost('manage/commonselect/queryOpenCityCountyRegionCommunity',params)
}

// 查询开通城市、区县、片区、小区 (包括楼盘)
export const queryOpenCityCountyRegionAllCommunity = params=>{
  return onPost('manage/commonselect/queryOpenCityCountyRegionAllCommunity',params)
}

// 查询开通城市、区县、片区、小区 url
export const queryOpenCityCountyRegionCommunityUrl = 'manage/commonselect/queryOpenCityCountyRegionCommunity'
// 查询开通城市、区县、片区 url
export const queryOpenCityCountyRegionUrl = 'manage/commonselect/queryOpenCityCountyRegion'
// 查查询开通城市 url
export const queryOpenCityUrl = 'manage/commonselect/queryOpenCity'

// 查询开通市、县 url
export const queryOpenCityCountyUrl = 'manage/commonselect/queryOpenCityCounty'

// 查询开通城市、公司、部门
export const queryOpenCityCompanyDepart = params=>{
  return onPost('manage/commonselect/queryOpenCityCompanyDepart',params)
}

// 查询开通城市、公司、部门、用户
export const queryOpenCityCompanyDepartUser = params=>{
  return onPost('manage/commonselect/queryOpenCityCompanyDepartUser',params)
}

// 查询开通城市、公司
export const queryOpenCityCompany = params=>{
  return onPost('manage/commonselect/queryOpenCityCompany',params)
}
//  根据权限查询部门、用户
export const queryDepartmentUserList = params=>{
  return onPost('manage/commonselect/queryDepartmentUserList',params)
}
// 获得线程中的用户信息
export const returnUserInfoUserThread = params=>{
  return onPost('manage/commonselect/returnUserInfoUserThread',params)
}

//20181212
//根据区域编号查询片区
export const queryRegionByAreaCode = params=>{
  return onPost('manage/commonselect/queryRegionByAreaCode',params)
}
// 根据城市编号查询区域
export const queryAreaByCityCode = params=>{
  return onPost('manage/commonselect/queryAreaByCityCode',params)
}

