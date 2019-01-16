package com.bashiju.api;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * 
 * @ClassName:  FinService   
 * @Description:财务平台服务
 * @author: liwen
 * @date:   2018年10月30日 上午10:16:27   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface FinServiceApi {

	/**
	 * 
	 * 保存员工工资配置方案
	 * @Description: (保存员工工资配置方案)   
	 * @param: data里是{userId,userName,companyId,companyName,permissionArea,operatorId,operator}
	 * @param: @return      
	 * @return: Long      
	 * @throws
	 */
	public Long saveEmployeeWagesConf(Map<String,Object> data);
	
	/**
	 * 
	 * 删除员工工资配置方案 
	 * @Description: (删除员工工资配置方案 )   
	 * @param: userId(多个用逗号隔开)
	 * @param: @return      
	 * @return: List      
	 * @throws
	 */
	public boolean delEmployeeWagesConf(String userId);
	
}
