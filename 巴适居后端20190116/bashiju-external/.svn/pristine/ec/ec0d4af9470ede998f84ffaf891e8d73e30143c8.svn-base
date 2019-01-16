package com.bashiju.ext.service.mq;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bashiju.ext.mapper.LogOperateMapper;
import com.bashiju.utils.pojo.PlatformLogModel;


@Service
public class LogManageConsumerMq {
	@Autowired
	LogOperateMapper logOperateMapper;
	@Autowired
	RabbitTemplate rabbitTemplate;
	Logger logger=LoggerFactory.getLogger(this.getClass());
	
	public void auxAddLog(PlatformLogModel platformLogModel) {
		String jsonString=null;
		String routKey=null;
		try {
			routKey=platformLogModel.getRoutKey();
			jsonString=JSON.toJSONString(platformLogModel);
			logger.warn("【管理平台新增】操作日志传入的对象platformLogModel，值是：{}",jsonString);
			Map<String,Object> map=JSON.parseObject(jsonString,Map.class);
				if (map.get("originalData")!=null) {
					map.remove("originalData");
				}
				if (map.get("newData")!=null) {
					map.remove("newData");
				}
				if (map.get("labelData")!=null) {
					map.remove("labelData");
				}
				map.put("permissionArea", platformLogModel.getDepartmentId());
				map.put("addTime", new Date());
				map.put("updateTime", new Date());
				map.put("isValid",1);
				insertData(map);
		} catch (Exception e) {
			logger.error("【管理平台】消息队列添加【新增操作】日志出错,值是{}，原因是：{}",jsonString,e.getMessage());
			rabbitTemplate.convertAndSend("ERP-EXT-DELAY-EXCHANGE",routKey, platformLogModel);
		}
	}

	public void auxUpdateLog(PlatformLogModel platformLogModel) {
		String jsonString=null;
		String routKey=null;
		try {
			routKey=platformLogModel.getRoutKey();
			jsonString=JSON.toJSONString(platformLogModel);
			logger.warn("【管理平台修改】操作日志传入的对象platformLogModel，值是：{}",jsonString);
			Map<String,Object> map=JSON.parseObject(jsonString,Map.class);
				Map originalData=(Map) map.get("originalData");
				Map newData=(Map) map.get("newData");
				Map labelData=(Map) map.get("labelData");
				Set<String> keys=labelData.keySet();
				if (keys!=null&&keys.size()>0) {
					StringBuffer log=new StringBuffer();
					for (String key : keys) {
						if (originalData.get(key)==null) {
							originalData.put(key, "");
						}
						if (newData.get(key)==null) {
							newData.put(key, "");
						}
						if (!originalData.get(key).toString().trim().equals(newData.get(key).toString().trim())) {
							Map label = (Map) labelData.get(key);
							if (label.get("label")!=null&&!"".equals(label.get("label"))) {
								log.append(label.get("label")+":");
								log.append(originalData.get(key).toString());
								log.append("->");
								log.append(newData.get(key)==null?"":newData.get(key).toString());
								log.append(";");
							}
						}
						
					}
					map.remove("originalData");
					map.remove("newData");
					map.remove("labelData");
					map.put("permissionArea", platformLogModel.getDepartmentId());
					map.put("addTime", new Date());
					map.put("updateTime", new Date());
					map.put("isValid",1);
					map.put("logContent", log.toString());
					if (StringUtils.isNotEmpty(log.toString()) ) {
						insertData(map);
					}
				}		
				
		} catch (Exception e) {
			logger.error("【管理平台】消息队列添加【更新操作】日志出错,值是{}，原因是：{}",jsonString,e.getMessage());
			rabbitTemplate.convertAndSend("ERP-EXT-DELAY-EXCHANGE",routKey, platformLogModel);
		}
	}
	
	public void auxDeleteLog(PlatformLogModel platformLogModel) {
		String jsonString=null;
		String routKey=null;
		try {
			routKey=platformLogModel.getRoutKey();
			jsonString=JSON.toJSONString(platformLogModel);
			logger.warn("【管理平台删除】操作日志传入的对象platformLogModel，值是：{}",jsonString);
			Map<String,Object> map=JSON.parseObject(jsonString,Map.class);
				if (map.get("originalData")!=null) {
					map.remove("originalData");
				}
				if (map.get("newData")!=null) {
					map.remove("newData");
				}
				if (map.get("labelData")!=null) {
					map.remove("labelData");
				}
				map.put("permissionArea", platformLogModel.getDepartmentId());
				map.put("addTime", new Date());
				map.put("updateTime", new Date());
				map.put("isValid",1);
				insertData(map);
		} catch (Exception e) {
			logger.error("【管理平台】消息队列添加【删除操作】日志出错,值是{}，原因是：{}",jsonString,e.getMessage());
			rabbitTemplate.convertAndSend("ERP-EXT-DELAY-EXCHANGE",routKey, platformLogModel);
		}
	}
	
	public void auxQueryLog(PlatformLogModel platformLogModel) {
		String jsonString=null;
		String routKey=null;
		try {
			routKey=platformLogModel.getRoutKey();
			jsonString=JSON.toJSONString(platformLogModel);
			logger.warn("【管理平台查询】操作日志传入的对象platformLogModel，值是：{}",jsonString);
			Map<String,Object> map=JSON.parseObject(jsonString,Map.class);
				if (map.get("originalData")!=null) {
					map.remove("originalData");
				}
				if (map.get("newData")!=null) {
					map.remove("newData");
				}
				if (map.get("labelData")!=null) {
					map.remove("labelData");
				}
				map.put("permissionArea", platformLogModel.getDepartmentId());
				map.put("addTime", new Date());
				map.put("updateTime", new Date());
				map.put("isValid",1);
				insertData(map);
		} catch (Exception e) {
			logger.error("【管理平台】消息队列添加【查询操作】日志出错,值是{}，原因是：{}",jsonString,e.getMessage());
			rabbitTemplate.convertAndSend("ERP-EXT-DELAY-EXCHANGE",routKey, platformLogModel);
		}
	}
	/**
	 * 插入数据库
	 */
	private void insertData(Map<String,Object> map) {
		if(map.containsKey("routKey")) {
			map.remove("routKey");
		}
		Map<String,Object> paramMap=new HashMap<>();
		paramMap.put("tableName", "sys_platform_log");
		paramMap.put("fields", map);
		paramMap.put("id", "");
		try {
			logOperateMapper.commonAdd(paramMap);
		} catch (Exception e) {
			logger.error("【管理平台】消息队列日志【添加到数据库】出错,值是{}，原因是：{}",map,e.getMessage());
		}
	}
}
