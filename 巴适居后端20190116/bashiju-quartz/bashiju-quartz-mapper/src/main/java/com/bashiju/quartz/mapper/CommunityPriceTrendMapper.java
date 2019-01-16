/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CommunityPriceTrendMapper.java   
 * @Package com.bashiju.quartz.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年8月13日 下午4:02:57   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.quartz.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName:  CommunityPriceTrendMapper   
 * @Description:统计小区每个月的价格走势   
 * @author: yangz
 * @date:   2018年8月13日 下午4:02:57   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface CommunityPriceTrendMapper {

	/**     
	 * @Description: 查询已开通的城市信息   
	 * @return: List<Map<String,String>>      
	 */ 
	List<Map<String, String>> queryOpenCityInfos();

	/**     
	 * @Description: 根据城市统计价格走势
	 * @param cityCode 城市编号
	 * @return: int      
	 */ 
	int staticesCommunityPriceTrend(@Param("cityCode")String cityCode);

}
