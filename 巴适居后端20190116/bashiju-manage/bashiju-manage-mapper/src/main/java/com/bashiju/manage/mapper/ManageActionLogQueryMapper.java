/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ManageActionLogQueryMapper.java   
 * @Package com.bashiju.manage.mapper      
 * @author: zuoyuntao     
 * @date:   2018年11月30日 下午4:05:55   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 后台管理操作日志查询持久层接口
 * @ClassName:ManageActionLogQueryMapper
 * @Description:后台管理操作日志查询持久层接口
 * @author:zuoyuntao
 * @date:2018年11月30日 下午4:05:55
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface ManageActionLogQueryMapper {
	/**
	 * 查询后台管理日志--带分页
	 * @Title: auxManageActionLogPageList
	 * @author: zuoyuntao  
	 * @Description:查询后台管理日志--带分页
	 * @param paramMap 参数对象
	 * @return      
	 * List<Map<String,Object>>
	 */
	public List<Map<String,Object>> auxManageActionLogPageList(Map<String,Object> paramMap);
	/**
	 * 查询系统日志--带分页
	 * @Title: auxSystemActionLogPageList
	 * @author: zuoyuntao  
	 * @Description:查询系统日志--带分页
	 * @param paramMap 参数对象
	 * @return      
	 * List<Map<String,Object>>
	 */
	public List<Map<String,Object>> auxSystemActionLogPageList(Map<String,Object> paramMap);
	/**
	 * 根据后台管理编号查询后台管理操作日志 --带分页
	 * @Title: auxManageActionLogByManageIdAndType
	 * @author: zuoyuntao  
	 * @Description:根据后台管理编号查询后台管理操作日志
	 * @param id 后台管理编号
	 * @return      
	 * Page<Map<String,Object>>
	 */
	public List<Map<String,Object>> auxManageActionLogByManageId(@Param("id")String id);
	/**
	 * 根据后台管理类型查询后台管理操作日志--带分页
	 * @Title: auxManageActionLogByManageIdAndType
	 * @author: zuoyuntao  
	 * @Description:根据后台管理编号查询后台管理操作日志
	 * @param type
	 * @return      
	 * Page<Map<String,Object>>
	 */
	public List<Map<String,Object>> auxManageActionLogByManageType(@Param("type")String type);
}
