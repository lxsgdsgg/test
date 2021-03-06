/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  EmployeeWagesConfigurateController.java   
 * @Package com.bashiju.fin.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年9月11日 上午10:43:30   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.fin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bashiju.fin.service.CommissionsConfigService;
import com.bashiju.fin.service.EmployeeWagesConfigurateService;
import com.bashiju.fin.service.SsfConfigurateService;
import com.bashiju.fin.service.WelfareConfigurateService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;

/**   
 * @ClassName:  EmployeeWagesConfigurateController   
 * @Description:员工薪资配置管理  
 * @author: yangz
 * @date:   2018年9月11日 上午10:43:30   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="wagesConfig")
public class EmployeeWagesConfigurateController extends BaseController {

	@Autowired
	private EmployeeWagesConfigurateService employeeWagesConfigurateService;
	@Autowired
	private WelfareConfigurateService welfareConfigurateService;
	@Autowired
	private SsfConfigurateService ssfConfigurateService;
	@Autowired
	CommissionsConfigService commissionsConfigService;
	/**
	 * 获取员工薪资配置信息
	 * @Description: 获取员工薪资配置信息  
	 * @param request
	 * @param response
	 * @return: Object
	 */
	@RequestMapping(value="employeeWagesConfigurate")
	@ResponseBody
	public Object employeeWagesConfigurate(HttpServletRequest request,HttpServletResponse response) {
		Long userId = null;
		if(request.getParameter("userId")!=null && !"".equals(request.getParameter("userId")))
			userId = Long.parseLong(request.getParameter("userId").toString());
		String deptId = request.getParameter("deptId");
		String welfareId = request.getParameter("welfareId");
		String ssfConfigId = request.getParameter("ssfConfigId");
		String agentComConfigId = request.getParameter("agentComConfigId");
		String financeComConfigId = request.getParameter("financeComConfigId");
		int pageNum = 1;
		int pageSize = 20;
		String num = request.getParameter("page");
		String size = request.getParameter("limit");
		if(!StringUtils.isEmpty(num))
			pageNum = Integer.parseInt(num);
		if(!StringUtils.isEmpty(size))
			pageSize = Integer.parseInt(size);
		Page<Map<String,Object>> page = employeeWagesConfigurateService
				.queryEmployeeWagesConfigurates(userId,deptId, welfareId, ssfConfigId, agentComConfigId,financeComConfigId, pageNum, pageSize);
		Map<String,Object> map = getPageResult(page);
		return JSONObject.toJSON(map);
	}
	
	/**
	 * 获取各种配置方案
	 * @Description: 获取各种配置方案（福利方案、社保方案、中介类提成方案、金融类提成方案）
	 * @param 
	 * @param 
	 * @return: Map<String,Object> 
	 */
	@RequestMapping(value="getSomeConfig")
	@ResponseBody
	public Map<String,Object> getSomeConfig(){
		List<Map<String, Object>> welfareConfig  = welfareConfigurateService.queryWelfareCOnfigurateAll(null);
		List<Map<String, Object>> ssfConfig  = ssfConfigurateService.querySsfConfigurateAll(null);
		List<Map<String, Object>> commissionsConfig  = commissionsConfigService.queryCommissionsConfig(null);
		Map<String,Object> res = new HashMap<>();
		res.put("welfareConfig", welfareConfig);
		res.put("ssfConfig", ssfConfig);
		res.put("commissionsConfig", commissionsConfig);
		return res;
	}
	
	/**
	 * 保存员工薪资信息
	 * @Description: 保存员工薪资信息   
	 * @param request
	 * @param response
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveOrUpdateEmployeeWagesConfigurate")
	@ResponseBody
	public BashijuResult saveOrUpdateEmployeeWagesConfigurate(HttpServletRequest request,HttpServletResponse response) {
		String jsonData = request.getParameter("jsonData");
//		String jsonData = "{\"financeComConfigId\":\"1\",\"ssfConfigName\":\"社保方案一\",\"welfareId\":\"1\",\"financeComConfigName\":\"金融类提成方案一\",\"ssfConfigId\":\"1\",\"basePay\":\"100\",\"agentComConfigName\":\"中介类提成方案一\",\"agentComConfigId\":\"1\",\"welfareName\":\"福利方案一\"}\r\n" ;
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("没有要保存的数据");
		Map<String,Object> map = (Map<String,Object>) JSONObject.parse(jsonData);
		map.put("basePay", Integer.parseInt(map.get("basePay").toString())*100);
		if(!map.containsKey("id") || StringUtils.isEmpty(map.get("id").toString())) {
			/*Map<String,Object> user = UserThreadLocal.get();
			map.put("companyId", user.get("companyId"));
			map.put("companyName", user.get("comName"));
			map.put("permissionArea", user.get("deptId"));
			map.put("operatorId", user.get("id"));
			map.put("addTime", new Date());
			map.put("updateTime",null);
			map.put("operator",user.get("realName"));*/
			
		}
		
		boolean result = employeeWagesConfigurateService.saveOrUpdateEmployeeWagesConfigurate(map);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	
	/**
	 * 删除员工薪资配置信息
	 * @Description: 删除员工薪资配置信息   
	 * @param request
	 * @param response
	 * @return: BashijuResult
	 */
	@RequestMapping(value="delEmployeeWagesConfigurate")
	@ResponseBody
	public BashijuResult delEmployeeWagesConfigurate(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		if(StringUtils.isEmpty(id))
			throw new BusinessException("请选择要删除的员工薪资配置信息");
		boolean result = employeeWagesConfigurateService.delEmployeeWagesConfigurate(id); 
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_DEL_ERROR);
	}
	
	public static void main(String[] args) {
		Map<String,Object> map = new HashMap<String,Object>(0);
		map.put("basePay", "100");
		map.put("welfareId", "1");
		map.put("welfareName", "福利方案一");
		map.put("ssfConfigId", "1");
		map.put("ssfConfigName", "社保方案一");
		map.put("agentComConfigId", "1");
		map.put("agentComConfigName", "中介类提成方案一");
		map.put("financeComConfigId", "1");
		map.put("financeComConfigName", "金融类提成方案一");
		
		System.out.println(JSONObject.toJSONString(map));
	}
}
