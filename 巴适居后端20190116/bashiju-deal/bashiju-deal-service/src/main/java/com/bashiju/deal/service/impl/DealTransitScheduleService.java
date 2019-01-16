/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  DealTransitScheduleService.java   
 * @Package com.bashiju.deal.service.impl      
 * @author: zuoyuntao     
 * @date:   2018年10月22日 上午11:59:07   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.deal.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.deal.mapper.DealTransitScheduleMapper;
import com.bashiju.deal.service.IDealTransitScheduleService;
import com.bashiju.enums.MenuEnum;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 查询过户进度数据信息 
 * @ClassName:DealTransitScheduleService
 * @Description:查询过户进度数据信息 
 * @author:zuoyuntao
 * @date:2018年10月22日 上午11:59:07
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@Service
public class DealTransitScheduleService implements IDealTransitScheduleService{
	/**
	 * 过户进度持久层接口
	 */
	@Autowired
	private DealTransitScheduleMapper mDealTransitScheduleMapper;
	/**
	 * 权限管理接口
	 */
	@Autowired
	private DataAuthHelper dataAuthHelper;
	/**
	 * <p>Title: queryDealScheduleDataPageList</p>   
	 * <p>Description: </p>
	 * @param paraMap 参数对象
	 * @param page 当前页
	 * @param limit 每页显示最大条数
	 * @return   
	 * @see com.bashiju.deal.service.IDealTransitScheduleService#queryDealScheduleDataPageList(java.util.Map, int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<Map<String,Object>> queryDealScheduleDataPageList(Map<String, Object> paraMap, int page, int limit){
		PageHelper.startPage(page,limit);
		dataAuthHelper.auth(MenuEnum.MENU_109.getCode(), String.valueOf(UserThreadLocal.get().get("id")));
		List<Map<String,Object>> retList = mDealTransitScheduleMapper.queryDealScheduleDataPageList(paraMap);
		for(Map<String,Object> obj : retList) {
			if(obj.containsKey("scheduleList") && null != obj.get("scheduleList")) {
				List<Map<String,Object>> scheduleList = (List<Map<String, Object>>) obj.get("scheduleList");
				for(Map<String,Object> map : scheduleList) {
					obj.put("status".concat(String.valueOf(map.get("detailCode"))), map.get("status"));
					obj.put("estimateFinishTime".concat(String.valueOf(map.get("detailCode"))), map.get("estimateFinishTime"));
					obj.put("actualFinishTime".concat(String.valueOf(map.get("detailCode"))), map.get("actualFinishTime"));
					obj.put("cycle".concat(String.valueOf(map.get("detailCode"))), map.get("cycle"));
				}
				obj.remove("scheduleList");
			}
		}
		return (Page<Map<String, Object>>) retList;
	}
}
