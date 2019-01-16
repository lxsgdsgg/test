package com.bashiju.sso.controller.log;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.pojo.SystemLogModel;
import com.bashiju.utils.service.SystemLogService;
import com.bashiju.utils.util.BashijuResult;

@RequestMapping(value="main")
@Controller
public class LogSystemController {
	@Autowired
	SystemLogService systemLogService;

	@RequestMapping(value="systemAddLog")
	@ResponseBody
	public BashijuResult addOperateLog(String message) {
		if (StringUtils.isNotEmpty(message)) {
			SystemLogModel param=JSON.parseObject(message, SystemLogModel.class);
			systemLogService.systemPlatformLogAdd(param);
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
		}	
	}
	
	@RequestMapping(value="systemUpdateLog")
	@ResponseBody
	public BashijuResult updateOperateLog(String message) {
		if (StringUtils.isNotEmpty(message)) {
			SystemLogModel param=JSON.parseObject(message, SystemLogModel.class);
			systemLogService.systemPlatformLogUpdate(param);
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
		}
		
	}
	
	@RequestMapping(value="systemDelLog")
	@ResponseBody
	public BashijuResult delOperateLog(String message) {
		if (StringUtils.isNotEmpty(message)) {
			SystemLogModel param=JSON.parseObject(message, SystemLogModel.class);
			systemLogService.systemPlatformLogDel(param);
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
		}
		
	}
	
	@RequestMapping(value="systemQueryLog")
	@ResponseBody
	public BashijuResult queryOperateLog(String message) {
		if (StringUtils.isNotEmpty(message)) {
			SystemLogModel param=JSON.parseObject(message, SystemLogModel.class);
			systemLogService.systemPlatformLogQuery(param);
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
		}
		
	}
}
