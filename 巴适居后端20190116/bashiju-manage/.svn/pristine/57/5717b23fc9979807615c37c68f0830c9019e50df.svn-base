/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  PhoneSecurityManage.java   
 * @Package com.bashiju.manage.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年10月18日 上午9:47:17   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

/**  
 * 电话号码安全管理映射接口
 * @ClassName:  PhoneSecurityManage   
 * @Description:电话号码安全管理映射接口
 * @author: wangpeng
 * @date:   2018年10月18日 上午9:47:17   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface PhoneSecurityManageMapper {
	/**
	 * 查询电话号码数据 
	 * @Title: queryPhoneSecurityData   
	 * @Description:查询电话号码数据 
	 * @param realNum 真实电话
	 * @return: Page<Map<String,Object>>
	 */
	 public Page<Map<String,Object>> queryPhoneSecurityData(Map<String,Object> paramMap);
	 
	 /**
	  * 查询电话是否绑定虚拟号
	  * @Title: queryIsBingding   
	  * @Description: 查询电话是否绑定虚拟号
	  * @param realNum
	  * @return: long
	  */
	 public long queryIsBingding(@Param("realNum")String realNum);
	 
	 /**
	  * 通过用户编号查询经纪人信息
	  * @Title: queryAgentInfoByUserId   
	  * @Description: 通过用户编号查询经纪人信息
	  * @param userId 用户编号
	  * @return: Map<String,Object>
	  */
	 public Map<String,Object> queryAgentInfoByUserId(@Param("userId")String userId);
}
