package com.bashiju.anal.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.bashiju.anal.mapper.DeptAchievementMapper;
import com.bashiju.anal.mapper.UserAchievementMapper;
import com.bashiju.anal.service.DeptAchievementService;
import com.bashiju.anal.service.UserAchievementService;
import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.BottomContentEnum;
import com.bashiju.enums.ExamineStatusEnum;
import com.bashiju.enums.FtpFileTypeEnum;
import com.bashiju.enums.HouseArticleTypeEnum;
import com.bashiju.enums.MenuEnum;
import com.bashiju.enums.OaArticleTypeEnum;
import com.bashiju.enums.RoleGroupEnum;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.FtpUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * 
 * @ClassName:  DeptAchievementServiceImpl   
 * @Description:(门店业绩排行具体实现)   
 * @author: liwen
 * @date:   2018年11月29日 下午14:21:32   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@SystemServiceLog(sourceType="门店业绩排行") 
@Service
public class DeptAchievementServiceImpl implements DeptAchievementService{
	
	@Autowired
	 private CommonSqlServie commonSqlServie;
	@Autowired
	private DeptAchievementMapper deptAchievementMapper;
	@Autowired
	private DataAuthHelper dataAuthHelper;
	
	@SystemServiceLog(operationType="查询门店业绩")
	@Override
	public List<Map<String, Object>> queryDeptAchievement(String months,String companyId) {
		List<Map<String, Object>> resList = deptAchievementMapper.queryAchievement(months,companyId);

		for(int i=0;i<resList.size();i++) {
			resList.get(i).put("sort", i);//排序码
		}
		return resList;
	}
				
}
