/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ICustomerHouseEntrustPageServiceApi.java   
 * @Package com.bashiju.wapi.usercentral      
 * @author: zuoyuntao     
 * @date:   2018年8月15日 下午2:37:17   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.wapi.usercentral;

import java.util.List;
import java.util.Map;

import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.out.comm.SelectItemResult;
import com.bashiju.www.pojo.service.out.usercentral.CustomerEntrustParamEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerHouseEntrustmentEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerUserCentralBuildingEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerUserCentralBuildingHouseEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerUserCentralCommunityEntity;

/**
 * 个人中心----房源委托页面服务接口
 * @ClassName:ICustomerHouseEntrustPageServiceApi
 * @Description:个人中心----房源委托页面服务接口
 * @author:zuoyuntao
 * @date:2018年8月15日 下午2:37:17
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface ICustomerHouseEntrustPageServiceApi {
	/**
	 * 查询房屋委托数据列表
	 * @Title: queryHouseEntrustPageList
	 * @author: zuoyuntao  
	 * @param key 客户登陆标记
	 * @param page 每页显示最少条数（不分页传入 0）
	 * @param limit 每页显示最大条数（不分页传入 0）      
	 * void JSON 格式为：
	 */
	public WebPage<CustomerHouseEntrustmentEntity> queryHouseEntrustPageList(
			String key, int page, int limit);
	
	/**
	 * 保存客户房源委托数据信息
	 * @Title: saveHouseEntrustmentData
	 * @author: zuoyuntao  
	 * @Description:保存客户房源委托数据信息
	 * @param houseEntity 房源委托实体类     
	 * @param verifyCode 手机验证码
	 * void
	 */
	public void saveHouseEntrustmentData(CustomerHouseEntrustmentEntity houseEntity
			,String verifyCode);
	/**
	 * 根据手机号码获取验证码 
	 * @Title: sendHouseVerifyCode
	 * @author: zuoyuntao  
	 * @Description:根据手机号码获取验证码  
	 * @param mobile 手机号码
	 * @return      
	 * String
	 */
	public void sendHouseVerifyCode(String mobile);
	/**
	 * 获取付款方式下拉
	 * @Title: queryPayOffMethod
	 * @author: zuoyuntao  
	 * @Description:获取付款方式下拉
	 * @return      
	 * Map<String,List<SelectItemResult>>
	 */
	public Map<String,List<SelectItemResult>> queryPayOffMethod();

	/**
	 * 房源撤销委托
	 * @Title: concelEntrust
	 * @author: zuoyuntao  
	 * @Description:房源撤销委托
	 * @param handleFlag 房源委托ID   
	 * void
	 */
	public void cancelEntrust(String entrustId);
	
	/**
	 * 获取小区数据信息
	 * @Title: queryCommunityList
	 * @author: zuoyuntao  
	 * @Description:获取小区数据信息
	 * @param rgCode 城市编码（城市行政区划编码，如：昆明（530100）
	 * @return      
	 * List<CustomerUserCentralCommunityEntity> 数据中包含【行政区、片区、小区】数据
	 */
	public List<CustomerUserCentralCommunityEntity> queryCommunityListApi(String rgCode);
	
	/**
	 * 根据小区ID获取座栋信息
	 * @Title: queryBuildingsData
	 * @author: zuoyuntao  
	 * @Description:根据小区ID获取座栋信息 
	 * @param communityId 小区ID
	 * @return      
	 * List<CustomerUserCentralBuildingEntity> 
	 */
	public List<CustomerUserCentralBuildingEntity> queryBuildingsDataApi(String communityId);
	/**
	 * 根据座栋ID查询单元、门牌号数据信息
	 * @Title: queryBuildingUnityAndHouse
	 * @author: zuoyuntao  
	 * @Description:根据座栋ID查询单元、门牌号数据信息
	 * @param buildingId 座栋ID
	 * @return      
	 * List<CustomerUserCentralBuildingHouseEntity>
	 */
	public List<CustomerUserCentralBuildingHouseEntity> queryBuildingUnityAndHouseApi(String buildingId);
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
	public CustomerHouseEntrustmentEntity queryEntrustDataByCustIdAndEntrustId(String key,String entrustId);
	/**
	 * 
	 * 房源重新委托
	 * @Title: updateCustomerEntrust
	 * @author: zuoyuntao  
	 * @Description:房源重新委托
	 * @param entrustEntity 
	 * @return      
	 * boolean 成功：true 失败：false
	 */
	public boolean updateCustomerEntrust(CustomerEntrustParamEntity entrustEntity);
	/**
	 * 根据房源Id撤销房源
	 * @Title: cancelOurHouseByHouseId
	 * @author: zuoyuntao  
	 * @Description:根据房源Id撤销房源
	 * @param houseId 房源ID
	 * @param key 客户登陆key    
	 * void
	 */
	public void cancelOurHouseByHouseId(String houseId,String key);
}
