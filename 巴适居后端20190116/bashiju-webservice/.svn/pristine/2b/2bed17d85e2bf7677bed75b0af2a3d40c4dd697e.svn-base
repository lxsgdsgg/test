package com.bashiju.www.service.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.enums.pc.ConditionTypeEnum;
import com.bashiju.wapi.INewHouseListPageApi;
import com.bashiju.www.pojo.comm.QueryCondition;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.input.newhouse.NewHouseQueryParam;
import com.bashiju.www.pojo.service.out.comm.AreaResult;
import com.bashiju.www.pojo.service.out.comm.RegionResult;
import com.bashiju.www.pojo.service.out.newhouse.HotNewHouseListResult;
import com.bashiju.www.pojo.service.out.newhouse.NewHouseListResult;
import com.bashiju.www.service.comm.IAreaService;
import com.bashiju.www.service.comm.ICommService;
import com.bashiju.www.service.newhouse.INewHouseService;

/**
 * 
 *   新房楼盘列表页接口实现
 * @ClassName:  NewHouseListPageApi   
 * @Description:   新房楼盘列表页接口实现
 * @author: wangkaifa
 * @date:   2018年8月21日 下午5:43:42       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class NewHouseListPageApiImpl implements INewHouseListPageApi {
	@Autowired
	ICommService commService;
	@Autowired
	IAreaService areaService;
	@Autowired
	INewHouseService newHouseService;
	
	@Override
	public List<QueryCondition> queryConditionsSellHouse(String cityCode) {
		List<QueryCondition> results=commService.queryQueryConditions(cityCode, ConditionTypeEnum.NEWHOUSE);
		return results;
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
	public List<HotNewHouseListResult> queryHotProjects(String cityCode, int limit) {
		List<HotNewHouseListResult> results=newHouseService.queryHotProjects(cityCode, limit);
		return results;
	}

	@Override
	public WebPage<NewHouseListResult> queryNewHouseListByName(String cityCode, String name, int page, int limit) {
		WebPage<NewHouseListResult> result=newHouseService.queryNewHouseListByName(cityCode, name, page, limit);
		return result;
	}

	@Override
	public WebPage<NewHouseListResult> queryNewHouseList(NewHouseQueryParam param, int page, int limit) {
		WebPage<NewHouseListResult> result=newHouseService.queryNewHouseList(param, page, limit);
		return result;
	}

}
