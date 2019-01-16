/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  EntrustAgentAcceptanceDealIImpl.java   
 * @Package com.bashiju.manage.service.impl      
 * @author: zuoyuntao     
 * @date:   2018年8月10日 上午9:24:13   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.housing.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.MenuEnum;
import com.bashiju.housing.mapper.AgentHouseEntrustAcceptanceDealMapper;
import com.bashiju.housing.service.IAgentHouseEntrustAcceptanceDealService;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 经纪人委托受理处理服务
 * @ClassName:EntrustAgentAcceptanceDealIImpl
 * @Description:经纪人委托受理处理服务
 * @author:zuoyuntao
 * @date:2018年8月10日 上午9:24:13
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@Service
@SystemServiceLog(sourceType="房源委托受理")
public class AgentHouseEntrustAcceptanceDealServiceImpl extends CommonSqlServie implements IAgentHouseEntrustAcceptanceDealService{
	/**
	 * 权限管理接口
	 */
	@Autowired
	private DataAuthHelper mDataAuthHelper;
	/**
	 * 经纪人受理持久层接口对象
	 */
	@Autowired
	private AgentHouseEntrustAcceptanceDealMapper mAgentHouseEntrustAcceptanceDealMapper;
	/**
	 * <p>Title: queryAgentAcceptanceList</p>   
	 * <p>Description: 查询经纪人房源委托受理数据信息</p>   
	 * @param paraMap 查询条件参数对象，包含：status 状态（0--未处理1--已处理2--已失效）
	 * @param page 每页最小条数
	 * @param limit 每页最大条数
	 * @return   
	 * @see com.bashiju.manage.service.IAgentHouseEntrustAcceptanceDealService#queryAgentAcceptanceList(java.util.Map, int, int, java.lang.String)
	 */
	@Override
	@SystemServiceLog(operationType="查询受理")
	public Page<Map<String, Object>> queryAgentAcceptanceList(Map<String, Object> paraMap, int page, int limit) {
		PageHelper.startPage(page,limit);
		mDataAuthHelper.auth(MenuEnum.MENU_499.getCode()
				, String.valueOf(UserThreadLocal.get().get("id")));
		Page<Map<String, Object>> retList = mAgentHouseEntrustAcceptanceDealMapper.queryAgentAcceptanceList(paraMap);
		ExecutionResult.descFormat(MenuEnum.MENU_499.getCode(), "查询经纪人受理房源委托信息");
		return retList;
	}
	/**
	 * <p>Title: queryCustomerHouseEntrust</p>   
	 * <p>Description: 查询客户房源委托信息</p>   
	 * @param paraMap 查询条件参数
	 * @param page 每页最小条数
	 * @param limit 每页最大条数
	 * @return   
	 * @see com.bashiju.manage.service.IAgentHouseEntrustAcceptanceDealService#queryCustomerHouseEntrust(java.util.Map, int, int)
	 */
	@Override
	@SystemServiceLog(operationType="查询房源委托")
	public Page<Map<String, Object>> queryCustomerHouseEntrust(Map<String, Object> paraMap, int page, int limit) {
		PageHelper.startPage(page,limit);
		mDataAuthHelper.auth(MenuEnum.MENU_499.getCode()
				, String.valueOf(UserThreadLocal.get().get("id")));
		Page<Map<String, Object>> retList = mAgentHouseEntrustAcceptanceDealMapper.queryCustomerHouseEntrust(paraMap);
		ExecutionResult.descFormat(MenuEnum.MENU_499.getCode(), "查询房源委托信息");
		return retList;
	}
	/**
	 * <p>Title: saveAgentAcceptanceDealResult</p>   
	 * <p>Description:保存委托信息 </p>   
	 * @param paraMap 参数对象
	 * @see com.bashiju.manage.service.IAgentHouseEntrustAcceptanceDealService#saveAgentAcceptanceDealResult(java.util.Map)
	 */
	@Override
	@SystemServiceLog(operationType="处理房源委托")
	public void saveAgentAcceptanceDealResult(Map<String,Object> paraMap) {
		paraMap.put("handelTime", new Date());
		//status 0--未处理 1--已处理 2--已失效
		paraMap.put("status", "1");
		paraMap.put("handelTime", new Date());
		//status 0--未处理 1--已处理 2--已失效
		paraMap.put("status", "1");
		
		Map<String,Object> updateMap = new HashMap<String,Object>();
		updateMap.put("handleFlag", "1");
		updateMap.put("id", paraMap.get("resourceId"));
		
		paraMap.remove("resourceId");
		
		commonOperationDatabase(paraMap, "sys_agentacceptance", "id", false);
		ExecutionResult.descFormat(String.valueOf(paraMap.get("id")), "更新经纪人受理状态");
		commonOperationDatabase(updateMap, "cust_houseentrust", "id", false);
		ExecutionResult.descFormat(String.valueOf(paraMap.get("resourceId")), "更新房屋委托状态");
	}
	/**
	 * <p>Title: refuseAgentAcceptanceEntrustById</p>   
	 * <p>Description: </p>   
	 * @param id 房源委托ID
	 * @param refuseReason 处理结果   
	 * @see com.bashiju.manage.service.IAgentHouseEntrustAcceptanceDealService#refuseAgentAcceptanceEntrustById(java.lang.String, java.lang.String)
	 */
	@Override
	@SystemServiceLog(operationType="拒绝委托")
	public void refuseAgentAcceptanceEntrustById(String id, String refuseReason) {
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("id", id);
		paraMap.put("handelResult", refuseReason);
		//0--未处理 1--已处理 2--已失效
		paraMap.put("status", "2");
		paraMap.put("isValid", 0);
		commonOperationDatabase(paraMap, "sys_agentacceptance", "id", false);
		ExecutionResult.descFormat(id, "经纪人拒绝房源委托");
	}

}
