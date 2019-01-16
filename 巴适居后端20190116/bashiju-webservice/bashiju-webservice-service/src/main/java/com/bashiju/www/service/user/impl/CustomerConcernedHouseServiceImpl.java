/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerConcernedHouseServiceImpl.java   
 * @Package com.bashiju.www.service.user.impl      
 * @author: zuoyuntao     
 * @date:   2018年7月26日 下午4:35:50   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.webservice.mapper.CustomerConcernedHouseMapper;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.out.usercentral.CustomerConcernedHouseEntity;
import com.bashiju.www.service.comm.ICommService;
import com.bashiju.www.service.user.ICustomerConcernedHouseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 个人中心--用户房源关注服务
 * @ClassName:CustomerConcernedHouseServiceImpl
 * @Description:个人中心--用户房源关注服务
 * @author:zuoyuntao
 * @date:2018年7月26日 下午4:35:50
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class CustomerConcernedHouseServiceImpl implements ICustomerConcernedHouseService{
	/**
	 * 房源关注接口
	 */
	@Autowired
	private CustomerConcernedHouseMapper mCustomerConcernedHouseMapper;
	/**
	 * 公共的我的关注接口
	 */
	@Autowired
	private ICommService mICommService;
	/**
	 * <p>Title: cancelConcernedHouse</p>   
	 * <p>Description: 取消对房源、小区的关注</p>   
	 * @param custId 登陆用户ID
	 * @param sourceId 房源、小区或其他关注对象的ID
	 * @param houseType 房源类型（1：新房、2：二手房、3：租房、4：小区）
	 * @see com.bashiju.www.service.user.ICustomerConcernedHouseService#cancelConcernedHouse(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void cancelConcernedHouse(String custId, String sourceId, String houseType) {
		mICommService.cancelAttention(Integer.parseInt(custId)
				, sourceId,Integer.parseInt(houseType));
	}
	
	/**
	 * <p>Title: queryAllConcernedHouseList</p>   
	 * <p>Description: 查询用户关注的房源、小区</p>   
	 * @param custId 登陆用户ID
	 * @param page 当前页
	 * @param limit 最大页
	 * @param type 关注类型 （1：新房、2：二手房、3：租房、4：小区）
	 * @return Page<CustomerConcernedHouseEntity>
	 * @see com.bashiju.www.service.user.ICustomerConcernedHouseService#queryAllConcernedHouseList(java.lang.String, java.lang.String)
	 */
	@Override
	public WebPage<CustomerConcernedHouseEntity> queryAllConcernedHouseList(String custId,int page,int limit
			,String type) {
		Page<CustomerConcernedHouseEntity> retList = null;
//		if(houseType.equals(HouseTypeEnum.COMMUNIT.getCode())) {
//			PageHelper.startPage(page, limit);
//			retList = mCustomerConcernedHouseMapper.queryAllConcernedCommunityList(custId,houseType);
//		}else if( !houseType.equals(HouseTypeEnum.COMMUNIT.getCode())) {
		
//		if(type == null) {// 为nul时是个人信息查询【我的关注】
//			PageHelper.startPage(page, limit);
//			retList = mCustomerConcernedHouseMapper.queryAllConcernedNewHouseList(custId);
//			PageHelper.startPage(page, limit);
//			Page<CustomerConcernedHouseEntity> newList = mCustomerConcernedHouseMapper.queryAllConcernedHouseList(custId,type);
//			for(CustomerConcernedHouseEntity entity : newList) {
//				entity.setCommunityName(entity.getName());
//			}
//			retList.setTotal(retList.getTotal() + newList.getTotal() );
//			retList.addAll(newList);
//			return new WebPage<CustomerConcernedHouseEntity>(retList);
//		}
		if(null != type && type.equals("1")) {//新房
			PageHelper.startPage(page, limit);
			retList = mCustomerConcernedHouseMapper.queryAllConcernedNewHouseList(custId);
		}else {
			PageHelper.startPage(page, limit);
			retList = mCustomerConcernedHouseMapper.queryAllConcernedHouseList(custId,type);
		}
		return new WebPage<CustomerConcernedHouseEntity>(retList);
	}
	
	/**
	 * 统计我的关注信息
	 * @Title: countMyConcerned
	 * @author: zuoyuntao  
	 * @Description:统计我的关注信息
	 * @param custId
	 * @return      
	 * long 
	 */
	@Override
	public long countMyConcerned(String custId) {
		return mCustomerConcernedHouseMapper.countMyConcerned(custId);
	}
}
