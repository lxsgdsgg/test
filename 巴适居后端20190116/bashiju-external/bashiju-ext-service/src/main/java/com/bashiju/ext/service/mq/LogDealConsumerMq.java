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
import com.bashiju.enums.LogBusinessOperateTypeEnum;
import com.bashiju.enums.LogBusinessSourceTypeEnum;
import com.bashiju.enums.LogBusinessTypeEnum;
import com.bashiju.enums.ReportAnalysisTypeEnum;
import com.bashiju.enums.ReportBusinessTypeEnum;
import com.bashiju.ext.mapper.LogOperateMapper;
import com.bashiju.utils.pojo.BusinessLogModel;


@Service
public class LogDealConsumerMq {
	@Autowired
	LogOperateMapper logOperateMapper;
	@Autowired
	RabbitTemplate rabbitTemplate;
	Logger logger=LoggerFactory.getLogger(this.getClass());
	
	public void auxAddLog(BusinessLogModel businessLogModel) {
		String jsonString=null;
		String routKey=null;
		try {
			routKey=businessLogModel.getRoutKey();
			jsonString=JSON.toJSONString(businessLogModel);
			logger.warn("【成交新增】操作日志传入的对象businessLogModel，值是：{}",jsonString);
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
				if (map.get("applicant")!=null) {
					map.remove("applicant");
				}
				if (map.get("newStatus")!=null) {
					map.remove("newStatus");
				}
				map.put("permissionArea", businessLogModel.getDepartmentId());
				if (map.get("addTime")==null||"".equals(map.get("addTime").toString())) {
					map.put("addTime", new Date());
					map.put("updateTime", new Date());
				}
				map.put("isValid",1);
				insertData(map);
				insertReport(businessLogModel);
		} catch (Exception e) {
			logger.error("【成交】消息队列添加【新增操作】日志出错,值是{}，原因是：{}",jsonString,e.getMessage());
			rabbitTemplate.convertAndSend("ERP-EXT-DELAY-EXCHANGE",routKey, businessLogModel);
		}
	}

