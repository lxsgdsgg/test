package com.bashiju.manage.mapper;


import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  PhoneBlacklistMapper   
 * @Description:电话黑名单映射接口
 * @author: wangpeng
 * @date:   2018年6月29日 下午5:34:34   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface PhoneBlacklistMapper {
	/**
	 * 
	 * @Title: queryPhoneBlacklist   
	 * @Description:条件查询电话黑名单   
	 * @param keyWord 关键字
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> queryPhoneBlacklist(@Param("keyWord")String keyWord);
	
	/**
	 * 
	 * @Title: queryMobileIsExist   
	 * @Description:查询是否已存在电话  
	 * @param mobile 电话
	 * @return: long
	 */
	long queryMobileIsExist(@Param("mobile")String mobile);
}
