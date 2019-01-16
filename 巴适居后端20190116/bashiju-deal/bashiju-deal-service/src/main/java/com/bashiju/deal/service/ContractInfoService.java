package com.bashiju.deal.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**
 * 
 * 合同信息管理接口
 * @ClassName:  ContractInfoService   
 * @Description:  合同信息管理接口
 * @author: wangkaifa
 * @date:   2018年10月17日 下午6:21:47       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface ContractInfoService {
	/**
	 * 
		 * 查询合同列表
		 * @Description:查询合同列表 
		 * @param condition 查询条件
		 * @param page
		 * @param limit
		 * @return 
		 * Page<Map<String,Object>>
	 */
	Page<Map<String, Object>> queryContractInfoList(String condition,int page,int limit);
	/**
	 * 
		 * 查询合同详细
		 * @Description: 查询合同详细
		 * @param id 合同id
		 * @return 
		 * Map<String,Object>
	 */
	Map<String, Object> queryDetailed(Long id);
	/**
	 * 
		 * 合同审批
		 * @Description: 合同审批
		 * @param id
		 * @param status
		 * @param approvalResult
		 * @return 
		 * Boolean
	 */
	Boolean updateStatus(Long id,Long shhid,String agreementType,String status,String approvalResult);
	/**
	 * 
		 * 根据房源id、客户id查询姓名电话
		 * @Description: 根据房源id、客户id查询姓名电话 
		 * @param houseId 房源id
		 * @param customerId 客源id
		 * @return 
		 * List<Map<String,Object>>
	 */
	List<Map<String, Object>> queryPayerInfo(Long houseId,String customerId);
	/**
	 * 
		 * 根据房源id查询待审核合同数量
		 * @Description: 根据房源id查询待审核合同数量 
		 * @param houseId
		 * @return 
		 * int
	 */
	int queryCountByHouseId(Long houseId);
}
