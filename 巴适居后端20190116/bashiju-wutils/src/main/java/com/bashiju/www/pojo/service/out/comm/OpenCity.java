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

import java.io.Serializable;

import lombok.Data;

/**   平台开通的城市信息 
 * @ClassName:  OpenCity   
 * @Description:平台开通的城市信息   
 * @author: yangz
 * @date:   2018年8月1日 上午11:01:10   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class OpenCity implements Serializable {

	private static final long serialVersionUID = 1804341938400211793L;
	/**
	 * 城市行政编码
	 */
	private String cityCode;
	/**
	 * 城市名称
	 */
	private String cityName;
	/**
	 * 城市第一个拼音字母
	 */
	private String pinyin;
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
	
	/**
	 * 纬度
	 */
	private String latitude;
	
	/**
	 * 经度
	 */
	private String longitude;
	/**
	 * 是否热门城市:0否,1是
	 */
	private int isHot;
}
