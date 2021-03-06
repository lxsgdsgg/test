package com.bashiju.customer.service;

import java.util.Map;

import com.github.pagehelper.Page;
/**
 * 客源带看记录接口
 * @ClassName:  DemandShowedRecordService   
 * @Description:客源带看记录接口
 * @author: wangpeng
 * @date:   2018年6月20日 上午10:03:24   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface DemandShowedRecordService {
/**
 * 条件查询客源带看记录  
 * @Title: queryDemandShowedRecordData   
 * @Description: 条件查询客源带看记录  
 * @param paramMap 查询参数
 * @param pageNum 当前页数
 * @param pageSize 每页总条数
 * @return: Page<Map<String,Object>>
 */
	Page<Map<String,Object>> queryDemandShowedRecordData(Map<String,Object> paramMap,int pageNum,int pageSize);
	/**
	 * 逻辑删除客源带看记录
	 * @Title: delCustShowedRecord   
	 * @Description: 逻辑删除客源带看记录
	 * @param id  客源带看记录id
	 * @return: boolean
	 */
	public boolean delDemandShowedRecord(String id);
	
	
	/**
	 * 条件查询客源带看记录   
	 * @Title: queryDemandShowedRecordById   
	 * @Description: 条件查询客源带看记录   
	 * @param id  客源带看记录id
	 * @return: Map<String,Object>
	 */
	Map<String,Object>  queryDemandShowedRecordById(String id);
	
	
	/**
	 * 保存或编辑客源带看记录
	 * @Title: saveOrUpdateDemandShowedRecord   
	 * @Description: 保存或编辑客源带看记录
	 * @param map
	 * @return: boolean
	 */
	boolean saveOrUpdateDemandShowedRecord(Map<String,Object>map);
	
	/**
	 * 条件查询新房信息 
	 * @Title: queryNewHouseInfoById   
	 * @Description: 条件查询新房信息 
	 * @param newHouseId 新房编号
	 * @return: Map<String,Object>
	 */
	Map<String,Object> queryNewHouseInfoById(String newHouseId);
}
