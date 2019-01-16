/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ReservationWatchHouseServiceImpl.java   
 * @Package com.bashiju.manage.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年8月3日 下午5:30:19   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.service.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.MenuEnum;
import com.bashiju.manage.mapper.ReservationWatchHouseMapper;
import com.bashiju.manage.service.ReservationWatchHouseService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**   
 * @ClassName:  ReservationWatchHouseServiceImpl   
 * @Description:经纪人预约服务   
 * @author: wangpeng
 * @date:   2018年8月3日 下午5:30:19   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class ReservationWatchHouseServiceImpl extends CommonSqlServie  implements ReservationWatchHouseService{
	
	@Autowired
	private ReservationWatchHouseMapper reservationWatchHouseMapper;
	@Autowired
	private DataAuthHelper dataAuthHelper;
	
	/**
	 * 查询当前登录经纪人的预约   
	 * <p>Title: queryReservationWatchHouseByAgenId</p>   
	 * <p>Description: 查询当前登录经纪人的预约</p>   
	 * @param agenId 经纪人id(userId)
	 * @param reservationDate 预约时间
	 * @param status 预约状态
	 * @see com.bashiju.manage.service.ReservationWatchHouseService#queryReservationWatchHouseByAgenId(java.lang.String)   
	 */
	
	@Override
	public Page<Map<String, Object>> queryReservationWatchHouseByAgenId(String reservationDate,String status,int page,int limit) {
		PageHelper.startPage(page, limit);
		dataAuthHelper.auth(MenuEnum.MENU_80.getCode(),UserThreadLocal.get().get("id").toString());
		String userId = UserThreadLocal.get().get("id").toString();
		Page<Map<String, Object>> pages = reservationWatchHouseMapper.queryReservationWatchHouseByAgenId(userId,reservationDate,status);
		return pages;
	}

	/**   
	 * <p>Title: updateReservationsInfo</p>   
	 * <p>Description: 修改经纪人预约 </p>   
	 * @param paramMap
	 * @see com.bashiju.manage.service.ReservationWatchHouseService#updateReservationsInfo(java.util.Map)   
	 */
	
	@Override
	public boolean updateReservationsInfo(Map<String,Object> paramMap) {
	long result = this.commonOperationDatabase(paramMap, "cust_reservations", "id",false);
	if(result>0)
		return true;
	return false;
	}

	/**   
	 * <p>Title: queryReservationWatchHouseById</p>   
	 * <p>Description: 条件查询经纪人预约看房</p>   
	 * @param id id
	 * @see com.bashiju.manage.service.ReservationWatchHouseService#queryReservationWatchHouseById(java.lang.String)   
	 */
	@Override
	public Map<String, Object> queryReservationWatchHouseById(String id) {
		if(StringUtils.isEmpty(id))
			throw new BusinessException("预约看房id不能为空");
		Map<String, Object> map = reservationWatchHouseMapper.queryReservationWatchHouseById(id);
		return map;
	}

}
