/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  StatisticsBuyDemandController.java   
 * @Package com.bashiju.anal.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年11月17日 下午2:18:46   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.anal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bashiju.anal.mapper.AnalCommonMapper;
import com.bashiju.anal.mapper.StatisticsBuyDemandMapper;
import com.bashiju.anal.pojo.Cell;
import com.bashiju.anal.pojo.DemandStatisticsLongitudinalParamsEnum;
import com.bashiju.anal.pojo.DemandStatisticsTransverseParamsEnum;
import com.bashiju.anal.pojo.Row;
import com.bashiju.anal.pojo.Thead;
import com.bashiju.enums.CustomerStatusEnum;
import com.bashiju.enums.HouseLevelEnum;
import com.bashiju.enums.HouseUsesEnum;
import com.bashiju.enums.RoleGroupEnum;
import com.bashiju.enums.pc.BuildAreaEnum;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.threadlocal.UserThreadLocal;

/**   
 * @ClassName:  StatisticsBuyDemandController   
 * @Description:求购统计   
 * @author: yangz
 * @date:   2018年11月17日 下午2:18:46   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="sbd")
public class StatisticsBuyDemandController {
	
	@Autowired
	private StatisticsBuyDemandMapper statisticsBuyDemandMapper;
	
	@Autowired
	private AnalCommonMapper commonMapper;

	/**
	 * 
	 * @Description: 进入需求统计页面   
	 * @param request
	 * @param response
	 * @return: ModelAndView
	 */
	@RequestMapping(value="statisticsBuyDemand")
	public ModelAndView statisticsBuyDemand(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("statisticsDemand/statisticsDemand");
		//获取部门数据信息
		List<Map<String, Object>> deptList = commonMapper.queryDeptTrees(UserThreadLocal.get().get("roleGroup").toString());
		mv.addObject("deptInfo", JSONObject.toJSONString(deptList));
		mv.addObject("transverse", DemandStatisticsTransverseParamsEnum.enumMap);
		mv.addObject("longitudinal", DemandStatisticsLongitudinalParamsEnum.enumMap);
		Map<?,?> user = UserThreadLocal.get();
		mv.addObject("cityInfo", commonMapper.queryCompanyCityInfo(user.get("roleGroup").toString(),user.get("id").toString(),user.get("companyId").toString()));
		String roleGroup = UserThreadLocal.get().get("roleGroup").toString();
		if(!RoleGroupEnum.ORDINARY_USER.getCode().equals(roleGroup)) {//非普通用户才返回
			List<Map<String, Object>> list = commonMapper.queryCompanyByRoleGroup(roleGroup);
			mv.addObject("companys", list);
		}
		return mv;
	}
	
	/**
	 * 
	 * @Description: 根据条件获取详细的报表信息  
	 * @param request
	 * @param response
	 * @return: ModelAndView
	 */
	@RequestMapping(value="getReportDetailInfo")
	public ModelAndView getReportDetailInfo(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("statisticsDemand/reportDetailInfo");
		String transverse = request.getParameter("transverse");//横向维度
		String longitudinal = request.getParameter("longitudinal");//纵向维度
		String cityCode = request.getParameter("cityCode");//城市编码
		String companyId = request.getParameter("companyId");
		String deptId = request.getParameter("deptId");
		String beginRecordTime = request.getParameter("beginRecordTime");
		String endRecordTime = request.getParameter("endRecordTime");
		String beginMainterFllowTime = request.getParameter("beginMainterFllowTime");
		String endMainterFllowTime = request.getParameter("endMainterFllowTime");
		if(StringUtils.isEmpty(cityCode)) 
			throw new BusinessException("城市编号不允许为空");
		if(StringUtils.isEmpty(transverse)) 
			throw new BusinessException("横向维度不允许为空");
		if(StringUtils.isEmpty(longitudinal))
			throw new BusinessException("纵向维度不允许为空");
		if(StringUtils.isEmpty(companyId))
			companyId = UserThreadLocal.get().get("companyId").toString();
		//封装表头
		Thead thead = new Thead();//定义表头对象
		List<Row> rows = new ArrayList<Row>(0);//定义表头行数
		List<List<Cell>> cells = new ArrayList<List<Cell>>(0);
		//第一行
		List<Cell> cells1 = new ArrayList<Cell>(0);//定义表头列数
		cells1.add(new Cell.Builder().setField("groups").setTitle("一维").build());
		cells1.add(new Cell.Builder().setField("dimension").setTitle("二维").build());
		
		List<Map<String, String>> transverse_list = new ArrayList<Map<String,String>>(0);
		List<Map<String, Object>> transverse_list2 =  new ArrayList<Map<String,Object>>(0);
		StringBuffer cell_sql = new StringBuffer(0);//sql行转列信息
		String cell_property = null;//分组维度
		String houseUse = null;//房源用途集合，用于做房源用途分组
		String houseUseId = null;//房源用途编号集合，用于做房源用途分组
		if(DemandStatisticsLongitudinalParamsEnum.L1.getCode().equals(longitudinal)) {//状态
			cell_property = " b.statusId ";
			transverse_list = CustomerStatusEnum.enumList;
		}else if(DemandStatisticsLongitudinalParamsEnum.L2.getCode().equals(longitudinal)) {//行政区
			cell_property = " e.code ";
			transverse_list = statisticsBuyDemandMapper.queryCityArea(companyId, cityCode);
		}else if(DemandStatisticsLongitudinalParamsEnum.L3.getCode().equals(longitudinal)) {//面积区间
//			cell_property = " b.buildSpace ";
			transverse_list2 = BuildAreaEnum.enumList;
		}else if(DemandStatisticsLongitudinalParamsEnum.L4.getCode().equals(longitudinal)) {//客户来源
			cell_property = " b.sourceTypeId ";
			transverse_list = statisticsBuyDemandMapper.queryResourceType();
		}else if(DemandStatisticsLongitudinalParamsEnum.L5.getCode().equals(longitudinal)) {//价格区间
//			cell_property = " b.sellingPrice ";
			transverse_list = statisticsBuyDemandMapper.queryCityavgpricelabel(cityCode);
		}else if(DemandStatisticsLongitudinalParamsEnum.L6.getCode().equals(longitudinal)) {//用途
			cell_property = " b.houseUsesIds ";
			transverse_list = HouseUsesEnum.enumList;
		}else if(DemandStatisticsLongitudinalParamsEnum.L7.getCode().equals(longitudinal)) {//客源等级
			cell_property = " b.levelType ";
			transverse_list = HouseLevelEnum.enumList;
		}
		if(DemandStatisticsTransverseParamsEnum.T8.getCode().equals(transverse)) {//当维度为用途时，要将维度信息返回帮助分组
			List<String> use = new ArrayList<String>(0);
			List<String> useId = new ArrayList<String>(0);
			for(Entry<String, String> em : HouseUsesEnum.enumMap.entrySet()) {
				useId.add(em.getKey());
				use.add(em.getValue());
			}
			houseUseId = StringUtils.join(useId.toArray(),',');
			houseUse = StringUtils.join(use.toArray(),',');
		}
		
		cell_sql.append(",'"+DemandStatisticsTransverseParamsEnum.enumMap.get(transverse)+"' groups ");	
		if(DemandStatisticsLongitudinalParamsEnum.L3.getCode().equals(longitudinal)) {
			for(Map<String,Object> map : transverse_list2) {
				cell_sql.append(",count(case when if(b.maxSpace>0,b.maxSpace >="+map.get("max")+",true)  and b.minSpace<"+map.get("max")+" then b.id end )'"+"cell_"+map.get("value")+"' ");
				cells1.add(new Cell.Builder().setField("cell_"+map.get("value")).setTitle(map.get("name").toString()).setTotalRow(true).build());
			}
		}else {
			for(Map<String,String> map : transverse_list) {
				if(DemandStatisticsLongitudinalParamsEnum.L5.getCode().equals(longitudinal)) {
					String min = map.get("min");
					String max = map.get("max");
					String value = map.get("value");
					cell_sql.append(",count(case when if(b.maxPrice>0,b.maxPrice >="+max+",true) and b.minPrice<"+max+" then b.id end )'"+"cell_"+value+"' ");
//					cell_sql.append(",count(case when b.minPrice >="+min+" and b.maxPrice <="+max+" then b.id end )'"+"cell_"+value+"' ");
				}else if(DemandStatisticsLongitudinalParamsEnum.L6.getCode().equals(longitudinal)){
					cell_sql.append(",count(case when find_in_set('"+map.get("value")+"',"+cell_property+") then b.id end )'"+"cell_"+map.get("value")+"' ");
				}else {
					if(cell_property!=null)
						cell_sql.append(",count(case when "+cell_property+"='"+map.get("value")+"' then b.id end )'"+"cell_"+map.get("value")+"' ");
				}
				cells1.add(new Cell.Builder().setField("cell_"+map.get("value")).setTitle(map.get("name")).setTotalRow(true).build());
			}
		}
		cells.add(cells1);
		rows.add(new Row.Builder().cells(cells).build());
		thead.setRows(rows);
		mv.addObject("thead", JSONObject.toJSON(thead));
		List<Map<String,Object>> dataList = statisticsBuyDemandMapper.getData(transverse,longitudinal,companyId,cityCode,cell_sql.toString(),deptId,
				beginRecordTime,endRecordTime,beginMainterFllowTime,endMainterFllowTime,houseUseId,houseUse);
		System.out.println(cell_sql);
		System.out.println(JSONArray.toJSON(dataList));
		mv.addObject("data", JSONArray.toJSON(dataList));
		mv.addObject("titles", "求购统计");
		return mv;
	}
	
	public static void main(String[] args) {
		List<String> use = new ArrayList<String>(0);
		for(Entry<String, String> em : HouseUsesEnum.enumMap.entrySet()) {
			use.add(em.getValue());
		}
		System.out.println(StringUtils.join(use.toArray(),','));
	}

}
