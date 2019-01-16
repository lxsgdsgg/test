/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerOwnerHouseServiceImpl.java   
 * @Package com.bashiju.www.service.user.impl      
 * @author: zuoyuntao     
 * @date:   2018年7月26日 下午4:13:32   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.service.user.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.api.RedisGetIdServiceApi;
import com.bashiju.webservice.mapper.CustomerOwnerHouseMapper;
import com.bashiju.wutils.exception.BusinessException;
import com.bashiju.wutils.redis.ICacheService;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.out.usercentral.CustomerHouseDetailEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerOwnerHouseEntity;
import com.bashiju.www.service.user.ICustomerOwnerHouseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;

/**
 * 个人中心--我的房子服务
 * @ClassName:CustomerOwnerHouseServiceImpl
 * @Description:个人中心--我的房子服务
 * @author:zuoyuntao
 * @date:2018年7月26日 下午4:13:32
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class CustomerOwnerHouseServiceImpl implements ICustomerOwnerHouseService{
	/**
	 * Redis数据读取服务
	 */
	@Autowired
	private ICacheService mCacheService;
	/**
	 * 我的房子持久层接口
	 */
	@Autowired
	private CustomerOwnerHouseMapper mCustomerOwnerHouseMapper;
	/**
	 * Redis获取各种码的服务接口
	 */
	@Autowired
	private RedisGetIdServiceApi mRedisGetIdServiceApi;
//	/**
//	 * 对象映射
//	 */
//	private static ObjectMapper MAPPER = new ObjectMapper();
	/**
	 * <p>Title: queryCustOwnerHouseList</p>   
	 * <p>Description: 查询客户个人发布的所有房源 </p>   
	 * @param custId 客户ID
	 * @param page 每页显示最小条数
	 * @param limit 每页显示最大条数
	 * @see com.bashiju.www.service.user.ICustomerOwnerHouseService#queryCustOwnerHouseList(java.lang.String)
	 */
	@Override
	public WebPage<CustomerOwnerHouseEntity> queryCustOwnerHouseList(
			String custId,int page,int limit) {
		WebPage<CustomerOwnerHouseEntity> pageList = null;
		PageHelper.startPage(page,limit);
		Page<CustomerOwnerHouseEntity> pageObj = mCustomerOwnerHouseMapper.queryMyOwnerHouseDataList(custId);
		pageList = new WebPage<CustomerOwnerHouseEntity>(pageObj);
		return pageList;
	}
	/**
	 * <p>Title: bindingSourcesByBindCode</p>   
	 * <p>Description:根据绑定码绑定房源和客户 </p>   
	 * @param custId 客户ID
	 * @param bindingCode 绑定码  
	 * @param type 绑定类型 （0 房源 1 客源）
	 * @see com.bashiju.www.service.user.ICustomerOwnerHouseService#bindingHouseByBindCode(java.lang.String, java.lang.String)
	 */
	@Override
	public void bindingSourcesByBindCode(String custId, String bindingCode,String type) throws Exception {
		String sourcesId = readDataValueByBindingCodeFromRedis(bindingCode);
		if(null == sourcesId) {
			throw new BusinessException("绑定码失效！请联系经纪人");
		}
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("custId", custId);
		paraMap.put("type", type);
		paraMap.put("sourcesId", sourcesId);
		paraMap.put("addTime", new Date());
		paraMap.put("isValid", "1");
		mCustomerOwnerHouseMapper.saveBindingHouseResult(paraMap);
		//删除Redis绑定码
		mCacheService.delCache(bindingCode);
	}
	
