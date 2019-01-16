package com.bashiju.wapi;

import java.util.List;

import com.bashiju.www.pojo.comm.QueryCondition;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.input.newhouse.NewHouseQueryParam;
import com.bashiju.www.pojo.service.out.comm.AreaResult;
import com.bashiju.www.pojo.service.out.comm.RegionResult;
import com.bashiju.www.pojo.service.out.newhouse.HotNewHouseListResult;
import com.bashiju.www.pojo.service.out.newhouse.NewHouseListResult;

/**
 * 
 *   新房楼盘列表页接口
 * @ClassName:  INewHouseListPageApi   
 * @Description:   新房楼盘列表页接口
 * @author: wangkaifa
 * @date:   2018年8月21日 下午5:32:56       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface INewHouseListPageApi {
	/**
	 * 
		 * 查询新房楼盘列表除区域、片区外的搜索条件类
		 * @Description: 查询新房楼盘列表除区域、片区外的搜索条件类
		 * @param cityCode 城市代码
		 * @return 
		 * List<QueryCondition>
	 */
	List<QueryCondition> queryConditionsSellHouse(String cityCode);
	
	/**
	 * 
		 * 根据城市查询区县
		 * @Description: 根据城市查询区县
		 * @param cityCode 城市代码
		 * @return [{name:五华区,code:530102},{name:官渡区,code:530104}]
		 * List<AreaResult>
	 */
	List<AreaResult> queryAreaByCity(String cityCode);
	/**
	 * 
		 * 根据区划码查询片区
		 * @Description: 根据区划码查询片区
		 * @param cityCode 城市代码
		 * @param areaCode 区划码
		 * @return [{name:大学城片区,id:1,areaCode:530102},{name:乌龙片区,id:2,areaCode:530102}]
		 * List<RegionResult>
	 */
	List<RegionResult> queryRegionByArea(String cityCode,String areaCode);
	/**
	 * 
		 * 新房列表，热门楼盘列表
		 * @Description: 新房列表，热门楼盘列表
		 * @param cityCode 城市代码
		 * @param limit 显示条数
		 * @return 
		 * List<HotNewHouseListResult>
	 */
	public List<HotNewHouseListResult> queryHotProjects(String cityCode,int limit);
	
	/**
	 * 
		 * 根据新房楼盘名称查询楼盘列表
		 * @Description: 根据新房楼盘名称查询楼盘列表
		 * @param cityCode 城市代码
		 * @param name 新房楼盘名称
		 * @param page 当前页
		 * @param limit 每页条数
		 * @return 
		 * WebPage<NewHouseListResult>
	 */
	public WebPage<NewHouseListResult> queryNewHouseListByName(String cityCode,String name,int page,int limit);
	/**
	 * 
		 * 新房列表查询
		 * @Description: 新房列表查询
		 * @param param 查询条件
		 * @param page 当前页
		 * @param limit 每页条数
		 * @return 
		 * WebPage<NewHouseListResult>
	 */
	public WebPage<NewHouseListResult> queryNewHouseList(NewHouseQueryParam param,int page,int limit);
}
