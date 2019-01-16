/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CompanyCityOpenInfoService.java   
 * @Package com.bashiju.manage.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年7月23日 下午4:36:28   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**   
 * @ClassName:  CompanyCityOpenInfoService   
 * @Description:公司城市开通管理接口
 * @author: yangz
 * @date:   2018年7月23日 下午4:36:28   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface CompanyCityOpenService {

	/**     
	 * @Description: 查询公司城市开通信息   
	 * @param provinceCode 省级编码
	 * @param cityCode 城市编码
	 * @param companyId 公司编号
	 * @param companyName 公司名称（模糊查询）
	 * @param isOpen 是否开通
	 * @return: Page<Map<String,Object>>      
	 */ 
	List<Map<String, Object>> queryCompanyCityOpenInfos(String provinceCode, String cityCode, String companyId,
			String companyName, Boolean isOpen);
	
	/**     
	 * @Description: 查询公司城市开通信息  (分页) 
	 * @param provinceCode 省级编码
	 * @param cityCode 城市编码
	 * @param companyId 公司编号
	 * @param companyName 公司名称（模糊查询）
	 * @param isOpen 是否开通
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: Page<Map<String,Object>>      
	 */ 
	Page<Map<String, Object>> queryCompanyCityOpenInfos(String provinceCode, String cityCode, String companyId,
			String companyName, Boolean isOpen, int pageNum, int pageSize);

	/**     
	 * @Description: 保存公司城市开通信息   
	 * @param map 待保存的公司城市开通信息
	 * @return: boolean      
	 */ 
	boolean saveOrUpdateCityOpen(Map<String,Object> map);

	/**     
	 * @Description: 取消公司的开通城市权限   
	 * @param id 要取消的开通信息
	 * @return: boolean      
	 */ 
	boolean cancelCompanyCityOpen(String id);

}
