/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  IAgentDetailServiceApi.java   
 * @Package com.bashiju.wapi   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年8月15日 下午3:13:11   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.wapi;

import java.util.List;

import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.agent.AgentAbility;
import com.bashiju.www.pojo.service.agent.AgentAbilityIdentification;
import com.bashiju.www.pojo.service.agent.AgentCustEvaluate;
import com.bashiju.www.pojo.service.agent.AgentDeail;
import com.bashiju.www.pojo.service.agent.BuyAndSellingRate;
import com.bashiju.www.pojo.service.agent.CustStarRating;
import com.bashiju.www.pojo.service.agent.DealEfficiency;
import com.bashiju.www.pojo.service.agent.DealTransactionInfo;
import com.bashiju.www.pojo.service.agent.GuestMessage;
import com.bashiju.www.pojo.service.agent.PieChart;
import com.bashiju.www.pojo.service.agent.ServiceTravel;

/**   
 * 经纪人详细页服务接口   
 * @ClassName:  IAgentDetailServiceApi   
 * @Description:经纪人详细页服务接口   
 * @author: yangz
 * @date:   2018年8月15日 下午3:13:11   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface IAgentDetailServiceApi {

	/**
	 * 条件查询经纪人详情信息
	 * @Title: queryAgentDeailByAgentId   
	 * @Description: 条件查询经纪人详情信息   
	 * @param agentId 经纪人编号
	 * @return: AgentDeail
	 */
	public AgentDeail queryAgentDeailByAgentId(String agentId);
	
	/**
	 * 条件查询经纪人能力概览信息
	 * @Title: queryAgentAbilityByAgentId   
	 * @Description: 条件查询经纪人能力概览信息   
	 * @param agentId 经纪人编号
	 * @return: List<AgentAbility>
	 */
	public List<AgentAbility> queryAgentAbilityByAgentId(String agentId);
	
	
	
	/**
	 * 条件查询客户对经纪人户评价
	 * @Title: queryUserRatingByAgentId   
	 * @Description: 条件查询客户对经纪人户评价
	 * @param agentId 经纪人编号
	 * @return: List<CustStarRating>
	 */
	public List<CustStarRating> queryUserRatingByAgentId(String agentId);

	/**
	 * 条件查询经纪人能力认定
	 * @Title: queryAgentAbilityIdentificationByAgentId   
	 * @Description: 条件查询经纪人能力认定
	 * @param agentId 经纪人编号
	 * @return: List<AgentAbilityIdentification>
	 */
	public List<AgentAbilityIdentification> queryAgentAbilityIdentificationByAgentId(String agentId);
	
	
	/**
	 * 保存用户对经纪人的留言
	 * @Title: saveGuestMessage   
	 * @Description: 保存用户对经纪人的留言  
	 * @return boolean true:成功，false:失败
	 * @param guestMessage 留言信息
	 */
	public boolean saveGuestMessage(GuestMessage guestMessage);
	
	/**
	 * 查询当前经纪人的成交记录
	 * @Title: queryDealTransactionInfoByTraderId   
	 * @Description: 查询当前经纪人的成交记录 
	 * @param  traderId 经纪人编号
	 * @param page 当前页数
	 * @param limit 每页显示的条数
	 * @return: Page<DealTransactionInfo>
	 */
	public WebPage<DealTransactionInfo> queryDealTransactionInfoByTraderId(String traderId,int page,int limit);
	
	/**
	 * 查询经纪人服务行程
	 * @Title: queryServiceTravel   
	 * @Description: 查询经纪人服务行程
	 * @param userId 经纪人系统编号
	 * @return: List<ServiceTravel>
	 */
	public List<ServiceTravel> queryServiceTravel(String userId);
	
	/**
	 * 查询经纪人的客户评价详情 
	 * @Description: 查询经纪人的客户评价详情 
	 * @param userId 经纪人系统编号
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: Page<AgentCustEvaluate>
	 */
	public WebPage<AgentCustEvaluate> queryCustEvaluateDetails(String userId,int pageNum,int pageSize); 
	
	
	/**
	 * 查询经纪人历史成交价格饼图   
	 * @Description: 查询经纪人历史成交价格饼图   
	 * @param userId 经纪人系统编号
	 * @return: List<PieChart>
	 */
	public List<PieChart> queryDealPriceDistribution(String userId);
	
	/**
	 * 查询经纪人历史成交面积饼图
	 * @Description: 查询经纪人历史成交面积饼图   
	 * @param userId 经纪人系统编号
	 * @return: List<PieChart>
	 */
	public List<PieChart> queryDealSpaceDistribution(String userId);
	
	/**
	 * 查询经纪人历史成交居室饼图
	 * @Description: 查询经纪人历史成交居室饼图   
	 * @param userId 经纪人系统编号
	 * @return: List<PieChart>
	 */
	public List<PieChart> queryDealRoomDistribution(String userId);
	
	/**
	 * 查询经纪人买卖比例信息   
	 * @Description: 查询经纪人买卖比例信息   
	 * @param userId 经纪人系统编号
	 * @return: BuyAndSellingRate
	 */
	public BuyAndSellingRate queryAgentBuyAndSellingRate(String userId);
	
	/**
	 * 查询经纪人成交效率信息
	 * @Description: 查询经纪人成交效率信息   
	 * @param userId 经纪人系统编号
	 * @return: DealEfficiency
	 */
	public DealEfficiency queryDealEfficiency(String userId); 
}
