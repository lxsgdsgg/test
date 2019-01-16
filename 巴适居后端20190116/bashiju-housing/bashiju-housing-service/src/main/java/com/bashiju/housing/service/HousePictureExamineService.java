/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  HousePictureExamineService.java   
 * @Package com.bashiju.housing.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年6月6日 下午6:26:30   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.housing.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**   
 * @ClassName:  HousePictureExamineService   
 * @Description:房源图片审核服务接口   
 * @author: yangz
 * @date:   2018年6月6日 下午6:26:30   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface HousePictureExamineService {

	/**
	 * 
	 * @Description: 条件查询申请信息   
	 * @param params 要查询的条件
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryHousePictureExamines(Map<String,Object> params);
	
	/**
	 * 
	 * @Description: 条件查询申请信息   
	 * @param params 要查询的条件
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryHousePictureExamines(Map<String,Object> params,int pageNum,int pageSize);
	
	/**
	 * 
	 * @Description: 批量审核房源图片信息   
	 * @param list 待审核的房源信息
	 * @return: boolean
	 */
	public boolean examineApplications(List<Map<String,Object>> list);
}
