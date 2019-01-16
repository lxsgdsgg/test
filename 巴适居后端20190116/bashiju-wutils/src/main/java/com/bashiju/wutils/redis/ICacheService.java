package com.bashiju.wutils.redis;

public interface ICacheService {
	
	/**
	 * 从redis中获取缓存数据
	 * @Title: getCache   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param key
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public String getCache(String key);
	
	public boolean delCache(String key);
	/**
	 * 保存数据到redis
	 * @Title: saveCache   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param key
	 * @param: @param value
	 * @param: @param time      
	 * @return: void      
	 * @throws
	 */
	public void saveCache(String key,Object value,int time);
	
	public void saveStringCache(String key,String value,int time);
}
