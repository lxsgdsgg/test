/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  SsfConfigurateServiceImpl.java   
 * @Package com.bashiju.fin.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年9月8日 下午2:17:31   
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
import com.bashiju.fin.mapper.SsfConfigurateMapper;
import com.bashiju.fin.service.SsfConfigurateService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**   
 * @ClassName:  SsfConfigurateServiceImpl   
 * @Description:社保配置管理服务   
 * @author: yangz
 * @date:   2018年9月8日 下午2:17:31   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
@SystemServiceLog(sourceType="社保配置")
public class SsfConfigurateServiceImpl extends CommonSqlServie implements SsfConfigurateService {

	@Autowired
	private SsfConfigurateMapper ssfConfigurateMapper;
	
	@Autowired
	private DataAuthHelper dataAuthHelper;
	
	/**
	 * 查询社保配置方案信息(包括详情信息) 
	 * @Description: 查询社保配置方案信息(包括详情信息)   
	 * @param name 配置方案名称(模糊查询)
	 * @return: List<Map<String,Object>>
	 * @see com.bashiju.fin.service.SsfConfigurateService#querySsfConfigurateAll(java.lang.String)   
	 */
	@Override
	public List<Map<String, Object>> querySsfConfigurateAll(String name) {
		dataAuthHelper.auth(MenuEnum.MENU_125.getCode(), UserThreadLocal.get().get("id").toString());
		return ssfConfigurateMapper.querySsfConfigurateAll(name);
	}

	/**
	 * 查询社保配置方案信息(包括详情信息) 
	 * @Description: 查询社保配置方案信息(包括详情信息)    
	 * @param name 配置方案名称(模糊查询)
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: Page<Map<String,Object>>
	 * @see com.bashiju.fin.service.SsfConfigurateService#querySsfConfigurateAll(java.lang.String, int, int)   
	 */
	@Override
	public Page<Map<String, Object>> querySsfConfigurateAll(String name, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Map<String,Object>> list = this.querySsfConfigurateAll(name);
		Page<Map<String,Object>> page = (Page<Map<String, Object>>) list;
		return page;
	}

	/**
	 * 保存配置方案信息   
	 * @Description: 保存配置方案信息   
	 * @param map 待保存的方案配置信息
	 * @return: boolean
	 * @see com.bashiju.fin.service.SsfConfigurateService#saveOrUpdateSsfConfigurate(java.util.Map)   
	 */
	@Override
	@SystemServiceLog(operationType="保存社保方案")
	public boolean saveOrUpdateSsfConfigurate(Map<String,Object> map) {
		if(map == null || map.size()<=0)
			throw new BusinessException("待保存的方案信息不允许为空");
		if(map.containsKey("id") && map.get("id")!=null) {//编辑
			long id = this.commonOperationDatabase(map, "finance_ssfConfigurate", "id", false);
			ExecutionResult.descFormat(Long.toString(id), "编辑社保");
		}else {
			long id = this.commonOperationDatabase(map, "finance_ssfConfigurate", false);
			ExecutionResult.descFormat(Long.toString(id), "新增社保");
		}
		return true;
	}

	/**
	 * 保存配置方案明细信息  
	 * @Description: 保存配置方案明细信息  
	 * @param map 待保存的方案配置明细信息
	 * @return: boolean
	 * @see com.bashiju.fin.service.SsfConfigurateService#saveOrupdateSsfConfigurateDetail(java.util.Map)   
	 */
	@Override
	@SystemServiceLog(operationType="保存社保明细")
	public long saveOrupdateSsfConfigurateDetail(Map<String,Object> map) {
		long id = 0;
		if(map == null || map.size()<=0)
			throw new BusinessException("待保存的方案详细信息不允许为空");
		if(map.containsKey("id") && map.get("id")!=null) {//编辑
			id = this.commonOperationDatabase(map, "finance_ssf_detail", "id", false);
			ExecutionResult.descFormat(Long.toString(id), "编辑社保详情");
		}else {
			id = this.commonOperationDatabase(map, "finance_ssf_detail", false);
			ExecutionResult.descFormat(Long.toString(id), "新增社保详情");
		}
		return id;
	}

	/**
	 * 删除社保配置方案
	 * @Description: 删除社保配置方案  
	 * @param ssfId 社保配置方案编号 
	 * @return: boolean
	 * @see com.bashiju.fin.service.SsfConfigurateService#delSsfConfigurate(java.lang.String)   
	 */
	@Override
	@SystemServiceLog(operationType="删除社保")
	public boolean delSsfConfigurate(String ssfId) {
		if(StringUtils.isEmpty(ssfId))
			throw new BusinessException("配置方案编号不允许为空");
		if(this.isRelation(ssfId))
			throw new BusinessException("存在关联数据，不允许删除");
		boolean tt=this.delData("finance_ssfConfigurate", "id", "'"+ssfId+"'", false);
		if(tt) {
			ExecutionResult.descFormat(ssfId, "删除社保方案");
			return true;
		}else
			return false;
	}

	/**
	 * 是否存在与社保配置方案关联数据
	 * @Description: 是否存在与社保配置方案关联数据   
	 * @param ssfId 社保配置方案编号 
	 * @return: boolean
	 * @see com.bashiju.fin.service.SsfConfigurateService#isRelation(java.lang.String)   
	 */
	@Override
	public boolean isRelation(String ssfId) {
		if(StringUtils.isEmpty(ssfId))
			throw new BusinessException("福利配置方案编号不允许为空");
		long result = ssfConfigurateMapper.isRelation(ssfId);
		if(result>0) 
			return true;
		return false;
	}

	/**
	 * 删除社保方案详情   
	 * @Description: 删除社保方案详情   
	 * @param ssfDetailId 社保方案详情编号
	 * @return: boolean
	 * @see com.bashiju.fin.service.SsfConfigurateService#delSsfConfigurateDetail(java.lang.String)   
	 */
	@Override
	@SystemServiceLog(operationType="删除社保详情")
	public boolean delSsfConfigurateDetail(String ssfDetailId) {
		if(StringUtils.isEmpty(ssfDetailId))
			throw new BusinessException("方案详情编号不允许为空");
		boolean tt=this.delData("finance_ssf_detail", "id", "'"+ssfDetailId+"'", false);
		if(tt) {
			ExecutionResult.descFormat(ssfDetailId, "删除社保详情");
			return true;
		}else
			return false;
	}

}
