/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CompanyCityOpenMapper.java   
 * @Package com.bashiju.manage.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年7月23日 下午5:06:57   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName:  CompanyCityOpenMapper   
 * @Description:公司城市开通管理持久层   
 * @author: yangz
 * @date:   2018年7月23日 下午5:06:57   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface CompanyCityOpenMapper {

	/**     
	 * @Description: 查询公司城市开通信息   
	 * @param provinceCode 省级编码
	 * @param cityCode 城市编码
	 * @param companyId 公司编号
	 * @param companyName 公司名称（模糊查询）
	 * @param isOpen 是否开通
	 * @return: Page<Map<String,Object>>      
	 */ 
	List<Map<String, Object>> queryCompanyCityOpenInfos(@Param("provinceCode")String provinceCode,@Param("cityCode")String cityCode,  
			@Param("companyId")String companyId,@Param("companyName")String companyName, @Param("isOpen")Boolean isOpen);
}
