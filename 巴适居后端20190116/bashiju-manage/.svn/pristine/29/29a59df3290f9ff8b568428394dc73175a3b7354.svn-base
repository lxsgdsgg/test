/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  IUserManageService.java   
 * @Package com.bashiju.manage.service   
 * @Description:用户管理接口类
 * @author: zuoyuntao     
 * @date:   2018年5月24日 上午10:36:23   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

package com.bashiju.manage.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**
 * 用户管理接口类
 * @ClassName:  IUserManageService   
 * @Description:用户管理接口类  
 * @author: zuoyuntao
 * @date:   2018年5月24日 上午10:36:23   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface IUserManageService {
	/**
	 * 查询所用用户数据信息--带分页
	 * @Title: queryAllUserInfoWithPage   
	 * @Description: 查询所用用户数据信息--带分页   
	 * @param: paraMap
	 * @param: minCount 每页显示最小条数
	 * @param: maxCount 每页显示最大条数
	 * @return: Page<Map<String,Object>>     
	 * Page{count=true, pageNum=0, pageSize=10, startRow=0, endRow=0, total=11, pages=2, reasonable=false, pageSizeZero=false}[{deptName=呈贡一区二店一组, permissionArea=BSJ0103, isHotName=是, addTime=2018-04-09 16:06:01.0, isPCLoginName=否, lockedUser=lockedUser, operator=1, moveUserInfo=moveUserInfo, solt=123, password=1, areaName=昆明市, sexName=男, statusName=正常, resetPsw=resetPsw, lastLoginSuccessTime=2018-05-29 15:48:52.0, id=5, dataUpdate=dataUpdate, birthWindowFlag=1, operatorId=1, wxNum=18888888884, userEdit=userEdit, syncFlag=1, roleId=7, sex=2, syncFlagName=是, hotAgentFlag=1, isValid=1, mobile=18888888884, deptId=BSJ010101010201, updateTime=2018-05-29 15:49:35.0, sortNo=1, realName=呈贡一区一店一组组员1, companyId=BSJ01, areaCode=530100, permissionSet=permissionSet, employee_num=006, userTypeName=是, roleName=组员, userType=1, parentMobile=13577000337, status=1}] 
	 * @throws
	 */
	public Page<Map<String,Object>> queryAllUserInfoWithPage(Map<String,Object> paraMap
			,int minCount,int maxCount);
	/**
	 * 查询用户数据更新日志--带分页
	 * @Title: queryUserDataUpdateLogWithPage
	 * @author: zuoyuntao  
	 * @Description: 查询用户数据更新日志--带分页  
	 * @param: paraMap
	 * @param: minCount 每页显示最小条数
	 * @param: maxCount 每页显示最大条数
	 * @return: Page<Map<String,Object>>      
	 * @throws
	 */
	public Page<Map<String,Object>> queryUserDataUpdateLogWithPage(Map<String,Object> paraMap
			,int minCount,int maxCount);
	/**
	 * 根据参数对象查询用户信息--参数Map对象中的key必须和表（视图）中的字段名称一致
	 * @Title: queryUserInfoByConn   
	 * @Description: 根据参数对象查询用户信息--参数Map对象中的key必须和表（视图）中的字段名称一致  
	 * @param: paraMap
	 * @return: List<Map<String, Object>>   
	 * Page{count=true, pageNum=0, pageSize=10, startRow=0, endRow=0, total=11, pages=2, reasonable=false, pageSizeZero=false}[{deptName=呈贡一区二店一组, permissionArea=BSJ0103, isHotName=是, addTime=2018-04-09 16:06:01.0, isPCLoginName=否, lockedUser=lockedUser, operator=1, moveUserInfo=moveUserInfo, solt=123, password=1, areaName=昆明市, sexName=男, statusName=正常, resetPsw=resetPsw, lastLoginSuccessTime=2018-05-29 15:48:52.0, id=5, dataUpdate=dataUpdate, birthWindowFlag=1, operatorId=1, wxNum=18888888884, userEdit=userEdit, syncFlag=1, roleId=7, sex=2, syncFlagName=是, hotAgentFlag=1, isValid=1, mobile=18888888884, deptId=BSJ010101010201, updateTime=2018-05-29 15:49:35.0, sortNo=1, realName=呈贡一区一店一组组员1, companyId=BSJ01, areaCode=530100, permissionSet=permissionSet, employee_num=006, userTypeName=是, roleName=组员, userType=1, parentMobile=13577000337, status=1}] 
	 * @throws
	 */
	public List<Map<String, Object>> queryUserInfoByConn(Map<String,Object> paraMap);
	/**
	 * 根据电话号码判断用户是否存在 
	 * @Title: jurgeUserExistsByMobile
	 * @author: zuoyuntao  
	 * @Description:根据电话号码判断用户是否存在
	 * @param mobile
	 * @return      
	 * boolean true/false   
	 * @throws
	 */
	public boolean jurgeUserExistsByMobile(String mobile);
	/**
	 * 添加或修改用户数据信息
	 * @Title: saveOrUpdateUserInfo   
	 * @Description: 添加或修改用户数据信息  
	 * @param: saveInfo：要保存的数据对象
	 * {"entryDate":"2018-05-31","syncFlag":"0","roleId":"3","idCode":"522426198607080816","sex":"1","hotAgentFlag":"0","ip":"127.0.0.1","mobile":"18999665555","deptId":"BSJ010101010101","postId":"1","birthDate":"2018-05-01","sortNo":"65","realName":"测试","areaCode":"530103","employee_num":"测试","id":"8","birthWindowFlag":"0","email":"fdasf@163.com"}
	 * @throws
	 */
	public void saveOrUpdateUserInfo(Map<String,Object> saveInfo);
	/**
	 * 保存用户数据更新日志数据信息
	 * @Title: saveOrUpdateUserInfo   
	 * @Description: 保存用户数据更新日志数据信息  
	 * @param: @param saveInfo：要保存的数据对象
	 * @return: void      
	 * @throws
	 */
	public void saveUserActionLog(Map<String,Object> saveInfo);
	/**
	 * 根据用户ID锁定用户信息
	 * @Title: lockedUserInfo   
	 * @Description: 根据用户ID锁定用户信息
	 * @param: paraMap:参数对象  ,{id=9, status=0}    
	 * @return: void      
	 * @throws
	 */
	public void lockedUserInfo(Map<String,Object> paraMap);
	/**
	 * 解锁用户
	 * @Title: unLockedUserInfo
	 * @author: zuoyuntao  
	 * @Description: 解锁用户  
	 * @param: paraMap {id=9, status=01}     
	 * @return: void      
	 * @throws
	 */
	public void unLockedUserInfo(Map<String,Object> paraMap);
	/**
	 * 重置用户密码
	 * @Title: resetUserPassword   
	 * @Description: 重置用户密码   
	 * @param: paraMap：参数对象 {password=123456, id=3}
	 * @return: void      
	 * @throws
	 */
	public void resetUserPassword(Map<String,Object> paraMap);
	
	/**
	 * 查询部门信息创建部门树
	 * @Title: queryDeptInfoToCreateTree
	 * @author: zuoyuntao  
	 * @Description: 查询部门信息创建部门树   
	 * @param: roleGroup：当前用户所属角色组
	 * @param 当前用户id
	 * @return: List<Map<String,Object>>  
	 * 部门信息：[{"maxLevel":"5","name":"昆明市","code":"BSJ0101","parentCode":"","level":"1"},{"maxLevel":"5","name":"呈贡区","code":"BSJ010101","parentCode":"BSJ0101","level":"2"},{"maxLevel":"5","name":"呈贡一区","code":"BSJ01010101","parentCode":"BSJ010101","level":"3"},{"maxLevel":"5","name":"呈贡一区一店","code":"BSJ0101010101","parentCode":"BSJ01010101","level":"4"},{"maxLevel":"5","name":"呈贡一区一店一组","code":"BSJ010101010101","parentCode":"BSJ01010101","level":"5"},{"maxLevel":"5","name":"呈贡一区一店二组","code":"BSJ010101010102","parentCode":"BSJ0101010101","level":"5"},{"maxLevel":"5","name":"呈贡一区二店","code":"BSJ0101010102","parentCode":"BSJ01010101","level":"4"},{"maxLevel":"5","name":"呈贡一区二店一组","code":"BSJ010101010201","parentCode":"BSJ0101010102","level":"5"},{"maxLevel":"5","name":"呈贡一区二店二组","code":"BSJ010101010202","parentCode":"BSJ0101010102","level":"5"},{"maxLevel":"5","name":"呈贡二区1","code":"BSJ01010102","parentCode":"BSJ010101","level":"3"},{"maxLevel":"5","name":"呈贡二区一店","code":"BSJ0101010201","parentCode":"BSJ01010102","level":"4"},{"maxLevel":"5","name":"呈贡二区一店一组","code":"BSJ010101020101","parentCode":"BSJ0101010201","level":"5"},{"maxLevel":"5","name":"呈贡二区二店","code":"BSJ0101010202","parentCode":"BSJ01010102","level":"4"},{"maxLevel":"5","name":"呈贡二区二店一组","code":"BSJ010101020201","parentCode":"BSJ0101010202","level":"5"},{"maxLevel":"5","name":"官渡区","code":"BSJ010102","parentCode":"BSJ0101","level":"2"},{"maxLevel":"5","name":"大理市","code":"BSJ0102","level":"1"},{"maxLevel":"5","name":"祥云县","code":"BSJ010201","parentCode":"BSJ0102","level":"2"},{"maxLevel":"5","name":"禾甸镇","code":"BSJ01020101","parentCode":"BSJ010201","level":"3"},{"maxLevel":"5","name":"A组","code":"BSJ0103","parentCode":"","level":"1"},{"maxLevel":"5","name":"B租","code":"BSJ010301","parentCode":"BSJ0103","level":"2"}]
	 * 介绍人信息：[{"maxLevel":6.0,"name":"昆明市","code":"BSJ0101","parentCode":"","level":"1"},{"maxLevel":6.0,"name":"呈贡区","code":"BSJ010101","parentCode":"BSJ0101","level":"2"},{"maxLevel":6.0,"name":"呈贡一区","code":"BSJ01010101","parentCode":"BSJ010101","level":"3"},{"maxLevel":6.0,"name":"呈贡一区一店","code":"BSJ0101010101","parentCode":"BSJ01010101","level":"4"},{"maxLevel":6.0,"name":"呈贡一区一店一组","code":"BSJ010101010101","parentCode":"BSJ01010101","level":"5"},{"maxLevel":6.0,"name":"呈贡一区一店二组","code":"BSJ010101010102","parentCode":"BSJ0101010101","level":"5"},{"maxLevel":6.0,"name":"呈贡一区二店","code":"BSJ0101010102","parentCode":"BSJ01010101","level":"4"},{"maxLevel":6.0,"name":"呈贡一区二店一组","code":"BSJ010101010201","parentCode":"BSJ0101010102","level":"5"},{"maxLevel":6.0,"name":"呈贡一区二店二组","code":"BSJ010101010202","parentCode":"BSJ0101010102","level":"5"},{"maxLevel":6.0,"name":"呈贡二区1","code":"BSJ01010102","parentCode":"BSJ010101","level":"3"},{"maxLevel":6.0,"name":"呈贡二区一店","code":"BSJ0101010201","parentCode":"BSJ01010102","level":"4"},{"maxLevel":6.0,"name":"呈贡二区一店一组","code":"BSJ010101020101","parentCode":"BSJ0101010201","level":"5"},{"maxLevel":6.0,"name":"呈贡二区二店","code":"BSJ0101010202","parentCode":"BSJ01010102","level":"4"},{"maxLevel":6.0,"name":"呈贡二区二店一组","code":"BSJ010101020201","parentCode":"BSJ0101010202","level":"5"},{"maxLevel":6.0,"name":"官渡区","code":"BSJ010102","parentCode":"BSJ0101","level":"2"},{"maxLevel":6.0,"name":"大理市","code":"BSJ0102","level":"1"},{"maxLevel":6.0,"name":"祥云县","code":"BSJ010201","parentCode":"BSJ0102","level":"2"},{"maxLevel":6.0,"name":"禾甸镇","code":"BSJ01020101","parentCode":"BSJ010201","level":"3"},{"maxLevel":6.0,"name":"A组","code":"BSJ0103","parentCode":"","level":"1"},{"maxLevel":6.0,"name":"B租","code":"BSJ010301","parentCode":"BSJ0103","level":"2"},{"maxLevel":6.0,"name":"呈贡一区一店店长","code":"2","parentCode":"BSJ0101010101","level":"6"},{"maxLevel":6.0,"name":"呈贡一区一店一组组员1","code":"4","parentCode":"BSJ010101010101","level":"6"},{"maxLevel":6.0,"name":"呈贡一区一店一组组长","code":"3","parentCode":"BSJ010101010101","level":"6"},{"maxLevel":6.0,"name":"测试","code":"8","parentCode":"BSJ010101010101","level":"6"},{"maxLevel":6.0,"name":"测试","code":"9","parentCode":"BSJ010101010101","level":"6"},{"maxLevel":6.0,"name":"测试--99++","code":"7","parentCode":"BSJ010101010102","level":"6"},{"maxLevel":6.0,"name":"呈贡一区二店店长","code":"6","parentCode":"BSJ0101010102","level":"6"},{"maxLevel":6.0,"name":"呈贡一区一店一组组员1","code":"5","parentCode":"BSJ010101010201","level":"6"},{"maxLevel":6.0,"name":"超级管理员","code":"1","parentCode":"BSJ0103","level":"6"},{"maxLevel":6.0,"name":"城市管理员1号","code":"10","parentCode":"BSJ0103","level":"6"},{"maxLevel":6.0,"name":"普通角色代表","code":"11","parentCode":"BSJ0103","level":"6"}]
	 * @throws
	 */
	public List<Map<String,Object>> queryDeptInfoToCreateTree(String roleGroup,String userId);
	
	/**
	 * 根据区划的编码做向后模糊查询
	 * @Title: queryAreaLikeCode
	 * @author: zuoyuntao  
	 * @Description: 根据区划的编码做向后模糊查询  
	 * @param: @param areaCode
	 * @param: @return      
	 * @return: List<Map<String,Object>>    
	 * areaList 行政区划[{"name":"北京市","code":"110000","parentCode":"0","level":"1"},{"name":"市辖区","code":"110100","parentCode":"110000","level":"2"},{"name":"东城区","code":"110101","parentCode":"110100","level":"3"},{"name":"西城区","code":"110102","parentCode":"110100","level":"3"},{"name":"朝阳区","code":"110105","parentCode":"110100","level":"3"},{"name":"丰台区","code":"110106","parentCode":"110100","level":"3"},{"name":"石景山区","code":"110107","parentCode":"110100","level":"3"},{"name":"海淀区","code":"110108","parentCode":"110100","level":"3"},{"name":"门头沟区","code":"110109","parentCode":"110100","level":"3"},{"name":"房山区","code":"110111","parentCode":"110100","level":"3"},{"name":"通州区","code":"110112","parentCode":"110100","level":"3"},{"name":"顺义区","code":"110113","parentCode":"110100","level":"3"},{"name":"昌平区","code":"110114","parentCode":"110100","level":"3"},{"name":"大兴区","code":"110115","parentCode":"110100","level":"3"},{"name":"怀柔区","code":"110116","parentCode":"110100","level":"3"},{"name":"平谷区","code":"110117","parentCode":"110100","level":"3"},{"name":"市辖县","code":"110200","parentCode":"110000","level":"2"},{"name":"密云县","code":"110228","parentCode":"110200","level":"3"},{"name":"延庆县","code":"110229","parentCode":"110200","level":"3"},{"name":"天津市","code":"120000","parentCode":"0","level":"1"},{"name":"市辖区","code":"120100","parentCode":"120000","level":"2"},{"name":"和平区","code":"120101","parentCode":"120100","level":"3"},{"name":"河东区","code":"120102","parentCode":"120100","level":"3"},{"name":"河西区","code":"120103","parentCode":"120100","level":"3"},{"name":"南开区","code":"120104","parentCode":"120100","level":"3"},{"name":"河北区","code":"120105","parentCode":"120100","level":"3"},{"name":"红桥区","code":"120106","parentCode":"120100","level":"3"},{"name":"东丽区","code":"120110","parentCode":"120100","level":"3"},{"name":"西青区","code":"120111","parentCode":"120100","level":"3"},{"name":"津南区","code":"120112","parentCode":"120100","level":"3"},{"name":"北辰区","code":"120113","parentCode":"120100","level":"3"},{"name":"武清区","code":"120114","parentCode":"120100","level":"3"},{"name":"宝坻区","code":"120115","parentCode":"120100","level":"3"},{"name":"滨海新区","code":"120116","parentCode":"120100","level":"3"},{"name":"市辖县","code":"120200","parentCode":"120000","level":"2"},{"name":"宁河县","code":"120221","parentCode":"120200","level":"3"},{"name":"静海县","code":"120223","parentCode":"120200","level":"3"},{"name":"蓟县","code":"120225","parentCode":"120200","level":"3"},{"name":"河北省","code":"130000","parentCode":"0","level":"1"},{"name":"石家庄市","code":"130100","parentCode":"130000","level":"2"},{"name":"市辖区","code":"130101","parentCode":"130100","level":"3"},{"name":"长安区","code":"130102","parentCode":"130100","level":"3"},{"name":"桥东区","code":"130103","parentCode":"130100","level":"3"},{"name":"桥西区","code":"130104","parentCode":"130100","level":"3"},{"name":"新华区","code":"130105","parentCode":"130100","level":"3"},{"name":"井陉矿区","code":"130107","parentCode":"130100","level":"3"},{"name":"裕华区","code":"130108","parentCode":"130100","level":"3"},{"name":"井陉县","code":"130121","parentCode":"130100","level":"3"},{"name":"正定县","code":"130123","parentCode":"130100","level":"3"},{"name":"栾城县","code":"130124","parentCode":"130100","level":"3"},{"name":"行唐县","code":"130125","parentCode":"130100","level":"3"},{"name":"灵寿县","code":"130126","parentCode":"130100","level":"3"},{"name":"高邑县","code":"130127","parentCode":"130100","level":"3"},{"name":"深泽县","code":"130128","parentCode":"130100","level":"3"},{"name":"赞皇县","code":"130129","parentCode":"130100","level":"3"},{"name":"无极县","code":"130130","parentCode":"130100","level":"3"},{"name":"平山县","code":"130131","parentCode":"130100","level":"3"},{"name":"元氏县","code":"130132","parentCode":"130100","level":"3"},{"name":"赵县","code":"130133","parentCode":"130100","level":"3"},{"name":"辛集市","code":"130181","parentCode":"130100","level":"3"},{"name":"藁城市","code":"130182","parentCode":"130100","level":"3"},{"name":"晋州市","code":"130183","parentCode":"130100","level":"3"},{"name":"新乐市","code":"130184","parentCode":"130100","level":"3"},{"name":"鹿泉市","code":"130185","parentCode":"130100","level":"3"},{"name":"唐山市","code":"130200","parentCode":"130000","level":"2"},{"name":"市辖区","code":"130201","parentCode":"130200","level":"3"},{"name":"路南区","code":"130202","parentCode":"130200","level":"3"},{"name":"路北区","code":"130203","parentCode":"130200","level":"3"},{"name":"古冶区","code":"130204","parentCode":"130200","level":"3"},{"name":"开平区","code":"130205","parentCode":"130200","level":"3"},{"name":"丰南区","code":"130207","parentCode":"130200","level":"3"},{"name":"丰润区","code":"130208","parentCode":"130200","level":"3"},{"name":"曹妃甸区","code":"130209","parentCode":"130200","level":"3"},{"name":"滦县","code":"130223","parentCode":"130200","level":"3"},{"name":"滦南县","code":"130224","parentCode":"130200","level":"3"},{"name":"乐亭县","code":"130225","parentCode":"130200","level":"3"},{"name":"迁西县","code":"130227","parentCode":"130200","level":"3"},{"name":"玉田县","code":"130229","parentCode":"130200","level":"3"},{"name":"遵化市","code":"130281","parentCode":"130200","level":"3"},{"name":"迁安市","code":"130283","parentCode":"130200","level":"3"},{"name":"秦皇岛市","code":"130300","parentCode":"130000","level":"2"},{"name":"市辖区","code":"130301","parentCode":"130300","level":"3"},{"name":"海港区","code":"130302","parentCode":"130300","level":"3"},{"name":"山海关区","code":"130303","parentCode":"130300","level":"3"},{"name":"北戴河区","code":"130304","parentCode":"130300","level":"3"},{"name":"青龙满族自治县","code":"130321","parentCode":"130300","level":"3"},{"name":"昌黎县","code":"130322","parentCode":"130300","level":"3"},{"name":"抚宁县","code":"130323","parentCode":"130300","level":"3"},{"name":"卢龙县","code":"130324","parentCode":"130300","level":"3"},{"name":"邯郸市","code":"130400","parentCode":"130000","level":"2"},{"name":"市辖区","code":"130401","parentCode":"130400","level":"3"},{"name":"邯山区","code":"130402","parentCode":"130400","level":"3"},{"name":"丛台区","code":"130403","parentCode":"130400","level":"3"},{"name":"复兴区","code":"130404","parentCode":"130400","level":"3"},{"name":"峰峰矿区","code":"130406","parentCode":"130400","level":"3"},{"name":"邯郸县","code":"130421","parentCode":"130400","level":"3"},{"name":"临漳县","code":"130423","parentCode":"130400","level":"3"},{"name":"成安县","code":"130424","parentCode":"130400","level":"3"},{"name":"大名县","code":"130425","parentCode":"130400","level":"3"},{"name":"涉县","code":"130426","parentCode":"130400","level":"3"},{"name":"磁县","code":"130427","parentCode":"130400","level":"3"},{"name":"肥乡县","code":"130428","parentCode":"130400","level":"3"},{"name":"永年县","code":"130429","parentCode":"130400","level":"3"},{"name":"邱县","code":"130430","parentCode":"130400","level":"3"},{"name":"鸡泽县","code":"130431","parentCode":"130400","level":"3"},{"name":"广平县","code":"130432","parentCode":"130400","level":"3"},{"name":"馆陶县","code":"130433","parentCode":"130400","level":"3"},{"name":"魏县","code":"130434","parentCode":"130400","level":"3"},{"name":"曲周县","code":"130435","parentCode":"130400","level":"3"},{"name":"武安市","code":"130481","parentCode":"130400","level":"3"},{"name":"邢台市","code":"130500","parentCode":"130000","level":"2"},{"name":"市辖区","code":"130501","parentCode":"130500","level":"3"},{"name":"桥东区","code":"130502","parentCode":"130500","level":"3"},{"name":"桥西区","code":"130503","parentCode":"130500","level":"3"},{"name":"邢台县","code":"130521","parentCode":"130500","level":"3"},{"name":"临城县","code":"130522","parentCode":"130500","level":"3"},{"name":"内丘县","code":"130523","parentCode":"130500","level":"3"},{"name":"柏乡县","code":"130524","parentCode":"130500","level":"3"},{"name":"隆尧县","code":"130525","parentCode":"130500","level":"3"},{"name":"任县","code":"130526","parentCode":"130500","level":"3"},{"name":"南和县","code":"130527","parentCode":"130500","level":"3"},{"name":"宁晋县","code":"130528","parentCode":"130500","level":"3"},{"name":"巨鹿县","code":"130529","parentCode":"130500","level":"3"},{"name":"新河县","code":"130530","parentCode":"130500","level":"3"},{"name":"广宗县","code":"130531","parentCode":"130500","level":"3"},{"name":"平乡县","code":"130532","parentCode":"130500","level":"3"},{"name":"威县","code":"130533","parentCode":"130500","level":"3"},{"name":"清河县","code":"130534","parentCode":"130500","level":"3"},{"name":"临西县","code":"130535","parentCode":"130500","level":"3"},{"name":"南宫市","code":"130581","parentCode":"130500","level":"3"},{"name":"沙河市","code":"130582","parentCode":"130500","level":"3"},{"name":"保定市","code":"130600","parentCode":"130000","level":"2"},{"name":"市辖区","code":"130601","parentCode":"130600","level":"3"},{"name":"新市区","code":"130602","parentCode":"130600","level":"3"},{"name":"北市区","code":"130603","parentCode":"130600","level":"3"},{"name":"南市区","code":"130604","parentCode":"130600","level":"3"},{"name":"满城县","code":"130621","parentCode":"130600","level":"3"},{"name":"清苑县","code":"130622","parentCode":"130600","level":"3"},{"name":"涞水县","code":"130623","parentCode":"130600","level":"3"},{"name":"阜平县","code":"130624","parentCode":"130600","level":"3"},{"name":"徐水县","code":"130625","parentCode":"130600","level":"3"},{"name":"定兴县","code":"130626","parentCode":"130600","level":"3"},{"name":"唐县","code":"130627","parentCode":"130600","level":"3"},{"name":"高阳县","code":"130628","parentCode":"130600","level":"3"},{"name":"容城县","code":"130629","parentCode":"130600","level":"3"},{"name":"涞源县","code":"130630","parentCode":"130600","level":"3"},{"name":"望都县","code":"130631","parentCode":"130600","level":"3"},{"name":"安新县","code":"130632","parentCode":"130600","level":"3"},{"name":"易县","code":"130633","parentCode":"130600","level":"3"},{"name":"曲阳县","code":"130634","parentCode":"130600","level":"3"},{"name":"蠡县","code":"130635","parentCode":"130600","level":"3"},{"name":"顺平县","code":"130636","parentCode":"130600","level":"3"},{"name":"博野县","code":"130637","parentCode":"130600","level":"3"},{"name":"雄县","code":"130638","parentCode":"130600","level":"3"},{"n...
	 * @throws
	 */
	public List<Map<String,Object>> queryAreaLikeCode(String areaCode);
	
	/**
	 * 查询介绍人信息 
	 * @Title: queryReferenInfoToCreateTree
	 * @author: zuoyuntao  
	 * @Description:查询介绍人信息
	 * @param areaCode 行政区划
	 * @return      
	 * List<Map<String,Object>> JSON 格式为：
	 */
	public List<Map<String,Object>> queryReferenInfoToCreateTree(String areaCode);
	
	/**
	 * 查询经纪人信息（code是userId和agentId的拼接）
	 * @Title: queryReferenInfoToCreateTree
	 * @author: zuoyuntao  
	 * @Description:查询经纪人信息（code是userId和agentId的拼接）
	 * @param areaCode 行政区划
	 * @return      
	 * List<Map<String,Object>> JSON 格式为：
	 */
	public List<Map<String,Object>> queryAgentInfoToCreateTree(String areaCode);
	
	/**     
	 * @Description: 保存附件信息  
	 * @param files 待保存的附件列表
	 * @return: boolean  
	 * @see com.bashiju.deal.service.DealService#saveAttachments(java.util.List)   
	 */
	public void saveAttachments(Map<String,Object> files);
}
