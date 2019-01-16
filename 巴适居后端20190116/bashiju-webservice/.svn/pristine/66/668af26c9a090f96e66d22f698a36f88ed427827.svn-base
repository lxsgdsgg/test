/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CommunityMapper.java   
 * @Package com.bashiju.webservice.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年7月24日 下午4:58:53   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.webservice.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bashiju.www.pojo.service.agent.Agent;
import com.bashiju.www.pojo.service.input.comm.FeedbackParam;
import com.bashiju.www.pojo.service.out.comm.ContactUsResult;
import com.bashiju.www.pojo.service.out.comm.DeedTaxResult;
import com.bashiju.www.pojo.service.out.comm.HousePropertyResult;
import com.bashiju.www.pojo.service.out.comm.LabelResult;
import com.bashiju.www.pojo.service.out.comm.WebMapOnelevelType;
import com.bashiju.www.pojo.service.out.comm.WebMapSecondlevelType;
import com.bashiju.www.pojo.service.out.comm.WebProtocolResult;
/**   
 * @ClassName:  CommMapper   
 * @Description:
 * @author: liwen
 * @date:   2018年7月27日 下午4:58:53   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface CommMapper {

	public int saveAttention(@Param("custId")int custId,@Param("sourceId")String sourceId,@Param("type")int type);
	public boolean delAttention(@Param("custId")int custId,@Param("sourceId")String sourceId,@Param("type")int type);
	public int isAttention(@Param("custId")int custId,@Param("sourceId")String sourceId,@Param("type")int type);
	public int addFeedback(FeedbackParam param);
	public List<WebProtocolResult> queryWebProtocol(int typeId);
	public List<WebMapOnelevelType> queryAreas(String cityCode);
	public List<WebMapSecondlevelType> queryRegions(String cityCode);
	public List<WebMapOnelevelType> queryArticleOneTypes(@Param("cityCode")String cityCode,@Param("type")String type,@Param("level")String level);
	public List<WebMapSecondlevelType> queryArticleLastTypes(@Param("cityCode")String cityCode,@Param("type")String type,@Param("level")String level);
	public List<LabelResult> queryLabel(String type);
	public List<ContactUsResult> queryContactUs();
	/**
	 * @Description:根据小区编号查询出小区中最优的4个经纪人  
	 * @param communityCode 小区编号    
	 * @return: List<Agent>      
	 */ 
	public List<Agent> queryCommunityBestAgent(@Param("communityCode")String communityCode);
	/**
	 * 
		 * 房源关注数量加一
		 * @Description: 房源关注数量加一 
		 * @param houseId
		 * @return 
		 * int
	 */
	public int addAttentionCount(@Param("houseId")long houseId);
	/**
	 * 
		 * 二手房房源关注数量加一
		 * @Description: 二手房房源关注数量加一 
		 * @param houseId
		 * @return 
		 * int
	 */
	public int addSellAttentionCount(@Param("houseId")long houseId);
	/**
	 * 
		 * 租房房源关注数量加一
		 * @Description: 租房房源关注数量加一 
		 * @param houseId
		 * @return 
		 * int
	 */
	public int addRentAttentionCount(@Param("houseId")long houseId);
	/**
	 * 
		 * 房源关注数量减一
		 * @Description: 房源关注数量减一
		 * @param houseId
		 * @return 
		 * int
	 */
	public int minusAttentionCount(@Param("houseId")long houseId);
	/**
	 * 
		 * 二手房房源关注数量减一
		 * @Description: 二手房房源关注数量减一
		 * @param houseId
		 * @return 
		 * int
	 */
	public int minusSellAttentionCount(@Param("houseId")long houseId);
	/**
	 * 
		 * 租房房源关注数量减一
		 * @Description: 租房房源关注数量减一
		 * @param houseId
		 * @return 
		 * int
	 */
	public int minusRentAttentionCount(@Param("houseId")long houseId);
	/**
	 * 
		 *	新房关注数量加一
		 * @Description: 新房关注数量加一 
		 * @param houseId
		 * @return 
		 * int
	 */
	public int addNewHouseAttentionCount(@Param("houseId")long houseId);
	/**
	 * 
		 * 新房查询表关注数量加一
		 * @Description: 新房查询表关注数量加一
		 * @param houseId
		 * @return 
		 * int
	 */
	public int addNewHouseQueryAttentionCount(@Param("houseId")long houseId);
	/**
	 * 
		 * 新房注数量减一
		 * @Description: 新房注数量减一
		 * @param houseId
		 * @return 
		 * int
	 */
	public int minusNewHouseAttentionCount(@Param("houseId")long houseId);
	/**
	 * 
		 * 新房查询表注数量减一
		 * @Description: 新房查询表注数量减一 
		 * @param houseId
		 * @return 
		 * int
	 */
	public int minusNewHouseQueryAttentionCount(@Param("houseId")long houseId);
	/**
	 * 查询条件列表 
	 * @Description:查询条件列表 
	 * @param cityCode 城市代码
	 * @param conditionType 条件类型
	 * @return: List<Map<String,String>>       
	 */ 
	public List<Map<String, String>> queryQueryConditions(@Param("cityCode")String cityCode, @Param("conditionType")String conditionType);
	/**
	 * 
		 * 查询首页主题列表
		 * @Description: 查询首页主题列表 
		 * @param cityCode 城市代码
		 * @param type 类型 1
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryThemeList(@Param("cityCode")String cityCode, @Param("type")String type);
	/**
	 * 
		 * 查询贷款上限
		 * @Description: 查询贷款上限
		 * @param cityCode 城市代码
		 * @return 
		 * Map<String,Object>
	 */
	public Map<String, Object> queryLoanCeiling(@Param("cityCode")String cityCode);
	/**
	 * 
		 * 查询首付房源类型
		 * @Description:  查询首付房源类型
		 * @param cityCode 城市代码
		 * @return 
		 * List<HousePropertyResult>
	 */
	public List<HousePropertyResult> queryHousePropertyList(@Param("cityCode")String cityCode);
	/**
	 * 
		 * 查询契税比例
		 * @Description: 查询契税比例
		 * @param housePropertyId
		 * @return 
		 * List<DeedTaxResult>
	 */
	public List<DeedTaxResult> queryDeedTaxResult(@Param("housePropertyId")Integer housePropertyId);
	/**
	 * 
		 * 查询非住宅税费比例
		 * @Description: 进入菜单主页面（详细描述） 
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryBusinessRateResult();
}
