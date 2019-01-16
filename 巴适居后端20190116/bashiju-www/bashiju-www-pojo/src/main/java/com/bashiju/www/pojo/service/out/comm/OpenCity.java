/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  OpenCity.java   
 * @Package com.bashiju.www.pojo.service.out.comm   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年8月1日 上午11:01:10   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.www.pojo.service.out.comm;

/**   
 * @ClassName:  OpenCity   
 * @Description:平台开通的城市信息   
 * @author: yangz
 * @date:   2018年8月1日 上午11:01:10   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class OpenCity {

	/**
	 * 城市行政编码
	 */
	private String cityCode;
	/**
	 * 城市名称
	 */
	private String cityName;
	
	/**
	 * 城市所在省级行政编码
	 */
	private String provinceCode;
	
	/**
	 * 城市所在省级行政名称
	 */
	private String provinceName;
	
	/**
	 * 映射地址
	 */
	private String url;

	public OpenCity() {
		super();
	}

	/**  
	 * @Title:  getCityCode <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getCityCode() {
		return cityCode;
	}

	/**  
	 * @Title:  setCityCode <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	/**  
	 * @Title:  getCityName <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getCityName() {
		return cityName;
	}

	/**  
	 * @Title:  setCityName <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**  
	 * @Title:  getProvinceCode <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getProvinceCode() {
		return provinceCode;
	}

	/**  
	 * @Title:  setProvinceCode <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	/**  
	 * @Title:  getProvinceName <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getProvinceName() {
		return provinceName;
	}

	/**  
	 * @Title:  setProvinceName <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	/**  
	 * @Title:  getUrl <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getUrl() {
		return url;
	}

	/**  
	 * @Title:  setUrl <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
