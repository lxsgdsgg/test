package com.bashiju.fin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.bashiju.api.FormServiceApi;
import com.bashiju.enums.CommissionStatusEnum;
import com.bashiju.enums.DealTypeEnum;
import com.bashiju.enums.ExamineStatusEnum;
import com.bashiju.fin.service.FinService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;

@Controller
public class FinController extends BaseController{
	@Autowired
	FormServiceApi formServiceApi;
	@Autowired
	FinService finService;
	/**
	 * 具体的跳转逻辑
	 * @param pageName
	 * @return 视图
	 */
	@RequestMapping(value="{pageName}",method=RequestMethod.GET)
	public String toPage(@PathVariable("pageName") String pageName,Model m)
	{
		return pageName;
	}
	
	@RequestMapping(value="commissionListPage/{pageName}")
	public String commissionListPage(Model m,@PathVariable("pageName") String pageName) {
		Map<String, Object> user = UserThreadLocal.get();
		String cityCode=user.get("cityCode").toString();
		String companyId=user.get("companyId").toString();
		m.addAttribute("communitList", JSON.toJSONString(formServiceApi.searchCommunity(cityCode.substring(0,4))));
		m.addAttribute("departmentList", JSON.toJSONString(formServiceApi.queryDept(companyId)));
		return pageName;
	}
	
	@RequestMapping(value="payment/{pageName}")
	public ModelAndView goPayRecord(HttpServletRequest request,HttpServletResponse response,@PathVariable("pageName") String pageName){
		List settlementList=formServiceApi.querySelectValue("one", "settlementType");
		List moneyTypeList=formServiceApi.querySelectValue("one", "moneyType");
		ModelAndView mv=getModelAndView(request, response, "payment/"+pageName);
		mv.addObject("settlementType", settlementList);
		mv.addObject("moneyType", moneyTypeList);		
		return mv;
	}
	
	@RequestMapping(value="getIncomeBaseInfo")
	@ResponseBody
	public BashijuResult getIncomeBaseInfo() {
		Map<String,Object> info = new HashMap<>();
		info.put("sureStatus", CommissionStatusEnum.map);
		info.put("examineStatus", ExamineStatusEnum.enumMap);
		info.put("dealType", DealTypeEnum.enumMap);
		return BashijuResult.ok(info);
	}
	
	@RequestMapping(value="querySelectValue")
	@ResponseBody
	public BashijuResult querySelectValue(String type,String param) {
		List res = formServiceApi.querySelectValue(type,param);
		return BashijuResult.ok(res);
	}
	
	
	
