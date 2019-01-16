package com.bashiju.manage.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  HousePropertyArticleClasses   
 * @Description:房产文章分类映射接口
 * @author: wangpeng
 * @date:   2018年7月19日 下午5:51:30   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface HousePropertyArticleClassesMapper {
	
	/**
	 * 
	 * @Title: queryHousePropertyArticleClasses   
	 * @Description: 房产文章分类  
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> queryHousePropertyArticleClasses(@Param("name")String name,@Param("level")String level,@Param("cityCode")String cityCode);

	
	
	/**
	 * 
	 * @Title: queryHousePropertyArticleClassesLevel   
	 * @Description: 查询房产文章分类等级   
	 * @return: List<Map<String,Object>>
	 */
	 List<Map<String,Object>>  queryHousePropertyArticleClassesNameByLevel(@Param("level")String level,@Param("type")String type);
	 /**
	  * 
	  * @Title: queryHousePropertyArticleClassesById   
	  * @Description:条件查询房产分类
	  * @param houseAticleId id
	  * @return: Map<String,Object>
	  */
	 Map<String,Object> queryHousePropertyArticleClassesById(@Param("houseAticleId")String houseAticleId);
	 
	 
	 /**
	  * 
	  * @Title: queryHousePropertyArticleClassesByTypeAndCityCode   
	  * @Description: 条件查询房产文章分类
	  * @param type 类型
	  * @param cityCode 城市编码
	  * @return: List<Map<String,Object>>
	  */
	 List<Map<String,Object>> queryHousePropertyArticleClassesByTypeAndCityCode(@Param("type")String type,@Param("cityCode")String cityCode);
	 /**
	  * 
	 	 * 查询所有文章类型
	 	 * @Description: 进入菜单主页面（详细描述） 
	 	 * @return 
	 	 * List<Map<String,Object>>
	  */
	 List<Map<String,Object>> queryAllArticleClasses();
	 /**
	  * 
	 	 * 查询城市关联的文章类型id
	 	 * @Description: 查询城市关联的文章类型id
	 	 * @param cityCode
	 	 * @return 
	 	 * List<Integer>
	  */
	 List<Integer> queryCityArticleClassesTree(@Param("cityCode")String cityCode);
	 /**
	  * 
	 	 * 查询城市关联文章类型列表
	 	 * @Description: 查询城市关联文章类型列表 
	 	 * @param name
	 	 * @param level
	 	 * @param cityCode
	 	 * @return 
	 	 * Page<Map<String,Object>>
	  */
	 Page<Map<String,Object>> queryCityArticleClasseList(@Param("name")String name,@Param("level")String level,@Param("cityCode")String cityCode);
}
