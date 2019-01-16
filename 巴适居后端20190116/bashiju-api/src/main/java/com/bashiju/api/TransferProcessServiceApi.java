/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  TransferProcessServiceApi.java   
 * @Package com.bashiju.api   
 * @Description:过户流程管理对外接口服务提供接口类  
 * @author: zuoyuntao     
 * @date:   2018年6月23日 下午3:41:41   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.api;

import java.util.List;
import java.util.Map;

/**
 * 过户流程管理对外接口服务提供接口类
 * @ClassName:TransferProcessServiceApi
 * @Description:过户流程管理对外接口服务提供接口类
 * @author:zuoyuntao
 * @date:2018年6月23日 下午3:41:41
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface TransferProcessServiceApi {
	/**
	 * 对外提供接口：返回过户流程方案所有数据
	 * @Title: queryTransferProcessInfo
	 * @author: zuoyuntao  
	 * @Description:对外提供接口：返回过户流程方案所有数据
	 * @param areaCode 行政区划编码，如：530100
	 * @param companyId 公司ID
	 * @param operatorId 用户ID
	 * @return      
	 * List<Map<String,Object>> JSON 格式为：{id=9, programmeName=预约合同过户流程, isValid=1, cityId=BSJ0102, cityName=大理市, valid=是, operator=测试人员, addTime=2018-05-23 13:57:41.0, updateOperator=测试人员, updateTime=2018-05-23 13:58:29.0, permissionArea=BSJ0103, operatorId=1}     
	 * @throws
	 */
	public List<Map<String, Object>> queryTransferProcessInfo(String areaCode
			,String companyId,String operatorId);
	/**
	 * 根据主配置ID明细配置数据信息
	 * @Title: queryTransferDetailByConfId   
	 * @Description: 根据主配置ID明细配置数据信息  
	 * @param: confId：主配置ID
	 * @param: confType：配置类型（1：业主明细材料，2：客户明细材料，3：过户流程设置）
	 * @return: List<Map<String,Object>> 
	 * 返回数据其中一条的JSON格式：{id=52, detailName=策策, addTime=2018-05-09 10:00:07.0, operator=测试人员, updateOperator=测试人员, updateTime=2018-05-23 13:58:13.0, confType=1, isValid=1, operatorId=1, confId=13, sortId=25, permissionArea=BSJ0103}     
	 * @throws
	 */
	public List<Map<String,Object>> queryTransferDetailInfo(String confId,String confType);
}
