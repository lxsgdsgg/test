/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  IDivideIntoConfigService.java   
 * @Package com.bashiju.manage.service   
 * @Description:    处理分成配置信息)   
 * @author: zuoyuntao     
 * @date:   2018年4月24日 下午3:01:46   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

package com.bashiju.manage.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**   
 * 分成配置接口类
 * @ClassName:  IDivideIntoConfigService   
 * @Description:分成配置接口类
 * @author: zuoyuntao
 * @date:   2018年4月24日 下午3:01:46   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface IDivideIntoConfigService {
	
	/**
	 * 查询所有的分成配置信息 
	 * @Title: queryDivideIntoConfigInfoList   
	 * @Description: 查询所有的分成配置信息   
	 * @param paraMap ：参数map对象，包含configName、areaCode两个字段
	 * @param curPageCount：分页起始数
	 * @param maxPageCont：分页结束数
	 * @return Page<Map<String,String>>   
	 * Page{count=true, pageNum=1, pageSize=10, startRow=0, endRow=10, total=6, pages=1, reasonable=false, pageSizeZero=false}[{id=12, configName=一手成交, configStatus=1, cityId=BSJ0101, isValid=1, cityName=昆明市, addTime=2018-05-23 10:25:05.0, operator=测试人员, updateOperator=测试人员, updateTime=2018-05-23 10:25:52.0, operatorId=1, permissionArea=BSJ0103, dealType=1, configType=3, houseEntrustTrans=3, keyMgrTrans=4, signUkTrans=0, picUploadTrans=6, companyId=BSJ01, actionRoleIptRate=15, actRoleOpMgrRate=5, actRoleHouseMsgRate=25, actRoleCusIptRate=5, actRoleCusMgrRate=5, actRoleContractMgrRate=25, actHouseEntrustMgrRate=5, actRoleKeyMgrRate=5, actRoleSignUkRate=5, actRolePivUploadRate=5, status=已设置, valid=有效, dealTypeName=住宅, configTypeName=一手成交, confAdd=confAdd, confEidt=confEidt, edit=edit, del=del}, {id=13, configName=住宅出售（住宅、商铺、车位）, configStatus=1, cityId=BSJ0101, isValid=1, cityName=昆明市, addTime=2018-05-23 10:28:52.0, operator=测试人员, updateOperator=测试人员, updateTime=2018-05-23 10:29:39.0, operatorId=1, permissionArea=BSJ0103, dealType=1, configType=1, houseEntrustTrans=0, keyMgrTrans=1, signUkTrans=5, picUploadTrans=0, companyId=BSJ01, actionRoleIptRate=20, actRoleOpMgrRate=20, actRoleHouseMsgRate=20, actRoleCusIptRate=5, actRoleCusMgrRate=5, actRoleContractMgrRate=5, actHouseEntrustMgrRate=5, actRoleKeyMgrRate=10, actRoleSignUkRate=10, actRolePivUploadRate=, status=已设置, valid=有效, dealTypeName=住宅, configTypeName=出售, confAdd=confAdd, confEidt=confEidt, edit=edit, del=del}, {id=14, configName=住宅出租（住宅、商铺、车位）, cityId=BSJ0101, isValid=1, cityName=昆明市, addTime=2018-05-23 10:24:01.0, operator=测试人员, updateOperator=测试人员, updateTime=2018-05-23 10:24:48.0, operatorId=1, permissionArea=BSJ0103, dealType=1, configType=2, companyId=BSJ01, status=未设置, valid=有效, dealTypeName=住宅, configTypeName=出租, confAdd=confAdd, confEidt=confEidt, edit=edit, del=del}, {id=15, configName=商业出售（写字楼、厂房、商铺、仓库，土地）, configStatus=1, cityId=BSJ0102, isValid=1, cityName=大理市, addTime=2018-05-22 18:59:52.0, operator=测试人员, updateOperator=测试人员, updateTime=2018-05-22 19:00:36.0, operatorId=1, permissionArea=BSJ0103, dealType=2, configType=1, houseEntrustTrans=3, keyMgrTrans=0, signUkTrans=0, picUploadTrans=6, companyId=BSJ01, actionRoleIptRate=5, actRoleOpMgrRate=1, actRoleHouseMsgRate=6, actRoleCusIptRate=6, actRoleCusMgrRate=8, actRoleContractMgrRate=9, actHouseEntrustMgrRate=15, actRoleKeyMgrRate=3, actRoleSignUkRate=2, actRolePivUploadRate=7, status=已设置, valid=有效, dealTypeName=商业, configTypeName=出售, confAdd=confAdd, confEidt=confEidt, edit=edit, del=del}, {id=32, configName=商业出租（写字楼、厂房、商铺、仓库、土地）, cityId=BSJ0102, isValid=1, cityName=大理市, addTime=2018-05-23 10:23:34.0, operator=测试人员, updateOperator=测试人员, updateTime=2018-05-23 10:24:21.0, operatorId=1, permissionArea=BSJ0103, dealType=2, configType=2, houseEntrustTrans=0, keyMgrTrans=0, signUkTrans=0, picUploadTrans=0, companyId=BSJ01, status=未设置, valid=有效, dealTypeName=商业, configTypeName=出租, confAdd=confAdd, confEidt=confEidt, edit=edit, del=del}, {id=33, configName=一手成交, cityId=BSJ0101, isValid=1, cityName=昆明市, addTime=2018-05-23 10:26:11.0, operator=测试人员, operatorId=1, permissionArea=BSJ0103, dealType=2, configType=3, houseEntrustTrans=0, keyMgrTrans=0, signUkTrans=0, picUploadTrans=0, companyId=BSJ01, status=未设置, valid=有效, dealTypeName=商业, configTypeName=一手成交, confAdd=confAdd, confEidt=confEidt, edit=edit, del=del}]   
	 * @throws
	 */
	public Page<Map<String,Object>> queryDivideIntoConfigInfoList(Map<String,Object> paraMap,
			int curPageCount,int maxPageCont);
	/**
	 * 增加或修改分成配置信息
	 * @Title: saveOrUpdateDivideInto   
	 * @Description: 增加或修改分成配置信息  
	 * @param: paraMap 传入操作数据对象
	 * 字段列表{id=32, configName=商业出租（写字楼、厂房、商铺、仓库、土地）, cityId=BSJ0102, isValid=1, addTime=2018-05-23 10:23:34.0, operator=测试人员, updateOperator=测试人员, updateTime=2018-05-23 10:24:21.0, operatorId=1, permissionArea=BSJ0103, dealType=2, configType=2, houseEntrustTrans=0, keyMgrTrans=0, signUkTrans=0, picUploadTrans=0, companyId=BSJ01, confAdd=confAdd, confEidt=confEidt, edit=edit, del=del}    
	 * @return void     
	 * @throws
	 */
	public void saveOrUpdateDivideInto(Map<String,Object> paraMap);
	/**
	 * 根据配置ID查询分成配置信息
	 * @Title: queryDivideIntoConfigInfoMapById   
	 * @Description: 根据配置ID查询分成配置信息   
	 * @param: confId：配置ID
	 * @return: Map<String,Object>  
	 * {id=32, configName=商业出租（写字楼、厂房、商铺、仓库、土地）, cityId=BSJ0102, isValid=1, addTime=2018-05-23 10:23:34.0, operator=测试人员, updateOperator=测试人员, updateTime=2018-05-23 10:24:21.0, operatorId=1, permissionArea=BSJ0103, dealType=2, configType=2, houseEntrustTrans=0, keyMgrTrans=0, signUkTrans=0, picUploadTrans=0, companyId=BSJ01, confAdd=confAdd, confEidt=confEidt, edit=edit, del=del}    
	 * @throws
	 */
	public Map<String,Object> queryDivideIntoConfigInfoMapById(String confId);
	/**
	 * 根据分成配置id删除配置信息
	 * @Title: deleteDivideIntoConfigInfoByConfId   
	 * @Description: 根据分成配置id删除配置信息   
	 * @param: paraMapL:参数对象，必须包含ID
	 * @return: "success"      
	 * @throws
	 */
	public String deleteDivideIntoConfigInfoByConfId(Map<String,Object> paraMap);
	/**
	 * 根据公司ID获取城市信息
	 * @Title: queryCityInfoByCompanyId   
	 * @Description: 根据公司ID获取城市信息   
	 * @param: companyId：公司ID
	 * @return: List<Map<String,Object>>  
	 * cityInfo：{BSJ0102=大理市, BSJ0101=昆明市}，key为城市Id，value为城市名称
	 * @throws
	 */
	public List<Map<String,Object>> queryCityInfoByCompanyId(String companyId);
	
	/**
	 * 根据城市Id和公司ID判断分成配置是否存在
	 * @Title: jurgeDivideIntoConfigExists   
	 * @Description: 根据城市Id和公司ID判断分成配置是否存在  
	 * @param: paraMap：参数对象（暂时包括：城市ID，公司ID，处理类型：商业或住宅，配置类型：出租/出售）
	 * @return: true/false      
	 * @throws
	 */
	public boolean jurgeDivideIntoConfigExists(Map<String,Object> paraMap);
}
