

/*****************日报后台接口*********************/

import {onPost} from "../main";
//日报
export const queryDailyReport = params =>{
  return onPost('manage/report/queryDailyReport',params)
}
//提交日报
export const dailyReportSubmit = params =>{
  return onPost('manage/report/dailyReportSubmit',params)
}
//已提交的日报
export const queryDailyReportExam = params =>{
  return onPost('manage/report/queryDailyReportExam',params)
}
