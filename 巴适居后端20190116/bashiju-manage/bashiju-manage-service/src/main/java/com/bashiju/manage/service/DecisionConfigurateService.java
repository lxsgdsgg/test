/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  DecisionConfigurateService.java   
 * @Package com.bashiju.manage.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年7月6日 上午9:34:28   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.service;

import java.util.Map;

import com.github.pagehelper.Page;

/**   
 * @ClassName:  DecisionConfigurateService   
 * @Description:决策配置服务类   
 * @author: yangz
 * @date:   2018年7月6日 上午9:34:28   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface DecisionConfigurateService {

	/**     
	 * @Description: 条件查询决策配置信息   
	 * @param cityCode 城市编码
	 * @param type 决策类型
	 * @param name 决策名称
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: Page<Map<String,Object>>      
	 */ 
	Page<Map<String, Object>> queryDecisisionConfigurate(String cityCode,String type, String name, int pageNum, int pageSize);

	/**     
	 * @Description: 保存决策配置明细信息   
	 * @param map 待保存的决策配置明细信息
	 * @return: boolean      
	 */ 
	boolean saveDecisisionCOnfigurateDetail(Map<String,Object> map);

}
