/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  EmployeeWagesConfigurate.java   
 * @Package com.bashiju.fin.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年9月10日 下午4:35:05   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.fin.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**   
 * @ClassName:  EmployeeCommissionsInfoService   
 * @Description:员工提成信息查询  
 * @author: liwen
 * @date:   2018年9月29日 下午14:35:05   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface EmployeeCommissionsInfoService {

	/**
	 * 查询员工提成信息   
	 * @Description: 查询员工提成信息   
	 * @param userName 员工名称
	 * @param months 月份
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryEmployeeCommissionsInfo(String companyId,Long userId,String deptId,String startMonth,String endMonth,int businessType);
	
	/**
	 * 查询员工提成信息(分页)  
	 * @Description: 查询员工提成信息(分页)   
	 * @param userName 员工名称
	 * @param months 月份
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryEmployeeCommissionsInfo(String companyId,Long userId,String deptId,String startMonth,String endMonth,int businessType,int pageNum,int pageSize);
	
	
	/**
	 * 生成公司员工月提成信息
	 * @Description: 生成公司员工月提成信息  
	 * @param companyId 公司编号
	 * @param month 月份(2018-01)
	 * @param userIds 员工编号，多个员工之间用英文逗号隔开
	 * @return: boolean
	 */
	public boolean saveGenerateCommissions(String companyId,String month,String userIds);
}
