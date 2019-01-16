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
import com.bashiju.manage.service.LoginRecordService;
import com.bashiju.utils.service.BaseController;
import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  Login_recordController   
 * @Description:登陆日志控制层  
 * @author: wangpeng
 * @date:   2018年5月14日 上午11:58:50   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="loginRecord")
public class LoginRecordController extends BaseController {

	@Autowired
	private LoginRecordService loginRecordService;
	/**
	 * 
	 * @Title: enterLogin_recordPage   
	 * @Description: 进入登录日志页面
	 * @param request
	 * @param response
	 * @return      
	 * @return: ModelAndView
	 */
	@RequestMapping(value="enterLoginRecordPage")
	public ModelAndView enterLoginRecordPage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = getModelAndView(request, response, "loginRecord/loginRecord");
		return mv;
	}
	/**
	 * 
	 * @Title: queryLoginecordData   
	 * @Description: 查询登录日志（分页）   
	 * @param request
	 * @param response
	 * @param phone 电话
	 * @param loginTime 登陆时间
	 * @param operator 操作人
	 * @param areaName 区域名称
	 * @return      
	 * @return: Object
	 */
	@RequestMapping(value="queryLoginRecordData")
	@ResponseBody
	public Object queryLoginecordData(HttpServletRequest request,HttpServletResponse response,String name,String beginAddTime,String endAddTime,int page,int limit){
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("name", name);
		paramMap.put("beginAddTime", beginAddTime);
		paramMap.put("endAddTime", endAddTime);
		Page<Map<String,Object>> pages = loginRecordService.queryLoginRecord(paramMap, page, limit);
		Map<String, Object> map = getPageResult(pages);
		return JSONArray.toJSON(map);
	}
}
