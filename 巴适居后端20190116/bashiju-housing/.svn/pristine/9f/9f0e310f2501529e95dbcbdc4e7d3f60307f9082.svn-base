/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  HousePictureExamineController.java   
 * @Package com.bashiju.housing.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年6月6日 下午6:12:24   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.housing.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bashiju.enums.ExamineStatusEnum;
import com.bashiju.housing.pojo.HouseAttachTypeEnum;
import com.bashiju.housing.service.HousePictureExamineService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.pojo.IMMsgModel;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;

/**   
 * @ClassName:  HousePictureExamineController   
 * @Description:房源相关图片审核管理控制器   
 * @author: yangz
 * @date:   2018年6月6日 下午6:12:24   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="houseRelationPicture")
public class HouseRelationPictureExamineController extends BaseController {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	Logger logger=LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HousePictureExamineService housePictureExamineService;

	/**
	 * 
	 * @Description: 进入房源相关图片审核管理页面   
	 * @param request
	 * @param response
	 * @return      
	 * @return: ModelAndView
	 */
	@RequestMapping(value="houseRelationPicturePage")
	public ModelAndView housePictureExaminePage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = this.getModelAndView(request, response, "houseRelationPictureExamine/houseRelationPictureExamine");
		mv.addObject("examineStatus", ExamineStatusEnum.enumMap);
		mv.addObject("examineStatusJson", JSONObject.toJSON(ExamineStatusEnum.enumMap));
		return mv;
	}
	
	/**
	 * 
	 * @Description: 查询申请信息   
	 * @param request
	 * @param response
	 * @return      
	 * @return: Object
	 */
	@RequestMapping(value="getHousePictureExamineData")
	@ResponseBody
	public Object getHousePictureExamineData(HttpServletRequest request,HttpServletResponse response) {
		String status = request.getParameter("status");
		String operatorId = request.getParameter("operatorId");
		String applyerId = request.getParameter("applyerId");
		String auditorId = request.getParameter("auditorId");
		String beginAddTime = request.getParameter("beginAddTime");
		String endAddTime = request.getParameter("endAddTime");
		String houseId = request.getParameter("houseId");
		String rejectReason = request.getParameter("rejectReason");
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("status", status);
		params.put("operatorId", operatorId);
		params.put("applyerId", applyerId);
		params.put("auditorId", auditorId);
		params.put("beginAddTime", beginAddTime);
		params.put("endAddTime", endAddTime);
		params.put("houseId", houseId);
		params.put("rejectReason", rejectReason);
		params.put("attachType", HouseAttachTypeEnum.RELATIONPICTURE.getCode());//房源相关图片
		int pageNum = 1;
		int pageSize = 20;
		String num = request.getParameter("page");
		String size = request.getParameter("limit");
		if(!StringUtils.isEmpty(num))
			pageNum = Integer.parseInt(num);
		if(!StringUtils.isEmpty(size))
			pageSize = Integer.parseInt(size);
		Page<Map<String,Object>> page =this.housePictureExamineService.queryHousePictureExamines(params, pageNum, pageSize);
		Map<String,Object> map = getPageResult(page);
		return JSONObject.toJSON(map);
	}
	/**
	 * 
	 * @Description: 批量审核房源相关图片信息   
	 * @param request
	 * @param response
	 * @return      
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="examineApplication")
	@ResponseBody
	public BashijuResult examineApplication(HttpServletRequest request,HttpServletResponse response) {
		String dataStr = request.getParameter("dataStr");
		List<Map<String,Object>> list= (List<Map<String, Object>>) JSONArray.parse(dataStr);
		boolean result = this.housePictureExamineService.examineApplications(list);

		for(Map<String,Object> map : list) {
			logger.warn("--------------------"+map.toString());
			IMMsgModel imMsgModel=new IMMsgModel();
			imMsgModel.setReSessionId(String.valueOf(map.get("id")));
			imMsgModel.setReSessionUuid(String.valueOf(map.get("uuid")));
			imMsgModel.setReSessionName(String.valueOf(map.get("realName")));
			imMsgModel.setMsgContent(String.valueOf(map.get("reason")));
			logger.warn("--------------------开始发送消息到消息队列");
			rabbitTemplate.convertAndSend("IM.MSG.SEND", imMsgModel);
			logger.warn("--------------------结束发送消息到消息队列");
			if(String.valueOf(map.get("examineStatus")).equals("1")) {
				map.put("reason", "");
			}
		}
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
}
