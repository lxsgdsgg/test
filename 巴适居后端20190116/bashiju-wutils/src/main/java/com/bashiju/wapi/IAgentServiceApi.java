/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  IAgentServiceApi.java   
 * @Package com.bashiju.wapi   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年8月15日 下午2:40:10   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.wapi;

import java.util.List;

import com.bashiju.www.pojo.comm.QueryCondition;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.agent.Agent;
import com.bashiju.www.pojo.service.agent.AgentQueryParams;

/**   
 * 经纪人列表页服务接口 
 * @ClassName:  IAgentServiceApi   
 * @Description:经纪人列表页服务接口   
 * @author: yangz
 * @date:   2018年8月15日 下午2:40:10   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface IAgentServiceApi {

	/**
	 * 条件查询经纪人列表信息
	 * @Description: 条件查询经纪人列表信息  
	 * @param params 查询条件
	 * @return: WebPage<Agent>
	 */
	public WebPage<Agent> queryAgents(AgentQueryParams params);
	
	/**
	 * 查询条件列表 
	 * @Description: 查询条件列表   
	 * @return: List<QueryCondition>
	 */
	public List<QueryCondition> queryQueryConditions();
	
	/**
	 * 条件搜索查询经纪人
	 * @Description: 条件搜索查询经纪人
	 * @param cityCode 城市编码
	 * @param params 查询条件
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: WebPage<Agent>
	 */
	public WebPage<Agent> queryAgentsByFullText(String cityCode,String params,int pageNum,int pageSize);
}
