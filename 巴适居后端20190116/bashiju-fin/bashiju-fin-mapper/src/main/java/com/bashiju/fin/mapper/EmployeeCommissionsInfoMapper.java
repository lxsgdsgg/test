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
 * @ClassName: EmployeeCommissionsInfoMapper   
 * @Description:员工提成信息持久层   
 * @author: liwen
 * @date:   2018年9月29日 下午14:44:30   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface EmployeeCommissionsInfoMapper {

	/**
	 * 查询员工提成信息   
	 * @Description: 查询员工提成信息   
	 * @param userName 员工名称
	 * @param months 月份
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryEmployeeCommissionsInfo(@Param("companyId")String companyId,@Param("userId")Long userId,@Param("deptId")String deptId,@Param("startMonth")String startMonth,@Param("endMonth")String endMonth,@Param("businessType")int businessType);

	/**     
	 * @Description: 生成公司员工月提成信息  
	 * @param companyId 公司编号
	 * @param month 月份(2018-01)
	 * @param userIds 员工编号(多个员工用英文逗号隔开)
	 * @return: int      
	 */ 
	public void saveGenerateCommissions(@Param("companyId")String companyId, @Param("month")String month, @Param("userIds")String userIds);

	
}
