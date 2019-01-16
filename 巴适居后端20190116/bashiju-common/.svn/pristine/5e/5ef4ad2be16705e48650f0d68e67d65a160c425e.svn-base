/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  TreesInfoMapper.java   
 * @Package com.bashiju.base.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年6月25日 下午4:09:04   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName:  TreesInfoMapper   
 * @Description:各类树结构服务   
 * @author: yangz
 * @date:   2018年6月25日 下午4:09:04   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface TreesInfoMapper {

	/**
	 * @Description: 查询部门树信息   
	 * <br>[公司]-->市-->大区-->区-->店-->组
	 * @param roleGroup 当前用户所属的角色组
	 * @param userId 当前用户id     
	 * @return: List<Map<String,Object>>      
	 */ 
	List<Map<String, Object>> queryDeptTrees(@Param("roleGroup")String roleGroup, @Param("userId")String userId);

	/**
	 * @Description: 查询用户树信息   
	 * <br>[公司]-->市-->大区-->区-->店-->组-->人
	 * @param roleGroup 当前用户所属的角色组
	 * @param userId 当前用户id
	 * @return: List<Map<String,Object>>      
	 */ 
	List<Map<String, Object>> queryDeptInfoToCreateTree(@Param("roleGroup")String roleGroup, @Param("userId")String userId);

	/**
	 * @Description: 查询小区树信息   
	 * <br>市-->区-->片区-->小区
	 * @param rgCode 行政区域代码
	 * @param userId 当前用户id
	 * @return: List<Map<String,Object>>      
	 */ 
	List<Map<String, Object>> getRegionList(@Param("rgCode")String rgCode);

	/**
	 * @Description: 查询行政区域信息 
	 * <br> 省-->市-->区(县)
	 * @return: List<Map<String,Object>>      
	 */ 
	List<Map<String, Object>> queryAreaSelect();

}
