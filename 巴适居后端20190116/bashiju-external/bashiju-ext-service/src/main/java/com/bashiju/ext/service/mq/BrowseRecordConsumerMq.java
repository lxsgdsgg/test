package com.bashiju.ext.service.mq;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bashiju.utils.service.CommonSqlServie;
import com.github.pagehelper.Page;

@Service
public class BrowseRecordConsumerMq {
	Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CommonSqlServie commonSqlServie;
	
	public void addHouseBrowseRecord(HashMap map) {
		try {
			if (map!=null) {
				String condition="shhId="+map.get("shhId")+" and operatorId="+map.get("operatorId");
				Page<Map<String, Object>> page=commonSqlServie.querySingleTable("id", "hs_todaySee", condition, 1, 10);
				if (page.getTotal()==0) {				
					commonSqlServie.idCustomOperationDatabase(map, "hs_todaySee", false);
				}
			}
		} catch (Exception e) {
			logger.error("【房源浏览】消息队列添加【今日浏览】记录出错,值是{}，原因是：{}",JSON.toJSONString(map),e.getMessage());
		}
	}
	
	public void addCustomerBrowseRecord(HashMap map) {
		try {
			if (map!=null) {
				String condition="demandId='"+map.get("demandId")+"' and operatorId="+map.get("operatorId")+" and addTime like '"+LocalDate.now().toString()+"%'";
				Page<Map<String, Object>> page=commonSqlServie.querySingleTable("id", "demand_browseRecord", condition, 1, 10);
				if (page.getTotal()==0) {					
					commonSqlServie.idCustomOperationDatabase(map, "demand_browseRecord", false);
				}
			}
		} catch (Exception e) {
			logger.error("【客源浏览】消息队列添加【今日浏览】记录出错,值是{}，原因是：{}",JSON.toJSONString(map),e.getMessage());
		}
	}
}
