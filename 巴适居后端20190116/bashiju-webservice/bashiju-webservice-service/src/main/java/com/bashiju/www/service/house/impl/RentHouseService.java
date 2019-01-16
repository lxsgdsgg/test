package com.bashiju.www.service.house.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bashiju.webservice.mapper.RentHouseMapper;
import com.bashiju.wutils.log.SystemServiceLog;
import com.bashiju.wutils.redis.ICacheService;
import com.bashiju.wutils.util.Md5EncryptUtils;
import com.bashiju.www.global.WebGlobal;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.input.house.HouseMapListParam;
import com.bashiju.www.pojo.service.input.house.HouseMapParam;
import com.bashiju.www.pojo.service.input.house.RentHouseParam;
import com.bashiju.www.pojo.service.out.house.GoodHouseListResult;
import com.bashiju.www.pojo.service.out.house.HouseListResult;
import com.bashiju.www.pojo.service.out.house.HouseMapListResult;
import com.bashiju.www.pojo.service.out.house.HouseMapPointResult;
import com.bashiju.www.service.comm.WebPageUtils;
import com.bashiju.www.service.house.IRentHouseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * 
 * 租房服务
 * @ClassName:  RentHouseService   
 * @Description:   租房服务
 * @author: wangkaifa
 * @date:   2018年8月10日 下午5:42:51       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@SystemServiceLog(sourceType="租房房源查询服务") 
@Service
public class RentHouseService implements IRentHouseService {
	@Autowired
	RentHouseMapper rentHouseMapper;
	@Autowired
	ICacheService cacheService;
	
