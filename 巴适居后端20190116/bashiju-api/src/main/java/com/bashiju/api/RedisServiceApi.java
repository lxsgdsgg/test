package com.bashiju.api;

import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName:  RedisServiceApi   
 * @Description:RedisServiceApi接口服务  
 * @author: zhaobin
 * @date:   2018年4月9日 上午10:21:18   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface RedisServiceApi {

	/*向redis存入数据*/
	public String set(final String key, final String value);
	/*从redis读取数据*/
	public String get(final String key);
	/*从redis删除数据*/
	public Long del(final String key);
	/*设置redis某个key的生存时间*/
	public Long expire(final String key, final Integer seconds);
	/*向redis存入数据并设置其生存时间*/
	public String set(final String key, final String value, final Integer seconds);
	/*向redis存入Map数据*/
	public  String setMap(String key ,Map<String,String> map);
	/*向redis存入Map数据并设置其生存时间*/
	public  String setMap(String key ,Map<String,String> map, final Integer seconds);
	/*从redis读取map数据*/
	public  Map<String,String> getMap(String key);
	/*向redis存入List数据*/
	public <T> String setList(final String key ,final List<T> list);
	/*向redis存入List数据并设置其生存时间*/
	public <T> String setList(final String key ,final List<T> list, final Integer seconds);
	/*从redis读取List数据*/
	public <T> List<T> getList(String key);
	/**
	 * 设置 Hash
	 */
	public  Long setHash(final String key ,final String field,final String value);
	/**
	 * 设置 Hash (是否设置超时)
	 */
    public  Long setHash(final String key ,final String field,final String value, boolean isExpirationTime);
	/**
	 * 获取Hash
	 */
	public  String getHash(final String key ,final String field);
}
