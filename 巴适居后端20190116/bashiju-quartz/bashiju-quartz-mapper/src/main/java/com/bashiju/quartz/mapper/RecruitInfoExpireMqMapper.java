/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  RecruitInfoExpireMqMapper.java   
 * @Package com.bashiju.quartz.mapper      
 * @author: zuoyuntao     
 * @date:   2018年8月9日 上午11:05:55   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.quartz.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 招聘岗位过期处理持久层接口
 * @ClassName:RecruitInfoExpireMqMapper
 * @Description:招聘岗位过期处理持久层接口
 * @author:zuoyuntao
 * @date:2018年8月9日 上午11:05:55
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface RecruitInfoExpireMqMapper {
	/**
	 * 更新发布岗位的状态
	 * @Title: updateRecruitInfoStatusById
	 * @author: zuoyuntao  
	 * @Description:更新发布岗位的状态
	 * @param id
	 * @param updateTime      
	 * void 
	 */
	public void updateRecruitInfoStatusById(@Param("id") String id
			,@Param("updateTime")String updateTime);
	/**
	 * 查询所有有效的发布岗位
	 * @Title: queryAllRecruitInfoList
	 * @author: zuoyuntao  
	 * @Description:查询所有有效的发布岗位 
	 * @return      
	 * List<Map<String,Object>> JSON 格式为：
	 */
	public List<Map<String,Object>> queryAllRecruitInfoList();
}
