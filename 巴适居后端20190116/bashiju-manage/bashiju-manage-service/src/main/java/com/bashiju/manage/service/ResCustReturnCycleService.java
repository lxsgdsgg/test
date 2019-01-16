package com.bashiju.manage.service;

import java.util.List;
import java.util.Map;
/**
 * 
 * @ClassName:  ResCustReturnCycleService   
 * @Description:客源回访周接口
 * @author: wangpeng
 * @date:   2018年5月31日 下午5:50:51   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface ResCustReturnCycleService {
	
	/**
	 * @Title: queryResCustReturnCycleData   
	 * @Description: 查询客源回访周期
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryResCustReturnCycleData();
	
	/**
	 * 
	 * @Title: saveOrUpdateResCustReturnCycle   
	 * @Description: 批量修改客源回访周期 
	 * @param map
	 * @return: boolean
	 */
	public boolean saveOrUpdateResCustReturnCycle(List<Map<String,Object>>map);

}
