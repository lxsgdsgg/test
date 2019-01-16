/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  BusinessExamineServiceImpl.java   
 * @Package com.bashiju.housing.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年6月5日 下午5:36:43   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.customer.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.customer.mapper.BusinessExamineMapper;
import com.bashiju.customer.pojo.BusinessExamineTypeEnum;
import com.bashiju.customer.service.BusinessExamineService;
import com.bashiju.customer.service.CustomerService;
import com.bashiju.enums.ExamineStatusEnum;
import com.bashiju.enums.MenuEnum;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**   
 * @ClassName:  BusinessExamineServiceImpl   
 * @Description:客源业务审核服务   
 * @author: liwen
 * @date:   2018年7月2日 下午5:36:43   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
@SystemServiceLog(sourceType="客源业务审核")
public class BusinessExamineServiceImpl extends CommonSqlServie implements BusinessExamineService {

	@Autowired
	private BusinessExamineMapper businessExamineMapper;
	
	@Autowired
	private DataAuthHelper dataAuthHelper;
	
	@Autowired
	private CustomerService customerService;
	/**
	 * 
	 * @Description: 条件查询客源业务审核信息  
	 * @param params 客源参数
	 * @return: List<Map<String,Object>>   
	 * @see com.bashiju.housing.service.BusinessExamineService#queryBusinessExamine(java.util.Map)   
	 */
	@Override
	public List<Map<String, Object>> queryBusinessExamine(String conditions) {
		dataAuthHelper.auth(MenuEnum.MENU_103.getCode(),UserThreadLocal.get().get("id").toString());
		Map<String,Object> params = JSON.parseObject(conditions);
		return businessExamineMapper.queryBusinessExamine(params);
	}
	
	/**
	 * 
	 * @Description: 根据id获取房源业务审核信息  
	 * @param id 待审核的信息id
	 * @return: Map<String,Object>
	 * @see com.bashiju.housing.service.BusinessExamineService#getBusinessExamine(java.lang.String)   
	 */
	@Override
	public Map<String, Object> queryBusinessExamineById(Long id) {
		dataAuthHelper.auth(MenuEnum.MENU_103.getCode(),UserThreadLocal.get().get("id").toString());
		return businessExamineMapper.queryBusinessExamineById(id);
	}

	/**
	 * 
	 * @Description: 条件查询客源源业务审核信息(分页)  
	 * @param params 客源参数
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: Page<Map<String,Object>>
	 * @see com.bashiju.housing.service.BusinessExamineService#queryBusinessExamine(java.util.Map, int, int)   
	 */
	@Override
	public Page<Map<String, Object>> queryBusinessExamine(String conditions, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<Map<String,Object>> pages = (Page<Map<String, Object>>) this.queryBusinessExamine(conditions);
		return pages;
	}

	/**
	 * 
	 * @Description: 发起审核请求
	 * @param map 待保存的审核信息
	 * @return: Long
	 * @see com.bashiju.housing.service.BusinessExamineService#saveOrUpdateBusinessExamine(java.util.Map)   
	 */
	@Override
	@SystemServiceLog(operationType="发起客源审核请求")
	public Long saveBusinessExamine(Map<String,Object> map) {
		if(map==null)
			throw new BusinessException("待审核的信息不允许为空");
		if(!BusinessExamineTypeEnum.enumMap.containsKey(map.get("examineTypeId")))
			throw new BusinessException("审核类型不正确");
		Long id = null;
		map.remove("id");
		map.put("status", ExamineStatusEnum.UNEXAMINED.getCode());//默认为未审核
		id = this.commonOperationDatabase(map, "demand_businessexamine", false);
		if(id!=null && id>0)
			ExecutionResult.descFormat(Long.toString(id), "新增客源业务审核");
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_ADD_ERROR);
		return id;
	}

	/**
	 * 
	 * @Description: 审核业务申请   
	 * @param id 待审核的业务id
	 * @param status 审核状态结果，1--已审核，2--已驳回
	 * @param rejectReason 驳回原因(只针对"02--已驳回"的审核状态结果有效)
	 * @return: boolean
	 * @see com.bashiju.housing.service.BusinessExamineService#examineApplication(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)   
	 */
	@Override
	public boolean examineApplication(Long id, String status,String rejectReason) {
		
		Map<String,Object> businessExamine = this.queryBusinessExamineById(id);
		if(businessExamine==null)
			throw new BusinessException("未找到要审核的信息["+id+"]");
		String examineType = (String)businessExamine.get("examineTypeId");
		String remark = (String)businessExamine.get("remark");
		String demandId = businessExamine.get("demandId").toString();
		if(StringUtils.isEmpty(examineType))
			throw new BusinessException("该申请信息的审核类型为空");
		Long applyerId = null;
		if(BusinessExamineTypeEnum.TAOBAO.getCode().equals(examineType))//当审核类型为淘宝时，需要将申请人返回
			applyerId = Long.parseLong(businessExamine.get("applyerId").toString());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("auditor", UserThreadLocal.get().get("id"));
		map.put("auditTime", new Date());
		map.put("status", status);
		if(ExamineStatusEnum.REJECT.getCode().equals(status))
			map.put("rejectReason", rejectReason);
		Long result = this.commonOperationDatabase(map, "demand_businessexamine", "id", false);
		if(result==null || result<=0)
			throw new BusinessException("审核操作异常");
		//调用房源的审核接口
		boolean backResult = customerService.doExamine(demandId, BusinessExamineTypeEnum.parseOf(examineType), 
				ExamineStatusEnum.parseOf(status), remark, applyerId);
		if(!backResult)
			throw new BusinessException("客源业务审核处理失败");
		ExecutionResult.descFormat(id+"", "审核业务申请");
		return true;
	}

}