	/**
	 * 
		 * 查询收付款审核列表
		 * @Description: 查询收付款审核列表 
		 * @param status
		 * @param timeType
		 * @param beginTime
		 * @param endTime
		 * @param moneyTypeId
		 * @param settlementTypeId
		 * @param payerType
		 * @param keyword
		 * @param keywordType
		 * @param page
		 * @param limit
		 * @return
		 * @throws Exception 
		 * Map<String,Object>
	 */
	@RequestMapping(value="queryPayRecordList")
	@ResponseBody
	public Map<String,Object> queryPayRecordList(Long transactionType,String status,String timeType,String beginTime,String endTime,
			Long moneyTypeId,Long settlementTypeId,Integer payerType,String keyword,String keywordType,int page,int limit) throws Exception{
		if (transactionType==null) {
			throw new BusinessException(ErrorCodeEnum.UNKNOWN_ERROR);
		}
		Map<String, Object> param=new HashMap<>();
		param.put("transactionType", transactionType);
		if (StringUtils.isNotEmpty(status)) {
			param.put("status", status);
		}
		if (StringUtils.isNotEmpty(timeType)) {
			param.put("timeType", timeType);
		}
		if (StringUtils.isNotEmpty(beginTime)) {
			param.put("beginTime", beginTime);
		}
		if (StringUtils.isNotEmpty(timeType)) {
			param.put("timeType", timeType);
		}
		if (StringUtils.isNotEmpty(beginTime)) {
			param.put("beginTime", beginTime);
		}
		if (StringUtils.isNotEmpty(endTime)) {
			param.put("endTime", endTime);
		}
		if (moneyTypeId!=null) {
			param.put("moneyTypeId", moneyTypeId);
		}
		if (settlementTypeId!=null) {
			param.put("settlementTypeId", settlementTypeId);
		}
		if (payerType!=null) {
			param.put("payerType", payerType);
		}
		if (StringUtils.isNotEmpty(keyword)) {
			param.put("keyword", keyword);
		}
		if (StringUtils.isNotEmpty(keywordType)) {
			param.put("keywordType", keywordType);
		}
		Page<Map<String, Object>> pageInfo = finService.queryPayRecord(param, page, limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	
	@RequestMapping(value="getCommissionList")
	@ResponseBody
	public Map<String,Object> getCommissionList(HttpServletRequest request,int page,int limit){
		Map<String,Object> conditions = new HashMap<>();
		conditions.put("status", request.getParameter("status"));
		conditions.put("dealType", request.getParameter("dealType"));
		conditions.put("moneyType", request.getParameter("moneyType"));
		conditions.put("timeType", request.getParameter("timeType"));
		conditions.put("starttime", request.getParameter("starttime"));
		conditions.put("endtime", request.getParameter("endtime"));
		//选的是部门，还是部门下的人
		conditions.put("dataType", request.getParameter("dataType"));
		conditions.put("deptId", request.getParameter("deptId"));
		conditions.put("communityId", request.getParameter("communityId"));
		//模糊条件
		conditions.put("vagueType", request.getParameter("vagueType"));
		conditions.put("vagueData", request.getParameter("vagueData"));
		Page<Map<String, Object>> pageInfo = finService.queryCommissionList(conditions,page, limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	
	@RequestMapping(value="updateInfo")
	@ResponseBody
	public BashijuResult updateInfo(Long id,String type,@RequestParam(required=false) String reason,@RequestParam(required=false) String sureTime){
		Long res = 0l;
		if("sureStatus".equals(type)) {//确认状态
			res = finService.updateInfo(id,type,"");
		}else if("reject".equals(type)) {//驳回
			res = finService.updateInfo(id,type,reason);
		}else if("updateSuretime".equals(type)) {//修改确认时间
			res = finService.updateInfo(id,type,sureTime);
		}
		if(res>0) {
			return BashijuResult.ok(res);
		}else {
			throw new BusinessException("失败");
		}
		
	}

	/**
	 * 
		 * 修改收付款审核状态
		 * @Description: 修改收付款审核状态 
		 * @param id 收付款id
		 * @param status 审核状态:1通过，2驳回
		 * @param reason 驳回原因
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping(value="updatePayStatus")
	@ResponseBody
	public BashijuResult updatePayStatus(Long id, String status, String reason) {
		finService.updatePayStatus(id, status, reason);
		return BashijuResult.ok();
	}

	@RequestMapping(value="saveFinanceDivide")
	@ResponseBody
	public BashijuResult saveFinanceDivide(@RequestBody Map<String,Object> data){
		
		boolean res = finService.saveFinanceDivide(data);
		if(res) {
			return BashijuResult.ok();
		}else {
			throw new BusinessException("失败");
		}
		
	}

	@RequestMapping(value="getFinanceDivide")
	@ResponseBody
	public BashijuResult getFinanceDivide(Long commissionId){
		
		List<Map<String,Object>> res = finService.queryFinanceDivide(commissionId);
		return BashijuResult.ok(res);
		
	}
	/**
	 * 
	 * @Title: 查日志列表   
	 * @Description:    
	 * @param: @param request
	 * @param: @param page
	 * @param: @param limit
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	@RequestMapping(value="getLogList")
	@ResponseBody
	public Map<String,Object> getLogList(HttpServletRequest request,int page,int limit){
		Map<String,Object> conditions = new HashMap<>();
		conditions.put("type", request.getParameter("type"));
		conditions.put("queryType", request.getParameter("queryType"));
		conditions.put("actionType", request.getParameter("actionType"));
		conditions.put("deptId", request.getParameter("deptId"));
		conditions.put("userId", request.getParameter("userId"));
		conditions.put("minTime", request.getParameter("minTime"));
		conditions.put("maxTime", request.getParameter("maxTime"));
		conditions.put("dealCode", request.getParameter("dealId")); 
		conditions.put("logContent", request.getParameter("logContent"));
		
		if(request.getParameter("companyId")==null || StringUtils.isEmpty(request.getParameter("companyId"))) {
			conditions.put("companyId", UserThreadLocal.get().get("companyId"));
		}else {
			conditions.put("companyId",request.getParameter("companyId"));
		}
		
		Page<Map<String, Object>> pageInfo = finService.auxQueryLog(conditions,page, limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}

}
