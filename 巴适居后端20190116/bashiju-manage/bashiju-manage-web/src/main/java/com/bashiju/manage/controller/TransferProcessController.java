/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  TransferProcessController.java   
 * @Package com.bashiju.manage.controller   
 * @Description:过户流程配置控制器   
 * @author: zuoyuntao     
 * @date:   2018年5月4日 上午11:47:22   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

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
import com.bashiju.manage.global.ManageGlobal;
import com.bashiju.manage.service.ICommunityService;
import com.bashiju.manage.service.IRegionService;
import com.bashiju.manage.service.ITransferProcessService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.util.StringUtil;

/**
 * 过户流程配置控制器
 * @ClassName:TransferProcessController   
 * @Description:过户流程配置控制器
 * @author: zuoyuntao
 * @date:   2018年5月4日 上午11:47:22   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@RequestMapping(value="transferProcess")
@Controller
public class TransferProcessController extends BaseController{
	@Autowired
	private ITransferProcessService mITransferProcessService;
	/**
	 * 片区管理接口对象
	 */
	@Autowired
	private IRegionService regionService;
	/**
	 * 构造树接口
	 */
	@Autowired
	private ICommunityService communityService;
	/**
	 * 获取主界面模型对象
	 * @Title: getMasterView   
	 * @Description: 获取主界面模型对象  
	 * @param: request 页面请求对象
	 * @param: response 页面响应对象
	 * @return: ModelAndView 视图中包含“AreaListJson”行政区划数据对象，
	 * JSON格式为：[{"code":"110000","parentCode":"0","level":"1","name":"北京市"},{},{}……]
	 * @throws
	 */
	@RequestMapping(value="transferProcess")
	public ModelAndView getMasterView(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView model = this.getModelAndView(request,response,"transferprocess/transferProcess");
		List<Map<String, Object>> list = regionService.queryAreaSelect();
		if(list.size() == 0) {
			throw new BusinessException("获取城市数据异常!");
		}
		model.addObject("AreaListJson", JSON.toJSONString(list));
		return model;
	}
	/**
	 * 获取明细信息（客户资料，业主资料）维护模型对象
	 * @Title: getDetailMgrModel   
	 * @Description: 获取明细信息（客户资料，业主资料）维护模型对象   
	 * @param: request 页面请求对象
	 * @param: response 页面响应对象
	 * @return: ModelAndView 视图中包含"valid","transferProc","newMap"对象，   
	 * valid为Map对象，格式为：{0=否, 1=是}  
	 * transferProc为Map对象，格式为：{id=12, programmeName=商业贷款过户流程, isValid=1, areaCode=BSJ0101, cityName=昆明市, valid=是, operator=测试人员, addTime=2018-05-23 14:38:48.0, updateOperator=测试人员, updateTime=2018-05-23 14:39:36.0, permissionArea=BSJ0103, operatorId=1, procModify=procModify, hsOwnerDetail=hsOwnerDetail, cusDetail=cusDetail, procConf=procConf, procDel=procDel, confType=1}
	 * newMap为Map对象，格式为：key为控件的ID，value为控件对应的数据记录
	 * {
	 * 1={id=57, detailName=身份证, addTime=2018-05-09 10:00:07.0, operator=测试人员, updateOperator=测试人员, updateTime=2018-05-23 13:58:13.0, confType=1, isValid=1, operatorId=1, confId=13, sortId=1, permissionArea=BSJ0103}, 
	 * 13={id=55, detailName=测测测, addTime=2018-05-09 10:00:07.0, operator=测试人员, updateOperator=测试人员, updateTime=2018-05-23 13:58:13.0, confType=1, isValid=1, operatorId=1, confId=13, sortId=13, permissionArea=BSJ0103}, 
	 * }
	 * @throws
	 */
	@RequestMapping(value="getDetailMgrModel")
	public ModelAndView getDetailMgrModel(HttpServletRequest request
			,HttpServletResponse response) {
		ModelAndView model = this.getModelAndView(
				request, response, "transferprocess/transferProcDetailMsg");
		model.addObject("valid",ManageGlobal.VALID);
		String procId = request.getParameter("procId");
		String confType = request.getParameter("confType");
		if(StringUtil.isNotEmpty(procId)) {
			List<Map<String,Object>> detailList = mITransferProcessService
					.queryTransferDetailByConfId(procId,confType);
			Map<String,Object> oneMap = mITransferProcessService.queryTransferProcById(procId);
			if(oneMap.size() == 0) {
				throw new BusinessException("未找到过户方案!");
			}
			oneMap.put("confType", confType);
			model.addObject("transferProc",oneMap);
			Map<String,Object> newMap = new HashMap<String,Object>();
			if(detailList.size() == 0) {
				detailList = null;
				newMap = null;
			}else {
				for(Map<String,Object> tempMap : detailList) {
					newMap.put(String.valueOf(tempMap.get("sortId")), tempMap);
				}
			}
			model.addObject("newMap",newMap);
		}
		return model;
	}
	/**
	 * 获取配置明细信息
	 * @Title: queryTransferConfigetail
	 * @author: zuoyuntao  
	 * @Description:获取配置明细信息
	 * @param procId 过户流程配置ID
	 * @param confType 配置类型 （1：业主明细材料，2：客户明细材料）
	 * @return      
	 * Object
	 */
	@RequestMapping(value="queryTransferConfigetail")
	@ResponseBody
	public Object queryTransferConfigetail(String procId,String confType) {
		if(StringUtil.isNotEmpty(procId)) {
			List<Map<String,Object>> detailList = mITransferProcessService
					.queryTransferDetailByConfId(procId,confType);
			Map<String,Object> oneMap = mITransferProcessService.queryTransferProcById(procId);
			if(oneMap.size() == 0) {
				throw new BusinessException("未找到过户方案!");
			}
			Map<String,Object> newMap = new HashMap<String,Object>();
			if(detailList.size() == 0) {
				detailList = null;
				newMap = null;
			}else {
				for(Map<String,Object> tempMap : detailList) {
					newMap.put(String.valueOf(tempMap.get("sortId")), tempMap);
				}
			}
			return newMap;
		}
		throw new BusinessException("传入配置ID为空");
	}
	/**
	 * 获得过户流程设置页面模型
	 * @Title: getTransProcSettingModel   
	 * @Description: 获得过户流程设置页面模型    
	 * @param: request 页面请求对象
	 * @param: response 页面响应对象
	 * @return: ModelAndView 
	 * 视图中包含如下对象：
	 * transferProc：过户方案信息
	 * procNameCount：下拉枚举值的长度
	 * procNames：下拉选项枚举值，如：{1=签买卖（定金）合同, 2=物业交割, 3=预约购买合同, 4=款项支付, 5=过户资料提供}
	 * confType：配置类型：1为业主材料明细 2为客户材料明细 3为过户流程配置
	 * transferProc主配置： {id=37, programmeName=公积金（组合）过户流程, isValid=1, areaCode=BSJ0102, cityName=大理市
	 * , valid=是, operator=测试人员, addTime=2018-05-23 14:40:30.0, permissionArea=BSJ0103, operatorId=1
	 * , procModify=procModify, hsOwnerDetail=hsOwnerDetail, cusDetail=cusDetail, procConf=procConf, procDel=procDel}  
	 * @throws
	 */
	@RequestMapping(value="getTransProcSettingModel")
	public ModelAndView getTransProcSettingModel(HttpServletRequest request
			,HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		model = this.getModelAndView(request, response, "transferprocess/transferProcSetPage");
		String procId = request.getParameter("procId");
		String confType = request.getParameter("confType");
		Map<String,Object> oneMap = mITransferProcessService.queryTransferProcById(procId);
		if(oneMap.size() == 0) {
			throw new BusinessException("未找到过户方案!");
		}
		List<Map<String,Object>> detailList = mITransferProcessService
				.queryTransferDetailByConfId(procId,confType);
		Map<String,Object> newMap = new HashMap<String,Object>();
		if(detailList.size() == 0) {
			detailList = null;
			newMap = null;
		}else {
			for(Map<String,Object> tempMap : detailList) {
				newMap.put(String.valueOf(tempMap.get("sortId")), tempMap);
			}
		}
		model.addObject("transferProc",oneMap);
		model.addObject("procNameCount",ManageGlobal.PROC_NAMES.size());
		model.addObject("procNames",ManageGlobal.PROC_NAMES);
		model.addObject("procNameInfo", newMap);
		model.addObject("confType",confType);
		return model;
	}
	/**
	 * 获取过户流程配置详细信息
	 * @Title: queryTransferProcSetDataList
	 * @author: zuoyuntao  
	 * @Description:获取过户流程配置详细信息
	 * @param procId 过户流程配置ID
	 * @param confType 配置类型 （1：业主明细材料，2：客户明细材料 3:过户流程流程配置）
	 * @return      
	 * Object
	 */
	@RequestMapping(value="queryTransferProcSetDataList")
	@ResponseBody
	public Object queryTransferProcSetDataList(String procId,String confType) {
		Map<String,Object> oneMap = mITransferProcessService.queryTransferProcById(procId);
		if(oneMap.size() == 0) {
			throw new BusinessException("未找到过户方案!");
		}
		List<Map<String,Object>> detailList = mITransferProcessService
				.queryTransferDetailByConfId(procId,confType);
		Map<String,Object> retMap = new HashMap<String,Object>();
		Map<String,Object> newMap = new HashMap<String,Object>();
		if(detailList.size() == 0) {
			detailList = null;
			newMap = null;
		}else {
			for(Map<String,Object> tempMap : detailList) {
				newMap.put(String.valueOf(tempMap.get("sortId")), tempMap);
			}
		}
		retMap.put("data", newMap);
		return retMap;
	}
	
	/**
	 * 获取过户流程名称 
	 * @Title: queryTransferProcName
	 * @author: zuoyuntao  
	 * @Description:获取过户流程名称  
	 * @return      
	 * Object JSON 格式为：key-value
	 */
	@RequestMapping(value="queryTransferProcName")
	@ResponseBody
	public Object queryTransferProcName() {
		return ManageGlobal.PROC_NAMES;
	}
	
	/**
	 * 获取增加或修改主配置信息模型
	 * @Title: getAddOrModifyModel   
	 * @Description: 获取增加或修改主配置信息模型  
	 * @param: request 页面请求对象
	 * @param: response 页面响应对象
	 * @return: ModelAndView 视图中包含如下对象： valid，cityInfo，transferProc
	 * valid（是否可用）：为Map对象，格式为：{0=否, 1=是}  
	 * cityInfo（城市信息）：{BSJ0102=大理市, BSJ0101=昆明市,......} key为城市ID，value为城市名称
	 * transferProc（主配置）：为Map对象，格式为：{id=38, programmeName=一次性付款过户流程, isValid=1, areaCode=BSJ0102, cityName=大理市, valid=是, operator=测试人员, addTime=2018-05-23 14:41:07.0, permissionArea=BSJ0103, operatorId=1, procModify=procModify, hsOwnerDetail=hsOwnerDetail, cusDetail=cusDetail, procConf=procConf, procDel=procDel}
	 * @throws
	 */
	@RequestMapping(value="getAddOrModifyModel")
	public ModelAndView getAddOrModifyModel(HttpServletRequest request
			,HttpServletResponse response) {
		ModelAndView model = this.getModelAndView(request, response, "transferprocess/transferProcConfig");
		model.addObject("valid",ManageGlobal.VALID);
		String procId = request.getParameter("procId");
		//获取城市信息
		List<Map<String, Object>> cityList = communityService.queryAreaSelect();
		model.addObject("cityList",JSON.toJSONString(cityList));
		if(StringUtil.isNotEmpty(procId)) {
			Map<String,Object> oneMap = mITransferProcessService.queryTransferProcById(procId);
			model.addObject("transferProc",oneMap);
		}
		return model;
	}
	
	/**
	 * 获取页面带分页的数据对象 
	 * @Title: queryTransferProcessList
	 * @author: zuoyuntao  
	 * @Description:获取页面带分页的数据对象 
	 * @param request 页面请求对象
	 * @param curCount 页面显示数据最小条数
	 * @param limitCount 页面显示数据最大条数
	 * @return      
	 * Object JSON 为Page对象，单条格式为：{id=9, programmeName=预约合同过户流程, isValid=1, areaCode=BSJ0102, cityName=大理市, valid=是, operator=测试人员, addTime=2018-05-23 13:57:41.0, updateOperator=测试人员, updateTime=2018-05-23 13:58:29.0, permissionArea=BSJ0103, operatorId=1, procModify=procModify, hsOwnerDetail=hsOwnerDetail, cusDetail=cusDetail, procConf=procConf, procDel=procDel}     
	 * @throws
	 */
	@RequestMapping(value="queryTransferProcessList")
	@ResponseBody
	public Object queryTransferProcessList(HttpServletRequest request,String curCount,String limitCount) {
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("programmeName", request.getParameter("transferProcName"));
		paraMap.put("areaCode", request.getParameter("areaCode"));
		int curPageCount = StringUtil.isEmpty(curCount)?0
				:Integer.parseInt(String.valueOf(curCount));
		int maxPageCont = StringUtil.isEmpty(limitCount)?0
				:Integer.parseInt(String.valueOf(limitCount));
		Page<Map<String,Object>> page = mITransferProcessService.queryTransferProcessDataInfo(
				paraMap, curPageCount, maxPageCont);
		return this.getPageResult(page);
	}
	
	/**
	 * 添加或修改过户流程配置方案
	 * @Title: saveOrUpdateTranserProc   
	 * @Description: 添加或修改过户流程配置方案  
	 * @param: jsonData 页面传入参数对象
	 * @return: BashijuResult      
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveOrUpdateTranserProc")
	@ResponseBody
	public BashijuResult saveOrUpdateTranserProc(String jsonData) {
		if(StringUtil.isEmpty(jsonData)) {
			throw new BusinessException("传入参数为空!");
		}
		Map<String,Object>paraMap = new HashMap<String,Object>();
		paraMap = (Map<String,Object>) JSONObject.parse(jsonData);
		
		Map<String,Object>oneMap = new HashMap<String,Object>();
		oneMap.put("companyId", UserThreadLocal.get().get("companyId"));
		oneMap.put("programmeName", String.valueOf(paraMap.get("procName")));
		if(mITransferProcessService.jargeDataIsExists(oneMap
				, ManageGlobal.HOUSE_EXCHAGE_CONFIG) 
				&& StringUtil.isEmpty(String.valueOf(paraMap.get("id")))) {
			throw new BusinessException("数据已存在！");
		}
		
		if(StringUtil.isNotEmpty(String.valueOf(paraMap.get("procId")))) {
			paraMap.put("updateOperator", UserThreadLocal.get().get("realName"));
			paraMap.put("id", paraMap.get("procId"));
		}
		paraMap.put("permissionArea", UserThreadLocal.get().get("deptId"));
		paraMap.put("operatorId", UserThreadLocal.get().get("id"));
		paraMap.remove("procId");
		paraMap.put("programmeName", paraMap.get("procName"));
		paraMap.put("companyId", UserThreadLocal.get().get("companyId"));
		paraMap.remove("procName");
		mITransferProcessService.saveOrUpdateHouseExData(paraMap);
		return BashijuResult.ok();
	}
	
	/**
	 * 删除配置方案数据信息
	 * @Title: delTransferProc   
	 * @Description: 删除配置方案数据信息   
	 * @param: procId 过户流程的配置ID
	 * @return: BashijuResult      
	 * @throws
	 */
	@RequestMapping(value="delTransferProc")
	@ResponseBody
	public BashijuResult delTransferProc(String procId) {
		if(StringUtil.isEmpty(procId)) {
			throw new BusinessException("传入参数为空!");
		}
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("id", procId);
		mITransferProcessService.delHouseExData(paraMap);
		return BashijuResult.ok();
	}
	
	/**
	 * 保存过户流程明细数据信息
	 * @Title: saveOrUpdateTransferProcDetail   
	 * @Description:保存过户流程明细数据信息 
	 * @param: jsonData 页面传入有数据对象
	 * @return: BashijuResult      
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveOrUpdateTransferProcDetail")
	@ResponseBody
	public BashijuResult saveOrUpdateTransferProcDetail(String jsonData) {
		if(StringUtil.isEmpty(jsonData)) {
			throw new BusinessException("传入参数为空!");
		}
		Map<String,Object>paraMap = new HashMap<String,Object>();
		paraMap = (Map<String,Object>) JSONObject.parse(jsonData);
		String confId = String.valueOf(paraMap.get("procId"));
		String confType = String.valueOf(paraMap.get("confType"));
		
		boolean isInsert = true;
		if(null != paraMap.get("detail")) {
			isInsert = false;
		}
		String permissionArea = String.valueOf(UserThreadLocal.get().get("deptId"));
		String operatorId = String.valueOf(UserThreadLocal.get().get("id"));
		paraMap.remove("procId");
		paraMap.remove("confType");
		paraMap.remove("detail");
		
		Iterator<String> it = paraMap.keySet().iterator();
		//用于传入后台保存的集合
		List<Map<String,Object>> saveList = new ArrayList<Map<String,Object>>();
		while(it.hasNext()) {
			Map<String,Object> tempMap = new HashMap<String,Object>();
			String key = String.valueOf(it.next());
			Object value = paraMap.get(key);
			if(StringUtil.isNotEmpty(String.valueOf(value))) {
				tempMap.put("confId", confId);
				tempMap.put("confType", confType);
				tempMap.put("permissionArea", permissionArea);
				tempMap.put("operatorId", operatorId);
				tempMap.put("sortId", key);
				if(!isInsert) {
					tempMap.put("updateOperator", UserThreadLocal.get().get("realName"));
				}
				if(confType.equals("3")) {
					tempMap.put("detailCode", value);
					tempMap.put("detailName", ManageGlobal.PROC_NAMES.get(value));
				}else {
					tempMap.put("detailName", value);
				}
				saveList.add(tempMap);
			}
		}
		if(saveList.size() == 0) {
			throw new BusinessException("页面传入数据为空!");
		}
		mITransferProcessService.saveOrUpdateHouseExDetailData(saveList,confId,true);
		return BashijuResult.ok();
	}
}
