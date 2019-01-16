package com.bashiju.www.service.user;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bashiju.www.pojo.service.user.CustCustomerEntity;
import com.bashiju.www.pojo.service.user.CustCustomerParam;

public interface CustCustomerService  {


	/**
	 * 
	 * @Title: queryIsExistMobile   
	 * @Description: 查询手机号码是否已存在   
	 * @param mobile 手机号码
	 * @return: boolean
	 */
	public boolean queryIsExistMobile(String mobile);
	
	/**
	 * @Title: saveCustCustomer   
	 * @Description: 保存用户在注册时输入信息 
	 * @param CustCustomerParam 用户输入参数
	 * @return: long
	 */
	public boolean  saveCustCustomer(CustCustomerParam custCustomerParam);
	
	  /**
     * @Title: updateCustCustomer   
     * @Description: 修改用户信息
     * @param custCustomerEntity 要修改的参数
     * @return: boolean
     */
    public boolean updateCustCustomer(CustCustomerEntity custCustomerEntity);
    
    /**
     * @Title: customerLogin   
     * @Description: 用户登录
     * @param mobile 电话号码
     * @param password 密码
     * @return: boolean
     */
    public boolean customerLogin(HttpServletRequest request,HttpServletResponse response,String mobile,String password,String validateCode);
	/**
	 * @Title: imageCheck   
	 * @Description: 验证码
	 * @param request
	 * @param response
	 * @return: String
	 */
    public String imageCheck(HttpServletRequest request,HttpServletResponse response);
    
    /**
     * @Title: sendMobileMsgVerfyCode   
     * @Description: 发送短信验证码
     * @param mobile 电话号码
     * @return: String
     */
    public String sendMobileMsgVerfyCode(String mobile);
    
    
    /**
     * @Title: customerMobileLogin   
     * @Description: 短信登录
     * @param mobile
     * @return: Map<String,Object>
     */
    public boolean customerMobileLogin(HttpServletRequest request,HttpServletResponse response,String mobile,String solt);

    /**
     * @Title: customerLoginout   
     * @Description: 退出登陆
     * @return: void
     * @throws UnsupportedEncodingException 
     */
    public void customerLoginout(HttpServletRequest request ,HttpServletResponse response);
}