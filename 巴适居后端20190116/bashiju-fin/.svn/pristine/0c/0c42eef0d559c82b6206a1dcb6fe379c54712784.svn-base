/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CommissionsConfigServiceImpl.java   
 * @Package com.bashiju.fin.service.impl   
 * @Description: 提成配置管理   
 * @author: yangz     
 * @date:   2018年9月12日 下午3:06:57   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.fin.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.MenuEnum;
import com.bashiju.fin.mapper.CommissionsConfigMapper;
import com.bashiju.fin.service.CommissionsConfigService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**   
 * @ClassName:  CommissionsConfigServiceImpl   
 * @Description:提成配置管理   
 * @author: yangz
 * @date:   2018年9月12日 下午3:06:57   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
@SystemServiceLog(sourceType="提成配置管理")
public class CommissionsConfigServiceImpl extends CommonSqlServie implements CommissionsConfigService {

	@Autowired
	private CommissionsConfigMapper commissionsConfigMapper;
	
	@Autowired
	private DataAuthHelper dataAuthHelper;
	
	/**
	 * 查询提成方案信息
	 * @Description: 查询提成方案信息  
	 * @param name 提成方案名称
	 * @return: List<Map<String,Object>>
	 * @see com.bashiju.fin.service.CommissionsConfigService#queryCommissionsConfig(java.lang.String)   
	 */
	@Override
	public List<Map<String, Object>> queryCommissionsConfig(String name) {
		dataAuthHelper.auth(MenuEnum.MENU_127.getCode(), UserThreadLocal.get().get("id").toString());
		return commissionsConfigMapper.queryCommissionsConfig(name);
	}

	/**
	 * 查询提成方案信息 
	 * @Description: 查询提成方案信息   
	 * @param name 提成方案名称
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: Page<Map<String,Object>>
	 * @see com.bashiju.fin.service.CommissionsConfigService#queryCommissionsConfig(java.lang.String, int, int)   
	 */
	@Override
	public Page<Map<String, Object>> queryCommissionsConfig(String name, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<Map<String,Object>> page = (Page<Map<String, Object>>) this.queryCommissionsConfig(name);
		return page;
	}

	/**
	 * 保存提成方案信息
	 * @Description: 保存提成方案信息   
	 * @param map 待保存的提成方案信息
	 * @return: boolean
	 * @see com.bashiju.fin.service.CommissionsConfigService#saveOrUpdateCommissionsConfig(java.util.Map)   
	 */
	@Override
	@SystemServiceLog(operationType="保存提成方案")
	public boolean saveOrUpdateCommissionsConfig(Map<String,Object> map) {
		if(map == null || map.size()<=0)
			throw new BusinessException("待保存的提成方案信息不允许为空");
		if(map.containsKey("id") && map.get("id")!=null) {//编辑
			long id = this.commonOperationDatabase(map, "finance_commissionsConfigurate", "id", false);
			ExecutionResult.descFormat(Long.toString(id), "编辑提成方案");
		}else {
			long id = this.commonOperationDatabase(map, "finance_commissionsConfigurate", false);
			ExecutionResult.descFormat(Long.toString(id), "新增提成方案");
		}
		return true;
	}

	/**
	 * 删除提成方案
	 * @Description: 删除提成方案   
	 * @param commConfigId 要删除的提成方案编号
	 * @return: boolean
	 * @see com.bashiju.fin.service.CommissionsConfigService#delCommissionsConfig(java.lang.String)   
	 */
	@Override
	@SystemServiceLog(operationType="删除提成方案")
	public boolean delCommissionsConfig(String commConfigId) {
		if(StringUtils.isEmpty(commConfigId))
			return false;
		if(this.isRelationCommissionConfig(commConfigId))
			throw new BusinessException("存在关联数据，不允许删除");
		boolean result = this.delData("finance_commissionsConfigurate", "id", commConfigId, false);
		if(result) {
			ExecutionResult.descFormat(commConfigId, "删除总部计提");
			return true;
		}else
			return false;
	}

	/**
	 * 是否存在与提成方案相关联数据
	 * @Description: 是否存在与提成方案相关联数据   
	 * @param commConfigId 待匹配的提成方案编号
	 * @return: boolean
	 * @see com.bashiju.fin.service.CommissionsConfigService#isRelationCommissionConfig(java.lang.String)   
	 */
	@Override
	public boolean isRelationCommissionConfig(String commConfigId) {
		int result = commissionsConfigMapper.isRelationCommissionConfig(commConfigId);
		if(result>0)
			return true;
		return false;
	}

	/**
	 *  根据提成配置方案编号与业务类型查询该方案下的实收提成比例信息
	 * @Description: 根据提成配置方案编号与业务类型查询该方案下的实收提成比例信息   
	 * @param commConfigId 提成配置方案编号
	 * @param BusiTypeId 业务类型编号
	 * @return: List<Map<String,Object>>
	 * @see com.bashiju.fin.service.CommissionsConfigService#queryCommConfigActualRate(java.lang.String, java.lang.String)   
	 */
	@Override
	public List<Map<String, Object>> queryCommConfigActualRate(String commConfigId, String BusiTypeId) {
		if(StringUtils.isEmpty(commConfigId) || StringUtils.isEmpty(BusiTypeId))
			throw new BusinessException("提成配置方案编号和业务类型编号都不允许为空");
		return commissionsConfigMapper.queryCommConfigActualRate(commConfigId, BusiTypeId);
	}

