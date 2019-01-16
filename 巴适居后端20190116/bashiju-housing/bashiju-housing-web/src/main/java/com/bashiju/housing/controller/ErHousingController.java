/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  TestDataController.java   
 * @Package com.bashiju.manage.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年4月10日 下午5:35:24   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.housing.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.bashiju.enums.HouseStatusEnum;
import com.bashiju.enums.HouseTransactionEnum;
import com.bashiju.enums.HouseUsesEnum;
import com.bashiju.housing.service.FormService;
import com.bashiju.housing.service.IBuildingService;
import com.bashiju.housing.service.IErHousingService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @ClassName  HousingController   
 * @Description  二手房源  
 * @author zhaobin
 * @date   2018年4月27日 上午11:46:59   
 *     
 * @Copyright 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@Controller
public class ErHousingController extends BaseController{
	
	@Autowired
	IErHousingService erHousingService;
	@Autowired
	IBuildingService buildingService;

	@Autowired
	FormService formService;
	

	/**
	 * 
	 * @Title tableTitle   
	 * @Description 获取房源日志列表的标题  
	 * @param request
	 * @param response
	 * @return
	 * @param @throws Exception      
	 * @return ModelAndView      
	 * @throws 
	 */
	@RequestMapping(value="housing-log")
	public ModelAndView tableTitle(HttpServletRequest request,HttpServletResponse response)throws Exception{		
		ModelAndView mv = getModelAndView(request, response, "housing-log");
		String list = erHousingService.queryTableTitle("1",true);
		if(!StringUtils.isEmpty(list)) {
			mv.addObject("tableTitle", list);
		}
		return mv;
	}
	/*@RequestMapping(value="houding-log-data")
	public Map<String,Object> tableData(){
		
	}*/
	/**
	 * 
	 * @Title: showHouseInfo （房源详细信息页面）  
	 * @Description: TODO(获取房源相关的基本信息)   
	 * @param: @param m （把返回信息绑定到页面用）
	 * @param: @param houseId(房源信息id)
	 * @param: @param formName(表单的一个固定名称，和房源信息houseUsesId同值)
	 * @param: @return  服务返回的res是一个封装了各信息的Map，   类似：res={houseInfo：{}，customInfo：{customField:{},customValue:{}}，followInfo：[{},{}]，relateagentInfo:[{},{}]}
	 * @return: String (返回房源详细信息页面(showhouse)，页面所带信息：1、关于权限控制的信息，被封装到res.houseInfo里了(houseInfoPermission)；2、页面上要显示的相关的信息(info),3、选部门、选人控件所需数据depts，4、选区域控件所需数据areas
	 *          其中，info里的具体信息有：房源基本信息（houseInfo）,房源自定义的字段和值（customInfo：{字段（customField），值（customValue）}）,房源跟进信息集合（followInfo）,房源相关经纪人信息集合（relateagentInfo）)     
	 * @throws liwen
	 */
	@RequestMapping(value="showHouseInfo")
	public String showHouseInfo(Model m,Long houseId,String formName) {
		Map<String, Object> user = UserThreadLocal.get();
		String cityCode=user.get("cityCode").toString();
		String companyId=user.get("companyId").toString();
		erHousingService.addHousingSee(houseId);
		Map res=erHousingService.queryHouseInfo(houseId,formName,"showinfo");
		m.addAttribute("houseInfoPermission", res.get("houseInfo"));
		m.addAttribute("info", JSON.toJSONString(res));
		m.addAttribute("houseId", JSON.toJSONString(houseId));
		m.addAttribute("depts", JSON.toJSONString(erHousingService.queryDept()));
		m.addAttribute("areas", JSON.toJSONString(erHousingService.queryCommunity()));
		return "showhouse";
	}
	
	/**
	 * 
	 * @Title: getErHouseInfo   
	 * @Description: 二手房源详细信息
	 * @param: @param houseId:二手房源主id
	 * @param: @param formName:表单名称
	 * @param: @return res
	 * (返回值res中包括：1、houseInfo：{}（页面所有显示字段信息）；  2、customInfo：{customField：[](自定义的字段名称的集合),customValue：{}(自定义的字段对应的值)} ；
	 *           3、followInfo：[](跟进信息列表) ；  4、relateagentInfo：[] (全部经纪人信息) ； 5、relateagentMoney：[](各经纪人预期业绩) ；
	 * @return: BashijuResult      
	 * @throws
	 */
	
	@RequestMapping(value="getErHouseInfo")
	@ResponseBody
	public BashijuResult getErHouseInfo(Long houseId,String formName) {
		long start=System.currentTimeMillis();
		erHousingService.addHousingSee(houseId);
		Map<String,Object> res=erHousingService.queryHouseInfo(houseId,formName,"showinfo");
		long end1=System.currentTimeMillis();
		System.out.println("controller1费时："+(end1-start));
		res.put("houseInfoPermission", res.get("houseInfo"));
		long end=System.currentTimeMillis();
		System.out.println("controller费时："+(end-start));
		return BashijuResult.ok(res); 
		
	}
	
	
	/**
	 * 
	 * @Title: getDescriptionField   
	 * @Description: TODO(查询房源特色字段)   
	 * @param: @param transactionTypeId：交易类型ID
	 * @param: @return      
	 * @return: BashijuResult      
	 * @throws
	 */
	@RequestMapping(value="getDescriptionField")
	@ResponseBody
	public BashijuResult getDescriptionField(String transactionTypeId) {
		List res = null;
		
		if(HouseTransactionEnum.SELL.getCode().equals(transactionTypeId)) {//出售
			res = formService.querySelectValue("one","selldescribetype");
		}else if(HouseTransactionEnum.LEASE.getCode().equals(transactionTypeId)){//出租
			res = formService.querySelectValue("one","rentdescribetype");
		}else if(HouseTransactionEnum.LEASE_SELL.getCode().equals(transactionTypeId)) {//租售
			List res1 = formService.querySelectValue("one","selldescribetype");
			List res2 = formService.querySelectValue("one","selldescribetype");
			res.add(res1);
			res.add(res2);
		}
		return BashijuResult.ok(res); 
	}
	
	
	
