/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerActionLogQueryController.java   
 * @Package com.bashiju.deal.controller      
 * @author: zuoyuntao     
 * @date:   2018年11月30日 下午4:04:19   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.customer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bashiju.customer.service.ICustomerActionLogQueryService;
import com.bashiju.enums.LogBusinessOperateTypeEnum;
import com.bashiju.enums.LogBusinessSourceTypeEnum;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.util.SQLFilter;
import com.github.pagehelper.Page;
import com.github.pagehelper.util.StringUtil;

/**
 * 客源日志查询控制器
 * @ClassName:CustomerActionLogQueryController
 * @Description:客源日志查询控制器
 * @author:zuoyuntao
 * @date:2018年11月30日 下午4:04:19
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@RequestMapping(value="customerLogQuery")
@Controller
public class CustomerActionLogQueryController extends BaseController{

	/**
	 * 客源操作日志查询服务接口
	 */
	@Autowired
	private ICustomerActionLogQueryService mICustomerActionLogQueryService;
	
	/**
	 * 查询客源日志--带分页
	 * @Title: queryCustomerActionLogPageList
	 * @author: zuoyuntao  
	 * @Description:查询客源日志--带分页
	 * @param page 当前页 
	 * @param limit 每页最大数
	 * @param paramMap 参数对象
	 * @return      
	 * Page<Map<String,Object>>
	 */
	@RequestMapping(value="queryCustomerActionLogPageList")
	@ResponseBody
	public Object queryCustomerActionLogPageList(int page,int limit,String jsonData) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if(StringUtil.isNotEmpty(jsonData)) {
			paramMap = JSONObject.parseObject(jsonData);
		}
		Page<Map<String,Object>> pageObj = 
				mICustomerActionLogQueryService.auxCustomerActionLogPageList(page, limit,paramMap);
		return this.getPageResult(pageObj);
	}
	/**
	 * 根据客源编号查询客源操作日志 --带分页
	 * @Title: auxCustomerActionLogByDemandId
	 * @author: zuoyuntao  
	 * @Description:根据客源编号查询客源操作日志
	 * @param page 当前页
	 * @param limit 每页最大条数
	 * @param demandId 客源编号
	 * @return      
	 * Page<Map<String,Object>>
	 */
	@RequestMapping(value="auxCustomerActionLogByDemandId")
	@ResponseBody
	public Object auxCustomerActionLogByDemandId(int page,int limit,String demandId) {
		if(StringUtil.isEmpty(demandId)) {
			throw new BusinessException("客源编号为空!");
		}
		return this.getPageResult(
				mICustomerActionLogQueryService.auxCustomerActionLogByDemandId(page, limit, demandId));
	}
	/**
	 * 根据客源类型查询客源操作日志--带分页
	 * @Title: queryCustomerActionLogByCustomerType
	 * @author: zuoyuntao  
	 * @Description:根据客源编号查询客源操作日志
	 * @param page 当前页
	 * @param limit 每页最大条数
	 * @param demandType 客源类型
	 * @return      
	 * Page<Map<String,Object>>
	 */
	@RequestMapping(value="queryCustomerActionLogByCustomerType")
	@ResponseBody
	public Object queryCustomerActionLogByCustomerType(int page,int limit,String demandType) {
		if(StringUtil.isEmpty(demandType)) {
			throw new BusinessException("客源类型为空!");
		}
		return this.getPageResult(
				mICustomerActionLogQueryService.auxCustomerActionLogByCustomerType(page, limit, demandType));
	}
	/**
	 * 根据类型获取枚举对象
	 * @Title: returnOperatorTypeObj
	 * @author: zuoyuntao  
	 * @Description:根据类型获取枚举对象
	 * @param type 业务类型（参照LogBusinessOperateTypeEnum类中的值传递）
	 * @return      
	 * Object 
	 */
	@RequestMapping(value="returnOperatorTypeObj")
	@ResponseBody
	public Object returnOperatorTypeObj(String type) {
		SQLFilter.sqlInject(type);
		List<Map<String , String>> retList = LogBusinessOperateTypeEnum.queryEnumByType(type);
		return retList;
	}
	
	/**
	 * 根据类型获取枚举对象
	 * @Title: returnSourceTypeObj
	 * @author: zuoyuntao  
	 * @Description:根据类型获取枚举对象
	 * @param type 业务类型（参照LogBusinessSourceTypeEnum类中的值传递）
	 * @return      
	 * Object 
	 */
	@RequestMapping(value="returnSourceTypeObj")
	@ResponseBody
	public Object returnSourceTypeObj(String type) {
		List<Map<String , String>> retList = LogBusinessSourceTypeEnum.queryEnumByType(type);
		return retList;
	}
}
