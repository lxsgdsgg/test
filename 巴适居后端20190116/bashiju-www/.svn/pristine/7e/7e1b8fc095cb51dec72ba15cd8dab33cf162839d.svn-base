/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerHouseTransitScheduleServiceImpl.java   
 * @Package com.bashiju.www.service.user.impl      
 * @author: zuoyuntao     
 * @date:   2018年7月26日 下午4:13:12   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.service.user.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.wutils.util.Md5EncryptUtils;
import com.bashiju.www.global.WebGlobal;
import com.bashiju.www.mapper.CustomerTransitScheduleMapper;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.out.usercentral.CustomerHouseTransitScheduleEntity;
import com.bashiju.www.service.comm.ICacheService;
import com.bashiju.www.service.user.ICustomerHouseTransitScheduleService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 过户进度数据读取服务
 * @ClassName:CustomerHouseTransitScheduleServiceImpl
 * @Description:过户进度数据读取服务
 * @author:zuoyuntao
 * @date:2018年7月26日 下午4:13:12
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class CustomerHouseTransitScheduleServiceImpl 
		implements ICustomerHouseTransitScheduleService{
	/**
	 * Redis数据读取服务接口
	 */
	@Autowired
	private ICacheService mICacheService;
	/**
	 * 读取房源过户进度数据信息持久层接口
	 */
	@Autowired
	private CustomerTransitScheduleMapper mCustomerTransitScheduleMapper;
	/**
	 * 对象映射
	 */
	private static ObjectMapper MAPPER = new ObjectMapper();
	/**
	 * <p>Title: queryHouseTransitScheculeDataList</p>   
	 * <p>Description: 读取过户进度数据信息</p>   
	 * @param custId 登陆用户ID
	 * @return   
	 * @see com.bashiju.www.service.user.ICustomerHouseTransitScheduleService#queryHouseTransitScheculeDataList(java.lang.String)
	 */
	@Override
	public WebPage<CustomerHouseTransitScheduleEntity> queryHouseTransitScheculeDataList(
			String custId,int page,int limit) {
		
		String redisKey = Md5EncryptUtils.md5Encrypt(
			"queryHouseTransitScheculeDataList" + custId.trim() + page + limit);
		
		WebPage<CustomerHouseTransitScheduleEntity> retList = readDataFromRedisService(redisKey);
		if(retList.getPageSize() == 0) {
			PageHelper.startPage(page,limit);
			Page<CustomerHouseTransitScheduleEntity> pageObj = mCustomerTransitScheduleMapper.queryHouseTransitScheculeDataList(custId);
			if(pageObj.size() > 0) {
				retList = new WebPage<CustomerHouseTransitScheduleEntity>(pageObj);
				mICacheService.saveCache(redisKey, retList, WebGlobal.OTHER_EXPTIME);
			}
		}
		return retList;
	}
	
	/**
	 * 根据key从Redis读取数据 
	 * @Title: readDataFromRedisService
	 * @author: zuoyuntao  
	 * @Description:根据key从Redis读取数据 
	 * @param key
	 * @return      
	 * List<CustomerHouseTransitScheduleEntity> JSON 格式为：
	 */
	@SuppressWarnings({ "unchecked" })
	private WebPage<CustomerHouseTransitScheduleEntity> readDataFromRedisService(String key){
		WebPage<CustomerHouseTransitScheduleEntity> retPage
				= new WebPage<CustomerHouseTransitScheduleEntity>();
		String retObj = mICacheService.getCache(key);
		if(null != retObj) {
			try {
				retPage = MAPPER.readValue(retObj, WebPage.class);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return retPage;
	}
	
}
