/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerPersonalCentralService.java   
 * @Package com.bashiju.www.service.user      
 * @author: zuoyuntao     
 * @date:   2018年8月2日 下午3:03:41   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.service.user;

import java.util.List;
import java.util.Map;

import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.agent.Agent;
import com.bashiju.www.pojo.service.out.usercentral.AdministrativeDivisionEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerHouseTransitScheduleEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerOwnerAppointmentEntity;
import com.bashiju.www.pojo.service.reservation.ReservationParam;
import com.bashiju.www.pojo.service.user.CustCustomerEntity;

/**
 * 个人中心服务接口
 * @ClassName:CustomerPersonalCentralService
 * @Description:个人中心服务接口
 * @author:zuoyuntao
 * @date:2018年8月2日 下午3:03:41
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface ICustomerPersonalCentralService {
	/**
	 * 获取【个人信息】 
	 * @Title: queryPersonalInfo
	 * @author: zuoyuntao  
	 * @Description:获取【个人信息】
	 * @param custId 客户ID
	 * @param numType 客户登陆类型
	 * @return      
	 * CustCustomerEntity
	 */
	public CustCustomerEntity queryPersonalInfo(String custId,String numType);
	/**
	 * 查询客户个人详细信息 
	 * @Title: queryPersonalDetailInfo
	 * @author: zuoyuntao  
	 * @Description:查询客户个人详细信息   
	 * @param custId 客户ID
	 * @param numType 客户登陆类型
	 * @return      
	 * CustCustomerEntity
	 */
	public CustCustomerEntity queryPersonalDetailInfo(String custId,String numType);
	/**
	 * 获取客户房源过户进度详细
	 * @Title: queryTransitScheduleHouseDetail
	 * @author: zuoyuntao  
	 * @Description:获取客户房源过户进度详细
	 * @param custId 客户ID
	 * @return      
	 * List<CustCustomerEntity>
	 */
	public List<CustomerHouseTransitScheduleEntity> queryTransitScheduleHouseDetail(
			String custId);
	/**
	 * 发送手机验证码
	 * @Title: sendMobileVerfyCode
	 * @author: zuoyuntao  
	 * @Description:获取验证码   
	 * @param mobile 手机号码  
	 * void
	 */
	public void sendMobileVerfyCode(String mobile);
	/**
	 * 客户修改密码
	 * @Title: modifyCustomerPassword
	 * @author: zuoyuntao  
	 * @Description:客户修改密码
	 * @param oriPassword 原密码
	 * @param custId 客户ID
	 * @param finalPassword 客户最终密码      
	 * void 
	 */
	public void modifyCustomerPassword(String custId,String oriPassword,String finalPassword);
	/**
	 * 查询客户收藏的经纪人
	 * @Title: queryMyselfAgentList
	 * @author: zuoyuntao  
	 * @Description:查询客户收藏的经纪人
	 * @param custId 客户ID
	 * @param agentType 经纪人类型（0：我聊过的经纪人 1：带看过的经纪人）   
	 * @param page 当前页
	 * @param limit 每页最大条数
	 * WebPage<Agent> 
	 */
	public WebPage<Agent> queryMyselfAgentList(String custId,String agentType,int page,int limit);
	/**
	 * 查询我的预约
	 * @Title: queryMyselfApointmentList
	 * @author: zuoyuntao  
	 * @Description:查询我的预约  
	 * @param custId 客户ID
	 * @param page 当前页
	 * @param limit 每页最大条数
	 * @return      
	 * List<CustomerOwnerAppointmentEntity> 
	 */
	public WebPage<CustomerOwnerAppointmentEntity> queryMyselfAppointmentList(String custId,int page,int limit) ;
	/**
	 * 取消预约
	 * @Title: concelMyselfAppointment
	 * @author: zuoyuntao  
	 * @Description:取消预约
	 * @param id 预约ID
	 * void 
	 */
	public void concelMyselfAppointment(String id);
	/**
	 * 保存用户个人信息
	 * @Title: saveCustCustomerInfo
	 * @author: zuoyuntao  
	 * @Description:保存用户个人信息 
	 * @param custEntity      
	 * void JSON 格式为：
	 */
	public void updateCustCustomerInfo(CustCustomerEntity custEntity);
	/***
	 * @Title: saveReservationWatchHouse   
	 * @Description: 新增预约   
	 * @param reservationParam 用户输入参数
	 * @return: boolean
	 */
	public boolean saveReservationWatchHouse(ReservationParam reservationParam);
	
	/**
	 * @Title: queryMaintainInfoByShhId   
	 * @Description: 条件查询房源维护人信息
	 * @param shhId 房源编号
	 * @return: Map<String,Object>
	 */
	public Map<String, Object> queryMaintainInfoByShhId(String shhId);
	
	/**
	 * @Title: queryIsExistReservationWatchHouseByCustIdAndShhId   
	 * @Description: 条件查询预约是否已存在
	 * @param custId 客户id
	 * @param shhId 房源id
	 * @return: boolean
	 */
	public boolean queryIsExistReservationWatchHouseByCustIdAndShhId(String custId,String shhId);
	/**
	 * 根据上级编码查询行政区划
	 * @Title: queryCountryAreaListByCityCode
	 * @author: zuoyuntao  
	 * @Description:根据上级编码查询行政区划
	 * @param parentCode 上级行政区划编码
	 * @param level 级次
	 * @return      
	 * List<AdministrativeDivisionEntity>
	 */
	public List<AdministrativeDivisionEntity> queryAreaListByPro(String parentCode,String level);
	/**
	 * 查询所有行政区划
	 * @Title: queryCountryAreaListByCityCode
	 * @author: zuoyuntao  
	 * @Description:查询所有行政区划
	 * @return      
	 * List<AdministrativeDivisionEntity>
	 */
	public List<AdministrativeDivisionEntity> queryAllAreaList();
	
	/**
	 * 删除我的预约
	 * @Title: deleteAppointment
	 * @author: zuoyuntao  
	 * @Description:删除我的预约
	 * @param id 预约ID
	 * @return      
	 * boolean 成功true/失败false
	 */
	public boolean deleteAppointment(String id);
}
