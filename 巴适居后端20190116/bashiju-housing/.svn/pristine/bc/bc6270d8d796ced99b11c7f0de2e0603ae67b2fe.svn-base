/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  HouseSeekByMapMapper.java   
 * @Package com.bashiju.housing.mapper   
 * @Description:地铁找房映射类
 * @author: zuoyuntao     
 * @date:   2018年6月7日 下午2:58:06   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.housing.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 地铁找房映射类
 * @ClassName:HouseSeekByMapMapper
 * @Description:地铁找房映射类
 * @author:zuoyuntao
 * @date:2018年6月7日 下午2:58:06
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface HouseSeekByMetroMapper {
	/**
	 * 查询地铁线路信息
	 * @Title: queryMetroLineInfo
	 * @author: zuoyuntao  
	 * @Description:查询地铁线路信息   
	 * @param paraMap 参数对象（预留）
	 * @return      
	 * List<Map<String,Object>> JSON 格式为：{}     
	 * @throws
	 */
	public List<Map<String,Object>> queryMetroLineInfo(Map<String,Object> paraMap);
	/**
	 * 查询地铁线路站点信息
	 * @Title: queryStationInfo
	 * @author: zuoyuntao  
	 * @Description:查询地铁线路站点信息   
	 * @param paraMap 参数对象（预留）
	 * @return      
	 * List<Map<String,Object>> JSON 格式为：{}     
	 * @throws
	 */
	public List<Map<String,Object>> queryStationInfo(Map<String,Object> paraMap);
	/**
	 * 统计小区层面的房源信息
	 * @Title: statisticsCommunityHouseInfoByConn
	 * @author: zuoyuntao  
	 * @Description:统计小区层面的房源信息   
	 * @param condition 其他条件
	 * @param paraMap 	参数集合
	 * @return      
	 * List<Map<String,String>> JSON 格式为：{}     
	 * @throws
	 */
	public List<Map<String,Object>> statisticsMetroCommunityHouseInfoByConn(
			@Param("condition") String condition,@Param("paraMap")Map<String,Object> paraMap);
	/**
	 * 统计小区层面的房源信息
	 * @Title: queryHouseSourceStatisticsInfo
	 * @author: zuoyuntao  
	 * @Description:统计小区层面的房源信息   
	 * @param condition 其他条件
	 * @param paraMap 	参数集合
	 * @return      
	 * List<Map<String,String>>  
	 * @throws
	 */
	public List<Map<String,Object>> queryHouseSourceStatisticsInfo(
			Map<String,Object> paraMap);
}
