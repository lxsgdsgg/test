package com.bashiju.customer.pojo;
/**
 * 
 * @ClassName:  CommunityQueryEntity   
 * @Description:TODO(小区列表查询参数类)   
 * @author: wangkaifa
 * @date:   2018年4月23日 上午10:36:34   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class CommunityQueryEntity {
	/**片区名称 **/
	private String regionName;
	/**小区名称（支持拼音） **/
	private String pinyin;
	/**热门小区 **/
	private Integer hot;
	/**行政区划码 **/
	private String areaCode;
	/**是否标注 **/
	private Integer biaozhu;
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public Integer getHot() {
		return hot;
	}
	public void setHot(Integer hot) {
		this.hot = hot;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public Integer getBiaozhu() {
		return biaozhu;
	}
	public void setBiaozhu(Integer biaozhu) {
		this.biaozhu = biaozhu;
	}
	@Override
	public String toString() {
		return "CommunityQueryEntity [regionName=" + regionName + ", pinyin=" + pinyin + ", hot=" + hot + ", areaCode="
				+ areaCode + ", biaozhu=" + biaozhu + "]";
	}
	
}
