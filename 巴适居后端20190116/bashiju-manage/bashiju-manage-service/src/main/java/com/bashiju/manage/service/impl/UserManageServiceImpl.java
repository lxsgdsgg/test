/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  UserManageServiceImpl.java   
 * @Package com.bashiju.manage.service.impl   
 * @Description:    TODO(用户管理接口实现类--业务处理层)   
 * @author: zuoyuntao     
 * @date:   2018年5月24日 上午10:55:53   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

package com.bashiju.manage.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.api.FinServiceApi;
import com.bashiju.api.PhoneSecurityManageServiceApi;
import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.MenuEnum;
import com.bashiju.manage.global.ManageGlobal;
import com.bashiju.manage.mapper.UserManageMapper;
import com.bashiju.manage.service.IUserManageService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.UUIDUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;

/**   
 * @ClassName:  UserManageServiceImpl   
 * @Description:TODO(用户管理接口实现类--业务处理层)   
 * @author: zuoyuntao
 * @date:   2018年5月24日 上午10:55:53   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@Service
@SystemServiceLog(sourceType="用户管理")
public class UserManageServiceImpl extends CommonSqlServie implements IUserManageService {
	/**
	 * 权限管理接口
	 */
	@Autowired
	private DataAuthHelper dataAuthHelper;
	/**
	 * 用户管理映射接口
	 */
	@Autowired
	private UserManageMapper mUserManageMapper;
	/**
	 * 经纪人隐私号码接口类
	 */
	@Autowired
	private PhoneSecurityManageServiceApi mPhoneSecurityManageServiceApi;
	/**
	 * 财务管理服务接口
	 */
	@Autowired
	private FinServiceApi mFinServiceApi;
	/**
	 * 查询所用用户数据信息--带分页
	 * @Title: queryAllUserInfoWithPage   
	 * @Description: 查询所用用户数据信息--带分页   
	 * @param: paraMap
	 * @param: minCount 每页显示最小条数
	 * @param: maxCount 每页显示最大条数
	 * @return: Page<Map<String,Object>>     
	 * Page{count=true, pageNum=0, pageSize=10, startRow=0, endRow=0, total=11, pages=2, reasonable=false, pageSizeZero=false}[{deptName=呈贡一区二店一组, permissionArea=BSJ0103, isHotName=是, addTime=2018-04-09 16:06:01.0, isPCLoginName=否, lockedUser=lockedUser, operator=1, moveUserInfo=moveUserInfo, solt=123, password=1, areaName=昆明市, sexName=男, statusName=正常, resetPsw=resetPsw, lastLoginSuccessTime=2018-05-29 15:48:52.0, id=5, dataUpdate=dataUpdate, birthWindowFlag=1, operatorId=1, wxNum=18888888884, userEdit=userEdit, syncFlag=1, roleId=7, sex=2, syncFlagName=是, hotAgentFlag=1, isValid=1, mobile=18888888884, deptId=BSJ010101010201, updateTime=2018-05-29 15:49:35.0, sortNo=1, realName=呈贡一区一店一组组员1, companyId=BSJ01, areaCode=530100, permissionSet=permissionSet, employee_num=006, userTypeName=是, roleName=组员, userType=1, parentMobile=13577000337, status=1}] 
	 */
	@Override
	public Page<Map<String,Object>> queryAllUserInfoWithPage(Map<String
			, Object> paraMap, int minCount, int maxCount) {
		PageHelper.startPage(minCount, maxCount);
		dataAuthHelper.auth(MenuEnum.MENU_63.getCode(),UserThreadLocal.get().get("id").toString());
		return mUserManageMapper.queryAllUserInfo(paraMap);
	}
	
	/**
	 * 查询用户数据更新日志--带分页
	 * @Title: queryUserDataUpdateLogWithPage
	 * @author: zuoyuntao  
	 * @Description: 查询用户数据更新日志--带分页  
	 * @param: paraMap
	 * @param: minCount 每页显示最小条数
	 * @param: maxCount 每页显示最大条数
	 * @return: Page<Map<String,Object>>      
	 */
	public Page<Map<String,Object>> queryUserDataUpdateLogWithPage(Map<String,Object> paraMap
			,int minCount,int maxCount){
		PageHelper.startPage(minCount, maxCount);
		dataAuthHelper.auth(MenuEnum.MENU_63.getCode(),UserThreadLocal.get().get("id").toString());
		return mUserManageMapper.queryUserDataUpdateLogWithPage(paraMap);
	}

	/**
	 * 根据参数对象查询用户信息--参数Map对象中的key必须和表（视图）中的字段名称一致
	 * @Title: queryUserInfoByConn   
	 * @Description: 根据参数对象查询用户信息--参数Map对象中的key必须和表（视图）中的字段名称一致  
	 * @param: paraMap
	 * @return: List<Map<String, Object>>   
	 * Page{count=true, pageNum=0, pageSize=10, startRow=0, endRow=0, total=11, pages=2, reasonable=false, pageSizeZero=false}[{deptName=呈贡一区二店一组, permissionArea=BSJ0103, isHotName=是, addTime=2018-04-09 16:06:01.0, isPCLoginName=否, lockedUser=lockedUser, operator=1, moveUserInfo=moveUserInfo, solt=123, password=1, areaName=昆明市, sexName=男, statusName=正常, resetPsw=resetPsw, lastLoginSuccessTime=2018-05-29 15:48:52.0, id=5, dataUpdate=dataUpdate, birthWindowFlag=1, operatorId=1, wxNum=18888888884, userEdit=userEdit, syncFlag=1, roleId=7, sex=2, syncFlagName=是, hotAgentFlag=1, isValid=1, mobile=18888888884, deptId=BSJ010101010201, updateTime=2018-05-29 15:49:35.0, sortNo=1, realName=呈贡一区一店一组组员1, companyId=BSJ01, areaCode=530100, permissionSet=permissionSet, employee_num=006, userTypeName=是, roleName=组员, userType=1, parentMobile=13577000337, status=1}] 
	 */
	@Override
	public List<Map<String, Object>> queryUserInfoByConn(Map<String, Object> paraMap) {
		Iterator<String> it = paraMap.keySet().iterator();
		StringBuilder condition = new StringBuilder();
		while(it.hasNext()) {
			String key = it.next();
			if(StringUtil.isNotEmpty(condition.toString())) {
				condition.append(" and ");
			}
			condition.append(key)
					 .append(" ='")
					 .append(String.valueOf(paraMap.get(key)))
					 .append("'");
		}
		List<Map<String,Object>> listMap = mUserManageMapper.queryUserInfoByConn(condition.toString());
		return listMap;
	}

	/**
	 * 根据电话号码判断用户是否存在 
	 * @Title: jurgeUserExistsByMobile
	 * @author: zuoyuntao  
	 * @Description:根据电话号码判断用户是否存在
	 * @param mobile
	 * @return      
	 * boolean true/false   
	 */
	public boolean jurgeUserExistsByMobile(String mobile) {
		long count = mUserManageMapper.counterUserByConn(" mobile ='" + mobile + "'");
		if(count > 0) {
			return true;
		}
		return false;
	}
	/**
	 * 添加或修改用户数据信息
	 * @Title: saveOrUpdateUserInfo   
	 * @Description: 添加或修改用户数据信息  
	 * @param: saveInfo：要保存的数据对象
	 * {"entryDate":"2018-05-31","syncFlag":"0","roleId":"3","idCode":"522426198607080816","sex":"1","hotAgentFlag":"0","ip":"127.0.0.1","mobile":"18999665555","deptId":"BSJ010101010101","postId":"1","birthDate":"2018-05-01","sortNo":"65","realName":"测试","areaCode":"530103","employee_num":"测试","id":"8","birthWindowFlag":"0","email":"fdasf@163.com"}
	 * @return: String      
	 */
	@Override
	@SystemServiceLog(operationType="添加/编辑用户")
	public void saveOrUpdateUserInfo(Map<String,Object> saveInfo) {
		String companyName = String.valueOf(saveInfo.get("companyName"));
		String deptName = String.valueOf(saveInfo.get("deptName"));
		saveInfo.remove("companyName");
		saveInfo.remove("deptName");
		String isAgent = String.valueOf(saveInfo.get("isAgent"));
		if(null != saveInfo.get("entryDate") && StringUtil.isNotEmpty(String.valueOf(saveInfo.get("entryDate")))) {
			saveInfo.put("entryDate", new SimpleDateFormat("yyyy-MM-dd").format(saveInfo.get("entryDate")));
		}
		if(null != saveInfo.get("birthDate") && StringUtil.isNotEmpty(String.valueOf(saveInfo.get("birthDate")))) {
			saveInfo.put("birthDate", new SimpleDateFormat("yyyy-MM-dd").format(saveInfo.get("birthDate")));
		}
		// 先解绑（保证事务统一）
		mPhoneSecurityManageServiceApi.unBindViertualPhone(String.valueOf(saveInfo.get("mobile")));
		if(saveInfo.containsKey("id")) {
			String userId = String.valueOf(saveInfo.get("id"));
			Map<String,Object> agentDetailMap = buildAgentDetails(saveInfo,false,isAgent);
			commonOperationDatabase(saveInfo,ManageGlobal.T_USER,ManageGlobal.PRI_FIELD,false);
			ExecutionResult.descFormat(String.valueOf(saveInfo.get("id")), "更新用户");
			if(isAgent.equals("1")) {
				agentDetailMap.put("userId", String.valueOf(userId));
				if(agentDetailMap.containsKey("id")) {
					commonOperationDatabase(agentDetailMap,"sys_agent_details","id",false);
					ExecutionResult.descFormat(String.valueOf(agentDetailMap.get("id")), "更新经纪人详情");
					bindViertualPhone(saveInfo, companyName, deptName,String.valueOf(agentDetailMap.get("id")));
				}else {
					long agentId = commonOperationDatabase(agentDetailMap,"sys_agent_details", false);
					ExecutionResult.descFormat(String.valueOf(userId), "添加经纪人详情");
					bindViertualPhone(saveInfo, companyName,deptName,String.valueOf(agentId));
				}
			}else if(agentDetailMap.containsKey("id")) {
				// 非经纪人删除已有经纪人信息
				delData("sys_agent_details", "id"
						, String.valueOf(agentDetailMap.get("id")), false);
				ExecutionResult.descFormat(String.valueOf(agentDetailMap.get("id")), "删除经纪人详情");
			}
		}else {
			Map<String,Object> agentDetailMap = buildAgentDetails(saveInfo,true,isAgent);
			saveInfo.put("uuid", UUIDUtil.generateUUID());
			long userId = commonOperationDatabase(saveInfo,ManageGlobal.T_USER, false);
			ExecutionResult.descFormat(String.valueOf(userId), "添加用户");
			
			if(isAgent.equals("1")) {
				agentDetailMap.put("userId", String.valueOf(userId));
				long agentId = commonOperationDatabase(agentDetailMap,"sys_agent_details", false);
				ExecutionResult.descFormat(String.valueOf(userId), "添加经纪人详情");
				bindViertualPhone(saveInfo, companyName,deptName,String.valueOf(agentId));
			}
			saveEmployeeWagesConf(saveInfo,companyName,userId);
			savePersonalInfo(saveInfo,userId);
		}
	}
	
	/**
	 * 添加用户信息时保存人事档案 
	 * @Title: savePersonalInfo
	 * @author: zuoyuntao  
	 * @Description:添加用户信息时保存人事档案
	 * @param saveInfo
	 * @param userId      
	 * void
	 */
	private void savePersonalInfo(Map<String,Object> saveInfo,long userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", String.valueOf(userId));
		map.put("operatorId", String.valueOf(saveInfo.get("operatorId")));
		map.put("operator", String.valueOf(saveInfo.get("operator")));
		map.put("permissionArea", String.valueOf(saveInfo.get("permissionArea")));
		this.idCustomOperationDatabase(map, "sys_personalrecords", false);
	}
	
	/**
	 * 保存一下工资福利信息
	 * @Title: saveEmployeeWagesConf
	 * @author: zuoyuntao  
	 * @Description:保存一下工资福利信息
	 * @param saveInfo
	 * @param companyName
	 * @param userId      
	 * void 
	 */
	private void saveEmployeeWagesConf(Map<String,Object> saveInfo,String companyName,long userId) {
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("userId", userId);
		data.put("userName", saveInfo.get("realName"));
		data.put("companyId", saveInfo.get("companyId"));
		data.put("companyName", companyName);
		data.put("permissionArea", saveInfo.get("permissionArea"));
		data.put("operator", UserThreadLocal.get().get("realName"));
		data.put("operatorId", saveInfo.get("operatorId"));
		mFinServiceApi.saveEmployeeWagesConf(data);
	}
	
	/**
	 * 绑定隐私号码
	 * @Title: bindViertualPhone
	 * @author: zuoyuntao  
	 * @Description:绑定隐私号码
	 * @param saveInfo
	 * @param companyName 公司名称
	 * @param deptName 部门名称
	 * @param agentId  经纪人ID
	 * void
	 */
	private void bindViertualPhone(Map<String,Object> saveInfo,String companyName
			,String deptName,String agentId) {
		boolean result = mPhoneSecurityManageServiceApi.bindViertualPhone(
				String.valueOf(saveInfo.get("mobile"))
				, String.valueOf(saveInfo.get("companyId")),companyName
				, String.valueOf(saveInfo.get("deptId")), deptName
				, String.valueOf(agentId), String.valueOf(saveInfo.get("realName")));
		ExecutionResult.descFormat(String.valueOf(agentId), "添加经纪人隐私号码");
		if(!result) {
			throw new BusinessException("添加隐私号码失败！");
		}
	}
	/**
	 * 组装经纪人详情数据
	 * @Title: buildAgentDetails
	 * @author: zuoyuntao  
	 * @Description:组装经纪人详情数据   
	 * @param paraMap 参数对象
	 * @param userId 用户Id
	 * @param isInsert 是否新增
	 * @return      
	 * Map<String,Object> 
	 */
	private Map<String,Object> buildAgentDetails(Map<String,Object> paraMap,boolean isInsert
			,String isAgent){
		Map<String,Object> agentDetailMap = new HashMap<String,Object>();
		agentDetailMap.put("syncFlag", paraMap.get("syncFlag"));
		//添加主要区域
		agentDetailMap.put("mainAreaCodes", paraMap.get("mainAreaCodes"));
		agentDetailMap.put("mainArea", paraMap.get("mainArea"));
		agentDetailMap.put("mainRegionsIds", paraMap.get("mainRegionsIds"));
		agentDetailMap.put("mainRegions", paraMap.get("mainRegions"));
		agentDetailMap.put("mainCommunityIds", paraMap.get("mainCommunityIds"));
		agentDetailMap.put("mainCommunity", paraMap.get("mainCommunity"));
		agentDetailMap.put("hotAgentFlag", paraMap.get("hotAgentFlag"));
		agentDetailMap.put("lastComment","");
		agentDetailMap.put("evaluateMyself","");
		agentDetailMap.put("permissionArea", String.valueOf(paraMap.get("permissionArea")));
		agentDetailMap.put("operatorId",  String.valueOf(paraMap.get("operatorId")));
		if(paraMap.containsKey("agentDetailId") && null != paraMap.get("agentDetailId")) {
			agentDetailMap.put("id",paraMap.get("agentDetailId"));
		}
		paraMap.remove("syncFlag");
		paraMap.remove("hotAgentFlag");
		paraMap.remove("mainRegions");
		paraMap.remove("mainRegionsIds");
		paraMap.remove("mainCommunity");
		paraMap.remove("mainCommunityIds");
		paraMap.remove("agentDetailId");
		paraMap.remove("mainArea");
		paraMap.remove("mainAreaCodes");
		
		return agentDetailMap;
	}
	
	/**
	 * 保存用户数据更新日志数据信息
	 * @Title: saveOrUpdateUserInfo   
	 * @Description: 保存用户数据更新日志数据信息  
	 * @param: @param saveInfo：要保存的数据对象
	 * @return: void      
	 */
	@Override
	@SystemServiceLog(operationType="用户数据更新")
	public void saveUserActionLog(Map<String,Object> saveInfo) {
		if(saveInfo.containsKey(ManageGlobal.PRI_FIELD)) {
			commonOperationDatabase(saveInfo,
					ManageGlobal.T_USER,ManageGlobal.PRI_FIELD,false);
			ExecutionResult.descFormat((String) saveInfo.get("id"), "更新用户数据日志");
		}else {
			commonOperationDatabase(saveInfo,ManageGlobal.T_USER, false);
			ExecutionResult.descFormat((String) saveInfo.get("id"), "添加用户数据日志");
		}
	}

	/**
	 * 根据用户ID锁定用户信息
	 * @Title: lockedUserInfo   
	 * @Description: 根据用户ID锁定用户信息
	 * @param: paraMap:参数对象  ,{id=9, status=0}    
	 * @return: void      
	 */
	@Override
	@SystemServiceLog(operationType="锁定用户")
	public void lockedUserInfo(Map<String,Object> paraMap) {
		this.commonOperationDatabase(paraMap,ManageGlobal.T_USER,ManageGlobal.PRI_FIELD, false);
		ExecutionResult.descFormat(String.valueOf(paraMap.get(
				ManageGlobal.PRI_FIELD)), "锁定用户");

	}
	
	/**
	 * 解锁用户
	 * @Title: unLockedUserInfo
	 * @author: zuoyuntao  
	 * @Description: 解锁用户  
	 * @param: paraMap {id=9, status=01}     
	 * @return: void      
	 */
	@Override
	@SystemServiceLog(operationType="解锁用户")
	public void unLockedUserInfo(Map<String,Object> paraMap) {
		commonOperationDatabase(paraMap,ManageGlobal.T_USER,ManageGlobal.PRI_FIELD, false);
		ExecutionResult.descFormat(String.valueOf(
				paraMap.get(ManageGlobal.PRI_FIELD)), "解锁用户");
	}

	/**
	 * 重置用户密码
	 * @Title: resetUserPassword   
	 * @Description: 重置用户密码   
	 * @param: paraMap：参数对象 {password=123456, id=3}
	 * @return: void      
	 */
	@Override
	@SystemServiceLog(operationType="重置用户密码")
	public void resetUserPassword(Map<String,Object> paraMap) {
		commonOperationDatabase(paraMap,ManageGlobal.T_USER,ManageGlobal.PRI_FIELD, false);
		ExecutionResult.descFormat(String.valueOf(paraMap.get(
				ManageGlobal.PRI_FIELD)), "重置用户密码");

	}
	
	/**
	 * 查询部门信息创建部门树
	 * @Title: queryDeptInfoToCreateTree
	 * @author: zuoyuntao  
	 * @Description: 查询部门信息创建部门树   
	 * @param: roleGroup：当前用户所属角色组
	 * @param 当前用户id
	 * @return: List<Map<String,Object>>  
	 * 部门信息：[{"maxLevel":"5","name":"昆明市","code":"BSJ0101","parentCode":"","level":"1"}]
	 * 介绍人信息：[{"maxLevel":6.0,"name":"超级管理员","code":"1","parentCode":"BSJ0103","level":"6"}]
	 */
	@Override
	public List<Map<String,Object>> queryDeptInfoToCreateTree(String roleGroup,String userId){
		dataAuthHelper.auth(MenuEnum.MENU_63.getCode(),userId);
		return mUserManageMapper.queryDeptInfoToCreateTree(roleGroup);
		
	}
	
	/**
	 * 根据区划的编码做向后模糊查询
	 * @Title: queryAreaLikeCode
	 * @author: zuoyuntao  
	 * @Description: 根据区划的编码做向后模糊查询  
	 * @param: @param areaCode
	 * @param: @return      
	 * @return: List<Map<String,Object>>    
	 * areaList 行政区划[{"name":"北京市","code":"110000","parentCode":"0","level":"1"}]
	 */
	@Override
	public List<Map<String,Object>> queryAreaLikeCode(String areaCode){
		return mUserManageMapper.queryAreaLikeCode(areaCode);
	}

	/**
	 * 查询介绍人信息 
	 * @Title: queryReferenInfoToCreateTree
	 * @author: zuoyuntao  
	 * @Description:查询介绍人信息
	 * @param areaCode 行政区划
	 * @return      
	 * List<Map<String,Object>> JSON 格式为：
	 */
	@Override
	public List<Map<String,Object>> queryReferenInfoToCreateTree(String areaCode){
		return mUserManageMapper.queryReferenInfoToCreateTree(areaCode);
	}
	
	/**
	 * 查询经纪人信息（code是userId和agentId的拼接）
	 * @Title: queryReferenInfoToCreateTree
	 * @author: zuoyuntao  
	 * @Description:查询经纪人信息（code是userId和agentId的拼接）
	 * @param areaCode 行政区划
	 * @return      
	 * List<Map<String,Object>> JSON 格式为：
	 */
	@Override
	public List<Map<String,Object>> queryAgentInfoToCreateTree(String areaCode){
		return mUserManageMapper.queryAgentInfoToCreateTree(areaCode);
	}
	
	/**     
	 * @Description: 保存附件信息  
	 * @param files 待保存的附件列表
	 * @return: boolean  
	 * @see com.bashiju.deal.service.DealService#saveAttachments(java.util.List)   
	 */
	@Override
	@SystemServiceLog(operationType="保存附件")
	public void saveAttachments(Map<String,Object> files) {
		commonOperationDatabase(files, "sys_user","id", false);
		ExecutionResult.descFormat(String.valueOf(files.get("id")), "保存附件");
	}
}
