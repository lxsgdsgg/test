/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  DivideintoServiceApiImpl.java   
 * @Package com.bashiju.manage.api   
 * @Description:分成管理-分成计算公共服务实现类
 * @author: zuoyuntao     
 * @date:   2018年6月23日 下午3:59:26   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.common.service.manageservice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.api.DivideintoServiceApi;
import com.bashiju.common.global.BaseGlobal;
import com.bashiju.common.mapper.DivideIntoConfigMapper;
import com.bashiju.enums.AgentTypeEnum;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.github.pagehelper.util.StringUtil;

/**
 * 分成管理-分成计算公共服务实现类
 * @ClassName:DivideintoServiceApiImpl
 * @Description:分成管理-分成计算公共服务实现类
 * @author:zuoyuntao
 * @date:2018年6月23日 下午3:59:26
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
@SystemServiceLog(sourceType="分成计算")
public class DivideintoServiceApiImpl implements DivideintoServiceApi {
	/**
	 * 分成配置数据库操作接口
	 */
	@Autowired
	private DivideIntoConfigMapper mDivideIntoConfigMapper;
	
	/**
	 * 通过传入条件计算分成
	 * @Title: caculateDivideIntoByConditions
	 * @author: zuoyuntao  
	 * @Description:通过传入条件计算分成
	 * @param totalDivideintoMoney 房源总的分成金额
	 * @param configType 配置类型（00出售01出租02一手成交）
	 * @param dealType 处理状态（1住宅 2 商业）
	 * @param companyId 公司ID
	 * @param rgCode 行政区划      
	 * @param agentTypeMap 经纪人信息集合（agentType，agentId）      
	 * void JSON 格式为：{}     
	 */
	@Override
	@SystemServiceLog(operationType="计算分成")
	public List<Map<String,Object>> caculateDivideIntoByConditions(
			String totalDivideintoMoney,String configType
			,String dealType,String rgCode,String companyId,Map<String,Object> agentTypeMap) {
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("configType", configType);
		paraMap.put("dealType", dealType);
		paraMap.put("cityId", rgCode);
		paraMap.put("configStatus", "1");
		paraMap.put("companyId", companyId);
		String condition = buildFindSql(paraMap);
		//添加权限
		List<Map<String,Object>> divideintoList = new ArrayList<Map<String,Object>>();
		ExecutionResult.descFormat(rgCode, "查询分成配置");
		divideintoList = mDivideIntoConfigMapper.queryDivideInfoConfByCondition(condition);
		if(divideintoList.size() > 1) {
			throw new BusinessException("根据条件找到多条分成配置，请检查数据");
		}
		if(divideintoList.size() == 0) {
			throw new BusinessException("根据条件未找到分成配置，请检查数据");
		}
		//会发生分成转移的角色
		//houseEntrustTrans 7:委托人 keyMgrTrans 8:拿钥匙人 signUkTrans 9:签独家人 picUploadTrans 10:照片上传人
		Map<String,Object> oneMap = divideintoList.get(0);
		buildDivideInfo(divideintoList.get(0),agentTypeMap);
		List<Map<String, Object>> retList = caculateDivideinfo(oneMap,totalDivideintoMoney,agentTypeMap);
		return retList;
	}
	/**
	 * 将分成比例换算成为金额
	 * @Title: caculateDivideinfo
	 * @author: zuoyuntao  
	 * @Description:将分成比例换算成为金额  
	 * @param paraMap 组装好的分成比例对象
	 * @param totalMoneyStr 总的分成金额
	 * void JSON 格式为：{}     
	 * @throws
	 */
	private List<Map<String,Object>> caculateDivideinfo(Map<String,Object> paraMap
			,String totalMoneyStr,Map<String,Object> agentTypeMap) {
		BigDecimal totalMoney = new BigDecimal(totalMoneyStr);
		Iterator<String> it = paraMap.keySet().iterator();
		List<Map<String,Object>> retList = new ArrayList<Map<String,Object>>();
		while(it.hasNext()) {
			Map<String,Object> retMap = new HashMap<String,Object>();
			String key = it.next();
			if(key.indexOf("Trans") != -1) {
				continue;
			}
			String value = String.valueOf(paraMap.get(key));
			if(StringUtil.isEmpty(value)) {
				continue;
			}
			if("null".equals(value)) {
				continue;
			}
			if("0".equals(value)) {
				continue;
			}
			BigDecimal temp = new BigDecimal(value);
			//estimateProfit -- 业绩(应收金额) dividReason--分成类型 dividRate -- 分成利率
			retMap.put("estimateProfit", totalMoney.multiply(temp)
					.divide(new BigDecimal("100")));//金额:除以100表示除以百分比
			retMap.put("dividRate", value);
			retMap.put("dividReason", BaseGlobal.ACTION_ROLE_MAP_MAPPER.get(key));
			retMap.put("agentId", agentTypeMap.get(
					BaseGlobal.ACTION_ROLE_MAP_MAPPER.get(key)));
			retList.add(retMap);
		}
		return retList;
	}
	
	/**
	 * 计算每个角色的分成比例
	 * @Title: buildDivideInfo
	 * @author: zuoyuntao  
	 * @Description:计算每个角色的分成比例
	 * @param paraMap 分成配置数据
	 * @param agentMap 经纪人信息
	 * void JSON 格式为：{}     
	 * @throws
	 */
	private void buildDivideInfo(Map<String,Object> paraMap,Map<String,Object> agentMap){
		//得到转移对象 --如果存在值则表示存在分成转移
		int houseEntrustTrans = Integer.parseInt(String.valueOf(paraMap.get("houseEntrustTrans")));
		int keyMgrTrans = Integer.parseInt(String.valueOf(paraMap.get("keyMgrTrans")));
		int signUkTrans = Integer.parseInt(String.valueOf(paraMap.get("signUkTrans")));
		int picUploadTrans = Integer.parseInt(String.valueOf(paraMap.get("picUploadTrans")));
		
		//得到被转移对象数的值
		//房源委托人比例
		Object actHouseEntrustMgrRate = paraMap.get(BaseGlobal.ACTION_ROLE_MAP_MAPPER.get(
				AgentTypeEnum.HOUSEENTRUSTER.getCode()));
		//拿钥匙人比例
		Object actRoleKeyMgrRate = paraMap.get(BaseGlobal.ACTION_ROLE_MAP_MAPPER.get(
				AgentTypeEnum.RECIVEKEYER.getCode()));
		//签独家人比例
		Object actRoleSignUkRate = paraMap.get(BaseGlobal.ACTION_ROLE_MAP_MAPPER.get(
				AgentTypeEnum.EXCLUSIVER.getCode()));
		//传照片人比例
		Object actRolePivUploadRate = paraMap.get(BaseGlobal.ACTION_ROLE_MAP_MAPPER.get(
				AgentTypeEnum.PICTURER.getCode()));
		//如果委托人转移不为0并且没用该经纪人类型
		if(houseEntrustTrans != 0 && !agentMap.containsKey(String.valueOf(
				AgentTypeEnum.HOUSEENTRUSTER.getCode()))) {
			String oriName = (String) BaseGlobal.ACTION_ROLE_MAP_MAPPER.get(
					AgentTypeEnum.HOUSEENTRUSTER.getCode());
			resetValueBuild(paraMap, String.valueOf(houseEntrustTrans), actHouseEntrustMgrRate,oriName);
		}
		//如果拿钥匙人转移不为0并且没用该经纪人类型
		if(keyMgrTrans != 0 && !agentMap.containsKey(String.valueOf(
				AgentTypeEnum.RECIVEKEYER.getCode()))) {
			String oriName = (String) BaseGlobal.ACTION_ROLE_MAP_MAPPER.get(
					AgentTypeEnum.RECIVEKEYER.getCode());
			resetValueBuild(paraMap, String.valueOf(keyMgrTrans), actRoleKeyMgrRate,oriName);
		}
		//如果签独家人转移不为0并且没用该经纪人类型
		if(signUkTrans != 0 && !agentMap.containsKey(String.valueOf(
				AgentTypeEnum.EXCLUSIVER.getCode()))) {
			String oriName = (String) BaseGlobal.ACTION_ROLE_MAP_MAPPER.get(
					AgentTypeEnum.EXCLUSIVER.getCode());
			resetValueBuild(paraMap, String.valueOf(signUkTrans), actRoleSignUkRate,oriName);
		}
		//如果传照片人转移不为0并且没用该经纪人类型
		if(picUploadTrans != 0 && !agentMap.containsKey(String.valueOf(
				AgentTypeEnum.PICTURER.getCode()))) {
			String oriName = (String) BaseGlobal.ACTION_ROLE_MAP_MAPPER.get(
					AgentTypeEnum.PICTURER.getCode());
			resetValueBuild(paraMap, String.valueOf(picUploadTrans), actRolePivUploadRate,oriName);
		}
	}
	/**
	 * 计算分成比例，将得到分成转移的目标对象值重新赋值
	 * @Title: resetValueBuild
	 * @author: zuoyuntao  
	 * @Description:计算分成比例，将得到分成转移的目标对象值重新赋值   
	 * @param paraMap 数据集合
	 * @param movedName 转移的目标对象（接受转移的对象）
	 * @param movedRateValue 被转移对象的比例
	 * void   
	 * @throws
	 */
	private void resetValueBuild(Map<String,Object> paraMap,String movedName
			,Object movedRateValue,String oriName) {
		//得到转移到的目标对象名称
		String tempName = (String) BaseGlobal.ACTION_ROLE_MAP_MAPPER.get(movedName);
		//得到转移到的目标对象比例
		String tempValue = String.valueOf( paraMap.get(tempName));
		paraMap.put(tempName, Integer.parseInt(String.valueOf( movedRateValue)) 
				+ Integer.parseInt(StringUtil.isEmpty(tempValue)?"0":tempValue));
		paraMap.remove(oriName);
	}
	/**
	 * 组装查询条件
	 * @Title: buildFindSql
	 * @author: zuoyuntao  
	 * @Description:组装查询条件   
	 * @param paraMap
	 * @return      
	 * String JSON 格式为：{}     
	 * @throws
	 */
	private String buildFindSql(Map<String,Object> paraMap) {
		StringBuilder conditionSql = new StringBuilder();
		Iterator<String> it = paraMap.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			String value = String.valueOf(paraMap.get(key));
			if(StringUtil.isEmpty(value)) {
				continue;
			}
			if(conditionSql.length() > 0 ) {
				conditionSql.append(" and ");
			}
			conditionSql.append(key)
				.append(" = '")
				.append(value)
				.append("'");
		}
		return conditionSql.toString();
	}
}
