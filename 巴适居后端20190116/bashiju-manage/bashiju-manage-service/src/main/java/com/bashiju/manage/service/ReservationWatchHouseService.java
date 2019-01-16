/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ReservationWatchHouseService.java   
 * @Package com.bashiju.manage.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年8月3日 下午5:23:38   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.service;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

/**   
 * @ClassName:  ReservationWatchHouseService   
 * @Description:经纪人预约看房接口
 * @author: wangpeng
 * @date:   2018年8月3日 下午5:23:38   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface ReservationWatchHouseService {
	/**
	 * 查询当前经纪人预约带看房源
	 * @Title: queryReservationWatchHouseByAgenId   
	 * @Description: 查询当前经纪人预约带看房源
	 * @param reservationDate 预约时间
	 * @param status预约状态
	 * @param page 当前页
	 * @param limit 每页总条数
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> queryReservationWatchHouseByAgenId(String reservationDate,String status,int page,int limit);
	/**
	 * @Title: updateReservationsInfo   
	 * @Description: 修改经纪人预约
	 * @param paramMap 
	 * @return: long
	 */
	boolean updateReservationsInfo(Map<String,Object> paramMap);
	/**
	 * @Title: queryReservationWatchHouseById   
	 * @Description: 条件查询经纪人预约看房
	 * @param id id
	 * @return: Map<String,Object>
	 */
	Map<String,Object> queryReservationWatchHouseById(String id);
}	
