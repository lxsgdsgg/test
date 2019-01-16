/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  UserDesertsInfoMapper.java   
 * @Package com.bashiju.fin.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年9月10日 上午10:27:49   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.fin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName:  UserDesertsInfoMapper   
 * @Description:员工奖罚持久层   
 * @author: yangz
 * @date:   2018年9月10日 上午10:27:49   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface UserDesertsInfoMapper {

	/**
	 * 查询员工奖罚信息
	 * @Description: 查询员工奖罚信息   
	 * @param userName 员工名称(模糊查询)
	 * @param type 奖罚类型(1：奖，-1：罚 )
	 * @param classesId 奖罚类目编号
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryUserDesertsInfos(@Param("userId")Long userId,@Param("deptId")String deptId,@Param("type")String type,@Param("classesId")String classesId,@Param("months")String months);
	
	/**
	 * 根据员工奖罚编号查询奖罚信息
	 * @Description: 根据员工奖罚编号查询奖罚信息  
	 * @param userDesertsId 员工奖罚编号
	 * @return: Map<String,Object>
	 */
	public Map<String,Object> getUserDesertsInfo(@Param("userDesertsId")String userDesertsId);
	
}
