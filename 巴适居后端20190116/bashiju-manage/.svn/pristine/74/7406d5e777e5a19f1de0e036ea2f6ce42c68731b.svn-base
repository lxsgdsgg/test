package com.bashiju.manage.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bashiju.manage.service.IHouseTransactionTaxService;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;
/**
 * 
 *   首付房屋类型管理
 * @ClassName:  HouseTransactionTaxController   
 * @Description:  首付房屋类型管理
 * @author: wangkaifa
 * @date:   2018年12月28日 下午3:52:30       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
public class HouseTransactionTaxController {
	@Autowired
	private IHouseTransactionTaxService houseTransactionTaxService;
	
	/**
	 * 
		 * 查询首付房屋类型列表
		 * @Description: 查询首付房屋类型列表 
		 * @param cityCode
		 * @param page
		 * @param limit
		 * @return 
		 * Map<String,Object>
	 */
	@RequestMapping(value="queryHousePropertyListByCity")
	@ResponseBody
	public Map<String,Object> queryHousePropertyListByCity(String cityCode, int page, int limit){
		Page<Map<String, Object>> pageInfo = houseTransactionTaxService.queryHousePropertyListByCity(cityCode, page, limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	/**
	 * 
		 * 根据访问类型id查询首付契税列表
		 * @Description: 根据访问类型id查询首付契税列表 
		 * @param housePropertyId
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping(value="queryDeedTaxs")
	@ResponseBody
	public BashijuResult queryDeedTaxs(Integer housePropertyId) {
		Map<String, Object> result=houseTransactionTaxService.queryDeedTaxs(housePropertyId);
		return BashijuResult.ok(result);
	}
	/**
	 * 
		 * 新增首付房屋类型
		 * @Description: 新增首付房屋类型） 
		 * @param jsonData
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping(value="addHouseProperty")
	@ResponseBody
	public BashijuResult addHouseProperty(String jsonData) {
		Map<String, Object> houseProperty=JSON.parseObject(jsonData, Map.class);
		Integer result=houseTransactionTaxService.addHouseProperty(houseProperty);
		return BashijuResult.ok(result);
	}
	/**
	 * 
		 * 修改首付房屋类型
		 * @Description: 修改首付房屋类型 
		 * @param jsonData
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping(value="updateHouseProperty")
	@ResponseBody
	public BashijuResult updateHouseProperty(String jsonData) {
		Map<String, Object> houseProperty=JSON.parseObject(jsonData, Map.class);
		Integer result=houseTransactionTaxService.updateHouseProperty(houseProperty);
		return BashijuResult.ok(result);
	}
	/**
	 * 
		 * 删除首付房屋类型
		 * @Description: 删除首付房屋类型 
		 * @param id
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping(value="deleteHouseProperty")
	@ResponseBody
	public BashijuResult deleteHouseProperty(Integer id) {
		Boolean result=houseTransactionTaxService.deleteHouseProperty(id);
		return BashijuResult.ok(result);
	}
}
