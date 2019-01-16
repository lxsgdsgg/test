/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  EmployeeWagesConfigurateServiceImpl.java   
 * @Package com.bashiju.fin.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年9月11日 上午9:27:36   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.fin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.ExamineStatusEnum;
import com.bashiju.enums.MenuEnum;
import com.bashiju.fin.mapper.EmployeeWagesInfoMapper;
import com.bashiju.fin.service.EmployeeWagesInfoService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**   
 * @ClassName:  EmployeeWagesInfoServiceImpl   
 * @Description: 员工薪资信息
 * @author: liwen
 * @date:   2018年9月27日 上午11:27:36   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
@SystemServiceLog(operationType="员工薪资信息")
public class EmployeeWagesInfoServiceImpl extends CommonSqlServie implements EmployeeWagesInfoService {

	@Autowired
	private EmployeeWagesInfoMapper employeeWagesInfoMapper;
	
	@Autowired
	private DataAuthHelper dataAuthHelper;
	
	/**
	 * 查询员工薪资信息   
	 * @Description: 查询员工薪资信息   
	 * @param userName 员工名称
	 * @param months 月份
	 * @return: List<Map<String,Object>>
	 */
	@Override
	public List<Map<String, Object>> queryEmployeeWagesInfos(String companyId,Long userId,String deptId,String startMonth,String endMonth) {
		dataAuthHelper.auth(MenuEnum.MENU_121.getCode(), UserThreadLocal.get().get("id").toString());
		return employeeWagesInfoMapper.queryEmployeeWagesInfos(companyId,userId,deptId,startMonth,endMonth);
	}

	/**
	 * 查询员工薪资信息(分页)  
	 * @Description: 查询员工薪资信息(分页)   
	 * @param userName 员工名称
	 * @param months 月份
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: Page<Map<String,Object>>
	 */
	@Override
	public Page<Map<String, Object>> queryEmployeeWagesInfos(String companyId,Long userId,String deptId,String startMonth,String endMonth,int pageNum,int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<Map<String,Object>> page = (Page<Map<String, Object>>) this.queryEmployeeWagesInfos(companyId,userId,deptId,startMonth,endMonth);
		return page;
	}

	/**
	 * 保存员工薪资福利信息
	 * @Description: 保存员工薪资福利信息   
	 * @param map 待保存的员工薪资福利
	 * @return: boolean
	 */
	@Override
	@SystemServiceLog(operationType="保存员工薪资福利")
	public boolean updateEmployeeWelfarePay(List<Map<String, Object>> listmap) {
		int welfarePay = 0;
		int id = Integer.parseInt(listmap.get(0).get("wagesInfoId").toString());
		if(listmap == null || listmap.size()<=0)
			throw new BusinessException("待保存的员工薪资福利信息不允许为空");
		
		 for(int i=0;i<listmap.size();i++) {
			 Double money = Double.parseDouble(listmap.get(i).get("companyPay").toString());
			 welfarePay = (int) (welfarePay + money*100);
			 listmap.get(i).put("companyPay", (int)(money*100));
		 }
		//保存福利明细
		 boolean res = this.batchCommonOperationDatabase(listmap, "finance_employee_wagesInfo_detail", "id", false);
		 Map<String,Object> mm = new HashMap<>();
		 mm.put("id", id);
		 mm.put("welfarePay", welfarePay);
		 //修改员工工资福利项
		 this.commonOperationDatabase(mm, "finance_employee_wagesInfo", "id", false);
		 ExecutionResult.descFormat("", "编辑员工薪资福利");
		return true;
	}
	
	@Override
	@SystemServiceLog(operationType="保存员工基本工资")
	public boolean updateEmployeeBasePay(String id,String basePay) {
		Map<String,Object> mm = new HashMap<>();
		mm.put("id", id);
		mm.put("basePay", Integer.parseInt(basePay)*100);
		this.commonOperationDatabase(mm, "finance_employee_wagesInfo", "id", false);
		return true;
	}
	
	/**
	 * 审核员工薪资福利信息 
	 * @Description: 审核员工薪资福利信息 
	 * @param map 待审核的薪资信息
	 * @return: boolean
	 * @see com.bashiju.fin.service.EmployeeWagesInfoService#saveExamineUserDeserts(java.util.Map)   
	 */
	@SuppressWarnings("unchecked")
	@Override
	@SystemServiceLog(operationType="审核员工薪资")
	public boolean saveExamineUserDeserts(Map<String, Object> map) {
		
		if(map == null || map.size()<=0)
			throw new BusinessException("待审核的员工薪资信息不允许为空");
		if(!map.containsKey("examineStatus") || StringUtils.isEmpty((String)map.get("examineStatus")))
			throw new BusinessException("审核状态不允许为空");
		if(!ExamineStatusEnum.enumMap.containsKey((String)map.get("examineStatus")))
			throw new BusinessException("请选择正确的审核状态");
		Map<String,Object> user = UserThreadLocal.get();
		List<Map<String, Object>> employees =  employeeWagesInfoMapper.queryEmployeeWagesInfos(UserThreadLocal.get().get("companyId").toString(),null,null,map.get("months").toString(),map.get("months").toString());
		if(employees.size()>0) {//编辑
			List<Map<String,Object>> examineList = new ArrayList<>();
			for(int i=0;i<employees.size();i++) {
				Map<String,Object> cm = new HashMap<>();
				cm.putAll(map);
				cm.put("id",employees.get(i).get("id"));
				cm.put("auditorId", user.get("id"));
				cm.put("auditor", user.get("realName"));
				cm.put("auditTime", new Date());
				examineList.add(cm);
			}
			
			boolean res = this.batchCommonOperationDatabase(examineList, "finance_employee_wagesInfo","id", false);
			ExecutionResult.descFormat("", "审核员工薪资");
		}else 
			throw new BusinessException("员工薪资编号不允许为空");
		return true;
	}

	/**
	 * 删除薪资配置方案
	 * @Description:删除薪资配置方案   
	 * @param wagesId 薪资配置方案编号
	 * @return: boolean
	 * @see com.bashiju.fin.service.EmployeeWagesConfigurateService#delEmployeeWagesConfigurate(java.lang.String)   
	 */
	@Override
	public boolean delEmployeeWagesConfigurate(String wagesId) {
		if(StringUtils.isEmpty(wagesId))
			return false;
		boolean result = this.delData("sys_employee_wagesConfigurate", "id", wagesId, false);
		if(result) {
			ExecutionResult.descFormat(wagesId, "删除员工薪资");
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * 生成员工工资 
	 * 生成员工工资  
	 * @param months 月份(2018-01)
	 * @param companyId 公司编号
	 * @param userIds 用户id(多个用逗号隔开)
	 * @return   
	 * @see com.bashiju.fin.service.EmployeeWagesInfoService#makeEmployeeWages(java.util.Map)
	 */
	public boolean makeEmployeeWages(String months,String companyId,String userIds) {
		if(StringUtils.isEmpty(months))
			throw new BusinessException("月份不允许为空");
		if(StringUtils.isEmpty(companyId))
			throw new BusinessException("请选择要计算的公司编号");
		Map<?,?> user = UserThreadLocal.get();
		employeeWagesInfoMapper.makeEmployeeWages(months,companyId,userIds
				,user.get("deptId").toString(),user.get("id").toString());
		return true;
	}

}
