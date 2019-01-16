package com.bashiju.manage.service;


import java.util.Map;

import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  PhoneBlacklistService   
 * @Description:TODO(电话黑名单接口)   
 * @author: wangpeng
 * @date:   2018年6月29日 下午5:41:55   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface PhoneBlacklistService {
	/**
	 * @Title: queryPhoneBlacklist   
	 * @Description: 条件查询电话黑明单  
	 * @param keyWord 关键字
	 * @param page 当前页数
	 * @param limit 每页总条数
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> queryPhoneBlacklist(String keyWord,int page,int limit);
	/**
	 * @Title: queryMobileIsExist   
	 * @Description: 查询当前电弧数据库里是否已存在   
	 * @param mobile 当前点哈
	 * @return: boolean
	 */
	boolean queryMobileIsExist(String mobile);
	
	/**
	 * @Title: saveOrUpdatePhoneBlacklist   
	 * @Description: 新增电话黑名单
	 * @param ParamMap 参数
	 * @return: boolean
	 */
	boolean saveOrUpdatePhoneBlacklist(Map<String,Object> paramMap);
	
	/***
	 * 逻辑删除电话黑名单
	 * @Title: deletePhoneBlack   
	 * @Description: 逻辑删除电话黑名单
	 * @param id 电话黑名单编号
	 * @return: boolean
	 */
	boolean deletePhoneBlack(String id);
}
