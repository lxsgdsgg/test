/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CurriculumVitaeServiceImpl.java   
 * @Package com.bashiju.manage.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年8月9日 下午2:25:56   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.manage.mapper.CurriculumVitaeMapper;
import com.bashiju.manage.service.CurriculumVitaeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**   
 * @ClassName:  CurriculumVitaeServiceImpl   
 * @Description:简历管理服务
 * @author: wangpeng
 * @date:   2018年8月9日 下午2:25:56   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class CurriculumVitaeServiceImpl  implements CurriculumVitaeService{

	@Autowired
	private CurriculumVitaeMapper curriculumVitaeMapper;
	
	/**   
	 * <p>Title: queryCurriculumVitae</p>   
	 * <p>Description: 条件查询简历信息</p>   
	 * @param page 当前页数
	 * @param limit 每页总条数
	 * @see com.bashiju.manage.service.CurriculumVitaeService#queryCurriculumVitae(int, int)   
	 */
	@Override
	public Page<Map<String, Object>> queryCurriculumVitae(int page, int limit) {
		PageHelper.startPage(page, limit);
		Page<Map<String,Object>> map = curriculumVitaeMapper.queryCurriculumVitae();
		return map;
	}
	/**   
	 * <p>Title: queryCurriculumVitaeDetailById</p>   
	 * <p>Description: 条件查询简历详情 </p>   
	 * @param id 简历id
	 * @see com.bashiju.manage.service.CurriculumVitaeService#queryCurriculumVitaeDetailById(java.lang.String)   
	 */
	@Override
	public List<Map<String,Object>> queryCurriculumVitaeDetailById(String id) {
		List<Map<String,Object>> deailMap =	curriculumVitaeMapper.queryCurriculumVitaeDetailById(id);
		return deailMap;
	}

}
