
package com.easou.novelpush.constants;

/**
 * @author xiaodong
 * @date 2014年10月30日 下午4:55:59
 */
public class RedisKeyConstants {
	/**
	 * 将用户的百度绑定信息保存到redis队列中，再由多台机器发送百度推送<br />
	 * key
	 */
	public static final String BAIDUINFO2REDIS_KEY = "push:novel:baiduinfo";
}
