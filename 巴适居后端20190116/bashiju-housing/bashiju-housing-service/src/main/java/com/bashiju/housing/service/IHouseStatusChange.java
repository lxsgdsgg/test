package com.bashiju.housing.service;

/**   
 * @ClassName  IStatusChange   
 * @DescriptionTODO(这里用一句话描述这个类的作用)   
 * @author zhaobin
 * @date   2018年5月30日 下午2:43:59   
 *     
 * @Copyright 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface IHouseStatusChange {

	/**
	 * 房源状态改变的方法
	 * @Title housingStatusChange   
	 * @Description    
	 * @param  map （包括公司ID;淘宝状态变更后的值，盘状态变更后的值，房源状态变更后的值,房源ID, ）      
	 * @return void
	 */
	public boolean erhousingAutoStatusChange(String companyId);
}
