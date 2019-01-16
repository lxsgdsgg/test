package com.bashiju.manage.service.impl;

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
import com.bashiju.enums.FtpSourceTypeEnum;
import com.bashiju.enums.HouseArticleTypeEnum;
import com.bashiju.enums.MenuEnum;
import com.bashiju.enums.RoleGroupEnum;
import com.bashiju.manage.mapper.ContentmanageMapper;
import com.bashiju.manage.mapper.ImgmanageMapper;
import com.bashiju.manage.service.ContentmanageService;
import com.bashiju.manage.service.ImgmanageService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.FtpUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * 
 * @ClassName:  ImgmanageServiceImpl   
 * @Description:TODO(图片管理具体实现)   
 * @author: liwen
 * @date:   2018年7月20日 上午11:21:32   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@SystemServiceLog(sourceType="图片管理") 
@Service
public class ImgmanageServiceImpl implements ImgmanageService{
	
	@Autowired
	 private CommonSqlServie commonSqlServie;
	@Autowired
	 private ImgmanageMapper imgmanageMapper;
	@Autowired
	private DataAuthHelper dataAuthHelper;

	public Page<Map<String, Object>> queryComImg(Map<String,Object> conditions,int page,int limit){
		PageHelper.startPage(page, limit);
		dataAuthHelper.auth(MenuEnum.MENU_55.getCode(),UserThreadLocal.get().get("id").toString());
		Page<Map<String, Object>> res = imgmanageMapper.queryComImg(conditions);
		return res;
	}
	
	
	public Long saveComImg(Map<String,Object> data) {
		Long res = 0l;
		Map<String,Object> mm = (Map)data;
		if(data.containsKey("id")) {
			res = commonSqlServie.commonOperationDatabase(mm, "sys_image", "id", false); //编辑
		}else {
			res = commonSqlServie.commonOperationDatabase(mm, "sys_image", false); //新增
		}
		return res;
	}
	
	public boolean delComImg(int id) {
		boolean res = commonSqlServie.delData("sys_image", "id", id+"", false);
		return res;
	}
}
