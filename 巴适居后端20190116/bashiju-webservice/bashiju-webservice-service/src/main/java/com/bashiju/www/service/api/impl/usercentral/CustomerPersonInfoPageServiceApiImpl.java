/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerPersonInfoPageServiceApiImpl.java   
 * @Package com.bashiju.www.service.api.impl.usercentral      
 * @author: zuoyuntao     
 * @date:   2018年8月15日 上午11:21:48   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.service.api.impl.usercentral;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.wapi.usercentral.ICustomerPersonInfoPageServiceApi;
import com.bashiju.www.pojo.service.user.CustCustomerEntity;
import com.bashiju.www.service.user.CustCustomerService;
import com.bashiju.www.service.user.ICustomerPersonalCentralService;

/**
 * 客户个人信息管理服务
 * @ClassName:CustomerPersonInfoPageServiceApiImpl
 * @Description:客户个人信息管理服务
 * @author:zuoyuntao
 * @date:2018年8月15日 上午11:21:48
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class CustomerPersonInfoPageServiceApiImpl extends CustomerPersonalCentralBaseServiceApi
	implements ICustomerPersonInfoPageServiceApi {
	
	/**
	 * 个人中心服务接口
	 */
	@Autowired
	private ICustomerPersonalCentralService mICustomerPersonalCentralService;
	@Autowired
	private CustCustomerService mCustCustomerService;

	/**   
	 * <p>Title: queryCustomerPersonalInfo</p>   
	 * <p>Description:获取客户个人注册信息 </p>   
	 * @param key 用户登陆标记
	 * @return   
	 * @see com.bashiju.wapi.usercentral.ICustomerPersonInfoPageServiceApi#queryCustomerPersonalInfo(java.lang.String)   
	 */

	@Override
	public CustCustomerEntity queryCustomerPersonalInfo(String key) {
		Map<String,Object> userInfo = getUserInfoByRedisKey(key);
		String custId = String.valueOf(userInfo.get("id"));
		String numType = String.valueOf(userInfo.get("numType"));
		return mICustomerPersonalCentralService.queryPersonalInfo(custId,numType);
	}

	/**   
	 * <p>Title: modifyCustomerInfo</p>   
	 * <p>Description: 修改手机号码</p>   
	 * @param key 客户登陆标记
	 * @param custEntity 客户注册信息
	 * @see com.bashiju.wapi.usercentral.ICustomerPersonInfoPageServiceApi#modifyMobile(java.lang.String, java.lang.String)   
	 */

	@Override
	public void modifyCustomerInfo(String key,CustCustomerEntity custEntity) {
		Map<String,Object> userInfo = getUserInfoByRedisKey(key);
		String custId = String.valueOf(userInfo.get("id"));
		String numType = String.valueOf(userInfo.get("numType"));
		custEntity.setId(custId);
		custEntity.setNumType(numType);
		mICustomerPersonalCentralService.updateCustCustomerInfo(custEntity);

	}

	/**   
	 * <p>Title: modifyPassword</p>   
	 * <p>Description: </p>   
	 * @param key 用户有登陆的Key
	 * @param oriPassword 原密码
	 * @param password   新密码
	 * @see com.bashiju.wapi.usercentral.ICustomerPersonInfoPageServiceApi#modifyPassword(java.lang.String, java.lang.String)   
	 */
	@Override
	public void modifyPassword(String key,String oriPassword, String password) {
		String custId = getUserIdByRedisKey(key);
		mICustomerPersonalCentralService.modifyCustomerPassword(custId,oriPassword,password);
	}

	/**   
	 * <p>Title: queryPhoneVerifyCode</p>   
	 * <p>Description: 发送短信验证码</p>   
	 * @param mobile 手机号码
	 * @return   
	 * @see com.bashiju.wapi.usercentral.ICustomerPersonInfoPageServiceApi#queryPhoneVerifyCode(java.lang.String)   
	 */

	@Override
	public void queryPhoneVerifyCode(String mobile) {
		mICustomerPersonalCentralService.sendMobileVerfyCode(mobile);
	}
	/**
	 * 个人中心--修改用户手机号码
	 * @Title: modifyUserMobile
	 * @author: zuoyuntao  
	 * @Description:个人中心--修改用户手机号码  
	 * @param mobile 旧的手机号码
	 * @param verifyCode 验证码
	 * @param newMobile 新的手机号码
	 * void
	 */
	@Override
	public void modifyUserMobile(String mobile,String verifyCode,String newMobile) {
		CustCustomerEntity custCustomerEntity = new CustCustomerEntity();
		custCustomerEntity.setMobile(mobile);
		custCustomerEntity.setMsgCode(verifyCode);
		custCustomerEntity.setNewMobile(newMobile);
		mCustCustomerService.updateCustCustomer(custCustomerEntity);
	}

}
