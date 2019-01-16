package com.bashiju.www.service.FootPrint;

import com.bashiju.www.pojo.service.FootPrint.FootPrintToQueryEntity;
import com.bashiju.www.pojo.service.FootPrint.FootPrintToSaveEntity;
import com.github.pagehelper.Page;

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
	 * @param custId 用户id
	 * @param custId 房源类型
	 * @return: Page<FootPrintEntity>
	 */
	Page<FootPrintToQueryEntity> queryFootPrintByCustId (String custId,String type);
	
	
	/**
	 * @Title: saveFootPrint   
	 * @Description: 新增我的足迹  
	 * @param footPrintToSaveEntity
	 * @return: boolean
	 */
	boolean  saveFootPrint(FootPrintToSaveEntity footPrintToSaveEntity);
}
