package com.bashiju.quartz.service.mq;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.enums.DiscStatusEnum;
import com.bashiju.enums.HouseStatusEnum;
import com.bashiju.enums.LogBusinessOperateTypeEnum;
import com.bashiju.enums.LogBusinessSourceTypeEnum;
import com.bashiju.enums.TaoBaoStatusEnum;
import com.bashiju.quartz.mapper.HouseAndCustomerStatusTaskMapper;
import com.bashiju.utils.pojo.BusinessLogModel;
import com.bashiju.utils.service.BusinessLogService;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.util.SpringContextHolder;
import com.github.pagehelper.Page;

/**   房源定时任务
 * @ClassName  StatusChange   
 * @DescriptionTODO(这里用一句话描述这个类的作用)   
 * @author zhaobin
 * @date   2018年5月30日 下午2:43:37   
 *     
 * @Copyright 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@Service
public class HouseStatusChangeMq extends CommonSqlServie {
@Autowired
HouseAndCustomerStatusTaskMapper erhouseTaskMapper;

		Logger logger=LoggerFactory.getLogger(getClass());
		static final String yxCode=HouseStatusEnum.EFFECTIVE.getCode();
		static final String taobaoCode=TaoBaoStatusEnum.TAOBAO.getCode();
		static final String privateDiscCode=DiscStatusEnum.PRIVATEDISC.getCode();
		static final String publicDiscCode=DiscStatusEnum.PUBLICDISC.getCode();
		static final String taoBaoDesc=TaoBaoStatusEnum.TAOBAO.getDesc();
		static final String publicDiscDesc=DiscStatusEnum.PUBLICDISC.getDesc();

	public void houseAutoStatusChange(String companyId) {	

		try {
			List<Map<String, Object>> list = erhouseTaskMapper.getHouseReturnVisitConfig(companyId);
			if (list.isEmpty()) {
				System.out.println("公司代码为[" + companyId + "]的房源状态无需更新");
			} else {
				for (int i = 0; i < list.size(); i++) {

					String transactionType = list.get(i).get("transactionType").toString();
					String levelType = list.get(i).get("levelType").toString();
					/**有效私盘全员回访超时Id列表**/
					int privatehouseAllBackDay = Integer.parseInt(list.get(i).get("privateAllBack").toString())*2;

					List<Map<String, Object>> privatehouseAllBackIdlist = erhouseTaskMapper.getHouseAllFllowTimeOutInfo(
							privatehouseAllBackDay, transactionType, yxCode, taobaoCode, privateDiscCode, levelType,
							companyId);

					/**有效私盘维护人回访超时Id列表**/
					int privatehousemainterBackDay = Integer.parseInt(list.get(i).get("privateAdminBack").toString())*2;
					List<Map<String, Object>> privatehousemainterBackIdlist = erhouseTaskMapper
							.getHouseMainterFllowTimeOutInfo(privatehousemainterBackDay, transactionType, yxCode,
									taobaoCode, privateDiscCode, levelType, companyId);

					/**有效公盘回访全员超时Id列表**/
					int publicHousAllBackDay = Integer.parseInt(list.get(i).get("pubicAllBack").toString())*2
							+ privatehouseAllBackDay;
					List<Map<String, Object>> publicHousAllBackIdlist = erhouseTaskMapper.getHouseAllFllowTimeOutInfo(
							publicHousAllBackDay, transactionType, yxCode, taobaoCode, publicDiscCode, levelType,
							companyId);

					/**有效公盘回访维护人超时Id列表**/
					int publicHousMainterBackDay = Integer.parseInt(list.get(i).get("publicAdminBack").toString())*2
							+ privatehousemainterBackDay;
					List<Map<String, Object>> publicHousMainterBackIdlist = erhouseTaskMapper
							.getHouseMainterFllowTimeOutInfo(publicHousMainterBackDay, transactionType, yxCode,
									taobaoCode, publicDiscCode, levelType, companyId);

					/**有效私盘全员带看超时Id列表**/
					int privatehouseAllLookDay = Integer.parseInt(list.get(i).get("privateAllLook").toString())*2;
					List<Map<String, Object>> privatehouseAllLookIdlist = erhouseTaskMapper.getHouseAllLookTimeOutInfo(
							privatehouseAllLookDay, transactionType, yxCode, taobaoCode, privateDiscCode, levelType,
							companyId);

					/**有效私盘维护人带看超时Id列表**/
					int privatehouseMainterLookDay = Integer.parseInt(list.get(i).get("privateAdminLook").toString())*2;
					List<Map<String, Object>> privatehouseMainterLookIdlist = erhouseTaskMapper
							.getHouseMainterLookTimeOutInfo(privatehouseMainterLookDay, transactionType, yxCode,
									taobaoCode, privateDiscCode, levelType, companyId);

					/**有效公盘全员带看超时Id列表**/
					int publicHouseAllLookDay = Integer.parseInt(list.get(i).get("publicAllLook").toString())*2
							+ privatehouseAllLookDay;
					List<Map<String, Object>> publicHouseAllLookIdlist = erhouseTaskMapper.getHouseAllLookTimeOutInfo(
							publicHouseAllLookDay, transactionType, yxCode, taobaoCode, publicDiscCode, levelType,
							companyId);

					/**有效公盘维护人带看超时Id列表**/
					int publicHouseMainterLookDay = Integer.parseInt(list.get(i).get("publicAdminLook").toString())*2
							+ privatehouseMainterLookDay;
					List<Map<String, Object>> publicHouseMainterLookIdlist = erhouseTaskMapper
							.getHouseMainterLookTimeOutInfo(publicHouseMainterLookDay, transactionType, yxCode,
									taobaoCode, publicDiscCode, levelType, companyId);

					updatePrivateDiscHouseBackStatus(privatehouseAllBackIdlist, companyId, levelType,
							privatehouseAllBackDay, true);
					updatePrivateDiscHouseBackStatus(privatehousemainterBackIdlist, companyId, levelType,
							privatehousemainterBackDay, false);
					updatePrivateDiscHouseLookStatus(privatehouseAllLookIdlist, companyId, levelType,
							privatehouseAllLookDay, true);
					updatePrivateDiscHouseLookStatus(privatehouseMainterLookIdlist, companyId, levelType,
							privatehouseMainterLookDay, false);
					updatePulicDiscHouseBackStatus(publicHousAllBackIdlist, companyId, levelType,
							publicHousAllBackDay - privatehouseAllBackDay, true);
					updatePulicDiscHouseBackStatus(publicHousMainterBackIdlist, companyId, levelType,
							publicHousMainterBackDay - privatehousemainterBackDay, false);
					updatePulicDiscHouseLookStatus(publicHouseAllLookIdlist, companyId, levelType,
							publicHouseAllLookDay - privatehouseAllLookDay, true);
					updatePulicDiscHouseLookStatus(publicHouseMainterLookIdlist, companyId, levelType,
							publicHouseMainterLookDay - privatehouseMainterLookDay, false);
				}
			} 
		} catch (Exception e) {
			logger.error("二手房源状态改变出错，出错公司ID={},原因={}",companyId,e.getMessage());
			RabbitTemplate rabbitTemplate = (RabbitTemplate) SpringContextHolder.getBean("reRabbitTemplate");
			rabbitTemplate.convertAndSend("ERP-QUARTZ-DELAY-EXCHANGE","ERHOUSE-STATUS.CHANGE", companyId);
		}
	}
	/**
	 * 批量更新私盘回访超时状态 
	 * @Title updatePrivateDiscHouseBackStatus   
	 * @Description 批量更新私盘回访超时状态     
	 * @param  list 房源ID列表
	 * @param  levelType  房源等级
	 * @return void
	 */
	private void updatePrivateDiscHouseBackStatus(List<Map<String, Object>> list,String companyId,String levelType,int day,Boolean isAllBackTimeOut) {
		
		if(list.size()>0)
		{
			String IdString ="";
			Boolean isStart=true;
			for(Map<String,Object> map:list) {
					if(!isStart) {
						IdString+=",";
					}
					IdString+="'"+map.get("id")+"'";
					if(isAllBackTimeOut) {
					saveLog(String.valueOf(map.get("id")), String.valueOf(map.get("id")),String.valueOf(map.get("companyId")),  String.valueOf(map.get("companyName")),
							 String.valueOf(map.get("departmentId")), String.valueOf(map.get("departmentName")),String.valueOf(map.get("cityCodes")),"私盘进公盘("+levelType+"类客源):全员回访期限超过"+day+"天");
					}else {
						saveLog(String.valueOf(map.get("id")), String.valueOf(map.get("id")),String.valueOf(map.get("companyId")),  String.valueOf(map.get("companyName")),
								 String.valueOf(map.get("departmentId")), String.valueOf(map.get("departmentName")),String.valueOf(map.get("cityCodes")),"私盘进公盘("+levelType+"类客源):维护人回访期限超过"+day+"天");						
					}
					isStart=false;
				}
			Map<String,Object> map=new HashMap<>();
				map.put("discStatusId",publicDiscCode);
				map.put("discStatus",publicDiscDesc);
				System.out.println("------------------------------->私盘ID="+IdString);
			this.batchcommonUpdate(map, "hs_secondhandhouse", "Id", IdString,null);
		}
	
	}
	
	
	/**
	 * 批量更新私盘带看超时状态 
	 * @Title updatePrivateDiscHouseBackStatus   
	 * @Description 批量更新私盘回访超时状态     
	 * @param  list 房源ID列表
	 * @param  levelType  房源等级
	 * @return void
	 */
	private void updatePrivateDiscHouseLookStatus(List<Map<String, Object>> list,String companyId,String levelType,int day,Boolean isAllLookTimeOut) {
		
		if(list.size()>0)
		{			
			String IdString ="";
			Boolean isStart=true;
			for(Map<String,Object> map:list) {
					if(!isStart) {
						IdString+=",";
					}
					IdString+="'"+map.get("id")+"'";
					if(isAllLookTimeOut) {
					saveLog(String.valueOf(map.get("id")), String.valueOf(map.get("id")),String.valueOf(map.get("companyId")),  String.valueOf(map.get("companyName")),
							 String.valueOf(map.get("departmentId")), String.valueOf(map.get("departmentName")),String.valueOf(map.get("cityCodes")),"私盘进公盘("+levelType+"类客源):全员带看期限超过"+day+"天");
					}else {
						saveLog(String.valueOf(map.get("id")), String.valueOf(map.get("id")),String.valueOf(map.get("companyId")),  String.valueOf(map.get("companyName")),
								 String.valueOf(map.get("departmentId")), String.valueOf(map.get("departmentName")),String.valueOf(map.get("cityCodes")),"私盘进公盘("+levelType+"类客源):维护人带看期限超过"+day+"天");						
					}
					isStart=false;
				}
			Map<String,Object> map=new HashMap<>();
				map.put("discStatusId",publicDiscCode);
				map.put("discStatus",publicDiscDesc);
				System.out.println("------------------------------->私盘房源ID="+IdString);
			this.batchcommonUpdate(map, "hs_secondhandhouse", "id", IdString,null);
		}
	
	}
	
	/**
	 * 批量更新公盘房源回访超时状态
	 * @Title updateHouseBackStatus   
	 * @Description 批量更新公盘房源回访超时状态   
	 * @param  list 房源ID列表
	 * @param  levelType  房源等级
	 * @return void
	 */
	private void updatePulicDiscHouseBackStatus(List<Map<String, Object>> list,String companyId,String levelType,int day,Boolean isAllBackTimeOut) {
		
		if(list.size()>0)
		{
			String IdString ="";
			Boolean isStart=true;
			for(Map<String,Object> map:list) {
					if(!isStart) {
						IdString+=",";
					}
					IdString+="'"+map.get("id")+"'";
					if(isAllBackTimeOut) {
					saveLog(String.valueOf(map.get("id")), String.valueOf(map.get("id")),String.valueOf(map.get("companyId")),  String.valueOf(map.get("companyName")),
							 String.valueOf(map.get("departmentId")), String.valueOf(map.get("departmentName")),String.valueOf(map.get("cityCodes")),"公盘进淘宝池("+levelType+"类客源):全员回访期限超过"+day+"天");
					}else {
						saveLog(String.valueOf(map.get("id")), String.valueOf(map.get("id")),String.valueOf(map.get("companyId")),  String.valueOf(map.get("companyName")),
								 String.valueOf(map.get("departmentId")), String.valueOf(map.get("departmentName")),String.valueOf(map.get("cityCodes")),"公盘进淘宝池("+levelType+"类客源):维护人回访期限超过"+day+"天");						
					}
					isStart=false;
				}

			Map<String,Object> map=new HashMap<>();
				map.put("taoBaoStatusId", taobaoCode);
				map.put("taoBaoStatus",taoBaoDesc);
				System.out.println("------------------------------->公盘房源ID="+IdString);
			this.batchcommonUpdate(map, "hs_secondhandhouse", "id", IdString,null);
		}
	
	}
	
	/**
	 * 批量更新公盘房源回访超时状态
	 * @Title updateHouseBackStatus   
	 * @Description 批量更新公盘房源回访超时状态   
	 * @param  list 房源ID列表
	 * @param  levelType  房源等级
	 * @return void
	 */
	private void updatePulicDiscHouseLookStatus(List<Map<String, Object>> list,String companyId,String levelType,int day,Boolean isAllLookTimeOut) {
		
		if(list.size()>0)
		{
			String IdString ="";
			Boolean isStart=true;
			for(Map<String,Object> map:list) {
					if(!isStart) {
						IdString+=",";
					}
					IdString+="'"+map.get("id")+"'";
					if(isAllLookTimeOut) {
					saveLog(String.valueOf(map.get("id")), String.valueOf(map.get("id")),String.valueOf(map.get("companyId")),  String.valueOf(map.get("companyName")),
							 String.valueOf(map.get("departmentId")), String.valueOf(map.get("departmentName")),String.valueOf(map.get("cityCodes")),"公盘进淘宝池("+levelType+"类客源):全员带看期限超过"+day+"天");
					}else {
						saveLog(String.valueOf(map.get("id")), String.valueOf(map.get("id")),String.valueOf(map.get("companyId")),  String.valueOf(map.get("companyName")),
								 String.valueOf(map.get("departmentId")), String.valueOf(map.get("departmentName")),String.valueOf(map.get("cityCodes")),"公盘进淘宝池("+levelType+"类客源):维护人带看期限超过"+day+"天");						
					}
					isStart=false;
				}
				Map<String,Object> map=new HashMap<>();
					map.put("taoBaoStatusId", taobaoCode);
					map.put("taoBaoStatus",taoBaoDesc);
					System.out.println("------------------------------->公盘房源ID="+IdString);
				this.batchcommonUpdate(map,"hs_secondhandhouse", "id", IdString,null);

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
		private void saveLog(String sourceCode,String sourceId,String companyId,String companyName,String departmentId,String departmentName,String cityCode,String content) {				
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
				businessLogModel.setLogContent(content);
				businessLogService.houseLogQuery(businessLogModel);
	}

}
