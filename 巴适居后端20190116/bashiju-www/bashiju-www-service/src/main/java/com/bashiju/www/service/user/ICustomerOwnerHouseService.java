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
	 * @param houseType 房源类型（1：出售 2：出租  3：租售）
	 * @param page 每页显示最小条数
	 * @param limit 每页显示最大条数
	 * WebPage<CustomerOwnerHouseEntity>
	 */
	public WebPage<CustomerOwnerHouseEntity> queryCustOwnerHouseList(
			String custId,String houseType,int page,int limit);
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
}
