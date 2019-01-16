/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  BusinessTypeMapper.java   
 * @Package com.bashiju.fin.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年9月11日 下午3:04:17   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.fin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName:  BusinessTypeMapper   
 * @Description:业务类型持久层  
 * @author: yangz
 * @date:   2018年9月11日 下午3:04:17   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface BusinessTypeMapper {

	
	/**
	 * 查询业务类型信息
	 * @Description: 查询业务类型信息  
	 * @param name 业务类型名称(模糊)
	 * @param companyId 公司编号
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryBusinessTypes(@Param("name")String name,@Param("companyId")String companyId);
}
