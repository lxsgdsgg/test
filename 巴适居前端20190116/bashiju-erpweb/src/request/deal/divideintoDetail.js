/*********************************分成明细查询*******************/
import {onPost,onGet} from "../main";

export const queryDealDividenInfoData = params =>{
  return onPost('deal/dealDividenInfo/queryDealDividenInfoData',params)
}

//下拉数据源
export const dropDown = params =>{
  return onPost('deal/dealDividenInfo/dropDown',params)
}
