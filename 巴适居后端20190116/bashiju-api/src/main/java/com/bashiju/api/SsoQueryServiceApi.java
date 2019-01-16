package com.bashiju.api;

import java.util.Map;

/**
 * 
 * @ClassName:  SsoQueryServiceApi   
 * @Description:Sso查询接口服务   
 * @author: zhaobin
 * @date:   2018年4月9日 上午10:29:01   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface SsoQueryServiceApi {

	public Map<String,Object> queryUserByToken(String token);
}
