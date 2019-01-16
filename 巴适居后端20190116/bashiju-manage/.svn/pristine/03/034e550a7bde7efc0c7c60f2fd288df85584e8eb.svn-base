/** 
 * 分成配置接口 
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  DivideIntoConfigMapper.java   
 * @Package com.bashiju.manage.mapper   
 * @Description:分成配置接口   
 * @author: zuoyuntao     
 * @date:   2018年4月24日 下午3:05:41   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

package com.bashiju.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

/**   
 * 分成配置接口
 * @ClassName:  DivideIntoConfigMapper   
 * @Description:分成配置接口   
 * @author: zuoyuntao
 * @date:2018年4月24日 下午3:05:41   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface DivideIntoConfigMapper {
	/**
	 * @Title: countDivideIntoConfigById   
	 * @Description: 根据配置名称统计配置条数   
	 * @param: configName：配置名称
	 * @return: int 数据条数
	 * @throws
	 */
	public long countDivideIntoConfigByName(@Param("configName") String configName);
	/**
	 * @Title: countDivideIntoConfigById   
	 * @Description: 根据分成配置ID统计配置条数   
	 * @param: configId：配置Id
	 * @return: int 数据条数
	 * @throws
	 */
	public long countDivideIntoConfigById(@Param("configId") String configId);
	/**
	 * 根据分成配置id删除分成配置信息 
	 * @Title: deleteDivideIntoConfInfoById   
	 * @Description: 根据分成配置id删除分成配置信息   
	 * @param: configId ：配置Id
	 * @return: void      
	 * @throws
	 */
	public void deleteDivideIntoConfInfoById(@Param("configId") String configId);
	
	/**
	 * 查询分成配置列表  
	 * @Title: queryDivideInfoConfList   
	 * @Description: 查询分成配置列表   
	 * @param: map
	 * @return: Page<Map<String,Object>>     
	 * Page{count=true, pageNum=1, pageSize=10, startRow=0, endRow=10, total=6, pages=1, reasonable=false, pageSizeZero=false}[{id=12, configName=一手成交, configStatus=1, cityId=BSJ0101, isValid=1, cityName=昆明市, addTime=2018-05-23 10:25:05.0, operator=测试人员, updateOperator=测试人员, updateTime=2018-05-23 10:25:52.0, operatorId=1, permissionArea=BSJ0103, dealType=1, configType=3, houseEntrustTrans=3, keyMgrTrans=4, signUkTrans=0, picUploadTrans=6, companyId=BSJ01, actionRoleIptRate=15, actRoleOpMgrRate=5, actRoleHouseMsgRate=25, actRoleCusIptRate=5, actRoleCusMgrRate=5, actRoleContractMgrRate=25, actHouseEntrustMgrRate=5, actRoleKeyMgrRate=5, actRoleSignUkRate=5, actRolePivUploadRate=5, status=已设置, valid=有效, dealTypeName=住宅, configTypeName=一手成交, confAdd=confAdd, confEidt=confEidt, edit=edit, del=del}, {id=13, configName=住宅出售（住宅、商铺、车位）, configStatus=1, cityId=BSJ0101, isValid=1, cityName=昆明市, addTime=2018-05-23 10:28:52.0, operator=测试人员, updateOperator=测试人员, updateTime=2018-05-23 10:29:39.0, operatorId=1, permissionArea=BSJ0103, dealType=1, configType=1, houseEntrustTrans=0, keyMgrTrans=1, signUkTrans=5, picUploadTrans=0, companyId=BSJ01, actionRoleIptRate=20, actRoleOpMgrRate=20, actRoleHouseMsgRate=20, actRoleCusIptRate=5, actRoleCusMgrRate=5, actRoleContractMgrRate=5, actHouseEntrustMgrRate=5, actRoleKeyMgrRate=10, actRoleSignUkRate=10, actRolePivUploadRate=, status=已设置, valid=有效, dealTypeName=住宅, configTypeName=出售, confAdd=confAdd, confEidt=confEidt, edit=edit, del=del}, {id=14, configName=住宅出租（住宅、商铺、车位）, cityId=BSJ0101, isValid=1, cityName=昆明市, addTime=2018-05-23 10:24:01.0, operator=测试人员, updateOperator=测试人员, updateTime=2018-05-23 10:24:48.0, operatorId=1, permissionArea=BSJ0103, dealType=1, configType=2, companyId=BSJ01, status=未设置, valid=有效, dealTypeName=住宅, configTypeName=出租, confAdd=confAdd, confEidt=confEidt, edit=edit, del=del}, {id=15, configName=商业出售（写字楼、厂房、商铺、仓库，土地）, configStatus=1, cityId=BSJ0102, isValid=1, cityName=大理市, addTime=2018-05-22 18:59:52.0, operator=测试人员, updateOperator=测试人员, updateTime=2018-05-22 19:00:36.0, operatorId=1, permissionArea=BSJ0103, dealType=2, configType=1, houseEntrustTrans=3, keyMgrTrans=0, signUkTrans=0, picUploadTrans=6, companyId=BSJ01, actionRoleIptRate=5, actRoleOpMgrRate=1, actRoleHouseMsgRate=6, actRoleCusIptRate=6, actRoleCusMgrRate=8, actRoleContractMgrRate=9, actHouseEntrustMgrRate=15, actRoleKeyMgrRate=3, actRoleSignUkRate=2, actRolePivUploadRate=7, status=已设置, valid=有效, dealTypeName=商业, configTypeName=出售, confAdd=confAdd, confEidt=confEidt, edit=edit, del=del}, {id=32, configName=商业出租（写字楼、厂房、商铺、仓库、土地）, cityId=BSJ0102, isValid=1, cityName=大理市, addTime=2018-05-23 10:23:34.0, operator=测试人员, updateOperator=测试人员, updateTime=2018-05-23 10:24:21.0, operatorId=1, permissionArea=BSJ0103, dealType=2, configType=2, houseEntrustTrans=0, keyMgrTrans=0, signUkTrans=0, picUploadTrans=0, companyId=BSJ01, status=未设置, valid=有效, dealTypeName=商业, configTypeName=出租, confAdd=confAdd, confEidt=confEidt, edit=edit, del=del}, {id=33, configName=一手成交, cityId=BSJ0101, isValid=1, cityName=昆明市, addTime=2018-05-23 10:26:11.0, operator=测试人员, operatorId=1, permissionArea=BSJ0103, dealType=2, configType=3, houseEntrustTrans=0, keyMgrTrans=0, signUkTrans=0, picUploadTrans=0, companyId=BSJ01, status=未设置, valid=有效, dealTypeName=商业, configTypeName=一手成交, confAdd=confAdd, confEidt=confEidt, edit=edit, del=del}] 
	 * @throws
	 */
	public Page<Map<String, Object>> queryDivideInfoConfList(Map<String, Object> map);
	
	/**
	 * 查询分成配置列表 
	 * @Title: queryDivideInfoConfList   
	 * @Description: 查询分成配置列表 
	 * @param: configId 主配置信息ID
	 * @return: Map<String,Object>  
	 * {id=32, configName=商业出租（写字楼、厂房、商铺、仓库、土地）, cityId=BSJ0102, isValid=1, addTime=2018-05-23 10:23:34.0, operator=测试人员, updateOperator=测试人员, updateTime=2018-05-23 10:24:21.0, operatorId=1, permissionArea=BSJ0103, dealType=2, configType=2, houseEntrustTrans=0, keyMgrTrans=0, signUkTrans=0, picUploadTrans=0, companyId=BSJ01, confAdd=confAdd, confEidt=confEidt, edit=edit, del=del}    
	 * @throws
	 */
	public Map<String,Object> queryDivideInfoConfById(@Param("configId") String configId);
	
	/**
	 * 根据公司ID获取城市信息  
	 * @Title: getCityInfoByCompanyId   
	 * @Description: 根据公司ID获取城市信息  
	 * @param: companyId：公司ID
	 * @return: List<Map<String,Object>>    
	 * {BSJ0102=大理市, BSJ0101=昆明市}，key为城市Id，value为城市名称    
	 * @throws
	 */
	public List<Map<String,Object>> getCityInfoByCompanyId(@Param("companyId")String companyId);
	/**
	 * 根据条件查询分成配置  
	 * @Title: queryDivideInfoConfByConn   
	 * @Description: 根据条件查询分成配置   
	 * @param: condition：查询条件
	 * @return: long 查询结果集的数据条数
	 * @throws
	 */
	public long queryDivideInfoConfByConn(@Param("condition")String condition);
	/**
	 * 根据条件查询分成配置  
	 * @Title: queryDivideInfoConfByConn   
	 * @Description: 根据条件查询分成配置   
	 * @param: condition：查询条件
	 * @return: 返回数据查询结果集
	 * @throws
	 */
	public List<Map<String,Object>> queryDivideInfoConfByCondition(
			@Param("condition")String condition);

}
