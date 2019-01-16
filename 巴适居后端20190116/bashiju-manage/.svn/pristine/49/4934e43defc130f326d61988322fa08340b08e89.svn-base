/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  TestDataServiceImpl.java   
 * @Package com.bashiju.manage.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年4月10日 下午6:38:04   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.manage.mapper.TestDataMapper;
import com.bashiju.manage.service.TestDataService;

/**   
 * @ClassName:  TestDataServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: yangz
 * @date:   2018年4月10日 下午6:38:04   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class TestDataServiceImpl implements TestDataService {

	@Autowired
	private TestDataMapper testDataMapper;


	@Override
	public List<Map<String, Object>> queryDataList(String userId) throws Exception {
		return testDataMapper.queryDataList();
	}

}
