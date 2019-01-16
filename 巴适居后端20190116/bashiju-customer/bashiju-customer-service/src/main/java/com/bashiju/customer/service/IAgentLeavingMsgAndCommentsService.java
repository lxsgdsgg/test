/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  IAgentLeavingMsgAndCommentsService.java   
 * @Package com.bashiju.manage.service      
 * @author: zuoyuntao     
 * @date:   2018年7月21日 上午10:26:29   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.customer.service;

import java.util.Map;

import com.github.pagehelper.Page;

/**
 * 经纪人客户评价留言管理接口
 * @ClassName:IAgentLeavingMsgAndCommentsService
 * @Description:经纪人客户评价留言管理接口
 * @author:zuoyuntao
 * @date:2018年7月21日 上午10:26:29
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface IAgentLeavingMsgAndCommentsService {
	/**
	 * 获取倒所有经纪人信息--带分页
	 * @Title: queryAllAgentInfoPages
	 * @author: zuoyuntao  
	 * @Description:获取倒所有经纪人信息--带分页
	 * @param paraMap 参数对象
	 * @param page 每页显示最少条数
	 * @param limit 每页显示最大条数
	 * @return      
	 * Page<Map<String,Object>> JSON 格式为：
	 */
	public Page<Map<String,Object>> queryAllAgentInfoPages(
			Map<String,Object> paraMap,int page,int limit );
	/**
	 * 获取到所有客户留言数据信息--带分页
	 * @Title: queryAllCustomerLeavingMsg
	 * @author: zuoyuntao  
	 * @Description:获取到所有客户留言数据信息--带分页   
	 * @param paraMap 参数对象
	 * @param page 每页显示最少条数
	 * @param limit 每页显示最大条数
	 * @return      
	 * Page<Map<String,Object>> JSON 格式为：
	 */
	public Page<Map<String,Object>> queryAllCustomerLeavingMsg(
			Map<String,Object> paraMap,int page,int limit );
	/**
	 * 获取到所有客户评价数据信息--带分页
	 * @Title: queryAllCustomerComments
	 * @author: zuoyuntao  
	 * @Description:获取到所有客户评价数据信息--带分页   
	 * @param paraMap 参数对象
	 * @param page 每页显示最少条数
	 * @param limit 每页显示最大条数
	 * @return      
	 * Page<Map<String,Object>> JSON 格式为：
	 */
	public Page<Map<String,Object>> queryAllCustomerComments(
			Map<String,Object> paraMap,int page,int limit );
	/**
	 * 审核客户评价
	 * @Title: checkCustmerComments
	 * @author: zuoyuntao  
	 * @Description:审核客户评价   
	 * @param paraMap  参数对象    
	 * void JSON 格式为：
	 */
	public void checkCustmerComments(Map<String,Object> paraMap);
	/**
	 * 删除客户评价 （根据评价ID删除）
	 * @Title: deleteCustmerComments
	 * @author: zuoyuntao  
	 * @Description:删除客户评价 （根据评价ID删除）   
	 * @param paraMap 参数对象
	 * void
	 */
	public void deleteCustmerComments(Map<String,Object> paraMap);
	/**
	 * 删除客户留言 （根据留言ID删除）
	 * @Title: deleteCustmerLeavingMsg
	 * @author: zuoyuntao  
	 * @Description:删除客户留言 （根据留言ID删除）   
	 * @param paraMap 参数对象
	 * void
	 */
	public void deleteCustmerLeavingMsg(Map<String,Object> paraMap);
	/**
	 * 标记客户留言/评价为已读 
	 * @Title: markInfoReaded
	 * @author: zuoyuntao  
	 * @Description:标记客户留言/评价为已读   
	 * @param id 评价/留言ID
	 * @param type 标记类型：1 客户评价 2 客户留言
	 * void JSON 格式为：
	 */
	public void markInfoReaded(String id,String type);
}