	public void auxUpdateLog(BusinessLogModel businessLogModel) {
		String jsonString=null;
		String routKey=null;
		try {
			routKey=businessLogModel.getRoutKey();
			jsonString=JSON.toJSONString(businessLogModel);
			logger.warn("【成交更新】操作日志传入的对象businessLogModel，值是：{}",jsonString);
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
					map.put("permissionArea", businessLogModel.getDepartmentId());
					if (map.get("addTime")==null||"".equals(map.get("addTime").toString())) {
						map.put("addTime", new Date());
						map.put("updateTime", new Date());
					}
					map.put("isValid",1);
					map.put("logContent", log.toString());
					if (map.get("applicant")!=null) {
						map.remove("applicant");
					}
					if (map.get("newStatus")!=null) {
						map.remove("newStatus");
					}
					if (StringUtils.isNotEmpty(log.toString()) ) {
						insertData(map);
					}					
				}		
				
		} catch (Exception e) {
			logger.error("【成交】消息队列添加【更新操作】日志出错,值是{}，原因是：{}",jsonString,e.getMessage());
			rabbitTemplate.convertAndSend("ERP-EXT-DELAY-EXCHANGE",routKey, businessLogModel);
		}
	}
	
	public void auxDeleteLog(BusinessLogModel businessLogModel) {
		
		String jsonString=null;
		String routKey=null;
		try {
			routKey=businessLogModel.getRoutKey();
			jsonString=JSON.toJSONString(businessLogModel);
			logger.warn("【成交删除】操作日志传入的对象businessLogModel，值是：{}",jsonString);
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
				if (map.get("applicant")!=null) {
					map.remove("applicant");
				}
				if (map.get("newStatus")!=null) {
					map.remove("newStatus");
				}
				map.put("permissionArea", businessLogModel.getDepartmentId());
				if (map.get("addTime")==null||"".equals(map.get("addTime").toString())) {
					map.put("addTime", new Date());
					map.put("updateTime", new Date());
				}
				map.put("isValid",1);
				insertData(map);
				insertReport(businessLogModel);
		} catch (Exception e) {
			logger.error("【成交】消息队列添加【删除操作】日志出错,值是{}，原因是：{}",jsonString,e.getMessage());
			rabbitTemplate.convertAndSend("ERP-EXT-DELAY-EXCHANGE",routKey, businessLogModel);
		}
	}
	
	public void auxQueryLog(BusinessLogModel businessLogModel) {
		
		String jsonString=null;
		String routKey=null;
		try {
			routKey=businessLogModel.getRoutKey();
			jsonString=JSON.toJSONString(businessLogModel);
			logger.warn("【成交查询】操作日志传入的对象businessLogModel，值是：{}",jsonString);
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
				if (map.get("applicant")!=null) {
					map.remove("applicant");
				}
				if (map.get("newStatus")!=null) {
					map.remove("newStatus");
				}
				map.put("permissionArea", businessLogModel.getDepartmentId());
				if (map.get("addTime")==null||"".equals(map.get("addTime").toString())) {
					map.put("addTime", new Date());
					map.put("updateTime", new Date());
				}
				map.put("isValid",1);
				insertData(map);
				insertReport(businessLogModel);
		} catch (Exception e) {
			logger.error("【成交】消息队列添加【查询操作】日志出错,值是{}，原因是：{}",jsonString,e.getMessage());
			rabbitTemplate.convertAndSend("ERP-EXT-DELAY-EXCHANGE",routKey, businessLogModel);
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
		paramMap.put("tableName", "sys_business_log");
		paramMap.put("fields", map);
		paramMap.put("id", "");
		try {
			logOperateMapper.commonAdd(paramMap);
		} catch (Exception e) {
			logger.error("【成交】消息队列日志【添加到数据库】出错,值是{}，原因是：{}",map,e.getMessage());
		}
	}
	
	private void insertReport(BusinessLogModel model) {
		LogBusinessSourceTypeEnum sourceTypeEnum=LogBusinessSourceTypeEnum.parseOf(model.getSourceTypeId()+"");
		LogBusinessOperateTypeEnum operateTypeEnum=LogBusinessOperateTypeEnum.parseOf(model.getOperatTypeId()+"");
		LogBusinessTypeEnum businessTypeEnum=LogBusinessTypeEnum.parseOf(model.getBusinessTypeId()+"");
		
		Map<String,Object> map=new HashMap<>();	
		map.put("sourceId", model.getSourceId());
		map.put("sourceCode", model.getSourceCode());
		map.put("cityCode", model.getCityCode());
		map.put("companyId", model.getCompanyId());
		map.put("companyName", model.getCompanyName());
		map.put("departmentId", model.getDepartmentId());
		map.put("departmentName", model.getDepartmentName());
		map.put("operatorId", model.getOperatorId());
		map.put("operator", model.getOperator());
		map.put("permissionArea", model.getDepartmentId());
		if (StringUtils.isNotEmpty(model.getAddTime())) {
			map.put("addTime", model.getAddTime());
			map.put("updateTime", model.getAddTime());
		}
		map.put("isValid",1);	
			
		//成交
		if (LogBusinessTypeEnum.DEAL.equals(businessTypeEnum)) {
			if (LogBusinessOperateTypeEnum.ADD_DEAL.equals(operateTypeEnum)) {
				map.put("sourceTypeId",Integer.parseInt(ReportBusinessTypeEnum.DEAL.getCode()));
				map.put("sourceTypeName",ReportBusinessTypeEnum.DEAL.getDesc());
				if (LogBusinessSourceTypeEnum.NEW_DEAL.equals(sourceTypeEnum)) {
					map.put("operatTypeId",Integer.parseInt(ReportAnalysisTypeEnum.DEAL_NEWHOUSE.getCode()));
					map.put("operatTypeName",ReportAnalysisTypeEnum.DEAL_NEWHOUSE.getDesc());
				}
				if (LogBusinessSourceTypeEnum.SELL_DEAL.equals(sourceTypeEnum)) {
					map.put("operatTypeId",Integer.parseInt(ReportAnalysisTypeEnum.DEAL_SELL.getCode()));
					map.put("operatTypeName",ReportAnalysisTypeEnum.DEAL_SELL.getDesc());
				}
				if (LogBusinessSourceTypeEnum.RENT_DEAL.equals(sourceTypeEnum)) {
					map.put("operatTypeId",Integer.parseInt(ReportAnalysisTypeEnum.DEAL_RENT.getCode()));
					map.put("operatTypeName",ReportAnalysisTypeEnum.DEAL_RENT.getDesc());
				}
			}
		}
		
		if (map.get("operatTypeId")!=null) {
			rabbitTemplate.convertAndSend("REPORT.OPERATION.ADD", map);
			/*Map<String,Object> paramMap=new HashMap<>();
			paramMap.put("tableName", "report_operation_analysis");
			paramMap.put("fields", map);
			paramMap.put("id", "");
			try {
				logOperateMapper.commonAdd(paramMap);
			} catch (Exception e) {
				logger.error("【成交】消息队列日志【添加到数据表report_operation_analysis】出错,值是{}，原因是：{}",map,e.getMessage());
			}*/
		}
	}

}
