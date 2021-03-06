package com.bashiju.www.service.comm;

import java.util.List;

import com.bashiju.enums.BottomContentEnum;
import com.bashiju.enums.ImgManageEnum;
import com.bashiju.enums.LabelTypeEnum;
import com.bashiju.enums.pc.ConditionTypeEnum;
import com.bashiju.www.pojo.comm.QueryCondition;
import com.bashiju.www.pojo.service.agent.Agent;
import com.bashiju.www.pojo.service.input.comm.FeedbackParam;
import com.bashiju.www.pojo.service.input.house.HouseTipOffParam;
import com.bashiju.www.pojo.service.out.comm.BusinessRateResult;
import com.bashiju.www.pojo.service.out.comm.ContactUsResult;
import com.bashiju.www.pojo.service.out.comm.HousePropertyResult;
import com.bashiju.www.pojo.service.out.comm.LabelResult;
import com.bashiju.www.pojo.service.out.comm.LoanRateResult;
import com.bashiju.www.pojo.service.out.comm.ThemeResult;
import com.bashiju.www.pojo.service.out.comm.WebMapList;
import com.bashiju.www.pojo.service.out.comm.WebProtocolResult;

/**
 * 
 *   公共服务接口
 * @ClassName:  ICommService   
 * @Description:   公共服务接口
 * @author: wangkaifa
 * @date:   2018年7月27日 下午12:05:03       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface ICommService {
	/**
	 * 
		 * 添加房源、小区关注
		 * @Description: 添加房源、小区关注 
		 * @param custId 客户id
		 * @param sourceId 资源id（二手房、租房、新房、新房、小区）
		 * @param type 资源类型，详见HouseTypeEnum
		 * @return 
		 * boolean
	 */
	boolean addAttention(int custId,String sourceId,int type);
	/**
	 * 
		 * 取消关注
		 * @Description: 取消关注 
		 * @param custId 客户id
		 * @param sourceId 资源id（二手房、租房、新房、新房、小区）
		 * @param type 资源类型，详见HouseTypeEnum
		 * @return 
		 * boolean
	 */
	boolean cancelAttention(int custId,String sourceId,int type);
	/**
	 * 
		 * 是否已关注资源
		 * @Description: 是否已关注资源 
		 * @param custId 客户id
		 * @param sourceId 资源id（二手房、租房、新房、新房、小区）
		 * @param type 资源类型，详见HouseTypeEnum
		 * @return true已关注，false未关注
		 * boolean
	 */
	boolean isAttention(int custId,String sourceId,int type);
	/**
	 * 
		 * 添加网站反馈，设置反馈时间为当前时间
		 * @Description: 添加网站反馈
		 * @param param 反馈信息
		 * @return 
		 * boolean
	 */
	boolean addFeedback(FeedbackParam param);
	
	/**
	 * 
		 * 添加房源举报，举报时间设置成当前时间
		 * @Description: 添加房源举报
		 * @param param 举报信息
		 * @return 
		 * boolean
	 */
	boolean addHouseTipOff(HouseTipOffParam param);
	/**
	 * @Description:根据小区编号查询出小区中最优的4个经纪人  
	 * @param communityCode 小区编号    
	 * @return: List<Agent>
	 */
	List<Agent> queryCommunityBestAgent(String communityCode);
	/**
	 * 
		 * 查询网站关于我们、用户协议详细信息
		 * @Description: 查询网站关于我们、用户协议详细信息
		 * @param bottomContentEnum
		 * @return 
		 * WebProtocolResult
	 */
	WebProtocolResult queryWebProtocol(BottomContentEnum bottomContentEnum);
	/**
	 * 
		 * 查询网站联系我们列表
		 * @Description: 查询网站联系我们列表
		 * @return 
		 * List<ContactUsResult>
	 */
	List<ContactUsResult> queryContactUs(); 
	
	/**
	 * 先从redis中取值，没有再查询数据库并存入redis
	 *  redis的key为前缀(WebGlobal.WEBMAP_ITEM_REDIS_PREFIX)+城市代码,
	 * @Title: queryWebMap   
	 * @Description: TODO(网站地图接口)   
	 * @param: @param cityCode
	 * @param: @return      
	 * @return: List<WebMapList>      
	 * @throws
	 */
	public List<WebMapList> queryWebMap(String cityCode);
	/**
	 * 
	 * @Title: queryLabel   
	 * @Description: TODO(查询热评、点评标签)   
	 * @param: @param labelTypeEnum
	 * @param: @return      
	 * @return: List<LabelResult>      
	 * @throws
	 */
	public List<LabelResult> queryLabel(LabelTypeEnum labelTypeEnum);
	
	/**
	 * 查询条件列表 
	 * @Description: 查询条件列表    
	 * @param cityCode 城市代码
	 * @param conditionType 条件类型
	 * @return: List<QueryCondition>
	 */
	public List<QueryCondition> queryQueryConditions(String cityCode,ConditionTypeEnum conditionType);
	/**
	 * 
		 * 查询首页主题列表
		 * @Description: 查询首页主题列表 
		 * @param cityCode 城市代码
		 * @param type 类型
		 * @return 
		 * List<HouseThemeResult>
	 */
	List<ThemeResult> queryThemes(String cityCode,ImgManageEnum type);
	/**
	 * 
		 * 查询贷款利率
		 * @Description:查询贷款利率） 
		 * @param cityCode 城市代码
		 * @return 
		 * LoanRateResult
	 */
	LoanRateResult queryLoanRate(String cityCode);
	/**
	 * 
		 * 住宅房源类型列表查询
		 * @Description: 住宅房源类型列表查询
		 * @param cityCode 城市代码
		 * @return 
		 * HousePropertyResult
	 */
	List<HousePropertyResult> queryHousePropertyList(String cityCode);
	/**
	 * 
		 * 非住宅交易税费比例
		 * @Description: 非住宅交易税费比例
		 * @return 
		 * BusinessRateResult
	 */
	BusinessRateResult queryBusinessRateResult();
}
