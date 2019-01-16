package com.bashiju.quartz.task;

import java.util.HashMap;
import java.util.Map;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.bashiju.utils.util.SpringContextHolder;

@DisallowConcurrentExecution
@Service
public class BashijuTask implements Job{
	private  Logger logger =LoggerFactory.getLogger(this.getClass()); 
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {		
		RabbitTemplate template = (RabbitTemplate) SpringContextHolder.getBean("reRabbitTemplate");
		JobDataMap jdMap=context.getJobDetail().getJobDataMap();
		/*将routingKey传到远端*/
		String routingKey = jdMap.get("routingKey").toString();
		
		/*将targetParam和JobDescription传到远端*/
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("targetParam", jdMap.get("targetParam")==null?"":jdMap.get("targetParam"));
		map.put("JobDescription", jdMap.get("jobDescription").toString());
		logger.warn("定时任务执行的参数map=={}",map.toString());
		/*提交通知*/
        template.convertAndSend(routingKey,map);

		
	}
}
