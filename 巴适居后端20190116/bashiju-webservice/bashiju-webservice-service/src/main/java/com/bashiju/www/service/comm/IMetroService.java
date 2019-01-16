package com.bashiju.www.service.comm;
/**
 * 
 *   地铁服务
 * @ClassName:  IMetroService   
 * @Description:   地铁服务
 * @author: wangkaifa
 * @date:   2018年7月24日 上午11:57:58       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

import java.util.List;

import com.bashiju.www.pojo.service.out.comm.MetroLinesResult;
import com.bashiju.www.pojo.service.out.comm.MetroStationResult;

public interface IMetroService {
	/**
	 * 
		 * 根据城市查询地铁线路
		 * 先从redis中取值，没有再查询数据库并存入redis
		 * redis的key为前缀(WebGlobal.METRO_LINE_REDIS_PREFIX)+城市代码,
		 * value为地铁线路值json字符串[1号线,2号线]
		 * @Description: 根据城市查询地铁线路
		 * @param cityCode 城市代码
		 * @return 
		 * List<String>
	 */
	List<MetroLinesResult> queryMetroLines(String cityCode);
	/**
	 * 
		 * 根据城市、线路查询地铁站点
		 * 先从redis中取值，没有再查询数据库并存入redis
		 *  redis的key为前缀(WebGlobal.METRO_STATION_REDIS_PREFIX)+城市代码,
		 *  value为地铁线路值json字符串[{name:大学城,id:1,metroName:1号线},{name:春融街,id:2,metroName:1号线}]
		 * @Description: 查询地铁站点
		 * @param cityCode
		 * @param metroName
		 * @return 
		 * List<MetroStationResult>
	 */
	List<MetroStationResult> queryMetroStationsByLine(String cityCode,int lineId);
}
