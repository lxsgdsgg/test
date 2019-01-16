/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ICustomerMyRequestPageServiceApi.java   
 * @Package com.bashiju.wapi.usercentral      
 * @author: zuoyuntao     
 * @date:   2018年8月15日 下午2:53:15   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.wapi.usercentral;

import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.out.usercentral.CustomerHouseRequestEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerRequestDetailEntity;

/**
 * 个人中心----我的需求服务接口
 * @ClassName:ICustomerMyRequestPageServiceApi
 * @Description:个人中心----我的需求服务接口
 * @author:zuoyuntao
 * @date:2018年8月15日 下午2:53:15
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface ICustomerMyRequestPageServiceApi {
	/**
	 * 查询我的需求列表
	 * @Title: queryMyRequestPageList
	 * @author: zuoyuntao  
	 * @Description:查询我的需求列表
	 * @param key 客户登陆标记
	 * @param requestType 客户需求类型 (0:所有（求租求购）4:求购 5：求租 )
	 * @param page 当前页
	 * @param limit 最大页  
	 * WebPage<CustomerHouseRequestEntity>
	 */
	public WebPage<CustomerHouseRequestEntity> queryMyRequestPageList(String key,String requestType, int page, int limit);
	/**
	 * 根据绑定码绑定客源信息
	 * @Title: bindingCustomerByBindCode
	 * @author: zuoyuntao  
	 * @Description:根据绑定码绑定客源信息 
	 * @param custId 登陆用户ID--必填
	 * @param bindCode 绑定码--必填
	 * void 
	 */
	public void bindingCustomerByBindCode(String custId,String bindCode);
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
	 * 根据需求id查询需求明细信息（列表页面【查看】使用）
	 * @Title: queryMyRequestDetailById
	 * @author: zuoyuntao  
	 * @Description:根据需求id查询需求明细信息
	 * @param demandId 需求编号     
	 * CustomerRequestDetailEntity 
	 */
	public CustomerRequestDetailEntity queryMyRequestDetailById(String demandId);

}
