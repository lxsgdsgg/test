/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  StoreShareManageMapper.java   
 * @Package com.bashiju.fin.mapper      
 * @author: zuoyuntao     
 * @date:   2018年12月5日 上午9:34:40   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.fin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 门店分摊管理持久层接口
 * @ClassName:StoreShareManageMapper
 * @Description:门店分摊管理持久层接口
 * @author:zuoyuntao
 * @date:2018年12月5日 上午9:34:40
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface StoreShareManageMapper {
	/**
	 * 查询门店分摊管理页面列表
	 * @Title: queryStoreSharePageList
	 * @author: zuoyuntao  
	 * @Description:查询门店分摊管理页面列表
	 * @param paraMap 参数对象
	 * @return      
	 * List<Map<String,Object>> JSON 格式为：
	 */
	public List<Map<String,Object>> queryStoreSharePageList(Map<String,Object> paraMap);
	/**
	 * 根据部门ID查询下属门店
	 * @Title: querySubordinateStoreDataByDeptId
	 * @author: zuoyuntao  
	 * @Description:根据部门ID查询下属门店
	 * @param deptId 部门ID
	 * @return      
	 * List<Map<String,Object>> 
	 */
	public List<Map<String,Object>> querySubordinateStoreDataByDeptId(@Param("deptId")String deptId);
	/**
	 * 根据配置ID查询下属门店分摊详细
	 * @Title: queryStoreShareDetailByShareId
	 * @author: zuoyuntao  
	 * @Description:根据部门ID查询下属门店
	 * @param shareId 主配置ID
	 * @return      
	 * List<Map<String,Object>> 
	 */
	public List<Map<String,Object>> queryStoreShareDetailByShareId(@Param("shareId")String shareId);

}
