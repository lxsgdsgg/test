/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  BackGroundCallRecordService.java   
 * @Package com.bashiju.manage.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2019年1月4日 上午10:14:34   
 * @version V1.0 
 * @Copyright: 2019 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.service;

import java.util.Map;

import com.github.pagehelper.Page;

/**   
 * 后台通话记录接口
 * @ClassName:  BackGroundCallRecordService   
 * @Description:后台通话记录接口  
 * @author: wangpeng
 * @date:   2019年1月4日 上午10:14:34   
 * @Copyright: 2019 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface BackGroundCallRecordService {
	/**
	 * 条件查询后台通话记录
	 * @Title: queryBackGroundCallRecord   
	 * @Description: 条件查询后台通话记录
	 * @param paramMap
	 * @return: Page<Map<String,Object>>
	 */
	 Page<Map<String,Object>> queryBackGroundCallRecord(Map<String,Object> paramMap,int page,int limit);
	 
	 /**
	  * 逻辑删除后台通话记录
	  * @Title: delBackGroundCallRecord   
	  * @Description: 逻辑删除后台通话记录
	  * @param id 跟进记录编号
	  * @return: boolean
	  */
	 boolean delBackGroundCallRecord(String id);
}
