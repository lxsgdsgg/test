package com.bashiju.housing.service;


import java.util.Map;

import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  HsKeyService   
 * @Description:钥匙管理接口
 * @author: wangpeng
 * @date:   2018年6月6日 下午5:50:12   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface HsKeyService {
	/**
	 * 
	 * @Title: queryHsKeyData   
	 * @Description: 查询钥匙信息
	 * @param houseStatus 房源状态
	 * @param reciver 收钥匙人Id
	 * @param keyStatus 钥匙状态
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param buildingsName 座栋信息
	 * @param pageNum 当前页数
	 * @param pageSize 每页总条数
	 * @return      
	 * @return: Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryHsKeyData(	Map<String,Object> paramMap,int pageNum,int pageSize);
	/**
	 * 
	 * @Title: HsKeyStatusUpdate   
	 * @Description: 更改钥匙状态
	 * @param map
	 * @return      
	 * @return: Boolean
	 */
	public boolean hsKeyStatusUpdate(Map<String,Object>map);
	
	
	/**
	 * 
	 * @Title: queryHsKeyLog   
	 * @Description: 查询钥匙日志信息   
	 * @param hsKeyId 钥匙id
	 * @param pageNum 当前页数
	 * @param pageSize 每页条数
	 * @return      
	 * @return: Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>>  queryHsKeyLog(String hsKeyId,int pageNum,int pageSize);
	/**
	 * 
	 * @Title: queryUserById   
	 * @Description: 条件查找用户  
	 * @return      
	 * @return: Map<String,Object>
	 */
	 public Map<String,Object> queryUserById(String id);
	 
	 /**
	  *  退还钥匙（逻辑删除）
	  * @Title: returnKey   
	  * @Description: 退还钥匙（逻辑删除）
	  * @param id 钥匙主键编号
	  * @return: boolean
	  */
	 public boolean returnKey(String id);
	 
	 /**
	  * 钥匙主键编号（即：清空借钥匙人的信息）
	  * @Title: keyBack   
	  * @Description: 归还钥匙
	  * @param id 钥匙主键编号
	  * @return: boolean
	  */
	 public boolean keyBack(String id);
}
