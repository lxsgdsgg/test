/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerTest.java   
 * @Package com.bashiju.www.test   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年8月6日 下午3:38:57   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.www.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;

import com.bashiju.api.RedisGetIdServiceApi;
import com.bashiju.www.pojo.service.agent.Agent;
import com.bashiju.www.pojo.service.agent.AgentQueryParams;
import com.bashiju.www.service.agent.AgentService;
import com.bashiju.www.service.navigationbar.NavigationBarService;
import com.bashiju.www.service.user.CustCustomerService;
import com.github.pagehelper.Page; 

public class CustomerTest extends BaseServiceTest {
	
    @Autowired	
    private CustCustomerService custCustomerService;
    
    @Autowired
    private  NavigationBarService navigationBarService;
   	
    @Autowired
    private AgentService agentService;
    @Autowired
	private RedisGetIdServiceApi redisGetIdServiceApi;
   
//   发送短信验证码
//   @Test
//   public void sentMsg() {
//	  String number =  custCustomerService.sendMobileMsgVerfyCode("15696703407");
//	  
//	  
//	  System.out.println("++++++++++++++++++++++"+number);
//   }
////   
   
//   	@Test
//    public void  saveCustomerInfo() {
////   		CustCustomerParam custCustomerParam = new CustCustomerParam();
////   		custCustomerParam.setMobile("15696703407");
////   		custCustomerParam.setName("王鹏");
////   		custCustomerParam.setPassword("wangpeng");
////   		custCustomerParam.setSolt("800127");
////   		
////   		boolean result = custCustomerService.saveCustCustomer(custCustomerParam);
////   		if(result)
////   			System.out.println("success");
////   		System.out.println("defeid");
//    }
//    @Test
//   	public void updateCustomerInfo() {
//    	CustCustomerEntity custCustomerEntity = new CustCustomerEntity();
//    	custCustomerEntity.setName("茄子1111");
//    	custCustomerEntity.setSolt("838140");
//    	custCustomerEntity.setMobile("15696703407");
//    	custCustomerEntity.setUpdateTime(new Date());
//    	custCustomerEntity.setBirthday(new Date());
//    	custCustomerEntity.setAddress("sss");
//    	custCustomerService.updateCustCustomer(custCustomerEntity);
//   	}
   	
    @Test
    public void agentListTest() {
    	AgentQueryParams param = new  AgentQueryParams();
    	param.setCityCode("530100");
    	Page<Agent> agent = agentService.queryAgents(param);
    	System.out.println(agent);
    }

}
