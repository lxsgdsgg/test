package com.bashiju.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * 
 *   PC端列表页搜索条件管理
 * @ClassName:  ClientQueryConditionController   
 * @Description:  PC端列表页搜索条件管理
 * @author: wangkaifa
 * @date:   2018年12月26日 下午5:07:53       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bashiju.manage.service.IClientQueryConditionService;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;
@Controller
@RequestMapping(value="queryCondition")
public class ClientQueryConditionController {
	@Autowired
	private IClientQueryConditionService clientQueryConditionService;
	
	/**
	 * 
		 * 根据类型查询搜索条件，类型为空查询所有
		 * @Description: 根据类型查询搜索条件，类型为空查询所有
		 * @param type 条件类型：0--出售房源，1--出租房源，2--新房，3--小区
		 * @param page
		 * @param limit
		 * @return 
		 * Map<String,Object>
	 */
	@RequestMapping(value="queryConditionList")
	@ResponseBody
	public Map<String,Object> queryConditionList(Integer type,int page,int limit){
		Page<Map<String, Object>> pageInfo = clientQueryConditionService.queryConditionList(type, page, limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	
	/**
	 * 
		 * 新增查询条件
		 * @Description: 新增查询条件
		 * @param jsonData
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping(value="addQueryCondition")
	@ResponseBody
	public BashijuResult addQueryCondition(String jsonData) {
		Integer result=clientQueryConditionService.addQueryCondition(jsonData);
		return BashijuResult.ok(result);
	}
	/**
	 * 
		 * 修改查询条件,关联城市的条件一起修改
		 * @Description: 修改查询条件,关联城市的条件一起修改
		 * @param jsonData
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping(value="updateQueryCondition")
	@ResponseBody
	public BashijuResult updateQueryCondition(String jsonData) {
		Integer result=clientQueryConditionService.updateQueryCondition(jsonData);
		return BashijuResult.ok(result);
	}
	/**
	 * 
		 * 删除查询条件,关联城市的条件一起删除
		 * @Description: 删除查询条件,关联城市的条件一起删除
		 * @param id
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping(value="deleteQueryCondition")
	@ResponseBody
	public BashijuResult deleteQueryCondition(Integer id) {
		Integer result=clientQueryConditionService.deleteQueryCondition(id);
		return BashijuResult.ok(result);
	}
	/**
	 * 
		 * 搜索条件查询树结构数据
		 * @Description: 搜索条件查询树结构数据
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping(value="queryConditionTree")
	@ResponseBody
	public BashijuResult queryConditionTree(String cityCode) {
		List<Map<String, Object>>result=clientQueryConditionService.queryConditionTree();
		List<Integer> ids=clientQueryConditionService.queryCityConditionIds(cityCode);
		Map<String, Object> map=new HashMap<>();
		map.put("root", result);
		map.put("ids", ids);
		return BashijuResult.ok(map);
	}
	/**
	 * 
		 * 查询城市条件列表
		 * @Description: 查询城市条件列表
		 * @param cityCode
		 * @param type
		 * @param page
		 * @param limit
		 * @return 
		 * Map<String,Object>
	 */
	@RequestMapping(value="queryCityConditionList")
	@ResponseBody
	public Map<String,Object> queryCityConditionList(String cityCode,Integer type,int page,int limit){
		Page<Map<String, Object>> pageInfo = clientQueryConditionService.queryCityConditionList(cityCode,type, page, limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	/**
	 * 
		 * 保存城市查询条件详情
		 * @Description: 保存城市查询条件详情
		 * @param cityCode
		 * @param JsonData
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping(value="saveCityCondition")
	@ResponseBody
	public BashijuResult saveCityCondition(String cityCode,String jsonData) {
		clientQueryConditionService.saveCityCondition(cityCode,jsonData);
		return BashijuResult.ok();
	}
	/**
	 * 
		 * 查询价格条件城市管理列表
		 * @Description: 查询价格条件城市管理列表 
		 * @param cityCode
		 * @param type
		 * @param page
		 * @param limit
		 * @return 
		 * Map<String,Object>
	 */
	@RequestMapping(value="queryCityAvgPriceList")
	@ResponseBody
	public Map<String,Object> queryCityAvgPriceList(String cityCode,Integer type,int page,int limit){
		Page<Map<String, Object>> pageInfo = clientQueryConditionService.queryCityAvgPriceList(cityCode,type, page, limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	/**
	 * 
		 * 新增均价查询条件
		 * @Description: 新增均价查询条件 
		 * @param jsonData
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping(value="addCityAvgPrice")
	@ResponseBody
	public BashijuResult addCityAvgPrice(String jsonData) {
		Integer result=clientQueryConditionService.addCityAvgPrice(jsonData);
		return BashijuResult.ok(result);
	}
	/**
	 * 
		 * 修改均价查询条件
		 * @Description: 修改均价查询条件
		 * @param jsonData
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping(value="updateCityAvgPrice")
	@ResponseBody
	public BashijuResult updateCityAvgPrice(String jsonData) {
		Integer result=clientQueryConditionService.updateCityAvgPrice(jsonData);
		return BashijuResult.ok(result);
	}
	/**
	 * 
		 * 删除均价查询条件
		 * @Description: 删除均价查询条件
		 * @param id
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping(value="deleteCityAvgPrice")
	@ResponseBody
	public BashijuResult deleteCityAvgPrice(Integer id) {
		Integer result=clientQueryConditionService.deleteCityAvgPrice(id);
		return BashijuResult.ok(result);
	}
}

