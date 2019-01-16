/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  AgentHouseEntrustAcceptanceDealController.java   
 * @Package com.bashiju.manage.controller      
 * @author: zuoyuntao     
 * @date:   2018年8月10日 上午10:05:56   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.housing.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.bashiju.enums.HouseStatusEnum;
import com.bashiju.enums.HouseUsesEnum;
import com.bashiju.housing.service.FormService;
import com.bashiju.housing.service.IAgentHouseEntrustAcceptanceDealService;
import com.bashiju.housing.service.IErHousingService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.util.StringUtil;

/**
 * 经纪人房源委托受理控制器
 * @ClassName:AgentHouseEntrustAcceptanceDealController
 * @Description:经纪人房源委托受理控制器
 * @author:zuoyuntao
 * @date:2018年8月10日 上午10:05:56
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@Controller
@RequestMapping(value="agentAcceptance")
public class AgentHouseEntrustAcceptanceDealController extends BaseController{
	/**
	 * 经纪人受理接口
	 */
	@Autowired
	private IAgentHouseEntrustAcceptanceDealService mIAgentHouseEntrustAcceptanceDealService;
	/**
	 * 二手房服务接口
	 */
	@Autowired
	private IErHousingService erHousingService;
	/**
	 * 动态表单服务接口
	 */
	@Autowired
	private FormService formService;
//	/**
//	 * 构造树接口
//	 */
//	@Autowired
//	private ICommunityService communityService;
	/**
	 * 获取主页面视图 
	 * @Title: getMainPageModel
	 * @author: zuoyuntao  
	 * @Description:获取主页面视图
	 * @param request 页面请求对象
	 * @param response 页面响应对象
	 * @return      
	 * ModelAndView
	 */
	@RequestMapping(value="getMainPageModel")
	public ModelAndView getMainPageModel(HttpServletRequest request
			,HttpServletResponse response) {
		ModelAndView model = this.getModelAndView(request, response, "agentAcceptanceDeal/agent_acceptance_deal");
		
		model.addObject("houseList",HouseUsesEnum.enumList);
		model.addObject("houseStatusList",HouseStatusEnum.enumList);
		model.addObject("communitList", JSON.toJSONString(erHousingService.queryCommunity()));
		model.addObject("departmentList", JSON.toJSONString(erHousingService.queryDept()));
		model.addObject("dynamicColumnList",formService.queryCustomField("house"));
		List<Map<String,Object>> labelList = erHousingService.queryLabel();
		model.addObject("labelList", labelList);
		
		return model;
	}
	/**
	 * 查询经纪人受理数据信息
	 * @Title: queryAgentAcceptanceList
	 * @author: zuoyuntao  
	 * @Description:查询经纪人受理数据信息
	 * @param clienteleName 客户称呼
	 * @param status 数据状态
	 * @param regionId 片区ID
	 * @param communityId 小区ID
	 * @param page 每页显示最小条数
	 * @param limit 每页显示最大条数
	 * @return      
	 * Object JSON 格式为：
	 */
	@RequestMapping(value="queryAgentAcceptanceList")
	@ResponseBody
	public Object queryAgentAcceptanceList(String clienteleName,String status,String regionId
			,String communityId,int page,int limit) {
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("clienteleName", clienteleName);
		paraMap.put("status", status);
		paraMap.put("regionId", regionId);
		paraMap.put("communityId", communityId);
		Page<Map<String,Object>> retPage = mIAgentHouseEntrustAcceptanceDealService
				.queryAgentAcceptanceList(paraMap, page, limit);
		return this.getPageResult(retPage);
	}
	/**
	 * 查询客户房源委托数据信息
	 * @Title: queryCustomerHouseEntrust
	 * @author: zuoyuntao  
	 * @Description:查询客户房源委托数据信息
	 * @param jsonData 页面查询条件参数
	 * @param page 每页显示最小条数
	 * @param limit 每页显示最大条数
	 * @return      
	 * Object
	 */
	@RequestMapping(value="queryCustomerHouseEntrust")
	@ResponseBody
	public Object queryCustomerHouseEntrust(String jsonData,int page,int limit) {
		Map<String,Object> paraMap = new HashMap<String,Object>();
		if(StringUtil.isNotEmpty(jsonData)) {
			paraMap = JSON.parseObject(jsonData);
		}
		Page<Map<String,Object>> retPage = mIAgentHouseEntrustAcceptanceDealService
				.queryCustomerHouseEntrust(paraMap, page, limit);
		return this.getPageResult(retPage);
	}
	/**
	 * 保存经纪人受理信息
	 * @Title: saveAgentAcceptanceDealResult
	 * @author: zuoyuntao  
	 * @Description:保存经纪人受理信息 
	 * @param jsonData 页面传入数据对象
	 * @param id 受理ID
	 * @param houseId 房源ID
	 * @param resourceId 房源委托ID
	 * @return      
	 * BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveAgentAcceptanceDealResult")
	@ResponseBody
	public BashijuResult saveAgentAcceptanceDealResult(String jsonData,String houseId
			,String id,String resourceId) {
		if(StringUtil.isEmpty(jsonData)) {
			throw new BusinessException("页面传入数据为空");
		}
		if(StringUtil.isEmpty(id)) {
			throw new BusinessException("经纪人受理ID为空!");
		}
		Map<String,Object> paraMap = (Map<String,Object>) JSON.parse(jsonData);
		paraMap.put("id", id);
		paraMap.put("resourceId", resourceId);
		paraMap.put("resultId", houseId);
		paraMap.put("handelResult", "已录入房源信息");
		
		mIAgentHouseEntrustAcceptanceDealService.saveAgentAcceptanceDealResult(paraMap);
		return BashijuResult.ok();
	}
	/**
	 * 拒绝房源委托数据
	 * @Title: refuseAgentAcceptanceEntrustById
	 * @author: zuoyuntao  
	 * @Description:拒绝房源委托数据   
	 * @param id 房源委托ID
	 * @param refuseReason 拒绝原因
	 * @return      
	 * BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="refuseAgentAcceptanceEntrustById")
	@ResponseBody
	public BashijuResult refuseAgentAcceptanceEntrustById(String jsonData) {
		if(StringUtil.isEmpty(jsonData)) {
			throw new BusinessException("页面传入数据为空");
		}
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap = (Map<String, Object>) JSON.parse(jsonData);
		mIAgentHouseEntrustAcceptanceDealService.refuseAgentAcceptanceEntrustById(
				String.valueOf(paraMap.get("id")), String.valueOf(paraMap.get("refuseReason")));
		return BashijuResult.ok();
	}
}
