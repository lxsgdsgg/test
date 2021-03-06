/**  
			 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  RecruitInfoServiceImpl.java   
 * @Package com.bashiju.oa.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年8月9日 下午5:19:07   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.oa.service.impl;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.MenuEnum;
import com.bashiju.oa.mapper.RecruitInfoMapper;
import com.bashiju.oa.service.RecruitInfoService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**   
 * @ClassName:  RecruitInfoServiceImpl   
 * @Description:招聘信息管理服务 
 * @author: wangpeng
 * @date:   2018年8月9日 下午5:19:07   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class RecruitInfoServiceImpl extends CommonSqlServie implements RecruitInfoService  {
	@Autowired
	private RecruitInfoMapper recruitInfoMapper;
	@Autowired
	private DataAuthHelper dataAuthHelper;
	
	/**   
	 * <p>Title: queryRecruitInfo</p>   
	 * <p>Description: 查询招聘信息</p>   
	 * @see com.bashiju.oa.service.RecruitInfoService#queryRecruitInfo()   
	 */
	
	@Override
	public Page<Map<String, Object>> queryRecruitInfo(int page,int limit,String name) {
		PageHelper.startPage(page, limit);
		dataAuthHelper.auth(MenuEnum.MENU_133.getCode(),UserThreadLocal.get().get("id").toString());
		Page<Map<String, Object>> map =	recruitInfoMapper.queryRecruitInfo(name);
		return map;
	}

	/**   
	 * <p>Title: queryRecruitInfoById</p>   
	 * <p>Description: 条件查询招聘信息</p>   
	 * @param id 招聘信息id
	 * @see com.bashiju.oa.service.RecruitInfoService#queryRecruitInfoById(java.lang.String)   
	 */
	
	@Override
	public Map<String, Object> queryRecruitInfoById(String id) {
		if(StringUtils.isEmpty(id))
			throw new BusinessException("id不能为空");
		Map<String, Object>  map = 	recruitInfoMapper.queryRecruitInfoById(id);
		return map;
	}

	/**   
	 * <p>Title: saveOrUpdateRecruitInfo</p>   
	 * <p>Description: </p>   
	 * @param paramMap
	 * @return   
	 * @see com.bashiju.oa.service.RecruitInfoService#saveOrUpdateRecruitInfo(java.util.Map)   
	 */
	
	@Override
	public boolean saveOrUpdateRecruitInfo(Map<String,Object> paramMap) {
		if(!paramMap.containsKey("id")||StringUtils.isEmpty(paramMap.get("id").toString())||paramMap.get("id")==null) {
			//新增
			paramMap.remove("id");
			paramMap.put("operatorId", UserThreadLocal.get().get("id"));
			paramMap.put("operator", UserThreadLocal.get().get("realName"));
			paramMap.put("publisherId", UserThreadLocal.get().get("id"));
			paramMap.put("publisher", UserThreadLocal.get().get("realName"));
			paramMap.put("permissionArea", UserThreadLocal.get().get("deptId"));
			paramMap.put("publishTime",new Date());
			long result = this.commonOperationDatabase(paramMap, "sys_recruitInfo", false);
			if(result>0) {
				ExecutionResult.descFormat(Long.toString(result), "新增招聘信息");
				return true;
			}
			return false;
		}else {
			//修改
			long result = 	this.commonOperationDatabase(paramMap, "sys_recruitInfo", "id", false);
			if(result>0) {
				ExecutionResult.descFormat(Long.toString(result), "修改招聘信息");
				return true;
			}return false;
		}
	}

	/**   
	 * <p>Title: delRecruitInfoById</p>   
	 * <p>Description: 逻辑删除招聘信息 </p>   
	 * @param id 招聘信息id
	 * @see com.bashiju.oa.service.RecruitInfoService#delRecruitInfoById(java.lang.String)   
	 */
	
	@Override
	public boolean delRecruitInfoById(String id) {
		if(StringUtils.isEmpty(id))
			throw new BusinessException("招聘信息id不能为空");
		boolean result = this.delData("sys_recruitInfo",id, "id",false);
		if(result)
		   return true;	
		return false;
	}
	
}
