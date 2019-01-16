/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  TotalPerformanceAnalysisController.java   
 * @Package com.bashiju.anal.controller      
 * @author: zuoyuntao     
 * @date:   2018年11月27日 上午10:10:40   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.anal.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.bashiju.anal.mapper.AnalCommonMapper;
import com.bashiju.anal.mapper.TotalPerformanceAnalysisMapper;
import com.bashiju.anal.pojo.Cell;
import com.bashiju.anal.pojo.IncomeTransverseParamsEnum;
import com.bashiju.anal.pojo.Row;
import com.bashiju.anal.pojo.Thead;
import com.bashiju.enums.CustomerTransactionEnum;
import com.bashiju.enums.DeptTypeEnum;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.util.StringUtil;

/**
 * 决策分析--全员业绩分析--控制器
 * @ClassName:TotalPerformanceAnalysisController
 * @Description:决策分析--全员业绩分析--控制器
 * @author:zuoyuntao
 * @date:2018年11月27日 上午10:10:40
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@RequestMapping(value="totalPerformanceAnalysis")
@Controller
public class TotalPerformanceAnalysisController {
	/**
	 * 公用的持久层对象
	 */
	@Autowired
	private AnalCommonMapper commonMapper;
	/**
	 * 全员业绩分析持久层对象
	 */
	@Autowired
	private TotalPerformanceAnalysisMapper mTotalPerformanceAnalysisMapper;
	/**
	 * 组装【全员业绩分析】报表表头 
	 * @Title: buildReportHeader
	 * @author: zuoyuntao  
	 * @Description:组装【全员业绩分析】报表表头 
	 * Thead
	 */
	private Thead buildReportHeader() {
		//封装表头
		Thead thead = new Thead();//定义表头对象
		List<Row> rows = new ArrayList<Row>(0);//定义表头行数
			
		//第一行
		List<Cell> cells1 = new ArrayList<Cell>(0);//定义表头列数
		List<List<Cell>> cells = new ArrayList<List<Cell>>(0);
		
		Cell.Builder areaName = new Cell.Builder();
		areaName.setField("areaName");
		areaName.setTitle("区");
		areaName.setTotalRowText("");
		areaName.setRowspan(2);
		areaName.setFixed("left");
		areaName.setWidth("120px;");
		cells1.add(areaName.build());
		
		Cell.Builder deptName = new Cell.Builder();
		deptName.setField("deptName");
		deptName.setTitle("门店");
		deptName.setTotalRowText("");
		deptName.setRowspan(2);
		deptName.setFixed("left");
		deptName.setWidth("120px;");
		cells1.add(deptName.build());
		
		cells1.add(new Cell.Builder().setTitle("买卖成交").setColspan(10).setAlign("center").build());
		cells1.add(new Cell.Builder().setTitle("租赁成交").setColspan(10).setAlign("center").build());
		cells1.add(new Cell.Builder().setTitle("一手成交").setColspan(10).setAlign("center").build());
		cells1.add(new Cell.Builder().setTitle("金融业务").setColspan(2).setAlign("center").build());
		cells.add(cells1);
		
		//第二行
		List<Cell> cells2 = new ArrayList<Cell>(0);//定义表头列数
		cells2.add(new Cell.Builder().setField("businessPicUpload").setTitle("买卖上传图片").setTotalRow(true).build());
//		cells2.add(new Cell.Builder().setField("bussinessPicUpload").setTitle("买卖上传图片").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("businessContractDeal").setTitle("买卖合同成交").setTotalRow(true).build());
//		cells2.add(new Cell.Builder().setField("businessCustomerInput").setTitle("买卖客户录入").setTotalRow(true).build());
//		cells2.add(new Cell.Builder().setField("businessCustomerDeal").setTitle("买卖客户成交").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("businessCustomerSourceInput").setTitle("买卖客源录入").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("businessCustomerSourceMaintain").setTitle("买卖客源维护").setTotalRow(true).build());
//		cells2.add(new Cell.Builder().setField("businessDeal").setTitle("买卖成交").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("businessHouseOpenPlate").setTitle("买卖房源开盘").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("businessHouseSourceInput").setTitle("买卖房源录入").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("businessHouseSourceMaintain").setTitle("买卖房源维护").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("businessKeysCarry").setTitle("买卖拿钥匙").setTotalRow(true).build());
//		cells2.add(new Cell.Builder().setField("businessCourtCase").setTitle("买卖案场").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("businessSoleEntrust").setTitle("买卖独家委托").setTotalRow(true).build());
//		cells2.add(new Cell.Builder().setField("businessKeysOwner").setTitle("买卖钥匙人").setTotalRow(true).build());
		
		Cell.Builder businessTotal = new Cell.Builder();
		businessTotal.setField("businessTotal");
		businessTotal.setTitle("买卖合计");
		cells2.add(businessTotal.build());
		
		cells2.add(new Cell.Builder().setField("rentPicUpload").setTitle("租赁上传图片").setTotalRow(true).build());
//		cells2.add(new Cell.Builder().setField("rentPicUpload").setTitle("租赁上传图片").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("rentContractDeal").setTitle("租赁合同成交").setTotalRow(true).build());
//		cells2.add(new Cell.Builder().setField("rentCustomerInput").setTitle("租赁客户录入").setTotalRow(true).build());
//		cells2.add(new Cell.Builder().setField("rentCustomerDeal").setTitle("租赁客户成交").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("rentCustomerSourceInput").setTitle("租赁客源录入").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("rentCustomerSourceMaintain").setTitle("租赁客源维护").setTotalRow(true).build());
//		cells2.add(new Cell.Builder().setField("rentDeal").setTitle("租赁成交").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("rentHouseOpenPlate").setTitle("租赁房源开盘").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("rentHouseSourceInput").setTitle("租赁房源录入").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("rentHouseSourceMaintain").setTitle("租赁房源维护").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("rentKeysCarry").setTitle("租赁拿钥匙").setTotalRow(true).build());
//		cells2.add(new Cell.Builder().setField("rentCourtCase").setTitle("租赁案场").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("rentSoleEntrust").setTitle("租赁独家委托").setTotalRow(true).build());
//		cells2.add(new Cell.Builder().setField("rentKeysOwner").setTitle("租赁钥匙人").setTotalRow(true).build());
		
		Cell.Builder rentTotal = new Cell.Builder();
		rentTotal.setField("rentTotal");
		rentTotal.setTitle("租赁合计");
		rentTotal.setStyle("color:green;");
		cells2.add(rentTotal.build());
		
		cells2.add(new Cell.Builder().setField("oneHandPicUpload").setTitle("一手上传图片").setTotalRow(true).build());
//		cells2.add(new Cell.Builder().setField("bussinessPicUpload").setTitle("一手上传图片").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("oneHandContractDeal").setTitle("一手合同成交").setTotalRow(true).build());
//		cells2.add(new Cell.Builder().setField("oneHandCustomerInput").setTitle("一手客户录入").setTotalRow(true).build());
//		cells2.add(new Cell.Builder().setField("oneHandCustomerDeal").setTitle("一手客户成交").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("oneHandCustomerSourceInput").setTitle("一手客源录入").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("oneHandCustomerSourceMaintain").setTitle("一手客源维护").setTotalRow(true).build());
//		cells2.add(new Cell.Builder().setField("oneHandDeal").setTitle("一手成交").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("oneHandHouseOpenPlate").setTitle("一手房源开盘").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("oneHandHouseSourceInput").setTitle("一手房源录入").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("oneHandHouseSourceMaintain").setTitle("一手房源维护").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("oneHandKeysCarry").setTitle("一手拿钥匙").setTotalRow(true).build());
//		cells2.add(new Cell.Builder().setField("oneHandCourtCase").setTitle("一手案场").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("oneHandSoleEntrust").setTitle("一手独家委托").setTotalRow(true).build());
//		cells2.add(new Cell.Builder().setField("oneHandKeysOwner").setTitle("一手钥匙人").setTotalRow(true).build());
		
		Cell.Builder oneHandTotal = new Cell.Builder();
		oneHandTotal.setField("oneHandTotal");
		oneHandTotal.setTitle("一手合计");
		cells2.add(oneHandTotal.build());
		
		cells2.add(new Cell.Builder().setField("financeCnt").setTitle("金融业绩").setTotalRow(true).build());
		Cell.Builder financeTotal = new Cell.Builder();
		financeTotal.setField("total");
		financeTotal.setTitle("合计");
		cells2.add(financeTotal.build());
		
		cells.add(cells2);
		rows.add(new Row.Builder().cells(cells).build());
		thead.setRows(rows);
		return thead;
	}
	/**
	 * @Description: 进入【全员业绩分析】页面   
	 * @param request
	 * @param response
	 * @return: ModelAndView
	 */
	@RequestMapping(value="pageMain")
	public ModelAndView statisticsTotalPerformance(HttpServletRequest request,HttpServletResponse response) {
		Map<?,?> user = UserThreadLocal.get();
		// 页面建模
		ModelAndView mv = new ModelAndView("totalPerformanceAnalysis/totalPerformanceAnalysis");
		//获取部门数据信息
		List<Map<String, Object>> deptList = commonMapper.queryDeptTrees(user.get("roleGroup").toString());
		// 门店信息
		mv.addObject("deptInfo", JSONObject.toJSONString(deptList));
		// 客源交易类型
		mv.addObject("customerTransactionType", CustomerTransactionEnum.enumMap);
		// 获取城市信息
		mv.addObject("cityInfo", commonMapper.queryCompanyCityInfo(user.get("roleGroup").toString(),user.get("id").toString(),user.get("companyId").toString()));
		// 组装表头信息
		mv.addObject("thead", JSONObject.toJSON(buildReportHeader()));
		// 部门类型信息
		mv.addObject("deptType", IncomeTransverseParamsEnum.enumMap);
		return mv;
	}
	/**
	 * 查询页面数据
	 * @Title: queryReportData
	 * @author: zuoyuntao  
	 * @Description:查询页面数据
	 * @param request
	 * @param response
	 * @return      
	 * Object
	 */
	@RequestMapping(value="queryReportData")
	@ResponseBody
	public Object queryReportData(HttpServletRequest request,HttpServletResponse response) {
		String begin = request.getParameter("beginDate");
		String end = request.getParameter("endDate");
		String deptId = request.getParameter("deptId");
		String deptType = request.getParameter("deptType");
		String confirmType = request.getParameter("confirmType");
		
		if(StringUtils.isEmpty(begin))
			begin = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if(StringUtils.isEmpty(end))
			end = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		if(StringUtil.isEmpty(deptType)) {
			deptType = DeptTypeEnum.DEPT_BIGAREA.getCode();//默认为大区
		}

		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("endDate", end);
		paramMap.put("beginDate", begin);
		paramMap.put("deptId", deptId);
		paramMap.put("deptTypeId", deptType);
		paramMap.put("confirmType", confirmType);
		paramMap.put("companyId", UserThreadLocal.get().get("companyId").toString());
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = mTotalPerformanceAnalysisMapper.queryPageDataList(paramMap);
		
		Map<String,Object> map = new HashMap<String,Object>(0);
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", list.size());
		map.put("data", list);
		return map;
	}
}
