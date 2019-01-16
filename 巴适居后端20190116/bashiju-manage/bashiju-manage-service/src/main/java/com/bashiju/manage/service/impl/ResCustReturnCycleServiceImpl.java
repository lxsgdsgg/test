package com.bashiju.manage.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.MenuEnum;
import com.bashiju.manage.mapper.ResCustReturnCycleMapper;
import com.bashiju.manage.service.ResCustReturnCycleService;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
/**
 * 
 * @ClassName:  ResCustReturnCycleServiceImpl   
 * @Description:客源回访周期服务  
 * @author: wangpeng
 * @date:   2018年5月31日 下午5:54:41   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
@SystemServiceLog(sourceType="客源回访周期设置")
public class ResCustReturnCycleServiceImpl extends CommonSqlServie implements ResCustReturnCycleService{
	@Autowired
	private ResCustReturnCycleMapper resCustReturnCycleMapper;
	@Autowired
	private DataAuthHelper dataAuthHelper;
	/**
	 * 
	 * <p>Title: queryResCustReturnCycleData</p>   
	 * <p>Description: 查询客源回访周期</p>   
	 * @return   
	 * @see com.bashiju.manage.service.ResCustReturnCycleService#queryResCustReturnCycleData()
	 */
	@Override
	public List<Map<String, Object>> queryResCustReturnCycleData() {
		String companyId=UserThreadLocal.get().get("companyId").toString();
		if(StringUtils.isEmpty(companyId))
			return null;
		dataAuthHelper.auth(MenuEnum.MENU_71.getCode(),UserThreadLocal.get().get("id").toString());
		List<Map<String,Object>> map=resCustReturnCycleMapper.queryResCustReturnCycleData(companyId);
		return map;
	}
	/**
	 * 
	 * <p>Title: saveOrUpdateResCustReturnCycle</p>   
	 * <p>Description: 批量修改客源回访周期</p>   
	 * @param map
	 * @return   
	 * @see com.bashiju.manage.service.ResCustReturnCycleService#saveOrUpdateResCustReturnCycle(java.util.List)
	 */
	@SuppressWarnings("unlikely-arg-type")
	@SystemServiceLog(operationType="批量更新客源回访周期")
	@Override
	public boolean saveOrUpdateResCustReturnCycle(List<Map<String, Object>> map) {
		boolean result;
		if(map.get(0).containsKey("id")) {
			//更新
		 result= this.batchCommonOperationDatabase(map, "res_cust_return_cycle", "id", false);
		 if(result) {
				ExecutionResult.descFormat(map.get(0).get("id").toString(),"批量更新客源回访周期");
				return true;
			}else
				return false;
		}else {
			//添加
			for(int i=0;i<map.size();i++) {
				map.get(i).put("companyId", UserThreadLocal.get().get("companyId"));
				map.get(i).put("permissionArea", UserThreadLocal.get().get("deptId"));
				map.get(i).put("operatorId", UserThreadLocal.get().get("id"));
//				map.get(i).put("operator", UserThreadLocal.get().get("realName"));
			}
			result = this.batchCommonOperationDatabase(map, "res_cust_return_cycle", false);
			if(result) {
				ExecutionResult.descFormat(String.valueOf(result),"批量添加客源回访周期");
				return true;
			}else
				return false;
		}
	}
}
