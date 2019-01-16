package com.bashiju.manage.service.impl;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.MenuEnum;
import com.bashiju.manage.mapper.StoreConfigurationMapper;
import com.bashiju.manage.service.StoreConfigurationService;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * 
 * @ClassName:  StoreConfigurationServiceImpl   
 * @Description:门店支出配置服务
 * @author: wangpeng
 * @date:   2018年6月27日 下午4:03:27   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
@SystemServiceLog(sourceType="门店支出配置")
public class StoreConfigurationServiceImpl extends CommonSqlServie implements StoreConfigurationService {
	
	@Autowired
	private StoreConfigurationMapper storeConfigurationMapper;
	
	@Autowired
	private DataAuthHelper dataAuthHelper;
	
	@Override
	@SystemServiceLog(operationType="查询费用类型")
	public Page<Map<String, Object>> queryFinanceCostType(int page, int limit,String cityCode) {
		
		PageHelper.startPage(page, limit);
		dataAuthHelper.auth(MenuEnum.MENU_131.getCode(), UserThreadLocal.get().get("id").toString());
		Page<Map<String,Object>> type = storeConfigurationMapper.queryFinanceCostType(cityCode);
		return type;
	}

	@Override
	@SystemServiceLog(operationType="条件查询费用项目")
	public Page<Map<String, Object>> queryFinanceCostProjByFinanceCostTypeId(String financeCostTypeId,int page, int limit) {
		PageHelper.startPage(page, limit);
		dataAuthHelper.auth(MenuEnum.MENU_131.getCode(), UserThreadLocal.get().get("id").toString());
		Page<Map<String,Object>> project = storeConfigurationMapper.queryFinanceCostProjByFinanceCostTypeId(financeCostTypeId);
		return project;
	}

	@Override
	@SystemServiceLog(operationType="新增费用类型")
	public boolean saveOrUpdateFinanceCostType(Map<String,Object> map) {
		if(!map.containsKey("id")||StringUtils.isEmpty(map.get("id").toString())||map.get("id")==null) {
			//新增
			map.remove("id");
			map.put("companyId", UserThreadLocal.get().get("companyId"));
			map.put("companyName", UserThreadLocal.get().get("comName"));
			map.put("permissionArea", UserThreadLocal.get().get("deptId"));
			map.put("operatorId", UserThreadLocal.get().get("id"));
			map.put("operator", UserThreadLocal.get().get("realName"));
			map.put("addTime", new Date());
			long result = this.commonOperationDatabase(map, "sys_financecosttype", false);
			if(result>0) {
				ExecutionResult.descFormat(Long.toString(result), "新增费用类型");
				return true;
			}
			return false;	
		}else{
			//修改
			long result = this.commonOperationDatabase(map, "sys_financecosttype", "id", false);
			if(result>0) {
				ExecutionResult.descFormat(Long.toString(result), "修改费用类型");
				return true;
			}
			return false;
		}
	}

	@Override
	@SystemServiceLog(operationType="新增费用项目")
	public boolean saveOrUpdateFinanceCostProj(Map<String,Object> map) {
		if(!map.containsKey("id")||StringUtils.isEmpty(map.get("id").toString())||map.get("id")==null) {
			//新增
			map.remove("id");	
			map.put("companyId", UserThreadLocal.get().get("companyId"));
			map.put("companyName", UserThreadLocal.get().get("comName"));
			map.put("permissionArea", UserThreadLocal.get().get("deptId"));
			map.put("operatorId", UserThreadLocal.get().get("id"));
			map.put("operator", UserThreadLocal.get().get("realName"));
			map.put("addTime", new Date());
			long result = this.commonOperationDatabase(map, "sys_financecostproj", false);
			if(result>0) {
				ExecutionResult.descFormat(Long.toString(result), "新增费用项目");
				return true;
			}else
				return false;
		}else {
			//修改
			long result = this.commonOperationDatabase(map, "sys_financecostproj", "id", false);
			if(result>0) {
				ExecutionResult.descFormat(Long.toString(result), "修改费用项目");
				return true;
			}else
				return false;
		}
			
		
	}
	@Override
	@SystemServiceLog(operationType="逻辑删除费用项目")
	public boolean delFinanceCostProjById(String financeCostProjId) {
		 boolean result=this.delData("sys_financecostproj", financeCostProjId, "id", false);
		if(result)
			return true;
		 return false;
	}

	@Override
	@SystemServiceLog(operationType="条件查询费用类型名称")
	public Map<String, Object> queryFinanceCostTypeNameById(String financeCostTypeId) {
		Map<String, Object> map = storeConfigurationMapper.queryFinanceCostTypeNameById(financeCostTypeId);
		return map;
	}
	
	@Override
	@SystemServiceLog(operationType="逻辑删除费用类型")
	public boolean deleteFinanceCostType(String id) {
		boolean result = this.delData("sys_financecosttype", id, "id", false);	
		if(result)
			return true;
		return false;
	}
	@Override
	@SystemServiceLog(operationType="判断费用类型是存在关联数据")
	public boolean  queryIsExistRelationData(String financeCostTypeId) {
		long  result = storeConfigurationMapper.queryIsExistRelationData(financeCostTypeId);
		if(result>0)
			return true;
		return false;	
	}
	
	
}
