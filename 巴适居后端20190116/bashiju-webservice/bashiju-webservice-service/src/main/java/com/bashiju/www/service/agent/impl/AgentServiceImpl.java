/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  AgentServiceImpl.java   
 * @Package com.bashiju.www.service.agent.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年7月28日 下午4:23:41   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.www.service.agent.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bashiju.webservice.mapper.AgentMapper;
import com.bashiju.wutils.exception.BusinessException;
import com.bashiju.wutils.global.WebGlobal;
import com.bashiju.wutils.redis.ICacheService;
import com.bashiju.wutils.util.Md5EncryptUtils;
import com.bashiju.www.pojo.comm.QueryCondition;
import com.bashiju.www.pojo.comm.QueryCondition.QueryConditionDetail;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.agent.Agent;
import com.bashiju.www.pojo.service.agent.AgentAbility;
import com.bashiju.www.pojo.service.agent.AgentAbilityIdentification;
import com.bashiju.www.pojo.service.agent.AgentCustEvaluate;
import com.bashiju.www.pojo.service.agent.AgentDeail;
import com.bashiju.www.pojo.service.agent.AgentQueryParams;
import com.bashiju.www.pojo.service.agent.BuyAndSellingRate;
import com.bashiju.www.pojo.service.agent.CommunityBestAgent;
import com.bashiju.www.pojo.service.agent.CustStarRating;
import com.bashiju.www.pojo.service.agent.DealEfficiency;
import com.bashiju.www.pojo.service.agent.DealTransactionInfo;
import com.bashiju.www.pojo.service.agent.GuestMessage;
import com.bashiju.www.pojo.service.agent.PieChart;
import com.bashiju.www.pojo.service.agent.ServiceTravel;
import com.bashiju.www.pojo.service.agent.SetGuestMessage;
import com.bashiju.www.service.agent.AgentService;
import com.bashiju.www.service.comm.WebPageUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**   
 * @ClassName:  AgentServiceImpl   
 * @Description:经纪人服务   
 * @author: yangz
 * @date:   2018年7月28日 下午4:23:41   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class AgentServiceImpl implements AgentService {
	@Autowired
	private AgentMapper agentMapper;
	
	@Autowired
	ICacheService cacheService;

	/**
	 * @Description: 条件查询经纪人列表信息  
	 * @param params 查询条件
	 * @return: Page<Agent>
	 * @see com.bashiju.www.service.agent.AgentService#queryAgents(com.bashiju.www.pojo.service.agent.AgentQueryParams)   
	 */
	@Override
	public Page<Agent> queryAgents(AgentQueryParams params) {
		if(params==null)
			throw new BusinessException("查询条件不允许为空");
		if(StringUtils.isEmpty(params.getCityCode()))
			throw new BusinessException("城市代码不允许为空");
		String key = Md5EncryptUtils.md5Encrypt("queryAgents"+JSONObject.toJSONString(params));
		String jsonData = cacheService.getCache(key);
		List<Agent> list = new ArrayList<Agent>(0);
		Page<Agent> pages =null;
		if(!StringUtils.isEmpty(jsonData)) {
			WebPage<Agent> wp = WebPageUtils.parseWebPage(jsonData, Agent.class);
			pages = WebPageUtils.webPageToPage(wp);
		}else {
			//当每页显示的条数超过60条，则默认只显示30条
			PageHelper.startPage(params.getPageNum(), params.getPageSize());
			list = agentMapper.queryAgents(params);
			pages = (Page<Agent>) list;
			if(list!=null && list.size()>0) {
				WebPage<Agent> wp = new WebPage<>(pages);
				cacheService.saveCache(key, wp, WebGlobal.LIST_EXPTIME);
			}
		}
		return pages;
	}
	
	/**
	 * 条件查询经纪人详情
	 * @Description: 条件查询经纪人详情
	 * @param agentId 经纪人编号
	 * @return AgentDeail 
	 * @see  com.bashiju.www.service.agent.AgentService#queryAgentDeailByAgentId(java.lang.String)     
	 */
	@Override
	public AgentDeail queryAgentDeailByAgentId(String agentId) {
		if(StringUtils.isEmpty(agentId))
			throw new BusinessException("经纪人编号不能为空");
		String key = Md5EncryptUtils.md5Encrypt("queryAgentDeailByAgentId"+agentId);
		String jsonData = cacheService.getCache(key);
		if(!StringUtils.isEmpty(jsonData)) {
			AgentDeail agentDeail = JSONObject.parseObject(jsonData, AgentDeail.class);
			return agentDeail;
		}else {
			AgentDeail agentDeail =	agentMapper.queryAgentDeailByAgentId(agentId);
			if(agentDeail!=null)
				cacheService.saveCache(key, agentDeail, WebGlobal.LIST_EXPTIME);
			return agentDeail;
		}
	}


	/**
	 * 条件查询经纪人能力概括
	 * @Description: 条件查询经纪人能力概括
	 * @param params 经纪人编号
	 * @return: AgentAbility 
	 * @see  com.bashiju.www.service.agent.AgentService#queryAgentAbilityByAgentId(java.lang.String)    
	 */
	@Override
	public  List<AgentAbility> queryAgentAbilityByAgentId(String agentId) {
		if(StringUtils.isEmpty(agentId))
			throw new BusinessException("经纪人编号不能为空");
		String key = Md5EncryptUtils.md5Encrypt("queryAgentAbilityByAgentId"+agentId);
		String jsonData = cacheService.getCache(key);
		if(!StringUtils.isEmpty(jsonData)) {
			List<AgentAbility> list = JSONArray.parseArray(jsonData, AgentAbility.class);
			return list;
		}else {
			List<AgentAbility> agentaBility = agentMapper.queryAgentAbilityByAgentId(agentId);
			if(agentaBility!=null && agentaBility.size()>0)
				cacheService.saveCache(key, agentaBility, WebGlobal.LIST_EXPTIME);
			return agentaBility;
		}
	}

	/**
	 * @Description: 条件查询用户对经纪人评分
	 * @param agentId 经纪人编号
	 * @return: List<UserRating>
	 * @see  com.bashiju.www.service.agent.AgentService#queryUserRatingByAgentId(java.lang.String)     
	 */
	@Override
	public List<CustStarRating> queryUserRatingByAgentId(String agentId) {
		if(StringUtils.isEmpty(agentId))
			throw new BusinessException("经纪人编号不能为空");
		String key = Md5EncryptUtils.md5Encrypt("queryUserRatingByAgentId"+agentId);
		String jsonData = cacheService.getCache(key);
		if(!StringUtils.isEmpty(jsonData)) {
			List<CustStarRating> list = JSONArray.parseArray(jsonData, CustStarRating.class);
			return list;
		}else {
			List<CustStarRating> userRating = agentMapper.queryUserRatingByAgentId(agentId);
			if(userRating!=null && userRating.size()>0)
				cacheService.saveCache(key, userRating, WebGlobal.LIST_EXPTIME);
			return userRating;
		}
	}
	
	/**
	 * @Description: 条件查询经纪人能力认定
	 * @param agentId 经纪人编号
	 * @return: AgentAbilityIdentification 
	 * @see com.bashiju.www.service.agent.AgentService#queryAgentAbilityIdentificationByAgentId(java.lang.String)    
	 */
	@Override
	public List<AgentAbilityIdentification> queryAgentAbilityIdentificationByAgentId(String agentId) {
		if(StringUtils.isEmpty(agentId))
			throw new BusinessException("经纪人编号不能为空");
		String key = Md5EncryptUtils.md5Encrypt("queryAgentAbilityIdentificationByAgentId"+agentId);
		String jsonData = cacheService.getCache(key);
		if(!StringUtils.isEmpty(jsonData)) {
			List<AgentAbilityIdentification> list = JSONArray.parseArray(jsonData, AgentAbilityIdentification.class);
			return list;
		}else {
			List<AgentAbilityIdentification> agentAbilityIdentification = agentMapper.queryAgentAbilityIdentificationByAgentId(agentId);
			if(agentAbilityIdentification!=null && agentAbilityIdentification.size()>0)
				cacheService.saveCache(key, agentAbilityIdentification, WebGlobal.LIST_EXPTIME);
			return agentAbilityIdentification;
		}
	}
	
	/**
	 * @Description:  新增用户留言
	 * @param guestMessage 留言信息
	 * @see  com.bashiju.www.service.agent.AgentService#saveGuestMessage(com.bashiju.www.pojo.service.agent.GuestMessage)
	 */
	@SuppressWarnings("unused")
	@Override
	public boolean saveGuestMessage(GuestMessage guestMessage) {
		if(guestMessage==null)
			throw new BusinessException("留言信息不允许为空");
		if(StringUtils.isEmpty(String.valueOf(guestMessage.getAgentCode())))
			throw new BusinessException("经纪人编号不能为空");
		//查询经纪人的权限域和operatorId,operator
		Map<String,Object> agentInfo = agentMapper.queryAgentOperAndPer(guestMessage.getAgentCode());
		if(agentInfo.size()<0 && agentInfo == null)
			throw new BusinessException("经纪人信息有误");
		SetGuestMessage sm = new SetGuestMessage();
		sm.setUserId(guestMessage.getUserCode());
		sm.setAgentId(guestMessage.getAgentCode());
		sm.setDeptId(guestMessage.getDeptCode());
		sm.setDeptName(guestMessage.getDeptName());
		sm.setMessageType(guestMessage.getMessageType());
		sm.setMessageTypeId(guestMessage.getMessageTypeCode());
		sm.setRealName(guestMessage.getRealName());
		sm.setMobile(guestMessage.getMobile());
		sm.setContent(guestMessage.getContent());
		sm.setOperator(agentInfo.get("operator").toString());
		sm.setOperatorId(agentInfo.get("operatorId").toString());
		sm.setPermissionArea(agentInfo.get("permissionArea").toString());
		sm.setIsvalid(true);
		sm.setAddTime(new Date());
		sm.setUpdateTime(new Date());
		sm.setRead(false);
		long result = agentMapper.saveGuestMessage(sm);
		if(result>0)
			return true;
		throw new BusinessException("添加留言失败");			
	}

	
	/**
	 * @Description: 条件查询经纪人成交信息
	 * @param traderId 经纪人系统编号
	 * @return:  DealTransactionInfo 
	 * @see  com.bashiju.www.service.agent.AgentService#queryDealTransactionInfoByTraderId(java.lang.String)
	 */
	@Override
	public Page<DealTransactionInfo> queryDealTransactionInfoByTraderId(String traderId,int page,int limit) {
		if(StringUtils.isEmpty(traderId))
			throw new BusinessException("经纪人系统编号不能为空");
		String key = Md5EncryptUtils.md5Encrypt("queryDealTransactionInfoByTraderId"+traderId+page+limit);
		String jsonData = cacheService.getCache(key);
		Page<DealTransactionInfo> pages =null;
		if(!StringUtils.isEmpty(jsonData)) {
			WebPage<DealTransactionInfo> wp = WebPageUtils.parseWebPage(jsonData, DealTransactionInfo.class);
			pages = WebPageUtils.webPageToPage(wp);
		}else {
			PageHelper.startPage(page, limit);
			pages = agentMapper.queryDealTransactionInfoByTraderId(traderId);
			WebPage<DealTransactionInfo> wp = new WebPage<DealTransactionInfo>(pages);
			if(wp.getResult().size()>0)
				cacheService.saveCache(key, wp, WebGlobal.LIST_EXPTIME);
		}
		return pages;
	}


	/**
	 * @Title: queryServiceTravel   
	 * @Description: 查询服务行程
	 * @param userId 经纪人系统编号
	 * @return: List<ServiceTravel>
	 * @see  com.bashiju.www.service.agent.AgentService#queryServiceTravel()  
	 */
	@Override
	public List<ServiceTravel> queryServiceTravel(String userId) {
		if(StringUtils.isEmpty(userId))
			throw new BusinessException("经纪人系统编号不能为空");
		String key = Md5EncryptUtils.md5Encrypt("queryServiceTravel"+userId);
		String jsonData = cacheService.getCache(key);
		if(!StringUtils.isEmpty(jsonData)) {
			List<ServiceTravel> list = JSONArray.parseArray(jsonData, ServiceTravel.class);
			return list;
		}else {
			List<ServiceTravel> serviceTravel = agentMapper.queryServiceTravel(userId);
			if(serviceTravel!=null && serviceTravel.size()>0)
				cacheService.saveCache(key, serviceTravel, WebGlobal.LIST_EXPTIME);
			return serviceTravel;
		}
//		return null;
	}


	/**
	 * 查询条件列表 
	 * @Description: 查询条件列表   
	 * @return: List<Map<String,String>>
	 * @see com.bashiju.www.service.agent.AgentService#queryQueryConditions()   
	 */
	@Override
	public List<QueryCondition> queryQueryConditions() {
		String key = Md5EncryptUtils.md5Encrypt("agentQueryQueryConditions");//这里的主键因为可能存在重复，所以加了个前缀
		String jsonData = cacheService.getCache(key);
		List<Map<String, String>> list = new ArrayList<Map<String,String>>(0);
		if(!StringUtils.isEmpty(jsonData)) {
			list = JSON.parseObject(jsonData, new TypeReference<List<Map<String,String>>>(){});
		}else {
			list = agentMapper.queryQueryConditions();
			if(list!=null && list.size()>0)
				cacheService.saveCache(key, list, WebGlobal.LIST_EXPTIME);
		}
		List<QueryCondition> conditions = new ArrayList<QueryCondition>(0);
		QueryCondition qc = new QueryCondition();
		qc.setConditionCode("labels");
		qc.setConditionName("标签");
		List<QueryConditionDetail> datas = new ArrayList<QueryConditionDetail>(0);
		for(Map<String,String> map : list) {
			QueryConditionDetail data = new QueryCondition().new QueryConditionDetail();
			data.setCode(map.get("code"));
			data.setName(map.get("name"));
			datas.add(data);
		}
		qc.setDatas(datas);
		conditions.add(qc);
		return conditions;
	}


	/**
	 * 查询小区内最优的4个经纪人
	 * @Description: 查询小区内最优的4个经纪人   
	 * @param communityId 小区编号
	 * @return: List<CommunityBestAgent>
	 * @see com.bashiju.www.service.agent.AgentService#queryCommunityBestAgent(java.lang.String)   
	 */
	@Override
	public List<CommunityBestAgent> queryCommunityBestAgent(String communityId) {
		if(StringUtils.isEmpty(communityId))
			throw new BusinessException("小区编号不能为空");
		String key = Md5EncryptUtils.md5Encrypt("queryCommunityBestAgent"+communityId);
		String jsonData = cacheService.getCache(key);
		if(!StringUtils.isEmpty(jsonData)) {
			List<CommunityBestAgent> list = JSONArray.parseArray(jsonData, CommunityBestAgent.class);
			return list;
		}else {
			List<CommunityBestAgent> list = agentMapper.queryCommunityBestAgent(communityId);
			if(list!=null && list.size()>0)
				cacheService.saveCache(key, list, WebGlobal.LIST_EXPTIME);
			return list;
		}
	}


	/**
	 * 查询经纪人的客户评价详情 
	 * @Description: 查询经纪人的客户评价详情 
	 * @param userId 经纪人系统编号
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @see com.bashiju.www.service.agent.AgentService#queryCustEvaluateDetails(java.lang.String)   
	 */
	@Override
	public Page<AgentCustEvaluate> queryCustEvaluateDetails(String userId,int pageNum,int pageSize) {
		if(StringUtils.isEmpty(userId))
			throw new BusinessException("经纪人系统编号不允许为空");
		String key = Md5EncryptUtils.md5Encrypt("queryCustEvaluateDetails"+userId+pageNum+pageSize);
		String jsonData = cacheService.getCache(key);
		Page<AgentCustEvaluate> pages =null;
		if(!StringUtils.isEmpty(jsonData)) {
			WebPage<AgentCustEvaluate> wp = WebPageUtils.parseWebPage(jsonData, AgentCustEvaluate.class);
			pages = WebPageUtils.webPageToPage(wp);
		}else {
			PageHelper.startPage(pageNum, pageSize);
			List<AgentCustEvaluate> list = agentMapper.queryCustEvaluateDetails(userId);
			pages = (Page<AgentCustEvaluate>) list;
			WebPage<AgentCustEvaluate> wp = new WebPage<AgentCustEvaluate>(pages);
			if(wp.getResult().size()>0)
				cacheService.saveCache(key, wp, WebGlobal.LIST_EXPTIME);
		}
		return pages;
	}


	/**
	 * 查询经纪人历史成交价格饼图   
	 * @Description: 查询经纪人历史成交价格饼图   
	 * @param userId 经纪人系统编号
	 * @return: List<PieChart>
	 * @see com.bashiju.www.service.agent.AgentService#queryDealPriceDistribution(java.lang.String)   
	 */
	@Override
	public List<PieChart> queryDealPriceDistribution(String userId) {
		if(StringUtils.isEmpty(userId))
			throw new BusinessException("经纪人系统编号不允许为空");
		String key = Md5EncryptUtils.md5Encrypt("queryDealPriceDistribution"+userId);
		String jsonData = cacheService.getCache(key);
		if(!StringUtils.isEmpty(jsonData)) {
			List<PieChart> list = JSONArray.parseArray(jsonData, PieChart.class);
			return list;
		}else {
			List<PieChart> list = agentMapper.queryDealPriceDistribution(userId);
			if(list!=null && list.size()>0)
				cacheService.saveCache(key, list, WebGlobal.LIST_EXPTIME);
			return list;
		}
	}


	/**
	 * 查询经纪人历史成交面积饼图
	 * @Description: 查询经纪人历史成交面积饼图   
	 * @param userId 经纪人系统编号
	 * @return: List<PieChart>
	 * @see com.bashiju.www.service.agent.AgentService#queryDealSpaceDistribution(java.lang.String)   
	 */
	@Override
	public List<PieChart> queryDealSpaceDistribution(String userId) {
		if(StringUtils.isEmpty(userId))
			throw new BusinessException("经纪人系统编号不允许为空");
		String key = Md5EncryptUtils.md5Encrypt("queryDealSpaceDistribution"+userId);
		String jsonData = cacheService.getCache(key);
		if(!StringUtils.isEmpty(jsonData)) {
			List<PieChart> list = JSONArray.parseArray(jsonData, PieChart.class);
			return list;
		}else {
			List<PieChart> list = agentMapper.queryDealSpaceDistribution(userId);
			if(list!=null && list.size()>0)
				cacheService.saveCache(key, list, WebGlobal.LIST_EXPTIME);
			return list;
		}
	}


	/**
	 * 查询经纪人历史成交居室饼图
	 * @Description: 查询经纪人历史成交居室饼图   
	 * @param userId 经纪人系统编号
	 * @return: List<PieChart>
	 * @see com.bashiju.www.service.agent.AgentService#queryDealRoomDistribution(java.lang.String)   
	 */
	@Override
	public List<PieChart> queryDealRoomDistribution(String userId) {
		if(StringUtils.isEmpty(userId))
			throw new BusinessException("经纪人系统编号不允许为空");
		String key = Md5EncryptUtils.md5Encrypt("queryDealRoomDistribution"+userId);
		String jsonData = cacheService.getCache(key);
		if(!StringUtils.isEmpty(jsonData)) {
			List<PieChart> list = JSONArray.parseArray(jsonData, PieChart.class);
			return list;
		}else {
			List<PieChart> list = agentMapper.queryDealRoomDistribution(userId);
			if(list!=null && list.size()>0)
				cacheService.saveCache(key, list, WebGlobal.LIST_EXPTIME);
			return list;
		}
	}


	/**
	 * 查询经纪人买卖比例信息   
	 * @Description: 查询经纪人买卖比例信息   
	 * @param userId 经纪人系统编号
	 * @return: BuyAndSellingRate  
	 * @see com.bashiju.www.service.agent.AgentService#queryAgentBuyAndSellingRate(java.lang.String)   
	 */
	@Override
	public BuyAndSellingRate queryAgentBuyAndSellingRate(String userId) {
		if(StringUtils.isEmpty(userId))
			throw new BusinessException("经纪人系统编号不允许为空");
		String key = Md5EncryptUtils.md5Encrypt("queryAgentBuyAndSellingRate"+userId);
		String jsonData = cacheService.getCache(key);
		if(!StringUtils.isEmpty(jsonData)) {
			BuyAndSellingRate obj = JSONObject.parseObject(jsonData, BuyAndSellingRate.class);
			return obj;
		}else {
			BuyAndSellingRate obj = agentMapper.queryAgentBuyAndSellingRate(userId);
			if(obj!=null)
				cacheService.saveCache(key, obj, WebGlobal.LIST_EXPTIME);
			return obj;
		}
	}


	/**
	 * 查询经纪人成交效率信息
	 * @Description: 查询经纪人成交效率信息   
	 * @param userId 经纪人系统编号
	 * @return: DealEfficiency
	 * @see com.bashiju.www.service.agent.AgentService#queryDealEfficiency(java.lang.String)   
	 */
	@Override
	public DealEfficiency queryDealEfficiency(String userId) {
		if(StringUtils.isEmpty(userId))
			throw new BusinessException("经纪人系统编号不允许为空");
		String key = Md5EncryptUtils.md5Encrypt("queryDealEfficiency"+userId);
		String jsonData = cacheService.getCache(key);
		if(!StringUtils.isEmpty(jsonData)) {
			DealEfficiency obj = JSONObject.parseObject(jsonData, DealEfficiency.class);
			return obj;
		}else {
			DealEfficiency obj = agentMapper.queryDealEfficiency(userId);
			if(obj !=null)
				cacheService.saveCache(key, obj, WebGlobal.LIST_EXPTIME);
			return obj; 
		}
	}


	/**
	 * 条件搜索查询经纪人
	 * @Description: 条件搜索查询经纪人
	 * @param cityCode 城市编码
	 * @param params 查询条件： 经纪人名称 (条件长度不小于两个字符不大于84个字符)
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: Page<Agent>
	 * @see com.bashiju.www.service.agent.AgentService#queryAgentsByFullText(java.lang.String, java.lang.String)   
	 */
	@Override
	public Page<Agent> queryAgentsByFullText(String cityCode, String params
			,int pageNum,int pageSize) {
		if(StringUtils.isEmpty(cityCode))
			throw new BusinessException("城市编号不允许为空");
		if(StringUtils.isEmpty(params))
			throw new BusinessException("查询条件不允许为空");
		if(params.length()<2)
			throw new BusinessException("查询条件太短了");
		if(params.length()>84)
			throw new BusinessException("查询条件太长了");
		
		String key = Md5EncryptUtils.md5Encrypt("queryAgentsByFullText"+cityCode+params+pageNum+pageSize);
		String jsonData = cacheService.getCache(key);
		Page<Agent> pages =null;
		if(!StringUtils.isEmpty(jsonData)) {
			WebPage<Agent> wp = WebPageUtils.parseWebPage(jsonData, Agent.class);
			pages = WebPageUtils.webPageToPage(wp);
		}else {
			PageHelper.startPage(pageNum, pageSize);
			List<Agent> list = agentMapper.queryAgentsByFullText(cityCode,params);
			pages = (Page<Agent>) list;
			WebPage<Agent> wp = new WebPage<Agent>(pages);
			if(wp.getResult().size()>0)
				cacheService.saveCache(key, wp, WebGlobal.LIST_EXPTIME);
		}
		return pages;
	}
	
}
