package com.bashiju.base.service.mq;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bashiju.utils.service.CommonSqlServie;

/**   
 * @ClassName  SaveLog   
 * @Description 日志保存
 * @author zhaobin
 * @date   2018年5月9日 上午10:56:32   
 *     
 * @Copyright 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class SaveLog extends CommonSqlServie {
	
	private Logger logger =LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
	private void saveUserLog(Map<String,Object> map) {
		Long res=this.commonOperationDatabase(map, "user_log",true);
		logger.warn("队列生效，日志记录成功，返回结果是{}",res);
	}
	 
	  @SuppressWarnings("unused")
	private void saveErrorLog(Map<String,Object> map) {
		Long res=this.commonOperationDatabase(map, "error_log",true);
		logger.warn("队列生效，错误日志记录成功，返回结果是{}",res);
	}
}
