/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CommunityBestAgent.java   
 * @Package com.bashiju.quartz.service.mq   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年7月31日 上午10:24:28   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.quartz.service.mq;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.quartz.mapper.CommunityBestAgentMapper;

/**   
 * @ClassName:  CommunityBestAgent   
 * @Description:筛选小区最优经纪人(筛选小区中综合评价最高的4个人)   
 * @author: yangz
 * @date:   2018年7月31日 上午10:24:28   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class CommunityBestAgentMq{

	@Autowired
	private CommunityBestAgentMapper communityBestAgentMapper;
	
	/**
	 * @Description: 更新并查询出最优经纪人信息          
	 * @return: void
	 */
	public void updateAndQueryCommunityBestAgents(Map<String,Object> Map) {
		communityBestAgentMapper.updateAndQueryCommunityBestAgents();
	}
	
}
