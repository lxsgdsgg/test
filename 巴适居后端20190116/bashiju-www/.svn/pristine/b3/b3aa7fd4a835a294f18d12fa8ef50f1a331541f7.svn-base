/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CommunityService.java   
 * @Package com.bashiju.www.service.community   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年7月24日 下午2:24:38   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.www.service.community;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bashiju.www.pojo.service.community.Community;
import com.bashiju.www.pojo.service.community.CommunityDetail;
import com.bashiju.www.pojo.service.community.CommunityQueryParams;
import com.bashiju.www.pojo.service.community.HotCommunity;
import com.bashiju.www.pojo.service.out.house.HouseListResult;
import com.github.pagehelper.Page;

/**   
 * @ClassName:  CommunityService   
 * @Description:小区信息服务接口   
 * @author: yangz
 * @date:   2018年7月24日 下午2:24:38   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface CommunityService {
	
	/**
	 * @Description: 条件查询小区信息   
	 * @param params 查询参数
	 * @return: Page<Community>
	 */
	public Page<Community> queryCommunitys(CommunityQueryParams params);
	
	
	/**
	 * @Description: 查询城市下的热门小区  
	 * @param cityCode 城市代码
	 * @param size 查询的条数
	 * @return: List<HotCommunity>
	 */
	public List<HotCommunity> queryHotCommunitys(String cityCode,int size);
	
	/**
	 * @Description: 根据小区编号查询小区详情信息   
	 * @param communityId 小区编号
	 * @return: CommunityDetail
	 */
	public CommunityDetail getCommunityDetail(String communityId);
	
	/**
	 * @Title: querySealHousingByCommunityId   
	 * @Description: 条件查询在售房源
	 * @param communityId 小区编号
	 * @return: List<HouseListResult>
	 */
	public List<HouseListResult> querySealHousingByCommunityId(String communityId);
	
	/**
	 * @Title: queryLeaseHousingByCommunityId   
	 * @Description: 条件出租房源   
	 * @param communityId 小区编号
	 * @return: List<HouseListResult>
	 */
	public List<HouseListResult> queryLeaseHousingByCommunityId(String communityId);
	
	
	/**
	 * @Title: queryNearbyCommunity   
	 * @Description: 条件查询附近小区   
	 * @param communityId 小区id
	 * @return: List<Community>
	 */
	public List<Community> queryNearbyCommunity(@Param("communityId")String communityId);
	
}
