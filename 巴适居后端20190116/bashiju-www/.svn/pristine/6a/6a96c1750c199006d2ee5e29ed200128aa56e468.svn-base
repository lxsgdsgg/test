package com.bashiju.www.service.api;

import java.util.List;

import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.input.house.HouseMapListParam;
import com.bashiju.www.pojo.service.input.house.HouseMapParam;
import com.bashiju.www.pojo.service.out.house.HouseMapListResult;
import com.bashiju.www.pojo.service.out.house.HouseMapPointResult;

/**
 * 
 *  二手房地铁找房页面接口
 * @ClassName:  IMapHouseSellPageService   
 * @Description:   二手房地铁找房页面接口
 * @author: wangkaifa
 * @date:   2018年8月10日 上午9:44:38       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface IMapHouseSellPageService {
	/**
	 * 
		 * 二手房地图找房，区域房源数量统计
		 * @Description: 二手房地图找房，区域房源数量统计
		 * @param param
		 * @return [{code:530102, name:五华区, total:1, longitude:102.721771, latitude:25.055556}]
		 * List<HouseMapPointResult>
	 */
	List<HouseMapPointResult> querySellMapHouseArea(HouseMapParam param);
	
	/**
	 * 
		 * 二手房地图找房，片区房源数量统计
		 * @Description: 二手房地图找房，片区房源数量统计
		 * @param param
		 * @return [{code:4, name:丰宁片区, total:1, longitude:102.678206, latitude:25.055008}]
		 * List<HouseMapPointResult>
	 */
	List<HouseMapPointResult> querySellMapHouseRegion(HouseMapParam param);
	/**
	 * 
		 * 二手房地图找房，小区房源数量统计
		 * @Description: 二手房地图找房，小区房源数量统计
		 * @param param
		 * @return [{code:4, name:正大紫都, total:1, longitude:102.67681, latitude:25.056682}]
		 * List<HouseMapPointResult>
	 */
	List<HouseMapPointResult> querySellMapHouseCommunity(HouseMapParam param);
	
	/**
	 * 
		 * 二手房地图找房,按区域查询房源列表
		 * @Description: 二手房地图找房,按区域查询房源列表
		 * @param param
		 * @return 
		 * WebPage<HouseMapListResult>
	 */
	WebPage<HouseMapListResult> querySellHouseMapList(HouseMapListParam param, int page, int limit);
	/**
	 * 
		 * 根据城市查询地铁线路
		 * @Description: 根据城市查询地铁线路
		 * @param cityCode 城市代码
		 * @return [1号线,2号线]
		 * List<String>
	 */
	List<String> queryMetroLines(String cityCode);
	/**
	 * 
		 * 二手房地图找房,地铁线路房源统计
		 * @Description:  二手房地图找房,地铁线路房源统计
		 * @param param 
		 * @return [{code:56, name:昆明火车站, total:1, longitude:102.730191, latitude:25.019725},{code:57, name:福德, total:0, longitude:102.736805, latitude:25.013173},{code:58, name:日新路, total:0, longitude:102.741443, latitude:25.007627}]
		 * List<HouseMapPointResult>
	 */
	List<HouseMapPointResult> querySellMapHouseMetro(HouseMapParam param);
	/**
	 * 
		 * 二手房地图找房,地铁线路小区房源统计
		 * @Description: 二手房地图找房,地铁线路小区房源统计 
		 * @param param
		 * @return [{code:4, name:正大紫都, total:1, longitude:102.67681, latitude:25.056682}]
		 * List<HouseMapPointResult>
	 */
	List<HouseMapPointResult> querySellMapHouseCommunityMetro(HouseMapParam param);
	/**
	 * 
		 * 二手房地图找房,地铁线路查询房源列表
		 * @Description: 二手房地图找房,地铁线路查询房源列表
		 * @param param
		 * @return 
		 * WebPage<HouseMapListResult>
	 */
	WebPage<HouseMapListResult> querySellHouseMapMetroList(HouseMapListParam param, int page, int limit);
}
