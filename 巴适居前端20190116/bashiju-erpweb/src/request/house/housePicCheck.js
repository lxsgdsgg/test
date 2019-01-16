/*******************************************************************************************************/
/** *************************************** 房源平台--房源图片审核 *****************************************/
/*******************************************************************************************************/
import {onGet, onPost} from '../main'

// 查询房源图片信息
export const queryHousePictureExamineData = params => {
  return onPost('house/housePictureExamine/getHousePictureExamineData', params)
}

// 查询房源图片审核页面查询控件下拉数据信息
export const queryHousePicCheckSelectOptionDatas = params => {
  return onPost('house/housePictureExamine/queryHousePicCheckSelectOptionDatas', params)
}

// 房源图片批量审核、确认、驳回调用函数
export const examineApplication = params => {
  return onPost('house/housePictureExamine/examineApplication', params)
}

// 房源图片批量审核、确认、驳回发送消息调用函数
export const sendSysMsg = params => {
  return onPost('sso/sendSysMsg', params)
}
