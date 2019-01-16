/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerStarCommentsServiceImpl.java   
 * @Package com.bashiju.www.service.user.impl      
 * @author: zuoyuntao     
 * @date:   2018年7月26日 下午4:14:14   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.service.user.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.enums.LabelTypeEnum;
import com.bashiju.webservice.mapper.CustomerStarCommentsMapper;
import com.bashiju.wutils.exception.BusinessException;
import com.bashiju.wutils.redis.ICacheService;
import com.bashiju.wutils.util.Md5EncryptUtils;
import com.bashiju.www.global.WebGlobal;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.out.comm.LabelResult;
import com.bashiju.www.pojo.service.out.usercentral.CustomerAgentStarCommentsEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerSeeHouseCommentsEntity;
import com.bashiju.www.pojo.service.starRating.CustomerStarRatingHotLabelEntity;
import com.bashiju.www.pojo.service.starRating.CustomerStarRatingServiceComment;
import com.bashiju.www.service.comm.ICommService;
import com.bashiju.www.service.user.ICustomerStarCommentsService;
import com.bashiju.www.tools.WebCommonTools;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;

/**
 * 客户评价服务
 * @ClassName:CustomerStarCommentsServiceImpl
 * @Description:客户评价服务
 * @author:zuoyuntao
 * @date:2018年7月26日 下午4:14:14
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class CustomerStarCommentsServiceImpl implements ICustomerStarCommentsService{
	/**
	 * Redis操作服务
	 */
	@Autowired
	private ICacheService mICacheService;
	/**
	 * 客户星级评价持久层接口对象
	 */
	@Autowired
	private CustomerStarCommentsMapper mCustomerStarCommentsMapper;
	/**
	 * 对象映射
	 */
	private static ObjectMapper MAPPER = new ObjectMapper();
	/**
	 * 通用服务接口
	 */
	@Autowired
	private ICommService mICommService;
	/**
	 * <p>Title: queryCustomerComments</p>   
	 * <p>Description:查询客户的所有评价 </p>   
	 * @param custId 登陆用户ID
	 * @param page 每页显示最少条数
	 * @param limit 每页显示最大条数
	 * @return   
	 * @see com.bashiju.www.service.user.ICustomerStarCommentsService#queryCustomerComments(java.lang.String)
	 */
	@Override
	public WebPage<CustomerAgentStarCommentsEntity> queryCustomerComments(
			String custId,int page,int limit) {
		String redisKey = Md5EncryptUtils.md5Encrypt("queryCustomerComments" 
				+ custId.trim() + page + limit);
		WebPage<CustomerAgentStarCommentsEntity> retList 
				= readAgentCustomerCommentsFromRedisByKey(redisKey);
		if(retList.getResult() == null) {
			PageHelper.startPage(page,limit);
			Page<CustomerAgentStarCommentsEntity> pageObj = 
					mCustomerStarCommentsMapper.queryCustomerCommentsDataList(custId);
			if(pageObj.size() > 0) {
				retList = new WebPage<CustomerAgentStarCommentsEntity>(pageObj);
				mICacheService.saveCache(redisKey,retList,WebGlobal.OTHER_EXPTIME);
			}
		}
		return (WebPage<CustomerAgentStarCommentsEntity>) retList;
	}
	/**
	 * 查询客户对房源的评价
	 * @Title: queryCustomerHouseComments
	 * @author: zuoyuntao  
	 * @Description:查询客户对房源的评价
	 * @param custId 客户ID
	 * @param page 每页显示最小条数
	 * @param limit 每页显示最大条数
	 * @return      
	 * Page<CustomerHouseStarCommentsEntity> JSON 格式为：
	 */
	@Override
	public WebPage<CustomerSeeHouseCommentsEntity> queryCustomerHouseComments(
			String custId,int page,int limit) {
		String redisKey = Md5EncryptUtils.md5Encrypt("queryCustomerHouseComments" 
				+ custId.trim() + page + limit);
		WebPage<CustomerSeeHouseCommentsEntity> retList 
				= readHouseCustomerCommentsFromRedisByKey(redisKey);
		if(retList.getResult() == null) {
			PageHelper.startPage(page,limit);
			Page<CustomerSeeHouseCommentsEntity> pageObj = 
					mCustomerStarCommentsMapper.queryCustomerHouseCommentsDataList(custId);
			if(pageObj.size() > 0) {
				retList = new WebPage<>(pageObj);
			}
			mICacheService.saveCache(redisKey,retList,WebGlobal.OTHER_EXPTIME);
		}
		return (WebPage<CustomerSeeHouseCommentsEntity>) retList;
	}
	/**
	 * <p>Title: saveCustomerComments</p>   
	 * <p>Description:保存用户评价 </p>   
	 * @param commentEntity 要保存的评价数据对象 
	 * @param imgVerifyCode 图片验证码
	 * @param request 页面请求对象
	 * @param response 页面响应对象
	 * @see com.bashiju.www.service.user.ICustomerStarCommentsService#saveCustomerComments(com.bashiju.www.pojo.service.out.usercentral.CustomerAgentStarCommentsEntity)
	 */
	@Override
	public void saveCustomerComments(CustomerAgentStarCommentsEntity commentEntity) {
		if(StringUtil.isEmpty(String.valueOf(commentEntity.getAgentId()))) {
			throw new BusinessException("传入经纪人ID为空");
		}
		Map<String,Object> userMap = mCustomerStarCommentsMapper.queryUserInfoByAgentId(commentEntity.getAgentId());
		if(null == userMap) {
			throw new BusinessException("未查询到经纪人信息");
		}
		String now = WebCommonTools.formatDate("yyyy-MM-dd HH:mm:ss");
		commentEntity.setUserId(Integer.parseInt(String.valueOf(userMap.get("id"))));
		commentEntity.setOperator(String.valueOf(userMap.get("operator"))); 
		commentEntity.setOperatorId(String.valueOf(userMap.get("operatorId"))); 
		commentEntity.setPermissionArea(String.valueOf(userMap.get("permissionArea"))); 
		commentEntity.setIsValid(1);
		commentEntity.setAddTime(now);
		commentEntity.setUpdateTime(now);
		
		List<CustomerStarRatingHotLabelEntity> hotList = commentEntity.getHotRatingLabelList();
		List<CustomerStarRatingServiceComment> serviceRateList = commentEntity.getServiceRatingLabelList();
		
		long id = mCustomerStarCommentsMapper.saveCustomerComments(commentEntity);
		
		List<Map<String,Object>> hotResultList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> serviceResultList = new ArrayList<Map<String,Object>>();
		buildSaveLabelsData(hotList, serviceRateList,commentEntity, id,hotResultList,serviceResultList);
		// 批量插入标签
		mCustomerStarCommentsMapper.batchAddLabels(hotResultList, serviceResultList);
		// 更新经纪人最后一次评价
		mCustomerStarCommentsMapper.updateAgentLastTimesComments(commentEntity.getAgentId()
				,commentEntity.getContent(), now);
	}
	/**
	 * 组装要保存的标签数据信息
	 * @Title: buildSaveLabelsData
	 * @author: zuoyuntao  
	 * @Description:组装要保存的标签数据信息
	 * @param hotList 热门标签集合
	 * @param serviceRateList 服务点评集合
	 * @param commentEntity 评价信息
	 * @param id      
	 * void
	 */
	private void buildSaveLabelsData(List<CustomerStarRatingHotLabelEntity> hotList
			,List<CustomerStarRatingServiceComment> serviceRateList
			,CustomerAgentStarCommentsEntity commentEntity,long id
			,List<Map<String,Object>> hotResultList,List<Map<String,Object>> serviceResultList) {
		String nowDate = WebCommonTools.formatDate("");
		Map<String,Object> oneMap = new HashMap<String,Object>();
		oneMap.put("starRatingId", id);
		oneMap.put("addTime", nowDate);
		oneMap.put("agentId", commentEntity.getUserId());
		oneMap.put("isValid", 1);
		oneMap.put("operator", commentEntity.getOperator());
		oneMap.put("operatorId", commentEntity.getOperatorId());
		oneMap.put("permissionArea", commentEntity.getPermissionArea());
		oneMap.put("updateTime", nowDate);
		if(hotList.size() > 0) {
			for(CustomerStarRatingHotLabelEntity temp : hotList) {
				Map<String,Object> tempMap = new HashMap<String,Object>();
				tempMap.putAll(oneMap);
				tempMap.put("labeld", temp.getLabeld());
				tempMap.put("labelId", temp.getLabelId());
				hotResultList.add(tempMap);
			}
		}
		if(serviceRateList.size() > 0) {
			for(CustomerStarRatingServiceComment temp : serviceRateList) {
				Map<String,Object> tempMap = new HashMap<String,Object>();
				tempMap.putAll(oneMap);
				tempMap.put("labeld", temp.getLabeld());
				tempMap.put("labelId", temp.getLabelId());
				serviceResultList.add(tempMap);
			}
		}
	}
	
	/**
	 * <p>Title: saveCustomerComments</p>   
	 * <p>Description:保存用户对房源的评价 </p>   
	 * @param commentEntity 要保存的评价数据对象 
	 * @see com.bashiju.www.service.user.ICustomerStarCommentsService#saveCustomerComments(com.bashiju.www.pojo.service.out.usercentral.CustomerAgentStarCommentsEntity)
	 */
	@Override
	public void saveCustomerHouseComments(String visterEvaluate,String id) {
		String now = WebCommonTools.formatDate("yyyy-MM-dd HH:mm:ss");
		mCustomerStarCommentsMapper.updateCustomerSeeHouseComments(visterEvaluate,id,now);
	}
	
	/**
	 * 根据Key从Redis读取数据
	 * @Title: readCustomerCommentsFromRedisByKey
	 * @author: zuoyuntao  
	 * @Description:根据Key从Redis读取数据
	 * @param key
	 * @return      
	 * Page<CustomerAgentStarCommentsEntity>
	 */
	@SuppressWarnings("unchecked")
	private WebPage<CustomerAgentStarCommentsEntity> readAgentCustomerCommentsFromRedisByKey(String key){
		WebPage<CustomerAgentStarCommentsEntity> pageList = null;
		String retStr = mICacheService.getCache(key);
		if(StringUtil.isEmpty(retStr)) {
			return new WebPage<CustomerAgentStarCommentsEntity>();
		}
		try {
			pageList = MAPPER.readValue(retStr, WebPage.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pageList;
	}
	
	/**
	 * 根据Key从Redis读取数据
	 * @Title: readCustomerCommentsFromRedisByKey
	 * @author: zuoyuntao  
	 * @Description:根据Key从Redis读取数据
	 * @param key
	 * @return      
	 * Page<CustomerAgentStarCommentsEntity>
	 */
	@SuppressWarnings("unchecked")
	private WebPage<CustomerSeeHouseCommentsEntity> readHouseCustomerCommentsFromRedisByKey(String key){
		WebPage<CustomerSeeHouseCommentsEntity> pageList = null;
		String retStr = mICacheService.getCache(key);
		if(StringUtil.isEmpty(retStr)) {
			return new WebPage<CustomerSeeHouseCommentsEntity>();
		}
		try {
			pageList = MAPPER.readValue(retStr, WebPage.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pageList;
	}
	/**
	 * <p>Title: queryAgentCommentLabels</p>   
	 * <p>Description:读取经纪人评价时的经纪人标签配置信息 </p>   
	 * @return   
	 * @see com.bashiju.www.service.user.ICustomerStarCommentsService#queryAgentCommentLabels()
	 */
	@Override
	public Map<String,Object> queryAgentCommentLabels() {
		List<LabelResult> hotLabels = mICommService.queryLabel(LabelTypeEnum.RP);
		List<LabelResult> serviceCommentLabels = mICommService.queryLabel(LabelTypeEnum.DP);
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("hotLabels", hotLabels);
		retMap.put("serviceCommentLabels", serviceCommentLabels);
		return retMap;
	}
	
	
}
