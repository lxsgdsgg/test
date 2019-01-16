package com.bashiju.www.pojo.service.input.house;


import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 
 *   房源地图找房参数
 * @ClassName:  HouseMapParam   
 * @Description: 房源地图找房参数
 * @author: wangkaifa
 * @date:   2018年7月24日 下午1:58:47       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class HouseMapParam implements Serializable{
	private static final long serialVersionUID = 2320747532433069794L;
	/**城市代码，必填**/
	protected String cityCode;
	/**租房类型:0全部、1整租、2合租、3公寓**/
	protected List<Integer> rentType;
	/**出售价格范围编号**/
	protected List<String> priceCodes;
	/**最低价格**/
	protected Integer minPrice;
	/**最高价格**/
	protected Integer maxPrice;
	/**面积范围编号**/
	protected List<String> spaceCodes;
	/**最小面积单位平米**/
	protected Integer minSpace;
	/**最大面积单位平米**/
	protected Integer maxSpace;
	/**房间数:6为5室以上**/
	protected List<Integer> rooms;

	/**楼层id:1为底层,2为低楼层,3为中楼层,4为高楼层,5为顶层**/
	protected List<Integer> fllorDesIds;
	/**朝向id:1为东,2为南,3为西,4为北,5为东南,6为东北,7为西南,8为西北**/
	protected List<Integer> orientationIds;
	/**装修id:1为毛胚,2为清水,3为简装,4为中装,5为精装,6为豪装**/
	protected List<Integer> decorationIds;
	/**楼龄范围编号**/
	protected List<String> buildYearCodes;
	
	/**房源类型:住宅house,公寓apartment,别墅villa,商铺shops,写字楼officeBuiling,其他other**/
	protected List<String> houseUses;
	
	/**区域代码**/
	protected String areaCode;
	/**片区id**/
	protected Integer regionId;	
	/**小区名称**/
	protected String communityName;
	/**地铁站名称**/
	protected String stationName;
	/**地铁线路Id**/
	protected Integer metroId;
	/**站点id**/
	protected Integer stationId;
}
