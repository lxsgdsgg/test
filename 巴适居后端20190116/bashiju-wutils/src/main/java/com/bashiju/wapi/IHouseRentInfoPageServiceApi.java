package com.bashiju.wapi;

import java.util.List;

import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.input.comm.FeedbackParam;
import com.bashiju.www.pojo.service.input.house.HouseTipOffParam;
import com.bashiju.www.pojo.service.out.article.RentSkillLinkResult;
import com.bashiju.www.pojo.service.out.comm.ImgResult;
import com.bashiju.www.pojo.service.out.comm.SelectItemResult;
import com.bashiju.www.pojo.service.out.house.GoodHouseListResult;
import com.bashiju.www.pojo.service.out.house.HouseCompareResult;
import com.bashiju.www.pojo.service.out.house.HouseFeedbackResult;
import com.bashiju.www.pojo.service.out.house.HouseListResult;
import com.bashiju.www.pojo.service.out.house.RentHouseInfoResult;
import com.bashiju.www.pojo.service.out.house.ShowedRecordResult;

/**
 * 
 *   租房详细页面接口
 * @ClassName:  IHouseRentInfoPageServiceApi   
 * @Description:  租房详细页面接口
 * @author: wangkaifa
 * @date:   2018年8月13日 下午3:45:43       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface IHouseRentInfoPageServiceApi {
	/**
	 * 
		 * 根据id查询租房房源基础信息,包括房源图片地址、房源特色、其他挂牌公司、小区信息、同价房源推荐等
		 * @Description: 根据id查询租房房源基础信息
		 * @param id
		 * @return 
		 * RentHouseInfoResult
	 */
	RentHouseInfoResult queryRentHouseInfo(long id);
	/**
	 * 
	 * 根据城市查询租房技巧列表,按照时间倒序
	 * @Description: 根据城市查询租房技巧列表
	 * @param cityCode 城市代码
	 * @Param limit 显示条数
	 * @return 
	 * List<RentSkillLinkResult>
	 */
	public List<RentSkillLinkResult> queryRentSkills(String cityCode,int limit);
	/**
	 * 
		 * 经纪人带看反馈分页查询,sort 排序：0为时间，1为有用次数
		 * @Description: 经纪人带看反馈分页查询 
		 * @param houseId 房源id
		 * @param sort 排序：0为时间，1为有用次数
		 * @param page 当前页
		 * @param limit 每页条数
		 * @return 
		 * WebPage<HouseFeedbackResult>
	 */
	WebPage<HouseFeedbackResult> queryFeedback(long houseId,int sort,int page,int limit);
	/**
	 * 
		 * 新增经纪人带看反馈有用次数，同用户同反馈点击只算一次，新增同时修改反馈表的有用次数
		 * @Description: 新增经纪人带看反馈有用次数 
		 * @param custId 客户id
		 * @param agentEvaluateHouseId 带看反馈id
		 * @return 
		 * boolean
	 */
	boolean addFeedbackUseCount(int custId,int agentEvaluateHouseId);
	/**
	 * 
		 * 根据房源id查询带看记录
		 * @Description: 根据房源id查询带看记录 
		 * @param houseId
		 * @param page 当前页数
		 * @param limit 每页条数
		 * @return 
		 * List<ShowedRecordResult>
	 */
	WebPage<ShowedRecordResult> queryShowedRecordByHouseId(long houseId,int page,int limit);
	/**
	 * 
		 * 查询相似房源
		 * @Description: 查询相似房源 
		 * @param communityId 小区id
		 * @param room 卧室数量
		 * @param price 价格,元
		 * @return 
		 * List<HouseListResult>
	 */
	List<GoodHouseListResult> queryRentSameHouseList(int communityId,int room,int price);
	/**
	 * 
		 * 房源比较查询
		 * @Description: 房源比较查询
		 * @param ids 比较房源id
		 * @return 
		 * List<HouseCompareResult>
	 */
	List<HouseCompareResult> queryHouseCompareList(List<Long> ids);
	/**
	 * 
		 * 查询举报类型列表
		 * @Description: 查询举报类型列表
		 * @return 
		 * List<SelectItemResult>
	 */
	List<SelectItemResult> queryTipOffTypes();
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
	 * 
		 * 添加租房房源关注
		 * @Description: 添加租房房源关注 
		 * @param custId 客户id
		 * @param houseId 房源id
		 * @return 
		 * boolean
	 */
	boolean addAttention(int custId,Integer houseId);
	/**
	 * 
		 * 取消关注
		 * @Description: 取消关注 
		 * @param custId 客户id
		 * @param houseId 房源id
		 * @return 
		 * boolean
	 */
	boolean cancelAttention(int custId,Integer houseId);
	/**
	 * 
		 * 是否已关注房源
		 * @Description: 是否已关注资源 
		 * @param custId 客户id
		 * @param houseId 房源id
		 * @return true已关注，false未关注
		 * boolean
	 */
	boolean isAttention(int custId,Integer houseId);
	/**
	 * 
		 * 添加网站反馈，设置反馈时间为当前时间
		 * @Description: 添加网站反馈
		 * @param param 反馈信息
		 * @return 
		 * boolean
	 */
	boolean addFeedback(FeedbackParam param);
	
	//租房委托图片
	/**
	 * 
	 * 根据城市查询 租房委托主题图片
	 * @Description: 根据城市查询 租房委托主题图片   
	 * @param: cityCode 城市编号
	 * @param: @return      
	 * @return: List<ImgResult>      
	 * @throws
	 */
    public List<ImgResult> getRentWtThemeImg(String cityCode);
}
