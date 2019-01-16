/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  BusinessExamineMapper.java   
 * @Package com.bashiju.housing.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年6月5日 下午5:47:31   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.customer.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName:  BusinessExamineMapper   
 * @Description:客源业务审核持久层  
 * @author: liwen
 * @date:   2018年7月2日 下午5:47:31   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface BusinessExamineMapper {

	/**
	 * 
	 * @Description: 条件查询客源业务审核信息  
	 * @param params 客源参数
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryBusinessExamine(Map<String,Object> params);
	
	
	/**
	 * 
	 * @Description: 根据id获取客源业务审核信息  
	 * @param id 待审核的信息id
	 * @return: Map<String,Object>      
	 */ 
	public Map<String, Object> queryBusinessExamineById(@Param("id")Long id);
}
