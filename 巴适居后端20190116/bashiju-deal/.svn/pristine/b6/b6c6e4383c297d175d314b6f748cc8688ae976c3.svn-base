package com.bashiju.deal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * 
 *  合同模板管理
 * @ClassName:  ContractController   
 * @Description:  合同模板管理
 * @author: wangkaifa
 * @date:   2018年9月21日 下午2:42:21       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bashiju.deal.service.ContractInfoService;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;
@Controller
public class ContractInfoController {
	@Autowired
	private ContractInfoService contractInfoService;
	
	/**
	 * 
		 * 查询合同列表
		 * @Description: 查询合同列表 
		 * @return 
		 * Page<Map<String,Object>>
	 */
	@RequestMapping(value="queryContractInfoList")
	@ResponseBody
	public Map<String, Object> queryContractInfoList(String condition,int page,int limit){
		Page<Map<String, Object>> pageInfo=contractInfoService.queryContractInfoList(condition,page, limit);
		Map<String,Object> map = new HashMap<String,Object>(0);
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	/**
	 * 
		 * 查询合同详细
		 * @Description: 查询合同详细
		 * @param id 合同id
		 * @return 
		 * Map<String,Object>
	 */
	@RequestMapping(value="queryContractDetailed")
	@ResponseBody
	public BashijuResult queryContractDetailed(Long id){
		Map<String, Object> result= contractInfoService.queryDetailed(id);
		return BashijuResult.ok(result);
	}
	
	/**
	 * 
		 * 修改合同状态
		 * @Description:修改合同状态
		 * @param id 合同id
		 * @param shhid房源id
		 * @param agreementType合同类型01买卖合同，02租赁合同
		 * @param status 合同状态:01通过，02拒绝
		 * @param approvalResult 审批意见
		 * @return 
		 * int
	 */
	@RequestMapping(value="updateContractStatus")
	@ResponseBody
	public BashijuResult updateStatus(Long id,Long shhid,String agreementType,String status, String approvalResult) {
		contractInfoService.updateStatus(id,shhid,agreementType, status, approvalResult);
		return BashijuResult.ok();
	}
	/**
	 * 
		 * 根据房源id、客户id查询姓名电话
		 * @Description: 根据房源id、客户id查询姓名电话
		 * @param houseId 房源id
		 * @param customerId 客源id
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping(value="queryPayerInfo")
	@ResponseBody
	public BashijuResult queryPayerInfo(Long houseId, String customerId) {
		List<Map<String, Object>> result=contractInfoService.queryPayerInfo(houseId, customerId);
		return BashijuResult.ok(result);
	}
	
	/**
	 * 
		 * 根据房源id查询待审核合同数量
		 * @Description: 根据房源id查询待审核合同数量 
		 * @param houseId
		 * @return 
		 * int
	 */
	@RequestMapping(value="queryCountByHouseId")
	@ResponseBody
	public BashijuResult queryCountByHouseId(Long houseId) {
		int result=contractInfoService.queryCountByHouseId(houseId);
		return BashijuResult.ok(result);
	}
}
