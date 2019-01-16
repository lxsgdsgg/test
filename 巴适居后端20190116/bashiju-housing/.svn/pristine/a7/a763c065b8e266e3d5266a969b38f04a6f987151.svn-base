/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  IHouseActionLogQueryService.java   
 * @Package com.bashiju.housing.service    
 * @author: zuoyuntao     
 * @date:   2018年11月30日 下午4:05:28   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.housing.service;

import java.util.Map;

import com.github.pagehelper.Page;

/**
 * 房源操作日志查询服务接口
 * @ClassName:IHouseActionLogQueryService
 * @Description:房源操作日志查询服务接口
 * @author:zuoyuntao
 * @date:2018年11月30日 下午4:05:28
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface IHouseActionLogQueryService {

	/**
	 * 查询房源日志--带分页
	 * @Title: auxHouseActionLogPageList
	 * @author: zuoyuntao  
	 * @Description:查询房源日志--带分页
	 * @param page 当前页 
	 * @param limit 每页最大数
	 * @param paramMap 参数对象
	 * @return      
	 * Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> auxHouseActionLogPageList(
			int page,int limit,Map<String,Object> paramMap);
	/**
	 * 查询电话日志--带分页
	 * @Title: auxTelephoneViewLogPageList
	 * @author: zuoyuntao  
	 * @Description:查询电话日志--带分页
	 * @param page 当前页 
	 * @param limit 每页最大数
	 * @param paramMap 参数对象
	 * @return      
	 * Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> auxTelephoneViewLogPageList(
			int page,int limit,Map<String,Object> paramMap);
	/**
	 * 查询一手房源日志--带分页
	 * @Title: auxOneHandHouseActionLogPageList
	 * @author: zuoyuntao  
	 * @Description:查询一手房源日志--带分页
	 * @param page 当前页 
	 * @param limit 每页最大数
	 * @param paramMap 参数对象
	 * @return      
	 * Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> auxOneHandHouseActionLogPageList(
			int page,int limit,Map<String,Object> paramMap);
	/**
	 * 根据房源编号查询房源操作日志 --带分页
	 * @Title: auxHouseActionLogByHouseId
	 * @author: zuoyuntao  
	 * @Description:根据房源编号查询房源操作日志
	 * @param page 当前页
	 * @param limit 每页最大条数
	 * @param dealId 房源编号
	 * @return      
	 * Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> auxHouseActionLogByHouseId(int page,int limit,String dealId);
	/**
	 * 根据房源类型查询房源操作日志--带分页
	 * @Title: auxHouseActionLogByHouseType
	 * @author: zuoyuntao  
	 * @Description:根据房源编号查询房源操作日志
	 * @param page 当前页
	 * @param limit 每页最大条数
	 * @param houseType 房源类型
	 * @return      
	 * Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> auxHouseActionLogByHouseType(
			int page,int limit,String houseType);
}
