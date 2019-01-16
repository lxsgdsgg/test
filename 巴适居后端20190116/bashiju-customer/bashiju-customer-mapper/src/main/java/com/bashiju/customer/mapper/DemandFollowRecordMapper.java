package com.bashiju.customer.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.dubbo.config.support.Parameter;
import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  DemandFollowRecordMapper   
 * @Description:客源跟进记录映射接口
 * @author: wangpeng
 * @date:   2018年6月12日 下午4:00:02   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface DemandFollowRecordMapper {
	/**
	 * 
	 * @Title: queryDemandFollowRecordDat   
	 * @Description: 条件查询客源带看记录
	 * @param transactionType 交易类型
	 * @param followTypeName 跟进方式名称
	 * @param followType 跟进方式
	 * @param followerId 跟进人id
	 * @param custIdAndFollowfollower 
	 * @param operatorId 操作人id
	 * @param beginTime 开始时间	
	 * @param endTime 结束时间
	 * @return      
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> queryDemandFollowRecordData(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: queryDemandFollowRecordByDemandId   
	 * @Description: 条件查询客源带看记录
	 * @param demandId 需求编号
	 * @return      
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> queryDemandFollowRecordByDemandId(@Param("demandId")String demandId);
}
