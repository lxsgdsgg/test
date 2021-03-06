/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CommunityPriceTrendMq.java   
 * @Package com.bashiju.quartz.service.mq   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年8月11日 下午6:26:25   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.quartz.service.mq;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.bashiju.quartz.mapper.CommunityPriceTrendMapper;

/**   
 * @ClassName:  CommunityPriceTrendMq   
 * @Description:统计小区每个月的价格走势(每个月的月末统计当月的价格走势)   
 * @author: yangz
 * @date:   2018年8月11日 下午6:26:25   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class CommunityPriceTrendMq {
	
	@Autowired
	private CommunityPriceTrendMapper communityPriceTrendMapper;

	/**
	 * @Description: 统计小区每个月的价格走势        
	 * @return: void
	 */
	public void staticesCommunityPriceTrend(Map<String,Object> maps) {
		//查询开通的城市，根据每个开通的城市去统计代替一次性统计，减小一次性统计的压力
		List<Map<String,String>> openCitys = communityPriceTrendMapper.queryOpenCityInfos();
		
		for(Map<String,String> map : openCitys) {
			if(!StringUtils.isEmpty(map.get("cityCode"))) {
				communityPriceTrendMapper.staticesCommunityPriceTrend(map.get("cityCode"));
			}
		}
		
	}
}
