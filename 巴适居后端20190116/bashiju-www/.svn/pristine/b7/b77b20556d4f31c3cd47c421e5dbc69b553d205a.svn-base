package com.bashiju.www.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import com.bashiju.www.pojo.CustCustomer;
import com.bashiju.www.pojo.service.user.CustCustomerEntity;

public interface CustCustomerMapper {
	public List<CustCustomer> queryAllCustomer();
	public Long saveCustomer(CustCustomer custCustomer);
	
	
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
     * @Title: userMobileCheck   
     * @Description:条件验证手机号码
     * @param mobile 手机号码
     * @param solt 盐值
     * @return: boolean
     */
    public long userMobileCheck(@Param("mobile")String mobile,@Param("solt")String solt);
    
    /**
     * @Title: updateCustCustomer   
     * @Description: 修改用户信息
     * @param paramMap 要修改的参数
     * @return: long
     */
    public long updateCustCustomer(CustCustomerEntity custCustomerEntity );
    
    /**
     * @Title: customerLogin   
     * @Description: 用户登录   
     * @param mobile 电话号码
     * @param password 密码
     * @return: long
     */
    public Map<String,Object> customerLogin(@Param("mobile")String mobile,@Param("password")String password);
    
    /**
     * @Title: customerMobileLogin   
     * @Description: 短信登录   
     * @param mobile 手机号码
     * @return: Map<String,Object>
     */
    public Map<String,Object> customerMobileLogin(@Param("mobile")String mobile);
	
}