/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  AgentAcceptanceUpdate.java   
 * @Package com.bashiju.quartz.service.mq   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年8月13日 下午5:29:26   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.quartz.service.mq;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bashiju.quartz.mapper.AgentAcceptanceUpdateMapper;

/**   
 * @ClassName:  AgentAcceptanceUpdate   
 * @Description:经纪人受理委托变更(当前委托的经纪人要是半小时之内不处理，则变更给另一个经纪人)   
 * @author: yangz
 * @date:   2018年8月13日 下午5:29:26   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class AgentAcceptanceUpdateMq {
	
	@Autowired
	private AgentAcceptanceUpdateMapper agentAcceptanceUpdateMapper;
	
	/**
	 * @Description: 变更经纪人委托信息        
	 * @return: void
	 */
	public void agentAcceptanceUpdate(Map<String,Object> map) {
		agentAcceptanceUpdateMapper.agentAcceptanceUpdate();
	}
}
