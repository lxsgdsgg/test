/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerMyHousesourcePageServiceApiImpl.java   
 * @Package com.bashiju.www.service.api.impl.usercentral      
 * @author: zuoyuntao     
 * @date:   2018年8月15日 下午2:09:16   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.service.api.impl.usercentral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.wapi.usercentral.ICustomerMyHousesourcePageServiceApi;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.out.usercentral.CustomerHouseDetailEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerOwnerHouseEntity;
import com.bashiju.www.service.user.ICustomerOwnerHouseService;

/**
 * 我的房源服务
 * @ClassName:CustomerMyHousesourcePageServiceApiImpl
 * @Description:我的房源服务
 * @author:zuoyuntao
 * @date:2018年8月15日 下午2:09:16
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class CustomerMyHousesourcePageServiceApiImpl extends 
			CustomerPersonalCentralBaseServiceApi implements ICustomerMyHousesourcePageServiceApi {
	/**
	 * 我的房源服务接口
	 */
	@Autowired
	private ICustomerOwnerHouseService mICustomerOwnerHouseService;
	/**
	 * <p>Title: queryCustOwnerHouseList</p>   
	 * <p>Description:查询我的房源列表 </p>   
	 * @param key 客户登陆标记
	 * @param page 当前页
	 * @param limit 最大页
	 * @return   
	 * @see com.bashiju.wapi.usercentral.ICustomerMyHousesourcePageServiceApi#queryCustOwnerHouseList(java.lang.String, java.lang.String, int, int)
	 */
	@Override
	public WebPage<CustomerOwnerHouseEntity> queryCustOwnerHouseList(String key, int page,
			int limit) {
		String custId = getUserIdByRedisKey(key);
		return mICustomerOwnerHouseService.queryCustOwnerHouseList(custId, page, limit);
	}
	/**
	 * <p>Title: bindingSourcesByBindCode</p>   
	 * <p>Description:房源绑定 </p>   
	 * @param key 用户登陆标记
	 * @param bindingCode 绑定码
	 * @param type 绑定类型 （0 房源 1 客源）
	 * @throws Exception   
	 * @see com.bashiju.wapi.usercentral.ICustomerMyHousesourcePageServiceApi#bindingSourcesByBindCode(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void bindingSourcesByBindCode(String key, String bindingCode, String type) throws Exception {
		String custId = getUserIdByRedisKey(key);
		mICustomerOwnerHouseService.bindingSourcesByBindCode(custId, bindingCode, type);
	}
	/**
	 * <p>Title: sendHouseVerifyCode</p>   
	 * <p>Description: 发送房源验证码</p>   
	 * @param mobile 手机号码
	 * @see com.bashiju.wapi.usercentral.ICustomerMyHousesourcePageServiceApi#sendHouseVerifyCode(java.lang.String)
	 */
	@Override
	public void sendHouseVerifyCode(String mobile) {
		mICustomerOwnerHouseService.sendHouseVerifyCode(mobile);
	}
	
	/**
	 * 根据房源ID查询房源详细信息
	 * @Title: queryMyHouseDetailByHouseID
	 * @author: zuoyuntao  
	 * @Description:根据房源ID查询房源详细信息
	 * @param houseId 房源ID
	 * @return      
	 * CustomerHouseDetailEntity
	 */
	@Override
	public CustomerHouseDetailEntity queryMyHouseDetailByHouseID(String houseId) {
		return mICustomerOwnerHouseService.queryMyHouseDetailByHouseID(houseId);
	}
	
	/**
	 * 根据绑定ID删除我的房源
	 * @Title: deleteMyHouseByBindId
	 * @author: zuoyuntao  
	 * @Description:根据绑定ID删除我的房源
	 * @param id 房源绑定ID
	 * void 
	 */
	@Override
	public void deleteMyHouseByBindId(String id) {
		mICustomerOwnerHouseService.deleteMyHouseByBindId(id);
	}
	
	/**
	 * 取消绑定我的房源
	 * @Title: cancelBindMyHouseByBindId
	 * @author: zuoyuntao  
	 * @Description:取消绑定我的房源
	 * @param id      
	 * void 
	 */
	@Override
	public void cancelBindMyHouseByBindId(String id) {
		mICustomerOwnerHouseService.cancelBindMyHouseByBindId(id);
	}

	/**
	 * 重新委托【我的房源】（房源信息无变动）
	 * @Title: againEntrustMyHouseById
	 * @author: zuoyuntao  
	 * @Description:重新委托【我的房源】（房源信息无变动）
	 * @param houseId 房源ID
	 * @param key 用户登陆key     
	 * void 
	 */
	public void againEntrustMyHouseById(String houseId,String key) {
		String custId = this.getUserIdByRedisKey(key);
		mICustomerOwnerHouseService.againEntrustMyHouseById(houseId, custId);
	}
	
	/**
	 * 根据绑定ID查询房源详细信息
	 * @Title: queryHoueseDetailById
	 * @author: zuoyuntao  
	 * @Description:根据绑定ID查询房源详细信息
	 * @param id 房源绑定ID
	 * @param key 用户登陆的key
	 * CustomerOwnerHouseEntity
	 */
	@Override
	public CustomerOwnerHouseEntity queryHoueseDetailById(String id,String key) {
		String custId = this.getUserIdByRedisKey(key);
		return mICustomerOwnerHouseService.queryHoueseDetailById(id, custId);
	}
}
