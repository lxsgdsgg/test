package com.bashiju.housing.pojo;

import java.io.Serializable;
import java.util.List;

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
	private Integer loanRatioCeiling;
	
	public List<LoanRateItem> getBusinessRates() {
		return businessRates;
	}
	public void setBusinessRates(List<LoanRateItem> businessRates) {
		this.businessRates = businessRates;
	}
	public List<LoanRateItem> getPublicFundRates() {
		return publicFundRates;
	}
	public void setPublicFundRates(List<LoanRateItem> publicFundRates) {
		this.publicFundRates = publicFundRates;
	}
	public List<LoanRateItem> getRebates() {
		return rebates;
	}
	public void setRebates(List<LoanRateItem> rebates) {
		this.rebates = rebates;
	}
	public Integer getPublicLoanCeiling() {
		return publicLoanCeiling;
	}
	public void setPublicLoanCeiling(Integer publicLoanCeiling) {
		this.publicLoanCeiling = publicLoanCeiling;
	}
	public Integer getLoanRatioCeiling() {
		return loanRatioCeiling;
	}
	public void setLoanRatioCeiling(Integer loanRatioCeiling) {
		this.loanRatioCeiling = loanRatioCeiling;
	}
	
	@Override
	public String toString() {
		return "LoanRateResult [businessRates=" + businessRates + ", publicFundRates=" + publicFundRates + ", rebates="
				+ rebates + ", publicLoanCeiling=" + publicLoanCeiling + ", LoanRatioCeiling=" + loanRatioCeiling + "]";
	}
}
