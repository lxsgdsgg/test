/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  BackGroundCallRecordServiceImpl.java   
 * @Package com.bashiju.manage.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2019年1月4日 上午10:18:18   
 * @version V1.0 
 * @Copyright: 2019 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.MenuEnum;
import com.bashiju.manage.mapper.BackGroundCallRecordMapper;
import com.bashiju.manage.service.BackGroundCallRecordService;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**  
 * 后台通话记录服务 
 * @ClassName:  BackGroundCallRecordServiceImpl   
 * @Description:后台通话记录服务
 * @author: wangpeng
 * @date:   2019年1月4日 上午10:18:18   
 * @Copyright: 2019 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class BackGroundCallRecordServiceImpl extends CommonSqlServie implements BackGroundCallRecordService {
	
	@Autowired
	private BackGroundCallRecordMapper backGroundCallRecordMapper;
	@Autowired
	private DataAuthHelper dataAuthHelper;
	@Override
	public Page<Map<String, Object>> queryBackGroundCallRecord(Map<String, Object> paramMap,int page,int limit) {
		PageHelper.startPage(page, limit);
		dataAuthHelper.auth(MenuEnum.MENU_77.getCode(), UserThreadLocal.get().get("id").toString());
		Page<Map<String, Object>> map = backGroundCallRecordMapper.queryBackGroundCallRecord(paramMap);
		return map;
	}

	
	@Override
	public boolean delBackGroundCallRecord(String id) {
		return false;
	}

}
