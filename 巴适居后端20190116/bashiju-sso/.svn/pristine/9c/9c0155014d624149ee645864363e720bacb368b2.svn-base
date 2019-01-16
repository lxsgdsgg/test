package com.bashiju.sso.controller.log;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.pojo.BusinessLogModel;
import com.bashiju.utils.service.BusinessLogService;
import com.bashiju.utils.util.BashijuResult;

@RequestMapping(value="main")
@Controller
public class LogDealController {
	@Autowired
	BusinessLogService businessLogService;
	

	@RequestMapping(value="dealAddLog")
	@ResponseBody
	public BashijuResult addOperateLog(String message) {
		if (StringUtils.isNotEmpty(message)) {
			BusinessLogModel param=JSON.parseObject(message, BusinessLogModel.class);
			businessLogService.dealLogAdd(param);
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
		}	
	}
	
	@RequestMapping(value="dealUpdateLog")
	@ResponseBody
	public BashijuResult updateOperateLog(String message) {
		if (StringUtils.isNotEmpty(message)) {
			BusinessLogModel businessLogModel=JSON.parseObject(message, BusinessLogModel.class);
			businessLogService.dealLogUpdate(businessLogModel);
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
		}
		
	}
	
	@RequestMapping(value="dealDelLog")
	@ResponseBody
	public BashijuResult delOperateLog(String message) {
		if (StringUtils.isNotEmpty(message)) {
			BusinessLogModel businessLogModel=JSON.parseObject(message, BusinessLogModel.class);
			businessLogService.dealLogDel(businessLogModel);
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
		}
		
	}
	
	@RequestMapping(value="dealQueryLog")
	@ResponseBody
	public BashijuResult queryOperateLog(String message) {
		if (StringUtils.isNotEmpty(message)) {
			BusinessLogModel businessLogModel=JSON.parseObject(message, BusinessLogModel.class);
			businessLogService.dealLogQuery(businessLogModel);
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
		}
		
	}
}
