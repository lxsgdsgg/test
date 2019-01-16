//日报审批
import {onPost} from "../main";
//日报列表
export const queryDailyReportExam = params =>{
  return onPost('manage/report/queryDailyReportExam',params)
}

// 日报批示
export const reportDailyInstructions = params =>{
  return onPost('manage/report/reportDailyInstructions',params)
}
