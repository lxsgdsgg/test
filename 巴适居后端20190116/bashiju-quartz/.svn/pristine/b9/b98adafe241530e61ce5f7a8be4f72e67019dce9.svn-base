package com.bashiju.quartz.service.mq;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.api.EmailServiceApi;
import com.bashiju.quartz.mapper.QuartzMapper;
import com.bashiju.utils.util.SpringContextHolder;

@Service
public class QueryCompanyMq {

	@Autowired
	QuartzMapper quartzMapper;
	@Autowired
	EmailServiceApi emailServiceApi;
/*	@Autowired
	HouseLevelChangeMq houseLevelChangeMq;
	@Autowired
	CustomerLevelChangeMq customerLevelChangeMq;*/
	private static final Logger logger=LoggerFactory.getLogger(QueryCompanyMq.class);
	
	public void queryCompany(Map<String,String> map) {
		RabbitTemplate template = (RabbitTemplate) SpringContextHolder.getBean("reRabbitTemplate");				
		try {
			List<String> companyIdList =quartzMapper.queryAllCompanyId();
			if (!companyIdList.isEmpty()) {
				/*获取routingKey数组*/
				String[] routingKeys = map.get("targetParam").split(",");
				for (String routingKey : routingKeys) {
					/*循环提交通知*/
					for (String companyId : companyIdList) {
						/*houseLevelChangeMq.houseAutolevelChange(companyId);
						customerLevelChangeMq.customerAutoLevelChange(companyId);*/
						template.convertAndSend(routingKey,companyId.toString());
					}
				}
			} 
		} catch (Exception e) {
			logger.error("定时任务队列获取公司ID出现故障:{}", e.getMessage());
			
		}
	}
	
}
