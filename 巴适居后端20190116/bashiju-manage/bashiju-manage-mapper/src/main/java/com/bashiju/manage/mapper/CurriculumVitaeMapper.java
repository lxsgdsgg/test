/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CurriculumVitaeMapper.java   
 * @Package com.bashiju.manage.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年8月9日 下午12:05:18   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

/**   
 * @ClassName:  CurriculumVitaeMapper   
 * @Description:简历管理
 * @author: wangpeng
 * @date:   2018年8月9日 下午12:05:18   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface CurriculumVitaeMapper {
		
	/**
	 * @Title: queryCurriculumVitae   
	 * @Description: 查询简历信息
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> queryCurriculumVitae();
	
	
	/**
	 * @Title: queryCurriculumVitaeDetailById   
	 * @Description: 条件查询简历详情
	 * @param id 简历id
	 * @return: Map<String,Object>
	 */
	List<Map<String,Object>> queryCurriculumVitaeDetailById(@Param("id")String id); 
	
}
