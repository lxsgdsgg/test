/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CurriculumVitaeService.java   
 * @Package com.bashiju.manage.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年8月9日 下午2:09:16   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**   
 * @ClassName:  CurriculumVitaeService   
 * @Description:简历管理服务接口 
 * @author: wangpeng
 * @date:   2018年8月9日 下午2:09:16   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface CurriculumVitaeService {
		/**
		 * @Title: queryCurriculumVitae   
		 * @Description:条件查询简历信息
		 * @param page 当前页数
		 * @param limit 每页总条数
		 * @return: Page<Map<String,Object>>
		 */
		Page<Map<String,Object>> queryCurriculumVitae(int page,int limit);
		
		/**
		 * @Title: queryCurriculumVitaeDetailById   
		 * @Description: 条件查询简历详细信息  
		 * @param id 简历id
		 * @return: Map<String,Object>
		 */
		List<Map<String,Object>> queryCurriculumVitaeDetailById(String id);
}
