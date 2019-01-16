/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  TestDataService.java   
 * @Package com.bashiju.manage.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年4月10日 下午6:35:19   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.housing.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bashiju.enums.ExamineStatusEnum;
import com.bashiju.housing.pojo.BusinessExamineHandleTypeEnum;
import com.bashiju.housing.pojo.BusinessExamineTypeEnum;
import com.github.pagehelper.Page;
/**
 * 
 * @ClassName  ErHousingService   
 * @Description 二手房源服务接口
 * @author zhaobin
 * @date   2018年4月27日 下午1:56:43   
 *     
 * @Copyright 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface IErHousingService {

	/**
	 * 
	 * @Title queryTableTitle   
	 * @Description 查询标题信息   
	 * @param  tableId   
	 * @return String      
	 * @throws
	 */
	public String queryTableTitle(String tableName,boolean islistShow);
	
	/*public String queryTableData();*/
	/**
	 * 
	 * @Title: queryDept   
	 * @Description: (查询部门)   
	 * @param: @return      
	 * @return: LinkedList<Map<String,Object>>      
	 * @throws
	 */
	public LinkedList<Map<String,Object>> queryDept();
	/**
	 * 
	 * @Title: queryHouseInfo   
	 * @Description: (查询房源详细页各信息，房源基本信息、跟进信息、经纪人信息等)   
	 * @param: @param HouseId
	 * @param: @param formName
	 * @param: @param type
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public Map<String,Object> queryHouseInfo(Long HouseId,String formName,String type);
	/**
	 * 
	 * @Title: queryAchievementRate   
	 * @Description: 查询分成比例   
	 * @param: @param companyId
	 * @param: @param achievementType
	 * @param: @return      
	 * @return: Integer      
	 * @throws
	 */
	public Map<String,Object> queryAchievementRate(String companyId,String achievementType);
	/**
	 * 
	 * @Title: queryPricedetail   
	 * @Description: (查价格详情)   
	 * @param: @param shhId
	 * @param: @param page
	 * @param: @param limit
	 * @param: @return      
	 * @return: Page<Map<String,Object>>      
	 * @throws
	 */
	public Page<Map<String, Object>> queryPricedetail(Long shhId,int page,int limit);
	/**
	 * 
	 * @Title: queryArchives   
	 * @Description: (查用户档案)   
	 * @param: @param shhId
	 * @param: @param type
	 * @param: @param page
	 * @param: @param limit
	 * @param: @return      
	 * @return: Page<Map<String,Object>>      
	 * @throws
	 */
	public Page<Map<String, Object>> queryArchives(Long shhId,String type,int page,int limit);
	/**
	 * 
	 * @Title: queryLookHouse   
	 * @Description: (查带看记录)   
	 * @param: @param shhId
	 * @param: @param page
	 * @param: @param limit
	 * @param: @return      
	 * @return: Page<Map<String,Object>>      
	 * @throws
	 */
	public Page<Map<String, Object>> queryLookHouse(Long shhId,int page,int limit);
	/**
	 * 
	 * @Title: queryFollowRecord   
	 * @Description: (查跟进记录)   
	 * @param: @param shhId
	 * @param: @param page
	 * @param: @param limit
	 * @param: @return      
	 * @return: Page<Map<String,Object>>      
	 * @throws
	 */
	public Page<Map<String, Object>> queryFollowRecord(Long shhId,int page,int limit);
	/**
	 * 
	 * @Title: businessExamine   
	 * @Description: (业务审批申请)   
	 * @param: @param data
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public String businessExamine(Map<String,Object> data);
	/**
	 * 
	 * @Title: changeSomeStatus   
	 * @Description: (房源一些状态的更改：用途、淘宝状态、盘状态等)   
	 * @param: @param data
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public String changeSomeStatus(Map<String,Object> data);
	/**
	 * 
	 * @Title: updateTag   
	 * @Description: (一些房源标签的更改：靓房、置顶、收藏等)   
	 * @param: @param shhId
	 * @param: @param type
	 * @param: @param status
	 * @param: @return      
	 * @return: Long      
	 * @throws
	 */
	public Long updateTag(Long shhId,String type,int status);
	/**
	 * 
	 * @Title: saveHouseFollow   
	 * @Description: (保存更进记录)   
	 * @param: @param data
	 * @param: @return      
	 * @return: Long      
	 * @throws
	 */
	public Long saveHouseFollow(Map<String,Object> data);
	
	/**
	 * 
	 * @Title: delHouseFollow   
	 * @Description: 删除跟进记录   
	 * @param: @param id
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean delHouseFollow(Long id);
	/**
	 * 
	 * @Title: saveOverallview   
	 * @Description: (保存全景看房地址)   
	 * @param: @param houseId
	 * @param: @param address
	 * @param: @return      
	 * @return: Long      
	 * @throws
	 */
	public Long saveOverallview(Long houseId,String address);
	/**
	 * 
	 * @Title: UpdateHouseOwner   
	 * @Description: (更改房源业主)   
	 * @param: @param data
	 * @param: @return      
	 * @return: Long      
	 * @throws
	 */
	public Long UpdateHouseOwner(Map<String,Object> data);
	/**
	 * 
	 * @Title: saveOwnerPhone   
	 * @Description: (新增业主电话)   
	 * @param: @param data
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public String saveOwnerPhone(Map<String,Object> data);
	/**
	 * 
	 * @Title: updateOwnerPhone   
	 * @Description: (修改业主电话)   
	 * @param: @param data
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public String updateOwnerPhone(Map<String,Object> data);
	/**
	 * 
	 * @Title: delOwnerPhone   
	 * @Description: (删除业主电话)   
	 * @param: @param id
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean delOwnerPhone(Long id);
	/**
	 * 
	 * @Title: changeHouseOwner   
	 * @Description: (变更业主)   
	 * @param: @param data
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public String changeHouseOwner(Map<String,Object> data);
	/**
	 * 
	 * @Title: updatePrice   
	 * @Description: (修改底价)   
	 * @param: @param data
	 * @param: @return      
	 * @return: Long      
	 * @throws
	 */
	public Long updatePrice(Map<String,Object> data);
	/**
	 * 
	 * @Title: updateAgent   
	 * @Description: (修改经纪人)   
	 * @param: @param data
	 * @param: @return      
	 * @return: Long      
	 * @throws
	 */
	public Long updateAgent(Map<String,Object> data);
	/**
	 * 
	 * @Title: saveFile   
	 * @Description: (保存房源附件：照片、相关照片、视频等)   
	 * @param: @param request
	 * @param: @return      
	 * @return: Long      
	 * @throws
	 */
	public String saveFile(HttpServletRequest request);
	/**
	 * 
	 * @Title: queryFile   
	 * @Description: (查询房源附件)   
	 * @param: @param shhId
	 * @param: @param attachType
	 * @param: @return      
	 * @return: List      
	 * @throws
	 */
	public List queryFile(Long shhId,String attachType);
	/**
	 * 
	 * @Title: queryFileById   
	 * @Description: 根据ID查询   
	 * @param: @param imgId
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public String queryFileById(Long imgId);
	/**
	 * 
	 * @Title: updateAttachment   
	 * @Description: (修改房源附件)   
	 * @param: @param data
	 * @param: @return      
	 * @return: Long      
	 * @throws
	 */
	public Long updateAttachment(Map<String,Object> data);
	
	/**
	 * 
	 * @Title: delFile   
	 * @Description: TODO(删除文件)   
	 * @param: @param imgIds
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean delFile(String imgIds,Long shhId);
	
	/**
	 * 
	 * @Title: saveHouseDescription   
	 * @Description: (保存房源描述)   
	 * @param: @param data
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean saveHouseDescription(List<Map<String,Object>> data);
	/**
	 * 
	 * @Title: queryHouseDescription   
	 * @Description: (查询房源描述)   
	 * @param: @param shhId
	 * @param: @param page
	 * @param: @param limit
	 * @param: @return      
	 * @return: Page<Map<String,Object>>      
	 * @throws
	 */
	public Page<Map<String, Object>> queryHouseDescription(Long shhId,int page,int limit);
	/**
	 * 
	 * @Title: saveAgentFeedback   
	 * @Description: (保存经纪人带看反馈)   
	 * @param: @param data
	 * @param: @return      
	 * @return: Long      
	 * @throws
	 */
	public Long saveAgentFeedback(Map<String,Object> data);
	/**
	 * 
	 * @Title: delAgentFeedback   
	 * @Description: (删除经纪人带看反馈)   
	 * @param: @param id
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean delAgentFeedback(int id);
	/**
	 * 
	 * @Title: queryAgentFeedback   
	 * @Description: (查询经纪人带看反馈)   
	 * @param: @param condition
	 * @param: @param page
	 * @param: @param limit
	 * @param: @return      
	 * @return: Page<Map<String,Object>>      
	 * @throws
	 */
	public Page<Map<String, Object>> queryAgentFeedback(String condition,int page,int limit);
	/**
	 * 
	 * @Title: queryLabel   
	 * @Description: (查询房源标签)   
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String,Object>> queryLabel();
	
	public List<Map<String,Object>> queryBuild(Long communityId);
	public List<Map<String,Object>> queryUnit(Long buildId);
	
	
	/**
	 * 
	 * @Title: addHousing   
	 * @Description: (新增房源)   
	 * @param: @param data
	 * @param: @param formName
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean addHousing(Map<String,Object> data,String formName);

	
	/**
	 * 
	 * @Title: queryTableDatas   
	 * @Description: (根据表单名称及搜索条件，查询表单table数据)   
	 * @param: @param formName
	 * @param: @param conditions
	 * @param: @param page
	 * @param: @param limit
	 * @param: @return      
	 * @return: Page<Map<String,Object>>      
	 * @throws
	 */
	public Page<Map<String, Object>> queryHousingTableDatas(String conditions, int page, int limit);
	/**
	 * 
	 * @Title: queryTableHiddenField   
	 * @Description: (查询用户自定义的表格隐藏列)   
	 * @param: @param formName
	 * @param: @param userId
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public Map<String, Object> queryTableHiddenField(String formName);
	/**
	 * 保存表单隐藏设置
	 * @Title: saveTableHiddenInfo   
	 * @Description: (这里用一句话描述这个方法的作用)   
	 * @param: @param formName
	 * @param: @param values
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean saveTableHiddenInfo(String formName,String values);
	
	public Map<String, Object> queryBuildingInfo(Long buildingId,String discStatus);
	
	/**
	 * 
	 * @Title: searchCommunity   
	 * @Description: (查询登录用户所在城市的片区、小区)   
	 * @param: @param cityCode
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	List<Map<String, Object>> queryCommunity();
	/**
	 * 
	 * @Title: getHousingCount   
	 * @Description: (获取同一家公司同一个房源编号的房源数量)   
	 * @param: @param houseId 房号id
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	long getHousingCount(Long houseId);
	/**
	 * 
	 * @Title: deleteHousing   
	 * @Description: (删除房源)   
	 * @param: @param houseIds 多个id，用逗号连接
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int deleteHousing(String houseIds);
	/**
	 * 
	 * @Title: updateHousing   
	 * @Description: (修改房源信息)   
	 * @param: @param data
	 * @param: @param formName
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int updateHousing(Map<String,Object> data,String formName);
	/**
	 * 
	 * @Title: addHousingSee   
	 * @Description: (添加房源浏览记录)   
	 * @param: @param houseId 房源id
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int addHousingSee(Long houseId);
	/**
	 * 
	 * @Title: doExamine   
	 * @Description: (房源业务审核处理接口)   
	 * @param: @param houseId 房源id
	 * @param: @param type 审核类型
	 * @param: @param result 审核结果
	 * @param: @param remark 房源待处理状态
	 * @param: @param recipient 接收人id
	 * @param: @param handleType 处理方式
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	boolean doExamine(long houseId,BusinessExamineTypeEnum type,ExamineStatusEnum result,String remark,Long recipient,BusinessExamineHandleTypeEnum handleType);
	
	/**
	 * 
	 * @Title: queryImagePath   
	 * @Description: (图片批量下载路径)   
	 * @param: houseId
	 * @param:  imageIds
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public Map<String, Object> queryImagePath(Long houseId,String imageIds);
	/**
	 * 
		 * 根据座栋单元id查询房源销控列表
		 * @Description: 根据座栋单元id查询房源销控列表 
		 * @param buildIngUnitId 座栋单元id
		 * @param @return 
		 * @return: List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryHouseListByUnitId(Long buildIngUnitId);
	/**
	 * 
		 * 查询房源私密数据
		 * @Description: 查询房源私密数据 
		 * @param houseId 房源id
		 * @param  type 1：为门牌号查询，2：为业主电话查询，3：为房源价格查询
		 * @param @return 
		 * @return: Object
	 */
	Object queryHousePrivateInfo(Long houseId,Integer type);
	/**
	 * 
		 * 房源批量转移
		 * @Description: 房源批量转移
		 * @param houseIds 房源id逗号分隔
		 * @param userId 转移用户id
		 * @return 
		 * boolean
	 */
	boolean houseTransfer(String houseIds,long userId);
	/**
	 * 
		 * 批量修改房源是否与外网同步
		 * @Description: 批量修改房源是否与外网同步 
		 * @param houseIds 房源id逗号分隔
		 * @param state 1、同步，0、不同步
		 * @return 
		 * boolean
	 */
	boolean houseSynchron(String houseIds,Integer state);
	
	
	/**
	 * @Description: 将房源数据分交易类型同步到客户端查询表中   
	 * @param houseIds 待同步的房源编号(多个用英文逗号隔开)
	 * @return: boolean
	 */
	boolean houseSynchron(String houseIds);
	
	/**
	 * 
		 * 批量修改房源信息
		 * @Description: 批量修改房源信息
		 * @param houseIds 房源id逗号分隔
		 * @param fields 修改的列名及修改值
		 * @return 
		 * boolean
	 */
	public boolean batchUpdateHouseInfo(String houseIds, Map<String, Object> fields);
	/**
	 * 
		 * 搜索学校表格数据
		 * @Description: 搜索学校表格数据 
		 * @param name 学校名称
		 * @param page 当前页数
		 * @param limit 每页条数
		 * @return 
		 * Page<Map<String,Object>>
	 */
	public Page<Map<String, Object>> querySchoolTable(String name, int page, int limit);
	
	/**房源匹配客源**/
	public Page<Map<String, Object>> queryMatchingCustomer(String conditions, int page, int limit);
	/**
	 * 
		 * 房源成交接口
		 * @Description: 房源成交接口
		 * @param dataJson 成交信息{dealType:成交类型,agreementId:合同编号,demandId:需求编号,shhid:房源id,owner:业主,custName:客户,traderId:成交人id,price:成交价格,dealTime:成交日期,
		 * landCertifcate:土地证号,propCertificate:产权证号,referenceId:权证人id,reference:权证人姓名,commissionPrice:应收佣金,supplementInfo:补充条款,isLoan:是否有贷款}
		 * @param commissionsJson 佣金信息[{moneyType:费用类型,moneyProj:费用项目,price:费用金额,estimatePayTime:预计缴费时间,remark:备注}]
		 * @param crossJson 跨店信息
		 * @return 
		 * boolean
	 */
	public boolean dealHousing(String dataJson,String commissionsJson,String crossJson);
	/**
	 * 
		 * 查询房源采集列表
		 * @Description: 查询房源采集列表 
		 * @param condition  {transactionTypeId:交易类型,communityName:小区名称,minPrice:最低价格,maxPrice:最大价格,
		 * minSpace:最小面积,maxSpace:最大面积,lookStatus:查看状态,inputStatus:录入状态,checkStatus:检查无效状态}
		 * @param page 当前页面
		 * @param limit 页面大小
		 * @return 
		 * Page<Map<String,Object>>
	 */
	public Page<Map<String, Object>> queryCrawlerList(String condition,int page,int limit);
	/**
	 * 
		 * 判断电话是否在黑名单，不在则返回业主电话、小区id相关的房源列表
		 * @Description: 判断电话是否在黑名单，不在则返回业主电话、小区id相关的房源列表
		 * @param phone 电话
		 * @param communityId 小区id
		 * @return [id:房源id,houseId:房源编号,transactionType:交易类型,status:房源状态,sellingPrice:售价,rentPrice:租价,recordTime:录入时间,maintainer:维护人]
		 * List<Map<String, Object>>
	 */
	public List<Map<String, Object>> queryHouseByPhone(String phone,Long communityId);
	/**
	 * 
		 * 根据座栋单元查询房号销控
		 * @Description: 根据座栋单元查询房号销控
		 * @param buildingUnitId 单元id
		 * @return {floorTotal:单元总楼层,houseStartNumber:房号开始数,floorStartNumber:楼层开始数,unitHouseCount:每层户数,
		 * houseList:[{houseNO:房号id,houseNumber:房号,floor:房号所在楼层,code:户数编号,hid:房源id,houseId:房源编号,transactionType:交易类型(出租、出售),status:房源状态(有效、暂缓、我售等),sellingPrice:出售价格(分),buildSpace:面积,room:几室,hall:几厅,kitchen:几厨,toilet:几卫,balcony:几阳}]}
		 * Map<String,Object>
	 */
	public Map<String, Object> queryHouseSalesStatusByUnit(Long buildingUnitId,String companyId);
	/**
	 * 
		 * 生成并发送房源绑定码
		 * @Description: 生成并发送房源绑定码 
		 * @param houseId 房源id
		 * @return 
		 * boolean
	 */
	public boolean sendBindCode(Long houseId);
	/**
	 * 
		 * 设置房源编号相同的房源的外网优先显示状态
		 * @Description: 设置房源编号相同的房源的外网优先显示状态 
		 * @param houseId 房源id
		 * void
	 */
	public void updateIsPriorityStatus(Long houseId);
	
	/**
	 * 
		 * 录入房源是否默认私盘
		 * @Description: 录入房源是否默认私盘
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryHouseDiscStatus();
	/**
	 * 
		 * 首页查询最新的房源
		 * @Description: 首页查询最新的房源 
		 * @param limit 显示条数
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryHouseByTime(Integer limit);
	/**
	 * 
		 *首页统计待跟进数量
		 * @Description: 首页统计待跟进数量
		 * @return 
		 * Integer
	 */
	public Integer queryUnFollowCountByUser();
	/**
	 * 
		 * 首页当月任务完成情况
		 * @Description: 进首页当月任务完成情况
		 * @return 
		 * Map<String,Object>
	 */
	public Map<String, Object> queryMonthtaskByUserId();
	/**
	 * 
		 * 查询应收佣金
		 * @Description: 查询应收佣金
		 * @param houseId 房源id
		 * @param contractType 合同类型
		 * @param dealPrice 成交价格
		 * @return 
		 * Integer
	 */
	public Integer queryCommissionPrice(Long houseId,String contractType,Integer dealPrice);
}
