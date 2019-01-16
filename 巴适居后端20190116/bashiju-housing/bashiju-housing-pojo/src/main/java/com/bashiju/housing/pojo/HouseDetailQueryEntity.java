/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  HouseDetailQueryEntity.java   
 * @Package com.bashiju.housing.pojo      
 * @author: zuoyuntao     
 * @date:   2018年9月27日 下午6:52:06   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.housing.pojo;

import com.github.pagehelper.util.StringUtil;

/**
 * 房源信息查询实体类--地图、地铁找房用
 * @ClassName:HouseDetailQueryEntity
 * @Description:房源信息查询实体类--地图、地铁找房用
 * @author:zuoyuntao
 * @date:2018年9月27日 下午6:52:06
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public class HouseDetailQueryEntity {
	/**维护人ID**/
	private int userId;
	/**最小建筑面积**/
	private int minBuildSpace;
	/**最大建筑面积**/
	private int maxBuildSpace;
	/**座栋**/
	private String building;
	/**小区ID**/
	private int communityId;
	/**公司ID**/
	private String companyId;
	/**片区ID**/
	private int regionId;
	/**地铁站点ID**/
	private String stationId;
	/** 1、房源编号，2、业主姓名，3、门牌号，4、业主电话、5小区名称，6、钥匙编号，7、委托编号，8、推荐标签，9、房源备注**/
	private AccurateEntity vagueData;
	
	/**  
	 * @Title:  getUserId <BR>  
	 * @Description:  <BR>  
	 * @return: int <BR>  
	 */
	public int getUserId() {
		return userId;
	}
	/**  
	 * @Title:  getMinBuildSpace <BR>  
	 * @Description:  <BR>  
	 * @return: int <BR>  
	 */
	public int getMinBuildSpace() {
		return minBuildSpace;
	}
	/**  
	 * @Title:  getMaxBuildSpace <BR>  
	 * @Description:  <BR>  
	 * @return: int <BR>  
	 */
	public int getMaxBuildSpace() {
		return maxBuildSpace;
	}
	/**  
	 * @Title:  getBuilding <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getBuilding() {
		return building;
	}
	/**  
	 * @Title:  getCommunityId <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public int getCommunityId() {
		return communityId;
	}
	/**  
	 * @Title:  getVagueData <BR>  
	 * @Description:  <BR>  
	 * @return: AccurateEntity <BR>  
	 */
	public AccurateEntity getVagueData() {
		return vagueData;
	}
	/**  
	 * @Title:  setUserId <BR>  
	 * @Description: <BR>  
	 * @return: int <BR>  
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**  
	 * @Title:  setMinBuildSpace <BR>  
	 * @Description: <BR>  
	 * @return: int <BR>  
	 */
	public void setMinBuildSpace(int minBuildSpace) {
		this.minBuildSpace = minBuildSpace;
	}
	/**  
	 * @Title:  setMaxBuildSpace <BR>  
	 * @Description: <BR>  
	 * @return: int <BR>  
	 */
	public void setMaxBuildSpace(int maxBuildSpace) {
		this.maxBuildSpace = maxBuildSpace;
	}
	/**  
	 * @Title:  setBuilding <BR>  
	 * @Description: <BR>  
	 * @return: int <BR>  
	 */
	public void setBuilding(String building) {
		this.building = building;
	}
	/**  
	 * @Title:  setCommunityId <BR>  
	 * @Description: <BR>  
	 * @return: int <BR>  
	 */
	public void setCommunityId(int communityId) {
		this.communityId = communityId;
	}
	/**  
	 * @Title:  setVagueData <BR>  
	 * @Description: <BR>  
	 * @return: AccurateEntity <BR>  
	 */
	public void setVagueData(AccurateEntity vagueData) {
		this.vagueData = vagueData;
	}
	/**  
	 * @Title:  getCompanyId <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getCompanyId() {
		return companyId;
	}
	/**  
	 * @Title:  setCompanyId <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/**  
	 * @Title:  getRegionId <BR>  
	 * @Description:  <BR>  
	 * @return: int <BR>  
	 */
	public int getRegionId() {
		return regionId;
	}
	/**  
	 * @Title:  setRegionId <BR>  
	 * @Description: <BR>  
	 * @return: int <BR>  
	 */
	
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	
	/**  
	 * @Title:  getStationId <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	
	public String getStationId() {
		return stationId;
	}
	/**  
	 * @Title:  setStationId <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public String buildQueryConditionSql() {
		StringBuilder conditionSql = new StringBuilder();
		StringBuilder tableSql=new StringBuilder();
		tableSql.append("SELECT DISTINCT ");
		tableSql.append("sh.id,");
		tableSql.append("sh.labeld,");
		tableSql.append("sh.houseId,");
		tableSql.append("sh.transactionType,");
		tableSql.append("sh.transactionTypeId,");//交易类型
		tableSql.append("sh.discStatusId,");//盘状态
		tableSql.append("sh.discStatus,");
		tableSql.append("sh.houseUses,");
		tableSql.append("sh.houseUsesId,");
		tableSql.append("hb.areaName,");
		tableSql.append("hb.regionName,");
		tableSql.append("hb.communityName,");
		tableSql.append("hb.buildingsName,");
		tableSql.append("hb.buildingsUnitName,");
		tableSql.append("hb.buildingHouseName,");
		tableSql.append("hb.floorCount,");
		tableSql.append("hb.totalLayers,");
		tableSql.append("sh.room,");
		tableSql.append("sh.hall,");
		tableSql.append("sh.toilet,");
		tableSql.append("sh.kitchen,");
		tableSql.append("sh.balcony,");
		tableSql.append("sh.useSpace,");
		tableSql.append("sh.buildSpace,");
		tableSql.append("sh.orientation,");
		tableSql.append("sh.decoration,");
		tableSql.append("sh.matching,");
		tableSql.append("sh.resourceType,");
		tableSql.append("sh.buildDates,");
		tableSql.append("sh.propertyType,");
		tableSql.append("FLOOR(sh.sellingPrice/1000000) as sellingPrice,");
		tableSql.append("FLOOR(sh.rentPrice/100) as rentPrice,");
		tableSql.append("sh.levelType,");
		tableSql.append("sh.remark,");
		tableSql.append("sh.entrustCode,");
		tableSql.append("sh.keyCode,");
		tableSql.append("sh.addTime,");
		tableSql.append("sh.updateTime,");
		tableSql.append("sh.allFollowTime,");
		tableSql.append("sh.mainterFllowTime,");
		tableSql.append("sh.operator,");
		tableSql.append("sh.mainterDept,");
		tableSql.append("sh.companyName,");
		tableSql.append("sh.permissionArea,");
		tableSql.append("sh.taobaoStatus,");//淘宝状态
		tableSql.append("sh.taobaoStatusId,");//淘宝状态
		
		tableSql.append("sh.examineStatus,");//审核状态
		tableSql.append("sh.isUnique,");//是否独家
		tableSql.append("sh.isKeys,");//是否有钥匙
		tableSql.append("sh.isSetTop,");//是否置顶
		tableSql.append("sh.isGood,");//是否靓房
		tableSql.append("sh.isPicture,");//是否有图片
		tableSql.append("sh.isVideo,");//是否有视频
		tableSql.append("sh.isDeposit,");//是否下定
		tableSql.append("sh.viewingCount,");//带看次数
		tableSql.append("hb.isSchoolRoom,");//是否靓房
		tableSql.append("sh.operatorId ");
		tableSql.append("FROM hs_secondhandhouse  AS sh ");//二手房源主表
		tableSql.append("INNER JOIN hs_housebaseinfo  AS hb ON sh.houseId=hb.id and sh.isValid=1");//关联房源基础表
		/** 1、房源编号，2、业主姓名，3、门牌号，4、业主电话、5小区名称，6、钥匙编号，7、委托编号，8、推荐标签，9、房源备注**/
		if (vagueData!=null) {
			if (vagueData.getType() !=null && vagueData.getValue() != null) {
				if (vagueData.getType() !=4 ) {
					conditionSql.append(" and ");
				}
				switch (vagueData.getType()) {
				case 1:
					conditionSql.append(" sh.houseId like '%");
					conditionSql.append(vagueData.getValue());
					conditionSql.append("%' ");
					break;
				case 2:
					conditionSql.append(" sh.owner like '%");
					conditionSql.append(vagueData.getValue());
					conditionSql.append("%' ");
					break;
				case 3:
					conditionSql.append(" hb.buildingHouseName like '%");
					conditionSql.append(vagueData.getValue());
					conditionSql.append("%' ");
					break;
				case 4:
					tableSql.append(" INNER JOIN hs_houseOwnerRelate AS hor ON hor.shhId=sh.id AND hor.isValid=1");
					tableSql.append(" and hor.phone like '%");
					tableSql.append(vagueData.getValue());
					tableSql.append("%' ");
					break;
				case 5:
					conditionSql.append(" hb.communityName like '%");
					conditionSql.append(vagueData.getValue());
					conditionSql.append("%' ");
					break;
				case 6:
					conditionSql.append(" sh.keyCode like '%");
					conditionSql.append(vagueData.getValue());
					conditionSql.append("%' ");
					break;
				case 7:
					conditionSql.append(" sh.entrustCode like '%");
					conditionSql.append(vagueData.getValue());
					conditionSql.append("%' ");
					break;
				case 8:
					conditionSql.append(" sh.labeld like '%");
					conditionSql.append(vagueData.getValue());
					conditionSql.append("%' ");
					break;
				case 9:
					conditionSql.append(" sh.remark like '%");
					conditionSql.append(vagueData.getValue());
					conditionSql.append("%' ");
					break;
				}
			}
		}
		if(StringUtil.isNotEmpty(String.valueOf(this.userId)) && this.userId != 0) {
			tableSql.append(" inner join sys_user u on sh.maintainId = u.id and u.id =").append(this.userId);
		}
		if(StringUtil.isNotEmpty(String.valueOf(communityId)) && communityId != 0) {
			conditionSql.append(" and hb.communityId = ").append(this.communityId);
		}
		if(StringUtil.isNotEmpty(String.valueOf(stationId))) {
			conditionSql.append(" and hb.communityId in( select a.communityId from sys_station_block_community a inner join sys_metro_station b \r\n" + 
					"on a.stationId = b.id and a.isValid = 1 and b.isValid = 1 and b.ukId = '").append(this.stationId).append("')");
		}
		if(StringUtil.isNotEmpty(this.building)) {
			conditionSql.append(" and hb.buildingsName like '%").append(this.building).append("%'");
		}
		if (StringUtil.isNotEmpty(String.valueOf(this.minBuildSpace)) && this.minBuildSpace != 0
				&& StringUtil.isNotEmpty(String.valueOf(this.maxBuildSpace)) && this.maxBuildSpace != 0) {
			conditionSql.append(" and sh.buildSpace between ").append(this.minBuildSpace)
					.append(" and ").append(this.maxBuildSpace);
		}
		if (this.minBuildSpace != 0 && this.maxBuildSpace == 0) {
			conditionSql.append(" and sh.buildSpace >= ").append(this.minBuildSpace);
		}
		
		if ( this.minBuildSpace == 0 && this.maxBuildSpace != 0) {
			conditionSql.append(" and sh.buildSpace <= ").append(this.maxBuildSpace);
		}
		
		if(StringUtil.isNotEmpty(this.companyId)) {
			conditionSql.append(" and sh.companyId = '").append(this.companyId).append("'");
		}
		
		if(this.regionId != 0) {
			conditionSql.append(" and hb.regionId = ").append(this.regionId);
		}
		if (conditionSql.length()>0) {
			tableSql.append(" where 1 = 1 ").append(conditionSql);
		}
		
		return tableSql.toString();
	}
	
	public static void main(String[] args) {
		HouseDetailQueryEntity query = new HouseDetailQueryEntity();
		System.out.println(query.buildQueryConditionSql());
	}
}
