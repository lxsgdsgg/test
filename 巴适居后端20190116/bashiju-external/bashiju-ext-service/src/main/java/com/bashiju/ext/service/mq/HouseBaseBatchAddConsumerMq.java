package com.bashiju.ext.service.mq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bashiju.api.RedisGetIdServiceApi;
import com.bashiju.ext.mapper.LogOperateMapper;
import com.bashiju.utils.service.CommonSqlServie;

@Service
public class HouseBaseBatchAddConsumerMq {
	Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CommonSqlServie commonSqlServie;
	@Autowired
	LogOperateMapper logOperateMapper;
	@Autowired
	RedisGetIdServiceApi redisGetIdServiceApi;
	/** 一次最多插入条数**/
	private final int maxCount=100;
	
	public void batchAddHouseBase(HashMap map) {
		try {
			if (map!=null) {
				Integer type=Integer.parseInt(map.get("type").toString());
				Map<String, Object> baseInfo=null;
				List<Map<String, Object>> houseInfo=null;
				//批量新增座栋
				if (type==1) {
					baseInfo=logOperateMapper.queryRegionInfoByCommunityId(Long.parseLong(map.get("communityId").toString()));
					List<Long> buildings= (List<Long>) map.get("buildings");
					houseInfo=logOperateMapper.queryHouseNumberInfoByBuildingIds(buildings);
				}
				//增加单元
				if (type==2) {
					baseInfo=logOperateMapper.queryRegionInfoByBuildingId(Long.parseLong(map.get("buildingId").toString()));
					houseInfo=logOperateMapper.queryHouseNumberInfoByUnitId(Long.parseLong(map.get("buildingUnit").toString()));
				}
				if (baseInfo!=null&&houseInfo!=null) {
					saveHouseBaseInfo(houseInfo,baseInfo,map);
				}
			}
		} catch (NumberFormatException e) {
			logger.error("【房源基础信息】消息队列添加【房源基础信息】记录出错,值是{}，原因是：{}",JSON.toJSONString(map),e.getMessage());
		}
	}
	
	private void saveHouseBaseInfo(List<Map<String, Object>> houseInfo,Map<String, Object> baseInfo,Map param) {
		String fieldString="id,cityCode,areaCode,areaName,regionId,regionName,communityId,"
		+ "communityName,buildingsId,buildingsName,bulidingUnitId,buildingsUnitName,buildingHouseId,buildingHouseName,"
		+ "elevatorCount,householdCount,floorCount,totalLayers,permissionArea,operatorId,isSchoolRoom,isSubwayRoom";
		String code=baseInfo.get("areaCode").toString();
		//分次数批量插入房源基础信息
		for (int j = 0; j < houseInfo.size(); j=j+maxCount) {
			try {
				int max = j+maxCount;
				if (max > houseInfo.size()) {
					max = houseInfo.size();
				}
				List<Map<String, Object>> itemList=houseInfo.subList(j, max);
				List<String> listString=new ArrayList<>();
				for(int i=0;i<itemList.size();i++) {		
					String houseCode=redisGetIdServiceApi.getHouseId(code);
					StringBuilder sb=new StringBuilder();
						sb.append("'").append(houseCode).append("','")
						.append(baseInfo.get("cityCode"))
						.append("','")
						.append(baseInfo.get("areaCode"))
						.append("','")
						.append(baseInfo.get("areaName"))
						.append("',")
						.append(baseInfo.get("regionId"))
						.append(",'")
						.append(baseInfo.get("regionName"))
						.append("',")
						.append(baseInfo.get("communityId"))
						.append(",'")
						.append(baseInfo.get("communityName"))
						.append("',")
						.append(itemList.get(i).get("buildingsId"))
						.append(",'")
						.append(itemList.get(i).get("buildingsName"))
						.append("',")
						.append(itemList.get(i).get("bulidingUnitId"))
						.append(",'")
						.append(itemList.get(i).get("buildingsUnitName"))
						.append("',")
						.append(itemList.get(i).get("buildingHouseId"))
						.append(",'")
						.append(itemList.get(i).get("buildingHouseName"))
						.append("',")
						.append(itemList.get(i).get("elevatorCount"))
						.append(",")
						.append(itemList.get(i).get("householdCount"))
						.append(",")
						.append(itemList.get(i).get("floorCount"))
						.append(",")
						.append(itemList.get(i).get("totalLayers"))
						.append(",'")
						.append(param.get("permissionArea"))
						.append("',")
						.append(param.get("operatorId"))
						.append(",")
						.append(baseInfo.get("isSchoolRoom"))
						.append(",")
						.append(baseInfo.get("isSubwayRoom"));
					listString.add(sb.toString());
				}
				commonSqlServie.batchAdd( fieldString, "hs_houseBaseInfo",listString, param.get("operator").toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
