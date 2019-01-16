/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  IUserRegisterManageService.java   
 * @Package com.bashiju.manage.service   
 * @Description:用户注册审核接口类   
 * @author: zuoyuntao     
 * @date:   2018年6月4日 下午5:01:49   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**
 * 用户注册审核接口类
 * @ClassName:IUserRegisterManageService
 * @Description:用户注册审核接口类
 * @author:zuoyuntao
 * @date:2018年6月4日 下午5:01:49
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface IUserRegisterManageService {
	/**
	 * 获取用户注册审核页面分页数据信息
	 * @Title: queryAllUserCheckInfo
	 * @author: zuoyuntao  
	 * @Description:获取用户注册审核页面分页数据信息   
	 * @param paraMap 参数对象
	 * @param page 页面显示最小条数
	 * @param limit 页面显示最大条数
	 * @return      
	 * [{deptName=呈贡一区一店, permissionArea=BSJ0101010101, checkInfoDel=checkInfoDel, addTime=2018-06-04 19:15:37.0, isValid=1, deptId=BSJ0101010101, computerPosistion=发射点发是, updateTime=2018-06-05 15:34:13.0, userCheckBtn=userCheckBtn, userName=呈贡一区一店店长, checkName=已审核, operator=[系统添加], checkStatus=1, checkTime=2018-06-05 15:34:13.0, userTypeName=分机, computerInfo=发射点发是, manageId=13321, manageName=呈贡一区一店店长, lastLoginSuccessTime=2018-06-05 15:34:12.0, id=1, userType=3, operatorId=13321}]     
	 * @throws
	 */
	public Page<Map<String,Object>> queryAllUserCheckInfo(
			Map<String,Object> paraMap,int page,int limit);
	/**
	 * 保存或修改用户注册信息
	 * @Title: saveOrUpdateUserRegisterInfo
	 * @author: zuoyuntao  
	 * @Description:保存或修改用户注册信息   
	 * @param paraMap      
	 * void  
	 * @throws
	 */
	public void saveOrUpdateUserRegisterInfo(Map<String,Object> paraMap);
	/**
	 * 根据用户ID查询用户注册信息 
	 * @Title: queryUserRegisterInfoByUserId
	 * @author: zuoyuntao  
	 * @Description:根据用户ID查询用户注册信息   
	 * @param userId
	 * @return      
	 * Map<String,Object> JSON 格式为：	 
	 * * {deptName=呈贡一区一店, permissionArea=BSJ0101010101, checkInfoDel=checkInfoDel, addTime=2018-06-04 19:15:37.0, isValid=1, deptId=BSJ0101010101, computerPosistion=发射点发是, updateTime=2018-06-05 15:34:13.0, userCheckBtn=userCheckBtn, userName=呈贡一区一店店长, checkName=已审核, operator=[系统添加], checkStatus=1, checkTime=2018-06-05 15:34:13.0, userTypeName=分机, computerInfo=发射点发是, manageId=13321, manageName=呈贡一区一店店长, lastLoginSuccessTime=2018-06-05 15:34:12.0, id=1, userType=3, operatorId=13321}     
	 * @throws
	 */
	public Map<String,Object> queryUserRegisterInfoByUserId(String userId);
	/**
	 * 根据用户ID删除用户注册信息 
	 * @Title: deleteUserRegisterInfoByUserId
	 * @author: zuoyuntao  
	 * @Description:根据用户ID删除用户注册信息   
	 * @param id 注册信息表的ID      
	 * void  
	 * @throws
	 */
	public void deleteUserRegisterInfoById(String id);
	/**
	 * 根据Map参数对象中的key、value组装查询条件
	 * @Title: queryUserRegisterInfoByConn
	 * @author: zuoyuntao  
	 * @Description:根据Map参数对象中的key、value组装查询条件
	 * @param paraMap
	 * @return      
	 * List<Map<String,Object>> JSON 格式为：
	 * [{deptName=呈贡一区一店, permissionArea=BSJ0101010101, checkInfoDel=checkInfoDel, addTime=2018-06-04 19:15:37.0, isValid=1, deptId=BSJ0101010101, computerPosistion=发射点发是, updateTime=2018-06-05 15:34:13.0, userCheckBtn=userCheckBtn, userName=呈贡一区一店店长, checkName=已审核, operator=[系统添加], checkStatus=1, checkTime=2018-06-05 15:34:13.0, userTypeName=分机, computerInfo=发射点发是, manageId=13321, manageName=呈贡一区一店店长, lastLoginSuccessTime=2018-06-05 15:34:12.0, id=1, userType=3, operatorId=13321}]     
	 * @throws
	 */
	public List<Map<String,Object>> queryUserRegisterInfoByConn(Map<String,Object> paraMap);
}
