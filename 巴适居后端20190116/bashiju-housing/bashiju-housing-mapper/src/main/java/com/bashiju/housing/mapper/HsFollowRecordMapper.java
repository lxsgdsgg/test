package com.bashiju.housing.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  HsFollowRecordMapper   
 * @Description:房源跟进映射接口
 * @author: wangpeng
 * @date:   2018年6月5日 下午3:10:27   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface HsFollowRecordMapper {
		/**
		 * 
		 * @Title: queryHsFollowRecord   
		 * @Description: TODO(条件查询所有房源跟进记录)   
		 * @param paramMap 查询参数
		 * @return      
		 * @return: Page<Map<String,Object>>
		 */
		Page<Map<String,Object>> queryHsFollowRecord(Map<String,Object> paramMap);
		
		
		/**
		 * 
		 * @Title: queryAllHsFollowRecordByHouseId   
		 * @Description: 条件查询房源回访周期
		 * @param houseId 房源编号
		 * @return      
		 * @return: Page<Map<String,Object>>
		 */
		Page<Map<String,Object>> queryAllHsFollowRecordByHouseId(@Param("houseId")String houseId);
}
