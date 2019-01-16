/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  NavigationBarServiceImpl.java   
 * @Package com.bashiju.www.service.navigationbar.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年7月25日 上午9:55:27   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.service.navigationbar.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.wutils.exception.BusinessException;
import com.bashiju.www.mapper.NavigationBarMapper;
import com.bashiju.www.pojo.service.out.comm.NavigationBar;
import com.bashiju.www.pojo.service.out.comm.OpenCity;
import com.bashiju.www.service.navigationbar.NavigationBarService;

/**   
 * @ClassName:  NavigationBarServiceImpl   
 * @Description:导航栏服务   
 * @author: wangpeng
 * @date:   2018年7月25日 上午9:55:27   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class NavigationBarServiceImpl implements NavigationBarService{

	
	@Autowired
	private NavigationBarMapper navigationBarMapper;
	
	/**
	 * @Title: queryHeadNavigationBar   
	 * @Description: 查询头部导航栏 
	 * @param cityCode  已开通城市行政区域代码
	 * @return: List<NavigationBar>
	 * @see com.bashiju.www.service.navigationbar.NavigationBarService#queryHeadNavigationBar(java.lang.String)
	 */
	
	@Override
	public List<NavigationBar> queryHeadNavigationBar(String cityCode) {
		if(StringUtils.isEmpty(cityCode))
			throw new BusinessException("开通城市不能为空");
		//导航类型为头部
		String type ="0";
		List<NavigationBar> navigationBar = navigationBarMapper.queryNavigationBarByType(cityCode,type);
		return navigationBar;
	}

	/**
	 * @Title: queryLinkedNavigationBar   
	 * @Description: 查询底部导航栏    
	 * @param cityCode 已开通城市行政区域代码
	 * @return: List<NavigationBar>
	 * @see com.bashiju.www.service.navigationbar.NavigationBarService#queryBottomNavigationBar(java.lang.String)
	 */
	
	@Override
	public List<NavigationBar> queryLinkedNavigationBar(String cityCode) {
		if(StringUtils.isEmpty(cityCode))
			throw new BusinessException("开通城市不能为空");
		//导航类型为底部
		String type ="1";
		List<NavigationBar> navigationBar =	navigationBarMapper.queryNavigationBarByType(cityCode,type);
		return navigationBar;
	}

	/**
	 * @Description: 查询开通的城市信息   
	 * @return: List<OpenCity>
	 * @see com.bashiju.www.service.navigationbar.NavigationBarService#queryOpenCitys()   
	 */
	@Override
	public List<OpenCity> queryOpenCitys() {
		return navigationBarMapper.queryOpenCitys();
	}

}
