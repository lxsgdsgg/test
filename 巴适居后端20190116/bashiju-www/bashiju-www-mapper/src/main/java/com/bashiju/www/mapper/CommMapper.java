/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CommunityMapper.java   
 * @Package com.bashiju.www.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年7月24日 下午4:58:53   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.www.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bashiju.www.pojo.service.agent.Agent;
import com.bashiju.www.pojo.service.input.comm.FeedbackParam;
import com.bashiju.www.pojo.service.out.comm.AreaResult;
import com.bashiju.www.pojo.service.out.comm.LabelResult;
import com.bashiju.www.pojo.service.out.comm.RegionResult;
import com.bashiju.www.pojo.service.out.comm.WebProtocolResult;
import com.bashiju.www.pojo.service.out.comm.WebMapOnelevelType;
import com.bashiju.www.pojo.service.out.comm.WebMapSecondlevelType;

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
		 * 房源关注数量减一
		 * @Description: 房源关注数量减一
		 * @param houseId
		 * @return 
		 * int
	 */
	public int minusAttentionCount(@Param("houseId")long houseId);

}
