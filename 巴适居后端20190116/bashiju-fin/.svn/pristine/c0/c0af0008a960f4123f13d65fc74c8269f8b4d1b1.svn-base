/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  BusinessTypeService.java   
 * @Package com.bashiju.fin.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年9月11日 下午2:37:29   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.fin.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**   
 * @ClassName:  BusinessTypeService   
 * @Description:业务类型接口   
 * @author: yangz
 * @date:   2018年9月11日 下午2:37:29   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface BusinessTypeService {

	/**
	 * 查询业务类型信息
	 * @Description: 查询业务类型信息   
	 * @param name 业务类型名称(模糊)
	 * @param companyId 公司编号
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryBusinessTypes(String name,String companyId);
	
	/**
	 * 查询业务类型信息
	 * @Description: 查询业务类型信息   
	 * @param name 业务类型名称
	 * @param companyId 公司编号
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryBusinessTypes(String name,String companyId, int pageNum,int pageSize);
	
	/**
	 * 更新业务类型信息   
	 * @Description: 更新业务类型信息   
	 * @param map 待更新的业务类型信息
	 * @return: boolean
	 */
	public boolean updateBusinessType(Map<String,Object> map);
}
