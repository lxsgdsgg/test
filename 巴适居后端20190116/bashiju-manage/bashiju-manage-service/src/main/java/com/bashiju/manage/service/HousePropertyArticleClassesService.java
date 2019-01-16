package com.bashiju.manage.service;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  HousePropertyArticleClassesService   
 * @Description:房产文章分类接口 
 * @author: wangpeng
 * @date:   2018年7月19日 下午6:03:34   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface HousePropertyArticleClassesService {
	/**
	 * 
	 * @Title: queryHousePropertyArticleClasses   
	 * @Description: 查询房产文章分类  
	 * @return      
	 * @return: Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryHousePropertyArticleClasses(String name,String level,String cityCode,int page,int limit);
	
	/**
	 * 
	 * @Title: saveHousePropertyArticleClasses   
	 * @Description: 新增房产文章分类
	 * @param map
	 * @return      
	 * @return: boolean
	 */
    boolean saveOrUpdateHousePropertyArticleClasses(Map<String,Object> map);
    
    
    

    /**
     * 
     * @Title: queryHousePropertyArticleClassesNameByLevel   
     * @Description: 查詢房产文章分类等级
     * @return      
     * @return: List<Map<String,Object>>
     */
    List<Map<String,Object>> queryHousePropertyArticleClassesNameByLevel(String level,String type);
    
    /**
     * 
     * @Title: delHousePropertyArticleClasses   
     * @Description: 逻辑删除房产文章分类  
     * @param houseAticleId
     * @return      
     * @return: boolean
     */
    boolean delHousePropertyArticleClasses(String houseAticleId);
    /**
     * 
     * @Title: queryHousePropertyArticleClassesById   
     * @Description: 条件查询房产文章分类 
     * @param houseAticleId 主键id
     * @return      
     * @return: Map<String,Object>
     */
    Map<String,Object> queryHousePropertyArticleClassesById(String houseAticleId);
    
    
    /**
     * 
     * @Title: queryHousePropertyArticleClassesByTypeAndCityCode   
     * @Description: 条件查询房产文章分类 
     * @param type 类型
     * @param cityCode 城市代码
     * @return      
     * @return: List<Map<String,Object>>
     */
    List<Map<String,Object>> queryHousePropertyArticleClassesByTypeAndCityCode(String type,String cityCode);
    /**
     * 
    	 * 查询文章类型树形数据
    	 * @Description: 查询文章类型树形数据 
    	 * @return 
    	 * List<Map<String,Object>>
     */
    List<Map<String,Object>> queryArticleClassTree();
    /**
     * 
    	 * 查询城市关联的文章类型id
    	 * @Description: 查询城市关联的文章类型id 
    	 * @param cityCode
    	 * @return 
    	 * List<Integer>
     */
    List<Integer> queryCityArticleClassesTree(String cityCode);
    /**
     * 
    	 * 查询城市关联的文章类型列表
    	 * @Description: 查询城市关联的文章类型列表 
    	 * @param name
    	 * @param level
    	 * @param cityCode
    	 * @param page
    	 * @param limit
    	 * @return 
    	 * Page<Map<String,Object>>
     */
    public Page<Map<String,Object>> queryCityArticleClasseList(String name,String level,String cityCode,int page,int limit);
    /**
     * 
    	 * 保存城市关联文章类型数据
    	 * @Description: 保存城市关联文章类型数据
    	 * @param cityCode
    	 * @param jsonArray 
    	 * void
     */
    public void saveCityArticleClasses(String cityCode,String jsonArray);
}
