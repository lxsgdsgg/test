/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CompanyComConfigMapper1.java   
 * @Package com.bashiju.fin.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年9月12日 下午5:54:41   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.fin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName:  CompanyComConfigMapper1   
 * @Description:总部计提持久层   
 * @author: yangz
 * @date:   2018年9月12日 下午5:54:41   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface CompanyComConfigMapper {

	/**
	 * 查询总部计提信息
	 * @Description: 查询总部计提信息   
	 * @param deptId 计提部门
	 * @param months 计提月份(yyyy-MM)
	 * @param isCalculate 是否计算提成 
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryCompanyComConfig(@Param("deptId")String deptId,@Param("months")String months,@Param("isCalculate")String isCalculate);
	
	/**
	 * 根据编号查询总部计提信息
	 * @Description: 根据编号查询总部计提信息   
	 * @param configId 总部计提编号
	 * @return: Map<String,Object>
	 */
	public Map<String,Object> getCompanyConmConfig(@Param("configId")String configId);
	/**
	 * 
	 * @Title: 查询添加计提的部门   
	 * @Description: 查询添加计提的部门      
	 * @param: @param companyId：公司id
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String,Object>> getDept(@Param("companyId") String companyId);
} 
