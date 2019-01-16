/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  AgentCommunityDealCount.java   
 * @Package com.bashiju.quartz.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年8月13日 下午2:25:10   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.quartz.mapper;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName:  AgentCommunityDealCount   
 * @Description:经纪人小区成交记录 统计  
 * @author: wangpeng
 * @date:   2018年8月13日 下午2:25:10   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface AgentCommunityDealCountMapper {
	
	/**
	 * @Title: updateAgentCommunityDealCount   
	 * @Description: 更新经纪人小区成交记录统计
	 * @param cityCode 城市编码
	 * @return: void
	 */
	void updateAgentCommunityDealCount(@Param("cityCode")String cityCode);
}
