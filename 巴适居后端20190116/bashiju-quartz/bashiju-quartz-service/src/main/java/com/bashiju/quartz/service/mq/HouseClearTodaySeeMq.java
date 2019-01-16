package com.bashiju.quartz.service.mq;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.quartz.service.impl.ResetSourceService;
/**
 * 
 *   房源删除今日浏览
 * @ClassName:  HouseClearTodaySeeMq   
 * @Description:   房源删除今日浏览
 * @author: wangkaifa
 * @date:   2018年7月14日 上午10:47:21       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class HouseClearTodaySeeMq {
	@Autowired
	ResetSourceService resetSourceService;
	
	public void clearTodaySee(Map<String,Object> maps) {
		System.out.println("***************************删除房源今日浏览定时任务开始*********************************");
		try {
			resetSourceService.deleteTodaySee();
			resetSourceService.twoWeekShowedCount();// 更新房源最近15天带看数量
			resetSourceService.updateCompanyOpenCityStatus();//自动关闭过期的公司开通城市
		} catch (Exception e) {
			System.out.println("*************删除房源今日浏览定时任务异常:"+e.getMessage());
		}
		System.out.println("***************************删除房源今日浏览定时任务结束*********************************");
	}
}
