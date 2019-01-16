/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  StatisticsCommunityRoomTypeMq.java   
 * @Package com.bashiju.quartz.service.mq   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年10月22日 上午10:04:28   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.quartz.service.mq;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.quartz.mapper.StatisticsMqMapper;

/**   
 * @ClassName:  StatisticsCommunityRoomTypeMq   
 * @Description:统计小区户型信息   
 * @author: yangz
 * @date:   2018年10月22日 上午10:04:28   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class StatisticsCommunityRoomTypeMq {

	@Autowired
	private StatisticsMqMapper statisticsMqMapper;
	/**
	 * 
	 * @Description: 操作方法         
	 * @return: void
	 */
	public void run(Map<String,Object> map) {
		List<Map<String,Object>> citys = statisticsMqMapper.queryCityOpenInfo();
		for(Map<String,Object> city : citys) {
			statisticsMqMapper.statisticsCommunityRoomType(city.get("cityCode").toString());
			statisticsMqMapper.updateClientCommunityHouseCnt(city.get("cityCode").toString());
		}
	}
}
