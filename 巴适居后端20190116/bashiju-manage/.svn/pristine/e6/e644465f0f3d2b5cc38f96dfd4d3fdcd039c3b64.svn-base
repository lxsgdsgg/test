package com.bashiju.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bashiju.enums.HouseArticleTypeEnum;
import com.bashiju.enums.RoleGroupEnum;
import com.bashiju.manage.service.ContentmanageService;
import com.bashiju.manage.service.HousePropertyArticleClassesService;
import com.bashiju.manage.service.LabelmanageService;
import com.bashiju.manage.service.impl.HousePropertyArticleClassesServiceImpl;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  ContentmanageController   
 * @Description:TODO(内容管理)   
 * @author: liwen
 * @date:   2018年8月9日 上午10:02:36   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
public class LabelmanageController {
	
	@Autowired
	LabelmanageService labelmanageService;
	
	@RequestMapping("/labelmanage/{pageName}")
	public String toPage(HttpServletRequest request,HttpServletResponse response,Model m,@PathVariable("pageName") String pageName){
		String userId = UserThreadLocal.get().get("id").toString();
		m.addAttribute("userId", userId);
		return "labelmanage/"+pageName;
		
	}
	
	@RequestMapping("/saveLabel")
	@ResponseBody
	public BashijuResult saveArticle(@RequestBody Map<String,Object> data){
		
		Long res = labelmanageService.saveLabel(data);
		if(res>0) {
			return BashijuResult.ok();
		}else {
			throw new BusinessException("保存失败");
		}
		
	}
	
	@RequestMapping("/getLabelList")
	@ResponseBody
	public Map<String,Object> getLabelList(String conditions,int page,int limit){
		Page<Map<String, Object>> pageInfo = labelmanageService.queryLabelList(conditions,page,limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
		
	}
	
	
	
	@RequestMapping("/delLabel")
	@ResponseBody
	public BashijuResult delLabel(Long id){
		boolean res = labelmanageService.delLabel(id);
		if(res) {
			return BashijuResult.ok();
		}else {
			throw new BusinessException("删除失败");
		}
		
	}
	
	
	
}
