/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CommunityMapper.java   
 * @Package com.bashiju.www.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年7月24日 下午4:58:53   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.www.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bashiju.www.pojo.service.community.Community;
import com.bashiju.www.pojo.service.community.CommunityDetail;
import com.bashiju.www.pojo.service.community.CommunityQueryParams;
import com.bashiju.www.pojo.service.community.HotCommunity;
import com.bashiju.www.pojo.service.out.house.HouseListResult;

/**   
 * @ClassName:  CommunityMapper   
 * @Description:小区信息持久层  
 * @author: yangz
 * @date:   2018年7月24日 下午4:58:53   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface CommunityMapper {

	/**
	 * @Description: 条件查询小区信息   
	 * @param params 查询参数
	 * @return: Page<Community>
	 */
	public List<Community> queryCommunitys(CommunityQueryParams params);

	/**
	 * @Description: 查询城市下的热门小区  
	 * @param cityCode 城市代码
	 * @param size 查询的条数
	 * @return: List<HotCommunity>     
	 */ 
	public List<HotCommunity> queryHotCommunitys(@Param("cityCode")String cityCode,@Param("size")int size);
	
	/**
	 * @Title: getCommunityDetail   
	 * @Description: 条件查询小区详情   
	 * @param communityId 小区编号
	 * @return: List<CommunityDetail>
	 */
	public CommunityDetail getCommunityDetail(@Param("communityId")String communityId);
	
	/**
	 * @Title: queryCommunityImages   
	 * @Description: 查询小区图片   
	 * @param communityId 小区编号
	 * @return: List<String>
	 */
	public List<String> queryCommunityImages(@Param("communityId")String communityId);
	
	/**
	 * @Description: 条件查询出售房源 
	 * @param communityId 城市代码
	 * @return:  List<HouseListResult>   
	 */ 
	public List<HouseListResult> querySealHousingByCommunityId(@Param("communityId")String communityId);
	
	/**
	 * @Description: 条件查询出租房源 
	 * @param communityId 城市代码
	 * @return:  List<HouseListResult>   
	 */ 
	public List<HouseListResult> queryLeaseHousingByCommunityId(@Param("communityId")String communityId);
	
	/**
	 * @Title: queryNearbyCommunity   
	 * @Description: 条件查询附近小区  
	 * @param communityId 小区id
	 * @return: List<Community>
	 */
	public List<Community> queryNearbyCommunity(@Param("communityId")String communityId);
	
}
