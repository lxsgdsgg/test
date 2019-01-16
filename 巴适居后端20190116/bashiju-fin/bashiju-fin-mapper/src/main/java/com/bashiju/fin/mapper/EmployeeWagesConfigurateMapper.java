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
 * @ClassName: EmployeeWagesConfigurateMapper   
 * @Description:员工薪资配置持久层   
 * @author: yangz
 * @date:   2018年9月11日 上午10:04:30   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface EmployeeWagesConfigurateMapper {

	/**
	 * 查询员工薪资配置信息   
	 * @Description: 查询员工薪资配置信息   
	 * @param userName 员工名称
	 * @param welfareId 福利方案编号
	 * @param ssfConfigId 社保方案编号
	 * @param agentComConfigId 中介类提成方案编号
	 * @param financeComConfigId 金融类提成方案编号
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryEmployeeWagesConfigurates(@Param("userId")Long userId,@Param("deptId")String deptId,@Param("welfareId")String welfareId,@Param("ssfConfigId")String ssfConfigId
			,@Param("agentComConfigId")String agentComConfigId,@Param("financeComConfigId")String financeComConfigId);
}
