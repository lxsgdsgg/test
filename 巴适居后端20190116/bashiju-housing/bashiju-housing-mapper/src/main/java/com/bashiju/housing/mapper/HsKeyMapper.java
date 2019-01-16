package com.bashiju.housing.mapper;


import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  HsKeyMapper   
 * @Description:钥匙管理映射接口
 * @author: wangpeng
 * @date:   2018年6月6日 下午5:45:03   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface HsKeyMapper {
		/**
		 * 
		 * @Title: queryHsKeyData   
		 * @Description: 查询钥匙信息 
		 * @param houseStatus 房源状态
		 * @param reciver 收钥人id
		 * @param keyStatus 钥匙状态
		 * @param beginTime 开始时间
		 * @param endTime 结束时间
		 * @param buildingsName 座栋名称
		 * @return      
		 * @return: Page<Map<String,Object>>
		 */
		public Page<Map<String,Object>> queryHsKeyData(	Map<String,Object> paramMap);

		/**
		 * 
		 * @Title: queryHsKeyLog   
		 * @Description: 查询钥匙日志   
		 * @param 钥匙主键
		 * @return      
		 * @return: Page<Map<String,Object>>
		 */
		Page<Map<String,Object>> queryHsKeyLog(@Param("hsKeyId")String hsKeyId);
		
		/**
		 * 
		 * @Title: queryUserById   
		 * @Description: 查找用户  
		 * @return      
		 * @return: Map<String,Object>
		 */
		 Map<String,Object> queryUser(@Param("borrower")String borrower);
}
