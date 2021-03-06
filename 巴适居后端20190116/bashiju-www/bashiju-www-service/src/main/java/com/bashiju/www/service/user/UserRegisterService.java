package com.bashiju.www.service.user;

import java.util.Map;

import com.bashiju.www.pojo.service.user.CustCustomerParam;
/**
 * 
 * @ClassName:  UserRegisterService   
 * @Description:用户注册接口  
 * @author: wangpeng
 * @date:   2018年7月16日 下午2:52:46   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface UserRegisterService {
	/**
	 * 
	 * @Title: userRegister   
	 * @Description: TODO(用户注册)   
	 * @param map
	 * @return      
	 * @return: boolean
	 */
	public boolean userRegister(Map<Object,Object> map);	
	
	/**
	 * 
	 * @Title: queryIsExistMobile   
	 * @Description: TODO(查询手机号码是否已存在)   
	 * @param mobile 手机号码
	 * @return      
	 * @return: boolean
	 */
	public boolean queryIsExistMobile(String mobile);
	/**
	 * @Title: saveCustCustomer   
	 * @Description: 保存用户在注册时输入信息 
	 * @param CustCustomerParam 用户输入参数
	 * @return: long
	 */
	public boolean  saveCustCustomer(CustCustomerParam CustCustomerParam);
	
	/**
	 * @Title: userMobileCheck   
	 * @Description: 验证手机号码
	 * @param mobile 手机号码
	 * @param solt 盐值
	 * @return: boolean
	 */
	public boolean userMobileCheck(String mobile,String solt);
	
	  /**
     * @Title: updateCustCustomer   
     * @Description: 修改用户信息
     * @param paramMap 要修改的参数
     * @return: long
     */
    public boolean updateCustCustomer(Map<Object,Object> paramMap);
}