//	/**
//	 * 根据Key从Redis服务器读取数据结果集 
//	 * @Title: readCustOwnerHouseFromRedisByKey
//	 * @author: zuoyuntao  
//	 * @Description:根据Key从Redis服务器读取数据结果集
//	 * @param key 
//	 * @return      
//	 * WebPage<CustomerOwnerHouseEntity>
//	 */
//	@SuppressWarnings({ "unchecked" })
//	private WebPage<CustomerOwnerHouseEntity> readCustOwnerHouseFromRedisByKey(String key){
//		WebPage<CustomerOwnerHouseEntity> retPage = new WebPage<CustomerOwnerHouseEntity>();
//		String retObj = mCacheService.getCache(key);
//		if(null != retObj) {
//			try {
//				retPage = MAPPER.readValue(retObj, WebPage.class);
//			} catch (JsonParseException e) {
//				e.printStackTrace();
//			} catch (JsonMappingException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return retPage;
//	}
	
	/**
	 * 根据key从Redis服务器读取数据
	 * @Title: readDataFromRedis
	 * @author: zuoyuntao  
	 * @Description:根据key从Redis服务器读取数据
	 * @param key Redis数据存储的Key
	 * @return      
	 * String 房源ID
	 */
	private String readDataValueByBindingCodeFromRedis(String key) {
		String retStr = mCacheService.getCache(key);
		if(StringUtil.isEmpty(retStr)) {
			return null;
		}
		return retStr;
	}
	/**
	 * 根据手机号码获取验证码 
	 * @Title: sendHouseVerifyCode
	 * @author: zuoyuntao  
	 * @Description:根据手机号码获取验证码  
	 * @param mobile 手机号码
	 * @return      
	 * String
	 */
	@Override
	public void sendHouseVerifyCode(String mobile) {
		String verifyCode = mRedisGetIdServiceApi.getMsgAuthCode(mobile);
		if(null == verifyCode) {
			throw new BusinessException("请勿频繁操作");
		}
	}
	
	/**
	 * 根据房源ID查询房源详细信息
	 * @Title: queryMyHouseDetailByHouseID
	 * @author: zuoyuntao  
	 * @Description:根据房源ID查询房源详细信息
	 * @param houseId 房源ID
	 * @return      
	 * CustomerHouseDetailEntity
	 */
	@Override
	public CustomerHouseDetailEntity queryMyHouseDetailByHouseID(String houseId) {
		return mCustomerOwnerHouseMapper.queryMyHouseDetailByHouseID(houseId);
	}
	
	/**
	 * 根据绑定ID删除我的房源
	 * @Title: deleteMyHouseByBindId
	 * @author: zuoyuntao  
	 * @Description:根据绑定ID删除我的房源
	 * @param id 房源绑定ID
	 * void 
	 */
	@Override
	public void deleteMyHouseByBindId(String id) {
		mCustomerOwnerHouseMapper.deleteMyHouseByBindId(id);
	}
	
	/**
	 * 取消绑定我的房源
	 * @Title: cancelBindMyHouseByBindId
	 * @author: zuoyuntao  
	 * @Description:取消绑定我的房源
	 * @param id      
	 * void 
	 */
	@Override
	public void cancelBindMyHouseByBindId(String id) {
		mCustomerOwnerHouseMapper.cancelBindMyHouseByBindId(id);
	}
	
	/**
	 * 重新委托【我的房源】（房源信息无变动）
	 * @Title: againEntrustMyHouseById
	 * @author: zuoyuntao  
	 * @Description:重新委托【我的房源】（房源信息无变动）
	 * @param houseId 房源ID
	 * @param custId 用户Id
	 * void 
	 */
	@Override
	public void againEntrustMyHouseById(String houseId,String custId) {
		mCustomerOwnerHouseMapper.againEntrustMyHouseById(houseId, custId);
	}
	
	/**
	 * 根据绑定ID查询房源详细信息
	 * @Title: queryHoueseDetailById
	 * @author: zuoyuntao  
	 * @Description:根据绑定ID查询房源详细信息
	 * @param id 房源绑定ID
	 * @param custId 客户id
	 * CustomerOwnerHouseEntity
	 */
	@Override
	public CustomerOwnerHouseEntity queryHoueseDetailById(String id,String custId) {
		return mCustomerOwnerHouseMapper.queryHoueseDetailById(id, custId);
	}
}