	/**
	 * 保存实收提成比例信息
	 * @Description: 保存实收提成比例信息
	 * @param map 待保存的实收提成比例
	 * @return: boolean
	 * @see com.bashiju.fin.service.CommissionsConfigService#saveOrUpdateCommConfigActualRate(java.util.Map)   
	 */
	@Override
	@SystemServiceLog(operationType="保存实收比例")
	public boolean saveOrUpdateCommConfigActualRate(List<Map<String,Object>> editDataList,List<Map<String,Object>> addDataList,String delId) {
		boolean res = false;
		if((editDataList == null || editDataList.size()<=0) && (addDataList == null || addDataList.size()<=0) && StringUtils.isEmpty(delId))
			throw new BusinessException("待保存的实收提成比例信息不允许为空");
		if(delId!=null && StringUtils.isNotEmpty(delId)) {
			this.delData("finance_commissionsConfigurate_actualRate", "id", delId, false);
		}
		if(editDataList.size()>0) {
			res = this.batchCommonOperationDatabase(editDataList, "finance_commissionsConfigurate_actualRate", "id", false);
		}
		if(addDataList.size()>0) {
			Map<String,Object> user = UserThreadLocal.get();
			for(Map<String,Object> m: addDataList) {
				m.put("permissionArea", user.get("deptId"));
				m.put("operatorId", user.get("id"));
			}
			res = this.batchCommonOperationDatabase(addDataList, "finance_commissionsConfigurate_actualRate", false);
		}
		ExecutionResult.descFormat("", "编辑实收比例");
		return res;
	}

	/**
	 * 根据提成配置方案编号与业务类型查询该方案下提成计算比例信息     
	 * @Description:根据提成配置方案编号与业务类型查询该方案下提成计算比例信息       
	 * @param commConfigId 提成配置方案编号
	 * @param BusiTypeId 业务类型编号
	 * @param months 月份 (例：2018-01)
	 * @return: List<Map<String,Object>>
	 * @see com.bashiju.fin.service.CommissionsConfigService#queryCommConfigRate(java.lang.String, java.lang.String)   
	 */
	@Override
	public List<Map<String, Object>> queryCommConfigRate(String commConfigId, String BusiTypeId,String months) {
		if(StringUtils.isEmpty(commConfigId) || StringUtils.isEmpty(BusiTypeId))
			throw new BusinessException("提成配置方案编号和业务类型编号都不允许为空");
		return commissionsConfigMapper.queryCommConfigRate(commConfigId, BusiTypeId,months);
	}

	/**
	 * 保存提成计算比例信息
	 * @Description: 保存提成计算比例信息
	 * @param map 待保存的提成比例
	 * @return: boolean
	 * @see com.bashiju.fin.service.CommissionsConfigService#saveOrUpdateCommConfigRate(java.util.Map)   
	 */
	@Override
	@SystemServiceLog(operationType="保存提成比例")
	public boolean saveOrUpdateCommConfigRate(List<Map<String,Object>> editDataList,List<Map<String,Object>> addDataList,String delId) {
		boolean res = false;
		if((editDataList == null || editDataList.size()<=0) && (addDataList == null || addDataList.size()<=0))
			throw new BusinessException("待保存的实收提成比例信息不允许为空");
		if(delId!=null && StringUtils.isNotEmpty(delId)) {
			this.delData("finance_commissionsConfigurate_rate", "id", delId, false);
		}
		if(editDataList.size()>0) {
			res = this.batchCommonOperationDatabase(editDataList, "finance_commissionsConfigurate_rate", "id", false);
		}
		if(addDataList.size()>0) {
			Map<String,Object> user = UserThreadLocal.get();
			for(Map<String,Object> m: addDataList) {
				m.put("permissionArea", user.get("deptId"));
				m.put("operatorId", user.get("id"));
			}
			res = this.batchCommonOperationDatabase(addDataList, "finance_commissionsConfigurate_rate",false);
		}
		ExecutionResult.descFormat("", "编辑提成比例");
		
		return true;
	}
	/**
	 * 
	 * 查询通提比例
	 * @param commConfigId
	 * @param BusiTypeId
	 * @return   
	 * @see com.bashiju.fin.service.CommissionsConfigService#queryUniversalRate(java.lang.String, java.lang.String)
	 */
	@Override
	@SystemServiceLog(operationType="查询通提比例")
	public Map<String,Object> queryUniversalRate(String commConfigId,String BusiTypeId) {
		
		return commissionsConfigMapper.queryUniversalRate(commConfigId, BusiTypeId);
	}
	/**
	 * 保存或更新通提比例
	 * @param map
	 * @return   
	 * @see com.bashiju.fin.service.CommissionsConfigService#saveOrUpdateUniversalRate(java.util.Map)
	 */
	@Override
	@SystemServiceLog(operationType="保存通提比例")
	public boolean saveOrUpdateUniversalRate(Map<String,Object> map) {
		if(map == null || map.size()<=0)
			throw new BusinessException("待更新的业务类型信息不允许为空");
		if(map.containsKey("id") && map.get("id")!=null) {
			long id = this.commonOperationDatabase(map, "finance_commissionsconfigurate_universalrate", "id", false);
			ExecutionResult.descFormat(Long.toString(id), "编辑通提比例");
		}else {
			long id = this.commonOperationDatabase(map, "finance_commissionsconfigurate_universalrate", false);
			ExecutionResult.descFormat(Long.toString(id),"新增通提比例");
		}
			
		return true;
	}
}
