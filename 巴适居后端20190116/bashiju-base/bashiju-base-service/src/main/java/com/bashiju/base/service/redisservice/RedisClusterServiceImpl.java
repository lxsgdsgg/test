package com.bashiju.base.service.redisservice;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bashiju.api.RedisServiceApi;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.global.SYSGlobal;

import redis.clients.jedis.JedisCluster;


/**
 * redis 存取公共方法

* <p>Title: RedisService<／p>

* <p>Description: <／p>

* <p>Company: bashiju<／p> 

* @author zhaobindaxia

* @date 2018年3月17日
 */
@Service
public class RedisClusterServiceImpl implements RedisServiceApi{

	private static final Logger logger=LoggerFactory.getLogger(RedisClusterServiceImpl.class);
    @Autowired
    private JedisCluster jedisCluster;

    /**
     * 执行set操作
     * 
     * @param key
     * @param value
     * @return
     */
    public String set(final String key, final String value) {
    		return jedisCluster.set(key, value);
                 
    }

    /**
     * 执行指定key的get操作
     * 
     * @param key
     * @return
     */
    public String get(final String key) {
    	if(jedisCluster.exists(key)) {
                return jedisCluster.get(key);
    	}
    	return null;
    }

    /**
     * 执行删除操作
     * 
     * @param key
     * @return
     */
    public Long del(final String key) {

                return jedisCluster.del(key);
    }

    /**
     * 设置生存时间，单位为：秒
     * 
     * @param key
     * @param seconds
     * @return
     */
    public Long expire(final String key, final Integer seconds) {
                return jedisCluster.expire(key, seconds);
    }

    /**
     * 执行set操作并且设置生存时间，单位为：秒
     * 
     * @param key
     * @param value
     * @return
     */
    public String set(final String key, final String value, final Integer seconds) {
                String str = jedisCluster.set(key, value);
                jedisCluster.expire(key, seconds);
                return str;
    }
    
    /** 
     * 设置 map 
     * @param key 
     * @param value 
     */ 
    public  String setMap(String key ,Map<String,String> map){ 
              return jedisCluster.hmset(key, map);
    }  
    
    /** 
     * 设置 map并设置生存时间 
     * @param <T> 
     * @param key 
     * @param value 
     */ 
    public  String setMap(String key ,Map<String,String> map, final Integer seconds){ 

                String str = jedisCluster.hmset(key, map);
                jedisCluster.expire(key, seconds);
                return str; 
    }
    /** 
     * 获取Map 
     * @param <T> 
     * @param key 
     * @return list 
     */  
    public  Map<String,String> getMap(String key){  
		    	if(jedisCluster.exists(key)) {
		            	Map<String,String> str = jedisCluster.hgetAll(key);
		                return str;
		    	}
		    	return null;
    }
    /** 
     * 设置 list 
     * @param <T> 
     * @param key 
     * @param value 
     * @return 
     */
    public <T> String setList(final String key ,final List<T> list){  
    			String  str = jedisCluster.set(key,JSON.toJSONString(list));
            	 return str;
    }  
    
    /** 
     * 设置 list并设置生存时间 
     * @param <T> 
     * @param key 
     * @param value 
     * @return 
     */
    public <T> String setList(final String key ,final List<T> list, final Integer seconds){  
            	 String  str = jedisCluster.set(key,JSON.toJSONString(list));
            	 jedisCluster.expire(key, seconds);
            	 return str;
           
    }  
    /** 
     * 获取list 
     * @param <T> 
     * @param key 
     * @return list 
     */ 
    public List<String> getList(String key){
			    	if(jedisCluster.exists(key)) {
			    		logger.info("-------------------key="+key+"---"+jedisCluster.exists(key));
		            	String in =jedisCluster.get(key);
		            	logger.info("-------------------in="+in+"---res="+JSON.parseArray(in,String.class));
		                 return JSON.parseArray(in,String.class);
			    	}
			    	return null;

   
    }
    
    /**
     * 
     * @Title: setHash   
     * @Description: 设置 Hash   
     * @param: @param key
     * @param: @param field
     * @param: @param value  
     * @return: Long,正确存入返回1;否则为0      
     * @throws
     */
    public  Long setHash(final String key ,final String field,final String value){  
   	
            	 Long  str =jedisCluster.hset(key, field, value);
            	 jedisCluster.expire(key, SYSGlobal.REDIS_LOGINOUTTIME);
            	 return str;
            
    } 
    /**
     * 
     * @Title: setHash   
     * @Description: 设置 Hash   
     * @param: @param key
     * @param: @param field
     * @param: @param value  
     *  @param: @param isExpirationTime 是否设置过期时间
     * @return: Long,正确存入返回1;否则为0      
     * @throws
     */
    public  Long setHash(final String key ,final String field,final String value, boolean isExpirationTime){  
   	
            	 Long  str = jedisCluster.hset(key, field, value);
            	 if(isExpirationTime) {
                	 int hour=LocalTime.now().getHour();
                	 int seconds=(24-hour)*3600;
                	 jedisCluster.expire(key, seconds);
            	 }
            	 return str;            
       
    } 
    
    /**
     * 
     * @Title: getHash   
     * @Description: 获取Hash   
     * @param: @param key
     * @param: @param field   
     * @return: value      
     * @throws
     */
    public  String getHash(final String key ,final String field){  

           	 String  str = jedisCluster.hget(key, field);
           	 return str;          
   }
    

}
