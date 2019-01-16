/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  TestDataService.java   
 * @Package com.bashiju.manage.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年4月10日 下午6:35:19   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.housing.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.Page;
/**
 * 
 * @ClassName  INewHousingService   
 * @Description 新房楼盘服务接口
 * @author liwen
 * @date   2018年8月17日 下午10:56:43   
 *     
 * @Copyright 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface INewHousingService {

	public LinkedList<Map<String,Object>> queryDept();
	/**
	 * 
	 * @Title: queryNewHouseInfo   
	 * @Description: (查询新房楼盘详细页基本信息)   
	 * @param: @param newHouseId
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public Map<String,Object> queryNewHouseInfo(int newHouseId);
	/**
	 * 
	 * @Title: queryLookHouse   
	 * @Description: (查询带看记录)   
	 * @param: @param newHouseId
	 * @param: @param page
	 * @param: @param limit
	 * @param: @return      
	 * @return: Page<Map<String,Object>>      
	 * @throws
	 */
	public Page<Map<String, Object>> queryLookHouse(int newHouseId,int page,int limit);
	/**
	 * 
	 * @Title: saveOverallview   
	 * @Description:(设置全景看房地址)   
	 * @param: @param newHouseId
	 * @param: @param address
	 * @param: @return      
	 * @return: Long      
	 * @throws
	 */
	public Long saveOverallview(int newHouseId,String address);
	/**
	 * 
	 * @Title: saveHousetypeImg   
	 * @Description: (保存户型图)   
	 * @param: @param request
	 * @param: @return      
	 * @return: Long      
	 * @throws
	 */
	public Long saveHousetypeImg(HttpServletRequest request);
	/**
	 * 
		 * 上传户型图视频
		 * @Description: 上传户型图视频 
		 * @param request
		 * @return 
		 * Long
	 */
	public Long saveHousetypeVideo(HttpServletRequest request);
	/**
	 * 
	 * @Title: queryHousetypeImg   
	 * @Description: (查询户型图)   
	 * @param: @param newHouseId
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String,Object>> queryHousetypeImg(int newHouseId);
	/**
	 * 
	 * @Title: updateHousetypeImg   
	 * @Description: (修改户型图信息)   
	 * @param: @param data
	 * @param: @return      
	 * @return: Long      
	 * @throws
	 */
	public Long updateHousetypeImg(Map<String,Object> data);
	/**
	 * 修改户型图图片
	 * @Title: updateHousetypePicture   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param request      
	 * @return: void      
	 * @throws
	 */
	public String updateHousetypePicture(HttpServletRequest request);
	/**
	 * 
	 * @Title: delHousetypeImg   
	 * @Description:(删除户型图)   
	 * @param: @param ids
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean delHousetypeImg(String ids,int newHouseId);
	/**
	 * 
	 * @Title: querySellHouseInfo   
	 * @Description: (根据单元查询一手房成交房源  )   
	 * @param: @param bulidingUnitId
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String,Object>> querySellHouseInfo(Long bulidingUnitId);
	/**
	 * 
	 * @Title: querySellHouseInfoByUser   
	 * @Description: (查询用户能看到的楼栋单元下的成交情况)   
	 * @param: @param bulidingUnitId
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String,Object>> querySellHouseInfoByUser(Long bulidingUnitId);
	/**
	 * 
	 * @Title: queryBuildingsByCommunity   
	 * @Description: TODO(根据小区出现楼栋列表)   
	 * @param: @param cid 小区id
	 * @param: @return      
	 * @return: Page<Map<String,Object>>      
	 * @throws
	 */
	public Page<Map<String, Object>>  queryBuildingsByCommunity(Long cid,int page,int limit);
	/**
	 * 
	 * @Title: queryBuildingUnitsByBuild   
	 * @Description: TODO(根据楼栋号查询单元列表)   
	 * @param: @param bid 楼栋id
	 * @param: @return      
	 * @return: Page<Map<String,Object>>      
	 * @throws
	 */
	public Page<Map<String, Object>>  queryBuildingUnitsByBuild(Long bid,int page,int limit);
	/**
	 * 
	 * @Title: queryHouseByUnit   
	 * @Description: TODO(根据单元查询房号列表)   
	 * @param: @param buid 单元id
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>>  queryHouseByUnit(Long buid);
	/**
	 * 
	 * @Title: updateHousing   
	 * @Description: TODO(修改房源信息)   
	 * @param: projectData
	 * @param: communityData
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int updateHousing(Map<String,Object> projectData,Map<String,Object> communityData);
	
	


	/**
	 * 
		 * 添加新房楼盘
		 * @Description: 添加新房楼盘
		 * @param param {communityData:{楼盘小区数据(有id是修改，没有id是新增小区)},newHouseData:{楼盘信息}}
		 * @return 
		 * Integer
	 */
	public Integer addNewHouse(String param);
	/**
	 * 
		 * 根据小区名称查询小区信息
		 * @Description: 根据小区名称查询小区信息
		 * @param areaCode 区域代码
		 * @param name 小区名称
		 * @return 
		 * Map<String,Object>
	 */
	public Map<String, Object> queryCommunityByName(String areaCode,String name);
	/**
	 * 
		 * 删除新房楼盘
		 * @Description: 删除新房楼盘） 
		 * @param id 楼盘id
		 * @return 
		 * Integer
	 */
	public boolean deleteNewHouse(Integer id);
	/**
	 * 
		 * 设置新房楼盘同步状态
		 * @Description: 设置新房楼盘同步状态
		 * @param id 新房楼盘id
		 * @param result 1为同步，0为不同步
		 * @return 
		 * Integer
	 */
	public Integer setSynchron(Integer id,Integer result);
	/**
	 * 
		 * 设置新房楼盘推荐状态
		 * @Description: 设置新房楼盘推荐状态
		 * @param id 新房楼盘id
		 * @param result 1为推荐，0为不推荐
		 * @return 
		 * Integer
	 */
	public Integer setIsGood(Integer id,Integer result);
	/**
	 * 
		 * 查询新房楼盘列表
		 * @Description: 查询新房楼盘列表
		 * @param param
		 * @return 
		 * Page<Map<String,Object>>
	 */
	public Page<Map<String, Object>> queryNewHouseList(String param,int page,int limit);
	/**
	 * 
		 * 查询当前用户公司下的用户列表
		 * @Description: 查询当前用户公司下的用户列表 
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryUserByCompanyId();
	/**
	 * 
		 * 新房成交
		 * @Description: 新房成交 
		 * @param dataJson 成交信息
		 * @param commissionsJson  佣金信息
		 * @return 
		 * boolean
	 */
	public boolean dealNewHousing(String dataJson, String commissionsJson);
	/**
	 * 
		 * 修改楼盘客户
		 * @Description: 修改楼盘客户 
		 * @param custServicerId 客户id
		 * @param  projectId 楼盘id
		 * @return 
		 * long
	 */
	public long updateCustServicer(Integer projectId,long custServicerId);
	/**
	 * 
		 * 更新楼盘佣金
		 * @Description: 更新楼盘佣金
		 * @param  projectId 楼盘id
		 * @param channelCommissions 渠道佣金
		 * @param settlementCommissions 结算佣金
		 * @return 
		 * long
	 */
	public long updateCommissions(Integer projectId,Integer channelCommissions,Integer settlementCommissions); 
	/**
	 * 
	 * 计算成交佣金   
	 * @Description: TODO(计算成交佣金 )   
	 * @param: @param companyId
	 * @param: @param price
	 * @param: @return      
	 * @return: double      
	 * @throws
	 */
	public double queryCommissionPrice(String companyId,String price);
}
