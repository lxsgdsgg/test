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

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bashiju.enums.BusinessTypeEnum;
import com.bashiju.fin.service.EmployeeCommissionsInfoService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;

/**   
 * @ClassName:  EmployeeCommissionsInfoController   
 * @Description:员工提成信息  
 * @author: liwen
 * @date:   2018年9月29日 下午14:43:30   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="commissionsInfo")
public class EmployeeCommissionsInfoController extends BaseController {

	@Autowired
	private EmployeeCommissionsInfoService employeeCommissionsInfoService;
	
	
	/**
	 * 获取员工提成信息  
	 * @Description: 获取员工提成信息  
	 * @param request
	 * @param response
	 * @return: Object
	 */
	@RequestMapping(value="getEmployeeCommissionsInfo")
	@ResponseBody
	public Object getEmployeeCommissionsInfo(HttpServletRequest request,HttpServletResponse response) {
		Long userId = null;
		if(request.getParameter("userId")!=null && !"".equals(request.getParameter("userId")))
			userId = Long.parseLong(request.getParameter("userId").toString());
		String deptId = request.getParameter("deptId");
		String companyId = request.getParameter("companyId");
		String startMonth = request.getParameter("startMonth");
		String endMonth = request.getParameter("endMonth");
		int businessType = 0;
		if(request.getParameter("businessType")!=null)
		businessType = Integer.parseInt(request.getParameter("businessType").toString());
		int pageNum = 1;
		int pageSize = 20;
		String num = request.getParameter("page");
		String size = request.getParameter("limit");
		if(!StringUtils.isEmpty(num))
			pageNum = Integer.parseInt(num);
		if(!StringUtils.isEmpty(size))
			pageSize = Integer.parseInt(size);
		Page<Map<String,Object>> page = employeeCommissionsInfoService
				.queryEmployeeCommissionsInfo(companyId,userId,deptId,startMonth,endMonth,businessType,pageNum, pageSize);
		Map<String,Object> map = getPageResult(page);
		return JSONObject.toJSON(map);
	}
	/**
	 * 
	 * @Title: 获取业务类型
	 * @Description: 获取业务类型  
	 * @param: @return      
	 * @return: BashijuResult      
	 * @throws
	 */
	@RequestMapping(value="getBusinessType")
	@ResponseBody
	public BashijuResult getBusinessType() {
		Map<String,Object> mm = new HashMap<>();
		mm.put("businessType", BusinessTypeEnum.enumMap);
		return BashijuResult.ok(mm);
	}
	
	@RequestMapping(value="makeEmployeeCommissions")
	@ResponseBody
	public BashijuResult makeEmployeeCommissions(HttpServletRequest request,HttpServletResponse response) {
		String companyId = UserThreadLocal.get().get("companyId").toString();
		String userIds = request.getParameter("userIds");
		String months = request.getParameter("months");
		boolean result = employeeCommissionsInfoService.saveGenerateCommissions(companyId,months,userIds);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	
	
}
