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
	/**城市代码**/
	protected String cityCode;
	/**出租价格单位元**/
	protected List<RangeResult> prices;
	/**最低价格单位元**/
	protected Integer minPrice;
	/**最高价格单位元**/
	protected Integer maxPrice;
	/**面积单位平米**/
	protected List<RangeResult> spaces;
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
	/**楼龄:21为20年以上**/
	protected List<Long> buildYears;
	/**房源类型:住宅house,公寓apartment,别墅villa,商铺shops,写字楼officeBuiling,其他other**/
	protected List<String> houseUse;

	
	/**排序:0为最新排序,1为价格排序,2为面积排序,3为热门排序**/
	protected Integer sort;
	/**升降序:0为降序,1为升序**/
	protected Integer asc;
	/**区域代码**/
	private String areaCode;
	/**片区id**/
	private Integer regionId;
	/**地铁房,值为1**/
	private Integer metroHouse;
	/**地铁线路名称**/
	private String metroName;
	/**站点id**/
	private Long stationId;
}
