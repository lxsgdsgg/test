package com.bashiju.wutils.util;

import org.apache.commons.codec.digest.DigestUtils;
/**
 * Md5加密工具
 * @ClassName:  Md5EncryptUtils   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: zhaobin
 * @date:   2018年7月26日 上午11:28:09   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class Md5EncryptUtils {

	public static String md5Encrypt(String str) {
		return DigestUtils.md5Hex(str);
	}
}
