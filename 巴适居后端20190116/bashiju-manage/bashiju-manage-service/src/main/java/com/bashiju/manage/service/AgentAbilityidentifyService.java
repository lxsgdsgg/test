/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  AgentAbilityidentifyService.java   
 * @Package com.bashiju.manage.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年8月7日 上午11:23:35   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**   
 * @ClassName:  AgentAbilityidentifyService   
 * @Description:经纪人能力认定接口
 * @author: wangpeng
 * @date:   2018年8月7日 上午11:23:35   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface AgentAbilityidentifyService {
	
	/**
	 * @Title: queryAgentAbilityidentify   
	 * @Description: 经纪人能力认定
	 * @param page 当前页数
	 * @param limit 每页总条数
	 * @return      
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> queryAgentAbilityidentify(String userName,String name,int page,int limit  );	
	/**
	 * @Title: saveOrUpdateAgentAbilityidentify   
	 * @Description: 新增经纪人能力认定 
	 * @param paramMap 保存参数
	 * @return: boolean
	 */
	public boolean saveOrUpdateAgentAbilityidentify(Map<String,Object> paramMap);
	
	
	/**
	 * @Title: queryAgentNameUserIdAndAgentId   
	 * @Description:查询经纪人名称用户id和经纪人编号   
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String,Object>> queryAgentNameUserIdAndAgentId();
	/**
	 * @Title: delAgentAbilityidentify   
	 * @Description: 逻辑删除经纪人能力认定
	 * @param id
	 * @return: boolean
	 */
	public boolean delAgentAbilityidentify(String id);
	
	/**
	 * @Title: queryAgentAbilityidentifyById   
	 * @Description: 条件查询经纪人能力认定
	 * @param id id
	 * @return: Map<String,Object>
	 */
	Map<String,Object> queryAgentAbilityidentifyById(String id);
}
