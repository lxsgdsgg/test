/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ICustomerOwnerHouseService.java   
 * @Package com.bashiju.www.service.user      
 * @author: zuoyuntao     
 * @date:   2018年7月26日 下午4:10:12   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.service.user;

import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.out.usercentral.CustomerHouseDetailEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerOwnerHouseEntity;

/**
 * 用户--我的房子--服务接口
 * @ClassName:ICustomerOwnerHouseService
 * @Description:用户--我的房子--服务接口
 * @author:zuoyuntao
 * @date:2018年7月26日 下午4:10:12
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface ICustomerOwnerHouseService {
	/**
	 * 查询我的房子（委托经纪人发布的房源信息）
	 * @Title: queryCustOwnerHouseList
	 * @author: zuoyuntao  
	 * @Description:查询我的房子（委托经纪人发布的房源信息）
	 * @param custId 用户ID--必填
	 * @param page 每页显示最小条数
	 * @param limit 每页显示最大条数
	 * WebPage<CustomerOwnerHouseEntity>
	 */
	public WebPage<CustomerOwnerHouseEntity> queryCustOwnerHouseList(
			String custId,int page,int limit);
	/**
	 * 根据绑定码绑定房源信息
	 * @Title: bindingSourcesByBindCode
	 * @author: zuoyuntao  
	 * @Description:根据绑定码绑定房源信息
	 * @param custId 用户ID 
	 * @param bindingCode 房源绑定码
	 * @param type 绑定类型 （0 房源 1 客源）
	 * void
	 */
	public void bindingSourcesByBindCode(String custId,String bindingCode,String type) throws Exception;
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
	public CustomerHouseDetailEntity queryMyHouseDetailByHouseID(String houseId) ;
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
	 * @Description:取消绑定我的房源
	 * @param id      
	 * void 
	 */
	public void cancelBindMyHouseByBindId(String id);
	/**
	 * 重新委托【我的房源】（房源信息无变动）
	 * @Title: againEntrustMyHouseById
	 * @author: zuoyuntao  
	 * @Description:重新委托【我的房源】（房源信息无变动）
	 * @param houseId 房源ID
	 * @param custId 用户Id
	 * void 
	 */
	public void againEntrustMyHouseById(String houseId,String custId);
	
	/**
	 * 根据绑定ID查询房源详细信息
	 * @Title: queryHoueseDetailById
	 * @author: zuoyuntao  
	 * @Description:根据绑定ID查询房源详细信息
	 * @param id 房源绑定ID
	 * @param custId 客户id
	 * CustomerOwnerHouseEntity
	 */
	public CustomerOwnerHouseEntity queryHoueseDetailById(String id,String custId);
}
