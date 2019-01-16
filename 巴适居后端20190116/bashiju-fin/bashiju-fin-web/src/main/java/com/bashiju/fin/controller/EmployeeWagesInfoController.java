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
import com.bashiju.fin.service.EmployeeWagesInfoService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;

/**   
 * @ClassName:  EmployeeWagesInfoController   
 * @Description:员工薪资信息  
 * @author: liwen
 * @date:   2018年9月27日 上午10:43:30   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="wagesInfo")
public class EmployeeWagesInfoController extends BaseController {

	@Autowired
	private EmployeeWagesInfoService employeeWagesInfoService;
	
	
	/**
	 * 获取员工薪资信息  
	 * @Description: 获取员工薪资信息  
	 * @param request
	 * @param response
	 * @return: Object
	 */
	@RequestMapping(value="getEmployeeWagesInfo")
	@ResponseBody
	public Object getEmployeeWagesInfo(HttpServletRequest request,HttpServletResponse response) {
		Long userId = null;
		String companyId = request.getParameter("companyId");
		if(request.getParameter("userId")!=null && !"".equals(request.getParameter("userId")))
			userId = Long.parseLong(request.getParameter("userId").toString());
		String deptId = request.getParameter("deptId");
		String startMonth = request.getParameter("startMonth");
		String endMonth = request.getParameter("endMonth");
		int pageNum = 1;
		int pageSize = 20;
		String num = request.getParameter("page");
		String size = request.getParameter("limit");
		if(!StringUtils.isEmpty(num))
			pageNum = Integer.parseInt(num);
		if(!StringUtils.isEmpty(size))
			pageSize = Integer.parseInt(size);
		Page<Map<String,Object>> page = employeeWagesInfoService
				.queryEmployeeWagesInfos(companyId,userId,deptId,startMonth,endMonth,pageNum, pageSize);
		Map<String,Object> map = getPageResult(page);
		return JSONObject.toJSON(map);
	}
	
	
	/**
	 * 保存员工福利信息
	 * @Description: 保存员工福利信息   
	 * @param request
	 * @param response
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="updateEmployeeWelfarePay")
	@ResponseBody
	public BashijuResult updateEmployeeWelfarePay(HttpServletRequest request,HttpServletResponse response) {
		String jsonData = request.getParameter("jsonData");
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("没有要保存的数据");
		List<Map<String,Object>> map = (List<Map<String, Object>>) JSONObject.parse(jsonData);
		
		boolean result = employeeWagesInfoService.updateEmployeeWelfarePay(map);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	
	/**
	 * 
	 * @Title: 修改员工基本工资   
	 * @Description: TODO(修改员工基本工资   )   
	 * @param: @param request
	 * @param: @param response      
	 * @return: void      
	 * @throws
	 */
	@RequestMapping(value="updateEmployeeBasePay")
	@ResponseBody
	public BashijuResult updateEmployeeBasePay(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
	    String basePay = request.getParameter("basePay");
	    boolean result = employeeWagesInfoService.updateEmployeeBasePay(id,basePay);
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
		//boolean result = employeeWagesConfigurateService.delEmployeeWagesConfigurate(id); 
		boolean result = true;
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_DEL_ERROR);
	}
	/**
	 * 
	 * @Title: makeEmployeeWages   
	 * @Description: TODO(生成员工上月工资)   
	 * @param: @param request
	 * @param: @param response
	 * @param: @return      
	 * @return: BashijuResult      
	 * @throws
	 */
	@RequestMapping(value="makeEmployeeWages")
	@ResponseBody
	public BashijuResult makeEmployeeWages(HttpServletRequest request,HttpServletResponse response) {
		String companyId = UserThreadLocal.get().get("companyId").toString();
		String userIds = request.getParameter("userIds");
		String months = request.getParameter("months");
		boolean result = employeeWagesInfoService.makeEmployeeWages(months,companyId,userIds);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_ADD_ERROR);
	}
	/**
	 * 
	 * 审核员工工资
	 * @Description: TODO(审核员工工资)   
	 * @param: @param request
	 * @param: @param response
	 * @param: @return      
	 * @return: BashijuResult      
	 * @throws
	 */
	@RequestMapping(value="examineEmployeeWages")
	@ResponseBody
	public BashijuResult examineEmployeeWages(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> data = new HashMap<>();
		data.put("months",request.getParameter("months"));
		data.put("examineStatus",request.getParameter("examineStatus"));
		data.put("rejectReason",request.getParameter("rejectReason")==null?"":request.getParameter("rejectReason"));
		boolean res = employeeWagesInfoService.saveExamineUserDeserts(data);
		if(res) {
			return BashijuResult.ok(UserThreadLocal.get().get("realName"));
		}else {
			throw new BusinessException("审核失败！");
		}
	}
	
	
}