	/**
	 * 
	 * @Title: getPricedetail（查看房源改价历史）   
	 * @Description: TODO(获取房源修改价格的历史信息记录)   
	  * @param: @param shhId(请求传来的参数1：房源信息id)
	 * @param: @param page (请求传来的参数2：当前页码)
	 * @param: @param limit (请求传来的参数3：每一页记录条数)
	 * @param: @return pageInfo (服务返回的是封装好的当前页的请求数据)     
	 * @return: Map<String,Object> （当前页的房源价格修改的记录）     
	 * @throws liwen
	 */
	@RequestMapping(value="getPricedetail")
	@ResponseBody
	public Map<String,Object> getPricedetail(Long shhId,int page,int limit) {
		Page<Map<String, Object>> pageInfo = erHousingService.queryPricedetail(shhId,page,limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	
	/**
	 * 
	 * @Title: getArchives（查看业主档案信息）   
	 * @Description: TODO(获取业主的档案信息，包括：1、房源（出租、出售、租售）；2、求租；3、求购；)   
	 * @param: @param shhId(请求传来的参数1：房源信息id)
	 * @param: @param type(请求传来的参数2：档案类型（房源：‘house’，求租：‘rent’，求购：‘buy’）)
	 * @param: @param page (请求传来的参数3：当前页码)
	 * @param: @param limit (请求传来的参数4：每一页记录条数)
	 * @param: @return pageInfo (服务返回的是封装好的当前页的请求数据)        
	 * @return: Map<String,Object> （当前页的业主档案信息）        
	 * @throws liwen
	 */
	@RequestMapping(value="getArchives")
	@ResponseBody
	public Map<String,Object> getArchives(Long shhId,String type,int page,int limit) {
		Page<Map<String, Object>> pageInfo = erHousingService.queryArchives(shhId,type,page,limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	
	/**
	 * 
	 * @Title: getLookHouse(查看房源带看记录)   
	 * @Description: TODO(查看该套房源被人带看的详细信息)   
	  * @param: @param shhId(请求传来的参数1：房源信息id)
	 * @param: @param page (请求传来的参数2：当前页码)
	 * @param: @param limit (请求传来的参数3：每一页记录条数)
	 * @param: @return pageInfo (服务返回的是封装好的当前页的请求数据)        
	 * @return: Map<String,Object>  （当前页的带看记录信息）        
	 * @throws liwen
	 */
	@RequestMapping(value="getLookHouse")
	@ResponseBody
	public Map<String,Object> getLookHouse(Long shhId,int page,int limit) {
		Page<Map<String, Object>> pageInfo = erHousingService.queryLookHouse(shhId,page,limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	
	/**
	 * 
	 * @Title: getFollowRecord （查看房源跟进信息）  
	 * @Description: TODO(查看该套房源被跟进的详细信息)   
	 * @param: @param shhId(请求传来的参数1：房源信息id)
	 * @param: @param page (请求传来的参数2：当前页码)
	 * @param: @param limit (请求传来的参数3：每一页记录条数)
	 * @param: @return pageInfo (服务返回的是封装好的当前页的请求数据)        
	 * @return: Map<String,Object> （当前页的跟进记录信息）             
	 * @throws liwen
	 */
	@RequestMapping(value="getFollowRecord")
	@ResponseBody
	public Map<String,Object> getFollowRecord(Long shhId,int page,int limit) {
		Page<Map<String, Object>> pageInfo = erHousingService.queryFollowRecord(shhId,page,limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	
	
	/**
	 * 
	 * @Title: changeExamine （房源业务审核）  
	 * @Description: TODO(房源这块需要提交审核的业务，包括：房源开盘，申请封盘，淘宝（从淘宝淘出），房源状态修改，房源举报)   
	 * @param: @param data（请求传来的参数（一个json对象）：data = {
					type:type,(审核的类型：房源开盘：houseopen，申请封盘：houseclose，淘宝：fromtaobao,房源状态修改:changestatus，房源举报：tipoff）
					examineInfo:examineInfo（相关的审核信息：examineInfo={shhId：房源Id,
					                                                remark:{
								                                             //若是房源开盘
								                                              (transactionTypeId:房源交易类型id，
								                                               transactionType：房源交易类型名称，
								                                               sellingPrice:出售价格，
								                                               rentPrice:出租价格，
								                                               rentPriceTypeId：出租类型id，
								                                               rentPriceTypeName：出租类型名称
								                                               statusId：房源状态，
								                                               entryRecordid：录入人记录id，
								                                               opendiscRecordid：开盘人记录id，
								                                               mainterRecordid：维护人记录id
								                                               )
								                                              //若是申请封盘
								                                               (closeHouseTypeId：封盘类型id，
								                                                closeHouseType：封盘类型名称，
								                                                closeHouseEndTime：封盘到期时间
								                                               )
								                                               //若是淘宝
								                                               ( 
								                                                 mainterRecordid：维护人记录id
								                                               )
								                                               //若是房源状态修改
								                                               ( statusId：新状态id，
								                                                 statusName：新状态名称，
								                                               )
								                                               //若是房源举报
								                                                (accusationTypeId:举报类型id，
								                                                 accusationType：举报类型名称，
								                                                 accusationReason：举报原因，
								                                                 mainterRecordid：维护人记录id
								                                                )
					                                                        }
					                                                content:申请原因（若是房源开盘，原因为：开盘)
			}
	 * @param: @return res (服务返回一个Long型,>0为正常)     
	 * @return: BashijuResult (返回执行情况)     
	 * @throws liwen
	 */
	@RequestMapping(value="businessExamine")
	@ResponseBody
	public BashijuResult businessExamine(@RequestBody Map<String,Object> data) {
		
		String res=erHousingService.businessExamine(data);
		
		if(!StringUtils.isNumeric(res)) {
			throw new BusinessException(res);
		}else if(Long.parseLong(res)>0){
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.SYSTEM_ADD_ERROR);
		}
		
	}
	
	/**
	 * 
	 * @Title: changeSomeStatus(房源的一些信息的改变)   
	 * @Description: TODO(房源一些信息的改变，包括：设置为进淘宝池，改变房源用途，收为私盘)   
	 * @param: @param data（请求传来的参数（一个json对象）：data = {
	 *                  type:type,（要改的类型：设置为进淘宝池：toTaobao,改变房源用途:changeUsage,收为私盘:setPrivatedisc)
					    statusInfo:statusInfo(要改的相关信息：statusInfo={
					                                                 id：房源id，
					                                                //若是改变房源用途
					                                                 (houseUsesId：新的房源用途的id，
					                                                  houseUses：新的房源用途的名称
					                                                  )
					                                                //若是收为私盘
					                                                 (recordid:原维护人记录id)
					                                                }
	 * @param: @return res (服务返回一个Long型,>0为正常)      
	 * @return: BashijuResult (返回执行情况)      
	 * @throws liwen
	 */
	@RequestMapping(value="changeSomeStatus")
	@ResponseBody
	public BashijuResult changeSomeStatus(@RequestBody Map<String,Object> data) {
		
		String res=erHousingService.changeSomeStatus(data);
		if("私盘数量已达到上限".equals(res)) {
			throw new BusinessException("私盘数量已达到上限");
		}else if(Long.parseLong(res)>0){
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.SYSTEM_ADD_ERROR);
		}
		
	}
	
	/**
	 * 
	 * @Title: updateTag (修改房源的一些推荐标签的状态)   
	 * @Description: TODO(修改房源的一些推荐标签的状态，比如：设置或取消房源置顶，设置或取消靓房，设置或取消房源收藏)   
	 * @param: @param shhId（请求传来的参数1：房源信息id）
	 * @param: @param type（请求传来的参数1：修改的类型：设置或取消房源置顶（top），设置或取消靓房（good），设置或取消房源收藏（store））
	 * @param: @param status（设置为1，取消为0）
	 * @param: @return res (服务返回一个Long型,>0为正常)         
	 * @return: BashijuResult (返回执行情况)      
	 * @throws liwen
	 */
	@RequestMapping(value="updateTag")
	@ResponseBody
	public BashijuResult updateTag(Long shhId,String type,int status) {
		
		Long res=erHousingService.updateTag(shhId,type,status);
		if(res>0) {
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.SYSTEM_ADD_ERROR);
		}
		
	}
	
	/**
	 * 
	 * @Title: saveHouseFollow (保存房源跟进、提醒和通知撤单) 
	 * @Description: TODO(保存房源的跟进和提醒信息)   
	 * @param: @param data（请求传来的参数（一个json对象）：data = {
	                      shhId:房源信息id
	                     //若填写跟进
	                      followInfo：followInfo{shhId:房源id，followType：跟进方式id，followTypeName：跟进方式名称，content:跟进内容}
	                    //若填写提醒
	                      remindInfo：remindInfo{receivePeopleId:提醒人id，receivePeopleName：提醒人名称，remindTime:提醒时间，remindContent：提醒内容}
	                    //若选中通知撤单
	                      isCancelNotice:1
	                 }
	 * @param: @return  res (服务返回一个Long型,>0为正常)    
	 * @return: BashijuResult (返回执行情况)      
	 * @throws liwen
	 */
	@RequestMapping(value="saveHouseFollowAndRemind")
	@ResponseBody
	public BashijuResult saveHouseFollow(@RequestBody Map<String,Object> data) {
		
		Long res=erHousingService.saveHouseFollow(data);
		if(res>0) {
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.SYSTEM_ADD_ERROR);
		}
		
	}
	/**
	 * 
	 * @Title: delHouseFollow   
	 * @Description: 删除房源跟进信息
	 * @param: @param id
	 * @param: @return      
	 * @return: BashijuResult      
	 * @throws
	 */
	@RequestMapping(value="delHouseFollow")
	@ResponseBody
	public BashijuResult delHouseFollow(Long id) {
		
		boolean res=erHousingService.delHouseFollow(id);
		if(res) {
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.SYSTEM_ADD_ERROR);
		}
		
	}
	
	/**
	 * 
	 * @Title: setOverallview （设置全景地址）  
	 * @Description: TODO(设置房源全景看房地址)   
	 * @param: @param houseId （请求传来的参数1：房源信息id）
	 * @param: @param address （请求传来的参数2：全景看房的地址）
	 * @param: @return res (服务返回一个Long型,>0为正常)         
	 * @return: BashijuResult  (返回执行情况)      
	 * @throws liwen
	 */
	@RequestMapping(value="setOverallview")
	@ResponseBody
	public BashijuResult setOverallview(Long houseId,String address) {
		
		Long res=erHousingService.saveOverallview(houseId,address);
		if(res==1) {
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.SYSTEM_ADD_ERROR);
		}
		
	}
	
	/**
	 * 
	 * @Title: UpdateHouseOwner (修改业主信息）  
	 * @Description: TODO(修改房源的业主的信息)   
	 * @param: @param data （请求传来的参数（一个json对象）：data = {
	 						id:房源信息id,
					 		owner:业主姓名,
					 		nameType:姓名类型id,
					 		nameTitle:姓名类型名称（先生，女士，公司）
					    }
	 * @param: @return  res (服务返回一个Long型,>0为正常)        
	 * @return: BashijuResult  (返回执行情况)     
	 * @throws liwen 
	 */
	@RequestMapping(value="UpdateHouseOwner")
	@ResponseBody
	public BashijuResult UpdateHouseOwner(@RequestBody Map<String,Object> data) {
		
		Long res=erHousingService.UpdateHouseOwner(data);
		if(res>0) {
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.SYSTEM_ADD_ERROR);
		}
		
	}
	
	/**
	 * 
	 * @Title: saveOwnerPhone (新增业主的电话）  
	 * @Description: TODO(为该房源的业主新增一个电话)   
	 * @param: @param data （请求传来的参数（一个json对象）：data = {
	 						shhId:房源信息id,
					 		phone:电话,
					 		remark:电话类型（本人,配偶，亲戚，朋友）
					    }
	 * @param: @return  res (服务返回一个Long型,>0为正常)      
	 * @return: BashijuResult  (返回执行情况)    
	 * @throws liwen
	 */
	@RequestMapping(value="saveOwnerPhone")
	@ResponseBody
	public BashijuResult saveOwnerPhone(@RequestBody Map<String,Object> data) {
		
		String res=erHousingService.saveOwnerPhone(data);
		if("该电话是黑名单，不能添加！".equals(res)) {
			throw new BusinessException(res);
		}else if(Long.parseLong(res)>0){
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.SYSTEM_ADD_ERROR);
		}
		
	}
	
