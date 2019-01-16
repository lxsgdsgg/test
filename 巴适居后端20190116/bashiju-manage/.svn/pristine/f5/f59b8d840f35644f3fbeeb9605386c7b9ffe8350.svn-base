/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  TempCommunity.java   
 * @Package com.bashiju.manage.ExportModelBean   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年12月3日 上午11:12:59   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.ExportModelBean;

import java.util.Date;

import com.bashiju.manage.pojo.BeanAnnotation;


/**   
 * @ClassName:  TempCommunity   
 * @Description:小区信息批量导入对象   
 * @author: yangz
 * @date:   2018年12月3日 上午11:12:59   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class TempCommunity {

//	@BeanAnnotation(Primarykey=true,regex="[0-9]{1,11}",errMsg="主键不存在")
//	private Integer id;
	
	@BeanAnnotation(minOccurs=1,regex="[\\u4e00-\\u9fa5_a-zA-Z0-9_]{2,30}",errMsg="小区名称不允许为空，不少于2个字，不多于30个字,且不允许包含特殊字符")
	private String name;
	@BeanAnnotation(minOccurs=1,regex="[\\u4e00-\\u9fa5_a-zA-Z0-9_]{2,30}",errMsg="小区备案名称不允许为空，不少于2个字，不多于30个字,且不允许包含特殊字符")
	private String recordName;
	@BeanAnnotation(minOccurs=1,regex="[a-zA-Z0-9_]{2,30}",errMsg="小区拼音码不允许为空，不少于2个字，不多于30个字，且只能包含英文数字")
	private String pinyin;
	@BeanAnnotation(minOccurs=1,regex="[0-9]{6}",errMsg="小区所属区域不允许为空，且为6位正整数")
	private String areaCode;
	@BeanAnnotation(minOccurs=1,regex="[0-9]{1,11}",errMsg="小区所属片区不允许为空，且为正整数")
	private String regionId;
	@BeanAnnotation(regex="[\\u4e00-\\u9fa5_a-zA-Z0-9_]{2,60}",errMsg="小区地址不少于2个字，不多于60个字,且不允许包含特殊字符")
	private String address;
	@BeanAnnotation(regex="^(\\-|\\+?)+[0-9]+([.][0-9]+){0,1}$",errMsg="经度只能为数字")
	private String longitude;
	@BeanAnnotation(regex="^(\\-|\\+?)+[0-9]+([.][0-9]+){0,1}$",errMsg="纬度只能为数字")
	private String latitude;
	@BeanAnnotation(regex="^[0-9]+([.][0-9]+){0,1}$",errMsg="占地面积只能为正数")
	private String floorArea;
	@BeanAnnotation(regex="^[0-9]+([.][0-9]+){0,1}$",errMsg="建筑面积只能为正数")
	private String buildingArea;
	@BeanAnnotation(minOccurs=1,regex="[0-9]{1,11}",errMsg="楼栋总数不能为空，且为正整数")
	private String buildingCount;
	@BeanAnnotation(minOccurs=1,regex="[0-9]{1,11}",errMsg="房屋总套数不能为空，且为正整数")
	private String houseCount;
	@BeanAnnotation(minOccurs=1,regex="[0-9]{2}",errMsg="产权年限不能为空，且为正整数")
	private String propertyLimit;
	@BeanAnnotation(minOccurs=1,regex="^[0-9]+([.][0-9]+){0,1}$",errMsg="容积率不能为空，且只能为正数")
	private String capacityRatio;
	@BeanAnnotation(minOccurs=1,regex="^[0-9]+([.][0-9]+){0,1}$",errMsg="绿化率不能为空，且只能为正数")
	private String greenRate;
	@BeanAnnotation(regex="[\\u4e00-\\u9fa5_a-zA-Z0-9_]{2,30}",errMsg="开发商名称不少于2个字，不多于30个字,且不允许包含特殊字符")
	private String developers;
	/**小区简介**/
	private String communityProfile;
	@BeanAnnotation(minOccurs=1,regex="^(0|([1-9][0-9]*)|(([0]\\.\\d{0,2}|[1-9][0-9]*\\.\\d{0,2})))$",errMsg="出售均价不能为空，小数不能超过两位数，且只能为正数")
	private String salePrice;
	@BeanAnnotation(minOccurs=1,regex="0|1",errMsg="是否热门小区不能为空，且只能选择1.是或0.否")
	private String isHot;
	@BeanAnnotation(minOccurs=1,regex="[0-9]{4}",errMsg="建筑年代不能为空，且为年份值")
	private String buildYear;
	/**配套设施编号**/
	private String matchingIds;
	/**配套设施**/
	private String matching;
	@BeanAnnotation(regex="[\\u4e00-\\u9fa5_a-zA-Z0-9_]{1,20}",errMsg="燃气不少于1个字，不多于20个字,且不允许包含特殊字符")
	private String gas;
	@BeanAnnotation(minOccurs=1,regex="0|1",errMsg="电梯服务不允许为空，且只能选择1.是或0.否")
	private String elevatorService;
	@BeanAnnotation(minOccurs=1,regex="0|1",errMsg="是否供水不允许为空，且只能选择1.是或0.否")
	private String waterSupply;
	@BeanAnnotation(minOccurs=1,regex="0|1",errMsg="是否供暖不允许为空，且只能选择1.是或0.否")
	private String heatSupply;
	@BeanAnnotation(minOccurs=1,regex="0|1",errMsg="是否供电不允许为空，且只能选择1.是或0.否")
	private String powerSupply;
	@BeanAnnotation(minOccurs=1,regex="[0-9]{0,11}",errMsg="地上停车位数量不能为空，且为正整数")
	private String groundParkingCount;
	@BeanAnnotation(minOccurs=1,regex="[0-9]{0,11}",errMsg="地下停车位数量不能为空，且为正整数")
	private String underGroundParkingCount;
	@BeanAnnotation(minOccurs=1,regex="^(0|([1-9][0-9]*)|(([0]\\.\\d{0,2}|[1-9][0-9]*\\.\\d{0,2})))$",errMsg="地上停车位出租价不能为空，小数不能超过两位数，且只能为正数")
	private String groundParkingLeasePrice;
	@BeanAnnotation(minOccurs=1,regex="^(0|([1-9][0-9]*)|(([0]\\.\\d{0,2}|[1-9][0-9]*\\.\\d{0,2})))$",errMsg="地下停车位出租价不能为空，小数不能超过两位数，且只能为正数")
	private String underGroundParkingLeasePrice;
	@BeanAnnotation(minOccurs=1,regex="^(0|([1-9][0-9]*)|(([0]\\.\\d{0,2}|[1-9][0-9]*\\.\\d{0,2})))$",errMsg="地上停车位出售价不能为空，小数不能超过两位数，且只能为正数")
	private String groundParkingSalePrice;
	@BeanAnnotation(minOccurs=1,regex="^(0|([1-9][0-9]*)|(([0]\\.\\d{0,2}|[1-9][0-9]*\\.\\d{0,2})))$",errMsg="地下停车位出售价不能为空，小数不能超过两位数，且只能为正数")
	private String underGroundParkingSalePrice;
	@BeanAnnotation(minOccurs=1,regex="(\\s)||(1[0-9]{10})|((\\s)||(([0-9]{3}[\\-][0-9]{8})|([0-9]{4}[\\-][0-9]{7,8})))",errMsg="物业电话不允许为空，移动电话为11位，固话号码格式为：区号-电话号码")
	private String propertyPhone;
	@BeanAnnotation(minOccurs=1,regex="^(0|([1-9][0-9]*)|(([0]\\.\\d{0,2}|[1-9][0-9]*\\.\\d{0,2})))$",errMsg="物业费不能为空，小数不能超过两位数，且只能为正数")
	private String propertyFees;
	@BeanAnnotation(regex="[\\u4e00-\\u9fa5_a-zA-Z0-9_]{2,30}",errMsg="物业名称不少于2个字，不多于30个字,且不允许包含特殊字符")
	private String propertyName;
	@BeanAnnotation(regex="[\\u4e00-\\u9fa5_a-zA-Z0-9_]{2,60}",errMsg="小区物业地址不少于2个字，不多于60个字,且不允许包含特殊字符")
	private String propertyAddress;
	@BeanAnnotation(regex="[\\u4e00-\\u9fa5_a-zA-Z0-9_]{2,60}",errMsg="小区所在街道不少于2个字，不多于60个字,且不允许包含特殊字符")
	private String street;
	@BeanAnnotation(minOccurs=1,regex="[a-zA-Z0-9]{1,80}",errMsg="权限域不允许为空")
	private String permissionArea;
	@BeanAnnotation(minOccurs=1,regex="[0-9]{1,11}",errMsg="操作人编号不允许为空")
	private String operatorId;
	@BeanAnnotation(minOccurs=1,regex="[\\u4e00-\\u9fa5_a-zA-Z0-9_]{2,10}",errMsg="操作人名称不允许为空")
	private String operator;
	@BeanAnnotation(minOccurs=1,errMsg="新增时间不允许为空")
	private Date addTime;


	public TempCommunity() {
		super();
	}




	/**  
	 * @Title:  getName <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getName() {
		return name;
	}


	/**  
	 * @Title:  setName <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**  
	 * @Title:  getRecordName <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getRecordName() {
		return recordName;
	}


	/**  
	 * @Title:  setRecordName <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}


	/**  
	 * @Title:  getPinyin <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getPinyin() {
		return pinyin;
	}


	/**  
	 * @Title:  setPinyin <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}


	/**  
	 * @Title:  getAreaCode <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getAreaCode() {
		return areaCode;
	}


	/**  
	 * @Title:  setAreaCode <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}


	/**  
	 * @Title:  getRegionId <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getRegionId() {
		return regionId;
	}


	/**  
	 * @Title:  setRegionId <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}


	/**  
	 * @Title:  getAddress <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getAddress() {
		return address;
	}


	/**  
	 * @Title:  setAddress <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setAddress(String address) {
		this.address = address;
	}


	/**  
	 * @Title:  getLongitude <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getLongitude() {
		return longitude;
	}


	/**  
	 * @Title:  setLongitude <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	/**  
	 * @Title:  getLatitude <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getLatitude() {
		return latitude;
	}


	/**  
	 * @Title:  setLatitude <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	/**  
	 * @Title:  getFloorArea <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getFloorArea() {
		return floorArea;
	}


	/**  
	 * @Title:  setFloorArea <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setFloorArea(String floorArea) {
		this.floorArea = floorArea;
	}


	/**  
	 * @Title:  getBuildingArea <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getBuildingArea() {
		return buildingArea;
	}


	/**  
	 * @Title:  setBuildingArea <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setBuildingArea(String buildingArea) {
		this.buildingArea = buildingArea;
	}


	/**  
	 * @Title:  getBuildingCount <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getBuildingCount() {
		return buildingCount;
	}


	/**  
	 * @Title:  setBuildingCount <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setBuildingCount(String buildingCount) {
		this.buildingCount = buildingCount;
	}


	/**  
	 * @Title:  getHouseCount <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getHouseCount() {
		return houseCount;
	}


	/**  
	 * @Title:  setHouseCount <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setHouseCount(String houseCount) {
		this.houseCount = houseCount;
	}


	/**  
	 * @Title:  getPropertyLimit <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getPropertyLimit() {
		return propertyLimit;
	}


	/**  
	 * @Title:  setPropertyLimit <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setPropertyLimit(String propertyLimit) {
		this.propertyLimit = propertyLimit;
	}


	/**  
	 * @Title:  getCapacityRatio <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getCapacityRatio() {
		return capacityRatio;
	}


	/**  
	 * @Title:  setCapacityRatio <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setCapacityRatio(String capacityRatio) {
		this.capacityRatio = capacityRatio;
	}


	/**  
	 * @Title:  getGreenRate <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getGreenRate() {
		return greenRate;
	}


	/**  
	 * @Title:  setGreenRate <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setGreenRate(String greenRate) {
		this.greenRate = greenRate;
	}


	/**  
	 * @Title:  getDevelopers <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getDevelopers() {
		return developers;
	}


	/**  
	 * @Title:  setDevelopers <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setDevelopers(String developers) {
		this.developers = developers;
	}


	/**  
	 * @Title:  getCommunityProfile <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getCommunityProfile() {
		return communityProfile;
	}


	/**  
	 * @Title:  setCommunityProfile <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setCommunityProfile(String communityProfile) {
		this.communityProfile = communityProfile;
	}


	/**  
	 * @Title:  getSalePrice <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getSalePrice() {
		return salePrice;
	}


	/**  
	 * @Title:  setSalePrice <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}


	/**  
	 * @Title:  getIsHot <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getIsHot() {
		return isHot;
	}


	/**  
	 * @Title:  setIsHot <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setIsHot(String isHot) {
		this.isHot = isHot;
	}


	/**  
	 * @Title:  getBuildYear <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getBuildYear() {
		return buildYear;
	}


	/**  
	 * @Title:  setBuildYear <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}


	/**  
	 * @Title:  getMatchingIds <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getMatchingIds() {
		return matchingIds;
	}

	/**  
	 * @Title:  setMatchingIds <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setMatchingIds(String matchingIds) {
		this.matchingIds = matchingIds;
	}




	/**  
	 * @Title:  getMatching <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getMatching() {
		return matching;
	}


	/**  
	 * @Title:  setMatching <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setMatching(String matching) {
		this.matching = matching;
	}


	/**  
	 * @Title:  getGas <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getGas() {
		return gas;
	}


	/**  
	 * @Title:  setGas <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setGas(String gas) {
		this.gas = gas;
	}


	/**  
	 * @Title:  getElevatorService <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getElevatorService() {
		return elevatorService;
	}


	/**  
	 * @Title:  setElevatorService <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setElevatorService(String elevatorService) {
		this.elevatorService = elevatorService;
	}


	/**  
	 * @Title:  getWaterSupply <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getWaterSupply() {
		return waterSupply;
	}


	/**  
	 * @Title:  setWaterSupply <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setWaterSupply(String waterSupply) {
		this.waterSupply = waterSupply;
	}


	/**  
	 * @Title:  getHeatSupply <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getHeatSupply() {
		return heatSupply;
	}


	/**  
	 * @Title:  setHeatSupply <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setHeatSupply(String heatSupply) {
		this.heatSupply = heatSupply;
	}


	/**  
	 * @Title:  getPowerSupply <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getPowerSupply() {
		return powerSupply;
	}


	/**  
	 * @Title:  setPowerSupply <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setPowerSupply(String powerSupply) {
		this.powerSupply = powerSupply;
	}


	/**  
	 * @Title:  getGroundParkingCount <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getGroundParkingCount() {
		return groundParkingCount;
	}


	/**  
	 * @Title:  setGroundParkingCount <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setGroundParkingCount(String groundParkingCount) {
		this.groundParkingCount = groundParkingCount;
	}


	/**  
	 * @Title:  getUnderGroundParkingCount <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getUnderGroundParkingCount() {
		return underGroundParkingCount;
	}


	/**  
	 * @Title:  setUnderGroundParkingCount <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setUnderGroundParkingCount(String underGroundParkingCount) {
		this.underGroundParkingCount = underGroundParkingCount;
	}


	/**  
	 * @Title:  getGroundParkingLeasePrice <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getGroundParkingLeasePrice() {
		return groundParkingLeasePrice;
	}


	/**  
	 * @Title:  setGroundParkingLeasePrice <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setGroundParkingLeasePrice(String groundParkingLeasePrice) {
		this.groundParkingLeasePrice = groundParkingLeasePrice;
	}


	/**  
	 * @Title:  getUnderGroundParkingLeasePrice <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getUnderGroundParkingLeasePrice() {
		return underGroundParkingLeasePrice;
	}


	/**  
	 * @Title:  setUnderGroundParkingLeasePrice <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setUnderGroundParkingLeasePrice(String underGroundParkingLeasePrice) {
		this.underGroundParkingLeasePrice = underGroundParkingLeasePrice;
	}


	/**  
	 * @Title:  getGroundParkingSalePrice <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getGroundParkingSalePrice() {
		return groundParkingSalePrice;
	}


	/**  
	 * @Title:  setGroundParkingSalePrice <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setGroundParkingSalePrice(String groundParkingSalePrice) {
		this.groundParkingSalePrice = groundParkingSalePrice;
	}


	/**  
	 * @Title:  getUnderGroundParkingSalePrice <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getUnderGroundParkingSalePrice() {
		return underGroundParkingSalePrice;
	}


	/**  
	 * @Title:  setUnderGroundParkingSalePrice <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setUnderGroundParkingSalePrice(String underGroundParkingSalePrice) {
		this.underGroundParkingSalePrice = underGroundParkingSalePrice;
	}


	/**  
	 * @Title:  getPropertyPhone <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getPropertyPhone() {
		return propertyPhone;
	}


	/**  
	 * @Title:  setPropertyPhone <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setPropertyPhone(String propertyPhone) {
		this.propertyPhone = propertyPhone;
	}


	/**  
	 * @Title:  getPropertyFees <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getPropertyFees() {
		return propertyFees;
	}


	/**  
	 * @Title:  setPropertyFees <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setPropertyFees(String propertyFees) {
		this.propertyFees = propertyFees;
	}


	/**  
	 * @Title:  getPropertyName <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getPropertyName() {
		return propertyName;
	}


	/**  
	 * @Title:  setPropertyName <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}


	/**  
	 * @Title:  getPropertyAddress <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getPropertyAddress() {
		return propertyAddress;
	}


	/**  
	 * @Title:  setPropertyAddress <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}


	/**  
	 * @Title:  getStreet <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getStreet() {
		return street;
	}


	/**  
	 * @Title:  setStreet <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**  
	 * @Title:  getPermissionArea <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getPermissionArea() {
		return permissionArea;
	}

	/**  
	 * @Title:  setPermissionArea <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setPermissionArea(String permissionArea) {
		this.permissionArea = permissionArea;
	}

	/**  
	 * @Title:  getOperatorId <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getOperatorId() {
		return operatorId;
	}

	/**  
	 * @Title:  setOperatorId <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	/**  
	 * @Title:  getOperator <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getOperator() {
		return operator;
	}

	/**  
	 * @Title:  setOperator <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**  
	 * @Title:  getAddTime <BR>  
	 * @Description:  <BR>  
	 * @return: Date <BR>  
	 */
	public Date getAddTime() {
		return addTime;
	}

	/**  
	 * @Title:  setAddTime <BR>  
	 * @Description: <BR>  
	 * @return: Date <BR>  
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	
}
