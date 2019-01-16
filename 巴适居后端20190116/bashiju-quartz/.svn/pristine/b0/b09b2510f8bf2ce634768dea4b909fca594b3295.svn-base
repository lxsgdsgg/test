package com.bashiju.quartz.util;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Scheduler;
import org.quartz.impl.StdScheduler;
import org.springframework.beans.factory.annotation.Autowired;

import com.bashiju.quartz.mapper.TaskMapper;
/**
 * Quartz工具类
 * 
 * @author zb
 *
 */
public class QuartzUtil {

	@Autowired
	static TaskMapper taskMapper;
//	private static Logger logger = Logger.getLogger(QuartzUtil.class);
	
	// 主工作需要保持名称唯一
	public static List<String> jobNames = new ArrayList<String>();

	// 公用Scheduler
	public static Scheduler scheduler = (StdScheduler) SpringContextUtil.getApplicationContext()
			.getBean("schedulerFactoryBean"); // 同一个对象
	// 循环队列取出目标工作对象

	public static String getScheduleJobName(String jobName) {
		if (jobNames.contains(jobName)) { // 如果队列中有id，说明其子任务需要执行
			return jobName;
		}
		return null;
	}
}
