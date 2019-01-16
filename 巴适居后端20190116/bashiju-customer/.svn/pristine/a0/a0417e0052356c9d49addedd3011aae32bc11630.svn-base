/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  DepositManageServiceImpl.java   
 * @Package com.bashiju.customer.service.impl      
 * @author: zuoyuntao     
 * @date:   2018年6月29日 下午3:47:05   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.customer.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.customer.mapper.IDepositMangeMapper;
import com.bashiju.customer.service.CustomerService;
import com.bashiju.customer.service.IDepositManageService;
import com.bashiju.enums.DepositManageEnum;
import com.bashiju.enums.MenuEnum;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 定金管理服务
 * @ClassName:DepositManageServiceImpl
 * @Description:定金管理服务
 * @author:zuoyuntao
 * @date:2018年6月29日 下午3:47:05
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
@SystemServiceLog(sourceType="定金管理")
public class DepositManageServiceImpl extends CommonSqlServie implements IDepositManageService {
	/**
	 * 权限接口类
	 */
	@Autowired
	private DataAuthHelper mDataAuthHelper;
	/**
	 * 定金管理映射接口
	 */
	@Autowired
	private IDepositMangeMapper mIDepositMangeMapper;
	/**
	 * 可以接口
	 */
	@Autowired
	private CustomerService mCustomerService;
	/**   
	 * <p>Title: queryDepositDataListPage</p>   
	 * <p>Description: 获取定金分页数据</p>   
	 * @param paraMap 参数对象
	 * @param page 最小条数
	 * @param limit 最大条数
	 * @return   
	 * @see com.bashiju.customer.service.IDepositManageService#queryDepositDataListPage(java.util.Map)   
	 */
	@Override
	@SystemServiceLog(operationType="查询定金")
	public Page<Map<String, Object>> queryDepositDataListPage(Map<String, Object> paraMap
			,int page,int limit) {
		PageHelper.startPage(page,limit);
		Page<Map<String, Object>> retPage = (Page<Map<String,Object>>) queryDepositDataList(paraMap);
		ExecutionResult.descFormat("", "查询定金");
		return retPage;
	}

	/**   
	 * <p>Title: queryDepositDataList</p>   
	 * <p>Description: 查询定金数据--无分页</p>   
	 * @param paraMap 参数对象
	 * @return   
	 * @see com.bashiju.customer.service.IDepositManageService#queryDepositDataList(java.util.Map)   
	 */
	@Override
	@SystemServiceLog(operationType="查询定金")
	public List<Map<String, Object>> queryDepositDataList(Map<String, Object> paraMap) {
		mDataAuthHelper.auth(MenuEnum.MENU_100.getCode()
				, String.valueOf(UserThreadLocal.get().get("id")));
		List<Map<String,Object>> retList = mIDepositMangeMapper.queryAllDepositDataList(paraMap);
		ExecutionResult.descFormat("", "查询定金数据信息");
		return retList;
	}

	/**   
	 * <p>Title: savePayOffData</p>   
	 * <p>Description: 保存定金支出数据</p>   
	 * @param paraMap 参数对象
	 * @see com.bashiju.customer.service.IDepositManageService#savePayOffData(java.util.Map)   
	 */
	@Override
	@SystemServiceLog(operationType="更新定金")
	public void savePayOffData(Map<String,Object> paraMap) {
		commonOperationDatabase(paraMap,"demand_bond","id", false);
		ExecutionResult.descFormat(String.valueOf(paraMap.get("id")), "更新定金支出信息");
	}

	/**   
	 * <p>Title: updateDepositData</p>   
	 * <p>Description:更新定金状态</p>   
	 * @param paraMap   
	 * @see com.bashiju.customer.service.IDepositManageService#updateDepositData(java.util.Map)   
	 */
	@Override
	@SystemServiceLog(operationType="更新定金")
	public void updateDepositData(Map<String,Object> paraMap) {
		if(paraMap.containsKey("status") && (paraMap.get("status").equals(DepositManageEnum.CONFIRMED.getCode()) 
				|| paraMap.get("status").equals(DepositManageEnum.RETREATED.getCode()))) {
			String houseId = String.valueOf(paraMap.get("houseId"));
			Long houseIdL = Long.parseLong(houseId);
			mCustomerService.DepositSureOp(String.valueOf(paraMap.get("demandId"))
					,houseIdL.longValue()
					,String.valueOf(paraMap.get("status")));
			paraMap.remove("demandId");
			paraMap.remove("houseId");
		}
		commonOperationDatabase(paraMap,"demand_bond","id", false);
		ExecutionResult.descFormat(String.valueOf(paraMap.get("id")), "更新定金状态");
	}

}
