package com.bashiju.anal.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.Page;

/**
 * 
 * @ClassName:  UserAchievementService   
 * @Description:(个人业绩排行接口)   
 * @author: liwen
 * @date:   2018年11月29日 上午09:12:07   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface UserAchievementService {
	
	public List<Map<String, Object>> queryUserAchievement(String months,String companyId,String userId);
	
}
