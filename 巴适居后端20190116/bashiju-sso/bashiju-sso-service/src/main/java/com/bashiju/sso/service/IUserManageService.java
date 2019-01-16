package com.bashiju.sso.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IUserManageService {
	
	/**
	 * 检查用户登录的手机号码是否在数据库存在
	 * @param phone
	 * @return
	 */
	public boolean checkUserPhone(String phone);
	
	/**
	 * 用户登录
	 * @param userPhone
	 * @param userPass
	 * @param ip
	 * @return
	 * @throws Exception
	 */
	public String doLogin(String userPhone,String userPass,String ip,String areaCode,String areaName,HttpServletRequest request, HttpServletResponse response)throws Exception;
	
	public Map<String, String> login(String userPhone,String userPass,String ip,String areaCode,String areaName,String hardware,HttpServletRequest request, HttpServletResponse response)throws Exception;
	/**
	 * 注销删除redis数据
	 * @param token
	 * @return
	 */
	public void delRedisData(String token,String ip,String areaCode,String areaName);
	/**
	 * 
		 * 查询登录用户信息
		 * @Description: 查询登录用户信息
		 * @param request
		 * @return 
		 * Map<String,Object>
	 */
	public Map<String,Object> queryUserInfo(HttpServletRequest request) throws Exception;
	/**
	 * 
		 * 锁屏解锁
		 * @Description: 锁屏解锁 
		 * @param userPhone
		 * @param userPass
		 * @return 
		 * String
	 */
	public String unlock(String userPhone,String userPass)throws JsonProcessingException;
}
