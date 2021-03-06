/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CommonMapper.java   
 * @Package com.bashiju.anal.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年11月21日 下午6:11:09   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.anal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName:  CommonMapper   
 * @Description:公共类接口  
 * @author: yangz
 * @date:   2018年11月21日 下午6:11:09   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface AnalCommonMapper {
	
	/**
	 * 查询部门树列表 
	 * @Description:  查询部门树列表 
	 * @param roleGroup 所属角色组
	 * @return: List<Map<String,Object>>
	 */ 
	public List<Map<String, Object>> queryDeptTrees(@Param("roleGroup")String roleGroup);

	
	/**
	 * 查询公司开通城市信息  
	 * @Description: 查询公司开通城市信息  
	 * @param roleGroup 用户所属角色组
	 * @param userId 当前用户编号 
	 * @param companyId 公司编号
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryCompanyCityInfo(@Param("roleGroup")String roleGroup,@Param("userId")String userId,@Param("companyId")String companyId);
	
	
	/**
	 * 根据部门编号获取所属部门类型
	 * @Description: 根据部门编号获取所属部门类型 
	 * @param deptId 部门编号
	 * @return: Integer
	 */
	public Integer getDeptTypeById(@Param("deptId")String deptId);


	/**     
	 * @Description: 根据角色组查询公司信息  
	 * @param roleGroup 所属角色组
	 * @return: List<Map<String, Object>>      
	 */ 
	public List<Map<String, Object>> queryCompanyByRoleGroup(@Param("roleGroup")String roleGroup);
}
