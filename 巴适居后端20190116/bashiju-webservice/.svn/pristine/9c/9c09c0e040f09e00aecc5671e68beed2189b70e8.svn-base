/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CommunityDetailServiceApiImpl.java   
 * @Package com.bashiju.www.service.api.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年8月15日 下午1:55:28   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.www.service.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.wapi.ICommunityDetailServiceApi;
import com.bashiju.www.pojo.service.agent.CommunityBestAgent;
import com.bashiju.www.pojo.service.community.Community;
import com.bashiju.www.pojo.service.community.CommunityDetail;
import com.bashiju.www.pojo.service.community.CommunityPriceTrend;
import com.bashiju.www.pojo.service.out.house.HouseListResult;
import com.bashiju.www.service.agent.AgentService;
import com.bashiju.www.service.community.CommunityService;

/**   
 * @ClassName:  CommunityDetailServiceApiImpl   
 * @Description:小区详情页服务   
 * @author: yangz
 * @date:   2018年8月15日 下午1:55:28   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class CommunityDetailServiceApiImpl implements ICommunityDetailServiceApi {

	@Autowired
	private CommunityService communityService;
	
	@Autowired
	private AgentService agentService;
	/**
	 * @Description: 根据小区编号查询小区详情信息   
	 * @param communityId 小区编号
	 * @return: CommunityDetail 
	 * @see com.bashiju.wapi.ICommunityDetailServiceApi#getCommunityDetail(java.lang.String)   
	 */
	@Override
	public CommunityDetail getCommunityDetail(String communityId) {
		return communityService.getCommunityDetail(communityId);
	}

	/**
	 * @Title: querySealHousingByCommunityId   
	 * @Description: 条件查询在售房源
	 * @param communityId 小区编号
	 * @return: List<HouseListResult>
	 * @see com.bashiju.wapi.ICommunityDetailServiceApi#querySealHousingByCommunityId(java.lang.String)   
	 */
	@Override
	public List<HouseListResult> querySealHousingByCommunityId(String communityId,Integer room) {
		return communityService.querySealHousingByCommunityId(communityId,room);
	}

	/**
	 * @Title: queryLeaseHousingByCommunityId   
	 * @Description: 条件查询出租房源   
	 * @param communityId 小区编号
	 * @return: List<HouseListResult>
	 * @see com.bashiju.wapi.ICommunityDetailServiceApi#queryLeaseHousingByCommunityId(java.lang.String)   
	 */
	@Override
	public List<HouseListResult> queryLeaseHousingByCommunityId(String communityId,Integer room) {
		return communityService.queryLeaseHousingByCommunityId(communityId,room);
	}

	/**
	 * @Title: queryNearbyCommunity   
	 * @Description: 条件查询附近小区   
	 * @param communityId 小区编号
	 * @return: List<Community> 
	 * @see com.bashiju.wapi.ICommunityDetailServiceApi#queryNearbyCommunity(java.lang.String)   
	 */
	@Override
	public List<Community> queryNearbyCommunity(String communityId) {
		return communityService.queryNearbyCommunity(communityId);
	}

	/**
	 * @Description: 查询小区的价格趋势信息  
	 * @param communityId 小区编号
	 * @return: List<CommunityPriceTrend>
	 * @see com.bashiju.wapi.ICommunityDetailServiceApi#queryCommunityPriceTrend(java.lang.String)   
	 */
	@Override
	public List<CommunityPriceTrend> queryCommunityPriceTrend(String communityId) {
		return communityService.queryCommunityPriceTrend(communityId);
	}

	/**
	 * 查询小区内最优的4个经纪人
	 * @Description: 查询小区内最优的4个经纪人   
	 * @param communityId 小区编号
	 * @return: List<CommunityBestAgent>
	 * @see com.bashiju.wapi.ICommunityDetailServiceApi#queryCommunityBestAgent(java.lang.String)   
	 */
	@Override
	public List<CommunityBestAgent> queryCommunityBestAgent(String communityId) {
		return agentService.queryCommunityBestAgent(communityId);
	}

}
