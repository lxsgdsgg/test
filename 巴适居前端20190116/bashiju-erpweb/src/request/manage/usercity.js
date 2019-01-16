/**
 * Created by YNBSJ-002 on 2018/12/24.
 */

import {onGet, onPost, BASE_URL} from '../main'

//查询用户分配城市表格数据
export const queryUserOpenCityList = params => {
  return onPost('manage/usercity/queryUserOpenCityList', params)
}

//新增用户分配城市
export const addUserOpenCity = params => {
  return onPost('manage/usercity/addUserOpenCity', params)
}

//修改用户分配城市
export const updateUserOpenCity = params => {
  return onPost('manage/usercity/updateUserOpenCity', params)
}

//删除用户分配城市
export const deleteUserOpenCity = params => {
  return onPost('manage/usercity/deleteUserOpenCity', params)
}

//公司开通城市下拉
export const queryCompanyOpenCity = params => {
  return onPost('manage/usercity/queryCompanyOpenCity', params)
}
