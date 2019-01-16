package com.bashiju.www.pojo.service.out.comm;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 
 *   贷款利率对象
 * @ClassName:  贷款利率对象  
 * @Description:   （详细描述）
 * @author: wangkaifa
 * @date:   2018年8月30日 下午2:05:41       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class LoanRateResult implements Serializable {

	private static final long serialVersionUID = -2873462636316044300L;
	/**商业贷款利率**/
	private List<LoanRateItem> businessRates;
	/**公积金贷款利率**/
	private List<LoanRateItem> publicFundRates;
	/**折扣**/
	private List<LoanRateItem> rebates;
	/**公积金贷款上限万元**/
	private Integer publicLoanCeiling;
	/**商业贷款最大比例**/
	private Integer LoanRatioCeiling;
	
}
