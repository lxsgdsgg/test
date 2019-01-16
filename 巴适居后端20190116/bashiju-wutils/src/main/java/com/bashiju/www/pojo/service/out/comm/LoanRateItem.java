package com.bashiju.www.pojo.service.out.comm;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 *   贷款利率子项
 * @ClassName:  LoanRateItem   
 * @Description:   贷款利率子项
 * @author: wangkaifa
 * @date:   2018年8月30日 下午2:07:31       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class LoanRateItem implements Serializable {

	private static final long serialVersionUID = 1329794243947396884L;
	/**值**/
	private String value;
	/**名称**/
	private String name;

}
