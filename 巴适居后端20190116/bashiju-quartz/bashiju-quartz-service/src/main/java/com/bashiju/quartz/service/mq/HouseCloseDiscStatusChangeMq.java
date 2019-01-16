package com.bashiju.quartz.service.mq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.enums.LogBusinessOperateTypeEnum;
import com.bashiju.enums.LogBusinessSourceTypeEnum;
import com.bashiju.quartz.mapper.HouseAndCustomerCloseDiscStatusMapper;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.pojo.BusinessLogModel;
import com.bashiju.utils.service.BusinessLogService;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.util.SpringContextHolder;

@Service
public class HouseCloseDiscStatusChangeMq extends CommonSqlServie{
	@Autowired
	HouseAndCustomerCloseDiscStatusMapper closeDiscStatusMapper;
	
	Logger logger=LoggerFactory.getLogger(getClass());
	public void houseCloseDiscStatusAutoChange(String companyId) {

		try {
			List<Map<String, Object>> houseInfo = closeDiscStatusMapper.queryCloseDiscHouseInfo(companyId);
			updateHouseCloseDiscStatus(houseInfo, companyId);
		} catch (Exception e) {
			logger.error("二手房源自动解封盘出错，出错公司ID={},原因={}",companyId,e.getMessage());
			RabbitTemplate rabbitTemplate = (RabbitTemplate) SpringContextHolder.getBean("reRabbitTemplate");
			rabbitTemplate.convertAndSend("ERP-QUARTZ-DELAY-EXCHANGE","ERHOUSE-CLOSEDISC.CHANGE", companyId);
		}

	}
	/**
	 *  批量更新房源解封盘 
	 * @param list 房源信息列表
	 * @param companyId 公司id
	 * @author 赵斌
	 */
	private void updateHouseCloseDiscStatus(List<Map<String,Object>> list,String companyId) {
		
		if(list.size()>0)
		{
			String IdString ="";
			Boolean isStart=true;
			for(Map<String,Object> map:list) {
					if(!isStart) {
						IdString+=",";
					}
					IdString+="'"+map.get("id")+"'";
					saveLog(String.valueOf(map.get("houseId")),String.valueOf(map.get("id")), String.valueOf(map.get("companyId")),  String.valueOf(map.get("companyName")),
							 String.valueOf(map.get("departmentId")), String.valueOf(map.get("departmentName")),String.valueOf(map.get("cityCodes")));
					isStart=false;
				}
			Map<String,Object> map=new HashMap<>();
				map.put("closeDiscFlg",0);
				map.put("closePlanteEndDate",null);
				System.out.println("------------------------------->需要解封的房源ID="+IdString);
			this.batchcommonUpdate(map, "hs_secondhandhouse", "houseId", IdString,null);
		}
	
	}
	
	/**
	 * 保存到日志
	 * @Title: saveLog   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param sourceId
	 * @param: @param companyId
	 * @param: @param companyName
	 * @param: @param departmentId
	 * @param: @param departmentName
	 * @param: @param cityCode      
	 * @return: void      
	 * @throws
	 */
	private void saveLog(String sourceCode,String sourceId,String companyId,String companyName,String departmentId,String departmentName,String cityCode) {				
		BusinessLogService businessLogService=new BusinessLogService();
		BusinessLogModel businessLogModel=new BusinessLogModel();
		businessLogModel.setSourceId(sourceId);
		businessLogModel.setSourceCode(sourceCode);
		businessLogModel.setCompanyId(companyId);
		businessLogModel.setDepartmentId(departmentId);
		businessLogModel.setDepartmentName(departmentName);
		businessLogModel.setCompanyName(companyName);
		businessLogModel.setCityCode(cityCode);
		businessLogModel.setOperatTypeId(Integer.parseInt(LogBusinessOperateTypeEnum.HOUSE_SYSTEM_OPERATE.getCode()));
		businessLogModel.setSourceTypeId(Integer.parseInt(LogBusinessSourceTypeEnum.SYSTEM_OPERATE.getCode()));
		businessLogModel.setOperatorId(0);
		businessLogModel.setOperator("定时操作");
		businessLogModel.setLogContent("自动解封盘");
		businessLogService.houseLogQuery(businessLogModel);
}
}
