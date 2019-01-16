package com.bashiju.www.service.house;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.input.house.HouseMapListParam;
import com.bashiju.www.pojo.service.input.house.HouseMapParam;
import com.bashiju.www.pojo.service.input.house.RentHouseParam;
import com.bashiju.www.pojo.service.out.house.GoodHouseListResult;
import com.bashiju.www.pojo.service.out.house.HouseListResult;
import com.bashiju.www.pojo.service.out.house.HouseMapListResult;
import com.bashiju.www.pojo.service.out.house.HouseMapPointResult;

/**
 * 
 *  租房服务接口
 * @ClassName:  IRentHouseService   
 * @Description:  租房服务接口
 * @author: wangkaifa
 * @date:   2018年8月10日 下午5:33:39       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface IRentHouseService {
	/**
	 * 
		 * 根据小区名称模糊搜索租房房源
		 * @Description: 根据小区名称模糊搜索租房房源 
		 * @param communityName 小区名称
		 * @return 
		 * WebPage<HouseListResult>
	 */
	WebPage<HouseListResult> queryRentHouseByCoummunity(String cityCode,String communityName,int page,int limit);
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
	public WebPage<HouseListResult> queryRentHouseByArea(RentHouseParam param,int page,int limit);
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
	public WebPage<HouseListResult> queryRentHouseByMetro(RentHouseParam param,int page,int limit);
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
		 * @param param 查询参数
		 * @param page 当前页
		 * @param limit 每页条数
		 * @return 
		 * WebPage<HouseMapListResult>
	 */
	WebPage<HouseMapListResult> queryRentHouseMapList(HouseMapListParam param,int page,int limit);
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
		 * @param param 查询参数
		 * @param page 当前页
		 * @param limit 每页条数
		 * @return 
		 * WebPage<HouseMapListResult>
	 */
	WebPage<HouseMapListResult> queryRentHouseMapMetroList(HouseMapListParam param,int page,int limit);
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
