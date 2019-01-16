/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  TestDataService.java   
 * @Package com.bashiju.manage.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年4月10日 下午6:35:19   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.service;

import java.util.List;
import java.util.Map;
/**   
 * @ClassName:  TestDataService   
 * @Description:测试数据服务接口   
 * @author: yangz
 * @date:   2018年4月10日 下午6:35:19   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface TestDataService {

	/**
	 * 
	 * @Description: 查询测试数据信息 
	 * @param userId 用户id
	 * @return
	 * @throws Exception      
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryDataList(String userId)throws Exception;
	
}
