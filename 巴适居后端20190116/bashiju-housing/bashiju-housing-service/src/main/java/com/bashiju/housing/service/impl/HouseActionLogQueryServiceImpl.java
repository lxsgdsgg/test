/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  HouseActionLogQueryServiceImpl.java   
 * @Package com.bashiju.housing.service.impl      
 * @author: zuoyuntao     
 * @date:   2018年11月30日 下午4:05:39   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.housing.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.MenuEnum;
import com.bashiju.housing.mapper.HouseActionLogQueryMapper;
import com.bashiju.housing.service.IHouseActionLogQueryService;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 房源操作日志查询服务实现类
 * @ClassName:HouseActionLogQueryServiceImpl
 * @Description:房源操作日志查询服务实现类
 * @author:zuoyuntao
 * @date:2018年11月30日 下午4:05:39
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@Service
public class HouseActionLogQueryServiceImpl implements IHouseActionLogQueryService{
	/**
	 * 持久层接口对象
	 */
	@Autowired
	private HouseActionLogQueryMapper mHouseActionLogQueryMapper;
	@Autowired
	private DataAuthHelper dataAuthHelper;
	/**
	 * <p>Title: auxHouseActionLogPageList</p>   
	 * <p>Description: </p>   
	 * @param page 当前页 
	 * @param limit 每页最大数
	 * @param paramMap 参数对象
	 * @return   
	 * @see com.bashiju.deal.service.IHouseActionLogQueryService#auxHouseActionLogPageList(int, int, java.lang.String, java.util.Map)
	 */
	@Override
	public Page<Map<String, Object>> auxHouseActionLogPageList(int page, int limit,
			Map<String, Object> paramMap) {
		PageHelper.startPage(page,limit);
//		if(UserThreadLocal.get().get("roleGroup").equals("03")) {
//			if(String.valueOf(paramMap.get("companyId")).equals("") 
//					&& String.valueOf(paramMap.get("departmentId")).equals("")
//					&& String.valueOf(paramMap.get("operatorId")).equals("")) {
//				paramMap.put("companyId", String.valueOf(UserThreadLocal.get().get("companyId")));
//			}
//		}else {
//			dataAuthHelper.auth(MenuEnum.MENU_94.getCode(), String.valueOf(UserThreadLocal.get().get("id")));
//		}
		dataAuthHelper.auth(MenuEnum.MENU_94.getCode(), String.valueOf(UserThreadLocal.get().get("id")));
		return (Page<Map<String, Object>>) mHouseActionLogQueryMapper.auxHouseActionLogPageList(paramMap);
	}
	/**
	 * <p>Title: auxTelephoneViewLogPageList</p>   
	 * <p>Description: </p>   
	 * @param page 当前页 
	 * @param limit 每页最大数
	 * @param paramMap 参数对象
	 * @return   
	 * @see com.bashiju.deal.service.auxTelephoneViewLogPageList#auxTelephoneViewLogPageList(int, int, java.lang.String, java.util.Map)
	 */
	@Override
	public Page<Map<String, Object>> auxTelephoneViewLogPageList(int page, int limit,
			Map<String, Object> paramMap) {
		PageHelper.startPage(page,limit);
		dataAuthHelper.auth(MenuEnum.MENU_95.getCode(), String.valueOf(UserThreadLocal.get().get("id")));
		return (Page<Map<String, Object>>) mHouseActionLogQueryMapper.auxTelephoneViewLogPageList(paramMap);
	}
	/**
	 * <p>Title: auxOneHandHouseActionLogPageList</p>   
	 * <p>Description: </p>   
	 * @param page 当前页 
	 * @param limit 每页最大数
	 * @param paramMap 参数对象
	 * @return   
	 * @see com.bashiju.deal.service.auxTelephoneViewLogPageList#auxOneHandHouseActionLogPageList(int, int, java.lang.String, java.util.Map)
	 */
	@Override
	public Page<Map<String, Object>> auxOneHandHouseActionLogPageList(int page, int limit,
			Map<String, Object> paramMap) {
		PageHelper.startPage(page,limit);
		dataAuthHelper.auth(MenuEnum.MENU_93.getCode(), String.valueOf(UserThreadLocal.get().get("id")));
		return (Page<Map<String, Object>>) mHouseActionLogQueryMapper.auxOneHandHouseActionLogPageList(paramMap);
	}

	/**
	 * <p>Title: auxHouseActionLogByHouseId</p>   
	 * <p>Description: </p>   
	 * @param page 当前页 
	 * @param limit 每页最大数
	 * @param houseId 房源编号
	 * @return   
	 * @see com.bashiju.deal.service.IHouseActionLogQueryService#auxHouseActionLogByHouseId(int, int, java.lang.String)
	 */
	@Override
	public Page<Map<String, Object>> auxHouseActionLogByHouseId(int page, int limit, String dealId) {
		PageHelper.startPage(page,limit);
		Page<Map<String, Object>> retPage = (Page<Map<String, Object>>) mHouseActionLogQueryMapper.auxHouseActionLogByHouseId(dealId);
		return retPage;
	}

	/**
	 * <p>Title: auxHouseActionLogByHouseType</p>   
	 * <p>Description: </p>   
	 * @param page 当前页 
	 * @param limit 每页最大数
	 * @param houseType 房源类型
	 * @return   
	 * @see com.bashiju.deal.service.IHouseActionLogQueryService#auxHouseActionLogByHouseType(int, int, java.lang.String)
	 */
	@Override
	public Page<Map<String, Object>> auxHouseActionLogByHouseType(int page, int limit, String dealType) {
		PageHelper.startPage(page,limit);
		return (Page<Map<String, Object>>) mHouseActionLogQueryMapper.auxHouseActionLogByHouseType(dealType);
	}

}
