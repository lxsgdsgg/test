package com.bashiju.oa.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.BottomContentEnum;
import com.bashiju.enums.ExamineStatusEnum;
import com.bashiju.enums.FtpFileTypeEnum;
import com.bashiju.enums.HouseArticleTypeEnum;
import com.bashiju.enums.MenuEnum;
import com.bashiju.enums.OaArticleTypeEnum;
import com.bashiju.enums.RoleGroupEnum;
import com.bashiju.oa.mapper.ArticleMapper;
import com.bashiju.oa.service.ArticleService;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.FtpUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * 
 * @ClassName:  ArticleServiceImpl   
 * @Description:(OA文章具体实现)   
 * @author: liwen
 * @date:   2018年10月05日 下午14:21:32   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@SystemServiceLog(sourceType="OA文章") 
@Service
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired
	 private CommonSqlServie commonSqlServie;
	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private DataAuthHelper dataAuthHelper;
	
	@SystemServiceLog(operationType="保存文章")
	@Override
	public Long saveArticle(Map<String,Object> data) {
		Long res = 0l;
		if(data.containsKey("id")) {//修改
			res = commonSqlServie.commonOperationDatabase(data, "sys_oa_article","id", false);
		}else {//新增
			res = commonSqlServie.commonOperationDatabase(data, "sys_oa_article", false);
		}
		
		return res;
	}
	
	@SystemServiceLog(operationType="查询文章列表")
	@Override
	public Page<Map<String, Object>> queryArticleList(Map<String,Object> conditions,int page,int limit){
		PageHelper.startPage(page, limit);
		Page<Map<String, Object>> pageInfo = null;
		if(OaArticleTypeEnum.OAKX.getCode().equals(conditions.get("type").toString())) {//快讯
			conditions.put("companyId", UserThreadLocal.get().get("companyId"));
			pageInfo = articleMapper.queryNewsList(conditions);
		}else {//公告
			conditions.put("companyId", UserThreadLocal.get().get("companyId"));
			pageInfo = articleMapper.queryNoticeList(conditions);
		}
		
		ExecutionResult.descFormat("", "查询文章列表");
		return pageInfo;
	}

		
	//上传带看图片
	@SystemServiceLog(operationType="上传")
	@Override
	public Map<String,Object> saveImg(HttpServletRequest request) throws Exception{
		String filePath = "";
		String fileName = "";
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator iter = multiRequest.getFileNames();

			if (iter.hasNext()) {
				// 一次遍历所有文件
				//List<MultipartFile> files = multiRequest.getFiles(iter.next().toString());
				
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				if (file != null) {
					fileName = file.getName();
					filePath = FtpUtil.uploadFile(file,"","",FtpFileTypeEnum.FTP_FILE_TP.getCode(),true);
					
				}
			}
		}
		Map<String,Object> res = new HashMap<>();
		res.put("fileName", fileName);
		res.put("filePath", filePath);
		return res;
	}
	
	
	@SystemServiceLog(operationType="删除")
	@Override
	public boolean delArticle(int id) {
		//从ftp删除原来图片
		Map<String,Object> mpath = articleMapper.queryArticleById(id);
		if(mpath!=null && mpath.size()>0) {
			FtpUtil.deleteFile("", "", FtpFileTypeEnum.FTP_FILE_TP.getCode(), mpath.get("path").toString());
		}
		return commonSqlServie.delData("sys_oa_article", "id", id+"", false);
	}
	
	@SystemServiceLog(operationType="查询内容审核列表")
	@Override
	public Page<Map<String, Object>> queryExamineList(Map<String,Object> conditions,int page,int limit){
		PageHelper.startPage(page, limit);
		Page<Map<String, Object>> pageInfo = null;
		conditions.put("companyId", UserThreadLocal.get().get("companyId"));
		pageInfo = articleMapper.queryExamineList(conditions);
		ExecutionResult.descFormat("", "查询内容审核列表");
		return pageInfo;
	}
	
	@SystemServiceLog(operationType="确定内容")
	@Override
	public String sureArticle(Long id) {
		String res = "";
		Map<String,Object> mm = new HashMap<>();
		mm.put("examineStatus",ExamineStatusEnum.EXAMINED.getCode());
		mm.put("auditorId", UserThreadLocal.get().get("id"));
		mm.put("auditor", UserThreadLocal.get().get("realName"));
		mm.put("auditTime", new Date());
		mm.put("id", id);
		commonSqlServie.commonOperationDatabase(mm, "sys_oa_article","id", false);
		res = UserThreadLocal.get().get("realName").toString();
		return res;
	}
	
	@SystemServiceLog(operationType="驳回内容")
	@Override
	public Map<String,Object> rejectArticle(Long id,String reason) {
		Map<String,Object> mm = new HashMap<>();
		mm.put("examineStatus",ExamineStatusEnum.REJECT.getCode());
		mm.put("rejectReason", reason);
		mm.put("auditorId", UserThreadLocal.get().get("id"));
		mm.put("auditor", UserThreadLocal.get().get("realName"));
		mm.put("auditTime", new Date());
		mm.put("id", id);
		Map<String,Object> res = new HashMap<>();
		commonSqlServie.commonOperationDatabase(mm, "sys_oa_article","id", false);
		res.put("auditor", UserThreadLocal.get().get("realName"));
		res.put("rejectReason", reason);
		return res;
	}
	
	@SystemServiceLog(operationType="快讯浏览")
	@Override
	public Long newsBrowse(int id,int browseCnt) {
		Map<String,Object> mm = new HashMap<>();
		mm.put("id",id);
		mm.put("browseCnt", browseCnt+1);
		
		Long res = commonSqlServie.commonOperationDatabase(mm, "sys_oa_article","id", false);
		
		return res;
	}
	
	@SystemServiceLog(operationType="公告浏览")
	@Override
	public Long noticeBrowse(int id,int browseCnt) {
		Map<String,Object> m1 = new HashMap<>();
		m1.put("id",id);
		m1.put("browseCnt", browseCnt+1);
		commonSqlServie.commonOperationDatabase(m1, "sys_oa_article","id", false);
		Map<String,Object> m2 = new HashMap<>();
		m2.put("articleId", id);
		m2.put("operatorId", UserThreadLocal.get().get("id"));
		m2.put("operator", UserThreadLocal.get().get("realName"));
		Long res = commonSqlServie.commonOperationDatabase(m2, "sys_oa_articlebrowse", false);
		return res;
	}
	
	@SystemServiceLog(operationType="查询最新快讯")
	@Override
	public List<Map<String, Object>> queryNewsInfo(String cityCode,int daynum){
		List<Map<String, Object>> res = articleMapper.queryNewsInfo(cityCode,daynum,UserThreadLocal.get().get("companyId").toString());
		return res;
	}
	
	@SystemServiceLog(operationType="查询最新公告")
	@Override
	public List<Map<String, Object>> queryNoticeInfo(String cityCode,int daynum){
		List<Map<String, Object>> res = articleMapper.queryNoticeInfo(cityCode,daynum,UserThreadLocal.get().get("companyId").toString());
		return res;
	}
	
	@SystemServiceLog(operationType="查询详细")
	@Override
	public Map<String,Object> queryArticleDetail(int id){
		Map<String,Object> res = articleMapper.queryArticleDetail(id);
		return res;
	}
	
	@SystemServiceLog(operationType="查授权城市")
	@Override
	public List<Map<String,Object>> queryCity(Long userId){
		List<Map<String,Object>> res = null;
		String cityCode = UserThreadLocal.get().get("cityCode").toString();
		String roleGroup = UserThreadLocal.get().get("roleGroup").toString();
		if(RoleGroupEnum.ADMIN.getCode().equals(roleGroup)) {//超级管理员:平台开通的城市
			res = articleMapper.queryCityByAdmin();
			return res;
		}else if(RoleGroupEnum.CITY_ADMIN.getCode().equals(roleGroup)) {//城市管理员：对用户或对角色授权的城市
			res = articleMapper.queryCityByUser(userId);
			if(res.size()>0) {
				return res;
			}else {
				 res = articleMapper.queryCityByRole(userId);
				 return res;
			}
		}else {//普通用户：用户自己所在城市
			res = new ArrayList<>();
			Map<String,Object> city = new HashMap<>();
			String cityName = articleMapper.queryCityName(cityCode);
			city.put("cityCode", cityCode);
			city.put("cityName", cityName);
			res.add(city);
			return res;
		}
		
	}
	
}
