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

import com.bashiju.enums.DeptTypeEnum;
import com.bashiju.enums.HouseStatusEnum;
import com.bashiju.enums.LogBusinessOperateTypeEnum;
import com.bashiju.enums.LogBusinessSourceTypeEnum;
import com.bashiju.enums.ResHouseLevelConditionEnum;
import com.bashiju.quartz.mapper.HouseAndCustomerLevelTaskMapper;
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
public class HouseLevelChangeMq extends CommonSqlServie {
@Autowired
HouseAndCustomerLevelTaskMapper houseTaskMapper;
	
		static final String yxCode=HouseStatusEnum.EFFECTIVE.getCode();
		boolean flag;
		Logger logger=LoggerFactory.getLogger(getClass());
		
		public void houseAutolevelChange(String companyId) {

			try {
				//查询公司的城市列表
				String queryCityCondition = "companyId='" + companyId + "' and deptTypeId='"
						+ DeptTypeEnum.DEPT_CITY.getCode() + "'";
				Page<Map<String, Object>> cityCodeList = this.querySingleTable("areaCode", "sys_dept",
						queryCityCondition, 1, 100);
				if (cityCodeList.get(0) == null) {
					System.out.println(new Date() + "公司代码为[" + companyId + "]下面没有找到城市");
					return;
				}
				for (Map<String, Object> map : cityCodeList) {
					flag = true;
					String cityCode = map.get("areaCode").toString();
					queryHouseLevlConfig(companyId, cityCode);
				} 
			} catch (Exception e) {
				logger.error("二手房源等级改变出错，出错公司ID={},原因={}",companyId,e.getMessage());
				RabbitTemplate rabbitTemplate = (RabbitTemplate) SpringContextHolder.getBean("reRabbitTemplate");
				rabbitTemplate.convertAndSend("ERP-QUARTZ-DELAY-EXCHANGE","ERHOUSE-LEVEL.CHANGE", companyId);
			}
	
		}
		
