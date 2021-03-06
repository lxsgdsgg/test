/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  teest.java   
 * @Package com.bashiju.www.service.navigationbar   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年7月25日 上午9:49:47   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.service.navigationbar;

import java.util.List;

import com.bashiju.www.pojo.service.out.comm.NavigationBar;
import com.bashiju.www.pojo.service.out.comm.OpenCity;
/**
 * 
 * @ClassName:  NavigationBarService   
 * @Description:导航栏接口  
 * @author: wangpeng
 * @date:   2018年7月25日 上午9:53:35   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface NavigationBarService {
	
	/**
	 * @Title: queryHeadNavigationBar   
	 * @Description: 查询头部导航栏 
	 * @param cityCode  已开通城市行政区域代码
	 * @return: List<NavigationBar>
	 */
	List<NavigationBar> queryHeadNavigationBar(String cityCode);
	
	/**
	 * @Title: queryLinkedNavigationBar   
	 * @Description: 查询底部导航栏    
	 * @param cityCode 已开通城市行政区域代码
	 * @return: List<NavigationBar>
	 */
	List<NavigationBar> queryLinkedNavigationBar(String cityCode);
	
	/**
	 * @Description: 查询开通的城市信息   
	 * @return: List<OpenCity>
	 */
	List<OpenCity> queryOpenCitys(); 
	
}
