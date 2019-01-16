/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CompanyComConfigService.java   
 * @Package com.bashiju.fin.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年9月12日 下午5:51:19   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.fin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.MenuEnum;
import com.bashiju.fin.mapper.CompanyComConfigMapper;
import com.bashiju.fin.service.CompanyComConfigService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**   
 * @ClassName:  CompanyComConfigService   
 * @Description:总部计提管理服务   
 * @author: yangz
 * @date:   2018年9月12日 下午5:51:19   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
@SystemServiceLog(sourceType="总部计提")
public class CompanyComConfigServiceImpl extends CommonSqlServie implements CompanyComConfigService {

	@Autowired
	private CompanyComConfigMapper companyComConfigMapper;
	
	@Autowired
	private DataAuthHelper dataAuthhelper;
	/**
	 * 查询总部计提信息
	 * @Description: 查询总部计提信息   
	 * @param deptId 计提部门
	 * @param months 计提月份(yyyy-MM)
	 * @param isCalculate 是否计算提成 
	 * @return: List<Map<String,Object>>
	 * @see com.bashiju.fin.service.CompanyComConfigService#queryCompanyComConfig(java.lang.String, java.lang.String, java.lang.String)   
	 */
	@Override
	public List<Map<String, Object>> queryCompanyComConfig(String deptId, String months, String isCalculate) {
		dataAuthhelper.auth(MenuEnum.MENU_129.getCode(), UserThreadLocal.get().get("id").toString());
		return companyComConfigMapper.queryCompanyComConfig(deptId, months, isCalculate);
	}

	/**
	 * 查询总部计提信息
	 * @Description: 查询总部计提信息 (分页) 
	 * @param deptId 计提部门
	 * @param months 计提月份(yyyy-MM)
	 * @param isCalculate 是否计算提成 
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: Page<Map<String,Object>>
	 * @see com.bashiju.fin.service.CompanyComConfigService#queryCompanyComConfig(java.lang.String, java.lang.String, java.lang.String, int, int)   
	 */
	@Override
	public Page<Map<String, Object>> queryCompanyComConfig(String deptId, String months, String isCalculate,
			int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<Map<String,Object>> page = (Page<Map<String, Object>>) this.queryCompanyComConfig(deptId, months, isCalculate);
		return page;
	}

	/**
	 * 保存总部计提信息
	 * @Description: 保存总部计提信息   
	 * @param map 待保存的计提信息
	 * @return: boolean
	 * @see com.bashiju.fin.service.CompanyComConfigService#saveOrUpdateCompanyComConfig(java.util.Map)   
	 */
	@Override
	@SystemServiceLog(operationType="保存总部计提")
	public boolean saveOrUpdateCompanyComConfig(Map<String,Object> map) {
		if(map == null || map.size()<=0)
			throw new BusinessException("待保存的计提信息不允许为空");
		if(map.containsKey("id") && map.get("id")!=null) {//编辑
			Map<String,Object> config = companyComConfigMapper.getCompanyConmConfig(map.get("id").toString());
			if(config!=null && config.size()>0 && "0".equals(config.get("isCalculate").toString())) {
				long id = this.commonOperationDatabase(map, "finance_companyComConfigurate", "id", false);
				ExecutionResult.descFormat(Long.toString(id), "编辑总部计提");
			}else 
				throw new BusinessException("计提信息不存在或者已经计算了提成不允许修改");
		}else {
			List<Map<String,Object>> depts = companyComConfigMapper.getDept(UserThreadLocal.get().get("companyId").toString());//UserThreadLocal.get().get("companyId").toString()
			List<Map<String,Object>> data = new ArrayList<>();
			if(depts.size()>0) {
				for(Map<String,Object> m : depts) {
					Map<String,Object> nmap = new HashMap<>();
					nmap.putAll(map);
					Map<String,Object> dataMap = (Map)nmap;
					dataMap.put("deptId", m.get("id"));
					data.add(dataMap);
				}
			}else {
				throw new BusinessException("没有计提的部门！");
			}
			boolean res = this.batchCommonOperationDatabase(data, "finance_companyComConfigurate", false);
			ExecutionResult.descFormat("", "新增总部计提");
		}
		return true;
	}

	/**
	 * 删除总部计提信息   
	 * @Description: 删除总部计提信息   
	 * @param configId 待删除的总部计提编号
	 * @return: boolean
	 * @see com.bashiju.fin.service.CompanyComConfigService#delCompanyComConfig(java.lang.String)   
	 */
	@Override
	@SystemServiceLog(operationType="删除总部计提")
	public boolean delCompanyComConfig(String configId) {
		if(StringUtils.isEmpty(configId))
			return false;
		Map<String,Object> config = companyComConfigMapper.getCompanyConmConfig(configId);
		if(config!=null && config.size()>0 && "0".equals(config.get("isCalculate").toString())) {
			boolean result = this.delData("finance_companyComConfigurate", "id", configId, false);
			if(result) {
				ExecutionResult.descFormat(configId, "删除总部计提");
				return true;
			}
		}else 
			throw new BusinessException("计提信息不存在或者已经计算了提成不允许删除");
		return false;
	}

}
