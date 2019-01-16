package com.bashiju.housing.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 *   住宅首付预算，房源类型下拉对象
 * @ClassName:  HousePropertyResult   
 * @Description:   首付预算，房源类型下拉对象
 * @author: wangkaifa
 * @date:   2018年9月7日 上午10:14:39       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class HousePropertyResult implements Serializable {
	private static final long serialVersionUID = -9117617599034868659L;
	/**房源类型id**/
	private Integer id;
	/**房源类型名称**/
	private String name;
	/**住宅、非住宅类型：1住宅，0非住宅**/
	private Integer type;
	/**增值税比例**/
	private Float addedValueTax;
	/**个人所得税比例**/
	private Float incomeTax;
	/**土地出让金比例**/
	private Float landTtransferTax;
	/**首付预算其他项收费比例**/
	private Float agencyFeeRatio;
	/**住宅契税比例**/
	private List<DeedTaxResult> deedTaxs;
	/**非住宅契税比例**/
	private Float businessDeedTax;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Float getAddedValueTax() {
		return addedValueTax;
	}
	public void setAddedValueTax(Float addedValueTax) {
		this.addedValueTax = addedValueTax;
	}
	public Float getIncomeTax() {
		return incomeTax;
	}
	public void setIncomeTax(Float incomeTax) {
		this.incomeTax = incomeTax;
	}
	public Float getLandTtransferTax() {
		return landTtransferTax;
	}
	public void setLandTtransferTax(Float landTtransferTax) {
		this.landTtransferTax = landTtransferTax;
	}
	public Float getAgencyFeeRatio() {
		return agencyFeeRatio;
	}
	public void setAgencyFeeRatio(Float agencyFeeRatio) {
		this.agencyFeeRatio = agencyFeeRatio;
	}
	public List<DeedTaxResult> getDeedTaxs() {
		return deedTaxs;
	}
	public void setDeedTaxs(List<DeedTaxResult> deedTaxs) {
		this.deedTaxs = deedTaxs;
	}
	public Float getBusinessDeedTax() {
		return businessDeedTax;
	}
	public void setBusinessDeedTax(Float businessDeedTax) {
		this.businessDeedTax = businessDeedTax;
	}
	@Override
	public String toString() {
		return "HousePropertyResult [id=" + id + ", name=" + name + ", type=" + type + ", addedValueTax="
				+ addedValueTax + ", incomeTax=" + incomeTax + ", landTtransferTax=" + landTtransferTax
				+ ", agencyFeeRatio=" + agencyFeeRatio + ", deedTaxs=" + deedTaxs + ", businessDeedTax="
				+ businessDeedTax + "]";
	}
}
