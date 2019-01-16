package com.bashiju.webservice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bashiju.www.pojo.service.input.house.HouseMapListParam;
import com.bashiju.www.pojo.service.input.house.HouseMapParam;
import com.bashiju.www.pojo.service.input.house.RentHouseParam;
import com.bashiju.www.pojo.service.input.house.SellHouseParam;
import com.bashiju.www.pojo.service.out.house.GoodHouseListResult;
import com.bashiju.www.pojo.service.out.house.HouseListResult;
import com.bashiju.www.pojo.service.out.house.HouseMapListResult;
import com.bashiju.www.pojo.service.out.house.HouseMapPointResult;
import com.github.pagehelper.Page;

/**
 * 
 *   租房
 * @ClassName:  RentHouseMapper   
 * @Description:   租房
 * @author: wangkaifa
 * @date:   2018年8月10日 下午2:24:50       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface RentHouseMapper {
	/** 
		 * 按区域查询租房列表
		 * @Description: 按区域查询租房列表
		 * @param param
		 * @return 
		 * Page<HouseListResult>
	 */
	public Page<HouseListResult> queryRentHouseByArea(RentHouseParam param);
	/**
	 * 
		 * 按地铁查询租房列表
		 * @Description: 按地铁查询租房列表
		 * @param param
		 * @return 
		 * Page<HouseListResult>
	 */
	public Page<HouseListResult> queryRentHouseByMetro(RentHouseParam param);
	/**
	 * 
		 * 地图找房查询区域信息
		 * @Description: 地图找房查询区域信息
		 * @param param
		 * @return 
		 * List<HouseMapPointResult>
	 */
	List<HouseMapPointResult> queryRentMapHouseArea(HouseMapParam param);
	
	/**
	 * 
		 * 地图找房查询片区信息
		 * @Description: 地图找房查询片区信息
		 * @param param
		 * @return 
		 * List<HouseMapPointResult>
	 */
	List<HouseMapPointResult> queryRentMapHouseRegion(HouseMapParam param);
	/**
	 * 
		 * 地图找房查询小区信息
		 * @Description: 地图找房查询小区信息
		 * @param param
		 * @return 
		 * List<HouseMapPointResult>
	 */
	List<HouseMapPointResult> queryRentMapHouseCommunity(HouseMapParam param);
	/**
	 * 
		 * 租房地图找房列表查询
		 * @Description: 租房地图找房列表查询
		 * @param param
		 * @return 
		 * Page<HouseMapListResult>
	 */
	Page<HouseMapListResult> queryRentHouseMapList(HouseMapListParam param);
	/**
	 * 
		 * 地图找房租房地铁线路房源统计
		 * @Description: 地图找房租房地铁线路房源统计 
		 * @param param
		 * @return 
		 * List<HouseMapPointResult>
	 */
	List<HouseMapPointResult> queryRentMapHouseMetro(HouseMapParam param);
	/**
	 * 
		 * 地图找房租房地铁线路小区房源统计
		 * @Description: 地图找房租房地铁线路小区房源统计 
		 * @param param
		 * @return 
		 * List<HouseMapPointResult>
	 */
	List<HouseMapPointResult> queryRentMapHouseCommunityMetro(HouseMapParam param);
	/**
	 * 
		 * 地图找房租房地铁线路房源列表查询
		 * @Description: 地图找房租房地铁线路房源列表查询
		 * @param param
		 * @return 
		 * Page<HouseMapListResult>
	 */
	Page<HouseMapListResult> queryRentHouseMapMetroList(HouseMapListParam param);
	/**
	 * 
		 * 租房列表好房推荐查询
		 * @Description: 租房列表好房推荐查询 
		 * @param cityCode
		 * @return 
		 * Page<GoodHouseListResult>
	 */
	Page<GoodHouseListResult> queryRentGoodHouses(@Param("cityCode")String cityCode);
	/**
	 * 
		 * 根据小区名称搜索房源
		 * @Description: 根据小区名称搜索房源
		 * @param cityCode 城市代码
		 * @param communityName 小区名称
		 * @return 
		 * Page<HouseListResult>
	 */
	Page<HouseListResult> queryRentHouseByCoummunity(@Param("cityCode")String cityCode,@Param("communityName")String communityName);
}
