/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  EmployeeWagesConfigurateMapper.java   
 * @Package com.bashiju.fin.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年9月11日 上午10:04:30   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.fin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName: EmployeeWagesInfoMapper   
 * @Description:员工薪资信息持久层   
 * @author: liwen
 * @date:   2018年9月27日 上午11:04:30   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface EmployeeWagesInfoMapper {

	/**
	 * 查询员工薪资信息   
	 * @Description: 查询员工薪资信息   
	 * @param userName 员工名称
	 * @param months 月份
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryEmployeeWagesInfos(@Param("companyId")String companyId,@Param("userId")Long userId,@Param("deptId")String deptId,@Param("startMonth")String startMonth,@Param("endMonth")String endMonth);

	/**     
	 * @Description: 生成员工工资主信息   
	 * @param months 要计算的月份(2018-01)
	 * @param companyId 公司编号
	 * @param userIds 用户编号(多个之间用英文逗号隔开)
	 * @param permissionArea 操作人权限域
	 * @param operatorId 操作人
	 * @return: int      
	 */ 
	public int makeEmployeeWages(@Param("months")String months, @Param("companyId")String companyId, @Param("userIds")String userIds
			,@Param("permissionArea")String permissionArea,@Param("operatorId")String operatorId);
}
