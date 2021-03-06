/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  IEntrustAgentAcceptanceDealService.java   
 * @Package com.bashiju.manage.service      
 * @author: zuoyuntao     
 * @date:   2018年8月10日 上午9:25:18   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.housing.service;

import java.util.Map;

import com.github.pagehelper.Page;

/**
 * 经纪人委托受理处理服务接口
 * @ClassName:IEntrustAgentAcceptanceDealService
 * @Description:经纪人委托受理处理服务接口
 * @author:zuoyuntao
 * @date:2018年8月10日 上午9:25:18
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface IAgentHouseEntrustAcceptanceDealService {
	/**
	 * 查询经纪人委托受理数据信息
	 * @Title: queryAgentAcceptanceList
	 * @author: zuoyuntao  
	 * @Description:查询经纪人委托受理数据信息
	 * @param paraMap 查询条件参数对象，包含：status 状态（0--未处理1--已处理2--已失效）
	 * @param page 每页显示最少条数
	 * @param limit 每页显示最大条数
	 * @return      
	 * List<Map<String,Object>> 
	 */
	public Page<Map<String,Object>> queryAgentAcceptanceList(Map<String,Object> paraMap
			,int page,int limit);
	/**
	 * 查询客户委托房源信息
	 * @Title: queryCustomerHouseEntrust
	 * @author: zuoyuntao  
	 * @Description:查询客户委托房源信息  
	 * @param paraMap 查询参数对象
	 * @param page 每页显示最小条数
	 * @param limit 每页显示最大条数
	 * @return      
	 * List<Map<String,Object>> JSON 格式为：
	 */
	public Page<Map<String,Object>> queryCustomerHouseEntrust(Map<String,Object> paraMap,int page
			,int limit);
	/**
	 * 保存经纪人受理委托处理结果
	 * @Title: saveAgentAcceptanceDealResult
	 * @author: zuoyuntao  
	 * @Description:保存经纪人受理委托处理结果
	 * @param paraMap 参数对象
	 * void 
	 */
	public void saveAgentAcceptanceDealResult(Map<String,Object> paraMap);
	/**
	 * 根据委托ID删除客户委托数据信息
	 * @Title: deleteAgentAcceptanceEntrustById
	 * @author: zuoyuntao  
	 * @Description:根据委托ID删除客户委托数据信息
	 * @param id 委托ID
	 * @param refuseReason 拒绝原因
	 * void
	 */
	public void refuseAgentAcceptanceEntrustById(String id,String refuseReason);
}
