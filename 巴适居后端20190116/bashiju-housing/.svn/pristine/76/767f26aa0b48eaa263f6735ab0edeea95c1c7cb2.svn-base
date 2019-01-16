/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  IHouseSeekByMapService.java   
 * @Package com.bashiju.housing.service   
 * @Description:地铁找房服务层接口   
 * @author: zuoyuntao     
 * @date:   2018年6月7日 下午2:58:55   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.housing.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**
 * 地铁找房服务层接口
 * @ClassName:IHouseSeekByMapService
 * @Description:地铁找房服务层接口
 * @author:zuoyuntao
 * @date:2018年6月7日 下午2:58:55
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface IHouseSeekByMetroService {
	/**
	 * 统计小区层面的房源信息
	 * @Title: queryStatisticsCommunityHouseInfo
	 * @author: zuoyuntao  
	 * @Description:统计小区层面的房源信息
	 * @param paraMap
	 * @return      
	 * List<Map<String,Object>> JSON 格式为：{}     
	 * @throws
	 */
	public List<Map<String, Object>> queryStatisticsCommunityHouseInfo(Map<String,Object> paraMap);
	/**
	 * 统计地铁站点层面的房源信息
	 * @Title: queryHouseSourceStatisticsInfo
	 * @author: zuoyuntao  
	 * @Description:统计地铁站点层面的房源信息
	 * @param paraMap
	 * @return      
	 * List<Map<String,Object>>    
	 * @throws
	 */
	public List<Map<String, Object>> queryHouseSourceStatisticsInfo(Map<String,Object> paraMap);
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
	 * 获取房源明细数据集合
	 * @Title: queryHouseSourceDataList
	 * @author: zuoyuntao  
	 * @Description:获取房源数据集合   
	 * @param paraMap
	 * @param page 最小条数
	 * @param limit 最大条数
	 * @return      
	 * Page<Map<String,Object>> JSON 格式为：{}     
	 * @throws
	 */
	public Page<Map<String,Object>> queryHouseSourceDataListForMetro(Map<String,Object> paraMap,int page,int limit);
}
