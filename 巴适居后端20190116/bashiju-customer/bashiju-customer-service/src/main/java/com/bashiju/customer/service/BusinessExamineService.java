/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  BusinessExamineService.java   
 * @Package com.bashiju.housing.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年6月5日 下午5:27:13   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.customer.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**   
 * @ClassName:  BusinessExamineService   
 * @Description:客源业务审核服务接口   
 * @author: liwen
 * @date:   2018年7月2日 下午5:27:13   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface BusinessExamineService {

	/**
	 * 
	 * @Description: 条件查询客源业务审核信息  
	 * @param params 房源参数
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryBusinessExamine(String conditions);
	
	/**
	 * 
	 * @Description: 根据id获取客源业务审核信息  
	 * @param id 待审核的信息id
	 * @return: Map<String,Object>
	 */
	public Map<String,Object> queryBusinessExamineById(Long id);
	
	/**
	 * 
	 * @Description: 条件查询客源业务审核信息(分页)  
	 * @param params 房源参数
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryBusinessExamine(String conditions,int pageNum,int pageSize);
	
	/**
	 * 
	 * @Description: 发起审核请求   
	 * @param map 待保存的审核信息
	 * @return: Long
	 */
	public Long saveBusinessExamine(Map<String,Object> map);
	
	/**
	 * 
	 * @Description: 审核业务申请   
	 * @param id 待审核的业务id
	 * @param status 审核状态结果，1--已审核，2--已驳回
	 * @param transferee 申请人
	 * @param rejectReason 驳回原因(只针对"02--已驳回"的审核状态结果有效)
	 * @return: boolean
	 */
	public boolean examineApplication(Long id,String status,String rejectReason);
}
