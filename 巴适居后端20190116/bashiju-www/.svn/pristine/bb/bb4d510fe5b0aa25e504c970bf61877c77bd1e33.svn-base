package com.bashiju.www.service.comm.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bashiju.enums.BottomContentEnum;
import com.bashiju.enums.HouseArticleTypeEnum;
import com.bashiju.enums.HousePropertyArticleClasses;
import com.bashiju.enums.LabelTypeEnum;
import com.bashiju.wutils.exception.BusinessException;
import com.bashiju.www.global.WebGlobal;
import com.bashiju.www.mapper.CommMapper;
import com.bashiju.www.mapper.IAreaMapper;
import com.bashiju.www.pojo.comm.HouseTypeEnum;
import com.bashiju.www.pojo.service.agent.Agent;
import com.bashiju.www.pojo.service.input.comm.FeedbackParam;
import com.bashiju.www.pojo.service.out.article.ArticleInfoResult;
import com.bashiju.www.pojo.service.out.comm.AreaResult;
import com.bashiju.www.pojo.service.out.comm.ContactUsResult;
import com.bashiju.www.pojo.service.out.comm.LabelResult;
import com.bashiju.www.pojo.service.out.comm.NavigationBar;
import com.bashiju.www.pojo.service.out.comm.WebMapList;
import com.bashiju.www.pojo.service.out.comm.WebMapOnelevelType;
import com.bashiju.www.pojo.service.out.comm.WebMapSecondlevelType;
import com.bashiju.www.pojo.service.out.comm.WebProtocolResult;
import com.bashiju.www.service.comm.ICacheService;
import com.bashiju.www.service.comm.ICommService;
import com.bashiju.www.service.navigationbar.NavigationBarService;
import com.github.pagehelper.util.StringUtil;
/**
 * 
 *   公共服务类
 * @ClassName:  CommServiceImpl   
 * @Description:  公共服务类
 * @author: wangkaifa
 * @date:   2018年7月27日 下午4:33:35       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class CommServiceImpl implements ICommService {
	@Autowired
	CommMapper commMapper;
	@Autowired
	NavigationBarService navigationBarService;
	@Autowired
	ICacheService cacheService;
	
	@Override
	public boolean addAttention(int custId, String sourceId, int type) {
		int res = commMapper.saveAttention(custId,sourceId,type);
		if (HouseTypeEnum.SECONDHOUSE.getCode().equals(type+"")||HouseTypeEnum.RENTHOUSE.getCode().equals(type+"")) {
			long houseId=Long.parseLong(sourceId);
			commMapper.addAttentionCount(houseId);
		}
		if(res==1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean cancelAttention(int custId, String sourceId, int type) {
		boolean res = commMapper.delAttention(custId, sourceId, type);
		if (HouseTypeEnum.SECONDHOUSE.getCode().equals(type+"")||HouseTypeEnum.RENTHOUSE.getCode().equals(type+"")) {
			long houseId=Long.parseLong(sourceId);
			commMapper.minusAttentionCount(houseId);
		}
		return res;
	}

	@Override
	public boolean isAttention(int custId, String sourceId, int type) {
		int res = commMapper.isAttention(custId, sourceId, type);
		if(res>0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean addFeedback(FeedbackParam param) {
		int res = commMapper.addFeedback(param);
		if(res==1) {
			return true;
		}
		return false;
	}

	/**
	 * @Description:根据小区编号查询出小区中最优的4个经纪人  
	 * @param communityCode 小区编号    
	 * @return: List<Agent>
	 * @see com.bashiju.www.service.comm.ICommService#queryCommunityBestAgent(java.lang.String)   
	 */
	@Override
	public List<Agent> queryCommunityBestAgent(String communityCode) {
		if(StringUtils.isEmpty(communityCode))
			throw new BusinessException("小区编号不允许为空");
		return this.commMapper.queryCommunityBestAgent(communityCode);
	}

	@Override
	public WebProtocolResult queryWebProtocol(BottomContentEnum bottomContentEnum) {
		WebProtocolResult info = null;	
		List<WebProtocolResult> infoList = commMapper.queryWebProtocol(Integer.parseInt(bottomContentEnum.getCode()));
		if(infoList.size()>0) {
			info = infoList.get(0);
		}
			
		return info;
	}

	@Override
	public List<ContactUsResult> queryContactUs() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<WebMapList> queryWebMap(String cityCode) {
		String key = WebGlobal.WEBMAP_INFO_REDIS_PREFIX+cityCode;
		String WebMapStr = cacheService.getCache(key);
		List<WebMapList> webMapList = new ArrayList<>();
		if(StringUtil.isEmpty(WebMapStr)) {
			List<WebMapOnelevelType> onelevelType = commMapper.queryAreas(cityCode);
			List<WebMapSecondlevelType> secondlevelType = commMapper.queryRegions(cityCode); 
			//资讯
			List<WebMapOnelevelType> articleOnelevelType1 = commMapper.queryArticleOneTypes(cityCode,HouseArticleTypeEnum.PROPERTYINFORMATION.getCode(),HousePropertyArticleClasses.Level1.getCode());
			//List<WebMapSecondlevelType> articleSecondlevelType1 = commMapper.queryArticleLastTypes(cityCode,HouseArticleTypeEnum.PROPERTYINFORMATION.getCode(),HousePropertyArticleClasses.Level2.getCode());
			//百科
			List<WebMapOnelevelType> articleOnelevelType2 = commMapper.queryArticleOneTypes(cityCode,HouseArticleTypeEnum.PROPERTYENCYCLOPEDIA.getCode(),HousePropertyArticleClasses.Level1.getCode());
			List<WebMapSecondlevelType> articleSecondlevelType2 = commMapper.queryArticleLastTypes(cityCode,HouseArticleTypeEnum.PROPERTYENCYCLOPEDIA.getCode(),HousePropertyArticleClasses.Level3.getCode());
			
			List<NavigationBar> navigationBar = navigationBarService.queryHeadNavigationBar(cityCode);
			for(NavigationBar nav : navigationBar) {
				String navigateCode = nav.getNavigateCode();
				String name = nav.getName();
				WebMapList webMap = new WebMapList();
				if(navigateCode.equals(WebGlobal.PROPERTYENCYCLOPEDIA)) {//百科
					webMap.setName(name);
					webMap.setOnelevelType(articleOnelevelType2);
					webMap.setSecondlevelType(articleSecondlevelType2);
					webMapList.add(webMap);
				}else if(navigateCode.equals(WebGlobal.PROPERTYINFORMATION)) {//资讯
					webMap.setName(name);
					webMap.setOnelevelType(articleOnelevelType1); 
					webMap.setSecondlevelType(null);//资讯只有一级类
					webMapList.add(webMap);
				}else {
					webMap.setName(name);
					webMap.setOnelevelType(onelevelType);
					webMap.setSecondlevelType(secondlevelType);
					webMapList.add(webMap);
				}
			}
			
			cacheService.saveCache(key, webMapList,WebGlobal.OTHER_EXPTIME);
		}else {
			webMapList = JSON.parseArray(WebMapStr, WebMapList.class);
		}
		
		return webMapList;
	}
	
	@Override
	public List<LabelResult> queryLabel(LabelTypeEnum labelTypeEnum){
		String key = WebGlobal.WEBMAP_INFO_REDIS_PREFIX+labelTypeEnum;
		String LabelStr = cacheService.getCache(key);
		if(StringUtil.isEmpty(LabelStr)) {
			
		}
		List<LabelResult> res = commMapper.queryLabel(labelTypeEnum.getCode());
		return res;
	}
}
