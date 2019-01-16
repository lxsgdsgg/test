/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerConcernedHouseMapper.java   
 * @Package com.bashiju.webservice.mapper      
 * @author: zuoyuntao     
 * @date:   2018年7月26日 下午4:47:22   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.webservice.mapper;

import org.apache.ibatis.annotations.Param;

import com.bashiju.www.pojo.service.community.Community;
import com.bashiju.www.pojo.service.out.usercentral.CustomerConcernedHouseEntity;
import com.github.pagehelper.Page;

/**
 * 用户个人中心--我的关注数据查询接口
 * @ClassName:CustomerConcernedHouseMapper
 * @Description:用户个人中心--我的关注数据查询接口
 * @author:zuoyuntao
 * @date:2018年7月26日 下午4:47:22
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface CustomerConcernedHouseMapper {
	/**
	 * 查询指定用户的所有关注的房源（包括新房、二手房和租房）
	 * @Title: queryAllConcernedHouseList
	 * @author: zuoyuntao  
	 * @Description:查询指定用户的所有关注的房源（包括新房、二手房和租房）
	 * @param custId 登陆用户ID
	 * @param type 关注类型 （1：新房、2：二手房、3：租房、4：小区）
	 * @return      
	 * Page<CustomerConcernedHouseEntity>
	 */
	public Page<CustomerConcernedHouseEntity> queryAllConcernedHouseList(@Param("custId")String custId
			,@Param("type")String type);
	
	/**
	 * 查询指定用户的所有关注的房源（包括新房、二手房和租房）
	 * @Title: queryAllConcernedCommunityList
	 * @author: zuoyuntao  
	 * @Description:查询指定用户的所有关注的房源（包括新房、二手房和租房）
	 * @param custId 登陆用户ID
	 * @return      
	 * Page<CustomerConcernedHouseEntity>
	 */
	public Page<Community> queryAllConcernedCommunityList(@Param("custId")String custId);
	/**
	 * 新房关注查询 
	 * @Title: queryAllConcernedNewHouseList
	 * @author: zuoyuntao  
	 * @Description:新房关注查询
	 * @param custId 客户ID
	 * @return      
	 * Page<CustomerConcernedHouseEntity> 
	 */
	public Page<CustomerConcernedHouseEntity> queryAllConcernedNewHouseList(@Param("custId")String custId);
	/**
	 * 统计我的关注信息
	 * @Title: countMyConcerned
	 * @author: zuoyuntao  
	 * @Description:统计我的关注信息
	 * @param custId
	 * @return      
	 * long 
	 */
	public long countMyConcerned(@Param("custId")String custId);
}
