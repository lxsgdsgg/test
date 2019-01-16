/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CompanyMapper.java   
 * @Package com.bashiju.manage.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年4月19日 上午10:17:03   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName:  CompanyMapper   
 * @Description:公司管理持久层   
 * @author: yangz
 * @date:   2018年4月19日 上午10:17:03   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface CompanyMapper {

	/**
	 * @Description: 条件查询公司信息    
	 * @param name 公司名称(模糊查询)
	 * @param doccode 法人证件号码
	 * @param legrepname 法人代表名称(模糊查询)
	 * @param legdocmobile 法人代表手机号
	 * @param status 公司状态
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryCompanys(@Param("name")String name,@Param("doccode")String doccode,
			@Param("legrepname")String legrepname,@Param("legdocmobile")String legdocmobile,@Param("status")String status);
	

	/**
	 * 
	 * @Description: 根据id查询公司信息   
	 * @param companyId 公司id
	 * @return: Map<String,Object>
	 */
	public Map<String,Object> getCompany(@Param("companyId")String companyId);


	/**     
	 * @Description: 根据公司id查询该公司下关联的数据   
	 * @param companyId 公司id
	 * @return: long   
	 */ 
	public long isRelation(@Param("companyId")String companyId);
	
	/**
	 * @Title: queryCityByAreacode   
	 * @Description: 通过区域代码查询出城市   
	 * @param regaddrcity 区域代码
	 * @return: Map<String,Object>
	 */
	public Map<String,Object>queryCityByAreacode(@Param("regaddrcity")String regaddrcity);
	
	/**
	 * @Title: queryCompanyNameAndId   
	 * @Description: 查询公司名称和公司编号  
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryCompanyNameAndId();
	
	/**
	 *  查询公司编号是否存在
	 * @Title: queryCompanyIsExist   
	 * @Description: 查询公司编号是否存在
	 * @param companyId 公司编号
	 * @return: boolean
	 */
	public boolean queryCompanyIsExist(@Param("companyId")String companyId);		
}
