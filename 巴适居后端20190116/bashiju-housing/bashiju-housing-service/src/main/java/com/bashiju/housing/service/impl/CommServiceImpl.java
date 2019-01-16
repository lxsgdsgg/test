package com.bashiju.housing.service.impl;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bashiju.api.PrivacyProtectionBaseServiceApi;
import com.bashiju.housing.mapper.CommMapper;
import com.bashiju.housing.pojo.BusinessRateResult;
import com.bashiju.housing.pojo.DeedTaxResult;
import com.bashiju.housing.pojo.HousePropertyResult;
import com.bashiju.housing.pojo.LoanRateItem;
import com.bashiju.housing.pojo.LoanRateResult;
import com.bashiju.housing.service.ICommService;
import com.bashiju.utils.threadlocal.UserThreadLocal;

@Service
public class CommServiceImpl implements ICommService {
	@Autowired
	CommMapper commMapper;
	@Autowired
	PrivacyProtectionBaseServiceApi privacyProtectionBaseServiceApi;
	
	@Override
	public LoanRateResult queryLoanRate() {
		String cityCode=UserThreadLocal.get().get("cityCode").toString();
		LoanRateResult result=new LoanRateResult();
		
		
		String rebates="[";
		//rebates+="{\"value\":\"1\",\"name\":\"无折扣\"},";
		rebates+="{\"value\":\"0.95\",\"name\":\"95折\"},";
		rebates+="{\"value\":\"0.9\",\"name\":\"9折\"},";
		rebates+="{\"value\":\"0.88\",\"name\":\"88折\"},";
		rebates+="{\"value\":\"0.87\",\"name\":\"87折\"},";
		rebates+="{\"value\":\"0.86\",\"name\":\"86折\"},";
		rebates+="{\"value\":\"0.85\",\"name\":\"85折\"},";
		rebates+="{\"value\":\"0.82\",\"name\":\"82折\"},";
		rebates+="{\"value\":\"0.8\",\"name\":\"8折\"},";
		rebates+="{\"value\":\"0.75\",\"name\":\"75折\"},";
		rebates+="{\"value\":\"0.7\",\"name\":\"7折\"},";
		rebates+="{\"value\":\"1.1\",\"name\":\"1.1倍\"},";
		rebates+="{\"value\":\"1.2\",\"name\":\"1.2倍\"},";
		rebates+="{\"value\":\"1.3\",\"name\":\"1.3倍\"},";
		rebates+="{\"value\":\"1.4\",\"name\":\"1.4倍\"},";
		rebates+="{\"value\":\"1.5\",\"name\":\"1.5倍\"}";
		rebates+="]";
		
		result.setRebates(JSON.parseArray(rebates, LoanRateItem.class));
		Map<String, Object> map=commMapper.queryLoanCeiling(cityCode);
		result.setPublicLoanCeiling(Integer.parseInt(map.get("publicLoanCeiling").toString()));
		result.setLoanRatioCeiling(Integer.parseInt(map.get("LoanRatioCeiling").toString()));
		DecimalFormat format=new DecimalFormat("0.00");
		Double businessLoanRatio=Double.parseDouble(map.get("businessLoanRatio").toString());
		Double publicLoanRatio=Double.parseDouble(map.get("publicLoanRatio").toString());
		String businessRate="[";
		businessRate+="{\"value\":\""+format.format(businessLoanRatio)+"\",\"name\":\"最新基准利率 "+format.format(businessLoanRatio)+"\"}";
		businessRate+="]";
		String publicFundRate="[";
		publicFundRate+="{\"value\":\""+format.format(publicLoanRatio)+"\",\"name\":\"最新基准利率"+format.format(publicLoanRatio)+"\"}";
		publicFundRate+="]";
		result.setBusinessRates(JSON.parseArray(businessRate, LoanRateItem.class));
		result.setPublicFundRates(JSON.parseArray(publicFundRate, LoanRateItem.class));
		return result;
	}

	@Override
	public List<HousePropertyResult> queryHousePropertyList() {
		String cityCode=UserThreadLocal.get().get("cityCode").toString();
		List<HousePropertyResult> results=commMapper.queryHousePropertyList(cityCode);	
		if (results!=null&&results.size()>0) {
			for (HousePropertyResult housePropertyResult : results) {
				if (housePropertyResult.getType()>0) {
					List<DeedTaxResult> deedTaxResults=commMapper.queryDeedTaxResult(housePropertyResult.getId());
					housePropertyResult.setDeedTaxs(deedTaxResults);	
				}else {
					/*BusinessRateResult businessRateResult= queryBusinessRateResult();
					housePropertyResult.setBusinessDeedTax(businessRateResult.getDeedTax());
					housePropertyResult.setAddedValueTax(businessRateResult.getAddedValueTax());
					housePropertyResult.setIncomeTax(businessRateResult.getIncomeTax());*/
				}		
			}
		}
		return results;
	}

	@Override
	public BusinessRateResult queryBusinessRateResult() {
		List<Map<String, Object>> list=commMapper.queryBusinessRateResult();
		BusinessRateResult result=new BusinessRateResult();
		if (list!=null&&list.size()>0) {
			for (Map<String, Object> map : list) {
				if ("deedTax".equals(map.get("code").toString())) {
					result.setDeedTax(Float.parseFloat(map.get("value").toString()));
				}
				if ("addedValueTax".equals(map.get("code").toString())) {
					result.setAddedValueTax(Float.parseFloat(map.get("value").toString()));
				}
				if ("incomeTax".equals(map.get("code").toString())) {
					result.setIncomeTax(Float.parseFloat(map.get("value").toString()));
				}
			}
		}	
		return result;
	}

	@Override
	public boolean tencentCloudClickDial(String houseId, String dst) {
		Map user = UserThreadLocal.get();
		String companyId = user.get("companyId").toString();
		String phone = user.get("mobile").toString();
		boolean result = privacyProtectionBaseServiceApi.tencentCloudClickDial(companyId, houseId, phone, dst);
		return result;
	}

}
