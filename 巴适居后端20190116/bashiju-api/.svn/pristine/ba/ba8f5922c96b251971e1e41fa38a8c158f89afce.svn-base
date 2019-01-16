/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  DeptServiceApi.java   
 * @Package com.bashiju.api   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年7月11日 下午3:48:37   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.api;

/**   
 * @ClassName:  DeptServiceApi   
 * @Description:部门信息服务对外接口   
 * @author: yangz
 * @date:   2018年7月11日 下午3:48:37   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface DeptServiceApi {

	/**
	 * @Description: 根据用户id和部门类型查询该用户属于该部门类型的部门编号   
	 * @param userId 用户id
	 * @param deptTypeId 部门类型id
	 * @return: String
	 */
	public String getDeptIdByUserAndDeptType(String userId,String deptTypeId);
	
	
	/**
	 * @Description: 根据公司编号和城市编码查询该公司下属于该城市的部门编号  
	 * @param companyId 公司编码
	 * @param cityCode 城市编码
	 * @return: String
	 */
	public String getDeptIdByCompanyAndCityCode(String companyId,String cityCode);
}
