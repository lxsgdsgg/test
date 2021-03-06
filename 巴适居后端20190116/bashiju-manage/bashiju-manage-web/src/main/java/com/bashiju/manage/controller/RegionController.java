package com.bashiju.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.bashiju.manage.service.IRegionService;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;

/**
 * 
 * @ClassName:  RegionController   
 * @Description:TODO(片区管理类)   
 * @author: wangkaifa
 * @date:   2018年4月18日 下午1:57:42   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping("region")
public class RegionController extends BaseController{
	@Autowired
	private IRegionService regionService;
	/**
	 * 具体的跳转逻辑
	 * @param pageName
	 * @return 视图
	 */
	@RequestMapping(value="{pageName}",method=RequestMethod.GET)
	public ModelAndView toPage(@PathVariable("pageName") String pageName,HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView mv = getModelAndView(request, response, "/region/"+pageName);
		List<Map<String, Object>> list=regionService.queryAreaSelect();
		mv.addObject("AreaListJson", JSON.toJSONString(list));
		return mv;
	}
	/**
	 * 
	 * @Title: getAreaList   
	 * @Description: TODO(获取行政区划列表)   
	 * @param: @param page
	 * @param: @param limit
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	@RequestMapping(value="getAreaList")
	@ResponseBody
	public Map<String,Object> getAreaList(String code,int page,int limit){
		Page<Map<String, Object>> pageInfo = regionService.queryArea(code,page, limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	
	@RequestMapping(value="getRegionList")
	@ResponseBody
	public Map<String,Object> getRegionList(String code,int page,int limit){
		Page<Map<String, Object>> pageInfo = regionService.queryRegion(code, page, limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	@RequestMapping(value="getRegionListByName")
	@ResponseBody
	public Map<String,Object> getRegionListByName(String cityCode,String name,int page,int limit){
		Page<Map<String, Object>> pageInfo = regionService.queryRegionByName(cityCode,name, page, limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	/**
	 * 添加片区
	 */
	@RequestMapping(value="addRegion")
	@ResponseBody
	public BashijuResult addRegion(String regionName, String areaCode, String regionLongitude, String regionLatitude) {
		String result=regionService.addRegion(regionName, areaCode, regionLongitude, regionLatitude);
		if ("success".equals(result)) {
			return BashijuResult.ok();
		}
		return BashijuResult.build(false, result);
	}
	/**
	 * 
		 * 修改片区
		 * @Description: 修改片区 
		 * @param id 片区id
		 * @param name 片区名称
		 * @param code 区域代码
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping(value="updateName")
	@ResponseBody
	public BashijuResult updateName(Long id, String name, String code) {
		Long result=regionService.updateName(id, name, code);
		return BashijuResult.ok(result);
	}
	/**
	 * 
		 * 删除片区
		 * @Description: 删除片区
		 * @param id 片区id
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping(value="deleteRegion")
	@ResponseBody
	public BashijuResult deleteRegion(Long id) {
		boolean result=regionService.deleteRegion(id);
		return BashijuResult.ok(result);
	}
	@RequestMapping(value="updateRegionPoint")
	@ResponseBody
	public BashijuResult updateRegionPoint(Long id,String longitude, String latitude) {
		long result=regionService.updateRegion(id, longitude, latitude);
		return BashijuResult.ok();
	}
	
	@RequestMapping(value="updateAreaPoint")
	@ResponseBody
	public BashijuResult updateAreaPoint(String code,String longitude, String latitude) {
		long result=regionService.updateArea(code, longitude, latitude);
		return BashijuResult.ok();
	}
}
