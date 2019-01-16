/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CompanyComConfigurateService.java   
 * @Package com.bashiju.fin.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年9月12日 下午5:37:44   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.fin.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**   
 * @ClassName:  CompanyComConfigurateService   
 * @Description:总部计提管理接口  
 * @author: yangz
 * @date:   2018年9月12日 下午5:37:44   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface CompanyComConfigService {

	/**
	 * 查询总部计提信息
	 * @Description: 查询总部计提信息   
	 * @param deptId 计提部门
	 * @param months 计提月份(yyyy-MM)
	 * @param isCalculate 是否计算提成 
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryCompanyComConfig(String deptId,String months,String isCalculate);
	
	
	/**
	 * 查询总部计提信息
	 * @Description: 查询总部计提信息 (分页) 
	 * @param deptId 计提部门
	 * @param months 计提月份(yyyy-MM)
	 * @param isCalculate 是否计算提成 
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryCompanyComConfig(String deptId,String months,String isCalculate,int pageNum,int pageSize);
	
	
	/**
	 * 保存总部计提信息
	 * @Description: 保存总部计提信息   
	 * @param map 待保存的计提信息
	 * @return: boolean
	 */
	public boolean saveOrUpdateCompanyComConfig(Map<String,Object> map);
	
	/**
	 * 删除总部计提信息   
	 * @Description: 删除总部计提信息   
	 * @param configId 待删除的总部计提编号
	 * @return: boolean
	 */
	public boolean delCompanyComConfig(String configId);
}
