package com.bashiju.wapi;

import java.util.List;

import com.bashiju.www.pojo.service.input.comm.FeedbackParam;
import com.bashiju.www.pojo.service.input.house.HouseTipOffParam;
import com.bashiju.www.pojo.service.out.comm.SelectItemResult;
import com.bashiju.www.pojo.service.out.newhouse.NewHouseDetailResult;
import com.bashiju.www.pojo.service.out.newhouse.NewHouseDynamicResult;
import com.bashiju.www.pojo.service.out.newhouse.SameNewHouseList;

/**
 * 
 *   新房详细页接口
 * @ClassName:  INewHouseInfoPageApi   
 * @Description:   新房详细页接口
 * @author: wangkaifa
 * @date:   2018年8月22日 上午10:38:39       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface INewHouseInfoPageApi {
	/**
	 * 
		 * 查询新房楼盘详情
		 * @Description: 查询新房楼盘详情
		 * @param newHouseId 新房楼盘id
		 * @return 
		 * NewHouseDetailResult
	 */
	NewHouseDetailResult selectNewHouseDetailInfo(int newHouseId);
	
	/**
	 * 
		 * 查询相似新房楼盘
		 * @Description: 查询相似新房楼盘
		 * @param avgPrice：新房楼盘均价
		 * @param limit：需要查询的条数
		 * @return 
		 * List<SameNewHouseList>
	 */
	List<SameNewHouseList> querySameNewHouseList(int avgPrice,int limit);
	/**
	 *  查询新房楼盘动态    
	 * @Description: 查询新房楼盘动态   
	 * @param newHouseId 新房楼盘id    
	 * @return: List<NewHouseDynamicResult>
	 */
	List<NewHouseDynamicResult> queryNewHouseDynamicList(String newHouseId);
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
		 * 添加新房楼盘举报，举报时间设置成当前时间
		 * @Description: 添加新房楼盘举报
		 * @param param 举报信息
		 * @return 
		 * boolean
	 */
	boolean addHouseTipOff(HouseTipOffParam param);
	/**
	 * 
		 * 添加新房楼盘关注
		 * @Description: 添加新房楼盘关注 
		 * @param custId 客户id
		 * @param newHouseId 新房id
		 * @return 
		 * boolean
	 */
	boolean addAttention(int custId,Integer newHouseId);
	/**
	 * 
		 * 取消关注
		 * @Description: 取消关注 
		 * @param custId 客户id
		 * @param newHouseId 新房id
		 * @return 
		 * boolean
	 */
	boolean cancelAttention(int custId,Integer newHouseId);
	/**
	 * 
		 * 是否已关注
		 * @Description: 是否已关注
		 * @param custId 客户id
		 * @param newHouseId 新房id
		 * @return true已关注，false未关注
		 * boolean
	 */
	boolean isAttention(int custId,Integer newHouseId);
	/**
	 * 
		 * 添加网站反馈，设置反馈时间为当前时间
		 * @Description: 添加网站反馈
		 * @param param 反馈信息
		 * @return 
		 * boolean
	 */
	boolean addFeedback(FeedbackParam param);
}
