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
public class LogNewHouseController {
	@Autowired
	BusinessLogService businessLogService;
	
	@RequestMapping(value="newHouseAddLog")
	@ResponseBody
	public BashijuResult addOperateLog(String message) {
		if (StringUtils.isNotEmpty(message)) {
			BusinessLogModel param=JSON.parseObject(message, BusinessLogModel.class);
			businessLogService.newHouseLogAdd(param);
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
		}	
	}
	@RequestMapping(value="newHouseUpdateLog")
	@ResponseBody
	public BashijuResult updateOperateLog(String message) {
		if (StringUtils.isNotEmpty(message)) {
			BusinessLogModel businessLogModel=JSON.parseObject(message, BusinessLogModel.class);
			businessLogService.newHouseLogUpdate(businessLogModel);
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
		}
		
	}
	
	@RequestMapping(value="newHouseDelLog")
	@ResponseBody
	public BashijuResult delOperateLog(String message) {
		if (StringUtils.isNotEmpty(message)) {
			BusinessLogModel businessLogModel=JSON.parseObject(message, BusinessLogModel.class);
			businessLogService.newHouseLogDel(businessLogModel);
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
		}
		
	}
	
	@RequestMapping(value="newHouseQueryLog")
	@ResponseBody
	public BashijuResult queryOperateLog(String message) {
		if (StringUtils.isNotEmpty(message)) {
			BusinessLogModel businessLogModel=JSON.parseObject(message, BusinessLogModel.class);
			businessLogService.newHouseLogQuery(businessLogModel);
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
		}
		
	}
}
