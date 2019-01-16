/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  AgentTest.java   
 * @Package com.bashiju.www.test   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年8月15日 下午5:30:28   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bashiju.www.pojo.service.agent.GuestMessage;
import com.bashiju.www.service.agent.AgentService;

/**   
 * @ClassName:  AgentTest   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: wangpeng
 * @date:   2018年8月15日 下午5:30:28   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class AgentTest extends  BaseServiceTest {
	@Autowired
	private AgentService agentService;

//	@Test
//	public void test() {
//		
//		List<CustStarRating> test = agentService.queryUserRatingByAgentId("2");
//		System.out.println(test);
//	}
//	
//	@Test
//	public void queryAgentsTest() {
//		AgentQueryParams  params = new AgentQueryParams();
//		params.setCityCode("530100");
//		Page<Agent> page = agentService.queryAgents(params);
//		System.out.println(page);
//	}
	
//	@Test
//	public void queryServiceTravel() {
//		List<ServiceTravel> list = agentService.queryServiceTravel("1");
//		System.out.println(list);
//	}
	
//	@Test
//	public void queryServiceTravel() {
//		List<AgentAbilityIdentification> agentAbilityIdentification = agentService.queryAgentAbilityIdentificationByAgentId("1");
//		System.out.println(agentAbilityIdentification);
//	}
/*	@Test
	public void queryServiceTravel() {
		AgentDeail agentDeail = agentService.queryAgentDeailByAgentId("1");
		System.out.println(agentDeail);
	}*/
//	@Test
//	public void queryDealTransactionInfoByTraderId() {
//		Page<DealTransactionInfo> test = agentService.queryDealTransactionInfoByTraderId("13320", 1, 10);
//		System.out.println(test);
//	}
	
	@Test
	public void queryServiceTravel() {
		GuestMessage guestMessage = new GuestMessage();
		guestMessage.setUserCode("1");
		guestMessage.setAgentCode("1");
		guestMessage.setContent("不好");
		guestMessage.setDeptName("测试waper2");
		guestMessage.setMessageTypeCode("1");
		guestMessage.setMobile("18302392882");
		guestMessage.setRealName("123456");
		guestMessage.setMessageType("1");
	   boolean result  = agentService.saveGuestMessage(guestMessage);
		  System.out.println(result);
	}


	
}
