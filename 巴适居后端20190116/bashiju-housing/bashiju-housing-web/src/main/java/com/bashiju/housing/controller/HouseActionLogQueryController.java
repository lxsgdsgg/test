/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  HouseActionLogQueryController.java   
 * @Package com.bashiju.housing.controller     
 * @author: zuoyuntao     
 * @date:   2018年11月30日 下午4:04:19   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.housing.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bashiju.enums.LogBusinessOperateTypeEnum;
import com.bashiju.enums.LogBusinessSourceTypeEnum;
import com.bashiju.housing.service.IHouseActionLogQueryService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.service.BaseController;
import com.github.pagehelper.Page;
import com.github.pagehelper.util.StringUtil;

/**
 * 房源日志查询控制器
 * @ClassName:HouseActionLogQueryController
 * @Description:房源日志查询控制器
 * @author:zuoyuntao
 * @date:2018年11月30日 下午4:04:19
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@RequestMapping(value="houseLogQuery")
@Controller
public class HouseActionLogQueryController extends BaseController{

	/**
	 * 房源操作日志查询服务接口
	 */
	@Autowired
	private IHouseActionLogQueryService mIHouseActionLogQueryService;
	
	/**
	 * 查询房源日志--带分页
	 * @Title: queryHouseActionLogPageList
	 * @author: zuoyuntao  
	 * @Description:查询房源日志--带分页
	 * @param page 当前页 
	 * @param limit 每页最大数
	 * @param paramMap 参数对象
	 * @return      
	 * Page<Map<String,Object>>
	 */
	@RequestMapping(value="queryHouseActionLogPageList")
	@ResponseBody
	public Object queryHouseActionLogPageList(int page,int limit,String jsonData) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if(StringUtil.isNotEmpty(jsonData)) {
			paramMap = JSONObject.parseObject(jsonData);
		}
		Page<Map<String,Object>> pageObj = 
				mIHouseActionLogQueryService.auxHouseActionLogPageList(page, limit,paramMap);
		return this.getPageResult(pageObj);
	}
	/**
	 * 查询电话日志--带分页
	 * @Title: auxTelephoneViewLogPageList
	 * @author: zuoyuntao  
	 * @Description:查询电话日志--带分页
	 * @param page 当前页 
	 * @param limit 每页最大数
	 * @param paramMap 参数对象
	 * @return      
	 * Page<Map<String,Object>>
	 */
	@RequestMapping(value="auxTelephoneViewLogPageList")
	@ResponseBody
	public Object auxTelephoneViewLogPageList(int page,int limit,String jsonData) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if(StringUtil.isNotEmpty(jsonData)) {
			paramMap = JSONObject.parseObject(jsonData);
		}
		Page<Map<String,Object>> pageObj = 
				mIHouseActionLogQueryService.auxTelephoneViewLogPageList(page, limit,paramMap);
		return this.getPageResult(pageObj);
	}
	/**
	 * 查询一手房源日志--带分页
	 * @Title: auxOneHandHouseActionLogPageList
	 * @author: zuoyuntao  
	 * @Description:查询一手房源日志--带分页
	 * @param page 当前页 
	 * @param limit 每页最大数
	 * @param paramMap 参数对象
	 * @return      
	 * Page<Map<String,Object>>
	 */
	@RequestMapping(value="auxOneHandHouseActionLogPageList")
	@ResponseBody
	public Object auxOneHandHouseActionLogPageList(int page,int limit,String jsonData) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if(StringUtil.isNotEmpty(jsonData)) {
			paramMap = JSONObject.parseObject(jsonData);
		}
		Page<Map<String,Object>> pageObj = 
				mIHouseActionLogQueryService.auxOneHandHouseActionLogPageList(page, limit,paramMap);
		return this.getPageResult(pageObj);
	}
	/**
	 * 根据房源编号查询房源操作日志 --带分页
	 * @Title: queryHouseActionLogByHouseId
	 * @author: zuoyuntao  
	 * @Description:根据房源编号查询房源操作日志
	 * @param page 当前页
	 * @param limit 每页最大条数
	 * @param houseId 房源编号
	 * @return      
	 * Page<Map<String,Object>>
	 */
	@RequestMapping(value="queryHouseActionLogByHouseId")
	@ResponseBody
	public Object queryHouseActionLogByHouseId(int page,int limit,String houseId) {
		if(StringUtil.isEmpty(houseId)) {
			throw new BusinessException("房源编号为空!");
		}
		return this.getPageResult(
				mIHouseActionLogQueryService.auxHouseActionLogByHouseId(page, limit, houseId));
	}
	/**
	 * 根据房源类型查询房源操作日志--带分页
	 * @Title: queryHouseActionLogByHouseType
	 * @author: zuoyuntao  
	 * @Description:根据房源编号查询房源操作日志
	 * @param page 当前页
	 * @param limit 每页最大条数
	 * @param houseType 房源类型
	 * @return      
	 * Page<Map<String,Object>>
	 */
	@RequestMapping(value="queryHouseActionLogByHouseType")
	@ResponseBody
	public Object queryHouseActionLogByHouseType(int page,int limit,String houseType) {
		if(StringUtil.isEmpty(houseType)) {
			throw new BusinessException("房源类型为空!");
		}
		return this.getPageResult(
				mIHouseActionLogQueryService.auxHouseActionLogByHouseType(page, limit, houseType));
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
		List<Map<String , String>> retList = LogBusinessOperateTypeEnum.queryEnumByType(type);
		return retList;
	}
	/**
	 * 根据类型获取枚举对象
	 * @Title: returnSourceTypeObj
	 * @author: zuoyuntao  
	 * @Description:根据类型获取枚举对象
	 * @param type 业务类型（参照LogBusinessOperateTypeEnum类中的值传递）
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
