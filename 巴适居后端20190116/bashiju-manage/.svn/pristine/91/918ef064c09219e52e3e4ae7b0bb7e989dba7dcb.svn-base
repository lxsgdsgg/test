/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  PhoneSecurityManagService.java   
 * @Package com.bashiju.manage.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年10月18日 上午10:05:24   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.service;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

/**   
 * 电话号码安全管理接口  
 * @ClassName:  PhoneSecurityManagService   
 * @Description:电话号码安全管理接口   
 * @author: wangpeng
 * @date:   2018年10月18日 上午10:05:24   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface PhoneSecurityManagService {
	
	/**
	 * 查询电话安全数据
	 * @Title: queryPhoneSecurityData   
	 * @Description: 查询电话安全数据
	 * @param paramMap
	 * @return: Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryPhoneSecurityData(Map<String,Object> paramMap,int page,int limit);
	
	
	
	 /**
	  * 查询电话是否绑定虚拟号
	  * @Title: queryIsBingding   
	  * @Description: 查询电话是否绑定虚拟号
	  * @param realNum 真实电话号码
	  * @return: boolean
	  */
	 public boolean queryIsBingding(String realNum);
	
	/**
	 * 通过用户编号查询经纪人信息
	 * @Title: queryAgentInfoByUserId   
	 * @Description: 通过用户编号查询经纪人信息
	 * @param userId 用户编号
	 * @return: Map<String,Object>
	 */
	 public Map<String,Object> queryAgentInfoByUserId(String userId);
}
