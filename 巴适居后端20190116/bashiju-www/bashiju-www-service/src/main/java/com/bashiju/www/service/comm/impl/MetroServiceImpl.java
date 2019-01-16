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
import com.bashiju.www.mapper.IFormMapper;
import com.bashiju.www.mapper.IMetroMapper;
import com.bashiju.www.pojo.service.out.comm.AreaResult;
import com.bashiju.www.pojo.service.out.comm.MetroStationResult;
import com.bashiju.www.pojo.service.out.comm.RangeResult;
import com.bashiju.www.pojo.service.out.comm.RegionResult;
import com.bashiju.www.pojo.service.out.comm.SelectItemResult;
import com.bashiju.www.service.comm.ICacheService;
import com.bashiju.www.service.comm.IMetroService;
import com.github.pagehelper.util.StringUtil;
/**
 * 
 * @ClassName:  IMetroServiceImpl   
 * @Description:TODO(地铁服务实现)   
 * @author: liwen
 * @date:   2018年7月25日 上午11:44:03   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@SystemServiceLog(sourceType="地铁线路") 
@Service
public class MetroServiceImpl implements IMetroService{
	 
	@Autowired(required=false)
	RedisServiceApi redisServiceApi;
	
	@Autowired
	IMetroMapper iMetroMapper ;
	
	@Autowired
	ICacheService cacheService;

	@Override
	public List<String> queryMetroLines(String cityCode) {
		String key = WebGlobal.METRO_LINE_REDIS_PREFIX+cityCode;
		String lineStr = cacheService.getCache(key);
		List<String> lineList = null;
		if(StringUtil.isEmpty(lineStr)) {
			lineList = iMetroMapper.queryLines(cityCode);
			cacheService.saveCache(key,lineList,WebGlobal.OTHER_EXPTIME);
		}else {
			lineList = JSON.parseArray(lineStr, String.class);
		}
		return lineList;
	}

	@Override
	public List<MetroStationResult> queryMetroStationsByLine(String cityCode, String metroName) {
		String key = WebGlobal.METRO_STATION_REDIS_PREFIX+cityCode;
		String stationStr = cacheService.getCache(key);
		List<MetroStationResult> stationList = null;
		if(StringUtil.isEmpty(stationStr)) {
			stationList = iMetroMapper.queryStations(cityCode);
			cacheService.saveCache(key,stationList,WebGlobal.OTHER_EXPTIME);
		}else {
			stationList = JSON.parseArray(stationStr, MetroStationResult.class);
		}
		List<MetroStationResult> getres = new ArrayList<>();
		for(MetroStationResult m : stationList) {
			if(m.getMetroName().equals(metroName)) {
				getres.add(m);
			}
		}
		
		return getres;
	}
	
	
}
