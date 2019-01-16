/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ExcelServiceImpl.java   
 * @Package com.bashiju.manage.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年12月3日 下午4:52:23   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.bashiju.manage.ExportModelBean.TempCommunity;
import com.bashiju.manage.mapper.ExcelMapper;
import com.bashiju.manage.service.ExcelService;
import com.bashiju.utils.exception.BusinessException;

/**   
 * @ClassName:  ExcelServiceImpl   
 * @Description:excel导入管理服务  
 * @author: yangz
 * @date:   2018年12月3日 下午4:52:23   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class ExcelServiceImpl implements ExcelService {
	
	@Autowired
	private ExcelMapper excelMapper;

	/**
	 * @Description:批量导入小区信息   
	 * @param list
	 * @return: boolean
	 * @see com.bashiju.manage.service.ExcelService#saveBatchExportCommunityInfo(java.util.List)   
	 */
	@Override
	public boolean saveBatchExportCommunityInfo(List<TempCommunity> list) {
		if(list!=null && list.size()>0) {
			excelMapper.batchExportCommunityInfo(list);
			//导入临时表后，通过存储过程将临时表中的数据分别写入小区表，二手小区表中
			String str = excelMapper.callBatchExportCommunity();
			if(StringUtils.isEmpty(str))//当异常时，将返回异常信息
				return true;
			else
				throw new BusinessException(str);
		}
			
		return false;
	}

}
