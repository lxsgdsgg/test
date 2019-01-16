package com.bashiju.manage.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.bashiju.manage.service.IUserService;
import com.bashiju.utils.service.BaseController;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping("user")
@Controller
/**
 * 用户管理类
 * @ClassName  UserController   
 * @Description 用户管理类 
 * @author zhaobin
 * @date   2018年5月30日 下午12:15:51   
 *     
 * @Copyright 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class UserController extends BaseController {
	@Autowired
    IUserService userService;

	public static final ObjectMapper mapper=new ObjectMapper();	
	
	
	
	@RequestMapping(value="listShow")
	public ModelAndView getFormField(HttpServletRequest request,HttpServletResponse response, String pageName) {
		ModelAndView modelAndView=this.getModelAndView(request, response, pageName);
		List<Map<String,Object>> formField=userService.queryTableField("sys_user", true);
		modelAndView.addObject("formField",JSONObject.toJSONString(formField));
		return modelAndView;
	}
	@RequestMapping(value="formShow")
	public ModelAndView getFormData(HttpServletRequest request,HttpServletResponse response, String pageName) {
		ModelAndView modelAndView=this.getModelAndView(request, response, pageName);
		List<Map<String,Object>> formField=userService.queryTableField("sys_user", false);
		modelAndView.addObject("formField",formField);
		return modelAndView;
	}
	@RequestMapping(value="getListShowBody")
	@ResponseBody
	public Map getFormData(int page,int limit) {
		Map map=this.getPageResult(userService.queryAllUser(page, limit));
		return map;		
	}
	
	@RequestMapping(value="getDropDownValue")
	@ResponseBody
	public List<Map<String,Object>> getDropDownValue(String arg0,String arg1) {
		List<Map<String,Object>> sql1=userService.queryCustomField(arg0, "value", "field='"+arg1+"'");
		String sql=sql1.get(0).get("value").toString();
		return userService.queryCustomComponentValues(sql);	
	}
}
