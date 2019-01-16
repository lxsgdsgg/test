package com.bashiju.www.service.newhouse;
/**
 * 
 *   新房楼盘服务
 * @ClassName:  INewHouseService   
 * @Description:  新房楼盘服务
 * @author: liwen
 * @date:   2018年8月18日 下午11:01:33       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

import java.util.List;

import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.input.newhouse.NewHouseQueryParam;
import com.bashiju.www.pojo.service.out.newhouse.GoodNewHouseListResult;
import com.bashiju.www.pojo.service.out.newhouse.HotNewHouseListResult;
import com.bashiju.www.pojo.service.out.newhouse.NewHouseDetailResult;
import com.bashiju.www.pojo.service.out.newhouse.NewHouseDynamicResult;
import com.bashiju.www.pojo.service.out.newhouse.NewHouseImgList;
import com.bashiju.www.pojo.service.out.newhouse.NewHouseListResult;
import com.bashiju.www.pojo.service.out.newhouse.NewHousePriceDetailResult;
import com.bashiju.www.pojo.service.out.newhouse.NewHouseTypeList;
import com.bashiju.www.pojo.service.out.newhouse.OpenDateResult;
import com.bashiju.www.pojo.service.out.newhouse.PresalePermitResult;
import com.bashiju.www.pojo.service.out.newhouse.RoomTimeResult;
import com.bashiju.www.pojo.service.out.newhouse.SameNewHouseList;


public interface INewHouseService {
	/**
	 * 
	 * 查询新房楼盘各详细   
	 * redis的key值:WebGlobal.NEWHOUSE_ALLDETAILED_REDIS_PREFIX+新房楼盘id
	 * @Description:查询新房楼盘各详细       
	 * @param: @param newHouseId 新房楼盘id
	 * @param: @return      
	 * @return: NewHouseDetailResult      
	 * @throws
	 */
	NewHouseDetailResult selectNewHouseDetailInfo(int newHouseId);
	
	
	/**
	 * 
		 * 查询新房楼盘在售户型
		 * redis的key值:WebGlobal.NEWHOUSETYPE_REDIS_PREFIX+新房楼盘id
		 * @Description: 查询新房楼盘在售户型
		 * @param newHouseId 新房楼盘id
		 * @return 
		 * List<NewHouseTypeList>
	 */
	List<NewHouseTypeList> selectNewHouseType(int newHouseId);
	
		
	
	/**
	 * 
		 * 查询新房楼盘相册
		 * redis的key值:WebGlobal.NEWHOUSEIMG_REDIS_PREFIX+新房楼盘id
		 * @Description: 查询新房楼盘相册
		 * @param newHouseId 新房楼盘id
		 * @return 
		 * NewHouseInfoResult
	 */
	List<NewHouseImgList> selectNewHouseImgs(int newHouseId);
	
	
	/**
	 * 
		 * 查询相似新房楼盘
		 * redis的key值:WebGlobal.NEWHOUSE_DETAILED_SAME_REDIS_PREFIX+新房楼盘均价
		 * @Description: 查询相似新房楼盘
		 * @param avgPrice：新房楼盘均价
		 * @param limit：需要查询的条数
		 * @return 
		 * List<SameNewHouseList>
	 */
	List<SameNewHouseList> querySameNewHouseList(int avgPrice,int limit);
	

	 
	/**
	 *  条件查询楼盘动态   
	 * @Title: queryNewHouseDynamicByProjectId   
	 * @Description: 条件查询楼盘动态   
	 * @param projectId 新房楼盘id    
	 * @return: List<NewHouseDynamicResult>
	 */
	List<NewHouseDynamicResult> queryNewHouseDynamicByProjectId(String projectId);
	
	/**
	 * 条件查询预售证信息  
	 * @Title: queryPresalePermitByProjectId   
	 * @Description: 条件查询预售证信息  
	 * @param projectId 新房楼盘id
	 * @return: List<PresalePermitResult>
	 */
	List<PresalePermitResult> queryPresalePermitByProjectId(String projectId);
	/**
	 * 条件查询交房时间
	 * @Title: queryRoomTimeByPorjectId   
	 * @Description: 条件查询交房时间  
	 * @param projectId 新房楼盘id
	 * @return: List<RoomTimeResult>
	 */
	List<RoomTimeResult> queryRoomTimeByPorjectId(String projectId);
	
	/**
	 * 条件查询楼盘开盘时间
	 * @Title: queryOpenDateByProjectId   
	 * @Description: 条件查询楼盘开盘时间
	 * @param projectId 新房楼盘id
	 * @return: List<OpenDateResult>
	 */
	List<OpenDateResult> queryOpenDateByProjectId(String projectId);
	/**
	 * 条件查询楼盘价格详情
	 * @Title: queryNewHousePriceDetailByProjectId   
	 * @Description: 条件查询楼盘价格详情
	 * @param projectId 新房楼盘id
	 * @return: List<NewHousePriceDetailResult>
	 */
	List<NewHousePriceDetailResult> queryNewHousePriceDetailByProjectId(String projectId);
	/**
	 * 
		 * 首页查询推荐新房列表
		 * @Description: 首页查询推荐新房列表
		 * @param cityCode 城市代码
		 * @param limit 显示条数
		 * @return 
		 * List<GoodNewHouseListResult>
	 */
	public List<GoodNewHouseListResult> queryGoodProjects(String cityCode,int limit);
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
		 * 新房列表查询
		 * @Description: 新房列表查询
		 * @param param 查询条件
		 * @param page 当前页
		 * @param limit 每页条数
		 * @return 
		 * WebPage<NewHouseListResult>
	 */
	public WebPage<NewHouseListResult> queryNewHouseList(NewHouseQueryParam param,int page,int limit);
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
}
