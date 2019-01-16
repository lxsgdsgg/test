/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerSeeHouseRecordPageServiceApiImpl.java   
 * @Package com.bashiju.www.service.api.impl.usercentral      
 * @author: zuoyuntao     
 * @date:   2018年8月15日 下午2:26:53   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.service.api.impl.usercentral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.wapi.usercentral.ICustomerSeeHouseRecordPageServiceApi;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.out.usercentral.CustomerSeeHouseRecordEntity;
import com.bashiju.www.service.user.ICustomerSeeHouseRecordService;

/**
 * 个人中心----看房记录服务
 * @ClassName:CustomerSeeHouseRecordPageServiceApiImpl
 * @Description:个人中心----看房记录服务
 * @author:zuoyuntao
 * @date:2018年8月15日 下午2:26:53
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class CustomerSeeHouseRecordPageServiceApiImpl
			extends CustomerPersonalCentralBaseServiceApi implements ICustomerSeeHouseRecordPageServiceApi{
	/**
	 * 看房记录服务接口
	 */
	@Autowired
	private ICustomerSeeHouseRecordService mICustomerSeeHouseRecordService;
	/**
	 * <p>Title: querySeeHouseRecordPageList</p>   
	 * <p>Description: 查询我的看房记录 </p>   
	 * @param key 用户登陆标记
	 * @param page 当前页
	 * @param limit 最大页
	 * @return   
	 * @see com.bashiju.wapi.usercentral.ICustomerSeeHouseRecordPageServiceApi#querySeeHouseRecordPageList(java.lang.String, int, int)
	 */
	@Override
	public WebPage<CustomerSeeHouseRecordEntity> querySeeHouseRecordPageList(String key,int page,int limit) {
		String custId = getUserIdByRedisKey(key);
		return mICustomerSeeHouseRecordService.queryAllCustomerSeeHouseRecord(custId, page, limit);
	}
	/**
	 * <p>Title: saveSeeHouseRecordComments</p>   
	 * <p>Description:保存房源评价信息 </p>   
	 * @param visterEvaluate 评价内容
	 * @param id 看房记录ID
	 * @see com.bashiju.www.service.user.ICustomerSeeHouseRecordService#saveSeeHouseRecordComments(com.bashiju.www.pojo.service.out.usercentral.CustomerSeeHouseCommentsEntity, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String)
	 */
	@Override
	public void saveSeeHouseRecordComments(String visterEvaluate,String id) {
		mICustomerSeeHouseRecordService.saveSeeHouseRecordComments(visterEvaluate,id);
	}
}
