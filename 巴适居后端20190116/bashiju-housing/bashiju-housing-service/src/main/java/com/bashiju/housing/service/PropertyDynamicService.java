/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  PropertyDynamicService.java   
 * @Package com.bashiju.housing.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年8月17日 下午3:42:52   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.housing.service;

import java.util.Map;

import com.github.pagehelper.Page;

/**   
 * 楼盘动态接口
 * @ClassName:  PropertyDynamicService   
 * @Description:楼盘动态接口
 * @author: wangpeng
 * @date:   2018年8月17日 下午3:42:52   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface PropertyDynamicService {
	
	/**
	 * 分页查询楼盘动态信息
	 * @Title: queryPropertyDynamicByProjectId   
	 * @Description: 分页查询楼盘动态信息   
	 * @param page 当前页数
	 * @param limit 每页总条数
	 * @param projectId 新房楼盘详情编号
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> queryPropertyDynamicByProjectId(String projectId,int page,int limit);
	/**
	 * 新增或修改楼盘动态信息  
	 * @Title: saveOrUpdatePropertyDynamic   
	 * @Description: 新增或修改楼盘动态信息  
	 * @param paramMap
	 * @return: boolean
	 */
	boolean saveOrUpdatePropertyDynamic(Map<String,Object> paramMap);
	/**
	 *  逻辑删除楼盘动态信息
	 * @Title: delPropertyDynamicById   
	 * @Description: 逻辑删除楼盘动态信息
	 * @param id 楼盘动态编号
	 * @return: boolean
	 */
	boolean delPropertyDynamicById(String id);
	/**
	 * 分页查询预售证信息列表
	 * @Title: queryPresalePermitListInfoByProjectId   
	 * @Description: 分页查询预售证信息列表
	 * @param page 当前页数
	 * @param limit 每页总条数
	 * @param projectId 新房楼盘详情编号
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> queryPresalePermitListInfoByProjectId(String projectId,int page,int limit);
	
//	/**
//	 *  条件查询预售证信息列表
//	 * @Title: queryPresalePermitListInfoById   
//	 * @Description: 条件查询预售证信息列表
//	 * @param id 预售证信息列表编号
//	 * @return: Map<String,Object>
//	 */
//	Map<String,Object> queryPresalePermitListInfoById(String id);
	
	/**
	 * 逻辑删除预售证信息列表 
	 * @Title: delPresalePermitListInfoById   
	 * @Description: 逻辑删除预售证信息列表 
	 * @param id 预售证信息列表编号
	 * @return: boolean
	 */
	boolean delPresalePermitListInfoById(String id);
	/**
	 *  新增或修改预售证信息列表
	 * @Title: saveOrUpdatePresalePermitListInfo   
	 * @Description: 新增或修改预售证信息列表
	 * @param paramMap
	 * @return: boolean
	 */
	boolean saveOrUpdatePresalePermitListInfo(Map<String,Object> paramMap);
	
	/**
	 * 分页查询楼盘开盘日期
	 * @Title: queryPropertyOpenDateByProjectId   
	 * @Description: 分页查询楼盘开盘日期
	 * @param page 当前也属于
	 * @param  limit 每页总条数 
	 * @param projectId 新房楼盘详情编号
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> queryPropertyOpenDateByProjectId(String projectId,int page,int limit);
	
	/**
	 * 条件查询楼盘开盘日期
	 * @Title: queryPropertyOpenDateById   
	 * @Description: 条件查询楼盘开盘日期
	 * @param id 楼盘开盘日期编号
	 * @return: Map<String,Object>
	 */
	Map<String,Object> queryPropertyOpenDateById(String id);
	/**
	 * 逻辑删除楼盘开日期
	 * @Title: delPropertyOpenDateById   
	 * @Description:逻辑删除楼盘开盘日期
	 * @param id 楼盘开盘日期编号
	 * @return: boolean
	 */
	boolean delPropertyOpenDateById(String id);
	
	/**
	 *  新增或修改楼盘开盘日期 
	 * @Title: saveOrUpdatePropertyOpenDate   
	 * @Description: 新增或修改楼盘开盘日期
	 * @param paramMap
	 * @return: boolean
	 */
	boolean saveOrUpdatePropertyOpenDate(Map<String,Object> paramMap);
	
	/**
	 * 条件查询楼盘价格历史
	 * @Title: queryPropertyPriceTrendByProjectId   
	 * @Description: 条件查询楼盘价格历史
	 * @param projectId  新房楼盘详情编号
	 * @param page 当前页数
	 * @param limit 每页总条数
	 * @return: Page<Map<String,Object>>
	 */
    Page<Map<String,Object>> queryPropertyPriceTrendByProjectId(String projectId,int page,int limit);
    /**
     * 逻辑删除楼盘价格历史
     * @Title: delPropertyPriceTrendById   
     * @Description: 逻辑删除楼盘价格历史
     * @param id 楼盘价格历史编号
     * @return: boolean
     */
    boolean delPropertyPriceTrendById(String id,String projectId);
    /**
     * 新增或修改楼盘价格历史
     * @Title: saveOrUpdatePropertyPriceTrend   
     * @Description: 新增或修改楼盘价格历史
     * @param paramMap
     * @return: boolean
     */
    boolean saveOrUpdatePropertyPriceTrend(Map<String,Object> paramMap);
    
    /**
     * 条件查询楼盘交房日期 
     * @Title: queryRoomTimeByProjectId   
     * @Description: 条件查询楼盘交房日期  
     * @param projectId 新房楼盘详情编号
     * @param page 当前页数
     * @param limit 每页总条数
     * @return: Page<Map<String,Object>>
     */
    Page<Map<String,Object>> queryRoomTimeByProjectId(String projectId,int page,int limit);
    
    /**
     * 条件查询楼盘交房日期
     * @Title: queryRoomTimeById   
     * @Description:条件查询楼盘交房日期  
     * @param id 楼盘交房日期编号
     * @return: Map<String,Object>
     */
    Map<String,Object> queryRoomTimeById(String id);
    /**
     * 逻辑删除楼盘交房日期
     * @Title: delRoomTimeById   
     * @Description: 逻辑删除楼盘交房日期
     * @param id  楼盘交房日期编号
     * @return: boolean
     */
    boolean delRoomTimeById(String id);
    /**
     * 新增或修改楼盘交房日期
     * @Title: saveOrUpdateRoomTime   
     * @Description: 新增或修改楼盘交房日期
     * @param paramMap
     * @return: boolean
     */
    boolean saveOrUpdateRoomTime(Map<String,Object> paramMap);
}
