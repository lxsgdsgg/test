package com.bashiju.anal.service.impl;

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
import com.bashiju.anal.mapper.UserAchievementMapper;
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
 * @ClassName:  UserAchievementServiceImpl   
 * @Description:(个人业绩排行具体实现)   
 * @author: liwen
 * @date:   2018年11月29日 上午10:21:32   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@SystemServiceLog(sourceType="个人业绩排行") 
@Service
public class UserAchievementServiceImpl implements UserAchievementService{
	
	@Autowired
	 private CommonSqlServie commonSqlServie;
	@Autowired
	private UserAchievementMapper userAchievementMapper;
	@Autowired
	private DataAuthHelper dataAuthHelper;
	
	@SystemServiceLog(operationType="查询个人业绩")
	@Override
	public List<Map<String, Object>> queryUserAchievement(String months,String companyId,String userId) {
		List<Map<String, Object>> res = userAchievementMapper.queryUserAchievement(months, userId, companyId);
		//上个月业绩
		List<Map<String, Object>> preRes = userAchievementMapper.queryPerMonthUserAchievement(months, userId, companyId);
		if(res.size()>0) {
			for(int i=0;i<res.size();i++) {
				res.get(i).put("sort", i);
				if(preRes.size()>0) {
					String sortChange = "0";
					for(int j=0;j<preRes.size();j++) {//算排行变化
						if(res.get(i).get("id").equals(preRes.get(j).get("userId"))){//同一个用户
							if(i>=j) {
								sortChange = "+"+(i-j);
							}else {
								sortChange = "-"+(j-i);
							}
						}
					}
					res.get(i).put("sortChange", sortChange);
				}
			}
		}
		return res;
	}
	
	
	
	
}
