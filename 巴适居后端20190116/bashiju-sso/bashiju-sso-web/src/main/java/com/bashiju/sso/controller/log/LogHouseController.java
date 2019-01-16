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
public class LogHouseController {
	@Autowired
	BusinessLogService businessLogService;
	

	@RequestMapping(value="houseAddLog")
	@ResponseBody
	public BashijuResult addOperateLog(String message) {
		if (StringUtils.isNotEmpty(message)) {
			BusinessLogModel param=JSON.parseObject(message, BusinessLogModel.class);
			businessLogService.houseLogAdd(param);
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
		}	
	}
	@RequestMapping(value="houseUpdateLog")
	@ResponseBody
	public BashijuResult updateOperateLog(String message) {
		if (StringUtils.isNotEmpty(message)) {
			BusinessLogModel businessLogModel=JSON.parseObject(message, BusinessLogModel.class);
			businessLogService.houseLogUpdate(businessLogModel);
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
		}
		
	}
	
	@RequestMapping(value="houseDelLog")
	@ResponseBody
	public BashijuResult delOperateLog(String message) {
		if (StringUtils.isNotEmpty(message)) {
			BusinessLogModel businessLogModel=JSON.parseObject(message, BusinessLogModel.class);
			businessLogService.houseLogDel(businessLogModel);
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
		}
		
	}
	
	@RequestMapping(value="houseQueryLog")
	@ResponseBody
	public BashijuResult queryOperateLog(String message) {
		if (StringUtils.isNotEmpty(message)) {
			BusinessLogModel businessLogModel=JSON.parseObject(message, BusinessLogModel.class);
			businessLogService.houseLogQuery(businessLogModel);
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
		}
		
	}
}
