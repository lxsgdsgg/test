package com.bashiju.api;

public interface RedisGetIdServiceApi {
	/**
	 * 获取房源绑定码(本方法已经将绑定码和对应的房源Id存入redis)
	 * @Title: getHouseBindCode   
	 * @Description: 获取房源绑定码 
	 * @param: @param houseId 房源ID
	 * @return: String  返回六位房源绑定码
	 */
	 public String getHouseBindCode(String houseId,String toPhone);
		/**
		 * 获取客源绑定码(本方法已经将绑定码和对应的客源Id存入redis)
		 * @Title: getHouseBindCode   
		 * @Description: 获取房源绑定码 
		 * @param: @param houseId 房源ID
		 * @return: String  返回六位客源绑定码
		 */
	 public String getCustomBindCode(String customId,String toPhone);
		/**
		 * 获取短信验证码(本方法已经将绑定码和对应的验证码存入redis)
		 * @Title: getHouseBindCode   
		 * @Description: 获取短信验证码
		 * @param: @param phoneNumber 手机号码
		 * @return: String  返回六位数字短信验证码
		 */
	 public String getMsgAuthCode(String phoneNumber);
	 
	 /**
	  * 从redis中删除码的数据
	  * @Title: DelCode   
	  * @Description: 从redis中删除码的数据  
	  * @param: key  主键
	  * @throws
	  */
	 public boolean DelCode(String key);
	 /**
	  * 获取客源编号
	  * @Title: getCustomId   
	  * @Description:获取客源编号
	  * @param: 是否是求购客源(是-求购.否-求租)
	  * @param: @return      
	  * @return: 返回客源编号     
	  * @throws
	  */
	 public String getCustomId();
	 
	 /**
	  * 获取成交编号
	  * @Title: getDealId   
	  * @Description: 获取成交编号   
	  * @param: @param mmDeal 是否是买卖成交(是-买卖.否-租赁)
	  * @param: @return      
	  * @return: 返回成交编号      
	  * @throws
	  */
	 public String getDealId(boolean mmDeal);
	 /**
	  * 获取房源编号   
	  * @Title: getHouseId   
	  * @Description: 获取房源编号   
	  * @param: areaCode 行政区域编码
	  * @param: @return      
	  * @return: 返回房源编号      
	  * @throws
	  */
	 public String getHouseId(String areaCode);
	 
	 /**
	  * 获取票据ID
	  * @Title: getTicketId   
	  * @Description: TODO(这里用一句话描述这个方法的作用)   
	  * @param: @return      
	  * @return: String      
	  * @throws
	  */
	 public String getTicketId();
}
