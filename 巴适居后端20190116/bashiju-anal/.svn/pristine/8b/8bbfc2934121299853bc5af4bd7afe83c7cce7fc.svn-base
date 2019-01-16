/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  StatisticsSaleHouseController.java   
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.utils.StringUtils;
import com.bashiju.anal.mapper.AnalCommonMapper;
import com.bashiju.anal.mapper.StatisticsSaleHouseMapper;
import com.bashiju.anal.pojo.Cell;
import com.bashiju.anal.pojo.HouseStatisticsLongitudinalParamsEnum;
import com.bashiju.anal.pojo.HouseStatisticsTransverseParamsEnum;
import com.bashiju.anal.pojo.Row;
import com.bashiju.anal.pojo.Thead;
import com.bashiju.enums.HouseLevelEnum;
import com.bashiju.enums.HouseStatusEnum;
import com.bashiju.enums.HouseUsesEnum;
import com.bashiju.enums.RoleGroupEnum;
import com.bashiju.enums.pc.BuildAreaEnum;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.threadlocal.UserThreadLocal;

/**   
 * @ClassName:  StatisticsSaleHouseController   
 * @Description:出售房源统计   
 * @author: yangz
 * @date:   2018年11月17日 下午2:18:46   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="ssh")
public class StatisticsSaleHouseController {
	
	@Autowired
	private StatisticsSaleHouseMapper statisticsSaleHouseMapper;
	
	@Autowired
	private AnalCommonMapper commonMapper;

	/**
	 * 
	 * @Description: 进入出售房源统计页面   
	 * @param request
	 * @param response
	 * @return: ModelAndView
	 */
	@RequestMapping(value="statisticsSaleHouse")
	public ModelAndView statisticsSaleHouse(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("statisticsHouse/statisticsHouse");
		//获取部门数据信息
		List<Map<String, Object>> deptList = commonMapper.queryDeptTrees(UserThreadLocal.get().get("roleGroup").toString());
		mv.addObject("deptInfo", JSONObject.toJSONString(deptList));
		mv.addObject("transverse", HouseStatisticsTransverseParamsEnum.enumMap);
		mv.addObject("longitudinal", HouseStatisticsLongitudinalParamsEnum.enumMap);
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
		ModelAndView mv = new ModelAndView("statisticsHouse/reportDetailInfo");
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
		if(HouseStatisticsLongitudinalParamsEnum.L1.getCode().equals(longitudinal)) {//状态
			cell_property = " b.statusId ";
			transverse_list = HouseStatusEnum.enumList;
		}else if(HouseStatisticsLongitudinalParamsEnum.L2.getCode().equals(longitudinal)) {//行政区
			cell_property = " c.areaCode ";
			transverse_list = statisticsSaleHouseMapper.queryCityArea(companyId, cityCode);
		}else if(HouseStatisticsLongitudinalParamsEnum.L3.getCode().equals(longitudinal)) {//产权性质
			cell_property = " b.propertyTypeId ";
			transverse_list = statisticsSaleHouseMapper.queryPropertyType();
		}else if(HouseStatisticsLongitudinalParamsEnum.L4.getCode().equals(longitudinal)) {//面积区间
			cell_property = " b.buildSpace ";
			transverse_list2 = BuildAreaEnum.enumList;
		}else if(HouseStatisticsLongitudinalParamsEnum.L5.getCode().equals(longitudinal)) {//客户来源
			cell_property = " b.resourceTypeId ";
			transverse_list = statisticsSaleHouseMapper.queryResourceType();
		}else if(HouseStatisticsLongitudinalParamsEnum.L6.getCode().equals(longitudinal)) {//售价区间
			cell_property = " b.sellingPrice ";
			transverse_list = statisticsSaleHouseMapper.queryCityavgpricelabel(cityCode);
		}else if(HouseStatisticsLongitudinalParamsEnum.L7.getCode().equals(longitudinal)) {//用途
			cell_property = " b.houseUsesId ";
			transverse_list = HouseUsesEnum.enumList;
		}else if(HouseStatisticsLongitudinalParamsEnum.L8.getCode().equals(longitudinal)) {//房源等级
			cell_property = " b.levelType ";
			transverse_list = HouseLevelEnum.enumList;
		}
		
		cell_sql.append(",'"+HouseStatisticsTransverseParamsEnum.enumMap.get(transverse)+"' groups ");	
		if(HouseStatisticsLongitudinalParamsEnum.L4.getCode().equals(longitudinal)) {
			for(Map<String,Object> map : transverse_list2) {
				if(cell_property!=null)
					cell_sql.append(",count(case when "+cell_property+">"+map.get("min")+" and "+cell_property+"<="+map.get("max")+" then b.id end )'"+"cell_"+map.get("value")+"' ");
				cells1.add(new Cell.Builder().setField("cell_"+map.get("value")).setTitle(map.get("name").toString()).setTotalRow(true).build());
			}
		}else {
			for(Map<String,String> map : transverse_list) {
				if(cell_property!=null) {
					if(HouseStatisticsLongitudinalParamsEnum.L6.getCode().equals(longitudinal)) {
						String min = map.get("min");
						String max = map.get("max");
						String value = map.get("value");
						if(Integer.parseInt(min)<Integer.parseInt(max))
							cell_sql.append(",count(case when "+cell_property+">"+min+" and "+cell_property+"<="+max+" then b.id end )'"+"cell_"+value+"' ");
						else
							cell_sql.append(",count(case when "+cell_property+">"+min+" then b.id end )'"+"cell_"+value+"' ");
					}else
						cell_sql.append(",count(case when "+cell_property+"='"+map.get("value")+"' then b.id end )'"+"cell_"+map.get("value")+"' ");
				}
				cells1.add(new Cell.Builder().setField("cell_"+map.get("value")).setTitle(map.get("name")).setTotalRow(true).build());
			}
		}
		cells.add(cells1);
		rows.add(new Row.Builder().cells(cells).build());
		thead.setRows(rows);
		mv.addObject("thead", JSONObject.toJSON(thead));
		List<Map<String,Object>> dataList = statisticsSaleHouseMapper.getData(transverse,longitudinal,companyId,cityCode,cell_sql.toString(),deptId,
				beginRecordTime,endRecordTime,beginMainterFllowTime,endMainterFllowTime);
		System.out.println(JSONArray.toJSON(dataList));
		mv.addObject("data", JSONArray.toJSON(dataList));
		mv.addObject("titles", "出售房源统计");
		return mv;
	}

}
