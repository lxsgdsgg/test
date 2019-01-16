/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerRecruitMapper.java   
 * @Package com.bashiju.webservice.mapper      
 * @author: zuoyuntao     
 * @date:   2018年8月7日 上午11:07:20   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.webservice.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bashiju.www.pojo.service.input.recruit.CustomerRecruitQueryParam;
import com.bashiju.www.pojo.service.out.recruit.CustReleaseRecruitDetailEntity;
import com.bashiju.www.pojo.service.out.recruit.CustReleaseRecruitEntity;
import com.github.pagehelper.Page;

/**
 * 招聘管理持久层接口
 * @ClassName:CustomerRecruitMapper
 * @Description:招聘管理持久层接口
 * @author:zuoyuntao
 * @date:2018年8月7日 上午11:07:20
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface CustomerRecruitMapper {
	/**
	 * 查询系统岗位列表信息
	 * @Title: queryRecruitPositionDataWithPage
	 * @author: zuoyuntao  
	 * @Description:查询系统岗位列表信息
	 * @param paramEntity
	 * @return      
	 * Page<CustomerRecruitQueryParam> 
	 */
	public Page<CustReleaseRecruitEntity> queryRecruitPositionDataWithPage(
			 CustomerRecruitQueryParam paramEntity);
	/**
	 * 查询发布岗位数据信息---小程序使用接口
	 * @Title: queryRecruitPositionDataWithPage
	 * @author: zuoyuntao  
	 * @Description:查询发布岗位数据信息 ---小程序使用接口
	 * @param paramEntity 参数实体类
	 * @return      
	 * List<CustReleaseRecruitEntity> 
	 */
	public List<CustReleaseRecruitEntity> queryRecruitPositionDataForApp(CustomerRecruitQueryParam paramEntity);
	/**
	 * 查询岗位详细
	 * @Title: queryPositionDetailList
	 * @author: zuoyuntao  
	 * @Description:查询岗位详细
	 * @param positionId 岗位ID
	 * @return      
	 * CustReleaseRecruitDetailEntity JSON 格式为：
	 */
	public CustReleaseRecruitDetailEntity queryPositionDetailList(
			@Param("positionId") String positionId);
	/**
	 * 保存投递简历信息
	 * @Title: saveResumeInfo
	 * @author: zuoyuntao  
	 * @Description:保存投递简历信息
	 * @param paraMap 参数对象      
	 * void
	 */
	public void saveResumeInfo(@Param("paraMap") Map<String,Object> paraMap);
}
