package com.bashiju.deal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
/**
 * 
 *  合同管理
 * @ClassName:  ContractInfoMapper   
 * @Description:  合同模板管理
 * @author: wangkaifa
 * @date:   2018年9月20日 下午4:55:46       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface ContractInfoMapper {
	/**
	 * 
		 * 查询合同列表
		 * @Description: 查询合同列表 
		 * @return 
		 * Page<Map<String,Object>>
	 */
	Page<Map<String, Object>> queryContractInfoList(Map<String, Object> param);
	/**
	 * 
		 * 根据合同id查询详细
		 * @Description: 根据合同id查询详细
		 * @param id 合同id
		 * @return 
		 * Map<String,Object>
	 */
	Map<String, Object> queryDetailed(@Param("id")Long id);
	/**
	 * 
		 * 根据房源id、客户id查询姓名电话
		 * @Description: 根据房源id、客户id查询姓名电话
		 * @param houseId 房源id
		 * @param customerId 客源id
		 * @return 
		 * List<Map<String,Object>>
	 */
	List<Map<String, Object>> queryPayerInfo(@Param("houseId")Long houseId,@Param("customerId")String customerId);
	/**
	 * 
		 * 根据房源id查询待审核的合同数量
		 * @Description: 根据房源id查询待审核的合同数量 
		 * @param houseId
		 * @return 
		 * int
	 */
	int queryCountByHouseId(@Param("houseId")Long houseId);
}
