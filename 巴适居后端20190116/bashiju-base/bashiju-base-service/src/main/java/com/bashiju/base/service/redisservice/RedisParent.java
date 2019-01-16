package com.bashiju.base.service.redisservice;

public interface RedisParent<T, E> {

	/**
	 * redis 存取的接口
	 * @param e
	 * @return
	 */
    public T callback(E e);

}
