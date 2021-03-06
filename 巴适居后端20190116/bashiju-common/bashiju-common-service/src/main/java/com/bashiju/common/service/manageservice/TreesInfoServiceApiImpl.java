/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  TreesInfoServiceApiImpl.java   
 * @Package com.bashiju.manage.api   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年6月7日 下午6:26:41   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.common.service.manageservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.bashiju.api.TreesInfoServiceApi;
import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.common.mapper.TreesInfoMapper;
import com.bashiju.enums.MenuEnum;
import com.bashiju.utils.exception.BusinessException;

/**   
 * @ClassName:  TreesInfoServiceApiImpl   
 * @Description:各类树结构服务  
 * @author: yangz
 * @date:   2018年6月7日 下午6:26:41   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class TreesInfoServiceApiImpl implements TreesInfoServiceApi {
	
	@Autowired
	private TreesInfoMapper treesInfoMapper;
	
	@Autowired
	private DataAuthHelper DataAuthHelper;
	
	/**
	 * @Description: 查询部门树信息   
	 * <br>[公司]-->市-->大区-->区-->店-->组
	 * @param roleGroup 当前用户所属的角色组
	 * @param userId 当前用户id
	 * @return: List<Map<String,Object>>
	 * @see com.bashiju.api.TreesInfoServiceApi#queryDeptTrees(java.lang.String,java.lang.String)   
	 */
	@Override
	public List<Map<String, Object>> queryDeptTrees(String roleGroup,String userId) {
		DataAuthHelper.auth(MenuEnum.MENU_64.getCode(), userId);
		return this.treesInfoMapper.queryDeptTrees(roleGroup,userId);
	}

	/**
	 * @Description: 查询用户树信息   
	 * <br>[公司]-->市-->大区-->区-->店-->组-->人
	 * @param roleGroup 当前用户所属的角色组
	 * @param userId 当前用户id
	 * @return: List<Map<String,Object>>
	 * @see com.bashiju.api.TreesInfoServiceApi#queryUsersTrees(java.lang.String,java.lang.String)   
	 */
	@Override
	public List<Map<String, Object>> queryUsersTrees(String roleGroup,String userId) {
		DataAuthHelper.auth(MenuEnum.MENU_63.getCode(), userId);
		return this.treesInfoMapper.queryDeptInfoToCreateTree(roleGroup,userId);
	}

	/**
	 * @Description: 查询小区树信息   
	 * <br>市-->区-->片区-->小区
	 * @param code 行政区域代码
	 * @param userId 当前用户id
	 * @return: List<Map<String,Object>>
	 * @see com.bashiju.api.TreesInfoServiceApi#queryCommunityTrees(java.lang.String,java.lang.String)   
	 */
	@Override
	public List<Map<String, Object>> queryCommunityTrees(String code,String userId) {
		if(StringUtils.isEmpty(code))
			throw new BusinessException("行政区域代码不允许为空");
		return this.treesInfoMapper.getRegionList(code);
	}


	/**
	 * @Description: 查询行政区域信息 
	 * <br> 省-->市-->区(县)
	 * @return: List<Map<String,Object>>
	 * @see com.bashiju.api.TreesInfoServiceApi#queryAreaTrees()   
	 */
	@Override
	public List<Map<String, Object>> queryAreaTrees() {
		return treesInfoMapper.queryAreaSelect();
	}

}
