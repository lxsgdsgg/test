/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  DivideintoServiceApi.java   
 * @Package com.bashiju.api   
 * @Description:分成管理-分成计算公共服务
 * @author: zuoyuntao     
 * @date:   2018年6月23日 下午3:57:22   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.api;

import java.util.List;
import java.util.Map;

/**
 * 分成管理-分成计算公共服务
 * @ClassName:DivideintoServiceApi
 * @Description:分成管理-分成计算公共服务
 * @author:zuoyuntao
 * @date:2018年6月23日 下午3:57:22
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface DivideintoServiceApi {

	/**
	 * 通过传入条件计算分成
	 * @Title: caculateDivideIntoByConditions
	 * @author: zuoyuntao  
	 * @Description:通过传入条件计算分成
	 * @param totalDivideintoMoney 房源总的分成金额
	 * @param configType 配置类型（00出售01出租02一手成交）
	 * @param dealType 处理状态（1住宅 2 商业）
	 * @param companyId 公司ID
	 * @param rgCode 行政区划      
	 * @param agentTypeMap 经纪人信息集合（agentType，agentId）      
	 * void JSON 格式为：{}     
	 */
	public List<Map<String,Object>> caculateDivideIntoByConditions(
			String totalDivideintoMoney,String configType
			,String dealType,String rgCode,String companyId,Map<String,Object> agentTypeMap);
}
