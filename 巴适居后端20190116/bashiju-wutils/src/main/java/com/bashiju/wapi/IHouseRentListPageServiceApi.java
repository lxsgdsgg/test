package com.bashiju.wapi;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bashiju.www.pojo.comm.QueryCondition;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.input.house.RentHouseParam;
import com.bashiju.www.pojo.service.out.comm.AreaResult;
import com.bashiju.www.pojo.service.out.comm.MetroLinesResult;
import com.bashiju.www.pojo.service.out.comm.MetroStationResult;
import com.bashiju.www.pojo.service.out.comm.RegionResult;
import com.bashiju.www.pojo.service.out.house.GoodHouseListResult;
import com.bashiju.www.pojo.service.out.house.HouseListResult;

/**
 * 
 *   租房列表页面接口
 * @ClassName:  IHouseRentListPageServiceApi   
 * @Description:   租房列表页面接口
 * @author: wangkaifa
 * @date:   2018年8月13日 下午3:35:06       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface IHouseRentListPageServiceApi {
	/**
	 * 
		 * 查询租房列表除区域、地铁外的搜索条件类
		 * @Description: 查询租房列表除区域、地铁外的搜索条件类
		 * @param cityCode 城市代码
		 * @return 
		 * List<QueryCondition>
	 */
	List<QueryCondition> queryConditionsSellHouse(String cityCode);
	/**
	 * 
		 * 根据小区名称模糊搜索租房房源
		 * @Description: 根据小区名称模糊搜索租房房源 
		 * @param communityName 小区名称
		 * @return 
		 * WebPage<HouseListResult>
	 */
	WebPage<HouseListResult> queryHouseRentListByCoummunity(String cityCode,String communityName,int page,int limit);
	/**
	 * 
		 * 根据城市查询区县
		 * @Description: 根据城市查询区县
		 * @param cityCode 城市代码
		 * @return [{name:五华区,code:530102},{name:官渡区,code:530104}]
		 * List<AreaResult>
	 */
	List<AreaResult> queryAreaByCity(String cityCode);
	/**
	 * 
		 * 根据区划码查询片区
		 * @Description: 根据区划码查询片区
		 * @param cityCode 城市代码
		 * @param areaCode 区划码
		 * @return [{name:大学城片区,id:1,areaCode:530102},{name:乌龙片区,id:2,areaCode:530102}]
		 * List<RegionResult>
	 */
	List<RegionResult> queryRegionByArea(String cityCode,String areaCode);
	/** 
	 * 
		 * 按区域查询租房列表
		 * @Description: 按区域查询租房列表 
		 * @param param 查询参数
		 * @param page 当前页
		 * @param limit 每页条数
		 * @return 
		 * WebPage<HouseListResult>
	 */
	WebPage<HouseListResult> queryRentHouseByArea(RentHouseParam param,int page,int limit);
	/**
	 * 
		 * 根据城市查询地铁线路
		 * @Description: 根据城市查询地铁线路
		 * @param cityCode 城市代码
		 * @return [{id:1,name:1号线},{id:2,name:2号线}]
		 * List<MetroLinesResult>
	 */
	List<MetroLinesResult> queryMetroLines(String cityCode);
	/**
	 * 
		 * 根据城市、线路查询地铁站点
		 * @Description: 查询地铁站点
		 * @param cityCode 城市代码
		 * @param lineId 地铁线路id
		 * @return [{stationName:大学城,id:1,lineId:1},{stationName:春融街,id:2,lineId:1}]
		 * List<MetroStationResult>
	 */
	List<MetroStationResult> queryMetroStationsByLine(String cityCode,int lineId);
	/**
	 * 
		 * 按地铁查询租房列表
		 * @Description: 按地铁查询租房列表 
		 * @param param 查询参数
		 * @param page 当前页
		 * @param limit 每页条数
		 * @return 
		 * WebPage<HouseListResult>
	 */
	WebPage<HouseListResult> queryRentHouseByMetro(RentHouseParam param,int page,int limit);
	/**
	 * 
		 * 租房列表好房推荐查询
		 * @Description: 租房列表好房推荐查询 
		 * @param cityCode 城市代码
		 * @param page 当前页
		 * @param limit 每页条数
		 * @return 
		 * Page<GoodHouseListResult>
	 */
	WebPage<GoodHouseListResult> queryRentGoodHouses(@Param("cityCode")String cityCode,int page,int limit);
}
