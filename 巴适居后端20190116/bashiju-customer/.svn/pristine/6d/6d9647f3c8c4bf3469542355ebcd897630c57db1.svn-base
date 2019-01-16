/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  IDepositManageService.java   
 * @Package com.bashiju.customer.service      
 * @author: zuoyuntao     
 * @date:   2018年6月29日 下午3:14:53   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.customer.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**
 * （简单描述）
 * @ClassName:IDepositManageService
 * @Description:（详细描述）
 * @author:zuoyuntao
 * @date:2018年6月29日 下午3:14:53
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface IDepositManageService {
	/**
	 * 获取定金数据集合--带分页
	 * @Title: queryDepositDataListPage
	 * @author: zuoyuntao  
	 * @Description:获取定金数据集合--带分页  
	 * @param paraMap 参数对象
	 * @param page 最小条数
	 * @param limit 最大条数
	 * @return      
	 * Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryDepositDataListPage(Map<String,Object> paraMap
			,int page,int limit) ;
	/**
	 * 获取定金数据集合
	 * @Title: queryDepositDataList
	 * @author: zuoyuntao  
	 * @Description:获取定金数据集合   
	 * @param paraMap
	 * @return      
	 * List<Map<String,Object>> JSON 格式为：
	 */
	public List<Map<String,Object>> queryDepositDataList(Map<String,Object> paraMap) ;
	/**
	 * 保存支出数据
	 * @Title: savePayOffData
	 * @author: zuoyuntao  
	 * @Description:保存支出数据
	 * @param paraMap      
	 * void
	 */
	public void savePayOffData(Map<String,Object> paraMap);
	/**
	 * 更新定金数据信息
	 * @Title: updateDepositData
	 * @author: zuoyuntao  
	 * @Description:更新定金数据信息
	 * @param paraMap      
	 * void 
	 */
	public void updateDepositData(Map<String,Object> paraMap);
}
