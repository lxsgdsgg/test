/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CurriculumVitaeServiceImpl.java   
 * @Package com.bashiju.oa.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年10月22日 下午2:10:21   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.oa.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.MenuEnum;
import com.bashiju.oa.mapper.CurriculumVitaeMapper;
import com.bashiju.oa.service.CurriculumVitaeService;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/** 
 * 简历管理服务  
 * @ClassName:  CurriculumVitaeServiceImpl   
 * @Description:简历管理服务
 * @author: wangpeng
 * @date:   2018年10月22日 下午2:10:21   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@Service
public class CurriculumVitaeServiceImpl  implements CurriculumVitaeService{

	@Autowired
	private CurriculumVitaeMapper curriculumVitaeMapper;
	
	@Autowired
	private DataAuthHelper dataAuthHelper;
	
	/**  
	 * 查询简历信息 
	 * <p>Title: queryCurriculumVitaeData</p>   
	 * <p>Description: 查询简历信息</p> 
	 * @param name 名称  
	 * @param page 当前页数
	 * @param limit 每页总条数
	 * @see com.bashiju.oa.service.CurriculumVitaeService#queryCurriculumVitaeData(int, int)   
	 */
	
	@Override
	public Page<Map<String, Object>> queryCurriculumVitaeData(String name,int page, int limit) {
		PageHelper.startPage(page, limit);
		dataAuthHelper.auth(MenuEnum.MENU_132.getCode(),UserThreadLocal.get().get("id").toString());
		Page<Map<String, Object>> pages = curriculumVitaeMapper.queryCurriculumVitaeData(name);
		return pages;
	}

}
