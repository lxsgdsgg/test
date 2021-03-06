package com.bashiju.housing.pojo;

import java.math.BigDecimal;

/**
 * 
 * @ClassName:  CommunityEntity   
 * @Description:TODO(小区实体类，用于controller接收参数)   
 * @author: wangkaifa
 * @date:   2018年4月23日 上午9:36:25   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class CommunityEntity {
	 private Long id;
	 /**小区名称 **/
	 private String name;
	 /**小区名称拼音 **/
	 private String pinyin;
	 /** 地址**/
	 private String address;
	 /**出售均价 **/
	 private Integer salePrice;
	 /**是否热门小区，1是，0不是**/
	 private Integer isHot;
	 /**区划码 **/
	 private String areaCode;
	 /**片区id **/
	 private Long regionId;
	 /**房屋总套数 **/
	 private Integer  houseCount;
	 /** 经度**/
	 private String  longitude;
	 /**纬度 **/
	 private String  latitude;
	 /**楼栋总数 **/
	 private Integer   buildingCount;
	 /**物业类型 **/
	 private String   estateType;
	 /**建造年代 **/
	 private Integer   buildYear;
	 /**产权年限 **/
	 private Integer   propertyLimit;
	 /**容积率 **/
	 private BigDecimal   capacityRatio;
	 /**绿化率 **/
	 private BigDecimal   greenRate;
	 /**住宅类型 **/
	 private String   residence;
	 /**占地面积 **/
	 private BigDecimal   floorArea;
	 /**建筑面积 **/
	 private BigDecimal   buildingArea;
	 /**燃气 **/
	 private String   gas;
	 /**电梯服务 **/
	 private String   elevatorService;
	 /**供水 **/
	 private String   waterSupply;
	 /**供暖 **/
	 private String   heatSupply;
	 /**供电 **/
	 private String   powerSupply;
	 /**地上停车位数 **/
	 private Integer   groundParkingCount;
	 /**地下停车位数量 **/
	 private Integer   underGroundParkingCount;
	 /** 地上停车位出租价**/
	 private Integer  groundParkingLeasePrice;
	 /**地下停车位出租价 **/
	 private Integer  underGroundParkingLeasePrice;
	 /**地上停车位出售价 **/
	 private Integer  groundParkingSalePrice;
	 /**地下停车位出售价 **/
	 private Integer  underGroundParkingSalePrice;
	 /** 物业费**/
	 private Integer  propertyFees;
	 /**物业电话 **/
	 private String  propertyPhone;
	 /**物业名称 **/
	 private String  propertyName;
	 /**物业地址 **/
	 private String propertyAddress;
	 /**街道 **/
	 private String  street;
	 /**小区简介 **/
	 private String  communityProfile;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Integer salePrice) {
		this.salePrice = salePrice;
	}
	public Integer getIsHot() {
		return isHot;
	}
	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public Long getRegionId() {
		return regionId;
	}
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	public Integer getHouseCount() {
		return houseCount;
	}
	public void setHouseCount(Integer houseCount) {
		this.houseCount = houseCount;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public Integer getBuildingCount() {
		return buildingCount;
	}
	public void setBuildingCount(Integer buildingCount) {
		this.buildingCount = buildingCount;
	}
	public String getEstateType() {
		return estateType;
	}
	public void setEstateType(String estateType) {
		this.estateType = estateType;
	}
	public Integer getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(Integer buildYear) {
		this.buildYear = buildYear;
	}
	public Integer getPropertyLimit() {
		return propertyLimit;
	}
	public void setPropertyLimit(Integer propertyLimit) {
		this.propertyLimit = propertyLimit;
	}
	public BigDecimal getCapacityRatio() {
		return capacityRatio;
	}
	public void setCapacityRatio(BigDecimal capacityRatio) {
		this.capacityRatio = capacityRatio;
	}
	public BigDecimal getGreenRate() {
		return greenRate;
	}
	public void setGreenRate(BigDecimal greenRate) {
		this.greenRate = greenRate;
	}
	public String getResidence() {
		return residence;
	}
	public void setResidence(String residence) {
		this.residence = residence;
	}
	public BigDecimal getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(BigDecimal floorArea) {
		this.floorArea = floorArea;
	}
	public BigDecimal getBuildingArea() {
		return buildingArea;
	}
	public void setBuildingArea(BigDecimal buildingArea) {
		this.buildingArea = buildingArea;
	}
	public String getGas() {
		return gas;
	}
	public void setGas(String gas) {
		this.gas = gas;
	}
	public String getElevatorService() {
		return elevatorService;
	}
	public void setElevatorService(String elevatorService) {
		this.elevatorService = elevatorService;
	}
	public String getWaterSupply() {
		return waterSupply;
	}
	public void setWaterSupply(String waterSupply) {
		this.waterSupply = waterSupply;
	}
	public String getHeatSupply() {
		return heatSupply;
	}
	public void setHeatSupply(String heatSupply) {
		this.heatSupply = heatSupply;
	}
	public String getPowerSupply() {
		return powerSupply;
	}
	public void setPowerSupply(String powerSupply) {
		this.powerSupply = powerSupply;
	}
	public Integer getGroundParkingCount() {
		return groundParkingCount;
	}
	public void setGroundParkingCount(Integer groundParkingCount) {
		this.groundParkingCount = groundParkingCount;
	}
	public Integer getUnderGroundParkingCount() {
		return underGroundParkingCount;
	}
	public void setUnderGroundParkingCount(Integer underGroundParkingCount) {
		this.underGroundParkingCount = underGroundParkingCount;
	}
	public Integer getGroundParkingLeasePrice() {
		return groundParkingLeasePrice;
	}
	public void setGroundParkingLeasePrice(Integer groundParkingLeasePrice) {
		this.groundParkingLeasePrice = groundParkingLeasePrice;
	}
	public Integer getUnderGroundParkingLeasePrice() {
		return underGroundParkingLeasePrice;
	}
	public void setUnderGroundParkingLeasePrice(Integer underGroundParkingLeasePrice) {
		this.underGroundParkingLeasePrice = underGroundParkingLeasePrice;
	}
	public Integer getGroundParkingSalePrice() {
		return groundParkingSalePrice;
	}
	public void setGroundParkingSalePrice(Integer groundParkingSalePrice) {
		this.groundParkingSalePrice = groundParkingSalePrice;
	}
	public Integer getUnderGroundParkingSalePrice() {
		return underGroundParkingSalePrice;
	}
	public void setUnderGroundParkingSalePrice(Integer underGroundParkingSalePrice) {
		this.underGroundParkingSalePrice = underGroundParkingSalePrice;
	}
	public Integer getPropertyFees() {
		return propertyFees;
	}
	public void setPropertyFees(Integer propertyFees) {
		this.propertyFees = propertyFees;
	}
	public String getPropertyPhone() {
		return propertyPhone;
	}
	public void setPropertyPhone(String propertyPhone) {
		this.propertyPhone = propertyPhone;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getPropertyAddress() {
		return propertyAddress;
	}
	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCommunityProfile() {
		return communityProfile;
	}
	public void setCommunityProfile(String communityProfile) {
		this.communityProfile = communityProfile;
	}
	@Override
	public String toString() {
		return "CommunityEntity [id=" + id + ", name=" + name + ", pinyin=" + pinyin + ", address=" + address
				+ ", salePrice=" + salePrice + ", isHot=" + isHot + ", areaCode=" + areaCode + ", regionId=" + regionId
				+ ", houseCount=" + houseCount + ", longitude=" + longitude + ", latitude=" + latitude
				+ ", buildingCount=" + buildingCount + ", estateType=" + estateType + ", buildYear=" + buildYear
				+ ", propertyLimit=" + propertyLimit + ", capacityRatio=" + capacityRatio + ", greenRate=" + greenRate
				+ ", residence=" + residence + ", floorArea=" + floorArea + ", buildingArea=" + buildingArea + ", gas="
				+ gas + ", elevatorService=" + elevatorService + ", waterSupply=" + waterSupply + ", heatSupply="
				+ heatSupply + ", powerSupply=" + powerSupply + ", groundParkingCount=" + groundParkingCount
				+ ", underGroundParkingCount=" + underGroundParkingCount + ", groundParkingLeasePrice="
				+ groundParkingLeasePrice + ", underGroundParkingLeasePrice=" + underGroundParkingLeasePrice
				+ ", groundParkingSalePrice=" + groundParkingSalePrice + ", underGroundParkingSalePrice="
				+ underGroundParkingSalePrice + ", propertyFees=" + propertyFees + ", propertyPhone=" + propertyPhone
				+ ", propertyName=" + propertyName + ", propertyAddress=" + propertyAddress + ", street=" + street
				+ ", communityProfile=" + communityProfile + "]";
	}
	
}
