/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  StatisticsMqMapper.java   
 * @Package com.bashiju.quartz.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年10月22日 上午10:43:24   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.quartz.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName:  StatisticsMqMapper   
 * @Description:统计任务持久层   
 * @author: yangz
 * @date:   2018年10月22日 上午10:43:24   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface StatisticsMqMapper {

	/**     
	 * @Description: 查询公司信息(公司编号，公司名称)   
	 * @return: List<Map<String,Object>>      
	 */ 
	List<Map<String, Object>> queryCompanys();
	
	/**
	 * 查询平台开通城市信息(城市编号，城市名称)  
	 * @Description: 查询平台开通城市信息(城市编号，城市名称)  
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String,Object>> queryCityOpenInfo();

	/**
	 * 统计经纪人小区成交信息
	 * @Description: 统计经纪人小区成交信息   
	 * @param companyId 公司编号(选填)
	 * @return: void
	 */
	void statisticsAgentCommunityDeal(@Param("companyId")String companyId);

	/**     
	 * @Description:统计小区户型信息        
	 * @param cityCode 城市编号(必填)
	 * @return: void      
	 */ 
	void statisticsCommunityRoomType(@Param("cityCode")String cityCode);
	
	/**
	 * 当执行了统计小区户型的方法后，及时将在售/在租房源更新到小区查询表中
	 * @Description: 当执行了统计小区户型的方法后，及时将在售/在租房源更新到小区查询表中
	 * @param cityCode 城市编号(必填)     
	 * @return: void
	 */
	void updateClientCommunityHouseCnt(@Param("cityCode")String cityCode);
	
	/**
	 * 更新经纪人的统计信息
	 * @Description: 更新经纪人的统计信息  
	 * @param companyId   公司编号(必填)   
	 * @return: void
	 */
	void updateAgentDetailsQueryInfo(@Param("companyId")String companyId);
	
	

}
