/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  TransferProcessMapper.java   
 * @Package com.bashiju.manage.mapper   
 * @Description:    TODO(过户流程管理映射类)   
 * @author: zuoyuntao     
 * @date:   2018年5月4日 上午11:57:25   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

package com.bashiju.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 过户流程管理映射接口类
 * @ClassName:  TransferProcessMapper   
 * @Description:过户流程管理映射接口类   
 * @author: zuoyuntao
 * @date:   2018年5月4日 上午11:57:25   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface TransferProcessMapper {
	/**
	 * 根据查询条件及表名称查询数据信息
	 * @Title: queryDataInfoConfByConn   
	 * @Description: 根据查询条件及表名称查询数据信息   
	 * @param: condition 附加查询条件
	 * @param: tableName 查询表名称
	 * @return: List<Map<String,Object>>   
	 * JSON 格式为（其中一条）：{id=9, programmeName=预约合同过户流程, isValid=1, cityId=BSJ0102, cityName=大理市, valid=是, operator=测试人员, addTime=2018-05-23 13:57:41.0, updateOperator=测试人员, updateTime=2018-05-23 13:58:29.0, permissionArea=BSJ0103, operatorId=1, procModify=procModify, hsOwnerDetail=hsOwnerDetail, cusDetail=cusDetail, procConf=procConf, procDel=procDel}   
	 * @throws
	 */
	public List<Map<String,Object>> queryDataInfoConfByConn(
			@Param("condition") String condition,@Param("tableName") String tableName);
	
	/**
	 * 根据主配置ID明细配置数据信息
	 * @Title: queryTransferDetailByConfId   
	 * @Description: 根据主配置ID明细配置数据信息   
	 * @param: confId：主配置ID
	 * @param: confType：配置类型（1：业主明细材料，2：客户明细材料）
	 * @return: List<Map<String,Object>>   
	 * 返回数据其中一条的JSON格式：{id=52, detailName=策策, addTime=2018-05-09 10:00:07.0, operator=测试人员, updateOperator=测试人员, updateTime=2018-05-23 13:58:13.0, confType=1, isValid=1, operatorId=1, confId=13, sortId=25, permissionArea=BSJ0103}     
	 * @throws
	 */
	public List<Map<String,Object>> queryTransferDetailByConfId(
			@Param("confId") String confId,@Param("confType") String confType);
}
