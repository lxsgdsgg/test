package com.bashiju.base.service.ssoqueryservice;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.bashiju.api.SsoQueryServiceApi;
import com.bashiju.base.service.redisservice.RedisClusterServiceImpl;
import com.bashiju.utils.global.SYSGlobal;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SsoQueryServiceImpl  extends RedisClusterServiceImpl implements SsoQueryServiceApi {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final Integer REDIS_TIME = 60 * 30;


    @Override
    public Map<String,Object> queryUserByToken(String key) {
    	
        Object jsonData = this.getHash(key, SYSGlobal.USER_LOGIN_ONLINE_TOKEN);
        
        if (jsonData==null) {
            // 登录超时
            return null;
        }

        // 重新设置Redis中的生存时间
        this.expire(key, REDIS_TIME);

        try {
            return MAPPER.readValue(jsonData.toString(), Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
