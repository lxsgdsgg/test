package com.bashiju.manage.controller;

import java.util.ArrayList;
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
import com.bashiju.manage.service.NavigationBarService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;
/**
 * @ClassName:  NavigationBarController   
 * @Description: 导航栏管理控制层
 * @author: wangpeng
 * @date:   2018年8月8日 上午10:16:16   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="navigationBar")
public class NavigationBarController extends BaseController {
		
	@Autowired
	private NavigationBarService navigationBarService;
	
	/**
	 * @Title: enterNavigationBarPage   
	 * @Description: 进入导航栏管理页面
	 * @param request
	 * @param response
	 * @return: ModelAndView
	 */
	@RequestMapping(value="enterNavigationBarPage")
	public ModelAndView enterNavigationBarPage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = getModelAndView(request, response, "navigationBar/navigationBar");
		return mv;
	}
	/**
	 * @Title: enterNavigationBarDetailPage   
	 * @Description: 进入导航栏管理详情页面
	 * @param request
	 * @param response
	 * @return: ModelAndView
	 */
	@RequestMapping(value="enterNavigationBarDetailPage")
	public ModelAndView enterNavigationBarDetailPage(HttpServletRequest request,HttpServletResponse response,String navigationBarId) {
		ModelAndView mv = getModelAndView(request, response, "navigationBar/navigationBarDetail");
		//条件查询导航栏
		Map<String,Object>  navigationBar = navigationBarService.queryNavigationBarById(navigationBarId);
		if(navigationBar!=null && navigationBar.size()>0)
			mv.addObject("navigationBar", navigationBar);
		return mv;
	}
	
	/**
	 * @Title: queryNavigationBarData   
	 * @Description: 分页查询导航栏数据
	 * @param page 当前页数
	 * @param limit 每页总条数
	 * @return: Map<String,Object>
	 */
	@RequestMapping(value="queryNavigationBarData")
	@ResponseBody
	public Map<String,Object> queryNavigationBarData(int page,int limit,String name,String type,String level){
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("name", name);
		paramMap.put("type", type);
		paramMap.put("level", level);
		Page<Map<String,Object>> pages = navigationBarService.queryNavigationBarData(page, limit,paramMap);
		Map<String, Object> map = getPageResult(pages);
		return map;
	}
	/**
	 * @Title: queryNavigationBarById   
	 * @Description: 条件查询导航栏信息
	 * @param navigationBarId 导航栏id
	 * @return: Map<String,Object>
	 */
	@RequestMapping(value="queryNavigationBarById")
	@ResponseBody
	public Map<String, Object> queryNavigationBarById(String navigationBarId){
		Map<String,Object>  map = navigationBarService.queryNavigationBarById(navigationBarId);
		return map;
	}
	
	/**
	 * @Title: delNavigationBarById   
	 * @Description: 逻辑删除导航栏
	 * @param navigationBarId 导航栏id
	 * @return: BashijuResult
	 */
	@RequestMapping(value="delNavigationBarById")
	@ResponseBody
	public BashijuResult delNavigationBarById(String navigationBarId) {
		boolean result = navigationBarService.delNavigationBarById(navigationBarId);
		if(result)
			return BashijuResult.ok();
		throw new BusinessException(ErrorCodeEnum.SYSTEM_DEL_ERROR);
	}
	
	/**
	 * @Title: saveOrUpdataNavigationBar   
	 * @Description: 新增或编辑导航栏信息
	 * @param jsonData 保存参数
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveOrUpdataNavigationBar")
	@ResponseBody
	public BashijuResult saveOrUpdataNavigationBar(String jsonData) {
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("参数不能为空");
		Map<String,Object> map = (Map<String,Object>) JSONObject.parse(jsonData);
		boolean result = navigationBarService.saveOrUpdataNavigationBar(map);
		if(result)
			return BashijuResult.ok();
		throw new BusinessException(ErrorCodeEnum.UPDATEFILE_ERROR);
	}
	/**
	 * @Title: queryAllNavigationBarNameByLevel   
	 * @Description:通过等级查询多导航栏名称  
	 * @param level 等级
	 * @return: List<Map<String,Object>>
	 */
	@RequestMapping(value="queryAllNavigationBarNameByLevel")
	@ResponseBody
	public List<Map<String,Object>> queryAllNavigationBarNameByLevel(String level){
		List<Map<String,Object>> map =	navigationBarService.queryAllNavigationNameByLevel(level);
		if(map!=null && map.size()>0)
			return map;
		return new ArrayList<Map<String,Object>>();
	}
	
	/**
	 * @Title: queryIsExistNavigationCode   
	 * @Description: 条件查询当好编号是否已存在 
	 * @param navigateCode 导航编号
	 * @return: BashijuResult
	 */
	@RequestMapping(value="queryIsExistNavigateCode")
	@ResponseBody
	public BashijuResult queryIsExistNavigationCode(String navigateCode) {
		boolean result = navigationBarService.queryIsExistNavigateCode(navigateCode);
		if(result)
			throw new BusinessException("导航编号已存在");
		return BashijuResult.ok();
	}
}
