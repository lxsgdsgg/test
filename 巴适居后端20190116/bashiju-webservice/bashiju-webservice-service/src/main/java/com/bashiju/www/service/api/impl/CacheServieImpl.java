package com.bashiju.www.service.api.impl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.api.EmailServiceApi;
import com.bashiju.api.RedisServiceApi;
import com.bashiju.wutils.redis.ICacheService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CacheServieImpl implements ICacheService {
	Logger logger=LoggerFactory.getLogger(this.getClass());
	final static ObjectMapper MAPPER=new ObjectMapper();
	
	@Autowired(required=false)
	RedisServiceApi redisServiceApi; 
	@Autowired(required=false)
	EmailServiceApi emailServiceApi;
	public String getCache(String key) {
		try {
			if(StringUtils.isNotEmpty(key)) {
				return redisServiceApi.get(key);
			}
			return null;
		} catch (Exception e) {
			logger.error("C端获取Redis key={}时出现故障:{}",key, e.getMessage());
			emailServiceApi.sendEmailToAdmin("C端获取Redis key="+key+"时出现故障", e.getMessage(),"1");
			return null;
		}

	}
	
	public boolean delCache(String key) {
		try {
			if(StringUtils.isNotEmpty(key)) {
				redisServiceApi.del(key);
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.error("C端删除Redis key={}时出现故障:{}",key, e.getMessage());
			emailServiceApi.sendEmailToAdmin("C端删除Redis key="+key+"时出现故障", e.getMessage(),"1");
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
			logger.error("C端存Redis key={},value={} 时出现故障:{}",key,value, e.getMessage());
			emailServiceApi.sendEmailToAdmin("C端存Redis key="+key+",value="+value+"时出现故障", e.getMessage(),"1");
		}
	}
	
	public void saveStringCache(String key,String value,int time) {
		try {
			if(value==null) {
				time=300;
			}
			redisServiceApi.set(key,value,time);			
		} catch (Exception e) {
			logger.error("C端存Redis key={},value={}时出现故障:{}",key, value,e.getMessage());
			emailServiceApi.sendEmailToAdmin("C端存Redis key="+key+",value="+value+"时出现故障", e.getMessage(),"1");
		}
	}
}
