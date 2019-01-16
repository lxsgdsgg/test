/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  IProposalSetMapper.java   
 * @Package com.bashiju.manage.mapper   
 * @Description:提成方案配置映射接口
 * @author: zuoyuntao     
 * @date:   2018年6月22日 上午11:23:00   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 提成方案配置映射接口
 * @ClassName:IProposalSetMapper
 * @Description:提成方案配置映射接口
 * @author:zuoyuntao
 * @date:2018年6月22日 上午11:23:00
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface IProposalSetMapper {
	/**
	 * 查询提成方案配置数据集合
	 * @Title: queryProposalSetAllDataList
	 * @author: zuoyuntao  
	 * @Description:查询提成方案配置数据集合
	 * @param paraMap
	 * @return      
	 * List<String,Object> JSON 格式为：{}     
	 * @throws
	 */
	public List<Map<String,Object>> queryProposalSetAllDataList(Map<String,Object> paraMap);
	/**
	 * 根据条件统计满足条件的数据数量
	 * @Title: queryProposalSetDataCountByCondition
	 * @author: zuoyuntao  
	 * @Description:根据条件统计满足条件的数据数量   
	 * @param condition
	 * @return      
	 * long   
	 * @throws
	 */
	public long queryProposalSetDataCountByCondition(@Param("condition") String condition); 
	/**
	 * 根据业绩类型、提成方案、城市等条件查询提成方案详细配置
	 * @Title: queryAllProposalDetailByCondition
	 * @author: zuoyuntao  
	 * @Description:根据业绩类型、提成方案、城市等条件查询提成方案详细配置   
	 * @param condition 组装条件
	 * @return      
	 * List<Map<String,Object>> JSON 格式为：{}     
	 * @throws
	 */
	public List<Map<String, Object>> queryAllProposalDetailByCondition(@Param("condition") String condition);
}
