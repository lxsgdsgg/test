/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  StatisticsSaleHouseMapper.java   
 * @Package com.bashiju.anal.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年11月21日 上午9:43:51   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.anal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName:  StatisticsBuyDemandMapper   
 * @Description:求租统计  
 * @author: yangz
 * @date:   2018年11月21日 上午9:43:51   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface StatisticsRentDemandMapper {

	/**
	 * 查询经纪公司开通城市行政区域   
	 * @Description: 查询经纪公司开通城市行政区域   
	 * @param companyId 公司编号
	 * @param cityCode 已开通的城市编号
	 * @return: List<Map<String,String>>
	 */
	public List<Map<String,String>> queryCityArea(@Param("companyId")String companyId,@Param("cityCode")String cityCode);
	
	/**
	 * 查询客户来源信息
	 * @Description: 查询客户来源信息   
	 * @return: List<Map<String,String>>
	 */
	public List<Map<String,String>> queryResourceType();
	
	/**
	 * 查询城市出售平均价格区间信息 
	 * @Description: 查询城市出售平均价格区间信息 
	 * @param cityCode 已开通的城市编号
	 * @return: List<Map<String,String>>
	 */
	public List<Map<String,String>> queryCityavgpricelabel(@Param("cityCode")String cityCode);

	/**     
	 * 查询数据
	 * @Description: 查询数据   
	 * @param transverse 横向维度(必选)
	 * @param longitudinal 纵向维度(必选)
	 * @param companyId 公司编号(必选)
	 * @param cityCode 城市编号(必选)
	 * @param cell_sql 分组维度sql(必选)
	 * @param deptId 部门编号(可选)
	 * @param beginRecordTime 起始录入日期(可选)
	 * @param endRecordTime 截止录入日期(可选)
	 * @param beginMainterFllowTime 起始维护日期(可选)
	 * @param endMainterFllowTime 截止维护日期(可选)
	 * @param houseUseId 房源用途编号参数组(当 transverse 为房源用途时有效)
	 * @param houseUse 房源用途参数组(当 transverse 为房源用途时有效)
	 * @return: List<Map<String,Object>>      
	 */ 
	public List<Map<String, Object>> getData(@Param("transverse")String transverse,@Param("longitudinal")String longitudinal,@Param("companyId")String companyId,@Param("cityCode")String cityCode, @Param("cell_sql")String cell_sql, @Param("deptId")String deptId,
			@Param("beginRecordTime")String beginRecordTime, @Param("endRecordTime")String endRecordTime, 
			@Param("beginMainterFllowTime")String beginMainterFllowTime, @Param("endMainterFllowTime")String endMainterFllowTime,@Param("houseUseId")String houseUseId,@Param("houseUse")String houseUse);
}
