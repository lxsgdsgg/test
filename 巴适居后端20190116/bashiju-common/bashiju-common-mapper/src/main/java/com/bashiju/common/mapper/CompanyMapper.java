/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CompanyMapper.java   
 * @Package com.bashiju.common.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年7月11日 下午3:58:29   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName:  CompanyMapper   
 * @Description:公司信息服务持久层   
 * @author: yangz
 * @date:   2018年7月11日 下午3:58:29   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface CompanyMapper {

	/**
	 * @Description: 根据城市行政编码查询该城市下的公司信息   
	 * @param cityCode 城市编码
	 * @return: List<Map<String,Object>>     
	 */ 
	List<Map<String, Object>> queryCompanyByCityCode(@Param("cityCode")String cityCode);

}
