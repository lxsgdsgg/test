/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ProposalAndWageSchemaServiceImpl.java   
 * @Package com.bashiju.manage.service.impl      
 * @author: zuoyuntao     
 * @date:   2018年6月25日 下午6:33:06   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.manage.global.ManageGlobal;
import com.bashiju.manage.mapper.ProposalAndWageSchemaMapper;
import com.bashiju.manage.service.IProposalAndWageSchemaService;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;

/**
 * 提成工资方案服务
 * @ClassName:ProposalAndWageSchemaServiceImpl
 * @Description:提成工资方案服务
 * @author:zuoyuntao
 * @date:2018年6月25日 下午6:33:06
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
@SystemServiceLog(sourceType="提成工资方案")
public class ProposalAndWageSchemaServiceImpl extends CommonSqlServie 
		implements IProposalAndWageSchemaService {
	/**
	 * 提成工资方案映射接口类
	 */
	@Autowired
	private ProposalAndWageSchemaMapper mProposalAndWageSchemaMapper;
	/**
	 * 权限接口
	 */
	@Autowired
	private DataAuthHelper mDataAuthHelper;
	/**   
	 * <p>Title: queryProposalAndWageSchemaInfoWithPage</p>   
	 * <p>Description: 查询提成工资方案-分页</p>   
	 * @param paraMap 参数对象
	 * @param page 最少条数
	 * @param limit 最大条数
	 * @return   
	 * @see com.bashiju.manage.service.IProposalAndWageSchemaService#queryProposalAndWageSchemaInfoWithPage(java.util.Map, int, int)   
	 */
	@Override
	@SystemServiceLog(operationType="查询数据")
	public Page<Map<String, Object>> queryProposalAndWageSchemaInfoWithPage(Map<String, Object> paraMap, int page,
			int limit) {
		PageHelper.startPage(page,limit);
	/*	mDataAuthHelper.auth(MenusEnum.MENU_257.getCode()
				, String.valueOf(UserThreadLocal.get().get("id")));*/
		List<Map<String,Object>> retList = queryAllProposalAndWageSchemaInfo(paraMap);
		ExecutionResult.descFormat("", "查询提成工资方案");
		return (Page<Map<String, Object>>) retList;
	}

	/**   
	 * <p>Title: queryAllProposalAndWageSchemaInfo</p>   
	 * <p>Description:查询提成工资方案数据 </p>   
	 * @param paraMap
	 * @return   
	 * @see com.bashiju.manage.service.IProposalAndWageSchemaService#queryAllProposalAndWageSchemaInfo(java.util.Map)   
	 */
	@Override
	@SystemServiceLog(operationType="查询数据")
	public List<Map<String, Object>> queryAllProposalAndWageSchemaInfo(Map<String, Object> paraMap) {
		List<Map<String,Object>> retList = mProposalAndWageSchemaMapper
				.queryAllProposalAndWageSchemaInfoByMap(paraMap);
		ExecutionResult.descFormat("", "查询提成工资方案");
		return retList;
	}

	/**   
	 * <p>Title: saveOrUpdateProposalAndWageSchemaInfo</p>   
	 * <p>Description:添加或更新提成工资方案数据 </p>   
	 * @param paraMap   
	 * @see com.bashiju.manage.service.IProposalAndWageSchemaService#saveOrUpdateProposalAndWageSchemaInfo(java.util.Map)   
	 */
	@Override
	@SystemServiceLog(operationType="添加/修改")
	public void saveOrUpdateProposalAndWageSchemaInfo(Map<String,Object> paraMap) {
		if(paraMap.containsKey(ManageGlobal.PRI_FIELD)) {
			commonOperationDatabase(paraMap
					, ManageGlobal.T_SYS_SALARY_EXTRACT_USER_CONFIG, ManageGlobal.PRI_FIELD, false);
			ExecutionResult.descFormat(String.valueOf(
				paraMap.get(ManageGlobal.PRI_FIELD)) ,"修改提成工资方案配置");
		}else {
			long id = commonOperationDatabase(paraMap
					, ManageGlobal.T_SYS_SALARY_EXTRACT_USER_CONFIG, false);
			ExecutionResult.descFormat(String.valueOf(id), "添加提成工资方案配置");
		}
	}

	/**   
	 * <p>Title: deleteProposalAndWageSchema</p>   
	 * <p>Description: 删除提成工资方案数据</p>   
	 * @param wageId   
	 * @see com.bashiju.manage.service.IProposalAndWageSchemaService#deleteProposalAndWageSchema(java.lang.String)   
	 */
	@Override
	@SystemServiceLog(operationType="删除提成工资方案")
	public void deleteProposalAndWageSchema(String wageId) {
		delData(ManageGlobal.T_SYS_SALARY_EXTRACT_USER_CONFIG
				, ManageGlobal.PRI_FIELD, wageId, false);
		ExecutionResult.descFormat(wageId, "删除提成工资方案配置");
	}

	/**   
	 * <p>Title: queryAllProposalAndWageSchemaInfoByCondition</p>   
	 * <p>Description:根据条件查询提成工资方案数据 </p>   
	 * @param paraMap
	 * @return   
	 * @see com.bashiju.manage.service.IProposalAndWageSchemaService#queryAllProposalAndWageSchemaInfoByCondition(java.util.Map)   
	 */

	@Override
	public List<Map<String, Object>> queryAllProposalAndWageSchemaInfoByCondition(
			Map<String, Object> paraMap) {
		String condition = buildConditionSql(paraMap, "sys_salary_config");
		List<Map<String, Object>> retList = mProposalAndWageSchemaMapper
				.queryAllProposalAndWageSchemaInfoByCondition(condition);
		return retList;
	}

	/**   
	 * <p>Title: jurgeProposalAndWageDataExists</p>   
	 * <p>Description:判断提成工资方案是否存在 </p>   
	 * @param paraMap
	 * @return   
	 * @see com.bashiju.manage.service.IProposalAndWageSchemaService#jurgeProposalAndWageDataExists(java.util.Map)   
	 */

	@Override
	public boolean jurgeProposalAndWageDataExists(Map<String, Object> paraMap) {
		String condition = buildConditionSql(paraMap,null);
		long count = mProposalAndWageSchemaMapper
				.countProposalAndWageSchemaInfoByCondition(condition);
		if(count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 组装查询条件
	 * @Title: buildConditionSql
	 * @author: zuoyuntao  
	 * @Description:组装查询条件
	 * @param paraMap 参数对象
	 * @param alias 别名
	 * @return      
	 * String JSON 格式为：
	 */
	@SuppressWarnings("unchecked")
	private String buildConditionSql (Map<String,Object> paraMap,String alias) {
		StringBuilder condition = new StringBuilder();
		String inSql = "";
		if(paraMap.containsKey("userIdMap")) {
			Map<String,String> userIdMap = (Map<String, String>) paraMap.get("userIdMap");
			paraMap.remove("userIdMap");
			inSql = buildInSql(userIdMap, "userId");
		}
		Iterator<String> it = paraMap.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			String value = String.valueOf(paraMap.get(key));
			if("null".equals(value)) {
				continue;
			}
			if(StringUtil.isEmpty(value)) {
				continue;
			}
			if(null != alias) {
				condition.append(" and ").append(alias).append(".")
					.append(key).append(" = '").append(value).append("'");
			}else {
				condition.append(" and ")
					.append(key).append(" = '").append(value).append("'");
			}
		}
		if(inSql.length() > 0) {
			condition.append(" and ").append(inSql);
		}
		return condition.toString();
	}
	
	/**
	 * 拼接In条件
	 * @Title: buildInSql
	 * @author: zuoyuntao  
	 * @Description:拼接In条件
	 * @param userIdMap
	 * @param fieldName 字段名称
	 * @return      
	 * String JSON 格式为：
	 */
	private String buildInSql(Map<String,String> userIdMap,String fieldName) {
		if(userIdMap.size() == 0) {
			return "";
		}
		Iterator<String> it = userIdMap.keySet().iterator();
		StringBuilder condition = new StringBuilder();
		while(it.hasNext()) {
			String key = it.next();
			String value = String.valueOf(userIdMap.get(key));
			if("null".equals(value)) {
				continue;
			}
			if(StringUtil.isEmpty(value)) {
				continue;
			}
			if(condition.length() > 0) {
				condition.append(",");
			}
			condition.append("'").append(value).append("'");
		}
		return fieldName + " in (" + condition.toString() + ")";  
	}
	
	/**
	 * <p>Title: batchSaveProposalAndWageSchemaInfo</p>   
	 * <p>Description: 保存批量配置工资和提成方案</p>   
	 * @param saveList 保存的数据集合
	 * @param paraMap   参数对象
	 * @see com.bashiju.manage.service.IProposalAndWageSchemaService#batchSaveProposalAndWageSchemaInfo(java.util.List)
	 */
	@Override
	@SystemServiceLog(operationType="批量保存")
	public void batchSaveProposalAndWageSchemaInfo(List<Map<String, Object>> saveList
			,Map<String,Object> paraMap) {
		//批量配置--先删除再插入
		String condition = buildConditionSql(paraMap,null);
		clearData(ManageGlobal.T_SYS_SALARY_EXTRACT_USER_CONFIG, " 1 = 1 " + condition);
		batchCommonOperationDatabase(saveList
				, ManageGlobal.T_SYS_SALARY_EXTRACT_USER_CONFIG, false);
		ExecutionResult.descFormat("", "批量保存提成和工资方案");
	} 
}