	@SystemServiceLog(operationType="区域查询租房列表")
	@Override
	public WebPage<HouseListResult> queryRentHouseByArea(RentHouseParam param,int page,int limit) {
		if (param==null||StringUtils.isEmpty(param.getCityCode()) ) {
			return null;
		}
		String key="queryRentHouseByArea"+JSON.toJSONString(param)+page+limit;
		key=Md5EncryptUtils.md5Encrypt(key);
		String json=cacheService.getCache(key);
		if (StringUtils.isEmpty(json)) {
			PageHelper.startPage(page, limit);
			Page<HouseListResult> pageInfo=rentHouseMapper.queryRentHouseByArea(param);
			WebPage<HouseListResult> result=new WebPage<>(pageInfo);
			cacheService.saveCache(key, result, WebGlobal.LIST_EXPTIME);
			return result;
		}
		WebPage<HouseListResult> result=WebPageUtils.parseWebPage(json, HouseListResult.class);
		return result;
	}
	@SystemServiceLog(operationType="按地图线路查询租房列表")
	@Override
	public WebPage<HouseListResult> queryRentHouseByMetro(RentHouseParam param,int page,int limit) {
		if (param==null||StringUtils.isEmpty(param.getCityCode()) ||param.getMetroId()==null) {
			return null;
		}
		String key="queryRentHouseByMetro"+JSON.toJSONString(param)+page+limit;
		key=Md5EncryptUtils.md5Encrypt(key);
		String json=cacheService.getCache(key);
		if (StringUtils.isEmpty(json)) {
			PageHelper.startPage(page, limit);
			Page<HouseListResult> pageInfo=rentHouseMapper.queryRentHouseByMetro(param);
			WebPage<HouseListResult> result=new WebPage<>(pageInfo);
			cacheService.saveCache(key, result, WebGlobal.LIST_EXPTIME);
			return result;
		}
		WebPage<HouseListResult> result=WebPageUtils.parseWebPage(json, HouseListResult.class);
		return result;
	}
	@SystemServiceLog(operationType="租房地图找房区域房源数量统计")
	@Override
	public List<HouseMapPointResult> queryRentMapHouseArea(HouseMapParam param) {
		if (param==null||StringUtils.isEmpty(param.getCityCode()) ) {
			return null;
		}
		String key="queryRentMapHouseArea"+JSON.toJSONString(param);
		key=Md5EncryptUtils.md5Encrypt(key);
		String json=cacheService.getCache(key);
		if (StringUtils.isEmpty(json)) {
			if (StringUtils.isNotEmpty(param.getCommunityName())) {
				param.setCommunityName("%"+param.getCommunityName()+"%");
			}
			List<HouseMapPointResult> results=rentHouseMapper.queryRentMapHouseArea(param);
			cacheService.saveCache(key, results, WebGlobal.LIST_EXPTIME);
			return results;
		}
		List<HouseMapPointResult> results=JSON.parseArray(json, HouseMapPointResult.class);
		return results;
	}
	@SystemServiceLog(operationType="租房地图房片区房源数量统计")
	@Override
	public List<HouseMapPointResult> queryRentMapHouseRegion(HouseMapParam param) {
		if (param==null||StringUtils.isEmpty(param.getCityCode()) ) {
			return null;
		}
		String key="queryRentMapHouseRegion"+JSON.toJSONString(param);
		key=Md5EncryptUtils.md5Encrypt(key);
		String json=cacheService.getCache(key);
		if (StringUtils.isEmpty(json)) {
			if (StringUtils.isNotEmpty(param.getCommunityName())) {
				param.setCommunityName("%"+param.getCommunityName()+"%");
			}
			List<HouseMapPointResult> results=rentHouseMapper.queryRentMapHouseRegion(param);
			cacheService.saveCache(key, results, WebGlobal.LIST_EXPTIME);
			return results;
		}
		List<HouseMapPointResult> results=JSON.parseArray(json, HouseMapPointResult.class);
		return results;
	}
	@SystemServiceLog(operationType="租房地图找房小区房源数量统计")
	@Override
	public List<HouseMapPointResult> queryRentMapHouseCommunity(HouseMapParam param) {
		if (param==null||StringUtils.isEmpty(param.getCityCode()) ) {
			return null;
		}
		String key="queryRentMapHouseCommunity"+JSON.toJSONString(param);
		key=Md5EncryptUtils.md5Encrypt(key);
		String json=cacheService.getCache(key);
		if (StringUtils.isEmpty(json)) {
			if (StringUtils.isNotEmpty(param.getCommunityName())) {
				param.setCommunityName("%"+param.getCommunityName()+"%");
			}
			List<HouseMapPointResult> results=rentHouseMapper.queryRentMapHouseCommunity(param);
			cacheService.saveCache(key, results, WebGlobal.LIST_EXPTIME);
			return results;
		}
		List<HouseMapPointResult> results=JSON.parseArray(json, HouseMapPointResult.class);
		return results;
	}
	@SystemServiceLog(operationType="租房地图找房查询房源列表")
	@Override
	public WebPage<HouseMapListResult> queryRentHouseMapList(HouseMapListParam param,int page,int limit) {
		if (param==null||StringUtils.isEmpty(param.getCityCode())) {
			return null;
		}
		String key="queryRentHouseMapList"+JSON.toJSONString(param)+page+limit;
		key=Md5EncryptUtils.md5Encrypt(key);
		String json=cacheService.getCache(key);
		if (StringUtils.isEmpty(json)) {
			if (StringUtils.isNotEmpty(param.getCommunityName())) {
				param.setCommunityName("%"+param.getCommunityName()+"%");
			}
			PageHelper.startPage(page, limit);
			Page<HouseMapListResult> pageInfo=rentHouseMapper.queryRentHouseMapList(param);
			WebPage<HouseMapListResult> result=new WebPage<>(pageInfo);
			cacheService.saveCache(key, result, WebGlobal.LIST_EXPTIME);
			return result;
		}
		WebPage<HouseMapListResult> result=WebPageUtils.parseWebPage(json, HouseMapListResult.class);
		return result;
	}
	@SystemServiceLog(operationType="租房地图找房地铁线路站点统计房源数量")
	@Override
	public List<HouseMapPointResult> queryRentMapHouseMetro(HouseMapParam param) {
		if (param==null||StringUtils.isEmpty(param.getCityCode())||param.getMetroId()==null) {
			return null;
		}
		String key="queryRentMapHouseMetro"+JSON.toJSONString(param);
		key=Md5EncryptUtils.md5Encrypt(key);
		String json=cacheService.getCache(key);
		if (StringUtils.isEmpty(json)) {
			List<HouseMapPointResult> results=rentHouseMapper.queryRentMapHouseMetro(param);
			cacheService.saveCache(key, results, WebGlobal.LIST_EXPTIME);
			return results;
		}
		List<HouseMapPointResult> results=JSON.parseArray(json, HouseMapPointResult.class);
		return results;
	}
	@SystemServiceLog(operationType="租房地图找房地铁站点小区统计房源数量")
	@Override
	public List<HouseMapPointResult> queryRentMapHouseCommunityMetro(HouseMapParam param) {
		if (param==null||StringUtils.isEmpty(param.getCityCode())||param.getMetroId()==null) {
			return null;
		}
		String key="queryRentMapHouseCommunityMetro"+JSON.toJSONString(param);
		key=Md5EncryptUtils.md5Encrypt(key);
		String json=cacheService.getCache(key);
		if (StringUtils.isEmpty(json)) {
			if (StringUtils.isNotEmpty(param.getStationName())) {
				param.setCommunityName("%"+param.getStationName()+"%");
			}
			List<HouseMapPointResult> results=rentHouseMapper.queryRentMapHouseCommunityMetro(param);
			cacheService.saveCache(key, results, WebGlobal.LIST_EXPTIME);
			return results;
		}
		List<HouseMapPointResult> results=JSON.parseArray(json, HouseMapPointResult.class);
		return results;
	}
	@SystemServiceLog(operationType="租房地图找房地铁线路房源列表")
	@Override
	public WebPage<HouseMapListResult> queryRentHouseMapMetroList(HouseMapListParam param,int page,int limit) {
		if (param==null||StringUtils.isEmpty(param.getCityCode())||param.getMetroId()==null) {
			return null;
		}
		String key="queryRentHouseMapMetroList"+JSON.toJSONString(param)+page+limit;
		key=Md5EncryptUtils.md5Encrypt(key);
		String json=cacheService.getCache(key);
		if (StringUtils.isEmpty(json)) {
			if (StringUtils.isNotEmpty(param.getStationName())) {
				param.setStationName("%"+param.getStationName()+"%");
			}
			PageHelper.startPage(page, limit);
			Page<HouseMapListResult> pageInfo=rentHouseMapper.queryRentHouseMapMetroList(param);
			WebPage<HouseMapListResult> result=new WebPage<>(pageInfo);
			cacheService.saveCache(key, result, WebGlobal.LIST_EXPTIME);
			return result;
		}
		WebPage<HouseMapListResult> result=WebPageUtils.parseWebPage(json, HouseMapListResult.class);
		return result;
	}
	@SystemServiceLog(operationType="租房列表页好房推荐")
	@Override
	public WebPage<GoodHouseListResult> queryRentGoodHouses(String cityCode,int page,int limit) {
		if (StringUtils.isEmpty(cityCode)) {
			return null;
		}
		String key="queryRentGoodHouses"+cityCode+page+limit;
		key=Md5EncryptUtils.md5Encrypt(key);
		String json=cacheService.getCache(key);
		if (StringUtils.isEmpty(json)) {
			PageHelper.startPage(page, limit);
			Page<GoodHouseListResult> pageInfo=rentHouseMapper.queryRentGoodHouses(cityCode);
			WebPage<GoodHouseListResult> result=new WebPage<>(pageInfo);
			cacheService.saveCache(key, result, WebGlobal.LIST_EXPTIME);
			return result;
		}
		WebPage<GoodHouseListResult> result=WebPageUtils.parseWebPage(json, GoodHouseListResult.class);
		return result;
	}
	@SystemServiceLog(operationType="租房列表页小区名称查询")
	@Override
	public WebPage<HouseListResult> queryRentHouseByCoummunity(String cityCode, String communityName, int page,
			int limit) {	
		if (StringUtils.isEmpty(cityCode)||StringUtils.isEmpty(communityName)) {
			return new WebPage<HouseListResult>(new Page<HouseListResult>());
		}
		//communityName="%"+communityName+"%";
		PageHelper.startPage(page, limit);
		Page<HouseListResult> pageInfo=rentHouseMapper.queryRentHouseByCoummunity(cityCode, communityName);
		WebPage<HouseListResult> result=new WebPage<>(pageInfo);
		return result;
	}

}
