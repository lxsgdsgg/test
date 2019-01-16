/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  PropertyDynamicMapper.java   
 * @Package com.bashiju.housing.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年8月17日 下午3:30:13   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.housing.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

/**
 * 楼盘动态映射接口    
 * @ClassName:  PropertyDynamicMapper   
 * @Description:楼盘动态映射接口   
 * @author: wangpeng
 * @date:   2018年8月17日 下午3:30:13   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface PropertyDynamicMapper {
	  /**
	   * 分页查询楼盘动态
	   * @Title: queryPropertyDynamic   
	   * @Description: 分页查询楼盘动态
	   * @param projectId
	   * @return: Page<Map<String,Object>>
	   */
      Page<Map<String,Object>> queryPropertyDynamicByProjectId(@Param("projectId")String projectId);
      /**
       * 新增或修改楼盘动态   
       * @Title: SaveOrUpdatePropertyDynamic   
       * @Description: 新增或修改楼盘动态   
       * @param paramMap 参数
       * @return: boolean
       */
      boolean SaveOrUpdatePropertyDynamic(Map<String,Object> paramMap);
      /**
       * 分页查询预售证信息列表
       * @Title: queryPresalePermitListInfo   
       * @Description: 分页查询预售证信息列表
       * @param projectId 新房楼盘详情编号
       * @return: Page<Map<String,Object>>
       */
      Page<Map<String,Object>> queryPresalePermitListInfoByProjectId(@Param("projectId")String projectId);
//      /**
//       * 条件查询预售证列表信息
//       * @Title: queryPresalePermitListInfoById   
//       * @Description: 条件查询预售证列表信息
//       * @param id 预售证列表信息编号
//       * @return: Map<String,Object>
//       */
//      Map<String,Object> queryPresalePermitListInfoById(@Param("id")String id);
      
      /**
       * 分页查询楼盘开盘时间
       * @Title: queryPropertyOpenDate   
       * @Description: 分页查询楼盘开盘时间
       * @param projectId 新房楼盘详情编号
       * @return: Page<Map<String,Object>>
       */
      Page<Map<String,Object>> queryPropertyOpenDateByProjectId(@Param("projectId")String projectId);
      
      /**
       * 
       * @Title: queryPropertyOpenDateById   
       * @Description: 条件查询楼盘开盘时间  
	   * @param id 楼盘开盘时间 编号
       * @return: Map<String,Object>
       */
      Map<String,Object> queryPropertyOpenDateById(@Param("id")String id);
      
      /**
       * 条件查询楼盘价格历史
       * @Title: queryPropertyPriceTrendByProjectId   
       * @Description: 条件查询楼盘价格历史
       * @param projectId 新房楼盘详情编号
       * @return: Page<Map<String,Object>>
       */
      Page<Map<String,Object>> queryPropertyPriceTrendByProjectId(@Param("projectId")String projectId);
      
      
      /**
       * 条件查询交房时间
       * @Title: queryRoomTimeByProjectId   
       * @Description: 条件查询交房时间
       * @param projectId 新房楼盘详情编号
       * @return: Page<Map<String,Object>>
       */
      Page<Map<String,Object>> queryRoomTimeByProjectId(@Param("projectId")String projectId);
      /**
       * 
       * @Title: queryRoomTimeById   
       * @Description: 条件查询楼盘交房日期
       * @param id 楼盘交房日期编号
       * @return: Map<String,Object>
       */
  	  Map<String,Object> queryRoomTimeById(@Param("id")String id);
      
      
      
}
