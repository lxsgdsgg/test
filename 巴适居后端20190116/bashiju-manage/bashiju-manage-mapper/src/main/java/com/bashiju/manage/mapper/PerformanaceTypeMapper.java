/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  PerformanaceTypeMapper.java   
 * @Package com.bashiju.manage.mapper   
 * @Description: 业绩类型管理映射接口 
 * @author: zuoyuntao     
 * @date:   2018年5月10日 上午9:46:07   
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
 * 业绩类型管理映射接口
 * @ClassName:  PerformanaceTypeMapper   
 * @Description:业绩类型管理映射接口   
 * @author: zuoyuntao
 * @date:   2018年5月10日 上午9:46:07   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface PerformanaceTypeMapper {
	/**
	 * 查询所有业绩类型数据
	 * @Title: queryPfmTypeList   
	 * @Description: 查询所有业绩类型数据   
	 * @param: paraMap 传入参数对象
	 * @return: Page<Map<String, Object>> 
	 * {count=8, msg=, code=0, data=Page{count=true, pageNum=1, pageSize=10, startRow=0, endRow=10, total=8, pages=1, reasonable=false, pageSizeZero=false}[{pfmTypeName=一手成交, usePfmTypeName=一手成交, caculateMethod=215, permissionArea=BSJ0103, pfmTypeEdit=pfmTypeEdit, addTime=2018-05-16 12:01:25.0, usePfmTypeValue=208, isValid=1, caculateName=合同直计算提, updateOperator=测试人员, updateTime=2018-05-24 10:28:06.0, cityId=BSJ0101, operator=测试人员, companyId=BSJ01, cityName=昆明市, usePfm=一手成交, id=97, operatorId=1, pfmTypeDel=pfmTypeDel}, {pfmTypeName=买卖业务, usePfmTypeName=商业出售,住房出售, caculateMethod=215, permissionArea=BSJ0103, pfmTypeEdit=pfmTypeEdit, addTime=2018-05-16 12:05:46.0, usePfmTypeValue=210,209, isValid=1, caculateName=合同直计算提, updateOperator=测试人员, updateTime=2018-05-23 16:13:31.0, cityId=BSJ0101, operator=测试人员, companyId=BSJ01, cityName=昆明市, usePfm=商业出售,住房出售, id=98, operatorId=1, pfmTypeDel=pfmTypeDel}, {pfmTypeName=租赁业务, usePfmTypeName=商业出租,住房出租, caculateMethod=215, permissionArea=BSJ0103, pfmTypeEdit=pfmTypeEdit, addTime=2018-05-16 12:06:04.0, usePfmTypeValue=212,211, isValid=1, caculateName=合同直计算提, updateOperator=测试人员, updateTime=2018-05-23 16:13:37.0, cityId=BSJ0101, operator=测试人员, companyId=BSJ01, cityName=昆明市, usePfm=商业出租,住房出租, id=99, operatorId=1, pfmTypeDel=pfmTypeDel}, {pfmTypeName=金融业务, usePfmTypeName=金融服务, caculateMethod=216, permissionArea=BSJ0103, pfmTypeEdit=pfmTypeEdit, addTime=2018-05-16 18:39:15.0, usePfmTypeValue=213, isValid=1, caculateName=累进回溯计算, updateOperator=测试人员, updateTime=2018-05-23 15:32:07.0, cityId=BSJ0101, operator=测试人员, companyId=BSJ01, cityName=昆明市, usePfm=金融服务, id=102, operatorId=1, pfmTypeDel=pfmTypeDel}, {pfmTypeName=一手成交, usePfmTypeName=一手成交, caculateMethod=215, permissionArea=BSJ0103, pfmTypeEdit=pfmTypeEdit, addTime=2018-05-23 16:26:30.0, usePfmTypeValue=208, isValid=1, caculateName=合同直计算提, cityId=BSJ0102, operator=测试人员, companyId=BSJ01, cityName=大理市, usePfm=一手成交, id=115, operatorId=1, pfmTypeDel=pfmTypeDel}, {pfmTypeName=买卖业务, usePfmTypeName=住房出售,商业出售, caculateMethod=215, permissionArea=BSJ0103, pfmTypeEdit=pfmTypeEdit, addTime=2018-05-23 16:29:26.0, usePfmTypeValue=209,210, isValid=1, caculateName=合同直计算提, cityId=BSJ0102, operator=测试人员, companyId=BSJ01, cityName=大理市, usePfm=住房出售,商业出售, id=116, operatorId=1, pfmTypeDel=pfmTypeDel}, {pfmTypeName=租赁业务, usePfmTypeName=住房出租,商业出租, caculateMethod=215, permissionArea=BSJ0103, pfmTypeEdit=pfmTypeEdit, addTime=2018-05-23 16:29:45.0, usePfmTypeValue=211,212, isValid=1, caculateName=合同直计算提, cityId=BSJ0102, operator=测试人员, companyId=BSJ01, cityName=大理市, usePfm=住房出租,商业出租, id=117, operatorId=1, pfmTypeDel=pfmTypeDel}, {pfmTypeName=金融业务, usePfmTypeName=金融服务, caculateMethod=215, permissionArea=BSJ0103, pfmTypeEdit=pfmTypeEdit, addTime=2018-05-23 16:30:05.0, usePfmTypeValue=213, isValid=1, caculateName=合同直计算提, updateOperator=测试人员, updateTime=2018-05-23 16:30:36.0, cityId=BSJ0102, operator=测试人员, companyId=BSJ01, cityName=大理市, usePfm=金融服务, id=118, operatorId=1, pfmTypeDel=pfmTypeDel}]}  
	 * @throws
	 */
	public Page<Map<String, Object>> queryPfmTypeList(Map<String,Object> paraMap);
	/**
	 * 根据ID查询业绩类型数据
	 * @Title: queryPfmTypeDataById   
	 * @Description: 根据ID查询业绩类型数据 
	 * @param: pfmTypeId 业绩类型ID
	 * @return: Map<String,String>  
	 * {pfmTypeName=一手成交, usePfmTypeName=一手成交, caculateMethod=215, permissionArea=BSJ0103, pfmTypeEdit=pfmTypeEdit, addTime=2018-05-16 12:01:25.0, usePfmTypeValue=208, isValid=1, caculateName=合同直计算提, updateOperator=测试人员, updateTime=2018-05-24 10:28:06.0, cityId=BSJ0101, operator=测试人员, companyId=BSJ01, cityName=昆明市, usePfm=一手成交, id=97, operatorId=1, pfmTypeDel=pfmTypeDel}    
	 * @throws
	 */
	public Map<String,String> queryPfmTypeDataById(@Param("pfmTypeId") String pfmTypeId);
	/**
	 * 根据业绩类型名称查询业绩类型数据
	 * @Title: queryPfmTypeDataByName   
	 * @Description: 根据业绩类型名称查询业绩类型数据  
	 * @param: pfmTypeName 业绩类型名称
	 * @return: Map<String,String>   
	 * {pfmTypeName=一手成交, usePfmTypeName=一手成交, caculateMethod=215, permissionArea=BSJ0103, pfmTypeEdit=pfmTypeEdit, addTime=2018-05-16 12:01:25.0, usePfmTypeValue=208, isValid=1, caculateName=合同直计算提, updateOperator=测试人员, updateTime=2018-05-24 10:28:06.0, cityId=BSJ0101, operator=测试人员, companyId=BSJ01, cityName=昆明市, usePfm=一手成交, id=97, operatorId=1, pfmTypeDel=pfmTypeDel}    
	 * @throws
	 */
	public Map<String,String> queryPfmTypeDataByName(@Param("pfmTypeName") String pfmTypeName);
	/**
	 * 根据组装条件查询业绩类型数据
	 * @Title: queryPfmTypeDataByCondition   
	 * @Description: 根据组装条件查询业绩类型数据  
	 * @param: condition 组装条件（如 and a=1 and b = 0 and c = 2 ....)condition为空时传 null对象
	 * @return: Map<String,Object>      
	 * {pfmTypeName=一手成交, usePfmTypeName=一手成交, caculateMethod=215, permissionArea=BSJ0103, pfmTypeEdit=pfmTypeEdit, addTime=2018-05-16 12:01:25.0, usePfmTypeValue=208, isValid=1, caculateName=合同直计算提, updateOperator=测试人员, updateTime=2018-05-24 10:28:06.0, cityId=BSJ0101, operator=测试人员, companyId=BSJ01, cityName=昆明市, usePfm=一手成交, id=97, operatorId=1, pfmTypeDel=pfmTypeDel}    
	 * @throws
	 */
	public Map<String,Object> queryPfmTypeDataByCondition(@Param("condition") String condition);
	/**
	 * 根据公司ID获取配置明细数据
	 * @Title: queryPfmTypeDetailByCompanyId   
	 * @Description: 根据公司ID获取配置明细数据   
	 * @param: companyId 公司ID
	 * @return: List<Map<String,Object>>  
	 * [{permissionArea=BSJ0103, addTime=2018-05-22 14:25:11.0, commisionName=住房出售, isValid=1, updateOperator=测试人员, updateTime=2018-05-22 14:25:10.0, operator=测试人员, commisionRate=10, companyId=BSJ01, commisionType=1, fiexdBalance=0, id=126, operatorId=1}, {permissionArea=BSJ0103, addTime=2018-05-22 14:25:11.0, commisionName=商业出售, isValid=1, updateOperator=测试人员, updateTime=2018-05-22 14:25:10.0, operator=测试人员, commisionRate=12, companyId=BSJ01, commisionType=2, fiexdBalance=0, id=127, operatorId=1}, {permissionArea=BSJ0103, addTime=2018-05-22 14:25:11.0, commisionName=住房出租, isValid=1, updateOperator=测试人员, updateTime=2018-05-22 14:25:10.0, operator=测试人员, commisionRate=20, companyId=BSJ01, commisionType=3, fiexdBalance=0, id=128, operatorId=1}, {permissionArea=BSJ0103, addTime=2018-05-22 14:25:11.0, commisionName=商业出租, isValid=1, updateOperator=测试人员, updateTime=2018-05-22 14:25:10.0, operator=测试人员, commisionRate=0, companyId=BSJ01, commisionType=4, fiexdBalance=20000, id=129, operatorId=1}, {permissionArea=BSJ0103, addTime=2018-05-22 14:25:11.0, commisionName=一手成交, isValid=1, updateOperator=测试人员, updateTime=2018-05-22 14:25:10.0, operator=测试人员, commisionRate=0, companyId=BSJ01, commisionType=5, fiexdBalance=666, id=130, operatorId=1}]    
	 * @throws
	 */
	public List<Map<String,Object>> queryPfmTypeDetailByCompanyId(
			@Param("companyId") String companyId);
	/**
	 * 根据条件获取配置明细
	 * @Title: queryPfmTypeDetailByCondition
	 * @author: zuoyuntao  
	 * @Description:根据条件获取配置明细  
	 * @param rgCode 城市编码
	 * @param companyId 公司ID
	 * @return      
	 * List<Map<String,Object>> JSON 格式为：
	 * 	 * [{permissionArea=BSJ0103, addTime=2018-05-22 14:25:11.0, commisionName=住房出售, isValid=1, updateOperator=测试人员, updateTime=2018-05-22 14:25:10.0, operator=测试人员, commisionRate=10, companyId=BSJ01, commisionType=1, fiexdBalance=0, id=126, operatorId=1}, {permissionArea=BSJ0103, addTime=2018-05-22 14:25:11.0, commisionName=商业出售, isValid=1, updateOperator=测试人员, updateTime=2018-05-22 14:25:10.0, operator=测试人员, commisionRate=12, companyId=BSJ01, commisionType=2, fiexdBalance=0, id=127, operatorId=1}, {permissionArea=BSJ0103, addTime=2018-05-22 14:25:11.0, commisionName=住房出租, isValid=1, updateOperator=测试人员, updateTime=2018-05-22 14:25:10.0, operator=测试人员, commisionRate=20, companyId=BSJ01, commisionType=3, fiexdBalance=0, id=128, operatorId=1}, {permissionArea=BSJ0103, addTime=2018-05-22 14:25:11.0, commisionName=商业出租, isValid=1, updateOperator=测试人员, updateTime=2018-05-22 14:25:10.0, operator=测试人员, commisionRate=0, companyId=BSJ01, commisionType=4, fiexdBalance=20000, id=129, operatorId=1}, {permissionArea=BSJ0103, addTime=2018-05-22 14:25:11.0, commisionName=一手成交, isValid=1, updateOperator=测试人员, updateTime=2018-05-22 14:25:10.0, operator=测试人员, commisionRate=0, companyId=BSJ01, commisionType=5, fiexdBalance=666, id=130, operatorId=1}]    
	 * @throws
	 */
	public List<Map<String,Object>> queryPfmTypeDetailByCondition(
			@Param("rgCode") String condition,@Param("companyId")String companyId);
	/**
	 * 根据组装条件查询业绩类型数据
	 * @Title: queryPfmTypeDataByCondition   
	 * @Description: 根据组装条件查询业绩类型数据  
	 * @param: condition 组装条件（如 and a=1 and b = 0 and c = 2 ....)condition为空时传 null对象
	 * @return: Map<String,Object>      
	 * {pfmTypeName=一手成交, usePfmTypeName=一手成交, caculateMethod=215, permissionArea=BSJ0103, pfmTypeEdit=pfmTypeEdit, addTime=2018-05-16 12:01:25.0, usePfmTypeValue=208, isValid=1, caculateName=合同直计算提, updateOperator=测试人员, updateTime=2018-05-24 10:28:06.0, cityId=BSJ0101, operator=测试人员, companyId=BSJ01, cityName=昆明市, usePfm=一手成交, id=97, operatorId=1, pfmTypeDel=pfmTypeDel}    
	 * @throws
	 */
	public Page<Map<String,Object>> queryPfmTypeDataListByCondition(@Param("condition") String condition);
}
