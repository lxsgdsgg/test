/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  DealTransitScheduleMapper.java   
 * @Package com.bashiju.deal.mapper      
 * @author: zuoyuntao     
 * @date:   2018年10月22日 上午11:59:25   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.deal.mapper;

import java.util.List;
import java.util.Map;

/**
 * 过户进度查询持久层接口
 * @ClassName:DealTransitScheduleMapper
 * @Description:过户进度查询持久层接口
 * @author:zuoyuntao
 * @date:2018年10月22日 上午11:59:25
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface DealTransitScheduleMapper {

	/**
	 * <p>Title: queryDealScheduleDataPageList</p>   
	 * <p>Description: </p>
	 * @param paraMap 参数对象
	 * @param page 当前页
	 * @param limit 每页显示最大条数
	 * @return   
	 * @see com.bashiju.deal.service.IDealTransitScheduleService#queryDealScheduleDataPageList(java.util.Map, int, int)
	 */
	public List<Map<String,Object>> queryDealScheduleDataPageList( Map<String, Object> paraMap);
}
