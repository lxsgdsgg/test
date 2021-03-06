/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerMyRequestPageServiceApiImpl.java   
 * @Package com.bashiju.www.service.api.impl.usercentral      
 * @author: zuoyuntao     
 * @date:   2018年8月15日 下午2:54:15   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.service.api.impl.usercentral;

import org.springframework.beans.factory.annotation.Autowired;

import com.bashiju.wapi.usercentral.ICustomerMyRequestPageServiceApi;
import com.bashiju.wutils.exception.BusinessException;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.out.usercentral.CustomerHouseRequestEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerRequestDetailEntity;
import com.bashiju.www.service.user.ICustomerHouseRequestService;
import com.github.pagehelper.util.StringUtil;

/**
 * 个人中心----我的需求服务接口
 * @ClassName:CustomerMyRequestPageServiceApiImpl
 * @Description:个人中心----我的需求服务接口
 * @author:zuoyuntao
 * @date:2018年8月15日 下午2:54:15
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public class CustomerMyRequestPageServiceApiImpl 
		extends CustomerPersonalCentralBaseServiceApi implements ICustomerMyRequestPageServiceApi{
	/**
	 * 我的需求服务接口
	 */
	@Autowired
	private ICustomerHouseRequestService mICustomerHouseRequestService;
	/**
	 * <p>Title: queryMyRequestPageList</p>   
	 * <p>Description: 查询我的需求列表</p> 
	 * @param key 客户登陆标记
	 * @param requestType 客户需求类型 (0:所有（求租求购）4:求购 5：求租 )
	 * @param page 当前页
	 * @param limit 最大页  
	 * @return   
	 * @see com.bashiju.wapi.usercentral.ICustomerMyRequestPageServiceApi#queryMyRequestPageList(java.lang.String, java.lang.String, int, int)
	 */
	@Override
	public WebPage<CustomerHouseRequestEntity> queryMyRequestPageList(String key,String requestType, int page, int limit) {
		String custId = getUserIdByRedisKey(key);
		return mICustomerHouseRequestService.queryMyHouseRequestList(custId, requestType, page, limit);
	}
	/**
	 * <p>Title: bindingCustomerByBindCode</p>   
	 * <p>Description: 绑定我的需求</p>   
	 * @param custId
	 * @param bindCode   
	 * @see com.bashiju.wapi.usercentral.ICustomerMyRequestPageServiceApi#bindingCustomerByBindCode(java.lang.String, java.lang.String)
	 */
	@Override
	public void bindingCustomerByBindCode(String custId, String bindCode) {
		mICustomerHouseRequestService.bindingCustomerByBindCode(custId, bindCode);
	}
	/**
	 * <p>Title: sendHouseVerifyCode</p>   
	 * <p>Description: 发送房源验证码</p>   
	 * @param mobile 手机号码  
	 * @see com.bashiju.wapi.usercentral.ICustomerMyRequestPageServiceApi#sendHouseVerifyCode(java.lang.String)
	 */
	@Override
	public void sendHouseVerifyCode(String mobile) {
		mICustomerHouseRequestService.queryHouseVerifyCode(mobile);
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
		if(StringUtil.isEmpty(demandId)) {
			throw new BusinessException("传入需求ID为空");
		}
		return mICustomerHouseRequestService.queryMyRequestDetailById(demandId);
	}
}
