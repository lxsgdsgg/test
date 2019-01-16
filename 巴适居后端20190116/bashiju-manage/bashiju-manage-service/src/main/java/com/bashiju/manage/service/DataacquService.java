package com.bashiju.manage.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**
 * 
 * @ClassName:  DataacquService   
 * @Description:TODO(数据采集配置接口)   
 * @author: liwen
 * @date:   2018年7月3日 上午11:22:07   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface DataacquService {
	public Long saveAnalysisTemplet(Map<String,Object> data);
	public Page<Map<String, Object>> queryAnalysisTemplet(int page,int limit,Map<String,Object> conditions);
	public boolean delAnalysisTemplet(Long id);
	public List<Map<String,Object>> queryTempletName();
	public Long saveAddressConf(Map<String,Object> data);
	public Page<Map<String, Object>> queryAddressConf(int page,int limit,Map<String,Object> conditions);
	public boolean delAddressConf(Long id);
	public Long saveJobConf(Map<String,Object> data);
	public Page<Map<String, Object>> queryJobConf(int page,int limit);
	public boolean delJobConf(Long id);
	public Long openOrStopJobConf(Long id,String status);
}

