package com.bashiju.quartz.service.mq;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.quartz.mapper.HousingCrawlerMapper;
/**
 * 
 *   查询数据库爬网url配置进行爬网
 * @ClassName:  HousingCrawlerProducerMq   
 * @Description:  查询数据库爬网url配置进行爬网
 * @author: wangkaifa
 * @date:   2018年7月3日 上午11:48:07       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class HousingCrawlerProducerMq {
	@Autowired
	HousingCrawlerMapper housingCrawlerMapper;	
	@Autowired
	AmqpTemplate amqpTemplate;
	/**
	 * 
		 * 执行爬网任务
		 * @Description: 执行爬网任务 
		 * @param maps 
		 * void
	 */
	public void queryCrawlerPage(Map<String,Object> maps) {
		try {
			System.out.println("***************开始爬网*******************");
			//查询数据库配置的url
			List<Map<String, Object>> list=housingCrawlerMapper.queryHouseCrawlerConfigure();
			System.out.println("******************爬网路径**********************");
			
			String routingKey=maps.get("targetParam").toString();
			if (list!=null&&list.size()>0) {
				for (Map<String, Object> map : list) {
					String urls=map.get("houseListUrl").toString();//爬网url
					if (StringUtils.isNotEmpty(urls)) {
						System.out.println(urls);
						String[] urlArr=urls.split(",");
						for (String url : urlArr) {
							if (StringUtils.isNotEmpty(url)) {
								Long configureId=Long.parseLong(map.get("id").toString());
								Long templateId=Long.parseLong(map.get("templateId").toString());
								Webcollector crawler=new Webcollector(url, configureId, templateId,housingCrawlerMapper);
								crawler.setAmqpTemplate(amqpTemplate);
								crawler.setRoutingKey(routingKey);
								crawler.start(Integer.MAX_VALUE);
								System.out.println("爬网引擎启动");
							}
						}
					}			
				}
			}
		} catch (Exception e) {
			System.out.println("爬网异常："+e.getMessage());
		}				
	}
}
