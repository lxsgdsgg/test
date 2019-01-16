package com.bashiju.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.bashiju.manage.service.ErrorLogService;
import com.bashiju.utils.service.BaseController;
import com.github.pagehelper.Page;

@Controller
@RequestMapping(value="errorLog")
public class ErrorLogController extends BaseController {
	@Autowired
	private ErrorLogService errorLogService;
	
	@RequestMapping(value="enterErrorLogPage")
	public ModelAndView enterErrorLogPage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=getModelAndView(request, response, "errorLog/errorLog");
		return mv;
	}
	/**
	 * 
	 * @Title: queryErrorLog   
	 * @Description: 分页查询错误日志信息
	 * @param request
	 * @param response
	 * @param methodName
	 * @param addTime 添加时间
	 * @param beginAddTime 开始时间
	 * @param endAddTime 结束时间
	 * @return      
	 * @return: Object
	 */
	@RequestMapping(value="getErrorLogData")
	@ResponseBody
	public Object queryErrorLog(HttpServletRequest request,HttpServletResponse response,String methodName,String loginUser,String addTime,String beginAddTime,String endAddTime,int page,int limit) {
		Map<String,Object> paramMap=new HashMap<>();
		paramMap.put("methodName", methodName);
		paramMap.put("loginUser", loginUser);
		paramMap.put("addTime", addTime);
		paramMap.put("beginAddTime", beginAddTime);
		paramMap.put("endAddTime", endAddTime);
		Page<Map<String,Object>>erros=errorLogService.queryErrorLog(paramMap, page, limit);
		Map<String,Object>map=getPageResult(erros);
		return map;
	}
}
