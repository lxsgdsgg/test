/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  IMetroStationManageMapper.java   
 * @Package com.bashiju.manage.mapper   
 * @Description:地铁线路信息维护映射类
 * @author: zuoyuntao     
 * @date:   2018年6月13日 下午5:32:32   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

/**
 * 地铁线路信息维护映射类
 * @ClassName:IMetroStationManageMapper
 * @Description:地铁线路信息维护映射类
 * @author:zuoyuntao
 * @date:2018年6月13日 下午5:32:32
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface IMetroStationManageMapper {
	/**
	 * 查询所有地铁线路数据信息—带分页
	 * @Title: queryAllMetroStationInfo
	 * @author: zuoyuntao  
	 * @Description:（详细描述）   
	 * @param paraMap
	 * @return      
	 * Page<Map<String,Object>> JSON 格式为：{}     
	 * @throws
	 */
	public Page<Map<String, Object>> queryAllMetroStationInfo(Map<String, Object> paraMap);
	/**
	 * 根据给定条件查询地铁线路数据信息
	 * @Title: queryMetroDataByCondition
	 * @author: zuoyuntao  
	 * @Description:根据给定条件查询地铁线路数据信息   
	 * @param paraMap
	 * @return      
	 * List<Map<String,Object>> JSON 格式为：{}     
	 * @throws
	 */
	public List<Map<String, Object>> queryMetroDataByCondition(
			@Param("condition") String condition);
	/**
	 *  查询地铁站点周边小区信息
	 * @Title: getStationCommunityPageObj
	 * @author: zuoyuntao  
	 * @Description: 查询地铁站点周边小区信息
	 * @param paraMap
	 * @return      
	 * Page<Map<String,Object>> JSON 格式为：{}     
	 * @throws
	 */
	public Page<Map<String, Object>> getStationCommunityPageObj(Map<String, Object> paraMap);
	/**
	 * 创建临时表
	 * @Title: creatTempTable
	 * @author: zuoyuntao  
	 * @Description:创建临时表     
	 * void JSON 格式为：
	 */
	public void creatTempTable();
	/**
	 * 从临时表读取数据保存到正式表
	 * @Title: saveMetroStationDataFromTempTable
	 * @author: zuoyuntao  
	 * @Description:从临时表读取数据保存到正式表         
	 * void 
	 */
	public void saveMetroStationDataFromTempTable();
	/**
	 * 更新数据
	 * @Title: updateMetroStationDataFromTempTable
	 * @author: zuoyuntao  
	 * @Description:更新数据         
	 * void
	 */
	public void updateMetroStationDataFromTempTable();
	/**
	 * 删除临时表 
	 * @Title: dropTempTable
	 * @author: zuoyuntao  
	 * @Description:删除临时表      
	 * void
	 */
	public void dropTempTable();
}
