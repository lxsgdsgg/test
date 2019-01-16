/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  AgentCommunityDealCountMq.java   
 * @Package com.bashiju.quartz.service.mq   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年8月13日 下午7:21:54   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.quartz.service.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.quartz.mapper.AgentCommunityDealCountMapper;

/**   
 * @ClassName:  AgentCommunityDealCountMq   
 * @Description:经纪人小区成交记录统计   
 * @author: wangpeng
 * @date:   2018年8月13日 下午7:21:54   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class AgentCommunityDealCountMq {
	@Autowired
	private AgentCommunityDealCountMapper agentCommunityDealCountMapper;
	/**
	 * @Title: AgentCommunityDealCount   
	 * @Description: 根据城市统计经纪人小区成交记录
	 * @param cityCode 城市编码       
	 * @return: void
	 */
	public void AgentCommunityDealCount(String cityCode) {
		System.out.println("**********************开始经纪人小区成交记录统计  *****************************");
		try {
			agentCommunityDealCountMapper.updateAgentCommunityDealCount(cityCode);
		} catch (Exception e) {
			System.out.println("删除经纪人小区成交记录统计异常"+e.getMessage());
		}
		System.out.println("***********************结束经纪人小区成交记录 统计 **************************************************");
	}
	
}
