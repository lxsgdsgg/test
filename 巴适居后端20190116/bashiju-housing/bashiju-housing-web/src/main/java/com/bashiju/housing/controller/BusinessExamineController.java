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
package com.bashiju.housing.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.bashiju.enums.ExamineStatusEnum;
import com.bashiju.enums.HouseTransactionEnum;
import com.bashiju.housing.pojo.BusinessExamineHandleTypeEnum;
import com.bashiju.housing.pojo.BusinessExamineTypeEnum;
import com.bashiju.housing.service.BusinessExamineService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;

/**   
 * @ClassName:  BusinessExamineController   
 * @Description:房源业务审核控制器   
 * @author: yangz
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
		ModelAndView mv = this.getModelAndView(request, response, "businessExamine/businessExamine");
		mv.addObject("transactionType", HouseTransactionEnum.enumMap);
		mv.addObject("transactionTypeJson", JSONObject.toJSON(HouseTransactionEnum.enumMap));
		mv.addObject("examineType", BusinessExamineTypeEnum.enumMap);
		mv.addObject("examineTypeJson", JSONObject.toJSON(BusinessExamineTypeEnum.enumMap));
		mv.addObject("examineStatus", ExamineStatusEnum.enumMap);
		mv.addObject("examineStatusJson", JSONObject.toJSON(ExamineStatusEnum.enumMap));
		mv.addObject("examineHandleTypeJson", JSONObject.toJSON(BusinessExamineHandleTypeEnum.enumMap));
		return mv;
	}
	/**
	 * 房源业务审批下拉数据 
	 * @Title: businessExamineDropdown   
	 * @Description: 房源业务审批下拉数据 
	 * @return: Object
	 */
	@RequestMapping(value="businessExamineDropdown")
	@ResponseBody
	public Object businessExamineDropdown() {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String,Object>> operatorInfo = businessExamineService.queryOperatorInfo();
		map.put("transactionType", HouseTransactionEnum.enumList);
		map.put("examineType", BusinessExamineTypeEnum.enumList);
		map.put("examineStatus", ExamineStatusEnum.enumList);
		map.put("operatorInfo", operatorInfo);
		map.put("handleType", BusinessExamineHandleTypeEnum.enumList);
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
	public Object getBusinessExamineData(HttpServletRequest request,HttpServletResponse response) {
		String transactionTypeId = request.getParameter("transactionTypeId");
		String examineTypeId = request.getParameter("examineTypeId");
		String status = request.getParameter("status");
		String operatorId = request.getParameter("operatorId");
		String beginAddTime = request.getParameter("beginAddTime");
		String endAddTime = request.getParameter("endAddTime");
		String auditor = request.getParameter("auditor");
		String applyerId = request.getParameter("applyerId");
		String beginAuditTime = request.getParameter("beginAuditTime");
		String endAuditTime = request.getParameter("endAuditTime");
		String houseId = request.getParameter("houseId");
		String content = request.getParameter("content");
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("transactionTypeId", transactionTypeId);
		params.put("examineTypeId", examineTypeId);
		params.put("status", status);
		params.put("operatorId", operatorId);
		params.put("beginAddTime", beginAddTime);
		params.put("endAddTime", endAddTime);
		params.put("auditor", auditor);
		params.put("applyerId", applyerId);
		params.put("beginAuditTime", beginAuditTime);
		params.put("endAuditTime", endAuditTime);
		params.put("houseId", houseId);
		params.put("content", content);
		int pageNum = 1;
		int pageSize = 20;
		String num = request.getParameter("page");
		String size = request.getParameter("limit");
		if(!StringUtils.isEmpty(num))
			pageNum = Integer.parseInt(num);
		if(!StringUtils.isEmpty(size))
			pageSize = Integer.parseInt(size);
		Page<Map<String,Object>> page =businessExamineService.queryBusinessExamine(params, pageNum, pageSize);
		Map<String,Object> map = getPageResult(page);
		return JSONObject.toJSON(map);
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
		String id = request.getParameter("id");
		String status = request.getParameter("status");
		String handleType = request.getParameter("handleType");
		String transferee = request.getParameter("transferee");
		String rejectReason = request.getParameter("rejectReason");
		boolean result = this.businessExamineService.examineApplication(id, status, handleType, transferee, rejectReason);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	
}
