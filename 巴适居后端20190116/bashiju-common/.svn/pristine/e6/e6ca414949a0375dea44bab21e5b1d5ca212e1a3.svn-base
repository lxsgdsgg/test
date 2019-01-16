/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CompanyServiceApiImpl.java   
 * @Package com.bashiju.common.service.manageservice   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年7月11日 下午3:53:08   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.common.service.manageservice;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.api.CompanyServiceApi;
import com.bashiju.common.mapper.CompanyMapper;

/**   
 * @ClassName:  CompanyServiceApiImpl   
 * @Description:公司信息对外服务   
 * @author: yangz
 * @date:   2018年7月11日 下午3:53:08   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class CompanyServiceApiImpl implements CompanyServiceApi {

	@Autowired
	private CompanyMapper companyMapper;
	
	
	/**
	 * @Description: 根据城市行政编码查询该城市下的公司信息   
	 * @param cityCode 城市编码
	 * @return: List<Map<String,Object>>
	 * @see com.bashiju.api.CompanyServiceApi#queryCompanyByCityCode(java.lang.String)   
	 */
	@Override
	public List<Map<String, Object>> queryCompanyByCityCode(String cityCode) {
		if(StringUtils.isEmpty(cityCode))
			return null;
		return this.companyMapper.queryCompanyByCityCode(cityCode);
	}

}
