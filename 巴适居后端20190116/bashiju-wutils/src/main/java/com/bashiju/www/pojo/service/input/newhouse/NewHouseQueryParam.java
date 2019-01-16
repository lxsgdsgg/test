package com.bashiju.www.pojo.service.input.newhouse;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 
 *   新房列表查询对象
 * @ClassName:  NewHouseQueryParam   
 * @Description:   新房列表查询对象
 * @author: wangkaifa
 * @date:   2018年8月21日 上午11:36:50       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class NewHouseQueryParam implements Serializable {

	private static final long serialVersionUID = -9001214601431026928L;
	/**城市代码，必填**/
	protected String cityCode;
	/**区域代码**/
	protected String areaCode;
	/**片区id**/
	protected Integer regionId;
	/**出售价格范围编号**/
	protected List<String> priceCodes;
	/**最低价格单位万元**/
	protected Integer minPrice;
	/**最高价格单位万元**/
	protected Integer maxPrice;
	/**面积范围编号**/
	protected List<String> spaceCodes;
	/**最小面积单位平米**/
	protected Integer minSpace;
	/**最大面积单位平米**/
	protected Integer maxSpace;
	/**房间数:6为5室以上**/
	protected List<Integer> rooms;
	/**房源类型:住宅house,公寓apartment,别墅villa,商铺shops,写字楼officeBuiling,其他other**/
	protected List<String> houseUses;
	/**装修id:1为毛胚,2为清水,3为简装,4为中装,5为精装,6为豪装**/
	protected List<Integer> decorationIds;
	/**排序:0为默认排序,1为均价,**/
	protected int sort;
	/**升降序:0为降序,1为升序**/
	protected int asc;
}
