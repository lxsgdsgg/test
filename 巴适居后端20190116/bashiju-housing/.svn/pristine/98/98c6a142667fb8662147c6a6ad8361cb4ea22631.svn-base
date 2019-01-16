package com.bashiju.housing.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.Page;

public interface IPrintTemplateService {
	/**
	 * 
		 * 查询模板列表）
		 * @Description: 进入菜单主页面（详细描述） 
		 * @param page 当前页数
		 * @param limit 每页条数
		 * @return 
		 * Page<Map<String,Object>>
	 */
	Page<Map<String, Object>> queryTemplateList(int page,int limit);
	/**
	 * 
		 * 查询打印模板下拉列表
		 * @Description: 查询打印模板下拉列表
		 * @return 
		 * List<Map<String,Object>>
	 */
	List<Map<String, Object>> queryTemplateSelectData();
	/**
	 * 
		 * 查询模板详细
		 * @Description: 查询模板详细 
		 * @param id 模板id
		 * @return 
		 * Map<String,Object>
	 */
	Map<String, Object> queryDetailed(Integer id);
	/**
	 * 
		 * 添加打印模板
		 * @Description: 添加打印模板 
		 * @param baseInfo 模板基础信息
		 * @param spanArry span标签数据
		 * @param picArry 图片标签数据
		 * @param backUrl 背景图片
		 * @return 
		 * int
	 */
	int addPrintTemplate(String baseInfo,String spanArry,String picArry,String backUrl);
	/**
	 * 
		 * 修改打印模板
		 * @Description: 修改打印模板 
		 * @param baseInfo 模板基础信息
		 * @param spanArry span标签数据
		 * @param picArry 图片标签数据
		 * @param backUrl 背景图片
		 * @return 
		 * int
	 */
	int updatePrintTemplate(String baseInfo,String spanArry,String picArry,String backUrl);
	/**
	 * 
		 * 删除打印模板
		 * @Description: 删除打印模板 
		 * @param id 模板id
		 * @return 
		 * int
	 */
	int deletePrintTemplate(Integer id);
	/**
	 * 
		 * 查询模板配景图片列表
		 * @Description: 查询模板配景图片列表
		 * @return 
		 * List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryBackground();
	/**
	 * 
		 * 上传背景图片
		 * @Description: 上传背景图片 
		 * @param request
		 * @return 
		 * int
	 */
	int uploadBackgroundPic(HttpServletRequest request);
	/**
	 * 
		 * 删除打印模板背景图片
		 * @Description: 删除打印模板背景图片
		 * @param id
		 * @return 
		 * boolean
	 */
	boolean deletePrintTemplateBackGroundPic(Integer id);	
	/**
	 * 
		 * 根据房源id查询房源打印信息
		 * @Description: 根据房源id查询房源打印信息
		 * @param houseId 房源id
		 * @param templateId 模板id
		 * @return 
		 * Map<String, Object>
	 */
	Map<String, Object> queryHousePrintDetailed(Long houseId,Integer templateId);
}
