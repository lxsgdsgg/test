package com.bashiju.sso.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserManageMapper{
	/*按照条件查询登录用户列表*/
	public Map<String, Object> queryUserInfo(Map<String, Object> conditionMap);
	/*按照条件查询数量*/
	public Integer queryUserCount(Map<String,Object> map);
	/*按照条件查询数量*/
	public Integer queryLoginRules(Map<String,Object> map);
	/**
	 * 
		 * 根据硬件编号查询硬件信息
		 * @Description: 根据硬件编号查询硬件信息
		 * @param hardcode 硬件编号
		 * @return {userType:硬件类型（UserTypeEnum）,companyId:绑定公司id,userId:绑定人id}
		 * Map<String,Object>
	 */
	public Map<String, Object> queryHardware(@Param("hardcode")String hardcode);
	/**
	 * 
		 * 根据硬件编号查询是否有待审核信息
		 * @Description: 根据硬件编号查询是否有待审核信息
		 * @param hardcode 硬件编号
		 * @return 数量
		 * Integer
	 */
	public Integer queryHardCheck(@Param("hardcode")String hardcode);
	/**
	 * 
		 * 判断公司在某城市是否开通
		 * @Description: 判断公司在某城市是否开通 
		 * @param companyId 公司id
		 * @param cityCode 城市代码
		 * @return 
		 * Integer
	 */
	public Integer queryCompanyOpenCity(@Param("companyId")String companyId,@Param("cityCode")String cityCode);
}
