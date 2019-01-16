package com.bashiju.housing.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bashiju.housing.service.IBuildingService;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;

/**
 * 
 * @ClassName:  BuildingController   
 * @Description:TODO(座栋管理)   
 * @author: zhaobin
 * @date:   2018年4月25日 下午12:00:26   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping("building")
public class BuildingController {
	@Autowired
	private IBuildingService buildingService;
	
	/**
	 * 
	 * @Title: queryBuildingsByCommunity   
	 * @Description: TODO(根据小区id查询座栋列表)   
	 * @param: @param cid 小区id
	 * @param: @param page
	 * @param: @param limit
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	@RequestMapping(value="queryBuildingList")
	@ResponseBody
	public Map<String,Object> queryBuildingsByCommunity(Long cid,int page,int limit){
		Page<Map<String, Object>> pageInfo = buildingService.queryBuildingsByCommunity(cid, page, limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	/**
	 * 
	 * @Title: queryBuildingUnitsByBuild   
	 * @Description: TODO(根据座栋id查询单元列表)   
	 * @param: @param bid 座栋id
	 * @param: @param page
	 * @param: @param limit
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	@RequestMapping(value="queryBuildingUnitList")
	@ResponseBody
	public Map<String,Object> queryBuildingUnitsByBuild(Long bid,int page,int limit){
		Page<Map<String, Object>> pageInfo = buildingService.queryBuildingUnitsByBuild(bid, page, limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	/**
	 * 
	 * @Title: queryHouseByUnit   
	 * @Description: TODO(根据单元id查询房号列表)   
	 * @param: @param buid 单元id
	 * @param: @return      
	 * @return: BashijuResult      
	 * @throws
	 */
	@RequestMapping(value="queryHouses")
	@ResponseBody
	public BashijuResult  queryHouseByUnit(Long buid){
		List<Map<String, Object>> list=buildingService.queryHouseByUnit(buid);
		return BashijuResult.build(true, "success", list);
	}
	
	
}
