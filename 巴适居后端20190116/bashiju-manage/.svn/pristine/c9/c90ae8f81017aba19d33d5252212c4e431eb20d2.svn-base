/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  SchoolBlockMgrController.java   
 * @Package com.bashiju.manage.controller   
 * @Description: 学区管理控制器   
 * @author: zuoyuntao     
 * @date:   2018年5月17日 上午10:30:10   
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bashiju.manage.global.ManageGlobal;
import com.bashiju.manage.service.ICommunityService;
import com.bashiju.manage.service.ISchoolBlockService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.util.StringUtil;

/**
 * 学区管理控制器
 * @ClassName:  SchoolBlockMgrController   
 * @Description:学区管理控制器  
 * @author: zuoyuntao
 * @date:   2018年5月17日 上午10:30:10   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="schoolBlockMgr")
public class SchoolBlockMgrController extends BaseController{
	/**
	 * 学区管理接口对象
	 */
	@Autowired
	private ISchoolBlockService mISchoolBlockService;
	/**
	 * 构造树接口
	 */
	@Autowired
	private ICommunityService communityService;
	/**
	 * 获取主页面模型
	 * @Title: getMainMv   
	 * @Description:获取主页面模型
	 * @param: request 页面请求对象
	 * @param: response 页面响应对象
	 * @return: ModelAndView 返回视图中包含行政区划数据对象
	 * AreaListJson：[{code=530102, parentCode=530100, level=1, name=五华区}, {code=530112, parentCode=530100, level=1, name=西山区}, {code=530122, parentCode=530100, level=1, name=晋宁县}, {code=530127, parentCode=530100, level=1, name=嵩明县}, {code=530181, parentCode=530100, level=1, name=安宁市}, {code=530101, parentCode=530100, level=1, name=市辖区}, {code=530111, parentCode=530100, level=1, name=官渡区}, {code=530114, parentCode=530100, level=1, name=呈贡区}, {code=530125, parentCode=530100, level=1, name=宜良县}, {code=530126, parentCode=530100, level=1, name=石林彝族自治县}, {code=530129, parentCode=530100, level=1, name=寻甸回族彝族自治县}, {code=530103, parentCode=530100, level=1, name=盘龙区}, {code=530113, parentCode=530100, level=1, name=东川区}, {code=530124, parentCode=530100, level=1, name=富民县}, {code=530128, parentCode=530100, level=1, name=禄劝彝族苗族自治县}, {code=5, parentCode=530111, level=2, name=小菜园}, {code=18, parentCode=530101, level=2, name=111111111}, {code=19, parentCode=530102, level=2, name=啊打发}, {code=9, parentCode=530114, level=2, name=新城第一区}, {code=12, parentCode=530112, level=2, name=南亚风情园}, {code=4, parentCode=530103, level=2, name=讽德诵功}, {code=16, parentCode=530113, level=2, name=的风格的}, {code=8, parentCode=530114, level=2, name=老城}, {code=11, parentCode=530111, level=2, name=世纪城}, {code=3, parentCode=530102, level=2, name=结果符合}, {code=14, parentCode=530102, level=2, name=w}, {code=6, parentCode=530124, level=2, name=发的广泛地}, {code=7, parentCode=530102, level=2, name=0}, {code=10, parentCode=530112, level=2, name=地方}, {code=2, parentCode=530102, level=2, name=好的水晶头}, {code=13, parentCode=530101, level=2, name=1}, {code=11, parentCode=9, level=3, name=爱生气啊}, {code=10, parentCode=13, level=3, name=15}, {code=7, parentCode=2, level=3, name=11}, {code=3, parentCode=12, level=3, name=玛卡切片}, {code=9, parentCode=13, level=3, name=13}, {code=6, parentCode=3, level=3, name=额外热舞}, {code=4, parentCode=4, level=3, name=把握今天陪我}, {code=8, parentCode=13, level=3, name=12}, {code=5, parentCode=3, level=3, name=投入和他如何}, {code=2, parentCode=4, level=3, name=听见撒}]
	 * @throws
	 */
	@RequestMapping(value="schoolBlockPage")
	@ResponseBody
	public ModelAndView getMainMv(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView model = this.getModelAndView(request, response, "schoolblock/schoolblock");
		//行政区划编码
		String curRgCode = (String) UserThreadLocal.get().get("areaCode");
//		List<Map<String, Object>> list = regionService.queryAreaSelect();
		List<Map<String, Object>> list = mISchoolBlockService.queryRegionList(curRgCode);
		//获取城市信息
		List<Map<String, Object>> cityList = communityService.queryAreaSelect();
		model.addObject("AreaListJson", JSON.toJSONString(list));
		model.addObject("cityList", JSON.toJSONString(cityList));
		return model;
	}
	/**
	 * 读取小区信息（带城市、行政区划和小区数据信息） 
	 * @Title: queryCommunitySelectWithRegion
	 * @author: zuoyuntao  
	 * @Description:读取小区信息（带城市、行政区划和小区数据信息）
	 * @return      
	 * Object
	 */
	@RequestMapping(value="queryCommunitySelectWithRegion")
	@ResponseBody
	public Object queryCommunitySelectWithRegion() {
		//行政区划编码
		String curRgCode = (String) UserThreadLocal.get().get("areaCode");
		List<Map<String, Object>> list = mISchoolBlockService.queryRegionList(curRgCode);
		return list;
	}

	/**
	 * 获取主页面数据信息
	 * @Title: getSchoolPageObj   
	 * @Description: 获取主页面数据信息
	 * @param: schoolName 学校名称
	 * @param: schoolAddress 学校地址
	 * @return: Object   
	 * {count=8, msg=, code=0, data=Page{count=true, pageNum=1, pageSize=10, startRow=0, endRow=10, total=8, pages=1, reasonable=false, pageSizeZero=false}[{schoolEdit=schoolEdit, permissionArea=BSJ0103, communitDel=communitDel, addTime=2018-05-17 16:24:27.0, schoolDel=schoolDel, isValid=1, schoolAddress=放大沙发s'f'd's, updateTime=2018-05-24 10:22:29.0, operator=测试人员, middleSchoolName= 法大师傅爱的色放安德森发大水, communityEdit=communityEdit, id=1, communityAdd=communityAdd, schoolName=呵呵了, operatorId=1}, {schoolEdit=schoolEdit, permissionArea=BSJ0103, communitDel=communitDel, addTime=2018-05-17 16:25:14.0, schoolDel=schoolDel, isValid=1, schoolAddress=法大师傅阿斯蒂芬, operator=测试人员, middleSchoolName=发大水发射点犯得上发生, communityEdit=communityEdit, id=2, communityAdd=communityAdd, schoolName=从沙发上的, operatorId=1}, {schoolEdit=schoolEdit, permissionArea=BSJ0103, communitDel=communitDel, addTime=2018-05-17 16:26:07.0, schoolDel=schoolDel, isValid=1, schoolAddress=放大沙发是, operator=测试人员, middleSchoolName=放大沙发是法大师傅打, communityEdit=communityEdit, id=3, communityAdd=communityAdd, schoolName=发的沙发上的, operatorId=1}, {schoolEdit=schoolEdit, permissionArea=BSJ0103, communitDel=communitDel, addTime=2018-05-18 09:34:12.0, schoolDel=schoolDel, isValid=1, schoolAddress=测试--1234, updateTime=2018-05-21 09:52:32.0, operator=测试人员, middleSchoolName=测试--456, communityEdit=communityEdit, id=4, communityAdd=communityAdd, schoolName=测试测试, operatorId=1}, {schoolEdit=schoolEdit, permissionArea=BSJ0103, communitDel=communitDel, addTime=2018-05-18 17:37:28.0, schoolDel=schoolDel, isValid=1, schoolAddress=法撒旦, updateTime=2018-05-21 10:31:52.0, operator=测试人员, middleSchoolName=法撒旦发范德萨发, communityEdit=communityEdit, id=6, communityAdd=communityAdd, schoolName=发射-123, operatorId=1}, {schoolEdit=schoolEdit, permissionArea=BSJ0103, communitDel=communitDel, addTime=2018-05-21 10:47:50.0, schoolDel=schoolDel, isValid=1, schoolAddress=法大师傅的撒地方阿三法撒旦发生的事, updateTime=2018-05-21 10:48:12.0, operator=测试人员, middleSchoolName=飞洒发的打算, communityEdit=communityEdit, id=7, communityAdd=communityAdd, schoolName=测试萨芬士, operatorId=1}, {schoolEdit=schoolEdit, permissionArea=BSJ0103, communitDel=communitDel, addTime=2018-05-21 11:58:00.0, schoolDel=schoolDel, isValid=1, schoolAddress=发射点发顺丰, updateTime=2018-05-21 11:59:18.0, operator=测试人员, middleSchoolName=法大师傅法大师傅, communityEdit=communityEdit, id=8, communityAdd=communityAdd, schoolName=沙发-1664, operatorId=1}, {schoolEdit=schoolEdit, permissionArea=BSJ0103, communitDel=communitDel, addTime=2018-05-21 13:37:06.0, schoolDel=schoolDel, isValid=1, schoolAddress=测试--888, updateTime=2018-05-21 13:42:34.0, operator=测试人员, middleSchoolName=测试, communityEdit=communityEdit, id=9, communityAdd=communityAdd, schoolName=测试-999, operatorId=1}]}   
	 * @throws
	 */
	@RequestMapping(value="getSchoolPageObj")
	@ResponseBody
	public Object getSchoolPageObj(String schoolName,String schoolAddress,String rgCode
			,String page,String limit) {
		if(null == page) {
			page = "0";
		}
		if(null == limit) {
			limit = "20";
		}
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("rgCode", rgCode);
		paraMap.put("schoolAddress", schoolAddress);
		paraMap.put("schoolName", schoolName);
		Page<Map<String,Object>> pageObj = mISchoolBlockService.querySchoolPageObj(
				paraMap,Integer.parseInt(page), Integer.parseInt(limit));
		return this.getPageResult(pageObj);
	}
	
	/**
	 * 获取小区分页数据对象
	 * @Title: getCommunityPageObj   
	 * @Description:获取小区分页数据对象 
	 * @param: communityName 小区名称
	 * @param: schoolId 学校ID
	 * @param: page 页面显示最小条数
	 * @param: limit 页面显示最大条数
	 * @return: Object  
	 * {count=4, msg=, code=0, data=Page{count=true, pageNum=1, pageSize=10, startRow=0, endRow=10, total=4, pages=1, reasonable=false, pageSizeZero=false}[{schoolEdit=schoolEdit, permissionArea=BSJ0103, communitDel=communitDel, addTime=2018-05-21 10:57:34.0, schoolDel=schoolDel, isValid=1, communityRemark=测试试试, updateTime=2018-05-21 11:28:14.0, operator=测试人员, schoolId=1, communityName=投入和他如何, communityEdit=communityEdit, id=10, communityAdd=communityAdd, communityId=5, operatorId=1}, {schoolEdit=schoolEdit, permissionArea=BSJ0103, communitDel=communitDel, addTime=2018-05-21 11:56:41.0, schoolDel=schoolDel, isValid=1, communityRemark=测试--12, operator=测试人员, schoolId=1, communityName=12, communityEdit=communityEdit, id=15, communityAdd=communityAdd, communityId=8, operatorId=1}, {schoolEdit=schoolEdit, permissionArea=BSJ0103, communitDel=communitDel, addTime=2018-05-24 10:22:57.0, schoolDel=schoolDel, isValid=1, communityRemark=test123, updateTime=2018-05-24 10:23:33.0, operator=测试人员, schoolId=1, communityName=13, communityEdit=communityEdit, id=21, communityAdd=communityAdd, communityId=9, operatorId=1}, {schoolEdit=schoolEdit, permissionArea=BSJ0103, communitDel=communitDel, addTime=2018-05-30 11:22:30.0, schoolDel=schoolDel, isValid=1, communityRemark=测试---1111, operator=测试人员, schoolId=1, communityName=玛卡切片, communityEdit=communityEdit, id=22, communityAdd=communityAdd, communityId=3, operatorId=1}]}    
	 * @throws
	 */
	@RequestMapping(value="getCommunityPageObj")
	@ResponseBody
	public Object getCommunityPageObj(String communityName,String schoolId,String page,String limit) {
		if(null == page) {
			page = "0";
		}
		if(null == limit) {
			limit = "20";
		}
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("schoolId", schoolId);
		Page<Map<String,Object>> pageObj = mISchoolBlockService.queryCommunityPageObj(
				paraMap,Integer.parseInt(page),Integer.parseInt(limit));
		return this.getPageResult(pageObj);
	}
	
	/**
	 * 根据学校Id获取周边小区信息
	 * @Title: getCommunitDataBySchoolId   
	 * @Description: 根据学校Id获取周边小区信息   
	 * @param: id 页面传入的ID值
	 * @param: type 1为学校数据信息；2为小区数据信息
	 * @return: Object 返回学校/小区数据对象
	 * 学校：{permissionArea=BSJ0103,addTime=2018-05-17 16:24:27.0,isValid=1, schoolAddress=放大沙发s'f'd's, updateTime=2018-05-24 10:22:29.0, operator=测试人员, middleSchoolName= 法大师傅爱的色放安德森发大水,id=1, schoolName=呵呵了, operatorId=1}
	 * 小区：{permissionArea=BSJ0103,addTime=2018-05-21 10:57:34.0, schoolDel=schoolDel, isValid=1, communityRemark=测试试试, updateTime=2018-05-21 11:28:14.0, operator=测试人员, schoolId=1, communityName=投入和他如何,id=10, communityId=5, operatorId=1}
	 * @throws
	 */
	@RequestMapping(value="getDataById")
	public Object getDataById(String id,String type) {
		if(StringUtil.isEmpty(id)) {
			throw new BusinessException("传入ID为空！");
		}
		if(StringUtil.isEmpty(type)) {
			throw new BusinessException("传入数据类为空！");
		}
		if(type.equals("1")) {
			return mISchoolBlockService.queryDataById(id,ManageGlobal.SCHOOL_INFO);
		}else {
			return mISchoolBlockService.queryDataById(id, ManageGlobal.SCHOOL_BLOCK_COMMUNITY);
		}
	}
	
	/**
	 * 保存学校信息或小区信息
	 * @Title: saveOrUpdateSchoolInfo   
	 * @Description: 保存学校信息或小区信息  
	 * @param: jsonObj 页面传入操作数据对象
	 * @param: type 1为学校数据信息；2为小区数据信息
	 * @return: BashijuResult      
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveOrUpdateSchoolInfo")
	@ResponseBody
	public BashijuResult saveOrUpdateSchoolInfo(String jsonData,String type) {
		Map<String,Object> paraMap = new HashMap<String,Object>();
		if(StringUtil.isEmpty(jsonData)) {
			throw new BusinessException("传入数据为空!");
		}
		paraMap = (Map<String,Object>) JSONObject.parse(jsonData);
		String tableName = "";
		if(type.equals("1")){
			String schoolName = String.valueOf(paraMap.get("schoolName"));
			if(StringUtil.isEmpty(schoolName)) {
				throw new BusinessException("录入的学校名称为空！");
			}
			if(StringUtil.isEmpty(String.valueOf(paraMap.get("id")))) {
				if(mISchoolBlockService.isObjExists(schoolName,type,null
						,(String) paraMap.get("rgCode"))) {
					return BashijuResult.build(false, "学校信息已存在！");
				}
				paraMap.remove("id");
			}
			tableName = ManageGlobal.SCHOOL_INFO;
		}else if(type.equals("2")) {
			//小区ID
			String communityId = String.valueOf(paraMap.get("communityId"));
			String communityName = String.valueOf(paraMap.get("communityName"));
			String schoolId = String.valueOf(paraMap.get("schoolId"));
			if(StringUtil.isEmpty(communityName)) {
				throw new BusinessException("小区信息为空！");
			}
			if(StringUtil.isEmpty(String.valueOf(paraMap.get("comId")))) {
				if(mISchoolBlockService.isObjExists(communityId,type,schoolId,"")) {
					return BashijuResult.build(false, "小区信息已存在！");
				}
			}else {
				paraMap.put("id",paraMap.get("comId"));
			}
			paraMap.remove("comId");
			tableName = ManageGlobal.SCHOOL_BLOCK_COMMUNITY;
		}else {
			throw new BusinessException("暂未实现此功能");
		}
		paraMap.put("operatorId", UserThreadLocal.get().get("id"));
		paraMap.put("permissionArea", UserThreadLocal.get().get("deptId"));
		
		mISchoolBlockService.saveOrUpdateSchoolInfo(paraMap, tableName);
		return BashijuResult.ok();
	}
	
	/**
	 * 删除学区数据信息
	 * @Title: delSchoolData   
	 * @Description: 删除学区数据信息   
	 * @param schoolId 学校ID
	 * @param communityId 小区ID
	 * @return: BashijuResult      
	 * @throws
	 */
	@RequestMapping(value="delSchoolBlockData")
	@ResponseBody
	public BashijuResult delSchoolBlockData(String schoolId,String communityId) {
		if(StringUtil.isEmpty(schoolId) && StringUtil.isEmpty(communityId)) {
			throw new BusinessException("传入ID为空！");
		}
		String tableName = "";
		Map<String,String> paraMap = new HashMap<String,String>();
		if(StringUtil.isNotEmpty(schoolId)) {
			tableName = ManageGlobal.SCHOOL_INFO;
			paraMap.put("schoolId", schoolId);
		}else {
			tableName = ManageGlobal.SCHOOL_BLOCK_COMMUNITY;
		}
		if(StringUtil.isNotEmpty(communityId)) {
			paraMap.put("communityId", communityId);
		}
		mISchoolBlockService.delSchoolBlockData(paraMap, tableName);
		return BashijuResult.ok();
	}
}
