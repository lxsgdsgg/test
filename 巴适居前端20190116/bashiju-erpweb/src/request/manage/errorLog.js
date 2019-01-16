/******************错误日志后台接口****************/

import {onPost,onGet} from '../main';

export  const  queryErrorLog = params =>{
  return onPost('manage/errorLog/getErrorLogData',params)
}
