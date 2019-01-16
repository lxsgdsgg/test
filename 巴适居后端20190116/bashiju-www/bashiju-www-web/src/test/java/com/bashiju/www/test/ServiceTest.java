package com.bashiju.www.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bashiju.www.service.agent.AgentService;
import com.bashiju.www.service.user.CustCustomerService;

/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  InterFaceTest.java   
 * @Package    
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年7月25日 下午5:24:12   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

/**   
 * @ClassName:  InterFaceTest   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: yangz
 * @date:   2018年7月25日 下午5:24:12   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@RunWith(SpringJUnit4ClassRunner.class)    
@ContextConfiguration(locations = {
		"classpath*:/bashiju-www-servlet.xml",
		"classpath*:/spring/applicationContext.xml",
		"classpath*:/spring/applicationContext-transaction.xml",
		"classpath*:/spring/applicationContext-mybatis.xml"
		})  
public class ServiceTest  extends BaseServiceTest{
	
//	@Autowired
//	private CommunityService communityService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private CustCustomerService custCustomerService;
//	@Autowired
//	private RandomValidateCode randomValidateCode;
	@Autowired(required=false) 
	MockHttpServletRequest request;
	
	@Autowired(required=false) 
	MockHttpServletResponse response;
//	@Test
//	public void test1() {
////		CommunityQueryParams params = new CommunityQueryParams();
////		params.setCityCode("530100");
////		params.setPageNum(1);
////		params.setPageSize(10);
////		Page<Community> page = communityService.queryCommunitys(params);
////		System.out.println(JSONObject.toJSONString(page));
////		HttpServletRequest request = 
////		HttpServletResponse response
//		
////		boolean result =	custCustomerService.customerLogin(request, response, "18302392883", "123456", "VJ7R");
////		if(result)
////			System.out.println("success");
////		else {
////			
////			System.out.println("defaid");
////		}
//	
//	}
	
	@Test
	public void checkImg() {
		String ss = custCustomerService.imageCheck(request, response);
		System.out.println("**********"+ss);
	}
}
