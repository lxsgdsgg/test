/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  AgentMapper.java   
 * @Package com.bashiju.www.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年7月28日 下午5:21:13   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bashiju.www.pojo.service.agent.Agent;
import com.bashiju.www.pojo.service.agent.AgentAbility;
import com.bashiju.www.pojo.service.agent.AgentAbilityIdentification;
import com.bashiju.www.pojo.service.agent.AgentDeail;
import com.bashiju.www.pojo.service.agent.AgentQueryParams;
import com.bashiju.www.pojo.service.agent.CustStarRating;
import com.bashiju.www.pojo.service.agent.DealTransactionInfo;
import com.bashiju.www.pojo.service.agent.ServiceTravel;
import com.bashiju.www.pojo.service.agent.SetGuestMessage;
import com.github.pagehelper.Page;

/**   
 * @ClassName:  AgentMapper   
 * @Description:经纪人详情映射文件  
 * @author: wangpeng
 * @date:   2018年7月28日 下午5:21:13   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface AgentMapper {
	
	/**
	 * @Title: queryAgentDeailByAgentId   
	 * @Description: 条件查询经纪人详情信息
	 * @param agentId 经纪人id
	 * @return: AgentDeail
	 */
	public  AgentDeail queryAgentDeailByAgentId(@Param("agentId")String agentId,@Param("deptId")String deptId);
	
	
	
	/**
	 * @Title: queryAgentAbilityByAgentId   
	 * @Description: 条件查询经纪人能力概括
	 * @param agentId 经纪人id
	 * @return: AgentAbility
	 */
	public List<AgentAbility> queryAgentAbilityByAgentId(@Param("agentId")String agentId);
	
	
	/**
	 * @Title: queryUserRatingByAgentId   
	 * @Description: 条件查询客户对经纪人户评价
	 * @param agentId 经纪人id
	 * @return: List<UserRating>
	 */
	public List<CustStarRating> queryUserRatingByAgentId(@Param("agentId")String agentId);

	/**
	 * @Title: queryAgentAbilityIdentificationByAgentId   
	 * @Description: 条件查询经纪人能力认定   
	 * @param agentId 经纪人id
	 * @return: AgentAbilityIdentification
	 */
	public AgentAbilityIdentification queryAgentAbilityIdentificationByAgentId(@Param("agentId")String agentId);
	
	/**
	 * @Title: saveGuestMessage   
	 * @Description: 添加用户对经纪人留言   
	 * @param setGuestMessage 留言内容
	 */
	public void saveGuestMessage(SetGuestMessage setGuestMessage);
	
	/**
	 * @Title: queryDealTransactionInfoByTraderId   
	 * @Description: 条件查询成交信息 
	 * @param traderId 成交人id
	 * @return: Page<DealTransactionInfo>
	 */
	public Page<DealTransactionInfo>  queryDealTransactionInfoByTraderId(@Param("traderId")String traderId);
	
	/**
	 * @Title: queryServiceTravel   
	 * @Description: 查询服务行程   
	 * @param userId 经纪人系统编号
	 * @return: List<ServiceTravel>
	 */
	public List<ServiceTravel> queryServiceTravel(@Param("userId")String userId);



	/**
	 * @Description: 条件查询经纪人列表信息  
	 * @param params 查询条件
	 * @return: Page<Agent>    
	 */ 
	public List<Agent> queryAgents(AgentQueryParams params);
}
