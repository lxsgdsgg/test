package com.bashiju.quartz.service.mq;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.quartz.service.impl.ResetSourceService;
/**
 * 
 *   房源、客源自动撤单
 * @ClassName:  HouseAndCustomerCancelNoticeMq   
 * @Description:   房源、客源自动撤单
 * @author: wangkaifa
 * @date:   2018年7月14日 上午10:53:18       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class HouseAndCustomerCancelNoticeMq {
	@Autowired
	ResetSourceService resetSourceService;
	
	public void cancelNotice(Map<String,Object> maps) {
		System.out.println("***************************房源客源自动撤单定时任务开始*********************************");
		try {
			resetSourceService.houseCancelNotice();//房源撤单
			resetSourceService.buyCustomerCancelNotice();//求购撤单
			resetSourceService.rentCustomerCancelNotice();//求租撤单
		} catch (Exception e) {
			System.out.println("房源客源自动撤单异常:"+e.getMessage());
		}
		System.out.println("***************************房源客源自动撤单定时任务结束*********************************");
	}
}
