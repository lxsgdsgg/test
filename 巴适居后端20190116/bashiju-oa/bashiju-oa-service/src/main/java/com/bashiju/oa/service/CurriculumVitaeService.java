/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CurriculumVitaeService.java   
 * @Package com.bashiju.oa.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年10月22日 下午2:09:04   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.oa.service;

import java.util.Map;

import com.github.pagehelper.Page;

/**
 * 简历管理服务接口    
 * @ClassName:  CurriculumVitaeService   
 * @Description:简历管理服务接口 
 * @author: wangpeng
 * @date:   2018年10月22日 下午2:09:04   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface CurriculumVitaeService {
	
	/**
	 * 条件查询简历信息   
	 * @Title: queryCurriculumVitaeData   
	 * @Description: 条件查询简历信息   
	 * @param name 名称
	 * @param page 当前页
	 * @param limit 每页总条数
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> queryCurriculumVitaeData(String name ,int page,int limit);
}
