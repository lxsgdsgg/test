/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  DealServiceApi.java   
 * @Package com.bashiju.api   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年6月15日 下午4:43:08   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.api;

import java.util.List;
import java.util.Map;

/**   
 * @ClassName:  DealServiceApi   
 * @Description:成交服务api   
 * @author: yangz
 * @date:   2018年6月15日 下午4:43:08   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface DealServiceApi {

	/**
	 * @Description: 保存成交信息   
	 * @param deal 要成交的信息
	 * @param commissions 佣金记录信息
	 * @return: String 成交编号
	 */
	public String saveDealInfo(Map<String,Object> deal,List<Map<String,Object>> commissions);
}
