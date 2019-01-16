package com.bashiju.www.pojo.service.input.house;

import java.io.Serializable;
import java.util.List;

import com.bashiju.www.pojo.comm.HouseSortEnum;
import com.bashiju.www.pojo.service.out.comm.RangeResult;

import lombok.Data;
/**
 * 
 *   租房房源查询基础参数
 * @ClassName:  RentHouseParam   
 * @Description: 租房房源查询基础参数
 * @author: wangkaifa
 * @date:   2018年7月24日 下午1:58:47       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@Data
public class RentHouseParam implements Serializable{
	private static final long serialVersionUID = -4846746472815298476L;
	/**城市代码**/
	protected String cityCode;
	/**租房类型:0全部、1整租、2合租、3公寓**/
	protected List<Integer> rentType;
	/**出售价格范围编号**/
	protected List<String> priceCodes;
	/**最低价格单位元**/
	protected Integer minPrice;
	/**最高价格单位元**/
	protected Integer maxPrice;
	/**面积范围编号**/
	protected List<String> spaceCodes;
	/**最小面积单位平米**/
	protected Integer minSpace;
	/**最大面积单位平米**/
	protected Integer maxSpace;
	/**房间数:6为5室以上**/
	protected List<Integer> rooms;
	/**电梯:1为有电梯,2为无电梯**/
	protected List<Integer> elevator;
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
	/**品牌id**/
	protected List<Integer> brandIds;
	/**特色id**/
	protected List<Integer> featureIds;
	
	/**排序:1为最新排序,2为价格排序,3为面积排序,4为热门排序**/
	protected Integer sort;
	/**升降序:0为降序,1为升序**/
	protected Integer asc;
	/**区域代码**/
	private String areaCode;
	/**片区id**/
	private Integer regionId;
	/**地铁房,值为1**/
	private Integer metroHouse;
	/**地铁线路id**/
	private Integer metroId;
	/**站点id**/
	private Integer stationId;
}
