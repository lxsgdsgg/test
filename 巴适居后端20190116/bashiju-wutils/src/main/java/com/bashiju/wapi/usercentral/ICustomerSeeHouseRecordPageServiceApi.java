/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ICustomerSeeHouseRecordPageServiceApi.java   
 * @Package com.bashiju.wapi.usercentral      
 * @author: zuoyuntao     
 * @date:   2018年8月15日 下午2:25:29   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.wapi.usercentral;

import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.out.usercentral.CustomerSeeHouseRecordEntity;

/**
 * 个人中心----看房记录服务接口
 * @ClassName:ICustomerSeeHouseRecordPageServiceApi
 * @Description:个人中心----看房记录服务接口
 * @author:zuoyuntao
 * @date:2018年8月15日 下午2:25:29
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface ICustomerSeeHouseRecordPageServiceApi {
	/**
	 * 查询我的看房记录 
	 * @Title: querySeeHouseRecordPageList
	 * @author: zuoyuntao  
	 * @Description:查询我的看房记录
	 * @param key 用户登陆标记
	 * @param page 当前页
	 * @param limit 最大页
	 * @return      
	 * WebPage<CustomerSeeHouseRecordEntity> JSON 格式为：
	 */
	public WebPage<CustomerSeeHouseRecordEntity> querySeeHouseRecordPageList(String key,int page,int limit);
	/**
	 * 保存房源评价信息
	 * @Title: saveSeeHouseRecordComments
	 * @author: zuoyuntao  
	 * @Description:保存房源评价信息
	 * @param visterEvaluate 评价内容
	 * @param id 看房记录ID
	 * void 
	 */
	public void saveSeeHouseRecordComments(String visterEvaluate,String id);
	
}
