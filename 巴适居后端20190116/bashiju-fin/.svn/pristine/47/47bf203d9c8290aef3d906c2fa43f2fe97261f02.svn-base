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
 * @ClassName:  EmployeeWagesInfoService   
 * @Description:员工薪资信息查询  
 * @author: liwen
 * @date:   2018年9月27日 上午10:35:05   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface EmployeeWagesInfoService {

	/**
	 * 查询员工薪资信息   
	 * @Description: 查询员工薪资信息   
	 * @param userName 员工名称
	 * @param months 月份
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryEmployeeWagesInfos(String companyId,Long userId,String deptId,String startMonth,String endMonth);
	
	/**
	 * 查询员工薪资信息(分页)  
	 * @Description: 查询员工薪资信息(分页)   
	 * @param userName 员工名称
	 * @param months 月份
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryEmployeeWagesInfos(String companyId,Long userId,String deptId,String startMonth,String endMonth,int pageNum,int pageSize);
	
	
	/**
	 * 保存员工薪资福利信息
	 * @Description: 保存员工薪资福利信息   
	 * @param map 待保存的员工薪资福利
	 * @return: boolean
	 */
	public boolean updateEmployeeWelfarePay(List<Map<String,Object>> map);
	/**
	 * 
	 * @Title: 修改员工基本工资
	 * @Description: TODO(修改员工基本工资)   
	 * @param: @param id
	 * @param: @param basePay
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean updateEmployeeBasePay(String id,String basePay);
	
	
	/**
	 * 审核员工薪资福利信息 
	 * @Description: 审核员工薪资福利信息 
	 * @param map 待审核的薪资信息
	 * @return: boolean
	 */
	public boolean saveExamineUserDeserts(Map<String,Object> map);
	
	/**
	 * 删除薪资配置方案
	 * @Description:删除薪资配置方案   
	 * @param wagesId 薪资配置方案编号
	 * @return: boolean
	 */
	public boolean delEmployeeWagesConfigurate(String wagesId);
	
	/**
	 * 
	 * 生成员工工资  
	 * @Description: 生成员工工资    
	 * @param months 月份(2018-01)
	 * @param companyId:公司编号
	 * @param userIds:用户id(多个用逗号隔开)
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean makeEmployeeWages(String months,String companyId,String userIds);
	
}
