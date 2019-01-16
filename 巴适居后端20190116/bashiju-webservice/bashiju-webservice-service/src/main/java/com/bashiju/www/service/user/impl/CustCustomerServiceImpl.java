package com.bashiju.www.service.user.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.api.RedisGetIdServiceApi;
import com.bashiju.webservice.mapper.CustCustomerMapper;
import com.bashiju.wutils.exception.BusinessException;
import com.bashiju.wutils.redis.ICacheService;
import com.bashiju.wutils.util.Md5EncryptUtils;
import com.bashiju.www.global.WebGlobal;
import com.bashiju.www.pojo.service.user.CustCustomerEntity;
import com.bashiju.www.pojo.service.user.CustCustomerInsertBingdingParam;
import com.bashiju.www.pojo.service.user.CustCustomerParam;
import com.bashiju.www.pojo.service.user.CustCustomerUpdateBingdingEntity;
import com.bashiju.www.pojo.service.user.CustCustomerUpdateBingdingParam;
import com.bashiju.www.service.api.impl.usercentral.CustomerPersonalCentralBaseServiceApi;
import com.bashiju.www.service.user.CustCustomerService;
import com.github.pagehelper.util.StringUtil;

/**
 * 用户服务
 * 
 * @ClassName: CustCustomerServiceImpl
 * @Description:用户服务
 * @author: wangpeng
 * @date: 2018年8月15日 下午1:37:18
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 *             本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class CustCustomerServiceImpl extends CustomerPersonalCentralBaseServiceApi implements CustCustomerService {

	@Autowired
	CustCustomerMapper custCustomerMapper;
	@Autowired
	private ICacheService cacheService;
	@Autowired
	private RedisGetIdServiceApi redisGetIdServiceApi;

	// 生成redis的key规则
	private final static String USERKEY = "！@#￥%……& +";

	/**
	 * 查询手机号码是否已存在
	 * 
	 * @Description: 查询手机号码是否已存在
	 * @param mobile
	 *            手机号码
	 * @return: boolean
	 * @see com.bashiju.www.service.user.UserRegisterService#queryIsExistMobile(java.lang.String)
	 */
	@Override
	public boolean queryIsExistMobile(String mobile) {
		long result = custCustomerMapper.queryIsExistMobile(mobile);
		if (result > 0)
			return true;// 号码已存在
		return false;
	}

	/**
	 * 保存用户注册信息
	 * 
	 * @Description: 保存用户注册信息
	 * @param custCustomerParam
	 *            用戶输入参数
	 * @return: boolean
	 * @see com.bashiju.www.service.user.UserRegisterService#saveCustCustomer(com.bashiju.www.pojo.service.user.CustCustomerParam)
	 */

	@Override
	public boolean saveCustCustomer(CustCustomerParam custCustomerParam) {
		if (StringUtils.isEmpty(custCustomerParam.toString()))
			throw new BusinessException("注册信息输入有误");
		// 判断手机号码是否在数据库已存在
		boolean isExist = this.queryIsExistMobile(custCustomerParam.getMobile());
		if (isExist)
			throw new BusinessException("手机号码已注册");
		// 从redis获取短信验证码
		String code = cacheService.getCache(custCustomerParam.getMobile());
		// 判断短信验证码是否正确
		if (!custCustomerParam.getMsgCode().equals(code))
			throw new BusinessException("验证码有误");
		// 注册
		CustCustomerEntity custCustomerEntity = new CustCustomerEntity();
		custCustomerEntity.setMobile(custCustomerParam.getMobile());
		custCustomerEntity.setName(custCustomerParam.getName());
		custCustomerEntity.setPassword(custCustomerParam.getPassword());
		// custCustomerEntity.setSolt(custCustomerParam.getSolt());
		custCustomerEntity.setBirthday(new Date());
		custCustomerEntity.setIsValid("1");
		custCustomerEntity.setSolt("0");
		custCustomerEntity.setAddTime(new Date());
		// 保存用户信息
		int result = custCustomerMapper.saveCustCustomer(custCustomerEntity);
		if (result > 0) {
			//保存成功后从redis刪除短信
			redisGetIdServiceApi.DelCode(custCustomerParam.getMobile());
			//注冊成功后綁定一個系统账号到，第三方表
			CustCustomerInsertBingdingParam bindingParam = new CustCustomerInsertBingdingParam();
			bindingParam.setAddTime(new Date());
			bindingParam.setCode("");
			bindingParam.setName(custCustomerParam.getName());
			bindingParam.setIsValid("1");
			bindingParam.setNumType("00");
			bindingParam.setOperator("系统操作");
			bindingParam.setProfilePhoto("");
			bindingParam.setCustId(String.valueOf(custCustomerEntity.getId()));
			custCustomerMapper.bindingThirdparty(bindingParam);
			return true;	
		}
		return false;
	}

	/**
	 * 修改用户信息
	 * 
	 * @Description: 修改用户信息
	 * @param paramMap
	 *            修改参数
	 * @return: boolean
	 * @see com.bashiju.www.service.user.UserRegisterService#updateCustCustomer(java.util.Map)
	 */
	@Override
	public boolean updateCustCustomer(CustCustomerEntity custCustomerEntity) {
		if (StringUtils.isEmpty(custCustomerEntity.toString()))
			throw new BusinessException("修改参数不能为空");
		if (StringUtils.isEmpty(custCustomerEntity.getMsgCode()))
			throw new BusinessException("短息验证码不能为空");
		if (StringUtils.isEmpty(custCustomerEntity.getMobile()))
			throw new BusinessException("手机号码不能为空");
		String mobile = StringUtil.isEmpty(custCustomerEntity.getMobile()) ? custCustomerEntity.getMobile()
				: custCustomerEntity.getNewMobile();
		String code = cacheService.getCache(mobile);
		// 验证页面输入短信验证是否正确
		System.out.println(code + "==============" + custCustomerEntity.getMsgCode());
		if (code.equals(custCustomerEntity.getMsgCode())) {
			long rt = custCustomerMapper.updateCustCustomer(custCustomerEntity);
			if (rt > 0) {
//				redisGetIdServiceApi.DelCode(custCustomerEntity.getMobile());
				return true;
			}
			throw new BusinessException("修改失败");
		} else
			throw new BusinessException("短信验证码有误");
	}

	/**
	 * 用户账号密码登录
	 * 
	 * @Description: 用户账号密码登录
	 * @param mobile
	 *            电话号码
	 * @param password
	 *            密码
	 * @return: boolean
	 * @see com.bashiju.www.service.user.CustCustomerService#customerLogin(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public String customerLogin(String mobile, String password) {
		if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password))
			throw new BusinessException("账号或密码不能为空");
		// 判断用户输入的验证码是否正确
		// redis验证码的取值
		// 获取用户信息
		Map<String, Object> custMap = custCustomerMapper.customerLogin(mobile, password);
		if (custMap == null || custMap.size() <= 0)
			throw new BusinessException("账号或密码有误");
		String id = custMap.get("id").toString();
		custMap.put("numType", "00");
		// 生成redis key值，規則： 时间戳 + custId
		String redisKey = USERKEY + System.currentTimeMillis() + id;
		// 将redisKey转为hash
		String hashKey = Md5EncryptUtils.md5Encrypt(redisKey);
		// 将转为hash值以后的key作为主键存入reids中，存储的value值为用户信息对象(包括：用户名称，用户id，用户电话)并且返回该key值
		cacheService.saveCache(hashKey, custMap, WebGlobal.CACHE_TIMES);
		return hashKey;

	}

	/**
	 * 发送手机验证码
	 * 
	 * @Description: 发送手机验证码
	 * @param mobile
	 *            电话号码
	 * @return: String
	 */
	@Override
	public String sendMobileMsgVerfyCode(String mobile) {
		if (StringUtils.isEmpty(mobile))
			throw new BusinessException("手机号码不能为空");
		// 发送短信验证码
		String mobileCode = redisGetIdServiceApi.getMsgAuthCode(mobile);
		return mobileCode;
	}

	/**
	 * 短信登录
	 * 
	 * @Description: 发送手机验证码
	 * @param mobile
	 *            电话号码
	 * @param msgCode
	 *            短信验证码验证码
	 */
	@SuppressWarnings({ "null", "unused" })
	@Override
	public String customerMobileLogin(String mobile, String msgCode) {
		if (StringUtils.isEmpty(msgCode))
			throw new BusinessException("短信验证码不能为空");
		// redis获取短信验证码
		String redisMsgCode = cacheService.getCache(mobile);
		if (StringUtils.isEmpty(redisMsgCode))
			throw new BusinessException("验证码失效，请重新发送");
		// 判断验证码是否正确
		if (!msgCode.equals(redisMsgCode)) {
			throw new BusinessException("验证码输入有误");
		}
		boolean isExist = this.queryIsExistMobile(mobile);
		if (!isExist) {
			// 未注册
			CustCustomerEntity custCustomerEntity = new CustCustomerEntity();
			custCustomerEntity.setPassword("");
			custCustomerEntity.setName(mobile);
			custCustomerEntity.setMobile(mobile);
			custCustomerEntity.setBirthday(new Date());
			custCustomerEntity.setIsValid("1");
			custCustomerEntity.setAddTime(new Date());
			int result = custCustomerMapper.saveCustCustomer(custCustomerEntity);
			if(result>0) {
				CustCustomerInsertBingdingParam bindingParam = new CustCustomerInsertBingdingParam();
				bindingParam.setAddTime(new Date());
				bindingParam.setCode("");
				bindingParam.setIsValid("1");
				bindingParam.setNumType("00");
				bindingParam.setName(mobile);
				bindingParam.setOperator("系统操作");
				bindingParam.setProfilePhoto("");
				bindingParam.setCustId(String.valueOf(custCustomerEntity.getId()));
				custCustomerMapper.bindingThirdparty(bindingParam);
			}
		}
		// 登录
		Map<String, Object> custInfo = custCustomerMapper.customerMobileLogin(mobile);
		custInfo.put("numType", "00");
		if (custInfo == null && custInfo.size() < 0) {
			throw new BusinessException("电话号码输入有误");
		}
		// 初始化rediskey
		String redisKey = USERKEY + System.currentTimeMillis() + custInfo.get("id");
		String hashKey = Md5EncryptUtils.md5Encrypt(redisKey);
		// 将用户信息存入redis,并返回redis的key
		cacheService.saveCache(hashKey, custInfo, WebGlobal.CACHE_TIMES);
		redisGetIdServiceApi.DelCode(mobile);
		return hashKey;
	}

	/**
	 * 退出登陆
	 * 
	 * @Description: 退出登陆
	 * @param redisKey
	 *            redis的key
	 */

	@Override
	public boolean customerLoginout(String redisKey) {
		boolean result = cacheService.delCache(redisKey);
		if (result)
			return true;
		throw new BusinessException("退出失败");
	}

	@Override
	public boolean updateCustomerPwdByMobile(String mobile, String password) {
		if (StringUtils.isEmpty(password) && StringUtils.isEmpty(mobile))
			throw new BusinessException("修改参数不能为空");
		long result = custCustomerMapper.updateCustomerPwdByMobile(mobile, password);
		if (result > 0)
			return true;
		return false;
	}

	@Override
	public boolean mobileMsgCheck(String mobile, String msgCode) {
		if (StringUtils.isEmpty(msgCode))
			throw new BusinessException("短信验证码不能为空");
		String redisMsgCode = cacheService.getCache(mobile);
		// 判断验证是否有效
		if (StringUtils.isEmpty(redisMsgCode))
			throw new BusinessException("验证码失效，请重新发送");
		// 判断验证码是否正确
		if (!msgCode.equals(redisMsgCode))
			throw new BusinessException("验证有误,请重试");
//		redisGetIdServiceApi.DelCode(mobile);	
		return true;
	}

	public String getCustIdByKey(String key) {
		String custId = cacheService.getCache(key);
		return custId;
	}



	/** 
	 *  第三方登录  
	 * <p>Title: thirdPartyLogin</p>   
	 * <p>Description: 第三方登录</p>   
	 * @param numType 登录类型 ：qq,wexin
	 * @param code 第三方账号
	 * @see com.bashiju.www.service.user.CustCustomerService#thirdPartyLogin(java.lang.String, java.lang.String, java.lang.String)   
	 */
	
