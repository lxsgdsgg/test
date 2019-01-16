package com.bashiju.manage.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.Page;

/**
 * 
 * @ClassName:  ImgmanageService   
 * @Description:TODO(图片管理接口)   
 * @author: liwen
 * @date:   2018年10月17日 上午11:22:07   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface ImgmanageService {
	
	public Page<Map<String, Object>> queryComImg(Map<String,Object> conditions,int page,int limit);
	public Long saveComImg(Map<String,Object> data);
	public boolean delComImg(int id);
	
}
