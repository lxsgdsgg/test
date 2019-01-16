/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  RoleServiceImpl.java   
 * @Package com.bashiju.manage.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年4月18日 下午2:50:52   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.MenuEnum;
import com.bashiju.manage.mapper.RoleMapper;
import com.bashiju.manage.service.RoleService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**   
 * @ClassName:  RoleServiceImpl   
 * @Description:角色管理服务  
 * @author: yangz
 * @date:   2018年4月18日 下午2:50:52   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
@SystemServiceLog(sourceType="角色管理")
public class RoleServiceImpl extends CommonSqlServie implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private DataAuthHelper dataAuthHelper;
	/**
	 * 
	 * @Description: 根据公司id查询该公司下边的角色  
	 * @param companyId 公司id
	 * @param groups 角色组
	 * @param roleName 角色名称
	 * @return      
	 * @return: List<Map<String,Object>>
	 */
	@Override
	public List<Map<String, Object>> queryRoles(String companyId,String groups,String roleName) {
//		if(StringUtils.isEmpty(companyId))
//			return null;
		dataAuthHelper.auth(MenuEnum.MENU_65.getCode(),UserThreadLocal.get().get("id").toString());
		return roleMapper.queryRoles(companyId,groups,roleName);
	}

	/**
	 * 
	 * @Description: 根据公司id查询公司下边的角色信息
	 * @param companyId 公司id
	 * @param groups 角色组
	 * @param roleName 角色名称
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return      
	 * @return: Page<Map<String,Object>>
	 */
	@Override
	public Page<Map<String, Object>> queryRoles(String companyId,String groups,String roleName,int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<Map<String,Object>> page= (Page<Map<String, Object>>) this.queryRoles(companyId,groups, roleName);
		return page;
	}

	/**
	 * 
	 * @Description: 根据角色id查询角色信息 
	 * @param roleId 角色id
	 * @return      
	 * @return: Map<String,Object>
	 */
	@Override
	public Map<String, Object> getRole(String roleId) {
		if(StringUtils.isEmpty(roleId))
			return null;
		return roleMapper.getRole(roleId);
	}

	/**
	 * 
	 * @Description: 根据角色id判断该角色是否存在关联数据  
	 * @param roleId 角色id
	 * @return      
	 * @return: boolean true表示有关联数据，false表示无关联数据
	 */
	@Override
	public boolean isRelation(String roleId) {
		if(roleMapper.isRelation(roleId)>0)
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @Description: 根据角色id删除角色信息   
	 * @param roleId 角色id
	 * @return      
	 * @return: boolean
	 */
	@Override
	@SystemServiceLog(operationType="删除角色")
	public boolean delRole(String roleId) {
		if(StringUtils.isEmpty(roleId))
			throw new BusinessException("请选择要删除的角色信息");
		if(this.isRelation(roleId))
			throw new BusinessException("存在关联数据，不允许删除");
		Map<String,Object> fields = new HashMap<String,Object>();
		fields.put("isvalid", "0");
		fields.put("id", roleId);
		long tt = this.commonOperationDatabase(fields, "sys_role", "id", false);
		if(tt>0) {
			ExecutionResult.descFormat(roleId, "删除角色");
			return true;
		}else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_DEL_ERROR);
	}

	/**
	 * 
	 * @Description: 保存角色信息   
	 * @param map 需要保存的角色信息，(注：map中包含的属性必须为sys_role表中的字段；
	 * 	当map中存在id字段时，认为是修改操作，否则为新增操作)
	 * @return      
	 * @return: long
	 */
	@Override
	@SystemServiceLog(operationType="保存角色")
	public long saveOrUpdateRole(Map<String,Object> map) {
		long ids=0;
		if(!map.containsKey("id")||StringUtils.isEmpty(map.get("id").toString())) {
			//新增
			map.remove("id");
			ids = this.commonOperationDatabase(map, "sys_role", false);
			if(ids>0)
				ExecutionResult.descFormat(Long.toString(ids), "新增角色");
			else
				throw new BusinessException(ErrorCodeEnum.SYSTEM_ADD_ERROR);
		}else {
			ids = this.commonOperationDatabase(map, "sys_role", "id", false);
			if(ids>0)
				ExecutionResult.descFormat(Long.toString(ids), "编辑角色");
			else
				throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
		}
		return ids;
	}

}
