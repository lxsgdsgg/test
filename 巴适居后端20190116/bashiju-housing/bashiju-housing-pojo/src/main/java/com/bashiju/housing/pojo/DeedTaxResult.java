package com.bashiju.housing.pojo;

import java.io.Serializable;

/**
 * 
 *  首付契税比例
 * @ClassName:  DeedTaxResult   
 * @Description:  首付契税比例
 * @author: wangkaifa
 * @date:   2018年9月7日 上午10:24:24       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class DeedTaxResult implements Serializable {
	private static final long serialVersionUID = -3063645163420872630L;
	/**是否满两年0否,1是**/
	private Integer isTwoYear;
	/**是否首套0否,1是**/
	private Integer isFirst;
	/**是否二套0否,1是**/
	private Integer isTwo;
	/**是否三套0否,1是**/
	private Integer isThree;
	/**是否90㎡以上0否,1是**/
	private Integer isLimitAreaAbove;
	/**税率**/
	private Float taxRate;
	public Integer getIsTwoYear() {
		return isTwoYear;
	}
	public void setIsTwoYear(Integer isTwoYear) {
		this.isTwoYear = isTwoYear;
	}
	public Integer getIsFirst() {
		return isFirst;
	}
	public void setIsFirst(Integer isFirst) {
		this.isFirst = isFirst;
	}
	public Integer getIsTwo() {
		return isTwo;
	}
	public void setIsTwo(Integer isTwo) {
		this.isTwo = isTwo;
	}
	public Integer getIsThree() {
		return isThree;
	}
	public void setIsThree(Integer isThree) {
		this.isThree = isThree;
	}
	public Integer getIsLimitAreaAbove() {
		return isLimitAreaAbove;
	}
	public void setIsLimitAreaAbove(Integer isLimitAreaAbove) {
		this.isLimitAreaAbove = isLimitAreaAbove;
	}
	public Float getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(Float taxRate) {
		this.taxRate = taxRate;
	}
	@Override
	public String toString() {
		return "DeedTaxResult [isTwoYear=" + isTwoYear + ", isFirst=" + isFirst + ", isTwo=" + isTwo + ", isThree="
				+ isThree + ", isLimitAreaAbove=" + isLimitAreaAbove + ", taxRate=" + taxRate + "]";
	}
}
