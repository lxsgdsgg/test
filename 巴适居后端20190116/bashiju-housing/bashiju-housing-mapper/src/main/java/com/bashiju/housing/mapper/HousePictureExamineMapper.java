/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  HousePictureExamineMapper.java   
 * @Package com.bashiju.housing.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年6月7日 上午9:31:45   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.housing.mapper;

import java.util.List;
import java.util.Map;

/**   
 * @ClassName:  HousePictureExamineMapper   
 * @Description:房源图片审核服务持久层   
 * @author: yangz
 * @date:   2018年6月7日 上午9:31:45   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface HousePictureExamineMapper {

	/**
	 * 
	 * @Description: 条件查询申请信息   
	 * @param params 要查询的条件
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryHousePictureExamines(Map<String,Object> params);
}
