/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  DivideIntoConfigController.java   
 * @Package com.bashiju.manage.controller   
 * @Description:分成配置控制类   
 * @author: zuoyuntao     
 * @date:   2018年4月24日 下午2:22:25   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

package com.bashiju.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bashiju.manage.global.ManageGlobal;
import com.bashiju.manage.service.IDivideIntoConfigService;
import com.bashiju.manage.service.IRegionService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.util.StringUtil;

/**
 * 分成配置控制类
 * @ClassName:DivideIntoConfigController   
 * @Description:分成配置控制类   
 * @author: zuoyuntao
 * @date:2018年4月24日 下午2:22:25   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@RequestMapping(value="divideIntoConfig")
@Controller
public class DivideIntoConfigController extends BaseController{
	/**
	 * 分成配置业务层处理接口
	 */
	@Autowired
	private IDivideIntoConfigService mIDivideIntoConfigService;
	/**
	 * 片区管理接口
	 */
	@Autowired
	private IRegionService regionService;
	/**
	 * 获得到分成配置主页面模型
	 * @Title: divideinfoPage   
	 * @Description: 获得到分成配置主页面模型   
	 * @param: request 页面请求对象
	 * @param: response 页面响应对象
	 * @param: @throws Exception      
	 * @return: ModelAndView 视图包含
	 * isSetted：{0=未设置, 1=已设置}  
	 * valid： {0=否, 1=是}  
	 * AreaListJson：[{"code":"110000","parentCode":"0","level":"1","name":"北京市"},
	 * {"code":"110100","parentCode":"110000","level":"2","name":"市辖区"},{"code":"370400","parentC...
	 * @throws
	 */
	@RequestMapping(value="dividePage")
	public ModelAndView divideinfoPage(HttpServletRequest request,HttpServletResponse response)throws Exception{
		ModelAndView mv = getModelAndView(request, response, "divideinto/divideinto");
		List<Map<String, Object>> list = regionService.queryAreaSelect();
		mv.addObject("AreaListJson", JSON.toJSONString(list));
		mv.addObject("valid",ManageGlobal.VALID);
		mv.addObject("isSetted",ManageGlobal.ZERO_OR_ONE);
		return mv;
	}
	/**
	 * 分成配置：添加或修改的模型建立
	 * @Title: addOrUpdatePage   
	 * @Description: 分成配置：添加或修改的模型建立
	 * @param: request 页面请求对象
	 * @param: response 页面响应对象
	 * @return: ModelAndView 
	 * cityInfo：{BSJ0102=大理市, BSJ0101=昆明市}，key为城市Id，value为城市名称
	 * valid：{0=否, 1=是}
	 * configtype：{1=出售, 2=出租, 3=一手成交}
	 * dealtype：{1=住宅, 2=商业}
	 * divideInfo：{id=32, configName=商业出租（写字楼、厂房、商铺、仓库、土地）, cityId=BSJ0102, isValid=1, addTime=2018-05-23 10:23:34.0, operator=测试人员, updateOperator=测试人员, updateTime=2018-05-23 10:24:21.0, operatorId=1, permissionArea=BSJ0103, dealType=2, configType=2, houseEntrustTrans=0, keyMgrTrans=0, signUkTrans=0, picUploadTrans=0, companyId=BSJ01, confAdd=confAdd, confEidt=confEidt, edit=edit, del=del}
	 * @throws
	 */
	@RequestMapping(value="divideIntoAddOrUpdatePage")
	public ModelAndView addOrUpdatePage(HttpServletRequest request
			,HttpServletResponse response)throws Exception{
		ModelAndView mv = getModelAndView(request, response, "divideinto/divideIntoAddOrUpdate");
		String configId = request.getParameter("configId");
		
		List<Map<String, Object>> list = regionService.queryAreaSelect();
		
//		List<Map<String,Object>> cityInfo = mIDivideIntoConfigService.queryCityInfoByCompanyId(
//				String.valueOf(UserThreadLocal.get().get("companyId")));
		Map<String,Object> cityMap = new HashMap<String,Object>();
		for(Map<String,Object> tempObj : list) {
			cityMap.put(String.valueOf(tempObj.get("code")), tempObj.get("name"));
		}
		mv.addObject("cityInfo",cityMap);
		mv.addObject("valid",ManageGlobal.VALID);
		mv.addObject("configtype",ManageGlobal.CONFIG_TYPE);
		mv.addObject("dealtype",ManageGlobal.DEAL_TYPE);
		if(StringUtils.isEmpty(configId)) {
			return mv;
		}
		Map<String,Object> oneMap = mIDivideIntoConfigService.queryDivideIntoConfigInfoMapById(configId);
		if(null != oneMap) {
			mv.addObject("divideInfo",oneMap);
		}
		return mv;
	}
	/**
	 * 分成配置页面模型获取
	 * @Title: confAddOrEditPage   
	 * @Description: 分成配置页面模型获取
	 * @param: request 页面请求对象
	 * @param: response 页面响应对象
	 * @return: ModelAndView  
	 * actRoleHouseMsg：{1=房源录入人, 2=房源开盘人, 3=房源维护人, 4=客源录入人, 5=客源维护人, 6=合同成交人, 7=房源委托人, 8=拿钥匙人, 9=签独家人, 10=传照片人}
	 * divideInfo：{id=33, configName=一手成交, cityId=BSJ0101, isValid=1, addTime=2018-05-23 10:26:11.0, operator=测试人员, operatorId=1, permissionArea=BSJ0103, dealType=2, configType=3, houseEntrustTrans=0, keyMgrTrans=0, signUkTrans=0, picUploadTrans=0, companyId=BSJ01, confAdd=confAdd, confEidt=confEidt, edit=edit, del=del}
	 * @throws
	 */
	@RequestMapping(value="confAddOrEditPage")
	public ModelAndView confAddOrEditPage(HttpServletRequest request
			,HttpServletResponse response)throws Exception{
		ModelAndView mv = getModelAndView(request, response, "divideinto/divideConfAddOrEdit");
		mv.addObject("actRoleHouseMsg",ManageGlobal.ACTION_ROLE_MAP);
		
		String configId = request.getParameter("configId");
		if(StringUtils.isEmpty(configId)) {
			return mv;
		}
		//返回配置主要数据信息
		Map<String,Object> oneMap = mIDivideIntoConfigService.queryDivideIntoConfigInfoMapById(configId);
		if(null != oneMap) {
			mv.addObject("divideInfo",oneMap);
		}
		return mv;
	}
	
	/**
	 * 根据ID查询分成详细配置信息 
	 * @Title: queryDivideDetailConfigObj
	 * @author: zuoyuntao  
	 * @Description:根据ID查询分成详细配置信息   
	 * @param configId 主配置ID
	 * @return      
	 * Object
	 */
	@RequestMapping(value="queryDivideDetailConfigObj")
	@ResponseBody
	public Object queryDivideDetailConfigObj(String configId) {
		Map<String,Object> oneMap = mIDivideIntoConfigService.queryDivideIntoConfigInfoMapById(configId);
		if(null != oneMap) {
			return oneMap;
		}
		throw new BusinessException("根据ID未查询到分成详细配置!");
	}
	/**
	 * 这里用一句话描述这个方法的作用
	 * @Title: queryAllDivideIntoConfigInfo   
	 * @Description: 这里用一句话描述这个方法的作用   
	 * @param: page:分页开始条数
	 * @param: limit：分页最大条数
	 * @param: configName：配置名称
	 * @param: codeHid：城市ID
	 * @return: Object  Page{count=true, pageNum=1, pageSize=10, startRow=0, endRow=10, total=6, pages=1, reasonable=false, pageSizeZero=false}[{id=12, configName=一手成交, configStatus=1, cityId=BSJ0101, isValid=1, cityName=昆明市, addTime=2018-05-23 10:25:05.0, operator=测试人员, updateOperator=测试人员, updateTime=2018-05-23 10:25:52.0, operatorId=1, permissionArea=BSJ0103, dealType=1, configType=3, houseEntrustTrans=3, keyMgrTrans=4, signUkTrans=0, picUploadTrans=6, companyId=BSJ01, actionRoleIptRate=15, actRoleOpMgrRate=5, actRoleHouseMsgRate=25, actRoleCusIptRate=5, actRoleCusMgrRate=5, actRoleContractMgrRate=25, actHouseEntrustMgrRate=5, actRoleKeyMgrRate=5, actRoleSignUkRate=5, actRolePivUploadRate=5, status=已设置, valid=有效, dealTypeName=住宅, configTypeName=一手成交, confAdd=confAdd, confEidt=confEidt, edit=edit, del=del}, {id=13, configName=住宅出售（住宅、商铺、车位）, configStatus=1, cityId=BSJ0101, isValid=1, cityName=昆明市, addTime=2018-05-23 10:28:52.0, operator=测试人员, updateOperator=测试人员, updateTime=2018-05-23 10:29:39.0, operatorId=1, permissionArea=BSJ0103, dealType=1, configType=1, houseEntrustTrans=0, keyMgrTrans=1, signUkTrans=5, picUploadTrans=0, companyId=BSJ01, actionRoleIptRate=20, actRoleOpMgrRate=20, actRoleHouseMsgRate=20, actRoleCusIptRate=5, actRoleCusMgrRate=5, actRoleContractMgrRate=5, actHouseEntrustMgrRate=5, actRoleKeyMgrRate=10, actRoleSignUkRate=10, actRolePivUploadRate=, status=已设置, valid=有效, dealTypeName=住宅, configTypeName=出售, confAdd=confAdd, confEidt=confEidt, edit=edit, del=del}, {id=14, configName=住宅出租（住宅、商铺、车位）, cityId=BSJ0101, isValid=1, cityName=昆明市, addTime=2018-05-23 10:24:01.0, operator=测试人员, updateOperator=测试人员, updateTime=2018-05-23 10:24:48.0, operatorId=1, permissionArea=BSJ0103, dealType=1, configType=2, companyId=BSJ01, status=未设置, valid=有效, dealTypeName=住宅, configTypeName=出租, confAdd=confAdd, confEidt=confEidt, edit=edit, del=del}, {id=15, configName=商业出售（写字楼、厂房、商铺、仓库，土地）, configStatus=1, cityId=BSJ0102, isValid=1, cityName=大理市, addTime=2018-05-22 18:59:52.0, operator=测试人员, updateOperator=测试人员, updateTime=2018-05-22 19:00:36.0, operatorId=1, permissionArea=BSJ0103, dealType=2, configType=1, houseEntrustTrans=3, keyMgrTrans=0, signUkTrans=0, picUploadTrans=6, companyId=BSJ01, actionRoleIptRate=5, actRoleOpMgrRate=1, actRoleHouseMsgRate=6, actRoleCusIptRate=6, actRoleCusMgrRate=8, actRoleContractMgrRate=9, actHouseEntrustMgrRate=15, actRoleKeyMgrRate=3, actRoleSignUkRate=2, actRolePivUploadRate=7, status=已设置, valid=有效, dealTypeName=商业, configTypeName=出售, confAdd=confAdd, confEidt=confEidt, edit=edit, del=del}, {id=32, configName=商业出租（写字楼、厂房、商铺、仓库、土地）, cityId=BSJ0102, isValid=1, cityName=大理市, addTime=2018-05-23 10:23:34.0, operator=测试人员, updateOperator=测试人员, updateTime=2018-05-23 10:24:21.0, operatorId=1, permissionArea=BSJ0103, dealType=2, configType=2, houseEntrustTrans=0, keyMgrTrans=0, signUkTrans=0, picUploadTrans=0, companyId=BSJ01, status=未设置, valid=有效, dealTypeName=商业, configTypeName=出租, confAdd=confAdd, confEidt=confEidt, edit=edit, del=del}, {id=33, configName=一手成交, cityId=BSJ0101, isValid=1, cityName=昆明市, addTime=2018-05-23 10:26:11.0, operator=测试人员, operatorId=1, permissionArea=BSJ0103, dealType=2, configType=3, houseEntrustTrans=0, keyMgrTrans=0, signUkTrans=0, picUploadTrans=0, companyId=BSJ01, status=未设置, valid=有效, dealTypeName=商业, configTypeName=一手成交, confAdd=confAdd, confEidt=confEidt, edit=edit, del=del}]    
	 * @throws
	 */
	@RequestMapping(value="queryAllDivideIntoConfigInfo")
	@ResponseBody
	public Object queryAllDivideIntoConfigInfo(String page,String limit,String configName,
			String codeHid) {
		//curPage:开始条数，limitPage：每次查询最大条数 默认设置1~~20
		Object curPage = StringUtil.isEmpty(page)?"1":page;
		Object limitPage = StringUtil.isEmpty(limit)?"20":limit;
		Map<String,Object> paraMap = new HashMap<String,Object>();
		if(StringUtils.isEmpty(configName)) {
			configName = null;
		}
		if(StringUtils.isEmpty(codeHid)) {
			codeHid = null;
		}
		paraMap.put("configName", configName);
		paraMap.put("areaCode", codeHid);
		Page<Map<String,Object>> pageObj = mIDivideIntoConfigService.queryDivideIntoConfigInfoList(
				paraMap, Integer.parseInt(String.valueOf( curPage)), 
			Integer.parseInt(String.valueOf(limitPage)));
		return getPageResult(pageObj);
	}
	/**
	 * 添加分成配置对象
	 * @Title: addDivideIntoConfInfo   
	 * @Description: 添加分成配置对象
	 * @param jsonData 页面传入操作数据对象
	 * @return: BashijuResult      
	 * @throws
	 */
	@RequestMapping(value="saveOrUpdateDivideInto")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public BashijuResult saveOrUpdateDivideInto(String jsonData) {
		if(StringUtils.isEmpty(jsonData)) {
			throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
		}
		
		Map<String,Object> map = (Map<String,Object>) JSONObject.parse(jsonData);
		map.put("companyId", String.valueOf(UserThreadLocal.get().get("companyId")));
		if(mIDivideIntoConfigService.jurgeDivideIntoConfigExists(map)
				&& StringUtil.isEmpty(String.valueOf(map.get("id")))) {
			throw new BusinessException("分成配置已存在！");
		}
		if(StringUtil.isNotEmpty(String.valueOf(map.get("configId")))) {
			map.put("updateOperator", UserThreadLocal.get().get("realName"));
			map.put("id", String.valueOf(map.get("configId")));
		}
		map.remove("configId");
		map.put("permissionArea", UserThreadLocal.get().get("deptId"));
		map.put("operatorId", UserThreadLocal.get().get("id"));
		map.put("companyId", UserThreadLocal.get().get("companyId"));
		mIDivideIntoConfigService.saveOrUpdateDivideInto(map);
		return BashijuResult.ok();
	}
	/**
	 * 删除【分成配置】信息
	 * @Title: deleteDivideIntoConfigInfo   
	 * @Description: 删除【分成配置】信息 
	 * @param: configId：配置ID
	 * @return: BashijuResult：返回BashijuResult对象
	 * @throws
	 */
	@RequestMapping("deleteDivideIntoConfigInfo")
	@ResponseBody
	public BashijuResult deleteDivideIntoConfigInfo(String configId){
		if(StringUtils.isEmpty(configId)) {
			throw new BusinessException("请选择要删除的数据信息");
		}
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("id",configId);
		mIDivideIntoConfigService.deleteDivideIntoConfigInfoByConfId(paraMap);
		return BashijuResult.ok();
	}
	/**
	 * 添加或修改分成配置信息
	 * @Title: saveOrUpdateDivConfInfo
	 * @author: zuoyuntao  
	 * @Description: 添加或修改分成配置信息 
	 * @param jsonData：页面传入参数
	 * @return BashijuResult JSON 格式为：{}     
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("saveOrUpdateDivConfInfo")
	@ResponseBody
	public BashijuResult saveOrUpdateDivConfInfo(String jsonData) {
		Map<String,Object> paraMap = (Map<String,Object>) JSONObject.parse(jsonData);
		if(paraMap.size() ==0) {
			throw new BusinessException(ErrorCodeEnum.SYSTEM_ADD_ERROR);
		}
		int result = this.checkDivideInfo(paraMap);
		if(result != 100) {
			throw new BusinessException("数字之和必须为100");
		}
		paraMap.put("companyId", UserThreadLocal.get().get("companyId"));
		Map<String,Object> oneMap = mIDivideIntoConfigService.queryDivideIntoConfigInfoMapById(
				String.valueOf(paraMap.get("configId")));
		if(null == oneMap) {
			throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
		}
		paraMap.put("updateOperator", UserThreadLocal.get().get("realName"));
		paraMap.put("id", String.valueOf(paraMap.get("configId")));
		paraMap.remove("configId");
		//房源委托人转移
		Object houseEntrustTrans = StringUtils.isEmpty(String.valueOf(paraMap.get("houseEntrustTrans"))) ? "0"
				: paraMap.get("houseEntrustTrans");
		// 拿钥匙人转移
		Object keyMgrTrans = StringUtils.isEmpty(String.valueOf(paraMap.get("keyMgrTrans"))) ? "0"
				: paraMap.get("keyMgrTrans");
		// 签独家人转移
		Object signUkTrans = StringUtils.isEmpty(String.valueOf(paraMap.get("signUkTrans"))) ? "0"
				: paraMap.get("signUkTrans");
		// 传照片人转移
		Object picUploadTrans = StringUtils.isEmpty(String.valueOf(paraMap.get("picUploadTrans"))) ? "0"
				: paraMap.get("picUploadTrans");
		paraMap.put("configStatus", "1");
		paraMap.put("houseEntrustTrans", houseEntrustTrans);
		paraMap.put("keyMgrTrans", keyMgrTrans);
		paraMap.put("signUkTrans",signUkTrans );
		paraMap.put("picUploadTrans",picUploadTrans );
		
		checkDivideTransIsSelected(paraMap, houseEntrustTrans, keyMgrTrans, signUkTrans, picUploadTrans);
		mIDivideIntoConfigService.saveOrUpdateDivideInto(paraMap);
		return BashijuResult.ok();
	}
	
	/**
	 * 校验分成转移是否选择了分成转移对象
	 * @Title: checkDivideTransIsSelected
	 * @author: zuoyuntao  
	 * @Description:校验分成转移是否选择了分成转移对象 
	 * @param paraMap 传入参数对象
	 * @param houseEntrustTrans 房源委托人转移对象
	 * @param keyMgrTrans 拿钥匙人转移对象
	 * @param signUkTrans 签独家人转移对象
	 * @param picUploadTrans 传照片人转移对象     
	 * void
	 */
	private void checkDivideTransIsSelected(Map<String,Object> paraMap,
			Object houseEntrustTrans,Object keyMgrTrans,Object signUkTrans,Object picUploadTrans) {
		String actHouseEntrustMgrRate = String.valueOf(paraMap.get("actHouseEntrustMgrRate"));
		String actRoleKeyMgrRate = String.valueOf(paraMap.get("actRoleKeyMgrRate"));
		String actRoleSignUkRate = String.valueOf(paraMap.get("actRoleSignUkRate"));
		String actRolePivUploadRate = String.valueOf(paraMap.get("actRolePivUploadRate"));
		if(StringUtil.isNotEmpty(actHouseEntrustMgrRate) && !"0".equals(actHouseEntrustMgrRate)) {
			if("0".equals(houseEntrustTrans)) {
				throw new BusinessException("房源委托人必须选择转移对象!");
			}
		}
		if(StringUtil.isNotEmpty(actRoleKeyMgrRate) && !"0".equals(actRoleKeyMgrRate)) {
			if("0".equals(keyMgrTrans)) {
				throw new BusinessException("拿钥匙人必须选择转移对象!");
			}
		}
		if(StringUtil.isNotEmpty(actRoleSignUkRate) && !"0".equals(actRoleSignUkRate)) {
			if("0".equals(signUkTrans)) {
				throw new BusinessException("签独家人必须选择转移对象!");
			}
		}
		if(StringUtil.isNotEmpty(actRolePivUploadRate) && !"0".equals(actRolePivUploadRate)) {
			if("0".equals(picUploadTrans)) {
				throw new BusinessException("传照片人必须选择转移对象!");
			}
		}
	}
	
	/**
	 * 校验分成比例总数
	 * @Title: checkDivideInfo   
	 * @Description: 校验分成比例总数   
	 * @param: paraMap 参数
	 * @return: int 分成比例的总数     
	 * @throws
	 */
	private int checkDivideInfo(Map<String,Object> paraMap) {
		int result = 0;
		result += StringUtils.isEmpty(String.valueOf(paraMap.get("actionRoleIptRate"))) ? 0
				: Integer.parseInt(String.valueOf(paraMap.get("actionRoleIptRate")));
		result += StringUtils.isEmpty(String.valueOf(paraMap.get("actRoleOpMgrRate"))) ? 0
				: Integer.parseInt(String.valueOf(paraMap.get("actRoleOpMgrRate")));
		result += StringUtils.isEmpty(String.valueOf(paraMap.get("actRoleHouseMsgRate"))) ? 0
				: Integer.parseInt(String.valueOf(paraMap.get("actRoleHouseMsgRate")));
		result += StringUtils.isEmpty(String.valueOf(paraMap.get("actRoleCusIptRate"))) ? 0
				: Integer.parseInt(String.valueOf(paraMap.get("actRoleCusIptRate")));
		result += StringUtils.isEmpty(String.valueOf(paraMap.get("actRoleCusMgrRate"))) ? 0
				: Integer.parseInt(String.valueOf(paraMap.get("actRoleCusMgrRate")));
		result += StringUtils.isEmpty(String.valueOf(paraMap.get("actRoleContractMgrRate"))) ? 0
				: Integer.parseInt(String.valueOf(paraMap.get("actRoleContractMgrRate")));
		result += StringUtils.isEmpty(String.valueOf(paraMap.get("actHouseEntrustMgrRate"))) ? 0
				: Integer.parseInt(String.valueOf(paraMap.get("actHouseEntrustMgrRate")));
		result += StringUtils.isEmpty(String.valueOf(paraMap.get("actRoleKeyMgrRate"))) ? 0
				: Integer.parseInt(String.valueOf(paraMap.get("actRoleKeyMgrRate")));
		result += StringUtils.isEmpty(String.valueOf(paraMap.get("actRoleSignUkRate"))) ? 0
				: Integer.parseInt(String.valueOf(paraMap.get("actRoleSignUkRate")));
		result += StringUtils.isEmpty(String.valueOf(paraMap.get("actRolePivUploadRate"))) ? 0
				: Integer.parseInt(String.valueOf(paraMap.get("actRolePivUploadRate")));
		return result;
	}
}