		/**
		 * 获取房源等级配置并更新相关数据
		 */
		private void queryHouseLevlConfig(String companyId,String cityCode) {
			List<Map<String,Object>> list=houseTaskMapper.queryHouseLevlConfig(companyId, cityCode);
			if(list.isEmpty()) {
				System.out.println(new Date()+"公司代码为["+companyId+"]城市编码为["+cityCode+"]的房源等级无需更新");	
				return;
			} 
				for(int i=0;i<list.size();i++) {
					String conditions=String.valueOf(list.get(i).get("conditions"));
					boolean taobaoStatus=false,isPicture=false,isKeys=false,isUnique=false,
							closeDiscFlg=false,isUrgent=false,isSatisfyTwoYear=false,isOnly=false;
					if(conditions.contains(ResHouseLevelConditionEnum.TAOBAOPOOL.getCode())) {
						taobaoStatus=true;
					}
					if(conditions.contains(ResHouseLevelConditionEnum.PHOTO.getCode())) {
						isPicture=true;
					}
					if(conditions.contains(ResHouseLevelConditionEnum.KEY.getCode())) {
						isKeys=true;
					}
					if(conditions.contains(ResHouseLevelConditionEnum.SOLE.getCode())) {
						isUnique=true;
					}
					if(conditions.contains(ResHouseLevelConditionEnum.SEALING.getCode())) {
						closeDiscFlg=true;
					}
					if(conditions.contains(ResHouseLevelConditionEnum.EAGER.getCode())) {
						isUrgent=true;
					}
					if(conditions.contains(ResHouseLevelConditionEnum.FORTWOYEARS.getCode())) {
						isSatisfyTwoYear=true;
					}
					if(conditions.contains(ResHouseLevelConditionEnum.ONLYHOUSING.getCode())) {
						isOnly=true;
					}
					
					String transactionTypeId=String.valueOf(list.get(i).get("transactionType"));
					String propertyType=String.valueOf(list.get(i).get("propertyType"));
					String decoratingType=String.valueOf(list.get(i).get("decoratingType"));
					String houseUses=String.valueOf(list.get(i).get("houseUses"));
					String matchingId=String.valueOf(list.get(i).get("matchingId"));
					String sellingMinPrice=String.valueOf(list.get(i).get("minPrice"));
					String sellingMaxPrice=String.valueOf(list.get(i).get("maxPrice"));
					String buildMinSpace=String.valueOf(list.get(i).get("minSpace"));
					String buildMaxSpace=String.valueOf(list.get(i).get("maxSpace"));
					String levelType=String.valueOf(list.get(i).get("levelType"));
					String payTypeId=String.valueOf(list.get(i).get("payTypeId"));
					String areaCode=String.valueOf(list.get(i).get("areaCode"));
					String regionId=String.valueOf(list.get(i).get("regionId"));
					/*拼接需求区域的查询条件*/
					StringBuilder areaCodeString=new StringBuilder(); 
					if(!areaCode.isEmpty()) {
						//sbdemand.append("1!=1 ");
						String[] areaCodes=String.valueOf(list.get(i).get("areaCode")).split(",");
						Boolean isStart=true;
						for(String res:areaCodes) {
						/*	if(res.contains("area-")) {
								sbdemand.append(" or ")
										.append("b.areaCode")
										.append("=")
										.append(res.replace("area-", "'"))
										.append("'");
							}if(res.contains("reg-")) {
								sbdemand.append(" or ")
								.append("b.regionId")
								.append("=")
								.append(res.replace("reg-", "'"))
								.append("'");
							}if(res.contains("com-")) {
								sbdemand.append(" or ")
								.append("b.communityId")
								.append("=")
								.append(res.replace("com-", "'"))
								.append("'");
							}*/
							if(!isStart) {
								areaCodeString.append(" or ");
							}
							areaCodeString.append(" b.areaCode='"+res+"' ");
							isStart=false;
						}
					}
					/*拼接多个片区的查询条件*/
					StringBuilder regionIdString=new StringBuilder(); 
					if(!StringUtils.isEmpty(regionId)) {
						String[] regionIds=regionId.split(",");
						Boolean isStart=true;
						for(String res:regionIds) {
							if(!isStart) {
								regionIdString.append(" or ");
							}
							regionIdString.append("b.regionId='"+res+"'");
							isStart=false;
						}						
					}
					/*拼接配套的查询条件*/
					StringBuilder matchingIdString=new StringBuilder(); 
					if(!StringUtils.isEmpty(matchingId)) {
						String[] matching=matchingId.split(",");
						Boolean isStart=true;
						for(String res:matching) {
							if(!isStart) {
								matchingIdString.append(" and ");
							}
							matchingIdString.append("b.matchingId='"+res+"'");
							isStart=false;
						}						
					}
					
					List<Map<String,Object>> houseABLevelIdlist=houseTaskMapper.queryHouseABLevelInfo(transactionTypeId, yxCode, companyId, cityCode, String.valueOf(areaCodeString), String.valueOf(regionIdString),taobaoStatus, isPicture, isKeys, isUnique, closeDiscFlg, isUrgent, isSatisfyTwoYear, isOnly, propertyType, decoratingType, houseUses, payTypeId, sellingMinPrice, sellingMaxPrice, buildMinSpace, buildMaxSpace, String.valueOf(matchingIdString),levelType);
					System.out.println("-----------houseABLevelIdlist="+houseABLevelIdlist);
						/*更新房源AB等级*/
					if(houseABLevelIdlist.size()>0)
					{

						String IdString ="";
						Boolean isStart=true;
						for(Map<String,Object> map:houseABLevelIdlist) {
							if(!isStart) {
								IdString+=",";
							}
							IdString+="'"+map.get("id")+"'";
							saveLog(String.valueOf(map.get("houseId")),String.valueOf(map.get("id")), String.valueOf(map.get("companyId")),  String.valueOf(map.get("companyName")),
									 String.valueOf(map.get("departmentId")), String.valueOf(map.get("departmentName")),String.valueOf(map.get("cityCodes")),levelType );
							isStart=false;
						}
						updateHouseLevel(IdString,levelType);
					}
				}
				
				/*非AB的更新房源C等级*/
				List<Map<String,Object>> houseCLevelList=gethouseLevelC(companyId,cityCode);
				if(houseCLevelList.size()>0)
				{String IdString ="";
				Boolean isStart=true;
				for(Map<String,Object> map:houseCLevelList) {
					if(!isStart) {
						IdString+=",";
					}
					IdString+="'"+map.get("id")+"'";
					saveLog(String.valueOf(map.get("houseId")),String.valueOf(map.get("id")), String.valueOf(map.get("companyId")),  String.valueOf(map.get("companyName")),
							 String.valueOf(map.get("departmentId")), String.valueOf(map.get("departmentName")),String.valueOf(map.get("cityCodes")),"C" );
					isStart=false;
				}
				updateHouseLevel(IdString,"C");
				}
				
				/*更新非有效房源为D等级*/
				List<Map<String,Object>> houseDLevelList=gethouseLevelD(companyId,cityCode);
				if(houseDLevelList.size()>0)
				{String IdString ="";
				Boolean isStart=true;
				for(Map<String,Object> map:houseDLevelList) {
					if(!isStart) {
						IdString+=",";
					}
					IdString+="'"+map.get("id")+"'";
					saveLog(String.valueOf(map.get("houseId")),String.valueOf(map.get("id")), String.valueOf(map.get("companyId")),  String.valueOf(map.get("companyName")),
							 String.valueOf(map.get("departmentId")), String.valueOf(map.get("departmentName")),String.valueOf(map.get("cityCodes")),"D" );
					isStart=false;
				}
				updateHouseLevel(IdString,"D");
				}
		}

	
	private List<Map<String,Object>> gethouseLevelC(String companyId,String cityCode) {
		return this.houseTaskMapper.queryHouseCLevelInfo(companyId,cityCode,yxCode);
	}
	
	private List<Map<String,Object>> gethouseLevelD(String companyId,String cityCode) {
		return this.houseTaskMapper.queryHouseDLevelInfo(companyId,cityCode,yxCode);
	}
	
	/**
	 * 更新房源等级
	 * @param houseIdString 房源id 字符串
	 * @param companyId 公司id
	 * @param cityCode 城市编码
	 * @param levelType 房源等级
	 */
	private void updateHouseLevel(String houseIdString,String levelType) {
		
	
			Map<String,Object> map=new HashMap<>();
				map.put("levelType",levelType);
				System.out.println("需要变更为【"+levelType+"】等级的房源需求ID="+houseIdString);
			this.batchcommonUpdate(map, "hs_secondhandhouse", "id", houseIdString,null);
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
		private void saveLog(String sourceCode,String sourceId,String companyId,String companyName,String departmentId,String departmentName,String cityCode,String levelType) {				
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
				businessLogModel.setLogContent("房源等级为调整"+levelType+"类房源");
				businessLogService.houseLogQuery(businessLogModel);
		}
		
}