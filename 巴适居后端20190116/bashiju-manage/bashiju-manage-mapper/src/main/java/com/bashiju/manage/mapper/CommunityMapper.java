package com.bashiju.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

/**
 * 
 * @ClassName:  CommunityMapper   
 * @Description:TODO(小区管理)   
 * @author: wangkaifa
 * @date:   2018年4月20日 下午5:07:36   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface CommunityMapper {
	/**
	 * 
	 * @Title: queryCommunityList   
	 * @Description: TODO(查询小区列表)   
	 * @param: @param map
	 * @param: @return      
	 * @return: Page<Map<String,Object>>      
	 * @throws
	 */
	public Page<Map<String, Object>>  queryCommunityList(Map<String, Object> map);
	/**
	 * 
	 * @Title: queryCommunityById   
	 * @Description: TODO(根据id查询小区信息)   
	 * @param: @param id
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public Map<String, Object>  queryCommunityById(@Param("id") Long id);
	/**
	 * 
	 * @Title: countCommunityByName   
	 * @Description: TODO(判断小区名称是否重名)   
	 * @param: name
	 * @param: areaCode   
	 * @param: recordName  
	 * @return: long      
	 * @throws
	 */
	public long countCommunityByName(@Param("name") String name,@Param("recordName") String recordName,@Param("areaCode") String areaCode);
	/**
	 * 
	 * @Title: queryAreaList   
	 * @Description: TODO(区域下拉列表)   
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> queryAreaList();
	/**
	 * 
	 * @Title: queryRegionsByArea   
	 * @Description: TODO(片区下拉列表)   
	 * @param: @param code
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> queryRegionsByArea(@Param("code") String code);
	/**
	 * 
	 * @Title: queryImageBycode   
	 * @Description: TODO(查询图片流)   
	 * @param: @param code
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public Map<String, Object> queryImageBycode(@Param("code") String code);
	/**
	 * 
	 * @Title: queryImagesByCommunity   
	 * @Description: TODO(查询小区图片编码列表)   
	 * @param: @param communityId
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> queryImagesByCommunity(@Param("communityId") Long communityId);
	/**
	 * 
	 * @Title: queryImage   
	 * @Description: TODO(查询小区图片)   
	 * @param: @param communityId
	 * @param: @param attachType
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> queryImage(@Param("communityId") int communityId,@Param("attachType") String attachType);
	/**
	 * 
	 * @Title: deleteImage   
	 * @Description: TODO(物理删除小区图片)   
	 * @param: @param code      
	 * @return: void      
	 * @throws
	 */
	void deleteImage(@Param("id") int id);
	/**
	 * 
		 * 查询小区属性列表
		 * @Description: 查询小区属性列表
		 * @param communityId 小区id
		 * @return [{id:2,type:2,code:2,name:钢、钢筋混凝土结构}]
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryCommunityPropertys(@Param("communityId") Long communityId);
	/**
	 * 
		 * 查询片城市、区域、区列表
		 * @Description: 查询片城市、区域、区列表
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryRegionList();
	/**
	 * 
		 * 根据id查询图片路径
		 * @Description: 根据id查询图片路径 
		 * @param ids
		 * @return 
		 * List<Map<String, Object>>
	 */
	public List<Map<String, Object>> queryPathByIds(@Param("ids")List<Integer> ids);
	/**
	 * 
		 * pc端同步小区
		 * @Description: pc端同步小区
		 * @param communityIds 小区id逗号分隔
		 * void
	 */
	public void communitySynchron(@Param("communityIds") String communityIds);
	/**
	 * 
		 * 将楼盘数据同步到客户端查询表中   
		 * @Description: 将楼盘数据同步到客户端查询表中 ） 
		 * @param houseIds 待同步的楼盘id(多个用英文逗号隔开)
		 * void
	 */
	public void newHouseSynchron(@Param("houseIds")String houseIds);
	/**
	 * 
		 * 根据小区查询楼盘id
		 * @Description: 根据小区查询楼盘id 
		 * @param communityId
		 * @return 
		 * Map<String,Object>
	 */
	public Map<String, Object> queryProjectId(@Param("communityId") Long communityId);
}
