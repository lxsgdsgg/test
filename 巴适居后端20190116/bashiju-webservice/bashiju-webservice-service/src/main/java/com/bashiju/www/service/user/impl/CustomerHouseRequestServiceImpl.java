/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerHouseRequestServiceImpl.java   
 * @Package com.bashiju.www.service.user.impl      
 * @author: zuoyuntao     
 * @date:   2018年7月26日 下午4:12:47   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.api.RedisGetIdServiceApi;
import com.bashiju.webservice.mapper.CustomerHouseRequestMapper;
import com.bashiju.wutils.exception.BusinessException;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.out.usercentral.CustomerHouseRequestEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerRequestDetailEntity;
import com.bashiju.www.service.user.ICustomerHouseRequestService;
import com.bashiju.www.service.user.ICustomerOwnerHouseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 个人中心--我的需求--服务
 * @ClassName:CustomerHouseRequestServiceImpl
 * @Description:个人中心--我的需求--服务
 * @author:zuoyuntao
 * @date:2018年7月26日 下午4:12:47
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class CustomerHouseRequestServiceImpl implements ICustomerHouseRequestService{
	/**
	 * 房源绑定服务接口
	 */
	@Autowired
	private ICustomerOwnerHouseService mICustomerOwnerHouseService;
	/**
	 * 个人中心：客户需求持久层接口
	 */
	@Autowired
	private CustomerHouseRequestMapper mCustomerHouseRequestMapper;
	/**
	 * Redis获取各种码的服务接口
	 */
	@Autowired
	private RedisGetIdServiceApi mRedisGetIdServiceApi;
	/**
	 * <p>Title: queryMyHouseRequestList</p>   
	 * <p>Description: 查询我的需求数据信息</p>   
	 * @param custId 登陆用户ID
	 * @param requestType 客户需求类型 (0:所有（求租求购）4:求购 5：求租 )
	 * @param page 每页显示最小条数
	 * @param limit 每页显示最大条数
	 * @see com.bashiju.www.service.user.ICustomerHouseRequestService#queryMyHouseRequestList(java.lang.String, java.lang.String, int, int)
	 */
	@Override
	public WebPage<CustomerHouseRequestEntity> queryMyHouseRequestList(
			String custId, String requestType,int page,int limit) {
		PageHelper.startPage(page, limit);
		Page<CustomerHouseRequestEntity> pageObj = mCustomerHouseRequestMapper.queryCustomerRequestDataList(custId,requestType);
		WebPage<CustomerHouseRequestEntity> retPage = new WebPage<CustomerHouseRequestEntity>(pageObj);
		return retPage;
	}
	/**
	 * <p>Title: bindingCustomerByBindCode</p>   
	 * <p>Description: 客户--需求绑定</p>   
	 * @param custId 登陆用户ID
	 * @param bindCode 登陆用户输入绑定码
	 * @see com.bashiju.www.service.user.ICustomerHouseRequestService#bindingCustomerByBindCode(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void bindingCustomerByBindCode(String custId, String bindCode) {
		try {
			//1:绑定类型---0 房源绑定 1 客源绑定
			mICustomerOwnerHouseService.bindingSourcesByBindCode(custId, bindCode,"1");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}
	/**
	 * 根据手机号码获取验证码 
	 * @Title: queryHouseVerifyCode
	 * @author: zuoyuntao  
	 * @Description:根据手机号码获取验证码  
	 * @param mobile 手机号码
	 * @return      
	 * String
	 */
	@Override
	public void queryHouseVerifyCode(String mobile) {
		String verifyCode = mRedisGetIdServiceApi.getMsgAuthCode(mobile);
		if(null == verifyCode) {
			throw new BusinessException("请勿频繁操作");
		}
	}
	/**
	 * 根据需求id查询需求明细信息
	 * @Title: queryMyRequestDetailById
	 * @author: zuoyuntao  
	 * @Description:根据需求id查询需求明细信息
	 * @param demandId 需求ID     
	 * CustomerRequestDetailEntity 
	 */
	@Override
	public CustomerRequestDetailEntity queryMyRequestDetailById(String demandId) {
		return mCustomerHouseRequestMapper.queryMyRequestDetailById(demandId);
	}
}
