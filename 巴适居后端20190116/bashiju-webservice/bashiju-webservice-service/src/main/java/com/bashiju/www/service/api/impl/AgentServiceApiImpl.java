/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  AgentServiceApiImpl.java   
 * @Package com.bashiju.www.service.api.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年8月15日 下午3:04:10   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.www.service.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.wapi.IAgentServiceApi;
import com.bashiju.www.pojo.comm.QueryCondition;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.agent.Agent;
import com.bashiju.www.pojo.service.agent.AgentQueryParams;
import com.bashiju.www.service.agent.AgentService;
import com.github.pagehelper.Page;

/**   
 * 经纪人列表页服务
 * @ClassName:  AgentServiceApiImpl   
 * @Description:经纪人列表页服务   
 * @author: yangz
 * @date:   2018年8月15日 下午3:04:10   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class AgentServiceApiImpl implements IAgentServiceApi {

	@Autowired
	private AgentService agentService;
	
	/**
	 * 条件查询经纪人列表信息
	 * @Description: 条件查询经纪人列表信息  
	 * @param params 查询条件
	 * @return: Page<Agent>
	 * @see com.bashiju.wapi.IAgentServiceApi#queryAgents(com.bashiju.www.pojo.service.agent.AgentQueryParams)   
	 */
	@Override
	public WebPage<Agent> queryAgents(AgentQueryParams params) {
		Page<Agent> page = agentService.queryAgents(params);
		WebPage<Agent> result=new WebPage<Agent>(page);
		return result;
	}

	/**
	 * 查询条件列表 
	 * @Description: 查询条件列表   
	 * @return: List<QueryCondition>
	 * @see com.bashiju.wapi.IAgentServiceApi#queryQueryConditions()   
	 */
	@Override
	public List<QueryCondition> queryQueryConditions() {
		return agentService.queryQueryConditions();
	}

	/**
	 * 条件搜索查询经纪人
	 * @Description: 条件搜索查询经纪人
	 * @param cityCode 城市编码
	 * @param params 查询条件
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: WebPage<Agent>
	 * @see com.bashiju.wapi.IAgentServiceApi#queryAgentsByFullText(java.lang.String, java.lang.String, int, int)   
	 */
	@Override
	public WebPage<Agent> queryAgentsByFullText(String cityCode, String params, int pageNum, int pageSize) {
		Page<Agent> page = agentService.queryAgentsByFullText(cityCode, params, pageNum, pageSize);
		WebPage<Agent> result=new WebPage<Agent>(page);
		return result;
	}

}
