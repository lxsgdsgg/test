/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerRecruitServiceImpl.java   
 * @Package com.bashiju.www.service.user.impl      
 * @author: zuoyuntao     
 * @date:   2018年8月7日 上午11:04:10   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.service.api.impl.recruit;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.wapi.ICustomerRecruitServiceApi;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.input.recruit.CustomerRecruitQueryParam;
import com.bashiju.www.pojo.service.out.recruit.CustCurriculumVitaeEntity;
import com.bashiju.www.pojo.service.out.recruit.CustReleaseRecruitDetailEntity;
import com.bashiju.www.pojo.service.out.recruit.CustReleaseRecruitEntity;
import com.bashiju.www.service.api.impl.usercentral.CustomerPersonalCentralBaseServiceApi;
import com.bashiju.www.service.recruit.ICustomerRecruitService;

/**
 * 公司招聘管理服务
 * @ClassName:CustomerRecruitServiceImpl
 * @Description:公司招聘管理服务
 * @author:zuoyuntao
 * @date:2018年8月7日 上午11:04:10
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class CustomerRecruitServiceApiImpl
		extends CustomerPersonalCentralBaseServiceApi implements ICustomerRecruitServiceApi{
	/**
	 * 加入我们服务接口
	 */
	@Autowired
	private ICustomerRecruitService mICustomerRecruitService;
	/**
	 * <p>Title: queryRecruitPositionDataWithPage</p>   
	 * <p>Description:查询发布岗位信息 </p>   
	 * @param paramEntity 查询参数实体对象
	 * @param page 每页显示最小条数
	 * @param limit 每页显示最大条数
	 * @return   
	 * @see com.bashiju.www.service.recruit.ICustomerRecruitService#queryRecruitPositionDataWithPage(com.bashiju.www.pojo.service.input.recruit.CustomerRecruitQueryParam, int, int)
	 */
	@Override
	public WebPage<CustReleaseRecruitEntity> queryRecruitPositionDataWithPage(CustomerRecruitQueryParam paramEntity,
			int page, int limit) {
		return mICustomerRecruitService.queryRecruitPositionDataWithPage(paramEntity, page, limit);
	}
	
	/**
	 * 查询发布岗位数据信息---小程序使用接口
	 * @Title: queryRecruitPositionDataWithPage
	 * @author: zuoyuntao  
	 * @Description:查询发布岗位数据信息 ---小程序使用接口
	 * @param paramEntity 参数实体类
	 * @return      
	 * List<CustReleaseRecruitEntity> 
	 */
	@Override
	public List<CustReleaseRecruitEntity> queryRecruitPositionDataForApp(CustomerRecruitQueryParam paramEntity){
		return mICustomerRecruitService.queryRecruitPositionDataForApp(paramEntity);
	}
	/**
	 * <p>Title: queryPositionDetailList</p>   
	 * <p>Description:查询岗位详细信息 </p>   
	 * @param positionId 岗位ID
	 * @return   
	 * @see com.bashiju.www.service.recruit.ICustomerRecruitService#queryPositionDetailList(java.lang.String)
	 */
	@Override
	public CustReleaseRecruitDetailEntity queryPositionDetailList(String positionId) {
		return mICustomerRecruitService.queryPositionDetailList(positionId);
	}
	/**
	 * <p>Title: saveResumeInfo</p>   
	 * <p>Description: 保存投递简历信息 </p> 
	 * @Description:保存投递简历信息
	 * @param detailEntity 求职者详细信息     
	 * @see com.bashiju.www.service.recruit.ICustomerRecruitService#updateResumeInfo(byte[], java.lang.String, boolean, com.bashiju.www.pojo.service.out.recruit.CustReleaseRecruitDetailEntity)
	 */
	@Override
	public void saveResumeInfo(CustCurriculumVitaeEntity detailEntity) {
		mICustomerRecruitService.saveResumeInfo(detailEntity);
	}
	/**
	 * <p>Title: getPoliticalOutlookSelect</p>   
	 * <p>Description:获取政治面貌枚举</p>   
	 * @return   
	 * @see com.bashiju.www.service.recruit.ICustomerRecruitService#getPoliticalOutlookSelect()
	 */
	@Override
	public List<Map<String , String>> getPoliticalOutlookSelect() {
		return mICustomerRecruitService.getPoliticalOutlookSelect();
	}
	/**
	 * <p>Title: getNationListSelect</p>   
	 * <p>Description:获取名族枚举 </p>   
	 * @return   
	 * @see com.bashiju.www.service.recruit.ICustomerRecruitService#getNationListSelect()
	 */
	@Override
	public List<Map<String,String>> getNationListSelect() {
		return mICustomerRecruitService.getNationListSelect();
	}
	/**
	 * <p>Title: getEducationListSelect</p>   
	 * <p>Description: 获取学历下拉选择数据 </p>   
	 * @return   
	 * @see com.bashiju.www.service.recruit.ICustomerRecruitService#getEducationListSelect()
	 */
	@Override
	public List<Map<String,Object>> getEducationListSelect(){
		return mICustomerRecruitService.getEducationListSelect();
	}

}
