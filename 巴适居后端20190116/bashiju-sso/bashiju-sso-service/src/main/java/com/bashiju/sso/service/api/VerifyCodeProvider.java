package com.bashiju.sso.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bashiju.api.RedisServiceApi;

@Component
public class VerifyCodeProvider {
	@Autowired(required=false)
	private RedisServiceApi redisServiceApi;
	//验证码超时时间120秒
	private int outTime=120;
	/**
	 * 
	 * @Title: setVerifyCode   
	 * @Description: redis存入验证码 
	 * @param: @param key
	 * @param: @param value      
	 * @return: void      
	 * @throws
	 */
	public void setVerifyCode(String key, String value) {
		
        redisServiceApi.set(key,value,outTime);
	}
	/**
	 * 
	 * @Title: getVerifyCode   
	 * @Description: redis取出验证码  
	 * @param: @param key
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public String getVerifyCode(String key) {
		String code=redisServiceApi.get(key);
		return code;
	}
}
