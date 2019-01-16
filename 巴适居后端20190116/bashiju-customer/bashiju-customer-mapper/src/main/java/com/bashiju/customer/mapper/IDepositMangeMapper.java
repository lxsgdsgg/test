/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  IDepositMangeMapper.java   
 * @Package com.bashiju.customer.mapper      
 * @author: zuoyuntao     
 * @date:   2018年6月29日 下午3:15:16   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.customer.mapper;

import java.util.List;
import java.util.Map;

/**
 * 定金管理映射接口类
 * @ClassName:IDepositMangeMapper
 * @Description:定金管理映射接口类
 * @author:zuoyuntao
 * @date:2018年6月29日 下午3:15:16
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface IDepositMangeMapper {
	/**
	 * 查询定金数据 
	 * @Title: queryAllDepositDataList
	 * @author: zuoyuntao  
	 * @Description:查询定金数据 
	 * @param paraMap 参数集合
	 * @return      
	 * List<Map<String,Object>> JSON 格式为：
	 */
	public List<Map<String,Object>> queryAllDepositDataList(Map<String,Object> paraMap);
}
