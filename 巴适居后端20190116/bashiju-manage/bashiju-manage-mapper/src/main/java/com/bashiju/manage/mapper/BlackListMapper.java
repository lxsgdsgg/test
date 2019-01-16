package com.bashiju.manage.mapper;


import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  BlackListMapper   
 * @Description:中介黑名单映射接口
 * @author: wangpeng
 * @date:   2018年6月11日 下午3:28:43   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface BlackListMapper {
	/**
	 * 
	 * @Title: queryBlackListData   
	 * @Description: 查询中介黑名单
	 * @param companyId 公司id
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String,Object>>queryBlackListData(@Param("keyword")String keyword);
	
	/**
	 * 
	 * @Title: queryBlackListById   
	 * @Description: 条件查询中介黑名单
	 * @param id
	 * @return: Map<String,Object>
	 */
	Map<String,Object>queryBlackListById(@Param("id")String id);
}
