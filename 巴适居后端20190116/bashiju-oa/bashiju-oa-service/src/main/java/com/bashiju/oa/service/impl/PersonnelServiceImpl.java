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
import com.bashiju.oa.mapper.PersonnelMapper;
import com.bashiju.oa.service.ArticleService;
import com.bashiju.oa.service.PersonnelService;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.FtpUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * 
 * @ClassName:  PersonnelServiceImpl   
 * @Description:(人事变动具体实现)   
 * @author: liwen
 * @date:   2018年11月20日 上午10:21:32   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@SystemServiceLog(sourceType="OA人事") 
@Service
public class PersonnelServiceImpl implements PersonnelService{
	
	@Autowired
	 private CommonSqlServie commonSqlServie;
	@Autowired
	private PersonnelMapper personnelMapper;
	@Autowired
	private DataAuthHelper dataAuthHelper;
	
	@SystemServiceLog(operationType="保存人事信息")
	@Override
	public Long savePersonnel(Map<String,Object> data) {
		Long res = commonSqlServie.commonOperationDatabase(data, "sys_personalrecords","id", false);
		return res;
	}
	
	@SystemServiceLog(operationType="保存人事变动")
	@Override
	public Long savePersonnelChange(Map<String,Object> data) {
		Map<String,Object> user = UserThreadLocal.get();
		data.put("operatorId", user.get("id"));
		data.put("permissionArea", user.get("deptId"));
		 Long res = commonSqlServie.commonOperationDatabase(data, "sys_personalrecords_change", false);
		return res;
	}
	
	@SystemServiceLog(operationType="查询人事列表")
	@Override
	public Page<Map<String, Object>> queryPersonnelList(Map<String,Object> conditions,int page,int limit){
		PageHelper.startPage(page, limit);
		dataAuthHelper.auth(MenuEnum.MENU_784.getCode(),UserThreadLocal.get().get("id").toString());
		Page<Map<String, Object>> pageInfo = personnelMapper.queryPersonnelList(conditions);
		
		ExecutionResult.descFormat("", "查询人事列表");
		return pageInfo;
	}

		
	//上传带看图片
	@SystemServiceLog(operationType="上传")
	@Override
	public Map<String,Object> saveImg(HttpServletRequest request){
		String filePath = "";
		String fileName = "";
		Long id = Long.parseLong(request.getParameter("id").toString());
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
					Map<String,Object> mm = new HashMap<>();
					mm.put("id", id);
					mm.put("profilePhoto", filePath);
					commonSqlServie.commonOperationDatabase(mm, "sys_user", "id", false);
				}
			}
		}
		Map<String,Object> res = new HashMap<>();
		res.put("fileName", fileName);
		res.put("filePath", filePath);
		return res;
	}
	
	
	@SystemServiceLog(operationType="查询变动审核列表")
	@Override
	public Page<Map<String, Object>> queryPersonnelExamine(Map<String,Object> conditions,int page,int limit){
		PageHelper.startPage(page, limit);
		dataAuthHelper.auth(MenuEnum.MENU_785.getCode(),UserThreadLocal.get().get("id").toString());
		Page<Map<String, Object>> pageInfo = null;
		pageInfo = personnelMapper.queryPersonnelExamine(conditions);
		ExecutionResult.descFormat("", "查询内容审核列表");
		return pageInfo;
	}
	
	@SystemServiceLog(operationType="确定变动")
	@Override
	public Long surePersonnel(Long id,String reason) {
		
		Map<String,Object> mm = new HashMap<>();
		mm.put("status",ExamineStatusEnum.EXAMINED.getCode());
		mm.put("approvalResult", reason);
		mm.put("approvalTime", new Date());
		mm.put("id", id);
		
		Long res = commonSqlServie.commonOperationDatabase(mm, "sys_personalrecords_change","id", false);
		return res;
	}
	
	@SystemServiceLog(operationType="驳回变动")
	@Override
	public Long rejectPersonnel(Long id,String reason) {
		Map<String,Object> mm = new HashMap<>();
		mm.put("status",ExamineStatusEnum.REJECT.getCode());
		mm.put("approvalResult", reason);
		mm.put("approvalTime", new Date());
		mm.put("id", id);
		
		Long res = commonSqlServie.commonOperationDatabase(mm, "sys_personalrecords_change","id", false);
		
		return res;
	}
	
	@SystemServiceLog(operationType="转到上级")
	@Override
	public Map<String,Object> turnPersonnel(Long id,String reason) {
		return null;
	}
	
	
}
