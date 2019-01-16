/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  HouseActionLogQueryMapper.java   
 * @Package com.bashiju.house.mapper      
 * @author: zuoyuntao     
 * @date:   2018年11月30日 下午4:05:55   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.housing.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 房源操作日志查询持久层接口
 * @ClassName:HouseActionLogQueryMapper
 * @Description:房源操作日志查询持久层接口
 * @author:zuoyuntao
 * @date:2018年11月30日 下午4:05:55
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface HouseActionLogQueryMapper {
	/**
	 * 查询房源日志--带分页
	 * @Title: auxHouseActionLogPageList
	 * @author: zuoyuntao  
	 * @Description:查询房源日志--带分页
	 * @param paramMap 参数对象
	 * @return      
	 * List<Map<String,Object>>
	 */
	public List<Map<String,Object>> auxHouseActionLogPageList(Map<String,Object> paramMap);
	/**
	 * 查询一手房源日志--带分页
	 * @Title: auxHouseActionLogPageList
	 * @author: zuoyuntao  
	 * @Description: 查询一手房源日志--带分页
	 * @param paramMap 参数对象
	 * @return      
	 * List<Map<String,Object>>
	 */
	public List<Map<String,Object>> auxOneHandHouseActionLogPageList(Map<String,Object> paramMap);
	/**
	 * 查询查看电话日志--带分页
	 * @Title: auxHouseActionLogPageList
	 * @author: zuoyuntao  
	 * @Description:查询查看电话日志--带分页
	 * @param paramMap 参数对象
	 * @return      
	 * List<Map<String,Object>>
	 */
	public List<Map<String,Object>> auxTelephoneViewLogPageList(Map<String,Object> paramMap);
	/**
	 * 根据房源编号查询房源操作日志 --带分页
	 * @Title: auxHouseActionLogByHouseId
	 * @author: zuoyuntao  
	 * @Description:根据房源编号查询房源操作日志
	 * @param houseId 房源编号
	 * @return      
	 * Page<Map<String,Object>>
	 */
	public List<Map<String,Object>> auxHouseActionLogByHouseId(@Param("houseId")String houseId);
	/**
	 * 根据房源类型查询房源操作日志--带分页
	 * @Title: auxHouseActionLogByHouseType
	 * @author: zuoyuntao  
	 * @Description:根据房源编号查询房源操作日志
	 * @param houseType
	 * @return      
	 * Page<Map<String,Object>>
	 */
	public List<Map<String,Object>> auxHouseActionLogByHouseType(@Param("houseType")String houseType);
}
