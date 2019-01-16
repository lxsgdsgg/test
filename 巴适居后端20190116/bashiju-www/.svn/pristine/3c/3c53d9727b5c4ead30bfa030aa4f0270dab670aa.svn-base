package com.bashiju.www.service.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.input.house.SellHouseParam;
import com.bashiju.www.pojo.service.out.comm.AreaResult;
import com.bashiju.www.pojo.service.out.comm.MetroStationResult;
import com.bashiju.www.pojo.service.out.comm.RegionResult;
import com.bashiju.www.pojo.service.out.house.GoodHouseListResult;
import com.bashiju.www.pojo.service.out.house.HouseListResult;
import com.bashiju.www.service.api.IHouseSellListPageService;
import com.bashiju.www.service.comm.IAreaService;
import com.bashiju.www.service.comm.IMetroService;
import com.bashiju.www.service.house.IHouseService;

/**
 * 
 *  二手房列表页面接口实现
 * @ClassName:  HouseSellListPageService   
 * @Description:   二手房列表页面接口实现
 * @author: wangkaifa
 * @date:   2018年8月10日 上午11:25:44       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class HouseSellListPageService implements IHouseSellListPageService {
	@Autowired
	IHouseService houseService;
	@Autowired
	IAreaService areaService;
	@Autowired
	IMetroService metroService;
	
	@Override
	public WebPage<HouseListResult> queryHouseSellListByCoummunity(String communityName, int page, int limit) {
		WebPage<HouseListResult> result=houseService.queryHouseByCoummunity(communityName, 1, page, limit);
		return result;
	}

	@Override
	public List<AreaResult> queryAreaByCity(String cityCode) {
		List<AreaResult> results=areaService.queryAreaByCity(cityCode);
		return results;
	}

	@Override
	public List<RegionResult> queryRegionByArea(String cityCode, String areaCode) {
		List<RegionResult> results=areaService.queryRegionByArea(cityCode, areaCode);
		return results;
	}

	@Override
	public WebPage<HouseListResult> queryHouseSellListByArea(SellHouseParam param, int page, int limit) {
		WebPage<HouseListResult> result=houseService.querySellHouseListByArea(param, page, limit);
		return result;
	}

	@Override
	public List<String> queryMetroLines(String cityCode) {
		List<String> results=metroService.queryMetroLines(cityCode);
		return results;
	}

	@Override
	public List<MetroStationResult> queryMetroStationsByLine(String cityCode, String metroName) {
		List<MetroStationResult> results=metroService.queryMetroStationsByLine(cityCode, metroName);
		return results;
	}

	@Override
	public WebPage<HouseListResult> querySellHouseListByMetro(SellHouseParam param, int page, int limit) {
		WebPage<HouseListResult> result=houseService.querySellHouseListByMetro(param, page, limit);
		return result;
	}

	@Override
	public WebPage<GoodHouseListResult> querySellGoodHouse(String cityCode, int page, int limit) {
		WebPage<GoodHouseListResult> result=houseService.querySellGoodHouse(cityCode, page, limit);
		return result;
	}

}
