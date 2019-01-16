/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  RecruitInfoMapper.java   
 * @Package com.bashiju.oa.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年8月9日 下午5:05:50   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.oa.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

/**   
 * @ClassName:  RecruitInfoMapper   
 * @Description:招聘信息管理映射接口
 * @author: wangpeng
 * @date:   2018年8月9日 下午5:05:50   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface RecruitInfoMapper {
	/**
	 * @Title: queryRecruitInfo   
	 * @Description: 查询招聘信息
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> queryRecruitInfo(@Param("name")String name);
	/**
	 * @Title: queryRecruitInfoById   
	 * @Description: 条件查询招聘信息  
	 * @param id 招聘信息id
	 * @return: Map<String,Object>
	 */
	Map<String,Object> queryRecruitInfoById(@Param("id")String id);
	/**
	 * @Title: saveOrUpdateRecruitInfo   
	 * @Description: 新增或修改招聘信息
	 * @return: long
	 */
	long saveOrUpdateRecruitInfo(Map<String,Object> paramMap);
}
