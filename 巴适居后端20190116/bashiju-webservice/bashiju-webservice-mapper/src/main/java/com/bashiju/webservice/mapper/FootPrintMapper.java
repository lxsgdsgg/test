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

package com.bashiju.webservice.mapper;

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
//	/**
//	 * 条件查询用户足迹
//	 * @Title: queryFootPrintByCustId   
//	 * @Description: 条件查询用户足迹
//	 * @param custId 用户id
//	 * @param type 房源类型
//	 * @return: Page<FootPrintEntity>
//	 */
//	Page<FootPrintToQueryEntity> queryFootPrintByCustId(@Param("custId")String custId,@Param("type")String type);
	
	/**
	 * 条件查询用户浏览出售足迹   
	 * @Title: querySaleFootPrintByCustId   
	 * @Description: 条件查询用户浏览出售足迹   
	 * @param custId 用户id
	 * @param type 房源类型
	 * @return: Page<FootPrintToQueryEntity>
	 */
	Page<FootPrintToQueryEntity> querySaleFootPrintByCustId(@Param("custId")String custId);
	
	/**
	 * 条件查询用户浏览出租足迹    
	 * @Title: querySaleFootPrintByCustId   
	 * @Description: 条件查询用户浏览出租足迹   
	 * @param custId 用户id
	 * @param type 房源类型
	 * @return: Page<FootPrintToQueryEntity>
	 */
	Page<FootPrintToQueryEntity> queryRentFootPrintByCustId(@Param("custId")String custId);
	
	/**
	 * 
	 * @Title: queryNewHouseFootPrintByCustId   
	 * @Description:条件查询用户新房足迹
	 * @param custId 用户id
	 * @param type 房源类型
	 * @return: Page<FootPrintToQueryEntity>
	 */
	Page<FootPrintToQueryEntity> queryNewHouseFootPrintByCustId(@Param("custId")String custId);
	/**
	 *  新增用户足迹
	 * @Title: saveFootPrint   
	 * @Description: 新增用户足迹  
	 * @param footPrintToSaveEntity
	 * @return: long
	 */
	long saveFootPrint(FootPrintToSaveEntity footPrintToSaveEntity);
	/**
	 *  条件查询用户足迹数量
	 * @Title: queryFootPrintCountByCustId   
	 * @Description: 条件查询用户足迹数量
	 * @param custId 经纪人id
	 * @return: FootPrintToQueryEntity
	 */
	FootPrintToQueryEntity queryFootPrintCountByCustId(@Param("custId")String custId);
	
	/**
	 * 条件删除我的足迹
	 * @Title: deleteFootPrint   
	 * @Description: 条件删除我的足迹
	 * @param id 我的足迹编号
	 * @return: long
	 */
	long deleteFootPrint(@Param("id")String id);
	/**
	 * 查询足迹是否已存在
	 * @Title: queryIsExist   
	 * @Description: 查询足迹是否已存在 
	 * @param custId 用户编号
	 * @param type 足迹类型
	 * @param sourceId 房源编号
	 * @return: long
	 */
	long queryIsExist(@Param("custId")String custId,@Param("type")String type,@Param("sourceId")String sourceId);
}
