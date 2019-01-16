/*******************************************************************************************************************
 ***************************************************后台管理--用户注册审核管理**********************************************
 * *****************************************************************************************************************/

import { onGet,onPost} from "../main"

/**
 * 查询用户注册信息
 * @param params
 */
export const queryUserRegisterAllInfoPage = params =>{
  return onPost('manage/userRegisterManage/queryUserRegisterAllInfoPage',params)
}

/**
 * 保存用户注册信息
 * @param params
 */
export const saveOrUpdateUserRegisterInfo = params=>{
  return onPost('manage/userRegisterManage/saveOrUpdateUserRegisterInfo',params)
}

/**
 * 删除用户注册审核
 * @param params
 */
export const deleteUserRegisterInfo = params=>{
  return onPost('manage/userRegisterManage/deleteUserRegisterInfo',params)
}
