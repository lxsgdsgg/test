/*******************************************************************************************************/
/** *************************************** OA文章管理 ***************************************/
/*******************************************************************************************************/
import { onGet, onPost } from '../main'

//获取一些基础信息
export const getArticleType = params=>{
  return onPost('oa/getArticleType', params)
}
// 获取文章列表
export const getArticleList = params=>{
  return onPost('oa/getArticleList', params)
}
//上传文章封面
export const uploadImg = params=>{
  return onPost('oa/uploadImg', params)
}
//保存文章
export const saveArticle = params=>{
  return onPost('oa/saveArticle', params)
}
//删除文章
export const delArticle = params=>{
  return onPost('oa/delArticle', params)
}

// 获取审核列表
export const getExamineList = params=>{
  return onPost('oa/getExamineList', params)
}
// 确认审核
export const sureArticle = params=>{
  return onPost('oa/sureArticle', params)
}

// 驳回
export const rejectArticle = params=>{
  return onPost('oa/rejectArticle', params)
}

// 获取快讯，公告详情
export const queryArticleDetail = params=>{
  return onPost('oa/queryArticleDetail', params)
}

// 公告浏览
export const noticeBrowse = params=>{
  return onPost('oa/noticeBrowse', params)
}

export const newsBrowse = params=>{
  return onPost('oa/newsBrowse', params)
}
//发送消息的接口
export const sendSysMsg = params => {
  return onPost('sso/sendSysMsg', params)
}






