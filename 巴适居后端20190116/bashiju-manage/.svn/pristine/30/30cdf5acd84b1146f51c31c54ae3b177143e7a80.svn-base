package com.bashiju.manage.mapper;


import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  ResCustLevelConditionMapper   
 * @Description:客源等级条件映射接口  
 * @author: wangpeng
 * @date:   2018年5月18日 下午5:15:50   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface ResCustLevelConditionMapper {
	/**
	 * 
	 * @Title: queryResCustLevelCondition   
	 * @Description: 条件查询客源等级条件   
	 * @param paramMap 查询参数
	 * @return      
	 * @return: Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryResCustLevelCondition(Map<String,Object> paramMap);

	/**
	 * 
	 * @Title: queryResCustLevelConditionById   
	 * @Description: 通过id查询客源等级条件
	 * @param id 客源等级条件Id 
	 * @return      
	 * @return: Map<String,Object>
	 */
	public Map<String,Object> queryResCustLevelConditionById(@Param("id")String id);
}
