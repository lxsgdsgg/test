package com.bashiju.manage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bashiju.api.RedisServiceApi;
import com.bashiju.manage.mapper.ManageBasicMapper;
import com.bashiju.manage.pojo.ElementUiResult;
import com.bashiju.manage.service.ManageBasicService;

@Service
public class ManageBasicServiceImpl implements ManageBasicService {
	
	@Autowired
	ManageBasicMapper manageBsicMapper;
	@Autowired
	RedisServiceApi redisServiceApi;
	@SuppressWarnings("unchecked")
	@Override
	public List<ElementUiResult> queryAllArea() {
		try {
			if (redisServiceApi.get("areaData") != null) {
				return (List<ElementUiResult>) JSONObject.parse(redisServiceApi.get("areaData"));
			} 
		} catch (Exception e) {
		}
		List<Map<String,Object>> lists=manageBsicMapper.queryAllArea();
		if(lists.isEmpty()) {
			return null;
		}
		Map<Integer,List<Map<String,Object>>> areaMap=new HashMap<>();
		for(Map<String,Object> eleMap:lists) {
			Integer level=Integer.valueOf(eleMap.get("parentCode").toString());
			if(!areaMap.containsKey(level)) {
				areaMap.put(Integer.parseInt(eleMap.get("parentCode").toString()), new ArrayList<Map<String,Object>>());
			}
			areaMap.get(Integer.parseInt(eleMap.get("parentCode").toString())).add(eleMap);
		}
		List<ElementUiResult> resList=new ArrayList<>();
		/*封装一级对象*/
		List<Map<String,Object>> levelListFirst=areaMap.get(0);
		for(Map<String,Object> firstMap:levelListFirst) {
			ElementUiResult firstEl=new ElementUiResult();
			firstEl.setLabel(firstMap.get("name").toString());
			firstEl.setValue(firstMap.get("code").toString());
			resList.add(firstEl);
			/*封装二级对象*/
			List<Map<String,Object>> levelSecondList=areaMap.get(Integer.parseInt(firstMap.get("code").toString()));
			List<ElementUiResult> secondList=new ArrayList<>();
			if(levelSecondList==null) {
				continue;
			}
			firstEl.setChildren(secondList);
			for(Map<String,Object> secondMap:levelSecondList) {
				ElementUiResult secondEl=new ElementUiResult();
				secondEl.setLabel(secondMap.get("name").toString());
				secondEl.setValue(secondMap.get("code").toString());
				secondList.add(secondEl);
				/*封装三级对象*/
				List<Map<String,Object>> levelthirdList=areaMap.get(Integer.parseInt(secondMap.get("code").toString()));
				List<ElementUiResult> thirdList=new ArrayList<>();
				if(levelthirdList==null) {
					continue;
				}
				secondEl.setChildren(thirdList);
				for(Map<String,Object> thirdMap:levelthirdList) {
					ElementUiResult thirdEl=new ElementUiResult();
					thirdEl.setLabel(thirdMap.get("name").toString());
					thirdEl.setValue(secondMap.get("code").toString());
					thirdList.add(thirdEl);					
				}
			}
		}
		redisServiceApi.set("areaData",JSONObject.toJSONString(resList),8*60*60*24);
		return resList;
  }
}
