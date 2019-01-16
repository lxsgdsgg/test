package com.bashiju.webservice.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bashiju.www.pojo.service.user.CustCustomerEntity;
/**
 * 
 * @ClassName:  UserRegisterMapper   
 * @Description:用戶注册映射接口   
 * @author: wangpeng
 * @date:   2018年7月17日 上午10:12:12   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface UserRegisterMapper {
	/**
	 * 
	 * @Title: queryIsExistMobile   
	 * @Description: 查询手机号码是否已存在  
	 * @param mobile 手机号码
	 * @return: long
	 */
    public long queryIsExistMobile(@Param("mobile")String mobile);
    
    /****
     * @Title: saveCustCustomer   
     * @Description: 保存用户信息   
     * @param custCustomerParam 用户注册时输入信息
     * @return: long
     */
    public long  saveCustCustomer(CustCustomerEntity custCustomerEntity);
    
    /**
     * @Title: updateCustCustomer   
     * @Description: 修改用户信息
     * @param paramMap 要修改的参数
     * @return: long
     */
    public long updateCustCustomer(Map<String,Object> paramMap);
}
