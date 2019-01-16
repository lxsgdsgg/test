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
import com.bashiju.enums.ExamineStatusEnum;
import com.bashiju.enums.HouseArticleTypeEnum;
import com.bashiju.enums.RoleGroupEnum;
import com.bashiju.manage.mapper.ContentmanageMapper;
import com.bashiju.manage.mapper.LabelmanageMapper;
import com.bashiju.manage.service.ContentmanageService;
import com.bashiju.manage.service.LabelmanageService;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.FtpUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * 
 * @ClassName:  LabelmanageServiceImpl   
 * @Description:TODO(标签管理具体实现)   
 * @author: liwen
 * @date:   2018年8月9日 上午11:21:32   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@SystemServiceLog(sourceType="标签管理") 
@Service
public class LabelmanageServiceImpl implements LabelmanageService{
	
	@Autowired
	 private CommonSqlServie commonSqlServie;
	@Autowired
	 private LabelmanageMapper labelmanageMapper;
	
	
	@SystemServiceLog(operationType="保存标签")
	@Override
	public Long saveLabel(Map<String,Object> data) {
		Long res = null;
		Map<String,Object> mm = (Map)data;
		String type = mm.get("type").toString();
		mm.put("permissionArea", UserThreadLocal.get().get("deptId"));
		mm.put("operatorId", UserThreadLocal.get().get("id"));
		mm.put("operator", UserThreadLocal.get().get("realName"));
		if(data.containsKey("id") && !"".equals(data.get("id")) && data.get("id")!=null) {//修改
			res = commonSqlServie.commonOperationDatabase(mm, "sys_labeldManage","id", false);
			
		}else {//新增
			mm.remove("id");
			res = commonSqlServie.commonOperationDatabase(mm, "sys_labeldManage", false);
		}
		
		return res;
	}
	
	@SystemServiceLog(operationType="查询标签列表")
	@Override
	public Page<Map<String, Object>> queryLabelList(String conditions,int page,int limit){
		PageHelper.startPage(page, limit);
		Page<Map<String, Object>> pageInfo = null;
		Map<String,Object> conditionsMap = JSON.parseObject(conditions);
		pageInfo = labelmanageMapper.queryLabelList(conditionsMap);
		
		ExecutionResult.descFormat("", "查询标签列表");
		return pageInfo;
	}

		
	
	
	@SystemServiceLog(operationType="删除")
	@Override
	public boolean delLabel(Long id) {
		return commonSqlServie.delData("sys_labeldManage", "id", id+"", false);
	}
	
	
	
	
	
	
	
	
}