//	@SuppressWarnings("unlikely-arg-type")
//	@Override
//	public String thirdPartyLogin(String code,String numType) {
//	  		if(StringUtils.isEmpty(numType))
//	  			throw new BusinessException("第三方登录类型不能为空");
//			if(!numType.equals(AccountTypeEnum.QQ.getCode()) && !numType.equals(AccountTypeEnum.WECHAT.getCode())
//					&& !numType.equals(AccountTypeEnum.WECHATAPPLE.getCode()))
//				throw new BusinessException("第三方登录类型有误");
//		    if(StringUtils.isEmpty(code))
//				throw new BusinessException("账号不能为空");
//			Map<String,Object> custInfo = custCustomerMapper.thirdPartyLogin(code, numType);
//			if(custInfo == null ||"".equals(custInfo))
//				throw new BusinessException("登陆有误");
//			//获取id
//			String id = custInfo.get("custId").toString();
//			custInfo.put("nameType",numType);
//			String redisKey = USERKEY + System.currentTimeMillis() + id;
//			// 将redisKey转为hash
//			String hashKey = Md5EncryptUtils.md5Encrypt(redisKey);
//			// 将转为hash值以后的key作为主键存入reids中，存储的value值为用户信息对象(包括：用户名称，用户id，用户电话)并且返回该key值
//			cacheService.saveCache(hashKey, custInfo, WebGlobal.CACHE_TIMES);
//			return hashKey; 
//	}
	@SuppressWarnings({ "null", "unlikely-arg-type" })
	@Override
	public String thirdPartyLogin(CustCustomerInsertBingdingParam insertParam) {
		/****
		 * 步骤：
		 *  一：传手机号码的情况：
		 *  二：不传手机号码的情况
		 *  传手机号码：
		 *       绑定：
		 *          查询手机号码在系统中是否已注册过
		 *           ①已注册过：
		 *           	绑定第三方登录的表
		 *           	登录
		 *           ②未注册过：
		 *           	系统注册一个用户，并绑定第三方表
		 *           	登录
		 *    不传手机号码
		 *	           登录：
		 *    		查询用户信息是否为空
		 *    			为空：提示用户未绑定第三方表
		 *              不为空：直接登录，返回登录信息    
		 */
		String hashKey="";
		Map<String,Object> keyMap= new HashMap<>();
			if(StringUtils.isEmpty(insertParam.getNumType()))
				throw new BusinessException("第三方绑定类型不能为空");
			if("01".equals(insertParam.getNumType()) && "02".equals(insertParam.getNumType()) && "05".equals(insertParam.getNumType()))
					throw new BusinessException("第三方类型有误");
			if(StringUtils.isEmpty(insertParam.getMobile())) {
				//未传入电话
				Map<String,Object> custInfo = custCustomerMapper.thirdPartyLogin(insertParam.getCode(), insertParam.getNumType());
				if(custInfo==null) {
					//查询不到用户信息
					//未注册
					return "unBind";
				}else {
					//登录
					String id = custInfo.get("custId").toString();
					String redisKey = USERKEY + System.currentTimeMillis() + id;
					// 将redisKey转为hash
					 hashKey = Md5EncryptUtils.md5Encrypt(redisKey);
					// 将转为hash值以后的key作为主键存入reids中，存储的value值为用户信息对象(包括：用户名称，用户id，用户电话)并且返回该key值
					keyMap.put("success", true);
					keyMap.put("id", custInfo.get("custId"));
//				    keyMap.put("msg", "登录成功");
					keyMap.put("hashKey", hashKey);
					keyMap.put("numType",insertParam.getNumType());
					cacheService.saveCache(hashKey, keyMap, WebGlobal.CACHE_TIMES);
					return hashKey; 
				}
			}else {
				if(StringUtils.isEmpty(insertParam.getCode()))
					throw new BusinessException("第三方账号不能为空");
				if(StringUtils.isEmpty(insertParam.getName()))
					throw new BusinessException("第三方名称不能为空");
				if(StringUtils.isEmpty(insertParam.getProfilePhoto()))
					throw new BusinessException("第三方头像不能为空");
				if(StringUtils.isEmpty(insertParam.getNumType()))
					throw new BusinessException("第三方类型不能为空");
				if(StringUtils.isEmpty(insertParam.getMsgCode()))
					throw new BusinessException("短信验证码不能为空");
//				传入手机号码
				String code = cacheService.getCache(insertParam.getMobile());
				// 判断短信验证码是否正确
				if (!insertParam.getMsgCode().equals(code))
					throw new BusinessException("验证码有误");
				Map<String, Object> info = custCustomerMapper.queryCustomerInfo(insertParam.getMobile());
				//查询手机号码是否在数据库里已存在
				if(info!=null && !"".equals(info)) {
					//查询第三方是否已绑定类型
					Map<String,Object> isBing = custCustomerMapper.queryIsBingding(info.get("id").toString(),insertParam.getNumType());
					if(isBing !=null ) {
						//已存在账户，修改
						CustCustomerUpdateBingdingEntity  updateEntity = new CustCustomerUpdateBingdingEntity();
						updateEntity.setCustId(isBing.get("custId").toString());
						updateEntity.setCode(insertParam.getCode());
						updateEntity.setNumType(insertParam.getNumType());
						updateEntity.setProfilePhoto(insertParam.getProfilePhoto());
						updateEntity.setName(insertParam.getName());
						updateEntity.setUpdateTime(new Date());
						long result = custCustomerMapper.updateBingdingInfo(updateEntity);
						if(result>0) {
							//登录
							String id = isBing.get("custId").toString();
							String redisKey = USERKEY + System.currentTimeMillis() + id;
							// 将redisKey转为hash
							 hashKey = Md5EncryptUtils.md5Encrypt(redisKey);
							// 将转为hash值以后的key作为主键存入reids中，存储的value值为用户信息对象(包括：用户名称，用户id，用户电话)并且返回该key值
							keyMap.put("success",true);
//							keyMap.put("msg", "绑定并且登录成功");
							keyMap.put("id", info.get("id"));
							keyMap.put("hashKey", hashKey);
							keyMap.put("numType",insertParam.getNumType());
							cacheService.saveCache(hashKey, keyMap, WebGlobal.CACHE_TIMES);
							return hashKey; 
						}else {
							return "error";
						}
					}else {
						//未绑定，执行绑定操作
						insertParam.getCode();
						insertParam.getName();
						insertParam.getProfilePhoto();
						insertParam.setOperator("系统操作");
						insertParam.getNumType();
						insertParam.setAddTime(new Date());
						insertParam.setIsValid("1");
						insertParam.setCustId(info.get("id").toString());
						long result = custCustomerMapper.bindingThirdparty(insertParam);
						//綁定成功后直接登录，返回信息
						if(result>0) {
							//获取id
							String id = info.get("id").toString();
							String redisKey = USERKEY + System.currentTimeMillis() + id;
							// 将redisKey转为hash
							 hashKey = Md5EncryptUtils.md5Encrypt(redisKey);
							// 将转为hash值以后的key作为主键存入reids中，存储的value值为用户信息对象(包括：用户名称，用户id，用户电话)并且返回该key值
							keyMap.put("success",true);
//							keyMap.put("msg", "绑定并且登录成功");
							keyMap.put("id", info.get("id"));
							keyMap.put("hashKey", hashKey);
							keyMap.put("numType",insertParam.getNumType());
							cacheService.saveCache(hashKey, keyMap, WebGlobal.CACHE_TIMES);
							return hashKey; 
						}else {
							//绑定第三方异常	
							return "error";
						}
					}	
				}else {
					//不存在
					//系统没有注册账号：系统注册一个账号
					CustCustomerEntity custCustomerEntity = new CustCustomerEntity();
					custCustomerEntity.setPassword("");
					custCustomerEntity.setName(insertParam.getName());
					custCustomerEntity.setMobile(insertParam.getMobile());
					custCustomerEntity.setBirthday(new Date());
					custCustomerEntity.setIsValid("1");
					custCustomerEntity.setAddTime(new Date());
					long isAdd = custCustomerMapper.saveCustCustomer(custCustomerEntity);
					//系统注册成功后：绑定第三方表
					if(isAdd>0) {
						insertParam.getCode();
						insertParam.getName();
						insertParam.getProfilePhoto();
						insertParam.setOperator("系统操作");
						insertParam.getNumType();
						insertParam.setAddTime(new Date());
						insertParam.setIsValid("1");
						insertParam.setCustId(custCustomerEntity.getId());
//						long isBing = custCustomerMapper.queryIsBingding(insertParam.getCode(),insertParam.getNumType());
//						if(isBing>0) {
//							throw new BusinessException("第三方账号已存在");
//						}
						long result = custCustomerMapper.bindingThirdparty(insertParam);
						//绑定第三方表后：直接登录
						if(result>0) {
							//获取id
							String id = custCustomerEntity.getId();
							String redisKey = USERKEY + System.currentTimeMillis() + id;
							// 将redisKey转为hash
							 hashKey = Md5EncryptUtils.md5Encrypt(redisKey);
							// 将转为hash值以后的key作为主键存入reids中，存储的value值为用户信息对象(包括：用户名称，用户id，用户电话)并且返回该key值
							keyMap.put("success",true);
							keyMap.put("id",custCustomerEntity.getId());
//							keyMap.put("msg", "绑定成功");
							keyMap.put("hashKey", hashKey);
							keyMap.put("numType",insertParam.getNumType());
							cacheService.saveCache(hashKey, keyMap, WebGlobal.CACHE_TIMES);
							return hashKey; 
						}else {
							//系统绑定第三方异常
							return "error";
						}
					}else {
						//系统注册账号失败
						return "error";
					}
				}
			}
	}

	@Override
	public String updateBingding(CustCustomerUpdateBingdingParam updateParam) {
		if(StringUtils.isEmpty(updateParam.toString()))
			throw new BusinessException("修改参数不能为空");
		CustCustomerUpdateBingdingEntity updateEntity = new CustCustomerUpdateBingdingEntity();
		updateEntity.setCustId(updateParam.getCustId());
		updateEntity.setCode(updateParam.getCode());
		updateEntity.setName(updateParam.getName());
		updateEntity.setProfilePhoto(updateParam.getProfilePhoto());
		updateEntity.setUpdateTime(new Date());
		updateEntity.setNumType(updateParam.getNumType());
		long result = custCustomerMapper.updateBingdingInfo(updateEntity);
		if(result>0) {
			String id = updateParam.getCustId();
			Map<String,Object> map = new HashMap<>();
			map.put("numType", map.get("numType"));
			map.put("code", updateParam.getCode());
			map.put("name",updateParam.getName());
			map.put("custId",updateParam.getCustId());
			map.put("profilePhoto", updateParam.getProfilePhoto());
			String redisKey = USERKEY + System.currentTimeMillis() + id;
			// 将redisKey转为hash
			 String hashKey = Md5EncryptUtils.md5Encrypt(redisKey);
			//存用户信息redis
			cacheService.saveCache(hashKey, map, WebGlobal.CACHE_TIMES);
			System.out.println(hashKey);
			return hashKey;
		}else {
			throw new BusinessException("修改失败");
		}
	}

}