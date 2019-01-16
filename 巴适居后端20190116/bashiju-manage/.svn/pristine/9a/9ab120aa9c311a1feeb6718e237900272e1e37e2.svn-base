/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  UserMapper.java   
 * @Package com.bashiju.manage.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年5月4日 上午9:21:58   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

/**   
 * @ClassName:  UserMapper   
 * @Description:用户持久层  
 * @author: yangz
 * @date:   2018年5月4日 上午9:21:58   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface UserMapper {
	
	/**
	 * 
	 * @Description: 根据id查询用户信息   
	 * @param id 用户id
	 * @return      
	 * @return: Map<String,Object>
	 */
	public Map<String,Object> getUserById(@Param("id")String id);
	
	/**
	 * 
	 * @Description: 根据手机号查询用户信息   
	 * @param mobile 手机号
	 * @return      
	 * @return: Map<String,Object>
	 */
	public Map<String,Object> getUserByMobile(@Param("mobile")String mobile);
	
	/**
	 * 
	 * @Description: 多条件 查询用户信息<br>
	 * map{<br>
	 * 	realName 用户名<br>
	 * 	mobile 手机号<br>
	 *  wxNum 微信号<br>
	 *  telPhone 固定电话<br>
	 *  companyId 所属公司<br>
	 *  deptId 所属部门<br>
	 *  roleId 所属角色<br>
	 *  sex 性别<br>
	 *  employee_num 员工号<br>
	 *  email 邮件号<br>
	 *  postId 所属职位<br>
	 * }<br>
	 * @param map 查询条件，用户属性
	 * @return      
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryUser(Map<String,Object> map);
	
	/**
	 * 
		 * 查询用户授权城市列表
		 * @Description: 查询用户授权城市列表 
		 * @param deptId
		 * @param userId
		 * @return 
		 * Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryUserOpenCity(@Param("deptId")String deptId,@Param("userId")Long userId);
	/**
	 * 
		 * 判断用户有没有添加城市
		 * @Description: 判断用户有没有添加城市
		 * @param userId
		 * @return 
		 * int
	 */
	public int queryUserOpenCityCount(@Param("userId")Long userId);
	/**
	 * 
		 * 查询公司开通城市
		 * @Description: 查询公司开通城市 
		 * @param companyId
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryCompanyOpenCity(@Param("companyId")String companyId);
}
