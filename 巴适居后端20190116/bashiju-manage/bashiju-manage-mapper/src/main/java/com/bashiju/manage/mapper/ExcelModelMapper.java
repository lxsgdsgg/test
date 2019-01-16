/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ExcelModelMapper.java   
 * @Package com.bashiju.manage.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年11月30日 下午4:43:18   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName:  ExcelModelMapper   
 * @Description:excel模板导出
 * @author: yangz
 * @date:   2018年11月30日 下午4:43:18   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface ExcelModelMapper {

	/**
	 * 
	 * @Description: 查询区域的下拉信息   
	 * @param cityCode 城市编号
	 * @return: List<String>
	 */
	public List<String> queryAreaCodeDropDown(@Param("cityCode")String cityCode);
	
	/**
	 * @Description: 查询片区的下拉信息  
	 * @param cityCode 城市编号
	 * @return: List<String>
	 */
	public List<String> queryRegionDropDown(@Param("cityCode")String cityCode);
	
	/**
	 * @Description: 查询小区配套下拉信息  
	 * @return: List<String>
	 */
	public List<String> queryCommunityMatchingDropDown();

	/**     
	 * @Description: 查询小区下所有房源的基本信息  
	 * @param communityId 小区编号
	 * @return: List<Map<String, String>>        
	 */ 
	public List<Map<String, String>> queryBaseHouse(@Param("communityId")String communityId);

	/**     
	 * @Description: 查询动态下拉信息   
	 * @param widgetCode 部件编码
	 * @return: List<String>           
	 */ 
	public List<String> queryDynamicDropDown(@Param("widgetCode")String widgetCode);

	/**     
	 * @Description: 查询公司下的所有员工信息   
	 * @param companyId 公司编号
	 * @return: List<String>       
	 */ 
	public List<String> queryUser(@Param("companyId")String companyId);
}
