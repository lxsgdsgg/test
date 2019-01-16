package com.bashiju.www.service.comm.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bashiju.api.RedisServiceApi;
import com.bashiju.wutils.log.SystemServiceLog;
import com.bashiju.www.global.WebGlobal;
import com.bashiju.www.mapper.IAreaMapper;
import com.bashiju.www.pojo.service.out.comm.AreaResult;
import com.bashiju.www.pojo.service.out.comm.RegionResult;
import com.bashiju.www.service.comm.IAreaService;
import com.bashiju.www.service.comm.ICacheService;
import com.github.pagehelper.util.StringUtil;
/**
 * 
 * @ClassName:  IAreaServiceImpl   
 * @Description:TODO(行政区域、片区服务具体实现)   
 * @author: liwen
 * @date:   2018年7月25日 上午11:44:03   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@SystemServiceLog(sourceType="行政区域、片区服务") 
@Service
public class AreaServiceImpl implements IAreaService {
	 
	@Autowired(required=false)
	RedisServiceApi redisServiceApi;
	
	@Autowired
	IAreaMapper iAreaMapper ;
	@Autowired
	ICacheService cacheService;
	
	/**
	 * 
		 * 根据城市查询区县
		 *  先从redis中取值，没有再查询数据库并存入redis
		 *  redis的key为前缀(WebGlobal.AREA_ITEM_REDIS_PREFIX)+城市代码
		 * value为区县值json字符串[{name:五华区,code:530102},{name:官渡区,code:530104}]
		 * @Description: 根据城市查询区县
		 * @param cityCode 城市代码
		 * @return 
		 * List<AreaResult>
	 */
	public List<AreaResult> queryAreaByCity(String cityCode){
		String key = WebGlobal.AREA_ITEM_REDIS_PREFIX+cityCode;
		String areaStr = cacheService.getCache(key);
		List<AreaResult> areaList = null;
		if(StringUtil.isEmpty(areaStr)) {
			areaList = iAreaMapper.queryAreas(cityCode);
			cacheService.saveCache(key, areaList,WebGlobal.OTHER_EXPTIME);
		}else {
			areaList = JSON.parseArray(areaStr, AreaResult.class);
		}
		return areaList;
	}
	
	/**
	 * 
		 * 根据区划码查询片区
		 * 先从redis中取值，没有再查询数据库并存入redis
		 *  redis的key为前缀(WebGlobal.REGION_ITEM_REDIS_PREFIX)+城市代码,
		 * value为片区值json字符串[{name:大学城,id:1,areaCode:530102},{name:乌龙片区,id:2,areaCode:530102}]
		 * @Description: 根据区划码查询片区
		 * @param cityCode 城市代码
		 * @param areaCode 区划码
		 * @return 
		 * List<RegionResult>
	 */
	public List<RegionResult> queryRegionByArea(String cityCode,String areaCode){
		String key = WebGlobal.REGION_ITEM_REDIS_PREFIX+cityCode;
		String regionStr = cacheService.getCache(key);
		List<RegionResult> regionList = null;
		if(StringUtil.isEmpty(regionStr)) {
			regionList = iAreaMapper.queryRegions(cityCode);
			cacheService.saveCache(key,regionList, WebGlobal.OTHER_EXPTIME);
		}else {
			 regionList = JSON.parseArray(regionStr, RegionResult.class);
		}
		
		List<RegionResult> getres = new ArrayList<>();
		for(RegionResult r : regionList) {
			if(r.getAreaCode().equals(areaCode)) {
				getres.add(r);
			}
		}
		
		return getres;
	}
}
