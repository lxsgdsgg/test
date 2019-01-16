/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  WageSchemaSettingMapper.java   
 * @Package com.bashiju.manage.mapper      
 * @author: zuoyuntao     
 * @date:   2018年6月25日 下午5:49:55   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 工资方案配置映射类
 * @ClassName:WageSchemaSettingMapper
 * @Description:工资方案配置映射类
 * @author:zuoyuntao
 * @date:2018年6月25日 下午5:49:55
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface ProposalAndWageSchemaMapper {
	/**
	 * 查询提成工资方案集合--参数为字符串条件
	 * @Title: queryAllWageSchemaSetingInfo
	 * @author: zuoyuntao  
	 * @Description:查询工资方案集合 
	 * @param condition 查询条件
	 * @return      
	 * List<Map<String,Object>> JSON 格式为：
	 */
	public List<Map<String,Object>> queryAllProposalAndWageSchemaInfoByCondition(
			@Param("condition") String condition);
	
	/**
	 * 查询提成工资方案集合--参数为Map对象
	 * @Title: queryAllWageSchemaSetingInfo
	 * @author: zuoyuntao  
	 * @Description:查询工资方案集合 
	 * @param paraMap 参数对象
	 * @return      
	 * List<Map<String,Object>> JSON 格式为：
	 */
	public List<Map<String,Object>> queryAllProposalAndWageSchemaInfoByMap(
			 Map<String,Object> paraMap);
	/**
	 * 根据条件统计数据总条数
	 * @Title: countWageSchemaInfoByCondition
	 * @author: zuoyuntao  
	 * @Description:根据条件统计数据总条数
	 * @param condition
	 * @return      
	 * long
	 */
	public long countProposalAndWageSchemaInfoByCondition(@Param("condition") String condition); 
}
