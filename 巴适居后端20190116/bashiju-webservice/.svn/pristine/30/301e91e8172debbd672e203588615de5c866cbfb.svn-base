package com.bashiju.www.service.comm.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bashiju.api.RedisServiceApi;
import com.bashiju.webservice.mapper.IAreaMapper;
import com.bashiju.webservice.mapper.ImgMapper;
import com.bashiju.wutils.log.SystemServiceLog;
import com.bashiju.wutils.redis.ICacheService;
import com.bashiju.www.global.WebGlobal;
import com.bashiju.www.pojo.service.out.comm.AreaResult;
import com.bashiju.www.pojo.service.out.comm.ImgResult;
import com.bashiju.www.pojo.service.out.comm.RegionResult;
import com.bashiju.www.service.comm.IAreaService;
import com.bashiju.www.service.comm.ImgService;
import com.github.pagehelper.util.StringUtil;
/**
 * 
 * @ClassName:  ImgServiceImpl   
 * @Description:TODO(图片服务具体实现)   
 * @author: liwen
 * @date:   2018年10月17日 下午17:24:03   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@SystemServiceLog(sourceType="图片服务") 
@Service
public class ImgServiceImpl implements ImgService {
	 
	@Autowired(required=false)
	RedisServiceApi redisServiceApi;
	
	@Autowired
	ImgMapper imgMapper ;
	@Autowired
	ICacheService cacheService;
	
	/**
	 * 
		 * 根据城市查询图片
		 *  先从redis中取值，没有再查询数据库并存入redis
		 *  redis的key为前缀(WebGlobal.COMIMG_REDIS_PREFIX)+城市代码
		 * 
		 * @Description: 根据城市查询图片
		 * @param cityCode 城市代码
		 * 
		 * @return 
		 * List<AreaResult>
	 */
	public List<ImgResult> queryImg(String cityCode,String type){
		String key = WebGlobal.COMIMG_REDIS_PREFIX+cityCode;
		String imgStr = cacheService.getCache(key);
		//cacheService.delCache(key);
		List<ImgResult> imgList = null;
		if(StringUtils.isEmpty(imgStr)) {
			imgList = imgMapper.queryImg(cityCode);
			cacheService.saveCache(key, imgList,WebGlobal.OTHER_EXPTIME);
		}else {
			imgList = JSON.parseArray(imgStr, ImgResult.class);
		}
		
		List<ImgResult> getres = new ArrayList<>();
		for(ImgResult img : imgList) {
			if(img.getType().equals(type)) {
				getres.add(img);
			}
		}
		
		return getres;
		//return null;
	}
	
	
}
