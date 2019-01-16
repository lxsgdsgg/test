package com.bashiju.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.bashiju.manage.service.UserLogService;
import com.bashiju.utils.service.BaseController;
import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  User_logController   
 * @Description:用户日志控制层  
 * @author: wangpeng
 * @date:   2018年5月14日 上午11:51:41   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="userLog")
public class UserLogController extends BaseController{
	@Autowired
	private UserLogService userLogService;
	/**
	 * 
	 * @Title: enterUser_logPage   
	 * @Description: 进入用户日志页面
	 * @param request
	 * @param response
	 * @return      
	 * @return: ModelAndView
	 */
	@RequestMapping(value="enterUserLogPage")
	public ModelAndView enterUserLogPage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = getModelAndView(request, response, "userLog/userLog");
		return mv;
	}
	/**
	 * 
	 * @Title: queryUser_logData   
	 * @Description: 分页查询用户日志信息   
	 * @param request
	 * @param response
	 * @return      
	 * @return: Object
	 */
	@RequestMapping(value="queryUserLogData",method=RequestMethod.POST)
	@ResponseBody
	public Object queryUserLogData(String operationType,String sourceType,String ip,String addTime,String companyName,int page, int limit) {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("operationType", operationType);
		paramMap.put("sourceType", sourceType);
		paramMap.put("loginIp", ip);
		paramMap.put("comName", companyName);
		if(!StringUtils.isEmpty(addTime)) {
			String[] addTimes = addTime.split(",");
			paramMap.put("beginAddTime", addTimes[0].toString());
			paramMap.put("endAddTime", addTimes[1].toString());
		}
		
		Page<Map<String,Object>> pages=userLogService.queryUserLog(paramMap, page, limit);
		Map<String,Object>map = getPageResult(pages);
		return JSONArray.toJSON(map);
	}
	
	/**
	 * 
	 * @Title: queryUser_logData   
	 * @Description: 分页查询用户日志信息    （未整理过的）
	 * @param request
	 * @param response
	 * @return      
	 * @return: Object
	 */
	/*@RequestMapping(value="queryUserLogData")
	@ResponseBody
	public Object queryUserLogData(HttpServletRequest request,HttpServletResponse response,String operationType,String sourceType,String addTime,String beginAddTime,String endAddTime,int page,int limit) {
		Map<String,Object> paramMap =new HashMap<>();
		paramMap.put("operationType", operationType);
		paramMap.put("sourceType", sourceType);
		paramMap.put("addTime", addTime);
		paramMap.put("beginAddTime", beginAddTime);
		paramMap.put("endAddTime", endAddTime);
		Page<Map<String,Object>> pages=userLogService.queryUserLog(paramMap, page, limit);
		Map<String,Object>map=getPageResult(pages);
		return JSONArray.toJSON(map);
	}*/
}
