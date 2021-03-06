/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ICustomerMyAgentServiceApi.java   
 * @Package com.bashiju.wapi.usercentral      
 * @author: zuoyuntao     
 * @date:   2018年8月15日 下午12:04:46   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.wapi.usercentral;

import java.util.Map;

import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.agent.Agent;
import com.bashiju.www.pojo.service.out.usercentral.CustomerAgentStarCommentsParamsEntity;

/**
 * 我的经纪人页面服务接口
 * @ClassName:ICustomerMyAgentServiceApi
 * @Description:我的经纪人页面服务接口
 * @author:zuoyuntao
 * @date:2018年8月15日 下午12:04:46
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface ICustomerMyAgentServiceApi {
	/**
	 * 查询我的经纪人
	 * @Title: queryMyAgentList
	 * @author: zuoyuntao  
	 * @Description:查询我的经纪人
	 * @param key 用户登录标记
	 * @param agentType 经纪人类型（0：我聊过的经纪人 1：带看过的经纪人） 
	 * @param page 当前页
	 * @param limit 每页最大条数
	 * @return      
	 * WebPage<Agent>
	 */
	public WebPage<Agent> queryMyAgentList(String key,String agentType,int page,int limit);
	/**
	 * 保存经纪人评价数据
	 * @Title: agentComment
	 * @author: zuoyuntao  
	 * @Description:保存经纪人评价数据
	 * @param key 客户登陆KEY
	 * @param commentEntity 评价实体对象
	 * void
	 */
	public void agentComment(String key,CustomerAgentStarCommentsParamsEntity commentEntity);
	
	/**
	 * 读取经纪人评价时的经纪人标签配置信息
	 * @Title: queryAgentCommentLabels
	 * @author: zuoyuntao  
	 * @Description:读取经纪人评价时的经纪人标签配置信息
	 * @return      
	 * Map<String,Object> map({hotLabels=List<LabelResult>},serviceCommentLabels=List<LabelResult>)
	 */
	public Map<String,Object> queryAgentCommentLabels();
}
