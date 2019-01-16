package com.bashiju.housing.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bashiju.housing.service.IPrintTemplateService;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;
@Controller
public class PrintTemplateController {
	@Autowired
	IPrintTemplateService printTemplateService;
	//打印模板列表查询
	@RequestMapping(value="queryTemplateList")
	@ResponseBody
	public BashijuResult queryTemplateList(int page,int limit) {
		Page<Map<String, Object>> pageInfo=printTemplateService.queryTemplateList(page, limit);
		Map<String, Object> result=new HashMap<>();
		result.put("total", pageInfo.getTotal());
		result.put("data", pageInfo.getResult());
		return BashijuResult.ok(result);
	}
	//模板详细查询
	@RequestMapping(value="queryDetailed")
	@ResponseBody
	public BashijuResult queryDetailed(Integer id) {
		Map<String, Object> result=printTemplateService.queryDetailed(id);
		return BashijuResult.ok(result);
	}
	//添加模板
	@RequestMapping(value="addPrintTemplate")
	@ResponseBody
	public BashijuResult addPrintTemplate(String baseInfo,String spanArry,String picArry,String backUrl) {
		int id=printTemplateService.addPrintTemplate(baseInfo, spanArry, picArry, backUrl);
		return BashijuResult.ok(id);
	}
	//修改模板
	@RequestMapping(value="updatePrintTemplate")
	@ResponseBody
	public BashijuResult updatePrintTemplate(String baseInfo,String spanArry,String picArry,String backUrl) {
		printTemplateService.updatePrintTemplate(baseInfo, spanArry, picArry, backUrl);
		return BashijuResult.ok();
	}
	//删除模板
	@RequestMapping(value="deletePrintTemplate")
	@ResponseBody
	public BashijuResult deletePrintTemplate(Integer id) {
		printTemplateService.deletePrintTemplate(id);
		return BashijuResult.ok();
	}
	//查询模板背景图片
	@RequestMapping(value="queryBackground")
	@ResponseBody
	public BashijuResult queryBackground() {
		List<Map<String, Object>> result=printTemplateService.queryBackground();
		return BashijuResult.ok(result);
	}
	//上传模板背景图片
	@RequestMapping(value="uploadBackgroundPic")
	@ResponseBody
	public BashijuResult uploadBackgroundPic(HttpServletRequest request) {
		printTemplateService.uploadBackgroundPic(request);
		return BashijuResult.ok();
	}
	//删除模板
	@RequestMapping(value = "deleteBackGroundPic")
	@ResponseBody
	public BashijuResult deletePrintTemplateBackGroundPic(Integer id) {
		printTemplateService.deletePrintTemplateBackGroundPic(id);
		return BashijuResult.ok();
	}
	/**
	 * 
		 * 根据房源id查询房源打印信息
		 * @Description: 根据房源id查询房源打印信息
		 * @param houseId 房源id
		 * @param templateId 模板id
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping(value = "queryHousePrintDetailed")
	@ResponseBody
	public BashijuResult queryHousePrintDetailed(Long houseId,Integer templateId) {
		Map<String, Object>result= printTemplateService.queryHousePrintDetailed(houseId,templateId);
		return BashijuResult.ok(result);
	}
	/**
	 * 
		 * 查询打印模板下拉列表
		 * @Description: 查询打印模板下拉列表
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping(value = "queryTemplateSelectData")
	@ResponseBody
	public BashijuResult queryTemplateSelectData() {
		List<Map<String, Object>> results=printTemplateService.queryTemplateSelectData();
		return BashijuResult.ok(results);
	}
}
