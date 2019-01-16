package com.bashiju.quartz.mapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


/**
 * 二手房源定时任务Mapper
 * @ClassName  ErTaskMapper   
 * @DescriptionTODO(这里用一句话描述这个类的作用)   
 * @author zhaobin
 * @date   2018年5月31日 下午6:22:14   
 *     
 * @Copyright 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface HouseAndCustomerStatusTaskMapper {

	/**获取房源回访配置**/
	public List<Map<String, Object>> getHouseReturnVisitConfig(@Param("companyId")String companyId);
	/**获取客源回访配置**/
	public List<Map<String, Object>> getCustomerReturnVisitConfig(@Param("companyId")String companyId);
	/**
	 * 
	 * @Title getHouseAllFllowTimeOutInfo   
	 * @Description  查询全员超期未回访的房源Id   
	 * @param allFollowDay 全员跟进超期天数
	 * @param  transactionTypeId 交易类型Id
	 * @param  statusId 状态Id（有效、成交等）
	 * @param  discStatusId 盘状态Id（公盘、私盘）
	 * @param  levelType  房源等级 
	 * @return Map<String,Object> 返回类型
	 */
	public List<Map<String,Object>> getHouseAllFllowTimeOutInfo(@Param("allFollowDay")int allFollowDay,@Param("transactionTypeId") String transactionTypeId,@Param("statusId") String statusId,@Param("taboBaoStatusId") String taboBaoStatusId,@Param("discStatusId") String discStatusId,@Param("levelType") String levelType,@Param("companyId") String companyId);
	
	/**
	 * 
	 * @Title getHouseMainterFllowTimeOutInfo   
	 * @Description  查询维护人超期未回访的房源Id   
	 * @param  mainterFllowDay 维护人跟进超期天数
	 * @param  transactionTypeId 交易类型Id
	 * @param  statusId 状态Id（有效、成交等）
	 * @param  discStatusId 盘状态Id（公盘、私盘）
	 * @param  levelType  房源等级 
	 * @return Map<String,Object> 返回类型
	 */
	public List<Map<String,Object>> getHouseMainterFllowTimeOutInfo(@Param("mainterFllowDay")int mainterFllowDay,@Param("transactionTypeId") String transactionTypeId,@Param("statusId") String statusId,@Param("taboBaoStatusId") String taboBaoStatusId,@Param("discStatusId") String discStatusId,@Param("levelType") String levelType,@Param("companyId") String companyId);
	

	/**
	 * 
	 * @Title getHouseFllowTimeOutInfo   
	 * @Description  查询全员超期未带看的房源Id   
	 * @param allFollowDay 全员跟进超期天数
	 * @param  transactionTypeId 交易类型Id
	 * @param  statusId 状态Id（有效、成交等）
	 * @param  discStatusId 盘状态Id（公盘、私盘）
	 * @param  levelType  房源等级 
	 * @return Map<String,Object> 返回类型
	 */
	public List<Map<String,Object>> getHouseAllLookTimeOutInfo(@Param("lastAllShowedDay")int lastAllShowedDay,@Param("transactionTypeId") String transactionTypeId,@Param("statusId") String statusId,@Param("taboBaoStatusId") String taboBaoStatusId,@Param("discStatusId") String discStatusId,
			@Param("levelType") String levelType,@Param("companyId") String companyId);
	
	/**
	 * 
	 * @Title getHouseMainterLookTimeOutInfo   
	 * @Description  查询维护人超期未带看的房源Id   
	 * @param  mainterFllowDay 维护人跟进超期天数
	 * @param  transactionTypeId 交易类型Id
	 * @param  statusId 状态Id（有效、成交等）
	 * @param  discStatusId 盘状态Id（公盘、私盘）
	 * @param  levelType  房源等级 
	 * @return String 返回类型
	 */
	public List<Map<String,Object>> getHouseMainterLookTimeOutInfo(@Param("lastShowedDay")int lastShowedDay,@Param("transactionTypeId") String transactionTypeId,@Param("statusId") String statusId,@Param("taboBaoStatusId") String taboBaoStatusId,@Param("discStatusId") String discStatusId,
			@Param("levelType") String levelType,@Param("companyId") String companyId);
	
	
	
	/**
	 * 
	 * @Title getCustomerAllFllowTimeOutInfo   
	 * @Description  查询全员超期未回访的房源Id   
	 * @param allFollowDay 全员跟进超期天数
	 * @param  transactionTypeId 交易类型Id
	 * @param  statusId 状态Id（有效、成交等）
	 * @param  discStatusId 盘状态Id（公盘、私盘）
	 * @param  levelType  房源等级 
	 * @return Map<String,Object> 返回类型
	 */
	public List<Map<String,Object>> getCustomerAllFllowTimeOutInfo(@Param("allFollowDay")int allFollowDay,@Param("transactionTypeId") String transactionTypeId,@Param("statusId") String statusId,@Param("taboBaoStatusId") String taboBaoStatusId,@Param("discStatusId") String discStatusId,@Param("levelType") String levelType,@Param("companyId") String companyId);
	
	/**
	 * 
	 * @Title getMainterFllowTimeOutInfo   
	 * @Description  查询维护人超期未回访的房源Id   
	 * @param  mainterFllowDay 维护人跟进超期天数
	 * @param  transactionTypeId 交易类型Id
	 * @param  statusId 状态Id（有效、成交等）
	 * @param  discStatusId 盘状态Id（公盘、私盘）
	 * @param  levelType  房源等级 
	 * @return Map<String,Object> 返回类型
	 */
	public List<Map<String,Object>> getCustomerMainterFllowTimeOutInfo(@Param("mainterFllowDay")int mainterFllowDay,@Param("transactionTypeId") String transactionTypeId,@Param("statusId") String statusId,@Param("taboBaoStatusId") String taboBaoStatusId,@Param("discStatusId") String discStatusId,@Param("levelType") String levelType,@Param("companyId") String companyId);
	

	/**
	 * 
	 * @Title getFllowTimeOutInfo   
	 * @Description  查询全员超期未带看的房源Id   
	 * @param allFollowDay 全员跟进超期天数
	 * @param  transactionTypeId 交易类型Id
	 * @param  statusId 状态Id（有效、成交等）
	 * @param  discStatusId 盘状态Id（公盘、私盘）
	 * @param  levelType  房源等级 
	 * @return Map<String,Object> 返回类型
	 */
	public List<Map<String,Object>> getCustomerAllLookTimeOutInfo(@Param("lastAllShowedDay")int lastAllShowedDay,@Param("transactionTypeId") String transactionTypeId,@Param("statusId") String statusId,@Param("taboBaoStatusId") String taboBaoStatusId,@Param("discStatusId") String discStatusId,
			@Param("levelType") String levelType,@Param("companyId") String companyId);
	
	/**
	 * 
	 * @Title getMainterLookTimeOutInfo   
	 * @Description  查询维护人超期未带看的房源Id   
	 * @param  mainterFllowDay 维护人跟进超期天数
	 * @param  transactionTypeId 交易类型Id
	 * @param  statusId 状态Id（有效、成交等）
	 * @param  discStatusId 盘状态Id（公盘、私盘）
	 * @param  levelType  房源等级 
	 * @return String 返回类型
	 */
	public List<Map<String,Object>> getCustomerMainterLookTimeOutInfo(@Param("lastShowedDay")int lastShowedDay,@Param("transactionTypeId") String transactionTypeId,@Param("statusId") String statusId,@Param("taboBaoStatusId") String taboBaoStatusId,@Param("discStatusId") String discStatusId,
			@Param("levelType") String levelType,@Param("companyId") String companyId);
	
	
	/**
	 * 
		 * 查询城市置顶过期的房源编号
		 * @Description: 查询城市置顶过期的房源编号
		 * @param maxDay 置顶最长时间
		 * @param cityCode 城市代码
		 * @return 房源编号
		 * List<String>
	 */
	public List<Map<String, Object>> queryTopHouseIdByCityCode(@Param("maxDay")int maxDay,@Param("cityCode") String cityCode);
	/**
	 * 
		 * 取消城市置顶过期的房源
		 * @Description: 取消城市置顶过期的房源
		 * @param maxDay 置顶最长时间
		 * @param cityCode 城市代码
		 * @return 房源编号
		 * List<String>
	 */
	public Integer updateIsTop(@Param("maxDay")int maxDay,@Param("cityCode") String cityCode);
	/**
	 * 
		 * 查询自动撤单房源编号
		 * @Description: 查询自动撤单房源编号
		 * @param maxDay 通知撤单等待最长时间
		 * @param cityCode 城市代码
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> querycancelNoticeHouseByCityCode(@Param("maxDay")int maxDay,@Param("cityCode") String cityCode);
	/**
	 * 
		 * 房源自动撤单
		 * @Description: 房源自动撤单
		 * @param maxDay 通知撤单等待最长时间
		 * @param cityCode 城市代码
		 * @return 
		 * Integer
	 */
	public Integer updateHouseIsCancelNotice(@Param("maxDay")int maxDay,@Param("cityCode") String cityCode);
	/**
	 * 
		 * 查询自动撤单客源编号
		 * @Description: 查询自动撤单客源编号
		 * @param maxDay 通知撤单等待最长时间
		 * @param cityCode 城市代码
		 * @param transactionTypeId 交易类型:4、求购,5、求租
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> querycancelNoticeCustomerByCityCode(@Param("maxDay")int maxDay,@Param("cityCode") String cityCode,@Param("transactionTypeId")int transactionTypeId);
	/**
	 * 
		 * 客源自动撤单
		 * @Description: 客源自动撤单
		 * @param maxDay 通知撤单等待最长时间
		 * @param cityCode 城市代码
		 * @param transactionTypeId 交易类型:4、求购,5、求租
		 * @return 
		 * Integer
	 */
	public Integer updateCustomerIsCancelNotice(@Param("maxDay")int maxDay,@Param("cityCode") String cityCode,@Param("transactionTypeId")int transactionTypeId);
	/**
	 * 
		 * 清除今日浏览记录
		 * @Description: 清除今日浏览记录
		 * @return 
		 * Integer
	 */
	public Integer deleteAllTodaySee();
	/**
	 * 
		 * 更新房源最近15天带看数量
		 * @Description: 更新房源最近15天带看数量
		 * @return 
		 * Integer
	 */
	public Integer updateViewingCount();
	/**
	 * 
		 * 更新二手房源最近15天带看数量
		 * @Description: 更新二手房源最近15天带看数量
		 * @return 
		 * Integer
	 */
	public Integer updateSellViewingCount();
	/**
	 * 
		 * 更新租房房源最近15天带看数量
		 * @Description: 更新租房房源最近15天带看数量
		 * @return 
		 * Integer
	 */
	public Integer updateRentViewingCount();
	/**
	 * 
		 * 更新新房最近15天带看数量
		 * @Description: 更新新房最近15天带看数量
		 * @return 
		 * Integer
	 */
	public Integer updateProjectViewingCount();
	/**
	 * 
		 * 更新新房查询表最近15天带看数量
		 * @Description: 更新新房查询表最近15天带看数量
		 * @return 
		 * Integer
	 */
	public Integer updateProjectQueryViewingCount();
	/**
	 * 
		 * 自动关闭过期的公司开通城市
		 * @Description: 自动关闭过期的公司开通城市
		 * @return 
		 * Integer
	 */
	public Integer updateCompanyOpenCityStatus();
}
