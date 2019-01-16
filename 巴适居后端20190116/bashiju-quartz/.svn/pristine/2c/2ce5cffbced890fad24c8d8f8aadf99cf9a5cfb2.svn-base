package com.bashiju.quartz.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.api.DecisionConfigurateServiceApi;
import com.bashiju.enums.CustomerTransactionEnum;
import com.bashiju.enums.DecisionConfigurateEnum;
import com.bashiju.enums.LogBusinessOperateTypeEnum;
import com.bashiju.enums.LogBusinessSourceTypeEnum;
import com.bashiju.quartz.mapper.HouseAndCustomerStatusTaskMapper;
import com.bashiju.utils.pojo.BusinessLogModel;
import com.bashiju.utils.service.BusinessLogService;
import com.bashiju.utils.service.CommonSqlServie;

/**
 * 
 *  房源、客源相关数据重置服务
 * @ClassName:  ResetSourceService   
 * @Description:  房源、客源相关数据重置服务
 * @author: wangkaifa
 * @date:   2018年7月7日 下午4:36:23       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class ResetSourceService extends CommonSqlServie{

	@Autowired
	DecisionConfigurateServiceApi decisionConfigurateServiceApi;
	@Autowired
	HouseAndCustomerStatusTaskMapper houseAndCustomerStatusTaskMapper;
	
	/**
	 * 
		 * 删除今日浏览
		 * @Description: 进入菜单主页面（详细描述）  
		 * void
	 */
	public void deleteTodaySee() {
		try {
			Integer count=houseAndCustomerStatusTaskMapper.deleteAllTodaySee();
			System.out.println("清除房源今日浏览："+count+"条");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
		 * 自动取消房源置顶
		 * @Description: 自动取消房源置顶
		 * void
	 */
	public void resetTopStatus() {
		try {
			//查询房源最长置顶天数决策配置
			List<Map<String,Object>> configurates=decisionConfigurateServiceApi.queryDecisionConfigurate(DecisionConfigurateEnum.DECISION_15.getCode());
			if (configurates!=null&&configurates.size()>0) {
				List<Map<String, Object>> houseList=new ArrayList<>();
				for (Map<String, Object> configurate : configurates) {
					try {
						//房源最长置顶天数
						Integer day=Integer.parseInt(configurate.get("val").toString());
						//城市代码
						String cityCode=configurate.get("cityCode").toString();
						//查询被取消置顶的房源，插入日志
						List<Map<String, Object>> list=houseAndCustomerStatusTaskMapper.queryTopHouseIdByCityCode(day, cityCode);
						if (list!=null&&list.size()>0) {
							houseList.addAll(list);
						}
						//取消置顶
						int count=houseAndCustomerStatusTaskMapper.updateIsTop(day, cityCode);
						System.out.println("取消房源置顶："+count+"条");
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (houseList.size()>0) {
					saveLog(houseList,"房源置顶到期自动取消",1);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
		 * 房源自动撤单
		 * @Description: 房源自动撤单
		 * void
	 */
	public void houseCancelNotice () {
		try {
			List<Map<String,Object>> configurates=decisionConfigurateServiceApi.queryDecisionConfigurate(DecisionConfigurateEnum.DECISION_11.getCode());
			if (configurates!=null&&configurates.size()>0) {
				List<Map<String, Object>> houseList=new ArrayList<>();
				for (Map<String, Object> configurate : configurates) {
					try {
						//房源最长通知撤单等待天数
						Integer day=Integer.parseInt(configurate.get("val").toString());
						//城市代码
						String cityCode=configurate.get("cityCode").toString();
						
						//查询被撤单的房源，插入日志
						List<Map<String, Object>> list=houseAndCustomerStatusTaskMapper.querycancelNoticeHouseByCityCode(day, cityCode);
						if (list!=null&&list.size()>0) {
							houseList.addAll(list);
						}
						//取消置顶
						int count=houseAndCustomerStatusTaskMapper.updateHouseIsCancelNotice(day, cityCode);
						System.out.println("房源自动撤单："+count+"条");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (houseList.size()>0) {
					saveLog(houseList,"房源通知撤单到期自动撤单",1);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
		 * 求购客源自动撤单
		 * @Description: 求购客源自动撤单  
		 * void
	 */
	public void buyCustomerCancelNotice () {
		try {
			List<Map<String,Object>> configurates=decisionConfigurateServiceApi.queryDecisionConfigurate(DecisionConfigurateEnum.DECISION_20.getCode());
			if (configurates!=null&&configurates.size()>0) {
				List<Map<String, Object>> customerList=new ArrayList<>();
				for (Map<String, Object> configurate : configurates) {
					try {
						//客源最长通知撤单等待天数
						Integer day=Integer.parseInt(configurate.get("val").toString());
						//城市代码
						String cityCode=configurate.get("cityCode").toString();
						
						//查询被撤单的客源，插入日志
						List<Map<String, Object>> list=houseAndCustomerStatusTaskMapper.querycancelNoticeCustomerByCityCode(day, cityCode,Integer.parseInt(CustomerTransactionEnum.BUY.getCode()));
						if (list!=null&&list.size()>0) {
							customerList.addAll(list);
						}
						//自动撤单
						int count=houseAndCustomerStatusTaskMapper.updateCustomerIsCancelNotice(day, cityCode,Integer.parseInt(CustomerTransactionEnum.BUY.getCode()));
						System.out.println("求购客源自动撤单："+count+"条");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (customerList.size()>0) {
					saveLog(customerList,"求购客源通知撤单到期自动撤单",0);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
		 * 求租客源自动撤单
		 * @Description: 求租客源自动撤单  
		 * void
	 */
	public void rentCustomerCancelNotice () {
		try {
			List<Map<String,Object>> configurates=decisionConfigurateServiceApi.queryDecisionConfigurate(DecisionConfigurateEnum.DECISION_29.getCode());
			if (configurates!=null&&configurates.size()>0) {
				List<Map<String, Object>> customerList=new ArrayList<>();
				for (Map<String, Object> configurate : configurates) {
					try {
						//客源最长通知撤单等待天数
						Integer day=Integer.parseInt(configurate.get("val").toString());
						//城市代码
						String cityCode=configurate.get("cityCode").toString();
						
						//查询被撤单的客源，插入日志
						List<Map<String, Object>> list=houseAndCustomerStatusTaskMapper.querycancelNoticeCustomerByCityCode(day, cityCode,Integer.parseInt(CustomerTransactionEnum.RENT.getCode()));
						if (list!=null&&list.size()>0) {
							customerList.addAll(list);
						}
						//自动撤单
						int count=houseAndCustomerStatusTaskMapper.updateCustomerIsCancelNotice(day, cityCode,Integer.parseInt(CustomerTransactionEnum.RENT.getCode()));
						System.out.println("求租客源自动撤单："+count+"条");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (customerList.size()>0) {
					saveLog(customerList,"求租客源通知撤单到期自动撤单",0);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
		 * 保存定时取消置房源顶日志
		 * @Description: 保存定时取消置房源顶日志
		 * @param list [{houseId:房源编号,companyId:公司id}]
		 * void
	 */
	private void saveLog(List<Map<String, Object>> list,String content,int isHouse) {
		BusinessLogService businessLogService=new BusinessLogService();
		for (int j = 0; j < list.size(); j++) {			
			BusinessLogModel businessLogModel=new BusinessLogModel();
			businessLogModel.setSourceId(list.get(j).get("sourceId").toString());
			businessLogModel.setSourceCode(list.get(j).get("sourceCode").toString());
			businessLogModel.setCompanyId(list.get(j).get("companyId").toString());
			businessLogModel.setDepartmentId(list.get(j).get("departmentId").toString());
			businessLogModel.setDepartmentName(list.get(j).get("departmentName").toString());
			businessLogModel.setCompanyName(list.get(j).get("companyName").toString());
			businessLogModel.setCityCode(list.get(j).get("cityCode").toString());			
			businessLogModel.setBusinessTypeId(Integer.parseInt(list.get(j).get("businessTypeId").toString()));
			businessLogModel.setSourceTypeId(Integer.parseInt(list.get(j).get("sourceTypeId").toString()));
			businessLogModel.setOperatorId(0);
			businessLogModel.setOperator("定时操作");
			businessLogModel.setLogContent(content);
			businessLogModel.setRemark(list.get(j).get("remark").toString());
			if (isHouse>0) {
				businessLogModel.setOperatTypeId(Integer.parseInt(LogBusinessOperateTypeEnum.HOUSE_SYSTEM_OPERATE.getCode()));
				businessLogService.houseLogQuery(businessLogModel);
			}else {
				businessLogModel.setOperatTypeId(Integer.parseInt(LogBusinessOperateTypeEnum.CUSTOMER_SYSTEM_OPERATE.getCode()));
				businessLogService.customerLogQuery(businessLogModel);
			}			
		}
		
	}
	/**
	 * 
		 *  更新房源最近15天带看数量
		 * @Description:  更新房源最近15天带看数量 
		 * void
	 */
	public void twoWeekShowedCount() {
		try {
			houseAndCustomerStatusTaskMapper.updateViewingCount();
			houseAndCustomerStatusTaskMapper.updateSellViewingCount();
			houseAndCustomerStatusTaskMapper.updateRentViewingCount();
			houseAndCustomerStatusTaskMapper.updateProjectViewingCount();
			houseAndCustomerStatusTaskMapper.updateProjectQueryViewingCount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
		 *  自动关闭过期的公司开通城市
		 * @Description:  自动关闭过期的公司开通城市
		 * void
	 */
	public void updateCompanyOpenCityStatus() {
		try {
			houseAndCustomerStatusTaskMapper.updateCompanyOpenCityStatus();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
