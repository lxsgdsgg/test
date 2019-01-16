package com.bashiju.quartz.service.mq;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.enums.CustomerStatusEnum;
import com.bashiju.enums.DiscStatusEnum;
import com.bashiju.enums.LogBusinessOperateTypeEnum;
import com.bashiju.enums.LogBusinessSourceTypeEnum;
import com.bashiju.enums.TaoBaoStatusEnum;
import com.bashiju.quartz.mapper.HouseAndCustomerStatusTaskMapper;
import com.bashiju.utils.pojo.BusinessLogModel;
import com.bashiju.utils.service.BusinessLogService;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.util.SpringContextHolder;
import com.github.pagehelper.Page;

/**   客源定时任务
 * @ClassName  StatusChange   
 * @DescriptionTODO(这里用一句话描述这个类的作用)   
 * @author zhaobin
 * @date   2018年5月30日 下午2:43:37   
 *     
 * @Copyright 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@Service
public class CustomerStatusChangeMq extends CommonSqlServie {
@Autowired
HouseAndCustomerStatusTaskMapper customerTaskMapper;

		static final String yxCode=CustomerStatusEnum.EFFECTIVE.getCode();
		static final String taobaoCode=TaoBaoStatusEnum.TAOBAO.getCode();
		static final String privateDiscCode=DiscStatusEnum.PRIVATEDISC.getCode();
		static final String publicDiscCode=DiscStatusEnum.PUBLICDISC.getCode();
		static final String taoBaoDesc=TaoBaoStatusEnum.TAOBAO.getDesc();
		static final String publicDiscDesc=DiscStatusEnum.PUBLICDISC.getDesc();
		Logger logger=LoggerFactory.getLogger(getClass());
		
	public void customerAutoStatusChange(String companyId) {	
		try {
			List<Map<String, Object>> list = customerTaskMapper.getCustomerReturnVisitConfig(companyId);
			if (list.isEmpty()) {
				System.out.println("公司代码为[" + companyId + "]的客源状态无需更新");
			} else {
				for (int i = 0; i < list.size(); i++) {

					String transactionType = list.get(i).get("transactionType").toString();
					String levelType = list.get(i).get("levelType").toString();
					/**有效私客全员回访超时Id列表**/
					int privateCustomerAllBackDay = Integer.parseInt(list.get(i).get("privateAllBack").toString())*2;

					List<Map<String, Object>> privateCustomerAllBackIdlist = customerTaskMapper
							.getCustomerAllFllowTimeOutInfo(privateCustomerAllBackDay, transactionType, yxCode,
									taobaoCode, privateDiscCode, levelType, companyId);

					/**有效私客维护人回访超时Id列表**/
					int privateCustomermainterBackDay = Integer
							.parseInt(list.get(i).get("privateAdminBack").toString())*2;
					List<Map<String, Object>> privateCustomermainterBackIdlist = customerTaskMapper
							.getCustomerMainterFllowTimeOutInfo(privateCustomermainterBackDay, transactionType, yxCode,
									taobaoCode, privateDiscCode, levelType, companyId);

					/**有效公客回访全员超时Id列表**/
					int publicHousAllBackDay = Integer.parseInt(list.get(i).get("pubicAllBack").toString())*2
							+ privateCustomerAllBackDay;
					List<Map<String, Object>> publicHousAllBackIdlist = customerTaskMapper
							.getCustomerAllFllowTimeOutInfo(publicHousAllBackDay, transactionType, yxCode, taobaoCode,
									publicDiscCode, levelType, companyId);

					/**有效公客回访维护人超时Id列表**/
					int publicHousMainterBackDay = Integer.parseInt(list.get(i).get("publicAdminBack").toString())*2
							+ privateCustomermainterBackDay;
					List<Map<String, Object>> publicHousMainterBackIdlist = customerTaskMapper
							.getCustomerMainterFllowTimeOutInfo(publicHousMainterBackDay, transactionType, yxCode,
									taobaoCode, publicDiscCode, levelType, companyId);

					/**有效私客全员带看超时Id列表**/
					int privateCustomerAllLookDay = Integer.parseInt(list.get(i).get("privateAllLook").toString())*2;
					List<Map<String, Object>> privateCustomerAllLookIdlist = customerTaskMapper
							.getCustomerAllLookTimeOutInfo(privateCustomerAllLookDay, transactionType, yxCode,
									taobaoCode, privateDiscCode, levelType, companyId);

					/**有效私客维护人带看超时Id列表**/
					int privateCustomerMainterLookDay = Integer
							.parseInt(list.get(i).get("privateAdminLook").toString())*2;
					List<Map<String, Object>> privateCustomerMainterLookIdlist = customerTaskMapper
							.getCustomerMainterLookTimeOutInfo(privateCustomerMainterLookDay, transactionType, yxCode,
									taobaoCode, privateDiscCode, levelType, companyId);

					/**有效公客全员带看超时Id列表**/
					int publicCustomerAllLookDay = Integer.parseInt(list.get(i).get("publicAllLook").toString())*2
							+ privateCustomerAllLookDay;
					List<Map<String, Object>> publicCustomerAllLookIdlist = customerTaskMapper
							.getCustomerAllLookTimeOutInfo(publicCustomerAllLookDay, transactionType, yxCode,
									taobaoCode, publicDiscCode, levelType, companyId);

					/**有效公客维护人带看超时Id列表**/
					int publicCustomerMainterLookDay = Integer.parseInt(list.get(i).get("publicAdminLook").toString())*2
							+ privateCustomerMainterLookDay;
					List<Map<String, Object>> publicCustomerMainterLookIdlist = customerTaskMapper
							.getCustomerMainterLookTimeOutInfo(publicCustomerMainterLookDay, transactionType, yxCode,
									taobaoCode, publicDiscCode, levelType, companyId);

					updatePrivateDiscCustomerBackStatus(privateCustomerAllBackIdlist, companyId, levelType,
							privateCustomerAllBackDay, true);
					updatePrivateDiscCustomerBackStatus(privateCustomermainterBackIdlist, companyId, levelType,
							privateCustomermainterBackDay, false);
					updatePrivateDiscCustomerLookStatus(privateCustomerAllLookIdlist, companyId, levelType,
							privateCustomerAllLookDay, true);
					updatePrivateDiscCustomerLookStatus(privateCustomerMainterLookIdlist, companyId, levelType,
							privateCustomerMainterLookDay, false);
					updatePulicDiscCustomerBackStatus(publicHousAllBackIdlist, companyId, levelType,
							publicHousAllBackDay - privateCustomerAllBackDay, true);
					updatePulicDiscCustomerBackStatus(publicHousMainterBackIdlist, companyId, levelType,
							publicHousMainterBackDay - privateCustomermainterBackDay, false);
					updatePulicDiscCustomerLookStatus(publicCustomerAllLookIdlist, companyId, levelType,
							publicCustomerAllLookDay - privateCustomerAllLookDay, true);
					updatePulicDiscCustomerLookStatus(publicCustomerMainterLookIdlist, companyId, levelType,
							publicCustomerMainterLookDay - privateCustomerMainterLookDay, false);
				}
			} 
		} catch (Exception e) {
			logger.error("客源状态改变出错，出错公司ID={},原因={}",companyId,e.getMessage());
			RabbitTemplate rabbitTemplate = (RabbitTemplate) SpringContextHolder.getBean("reRabbitTemplate");
			rabbitTemplate.convertAndSend("ERP-QUARTZ-DELAY-EXCHANGE","CUSTOMER-STATUS.CHANGE", companyId);
		}
	}
	/**
	 * 批量更新私客回访超时状态 
	 * @Title updatePrivateDiscCustomerBackStatus   
	 * @Description 批量更新私客回访超时状态     
	 * @param  list 客源ID列表
	 * @param  levelType  客源等级
	 * @return void
	 */
	private void updatePrivateDiscCustomerBackStatus(List<Map<String,Object>> list,String companyId,String levelType,int day,Boolean isAllBackTimeOut) {
		
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
							 String.valueOf(map.get("departmentId")), String.valueOf(map.get("departmentName")),String.valueOf(map.get("cityCodes")),"私客进公客("+levelType+"类客源):全员回访期限超过"+day+"天");
					}else {
						saveLog(String.valueOf(map.get("id")), String.valueOf(map.get("id")),String.valueOf(map.get("companyId")),  String.valueOf(map.get("companyName")),
								 String.valueOf(map.get("departmentId")), String.valueOf(map.get("departmentName")),String.valueOf(map.get("cityCodes")),"私客进公客("+levelType+"类客源):维护人回访期限超过"+day+"天");						
					}
					isStart=false;
				}
			Map<String,Object> map=new HashMap<>();
				map.put("discStatusId",publicDiscCode);
				map.put("discStatus",publicDiscDesc);
				System.out.println("------------------------------->私客ID="+IdString);
			this.batchcommonUpdate(map, "demand_customerbusinessinfo", "id", IdString,null);
		}
	
	}
	
	
	/**
	 * 批量更新私客带看超时状态 
	 * @Title updatePrivateDiscCustomerBackStatus   
	 * @Description 批量更新私客回访超时状态     
	 * @param  list 客源ID列表
	 * @param  levelType  客源等级
	 * @return void
	 */
	private void updatePrivateDiscCustomerLookStatus(List<Map<String,Object>> list,String companyId,String levelType,int day,Boolean isAllLookTimeOut) {
		
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
							 String.valueOf(map.get("departmentId")), String.valueOf(map.get("departmentName")),String.valueOf(map.get("cityCodes")),"私客进公客("+levelType+"类客源):全员带看期限超过"+day+"天");
					}else {
						saveLog(String.valueOf(map.get("id")), String.valueOf(map.get("id")),String.valueOf(map.get("companyId")),  String.valueOf(map.get("companyName")),
								 String.valueOf(map.get("departmentId")), String.valueOf(map.get("departmentName")),String.valueOf(map.get("cityCodes")),"私客进公客("+levelType+"类客源):维护人带看期限超过"+day+"天");						
					}
					isStart=false;
				}
			Map<String,Object> map=new HashMap<>();
				map.put("discStatusId",publicDiscCode);
				map.put("discStatus",publicDiscDesc);
				System.out.println("------------------------------->私客客源ID="+IdString);
			this.batchcommonUpdate(map, "demand_customerbusinessinfo", "id", IdString,null);
		}
	
	}
	
	/**
	 * 批量更新公客客源回访超时状态
	 * @Title updateCustomerBackStatus   
	 * @Description 批量更新公客客源回访超时状态   
	 * @param  list 客源ID列表
	 * @param  levelType  客源等级
	 * @return void
	 */
	private void updatePulicDiscCustomerBackStatus(List<Map<String,Object>> list,String companyId,String levelType,int day,Boolean isAllBackTimeOut) {
		
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
							 String.valueOf(map.get("departmentId")), String.valueOf(map.get("departmentName")),String.valueOf(map.get("cityCodes")),"公客进淘宝池("+levelType+"类客源):全员回访期限超过"+day+"天");
					}else {
						saveLog(String.valueOf(map.get("id")), String.valueOf(map.get("id")),String.valueOf(map.get("companyId")),  String.valueOf(map.get("companyName")),
								 String.valueOf(map.get("departmentId")), String.valueOf(map.get("departmentName")),String.valueOf(map.get("cityCodes")),"公客进淘宝池("+levelType+"类客源):维护人回访期限超过"+day+"天");						
					}
					isStart=false;
				}

			Map<String,Object> map=new HashMap<>();
				map.put("taoBaoStatusId", taobaoCode);
				map.put("taoBaoStatus",taoBaoDesc);
				System.out.println("------------------------------->公客客源ID="+IdString);
			this.batchcommonUpdate(map, "demand_customerbusinessinfo", "id", IdString,null);
		}
	
	}
	
	/**
	 * 批量更新公客客源回访超时状态
	 * @Title updateCustomerBackStatus   
	 * @Description 批量更新公客客源回访超时状态   
	 * @param  list 客源ID列表
	 * @param  levelType  客源等级
	 * @return void
	 */
	private void updatePulicDiscCustomerLookStatus(List<Map<String,Object>> list,String companyId,String levelType,int day,Boolean isAllLookTimeOut) {
		
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
							 String.valueOf(map.get("departmentId")), String.valueOf(map.get("departmentName")),String.valueOf(map.get("cityCodes")),"公客进淘宝池("+levelType+"类客源):全员带看期限超过"+day+"天");
					}else {
						saveLog(String.valueOf(map.get("id")), String.valueOf(map.get("id")),String.valueOf(map.get("companyId")),  String.valueOf(map.get("companyName")),
								 String.valueOf(map.get("departmentId")), String.valueOf(map.get("departmentName")),String.valueOf(map.get("cityCodes")),"公客进淘宝池("+levelType+"类客源):维护人带看期限超过"+day+"天");						
					}
					isStart=false;
				}
				Map<String,Object> map=new HashMap<>();
					map.put("taoBaoStatusId", taobaoCode);
					map.put("taoBaoStatus",taoBaoDesc);
					System.out.println("------------------------------->公客客源ID="+IdString);
				this.batchcommonUpdate(map,"demand_customerbusinessinfo", "id", IdString,null);

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
		private void saveLog(String sourceId,String sourceCode,String companyId,String companyName,String departmentId,String departmentName,String cityCode,String content) {				
				BusinessLogService businessLogService=new BusinessLogService();
				BusinessLogModel businessLogModel=new BusinessLogModel();
				businessLogModel.setSourceId(sourceId);
				businessLogModel.setSourceCode(sourceCode);
				businessLogModel.setCompanyId(companyId);
				businessLogModel.setDepartmentId(departmentId);
				businessLogModel.setDepartmentName(departmentName);
				businessLogModel.setCompanyName(companyName);
				businessLogModel.setCityCode(cityCode);
				businessLogModel.setOperatTypeId(Integer.parseInt(LogBusinessOperateTypeEnum.CUSTOMER_SYSTEM_OPERATE.getCode()));
				businessLogModel.setSourceTypeId(Integer.parseInt(LogBusinessSourceTypeEnum.SYSTEM_OPERATE.getCode()));
				businessLogModel.setOperatorId(0);
				businessLogModel.setOperator("定时操作");
				businessLogModel.setLogContent(content);
				businessLogService.customerLogQuery(businessLogModel);
		}

}
