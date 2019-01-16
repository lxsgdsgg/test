package com.bashiju.quartz.service.mq;

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
import com.bashiju.enums.DeptTypeEnum;
import com.bashiju.enums.LogBusinessOperateTypeEnum;
import com.bashiju.enums.LogBusinessSourceTypeEnum;
import com.bashiju.enums.ResCustLevelConditionEnum;
import com.bashiju.quartz.mapper.HouseAndCustomerLevelTaskMapper;
import com.bashiju.utils.pojo.BusinessLogModel;
import com.bashiju.utils.service.BusinessLogService;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.util.SpringContextHolder;
import com.github.pagehelper.Page;

/**   客源定时任务
 * @ClassName  StatusChange   
 * @Description (这里用一句话描述这个类的作用)   
 * @author zhaobin
 * @date   2018年5月30日 下午2:43:37   
 *     
 * @Copyright 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@Service
public class CustomerLevelChangeMq extends CommonSqlServie {
@Autowired
HouseAndCustomerLevelTaskMapper customerTaskMapper;
	Logger logger=LoggerFactory.getLogger(getClass());
	static final String yxCode=CustomerStatusEnum.EFFECTIVE.getCode();
	boolean flag;
	public void customerAutoLevelChange(String companyId) {
		logger.warn("开始执行客源等级定时任务，公司ID是{}",companyId);
		try {
			//查询公司的城市列表
			String queryCityCondition = "companyId='" + companyId + "' and deptTypeId='"
					+ DeptTypeEnum.DEPT_CITY.getCode() + "'";
			Page<Map<String, Object>> cityCodeList = this.querySingleTable("areaCode", "sys_dept", queryCityCondition,
					1, 100);
			if (cityCodeList.get(0) == null) {
				System.out.println(new Date() + "公司代码为[" + companyId + "]下面没有找到城市");
				return;
			}
			for (Map<String, Object> map : cityCodeList) {
				flag = true;
				String cityCode = map.get("areaCode").toString();
				queryCustomerLevlConfig(companyId, cityCode);
			} 
		} catch (Exception e) {
			logger.error("客源等级改变出错，出错公司ID={},原因={}",companyId,e.getMessage());
			RabbitTemplate rabbitTemplate = (RabbitTemplate) SpringContextHolder.getBean("reRabbitTemplate");
			rabbitTemplate.convertAndSend("ERP-QUARTZ-DELAY-EXCHANGE","CUSTOMER-LEVEL.CHANGE", companyId);
		}
	}
	
	/**
	 * 获取客源等级配置并更新相关数据
	 */
	private void queryCustomerLevlConfig(String companyId,String cityCode) {
		List<Map<String,Object>> list=customerTaskMapper.queryCustomerLevlConfig(companyId, cityCode);
		if(list.isEmpty()) {
			System.out.println(new Date()+"公司代码为["+companyId+"]城市编码为["+cityCode+"]的客源等级无需更新");	
			return;
		} 
			for(int i=0;i<list.size();i++) {
				String conditions=String.valueOf(list.get(i).get("conditions"));
				boolean isRepeat=false,isBond=false,isUrgent=false,isCloseDisc=false,taobaoStatus=false;
				if(conditions.contains(ResCustLevelConditionEnum.LOOKBACK.getCode())) {
					isRepeat=true;
				}
				if(conditions.contains(ResCustLevelConditionEnum.DETERMINE.getCode())) {
					isBond=true;
				}
				if(conditions.contains(ResCustLevelConditionEnum.EAGER.getCode())) {
					isUrgent=true;
				}
				if(conditions.contains(ResCustLevelConditionEnum.SEALING.getCode())) {
					isCloseDisc=true;
				}
				if(conditions.contains(ResCustLevelConditionEnum.TAOBAOPOOL.getCode())) {
					taobaoStatus=true;
				}
				
				String transactionType=String.valueOf(list.get(i).get("transactionType"));
				String viewingCount=String.valueOf(list.get(i).get("lookCount"));
				String minPrice=String.valueOf(list.get(i).get("minPrice"));
				String maxPrice=String.valueOf(list.get(i).get("maxPrice"));
				String minSpace=String.valueOf(list.get(i).get("minSpace"));
				String maxSpace=String.valueOf(list.get(i).get("maxSpace"));
				String levelType=String.valueOf(list.get(i).get("levelType"));
				List<Map<String,Object>> customerABLevelIdlist=customerTaskMapper.queryCustomerABLevelInfo(transactionType, yxCode, companyId,cityCode,
						isRepeat, isBond, isUrgent, isCloseDisc, taobaoStatus, viewingCount, minPrice, maxPrice, minSpace, maxSpace,levelType);
				
				/*更新客源AB等级*/
				if(customerABLevelIdlist.size()>0)
				{
					String IdString ="";
					Boolean isStart=true;
					for(Map<String,Object> map:customerABLevelIdlist) {
						if(!isStart) {
							IdString+=",";
						}
						IdString+="'"+map.get("id")+"'";
						saveLog(String.valueOf(map.get("id")),String.valueOf(map.get("id")), String.valueOf(map.get("companyId")),  String.valueOf(map.get("companyName")),
								 String.valueOf(map.get("departmentId")), String.valueOf(map.get("departmentName")),String.valueOf(map.get("cityCodes")),levelType );
						isStart=false;
					}
					updateCustomerLevel(IdString,levelType);
			}
			
			/*非AB的更新客源C等级*/
			List<Map<String,Object>> customerCLevelList=queryCustomerLevelC(companyId,cityCode);
			if(customerCLevelList.size()>0)
			{
					String IdString ="";
					Boolean isStart=true;
					for(Map<String,Object> map:customerCLevelList) {
						if(!isStart) {
							IdString+=",";
						}
						IdString+="'"+map.get("id")+"'";
						saveLog(String.valueOf(map.get("id")),String.valueOf(map.get("id")), String.valueOf(map.get("companyId")),  String.valueOf(map.get("companyName")),
								 String.valueOf(map.get("departmentId")), String.valueOf(map.get("departmentName")),String.valueOf(map.get("cityCodes")),"C" );
						isStart=false;
					}
					updateCustomerLevel(IdString,"C");
			}
			
			/*更新非有效客源为D等级*/
			List<Map<String,Object>> customerDLevelList=queryCustomerLevelD(companyId,cityCode);
			if(customerDLevelList.size()>0)
			{
				String IdString ="";
				Boolean isStart=true;
				for(Map<String,Object> map:customerDLevelList) {
						if(!isStart) {
							IdString+=",";
						}
						IdString+="'"+map.get("id")+"'";
						saveLog(String.valueOf(map.get("id")),String.valueOf(map.get("id")), String.valueOf(map.get("companyId")),  String.valueOf(map.get("companyName")),
								 String.valueOf(map.get("departmentId")), String.valueOf(map.get("departmentName")),String.valueOf(map.get("cityCodes")),"D" );
						isStart=false;
					}
				updateCustomerLevel(IdString,"D");
			}
		}
	}
	
	private List<Map<String,Object>> queryCustomerLevelC(String companyId,String cityCode) {
		return this.customerTaskMapper.queryCustomerCLevelInfo(companyId,cityCode,yxCode);
	}
	
	private List<Map<String,Object>> queryCustomerLevelD(String companyId,String cityCode) {
		return this.customerTaskMapper.queryCustomerDLevelInfo(companyId,cityCode,yxCode);
	}
	
	/**
	 * 更新客源等级
	 * @param customerIdString 客源id 字符串
	 * @param companyId 公司id
	 * @param cityCode 城市编码
	 * @param levelType 客源等级
	 */
	private void updateCustomerLevel(String customerIdString,String levelType) {
		

			Map<String,Object> map=new HashMap<>();
				map.put("levelType",levelType);
				System.out.println("需要变更为【"+levelType+"】等级的客源需求ID="+customerIdString);
			this.batchcommonUpdate(map, "demand_customerbusinessinfo", "id", customerIdString,null);
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
		private void saveLog(String sourceId,String sourceCode,String companyId,String companyName,String departmentId,String departmentName,String cityCode,String levelType) {				
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
				businessLogModel.setLogContent("客源等级为调整"+levelType+"类客源");
				businessLogService.customerLogQuery(businessLogModel);
		}
	}
