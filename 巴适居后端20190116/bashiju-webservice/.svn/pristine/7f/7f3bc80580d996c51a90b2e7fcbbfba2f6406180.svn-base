/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerHouseEntrustPageServiceApiImpl.java   
 * @Package com.bashiju.www.service.api.impl.usercentral      
 * @author: zuoyuntao     
 * @date:   2018年8月15日 下午2:38:34   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.service.api.impl.usercentral;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.wapi.usercentral.ICustomerHouseEntrustPageServiceApi;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.out.comm.SelectItemResult;
import com.bashiju.www.pojo.service.out.usercentral.CustomerEntrustParamEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerHouseEntrustmentEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerUserCentralBuildingEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerUserCentralBuildingHouseEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerUserCentralCommunityEntity;
import com.bashiju.www.service.user.ICustomerEntrustmentService;

/**
 * 个人中心-----房源委托页面服务
 * @ClassName:CustomerHouseEntrustPageServiceApiImpl
 * @Description:个人中心-----房源委托页面服务
 * @author:zuoyuntao
 * @date:2018年8月15日 下午2:38:34
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class CustomerHouseEntrustPageServiceApiImpl
		extends CustomerPersonalCentralBaseServiceApi implements ICustomerHouseEntrustPageServiceApi {
	/**
	 * 房源委托服务接口
	 */
	@Autowired
	private ICustomerEntrustmentService mICustomerEntrustmentService;
	/**   
	 * <p>Title: queryHouseEntrustPageList</p>   
	 * <p>Description: </p>   
	 * @param key 客户登陆标记
	 * @param page 每页显示最少条数（不分页传入 0）
	 * @param limit 每页显示最大条数（不分页传入 0） 
	 * @see com.bashiju.wapi.usercentral.ICustomerHouseEntrustPageServiceApi#queryHouseEntrustPageList(java.lang.String, int, int)   
	 */
	@Override
	public WebPage<CustomerHouseEntrustmentEntity> queryHouseEntrustPageList(
			String key, int page, int limit) {
		String custId = this.getUserIdByRedisKey(key);
		return mICustomerEntrustmentService.queryHouseEntrustmentDataList(custId, page, limit);
	}
	/**
	 * <p>Title: saveHouseEntrustmentData</p>   
	 * <p>Description:保存房源委托信息 </p>   
	 * @param houseEntity 房源委托实体对象
	 * @param verifyCode 短信验证码  
	 * @see com.bashiju.wapi.usercentral.ICustomerHouseEntrustPageServiceApi#saveHouseEntrustmentData(com.bashiju.www.pojo.service.out.usercentral.CustomerHouseEntrustmentEntity, java.lang.String)
	 */
	@Override
	public void saveHouseEntrustmentData(CustomerHouseEntrustmentEntity houseEntity, String verifyCode) {
		String custId = this.getUserIdByRedisKey(houseEntity.getLoginKey());
		houseEntity.setCustId(custId);
		mICustomerEntrustmentService.saveHouseEntrustmentData(houseEntity, verifyCode);
		
	}
	/**
	 * <p>Title: sendHouseVerifyCode</p>   
	 * <p>Description: 发送手机验证码 </p>   
	 * @param mobile   
	 * @see com.bashiju.wapi.usercentral.ICustomerHouseEntrustPageServiceApi#queryHouseVerifyCode(java.lang.String)
	 */
	@Override
	public void sendHouseVerifyCode(String mobile) {
		mICustomerEntrustmentService.queryHouseVerifyCode(mobile);
		
	}
	/**
	 * <p>Title: queryPayOffMethod</p>   
	 * <p>Description: 获取付款方式 </p>   
	 * @return   
	 * @see com.bashiju.wapi.usercentral.ICustomerHouseEntrustPageServiceApi#queryPayOffMethod()
	 */
	@Override
	public Map<String, List<SelectItemResult>> queryPayOffMethod() {
		return mICustomerEntrustmentService.queryPayOffMethod();
	}
	/**
	 * <p>Title: cancelEntrust</p>   
	 * <p>Description: 取消房源委托</p>   
	 * @param entrustId 房源委托ID
	 * @see com.bashiju.wapi.usercentral.ICustomerHouseEntrustPageServiceApi#cancelEntrust(com.bashiju.www.pojo.service.out.usercentral.CustomerHouseEntrustmentEntity)
	 */
	@Override
	public void cancelEntrust(String entrustId) {
		mICustomerEntrustmentService.concelEntrust(entrustId);
	}
	
	/**
	 * 获取小区数据信息
	 * @Title: queryCommunityList
	 * @author: zuoyuntao  
	 * @Description:获取小区数据信息
	 * @param rgCode 城市编码（城市行政区划编码，如：昆明（530100）
	 * @return      
	 * List<CustomerUserCentralCommunityEntity> 数据中包含【行政区、片区、小区】数据
	 */
	@Override
	public List<CustomerUserCentralCommunityEntity> queryCommunityListApi(String rgCode){
		return mICustomerEntrustmentService.queryCommunityList(rgCode);
	}
	
	/**
	 * 根据小区ID获取座栋信息
	 * @Title: queryBuildingsData
	 * @author: zuoyuntao  
	 * @Description:根据小区ID获取座栋信息 
	 * @param communityId 小区ID
	 * @return      
	 * List<CustomerUserCentralBuildingEntity> 
	 */
	@Override
	public List<CustomerUserCentralBuildingEntity> queryBuildingsDataApi(String communityId){
		return mICustomerEntrustmentService.queryBuildingsData(communityId);
	}
	
	/**
	 * 根据座栋ID查询单元、门牌号数据信息
	 * @Title: queryBuildingUnityAndHouse
	 * @author: zuoyuntao  
	 * @Description:根据座栋ID查询单元、门牌号数据信息
	 * @param buildingId 座栋ID
	 * @return      
	 * List<CustomerUserCentralBuildingHouseEntity>
	 */
	@Override
	public List<CustomerUserCentralBuildingHouseEntity> queryBuildingUnityAndHouseApi(String buildingId){
		return mICustomerEntrustmentService.queryBuildingUnityAndHouse(buildingId);
	}
	
	/**
	 * 根据客户登陆key和委托ID查委托
	 * @Title: queryEntrustDataByCustIdAndEntrustId
	 * @author: zuoyuntao  
	 * @Description:根据客户登陆key和委托ID查委托
	 * @param key 用户登陆key
	 * @param entrustId 委托ID
	 * @return      
	 * CustomerHouseEntrustmentEntity
	 */
	@Override
	public CustomerHouseEntrustmentEntity queryEntrustDataByCustIdAndEntrustId(String key,String entrustId) {
		String custId = this.getUserIdByRedisKey(key);
		return mICustomerEntrustmentService.queryEntrustDataByCustIdAndEntrustId(custId,entrustId);
	}
	
	/**
	 * 
	 * 房源重新委托
	 * @Title: updateCustomerEntrust
	 * @author: zuoyuntao  
	 * @Description:房源重新委托
	 * @param entrustEntity
	 * @return    
	 * boolean  
	 */
	@Override
	public boolean updateCustomerEntrust(CustomerEntrustParamEntity entrustEntity) {
		String custId = getUserIdByRedisKey(entrustEntity.getLoginKey());
		entrustEntity.setCustId(custId);
		return mICustomerEntrustmentService.updateCustomerEntrust(entrustEntity);
	}
	
	/**
	 * 根据房源Id撤销房源
	 * @Title: cancelOurHouseByHouseId
	 * @author: zuoyuntao  
	 * @Description:根据房源Id撤销房源
	 * @param houseId 房源ID
	 * @param key 客户登陆key    
	 * void
	 */
	@Override
	public void cancelOurHouseByHouseId(String houseId,String key) {
		String custId = this.getUserIdByRedisKey(key);
		mICustomerEntrustmentService.cancelOurHouseById(houseId, custId);
	}

}
