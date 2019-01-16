/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ProposalSetController.java   
 * @Package com.bashiju.manage.controller   
 * @Description:提成方案配置控制器
 * @author: zuoyuntao     
 * @date:   2018年6月22日 上午9:28:32   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bashiju.manage.service.ICommunityService;
import com.bashiju.manage.service.IProposalSetService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.util.StringUtil;

/**
 * 提成方案配置控制器
 * @ClassName:ProposalSetController
 * @Description:提成方案配置控制器
 * @author:zuoyuntao
 * @date:2018年6月22日 上午9:28:32
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="proposalSet")
public class ProposalSetController extends BaseController{
	/**
	 * 构造树接口
	 */
	@Autowired
	private ICommunityService communityService;
	/**
	 * 提成方案配置接口
	 */
	@Autowired
	private IProposalSetService mIProposalSetService;
	/**
	 * （简单描述） 
	 * @Title: getProposalSetPage
	 * @author: zuoyuntao  
	 * @Description:（详细描述）   
	 * @param request
	 * @param response
	 * @return      
	 * ModelAndView JSON 格式为：{}     
	 * @throws
	 */
	@RequestMapping(value="getProposalSetPage")
	public ModelAndView getProposalSetPage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView model = this.getModelAndView(request, response, "proposalSet/proposal_set");
		List<Map<String, Object>> cityList = communityService.queryAreaSelect();
		model.addObject("cityList",JSONObject.toJSON(cityList));
		return model;
	}
	
	/**
	 * 获取所有方案设置数据信息
	 * @Title: queryProposalSetAllDataList
	 * @author: zuoyuntao  
	 * @Description:获取所有方案设置数据信息
	 * @param jsonData
	 * @param page
	 * @param limit
	 * @return      
	 * Object JSON 格式为：{}     
	 * @throws
	 */
	@RequestMapping(value="queryProposalSetAllDataList")
	@ResponseBody
	public Object queryProposalSetAllDataList(String jsonData,int page,int limit
			,String extractName,String areaCode) {
		Map<String,Object> paraMap = null;
		if(StringUtil.isEmpty(jsonData)) {
			paraMap = new HashMap<String,Object>();
		}else {
			paraMap = JSONObject.parseObject(jsonData);
		}
		paraMap.put("extractName", extractName);
		paraMap.put("areaCode", areaCode);
		Page<Map<String,Object>> pageObj = mIProposalSetService
				.queryProposalSetAllDataListPage(paraMap, page, limit);
		return getPageResult(pageObj);
	}
	
	/**
	 * 获取所有的业绩类型数据
	 * @Title: getPerformanceTypePageObj   
	 * @Description: 获取所有的业绩类型数据 
	 * @param: areaCode：行政区划
	 * @param: proposalId：提成方案ID
	 * @param: curCount：最小条数
	 * @param: limitCount：最大条数
	 * @return: Object    
	 * {count=8, msg=, code=0, data=Page{count=true, pageNum=1, pageSize=10, startRow=0, endRow=10, total=8, pages=1, reasonable=false, pageSizeZero=false}[{pfmTypeName=一手成交, usePfmTypeName=一手成交, caculateMethod=215, permissionArea=BSJ0103, pfmTypeEdit=pfmTypeEdit, addTime=2018-05-16 12:01:25.0, usePfmTypeValue=208, isValid=1, caculateName=合同直计算提, updateOperator=测试人员, updateTime=2018-05-24 10:28:06.0, cityId=BSJ0101, operator=测试人员, companyId=BSJ01, cityName=昆明市, usePfm=一手成交, id=97, operatorId=1, pfmTypeDel=pfmTypeDel}, {pfmTypeName=买卖业务, usePfmTypeName=商业出售,住房出售, caculateMethod=215, permissionArea=BSJ0103, pfmTypeEdit=pfmTypeEdit, addTime=2018-05-16 12:05:46.0, usePfmTypeValue=210,209, isValid=1, caculateName=合同直计算提, updateOperator=测试人员, updateTime=2018-05-23 16:13:31.0, cityId=BSJ0101, operator=测试人员, companyId=BSJ01, cityName=昆明市, usePfm=商业出售,住房出售, id=98, operatorId=1, pfmTypeDel=pfmTypeDel}, {pfmTypeName=租赁业务, usePfmTypeName=商业出租,住房出租, caculateMethod=215, permissionArea=BSJ0103, pfmTypeEdit=pfmTypeEdit, addTime=2018-05-16 12:06:04.0, usePfmTypeValue=212,211, isValid=1, caculateName=合同直计算提, updateOperator=测试人员, updateTime=2018-05-23 16:13:37.0, cityId=BSJ0101, operator=测试人员, companyId=BSJ01, cityName=昆明市, usePfm=商业出租,住房出租, id=99, operatorId=1, pfmTypeDel=pfmTypeDel}, {pfmTypeName=金融业务, usePfmTypeName=金融服务, caculateMethod=216, permissionArea=BSJ0103, pfmTypeEdit=pfmTypeEdit, addTime=2018-05-16 18:39:15.0, usePfmTypeValue=213, isValid=1, caculateName=累进回溯计算, updateOperator=测试人员, updateTime=2018-05-23 15:32:07.0, cityId=BSJ0101, operator=测试人员, companyId=BSJ01, cityName=昆明市, usePfm=金融服务, id=102, operatorId=1, pfmTypeDel=pfmTypeDel}, {pfmTypeName=一手成交, usePfmTypeName=一手成交, caculateMethod=215, permissionArea=BSJ0103, pfmTypeEdit=pfmTypeEdit, addTime=2018-05-23 16:26:30.0, usePfmTypeValue=208, isValid=1, caculateName=合同直计算提, cityId=BSJ0102, operator=测试人员, companyId=BSJ01, cityName=大理市, usePfm=一手成交, id=115, operatorId=1, pfmTypeDel=pfmTypeDel}, {pfmTypeName=买卖业务, usePfmTypeName=住房出售,商业出售, caculateMethod=215, permissionArea=BSJ0103, pfmTypeEdit=pfmTypeEdit, addTime=2018-05-23 16:29:26.0, usePfmTypeValue=209,210, isValid=1, caculateName=合同直计算提, cityId=BSJ0102, operator=测试人员, companyId=BSJ01, cityName=大理市, usePfm=住房出售,商业出售, id=116, operatorId=1, pfmTypeDel=pfmTypeDel}, {pfmTypeName=租赁业务, usePfmTypeName=住房出租,商业出租, caculateMethod=215, permissionArea=BSJ0103, pfmTypeEdit=pfmTypeEdit, addTime=2018-05-23 16:29:45.0, usePfmTypeValue=211,212, isValid=1, caculateName=合同直计算提, cityId=BSJ0102, operator=测试人员, companyId=BSJ01, cityName=大理市, usePfm=住房出租,商业出租, id=117, operatorId=1, pfmTypeDel=pfmTypeDel}, {pfmTypeName=金融业务, usePfmTypeName=金融服务, caculateMethod=215, permissionArea=BSJ0103, pfmTypeEdit=pfmTypeEdit, addTime=2018-05-23 16:30:05.0, usePfmTypeValue=213, isValid=1, caculateName=合同直计算提, updateOperator=测试人员, updateTime=2018-05-23 16:30:36.0, cityId=BSJ0102, operator=测试人员, companyId=BSJ01, cityName=大理市, usePfm=金融服务, id=118, operatorId=1, pfmTypeDel=pfmTypeDel}]}  
	 * @throws
	 */
	@RequestMapping(value="getPerformanceTypePageObjForProposal")
	@ResponseBody
	public Object getPerformanceTypePageObjForProposal(String areaCode
			,String proposalId,int page,int limit) {
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("cityId", areaCode);
		paraMap.put("confId", proposalId);
		paraMap.put("companyId", UserThreadLocal.get().get("companyId"));
		Page<Map<String, Object>> pageObj = mIProposalSetService
				.queryPfmTypePageObjForProposal(paraMap,page,limit);
		Map<String, Object> retMap = getPageResult(pageObj); 
		return retMap;
	}
	/**
	 * 保存或更新提成方案数据信息
	 * @Title: saveOrUpdateProposalInfo
	 * @author: zuoyuntao  
	 * @Description:保存或更新提成方案数据信息
	 * @param jsonData 页面传入参数
	 * @return      
	 * BashijuResult     
	 * @throws
	 */
	@RequestMapping(value="saveOrUpdateProposalInfo")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public BashijuResult saveOrUpdateProposalInfo(String jsonData) {
		if(StringUtil.isEmpty(jsonData)) {
			throw new BusinessException("传入参数错误！请重试！");
		}
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap = (Map<String,Object>) JSON.parse(jsonData);
		
		String companyId = String.valueOf(UserThreadLocal.get().get("companyId"));
		Map<String,Object> temp = new HashMap<String,Object>();
		temp.put("companyId", companyId);
		temp.put("areaCode", paraMap.get("areaCode"));
		temp.put("extractName", paraMap.get("extractName"));
		if(mIProposalSetService.isDataExists(temp) 
				&& StringUtil.isEmpty(String.valueOf(paraMap.get("id")))) {
			throw new BusinessException("公司在该城市已存在此方案!");
		}
		
		String id = String.valueOf(paraMap.get("id"));
		if(StringUtil.isEmpty(id)) {
			paraMap.remove("id");
		}
		mIProposalSetService.saveOrUpdateProposalInfo(paraMap);
		return BashijuResult.ok();
	}
	/**
	 * 删除提成方案
	 * @Title: deleteProposalSetInfo
	 * @author: zuoyuntao  
	 * @Description:删除提成方案  
	 * @param proposalId 方案Id
	 * @return      
	 * BashijuResult 
	 * @throws
	 */
	@RequestMapping(value="deleteProposalSetInfo")
	@ResponseBody
	public BashijuResult deleteProposalSetInfo(String proposalId) {
		if(StringUtil.isEmpty(proposalId)) {
			throw new BusinessException("传入ID为空，请重试!");
		}
		mIProposalSetService.deleteProposalInfoById(proposalId);
		return BashijuResult.ok();
	}
	/**
	 * 保存方详细设置
	 * @Title: saveProposalSetDetailInfo
	 * @author: zuoyuntao  
	 * @Description:保存方详细设置
	 * @param jsonData
	 * @return      
	 * BashijuResult  
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveProposalSetDetailInfo")
	@ResponseBody
	public BashijuResult saveProposalSetDetailInfo(String jsonData) {
		if(StringUtil.isEmpty(jsonData)) {
			throw new BusinessException("传入参数错误！请重试！");
		}
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap = (Map<String, Object>) JSON.parse(jsonData);
		String proposalId = String.valueOf(paraMap.get("proposalId"));
		String pfmTypeId = String.valueOf(paraMap.get("pfmTypeId"));
		String areaCode = String.valueOf(paraMap.get("areaCode"));
		paraMap.remove("pfmTypeId");
		paraMap.remove("proposalId");
		paraMap.remove("areaCode");
		if(StringUtil.isEmpty(proposalId)) {
			throw new BusinessException("传入参数错误！请重试！");
		}
		List<Map<String,Object>> saveList = new ArrayList<Map<String,Object>>();
		saveList = buildSaveList(paraMap, proposalId, pfmTypeId, areaCode);
		mIProposalSetService.saveProposalSetDetail(saveList, proposalId,pfmTypeId,areaCode);
		return BashijuResult.ok();
	}
	
	/**
	 * 组装提成方案详细保存数据
	 * @Title: buildSaveList
	 * @author: zuoyuntao  
	 * @Description:组装提成方案详细保存数据   
	 * @param paraMap
	 * @param prososalId
	 * @param pfmTypeId
	 * @param areaCode
	 * @return      
	 * List<Map<String,Object>> JSON 格式为：{}     
	 * @throws
	 */
	private List<Map<String,Object>> buildSaveList(Map<String,Object> paraMap
			,String proposalId,String pfmTypeId,String areaCode){
		Iterator<String> it = paraMap.keySet().iterator();
		String companyId = String.valueOf(UserThreadLocal.get().get("companyId"));
		String permisionArea = String.valueOf(UserThreadLocal.get().get("deptId"));
		String operatorId = String.valueOf(UserThreadLocal.get().get("id"));
		List<Map<String,Object>> retList = new ArrayList<Map<String,Object>>();
		while(it.hasNext()) {
			Map<String,Object> temp = new HashMap<String,Object>();
			String key = it.next();
			String value = String.valueOf(paraMap.get(key));
			if(StringUtil.isNotEmpty(value) && !"null".equals(value)) {
				if(Integer.parseInt(key) <= 10) {
					int keyInt = Integer.parseInt(key) + 10;
					String valueTwo = String.valueOf(paraMap.get(String.valueOf(keyInt)));
					if(StringUtil.isEmpty(valueTwo) && "null".equals(valueTwo)) {
						throw new BusinessException("合同提成比例和提成比例必须同时录入!");
					}
					temp.put("contractRate", value);
					temp.put("extractRate", valueTwo);
				}
				if(Integer.parseInt(key) > 10) {
					continue;
				}
				temp.put("sortNo", key);
				temp.put("companyId", companyId);
				temp.put("permissionArea", permisionArea);
				temp.put("operatorId", operatorId);
				temp.put("confId", proposalId);
				temp.put("pfmTypeId", pfmTypeId);
				temp.put("areaCode", areaCode);
				temp.put("isSetted", "1");
				retList.add(temp);
			}
		}
		return retList;
	}
	/**
	 * 根据条件查询提成方案配置明细
	 * @Title: queryAllProposalDetail
	 * @author: zuoyuntao  
	 * @Description:根据条件查询提成方案配置明细 
	 * @param jsonData 页面传入参数  可能包含业绩类型ID，areaCode,提成方案配置ID
	 * @return      
	 * Object JSON 格式为：{}     
	 * @throws
	 */
	@RequestMapping(value="queryAllProposalDetail")
	@ResponseBody
	public BashijuResult queryAllProposalDetail(String confId,String pfmTypeId,String areaCode) {
		Map<String,Object> paraMap = new HashMap<String,Object>();
		if(StringUtil.isNotEmpty(confId)) {
			paraMap.put("confId", confId);
		}
		if(StringUtil.isNotEmpty(pfmTypeId)) {
			paraMap.put("pfmTypeId", pfmTypeId);
		}
		if(StringUtil.isNotEmpty(areaCode)) {
			paraMap.put("areaCode", areaCode);
		}
		List<Map<String,Object>> retList = mIProposalSetService.queryAllProposalDetail(paraMap);
		return BashijuResult.ok(retList);
	}
}
