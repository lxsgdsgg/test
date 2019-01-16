/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ExcelService.java   
 * @Package com.bashiju.manage.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年12月3日 下午4:07:31   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.service;

import java.util.List;

import com.bashiju.manage.ExportModelBean.TempCommunity;

/**   
 * @ClassName:  ExcelService   
 * @Description:excel管理服务
 * @author: yangz
 * @date:   2018年12月3日 下午4:07:31   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface ExcelService {
	
	/**
	 * @Description:批量导入小区信息   
	 * @param list
	 * @return: boolean
	 */
	public boolean saveBatchExportCommunityInfo(List<TempCommunity> list);

}
