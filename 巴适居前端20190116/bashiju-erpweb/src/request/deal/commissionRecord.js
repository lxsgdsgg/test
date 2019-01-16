/*********************************佣金记录查询*******************/
import {onPost,onGet} from "../main";

export const queryCommissionRecordData = params =>{
  return onPost('deal/DealCommissionRecord/queryDealCommissionRecordData',params)
}

//下拉数据源
export const dropDown = params =>{
  return onPost('deal/DealCommissionRecord/dropDown',params)
}
