package com.bashiju.manage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bashiju.manage.pojo.ElementUiResult;
import com.bashiju.manage.service.ManageBasicService;
import com.bashiju.utils.util.BashijuResult;
/**
 * 
 * @ClassName:  ManageBasicController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: zhaobin
 * @date:   2018年7月4日 下午3:29:29   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@RequestMapping("basic")
@Controller
public class ManageBasicController {
	@Autowired
	ManageBasicService ManageBasicService;
	
	@RequestMapping("queryArea")
	@ResponseBody
	public Object queryArea() {
		return ManageBasicService.queryAllArea();
	}
	
	
}
