package com.bashiju.manage.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.Page;

/**
 * 
 * @ClassName:  ContentmanageService   
 * @Description:TODO(内容管理接口)   
 * @author: liwen
 * @date:   2018年7月20日 上午11:22:07   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface ContentmanageService {
	public Long saveArticle(Map<String,Object> data);
	public Page<Map<String, Object>> queryArticleList(Map<String,Object> conditions,int page,int limit);
	public Map<String,Object> saveImg(HttpServletRequest request);
	public Map<String,Object> saveEditorImg(HttpServletRequest request);
	public boolean delArticle(Long id,String type);
	public Page<Map<String, Object>> queryExamineList(Map<String,Object> conditions,int page,int limit);
	public Long sureArticle(Long id);
	public Long rejectArticle(Long id,String reason);
	public List<Map<String,Object>> queryCity(Long userId);
	public Page<Map<String, Object>> queryCustTipoffk(Map<String,Object> condition,int page,int limit);
	public Page<Map<String, Object>> queryCustFeedback(Map<String,Object> condition,int page,int limit);
	public Long saveHandleResult(int id,String handleResult,String type);
	/*public Long saveAnalysisTemplet(Map<String,Object> data);
	public Page<Map<String, Object>> queryAnalysisTemplet(int page,int limit);
	
	public List<Map<String,Object>> queryTempletName();
	public Long saveAddressConf(Map<String,Object> data);
	public Page<Map<String, Object>> queryAddressConf(int page,int limit);
	public boolean delAddressConf(Long id);
	public Long saveJobConf(Map<String,Object> data);
	public Page<Map<String, Object>> queryJobConf(int page,int limit);
	public boolean delJobConf(Long id);*/
	
}
