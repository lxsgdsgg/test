package com.bashiju.quartz.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class QuartzCronDateUtils {
	/**
	 *  
	 * @Title formatDateByPattern   
	 * @Description 日期转换cron表达式时间格式 
	 * @param s date
	 * @param dateFormat : e.g:yyyy-MM-dd HH:mm:ss 
	 * @param @return      
	 * @return String
	 */
    private static String formatDateByPattern(Date date,String dateFormat){  
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);  
        String formatTimeStr = null;  
        if (date != null) {  
            formatTimeStr = sdf.format(date);  
        }  
        System.out.print(formatTimeStr);
        return formatTimeStr;  
    }  
    /*** 
     * convert Date to cron ,eg.  "14 01 17 22 07 ? 2017" 
     * @param date:时间点 
     * * @param day:date的几天后执行
     * @return 
     */  
    public static String getCron(Date  date,int day){
        String dateFormat="ss mm HH dd MM ? yyyy"; 
        Calendar now =Calendar.getInstance();  
        now.setTime(date);  
        now.set(Calendar.DATE,now.get(Calendar.DATE)+day);  

        return formatDateByPattern(now.getTime(),dateFormat);  
    }  
}