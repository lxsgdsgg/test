/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  NavigationBarMapper.java   
 * @Package com.bashiju.webservice.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年7月25日 上午9:44:30   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.webservice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bashiju.www.pojo.service.out.comm.NavigationBar;
import com.bashiju.www.pojo.service.out.comm.OpenCity;

/**   
 * @ClassName:  NavigationBarMapper   
 * @Description:导航条映射接口
 * @author: wangpeng
 * @date:   2018年7月25日 上午9:44:30   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface NavigationBarMapper {
	
	/**
	 * @Title: queryNavigationBarByType   
	 * @Description:  条件查询导航条信息
	 * @param cityCode 已开通城市行政区域代码
	 * @param type 导航条类型 :  0--头部导航栏  ,1--底部导航栏
	 * @return: List<NavigationBar>
	 */
	List<NavigationBar> queryNavigationBarByType(@Param("cityCode")String cityCode,@Param("type")String type);

	/**
	 * @Description: 查询开通的城市信息   
	 * @return: List<OpenCity>     
	 */ 
	List<OpenCity> queryOpenCitys();
	
	
}