	/**
	 * 
	 * @Title: updateOwnerPhone (修改业主的电话)   
	 * @Description: TODO(修改 业主的电话信息)   
	 * @param: @param data （请求传来的参数（一个json对象）：data = {
	 						id:业主此条电话记录id,
					 		phone:电话,
					 		remark:电话类型（本人,配偶，亲戚，朋友）
					    }
	 * @param: @return   res (服务返回一个Long型,>0为正常)     
	 * @return: BashijuResult  (返回执行情况)     
	 * @throws liwen
	 */
	@RequestMapping(value="updateOwnerPhone")
	@ResponseBody
	public BashijuResult updateOwnerPhone(@RequestBody Map<String,Object> data) {
		
		String res=erHousingService.updateOwnerPhone(data);
		if("该电话是黑名单，不能添加！".equals(res)) {
			throw new BusinessException(res);
		}else if(Long.parseLong(res)>0){
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.SYSTEM_ADD_ERROR);
		}
		
	}
	
	/**
	 * 
	 * @Title: delOwnerPhone (删除业主的电话)  
	 * @Description: TODO(删除业主的一个电话的信息)   
	 * @param: @param id （请求传来的参数：业主此条电话记录id）
	 * @param: @return  res (服务返回一个boolean型,true为成功，false为失败)    
	 * @return: BashijuResult (返回执行情况)     
	 * @throws liwen
	 */
	@RequestMapping(value="delOwnerPhone")
	@ResponseBody
	public BashijuResult delOwnerPhone(Long id) {
		
		boolean res=erHousingService.delOwnerPhone(id);
		if(res) {
			return BashijuResult.ok();
		}else {
			throw new BusinessException("删除失败");
		}
		
	}
	
	/**
	 * 
	 * @Title: changeHouseOwner(变更业主)   
	 * @Description: TODO(变更房源的业主信息)   
	 * @param: @param data （请求传来的参数（一个json对象）：data = {
	  						houseId:房源信息id，
	 						owner：业主信息（{id：房源信息id，owner：业主姓名，nameType：姓名类型id，nameTitle：姓名类型名称（先生，女士，公司）}），
	 						ownerphone：业主电话信息（{shhId:房源信息id，phone：电话，remark:电话类型（本人,配偶，亲戚，朋友）}）
					    }
	 * @param: @return  res (服务返回一个Long型,0为异常，>0为正常)     
	 * @return: BashijuResult  (返回执行情况)     
	 * @throws liwen
	 */
	@RequestMapping(value="changeHouseOwner")
	@ResponseBody
	public BashijuResult changeHouseOwner(@RequestBody Map<String,Object> data) {
		
		String res=erHousingService.changeHouseOwner(data);
		if("该电话是黑名单，不能添加！".equals(res)) {
			throw new BusinessException(res);
		}else if(Long.parseLong(res)>0){
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.SYSTEM_ADD_ERROR);
		}
		
	}
	
	/**
	 * 
	 * @Title: updatePrice  (修改房源底价) 
	 * @Description: TODO(修改房源的出租底价，出售底价)   
	 * @param: @param data （请求传来的参数（一个json对象）：data = {
	 						shhId:房源信息id,
					 		minSellingPrice:出售底价,
					 		minRentPrice：出租底价，
					    }
	 * @param: @return  res (服务返回一个Long型,0为异常，>0为正常)   
	 * @return: BashijuResult (返回执行情况)       
	 * @throws liwen
	 */
	@RequestMapping(value="updatePrice")
	@ResponseBody
	public BashijuResult updatePrice(@RequestBody Map<String,Object> data) {
		Long res=erHousingService.updatePrice(data);
		if(res==1) {
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.SYSTEM_ADD_ERROR);
		}
		
	}
	
	
	/**
	 * 
	 * @Title: updateAgent (新增或修改相关经纪人)  
	 * @Description: TODO(新增或修改房源的相关经纪人的信息，包括：维护人，拿钥匙人，图片人，独家人，委托人)   
	 * @param: @param data （请求传来的参数（一个json对象）：data = {
	 									//若维护人
	 									 (shhId:房源信息id,
										  recordid:原维护人记录id,
										  peopleid:原维护人id,
										  people:原维护人,
										  type:"wh"
										 )
										//若拿钥匙人
										 (shhId:房源信息id,
										  recordid:原钥匙人记录id,
										  peopleid:原钥匙人id,
										  people:原钥匙人,
										  ysrecordid:原钥匙管理记录id,
										  //具体钥匙信息
										  ysInfo:{shhId:房源信息id,keyCode:钥匙编号，receipt:钥匙收据，reciverId：接受钥匙人id，reciver：接收钥匙人，reciveTime：接收钥匙时间},
										  //若是钥匙人进行修改
										  ysHasChange:{keyCode:修改前的钥匙编号，yspeopleid：修改前的钥匙人id},
										  type:"ys"
										 )
									   //若图片人
									    (shhId:房源信息id,
										 recordid:原图片人记录id,
										 peopleid:原图片人id,
										 people:原图片人,
										 type:"img"
										)
									  //若独家人 
									   (shhId:房源信息id,
									    recordid:原独家人记录id,
									    peopleid:原独家人id,
									    people:原独家人,
									    code:独家编号，
									    time：独家时间，
									    djmoney：独家金额，
									    type:"dj"
									  )
									  //若委托人
									   (shhId:房源信息id,
									    recordid:原委托人记录id,
									    peopleid:原委托人id,
									    people:原委托人,
									    code:委托编号，
									    time：委托时间，
									    type:"wt"
									  )
	                         }
	 * @param: @return res(服务返回一个Long型,0为异常，>0为正常)      
	 * @return: BashijuResult (返回执行情况)     
	 * @throws liwen
	 */
	@RequestMapping(value="updateAgent")
	@ResponseBody
	public BashijuResult updateAgent(@RequestBody Map<String,Object> data) {
		
		Long res=erHousingService.updateAgent(data);
		if(res>0) {
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.SYSTEM_ADD_ERROR);
		}
		
	}
	
	/**
	 * 
	 * @Title: uploadFile （房源附件上传）  
	 * @Description: TODO(上传房源的一些附件，如：房源图片，相关图片，视频等)   
	 * @param: @param request（服务端上传文件用到,里面带有参数：pictureType：图片类型id,pictureName：图片类型名称，attachType：附件类型（01是房源图片，02是房源相关图片，03是房源视频），shhId：房源主id ，houseId：房源编号）
	 * @param: @return res (服务返回一个Long型,0为异常，>0为正常)     
	 * @return: BashijuResult (返回执行情况)     
	 * @throws liwen
	 */
	@RequestMapping(value="uploadFile")
	@ResponseBody
	public BashijuResult uploadFile(HttpServletRequest request) {
			String res = erHousingService.saveFile(request);
			if(StringUtils.isNotEmpty(res)) {
				return BashijuResult.ok(res);
			}else {
				throw new BusinessException("上传错误");
			}
	}
	
	/**
	 * 
	 * @Title: getFile  (获取房源附件) 
	 * @Description: TODO(查询出文件信息，包括该房源下的通过了审核的房源图片、相关图片、视频等)   
	 * @param: @param attachType（请求传来的参数1：附件类型（图片：01，相关图片：02，视频：03））
	 * @param: @param shhId（请求传来的参数2：房源信息id）
	 * @param: @return imgs（服务返回符合条件的文件信息集合）     
	 * @return: BashijuResult （返回信息集合）     
	 * @throws liwen
	 */
	@RequestMapping(value="getFile")
	@ResponseBody
	private BashijuResult getFile(String attachType,Long shhId){
		List imgs = erHousingService.queryFile(shhId,attachType);
		return BashijuResult.ok(imgs);
	}
	/**
	 * 
	 * @Title: downloadFile  (单个下载) 
	 * @Description: TODO(下载单个附件)   
	 * @param: @param request
	 * @param: @param response
	 * @param: @param imgId :图片id     
	 * @return: void      
	 * @throws
	 */
	@RequestMapping(value="downloadFile")
	public void downloadFile(HttpServletRequest request,HttpServletResponse response,Long imgId) {
		String filepath = erHousingService.queryFileById(imgId);
		File file = new File(filepath);
		   String name = file.getName();
		   try{
			   if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {  
				   name = URLEncoder.encode(name, "UTF-8");  
				} else {  
					name = new String(name.getBytes("UTF-8"), "ISO8859-1");  
				}
		   }catch(Exception e){
			   e.printStackTrace();
		   }
		    
		   response.setCharacterEncoding("utf-8");
	       response.setContentType("multipart/form-data");
	       response.setHeader("Content-Disposition", "attachment;fileName="
	                + name);
	        try {
	            InputStream inputStream = new FileInputStream(file);
	            OutputStream os = response.getOutputStream();
	            byte[] b = new byte[2048];
	            int length;
	            while ((length = inputStream.read(b)) > 0) {
	                os.write(b, 0, length);
	            }
	 
	            os.close();
	 
	            inputStream.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return;
	}
	
	/**
	 * 
	 * @Title: updateAttachment(修改房源附件信息) 
	 * @Description: TODO(修改房源附件的一些相关信息，如房源图片、相关图片的类型，是否设为封面等)   
	 * @param: @param data （请求传来的参数（一个json对象）：类似data = { shhId:房源信息id，
	 														   imgInfos:{id:图片id,
															  			 isCover:是否设置为封面,
															  			 pictureType:图片类型id,
															  			 pictureName:图片类型名称
															            }
					                                         }
	 * @param: @return  res(服务返回一个Long型,0为异常，>0为正常)     
	 * @return: BashijuResult （返回信息集合）     
	 * @throws liwen
	 */
	@RequestMapping(value="updateAttachment")
	@ResponseBody
	public BashijuResult updateAttachment(@RequestBody Map<String,Object> data) {
		Long res = erHousingService.updateAttachment(data);
		if(res>0) {
			return BashijuResult.ok();
		}else {
			throw new BusinessException("修改失败");
		}
		
	}
	/**
	 * 
	 * @Title: delFile (删除文件)  
	 * @Description: TODO(删除文件)   
	 * @param: @param imgIds （多个用逗号隔开）     
	 * @return: void      
	 * @throws
	 */
	@RequestMapping(value="delFile")
	@ResponseBody
	public BashijuResult delFile(String imgIds,Long shhId) {
		boolean res = erHousingService.delFile(imgIds,shhId);
		if(res) {
			return BashijuResult.ok();
		}else {
			throw new BusinessException("删除失败");
		}
	}
	
	/**
	 * 
	 * @Title: saveHouseDescription （新增或修改房源描述） 
	 * @Description: TODO(新增或修改房源的描述信息)   
	 * @param: @param data （请求传来的参数（一个json对象）：data = {
	 						shhId:房源信息id,
					 		description:描述信息,
					 		remark：备注，
					 	   //若修改
					 	    id:原房源描述记录id
					    }
	 * @param: @return  res(服务返回一个Long型,0为异常，>0为正常)     
	 * @return: BashijuResult （返回信息集合）     
	 * @throws liwen
	 */
	@RequestMapping(value="saveHouseDescription")
	@ResponseBody
	public BashijuResult saveHouseDescription(@RequestBody List<Map<String,Object>> data) {
		
		boolean res=erHousingService.saveHouseDescription(data);
		if(res) {
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.SYSTEM_ADD_ERROR);
		}
		
	}
	
	/**
	 * 
	 * @Title: getHouseDescription (查询房源描述）  
	 * @Description: TODO(查询该房源的描述信息列表)   
	 * @param: @param shhId(请求传来的参数1：房源信息id)
	 * @param: @param page (请求传来的参数2：当前页码)
	 * @param: @param limit (请求传来的参数3：每一页记录条数)
	 * @param: @return pageInfo (服务返回的是封装好的当前页的请求数据)        
	 * @return: Map<String,Object> （当前页的房源描述信息）             
	 * @throws liwen
	 */
	@RequestMapping(value="getHouseDescription")
	@ResponseBody
	public Map<String,Object> getHouseDescription(Long shhId,int page,int limit) {
		Page<Map<String, Object>> pageInfo = erHousingService.queryHouseDescription(shhId,page,limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
		
	}
	
	
	
	/**
	 * 具体的跳转逻辑
	 * @param pageName
	 * @return 视图
	 */
	@RequestMapping(value="{pageName}",method=RequestMethod.GET)
	public String toPage(@PathVariable("pageName") String pageName,Model m)
	{
		List list = erHousingService.queryDept();
		if("showhouse".equals(pageName)) {
			m.addAttribute("deptListJson", JSON.toJSONString(list));
			return pageName;
		}
		m.addAttribute("houseList",HouseUsesEnum.enumList);
		return "/house/"+pageName;
	}
	/**
	 * 
		 * 进入房源编辑主页面 
		 * @Description: 进入房源编辑主页面 
		 * @param m
		 * @param houseId 房源id
		 * @param formName 表单类型名称
		 * @return 
		 * @return: String
	 */
	@RequestMapping(value="editHouse")
	public String editHouse(Model m,Long houseId,String formName) {
		Map res=erHousingService.queryHouseInfo(houseId,formName,"updateinfo");
		LinkedList<Map<String,Object>> depts = erHousingService.queryDept();
		List<Map<String,Object>> labelList = erHousingService.queryLabel();
		m.addAttribute("info", JSON.toJSONString(res));
		m.addAttribute("houseId", houseId);
		m.addAttribute("formName", formName);
		m.addAttribute("labelList", labelList);
		//m.addAttribute("depts", JSON.toJSONString(depts));
		m.addAttribute("dynamicColumnList",formService.queryCustomField(formName));
		return "/house/editHouse";
	}
	/**
	 * 
		 * 进入二手房源列表页面
		 * @Description: 进入二手房源列表页面 
		 * @param request
		 * @param response
		 * @return 
		 * @return: ModelAndView
	 */
	@RequestMapping(value="list")
	public ModelAndView goHouseList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv=getModelAndView(request, response, "house/list");
		//mv.addObject("communitList", JSON.toJSONString(buildingService.getRegionList()));
		mv.addObject("houseList",HouseUsesEnum.enumList);
		mv.addObject("houseStatusList",HouseStatusEnum.enumList);
		mv.addObject("communitList", JSON.toJSONString(erHousingService.queryCommunity()));
		mv.addObject("departmentList", JSON.toJSONString(erHousingService.queryDept()));
		mv.addObject("dynamicColumnList",formService.queryCustomField("house"));
		List<Map<String,Object>> labelList = erHousingService.queryLabel();
		mv.addObject("labelList", labelList);
		return mv;
	}
	/**
	 * 
		 * 房源标签列表
		 * @Description: 房源标签列表
		 * @return 
		 * List<Map<String,Object>>
	 */
	@RequestMapping(value="queryHouseLabel")
	@ResponseBody
	public List<Map<String,Object>> queryHouseLabel(){
		List<Map<String,Object>> labelList = erHousingService.queryLabel();
		return labelList;
	}
	/**
	 * 
		 * 添加二手房源
		 * @Description: 添加二手房源 
		 * @param dataJson json格式:{"houseUsesId":"house","houseUses":"住宅","status":"有效","discStatus":"公盘","communityId":"11","buildingsId":"30","bulidingUnitId":"61","buildingHouseId":"2458","buildingsName":"09号楼","buildingsUnitName":"1单元","buildingHouseName":"21001","owner":"更多","nameTitle":"先生","phone":"12345678","relateName":"本人","statusId":"1","discStatusId":"2","nameType":"1","relateType":"1","titles":"豆腐干豆腐","labeld":"满五年;","isSynchron":"1","transactionType":"出售","sellingPrice":"100","rentPriceTypeName":"元/月","room":"1","hall":"1","toilet":"1","kitchen":"1","balcony":"1","buildSpace":"100","useSpace":"90","floorCount":"2","totalLayers":"20","orientation":"东","elevatorCount":"2","householdCount":"4","decoration":"毛胚","matching":"床;","actuality":"空置;","houseType":"低层","structure":"剪力墙结构","buildDates":"2010","propertyType":"商品房","certificateDate":"2018-06-08","propRightsLen":"70年","taxpayer":"买方承担","isSatisfyTwoYear":"1","isOnly":"1","isUrgent":"1","isFullAmount":"1","viewingType":"提前预约","resourceType":"上门","entrustCode":"111","payType":"押一年付","percentage":"30","minSellingPrice":"100","minRentPrice":"900","transactionTypeId":"1","rentPriceTypeId":"1","orientationId":"1","decorationId":"1","houseTypeId":"1","structureId":"1","propertyTypeId":"1","propRightsLenId":"1","taxpayerId":"1","viewingTypeId":"1","resourceTypeId":"1","payTypeId":"1"}
		 * @return 
		 * @return: BashijuResult
	 */
	@RequestMapping(value="addHouse")
	@ResponseBody
	public BashijuResult addHouse(String dataJson) {
		Map data=JSON.parseObject(dataJson, Map.class);
		String formName=data.get("houseUsesId").toString();
		
		erHousingService.addHousing(data,formName);
		return BashijuResult.ok();
	}
	/**
	 * 
		 * 判断房源是否已添加
		 * @Description: 判断房源是否已添加
		 * @param houseId 房源id
		 * @return 房源数量
		 * @return: BashijuResult
	 */
	@RequestMapping(value="queryCountHousing")
	@ResponseBody
	public BashijuResult queryCountHousing(Long houseId) {
		long count=erHousingService.getHousingCount(houseId);
		return BashijuResult.ok(count);
	}
	/**
	 * 
		 * 查询二手房源列表
		 * @Description: 查询二手房源列表
		 * @param condition 查询条件，json格式{"transactionTypeId":"4","buildingUnit":"2","buildingHouse":"","areas":[{"value":"3","type":"3"}],"status":{"effective":3},"myPrivate":"","myHousing":"","myCollection":"","todaySee":"","managePlate":"","unConfirm":"","hunting":"1","unHunting":"","isPicture":"","isVideo":"","isKey":"","unKey":"","isUnique":"","isIntrust":"","closeDiscWay":"","closeDiscPhone":"","cancelNoticeFlg":"","newHousing":"","isUrgent":"","isSchoolRoom":"","isGood":"","isSynchron":"","contractEnd":"","reducePriceFlg":"","viewing":""}
		 * @param start 开始行
		 * @param length 每页长度
		 * @param draw
		 * @return 
		 * @param @throws Exception 
		 * @return: Map<String,Object> {recordsFiltered=1, data=Page{count=true, pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1, reasonable=false, pageSizeZero=false}[{addTime=2018-06-05 13:54:10.0, companyName=巴适居网络有限公司, buildingsName=1号楼, totalLayers=10, hall=1, isSchoolRoom=1, balcony=1, allFollowTime=2018-06-05 13:54:10.0, examineStatus=0, operator=超级管理员, houseUses=住宅, areaName=西山区, levelType=C, communityName=玛卡切片, id=14116, decoration=毛胚, operatorId=13320, labeld=满五年;, keyCode=5544, toilet=1, mainterFllowTime=2018-06-05 13:54:10.0, mainterDept=A组, updateInfo=updateInfo, houseId=530112000003, discStatus=公盘, permissionArea=BSJ0103, buildDates=2014, regionName=南亚风情园, taobaoStatus=淘宝池, useSpace=54, buildingsUnitName=2单元, matching=床;衣柜;, taobaoStatusId=1, sellingPrice=60, houseUsesId=zhuzhai, entrustCode=dfds, propertyType=商品房, kitchen=1, rentPrice=800, buildingHouseName=501, orientation=东, transactionTypeId=1, floorCount=5, updateTime=2018-06-08 11:57:20.0, room=1, transactionType=出售, buildSpace=60, discStatusId=2, resourceType=上门}], draw=16, recordsTotal=1}
	 */
	@RequestMapping(value="searchHouseList")
	@ResponseBody
	public Map<String,Object> searchHouseList(String condition,int start,int length,int draw) throws Exception{
		int page=1;
		if (start>0) {
			start++;
			page=start/length;
			if (page>0&&(start%length)>0) {
				page++;
			}
		}
		//condition="{\"transactionTypeId\":3,\"status\":{\"isAll\":\"\",\"effective\":1}}";
		Page<Map<String, Object>> pageInfo = erHousingService.queryHousingTableDatas(condition, page, length);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("recordsTotal", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("draw", draw);
		map.put("recordsFiltered", pageInfo.getTotal());
		return map;
	}
	/**
	 * 
		 * 查询座栋信息
		 * @Description: 查询座栋信息
		 * @param buildingId 座栋id
		 * @param discStatus 盘状态:1、私盘,2、公盘
		 * @return {success:true成功、false失败,msg:错误原因,data:{floor:楼层，floorTotal:楼高，unitElevatorCount:几梯，unitHouseCount:几户}}
		 * @return: BashijuResult
	 */
	@RequestMapping(value="queryBuildingInfo")
	@ResponseBody
	public BashijuResult queryBuildingInfo(Long buildingId,String discStatus) {
		Map<String, Object> buildingInfo=erHousingService.queryBuildingInfo(buildingId,discStatus);
		return BashijuResult.ok(buildingInfo);
	}
	/**
	 * 
		 * 查询用户自定义隐藏的列名称
		 * @Description: 查询表格隐藏的列名称
		 * @param formName 表单名称
		 * @return  返回columns数组["id","name"]
		 * @return: BashijuResult
	 */
	@RequestMapping(value="getTableColHiddenList")
	@ResponseBody
	public BashijuResult getTableColHiddenList(String formName) {
		Map<String, Object> result=erHousingService.queryTableHiddenField(formName);
		return BashijuResult.ok(result);
	}
	/**
	 * 
		 * 保存用户自定义列设置
		 * @Description: 保存用户自定义列设置
		 * @param formName 表单名称
		 * @param columns 隐藏的列名称，用英文逗号分隔
		 * @return 
		 * @return: BashijuResult
	 */
	@RequestMapping(value="setTableColHidden")
	@ResponseBody
	public BashijuResult setTableColHidden(String formName,String columns) {
		boolean result=erHousingService.saveTableHiddenInfo(formName, columns);
		return BashijuResult.ok();
	}
	/**
	 * 
		 * 批量删除房源
		 * @Description: 批量删除房源
		 * @param houseIds 房源id，多个用英文逗号分隔
		 * @return 
		 * @return: BashijuResult
	 */
	@RequestMapping(value="deleteHousing")
	@ResponseBody
	public BashijuResult deleteHousing(String houseIds) {
		erHousingService.deleteHousing(houseIds);
		return BashijuResult.ok();
	}
	/**
	 * 
		 * 修改房源信息
		 * @Description: 修改房源信息
		 * @param dataJson json格式{customData:{动态表单值},id:1,transactionTypeId:1,....}
		 * @param formName 表单名称
		 * @return 
		 * @return: BashijuResult
	 */
	@RequestMapping(value="updateHousing")
	@ResponseBody
	public BashijuResult updateHousing(String dataJson, String formName) {
		if (StringUtils.isNotEmpty(dataJson)&&StringUtils.isNotEmpty(formName)) {
			Map<String,Object> data=JSON.parseObject(dataJson, Map.class);
			erHousingService.updateHousing(data,formName);
			return BashijuResult.ok();
		}
		throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
	}
	
	/**
	 * 
		 * 批量下载图片
		 * @Description: 批量下载图片
		 * @param request
		 * @param  response
		 * @param  houseId 房源id
		 * @param  imageIds 图片编号，多个用英文逗号分隔
		 * @param @throws IOException 
		 * @return: void
	 */
	@RequestMapping(value="downloadImages")
	public void downloadImages(HttpServletRequest request,HttpServletResponse response,Long houseId, String imageIds) throws IOException{
		
       
        if (StringUtils.isNotEmpty(imageIds)) {
        	Map<String, Object> map=erHousingService.queryImagePath(houseId, imageIds);
        	if (map==null) {
				return;
			}
        	response.setCharacterEncoding("UTF-8");  
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);//设置为下载application/x-download  
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
        	 response.setDateHeader("Expire", 0);
        	 String fileName=map.get("titles").toString()+map.get("houseId").toString();
             response.setHeader("content-disposition", "attachment;filename="+fileName+".zip");
             List<Map<String,Object>> pathArr=(List<Map<String,Object>>) map.get("paths"); 
			 ZipOutputStream  zos = new ZipOutputStream(response.getOutputStream()); 
			for (Map path : pathArr) {
				if (path!=null) {
					String value=path.get("path").toString();
					String name=value.substring(value.lastIndexOf("/")+1);
					// 网络请求所需变量
			        InputStream in = null;
			        URL url = new URL(value);
		            // 根据Url打开地址，以utf-8编码的形式返回输入流
		            in = url.openStream();			           			           						 
	                ZipEntry ze = new ZipEntry(name);  
	                zos.putNextEntry(ze);  

	                byte[] data = new byte[1024];  
	                int length = in.read(data);
	                while (length != -1) {                    
	                    zos.write(data,0,length);  
	                    length = in.read(data);	                  
	                }   
	                zos.closeEntry(); 
	                in.close();
				}
			}
			if (zos!=null) {
				zos.flush(); 
				zos.close();
			}
		}
	}
	/**
	 * 
		 * 根据座栋单元id查询房源销控列表
		 * @Description: 根据座栋单元id查询房源销控列表 
		 * @param  buildIngUnitId 座栋单元id
		 * @return transactionType:交易类型名称,transactionTypeId:交易类型id,status:房源状态,statusId:房源状态id,
		 * id:房源id,houseId:房源编号,buildSpace:建筑面积,sellingPrice:售价,rentPrice:租价,room:几室,hall:几厅,toilet:几卫,
		 * kitchen:几厨,balcony:几阳,floorCount:楼层,buildingHouseName:房号,buildingHouseId:房号id 
		 * @return: BashijuResult
	 */
	@RequestMapping(value="queryHouseListByUnitId")
	@ResponseBody
	public BashijuResult queryHouseListByUnitId(Long buildIngUnitId) {
		List<Map<String, Object>> list=erHousingService.queryHouseListByUnitId(buildIngUnitId);
		return BashijuResult.ok(list);
	}
	/**
	 * 
		 * 查询房源私密数据 
		 * @Description:查询房源私密数据 
		 * @param houseId 房源id
		 * @param type 1：为门牌号查询，2：为业主电话查询，3：为房源价格查询
		 * @return type为1返回{buildingsId:'',buildingsName:'',bulidingUnitId:'',buildingsUnitName:'',buildingHouseId:'',buildingHouseName:''}
		 * type为2返回[{phone:'',remark:''}]
		 * type为3返回{sellingPrice:'',rentPrice:''}
		 * BashijuResult {success:true,msg:success,data:{}}
	 */
	@RequestMapping(value="queryHousePrivateInfo")
	@ResponseBody
	public BashijuResult queryHousePrivateInfo(Long houseId, Integer type) {
		if (houseId!=null&&type!=null&&houseId>0&&type>0) {
			Object result=erHousingService.queryHousePrivateInfo(houseId,type);
			if(result instanceof List) {
				List<Map<String,Object>> list = (List)result;
				
				if(list.get(0).containsKey("examineNum") && "1".equals(list.get(0).get("examineNum").toString())){
					throw new BusinessException("已封盘，不能看");
				}
			}
			return BashijuResult.ok(result);
		}	
		throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
	}
	/**
	 * 
		 * 批量转移维护人
		 * @Description: 批量转移维护人
		 * @param houseIds 房源id，多个用英文逗号分隔
		 * @param userId 转移给用户id
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping(value="houseTransfer")
	@ResponseBody
	public BashijuResult houseTransfer(String houseIds, long userId) {
		if (StringUtils.isNotEmpty(houseIds)&&userId>0) {
			erHousingService.houseTransfer(houseIds,userId);
			return BashijuResult.ok();
		}		
		throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
	}
	/**
	 * 
		 * 批量修改房源是否与外网同步
		 * @Description: 批量修改房源是否与外网同步 
		 * @param houseIds 房源id，多个用英文逗号分隔
		 * @param state 1、同步，0、不同步
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping(value="houseSynchron")
	@ResponseBody
	public BashijuResult houseSynchron(String houseIds, Integer state) {
		if (StringUtils.isNotEmpty(houseIds)) {
			erHousingService.houseSynchron(houseIds,state);
			return BashijuResult.ok();
		}
		throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
	}
	/**
	 * 
		 * 搜索学校表格数据
		 * @Description:搜索学校表格数据
		 * @param name 学校名称
		 * @param page 当前页数
		 * @param limit 每页条数
		 * @return {count:10,code:0,msg:'',data:[{id:1,schoolName:'',schoolAddress:''}]}
		 * Map<String,Object>
	 */
	@RequestMapping(value="querySchoolTable")
	@ResponseBody
	public Map<String,Object> querySchoolTable(String name,int page,int limit) {
		Page<Map<String, Object>> pageInfo = erHousingService.querySchoolTable(name,page,limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
		
	}
	/**
	 * 
		 * 匹配客源
		 * @Description: 匹配客源 
		 * @param conditions 匹配条件 {areas:[{type:1,value:区域编号},{type:2,value:片区id},{type:3,value:小区id}],minSpace:最小面积,maxSpace:最大面积,minSellingPrice:最小售价,maxSellingPrice:最大售价,
		 *   minRentPrice:最小租价,maxRentPrice:最大租价,minRoom:最小房间数,maxRoom:最大房间数,minFloor:最小楼层,maxFloor:最大楼层,transactionTypeId:房源交易类型}
		 * @param page 当前页数
		 * @param limit 每页条数
		 * @return `status`,id, areas ,minSpace,maxSpace,minPrice,maxPrice,minRoom,maxRoom,minFloorCount,maxFloorCount,maintainer
		 * Map<String,Object>
	 */
	@RequestMapping(value="matchingCustomer")
	@ResponseBody
	public Map<String,Object> matchingCustomer(String conditions, int page, int limit) {
		//conditions="{\"transactionTypeId\":1,\"minSpace\":60,\"areas\":[{\"type\":1,\"value\":\"530114\"}]}";
		Page<Map<String, Object>> pageInfo = erHousingService.queryMatchingCustomer(conditions,page,limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	/**
	 * 
		 * 房源成交
		 * @Description: 房源成交
		 * @param dataJson 成交信息{dealType:成交类型,agreementId:合同编号,demandId:需求编号,shhid:房源id,owner:业主,custName:客户,traderId:成交人id,price:成交价格,dealTime:成交日期,
		 * landCertifcate:土地证号,propCertificate:产权证号,referenceId:权证人id,reference:权证人姓名,commissionPrice:应收佣金,supplementInfo:补充条款,isLoan:是否有贷款,isautoDivide:0不自动分成1自动分成}
		 * @param commissionsJson 佣金信息[{moneyType:费用类型,moneyProj:费用项目,price:费用金额,payerType:缴费人类型(0业主,1客户),estimatePayTime:预计缴费时间,remark:备注}]
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping(value="dealHousing")
	@ResponseBody
	public BashijuResult dealHousing(String dataJson, String commissionsJson,String crossJson) {
		//dataJson="{\"dealType\":\""+DealTypeEnum.BUSINESSDEAL.getCode()+"\",\"agreementId\":\"erg1427\",\"demandId\":\"QG-18062916485702\",\"shhid\":5,\"owner\":\"白皮纹\",\"custName\":\"五十块\","
		//		+ "\"traderId\":13320,\"price\":100,\"dealTime\":\"2018-06-25\",\"landCertifcate\":\"123\",\"referenceId\":\"456\",\"referenceId\":13154,\"isautoDivide\":\"1\","
		//		+ "\"reference\":\"城市管理员1号13154\",\"commissionPrice\":1000,\"supplementInfo\":\"dchgfh\",\"isLoan\":1}";
		//commissionsJson="[{\"moneyType\":\"0\",\"moneyProjId\":1,\"moneyProjName\":\""+CommissionProjEnum.AGENCYFEE.getDesc()+"\",\"price\":200,\"payerType\":\"0\",\"estimatePayTime\":\"2018-06-28\",\"remark\":\"不是个\"}]";
		if (StringUtils.isNotEmpty(dataJson)) {
			erHousingService.dealHousing(dataJson, commissionsJson,crossJson);
			return BashijuResult.ok();
		}
		throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
	}
	
	/**
	 * 
		 * 查询房源采集列表
		 * @Description: 查询房源采集列表） 
		 * @param condition {transactionTypeId:交易类型,communityName:小区名称,minPrice:最低价格,maxPrice:最大价格,
		 * minSpace:最小面积,maxSpace:最大面积,lookStatus:查看状态,inputStatus:录入状态,checkStatus:检查无效状态}
		 * @param page 当前页面
		 * @param limit 页面大小
		 * @return 
		 * Map<String,Object>
	 */
	@RequestMapping("/queryCrawlerList")
	@ResponseBody
	public Map<String,Object> queryCrawlerList(String condition,int page,int limit){
		Page<Map<String, Object>> pageInfo = erHousingService.queryCrawlerList(condition,page,limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
		
	}
	/**
	 * 
		 * 判断电话是否在黑名单，不在则返回业主电话、小区id相关的房源列表
		 * @Description: 判断电话是否在黑名单，不在则返回业主电话、小区id相关的房源列表
		 * @param phone 业主电话
		 * @param communityId 小区id
		 * @return  {success:true成功、false失败,msg:错误信息,data[id:房源id,houseId:房源编号,transactionType:交易类型,status:房源状态,sellingPrice:售价,rentPrice:租价,recordTime:录入时间,maintainer:维护人]}
		 * BashijuResult
	 */
	@RequestMapping("/queryHouseByPhone")
	@ResponseBody
	public BashijuResult queryHouseByPhone(String phone, Long communityId){
		List<Map<String, Object>> list=erHousingService.queryHouseByPhone(phone, communityId);
		return BashijuResult.ok(list);
	}
	/**
	 * 
		 * 根据座栋单元查询房号销控
		 * @Description: 根据座栋单元查询房号销控
		 * @param buildingUnitId 单元id
		 * @param companyId 公司id
		 * @return {floorTotal:单元总楼层,houseStartNumber:房号开始数,floorStartNumber:楼层开始数,unitHouseCount:每层户数,
		 * houseList:[{houseNO:房号id,houseNumber:房号,floor:房号所在楼层,code:户数编号,hid:房源id,houseId:房源编号,transactionType:交易类型(出租、出售),status:房源状态(有效、暂缓、我售等),sellingPrice:出售价格(分),buildSpace:面积,room:几室,hall:几厅,kitchen:几厨,toilet:几卫,balcony:几阳}]}
		 * BashijuResult
	 */
	@RequestMapping("/queryHouseSalesStatusByUnit")
	@ResponseBody
	public BashijuResult queryHouseSalesStatusByUnit(Long buildingUnitId,String companyId){
		Map<String, Object> data=erHousingService.queryHouseSalesStatusByUnit(buildingUnitId,companyId);
		return BashijuResult.ok(data);
	}

	
	@RequestMapping(value="saveAgentFeedback")
	@ResponseBody
	public BashijuResult saveAgentFeedback(@RequestBody Map<String,Object> data) {
		Long res = erHousingService.saveAgentFeedback(data);
		if(res>0) {
			return BashijuResult.ok();
		}else if(res==0){
			throw new BusinessException("保存失败,你还没带看过该房源，不能写反馈！");
		}else {
			throw new BusinessException("保存失败");
		}
		
	}
	@RequestMapping(value="delAgentFeedback")
	@ResponseBody
	public BashijuResult delAgentFeedback(int id) {
		boolean res = erHousingService.delAgentFeedback(id);
		if(res) {
			return BashijuResult.ok();
		}else {
			throw new BusinessException("删除失败");
		}
		
	}
	
	@RequestMapping("/getAgentFeedback")
	@ResponseBody
	public Map<String,Object> getAgentFeedback(String condition,int page,int limit){
		Page<Map<String, Object>> pageInfo = erHousingService.queryAgentFeedback(condition,page,limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
		
	}
	
	

	/**
	 * 
		 * 生成并发送房源绑定码
		 * @Description: 生成并发送房源绑定码
		 * @param houseId 房源id
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping("/sendBindCode")
	@ResponseBody
	public BashijuResult sendBindCode(Long houseId){
		erHousingService.sendBindCode(houseId);
		return BashijuResult.ok();
	}
	
	@RequestMapping("/getBuild")
	@ResponseBody
	public BashijuResult getBuild(Long communityId){
		List<Map<String,Object>> res = erHousingService.queryBuild(communityId);
		return BashijuResult.ok(res);
	}
	@RequestMapping("/getUnit")
	@ResponseBody
	public BashijuResult getUnit(Long buildId){
		List<Map<String,Object>> res = erHousingService.queryUnit(buildId);
		return BashijuResult.ok(res);
	}
	
	//返回下拉值
	@RequestMapping("/getSelectValue/{type}")
	@ResponseBody
	public BashijuResult getSelectValue(HttpServletRequest request,HttpServletResponse response,Model m,@PathVariable("type") String type,@RequestParam(required=false) String param){
		List selectitems = formService.querySelectValue(type,param);
		if ("many".equals(type)) {
			Map<String, Object> map=new HashMap<>();
			for (Object o : selectitems) {
				Map<String, Object> item=(Map<String, Object>) o;
				for (String key : item.keySet()) {
					map.put(key, item.get(key));
					break;
				}			
			}
			return BashijuResult.ok(map);
		}
		return BashijuResult.ok(selectitems);
		
	}
	
	
	//返回区间值
	@RequestMapping("/getRegionValue/{type}")
	@ResponseBody
	public BashijuResult getRegionValue(HttpServletRequest request,HttpServletResponse response,Model m,@PathVariable("type") String type,@RequestParam(required=false) String tag,@RequestParam(required=false) String param){
		List values = formService.queryRegionValue(type,tag,param);
		return BashijuResult.ok(values);
		
	}
	
	/**
	 * 
		 * 录入房源是否默认私盘
		 * @Description: 录入房源是否默认私盘
		 * @return 
		 * List<Map<String,Object>>
	 */
	@RequestMapping("/queryHouseDiscStatus")
	@ResponseBody
	public BashijuResult queryHouseDiscStatus(){
		List<Map<String, Object>> result=erHousingService.queryHouseDiscStatus();
		return BashijuResult.ok(result);
	}
	/**
	 * 
		 * 首页查询最新的房源
		 * @Description: 首页查询最新的房源
		 * @param limit 显示条数
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping("/queryHouseByTime")
	@ResponseBody
	public BashijuResult queryHouseByTime(Integer limit){
		List<Map<String, Object>> result=erHousingService.queryHouseByTime(limit);
		return BashijuResult.ok(result);
	}
	/**
	 * 
		 * 首页统计待跟进数量
		 * @Description: 首页统计待跟进数量
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping("/queryUnFollowCountByUser")
	@ResponseBody
	public BashijuResult queryUnFollowCountByUser(){
		Integer result=erHousingService.queryUnFollowCountByUser();
		return BashijuResult.ok(result);
	}
	/**
	 * 
		 * 首页统计当月任务完成情况
		 * @Description: 首页统计当月任务完成情况
		 * @return 
		 * BashijuResult {"success":true,"msg":"操作成功",
		 * "data":{"sellCustomerAdd":新增客源任务量,"doDealMoney":业绩完成金额(元),"sellHouseAdd":新增房源任务量,"doSellCustomerAdd":新增客源完成量,
		 * "doSellHouseShowed":房源带看完成量,"sellHouseShowed":房源带看任务量,"doSellHouseAdd":房源新增完成量,"dealMoney":业绩任务金额}}
	 */
	@RequestMapping("/queryMonthtaskByUser")
	@ResponseBody
	public BashijuResult queryMonthtaskByUser(){
		Map<String, Object> result=erHousingService.queryMonthtaskByUserId();
		return BashijuResult.ok(result);
	}
	/**
	 * 
		 * 查询成交佣金
		 * @Description: 查询成交佣金 
		 * @param houseId 房源id
		 * @param contractType 合同类型
		 * @param dealPrice 成交金额
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping("/queryCommissionPrice")
	@ResponseBody
	public BashijuResult queryCommissionPrice(Long houseId, String contractType, Integer dealPrice) {
		Integer result=erHousingService.queryCommissionPrice(houseId,contractType,dealPrice);
		return BashijuResult.ok(result);
	}
}
