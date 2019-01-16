/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  FootPrintMapper.java   
 * @Package com.bashiju.www.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年8月11日 下午4:00:28   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.mapper;

import org.apache.ibatis.annotations.Param;

import com.bashiju.www.pojo.service.FootPrint.FootPrintToQueryEntity;
import com.bashiju.www.pojo.service.FootPrint.FootPrintToSaveEntity;
import com.github.pagehelper.Page;

/**   
 * @ClassName:  FootPrintMapper   
 * @Description:我的足迹映射接口
 * @author: wangpeng
 * @date:   2018年8月11日 下午4:00:28   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface FootPrintMapper {
	/**
	 * @Title: queryFootPrintByCustId   
	 * @Description: 条件查询我的足迹
	 * @param custId 用户id
	 * @param type 房源类型
	 * @return: Page<FootPrintEntity>
	 */
	Page<FootPrintToQueryEntity> queryFootPrintByCustId(@Param("custId")String custId,@Param("type")String type);
	/**
	 * @Title: saveFootPrint   
	 * @Description: 新增我的足迹  
	 * @param footPrintToSaveEntity
	 * @return: long
	 */
	long saveFootPrint(FootPrintToSaveEntity footPrintToSaveEntity);
}
