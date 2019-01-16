/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  DeptServiceApiImpl.java   
 * @Package com.bashiju.common.service.manageservice   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年7月11日 下午3:53:26   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.common.service.manageservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.bashiju.api.DeptServiceApi;
import com.bashiju.common.mapper.DeptMapper;

/**   
 * @ClassName:  DeptServiceApiImpl   
 * @Description:部门信息对外服务   
 * @author: yangz
 * @date:   2018年7月11日 下午3:53:26   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class DeptServiceApiImpl implements DeptServiceApi {
	
	@Autowired
	private DeptMapper deptMapper;

	/**
	 * @Description: 根据用户id和部门类型查询该用户属于该部门类型的部门编号   
	 * @param userId 用户id
	 * @param deptTypeId 部门类型id
	 * @return: String
	 * @see com.bashiju.api.DeptServiceApi#getDeptIdByUserAndDeptType(java.lang.String, java.lang.String)   
	 */
	@Override
	public String getDeptIdByUserAndDeptType(String userId, String deptTypeId) {
		if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(deptTypeId))
			return null;
		return deptMapper.getDeptIdByUserAndDeptType(userId,deptTypeId);
	}

	/**
	 * @Description: 根据公司编号和城市编码查询该公司下属于该城市的部门编号  
	 * @param companyId 公司编码
	 * @param cityCode 城市编码
	 * @return: String
	 * @see com.bashiju.api.DeptServiceApi#getDeptIdByCompanyAndCityCode(java.lang.String, java.lang.String)   
	 */
	@Override
	public String getDeptIdByCompanyAndCityCode(String companyId, String cityCode) {
		if(StringUtils.isEmpty(companyId) || StringUtils.isEmpty(cityCode))
			return null;
		return deptMapper.getDeptIdByCompanyAndCityCode(companyId,cityCode);
	}

}
