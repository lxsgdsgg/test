package com.bashiju.www.service.FootPrint;

import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.FootPrint.FootPrintToQueryEntity;
import com.bashiju.www.pojo.service.FootPrint.FootPrintToSaveEntity;

/**   
 * @ClassName:  FootPrintService   
 * @Description:我的足迹服务
 * @author: wangpeng 
 * @date:   2018年8月11日 下午4:13:34   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface FootPrintService {
	/**
	 * @Title: queryFootPrintByCustId   
	 * @Description: 条件查询我的足迹   
	 * @param key 用户登录后的返回key
     * @param type 房源类型
	 * @return: WebPage<FootPrintEntity>
	 */
	WebPage<FootPrintToQueryEntity> queryFootPrintByCustId (String key,String type,int page,int limit);
	
	
	/**
	 * @Title: saveFootPrint   
	 * @Description: 新增我的足迹  
	 * @param footPrintToSaveEntity
	 * @return: boolean
	 */
	boolean  saveFootPrint(FootPrintToSaveEntity footPrintToSaveEntity);
	
	/**
	 * @Title: queryFootPrintCount   
	 * @Description: 条件查询用户足迹数量
	 * @param key 用户登录后的返回key
	 * @return: FootPrintToQueryEntity
	 */
	FootPrintToQueryEntity queryFootPrintCountByCustId(String key);
	
	/**
	 * 删除我的足迹 
	 * @Title: deleteFootPrint   
	 * @Description: 删除我的足迹 
	 * @param id 我的足迹编号
	 * @return: long
	 */
	boolean  deleteFootPrint(String id);
}
