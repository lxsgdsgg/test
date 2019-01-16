package com.bashiju.quartz.service.mq;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.quartz.service.impl.ResetSourceService;
/**
 * 
 *   房源自动取消置顶
 * @ClassName:  HouseCancelSetTopMq   
 * @Description:    房源自动取消置顶
 * @author: wangkaifa
 * @date:   2018年7月14日 上午10:51:32       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class HouseCancelSetTopMq {
	@Autowired
	ResetSourceService resetSourceService;
	
	public void houseCancelSetTop(Map<String,Object> maps) {
		System.out.println("***************************取消房源置顶定时任务开始*********************************");
		try {
			resetSourceService.resetTopStatus();
		} catch (Exception e) {
			System.out.println("取消房源置顶异常:"+e.getMessage());
		}
		System.out.println("***************************取消房源置顶定时任务结束*********************************");
	}
}
