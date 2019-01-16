/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  UserDesertsInfo.java   
 * @Package com.bashiju.fin.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年9月8日 下午5:26:29   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.fin.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**   
 * @ClassName:  UserDesertsInfo   
 * @Description:员工奖罚接口
 * @author: yangz
 * @date:   2018年9月8日 下午5:26:29   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface UserDesertsInfoService {

	/**
	 * 查询员工奖罚信息
	 * @Description: 查询员工奖罚信息   
	 * @param userName 员工名称(模糊查询)
	 * @param type 奖罚类型(1：奖，-1：罚 )
	 * @param classesId 奖罚类目编号
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryUserDesertsInfos(Long userId,String deptId,String type,String classesId,String months);
	
	/**
	 * 根据员工奖罚编号查询奖罚信息
	 * @Description: 根据员工奖罚编号查询奖罚信息  
	 * @param userDesertsId 员工奖罚编号
	 * @return: Map<String,Object>
	 */
	public Map<String,Object> getUserDesertsInfo(String userDesertsId);
	
	/**
	 * 查询员工奖罚信息(分页)
	 * @Description: 查询员工奖罚信息(分页)   
	 * @param userName 员工名称(模糊查询)
	 * @param type 奖罚类型(1：奖，-1：罚 )
	 * @param classesId 奖罚类目编号
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryUserDesertsInfos(Long userId,String deptId,String type,String classesId,String months,int pageNum,int pageSize);
	
	/**
	 * 保存员工奖罚信息
	 * @Description: 保存员工奖罚信息   
	 * @param map 待保存的员工奖罚信息
	 * @return: boolean
	 */
	public boolean saveOrUpdateUserDesertsInfo(Map<String,Object> map);
	
	/**
	 * 审核用户奖罚信息 
	 * @Description: 审核用户奖罚信息   
	 * @param map 待审核的奖罚信息
	 * @return: boolean
	 */
	public boolean saveExamineUserDeserts(Map<String,Object> map);
	
	/**
	 * 删除用户奖罚信息
	 * @Description: 删除用户奖罚信息  
	 * @param userDesertsId 用户奖罚信息编号
	 * @return: boolean
	 */
	public boolean delUserDesertsInfo(String userDesertsId);
}
