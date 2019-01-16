package com.bashiju.housing.service;


import java.util.Map;

import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  HsFollowRecordService   
 * @Description:房源跟进服务接口
 * @author: wangpeng
 * @date:   2018年6月5日 下午2:54:16   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface HsFollowRecordService {
	/**
	 * 
	 * @Title: queryHsFollowRecord   
	 * @Description: TODO(条件查询房源跟进)   
	 * @param paramMap 查询参数
	 * @param page 当前页数
	 * @param limit 每页总条数
	 * @return      
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String,Object>>  queryHsFollowRecord(Map<String,Object> paramMap,int page,int limit);
	
	/**
	 * 
	 * @Title: queryAllHsFollowRecordByHouseId   
	 * @Description: 通过房源编号查询房源跟进信息(分页)
	 * @param houseId 房源编号
	 * @param page 当前页数
	 * @param limit 每页总跳数
	 * @return      
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> queryAllHsFollowRecordByHouseId(String houseId,int page,int limit);
}
