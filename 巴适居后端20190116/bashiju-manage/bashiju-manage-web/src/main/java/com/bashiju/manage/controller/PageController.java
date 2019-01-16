package com.bashiju.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 页面跳转

* <p>Title: PageController<／p>

* <p>Description: <／p>

* <p>Company: bashiju<／p> 

* @author zhaobindaxia

* @date 2018年3月13日
 */
@Controller
@RequestMapping("page")
public class PageController {
	
	/**
	 * 具体的跳转逻辑
	 * @param pageName
	 * @return 视图
	 */
	@RequestMapping(value="{pageName}",method=RequestMethod.GET)
	public String toPage(@PathVariable("pageName") String pageName)
	{
		return pageName;
	}
	
	
	
	
}
