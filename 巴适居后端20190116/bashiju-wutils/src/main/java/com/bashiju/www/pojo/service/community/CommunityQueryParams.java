package com.bashiju.www.pojo.service.community;

import java.io.Serializable;

import lombok.Data;

/**   
 * @ClassName:  CommunityQueryParams   
 * @Description:小区查询条件   
 * @author: yangz
 * @date:   2018年7月24日 下午3:55:29   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class CommunityQueryParams  implements Serializable{

	private static final long serialVersionUID = 2362109505099387850L;

	/**
	 * 城市代码
	 */
	private String cityCode;
	
	/**
	 * 行政区域编码
	 */
	private String areaCode;
	
	/**
	 * 片区编号
	 */
	private String regionCode;
	
	/**
	 * 地铁线路编号
	 */
	private String metroCode;
	
	/**
	 * 地铁站点编号
	 */
	private String stationCode;
	
	/**
	 * 均价查询条件(多个条件之间用英文逗号隔开.如：1,2,3)
	 */
	private String avgPrice;
	
	/**
	 * 类型查询条件(多个条件之间用英文逗号隔开.如：1,2,3)
	 */
	private String type;
	
	/**
	 * 楼龄查询条件(多个条件之间用英文逗号隔开.如：1,2,3)
	 */
	private String buildAge;
	
	/**
	 * 自定义最低售价
	 */
	private Integer minPrice;
	
	/**
	 * 自定义最高售价
	 */
	private Integer maxPrice;
	
	/**
	 * 当前页
	 */
	private int pageNum;
	
	/**
	 * 每页显示的条数
	 */
	private int pageSize;
	
	/**
	 * 排序编号：0--默认,1--均价,2--成交量
	 */
	private int sort;
	
	/**
	 * 升降序：false--升序,true--降序 默认为升序
	 */
	private boolean asc;
	
	public CommunityQueryParams() {
		super();
	}


}
