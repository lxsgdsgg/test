/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ExcelTestController.java   
 * @Package com.bashiju.manage.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年11月29日 下午2:54:21   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.list.TreeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.druid.util.StringUtils;
import com.bashiju.enums.DiscStatusEnum;
import com.bashiju.enums.ExamineStatusEnum;
import com.bashiju.enums.HouseLevelEnum;
import com.bashiju.enums.HouseStatusEnum;
import com.bashiju.enums.HouseTransactionEnum;
import com.bashiju.enums.HouseUsesEnum;
import com.bashiju.enums.TaoBaoStatusEnum;
import com.bashiju.enums.pc.HouseTypeEnum;
import com.bashiju.enums.pc.OrientationEnum;
import com.bashiju.manage.service.ExcelModelService;
import com.bashiju.manage.utils.ExcelUtil;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.threadlocal.UserThreadLocal;

/**   
 * @ClassName:  ExcelModelController   
 * @Description:excel模板导出  
 * @author: yangz
 * @date:   2018年11月29日 下午2:54:21   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="excelModel")
public class ExcelModelController {
	
	@Autowired
	private ExcelModelService excelModelService;
	
	/**
	 * @Description: 导出小区模板  
	 * @param request
	 * @param response      
	 * @return: void
	 */
	@RequestMapping(value="exportCommunityExcelModel")
	public void exportCommunityExcelModel(HttpServletRequest request,HttpServletResponse response) {
		Map<String,String> title = new LinkedHashMap<String,String>();
		title.put("name","名称");
		title.put("recordName","备案名");
		title.put("pinyin","名称拼音");
		title.put("areaCode","区域");
		title.put("regionId","片区");
		title.put("address","地址");
		title.put("longitude","经度");
		title.put("latitude","纬度");
		title.put("floorArea","占地面积");
		title.put("buildingArea","建筑面积");
		title.put("buildingCount","楼栋总数");
		title.put("houseCount","房屋总套数");
		title.put("propertyLimit","产权年限");
		title.put("capacityRatio","容积率");
		title.put("greenRate","绿化率");
		title.put("developers","开发商");
		title.put("communityProfile","小区简介");
		
		title.put("salePrice","出售均价");
		title.put("isHot","是否热门小区");
		title.put("buildYear","建筑年代");
		title.put("matching","配套");
		title.put("gas","燃气");
		title.put("elevatorService","电梯服务");
		title.put("waterSupply","供水");
		title.put("heatSupply","供暖");
		title.put("powerSupply","供电");
		title.put("groundParkingCount","地上停车位数量");
		title.put("underGroundParkingCount","地下停车位数量");
		title.put("groundParkingLeasePrice","地上停车位出租价");
		title.put("underGroundParkingLeasePrice","地下停车位出租价");
		title.put("groundParkingSalePrice","地上停车位出售价");
		title.put("underGroundParkingSalePrice","地下停车位出售价");
		title.put("propertyPhone","物业电话");
		title.put("propertyFees","物业费");
		title.put("propertyName","物业名称");
		title.put("propertyAddress","物业地址");
		title.put("street","街道");
		
		try {
			Map<String, List<String>> dropDowns = new HashMap<String,List<String>>(0);
			String cityCode = UserThreadLocal.get().get("cityCode").toString();
			String[] isUse = {"1.是","0.否"};
			dropDowns.put("areaCode", excelModelService.queryAreaCodeDropDown(cityCode));
			dropDowns.put("regionId", excelModelService.queryRegionDropDown(cityCode));
			dropDowns.put("matching", excelModelService.queryCommunityMatchingDropDown());
			dropDowns.put("isHot", Arrays.asList(isUse));
			dropDowns.put("elevatorService", Arrays.asList(isUse));
			dropDowns.put("waterSupply", Arrays.asList(isUse));
			dropDowns.put("heatSupply", Arrays.asList(isUse));
			dropDowns.put("powerSupply", Arrays.asList(isUse));
			ExcelUtil.exportModelXLSX(dropDowns, title, "小区信息模板",null, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 导出二手房源模板
	 * @Description: 导出二手房源模板  
	 * @param request
	 * @param response      
	 * @return: void
	 */
	@RequestMapping(value="exportSecondHouseExcelModel")
	public void exportSecondHouseExcelModel(HttpServletRequest request,HttpServletResponse response) {
		String communityId = request.getParameter("communityId");
		if(StringUtils.isEmpty(communityId))
			throw new BusinessException("小区编号不允许为空");
		List<Map<String,String>> baseHouses = this.excelModelService.queryBaseHouse(communityId);
		
		Map<String,String> title = new LinkedHashMap<String,String>();
		title.put("str","房源");
		title.put("titles","标题");
		title.put("transactionType","房源交易类型");
//		title.put("contractEndDate","");
//		title.put("contractStatus","");//TODO：这个要不要显示
		title.put("status","房源状态");
		title.put("examineStatus","审核状态");
		title.put("discStatus","盘状态");
		title.put("taoBaoStatus","淘宝状态");
		title.put("taobaoTime","进入淘宝时间");
		title.put("houseUses","房屋用途");
		title.put("useSpace","使用面积");
		title.put("buildSpace","建筑面积");
		title.put("orientation","朝向");
		title.put("decoration","装修");
		title.put("matching","配套");
		title.put("resourceType","房源来源");
		title.put("buildDates","建筑年代");
		title.put("propertyType","产权类型");
		title.put("certificateDate","产证日期");
		title.put("sellingPrice","售价");
		title.put("rentPrice","租价");
		title.put("minSellingPrice","最低售价");
		title.put("minRentPrice","最低租价");
		title.put("rentPriceTypeName","租价类型名称");
		title.put("levelType","等级");
		title.put("remark","备注");
		title.put("recordTime","录入日期");
		title.put("maintainer","维护人");
//		title.put("mainterDept","维护人所在部门");
		title.put("mainterFllowTime","维护人最后跟进日期");
		title.put("allFollowTime","全员最后跟进日期");
		title.put("isSynchron","是否与外网同步");
		title.put("isSatisfyTwoYear","是否满两年");
		title.put("isOnly","是否唯一住房");
		title.put("isUrgent","是否急切");
		title.put("isFullAmount","是否要求全款");
//		title.put("changePriceFlg","变价标识");//初始数据这个不管
		title.put("isKeys","是否有钥匙");
		title.put("isUnique","是否独家");
		title.put("isIntrust","是否委托");
//		title.put("closeDiscFlg","是否封盘");//初始数据封盘标识不管
		title.put("isCancelNotice","是否通知撤单");
		title.put("cancelNoticeTime","通知撤单时间");
		title.put("isSetTop","是否置顶");
		title.put("setTopTime","置顶时间");
		title.put("isGood","是否靓房");
		title.put("goodTime","靓房设置时间");
		title.put("labeld","推荐标签");
		title.put("owner","业主名称");
		title.put("nameTitle","称呼");
		title.put("structure","建筑结构");
		title.put("room","卧室数量");
		title.put("hall","大厅数量");
		title.put("toilet","卫生间数量");
		title.put("kitchen","厨房数量");
		title.put("balcony","阳台数量");
		title.put("panorama","全景地址");
		title.put("propRightsLen","产权年限");
		title.put("houseType","房屋类型");
		title.put("apartmentType","户型结构");
//		title.put("rentPriceUnit","租价单位");
		title.put("taxpayer","税费承担方");
		title.put("viewingType","看房方式");
		title.put("isPicture","是否有图片");
		title.put("isVideo","是否有视频");
		title.put("officeBuildLevel","写字楼级别");
		title.put("isDeposit","是否已下定金");
		title.put("isPriority","是否优先显示");
		title.put("viewingCount","带看次数");
		title.put("officeBuildRate","写字楼实率");
		title.put("floorHeight","层高");
		title.put("position","地段");
		title.put("industry","商铺行业");
		title.put("actuality","现状");
		title.put("planType","土地规划");
		title.put("situation","土地状况");
		title.put("entrustCode","独家/委托编号");
		title.put("keyCode","钥匙编号");
		title.put("percentage","首付百分比");
		title.put("payType","付款方式");
		title.put("openPlateDate","开盘时间");
		title.put("lastShowedTime","最后带看时间");
		title.put("lastAllShowedTime","全员最后带看时间");
		title.put("closePlanteEndDate","封盘到期时间");
		title.put("verificationCode","房源核验码");
		title.put("lastTradingTime","上次交易时间");
		title.put("mortgageName","抵押信息");
		title.put("isUploadCertificate","是否上传房本照片");
		title.put("heatingModeName","供暖方式");
		title.put("tradingRightsName","交易权属");
		title.put("housingYears","房屋年限");
		title.put("browsingCount","浏览次数");
		title.put("attentionCount","关注次数");
		title.put("lastViewingCount","最近15天带看次数");
		
		try {
			Map<String, List<String>> dropDowns = new HashMap<String,List<String>>(0);
			Map<?,?> user = UserThreadLocal.get();
			String[] isUse = {"1.是","0.否"};
			String[] rentPriceTypeName = {"1.元/月","2.元/季度","3.元/年","5.元/平/月"};//TODO：这里他们的逻辑好像不对
			String[] heatingModeName = {"1.集中供暖","2.无供暖"};
			String[] tradingRightsName = {"0.非共有","1.共有"};
			Map<String,String> transactionTypeMap = HouseTransactionEnum.enumMap;
			List<String> transactionTypeList = new TreeList<String>();
			for(Map.Entry<String, String> entry : transactionTypeMap.entrySet()) {
				transactionTypeList.add(entry.getKey()+"."+entry.getValue());
			}
			Map<String,String> statusMap = HouseStatusEnum.enumMap;
			List<String> statusList = new TreeList<String>();
			for(Map.Entry<String, String> entry : statusMap.entrySet()) {
				statusList.add(entry.getKey()+"."+entry.getValue());
			}
			Map<String,String> examineStatusMap = ExamineStatusEnum.enumMap;
			List<String> examineStatusList = new TreeList<String>();
			for(Map.Entry<String, String> entry : examineStatusMap.entrySet()) {
				examineStatusList.add(entry.getKey()+"."+entry.getValue());
			}
			Map<String,String> discStatusMap = DiscStatusEnum.enumMap;
			List<String> discStatusList = new TreeList<String>();
			for(Map.Entry<String, String> entry : discStatusMap.entrySet()) {
				discStatusList.add(entry.getKey()+"."+entry.getValue());
			}
			
			Map<String,String> taoBaoStatusMap = TaoBaoStatusEnum.enumMap;
			List<String> taoBaoStatusList = new TreeList<String>();
			for(Map.Entry<String, String> entry : taoBaoStatusMap.entrySet()) {
				taoBaoStatusList.add(entry.getKey()+"."+entry.getValue());
			}
			
			Map<String,String> houseUsesMap = HouseUsesEnum.enumMap;
			List<String> houseUsesList = new TreeList<String>();
			for(Map.Entry<String, String> entry : houseUsesMap.entrySet()) {
				houseUsesList.add(entry.getKey()+"."+entry.getValue());
			}
			
			Map<String,String> orientationMap = OrientationEnum.enumMap;
			List<String> orientationList = new TreeList<String>();
			for(Map.Entry<String, String> entry : orientationMap.entrySet()) {
				orientationList.add(entry.getKey()+"."+entry.getValue());
			}
			
			Map<String,String> houseLevelMap = HouseLevelEnum.enumMap;
			List<String> houseLevelList = new TreeList<String>();
			for(Map.Entry<String, String> entry : houseLevelMap.entrySet()) {
				houseLevelList.add(entry.getKey()+"."+entry.getValue());
			}
			
			
			Map<String,String> houseTypeMap = HouseTypeEnum.enumMap;
			List<String> houseTypeList = new TreeList<String>();
			for(Map.Entry<String, String> entry : houseTypeMap.entrySet()) {
				houseTypeList.add(entry.getKey()+"."+entry.getValue());
			}
			
			dropDowns.put("transactionType", transactionTypeList);
			dropDowns.put("status", statusList);
			dropDowns.put("examineStatus", examineStatusList);
			dropDowns.put("discStatus", discStatusList);
			dropDowns.put("taoBaoStatus", taoBaoStatusList);
			dropDowns.put("houseUses", houseUsesList);
			dropDowns.put("orientation", orientationList);
			dropDowns.put("decoration", excelModelService.queryDynamicDropDown("decoration"));
			dropDowns.put("matching", excelModelService.queryDynamicDropDown("cmatching"));//TODO:这里的配套只选了一个，应该是要根据不同的房源用途选择不同的配套设施
			dropDowns.put("resourceType", excelModelService.queryDynamicDropDown("resourceType"));
			dropDowns.put("propertyType", excelModelService.queryDynamicDropDown("propertyType"));
			dropDowns.put("rentPriceTypeName", Arrays.asList(rentPriceTypeName));
			dropDowns.put("levelType", houseLevelList);
			dropDowns.put("maintainer", excelModelService.queryUser(user.get("companyId").toString()));
//			dropDowns.put("mainterDept",);
			dropDowns.put("isSynchron", Arrays.asList(isUse));
			dropDowns.put("isSatisfyTwoYear", Arrays.asList(isUse));
			dropDowns.put("isOnly", Arrays.asList(isUse));
			dropDowns.put("isUrgent", Arrays.asList(isUse));
			dropDowns.put("isFullAmount", Arrays.asList(isUse));
//			dropDowns.put("changePriceFlg",);
			dropDowns.put("isKeys", Arrays.asList(isUse));
			dropDowns.put("isUnique", Arrays.asList(isUse));
			dropDowns.put("isIntrust", Arrays.asList(isUse));
//			dropDowns.put("closeDiscFlg",);
			
			dropDowns.put("isCancelNotice", Arrays.asList(isUse));
			dropDowns.put("isSetTop", Arrays.asList(isUse));
			dropDowns.put("isGood", Arrays.asList(isUse));
			dropDowns.put("structure", excelModelService.queryDynamicDropDown("structure"));
			dropDowns.put("propRightsLen", excelModelService.queryDynamicDropDown("propRightsLen"));
			dropDowns.put("houseType", houseTypeList);
			dropDowns.put("taxpayer", excelModelService.queryDynamicDropDown("taxpayer"));
			dropDowns.put("viewingType", excelModelService.queryDynamicDropDown("viewingType"));
			dropDowns.put("isPicture", Arrays.asList(isUse));
			
			dropDowns.put("isPicture", Arrays.asList(isUse));
			dropDowns.put("isVideo", Arrays.asList(isUse));
			dropDowns.put("officeBuildLevel", excelModelService.queryDynamicDropDown("officeBuildLevel"));
			dropDowns.put("isDeposit", Arrays.asList(isUse));
			dropDowns.put("position", excelModelService.queryDynamicDropDown("position"));
			dropDowns.put("industry", excelModelService.queryDynamicDropDown("industry"));
			dropDowns.put("actuality", excelModelService.queryDynamicDropDown("factoryActuality"));//TODO:这里的配套只选了一个，应该是要根据不同的房源用途选择不同的配套设施
			dropDowns.put("planType", excelModelService.queryDynamicDropDown("planType"));
			
			dropDowns.put("situation", excelModelService.queryDynamicDropDown("situation"));
			dropDowns.put("payType", excelModelService.queryDynamicDropDown("sellPayType"));//TODO:还有个出租付款类型的
			dropDowns.put("mortgageName", Arrays.asList(isUse));
			dropDowns.put("isUploadCertificate", Arrays.asList(isUse));
			dropDowns.put("heatingModeName", Arrays.asList(heatingModeName));
			dropDowns.put("tradingRightsName",Arrays.asList(tradingRightsName));
			dropDowns.put("isPriority", excelModelService.queryDynamicDropDown("isPriority"));
			dropDowns.put("housingYears", excelModelService.queryDynamicDropDown("housingYears"));
			dropDowns.put("apartmentType", excelModelService.queryDynamicDropDown("apartmentType"));
//			dropDowns.put("contractStatus", );
			
			ExcelUtil.exportModelXLSX(dropDowns, title, "二手房源模板",baseHouses, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
