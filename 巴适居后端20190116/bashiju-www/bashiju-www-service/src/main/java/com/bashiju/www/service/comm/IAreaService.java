package com.bashiju.www.service.comm;
/**
 * 
 *   行政区域、片区服务
 * @ClassName:  IAreaService   
 * @Description:   行政区域、片区服务
 * @author: wangkaifa
 * @date:   2018年7月23日 下午5:55:28       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

import java.util.List;

import com.bashiju.www.pojo.service.out.comm.AreaResult;
import com.bashiju.www.pojo.service.out.comm.RegionResult;

public interface IAreaService{
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
	List<AreaResult> queryAreaByCity(String cityCode);
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
	List<RegionResult> queryRegionByArea(String cityCode,String areaCode);
}
