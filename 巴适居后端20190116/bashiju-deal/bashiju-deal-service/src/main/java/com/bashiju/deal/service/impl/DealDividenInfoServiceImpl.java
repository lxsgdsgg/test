package com.bashiju.deal.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.deal.mapper.DealDividenInfoMapper;
import com.bashiju.deal.service.DealDividenInfoService;
import com.bashiju.enums.MenuEnum;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * 
 * @ClassName:  DealDividenInfoServiceImpl   
 * @Description:分成明细查询服务   
 * @author: wangpeng
 * @date:   2018年6月29日 下午2:29:54   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
@SystemServiceLog(operationType="分成明细查询服务")
public class DealDividenInfoServiceImpl  implements DealDividenInfoService{
	@Autowired
	private DealDividenInfoMapper dealDividenInfoMapper;
	@Autowired
	private DataAuthHelper dataAuthHelper;
	@Override
	@SystemServiceLog(operationType="分成明细查询")
	public Page<Map<String, Object>> queryDealDividenInfo( Map<String,Object> paramMap,int page,int limit) {
		PageHelper.startPage(page, limit);
		dataAuthHelper.auth(MenuEnum.MENU_108.getCode(),UserThreadLocal.get().get("id").toString());
		Page<Map<String, Object>> map = dealDividenInfoMapper.queryDealDividenInfo(paramMap);
		return map;
	}
}
