package com.bashiju.manage.service;


import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  Res_house_level_conditionImpl   
 * @Description:房源等级条件接口
 * @author: wangpeng
 * @date:   2018年5月14日 下午2:31:23   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface ResHouseLevelConditionService {
	/**
	 * 
	 * @Title: queryResHouseLevelCsondition   
	 * @Description: 查询 房源等级条件信息(分页)
	 * @param transactionType
	 * @param page 当前页数
	 * @param limit 每页总条数
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> queryResHouseLevelCondition(Map<String,Object> paramMap,int page,int limit);
	
	/**
	 * @Title: queryResHouseLevelConditionById   
	 * @Description: 通过id查询房源等级条件  
	 * @param id 房源等级条件id
	 * @return: Map<String,Object>
	 */
	public Map<String,Object> queryResHouseLevelConditionById(String id);
	
	/**
	 * @Title: delResHouseLevelCondition   
	 * @Description: 逻辑删除房源等级条件   
	 * @param id
	 * @return: Boolean
	 */
	public Boolean delResHouseLevelCondition(String id);
	/**
	 * @Title: saveOrUpdateResHouseLevelCondition   
	 * @Description: 编辑和新增房源登记条件
	 * @param map
	 * @return: Boolean
	 */
	 boolean saveOrUpdateResHouseLevelCondition(Map<String,Object> map);
	
}


