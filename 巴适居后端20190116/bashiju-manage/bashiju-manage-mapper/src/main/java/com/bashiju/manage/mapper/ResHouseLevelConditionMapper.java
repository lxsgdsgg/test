package com.bashiju.manage.mapper;


import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  Res_house_level_condition   
 * @Description:房源等级条件管理映射   
 * @author: wangpeng
 * @date:   2018年5月14日 下午2:16:36   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface ResHouseLevelConditionMapper {
		/**
		 * @Title: queryResHouseLevelCondition   
		 * @Description: 条件查询房源等级条件信息   
		 * @param paramMap 查询参数
		 * @return: Page<Map<String,Object>>
		 */
		Page<Map<String,Object>> queryResHouseLevelCondition(Map<String,Object> paramMap);
		
		/**
		 * @Title: queryResHouseLevelConditionById   
		 * @Description: 通过id查询出房源等级条件信息
		 * @param id 房源等级条件id
		 * @return: Map<String,Object>
		 */
		public Map<String,Object> queryResHouseLevelConditionById(@Param("id")String id);
		
}
