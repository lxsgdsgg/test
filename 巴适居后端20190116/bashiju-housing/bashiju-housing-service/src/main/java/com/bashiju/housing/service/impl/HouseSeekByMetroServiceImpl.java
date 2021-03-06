/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  HouseSeekByMapServiceImpl.java   
 * @Package com.bashiju.housing.service.impl   
 * @Description:地铁找房服务层对象
 * @author: zuoyuntao     
 * @date:   2018年6月7日 下午3:02:39   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.housing.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.MenuEnum;
import com.bashiju.housing.mapper.HouseSeekByMetroMapper;
import com.bashiju.housing.service.IHouseSeekByMetroService;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;

/**
 * 地铁找房服务层对象
 * @ClassName:HouseSeekByMapServiceImpl
 * @Description:地铁找房服务层对象
 * @author:zuoyuntao
 * @date:2018年6月7日 下午3:02:39
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
@SystemServiceLog(sourceType="地铁找房")
public class HouseSeekByMetroServiceImpl extends CommonSqlServie implements IHouseSeekByMetroService {
	/**
	 * 地铁找房映射类
	 */
	@Autowired
	private HouseSeekByMetroMapper mHouseSeekByMetroMapper;
	/**
	 * 权限接口
	 */
	@Autowired
	private DataAuthHelper mDataAuthHelper;
	/**
	 * <p>Title: queryStatisticsCommunityHouseInfo</p>   
	 * <p>Description: 根据小区、地铁站点统计房源</p>   
	 * @param paraMap
	 * @return   
	 * @see com.bashiju.housing.service.IHouseSeekByMetroService#statisticsCommunityHouseInfo(java.util.Map)
	 */
	@Override
	@SystemServiceLog(operationType="统计房源")
	public List<Map<String, Object>> queryStatisticsCommunityHouseInfo(Map<String,Object> paraMap){
		mDataAuthHelper.auth(MenuEnum.MENU_86.getCode(), UserThreadLocal.get().get("id").toString());
		//添加地铁小区条件
		List<Map<String, Object>> retList 
			= mHouseSeekByMetroMapper.statisticsMetroCommunityHouseInfoByConn(null, paraMap);
		return retList;
	}
	/**
	 * <p>Title: queryHouseSourceStatisticsInfo</p>   
	 * <p>Description: 根据小区、地铁站点统计房源</p>   
	 * @param paraMap
	 * @return   
	 * @see com.bashiju.housing.service.IHouseSeekByMetroService#statisticsCommunityHouseInfo(java.util.Map)
	 */
	@Override
	@SystemServiceLog(operationType="统计房源")
	public List<Map<String, Object>> queryHouseSourceStatisticsInfo(Map<String,Object> paraMap){
		mDataAuthHelper.auth(MenuEnum.MENU_86.getCode(), UserThreadLocal.get().get("id").toString());
		paraMap.put("roleGroup", UserThreadLocal.get().get("roleGroup"));
		//添加地铁小区条件
		List<Map<String, Object>> retList = mHouseSeekByMetroMapper.queryHouseSourceStatisticsInfo(paraMap);
		return retList;
	}
	
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
	public Page<Map<String,Object>> queryHouseSourceDataListForMetro(Map<String,Object> paraMap,int page,int limit){
		PageHelper.startPage(page,limit);
		mDataAuthHelper.auth(MenuEnum.MENU_86.getCode(), UserThreadLocal.get().get("id").toString());
		List<Map<String, Object>> retList 
			= mHouseSeekByMetroMapper.statisticsMetroCommunityHouseInfoByConn(null, paraMap);
		if(retList.size() > 0) {
			return (Page<Map<String, Object>>) retList;
		}
		return null;
	}
	/**
	 * 组装查询语句
	 * @Title: buildConditionSql
	 * @author: zuoyuntao  
	 * @Description:组装查询语句   
	 * @param paraMap
	 * @return      
	 * String 
	 */
	@SuppressWarnings("unused")
	private String buildConditionSql(Map<String,Object> paraMap) {
		StringBuilder conditionSql = new StringBuilder();
		Iterator<String> it = paraMap.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			String value = (String) paraMap.get(key);
			if(StringUtil.isEmpty(value)) {
				continue;
			}
			conditionSql.append(" and ")
						.append(key)
						.append(" = '")
						.append(paraMap.get(key))
						.append("'");
		}
		return conditionSql.toString();
	}
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
	@Override
	public List<Map<String,Object>> queryMetroLineInfo(Map<String,Object> paraMap){
		mDataAuthHelper.auth(MenuEnum.MENU_86.getCode(), UserThreadLocal.get().get("id").toString());
		return mHouseSeekByMetroMapper.queryMetroLineInfo(paraMap);
	}
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
	@Override
	public List<Map<String,Object>> queryStationInfo(Map<String,Object> paraMap){
//		mDataAuthHelper.auth(MenuEnum.MENU_86.getCode(), UserThreadLocal.get().get("id").toString());
		return mHouseSeekByMetroMapper.queryStationInfo(paraMap);
	}
}
