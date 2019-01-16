/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ICustomerMyHousesourcePageServiceApi.java   
 * @Package com.bashiju.wapi.usercentral      
 * @author: zuoyuntao     
 * @date:   2018年8月15日 下午2:04:34   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.wapi.usercentral;

import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.out.usercentral.CustomerHouseDetailEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerOwnerHouseEntity;

/**
 * 我的房源服务接口
 * @ClassName:ICustomerMyHousesourcePageServiceApi
 * @Description:我的房源服务接口
 * @author:zuoyuntao
 * @date:2018年8月15日 下午2:04:34
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface ICustomerMyHousesourcePageServiceApi {
	/**
	 * 查询我的房源列表 
	 * @Title: queryCustOwnerHouseList
	 * @author: zuoyuntao  
	 * @Description:查询我的房源列表
	 * @param key 用户登陆标记
	 * @param page 当前页
	 * @param limit 最大页
	 * @return      
	 * WebPage<CustomerOwnerHouseEntity> JSON 格式为：
	 */
	public WebPage<CustomerOwnerHouseEntity> queryCustOwnerHouseList(String key,int page,int limit);
	/**
	 * 绑定我的房源
	 * @Title: bindingSourcesByBindCode
	 * @author: zuoyuntao  
	 * @Description:绑定我的房源
	 * @param key 客户登陆标记
	 * @param bindingCode 绑定码
	 * @param type 绑定类型 （0 房源 1 客源）
	 * @throws Exception      
	 * void 
	 */
	public void bindingSourcesByBindCode(String key, String bindingCode,String type) throws Exception;
	
	/**
	 * 根据手机号码获取验证码 
	 * @Title: sendHouseVerifyCode
	 * @author: zuoyuntao  
	 * @Description:根据手机号码获取验证码  
	 * @param mobile 手机号码
	 * @return      
	 * String
	 */
	public void sendHouseVerifyCode(String mobile);
	/**
	 * 根据房源ID查询房源详细信息
	 * @Title: queryMyHouseDetailByHouseID
	 * @author: zuoyuntao  
	 * @Description:根据房源ID查询房源详细信息
	 * @param houseId 房源ID
	 * @return      
	 * CustomerHouseDetailEntity
	 */
	public CustomerHouseDetailEntity queryMyHouseDetailByHouseID(String houseId);
	/**
	 * 根据绑定ID删除我的房源
	 * @Title: deleteMyHouseByBindId
	 * @author: zuoyuntao  
	 * @Description:根据绑定ID删除我的房源
	 * @param id 房源绑定ID
	 * void 
	 */
	public void deleteMyHouseByBindId(String id);
	/**
	 * 取消绑定我的房源
	 * @Title: cancelBindMyHouseByBindId
	 * @author: zuoyuntao  
	 * @Description:取消绑定我的房源 TODO:撤销绑定是需要像经纪人发送消息，从而进行操作ERP房源
	 * @param id      
	 * void 
	 */
	public void cancelBindMyHouseByBindId(String id);
	/**
	 * 根据绑定ID查询房源详细信息
	 * @Title: queryHoueseDetailById
	 * @author: zuoyuntao  
	 * @Description:根据绑定ID查询房源详细信息
	 * @param id 房源绑定ID
	 * @param key 用户登陆的key
	 * CustomerOwnerHouseEntity
	 */
	public CustomerOwnerHouseEntity queryHoueseDetailById(String id,String key);
}
