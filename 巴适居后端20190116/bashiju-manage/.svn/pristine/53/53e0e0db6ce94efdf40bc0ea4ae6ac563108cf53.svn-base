/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ExcelMapper.java   
 * @Package com.bashiju.manage.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年12月3日 下午4:55:03   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.mapper;

import java.util.List;

import com.bashiju.manage.ExportModelBean.TempCommunity;

/**   
 * @ClassName:  ExcelMapper   
 * @Description:excel导出管理持久层  
 * @author: yangz
 * @date:   2018年12月3日 下午4:55:03   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface ExcelMapper {

	/**
	 * @Description:批量导入小区信息   
	 * @param list
	 * @return: int
	 */
	public int batchExportCommunityInfo(List<TempCommunity> list);

	/**     
	 * @Description: 将小区临时表中的信息写入小区表，二手小区表中        
	 * @return: String      
	 */ 
	public String callBatchExportCommunity();
}
