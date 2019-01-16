/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  DeptMapper.java   
 * @Package com.bashiju.common.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年7月11日 下午3:59:56   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.common.mapper;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName:  DeptMapper   
 * @Description:部门信息服务持久层 
 * @author: yangz
 * @date:   2018年7月11日 下午3:59:56   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface DeptMapper {
	
	
	/**
	 * @Description: 根据用户id和部门类型查询该用户属于该部门类型的部门编号   
	 * @param userId 用户id
	 * @param deptTypeId 部门类型id
	 * @return: String  
	 */ 
	String getDeptIdByUserAndDeptType(@Param("userId")String userId, @Param("deptTypeId")String deptTypeId);

	/**
	 * @Description: 根据公司编号和城市编码查询该公司下属于该城市的部门编号  
	 * @param companyId 公司编码
	 * @param cityCode 城市编码
	 * @return: String     
	 */ 
	String getDeptIdByCompanyAndCityCode(@Param("companyId")String companyId, @Param("cityCode")String cityCode);

}
