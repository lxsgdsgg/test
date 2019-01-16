/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  AgentAcceptanceUpdateMapper.java   
 * @Package com.bashiju.quartz.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年8月27日 下午2:33:29   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.quartz.mapper;

/**   
 * @ClassName:  AgentAcceptanceUpdateMapper   
 * @Description:经纪人受理委托变更(当前委托的经纪人要是半小时之内不处理，则变更给另一个经纪人) 
 * @author: yangz
 * @date:   2018年8月27日 下午2:33:29   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface AgentAcceptanceUpdateMapper {

	/**     
	 * @Description: 变更经纪人委托信息         
	 * @return: void      
	 */ 
	void agentAcceptanceUpdate();

}
