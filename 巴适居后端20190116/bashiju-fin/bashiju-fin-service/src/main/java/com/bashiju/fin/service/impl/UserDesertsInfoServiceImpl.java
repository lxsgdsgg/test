/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  UserDesertsInfoServiceImpl.java   
 * @Package com.bashiju.fin.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年9月10日 上午10:22:24   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.fin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.ExamineStatusEnum;
import com.bashiju.enums.MenuEnum;
import com.bashiju.fin.mapper.UserDesertsInfoMapper;
import com.bashiju.fin.service.UserDesertsInfoService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**   
 * @ClassName:  UserDesertsInfoServiceImpl   
 * @Description:员工奖罚服务   
 * @author: yangz
 * @date:   2018年9月10日 上午10:22:24   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
@SystemServiceLog(sourceType="员工奖罚")
public class UserDesertsInfoServiceImpl extends CommonSqlServie implements UserDesertsInfoService {
	
	@Autowired
	private UserDesertsInfoMapper userDesertsInfoMapper;
	
	@Autowired
	private DataAuthHelper dataAuthHelper;

	/**
	 * 查询员工奖罚信息
	 * @Description: 查询员工奖罚信息   
	 * @param userName 员工名称(模糊查询)
	 * @param type 奖罚类型(1：奖，-1：罚 )
	 * @param classesId 奖罚类目编号
	 * @return: List<Map<String,Object>>
	 * @see com.bashiju.fin.service.UserDesertsInfoService#queryUserDesertsInfos(java.lang.String, java.lang.String, java.lang.String)   
	 */
	@Override
	public List<Map<String, Object>> queryUserDesertsInfos(Long userId,String deptId, String type, String classesId,String months) {
		dataAuthHelper.auth(MenuEnum.MENU_123.getCode(), UserThreadLocal.get().get("id").toString());
		return userDesertsInfoMapper.queryUserDesertsInfos(userId,deptId,type, classesId,months);
	}
	
	/**
	 * 根据员工奖罚编号查询奖罚信息
	 * @Description: 根据员工奖罚编号查询奖罚信息  
	 * @param userDesertsId 员工奖罚编号
	 * @return: Map<String,Object>
	 * @see com.bashiju.fin.service.UserDesertsInfoService#getUserDesertsInfo(java.lang.String)   
	 */
	@Override
	public Map<String, Object> getUserDesertsInfo(String userDesertsId) {
		if(StringUtils.isNotEmpty(userDesertsId))
			return userDesertsInfoMapper.getUserDesertsInfo(userDesertsId);
		return null;
	}

	/**
	 * 查询员工奖罚信息(分页)
	 * @Description: 查询员工奖罚信息(分页)   
	 * @param userName 员工名称(模糊查询)
	 * @param type 奖罚类型(1：奖，-1：罚 )
	 * @param classesId 奖罚类目编号
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: Page<Map<String,Object>>
	 * @see com.bashiju.fin.service.UserDesertsInfoService#queryUserDesertsInfos(java.lang.String, java.lang.String, java.lang.String, int, int)   
	 */
	@Override
	public Page<Map<String, Object>> queryUserDesertsInfos(Long userId,String deptId, String type, String classesId,String months,int pageNum,
			int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<Map<String,Object>> page = (Page<Map<String, Object>>) this.queryUserDesertsInfos(userId,deptId, type, classesId,months);
		return page;
	}

	/**
	 * 保存员工奖罚信息
	 * @Description: 保存员工奖罚信息   
	 * @param map 待保存的员工奖罚信息
	 * @return: boolean
	 * @see com.bashiju.fin.service.UserDesertsInfoService#saveOrUpdateUserDesertsInfo(java.util.Map)   
	 */
	@Override
	@SystemServiceLog(operationType="保存员工奖罚")
	public boolean saveOrUpdateUserDesertsInfo(Map<String,Object> map) {
		if(map == null || map.size()<=0)
			throw new BusinessException("待保存的保存员工奖罚信息不允许为空");
		if(map.containsKey("id") && map.get("id")!=null) {//编辑
			long id = this.commonOperationDatabase(map, "finance_userDesertsInfo", "id", false);
			ExecutionResult.descFormat(Long.toString(id), "编辑员工奖罚");
		}else {
			long id = this.commonOperationDatabase(map, "finance_userDesertsInfo", false);
			ExecutionResult.descFormat(Long.toString(id), "新增员工奖罚");
		}
		return true;
	}

	/**
	 * 审核用户奖罚信息 
	 * @Description: 审核用户奖罚信息   
	 * @param map 待审核的奖罚信息
	 * @return: boolean
	 * @see com.bashiju.fin.service.UserDesertsInfoService#saveExamineUserDeserts(java.util.Map)   
	 */
	@SuppressWarnings("unchecked")
	@Override
	@SystemServiceLog(operationType="审核用户奖罚")
	public boolean saveExamineUserDeserts(Map<String,Object> map) {
		if(map == null || map.size()<=0)
			throw new BusinessException("待审核的员工奖罚信息不允许为空");
		if(!map.containsKey("examineStatus") || StringUtils.isEmpty((String)map.get("examineStatus")))
			throw new BusinessException("审核状态不允许为空");
		if("0".equals((String)map.get("examineStatus")) || !ExamineStatusEnum.enumMap.containsKey((String)map.get("examineStatus")))
			throw new BusinessException("请选择正确的审核状态");
		Map<String,Object> user = UserThreadLocal.get();
		if(map.containsKey("id") && map.get("id")!=null) {//编辑
			map.put("auditorId", user.get("id"));
			map.put("auditor", user.get("realName"));
			map.put("auditTime", new Date());
			long id = this.commonOperationDatabase(map, "finance_userDesertsInfo", "id", false);
			ExecutionResult.descFormat(Long.toString(id), "审核员工奖罚");
		}else 
			throw new BusinessException("员工奖罚编号不允许为空");
		return true;
	}

	/**
	 * 删除用户奖罚信息
	 * @Description: 删除用户奖罚信息  
	 * @param userDesertsId 用户奖罚信息编号
	 * @return: boolean
	 * @see com.bashiju.fin.service.UserDesertsInfoService#delUserDesertsInfo(java.lang.String)   
	 */
	@Override
	@SystemServiceLog(operationType="删除员工奖罚")
	public boolean delUserDesertsInfo(String userDesertsId) {
		if(!StringUtils.isEmpty(userDesertsId)) {
			Map<String,Object> map = this.getUserDesertsInfo(userDesertsId);
			if(map==null || !ExamineStatusEnum.UNEXAMINED.getCode().equals(map.get("examineStatus").toString()))
				throw new BusinessException("该信息不存在或已审核，不允许删除");
			boolean result = this.delData("finance_userDesertsInfo", "id", userDesertsId, false);
			if(result) {
				ExecutionResult.descFormat(userDesertsId, "删除员工奖罚");
				return true;
			}
		}
		return false;
	}

}
