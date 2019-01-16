/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerMyAgentServiceApiImpl.java   
 * @Package com.bashiju.www.service.api.impl.usercentral      
 * @author: zuoyuntao     
 * @date:   2018年8月15日 下午12:05:48   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.service.api.impl.usercentral;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.wapi.usercentral.ICustomerMyAgentServiceApi;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.agent.Agent;
import com.bashiju.www.pojo.service.out.usercentral.CustomerAgentStarCommentsEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerAgentStarCommentsParamsEntity;
import com.bashiju.www.service.user.ICustomerPersonalCentralService;
import com.bashiju.www.service.user.ICustomerStarCommentsService;

/**
 * 我的经纪人页面服务
 * @ClassName:CustomerMyAgentServiceApiImpl
 * @Description:我的经纪人页面服务
 * @author:zuoyuntao
 * @date:2018年8月15日 下午12:05:48
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class CustomerMyAgentServiceApiImpl extends CustomerPersonalCentralBaseServiceApi
		implements ICustomerMyAgentServiceApi {
	/**
	 * 个人中心服务接口
	 */
	@Autowired
	private ICustomerPersonalCentralService mICustomerPersonalCentralService;
	/**
	 * 经纪人评价、房源评价服务接口
	 */
	@Autowired
	private ICustomerStarCommentsService mICustomerStarCommentsService;
	/**   
	 * <p>Title: queryMyAgentList</p>   
	 * <p>Description: </p>   
	 * @param key 用户登陆标记
	 * @param agentType 经纪人类型（0：我聊过的经纪人 1：带看过的经纪人） 
	 * @param page 当前页
	 * @param limit 每页最大条数  
	 * @see com.bashiju.wapi.usercentral.ICustomerMyAgentServiceApi#queryMyAgentList(java.lang.String)   
	 */
	@Override
	public WebPage<Agent> queryMyAgentList(String key,String agentType,int page,int limit) {
		String custId = getUserIdByRedisKey(key);
		return mICustomerPersonalCentralService.queryMyselfAgentList(custId, agentType,page,limit);
	}
	/**
	 * 保存经纪人评价数据
	 * @Title: agentComment
	 * @author: zuoyuntao  
	 * @Description:保存经纪人评价数据
	 * @param key 客户登陆KEY
	 * @param commentEntity 评价实体对象
	 * void
	 */
	@Override
	public void agentComment(String key,CustomerAgentStarCommentsParamsEntity commentEntity) {
		String custId = getUserIdByRedisKey(key);
		CustomerAgentStarCommentsEntity saveEntity = new CustomerAgentStarCommentsEntity();
		saveEntity.setCustId(Integer.parseInt(custId));
		saveEntity.setAgentId(commentEntity.getAgentId());
		saveEntity.setStar(commentEntity.getStar());
		saveEntity.setContent(commentEntity.getContent());
		saveEntity.setCustName(commentEntity.getCustName());
		saveEntity.setServiceRatingLabelList(commentEntity.getServiceRatingLabelList());
		saveEntity.setHotRatingLabelList(commentEntity.getHotRatingLabelList());
		mICustomerStarCommentsService.saveCustomerComments(saveEntity);
	}
	/**
	 * <p>Title: queryAgentCommentLabels</p>   
	 * <p>Description:读取经纪人评价标签 </p>   
	 * @return   
	 * @see com.bashiju.wapi.usercentral.ICustomerMyAgentServiceApi#queryAgentCommentLabels()
	 */
	@Override
	public Map<String, Object> queryAgentCommentLabels() {
		return mICustomerStarCommentsService.queryAgentCommentLabels();
	}

}
