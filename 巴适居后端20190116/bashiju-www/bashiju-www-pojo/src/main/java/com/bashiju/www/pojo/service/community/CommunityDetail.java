/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CommunityDetail.java   
 * @Package com.bashiju.www.pojo.service.out.comm   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年7月26日 下午2:21:10   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.community;

import java.util.List;

/**   
 * @ClassName:  CommunityDetail   
 * @Description:小区详情信息实体   
 * @author: wangpeng
 * @date:   2018年7月26日 下午2:21:10   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public class CommunityDetail {
	
	/**
	 * 小区名称
	 */
	private String name;
	
	/**
	 * 小区件售价
	 */
	private String salePrice;
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 容积率
	 */
	private String capacityRatio;
	/**
	 * 小区户数(房屋总套数)
	 */
	private String houseCount;
	/**
	 * 绿化率
	 */
	private String greenRate;
	
	/**
	 * (地上)车位数量
	 */
	private String groundParkingCount;
	
	/**
	 * 建筑年代
	 */
	private String buildYear;
	
	/**
	 * 物业费
	 */
	private String propertyFees;
	
	/**
	 * 物业类型
	 */
	private String estateType;
	
	/**
	 * 物业公司
	 */
	private String propertyName;
	
	/**
	 * 开发商
	 */
	private String developers;
	
	/**
	 * 地铁标签(多个标签之间使用英文逗号隔开)
	 */
	private String subwayLabel;
	
	/**
	 * 学校标签(多个标签之间使用英文逗号隔开)
	 */
	private String schoolLabel;
	
	
	/**
	 * 小区描述
	 */
	private String communityDescribe;
	
	/**
	 * 小区图片列表
	 */
	private List<String> pictures;

	/**
	 * 二手房源在售套数
	 */
	private String onSaleCnt;
	
	/**
	 * 在租房源套数
	 */
	private String leaseCnt;
	
	/**
	 * 在售一室房源套数
	 */
	private String saleRoom1;
	/**
	 * 在售二室房源套数
	 */
	private String saleRoom2;
	/**
	 * 在售三室房源套数
	 */
	private String saleRoom3;
	/**
	 * 在售四室房源套数
	 */
	private String saleRoom4;
	
	/**
	 * 在租一室房源套数
	 */
	private String leaseRoom1;
	/**
	 * 在租二室房源套数
	 */
	private String leaseRoom2;
	/**
	 * 在租三室房源套数
	 */
	private String leaseRoom3;
	/**
	 * 在租四室房源套数
	 */
	private String leaseRoom4;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCapacityRatio() {
		return capacityRatio;
	}

	public void setCapacityRatio(String capacityRatio) {
		this.capacityRatio = capacityRatio;
	}

	public String getHouseCount() {
		return houseCount;
	}

	public void setHouseCount(String houseCount) {
		this.houseCount = houseCount;
	}

	public String getGreenRate() {
		return greenRate;
	}

	public void setGreenRate(String greenRate) {
		this.greenRate = greenRate;
	}

	public String getGroundParkingCount() {
		return groundParkingCount;
	}

	public void setGroundParkingCount(String groundParkingCount) {
		this.groundParkingCount = groundParkingCount;
	}

	public String getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}

	public String getPropertyFees() {
		return propertyFees;
	}

	public void setPropertyFees(String propertyFees) {
		this.propertyFees = propertyFees;
	}

	public String getEstateType() {
		return estateType;
	}

	public void setEstateType(String estateType) {
		this.estateType = estateType;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getDevelopers() {
		return developers;
	}

	public void setDevelopers(String developers) {
		this.developers = developers;
	}

	public String getSubwayLabel() {
		return subwayLabel;
	}

	public void setSubwayLabel(String subwayLabel) {
		this.subwayLabel = subwayLabel;
	}

	public String getSchoolLabel() {
		return schoolLabel;
	}

	public void setSchoolLabel(String schoolLabel) {
		this.schoolLabel = schoolLabel;
	}

	public String getCommunityDescribe() {
		return communityDescribe;
	}

	public void setCommunityDescribe(String communityDescribe) {
		this.communityDescribe = communityDescribe;
	}

	public List<String> getPictures() {
		return pictures;
	}

	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}
	
	
	
	
	
	
	public String getOnSaleCnt() {
		return onSaleCnt;
	}

	public void setOnSaleCnt(String onSaleCnt) {
		this.onSaleCnt = onSaleCnt;
	}

	public String getLeaseCnt() {
		return leaseCnt;
	}

	public void setLeaseCnt(String leaseCnt) {
		this.leaseCnt = leaseCnt;
	}

	public String getSaleRoom1() {
		return saleRoom1;
	}

	public void setSaleRoom1(String saleRoom1) {
		this.saleRoom1 = saleRoom1;
	}

	public String getSaleRoom2() {
		return saleRoom2;
	}

	public void setSaleRoom2(String saleRoom2) {
		this.saleRoom2 = saleRoom2;
	}

	public String getSaleRoom3() {
		return saleRoom3;
	}

	public void setSaleRoom3(String saleRoom3) {
		this.saleRoom3 = saleRoom3;
	}

	public String getSaleRoom4() {
		return saleRoom4;
	}

	public void setSaleRoom4(String saleRoom4) {
		this.saleRoom4 = saleRoom4;
	}

	public String getLeaseRoom1() {
		return leaseRoom1;
	}

	public void setLeaseRoom1(String leaseRoom1) {
		this.leaseRoom1 = leaseRoom1;
	}

	public String getLeaseRoom2() {
		return leaseRoom2;
	}

	public void setLeaseRoom2(String leaseRoom2) {
		this.leaseRoom2 = leaseRoom2;
	}

	public String getLeaseRoom3() {
		return leaseRoom3;
	}

	public void setLeaseRoom3(String leaseRoom3) {
		this.leaseRoom3 = leaseRoom3;
	}

	public String getLeaseRoom4() {
		return leaseRoom4;
	}

	public void setLeaseRoom4(String leaseRoom4) {
		this.leaseRoom4 = leaseRoom4;
	}
	
	@Override
	public String toString() {
		return "CommunityDetail[name="+name+",salePrice="+salePrice+",address="+address+",communityDescribe="+communityDescribe+""
				+ ",capacityRatio="+capacityRatio+",houseCount="+houseCount+",greenRate="+greenRate+""
				+ ",groundParkingCount="+groundParkingCount+",buildYear="+buildYear+",propertyFees="+propertyFees+""
				+ ",estateType="+estateType+",propertyName="+propertyName+",developers="+developers+","
				+ "subwayLabel="+subwayLabel+",schoolLabel="+schoolLabel+",pictures="+pictures+""
				+"onSaleCnt="+onSaleCnt+",saleRoom1="+saleRoom1+",saleRoom2="+saleRoom2+",saleRoom3="+saleRoom3+",saleRoom4="+saleRoom4+""
				+ ",leaseCnt="+leaseCnt+",leaseRoom1="+leaseRoom1+",leaseRoom2="+leaseRoom2+",leaseRoom3="+leaseRoom3+",leaseRoom4="+leaseRoom4+"]";
	}
}
