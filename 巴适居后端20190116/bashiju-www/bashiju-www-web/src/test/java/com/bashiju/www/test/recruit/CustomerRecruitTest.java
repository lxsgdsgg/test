/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerRecruitTest.java   
 * @Package com.bashiju.www.test.recruit      
 * @author: zuoyuntao     
 * @date:   2018年8月7日 上午11:08:20   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.test.recruit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.bashiju.enums.DocumentTypeEnum;
import com.bashiju.enums.NationEnum;
import com.bashiju.enums.PoliticalOutlookEnum;
import com.bashiju.www.pojo.service.input.recruit.CustomerRecruitQueryParam;
import com.bashiju.www.pojo.service.out.recruit.CustCurriculumVitaeEntity;
import com.bashiju.www.service.recruit.ICustomerRecruitService;
import com.bashiju.www.test.BaseServiceTest;
import com.bashiju.www.tools.WebCommonTools;

/**
 * 招聘管理测试类
 * @ClassName:CustomerRecruitTest
 * @Description:招聘管理测试类
 * @author:zuoyuntao
 * @date:2018年8月7日 上午11:08:20
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public class CustomerRecruitTest extends BaseServiceTest {
	/**
	 * 招聘管理服务接口
	 */
	@Autowired
	private ICustomerRecruitService mICustomerRecruitService;
	@Test
	public void queryPositionDetailList() {
		//查询岗位详细
		mICustomerRecruitService.queryPositionDetailList("1");
		
	}
	@Test
	public void queryRecruitPositionDataWithPage() {
		//查询岗位
		CustomerRecruitQueryParam paramEntity = new CustomerRecruitQueryParam();
//		paramEntity.setName("测试");
//		paramEntity.setWorkCityCode("530100");
		//查询岗位列表
		mICustomerRecruitService.queryRecruitPositionDataWithPage(paramEntity, 0, 10);
	}
	@Test
	public void getEducationListSelect() {
		//获取学历信息
		mICustomerRecruitService.getEducationListSelect();
	}
	
	@Test
	public void getNationListSelect() {
		//获取名族信息
		mICustomerRecruitService.getNationListSelect();
	}
	
	@Test
	public void getPoliticalOutlookSelect() {
		//获取政治面貌信息
		mICustomerRecruitService.getPoliticalOutlookSelect();
	}
	
	@Test
	public void saveResumeInfo() {
		//投递简历
		CustCurriculumVitaeEntity detailEntity = new CustCurriculumVitaeEntity();
		detailEntity.setAddress("置信银河广场B座1102室");
		detailEntity.setAttachment("测试");
		detailEntity.setBirthdate(WebCommonTools.formatDate("yyyy-MM-dd"));
		detailEntity.setDocCode("530101199805086324");
		detailEntity.setDocType(DocumentTypeEnum.IdentifyType.getCode());
		detailEntity.setEmail("88888888@163.com");
		detailEntity.setGraduatDate(WebCommonTools.formatDate("yyyy-MM-dd"));
		detailEntity.setHouseholdRegister("昆明市呈贡片区春融街置信银河广场");
		detailEntity.setIsMarry(0);
		detailEntity.setMobile("13677798556");
		detailEntity.setName("阿三");
		detailEntity.setNationalities(NationEnum.OtherEthnicMinority.getCode());
		detailEntity.setPoliticalOrientation(PoliticalOutlookEnum.MemberCPOC.getCode());
		detailEntity.setRecruitInfoId(1);
		detailEntity.setSex(0);
		detailEntity.setWorkDate(WebCommonTools.formatDate("yyyy-MM-dd"));
		
		//保存投递简历
		int type = 1;
		MultipartFile file = null;
		boolean isWatermark = false;
		boolean isImport = false;
		mICustomerRecruitService.saveResumeInfo(file,type,isWatermark,isImport,detailEntity);
	}

}
