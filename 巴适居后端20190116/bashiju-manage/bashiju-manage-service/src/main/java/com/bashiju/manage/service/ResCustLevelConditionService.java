package com.bashiju.manage.service;

import java.util.Map;

import com.github.pagehelper.Page;

/**
 * 
 * @ClassName:  ResCustLevelConditionService   
 * @Description:客源等级条件接口
 * @author: wangpeng
 * @date:   2018年5月18日 下午5:37:07   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface ResCustLevelConditionService {
	/**
	 * @Title: queryResCustLevelCondition   
	 * @Description: 查询客源等级条件信息（分页）
	 * @param transcationType 交易类型
	 * @param levelType 等级
	 * @param companyId 公司id
	 * @param pageNum 当前页数
	 * @param PageSize 每页总条数
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String,Object>>  queryResCustLevelCondition(Map<String,Object> paramMap,int pageNum,int pageSize);
	/**
	 * @Title: queryResCustLevelConditionById   
	 * @Description: 通过id查询客源等级条件信息
	 * @param id 客源等级条件Id
	 * @return: Map<String,Object>
	 */
	Map<String,Object>  queryResCustLevelConditionById(String id);
	
	/**
	 * @Title: saveOrUpdateResCustLevelCcondition   
	 * @Description:编辑或保存客源等级条件
	 * @param map
	 * @return: boolean
	 */
	public boolean saveOrUpdateResCustLevelCcondition(Map<String,Object>map);
	/**
	 * @Title: delResCustLevelCondition   
	 * @Description: 逻辑删除客源等级条件
	 * @param id  客源等级条件id
	 * @return: boolean
	 */
	public boolean delResCustLevelCondition(String id);
	
}
