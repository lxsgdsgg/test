/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ICustomerStarCommentsService.java   
 * @Package com.bashiju.www.service.user      
 * @author: zuoyuntao     
 * @date:   2018年7月26日 下午4:11:13   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.service.user;

import java.util.Map;

import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.out.usercentral.CustomerAgentStarCommentsEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerSeeHouseCommentsEntity;

/**
 * 客户评价接口
 * @ClassName:ICustomerStarCommentsService
 * @Description:客户评价接口
 * @author:zuoyuntao
 * @date:2018年7月26日 下午4:11:13
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface ICustomerStarCommentsService {
	/**
	 * 获取我的评价
	 * @Title: queryMyComments
	 * @author: zuoyuntao  
	 * @Description:获取我的评价
	 * @param custId 【custId必填】
	 * @param page 每页显示最少条数
	 * @param limit 每页显示最大条数
	 * void JSON 格式为：
	 */
	public WebPage<CustomerAgentStarCommentsEntity> queryCustomerComments(
			String custId,int page,int limit);
	/**
	 * 保存客户评价
	 * @Title: saveCustomerComments
	 * @author: zuoyuntao  
	 * @Description:保存客户评价
	 * @param commentEntity      
	 * void
	 */
	public void saveCustomerComments(CustomerAgentStarCommentsEntity commentEntity);
	/**
	 * 保存房源评价信息
	 * @Title: saveSeeHouseRecordComments
	 * @author: zuoyuntao  
	 * @Description:保存房源评价信息
	 * @param visterEvaluate 评价内容
	 * @param id 看房记录ID
	 * void 
	 */
	public void saveCustomerHouseComments(String visterEvaluate,String id);
	/**
	 * 查询客户对房源的评价
	 * @Title: queryCustomerHouseComments
	 * @author: zuoyuntao  
	 * @Description:查询客户对房源的评价
	 * @param custId 客户Id
	 * @param page 每页显示最小条数
	 * @param limit 每页显示最大条数
	 * @return      
	 * Page<CustomerHouseStarCommentsEntity>
	 */
	public WebPage<CustomerSeeHouseCommentsEntity> queryCustomerHouseComments(
			String custId,int page,int limit);
	/**
	 * 读取经纪人评价时的经纪人标签配置信息
	 * @Title: queryAgentCommentLabels
	 * @author: zuoyuntao  
	 * @Description:读取经纪人评价时的经纪人标签配置信息
	 * @return      
	 * Map<String,Object> map({hotLabels=List<LabelResult>},serviceCommentLabels=List<LabelResult>)
	 */
	public Map<String,Object> queryAgentCommentLabels();
}
