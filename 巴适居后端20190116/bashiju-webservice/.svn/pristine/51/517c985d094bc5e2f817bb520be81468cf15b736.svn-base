/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerPersonalCentralMapper.java   
 * @Package com.bashiju.webservice.mapper      
 * @author: zuoyuntao     
 * @date:   2018年7月25日 下午2:41:21   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.webservice.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bashiju.www.pojo.service.out.usercentral.CustomerHouseDetailEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerOwnerHouseEntity;
import com.github.pagehelper.Page;

/**
 * 客户个人中心映射接口
 * @ClassName:CustomerPersonalCentralMapper
 * @Description:客户个人中心映射接口
 * @author:zuoyuntao
 * @date:2018年7月25日 下午2:41:21
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface CustomerOwnerHouseMapper {
	/**
	 * 获取我的房子信息
	 * @Title: queryMyOwnerHouseDataList
	 * @author: zuoyuntao  
	 * @Description:获取我的房子信息
	 * @param custId 客户ID
	 * @return      
	 * Page<CustomerOwnerHouseEntity>
	 */
	public Page<CustomerOwnerHouseEntity> queryMyOwnerHouseDataList(@Param("custId") String custId);
	/**
	 * 绑定用户和房源关系信息
	 * @Title: saveBindingHouseResult
	 * @author: zuoyuntao  
	 * @Description:绑定用户和房源关系信息
	 * @param paraMap 必须包含{custId,shhId,addTime,isValid}
	 * @return      
	 * long
	 */
	public long saveBindingHouseResult(@Param("paraMap")Map<String,Object> paraMap);
	/**
	 * 根据条件查询房源绑定情况
	 * @Title: countAlreadyBindHouse
	 * @author: zuoyuntao  
	 * @Description:根据条件查询房源绑定情况   
	 * @param custId 客户ID
	 * @param sourcesId 资源ID 
	 * @param transactionType 交易类型（0 出售 1出租）
	 * @return      
	 * long 
	 */
	public long countAlreadyBindHouse(@Param("custId") String custId,@Param("sourcesId") String sourcesId
			,@Param("transactionType") String transactionType);
	/**
	 * 根据房源ID查询房源详细信息
	 * @Title: queryMyHouseDetailByHouseID
	 * @author: zuoyuntao  
	 * @Description:根据房源ID查询房源详细信息
	 * @param houseId 房源ID
	 * @return      
	 * CustomerHouseDetailEntity
	 */
	public CustomerHouseDetailEntity queryMyHouseDetailByHouseID(@Param("houseId")String houseId);
	/**
	 * 根据绑定ID删除我的房源
	 * @Title: deleteMyHouseByBindId
	 * @author: zuoyuntao  
	 * @Description:根据绑定ID删除我的房源
	 * @param id 房源绑定ID
	 * void 
	 */
	public void deleteMyHouseByBindId(@Param("id")String id);
	
	/**
	 * 取消绑定我的房源
	 * @Title: cancelBindMyHouseByBindId
	 * @author: zuoyuntao  
	 * @Description:取消绑定我的房源
	 * @param id      
	 * void 
	 */
	public void cancelBindMyHouseByBindId(@Param("id")String id);
	
	/**
	 * 重新委托【我的房源】（房源信息无变动）
	 * @Title: againEntrustMyHouseById
	 * @author: zuoyuntao  
	 * @Description:重新委托【我的房源】（房源信息无变动）
	 * @param houseId 房源ID
	 * @param custId 用户Id
	 * void 
	 */
	public void againEntrustMyHouseById(@Param("houseId")String houseId,@Param("custId")String custId);
	/**
	 * 根据绑定ID查询房源详细信息
	 * @Title: queryHoueseDetailById
	 * @author: zuoyuntao  
	 * @Description:根据绑定ID查询房源详细信息
	 * @param id 房源绑定ID
	 * @param custId 客户id
	 * CustomerOwnerHouseEntity
	 */
	public CustomerOwnerHouseEntity queryHoueseDetailById(@Param("id")String id,@Param("custId")String custId);
}
