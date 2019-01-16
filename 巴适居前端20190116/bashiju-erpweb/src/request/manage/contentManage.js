/*******************************************************************************************************/
/** *************************************** 内容管理 ***************************************/
/*******************************************************************************************************/
import { onGet, onPost } from '../main'
//获取资讯一些基础信息
export const getInformationBaseInfo = params=>{
  return onPost('manage/getInformationBaseInfo', params)
}
//获取百科一些基础信息
export const getEncyclopediasBaseInfo = params=>{
  return onPost('manage/getEncyclopediasBaseInfo', params)
}
// 获取文章列表
export const getArticleList = params=>{
  return onPost('manage/getArticleList', params)
}
//上传文章封面
export const uploadImg = params=>{
  return onPost('manage/uploadImg', params)
}
//保存文章
export const saveArticle = params=>{
  return onPost('manage/saveArticle', params)
}
//删除文章
export const delArticle = params=>{
  return onPost('manage/delArticle', params)
}

// 获取资讯类型
export const getInformationClasse = params=>{
  return onPost('manage/getInformationClasse', params)
}

// 获取审核列表
export const getExamineList = params=>{
  return onPost('manage/getExamineList', params)
}
// 确认审核
export const sureArticle = params=>{
  return onPost('manage/sureArticle', params)
}

// 驳回
export const rejectArticle = params=>{
  return onPost('manage/rejectArticle', params)
}

//获取底部内容类型
export const getBottomContentType = params=>{
  return onPost('manage/getBottomContentType', params)
}

//获取网站客户举报房源列表
export const getCustTipoff = params=>{
  return onPost('manage/getCustTipoff', params)
}

//获取网站客户反馈列表
export const getCustFeedback = params=>{
  return onPost('manage/getCustFeedback', params)
}
//保存处理结果
export const saveHandleResult = params=>{
  return onPost('manage/saveHandleResult', params)
}
//发送消息的接口
export const sendSysMsg = params => {
  return onPost('sso/sendSysMsg', params)
}
