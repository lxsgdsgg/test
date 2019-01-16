/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ICustomerHouseRequestService.java   
 * @Package com.bashiju.www.service.user      
 * @author: zuoyuntao     
 * @date:   2018年7月26日 下午4:09:50   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.service.user;

import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.out.usercentral.CustomerHouseRequestEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerRequestDetailEntity;

/**
 * 用户--我的需求--服务接口
 * @ClassName:ICustomerHouseRequestService
 * @Description:用户--我的需求--服务接口
 * @author:zuoyuntao
 * @date:2018年7月26日 下午4:09:50
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface ICustomerHouseRequestService {
	/**
	 * 查询我的房源需求列表
	 * @Title: queryMyHouseRequestList
	 * @author: zuoyuntao  
	 * @Description:查询我的房源需求列表 
	 * @param custId 登陆用户Id 【custId必填】
	 * @param requestType 客户需求类型 (0:所有（求租求购）4:求购 5：求租 )
	 * @param page 每页显示最少条数
	 * @param limit 每页显示最大条数
	 * WebPage<CustomerHouseRequestEntity>
	 */
	public WebPage<CustomerHouseRequestEntity> queryMyHouseRequestList(String custId
			, String requestType,int page,int limit);
	/**
	 * 根据绑定码绑定客源信息
	 * @Title: bindingCustomerByBindCode
	 * @author: zuoyuntao  
	 * @Description:根据绑定码绑定客源信息 
	 * @param custId 登陆用户ID--必填
	 * @param bindCode 绑定码--必填
	 * @param requestType 需求类型--必填
	 * void 
	 */
	public void bindingCustomerByBindCode(String custId,String bindCode);
	/**
	 * 根据手机号码获取验证码 
	 * @Title: queryHouseVerifyCode
	 * @author: zuoyuntao  
	 * @Description:根据手机号码获取验证码  
	 * @param mobile 手机号码
	 * @return 
	 * String
	 */
	public void queryHouseVerifyCode(String mobile);
	/**
	 * 根据需求id查询需求明细信息
	 * @Title: queryMyRequestDetailById
	 * @author: zuoyuntao  
	 * @Description:根据需求id查询需求明细信息
	 * @param demandId 需求ID     
	 * CustomerRequestDetailEntity 
	 */
	public CustomerRequestDetailEntity queryMyRequestDetailById(String demandId);
}
