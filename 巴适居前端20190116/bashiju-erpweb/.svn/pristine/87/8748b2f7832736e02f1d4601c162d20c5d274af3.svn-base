/*******************************************************************************************************/
/** *************************************** 后台 用户管理接口地址集合 ***************************************/
/*******************************************************************************************************/

import {onGet, onPost, BASE_URL} from '../main'

// 获取用户管理数据
export const getUserAllDataWithPage = params => {
  return onPost('manage/usermanage/getUserAllDataWithPage', params)
}

// 新增修改用户
export const saveOrUpdateUserInfo = params => {
  return onPost('manage/usermanage/saveOrUpdateUserInfo', params)
}

// 锁定用户
export const lockedUserInfo = params => {
  return onGet('manage/usermanage/lockedUserInfo', params)
}

// 解锁用户
export const unLockedUserInfor = params => {
  return onGet('manage/usermanage/unLockedUserInfor', params)
}

// 重置密码
export const resetUserPassword = params => {
  return onPost('manage/usermanage/resetUserPassword', params)
}

// 根据片区ID查询小区
export const queryCommunityByRegionId = params => {
  return onGet('manage/commonselect/queryCommunityByRegionId', params)
}

// 区域 片区
export const queryAreaAndRegion = params => {
  return onGet('manage/commonselect/queryAreaAndRegion', params)
}

// 头像上传地址
export const avatarUploadUrl = BASE_URL + 'manage/usermanage/uploadHeadImgs'
