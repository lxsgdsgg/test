package com.bashiju.api;

import com.aliyuncs.dyplsapi.model.v20170525.BindAxnExtensionResponse;
import com.aliyuncs.dyplsapi.model.v20170525.UnbindSubscriptionResponse;

public interface PrivacyProtectionBaseServiceApi {
	/**
	 * 绑定手机号码
	 * @Title: bindAxnExtension   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param Phone
	 * @param: @return      
	 * @return: BindAxnExtensionResponse  
	 *返回对用关系Id;
	 * String axnExtensionSubsId = axnExtensionResponse.getSecretBindDTO() == null ?  null : axnExtensionResponse.getSecretBindDTO().getSubsId();
	 *返回隐私号码
	 * String axnExtensionSecretNo = axnExtensionResponse.getSecretBindDTO() == null ?  null : axnExtensionResponse.getSecretBindDTO().getSecretNo();
	 * 返回分机号码
	 * String axnExtension = axnExtensionResponse.getSecretBindDTO() == null ?  null : axnExtensionResponse.getSecretBindDTO().getExtension();
     * 绑定失败返回null;    
	 * @throws
	 */
	public  BindAxnExtensionResponse bindAxnExtension(String Phone);
	/**
	 * 
	 * @Title: 需要解绑的对应关系   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param subsId 对应关系Id
	 * @param: @param secretNo 隐私号码
	 * @return: boolean 解绑成功返回true,解绑失败返回false。      
	 * @throws
	 */
	public  boolean unbind(String subsId, String secretNo);
	
	/**
	 * 点击拨号服务接口
	 * @Title: clickDial   
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param: companyId 公司Id  
	 * @param: houseId 房源Id
	 * @param: @param src 经纪人手机/座机号码
	 * @param: @param dst 房东手机/座机号码    
	 * @return: boolean   成功-true;失败-false  
	 * @throws
	 */
	
    public boolean tencentCloudClickDial(String companyId,String houseId,String src,String dst);
}
