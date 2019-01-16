package com.bashiju.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bashiju.manage.service.IBuildingService;
import com.bashiju.utils.exception.BusinessException;
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
	/**
	 * 
	 * @Title: queryBuildingsById   
	 * @Description: TODO(根据id查询座栋信息)   
	 * @param: @param id
	 * @param: @return      
	 * @return: BashijuResult      
	 * @throws
	 */
	@RequestMapping(value="queryBuilding")
	@ResponseBody
	public BashijuResult  queryBuildingsById(Long id){
		Map<String, Object> map=buildingService.queryBuildingsById(id);
		return BashijuResult.build(true, "success", map);
	}
	/**
	 * 
	 * @Title: queryBuildingUnitsById   
	 * @Description: TODO(根据id查询单元信息)   
	 * @param: @param id
	 * @param: @return      
	 * @return: BashijuResult      
	 * @throws
	 */
	@RequestMapping(value="queryBuildingUnit")
	@ResponseBody
	public BashijuResult  queryBuildingUnitsById(Long id){
		Map<String, Object> map=buildingService.queryBuildingUnitsById(id);
		return BashijuResult.build(true, "success", map);
	}
	/**
	 * 
	 * @Title: addBuilding   
	 * @Description: TODO(添加座栋)   
	 * @param: @param dataJson
	 * @param: @return      
	 * @return: BashijuResult      
	 * @throws
	 */
	@RequestMapping(value="addBuilding")
	@ResponseBody
	public BashijuResult  addBuilding(String dataJson,String areaCode,String areaName,Long communityId){
		if (StringUtils.isNotEmpty(dataJson)) {
			Map<String, Object> map = JSON.parseObject(dataJson, Map.class);
			map.put("communityId", communityId);
			map.put("areaCode", areaCode);
			map.put("areaName", areaName);
			buildingService.addBuilding(map);
			return BashijuResult.ok();
		}
		throw new BusinessException("参数错误");
	}
	/**
	 * 
	 * @Title: batchAddBuilding   
	 * @Description: TODO(批量添加座栋)   
	 * @param: @param dataJson
	 * @param: @return      
	 * @return: BashijuResult      
	 * @throws
	 */
	@RequestMapping(value="batchAddBuilding")
	@ResponseBody
	public BashijuResult  batchAddBuilding(String dataJson,String areaCode,String areaName,Long communityId){
		if (StringUtils.isNotEmpty(dataJson)) {
			Map<String, Object> map = JSON.parseObject(dataJson, Map.class);
			map.put("communityId", communityId);
			map.put("areaCode", areaCode);
			map.put("areaName", areaName);
			buildingService.batchAddBuilding(map);
			return BashijuResult.ok();
		}
		throw new BusinessException("参数错误");
	}
	
	@RequestMapping(value="updateBuilding")
	@ResponseBody
	public BashijuResult  updateBuilding(String dataJson){
		if (StringUtils.isNotEmpty(dataJson)) {
			Map<String, Object> map = JSON.parseObject(dataJson, Map.class);
			buildingService.updateBuilding(map);
			return BashijuResult.ok();
		}
		throw new BusinessException("参数错误");
	}
	@RequestMapping(value="updateBuildingUnit")
	@ResponseBody
	public BashijuResult  updateBuildingUnit(String dataJson){
		if (StringUtils.isNotEmpty(dataJson)) {
			Map<String, Object> map = JSON.parseObject(dataJson, Map.class);
			buildingService.updateBuildingUnit(map);
			return BashijuResult.ok();
		}
		throw new BusinessException("参数错误");
	}
	
	@RequestMapping(value="addBuildingUnit")
	@ResponseBody
	public BashijuResult  addBuildingUnit(String dataJson){
		if (StringUtils.isNotEmpty(dataJson)) {
			Map<String, Object> map = JSON.parseObject(dataJson, Map.class);
			buildingService.addBuildingUnit(map);
			return BashijuResult.ok();
		}
		throw new BusinessException("参数错误");
	}
	@RequestMapping(value="saveHouseName")
	@ResponseBody
	public BashijuResult  saveHouseName(String dataJson){
		if (StringUtils.isNotEmpty(dataJson)) {
			Map<String, Object> map = JSON.parseObject(dataJson, Map.class);
			buildingService.saveHouses(map);
			return BashijuResult.ok();
		}
		throw new BusinessException("参数错误");
	}
	/**
	 * 
		 * 根据单元id查询房号，按照楼层拼接
		 * @Description: 根据单元id查询房号，按照楼层拼接 
		 * @param buildingUnitId 单元id
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping(value="queryBuildingHouseByUnitId")
	@ResponseBody
	public BashijuResult  queryBuildingHouseByUnitId(Long buildingUnitId){
		List<Map<String, Object>> list=buildingService.queryBuildingHouseByUnitId(buildingUnitId);
		return BashijuResult.ok(list);
	}
}
