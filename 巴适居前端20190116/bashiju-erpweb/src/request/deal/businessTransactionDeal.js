/**********************************************************************************************************************/
/********************************************买卖成交-后台接口管理*********************************************************/
/**********************************************************************************************************************/


import {onPost, onGet, BASE_URL} from "../main";

// 查询买卖成交页面数据集合
export const queryBussinessTransactionDealPageList = params=>{
  return onPost('deal/deal/getData',params)
}

// 查询买卖成交页面数据集合
export const getTableColHiddenList = params=>{
  return onPost('deal/deal/queryDealEnum',params)
}

// 根据成交ID和成交类型查询成交详细数据集合
export const queryDealInfoByIdAndType = params=>{
  return onPost('deal/deal/queryDealInfoByIdAndType',params)
}

// 根据成交id、成交类型查询成交明细所有页面数据
export const queryDetailAllPage = params=>{
  return onPost('deal/deal/queryDetailAllPage',params)
}

// 页面获取成交业务的相关枚举值
export const queryDealEnum = params=>{
  return onPost('deal/deal/queryDealEnum',params)
}

// 修改成交信息
export const updateDealTransaction = params=>{
  return onPost('deal/deal/updateDealTransaction',params)
}

/*******************************************分成信息**********************************/
// 根据成交编号查询金融类分成配置信息
export const queryDivideInfosByDealId = params=>{
  return onPost('deal/deal/queryDivideInfos',params)
}

// 根据成交编号查询金融类分成配置信息
export const queryFinanceDivideInfos = params=>{
  return onPost('deal/deal/queryFinanceDivideInfos',params)
}

// 保存金融类分成配置信息
export const saveFinanceDivideInfos = params=>{
  return onPost('deal/deal/saveFinanceDivideInfos',params)
}

// 保存修改后的分成配置信息
export const saveDivideInfos = params=>{
  return onPost('deal/deal/saveDivideInfos',params)
}

// 确认分成配置信息
export const sureDividInfos = params=>{
  return onPost('deal/deal/sureDividInfos',params)
}

/*******************************************佣金记录**********************************/

// 根据成交编号查询佣金信息
export const queryCommissionInfosByDealId = params=>{
  return onPost('deal/deal/queryCommissionRecordByDealId',params)
}

// 根据成交编号查询佣金记录（调整计划时使用）
export const queryCommissionInfos = params=>{
  return onPost('deal/deal/queryCommissionInfos',params)
}

// 收取佣金
export const reciverDealCommission = params=>{
  return onPost('deal/deal/reciverDealCommission',params)
}

// 取消收取佣金
export const cancelCommission = params=>{
  return onPost('deal/deal/cancelCommission',params)
}

// 更新佣金记录
export const updateDealCommission = params=>{
  return onPost('deal/deal/updateDealCommission',params)
}

// 保存佣金计划信息
export const saveCommissionnfos = params=>{
  return onPost('deal/deal/saveCommissionnfos',params)
}

// 删除佣金记录
export const delCommissions = params=>{
  return onPost('deal/deal/delCommissions',params)
}

// 获取票据编号
export const getBillNoForCommission = params=>{
  return onPost('deal/deal/getBillNoForCommission',params)
}


/*******************************************附件信息**********************************/

// 保存图片信息
export const uploadImgs = params=>{
  return onPost('deal/deal/uploadImgs',params)
}

// 查询附件信息
export const getAttachData = params=>{
  return onPost('deal/deal/getAttachData',params)
}

// 下载附件信息
export const downloadImage = params=>{
  return onPost('deal/deal/downloadImage',params)
}

// 删除附件信息
export const deleteAttach = params=>{
  return onPost('deal/deal/deleteAttach',params)
}

/*******************************************跟进记录**********************************/

// 删除跟进记录信息
export const delFollowRecord = params=>{
  return onPost('deal/deal/delFollowRecord',params)
}

