package com.bashiju.www.service.comm.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.api.EmailServiceApi;
import com.bashiju.api.RedisServiceApi;
import com.bashiju.www.service.comm.ICacheService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CacheServieImpl implements ICacheService {
	Logger logger=Logger.getLogger(this.getClass());
	final static ObjectMapper MAPPER=new ObjectMapper();
	
	@Autowired(required=false)
	RedisServiceApi redisServiceApi; 
	@Autowired(required=false)
	EmailServiceApi emailServiceApi;
	public String getCache(String key) {
		String json=null;
		try {
			json = redisServiceApi.get(key);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			emailServiceApi.sendEmailToAdmin("C端获取Redis出现故障", e.getMessage(),"1");
			return null;
		}

	}
	
	public boolean delCache(String key) {
		try {
			redisServiceApi.del(key);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			emailServiceApi.sendEmailToAdmin("C端删除Redis出现故障", e.getMessage(),"1");
			return false;
		}

	}
	
	public void saveCache(String key,Object value,int time) {
		try {
			if(value==null) {
				time=300;
			}
			String data=MAPPER.writeValueAsString(value);
			redisServiceApi.set(key,data,time);			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			emailServiceApi.sendEmailToAdmin("C端存取Redis出现故障", e.getMessage(),"1");
		}
	}
	
	public void saveStringCache(String key,String value,int time) {
		try {
			if(value==null) {
				time=300;
			}
			redisServiceApi.set(key,value,time);			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			emailServiceApi.sendEmailToAdmin("C端存取Redis出现故障", e.getMessage(),"1");
		}
	}
}
