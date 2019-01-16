/*******************后台通话记录接口****************/
import {onPost,onGet} from "../main";

//后台通话记录
export const queryBackgroundCallRecord = params =>{
  return onPost('manage/backGroundCallRecord/queryBackGroundCallRecord',params);
}
