package com.bashiju.sso.controller.systemmsg;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.pojo.IMMsgModel;
import com.bashiju.utils.util.BashijuResult;
@Controller
public class SystemMsgController {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	
	@RequestMapping(value="sendSysMsg")
	@ResponseBody
	public BashijuResult sendSysMsg(String message) {
		if (StringUtils.isNotEmpty(message)) {
			IMMsgModel imMsgModel=JSON.parseObject(message, IMMsgModel.class);
			rabbitTemplate.convertAndSend("IM.MSG.SEND", imMsgModel);		
			return BashijuResult.ok();
		}else {
			throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
		}	
	} 
}
