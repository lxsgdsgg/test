/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  BusinessExamineController.java   
 * @Package com.bashiju.housing.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年6月5日 下午4:56:57   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.customer.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.bashiju.customer.pojo.BusinessExamineTypeEnum;
import com.bashiju.customer.service.BusinessExamineService;
import com.bashiju.enums.CustomerTransactionEnum;
import com.bashiju.enums.ExamineStatusEnum;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;

/**   
 * @ClassName:  BusinessExamineController   
 * @Description:客源业务审核控制器   
 * @author: liwen
 * @date:   2018年6月5日 下午4:56:57   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="businessExamine")
public class BusinessExamineController extends BaseController {
	
	@Autowired
	private BusinessExamineService businessExamineService;

	/**
	 * 
	 * @Description: 进入主页面   
	 * @param request
	 * @param response
	 * @return      
	 * @return: ModelAndView
	 */
	@RequestMapping(value="businessExaminePage")
	public ModelAndView businessExaminePage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = this.getModelAndView(request, response, "businessExamine");
		mv.addObject("transactionType", CustomerTransactionEnum.enumMap);
		mv.addObject("transactionTypeJson", JSONObject.toJSON(CustomerTransactionEnum.enumMap));
		mv.addObject("examineType", BusinessExamineTypeEnum.enumMap);
		mv.addObject("examineTypeJson", JSONObject.toJSON(BusinessExamineTypeEnum.enumMap));
		mv.addObject("examineStatus", ExamineStatusEnum.enumMap);
		mv.addObject("examineStatusJson", JSONObject.toJSON(ExamineStatusEnum.enumMap));
		return mv;
	}
	
	/**
	 * 客源业务审批下拉数据 
	 * @Title: businessExamineDropdown   
	 * @Description: 房源业务审批下拉数据 
	 * @return: Object
	 */
	@RequestMapping(value="getCustBusinessExamineDropdown")
	@ResponseBody
	public Object getCustBusinessExamineDropdown() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("transactionType", CustomerTransactionEnum.enumList);
		map.put("examineType", BusinessExamineTypeEnum.enumList);
		map.put("examineStatus", ExamineStatusEnum.enumList);
		return map;
	}
	/**
	 * 
	 * @Description: 条件查询房源业务审核数据信息   
	 * @param request
	 * @return      
	 * @return: Object
	 */
	@RequestMapping(value="getBusinessExamineData")
	@ResponseBody
	public Map<String,Object> getBusinessExamineData(String conditions,int page,int limit){
		Page<Map<String, Object>> pageInfo = businessExamineService.queryBusinessExamine(conditions,page, limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	
	/**
	 * 
	 * @Description: 审核信息数据   
	 * @param request
	 * @param response
	 * @return      
	 * @return: BashijuResult
	 */
	@RequestMapping(value="examineApplication")
	@ResponseBody
	public BashijuResult examineApplication(HttpServletRequest request,HttpServletResponse response) {
		Long id = Long.parseLong(request.getParameter("id"));
		String status = request.getParameter("status");
		String rejectReason = request.getParameter("rejectReason");
		boolean result = this.businessExamineService.examineApplication(id, status, rejectReason);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	
}
