/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ReservationWatchHouse.java   
 * @Package com.bashiju.manage.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年8月3日 下午5:13:43   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

/**   
 * @ClassName:  ReservationWatchHouse   
 * @Description:预约看房管理映射
 * @author: wangpeng
 * @date:   2018年8月3日 下午5:13:43   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface ReservationWatchHouseMapper {
	
	/**
	 * @Title: queryReservationWatchHouseByAgenId   
	 * @Description: 查询经纪人当前预约情况  
	 * @param userId 经纪人id(userId)
	 * @param reservationDate 预约日期
	 * @param status 处理状态
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> queryReservationWatchHouseByAgenId(@Param("userId")String userId,@Param("reservationDate")String reservationDate,@Param("status")String status);
	
	/**
	 * @Title: updateReservationsInfo   
	 * @Description: 修改预约信息 
	 * @param paramMap
	 * @return: long
	 */
	long updateReservationsInfo(Map<String,Object> paramMap);
	/**
	 * @Title: queryReservationWatchHouseById   
	 * @Description: 条件查询经纪人预约看房 
	 * @param id id
	 * @return: Map<String,Object>
	 */
	Map<String,Object> queryReservationWatchHouseById(@Param("id")String id);
	
}
