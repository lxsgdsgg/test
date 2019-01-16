/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerActionLogQueryServiceImpl.java   
 * @Package com.bashiju.deal.service.impl      
 * @author: zuoyuntao     
 * @date:   2018年11月30日 下午4:05:39   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.customer.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.customer.mapper.CustomerActionLogQueryMapper;
import com.bashiju.customer.service.ICustomerActionLogQueryService;
import com.bashiju.enums.MenuEnum;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 客源操作日志查询服务实现类
 * @ClassName:CustomerActionLogQueryServiceImpl
 * @Description:客源操作日志查询服务实现类
 * @author:zuoyuntao
 * @date:2018年11月30日 下午4:05:39
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@Service
public class CustomerActionLogQueryServiceImpl implements ICustomerActionLogQueryService{
	/**
	 * 持久层接口对象
	 */
	@Autowired
	private CustomerActionLogQueryMapper mCustomerActionLogQueryMapper;
	@Autowired
	private DataAuthHelper dataAuthHelper;
	/**
	 * <p>Title: auxCustomerActionLogPageList</p>   
	 * <p>Description: </p>   
	 * @param page 当前页 
	 * @param limit 每页最大数
	 * @param paramMap 参数对象
	 * @return   
	 * @see com.bashiju.ICustomerActionLogQueryService.service.ICustomerActionLogQueryService#auxCustomerActionLogPageList(int, int, java.lang.String, java.util.Map)
	 */
	@Override
	public Page<Map<String, Object>> auxCustomerActionLogPageList(int page, int limit,
			Map<String, Object> paramMap) {
		PageHelper.startPage(page,limit);
		dataAuthHelper.auth(MenuEnum.MENU_104.getCode(), String.valueOf(UserThreadLocal.get().get("id")));
		return (Page<Map<String, Object>>) mCustomerActionLogQueryMapper.auxCustomerActionLogPageList(paramMap);
	}

	/**
	 * <p>Title: auxCustomerActionLogByDemandId</p>   
	 * <p>Description: </p>   
	 * @param page 当前页 
	 * @param limit 每页最大数
	 * @param demandId 客源编号
	 * @return   
	 * @see com.bashiju.ICustomerActionLogQueryService.service.ICustomerActionLogQueryService#auxCustomerActionLogByDemandId(int, int, java.lang.String)
	 */
	@Override
	public Page<Map<String, Object>> auxCustomerActionLogByDemandId(int page,int limit,String demandId) {
		PageHelper.startPage(page,limit);
		Page<Map<String, Object>> retPage = (Page<Map<String, Object>>) 
				mCustomerActionLogQueryMapper.auxCustomerActionLogByCustomerId(demandId);
		return retPage;
	}

	/**
	 * <p>Title: auxCustomerActionLogByCustomerType</p>   
	 * <p>Description: </p>   
	 * @param page 当前页 
	 * @param limit 每页最大数
	 * @param demandType 客源类型
	 * @return   
	 * @see com.bashiju.ICustomerActionLogQueryService.service.ICustomerActionLogQueryService#auxCustomerActionLogByCustomerType(int, int, java.lang.String)
	 */
	@Override
	public Page<Map<String, Object>> auxCustomerActionLogByCustomerType(int page,int limit,String demandType) {
		PageHelper.startPage(page,limit);
		return (Page<Map<String, Object>>) mCustomerActionLogQueryMapper.auxCustomerActionLogByType(demandType);
	}

}
