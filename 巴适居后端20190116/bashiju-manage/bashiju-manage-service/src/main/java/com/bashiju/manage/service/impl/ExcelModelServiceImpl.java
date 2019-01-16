/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  excelModelServiceImpl.java   
 * @Package com.bashiju.manage.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年11月30日 下午4:58:02   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.manage.mapper.ExcelModelMapper;
import com.bashiju.manage.service.ExcelModelService;
import com.bashiju.utils.exception.BusinessException;

/**   
 * @ClassName:  excelModelServiceImpl   
 * @Description:excel模板导出
 * @author: yangz
 * @date:   2018年11月30日 下午4:58:02   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class ExcelModelServiceImpl implements ExcelModelService {
	
	@Autowired
	private ExcelModelMapper excelModelMapper;

	/**
	 * 
	 * @Description: 查询区域的下拉信息   
	 * @param cityCode 城市编号
	 * @return: List<String>
	 * @see com.bashiju.manage.service.ExcelModelService#queryAreaCodeDropDown(java.lang.String)   
	 */
	@Override
	public List<String> queryAreaCodeDropDown(String cityCode) {
		if(StringUtils.isEmpty(cityCode))
			throw new BusinessException("城市编号不允许为空");
		return excelModelMapper.queryAreaCodeDropDown(cityCode);
	}

	/**
	 * 
	 * @Description: 查询片区的下拉信息  
	 * @param cityCode 城市编号
	 * @return: List<String>
	 * @see com.bashiju.manage.service.ExcelModelService#queryRegionDropDown(java.lang.String)   
	 */
	@Override
	public List<String> queryRegionDropDown(String cityCode) {
		if(StringUtils.isEmpty(cityCode))
			throw new BusinessException("城市编号不允许为空");
		return excelModelMapper.queryRegionDropDown(cityCode);
	}

	/**     
	 * @Description: 查询小区配套的下拉信息   
	 * @return: List<String>      
	 * @see com.bashiju.manage.service.ExcelModelService#queryCommunityMatchingDropDown()   
	 */
	@Override
	public List<String> queryCommunityMatchingDropDown() {
		return excelModelMapper.queryCommunityMatchingDropDown();
	}

	/**     
	 * @Description: 查询小区下所有房源的基本信息  
	 * @param communityId 小区编号
	 * @return: List<Map<String, String>>   
	 * @see com.bashiju.manage.service.ExcelModelService#queryBaseHouse(java.lang.String)   
	 */
	@Override
	public List<Map<String, String>> queryBaseHouse(String communityId) {
		if(StringUtils.isEmpty(communityId))
			throw new BusinessException("小区编号不允许为空");
		return excelModelMapper.queryBaseHouse(communityId);
	}

	/**     
	 * @Description: 查询动态下拉信息   
	 * @param widgetCode 部件编码
	 * @return: List<String>      
	 * @see com.bashiju.manage.service.ExcelModelService#queryDynamicDropDown(java.lang.String)   
	 */
	@Override
	public List<String> queryDynamicDropDown(String widgetCode) {
		if(StringUtils.isEmpty(widgetCode))
			throw new BusinessException("部件编码不允许为空");
		return excelModelMapper.queryDynamicDropDown(widgetCode);
	}

	/**     
	 * @Description: 查询公司下的所有员工信息   
	 * @param companyId 公司编号
	 * @return: List<String> 
	 * @see com.bashiju.manage.service.ExcelModelService#queryUser(java.lang.String)   
	 */
	@Override
	public List<String> queryUser(String companyId) {
		if(StringUtils.isEmpty(companyId))
			throw new BusinessException("公司编号不允许为空");
		return excelModelMapper.queryUser(companyId);
	}

}