// 保存跟进记录信息
export const savefollowRecord = params=>{
  return onPost('deal/deal/savefollowRecord',params)
}

// 查询跟进记录信息
export const queryFollowRecordByDealId = params=>{
  return onPost('deal/deal/queryFollowRecordByDealId',params)
}

// 查询跟进记录信息（不带分页）
export const queryFollowRecordByDealIdNoPage = params=>{
  return onPost('deal/deal/queryFollowRecordByDealIdNoPage',params)
}

/*******************************************代收付款**********************************/

// 保存代收付款信息
export const savePayRecordd = params=>{
  return onPost('deal/deal/savePayRecordd',params)
}

// 根据成交ID查询代收付款信息
export const queryPayRecordsByDealId = params=>{
  return onPost('deal/deal/queryPayRecordsByDealId',params)
}

// 刪除代收付款信息
export const delPayRecordd = params=>{
  return onPost('deal/deal/delPayRecordd',params)
}

/*******************************************过户进度**********************************/

// 新增过户进度信息
export const addTransferSchedule = params=>{
  return onPost('deal/deal/addTransferSchedule',params)
}

// 批量修改过户进度信息
export const batchUpdateTransferScheduleTime = params=>{
  return onPost('deal/deal/batchUpdateTransferScheduleTime',params)
}

// 取消过户进度信息
export const cancelTransferSchedule = params=>{
  return onPost('deal/deal/cancelTransferSchedule',params)
}

// 删除过户进度信息
export const deleteTransferSchedule = params=>{
  return onPost('deal/deal/deleteTransferSchedule',params)
}

// 更新过户进度信息
export const updateTransferSchedule = params=>{
  return onPost('deal/deal/updateTransferSchedule',params)
}

// 启动过户进度信息
export const saveTransferProcess = params=>{
  return onPost('deal/deal/saveTransferProcess',params)
}

// 获取过户流程详细信息
export const getTransferProcessDetails = params=>{
  return onPost('deal/deal/getTransferProcessDetails',params)
}

// 获取过户进度列表信息
export const queryTransferSchedulePage = params=>{
  return onPost('deal/deal/queryTransferSchedulePage',params)
}

// 获取过户流程信息设置下拉选项
export const queryTransferProcName = params=>{
  return onPost('manage/transferProcess/queryTransferProcName',params)
}

// 获取过户流程所有配置（启动过户使用）
export const queryTransferProcessInfo = params=>{
  return onPost('deal/deal/queryAllTransferProcessInfo',params)
}

/*******************************************违约**********************************/
// 取消违约
export const cancelDealBreach = params=>{
  return onPost('deal/deal/cancelDealBreach',params)
}

// 违约
export const dealBreach = params=>{
  return onPost('deal/deal/dealBreach',params)
}

// 图片下载地址
export const downloadImagesUrl = BASE_URL + 'deal/deal/downloadImage'





// 成交-----过户进度查询
export const queryDealScheduleDataPageList = params=>{
  return onPost('deal/transitScheduleQuery/queryDealScheduleDataPageList',params)
}


// 成交-----根据合同编号读取合同信息
export const queryContractInfoByAgreenmentId = params=>{
  return onPost('deal/queryContractByCode',params)
}

// 成交日志-----查询成交日志
export const queryDealActionLogPageList = params=>{
  return onPost('deal/dealLogQuery/queryDealActionLogPageList',params)
}
// 成交日志-----根据成交编号查询成交日志
export const queryDealActionLogByDealId = params=>{
  return onPost('deal/dealLogQuery/queryDealActionLogByDealId',params)
}
// 成交日志-----根据成交类型查询成交日志
export const queryDealActionLogByDealType = params=>{
  return onPost('deal/dealLogQuery/queryDealActionLogByDealType',params)
}
// 成交日志-----根据成交类型查询成交操作枚举对象
export const returnOperatorTypeObj = params=>{
  return onPost('deal/dealLogQuery/returnOperatorTypeObj',params)
}
