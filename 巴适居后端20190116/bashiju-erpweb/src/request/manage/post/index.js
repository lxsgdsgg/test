/*******************************************************************************************************/
/** *************************************** 后台管理--职位管理 *****************************************/
/*******************************************************************************************************/

import {onPost} from "../../main";

// 查询职位管理页面数据
export const queryPostDataPageList = params=>{
  return onPost('manage/post/getPost',params)
}

// 新增或修改职位信息保存函数
export const saveOrUpdatePosition = params=>{
  return onPost('manage/post/saveOrUpdatePost',params)
}

// 根据职位ID删除职位信息
export const deletePositionById = params=>{
  return onPost('manage/post/deletePost',params)
}
