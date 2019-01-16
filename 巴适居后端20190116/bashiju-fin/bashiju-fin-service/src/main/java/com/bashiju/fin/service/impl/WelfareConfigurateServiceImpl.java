/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  WelfareConfigurateServiceImpl.java   
 * @Package com.bashiju.fin.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年9月4日 上午10:45:32   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.fin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.MenuEnum;
import com.bashiju.fin.mapper.WelfareConfigurateMapper;
import com.bashiju.fin.service.WelfareConfigurateService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**   
 * @ClassName:  WelfareConfigurateServiceImpl   
 * @Description:福利配置管理接口
 * @author: yangz
 * @date:   2018年9月4日 上午10:45:32   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
@SystemServiceLog(sourceType="福利配置管理")
public class WelfareConfigurateServiceImpl extends CommonSqlServie implements WelfareConfigurateService {

	@Autowired
	private WelfareConfigurateMapper welfareConfigurateMapper;
	
	@Autowired
	private DataAuthHelper dataAuthHelper;
	
	/**     
	 * @Description: 获取福利方案信息(包括详情信息)
	 * @param name 方案名称(模糊查询)
	 * @return: Page<Map<String,Object>> 
	 * @see com.bashiju.fin.service.WelfareConfigurateService#queryWelfareCOnfigurateAll(java.lang.String)   
	 */
	@Override
	public List<Map<String, Object>> queryWelfareCOnfigurateAll(String name) {
		dataAuthHelper.auth(MenuEnum.MENU_124.getCode(), UserThreadLocal.get().get("id").toString());
		List<Map<String,Object>> list = welfareConfigurateMapper.queryWelfareCOnfigurateAll(name);
		return list;
	}


	/**     
	 * @Description: 获取福利方案信息(包括详情信息)
	 * @param name 方案名称(模糊查询)
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: Page<Map<String,Object>>
	 * @see com.bashiju.fin.service.WelfareConfigurateService#queryWelfareCOnfigurateAll(java.lang.String, int, int)   
	 */
	@Override
	public Page<Map<String, Object>> queryWelfareCOnfigurateAll(String name, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Map<String,Object>> list = this.queryWelfareCOnfigurateAll(name);
		Page<Map<String,Object>> page = (Page<Map<String, Object>>) list;
		return page;
	}

	/**
	 * 保存配置方案信息   
	 * @Description: 保存配置方案信息   
	 * @param map 待保存的方案配置信息
	 * @return: boolean 
	 * @see com.bashiju.fin.service.WelfareConfigurateService#saveOrUpdateWelfareConfig(java.util.Map)   
	 */
	@Override
	@SystemServiceLog(operationType="保存福利")
	public boolean saveOrUpdateWelfareConfig(Map<String,Object> map) {
		if(map == null || map.size()<=0)
			throw new BusinessException("待保存的方案信息不允许为空");
		if(map.containsKey("id") && map.get("id")!=null) {//编辑
			long id = this.commonOperationDatabase(map, "finance_welfareConfigurate", "id", false);
			ExecutionResult.descFormat(Long.toString(id), "编辑福利");
		}else {
			long id = this.commonOperationDatabase(map, "finance_welfareConfigurate", false);
			ExecutionResult.descFormat(Long.toString(id), "新增福利");
		}
		return true;
	}

	/**
	 * 保存配置方案明细信息  
	 * @Description: 保存配置方案明细信息  
	 * @param map 待保存的方案配置明细信息
	 * @return: boolean 
	 * @see com.bashiju.fin.service.WelfareConfigurateService#saveOrupdateWelfareConfigDetail(java.util.Map)   
	 */
	@Override
	@SystemServiceLog(operationType="保存福利详情")
	public long saveOrupdateWelfareConfigDetail(Map<String,Object> map) {
		long id = 0;
		if(map == null || map.size()<=0)
			throw new BusinessException("待保存的方案详细信息不允许为空");
		if(map.containsKey("id") && map.get("id")!=null) {//编辑
			id = this.commonOperationDatabase(map, "finance_welfareConfigurate_detail", "id", false);
			ExecutionResult.descFormat(Long.toString(id), "编辑福利详情");
		}else {
			id = this.commonOperationDatabase(map, "finance_welfareConfigurate_detail", false);
			ExecutionResult.descFormat(Long.toString(id), "新增福利详情");
		}
		return id;
	}


	/**
	 * 删除福利配置方案
	 * @Description: 删除福利配置方案  
	 * @param welfareId 福利配置方案编号 
	 * @return: boolean
	 * @see com.bashiju.fin.service.WelfareConfigurateService#delWelfareConfig(java.lang.String)   
	 */
	@Override
	@SystemServiceLog(operationType="删除福利方案")
	public boolean delWelfareConfig(String welfareId) {
		if(StringUtils.isEmpty(welfareId))
			throw new BusinessException("福利配置方案编号不允许为空");
		if(this.isRelation(welfareId))
			throw new BusinessException("存在关联数据，不允许删除");
		boolean tt=this.delData("finance_welfareConfigurate", "id", "'"+welfareId+"'", false);
		if(tt) {
			ExecutionResult.descFormat(welfareId, "删除福利方案");
			return true;
		}else
			return false;
	}


	/**
	 * 是否存在与福利配置方案关联数据
	 * @Description: 是否存在与福利配置方案关联数据   
	 * @param welfareId 福利配置方案编号 
	 * @return: boolean   
	 */ 
	@Override
	public boolean isRelation(String welfareId) {
		if(StringUtils.isEmpty(welfareId))
			throw new BusinessException("福利配置方案编号不允许为空");
		long result = welfareConfigurateMapper.isRelation(welfareId);
		if(result>0) 
			return true;
		return false;
	}


	/**
	 * 删除福利方案详情   
	 * @Description: 删除福利方案详情   
	 * @param welfareDetailId 福利方案详情编号
	 * @return: boolean  
	 * @see com.bashiju.fin.service.WelfareConfigurateService#delWelfareConfigDetail(java.lang.String)   
	 */
	@Override
	@SystemServiceLog(operationType="删除福利详情")
	public boolean delWelfareConfigDetail(String welfareDetailId) {
		if(StringUtils.isEmpty(welfareDetailId))
			throw new BusinessException("福利方案详情编号不允许为空");
		boolean tt=this.delData("finance_welfareConfigurate_detail", "id", "'"+welfareDetailId+"'", false);
		if(tt) {
			ExecutionResult.descFormat(welfareDetailId, "删除福利详情");
			return true;
		}else
			return false;
	}

}
