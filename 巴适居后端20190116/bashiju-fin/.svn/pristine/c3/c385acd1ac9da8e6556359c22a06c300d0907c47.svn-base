package com.bashiju.fin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

/**
 * 
 * @ClassName:  FinMapper   
 * @Description:TODO(财务平台)   
 * @author: liwen
 * @date:   2018年6月27日 上午9:29:08   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface FinMapper {
	public Page<Map<String, Object>> queryCommissionList(Map<String, Object> conditions);
	public Page<Map<String, Object>> queryPayRecord(Map<String, Object> conditions);
	public List<Map<String,Object>>queryFinanceDivide(Long commissionId);
	public Page<Map<String, Object>> queryWagesLog(Map<String, Object> conditions);
	public Page<Map<String, Object>> queryConfigLog(Map<String, Object> conditions);
	public Page<Map<String, Object>> queryCommissionLog(Map<String, Object> conditions);
	public Page<Map<String, Object>> queryFinanceLog(Map<String, Object> conditions);
	public Page<Map<String, Object>> queryReceiveRecordLog(Map<String, Object> conditions);
	public Page<Map<String, Object>> queryPayRecordLog(Map<String, Object> conditions);
}
