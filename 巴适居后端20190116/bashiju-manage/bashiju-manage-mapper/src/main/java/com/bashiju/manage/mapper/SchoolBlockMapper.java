/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  SchoolBlockMapper.java   
 * @Package com.bashiju.manage.mapper   
 * @Description: 学区管理映射类 
 * @author: zuoyuntao     
 * @date:   2018年5月17日 上午11:10:37   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

package com.bashiju.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

/**   
 * 学区管理映射类
 * @ClassName:  SchoolBlockMapper   
 * @Description:学区管理映射类 
 * @author: zuoyuntao
 * @date:2018年5月17日 上午11:10:37   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface SchoolBlockMapper {

	/**
	 * 获取学校信息分页对象
	 * @Title: getSchoolPageObj   
	 * @Description: 获取学校信息分页对象   
	 * @param paraMap 参数对象
	 * @return: Page<Map<String,Object>>     
	 * {count=8, msg=, code=0, data=Page{count=true, pageNum=1, pageSize=10, startRow=0, endRow=10, total=8, pages=1, reasonable=false, pageSizeZero=false}[{schoolEdit=schoolEdit, permissionArea=BSJ0103, communitDel=communitDel, addTime=2018-05-17 16:24:27.0, schoolDel=schoolDel, isValid=1, schoolAddress=放大沙发s'f'd's, updateTime=2018-05-24 10:22:29.0, operator=测试人员, middleSchoolName= 法大师傅爱的色放安德森发大水, communityEdit=communityEdit, id=1, communityAdd=communityAdd, schoolName=呵呵了, operatorId=1}, {schoolEdit=schoolEdit, permissionArea=BSJ0103, communitDel=communitDel, addTime=2018-05-17 16:25:14.0, schoolDel=schoolDel, isValid=1, schoolAddress=法大师傅阿斯蒂芬, operator=测试人员, middleSchoolName=发大水发射点犯得上发生, communityEdit=communityEdit, id=2, communityAdd=communityAdd, schoolName=从沙发上的, operatorId=1}, {schoolEdit=schoolEdit, permissionArea=BSJ0103, communitDel=communitDel, addTime=2018-05-17 16:26:07.0, schoolDel=schoolDel, isValid=1, schoolAddress=放大沙发是, operator=测试人员, middleSchoolName=放大沙发是法大师傅打, communityEdit=communityEdit, id=3, communityAdd=communityAdd, schoolName=发的沙发上的, operatorId=1}, {schoolEdit=schoolEdit, permissionArea=BSJ0103, communitDel=communitDel, addTime=2018-05-18 09:34:12.0, schoolDel=schoolDel, isValid=1, schoolAddress=测试--1234, updateTime=2018-05-21 09:52:32.0, operator=测试人员, middleSchoolName=测试--456, communityEdit=communityEdit, id=4, communityAdd=communityAdd, schoolName=测试测试, operatorId=1}, {schoolEdit=schoolEdit, permissionArea=BSJ0103, communitDel=communitDel, addTime=2018-05-18 17:37:28.0, schoolDel=schoolDel, isValid=1, schoolAddress=法撒旦, updateTime=2018-05-21 10:31:52.0, operator=测试人员, middleSchoolName=法撒旦发范德萨发, communityEdit=communityEdit, id=6, communityAdd=communityAdd, schoolName=发射-123, operatorId=1}, {schoolEdit=schoolEdit, permissionArea=BSJ0103, communitDel=communitDel, addTime=2018-05-21 10:47:50.0, schoolDel=schoolDel, isValid=1, schoolAddress=法大师傅的撒地方阿三法撒旦发生的事, updateTime=2018-05-21 10:48:12.0, operator=测试人员, middleSchoolName=飞洒发的打算, communityEdit=communityEdit, id=7, communityAdd=communityAdd, schoolName=测试萨芬士, operatorId=1}, {schoolEdit=schoolEdit, permissionArea=BSJ0103, communitDel=communitDel, addTime=2018-05-21 11:58:00.0, schoolDel=schoolDel, isValid=1, schoolAddress=发射点发顺丰, updateTime=2018-05-21 11:59:18.0, operator=测试人员, middleSchoolName=法大师傅法大师傅, communityEdit=communityEdit, id=8, communityAdd=communityAdd, schoolName=沙发-1664, operatorId=1}, {schoolEdit=schoolEdit, permissionArea=BSJ0103, communitDel=communitDel, addTime=2018-05-21 13:37:06.0, schoolDel=schoolDel, isValid=1, schoolAddress=测试--888, updateTime=2018-05-21 13:42:34.0, operator=测试人员, middleSchoolName=测试, communityEdit=communityEdit, id=9, communityAdd=communityAdd, schoolName=测试-999, operatorId=1}]}   
	 * @throws
	 */
	public Page<Map<String, Object>> getSchoolPageObj(Map<String,Object> paraMap);

	/**
	 * 获取小区分页对象
	 * @Title: getCommunityPageObj   
	 * @Description: 获取小区分页对象   
	 * @param:  paraMap 包含学校Id：schoolId（值不可为空）和小区名称字段：communityName（值可以为空）
	 * @return: Page<Map<String,Object>>     
	 * {count=4, msg=, code=0, data=Page{count=true, pageNum=1, pageSize=10, startRow=0, endRow=10, total=4, pages=1, reasonable=false, pageSizeZero=false}[{schoolEdit=schoolEdit, permissionArea=BSJ0103, communitDel=communitDel, addTime=2018-05-21 10:57:34.0, schoolDel=schoolDel, isValid=1, communityRemark=测试试试, updateTime=2018-05-21 11:28:14.0, operator=测试人员, schoolId=1, communityName=投入和他如何, communityEdit=communityEdit, id=10, communityAdd=communityAdd, communityId=5, operatorId=1}, {schoolEdit=schoolEdit, permissionArea=BSJ0103, communitDel=communitDel, addTime=2018-05-21 11:56:41.0, schoolDel=schoolDel, isValid=1, communityRemark=测试--12, operator=测试人员, schoolId=1, communityName=12, communityEdit=communityEdit, id=15, communityAdd=communityAdd, communityId=8, operatorId=1}, {schoolEdit=schoolEdit, permissionArea=BSJ0103, communitDel=communitDel, addTime=2018-05-24 10:22:57.0, schoolDel=schoolDel, isValid=1, communityRemark=test123, updateTime=2018-05-24 10:23:33.0, operator=测试人员, schoolId=1, communityName=13, communityEdit=communityEdit, id=21, communityAdd=communityAdd, communityId=9, operatorId=1}, {schoolEdit=schoolEdit, permissionArea=BSJ0103, communitDel=communitDel, addTime=2018-05-30 11:22:30.0, schoolDel=schoolDel, isValid=1, communityRemark=测试---1111, operator=测试人员, schoolId=1, communityName=玛卡切片, communityEdit=communityEdit, id=22, communityAdd=communityAdd, communityId=3, operatorId=1}]}    
	 * @throws
	 */
	public Page<Map<String, Object>> getCommunityPageObj(Map<String, Object> paraMap);

	/**
	 * 根据数据ID和表名称获取数据信息（可以既是学校数据信息也可是小区数据信息）
	 * @Title: getDataById   
	 * @Description: 根据数据ID和表名称获取数据信息  （可以既是学校数据信息也可是小区数据信息）
	 * @param: id 小区Id或学校Id
	 * @param: tableName 表名称（小区信息表或学校信息表）
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getDataById(@Param("id") String id
			,@Param("tableName") String tableName);
	/**
	 * 通过学校名称获取该学校名称的总条数
	 * @Title: getSchoolCountByName   
	 * @Description: 通过学校名称获取该学校名称的总条数   
	 * @param: schoolName：学校名称
	 * @param: rgCode 城市编码
	 * @return: int 该学校名称的数据条数      
	 * @throws
	 */
	public long getSchoolCountByName(@Param("schoolName") String schoolName
			,@Param("rgCode") String rgCode);
	/**
	 * 通过小区ID查询小区数据信息  
	 * @Title: getSchoolCountByName   
	 * @Description: 通过小区ID查询小区数据信息   
	 * @param: communityId：小区ID
	 * @param: schoolId：学校ID
	 * @return int 该小区的数据总条数
	 * @throws
	 */
	public long getCommunityCountByCodintion(@Param("communityId") String communityId
			,@Param("schoolId") String schoolId);
	/**
	 * 根据行政区划查询行政区划数据信息
	 * @Title: getRegionList   
	 * @Description:根据行政区划查询行政区划数据信息   
	 * @param: rgCode:行政区划编码
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String,Object>> getRegionList(@Param("rgCode") String rgCode);
}
